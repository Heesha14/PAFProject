package com.security;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
    public static final String AUTHENTICATION_HEADER_KEY = "Authorization";
    public static final String AUTHENTICATION_HEADER_PREFIX = "Basic ";

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        //Fetch authorization header
        List<String> authHeader = requestContext.getHeaders().get(AUTHENTICATION_HEADER_KEY);
        Method method = resourceInfo.getResourceMethod();

        //If no authorization information present; block access
        if(authHeader == null || authHeader.isEmpty()) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("You cannot access this resource").build());
            return;
        }

        if(authHeader != null && authHeader.size() > 0 ) {

            //Get encoded username and password
            String authToken = authHeader.get(0);
            authToken = authToken.replaceFirst(AUTHENTICATION_HEADER_PREFIX, "");
        	
        	System.out.println(authToken);

                //Decode username and password
        	String decodedString = "";
			try {
				byte[] decodedBytes = Base64.getDecoder().decode(
						authToken);
			  decodedString = new String(decodedBytes, "UTF-8");
			  System.out.println(decodedString);
			} catch (IOException e) {
				e.printStackTrace();
				e.getMessage();
			}

            //Split username and password tokens
            final StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

            if("admin".equals(username) && "admin".equals(password)) {
                return;
            }
            Response unauthoriazedStatus = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\" : \"not authorized 3\"}")
                    .build();
            requestContext.abortWith(unauthoriazedStatus);
        }
    }
}

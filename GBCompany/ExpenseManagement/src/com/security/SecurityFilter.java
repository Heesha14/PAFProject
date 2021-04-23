package com.security;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 * @author Jaanvi.S.C.H IT19801100
 *
 */
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
        if (authHeader == null || authHeader.isEmpty()) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("You cannot access this resource").build());
            return;
        }

        if (!method.isAnnotationPresent(PermitAll.class)) {
            //Access denied for all
            if (method.isAnnotationPresent(DenyAll.class)) {
                System.out.println("inside DenyAll");
                Response unauthorizedStatus = Response
                        .status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\" : \"access denied\"}")
                        .build();
                requestContext.abortWith(unauthorizedStatus);
            }

            if (authHeader != null && authHeader.size() > 0) {

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
                if (method.isAnnotationPresent(RolesAllowed.class)) {
                    RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                    Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

                    //Is user valid?
                    ClientConfig clientConfig = new ClientConfig();
                    HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(username, password);
                    clientConfig.register(feature);

                    clientConfig.register(JacksonFeature.class);

                    Client client = ClientBuilder.newClient(clientConfig);
                    WebTarget webTarget;
                    if (rolesSet.contains("admin")) {
                        webTarget = client.target("http://localhost:8443/UserManagement/GBCompany").path("Users/admin");
                    } else {
                        webTarget = client.target("http://localhost:8443/UserManagement/GBCompany")
                                .path("Users/admin");
                    }

                    Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

                    Response response = invocationBuilder.get();

                    if (response.getStatus() != 200) {
                        Response unauthoriazedStatus = Response.status(Response.Status.UNAUTHORIZED)
                                .entity("{\"error\" : \"not authorized 3\"}").build();
                        requestContext.abortWith(unauthoriazedStatus);

                    }
                    return;
                }

            }
        }
        Response unauthoriazedStatus = Response.status(Response.Status.UNAUTHORIZED)
                .entity("not authorized to access the resource").build();
        requestContext.abortWith(unauthoriazedStatus);

    }

}

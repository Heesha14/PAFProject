package com.rest;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.model.Users;
import com.service.UserService;
import com.service.UserServiceImpl;

/**
 * Rest controller for user management
 *
 * @author HeeshaJ
 */
@Path("/Users")
public class UserManagementRest {


    @RolesAllowed({"admin"})
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String createUser(@FormParam("username") String username, @FormParam("password") String password,
                             @FormParam("email") String email, @FormParam("phone") String phone, @FormParam("gender") String gender,
                             @FormParam("designation") String designation) {
        UserService userService = new UserServiceImpl();
        return userService.createUser(username, password, email, phone, gender, designation);

    }

    @RolesAllowed({"admin"})
    @GET
    @Path("list")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response getAllUsers() {

        UserService objUserService = new UserServiceImpl();
        GenericEntity<List<Users>> userResponse;

        try {
            userResponse = new GenericEntity<List<Users>>(objUserService.getAllUsers()) {
            };
        } catch (Exception ex) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }

        return Response.ok(userResponse).build();
    }

    @DELETE
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteUser(@FormParam("userId") String userId) throws SQLException {
        UserService userService = new UserServiceImpl();
        return userService.deleteUser(userId);

    }

    @RolesAllowed({"admin"})
    @GET
    @Path("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean checkAdmin() {

        return true;

    }

    @RolesAllowed({"admin"})
    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateUser(String request) {
        UserService userService = new UserServiceImpl();
        // Convert the input string to a JSON object
        JsonObject userObj = new JsonParser().parse(request).getAsJsonObject();
        // Read the values from the JSON object
        String aId = userObj.get("userId").getAsString();
        String username = userObj.get("username").getAsString();
        String password = userObj.get("password").getAsString();
        String email = userObj.get("email").getAsString();
        String phone = userObj.get("phone").getAsString();
        String gender = userObj.get("gender").getAsString();
        String first_name = userObj.get("first_name").getAsString();
        String last_name = userObj.get("last_name").getAsString();

        return userService.updateUser(aId, username, password, email, phone, gender, first_name, last_name);
    }


}

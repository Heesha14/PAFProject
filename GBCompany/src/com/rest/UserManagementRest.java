package com.rest;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import com.model.Users;
import com.service.UserService;
import com.service.UserServiceImpl;

/**
 * Rest controller for user management
 *
 * @author HeeshaJ
 *
 */
@Path("/Users")
public class UserManagementRest {

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

	@POST
	@Path("list")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response getDigitalProduct() {

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

}

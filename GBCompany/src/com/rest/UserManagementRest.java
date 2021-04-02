package com.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.service.UserService;
import com.service.UserServiceImpl;

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

}

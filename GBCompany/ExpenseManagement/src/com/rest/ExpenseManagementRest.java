package com.rest;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import com.model.Expenses;
import com.service.ExpenseService;
import com.service.ExpenseServiceImpl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Rest controller for user management
 *
 * @author HeeshaJ
 *
 */
@Path("/Expenses")
public class ExpenseManagementRest {

	@RolesAllowed({"admin"})
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addExpense(String request) {
		Expenses expenseModel = new Expenses();
		ExpenseService expenseService = new ExpenseServiceImpl();

		JsonObject requestBody = new JsonParser().parse(request).getAsJsonObject();

		expenseModel.setExpenseTitle(requestBody.get("expenseTitle").getAsString());
		expenseModel.setExpenseDesc(requestBody.get("expenseDesc").getAsString());
		expenseModel.setExpenseAmount(requestBody.get("expenseAmount").getAsDouble());
		expenseModel.setExpenseStatus(requestBody.get("expenseStatus").getAsString());
		expenseModel.setExpenseDate(requestBody.get("expenseDate").getAsString());

		return expenseService.insertExpense(expenseModel);

	}
	
	@RolesAllowed({"admin"})
	@PUT
	@Path("/updatExpenseStatus")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEStatus(String request)
	{
		ExpenseService expenseService = new ExpenseServiceImpl();
		//Convert the input string to a JSON object	 
		JsonObject expObject = new JsonParser().parse(request).getAsJsonObject();
	
		//Read the values from the JSON object	 
		int expenseId = expObject.get("expenseId").getAsInt();
		String paymentStatus = expObject.get("paymentStatus").getAsString();
		
		String output = expenseService.updateExpenseStatus(expenseId, paymentStatus);
	
		return output;
	}




}

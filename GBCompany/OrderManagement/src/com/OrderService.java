package com;

import model.Order;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Orders")
public class OrderService{
	Order OrderObj = new Order();

	
	
//retrieve
	
/*
@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readOrders() 
 {     
 return OrderObj.readOrders(); 
 }

*/


@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN)
public String insertOrder(@FormParam("Order_Date") String Order_Date,
						@FormParam("Order_paid_status") String Order_paid_status, 
						@FormParam("OrderDesc") String OrderDesc,
						@FormParam("buyid") String buyid,
						@FormParam("Project_ID") String Project_ID,
						@FormParam("pid") String pid
						
				)
{
	String output = OrderObj.insertOrder(Order_Date, Order_paid_status, OrderDesc, buyid,Project_ID,pid);
	return output;
	
}
}

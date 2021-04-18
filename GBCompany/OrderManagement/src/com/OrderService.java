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
	

@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readOrders() 
 {     
 return OrderObj.readOrders(); 
 }




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

//updating

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN)
public String updateItem(String OrderData)
{
	//Convert the input string to a JSON object 
	JsonObject OrderObject = new JsonParser().parse(OrderData).getAsJsonObject();
	
	//Read the values from the JSON object
	
	String Order_ID = OrderObject.get("Order_ID").getAsString();
	String Order_Date = OrderObject.get("Order_Date").getAsString();
	String Order_paid_status = OrderObject.get("Order_paid_status").getAsString();
	String OrderDesc = OrderObject.get("OrderDesc").getAsString();
	String buyid = OrderObject.get("buyid").getAsString();
	String Project_ID = OrderObject.get("Project_ID").getAsString();
	String pid = OrderObject.get("pid").getAsString();
	
	String output = OrderObj.updateOrder(Order_ID, Order_Date, Order_paid_status, OrderDesc, buyid,Project_ID,pid );
	
	return output; 
	
	
	}



































/*
@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN)
public String updateOrder(String OrderData)
{
	//Convert the input string to a JSON object 
	JsonObject OrderObject = new JsonParser().parse(OrderData).getAsJsonObject();
	
	//Read the values from the JSON object
	
	String Order_ID  = OrderObject.get("Order_ID ").getAsString();
	String Order_Date = OrderObject.get("Order_Date").getAsString();
	String Order_paid_status = OrderObject.get("Order_paid_status").getAsString();
	String OrderDesc = OrderObject.get("OrderDesc").getAsString();
	String buyid = OrderObject.get("buyid").getAsString();
	String Project_ID = OrderObject.get("Project_ID").getAsString();
	String pid = OrderObject.get("pid").getAsString();
	
	String output = OrderObj.updateOrder(Order_ID, Order_Date, Order_paid_status, OrderDesc, buyid, Project_ID,pid);
	
	return output; 
	}
*/

//deelting


@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteItem(String OrderData)
{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(OrderData, "", Parser.xmlParser());
	
	//Read the value from the element <itemID>
	String Order_ID = doc.select("Order_ID").text();
	String output = OrderObj.deleteOrder(Order_ID);
	
	return output;
	
	
}
}

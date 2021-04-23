package com;

import model.Payment;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payments") 
public class PaymentService {
	
	Payment paymentObj = new Payment();
	
	//order payment insert
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertOrderPayment(@FormParam("Order_ID") int Order_ID,
								@FormParam("amount") String amount,
								@FormParam("credit_card_no") String credit_card_no,
								@FormParam("cvv") int cvv,
								@FormParam("payment_status") String payment_status,
								@FormParam("paid_date") Date paid_date)
	{
		String output = paymentObj.insertOrderPayment(Order_ID, amount, credit_card_no, cvv, payment_status, paid_date);
		return output;
	}
	
	//order payment read
	@GET
	@Path("/read")
	@Produces(MediaType.TEXT_HTML)
	public String readOrderPayment()
	{
		return paymentObj.readOrderPayment();
	}

	
	//order payment update
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateOrderPayment(String paymentData)
	{
	
		//Convert the input string to a JSON object	 
		JsonObject payObject = new JsonParser().parse(paymentData).getAsJsonObject();
	
		//Read the values from the JSON object	 
		int pid = payObject.get("pid").getAsInt();	 
		int Order_ID = payObject.get("Order_ID").getAsInt();	 
		String amount = payObject.get("amount").getAsString();	 
		String credit_card_no = payObject.get("credit_card_no").getAsString();	 
		int cvv = payObject.get("cvv").getAsInt();
		String payment_status = payObject.get("payment_status").getAsString();
		String paid_date = payObject.get("paid_date").getAsString();
		
		String output = paymentObj.updateOrderPayment(pid, Order_ID, amount, credit_card_no, cvv, payment_status, Date.valueOf(paid_date));
	
		return output;
	}
	
	//order payment delete
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteOrderPayment(String paymentData)
	{	
		//Convert the input string to an XML document 
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

	
		//Read the value from the element <itemID>	 
		String pid = doc.select("pid").text();
	 
		String output = paymentObj.deleteOrderPayment(pid);
	
		return output;	
	}
	
	//fund payment insert
		@POST
		@Path("/addfund")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertFundPayment(@FormParam("fundId") int fundId,
									@FormParam("amount") String amount,
									@FormParam("paid_date") Date paid_date)
		{
			String output = paymentObj.insertFundPayment(fundId, amount, paid_date);
			return output;
		}
		
	//expense payment insert
		@POST
		@Path("/addexpensePayment")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public String insertExpensesToPayment(String request)
		{
			JsonObject payObject = new JsonParser().parse(request).getAsJsonObject();
			System.out.println("In payemnets");
			
			// Read the values from the JSON object
			int expenseId = payObject.get("expenseId").getAsInt();
			String amount = payObject.get("amount").getAsString();
			String payment_status = payObject.get("payment_status").getAsString();
			String paid_date = payObject.get("paid_date").getAsString();
			System.out.println(expenseId+amount+payment_status+paid_date);
			
			return paymentObj.insertExpensesPayment(expenseId, amount, payment_status, null);
		}
		
		@PUT
		@Path("/updateStatus")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public String updateEPayment(String request)
		{
		
			//Convert the input string to a JSON object	 
			JsonObject expObject = new JsonParser().parse(request).getAsJsonObject();
		
			//Read the values from the JSON object	 
			int expenseId = expObject.get("expenseId").getAsInt();
			String paymentStatus = expObject.get("paymentStatus").getAsString();
			
			String output = paymentObj.updateExpensePayment(expenseId, paymentStatus);
		
			return output;
		}
		

}

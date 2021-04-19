package com;

import model.Payment;

import java.sql.Date;

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
	
	//order payment delete
	
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
		
	//fund payment insert
	@POST
	@Path("/addexpense")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertExpensesPayment(@FormParam("expenseId") int expenseId,
									@FormParam("amount") String amount,
									@FormParam("payment_status") String payment_status,
									@FormParam("paid_date") Date paid_date)
	{
		String output = paymentObj.insertExpensesPayment(expenseId, amount, payment_status, paid_date);
		return output;
	}

}

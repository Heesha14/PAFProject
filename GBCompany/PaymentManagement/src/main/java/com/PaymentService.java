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
	
	//payment insert
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("Order_ID") int Order_ID,
								@FormParam("FundId") int FundId,
								@FormParam("expenseId") int expenseId,
								@FormParam("amount") String amount,
								@FormParam("credit_card_no") String credit_card_no,
								@FormParam("cvv") int cvv,
								@FormParam("payment_status") String payment_status,
								@FormParam("paid_date") Date paid_date)
	{
		String output = paymentObj.insertPayment(Order_ID, FundId, expenseId, amount, credit_card_no, cvv, payment_status, paid_date);
		return output;
	}

}

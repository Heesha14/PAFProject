package rest;


import service.FundService;


import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 


@Path("/Funds")
public class FundManagementRest {
	
	
		FundService fundObj = new FundService(); 

		
		@GET
		@Path("/") 
		@Produces(MediaType.TEXT_HTML) 
		public String readItems() 
		 { 
		 return fundObj.readFunds(); 
		 } 
		
		
		
		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		
		public String insertItem ( 
				@FormParam("fundCode") String fundCode, 
				@FormParam("fundType") String fundType, 
				@FormParam("amount") String amount, 
				@FormParam("date") String date,
				@FormParam("status") String status ) 
		{ 
			String output = fundObj.addFund(fundCode, fundType, amount, date,status); 
			return output; 
		}
		
		
		

		@PUT
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String updateFund(String fundData) 
		{ 
			//Convert the input string to a JSON object 
			JsonObject fundUpdateObject = new JsonParser().parse(fundData).getAsJsonObject(); 
			//Read the values from the JSON object
			String fundId = fundUpdateObject.get("fundId").getAsString(); 
			String fundCode = fundUpdateObject.get("fundCode").getAsString(); 
			String fundType = fundUpdateObject.get("fundType").getAsString(); 
			String amount = fundUpdateObject.get("amount").getAsString(); 
			String date = fundUpdateObject.get("date").getAsString();
			String status = fundUpdateObject.get("status").getAsString();
		 
			String output = fundObj.updateFund(fundId, fundCode, fundType, amount, date,status); 
			return output; 
		}

		
		
		
		
		@DELETE
		@Path("/") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deleteFund(String fundData) 
		{ 
		//Convert the input string to an XML document
		 Document document = Jsoup.parse(fundData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String fundId = document.select("fundId").text(); 
		 String output = fundObj.deleteFund(fundId); 
		return output; 
		}
		
		
		
		
		

}

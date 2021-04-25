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
	
		//Fund service object
	
		FundService fundObj = new FundService(); 

		//retrieve fund service
		
		@GET
		@Path("RetrieveFunds") 
		@Produces(MediaType.TEXT_HTML) 
		public String readItems() 
		 { 
		 return fundObj.readFunds(); 
		 } 
		
		
		//add fund service
		
		@POST
		@Path("AddFunds") 
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
		
		
		
		//update fund service
		
		@PUT
		@Path("UpdateFunds") 
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

		
		
		
		//delete fund service
		
		@DELETE
		@Path("DeleteFunds") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deleteFund(String fundData) 
		{ 

		//Convert the input string to an XML document	
		 Document document = Jsoup.parse(fundData, "", Parser.xmlParser()); 
		 
		//Read the values 
		 String fundId = document.select("fundId").text(); 
		 
		 //output 
		 String output = fundObj.deleteFund(fundId); 
		
		return output; 
		}
		
		
	
		
		//update fund state by project Manager
		@PUT
		@Path("/updateStatus")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON0)
		public String updateFundStatus(String updateStatus)
		{
		
			//Convert the input string to a JSON object	 
			JsonObject stObj = new JsonParser().parse(updateStatus).getAsJsonObject();
		
			//Read the values from the JSON object	 
			int fundId = stObj.get("fundId").getAsInt();
			String status = stObj.get("status").getAsString();
			
			String output = fundObj.updateFundStatus(fundId, status);
		
			return output;
		}
		

}

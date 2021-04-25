package service;



import dbutil.DBconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;

//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.Invocation;
//import javax.ws.rs.client.WebTarget;
//import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

//import com.google.gson.JsonObject;



import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class FundService {
	
	public static Connection con;
		
	
	
		public String readFunds() 
		{ 
		String output = ""; 
		try
		{ 
		
		con = DBconnect.getconnect();
		
		if (con == null) 
		{
			 return "Error while connecting to the database for reading."; 
		} 
		 
		 // Prepare the html fund table to be displayed
		 output = "<table border='1'>"
		 		+ "<tr>"
		 		+ "<th>Fund Code</th>"
		 		+ "<th>Fund Type</th>" 
		 		+ "<th>Amount</th>"
		 		+ "<th>Date</th>" 
		 		+ "<th>Status</th>"
		 		+ "<th>Update</th>"
		 		+ "<th>Delete</th>"
		 		+ "</tr>"; 
		 
		 //call sql query
		 String query = "select * from funds"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		
		 while (rs.next()) 
		 { 
		 String fundID = Integer.toString(rs.getInt("fundId")); 
		 String fundCode = rs.getString("fundCode"); 
		 String fundType = rs.getString("fundType"); 
		 String amount = Double.toString(rs.getDouble("amount")); 
		 String date = rs.getString("date"); 
		 String status = rs.getString("status"); 
		 
		 // Add into the html table
		 output += "<tr><td>" + fundCode + "</td>"; 
		 output += "<td>" + fundType + "</td>"; 
		 output += "<td>" + amount + "</td>"; 
		 output += "<td>" + date + "</td>"; 
		 output += "<td>" + status + "</td>"; 
		 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='#'>" 
		 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='fundId' type='hidden' value='" + fundID
		 + "'>" + "</form></td></tr>"; 
		 } 
		 con.close(); 
		
		 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while reading the fund details."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }


	
		
		//add fund for the projects
		
		public String addFund(String code, String type, String amount, String date,String status) 
		{ 
			String output = ""; 
			try
			{ 		
					//database connection
					con = DBconnect.getconnect(); 
					if (con == null) 
					{
						return "Error while connecting to the database for inserting."; 
						
					} 
		 
					// create a prepared statement
					String query = " insert into funds (fundId,fundCode,fundType,amount,date,status)" + " values (?, ?, ?, ?, ?,?)"; 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					// binding values
					preparedStmt.setInt(1, 0); 
					preparedStmt.setString(2, code); 
					preparedStmt.setString(3, type); 
					preparedStmt.setDouble(4, Double.parseDouble(amount)); 
					preparedStmt.setString(5, date); 
					preparedStmt.setString(6, status);
					
					// execute the statement
		 			preparedStmt.execute(); 
					con.close(); 
					output = "Fund detials Inserted successfully";
					System.out.println();	
		
					output += addFundPayment(code,type,amount);
					
			} 
			catch (Exception e) 
			{ 
				output = "Error while inserting the funds details"; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		 } 
		

		
		public String addFundPayment(String code,String type,String amount) {
			try {
				MediaType JSONType = MediaType.get("application/json; charset=utf-8");
				OkHttpClient client = new OkHttpClient();
				RequestBody body = RequestBody.create("{ 'fundCode':'" + code
						+ "','fundName':'"+ type
						+ "','amount':'" + amount
					
						+ "'}", JSONType);
				Request request = new Request.Builder()
						.url("http://localhost:8090/PaymentManagement/PaymentService/Payments/addfund").post(body)
						.build();

				try (Response response = client.newCall(request).execute()) {
					return response.body().string();
				}
			} catch (Exception e) {
				System.out.println("Error in Inserting Fund Payment " + e);
				e.printStackTrace();
				e.getMessage();
				return "error";
			}
			
		}


		
		//update fund details
		public String updateFund(String id, String code, String type, String amount, String date, String status)
		{ 
			String output = ""; 
			try
			{ 	
				//database connection
				con = DBconnect.getconnect(); 
				if (con == null) 
				{
					return "Error while connecting to the database for updating."; 
				} 
			
				// call the query
				String query = "UPDATE funds SET fundCode=?,fundType=?,amount=?,date=?,status=?  WHERE fundId=?"; 
			
				// create a prepared statement
				PreparedStatement preparedStmt = con.prepareStatement(query); 
			
				preparedStmt.setString(1, code); 
				preparedStmt.setString(2, type); 
				preparedStmt.setDouble(3, Double.parseDouble(amount)); 
				preparedStmt.setString(4, date);
				preparedStmt.setString(5, status);
				preparedStmt.setInt(6, Integer.parseInt(id)); 
			
				// execute the statement
				int updateFund = preparedStmt.executeUpdate(); 
				
				//check whether is it exist record
				if ( updateFund > 0 ) {
				
				//output
				output = "Fund details Updated successfully"; 
				} else
					output = "Invalid Fund Id."; 
				con.close(); 
				} 
				catch (Exception e) 
				{ 
					output = "Error while updating the fund details."; 
					System.err.println(e.getMessage()); 
				} 
				return output; 
			} 
		
		
		
		
		//delete fund record
		public String deleteFund(String fundId) 
		{ 
			String output = ""; 
			try
			{ 
				//database connection
				con = DBconnect.getconnect(); 
				if (con == null) 
				{
					return "Error while connecting to the database for deleting."; 
				} 
				
				
			
				// call the query
				String query = "delete from funds where fundId=?"; 
				
			
				//create the prepared statement
				PreparedStatement preparedStmt = con.prepareStatement(query); 
			
				preparedStmt.setInt(1, Integer.parseInt(fundId)); 
				// execute the statement
				int deleteFund = preparedStmt.executeUpdate(); 
				
				if ( deleteFund > 0 ) {

					output = "Fund details Deleted successfully";
				}
				else {
					output = "Invalid fund Id";
				}
				con.close(); 
				 
				
				
			}
			catch (Exception e) 
			{ 
				output = "Error while deleting the fund details."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		} 
		
		
		//update fund-status by project Manager 
		public String updateFundStatus( int fundId,String status)
		{ 
			String output = ""; 
			try
			{ 	
				//database connection
				con = DBconnect.getconnect(); 
				if (con == null) 
				{
					return "Error while connecting to the database for updating."; 
				} 
			
				// call the query to update status of the fund table
				String query = "UPDATE funds SET status=?  WHERE fundId=?"; 
			
				// create a prepared statement
				PreparedStatement preparedStmt = con.prepareStatement(query); 
			
				
				preparedStmt.setString(1, status);
				preparedStmt.setInt(2, fundId); 
			
				// execute the statement
				int updateFund = preparedStmt.executeUpdate(); 
				
				//check wheather is it exis record
				if ( updateFund > 0 ) {
				
				//output
				output = "Fund status record Updated successfully"; 
				} else
					output = "Invalid Fund Id."; 
				con.close(); 
				} 
				catch (Exception e) 
				{ 
					output = "Error while updating the fund status."; 
					System.err.println(e.getMessage()); 
				} 
				return output; 
			} 
				
		


}

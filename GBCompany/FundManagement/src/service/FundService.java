package service;

import dbutil.DBconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FundService {
	
	public static Connection con;
		
		public String readFunds() 
		 { 
		 String output = ""; 
		 try
		 { 
		
		con = DBconnect.getconnect();
		 if (con == null) 
		 {return "Error while connecting to the database for reading."; } 
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Fund Code</th><th>Fund Type</th>" +
		 "<th>Amount</th>" + 
		 "<th>Date</th>" + "<th>Status</th>" +
		 "<th>Update</th><th>Delete</th></tr>"; 
		 
		 String query = "select * from funds"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
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
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while reading the items."; 
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
		 output = "Inserted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while inserting the funds details"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
		
		
		//update fund details
		
		public String updateFund(String id, String code, String type, String amount, String date, String status)
		{ 
			 String output = ""; 
			 try
			 { 
			 con = DBconnect.getconnect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
			 // create a prepared statement
			 String query = "UPDATE funds SET fundCode=?,fundType=?,amount=?,date=?,status=?  WHERE fundId=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, code); 
			 preparedStmt.setString(2, type); 
			 preparedStmt.setDouble(3, Double.parseDouble(amount)); 
			 preparedStmt.setString(4, date);
			 preparedStmt.setString(5, status);
			 preparedStmt.setInt(6, Integer.parseInt(id)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while updating the fund details."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
		
		
		
		
		
		public String deleteFund(String fundId) 
		 { 
		 String output = ""; 
		 try
		 { 
		 con = DBconnect.getconnect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 // create a prepared statement
		 String query = "delete from funds where fundId=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(fundId)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the fund details."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
		


}

package model;

import java.sql.*;

public class Payment {
	
	//A common method to connect to the DB
	private Connection connect()
	{	 
		Connection con = null;
	 
		try	 
		{	 
			Class.forName("com.mysql.cj.jdbc.Driver");
	 
			//Provide the correct details: DBServer/DBName, username, password	 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gb", "root", "heshikihalpe95");	 
		}	 
		catch (Exception e)	 
		{e.printStackTrace();}
		
		return con;
	}
	
	public String insertPayment(int orderID, int fundID, int expenseId, String amount, String creditCardNo, int cvv, String paymentStatus, Date paidDate)
	{
	 
		String output = "";
	 
		try
	 
		{	 
			Connection con = connect();
	 
			if (con == null)	 
			{return "Error while connecting to the database for inserting."; }
	 
			// create a prepared statement	 
			String query = " insert into payments(`pid`, `Order_ID`,`FundId`,`expenseId`,`amount`,`credit_card_no`,`cvv`,`payment_status`,`paid_date`)"	 
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	 
			PreparedStatement preparedStmt = con.prepareStatement(query);
	 
			// binding values	 
			preparedStmt.setInt(1, 0);	 
			preparedStmt.setInt(2, orderID);	 
			preparedStmt.setInt(3, fundID);
			preparedStmt.setInt(4, expenseId);
			preparedStmt.setDouble(5, Double.parseDouble(amount));
			preparedStmt.setString(6, creditCardNo);
			preparedStmt.setInt(7, cvv);
			preparedStmt.setString(8, paymentStatus);
			preparedStmt.setDate(9, paidDate);
			
			// execute the statement	 
			preparedStmt.execute();	 
			con.close();	 
			output = "Inserted successfully";	 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the payment."; 
			System.err.println(e.getMessage()); 
		}	 
		return output;
	}

}

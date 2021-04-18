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
	
	//insert order payment
	public String insertOrderPayment(int orderID, String amount, String creditCardNo, int cvv, String paymentStatus, Date paidDate)
	{
	 
		String output = "";
	 
		try
	 
		{	 
			Connection con = connect();
	 
			if (con == null)	 
			{return "Error while connecting to the database for inserting."; }
	 
			// create a prepared statement	 
			String query = " insert into order_payments(`pid`,`Order_ID`,`amount`,`credit_card_no`,`cvv`,`payment_status`,`paid_date`)"	 
					+ " values (?, ?, ?, ?, ?, ?, ?)";
	 
			PreparedStatement preparedStmt = con.prepareStatement(query);
	 
			// binding values	 
			preparedStmt.setInt(1, 0);	 
			preparedStmt.setInt(2, orderID);
			preparedStmt.setDouble(3, Double.parseDouble(amount));
			preparedStmt.setString(4, creditCardNo);
			preparedStmt.setInt(5, cvv);
			preparedStmt.setString(6, paymentStatus);
			preparedStmt.setDate(7, paidDate);
			
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
	
	//insert fund payment
	public String insertFundPayment(int fundID, String amount, Date paidDate)
	{
	 
		String output = "";
	 
		try
	 
		{	 
			Connection con = connect();
	 
			if (con == null)	 
			{return "Error while connecting to the database for inserting."; }
	 
			// create a prepared statement	 
			String query = " insert into fund_payments(`fundPayid`,`fundId`,`amount`,`paid_date`)"	 
					+ " values (?, ?, ?, ?)";
	 
			PreparedStatement preparedStmt = con.prepareStatement(query);
	 
			// binding values	 
			preparedStmt.setInt(1, 0);	 
			preparedStmt.setInt(2, fundID);
			preparedStmt.setDouble(3, Double.parseDouble(amount));
			preparedStmt.setDate(4, paidDate);
			
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

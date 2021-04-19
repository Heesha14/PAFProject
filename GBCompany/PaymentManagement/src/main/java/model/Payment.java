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
	
	//retrieve order payments
	public String readOrderPayment()
	{
		String output = "";	 
		try	 
		{	 
			Connection con = connect();	 
			if (con == null)	 
			{return "Error while connecting to the database for reading."; }
			
			// Prepare the html table to be displayed	 
			output = "<table border='1'><tr><th>PID</th><th>OrderID</th>" +	 
					"<th>Payment amount</th>" +	 
					"<th>Credit Card No</th>" +	 
					"<th>CVV</th>" + 
					"<th>Payment Status</th>" + 
					"<th>Paid Date</th>" + 
					"<th>Update</th><th>Remove</th></tr>";
	 
			String query = "select * from order_payments";	 
			Statement stmt = con.createStatement();	 
			ResultSet rs = stmt.executeQuery(query);
	 
			// iterate through the rows in the result set	 
			while (rs.next())	 
			{	 
				String pid = Integer.toString(rs.getInt("pid"));
				String Order_ID = Integer.toString(rs.getInt("Order_ID"));
				String amount = Double.toString(rs.getDouble("amount"));
				String credit_card_no = rs.getString("credit_card_no");
				String cvv = Integer.toString(rs.getInt("cvv"));
				String payment_status = rs.getString("payment_status");	 
				Date paid_date = rs.getDate("paid_date");
	 
				// Add into the html table	 
				output += "<tr><td>" + pid + "</td>";	 
				output += "<td>" + Order_ID + "</td>";	 
				output += "<td>" + amount + "</td>";	 
				output += "<td>" + credit_card_no + "</td>";
				output += "<td>" + cvv + "</td>";
				output += "<td>" + payment_status + "</td>";
				output += "<td>" + paid_date + "</td>";
	 
				// buttons	 
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"	 
						+ "<td><form method='post' action='order_payments.jsp'>"	 
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"	 
						+ "<input name='pid' type='hidden' value='" + pid	 
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
	
	//update order payments
	
	//delete order payments
	
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
	
	//insert expenses payment
		public String insertExpensesPayment(int expenseID, String amount, String expenseStatus, Date paidDate)
		{
		 
			String output = "";
		 
			try
		 
			{	 
				Connection con = connect();
		 
				if (con == null)	 
				{return "Error while connecting to the database for inserting."; }
		 
				// create a prepared statement	 
				String query = " insert into expenses_payments(`exPayid`,`expenseId`,`amount`,`payment_status`,`paid_date`)"	 
						+ " values (?, ?, ?, ?,?)";
		 
				PreparedStatement preparedStmt = con.prepareStatement(query);
		 
				// binding values	 
				preparedStmt.setInt(1, 0);	 
				preparedStmt.setInt(2, expenseID);
				preparedStmt.setDouble(3, Double.parseDouble(amount));
				preparedStmt.setString(4, expenseStatus);
				preparedStmt.setDate(5, paidDate);
				
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

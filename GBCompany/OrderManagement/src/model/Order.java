package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Order {

	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/order_management", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("db connection error");
		}
		return con;
	}
	
	
	
	

	public String insertOrder(String Date, String paid_status, String Desc, String buyerid, String Projid, String payid ) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " INSERT INTO `order`(`Order_ID`, `Order_Date`, `Order_paid_status`, `OrderDesc`, `buyid`, `Project_ID`, `pid`) "
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			System.out.println(query);
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Date);
			preparedStmt.setString(3, paid_status);
			preparedStmt.setString(4, Desc);
			preparedStmt.setString(5, buyerid);
			preparedStmt.setString(6, Projid);
			preparedStmt.setString(7, payid);
			
		;
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			e.printStackTrace();
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	//retrieving data
	
	public String readOrders() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr></th><th>Order_Date</th>" + "<th>Order_paid_status</th>"
					+ "<th>OrderDesc</th>" + "<th>buyid</th>" + "<th>Project_ID</th>"+ "<th>pid</th>";

			String query = "select * from `order`";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String Order_ID = Integer.toString(rs.getInt("Order_ID"));
				String Order_Date = rs.getString("Order_Date");
				String Order_paid_status = rs.getString("Order_paid_status");
				String OrderDesc = rs.getString("Order_paid_status");
				String buyid = rs.getString("buyid");
				String Project_ID = rs.getString("Project_ID");
				String pid = rs.getString("pid");
				
				
				
				// Add into the html table
				output += "<tr><td>" + Order_Date + "</td>";
				output += "<td>" + Order_paid_status + "</td>";
				output += "<td>" + OrderDesc + "</td>";
				output += "<td>" + buyid + "</td>";
				output += "<td>" + Project_ID + "</td>";
				output += "<td>" + pid + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='#'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name= 'Order_ID' type='hidden' value='" + Order_ID + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
			} catch (Exception e) {
				e.printStackTrace();
			output = "Error while reading the orders.";
			System.err.println(e.getMessage());
		}
		return output;
	
	}
	
	//UPDATING 
	
	public String updateOrder(String OrderID, String orderdate, String paidstatus, String orderdescription, String buyerid,String proID, String paymentID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE `order` SET Order_Date=?,Order_paid_status=?,OrderDesc=?,buyid=?,Project_ID=?,pid=?  WHERE Order_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, orderdate);
			preparedStmt.setString(2, paidstatus);
			preparedStmt.setString(3, orderdescription);
			preparedStmt.setString(4, buyerid);
			preparedStmt.setString(5, proID);
			preparedStmt.setString(6, paymentID);
			preparedStmt.setInt(7, Integer.parseInt(OrderID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public String updateOrder(String OrderID, String OrderDate, String Orderpaidstatus, String Orderdescription, String bid,String projID,String payid) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE `order` SET Order_Date=?,Order_paid_status=?,OrderDesc =?,buyid=?,Project_ID=?,pid=?  WHERE Order_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, OrderDate);
			preparedStmt.setString(2, Orderpaidstatus);
			preparedStmt.setString(3, Orderdescription);
			preparedStmt.setString(4, bid);
			preparedStmt.setString(5, projID);
			preparedStmt.setString(6, payid);
			preparedStmt.setInt(7, Integer.parseInt(OrderID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the order.";
			System.err.println(e.getMessage());
		}
		return output;
	} */
	
	//deleting
	
	
	public String deleteOrder(String Order_ID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from `order` where Order_ID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(Order_ID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the order."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
}
	
	
	
	
	
	
	
	



	
	
	
	
	
	
	
	
	
	

	


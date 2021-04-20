package model;

import java.sql.*;

public class Project {
 // A common method to connect to the DB
 //DB connection added
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projects_db", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertProject(String pmid, String fbid, String Project_Name, String Start_Date, String Deadline_Date, String Project_Status, String Project_Fund_Amt, String Project_Sell_Amt) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			System.out.println("Connection = "+con);
			// create a prepared statement
			String query = " insert into Project(`Project_ID`,`pmid`,`fbid`,`Project_Name`,`Start_Date`,`Deadline_Date`,`Project_Status`,`Project_Fund_Amt`,`Project_Sell_Amt`)"
					+ " values (?,?,?,?,?, ?, ?, ?, ?)";
			
			System.out.println("SQL statemen= "+query);

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, pmid);
			preparedStmt.setString(3, fbid);
			preparedStmt.setString(4, Project_Name);
			preparedStmt.setString(5, Start_Date);
			preparedStmt.setString(6, Deadline_Date);
			preparedStmt.setString(7, Project_Status);
			preparedStmt.setString(8, Project_Fund_Amt);
			preparedStmt.setString(9, Project_Sell_Amt);

// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
			System.out.println("Output before exception= "+output);
			
		} catch (Exception e) {
			output = "Error while inserting the project.";
			System.err.println(e.getMessage());
			System.out.println("Output after exception= "+output);
			e.printStackTrace();
		}
		return output;
	}


	public String readProjects() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>PM ID</th><th>FB ID</th><th>Project_Name</th><th>Start_Date</th><th>Deadline_Date</th><th>Project_Status</th><th>Project_Fund_Amt</th><th>Project_Sell_Amt</th>" 
			+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from project";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Project_ID = Integer.toString(rs.getInt("Project_ID"));
				String pmid = rs.getString("pmid");
				String fbid = rs.getString("fbid");
				String Project_Name = rs.getString("Project_Name");
				String Start_Date = rs.getString("Start_Date");
				String Deadline_Date = rs.getString("Deadline_Date");
				String Project_Status = rs.getString("Project_Status");
				String Project_Fund_Amt = rs.getString("Project_Fund_Amt");
				String Project_Sell_Amt = rs.getString("Project_Sell_Amt");
				// Add into the html table
				output += "<tr><td>" + pmid + "</td>";
				output += "<td>" + fbid + "</td>";
				output += "<td>" + Project_Name + "</td>";
				output += "<td>" + Start_Date + "</td>";
				output += "<td>" + Deadline_Date + "</td>";
				output += "<td>" + Project_Status + "</td>";
				output += "<td>" + Project_Name + "</td>";
				output += "<td>" + Project_Fund_Amt + "</td>";
				output += "<td>" + Project_Sell_Amt + "</td></tr>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='projects.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='Project_ID' type='hidden' value='" + Project_ID + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the projects.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateProject(String Project_ID, String pmid, String fbid, String Project_Name, String Start_Date, String Deadline_Date, String Project_Status, String Project_Fund_Amt, String Project_Sell_Amt) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE project SET pmid=?,fbid=?,Project_Name=?,Start_Date=?,Deadline_Date=?,Project_Status=?,Project_Fund_Amt=?,Project_Sell_Amt=? WHERE Project_ID=? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, pmid);
			preparedStmt.setString(2, fbid);
			preparedStmt.setString(3, Project_Name);
			preparedStmt.setString(4, Start_Date);
			preparedStmt.setString(5, Deadline_Date);
			preparedStmt.setString(6, Project_Status);
			preparedStmt.setString(7, Project_Fund_Amt);
			preparedStmt.setString(8, Project_Sell_Amt);
			preparedStmt.setString(9, Project_ID);
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

	public String deleteProject(String Project_ID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from project where Project_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Project_ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the project.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
}

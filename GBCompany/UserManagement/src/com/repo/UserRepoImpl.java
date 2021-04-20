package com.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbutil.DBConn;
import com.model.Users;

public class UserRepoImpl implements UserRepo {


	private static Connection conn;

	@Override
	public String createUser(String username, String password, String email, String phone, String gender, String designation) {
		String sql = null;
		String output;
		try {
			conn = DBConn.getConnection();

			if (designation.equalsIgnoreCase("AD")) {
				sql = "INSERT INTO Admin(`username`,`password`,`email`,`phone`,`gender`,`designation`) " + "VALUES (?,?,?,?,?,?)";
			} else if (designation.equalsIgnoreCase("PM")) {
				sql = "INSERT INTO Project_Manager(`username`,`password`,`email`,`phone`,`gender`,`designation`) " + "VALUES (?,?,?,?,?,?)";

			} else if (designation.equalsIgnoreCase("FB")) {
				sql = "INSERT INTO Funding_Body(`username`,`password`,`email`,`phone`,`gender`,`designation`) " + "VALUES (?,?,?,?,?,?)";

			} else if (designation.equalsIgnoreCase("BY")) {
				sql = "INSERT INTO Buyer(`username`,`password`,`email`,`phone`,`gender`,`designation`) " + "VALUES (?,?,?,?,?,?)";

			} else {
				return "Designation type invalid";
			}

			System.out.println("Queryyyyyyyyyyyyyy  " + sql);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, phone);
			preparedStatement.setString(5, gender);
			preparedStatement.setString(6, designation);

			preparedStatement.execute();

			output = "Registration successfull";

			addUsertoUserTable(username, password, designation);

			return output;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return "Registration error";

		} finally {
			/*
			 * database connectivity closed at the end of transaction
			 */
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public List<Users> getUsersList() throws SQLException {
		List<Users> userList = new ArrayList<>();
		String sql = null;
		try {
			conn = DBConn.getConnection();
			sql = "SELECT * FROM admin \n" +
					"UNION \n" +
					"SELECT * FROM funding_body \n" +
					"UNION \n" +
					"SELECT * FROM project_manager \n" +
					"UNION \n" +
					"SELECT * FROM buyer\n";

			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Users users = new Users();
				users.setId(rs.getString("aId"));
				users.setFirst_name(rs.getString("first_name"));
				users.setLast_name(rs.getString("last_name"));
				users.setDesignation(rs.getString("designation"));
				users.setPhone(rs.getString("phone"));
				users.setEmail(rs.getString("email"));
				users.setGender(rs.getString("gender"));
				users.setUsername(rs.getString("username"));
				users.setPassword(rs.getString("password"));
				users.setRegisterDate(rs.getString("registerDate"));

				userList.add(users);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}
		return userList;
	}

	@Override
	public String deleteUser(String userId) throws SQLException {

		String userRole = userId.substring(0, 2);
		String sql = null;
		String result;
		System.out.println(userRole);
		try {
			conn = DBConn.getConnection();

			if (userRole.equalsIgnoreCase("AD")) {
				sql = "DELETE FROM ADMIN WHERE aId = ? ";
				System.out.println(sql);
			} else if (userRole.equalsIgnoreCase("PM")) {
				sql = "DELETE FROM Project_Manager WHERE pmID = ? ";
			} else if (userRole.equalsIgnoreCase("FB")) {
				sql = "DELETE FROM Funding_Body WHERE fbId = ? ";
			} else if (userRole.equalsIgnoreCase("BY")) {
				sql = "DELETE FROM Buyer WHERE buyId = ? ";
			} else {
				return "Invalid UserId";
			}

			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, userId);

			preparedStatement.execute();
			conn.close();
			result = "User deleted successfully";

		} catch (Exception e) {
			result = "Error when deleting user";
			e.printStackTrace();
		}
		return result;

	}

	public void addUsertoUserTable(String username, String password, String role) {
		String sql = null;
		String output;
		try {
			conn = DBConn.getConnection();

			sql = "INSERT INTO Users(`uId`,`username`,`password`,`role`) " + "VALUES (?,?,?,?)";

			System.out.println("Queryyyyyyyyyyyyyy  " + sql);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, role);

			preparedStatement.execute();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();

		} finally {
			/*
			 * database connectivity closed at the end of transaction
			 */
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public String updateUser(String Id, String username, String password, String email, String phone, String gender, String firstName, String lastName) {
		String output = "";
		String sql = null;
		String userId = Id.substring(0, 2);
		System.out.println(userId);
		try {
			conn = DBConn.getConnection();
			if (conn == null) {
				return "Error while connecting to the database for updating.";
			}
			if (userId.contains("AD")) {
				sql = "UPDATE `Admin` SET `username`= ? ,`password`= ? ,`email`= ?,`phone`= ?,`gender`= ?," +
						"`first_name`= ?,`last_name`= ? ,designation = 'admin' WHERE `aID` = ?";
				System.out.println(sql);
			} else if (userId.equalsIgnoreCase("PM")) {
				sql = "UPDATE `Project_Manager` SET `username`= ? ,`password`= ? ,`email`= ?,`phone`= ?,`gender`= ?," +
						"`first_name`= ?,`last_name`= ? ,designation = 'project_manager' WHERE `pmId` = ?";
			} else if (userId.equalsIgnoreCase("FB")) {
				sql = "UPDATE `Funding_Body` SET `username`= ? ,`password`= ? ,`email`= ?,`phone`= ?,`gender`= ?," +
						"`first_name`= ?,`last_name`= ? ,designation = 'funding_body' WHERE `fbId` = ?";
			} else if (userId.equalsIgnoreCase("BY")) {
				sql = "UPDATE `Buyer` SET `username`= ? ,`password`= ? ,`email`= ?,`phone`= ?,`gender`= ?," +
						"`first_name`= ?,`last_name`= ? ,designation = 'buyer' WHERE `buyId` = ?";
			} else {
				return "Invalid UserId";
			}
// create a prepared statement
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
// binding values
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, phone);
			preparedStatement.setString(5, gender);
			preparedStatement.setString(6, firstName);
			preparedStatement.setString(7, lastName);
			preparedStatement.setString(8, Id);
// execute the statement
			preparedStatement.execute();
			conn.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the user.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	@Override
	public String getAllUsers() {
		String output;
		String sql = null;
		try {
			conn = DBConn.getConnection();
			sql = "SELECT * FROM admin \n" +
					"UNION \n" +
					"SELECT * FROM funding_body \n" +
					"UNION \n" +
					"SELECT * FROM project_manager \n" +
					"UNION \n" +
					"SELECT * FROM buyer\n";

			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			output = "<table border=\"1\">"
					+ "<tr><th> User ID </th>"
					+ "<th>First Name</th>"
					+ "<th>Last Name</th>"
					+ "<th>Designation</th>"
					+ "<th>Phone Number</th>"
					+ "<th>Email</th>"
					+ "<th>Gender</th>"
					+ "<th>Username</th>"
					+ "<th>Pasword</th>"
					+ "<th>Register Date</th></tr>";

			while (rs.next()) {
				String id = rs.getString("aId");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String designation = rs.getString("designation");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String registerDate = rs.getString("registerDate");

				output += "<tr><td>" + id + "</td>";
				output += "<td>" + first_name + "</td>";
				output += "<td>" + last_name + "</td>";
				output += "<td>" + designation + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + username + "</td>";
				output += "<td>" + password + "</td>";
				output += "<td>" + registerDate + "</td></tr>";

			}
			conn.close();
			output += "</table>";
		} catch (Exception e) {
			output = "Error while retieving the user.";
			System.err.println(e.getMessage());
		}
		return output;

	}
}
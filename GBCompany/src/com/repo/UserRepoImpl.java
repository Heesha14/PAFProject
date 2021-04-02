package com.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dbutil.DBConn;

public class UserRepoImpl implements UserRepo{


	private static Connection conn;

	@Override
	public String createUser(String username, String password, String email, String phone, String gender,String designation) {
		String sql = null;
		System.out.println("designation:   "+designation );
		try {
			conn = DBConn.getConnection();
			
			if(designation.equalsIgnoreCase("AD")) {
				sql = "INSERT INTO Admin(`username`,`password`,`email`,`phone`,`gender`,`designation`) " + "VALUES (?,?,?,?,?,?)";
			}
			else if(designation.equalsIgnoreCase("PM")) {
				sql = "INSERT INTO Project_Manager(`username`,`password`,`email`,`phone`,`gender`,`designation`) " + "VALUES (?,?,?,?,?,?)";

			}
			else if(designation.equalsIgnoreCase("FB")) {
				sql = "INSERT INTO Funding_Body(`username`,`password`,`email`,`phone`,`gender`,`designation`) " + "VALUES (?,?,?,?,?,?)";

			}
			else if(designation.equalsIgnoreCase("BY")) {
				sql = "INSERT INTO Buyer(`username`,`password`,`email`,`phone`,`gender`,`designation`) " + "VALUES (?,?,?,?,?,?)";

			}else {
				return "Designation type invalid";
			}

			System.out.println("Queryyyyyyyyyyyyyy  "+ sql);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, phone);
			preparedStatement.setString(5, gender);
			preparedStatement.setString(6, designation);

			preparedStatement.execute();

			return "Registration successfull";

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
}

package com.repo;

import java.sql.SQLException;
import java.util.List;

import com.model.Users;

/**
 * @author HeeshaJ
 *
 */
public interface UserRepo {

	public String createUser(String username, String password, String email, String phone, String gender,String designation);


	/**
	 * Gets all user details from User table
	 *
	 * @return the user details.
	 * @throws SQLException
	 */
	public List<Users> getUsersList() throws SQLException;

}
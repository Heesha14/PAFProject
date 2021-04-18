package com.repo;

import java.sql.SQLException;
import java.util.List;

import com.model.Users;

/**
 * @author HeeshaJ
 *
 */
public interface UserRepo {

	/**
	 *
	 * @param username
	 * @param password
	 * @param email
	 * @param phone
	 * @param gender
	 * @param designation
	 * @return
	 */
	public String createUser(String username, String password, String email, String phone, String gender,String designation);


	/**
	 * Gets all user details from User table
	 *
	 * @return the user details.
	 * @throws SQLException
	 */
	public List<Users> getUsersList() throws SQLException;

	/**
	 * Delete user respectively
	 *
	 * @return the deleted message.
	 * @throws SQLException
	 */
	public String deleteUser(String userId) throws SQLException;

	String updateUser(String userId, String username, String password, String email, String phone, String gender, String firstName, String lastName);
}
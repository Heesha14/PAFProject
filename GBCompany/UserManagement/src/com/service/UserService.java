package com.service;

import java.sql.SQLException;
import java.util.List;

import com.model.Users;

/**
 * @author HeeshaJ
 *
 */
public interface UserService {

	public String createUser(String username, String password, String email, String phone, String gender,String designation);

	/**
	 * Gets all details of users
	 *
	 * @return the list of users.
	 * @throws SQLException
	 */
	public List<Users> getAllUsers() throws SQLException;

	/**
	 * Delete user respectively
	 *
	 * @return the deleted message.
	 * @throws SQLException
	 */
	public String deleteUser(String userId) throws SQLException;
}

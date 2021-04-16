package com.service;

import java.sql.SQLException;
import java.util.List;

import com.model.Users;
import com.repo.UserRepo;
import com.repo.UserRepoImpl;

public class UserServiceImpl implements UserService {

	UserRepo userRepo = new UserRepoImpl();

	@Override
	public String createUser(String username, String password, String email, String phone, String gender,
							 String designation) {
		return userRepo.createUser(username, password, email, phone, gender, designation);
	}

	@Override
	public List<Users> getAllUsers() throws SQLException {
		return userRepo.getUsersList();
	}

	@Override
	public String deleteUser(String userId) throws SQLException {
		System.out.println("deleteUser service");
		return userRepo.deleteUser(userId);
	}

	@Override
	public String getUserByID() throws SQLException {
		return null;
	}

}

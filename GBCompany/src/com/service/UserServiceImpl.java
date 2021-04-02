package com.service;

import com.repo.UserRepo;
import com.repo.UserRepoImpl;

public class UserServiceImpl implements UserService {
	
	UserRepo userRepo = new UserRepoImpl();

	@Override
	public String createUser(String username, String password, String email, String phone, String gender,
			String designation) {
		return userRepo.createUser(username, password, email, phone, gender, designation);
	}

}

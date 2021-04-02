package com.repo;

public interface UserRepo {
	
	public String createUser(String username, String password, String email, String phone, String gender,String designation);
}
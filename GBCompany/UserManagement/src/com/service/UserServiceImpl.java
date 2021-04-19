package com.service;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import com.model.Users;
import com.repo.UserRepo;
import com.repo.UserRepoImpl;

public class UserServiceImpl implements UserService {

    UserRepo userRepo = new UserRepoImpl();

    @Override
    public String createUser(String username, String password, String email, String phone, String gender,
                             String designation) {
        String validation = "";
        validation = validateInput(email, phone, gender, password,designation);

        if (validation.equalsIgnoreCase("Success")) {
            return userRepo.createUser(username, password, email, phone, gender, designation);
        } else {
            return validation;
        }

    }

    @Override
    public List<Users> getAllUsers() throws SQLException {
        return userRepo.getUsersList();
    }

    @Override
    public String deleteUser(String userId) throws SQLException {
        return userRepo.deleteUser(userId);
    }

    @Override
    public String getUserByID() throws SQLException {
        return null;
    }

    @Override
    public String updateUser(String userId, String username, String password, String email, String phone, String gender,
                             String firstName, String lastName) {
        return userRepo.updateUser(userId, username, password, email, phone, gender, firstName, lastName);
    }

    @SuppressWarnings("unused")
	public String validateInput(String email, String mobile, String gender, String password,String designation) {
        String result = "Success";

		if (email == null || email.equals("")) {
			return "Email cannot be empty";
		}
        else {
			// check the validity of email
			/// ^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/
			String emailregex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
					+ "A-Z]{2,7}$";
			Pattern pat = Pattern.compile(emailregex);
			if (!pat.matcher(email).matches())
				return "Invalid Email";

		}
		if (mobile == null || mobile.equals("")) {
			return "Mobile number cannot be empty";
		}
        else{
            // check the validity of mobile
            String mobileregex = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
            Pattern pat1 = Pattern.compile(mobileregex);

            if (!pat1.matcher(mobile).matches()) {
               return "Invalid mobile number";
            }
        }
        if(gender == null || gender.equals(""))
        	return "Enter gender Male(M) or Female(F) ";
        else {
            if (!gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F")) 
                return "Gender must be Male(M) or Female(F)";

        }
		if(designation == null || designation.equals(""))
			return "Enter designation Admin(AD) or Project Manager(PM) or Funding Body(FB) or Buyer(BY) ";
		else if(!designation.equalsIgnoreCase("AD") || designation.equalsIgnoreCase("PM")
					|| designation.equalsIgnoreCase("FB")|| designation.equalsIgnoreCase("BY"))
				return "Designation must be Admin(AD) or Project Manager(PM) or Funding Body(FB) or Buyer(BY)";

		if (password == null || password.equals(""))
			return "Password cannot be empty";
		else {
			String passwordregex = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
			Pattern pat2 = Pattern.compile(passwordregex);
			if (!pat2.matcher(password).matches())
				return "Password should contain at least one numeric digit, one uppercase and one lowercase letter";
		}
        return result;

    }

}
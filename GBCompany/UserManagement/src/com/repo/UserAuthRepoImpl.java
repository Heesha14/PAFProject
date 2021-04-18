//package com.repo;
//
//import com.dbutil.DBConn;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.Set;
//
//public class UserAuthRepoImpl implements  UserAuthRepo{
//
//    private static Connection conn;
//
//    @Override
//    public boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet)
//    {
//        boolean isAllowed = false;
//        String role = "" , user = "", psw = "" ;
//        String sql = null;
//
//        try {
//            conn = DBConn.getConnection();
//
//            // create a prepared statement
//            sql = "SELECT * FROM USERS WHERE username = ? AND password = ?";
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            preparedStatement.setString(1,username);
//            preparedStatement.setString(2,password);
//
//            preparedStatement.execute();
//            //execute the statement
//            ResultSet rs = preparedStatement.executeQuery(sql);
//
//            if(rs.next()) {
//                user = rs.getString("username");
//                psw = rs.getString("password");
//                role=rs.getString("role");
//
//            }
//            conn.close();
//
//
//        } catch (Exception e) {
//
//            System.err.println(e.getMessage());
//        }
//
//        if(username.equals(user) && password.equals(psw))
//        {
//
//            //Step 2. Verify user role
//            if(rolesSet.contains(role))
//            {
//
//                isAllowed = true;
//                System.out.println(username +" "+password );
//            }
//        }
//        return isAllowed;
//
//    }
//}

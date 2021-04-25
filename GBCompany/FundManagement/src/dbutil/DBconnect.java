package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnect {
	
	//A common method to connect to the DB
	
	public static Connection getconnect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "root"); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 }
	
	
	
}

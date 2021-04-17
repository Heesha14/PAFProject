package DButil;

import java.sql.*;

public class DBCon {
	
	//Connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gbdb", "root", "heshikihalpe95");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }

}
package com.utils;
import java.sql.*;
public class DbUtils {
	private static String driverName="com.mysql.cj.jdbc.Driver";
	private String URL="jdbc:mysql://localhost:3306/blackjack?serverTimezone=UTC";
	private String username="root";
	private String password="root";
	private Connection conn=null;
	static{
		try{
			Class.forName(driverName);
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}
public Connection getConn()
{
	try{
		conn=DriverManager.getConnection(URL, username, password);
	}catch (SQLException e){
		e.printStackTrace();
	}
	return conn;
}
public void closeConn(Connection conn)
{
	if(conn!=null)
	{
		try{
			conn.close();
			conn=null;	
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
}

	
}


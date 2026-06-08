package org.OnlineExamSystem.repositiory;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnection {
	

		private static Connection conn;
		
		public static Connection getConnection()
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineexamsystem","root","Avi@1234");
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return conn;
		}
	}


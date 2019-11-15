package DB;

import java.sql.*;

public class DBConnection 
{
	public static Connection CreateConnection()
	{
		Connection conn = null;;
		
		//String url = "jdbc:mysql://localhost:3306/toeicmyclass?useUnicode=true&amp;characterEncoding=utf8";
		//String username = "root";
		//String password = "1234";
		
		String url = "jdbc:mysql://mysql49261-tunglamdemojspservlet.jelastic.tsukaeru.net/toeicmyclass?useUnicode=true&amp;characterEncoding=utf8";
		String username = "root";
		String password = "feWej3U70Z";
		
		try 
		{
			//load driver
			Class.forName("com.mysql.jdbc.Driver");
			//create connection
			conn = DriverManager.getConnection(url,username,password);
			
		} 
		catch (ClassNotFoundException e) 
		{

			e.printStackTrace();
		}
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}	
		return conn;
	}
}

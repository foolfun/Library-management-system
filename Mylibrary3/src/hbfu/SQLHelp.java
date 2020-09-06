package hbfu;

import java.sql.*;

public class SQLHelp {
	
	static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static String sqlURI = "jdbc:sqlserver://localhost:1433;DatabaseName=Book";
	static String username = "sa";
	static String pwd = "sa";
	static Connection connection;
	public static Connection conn(){//创建连接（sql数据库）
		try {
			Class.forName(driverName);//创建驱动
			connection = DriverManager.getConnection(sqlURI, username, pwd);
			System.out.println("connection success!!!");
		} catch (Exception e) {
			System.out.println("连接失败："+e.getMessage());
		}
		return connection;
		
	}
}

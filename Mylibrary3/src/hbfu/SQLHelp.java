package hbfu;

import java.sql.*;

public class SQLHelp {
	
	static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static String sqlURI = "jdbc:sqlserver://localhost:1433;DatabaseName=Book";
	static String username = "sa";
	static String pwd = "sa";
	static Connection connection;
	public static Connection conn(){//�������ӣ�sql���ݿ⣩
		try {
			Class.forName(driverName);//��������
			connection = DriverManager.getConnection(sqlURI, username, pwd);
			System.out.println("connection success!!!");
		} catch (Exception e) {
			System.out.println("����ʧ�ܣ�"+e.getMessage());
		}
		return connection;
		
	}
}

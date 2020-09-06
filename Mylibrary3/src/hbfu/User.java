package hbfu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User{//封装类
	
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ResultSet queryUser(String sql){
		
		Connection conn =  SQLHelp.conn();
		ResultSet rs = null;
		try {
			Statement sm = conn.createStatement();
			rs = sm.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;		
	}
	public int addUser(String userid,String pwd){
		Connection conn =  SQLHelp.conn();//连接数据库		
		int n = 0;
		String sql = "insert into t_user values(?,?)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, pwd);
			n = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}
	public int updateUser(String userid,String pwd){
		Connection conn =  SQLHelp.conn();//连接数据库		
		int n = 0;
		String sql = "update t_user set pwd = ? where userid = ?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, pwd);
			n = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
}
	

package hbfu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Bookin {
	
	private String bcode;
	private String bookname;
	private String bookloc;//在架信息
	private String num;
	
	public String getBookcode() {
		return bcode;
	}
	public void setBookcode(String bcode) {
		this.bcode = bcode;
	}
	
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	
	public String getBookloc() {
		return bookloc;
	}
	public void setBookloc(String bookloc) {
		this.bookloc = bookloc;
	}
	
	public String getNumber() {
		return num;
	}
	public void setNumber(String num) {
		this.num = num;
	}
	
	public ResultSet queryBook(String sql){
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
	public int addBook(String bcode,String bookname,String bookloc,String num){
		Connection conn =  SQLHelp.conn();//连接数据库		
		int n = 0;
		String sql = "insert into t_book values(?,?,?,?)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,bcode);
			pst.setString(2,bookname);
			pst.setString(3,bookloc);
			pst.setString(4,num);
			n = pst.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return n;
	}
	public int updateBook(String bcode,String bookname,String bookloc,String num){
		Connection conn =  SQLHelp.conn();//连接数据库		
		int n = 0;
		String sql = "update t_book set bookname=?,bookloc=?,num=? where bcode=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setString(1,bookname);
			pst.setString(2,bookloc);
			pst.setString(3,num);
			pst.setString(4,bcode);
			n = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
	public int deleteBook(String bookname){
		Connection conn =  SQLHelp.conn();//连接数据库		
		int n = 0;
		String sql = "delete from t_book where bookname=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,bookname);
			n = pst.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return n;
	}
	
}

package hbfu;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Usermanage  extends JFrame implements ActionListener{
	
	private String userid;
	private String pwd;
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Usermanage(String userid){
		this.userid = userid;
	}
	
	public Usermanage(String userid,String pwd){
		this.userid = userid;
		this.pwd = pwd;
	}
	public Usermanage(){

	}
	
	private static final long serialVersionUID = 1L;
	
	JFrame frame = new JFrame("用户界面");
	JButton re = new JButton("返回");    	
	JButton exit = new JButton("退出");    	
	JButton search = new JButton("查询预约");    	
	JTextField bookname = new JTextField(10);
	public void inint(){
		
    	JPanel p = new JPanel(null);
    	p.setOpaque(false);//设置控件不透明
    	
    	frame.setSize(900, 700);
    	frame.setResizable(false);//不可改变窗口大小
    	frame.setLocationRelativeTo(null);//窗口居中
    	frame.add(p);
    	
    	p.setBorder(BorderFactory.createTitledBorder("欢迎用户："+userid+"来到图书管理系统"));  //设置边界
    	
        JLabel wel = new JLabel("欢迎来到用户管理界面");
        wel.setFont(new Font("宋体", Font.BOLD,30));//设置字体为宋体，粗体，28号
        wel.setBounds(280,80,500,50);
    	p.add(wel);
    	
  	    JLabel inputlable = new JLabel("请输入书名:");
    	inputlable.setFont(new Font("楷体", Font.BOLD,28));//设置字体为宋体，粗体，28号
    	inputlable.setBounds(100,260,200,50);
    	p.add(inputlable);
    	
    	bookname.setFont(new Font("宋体", Font.BOLD,26));
    	bookname.setBounds(300,260,200,40);
    	p.add(bookname);
    	    	
    	re.setFont(new Font("楷体", Font.BOLD,25));//返回
    	re.setBounds(750,550,100,30);
    	p.add(re);
    	    	
    	exit.setFont(new Font("楷体", Font.BOLD,25));//退出
    	exit.setBounds(50,550,100,30);
    	p.add(exit);

    	search.setFont(new Font("楷体", Font.BOLD,25));//查询预约
    	search.setBounds(600,260,200,40);
    	p.add(search);
    	
    	//注册监听
    	exit.addActionListener(this);
    	search.addActionListener(this); 
    	re.addActionListener(this); 
    	frame.setVisible(true);
	
	}
@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		//System.out.println(str);
		if(str.equals("退出")){
			Cue c = new Cue();
			c.inint("感谢您的使用",500,250,190,10,20);
			frame.dispose();
		}
		else if(str.equals("返回")){
			Logint lg = new Logint();
			lg.inint();
			frame.dispose();
		}
		else if(str.equals("查询预约")){
			Bookin book = new Bookin();
			book.setBookname(bookname.getText());
			System.out.println("书名"+book.getBookname());
			
			String sql = "select * from t_book where bookname =\'"+book.getBookname()+"\'";
		//	System.out.println(sql);
			ResultSet rs =  book.queryBook(sql);
	        System.out.println("执行了rs");		
			try {
				if(rs.next()==true){
					//Usersearch se = new Usersearch(userid,book.getBookcode(),book.getBookname(),book.getBookloc(),book.getNumber());
					//System.out.println("执行了这句话");
					Usersearch se = new Usersearch(userid,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
					se.inint();
					frame.dispose();	
				}else{
					Cue c = new Cue();
					c.inint("查询无此书",400,250,150,10,20);
					bookname.setText("");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}


}


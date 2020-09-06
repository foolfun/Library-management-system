package hbfu;

import java.awt.Container;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JFrame implements ActionListener{
	JFrame frame = new JFrame("注册界面");
	JLabel lab1 = new JLabel("账    号: ");
	JTextField username = new JTextField(20);
	JLabel lab2 = new JLabel("密    码: ");
	JPasswordField password = new JPasswordField(20);
	JLabel lab3 = new JLabel("确认密码: ");
	JPasswordField passwordag = new JPasswordField(20);
	JButton login = new JButton("提交");
	JButton cancel = new JButton("取消");
	JPanel p = new JPanel();
	
	public void inint(){
	frame.setResizable(false);
	JPanel p = new JPanel(null);
	p.setOpaque(false);//设置控件不透明
	frame.setVisible(true);//窗口可见
	
	frame.setSize(750, 600);
	frame.setLocationRelativeTo(null);
	frame.add(p);
	
	    JLabel welcome = new JLabel("欢迎注册图书管理系统");
	    welcome.setFont(new Font("楷体", Font.BOLD,28));//设置字体为宋体，粗体，28号
	 	welcome.setBounds(210,30,300,50);
		p.add(welcome);
		
		lab1.setFont(new Font("楷体", Font.BOLD,25));
		lab1.setBounds(200,130,150,30);
		p.add(lab1); 
		
		lab2.setFont(new Font("楷体", Font.BOLD,25));
		lab2.setBounds(200,230,150,30);
		p.add(lab2); 
		
		lab3.setFont(new Font("楷体", Font.BOLD,25));
		lab3.setBounds(200,330,150,30);
		p.add(lab3); 
		
		username.setFont(new Font("宋体", Font.BOLD,26));
		username.setBounds(350,130,175,40);
		p.add(username);
		
		password.setFont(new Font("宋体", Font.BOLD,26));
		password.setBounds(350,230,175,40);
		password.setEchoChar('*');
		p.add(password);
		
		passwordag.setFont(new Font("宋体", Font.BOLD,26));
		passwordag.setBounds(350,330,175,40);
		passwordag.setEchoChar('*');
		p.add(passwordag);
			
		login .setFont(new Font("楷体", Font.BOLD,25));
		login .setBounds(200,450,100,30);
		p.add(login); 
		
		cancel.setFont(new Font("楷体", Font.BOLD,25));
		cancel.setBounds(450,450,100,30);
		p.add(cancel); 
		
		//注册监听
		login.addActionListener(this);
		cancel.addActionListener(this);
	}
	public ArrayList extractData(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int num = md.getColumnCount();
		ArrayList listOfRows = new ArrayList();
		while (rs.next()) {
		Map mapOfColValues = new HashMap(num);
		for (int i = 1; i <= num; i++) {
		mapOfColValues.put(md.getColumnName(i), rs.getObject(i));
		}
		listOfRows.add(mapOfColValues);
		}
		return listOfRows;
		}
	/*public static void main(String []args){
		Register test = new Register();
		test.inint();		
	}*/
@Override
public void actionPerformed(ActionEvent e) {
	String str = e.getActionCommand();
	System.out.println(str);
	if(str.equals("提交")){
		String str1 = passwordag.getText().trim();
		User user = new User();
		user.setUsername(username.getText().trim());
		user.setPassword(password.getText().trim());
		if(username.getText().trim().equals("")  || password.getText().trim().equals("") || passwordag.getText().trim().equals("") ){
			Cue c = new Cue();
			c.inint("请把信息填写完整",400,250,120,10,20);
		}
		else{
			if(str1.equals(password.getText())){
				int n =user.addUser(user.getUsername(), user.getPassword());
				if(n>0){	
					String sql = "select * from t_user where userid = \'"+user.getUsername()+"\' and pwd = \'"+user.getPassword()+"\'";
					System.out.println(sql);
					ResultSet rs =  user.queryUser(sql);
					try {
						if(extractData(rs) != null){
							Cue c = new Cue();
							c.inint("注册成功",400,250,150,10,20);
							frame.dispose();
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}else{
				Cue c = new Cue();
				c.inint("该用户名已被注册",400,250,120,10,20);
				username.setText("");
				password.setText("");
				passwordag.setText("");
			}
			}
			else{
				password.setText("");
				passwordag.setText("");
				Cue c = new Cue();
				c.inint("两次密码不一致",400,250,130,10,20);
			}
		}
		
	}else if(str.equals("取消")){
		    frame.dispose();
	}
	
	}

}

package hbfu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
public class Login extends JFrame implements ActionListener{

	JFrame frame = new JFrame("登陆界面");
	JLabel lab1 = new JLabel("管理员：");
	JTextField username = new JTextField(10);
	JLabel lab2 = new JLabel("密  码：");
	JPasswordField password = new JPasswordField(10);
	JButton login = new JButton("登录");
	JButton cancel = new JButton("取消");

	public void inint(){
		
		frame.setResizable(false);//不可改变窗口大小
    	JPanel p = new JPanel(null);
    	//Container container=this.getContentPane();//创建了一个容器对象
    	p.setOpaque(false);//设置控件不透明
    	frame.setVisible(true);//窗口可见
    	
    	frame.setSize(900, 700);
    	frame.setLocationRelativeTo(null);
    	frame.add(p);
    	
 	    JLabel welcome = new JLabel("欢迎登录图书管理系统");
 	    welcome.setFont(new Font("楷体", Font.BOLD,28));//设置字体为宋体，粗体，28号
 	 	welcome.setBounds(300,100,300,50);
    	p.add(welcome);
    	
    	lab1.setFont(new Font("楷体", Font.BOLD,25));
    	lab1.setBounds(300,200,200,30);
    	p.add(lab1); 
    	
    	lab2.setFont(new Font("楷体", Font.BOLD,25));
    	lab2.setBounds(300,300,200,30);
    	p.add(lab2); 
    	
    	username.setFont(new Font("宋体", Font.BOLD,26));
    	username.setBounds(450,200,175,40);
    	p.add(username);
    	
    	password.setFont(new Font("宋体", Font.BOLD,26));
    	password.setBounds(450,300,175,40);
    	password.setEchoChar('*');
    	p.add(password);
			
    	login .setFont(new Font("楷体", Font.BOLD,25));
    	login .setBounds(300,400,100,30);
    	p.add(login); 
    	
    	cancel.setFont(new Font("楷体", Font.BOLD,25));
    	cancel.setBounds(525,400,100,30);
    	p.add(cancel); 
    	
    	//注册监听
    	login.addActionListener(this);
    	cancel.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("登录")){
			User user = new User();
			user.setUsername(username.getText().trim().toString());
			user.setPassword(password.getText().trim().toString());
			System.out.println(user.getUsername()+","+user.getPassword());
			String sql = "select * from t_user where userid = \'"+user.getUsername()+"\' and pwd = \'"+user.getPassword()+"\'";
			System.out.println(sql);
			ResultSet rs =  user.queryUser(sql);
			try {
				if(rs.next()==true){
					if(username.getText().trim().toString().equals("hbfu")){
						Manage m = new Manage(user.getUsername(),user.getPassword());
						m.inint();
						frame.dispose();
					}else{
						Cue c = new Cue();
						c.inint("仅限管理员登录",400,250,130,10,20);
					}
					
				}else{
					username.setText("");
					password.setText("");
					Cue c = new Cue();
					c.inint("错误:不是管理员或者密码错误",400,250,55,10,20);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	   else if(str.equals("取消")){
		   First f = new First();
			f.inint();
			frame.dispose();
		}
		
	}


}


package hbfu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
public class First extends JFrame implements ActionListener{

	JFrame frame = new JFrame("登录界面");
	JButton a= new JButton("管理员登录");    	
	JButton b= new JButton("普通用户登录"); 
	
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
 	    welcome.setFont(new Font("楷体", Font.BOLD,30));//设置字体为宋体，粗体，28号
 	 	welcome.setBounds(300,100,400,50);
    	p.add(welcome);
    	
    	JLabel tip = new JLabel("请选择登录方式：");
    	tip.setFont(new Font("楷体", Font.BOLD,28));//设置字体为宋体，粗体，28号
    	tip.setBounds(150,200,300,50);
     	p.add(tip);
    	
    	a.setFont(new Font("楷体", Font.BOLD,25));
    	a.setBounds(200,300,200,50);
    	p.add(a);

    	b.setFont(new Font("楷体", Font.BOLD,25));
    	b.setBounds(500,300,200,50);
    	p.add(b);
    	
    	//注册监听
    	a.addActionListener(this);
    	b.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("管理员登录")){
				Login l = new Login();
				l.inint();
				frame.dispose();
		}			
	   else if(str.equals("普通用户登录")){
		   Logint lt = new Logint();
			lt.inint();	
			frame.dispose();
		}
		
	}


}


package hbfu;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Modify extends JFrame implements ActionListener{
	JFrame frame = new JFrame("修改界面");
	JButton re = new JButton("返回");
	JButton exit = new JButton("退出");
	JButton handon = new JButton("提交");
	JTextField bookn,booknum,loc,nb;
	
	private String userid;	
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
	
	public Modify(String userid,String bcode,String bookname,String bookloc,String num){
		this.userid = userid;
		this.bcode = bcode;
		this.bookname = bookname;
		this.bookloc = bookloc;
		this.num = num;
	}
	
	public void inint(){
		frame.setResizable(false);
    	JPanel p = new JPanel(null);
    	p.setOpaque(false);//设置控件不透明
    	frame.setVisible(true);//窗口可见
    	
    	frame.setSize(900,700);
    	frame.setLocationRelativeTo(null);
    	frame.add(p);

    	p.setBorder(BorderFactory.createTitledBorder("欢迎管理员："+userid+"来到图书管理系统"));
    	
    	JLabel result = new JLabel("请填入修改内容：");
    	result.setFont(new Font("楷体", Font.BOLD,18));
    	result.setBounds(20,20,200,50);
    	p.add(result);
    	
    	re.setFont(new Font("楷体", Font.BOLD,25));//返回
    	re.setBounds(750,550,100,30);
    	p.add(re);
    	
    	exit.setFont(new Font("楷体", Font.BOLD,25));//退出
    	exit.setBounds(50,550,100,30);
    	p.add(exit);
    	
    	handon.setFont(new Font("楷体", Font.BOLD,25));//提交
    	handon.setBounds(400,500,100,30);
    	p.add(handon);
    	
    	JLabel la1 = new JLabel("书名：");
    	la1.setFont(new Font("楷体", Font.BOLD,18));
    	la1.setBounds(50,100,100,50);
    	p.add(la1);
    	
    	JLabel la2 = new JLabel("编号：");
    	la2.setFont(new Font("楷体", Font.BOLD,18));
    	la2.setBounds(50,200,100,50);
    	p.add(la2);
    	
    	JLabel la3 = new JLabel("在架信息：");
    	la3.setFont(new Font("楷体", Font.BOLD,18));
    	la3.setBounds(50,300,150,50);
    	p.add(la3);
    	
    	JLabel la4 = new JLabel("剩余书数：");
    	la4.setFont(new Font("楷体", Font.BOLD,18));
    	la4.setBounds(50,400,150,50);
    	p.add(la4);
    	
    	bookn = new JTextField(bookname);
    	bookn.setFont(new Font("宋体", Font.BOLD,18));
    	bookn.setBounds(200,100,300,50);
    	p.add(bookn);
    	
    	booknum = new JTextField(bcode);
    	booknum.setFont(new Font("楷体", Font.BOLD,18));
    	booknum.setBounds(200,200,300,50);
    	p.add(booknum);

    	loc = new JTextField(bookloc);
    	loc.setFont(new Font("楷体", Font.BOLD,18));
    	loc.setBounds(200,300,600,50);
    	p.add(loc);

    	nb = new JTextField(num);
    	nb.setFont(new Font("楷体", Font.BOLD,18));
    	nb.setBounds(200,400,100,50);
    	p.add(nb);
    	
    	
    	//注册监听
    	exit.addActionListener(this);
    	re.addActionListener(this);
    	handon.addActionListener(this);
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("退出")){
			Cue c = new Cue();
			c.inint("感谢您的使用",500,250,190,10,20);
			frame.dispose();
		}
		else if(str.equals("返回")){
			Manage ma = new Manage(userid);
			ma.inint();
			frame.dispose();
		}
		else if(str.equals("提交")){
			Cue c =null;
				  Bookin bookin=new Bookin();
					bookin.updateBook(booknum.getText(),bookn.getText(),loc.getText(),nb.getText());
					c= new Cue();
					c.inint("您已经成功修改",500,250,150,10,20);
					
				}
				
		}
		
	}



	




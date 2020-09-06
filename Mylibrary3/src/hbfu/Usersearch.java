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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Usersearch extends JFrame implements ActionListener{
	
	JFrame frame = new JFrame("查询界面");
	JButton re = new JButton("返回");
	JButton exit = new JButton("退出");
	JButton order = new JButton("预定");
	JButton ce = new JButton("取消");
	JLabel l1,l2,l3,l4;
	/*public static void main(String []args){
		Usersearch test = new Usersearch();
		test.inint();		
	}*/
	
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
	
	public Usersearch(String userid,String bcode,String bookname,String bookloc,String num){
		this.userid = userid;
		this.bcode = bcode;
		this.bookname = bookname;
		this.bookloc = bookloc;
		this.num = num;
	}
	
	public Usersearch(){
		
	}
	public void inint(){
		
		frame.setResizable(false);
		JPanel p = new JPanel(null);
		p.setOpaque(false);//设置控件不透明
		frame.setVisible(true);//窗口可见
		
		frame.setSize(900, 700);
    	frame.setLocationRelativeTo(null);
    	frame.add(p);
    	p.setBorder(BorderFactory.createTitledBorder("欢迎用户："+userid+"来到图书管理系统"));
    	
    	JLabel result = new JLabel("查询的结果如下：");
    	result.setFont(new Font("楷体", Font.BOLD,18));
    	result.setBounds(50,50,200,50);
    	p.add(result);
    	   	
    	 l1 = new JLabel(bookname);
    	 l2 = new JLabel(bcode);
    	 l3 = new JLabel(bookloc);   	
    	 l4 = new JLabel(num);
    	
    	JLabel la1 = new JLabel("书名：");
    	la1.setFont(new Font("楷体", Font.BOLD,18));
    	la1.setBounds(50,100,100,50);
    	p.add(la1);
    	
    	l1.setFont(new Font("楷体", Font.BOLD,18));//书名
    	l1.setBounds(150,100,300,50);
    	p.add(l1);
    	
    	JLabel la2 = new JLabel("编号：");
    	la2.setFont(new Font("楷体", Font.BOLD,18));
    	la2.setBounds(50,200,100,50);
    	p.add(la2);

    	l2.setFont(new Font("楷体", Font.BOLD,18));//编号
    	l2.setBounds(150,200,300,50);
    	p.add(l2);

    	JLabel la3 = new JLabel("在架地点：");
    	la3.setFont(new Font("楷体", Font.BOLD,18));
    	la3.setBounds(50,300,150,50);
    	p.add(la3);

    	l3.setFont(new Font("楷体", Font.BOLD,18));//在架地点
    	l3.setBounds(150,300,600,50);
    	p.add(l3);
    	
    	JLabel la4 = new JLabel("剩余书数：");
    	la4.setFont(new Font("楷体", Font.BOLD,18));
    	la4.setBounds(50,400,150,50);
    	p.add(la4);

    	l4.setFont(new Font("楷体", Font.BOLD,18));//书数
    	l4.setBounds(150,400,100,50);
    	p.add(l4);

    	
    	re.setFont(new Font("楷体", Font.BOLD,25));//返回
    	re.setBounds(750,550,100,30);
    	p.add(re);
    	
    	exit.setFont(new Font("楷体", Font.BOLD,25));//退出
    	exit.setBounds(50,550,100,30);
    	p.add(exit);
    	
    	order.setFont(new Font("楷体", Font.BOLD,25));//预定
    	order.setBounds(250,500,100,30);
    	order.setBackground(Color.green);
    	p.add(order);
    	
    	ce.setFont(new Font("楷体", Font.BOLD,25));//取消
    	ce.setBounds(550,500,100,30);
    	p.add(ce);
    	
    	//注册监听
    	exit.addActionListener(this);
    	re.addActionListener(this);
    	order.addActionListener(this);
    	ce.addActionListener(this);
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
			Usermanage ma = new Usermanage(userid);
			ma.inint();
			frame.dispose();
		}
		else if(str.equals("预定")){
			if(order.getBackground()==Color.green){
				order.setBackground(Color.darkGray);
				Cue c =null;
				//	System.out.println("123456"+this.getBookname());
					Bookin bookin=new Bookin();
					int booknum=Integer.parseInt(this.getNumber().trim());
					if(booknum>0){
						booknum=booknum-1;
						bookin.updateBook(this.getBookcode(), this.getBookname(),this.getBookloc(),booknum+"");
						l4.setText(booknum+"");
						c= new Cue();
						c.inint("您已经成功预约该书",500,250,150,10,20);
						
					}
					else{
						c= new Cue();
						c.inint("该类书籍已无剩余，申请失败！",500,250,120,10,20);
					}
			}else{
				Cue ce = new Cue();
				ce.inint("不可重复预约",500,250,180,10,20);
			}

		}
		else if(str.equals("取消")){
			if(order.getBackground()==Color.green){
				Cue c = new Cue();
				c.inint("还未预约，无法取消",500,250,150,10,20);
			}else{
			Bookin bookin=new Bookin();
			int booknum=Integer.parseInt(this.getNumber().trim());
			bookin.updateBook(this.getBookcode(), this.getBookname(),this.getBookloc(),booknum+"");
			l4.setText(booknum+"");
			Cue c = new Cue();
			c.inint("您已经取消此次预约，欢迎下次预约。",500,250,80,10,20);
			order.setBackground(Color.green);
			}
		}
		
	}

}

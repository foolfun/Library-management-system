package hbfu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Manage  extends JFrame implements ActionListener{
	

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

	public Manage(String userid){
		this.userid = userid;
	}
	
	public Manage(String userid,String pwd){
		this.userid = userid;
		this.pwd = pwd;
	}
	public Manage(){

	}
	/*public static void main(String []args){
	Manage test = new Manage();
	test.inint();		
}*/
	private static final long serialVersionUID = 1L;
	
	JFrame frame = new JFrame("管理界面");
	JButton ag = new JButton("重置");
	JButton re = new JButton("返回");    	
	JButton exit = new JButton("退出");    	
	JButton search = new JButton("查询");    	
	JButton add= new JButton("添加"); 	
	JButton modify = new JButton("修改"); 	 	
	JButton delete =new JButton("删除");
	JTextField bookname = new JTextField(10);
	public void inint(){
		
		frame.setResizable(false);//不可改变窗口大小
    	JPanel p = new JPanel(null);
    	p.setOpaque(false);//设置控件不透明
    			
        /*JLabel l=new JLabel();
    	Icon icon=new ImageIcon("D:\\图书管理系统\\Mylibrary\\src\\hbfu\\manage.jpg");     //在此直接创建对象
    	l.setIcon(icon);
    	l.setBounds(0, 0, icon.getIconWidth(),icon.getIconHeight());
    	p.add(l,new Integer(Integer.MIN_VALUE));
    	getContentPane().add(p);
         pack();             //窗口适应组件大小*/
    	
    	frame.setSize(900, 700);
    	frame.setLocationRelativeTo(null);//窗口居中
    	frame.add(p);
    	p.setBorder(BorderFactory.createTitledBorder("欢迎管理员："+userid+"来到图书管理系统"));  //设置边界
    	
    	
        JLabel wel = new JLabel("欢迎来到管理界面");
        wel.setFont(new Font("宋体", Font.BOLD,30));//设置字体为宋体，粗体，28号
        wel.setBounds(320,50,500,50);
    	p.add(wel);
    	
  	    JLabel inputlable = new JLabel("请输入书名:");
    	inputlable.setFont(new Font("楷体", Font.BOLD,28));//设置字体为宋体，粗体，28号
    	inputlable.setBounds(180,150,300,50);
    	p.add(inputlable);
    	
    	bookname.setFont(new Font("宋体", Font.BOLD,26));
    	bookname.setBounds(380,150,200,40);
    	p.add(bookname);
    	
       	ag.setFont(new Font("楷体", Font.BOLD,25));//重置
    	ag.setBounds(630,150,100,30);
    	p.add(ag);
    	    	
    	re.setFont(new Font("楷体", Font.BOLD,25));//返回
    	re.setBounds(750,550,100,30);
    	p.add(re);
    	    	
    	exit.setFont(new Font("楷体", Font.BOLD,25));//退出
    	exit.setBounds(50,550,100,30);
    	p.add(exit);

    	search.setFont(new Font("楷体", Font.BOLD,25));//查询
    	search.setBounds(150,300,105,50);
    	p.add(search);

    	add.setFont(new Font("楷体", Font.BOLD,25));//添加
    	add.setBounds(315,300,105,50);
    	p.add(add);

    	modify.setFont(new Font("楷体", Font.BOLD,25));//修改
    	modify.setBounds(480,300,105,50);
    	p.add(modify);

    	delete.setFont(new Font("楷体", Font.BOLD,25));//删除
    	delete.setBounds(650,300,105,50);
    	p.add(delete);
    	
    	//注册监听
    	exit.addActionListener(this);
    	ag.addActionListener(this);
    	search.addActionListener(this); 
    	add.addActionListener(this); 
    	re.addActionListener(this); 
    	modify.addActionListener(this); 
    	delete.addActionListener(this);
    	frame.setVisible(true);
	
	}


@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		System.out.println(str);
		if(str.equals("查询")){
			if(bookname.getText().equals("")){
				Cue c = new Cue();
				c.inint("请填入要查询的书名",500,250,150,10,20);
			}else{
				Bookin book = new Bookin();
				book.setBookname(bookname.getText());
				System.out.println(book.getBookname());
				String sql = "select * from t_book where bookname =\'"+book.getBookname()+"\'";
				System.out.println(sql);
				ResultSet rs =  book.queryBook(sql);
				try {
					if(rs.next()==true){
						//System.out.println(book.getBookcode()+book.getBookname()+book.getBookloc()+book.getNumber());
					    //Search se = new Search(userid,book.getBookcode(),book.getBookname(),book.getBookloc(),book.getNumber());
						Search se = new Search(userid,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));	
						se.inint();
						//System.out.println("执行了rs的结果");
						frame.dispose();	
					}else{
						Cue c = new Cue();
						c.inint("查询无此书",400,250,150,10,20);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		else if(str.equals("退出")){
			Cue c = new Cue();
			c.inint("感谢您的使用",500,250,190,10,20);
			frame.dispose();
		}
		else if(str.equals("返回")){
			Login lg = new Login();
			lg.inint();
			frame.dispose();
		}
		else if(str.equals("修改")){
			if(bookname.getText().equals("")){
				Cue c = new Cue();
				c.inint("请填入要修改的书名",500,250,150,10,20);
			}else{
				Bookin book = new Bookin();
				book.setBookname(bookname.getText());
				System.out.println(book.getBookname());
				String sql = "select * from t_book where bookname =\'"+book.getBookname()+"\'";
				System.out.println(sql);
				ResultSet rs =  book.queryBook(sql);
				try {
					if(rs.next()==true){
						Modify mo = new Modify(userid,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));	
						mo.inint();
						frame.dispose();	
					}else{
						Cue c = new Cue();
						c.inint("此书尚未存在",400,250,130,10,20);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(str.equals("添加")){
			Add ad = new Add(userid);
			ad.inint();
			frame.dispose();
		}
		else if(str.equals("删除")){
			if(bookname.getText().equals("")){
				Cue c = new Cue();
				c.inint("请填入要删除的书名",500,250,150,10,20);
			}else{
				Bookin book = new Bookin();
				book.setBookname(bookname.getText());
				System.out.println(book.getBookname());
				String sql = "select * from t_book where bookname =\'"+book.getBookname()+"\'";
				System.out.println(sql);
				ResultSet rs =  book.queryBook(sql);
				try {
					if(rs.next()==true){
						Delete de= new Delete(bookname.getText());
						de.inint();	
					}else{
						Cue c = new Cue();
						c.inint("此书不存在,无法删除",400,250,100,10,20);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(str.equals("重置")){
			bookname.setText("");
		}

  }
}

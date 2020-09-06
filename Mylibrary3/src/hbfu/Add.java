package hbfu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class Add extends JFrame implements ActionListener{
	JFrame frame = new JFrame("添加界面");
	JButton re = new JButton("返回");
	JButton exit = new JButton("退出");
	JButton handon = new JButton("提交");
	JTextField bookname = new JTextField(20);//书名
	JTextField bookcode = new JTextField(20);//编码
	JTextField bookloc = new JTextField(50);//在架信息
	JTextField num = new JTextField(10);//剩余书数
	
	/*public static void main(String []args){
		Add test = new Add();
		test.inint();		
	}*/
	
	private String userid;
	
	public Add(String userid){
		this.userid = userid; 
	}
	
	public Add(){
		
	}
	
	public void inint(){
		frame.setResizable(false);//不可改变窗口大小
    	JPanel p = new JPanel(null);
    	p.setOpaque(false);//设置控件不透明
    	frame.setVisible(true);//窗口可见
    	
    	frame.setSize(900,700);
    	frame.setLocationRelativeTo(null);
    	frame.add(p);

    	p.setBorder(BorderFactory.createTitledBorder("欢迎管理员："+userid+"来到图书管理系统"));
    	
    	JLabel result = new JLabel("请填入内容：");
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
    	
    	bookname.setFont(new Font("宋体", Font.BOLD,18));
    	bookname.setBounds(200,100,300,50);
    	p.add(bookname);

    	bookcode.setFont(new Font("楷体", Font.BOLD,18));
    	bookcode.setBounds(200,200,300,50);
    	p.add(bookcode);

    	bookloc.setFont(new Font("楷体", Font.BOLD,18));
    	bookloc.setBounds(200,300,600,50);
    	p.add(bookloc);

    	num.setFont(new Font("楷体", Font.BOLD,18));
    	num.setBounds(200,400,100,50);
    	p.add(num);
    	
    	
    	//注册监听
    	exit.addActionListener(this);
    	re.addActionListener(this);
    	handon.addActionListener(this);
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
			Bookin book = new Bookin();
			book.setBookcode(bookcode.getText().trim());
			book.setBookname(bookname.getText().trim());
			book.setBookloc(bookloc.getText().trim());
			book.setNumber(num.getText().trim().toString());
			if(bookcode.getText().trim().equals("")  || bookname.getText().trim().equals("") || bookloc.getText().trim().equals("")|| num.getText().trim().equals("") ){
				Cue c = new Cue();
				c.inint("请把信息填写完整",400,250,120,10,20);
			}
			else{
				int n =book.addBook(book.getBookcode(),book.getBookname(),book.getBookloc(),book.getNumber());
				if(n>0){	
					String sql = "select * from t_book where bcode = \'"+book.getBookcode()+"\' ";
					System.out.println(sql);
					ResultSet rs =  book.queryBook(sql);
							try {
								if(extractData(rs) != null){
								Cue c = new Cue();
								c.inint("提交成功",400,250,150,10,20);
								bookcode.setText("");
								bookname.setText("");
								num.setText("");
								bookloc.setText("");
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							}else{
								Cue c = new Cue();
								c.inint("该编号已存在",400,250,120,10,20);
								bookcode.setText("");
								}
					
						}
			}
				
		}//提交
	}



	




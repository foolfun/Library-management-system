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
	
	JFrame frame = new JFrame("�û�����");
	JButton re = new JButton("����");    	
	JButton exit = new JButton("�˳�");    	
	JButton search = new JButton("��ѯԤԼ");    	
	JTextField bookname = new JTextField(10);
	public void inint(){
		
    	JPanel p = new JPanel(null);
    	p.setOpaque(false);//���ÿؼ���͸��
    	
    	frame.setSize(900, 700);
    	frame.setResizable(false);//���ɸı䴰�ڴ�С
    	frame.setLocationRelativeTo(null);//���ھ���
    	frame.add(p);
    	
    	p.setBorder(BorderFactory.createTitledBorder("��ӭ�û���"+userid+"����ͼ�����ϵͳ"));  //���ñ߽�
    	
        JLabel wel = new JLabel("��ӭ�����û��������");
        wel.setFont(new Font("����", Font.BOLD,30));//��������Ϊ���壬���壬28��
        wel.setBounds(280,80,500,50);
    	p.add(wel);
    	
  	    JLabel inputlable = new JLabel("����������:");
    	inputlable.setFont(new Font("����", Font.BOLD,28));//��������Ϊ���壬���壬28��
    	inputlable.setBounds(100,260,200,50);
    	p.add(inputlable);
    	
    	bookname.setFont(new Font("����", Font.BOLD,26));
    	bookname.setBounds(300,260,200,40);
    	p.add(bookname);
    	    	
    	re.setFont(new Font("����", Font.BOLD,25));//����
    	re.setBounds(750,550,100,30);
    	p.add(re);
    	    	
    	exit.setFont(new Font("����", Font.BOLD,25));//�˳�
    	exit.setBounds(50,550,100,30);
    	p.add(exit);

    	search.setFont(new Font("����", Font.BOLD,25));//��ѯԤԼ
    	search.setBounds(600,260,200,40);
    	p.add(search);
    	
    	//ע�����
    	exit.addActionListener(this);
    	search.addActionListener(this); 
    	re.addActionListener(this); 
    	frame.setVisible(true);
	
	}
@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		//System.out.println(str);
		if(str.equals("�˳�")){
			Cue c = new Cue();
			c.inint("��л����ʹ��",500,250,190,10,20);
			frame.dispose();
		}
		else if(str.equals("����")){
			Logint lg = new Logint();
			lg.inint();
			frame.dispose();
		}
		else if(str.equals("��ѯԤԼ")){
			Bookin book = new Bookin();
			book.setBookname(bookname.getText());
			System.out.println("����"+book.getBookname());
			
			String sql = "select * from t_book where bookname =\'"+book.getBookname()+"\'";
		//	System.out.println(sql);
			ResultSet rs =  book.queryBook(sql);
	        System.out.println("ִ����rs");		
			try {
				if(rs.next()==true){
					//Usersearch se = new Usersearch(userid,book.getBookcode(),book.getBookname(),book.getBookloc(),book.getNumber());
					//System.out.println("ִ������仰");
					Usersearch se = new Usersearch(userid,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
					se.inint();
					frame.dispose();	
				}else{
					Cue c = new Cue();
					c.inint("��ѯ�޴���",400,250,150,10,20);
					bookname.setText("");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}


}


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
	
	JFrame frame = new JFrame("�������");
	JButton ag = new JButton("����");
	JButton re = new JButton("����");    	
	JButton exit = new JButton("�˳�");    	
	JButton search = new JButton("��ѯ");    	
	JButton add= new JButton("���"); 	
	JButton modify = new JButton("�޸�"); 	 	
	JButton delete =new JButton("ɾ��");
	JTextField bookname = new JTextField(10);
	public void inint(){
		
		frame.setResizable(false);//���ɸı䴰�ڴ�С
    	JPanel p = new JPanel(null);
    	p.setOpaque(false);//���ÿؼ���͸��
    			
        /*JLabel l=new JLabel();
    	Icon icon=new ImageIcon("D:\\ͼ�����ϵͳ\\Mylibrary\\src\\hbfu\\manage.jpg");     //�ڴ�ֱ�Ӵ�������
    	l.setIcon(icon);
    	l.setBounds(0, 0, icon.getIconWidth(),icon.getIconHeight());
    	p.add(l,new Integer(Integer.MIN_VALUE));
    	getContentPane().add(p);
         pack();             //������Ӧ�����С*/
    	
    	frame.setSize(900, 700);
    	frame.setLocationRelativeTo(null);//���ھ���
    	frame.add(p);
    	p.setBorder(BorderFactory.createTitledBorder("��ӭ����Ա��"+userid+"����ͼ�����ϵͳ"));  //���ñ߽�
    	
    	
        JLabel wel = new JLabel("��ӭ�����������");
        wel.setFont(new Font("����", Font.BOLD,30));//��������Ϊ���壬���壬28��
        wel.setBounds(320,50,500,50);
    	p.add(wel);
    	
  	    JLabel inputlable = new JLabel("����������:");
    	inputlable.setFont(new Font("����", Font.BOLD,28));//��������Ϊ���壬���壬28��
    	inputlable.setBounds(180,150,300,50);
    	p.add(inputlable);
    	
    	bookname.setFont(new Font("����", Font.BOLD,26));
    	bookname.setBounds(380,150,200,40);
    	p.add(bookname);
    	
       	ag.setFont(new Font("����", Font.BOLD,25));//����
    	ag.setBounds(630,150,100,30);
    	p.add(ag);
    	    	
    	re.setFont(new Font("����", Font.BOLD,25));//����
    	re.setBounds(750,550,100,30);
    	p.add(re);
    	    	
    	exit.setFont(new Font("����", Font.BOLD,25));//�˳�
    	exit.setBounds(50,550,100,30);
    	p.add(exit);

    	search.setFont(new Font("����", Font.BOLD,25));//��ѯ
    	search.setBounds(150,300,105,50);
    	p.add(search);

    	add.setFont(new Font("����", Font.BOLD,25));//���
    	add.setBounds(315,300,105,50);
    	p.add(add);

    	modify.setFont(new Font("����", Font.BOLD,25));//�޸�
    	modify.setBounds(480,300,105,50);
    	p.add(modify);

    	delete.setFont(new Font("����", Font.BOLD,25));//ɾ��
    	delete.setBounds(650,300,105,50);
    	p.add(delete);
    	
    	//ע�����
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
		if(str.equals("��ѯ")){
			if(bookname.getText().equals("")){
				Cue c = new Cue();
				c.inint("������Ҫ��ѯ������",500,250,150,10,20);
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
						//System.out.println("ִ����rs�Ľ��");
						frame.dispose();	
					}else{
						Cue c = new Cue();
						c.inint("��ѯ�޴���",400,250,150,10,20);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		else if(str.equals("�˳�")){
			Cue c = new Cue();
			c.inint("��л����ʹ��",500,250,190,10,20);
			frame.dispose();
		}
		else if(str.equals("����")){
			Login lg = new Login();
			lg.inint();
			frame.dispose();
		}
		else if(str.equals("�޸�")){
			if(bookname.getText().equals("")){
				Cue c = new Cue();
				c.inint("������Ҫ�޸ĵ�����",500,250,150,10,20);
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
						c.inint("������δ����",400,250,130,10,20);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(str.equals("���")){
			Add ad = new Add(userid);
			ad.inint();
			frame.dispose();
		}
		else if(str.equals("ɾ��")){
			if(bookname.getText().equals("")){
				Cue c = new Cue();
				c.inint("������Ҫɾ��������",500,250,150,10,20);
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
						c.inint("���鲻����,�޷�ɾ��",400,250,100,10,20);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(str.equals("����")){
			bookname.setText("");
		}

  }
}

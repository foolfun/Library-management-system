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
	JFrame frame = new JFrame("��ӽ���");
	JButton re = new JButton("����");
	JButton exit = new JButton("�˳�");
	JButton handon = new JButton("�ύ");
	JTextField bookname = new JTextField(20);//����
	JTextField bookcode = new JTextField(20);//����
	JTextField bookloc = new JTextField(50);//�ڼ���Ϣ
	JTextField num = new JTextField(10);//ʣ������
	
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
		frame.setResizable(false);//���ɸı䴰�ڴ�С
    	JPanel p = new JPanel(null);
    	p.setOpaque(false);//���ÿؼ���͸��
    	frame.setVisible(true);//���ڿɼ�
    	
    	frame.setSize(900,700);
    	frame.setLocationRelativeTo(null);
    	frame.add(p);

    	p.setBorder(BorderFactory.createTitledBorder("��ӭ����Ա��"+userid+"����ͼ�����ϵͳ"));
    	
    	JLabel result = new JLabel("���������ݣ�");
    	result.setFont(new Font("����", Font.BOLD,18));
    	result.setBounds(20,20,200,50);
    	p.add(result);
    	
    	re.setFont(new Font("����", Font.BOLD,25));//����
    	re.setBounds(750,550,100,30);
    	p.add(re);
    	
    	exit.setFont(new Font("����", Font.BOLD,25));//�˳�
    	exit.setBounds(50,550,100,30);
    	p.add(exit);
    	
    	handon.setFont(new Font("����", Font.BOLD,25));//�ύ
    	handon.setBounds(400,500,100,30);
    	p.add(handon);
    	
    	JLabel la1 = new JLabel("������");
    	la1.setFont(new Font("����", Font.BOLD,18));
    	la1.setBounds(50,100,100,50);
    	p.add(la1);
    	
    	JLabel la2 = new JLabel("��ţ�");
    	la2.setFont(new Font("����", Font.BOLD,18));
    	la2.setBounds(50,200,100,50);
    	p.add(la2);
    	
    	JLabel la3 = new JLabel("�ڼ���Ϣ��");
    	la3.setFont(new Font("����", Font.BOLD,18));
    	la3.setBounds(50,300,150,50);
    	p.add(la3);
    	
    	JLabel la4 = new JLabel("ʣ��������");
    	la4.setFont(new Font("����", Font.BOLD,18));
    	la4.setBounds(50,400,150,50);
    	p.add(la4);
    	
    	bookname.setFont(new Font("����", Font.BOLD,18));
    	bookname.setBounds(200,100,300,50);
    	p.add(bookname);

    	bookcode.setFont(new Font("����", Font.BOLD,18));
    	bookcode.setBounds(200,200,300,50);
    	p.add(bookcode);

    	bookloc.setFont(new Font("����", Font.BOLD,18));
    	bookloc.setBounds(200,300,600,50);
    	p.add(bookloc);

    	num.setFont(new Font("����", Font.BOLD,18));
    	num.setBounds(200,400,100,50);
    	p.add(num);
    	
    	
    	//ע�����
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
		if(str.equals("�˳�")){
			Cue c = new Cue();
			c.inint("��л����ʹ��",500,250,190,10,20);
			frame.dispose();
		}
		else if(str.equals("����")){
			Manage ma = new Manage(userid);
			ma.inint();
			frame.dispose();
		}
		else if(str.equals("�ύ")){
			Bookin book = new Bookin();
			book.setBookcode(bookcode.getText().trim());
			book.setBookname(bookname.getText().trim());
			book.setBookloc(bookloc.getText().trim());
			book.setNumber(num.getText().trim().toString());
			if(bookcode.getText().trim().equals("")  || bookname.getText().trim().equals("") || bookloc.getText().trim().equals("")|| num.getText().trim().equals("") ){
				Cue c = new Cue();
				c.inint("�����Ϣ��д����",400,250,120,10,20);
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
								c.inint("�ύ�ɹ�",400,250,150,10,20);
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
								c.inint("�ñ���Ѵ���",400,250,120,10,20);
								bookcode.setText("");
								}
					
						}
			}
				
		}//�ύ
	}



	




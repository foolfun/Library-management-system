package hbfu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
public class Logint extends JFrame implements ActionListener{

	JFrame frame = new JFrame("��½����");
	JLabel lab1 = new JLabel("�û�����");
	JTextField username = new JTextField(10);
	JLabel lab2 = new JLabel("��  �룺");
	JPasswordField password = new JPasswordField(10);
	JButton login = new JButton("��¼");
	JButton register = new JButton("ע��");
	JButton re = new JButton("����");
	public void inint(){
		
		frame.setResizable(false);//���ɸı䴰�ڴ�С
    	JPanel p = new JPanel(null);
    	//Container container=this.getContentPane();//������һ����������
    	p.setOpaque(false);//���ÿؼ���͸��
    	frame.setVisible(true);//���ڿɼ�
    	
    	frame.setSize(900, 700);
    	frame.setLocationRelativeTo(null);
    	frame.add(p);
    	
 	    JLabel welcome = new JLabel("��ӭ��¼ͼ�����ϵͳ");
 	    welcome.setFont(new Font("����", Font.BOLD,28));//��������Ϊ���壬���壬28��
 	 	welcome.setBounds(300,100,300,50);
    	p.add(welcome);
    	
    	lab1.setFont(new Font("����", Font.BOLD,25));
    	lab1.setBounds(300,200,200,30);
    	p.add(lab1); 
    	
    	lab2.setFont(new Font("����", Font.BOLD,25));
    	lab2.setBounds(300,300,200,30);
    	p.add(lab2); 
    	
    	username.setFont(new Font("����", Font.BOLD,26));
    	username.setBounds(450,200,175,40);
    	p.add(username);
    	
    	password.setFont(new Font("����", Font.BOLD,26));
    	password.setBounds(450,300,175,40);
    	password.setEchoChar('*');
    	p.add(password);
			
    	login .setFont(new Font("����", Font.BOLD,25));
    	login .setBounds(300,400,100,30);
    	p.add(login); 
    	
    	register .setFont(new Font("����", Font.BOLD,25));
    	register .setBounds(525,400,100,30);
    	p.add(register); 
    	
    	re.setFont(new Font("����", Font.BOLD,25));//����
    	re.setBounds(750,550,100,30);
    	p.add(re);
    	
    	//ע�����
    	login.addActionListener(this);
    	register.addActionListener(this);
    	re.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("��¼")){
			User user = new User();
			user.setUsername(username.getText().trim().toString());
			user.setPassword(password.getText().trim().toString());
			System.out.println(user.getUsername()+","+user.getPassword());
			String sql = "select * from t_user where userid = \'"+user.getUsername()+"\' and pwd = \'"+user.getPassword()+"\'";
			System.out.println(sql);
			ResultSet rs =  user.queryUser(sql);
			try {
				if(rs.next()==true){
					Usermanage m = new Usermanage(user.getUsername(),user.getPassword());
					m.inint();
					frame.dispose();
				}else{
					username.setText("");
					password.setText("");
					Cue c = new Cue();
					c.inint("���û�������",400,250,130,10,20);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	   else if(str.equals("ע��")){
			Register r = new Register();
			r.inint();
		}
	   else if(str.equals("����")){
			First f = new First();
			f.inint();
			frame.dispose();
		}
		
	}


}


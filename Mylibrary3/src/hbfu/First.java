package hbfu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
public class First extends JFrame implements ActionListener{

	JFrame frame = new JFrame("��¼����");
	JButton a= new JButton("����Ա��¼");    	
	JButton b= new JButton("��ͨ�û���¼"); 
	
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
 	    welcome.setFont(new Font("����", Font.BOLD,30));//��������Ϊ���壬���壬28��
 	 	welcome.setBounds(300,100,400,50);
    	p.add(welcome);
    	
    	JLabel tip = new JLabel("��ѡ���¼��ʽ��");
    	tip.setFont(new Font("����", Font.BOLD,28));//��������Ϊ���壬���壬28��
    	tip.setBounds(150,200,300,50);
     	p.add(tip);
    	
    	a.setFont(new Font("����", Font.BOLD,25));
    	a.setBounds(200,300,200,50);
    	p.add(a);

    	b.setFont(new Font("����", Font.BOLD,25));
    	b.setBounds(500,300,200,50);
    	p.add(b);
    	
    	//ע�����
    	a.addActionListener(this);
    	b.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("����Ա��¼")){
				Login l = new Login();
				l.inint();
				frame.dispose();
		}			
	   else if(str.equals("��ͨ�û���¼")){
		   Logint lt = new Logint();
			lt.inint();	
			frame.dispose();
		}
		
	}


}


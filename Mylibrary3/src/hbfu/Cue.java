package hbfu;


import java.awt.Font;

import javax.swing.*;

public class Cue {


	public void inint(String n,int x,int y,int m,int w,int k){
		
		JFrame frame = new JFrame("��ʾ");
		JPanel p = new JPanel(null);
		frame.setResizable(false);//���ɸı䴰�ڴ�С
		//Container container=this.getContentPane();//������һ����������
		p.setOpaque(false);//���ÿؼ���͸��
		frame.setVisible(true);//���ڿɼ�
		
		frame.setSize(x, y);
    	frame.setLocationRelativeTo(null);
    	frame.add(p);
    	
    	JLabel cue = new JLabel(n);
    	cue.setFont(new Font("����", Font.BOLD,k));
    	cue.setBounds(m,w,670,200);
    	p.add(cue);


	}

}

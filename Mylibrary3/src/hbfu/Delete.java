package hbfu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Delete implements ActionListener {

	
	JButton sure = new JButton("ȷ��");
	JButton cancel = new JButton("ȡ��");
	JFrame frame = new JFrame("��ʾ");
	String bookname;
	
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public Delete(String bookname){
		this.bookname=bookname;
	}
	public void inint(){
		
		JPanel p = new JPanel(null);
		p.setOpaque(false);//���ÿؼ���͸��
		frame.setVisible(true);//���ڿɼ�
		
		frame.setSize(400,280 );//���ڴ�С
		frame.setResizable(false);//���ɸı䴰�ڴ�С
    	frame.setLocationRelativeTo(null);//���ھ���
    	frame.add(p);
    	
    	JLabel cue = new JLabel("��ȷ��Ҫɾ�����ڸ����������Ϣ��");
    	cue.setFont(new Font("����", Font.BOLD,18));
    	cue.setBounds(50,10,400,100);
    	p.add(cue);
    	
    	sure.setFont(new Font("����", Font.BOLD,25));//ȷ��
    	sure.setBounds(80,150,100,30);
    	p.add(sure);
    	
    	cancel.setFont(new Font("����", Font.BOLD,25));//ȡ��
    	cancel.setBounds(200,150,100,30);
    	p.add(cancel);
    	
    	//ע�����
    	sure.addActionListener(this);
    	cancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String str = e.getActionCommand();
		System.out.println(str);
		if(str.equals("ȷ��")){
				Bookin bookin=new Bookin();
				bookin.deleteBook(bookname);
				Cue c = new Cue();
				c.inint("ɾ���ɹ���",500,250,190,10,20);
				frame.dispose();
		}
		else if(str.equals("ȡ��")){
			   frame.dispose();
		}
		
	}
}

package hbfu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Delete implements ActionListener {

	
	JButton sure = new JButton("确认");
	JButton cancel = new JButton("取消");
	JFrame frame = new JFrame("提示");
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
		p.setOpaque(false);//设置控件不透明
		frame.setVisible(true);//窗口可见
		
		frame.setSize(400,280 );//窗口大小
		frame.setResizable(false);//不可改变窗口大小
    	frame.setLocationRelativeTo(null);//窗口居中
    	frame.add(p);
    	
    	JLabel cue = new JLabel("您确认要删除关于该书的所有信息？");
    	cue.setFont(new Font("楷体", Font.BOLD,18));
    	cue.setBounds(50,10,400,100);
    	p.add(cue);
    	
    	sure.setFont(new Font("楷体", Font.BOLD,25));//确认
    	sure.setBounds(80,150,100,30);
    	p.add(sure);
    	
    	cancel.setFont(new Font("楷体", Font.BOLD,25));//取消
    	cancel.setBounds(200,150,100,30);
    	p.add(cancel);
    	
    	//注册监听
    	sure.addActionListener(this);
    	cancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String str = e.getActionCommand();
		System.out.println(str);
		if(str.equals("确认")){
				Bookin bookin=new Bookin();
				bookin.deleteBook(bookname);
				Cue c = new Cue();
				c.inint("删除成功！",500,250,190,10,20);
				frame.dispose();
		}
		else if(str.equals("取消")){
			   frame.dispose();
		}
		
	}
}

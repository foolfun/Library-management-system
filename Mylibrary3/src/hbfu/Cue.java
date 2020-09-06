package hbfu;


import java.awt.Font;

import javax.swing.*;

public class Cue {


	public void inint(String n,int x,int y,int m,int w,int k){
		
		JFrame frame = new JFrame("提示");
		JPanel p = new JPanel(null);
		frame.setResizable(false);//不可改变窗口大小
		//Container container=this.getContentPane();//创建了一个容器对象
		p.setOpaque(false);//设置控件不透明
		frame.setVisible(true);//窗口可见
		
		frame.setSize(x, y);
    	frame.setLocationRelativeTo(null);
    	frame.add(p);
    	
    	JLabel cue = new JLabel(n);
    	cue.setFont(new Font("楷体", Font.BOLD,k));
    	cue.setBounds(m,w,670,200);
    	p.add(cue);


	}

}

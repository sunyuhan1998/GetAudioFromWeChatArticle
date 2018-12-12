package com.sunyuhan.getaudio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author sunyuhan
 *
 * 2018年12月11日
 * 
 * GUI绘制，一个显示框一个进度条一个输入框
 */
public class InitGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	static JFrame jf1 = new JFrame("获取微信图文音频 author：SunYuHan");	
	static JTextArea jta;
	static JTextField jtf;
	static JProgressBar jpb;
	//绘制窗口方法
	public void init() {
		//初始化
		jta = new JTextArea();
		jpb = new JProgressBar();
		jtf = new JTextField("在此输入微信图文链接");
		//设置属性
		jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf1.setLocation(600, 150);
		jf1.setSize(550, 190);
		jf1.setResizable(false);
		jf1.setLayout(null);
		jta.setEditable(false);
		jpb.setMaximum(0);
		jpb.setStringPainted(true);
		jtf.setEditable(true);
		jta.setBounds(0, 0, 550, 90);
		jpb.setBounds(0, 102, 550, 15);
		jtf.setBounds(0, 124, 550, 30);
		//添加到窗口
		jf1.add(new JScrollPane(jta));
		jf1.add(jta);
		jf1.add(jpb);
		jf1.add(jtf);
		//可见
		jf1.setVisible(true);
		
		jtf.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				 jtf.setText("在此输入微信图文链接");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				 jtf.setText("");
				
			}
		});
		jtf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!jtf.getText().equals("")) {
					new ActionPer().start();
				}
			}

		});
	}

}

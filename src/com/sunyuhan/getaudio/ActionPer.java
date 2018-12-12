package com.sunyuhan.getaudio;

import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author sunyuhan
 *
 * 2018年12月12日
 * 
 * 监听器子线程
 */
public class ActionPer extends Thread{
	static JTextField jtf = InitGUI.jtf;
	static JTextArea jta = InitGUI.jta;
	@Override
	public void run() {
		String temp = jtf.getText();
		jtf.setText("");
		jta.setText("");
		jta.append("已获取链接，正在解析...\n");
		GetAudio client = new GetAudio(temp, "UTF-8");
        String result = client.get();
        if (result.equals("error")) {
        	jta.append("地址解析失败！你输入的地址不正确！\n");
        	return;
		} else {
			jta.append("解析成功，正在下载...\n");
		}
        String filename = new Download().downloadNet(result);
        if (filename.equals("error")) {
			jta.append("下载出现故障！请马上联系作者...\n");
			return;
		}else {
			jta.append("下载完毕！文件名"+filename+" 请到本程序目录下查看！\n");
		}
	}
	//初始化进度条方法，设置进度条最大长度
	public static void initPB(int max) {
		InitGUI.jpb.setMaximum(max);
	}
	//增长进度条
	public static void addPB(int pb) {
		InitGUI.jpb.setValue(pb);
	}

}

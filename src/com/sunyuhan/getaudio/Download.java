package com.sunyuhan.getaudio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sunyuhan
 *
 * 2018年12月12日
 * 
 * 下载模块
 */
public class Download {
	 URLConnection conn =null;
     InputStream inStream = null;
     FileOutputStream fs = null;
     File filePath = null;//下载后的文件路径
     String filename = "";//下载后的文件名
	public String downloadNet(String path){
		if (path.equals("error")) {
			return "error";
		}
        try {
        	int byteread = 0;
        	int bytenum = 0;
            URL url = new URL(path);
            long fileLength = ProgressBar.getFileLength(path);
            ActionPer.initPB((int)fileLength);
            conn = url.openConnection();
            inStream = conn.getInputStream();
            //构建日期作为文件名
            filename = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new Date())+".mp3";
            //取当前位置绝对路径
            filePath = new File("");
            String finalFile = filePath.getAbsolutePath()+"\\下载\\"+filename;
            //建立文件输出
            fs = new FileOutputStream(finalFile);
            //下载
            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread);
                //移动进度条
                bytenum+=byteread;
                ActionPer.addPB(bytenum);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "下载出现错误！\n";
        }finally {
			try {
				fs.close();
			} catch (IOException e) {
				return "文件流关闭失败！\n";
			}
		}
        return filename;
    }

}

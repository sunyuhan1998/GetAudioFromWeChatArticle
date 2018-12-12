package com.sunyuhan.getaudio;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author sunyuhan
 *
 * 2018年12月12日
 * 
 * 获取进度条长度
 */
public class ProgressBar {
    public static long getFileLength(String downloadUrl) throws IOException{
		  if(downloadUrl == null || "".equals(downloadUrl)){
			  return 0L ; 
		  }
	      URL url = new URL(downloadUrl);
	      HttpURLConnection conn = null;
	      try {
	          conn = (HttpURLConnection) url.openConnection();
	          conn.setRequestMethod("HEAD");
	          conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows 7; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.73 Safari/537.36 YNoteCef/5.8.0.1 (Windows)");
	          return (long) conn.getContentLength();
	      } catch (IOException e) {
	          return 0L;
	      } finally {
	          conn.disconnect();
	    }
     }

}

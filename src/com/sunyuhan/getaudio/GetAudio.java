package com.sunyuhan.getaudio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @author sunyuhan
 *
 * 2018年12月11日
 * 
 * 根据微信图文链接获取其中的音频
 * 实现原理：
	  微信获取音频是通过madiaID向后台申请音频的，
	  而这个id在播放音频前就已经写在页面里了，属性
	  名称为：voice_encode_fileid，只要获取到
	  这个属性的值，按规律拼接url就可以获得音频的链接。
 */
public class GetAudio {
	private String u;
    private String encoding;

    public GetAudio(String u, String encoding) {
        this.u = u;
        this.encoding = encoding;
    }

    public String get(){
    	try {
			// 根据链接，生成一个URL对象
			URL url = new URL(u);
			// 打开URL
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			// 得到输入流，即网页内容
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), encoding));
			String line;
			String result = "";
			while ((line = reader.readLine()) != null) {
			   result +=line;
			}
			//id字符串起始点
			int id = result.indexOf("voice_encode_fileid");
			//这个范围只是一个估值，一定大于MediaID的长度
			result = result.substring(id+21,id+55);
			//id字符串结束点
			int id2 = result.indexOf("\">");
			//MediaID
			result = result.substring(0,id2);
			//拼接url
			result = "https://res.wx.qq.com/voice/getvoice?mediaid="+result;
			return result;
		} catch (Exception e) {
			return "error";
		}
    }

}

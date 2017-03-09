package com.skywayct.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.jfinal.kit.PropKit;
import com.skywayct.util.MenuUtil;

public class WXTagUtil {
	public static String getAllMemberByTag(String tag) {
		String access_token = getAccessToken();
		String action = "https://api.weixin.qq.com/cgi-bin/user/tag/create?access_token="
				+ access_token;
		return execute(action, tag);

	}
	public static String addTag(String tag) {
		String access_token = getAccessToken();
		String action = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token="
				+ access_token;
		return execute(action, tag);

	}

	public static String editTag(String tag) {
		String access_token = getAccessToken();
		String action = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token="
				+ access_token;
		return execute(action, tag);

	}

	public static String delTag(String tag) {
		String access_token = getAccessToken();
		String action = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token="
				+ access_token;
		return execute(action, tag);
	}
	
	public static String addTagToU(String tag) {
		String access_token = getAccessToken();
		String action = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token="
				+ access_token;
		return execute(action, tag);

	}
	
	public static String delTagToU(String tag) {
		String access_token = getAccessToken();
		String action = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token="
				+ access_token;
		return execute(action, tag);

	}
	
	public static String getUTagList(String tag) {
		String access_token = getAccessToken();
		String action = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token="
				+ access_token;
		return execute(action, tag);

	}
	
	public static String getTagMember(String tag) {
		String access_token = getAccessToken();
		String action = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token="
				+ access_token;
		return execute(action, tag);
	}

	public static String execute(String action, String tag) {
		try {
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(tag.getBytes("UTF-8"));// 传入参数
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			return message;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return " 失败！";
	}

	public static String getAccessToken() {
		PropKit.use("config_pro.txt");
		String access_token = MenuUtil.getAccess_token();
		return access_token;
	}

}

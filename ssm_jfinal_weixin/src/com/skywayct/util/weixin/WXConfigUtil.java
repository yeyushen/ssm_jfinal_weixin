package com.skywayct.util.weixin;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class WXConfigUtil {
	private static Properties pps;
	private static InputStream in;
	private static String filepath = "weixin.properties";
//	static {
//		String path = ProUtil.class.getClassLoader().getResource(filepath)
//				.getPath();
//		try {
//			pps = new Properties();
//			in = new BufferedInputStream(new FileInputStream(path));
//			pps.load(in);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 根据key获取value
	 * 
	 * @param key
	 * @return
	 */
	public static String getValueByKey(String key) {
		return pps.getProperty(key);
	}

	/**
	 * 获取文件路径
	 * 
	 * @param path
	 * @return
	 */
//	public static String getPath(String fileName) {
//		return ProUtil.class.getClassLoader().getResource(fileName).getPath();
//	}
}

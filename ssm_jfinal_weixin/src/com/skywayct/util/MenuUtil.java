package com.skywayct.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import net.sf.json.JSONObject;

import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.jfinal.ApiController;
import com.jfinal.weixin.sdk.kit.ImageKit;

public class MenuUtil extends ApiController{
	
	public ApiConfig getApiConfig() {
		ApiConfig ac = new ApiConfig();
		
		// 配置微信 API 相关常量
		ac.setToken(PropKit.get("token"));
		ac.setAppId(PropKit.get("appId"));
		ac.setAppSecret(PropKit.get("appSecret"));
		
		/**
		 *  是否对消息进行加密，对应于微信平台的消息加解密方式：
		 *  1：true进行加密且必须配置 encodingAesKey
		 *  2：false采用明文模式，同时也支持混合模式
		 */
		ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", false));
		ac.setEncodingAesKey(PropKit.get("encodingAesKey", "setting it in config file"));
		return ac;
	}
	
	
	/**
	 * 获得ACCESS_TOKEN
	 * 
	 * @Title: getAccess_token
	 * @Description: 获得ACCESS_TOKEN
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
//	private static String APPID = Tools.readTxtFile(Const.APPID);
//	private static String APPSECRET = Tools.readTxtFile(Const.APPSECRET);
	

	public static String getAccess_token() {
		String APPID =PropKit.get("appId");
		String APPSECRET =PropKit.get("appSecret");
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ APPID + "&secret=" + APPSECRET;
		String accessToken = null;
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet
					.openConnection();

			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");

			JSONObject demoJson = JSONObject.fromObject(message);
			accessToken = demoJson.getString("access_token");

			System.out.println(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	
	public static String getMenu(){
		String access_token = getAccess_token();
		String action = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token="
				+ access_token;
		try {
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
			http.connect();
			OutputStream os = http.getOutputStream();
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			return "getMenu " + message;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "getMenu 失败";
	}


	/**
	 * 创建Menu
	 * 
	 * @Title: createMenu
	 * @Description: 创建Menu
	 * @param @return
	 * @param @throws IOException 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	public static String createMenu() {
		
		String menu = "{\"button\":[{\"name\":\"充值\",\"sub_button\":[{\"type\":\"view\",\"name\":\"流量查询\",\"url\":\"http://mp.weixin.qq.com/s?__biz=MzI0MTI2ODU4OA==&mid=100000040&idx=1&sn=37106003e49b981e2d53edbbdca78132&scene=0&previewkey=gaY9ATJJfOApQ26mf6BuDMwqSljwj2bfCUaCyDofEow%3D#wechat_redirect\"},{\"type\":\"view\",\"name\":\"充值查询\",\"url\":\""+ SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/ordrecord.html", true)+"\"},"
				+ "{\"type\":\"view\",\"name\":\"话费\",\"url\":\""+ SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/phcharge.html", true)+"\"},{\"type\":\"view\",\"name\":\"流量\",\"url\":\""+ SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/recharge.html", true)+"\"}]},"
				+ "{\"name\":\"地主福利\",\"sub_button\":[{\"type\":\"view\",\"name\":\"激情欧洲杯\",\"url\":\"http://mp.weixin.qq.com/s?__biz=MzI0MTI2ODU4OA==&mid=2247483666&idx=1&sn=94f69509c14f062fd12342ad74612fcb&scene=4#wechat_redirect\"}]},"
				+ "{\"name\":\"服务中心\",\"sub_button\":[{\"type\":\"click\",\"name\":\"在线客服\",\"key\":\"DKF\"},"
				+ " {\"type\":\"view\",\"name\":\"个人中心\",\"url\":\""+ SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/wxuser.html", true)+"\"},"
				+ " {\"type\":\"view\",\"name\":\"平台介绍\",\"url\":\"https://mp.weixin.qq.com/s?__biz=MzI0MTI2ODU4OA==&mid=100000005&idx=1&sn=9bc8e77229324e2b95013cf539538d16&scene=0&previewkey=vRs%2FuW1CqplO3dEinCholsNS9bJajjJKzz%2F0By7ITJA%3D#rd\"},"
				+ " {\"type\":\"view\",\"name\":\"推广名片\",\"url\":\""+ SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/qrcode.html", true)+"\"},"
				+ " {\"type\":\"view\",\"name\":\"收益说明\",\"url\":\"http://mp.weixin.qq.com/s?__biz=MzI0MTI2ODU4OA==&mid=100000010&idx=1&sn=ca6645efcb6f06081eced2acd39f1a21&scene=0&previewkey=vRs%2FuW1CqplO3dEinCholsNS9bJajjJKzz%2F0By7ITJA%3D#wechat_redirect\"} ]}"
				+ "]}";

		// 此处改为自己想要的结构体，替换即可
		String access_token = getAccess_token();
		String action = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
				+ access_token;
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
			os.write(menu.getBytes("UTF-8"));// 传入参数
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			return "返回信息" + message;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "createMenu 失败";
	}

	/**
	 * 删除当前Menu
	 * 
	 * @Title: deleteMenu
	 * @Description: 删除当前Menu
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String deleteMenu() {
		String access_token = getAccess_token();
		String action = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="
				+ access_token;
		try {
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			OutputStream os = http.getOutputStream();
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			return "deleteMenu返回信息:" + message;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "deleteMenu 失败";
	}
	
	
	public static String getTags() {
		String access_token = getAccess_token();
		String action = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token="
				+ access_token;
		try {
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
			http.connect();
			OutputStream os = http.getOutputStream();
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			return /*"getTags" +*/ message;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "getTags 失败";
	}
	
	
	public static String createConMenu(int tagid){
		String menu = "{\"button\":[{\"name\":\"充值\",\"sub_button\":[{\"type\":\"click\",\"name\":\"充值查询\",\"key\":\"服务失败\"},"
				+ "{\"type\":\"click\",\"name\":\"话费\",\"key\":\"HF\"},{\"type\":\"click\",\"name\":\"流量\",\"key\":\"服务失败\"}]},"
				+ "{\"name\":\"地主达人\",\"sub_button\":[{\"type\":\"click\",\"name\":\"地主福利\",\"key\":\"服务失败\"}]},"
				+ "{\"name\":\"服务中心\",\"sub_button\":[{\"type\":\"click\",\"name\":\"在线客服\",\"key\":\"服务失败\"},"
				+ " {\"type\":\"click\",\"name\":\"个人中心\",\"key\":\"服务失败\"},"
				+ " {\"type\":\"click\",\"name\":\"平台介绍\",\"key\":\"服务失败\"},"
				+ " {\"type\":\"click\",\"name\":\"推广名片\",\"key\":\"服务失败\"},"
				+ " {\"type\":\"click\",\"name\":\"佣金快报\",\"key\":\"服务失败\"} ]}"
				+ "],\"matchrule\":{\"tag_id\":\""+tagid+"\"}}";

		// 此处改为自己想要的结构体，替换即可
		String access_token = getAccess_token();
		String action = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token="+ access_token;
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
			os.write(menu.getBytes("UTF-8"));// 传入参数
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			return "返回信息" + message;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "createConMenu 失败！";
	}

	/**
	 * 页面连接
	 * 
	 * @Title: connection
	 * @Description:
	 * @param @return
	 * @param @throws IOException 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	// public static String connection() {
	// // orderVO[] vos = new orderVO[2];
	// // vos[0] = new orderVO();
	// // vos[1] = new orderVO();
	// // vos[0].setPhone("13544452227");
	// // vos[0].setProduct_id("1");
	// // vos[1].setPhone("13544452227");
	// // vos[1].setProduct_id("1");
	// // JSONObject A= createJSONObjectList("1", "1", vos);
	// String menu =
	// "{\"ds\":[{\"dfdfdfdf\"]},\"button\":[{\"type\":\"click\",\"name\":\"充值\",\"key\":\"qr\"},"
	// +
	// "{\"type\":\"view\",\"name\":\"流量充值\",\"url\":\"http://weixin.leedate.com//m/chongzhi.php\"},"
	// +
	// "{\"name\":\"我的助手\",\"sub_button\":[{\"type\":\"view\",\"name\":\"会员中心\",\"url\":\"http://weixin.leedate.com/m/user.php/\"},"
	// +
	// "   {\"type\":\"view\",\"name\":\"客服热线\",\"url\":\"http://weixin.leedate.com/m/chongzhi.php/\"},"
	// +
	// " {\"type\":\"view\",\"name\":\"平台介绍\",\"url\":\"http://weixin.leedate.com/m/new.php?id=18/\"} ]}"
	// +
	// "]}";
	// JSONObject json= new JSONObject(menu);
	// JSONArray jsonArray=json.getJSONArray("button");
	// JSONObject jsonArray2=json.getJSONObject("ds");
	// String loginNames="loginname list:";
	// for(int i=0;i<jsonArray.length();i++){
	// JSONObject user=(JSONObject) jsonArray.get(i);
	// String userName=(String) user.get("type");
	// if(i==jsonArray.length()-1){
	// loginNames+=userName;
	// }else{
	// loginNames+=userName+",";
	// }
	// }
	// String action =
	// "http://127.0.0.1:80/api/batchorder?appid=ea7606a0001bf1b62d7b74a5469011af&access_token=0fec1646977febdc814a87f9bf5063ae21f69e0f&serial=fsmp1461310523889001&phone=15089340112&product_id=1";
	// try {
	// URL url = new URL(action);
	// HttpURLConnection http = (HttpURLConnection) url.openConnection();
	//
	// http.setRequestMethod("GET");
	// http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	// http.setDoOutput(true);
	// http.setDoInput(true);
	// System.setProperty("sun.net.client.defaultConnectTimeout",
	// "30000");//连接超时30秒
	// System.setProperty("sun.net.client.defaultReadTimeout", "30000");
	// //读取超时30秒
	// http.connect();
	// OutputStream os= http.getOutputStream();
	//
	// ObjectOutputStream oos = new ObjectOutputStream(os);
	// oos.writeObject(menu);
	//
	// oos.flush();
	// oos.close();
	//
	// InputStream is =http.getInputStream();
	// int size =is.available();
	// byte[] jsonBytes =new byte[size];
	// is.read(jsonBytes);
	// String message=new String(jsonBytes,"UTF-8");
	// return "返回信息:"+message;
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return "连接失败";
	// }
	public static BigDecimal getBigDecimal(double num){
		  return new BigDecimal(num);
		 }
	 public static double multiply(double num1, double num2){
		  BigDecimal big1 = getBigDecimal(num1);
		  BigDecimal big2 = getBigDecimal(num2);
		  return big1.multiply(big2).doubleValue();
		 }

		public static String getTwoDecimals(double a) {
			String str = String.valueOf(a);
			String s1 = null;
			String s2 = null;
			String s3 = null;
			String numberString = null;
			s1 = str.substring(0, str.indexOf("."));
			s2 = str.substring(str.indexOf(".") + 1);
			char[] ch1 = s2.toCharArray();
			if (ch1.length == 1) {
				s3 = "0";
				numberString = s1 + s2 + s3;
			} else {
				s2 = str.substring(str.indexOf(".") + 1, str.indexOf(".") + 3);
				numberString = s1 + s2;
			}
			if (numberString.startsWith("0")) {
				numberString = numberString.substring(1);
				if (numberString.startsWith("0")) {
					numberString = numberString.substring(1);
				}
			}
			return numberString;
		}
	public static void main(String[] args) {
		PropKit.use("config_pro.txt");
		// System.out.println(connection());
//		System.out.println(deleteMenu());
//		System.out.println(createMenu())
//		System.out.println(getTags());
//		System.out.println(createConMenu(2)); //标签改变需要重新生成自定义和个性化按钮
//		System.out.println(getMenu());
		
//		BigInteger bint = new BigInteger("28686");
//		System.out.println(bint.testBit(15));
		
//		ApiConfigKit.setThreadLocalApiConfig(new MenuUtil().getApiConfig());
//		System.out.println(AccessTokenApi.getAccessToken().getAccessToken());
		
		
		try {
			File _fileact = new File(ImageKit.getpath() + "activity.txt");
			if(_fileact.exists()){
				ImageKit.ImageTset("obNUxwY6d6LkXj8mHSkqEhT9UT9o", "奋斗", System.currentTimeMillis() + 2505600000l,null,true);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// public void a(String product_id,String phone){
	// int sendResult = 0;
	// String sout = null;
	// String s2 = "http://llw.eatmeng.com";
	// //开始充值
	// String timestamp = new Long(new Date().getTime()) + "";
	// String calbackUrl = "http://xxx.com/callback.jsp?lx=19&idcm=" + new
	// Long(new Date().getTime());
	// String teJson22 = "{";
	// teJson22 += "\"nonceStr\":\"" + timestamp + "\",";
	// teJson22 += "\"timestamp\":\"" + timestamp + "\",";
	// teJson22 += "\"rechargeList\":[{";
	// teJson22 += "\"clecs\":\"1\",";
	// teJson22 += "\"flowCode\":\"" + product_id + "\",";
	// teJson22 += "\"mobile\":\"" + phone + "\",";
	// teJson22 += "\"feedbackURL\":\"" + calbackUrl + "\"";
	// teJson22 += "}]}";
	//
	// String sign = "appSecret=" + "YCc5pye1dMYihICK" + "&appid=" +
	// "LP03604577" + "&nonceStr=" + timestamp +
	// "&rechargeList=Array&timestamp=" + timestamp;
	// SHA1 sh = new SHA1();
	// String signSh = sh.SHA1Digest(sign);
	// if (signSh.length() == 37) {
	// signSh = "000" + signSh;
	// }
	// if (signSh.length() == 38) {
	// signSh = "00" + signSh;
	// }
	// if (signSh.length() == 39) {
	// signSh = "0" + signSh;
	// }
	// Map<String, String> headMap = new HashMap<String, String>();
	// headMap.put("appid", payPassword);
	// headMap.put("sign", signSh);
	// try {
	// sout = posUrlData(s2 + "/Platform/Flow/rechargeList", teJson22, headMap);
	// //获取数据
	// try {
	// // sout=unescapeUnicode(sout);
	// JSONObject resultOut = JSONObject.fromObject(sout);
	// String status = T.getStr(resultOut.getString("status"), "");
	// if (status.equals("true")) {
	// JSONArray rechargeList = resultOut.getJSONArray("rechargeList");
	// JSONObject orderObj = rechargeList.getJSONObject(0);
	// status = orderObj.getString("status");
	// if (status.equals("true")) {
	// sendResult = 1;
	// backOrderId = T.getStr(orderObj.getString("orderNum"), "");
	// } else {
	// JSONObject errorInfoOjb = orderObj.getJSONObject("errorInfo");
	// rsusss = T.getStr(errorInfoOjb.getString("errorDescription"), "");
	// rsusss += "，错误码：" + T.getStr(errorInfoOjb.getString("errorCode"), "");
	// }
	// } else {
	// JSONObject errorInfoOjb = resultOut.getJSONObject("errorInfo");
	// rsusss = T.getStr(errorInfoOjb.getString("errorDescription"), "");
	// rsusss += "，错误码：" + T.getStr(errorInfoOjb.getString("errorCode"), "");
	// }
	// } catch (Exception ex) {
	// errMsg = "提交出错201";
	// sout += "<br>充值提供失败" + ex;
	// // out.println(sout);
	// // out.flush();
	// // return;
	// }
	// } catch (Exception ex) {
	// errMsg = "提交出错201";
	// sout += "<br>充值提供失败";
	// // out.println(sout);
	// // out.flush();
	// // return;
	// }
	//
	// }



}
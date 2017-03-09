package com.jfinal.weixin.info;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.weixin.model.Member;
import com.jfinal.weixin.model.Memberqrcode;
import com.jfinal.weixin.model.Noticeboard;
import com.jfinal.weixin.model.OrderWarning;
import com.jfinal.weixin.model.Scene;
import com.jfinal.weixin.model.Wallet;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.JsTicket;
import com.jfinal.weixin.sdk.api.JsTicketApi;
import com.jfinal.weixin.sdk.api.UserApi;
import com.jfinal.weixin.sdk.api.JsTicketApi.JsApiType;
import com.jfinal.weixin.sdk.api.QrcodeApi;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.jfinal.ApiController;
import com.jfinal.weixin.sdk.kit.ImageKit;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.jfinal.weixin.sdk.utils.JsonUtils;
import com.jfinal.weixin.sdk.utils.SHA1;

/***
 * 会员信息查询
 * @author jwt
 *
 */
public class UserInfoController extends ApiController {

	static Log logger = Log.getLog(UserInfoController.class);
	File qrimageFile = null;
	
	@SuppressWarnings("unchecked")
	public void index() throws Exception {
		String code = getPara("code");
		
		//测试代码 start 注释后再提交
		removeSessionAttr("open_id");
		setSessionAttr("open_id", "obNUxwY6d6LkXj8mHSkqEhT9UT9o");
		//测试代码 end
		
		String open_id =null;
		if(!StrKit.isBlank(code)){ 
			SnsAccessToken accessToken = SnsAccessTokenApi.getSnsAccessToken(PropKit.get("appId"), PropKit.get("appSecret"), code);			
			if(StrKit.isBlank(accessToken.getOpenid()))
			{ 
				open_id = getSessionAttr("open_id");
				if(StrKit.isBlank(open_id))
				{
					renderText("{\"code\":-1,\"msg\":\"还未关注或页面打开超时\"}");
					return;
				}	
			}
			else
				setSessionAttr("open_id", accessToken.getOpenid());
			
			JsTicket jsTicket = JsTicketApi.getTicket(JsApiType.jsapi);
			String url = getPara("url");
			if(url.indexOf("flag") >0) 
				url = url + "&code="+code;
			else
				url = url +"&state=wx";
			open_id = getSessionAttr("open_id");
			if(StrKit.isBlank(open_id)) open_id = accessToken.getOpenid();
			String json = getjsApiSign(jsTicket.getTicket(),open_id,url,this);
			renderText(json);
			return;
		}
		open_id = getSessionAttr("open_id");
		if (StrKit.isBlank(open_id)) {
			renderText("{\"code\":-1,\"msg\":\"还未关注或页面打开超时!\"}");
			return;
		}
		
		Member model = Member.dao
				.findFirst("select a.*,b.amount,if(to_days(now()) - to_days(c.ts) = 0,1,0) as sign,c.points,ISNULL(b.password) as pas from weixin_member a left join weixin_wallet b on a.openid = b.open_id left join weixin_mypoints c on a.openid = c.open_id where a.openid ='"
						+ open_id + "' and a.dr = 0");
		if (null == model) {
			renderText("{\"code\":-1,\"msg\":\"请重新关注公众号\"}");
			return;
		}
		if(model.getStr("nick_name")==null){
			ApiResult result =UserApi.getUserInfo(open_id);
			if(result!=null){
				model.set("nick_name", result.getStr("nickname"));
				model.set("name", result.getStr("nickname"));
				model.set("photo", result.getStr("headimgurl"));
				model.set("sex",  result.getInt("sex"));
				model.set("province", result.getStr("province"));
				model.set("country", result.getStr("country"));
				model.set("city", result.getStr("city"));
				model.update();
			}
		}
		
		if("sv".equals(getPara("do"))){
			renderText(saveUserinfo(open_id));
			return;
		}
		
		if("cx".equals(getPara("do"))){
			renderText(queryUserinfo(open_id));
			return;
		}
		
		if("pas".equals(getPara("do"))){
			renderText(changePass(open_id));
			return;
		}
		
		qrimageFile = new File(ImageKit.getpath() + open_id.toLowerCase() + "_qr.png");
//		getUserPhoto(model.getStr("photo"), open_id); //如果需要用粉丝头像进行图片和成把这去注释去掉
		getQrcode(open_id,model.getStr("nick_name"));
		try {
			Map map = new HashMap();
			map.put("photo", model.getStr("photo"));
			map.put("nickname", model.getStr("nick_name"));
			map.put("name", model.getStr("name"));
			map.put("qrcode", "weixin/images/" + open_id.toLowerCase() + "_qr.png");
			map.put("phone", model.getStr("phone"));
			map.put("sex", model.getStr("sex"));
			map.put("amount", model.getDouble("amount"));
			map.put("points", model.getDouble("points"));
			map.put("sign", model.getLong("sign"));
			map.put("pas", model.getLong("pas"));
			Member count = Member.dao.findFirst("select (select count(m.openid) from weixin_member m where m.parent_id=w.openid and m.dr=0)  as lv1,(select count(1) from weixin_member m where exists(select 1 from weixin_member a where a.parent_id = w.openid and a.openid = m.parent_id and a.dr=0) and m.dr=0) as lv2,(select count(1) from weixin_member m where exists(select 1 from weixin_member a where a.parent_id in (select openid from weixin_member where parent_id = w.openid ) and a.openid = m.parent_id) and dr =0) as lv3 from weixin_member w where 1=1 and w.dr =0 and w.openid ='"+ open_id +"'");
			if (null!= count) {
				map.put("lv1", count.getLong("lv1"));
				map.put("lv2", count.getLong("lv2"));
				map.put("lv3", count.getLong("lv3"));
			}
			JSONObject mjson = new JSONObject();
			mjson.putAll(map);
			renderText(mjson.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

	}
	
	
	public String saveUserinfo(String open_id) throws UnsupportedEncodingException{
		Member model = Member.dao.findFirst("select ordinarymember_id,name,phone,bank_name,bank_no,bank_user,bank_addr from weixin_member where openid='"+ open_id +"'");
		model.set("ordinarymember_id", model.getStr("ordinarymember_id"));
		model.set("name", new String(getPara("name").getBytes("ISO-8859-1"),"UTF-8"));
		model.set("phone", getPara("phone"));
		model.set("bank_name", new String(getPara("bank_name").getBytes("ISO-8859-1"),"UTF-8"));
		model.set("bank_no", new String(getPara("bank_no").getBytes("ISO-8859-1"),"UTF-8"));
		model.set("bank_user", new String(getPara("bank_user").getBytes("ISO-8859-1"),"UTF-8"));
		model.set("bank_addr", new String(getPara("bank_addr").getBytes("ISO-8859-1"),"UTF-8"));
		model.update();
		return "{\"success\":true}";
	}
	
	public String queryUserinfo(String open_id){
		Member member = Member.dao.findFirst("select ordinarymember_id,name,phone,bank_name,bank_no,bank_user,bank_addr from weixin_member where openid='"+ open_id +"'");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", member.getStr("name"));
		map.put("phone", member.getStr("phone"));
		map.put("bank_name", member.getStr("bank_name"));
		map.put("bank_no", member.getStr("bank_no"));
		map.put("bank_user", member.getStr("bank_user"));
		map.put("bank_addr", member.getStr("bank_addr"));
		JSONObject mjson = new JSONObject();
		mjson.putAll(map);
		return mjson.toString();
	}
	
	public String changePass(String open_id){
		Wallet pas = Wallet.dao.findFirst("select wallet_id,password from weixin_wallet where open_id ='"+ open_id+"'");
		pas.set("wallet_id", pas.getStr("wallet_id"));
		if(StrKit.isBlank(getPara("o")) || "d41d8cd98f00b204e9800998ecf8427e".equals(getPara("o"))){
			pas.set("password", getPara("n"));
		}else{
			if(!getPara("o").equals(pas.get("password"))){
				return "{\"fail\":\"原密码不对！\"}";
			}
			pas.set("password", getPara("n"));
		}
		pas.update();
		return "{\"success\":true}";
	}
	

	public void getUserPhoto(String surl, String open_id) throws Exception {
		if (qrimageFile.exists()) {return;}
		File imageFile = new File(ImageKit.getpath() + open_id + ".png");
		httpGet(surl, imageFile);
	}

	public void getQrcode(String open_id,String nickname) throws Exception {
		//活动期间
		File _fileact = new File(ImageKit.getpath() + "activity.txt");
		
		//永久二维码
		if(!_fileact.exists()){
			Memberqrcode mbq = Memberqrcode.dao.findFirst("select * from weixin_menberqrcode where status =0 and open_id = ?",open_id);
			if(mbq!=null){
				ImageKit.ImageTset(open_id, nickname, System.currentTimeMillis() + 2505600000l,mbq.getStr("path"),false);
				return;
			}
		}
		// 临时二维码
		Scene scene = Scene.dao
				.findFirst("select * from weixin_scene where open_id ='"
						+ open_id + "'");
		if (null == scene) {
			scene = new Scene();
			scene.put("open_id", open_id);
			scene.put("create_time", System.currentTimeMillis());
			scene.save();
		}
		if(System.currentTimeMillis() < scene.getLong("create_time") + 2505600000l && qrimageFile.exists()) return;
		scene.put("create_time", System.currentTimeMillis());
		scene.update();
		File imageFile = new File(ImageKit.getpath()+ open_id.toLowerCase() + "_skyway.png");
		ApiConfigKit.setThreadLocalApiConfig(getApiConfig());
		ApiResult ar = QrcodeApi.createTemporary(2592000,scene.getInt("scene_id"));
		JSONObject json = JSONObject.fromObject(ar.getJson());
		httpGet(QrcodeApi.getShowQrcodeUrl(json.getString("ticket")), imageFile);
		//活动推广码
		if(_fileact.exists()){
			ImageKit.ImageTset(open_id, nickname, System.currentTimeMillis() + 2505600000l,null,true);
			return;
		}
		ImageKit.ImageTset(open_id, nickname, System.currentTimeMillis() + 2505600000l,null,false);
	}

	public void httpGet(String surl, File imageFile) {
		// new一个URL对象
		URL url;
		try {
			url = new URL(surl);
			// 打开链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置请求方式为"GET"
			conn.setRequestMethod("GET");
			// 超时响应时间为5秒
			conn.setConnectTimeout(5 * 1000);
			// 通过输入流获取图片数据
			InputStream inStream = conn.getInputStream();
			// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
			byte[] data = readInputStream(inStream);

			// 创建输出流
			FileOutputStream outStream = new FileOutputStream(imageFile);
			// 写入数据
			outStream.write(data);
			// 关闭输出流
			outStream.close();
		} catch (MalformedURLException e) {
			logger.error(e.toString(), e);
		} catch (IOException e) {
			logger.error(e.toString(), e);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream.toByteArray();
	}
	
	public static String getjsApiSign(String ticket,String openid,String url,Controller con){
		Map<String, String> packageParams = new HashMap<String, String>();
    	packageParams.put("noncestr", System.currentTimeMillis() + "");
    	packageParams.put("jsapi_ticket", ticket);
    	packageParams.put("timestamp", System.currentTimeMillis() / 1000 + "");
    	packageParams.put("url", url);
		String stringA = PaymentKit.packageSign(packageParams, false);
		String signature = new SHA1().SHA1Digest(stringA).toUpperCase();
		packageParams.remove("jsapi_ticket");
		packageParams.put("appId",  PropKit.get("appId"));
    	packageParams.put("open_id",  openid.toLowerCase());
    	packageParams.put("signature",  signature);
    	Noticeboard notice = Noticeboard.dao.findFirst("select * from sys_noticeboard");
    	if(notice==null)
    		packageParams.put("notice", "");
		else
			packageParams.put("notice", notice.getStr("content"));
    	OrderWarning war = OrderWarning.dao.findFirst("select a.*,b.nick_name from weixin_order_warning a,weixin_member b where a.warning_tagid = if(isnull(b.warning_tagid),0,b.warning_tagid) and b.openid = ?",openid);
    	con.setSessionAttr("warning",war);
    	String jsonStr = JsonUtils.toJson(packageParams).toLowerCase();
    	logger.info(jsonStr);
		return jsonStr;
	}

	/**
	 * 如果要支持多公众账号，只需要在此返回各个公众号对应的  ApiConfig 对象即可
	 * 可以通过在请求 url 中挂参数来动态从数据库中获取 ApiConfig 属性值
	 */
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


}

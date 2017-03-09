package com.skywayct.util.weixin;

import java.util.Formatter;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class WxSDKUtils {
//	@Resource
//	private CacheClient cacheClient;
//	private static final Logger log = LoggerFactory.getLogger(WxSDKUtils.class);
//
//	private String appId = WXConfigUtil.getValueByKey("appId");
//	private String secret = WXConfigUtil.getValueByKey("secret");
//
//	// 根据code获取openID
//	public String getOpenid(String code) {
//		if (StringUtils.isNullOrEmpty(code)) {
//			return null;
//		}
//		HttpEntity hEntity = HttpUtil.httpPostConn(
//				"https://api.weixin.qq.com/sns/oauth2/access_token", "appid="
//						+ appId + "&secret=" + secret
//						+ "&grant_type=authorization_code&code=" + code);
//		String responseBody = hEntity.getRenHtml();
//		try {
//			JSONObject json = new JSONObject(responseBody);
//			return json.getString("openid");
//		} catch (JSONException e) {
//			log.error("转String为json数据出错！");
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	// 根据CODE获取粉丝信息
//	public FansVo getFansInfo(String code) {
//		if (StringUtils.isNullOrEmpty(code)) {
//			return null;
//		}
//		String openId = getOpenid(code);
//		String token = getToken();
//		return getFansInfo(token, openId);
//	}
//
//	// 根据token和open获取粉丝信息
//	public FansVo getFansInfo(String token, String openId) {
//		if (StringUtils.findEmptyIndex(token, openId) > -1) {
//			return null;
//		}
//		String key = CacheKeyEnum.H5_WEIXIN_FANS_INFO_KEY.toString() + token
//				+ openId;
//		FansVo fans = (FansVo) cacheClient.get(key);
//		if (!StringUtils.isNullOrEmpty(token)) {
//			return fans;
//		}
//		HttpEntity hEntity = HttpUtil.httpPostConn(
//				"https://api.weixin.qq.com/cgi-bin/user/info", "access_token="
//						+ token + "&openid=" + openId + "&lang=zh_CN");
//		fans = (FansVo) ReqJsonUtil.jsonToObject(hEntity.getRenHtml(),
//				FansVo.class);
//		if (!StringUtils.isNullOrEmpty(fans)) {
//			cacheClient.set(key, fans, 60);
//			return fans;
//		}
//		return null;
//	}
//
//	// 获取当前token
//	public String getToken() {
//		String key = CacheKeyEnum.H5_WEIXIN_TOKEN_KEY.toString();
//		String token = (String) cacheClient.get(key);
//		if (!StringUtils.isNullOrEmpty(token)) {
//			return token;
//		}
//		HttpEntity hEntity = HttpUtil
//				.httpGetConn("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
//						+ appId + "&secret=" + secret);
//		Map<String, Object> paraMap = ReqJsonUtil.jsonToMap(hEntity
//				.getRenHtml());
//		token = String.valueOf(paraMap.get("access_token"));
//		if (!StringUtils.isNullOrEmpty(token)) {
//			cacheClient.set(key, token, 7000);
//			return token;
//		}
//		return null;
//	}
//
//	
//
//	private String byteToHex(byte[] hash) {
//		Formatter formatter = new Formatter();
//		byte[] arrayOfByte = hash;
//		int j = hash.length;
//		for (int i = 0; i < j; i++) {
//			byte b = arrayOfByte[i];
//			formatter.format("%02x", new Object[] { Byte.valueOf(b) });
//		}
//		String result = formatter.toString();
//		formatter.close();
//		return result;
//	}
//
//	public static void main(String[] args) {
//		FansVo vo = new WxSDKUtils().getFansInfo("03121536811b5516c045d0a9b129712c");
//		System.out.println("ok");
//	}
}
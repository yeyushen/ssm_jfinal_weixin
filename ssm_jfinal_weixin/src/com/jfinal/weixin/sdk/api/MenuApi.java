
package com.jfinal.weixin.sdk.api;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

/**
 * menu api
 */
public class MenuApi {
	
	private static String getMenu = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";
	private static String createMenu = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
	
	/**
	 * 查询自定义菜单
	 */
	public static ApiResult getMenu() {
		String jsonResult = HttpUtils.get(getMenu + AccessTokenApi.getAccessTokenStr());
		return new ApiResult(jsonResult);
	}
	
	/**
	 * 创建自定义菜单
	 */
	public static ApiResult createMenu(String jsonStr) {
		String jsonResult = HttpUtils.post(createMenu + AccessTokenApi.getAccessTokenStr(), jsonStr);
		return new ApiResult(jsonResult);
	}
	
	private static String deleteMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";
	
	/**
	 * 自定义菜单删除接口
	 * @return ApiResult
	 */
	public static ApiResult deleteMenu() {
		String jsonResult = HttpUtils.get(deleteMenuUrl + AccessTokenApi.getAccessTokenStr());
		return new ApiResult(jsonResult);
	}
	
	private static String addConditionalUrl = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=";
	
	/**
	 * 创建个性化菜单
	 * @param jsonStr
	 * @return
	 */
	public static ApiResult addConditional(String jsonStr) {
		String jsonResult = HttpUtils.post(addConditionalUrl + AccessTokenApi.getAccessTokenStr(), jsonStr);
		return new ApiResult(jsonResult);
	}
	
	private static String delConditionalUrl = "https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=";
	
	/**
	 * 删除个性化菜单
	 * @param menuid menuid为菜单id，可以通过自定义菜单查询接口获取。
	 * @return ApiResult
	 */
	public static ApiResult delConditional(String menuid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("menuid", menuid);
		
		String url = delConditionalUrl + AccessTokenApi.getAccessTokenStr();
		
		String jsonResult = HttpUtils.post(url, JsonUtils.toJson(params));
		return new ApiResult(jsonResult);
	}
	
	private static String tryMatchUrl = "https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=";
	
	/**
	 * 测试个性化菜单匹配结果
	 * @param userId user_id可以是粉丝的OpenID，也可以是粉丝的微信号。
	 * @return ApiResult
	 */
	public static ApiResult tryMatch(String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", userId);
		
		String url = tryMatchUrl + AccessTokenApi.getAccessTokenStr();
		
		String jsonResult = HttpUtils.post(url, JsonUtils.toJson(params));
		return new ApiResult(jsonResult);
	}
	
	private static String getCurrentSelfMenuInfoUrl = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=";
	
	/**
	 * 获取自定义菜单配置接口
	 */
	public static ApiResult getCurrentSelfMenuInfo() {
		String jsonResult = HttpUtils.get(getCurrentSelfMenuInfoUrl + AccessTokenApi.getAccessTokenStr());
		return new ApiResult(jsonResult);
	}
	
}



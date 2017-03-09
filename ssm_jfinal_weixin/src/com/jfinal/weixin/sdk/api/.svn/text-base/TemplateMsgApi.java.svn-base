
package com.jfinal.weixin.sdk.api;

import com.jfinal.weixin.sdk.utils.HttpUtils;

/**
 * 模板消息 API
 * 文档地址：http://mp.weixin.qq.com/wiki/17/304c1885ea66dbedf7dc170d84999a9d.html
 */
public class TemplateMsgApi {
	
	private static String sendApiUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	
	/**
	 * 发送模板消息
	 */
	public static ApiResult send(String jsonStr) {
		String jsonResult = HttpUtils.post(sendApiUrl + AccessTokenApi.getAccessToken().getAccessToken(), jsonStr);
		return new ApiResult(jsonResult);
	}
}



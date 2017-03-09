
package com.jfinal.weixin.sdk.api;

import com.jfinal.weixin.sdk.kit.ParaMap;
import com.jfinal.weixin.sdk.utils.HttpUtils;

/**
 * 网页授权获取 access_token API
 */
public class SnsApi
{
    private static String getUserInfo = "https://api.weixin.qq.com/sns/userinfo";
    
    /**
     * 获取用户个人信
     * @param accessToken 调用凭证access_token
     * @param openId 普通用户的标识，对当前开发者帐号唯一
     * @return ApiResult
     */
    public static ApiResult getUserInfo(String accessToken, String openId)
    {
        ParaMap pm = ParaMap.create("access_token", accessToken).put("openid", openId).put("lang", "zh_CN");
        return new ApiResult(HttpUtils.get(getUserInfo, pm.getData()));
    }
    
}

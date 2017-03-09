package com.jfinal.weixin.info;

import java.io.File;
import java.io.UnsupportedEncodingException;

import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.weixin.model.Member;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.api.TemplateMsgApi;
import com.jfinal.weixin.sdk.jfinal.ApiController;

/***
 * 用于活动判断是否关注了流量地主
 * @author jwt
 *
 */
public class CheckSubscController extends ApiController {

	static Log logger = Log.getLog(CheckSubscController.class);
	File qrimageFile = null;
	
	@SuppressWarnings("unchecked")
	public void index() throws Exception {
		
		String ck = getPara("ck");
		if(!StrKit.isBlank(ck)){
			checkSub(ck);
			return;
		}
		
		if(!StrKit.isBlank(getPara("tp")) && getPara("tp").equals("template")){
			testTemplate();
			return;
		}
		
		String code = getPara("code");
		String cu = getPara("cu");
		if(!StrKit.isBlank(cu)) setSessionAttr("cu", cu);
		String open_id =null;
		if(!StrKit.isBlank(code)){ 
			SnsAccessToken accessToken = SnsAccessTokenApi.getSnsAccessToken(PropKit.get("appId"), PropKit.get("appSecret"), code);			
			if(StrKit.isBlank(accessToken.getOpenid()))
			{ 
				open_id = getSessionAttr("open_id");
				if(StrKit.isBlank(open_id))
				{
					redirect(PropKit.get("acturl")+"&sign=f");
					return;
				}
			}
			setSessionAttr("open_id", accessToken.getOpenid());
			open_id = accessToken.getOpenid();
			if (StrKit.isBlank(open_id)) {
				redirect(PropKit.get("acturl")+"&sign=f");
				return;
			}
			Member model = Member.dao
					.findFirst("select id,ordinarymember_id,cu,openid from weixin_member where openid =? and dr = 0",open_id);
			if (null == model) {
				redirect(PropKit.get("acturl")+"&sign=f");
				return;
			}
			if(StrKit.isBlank(model.getStr("cu")))
			{
				model.set("cu", getSessionAttr("cu"));
				model.update();
			}else if(!model.getStr("cu").equals(getSessionAttr("cu"))){
				redirect(PropKit.get("acturl")+"&sign=f");
				return;
			}
			redirect(PropKit.get("acturl")+"&sign="+model.getStr("openid"));
			return;
		}else{
			redirect(SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/checksub?cu="+cu,true));
		}
	}
	
	public void checkSub(String openid){
		Member model = Member.dao
				.findFirst("select nick_name,photo from weixin_member where openid =? and dr = 0",openid);
		renderText(model.toJson());
	}
	
	public void testTemplate() throws UnsupportedEncodingException{
		renderText("当前公众号："+new String(PropKit.get("appName").getBytes("GBK"),"GBk"));
		TemplateMsgApi.send("{ \"touser\" : \"obNUxwY6d6LkXj8mHSkqEhT9UT9o\", \"template_id\" : \""+PropKit.get("msgid")+"\", \"url\" : \"\", \"data\" :{ \"first\" : { \"value\" : \"恭喜你流量充值成功！\", \"color\" : \"#173177\" }, \"keyword1\" :{ \"value\" : \"10M\", \"color\" : \"#173177\" }, \"keyword2\" : { \"value\" : \""+new String(PropKit.get("appName").getBytes("UTF-8"),"UTF-8")+"\", \"color\" : \"#173177\" }, \"keyword3\" : { \"value\" : \"13265184912\", \"color\" : \"#173177\" }, \"keyword4\" : { \"value\" : \"10元\", \"color\" : \"#173177\" }, \"remark\" :{ \"value\" : \"于 2016-09-23 充值,欢迎再次充值！\", \"color\" : \"#173177\" } } }");
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
		ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", false));
		ac.setEncodingAesKey(PropKit.get("encodingAesKey", "setting it in config file"));
		return ac;
	}


}

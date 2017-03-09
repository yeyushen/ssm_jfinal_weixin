package com.skywayct.controller.weixin.mymenu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.ShorturlApi;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.mymenu.MyMenuManger;
import com.skywayct.util.Jurisdiction;
import com.skywayct.util.MenuUtil;
import com.skywayct.util.PageData;

/**
 * 说明：自定义菜单 创建人： jwt 创建时间：2016-06-06
 */
@Controller
@RequestMapping(value="/mymenu")
public class MyMenuController extends BaseController{
	String menuUrl = "mymenu/list.do"; // 菜单地址(权限用)
	@Resource(name = "mymenuService")
	private MyMenuManger mymenuService;
	
	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "列表mymenu");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;}
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		List<PageData> varList = mymenuService.list(page); 

		mv.setViewName("weixin/mymenu/mymenu_list");
		mv.addObject("varList", varList.get(0).get("menu_json"));

		mv.addObject("pd", pd);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		return mv;
	}
	
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"保存mymenu");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		
		PageData pd = new PageData();
		pd = this.getPageData();
		if(StrKit.isBlank(pd.getString("menu_json")))
			return "fail";
		
		
		/*@dm@1【流量充值】 @dm@2【话费充值】 @dm@3【充值查询】@dm@4【个人中心】 @dm@5【推广名片】*/
		
		String llczurl = SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/recharge.html", true);
		String hfczurl = SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/phcharge.html", true);
		String czcxurl = SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/ordrecord.html", true);
		String wxuserurl = SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/wxuser.html", true);
		String qrcodeurl = SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/qrcode.html", true);
		
		String menujson = pd.getString("menu_json").replace("@dm@1", llczurl).replace("@dm@2", hfczurl).replace("@dm@3", czcxurl).replace("@dm@4", wxuserurl).replace("@dm@5", qrcodeurl);
//		ApiConfigKit.setThreadLocalApiConfig(getApiConfig());
//		String access_token = AccessTokenApi.getAccessTokenStr();
		
		String access_token = MenuUtil.getAccess_token();
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
			os.write(menujson.getBytes("UTF-8"));// 传入参数
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			if(message.indexOf("ok") > 0)
			{
				if(mymenuService.isExist("")){
					mymenuService.updateByMenuId(pd);
				}else{
					mymenuService.save(pd);
				}
				return "success";
			}else{
				return "fail";
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "fail";
	}
	
	/**
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/long2short")
	@ResponseBody
	public ModelAndView long2short(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername() + "long2short");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		mv.setViewName("weixin/mymenu/long2short");
		mv.addObject("pd", pd);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		return mv;
	}
	
	/**
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/do2short")
	@ResponseBody
	public String do2short(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername() + "do2short");
		PageData pd = new PageData();
		pd = this.getPageData();
		ApiConfigKit.setThreadLocalApiConfig(getApiConfig());
		ApiResult api = ShorturlApi.getShortUrl(pd.getString("longurl"));
		return api.toString();
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

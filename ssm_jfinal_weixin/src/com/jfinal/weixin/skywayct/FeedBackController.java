package com.jfinal.weixin.skywayct;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.weixin.business.WeixinBusiness;
import com.jfinal.weixin.model.FeedBack;
import com.jfinal.weixin.model.Order;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.TemplateMsgApi;
import com.jfinal.weixin.sdk.jfinal.ApiController;
import com.jfinal.weixin.sdk.kit.ToolKit;
import com.jfinal.weixin.skywayct.api.inteface.OrderHandler;
import com.skywayct.util.MenuUtil;

public class FeedBackController  extends ApiController {
	static Log logger = Log.getLog(FeedBackController.class);
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
	
	public void index() throws Exception {
		getRequest();
		String TaskID = getPara("TaskID");
		String Status = getPara("Status");
		String reportStr = getPara("msg");
		if(TaskID==null||TaskID.equals("")){
			StringBuilder json = new StringBuilder();
			BufferedReader reader;

			reader = getRequest().getReader();
			String line = null;
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
			reader.close();

			JSONObject resultOut = JSONObject.fromObject(json.toString());
			TaskID = resultOut.getString("serial");
			Status = resultOut.getString("code");
			reportStr = resultOut.getString("msg");
		}
		logger.info("TaskID-----------"+TaskID);
		logger.info("Status-----------"+Status);
		FeedBack fb = new FeedBack();
		fb.set("taskid", TaskID);
		fb.set("status", Status);
		fb.save();
		Order order =Order.dao.findFirst(" select * from weixin_order where TaskID ='"+TaskID+"'");
		if(order==null||order.getInt("status")==0||order.getInt("status")==1){
			renderText("ok");
			return;
		}
		if(Status!=null&&Status.equals("10000")){
			  OrderHandler.reNewOrder(order, true);
			  renderText("ok");
			  TemplateMsgApi.send("{ \"touser\" : \""+order.getStr("openid")+"\", \"template_id\" : \""+PropKit.get("msgid")+"\", \"url\" : \"\", \"data\" :{ \"first\" : { \"value\" : \"恭喜你流量充值成功！\", \"color\" : \"#173177\" }, \"keyword1\" :{ \"value\" : \""+order.getStr("note")+"\", \"color\" : \"#173177\" }, \"keyword2\" : { \"value\" : \""+PropKit.get("appName")+"\", \"color\" : \"#173177\" }, \"keyword3\" : { \"value\" : \""+order.getStr("phone")+"\", \"color\" : \"#173177\" }, \"keyword4\" : { \"value\" : \""+order.getDouble("settlement_price")+"元\", \"color\" : \"#173177\" }, \"remark\" :{ \"value\" : \"于 "+order.getTimestamp("recharge_dttm")+" 充值,欢迎再次充值！\", \"color\" : \"#173177\" } } }");
			  return;
		}
		else{
			  OrderHandler.reNewOrder(order, false);
			  //退款?
			  if(order.getInt("settlement_method")==1){
				  WeixinBusiness b = new WeixinBusiness();
				  b.refund(new String[]{order.getStr("order_id")});
			  }
			  renderText("ok");
			  
			  if( StrKit.notBlank(reportStr) ){
			  order.set("log", reportStr);
			  }else{
				  order.set("log", "充值失败");
			  }
			  try{
			  order.update();
			  }catch(Exception e){
				  e.printStackTrace();
				  return;
			  }			  
			  return;
		}
	}
	
	
//	public static void main(String args[]) throws UnsupportedEncodingException{
//		PropKit.use("config_pro.txt");
//		ApiConfigKit.setThreadLocalApiConfig(new MenuUtil().getApiConfig());
//		TemplateMsgApi.send("{ \"touser\" : \"obNUxwY6d6LkXj8mHSkqEhT9UT9o\", \"template_id\" : \""+PropKit.get("msgid")+"\", \"url\" : \"\", \"data\" :{ \"first\" : { \"value\" : \"恭喜你流量充值成功！\", \"color\" : \"#173177\" }, \"keyword1\" :{ \"value\" : \"10M\", \"color\" : \"#173177\" }, \"keyword2\" : { \"value\" : \""+new String(PropKit.get("appName").getBytes("UTF-8"),"UTF-8")+"\", \"color\" : \"#173177\" }, \"keyword3\" : { \"value\" : \"13265184912\", \"color\" : \"#173177\" }, \"keyword4\" : { \"value\" : \"10元\", \"color\" : \"#173177\" }, \"remark\" :{ \"value\" : \"于 2016-09-23 充值,欢迎再次充值！\", \"color\" : \"#173177\" } } }");
//		
//		
//	}

}

/**
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.skywayct;

import java.io.File;

import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.weixin.model.Member;
import com.jfinal.weixin.model.Memberqrcode;
import com.jfinal.weixin.model.Scene;
import com.jfinal.weixin.model.Statistics;
import com.jfinal.weixin.model.Textmsg;
import com.jfinal.weixin.model.Wallet;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.api.UserApi;
import com.jfinal.weixin.sdk.jfinal.MsgControllerAdapter;
import com.jfinal.weixin.sdk.kit.ImageKit;
import com.jfinal.weixin.sdk.msg.in.InImageMsg;
import com.jfinal.weixin.sdk.msg.in.InLinkMsg;
import com.jfinal.weixin.sdk.msg.in.InLocationMsg;
import com.jfinal.weixin.sdk.msg.in.InShortVideoMsg;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.InVideoMsg;
import com.jfinal.weixin.sdk.msg.in.InVoiceMsg;
import com.jfinal.weixin.sdk.msg.in.event.InCustomEvent;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.msg.in.event.InLocationEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMassEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.msg.in.event.InQrCodeEvent;
import com.jfinal.weixin.sdk.msg.in.event.InTemplateMsgEvent;
import com.jfinal.weixin.sdk.msg.in.speech_recognition.InSpeechRecognitionResults;
import com.jfinal.weixin.sdk.msg.out.OutCustomMsg;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;
import com.skywayct.util.DateUtil;
import com.skywayct.util.UuidUtil;
import com.skywayct.util.weixin.WXTagUtil;

/**
 * 将此 DemoController 在YourJFinalConfig 中注册路由，
 * 并设置好weixin开发者中心的 URL 与 token ，使 URL 指向该
 * DemoController 继承自父类 WeixinController 的 index
 * 方法即可直接运行看效果，在此基础之上修改相关的方法即可进行实际项目开发
 */
public class WeixinMsgController extends MsgControllerAdapter {
	
	static Log logger = Log.getLog(WeixinMsgController.class);
	//private static final String helpStr = "";

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

	protected void processInTextMsg(InTextMsg inTextMsg)
	{
		Textmsg tm = Textmsg.dao.findFirst(" select * from weixin_textmsg where KEYWORD ='"+inTextMsg.getContent()+"'");
		if(tm!=null&&inTextMsg.getContent().equals(tm.getStr("KEYWORD"))){
			OutTextMsg textmsg = new OutTextMsg(inTextMsg);
			textmsg.setContent(tm.getStr("CONTENT"));
			render(textmsg);
			return;
		}
		else{
		   tm = Textmsg.dao.findFirst(" select * from weixin_textmsg where KEYWORD ='默认回复'");
		   if(tm!=null){
			  OutTextMsg textmsg = new OutTextMsg(inTextMsg);
			  textmsg.setContent(tm.getStr("CONTENT"));
			  render(textmsg);
			  return;
		   }
		}
		//转发给多客服PC客户端 OutCustomMsg
		//公众号上返回信息 OutTextMsg  处理关键字信息
//		if("模板".endsWith(inTextMsg.getContent())){
//			TemplateMsgApi.send("{ \"touser\" : \""+inTextMsg.getFromUserName()+"\", \"template_id\" : \"jYxPRAI7GTo_YXAlKineCPyBhTLQvYVilirSbtoXug8\", \"url\" : \"http://weixin.qq.com/download\", \"data\" :{ \"first\" : { \"value\" : \"恭喜你购买成功！\", \"color\" : \"#173177\" }, \"keyword1\" :{ \"value\" : \"巧克力\", \"color\" : \"#173177\" }, \"keyword2\" : { \"value\" : \"39.8元\", \"color\" : \"#173177\" }, \"keyword3\" : { \"value\" : \"2014年9月22日\", \"color\" : \"#173177\" }, \"keyword4\" : { \"value\" : \"2014年9月22日\", \"color\" : \"#173177\" }, \"remark\" :{ \"value\" : \"欢迎再次购买！\", \"color\" : \"#173177\" } } }");
//			return;
//		}
		//OutNewsMsg outMsg = new OutNewsMsg(inTextMsg); 图文消息
		OutCustomMsg outCustomMsg = new OutCustomMsg(inTextMsg);
		render(outCustomMsg);
	}

	@Override
	protected void processInVoiceMsg(InVoiceMsg inVoiceMsg)
	{
		//转发给多客服PC客户端
		OutCustomMsg outCustomMsg = new OutCustomMsg(inVoiceMsg);
		render(outCustomMsg);
	}

	@Override
	protected void processInVideoMsg(InVideoMsg inVideoMsg)
	{
		//转发给多客服PC客户端
		OutCustomMsg outCustomMsg = new OutCustomMsg(inVideoMsg);
		render(outCustomMsg);
	}

	@Override
	protected void processInShortVideoMsg(InShortVideoMsg inShortVideoMsg)
	{
		//转发给多客服PC客户端
		OutCustomMsg outCustomMsg = new OutCustomMsg(inShortVideoMsg);
		render(outCustomMsg);
	}

	@Override
	protected void processInLocationMsg(InLocationMsg inLocationMsg)
	{
		//转发给多客服PC客户端
		OutCustomMsg outCustomMsg = new OutCustomMsg(inLocationMsg);
		render(outCustomMsg);
	}

	@Override
	protected void processInLinkMsg(InLinkMsg inLinkMsg)
	{
		//转发给多客服PC客户端
		OutCustomMsg outCustomMsg = new OutCustomMsg(inLinkMsg);
		render(outCustomMsg);
	}

	@Override
	protected void processInCustomEvent(InCustomEvent inCustomEvent)
	{
		logger.debug("测试方法：processInCustomEvent()");
		renderNull();
	}

	protected void processInImageMsg(InImageMsg inImageMsg)
	{
		//转发给多客服PC客户端
		OutCustomMsg outCustomMsg = new OutCustomMsg(inImageMsg);
		render(outCustomMsg);
	}

	/**
	 * 实现父类抽方法，处理关注/取消关注消息
	 */
	protected void processInFollowEvent(InFollowEvent inFollowEvent)
	{
		if (InFollowEvent.EVENT_INFOLLOW_SUBSCRIBE.equals(inFollowEvent.getEvent()))
		{
			logger.debug("关注：" + inFollowEvent.getFromUserName());
			OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
			String msg = synMember(inFollowEvent.getFromUserName(),null);
			if(msg.equals("0")){
				try {
					File _fileact = new File(ImageKit.getpath() + "activity.txt");
					if(_fileact.exists()){
						outMsg.setContent("欢迎来到流量地主公众平台\r\n感谢您的关注 \r\n \r\n"
								+ "	<a href='http://mp.weixin.qq.com/s?__biz=MzI0MTI2ODU4OA==&mid=100000024&idx=1&sn=da115995ae860e9d6b4131e4999eea1e#rd'>新手推广指引</a>\r\n \r\n "
								+ "	<a href='"+SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/wxuser.html",true)+"'>进入个人中心</a>\r\n \r\n "
								+ " <a href='"+SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/recharge.html",true)+"'>进入流量充值</a>\r\n"
								+ "邀请好友赢大奖活动火爆进行中,参与方式：\r\n1.关注流量地主公众号"
								+ "\r\n2.点击通过流量地主公众号下方栏目：邀好友赢大奖。将我的推荐名片发送给好友，当好友成功关注后视为一次成功邀请，即可获得1积分。邀请朋友数量越多积分越高，赢取更高的奖品机会。"
								+ " \r\n<a href='"+ SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/qrcode.html",true) +"'>立即参与</a>");
						render(outMsg);
					}else{
						outMsg.setContent("欢迎来到流量地主公众平台\r\n感谢您的关注 \r\n \r\n"
								+ "	<a href='http://mp.weixin.qq.com/s?__biz=MzI0MTI2ODU4OA==&mid=100000024&idx=1&sn=da115995ae860e9d6b4131e4999eea1e#rd'>新手推广指引</a>\r\n \r\n \r\n"
								+ "	<a href='"+SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/wxuser.html",true)+"'>进入个人中心</a>\r\n \r\n \r\n"
								+ " <a href='"+SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/recharge.html",true)+"'>进入流量充值</a>");
						render(outMsg);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		}
		// 如果为取消关注事件，将无法接收到传回的信息
		if (InFollowEvent.EVENT_INFOLLOW_UNSUBSCRIBE.equals(inFollowEvent.getEvent()))
		{
			logger.debug("取消关注：" + inFollowEvent.getFromUserName());
			synMember(inFollowEvent.getFromUserName(),null);
			render("");
		}
	}

	@Override
	protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent)
	{
		if (InQrCodeEvent.EVENT_INQRCODE_SUBSCRIBE.equals(inQrCodeEvent.getEvent()))
		{
			logger.debug("扫码未关注：" + inQrCodeEvent.getFromUserName());
			String msg = synMember(inQrCodeEvent.getFromUserName(),inQrCodeEvent.getEventKey());
			if(msg.equals("0")){
				OutTextMsg outMsg = new OutTextMsg(inQrCodeEvent);
				try {
					File _fileact = new File(ImageKit.getpath() + "activity.txt");
					if(_fileact.exists()){
						outMsg.setContent("欢迎来到流量地主公众平台\r\n感谢您的关注 \r\n \r\n"
								+ "	<a href='http://mp.weixin.qq.com/s?__biz=MzI0MTI2ODU4OA==&mid=100000024&idx=1&sn=da115995ae860e9d6b4131e4999eea1e#rd'>新手推广指引</a>\r\n \r\n "
								+ "	<a href='"+SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/wxuser.html",true)+"'>进入个人中心</a>\r\n \r\n "
								+ " <a href='"+SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/recharge.html",true)+"'>进入流量充值</a>\r\n"
								+ "邀请好友赢大奖活动火爆进行中,参与方式：\r\n1.关注流量地主公众号"
								+ "\r\n2.点击通过流量地主公众号下方栏目：邀好友赢大奖。将我的推荐名片发送给好友，当好友成功关注后视为一次成功邀请，即可获得1积分。邀请朋友数量越多积分越高，赢取更高的奖品机会。"
								+ " \r\n<a href='"+ SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/qrcode.html",true) +"'>立即参与</a>");
						render(outMsg);
					}else{
						outMsg.setContent("欢迎来到流量地主公众平台\r\n感谢您的关注 \r\n \r\n"
								+ "	<a href='http://mp.weixin.qq.com/s?__biz=MzI0MTI2ODU4OA==&mid=100000024&idx=1&sn=da115995ae860e9d6b4131e4999eea1e#rd'>新手推广指引</a>\r\n \r\n \r\n"
								+ "	<a href='"+SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/wxuser.html",true)+"'>进入个人中心</a>\r\n \r\n \r\n"
								+ " <a href='"+SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/recharge.html",true)+"'>进入流量充值</a>");
						render(outMsg);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (InQrCodeEvent.EVENT_INQRCODE_SCAN.equals(inQrCodeEvent.getEvent()))
		{
			logger.debug("扫码已关注：" + inQrCodeEvent.getFromUserName());
		}
	}

	@Override
	protected void processInLocationEvent(InLocationEvent inLocationEvent)
	{
		logger.debug("发送地理位置事件：" + inLocationEvent.getFromUserName());
		OutTextMsg outMsg = new OutTextMsg(inLocationEvent);
		outMsg.setContent("地理位置是：" + inLocationEvent.getLatitude());
		render(outMsg);
	}

	@Override
	protected void processInMassEvent(InMassEvent inMassEvent)
	{
		logger.debug("测试方法：processInMassEvent()");
		renderNull();
	}

	/**
	 * 实现父类抽方法，处理自定义菜单事件
	 */
	protected void processInMenuEvent(InMenuEvent inMenuEvent)
	{
		logger.debug("菜单事件：" + inMenuEvent.getEventKey());
		OutTextMsg outMsg = new OutTextMsg(inMenuEvent); //文本消息
//		OutNewsMsg newsMsg = new OutNewsMsg();  //图文消息
		if(inMenuEvent.getEventKey().equals("HF"))
			outMsg.setContent("话费功能尚未开通");
		else if(inMenuEvent.getEventKey().equals("DKF"))
		{
			OutCustomMsg outCustomMsg = new OutCustomMsg(inMenuEvent);
			render(outCustomMsg);
			return;
		}	
		else
		{
			Textmsg tm = Textmsg.dao.findFirst(" select * from weixin_textmsg where KEYWORD ='"+inMenuEvent.getEventKey()+"'");
			if(tm!=null&&inMenuEvent.getEventKey().equals(tm.getStr("KEYWORD"))){
				outMsg.setContent(tm.getStr("CONTENT"));
			}
			else{
			   tm = Textmsg.dao.findFirst(" select * from weixin_textmsg where KEYWORD ='默认回复'");
			   if(tm!=null){
				   outMsg.setContent(tm.getStr("CONTENT"));
			   }
			}
		}	
		//outMsg.setContent(inMenuEvent.getEventKey());
		render(outMsg);
	}

	@Override
	protected void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults)
	{
		logger.debug("语音识别事件：" + inSpeechRecognitionResults.getFromUserName());
		OutTextMsg outMsg = new OutTextMsg(inSpeechRecognitionResults);
		outMsg.setContent("语音识别内容是：" + inSpeechRecognitionResults.getRecognition());
		render(outMsg);
	}

	@Override
	protected void processInTemplateMsgEvent(InTemplateMsgEvent inTemplateMsgEvent)
	{
		logger.debug("测试方法：processInTemplateMsgEvent()");
		renderNull();
	}
	
	public String synMember(String fromUserName,String event){
		//获取token
		//String access_token = AccessTokenApi.getAccessTokenStr();
		//获取用户信息
		ApiResult result =UserApi.getUserInfo(fromUserName);
		logger.debug("--------------获取的用户信息 ："+result.toString());
		String msg = null;
		if(result==null) return "0" ;
		Member m = new Member();
		m  = m.findFirst("select * from weixin_member where openid= '"+fromUserName+"'");
		try {
		if(m!=null&&result.get("subscribe")!=null&&result.getInt("subscribe")==0){
			m.set("dr", 1);
			m.update();
			//statistics(m.getStr("openid"),m.getStr("parent_id"),-1);
			Statistics.addStatistics(m.getStr("openid"), m.getStr("parent_id"), -1);
			return "0" ; 
		}
		//判断以前是否关注过;

		if(m!=null && m.getInt("is_black") == null){
			m.set("dr", 0);
			m.set("nick_name", result.getStr("nickname"));
			m.set("name", result.getStr("nickname"));
			m.set("photo", result.getStr("headimgurl"));
			m.set("sex",  result.getInt("sex"));
			m.set("province", result.getStr("province"));
			m.set("country", result.getStr("country"));
			m.set("city", result.getStr("city"));
//			m.set("create_date", DateUtil.fomatDateTiem(System.currentTimeMillis()));
			m.update();
			//统计人数
			//statistics(m.getStr("openid"),m.getStr("parent_id"),1);
			Statistics.addStatistics(m.getStr("openid"), m.getStr("parent_id"), 1);
			msg = "0";
		}else if(m!=null && m.getInt("is_black") == 0){//	如果后台查到该会员是在黑名单，则将其再次同步设置到微信端黑名单标签组
			String tag = "{\"openid_list\":[\"" + m.getStr("openid") + "\"],\"tagid\":2}";//	因为星标组tagid固定为2，避免新建标签的tagid不固定，所以用星标组用来作为黑名单组
			String message = WXTagUtil.addTagToU(tag);
			msg = "1";
		}
		else{
			m = new Member();
			m.set("ordinarymember_id", UuidUtil.get32UUID());
			m.set("openid", fromUserName);
			m.set("nick_name", result.getStr("nickname"));
			m.set("name", result.getStr("nickname"));
			m.set("photo", result.getStr("headimgurl"));
			m.set("sex",  result.getInt("sex"));
			m.set("province", result.getStr("province"));
			m.set("country", result.getStr("country"));
			m.set("city", result.getStr("city"));
			m.set("dr", 0);
			m.set("create_date", DateUtil.fomatDateTiem(System.currentTimeMillis()));
			m.set("level_id", "0");
			//查询场景值
			if(event!=null){
				logger.info("*******************************"+event);
				Scene s = null;
				s = Scene.dao.findFirst("select a.scene_id,a.open_id from weixin_scene a,weixin_menberqrcode b where a.scene_id = b.scene_id and status =1 and a.scene_id = ?",event.substring(8, event.length()));
			
				if(s!=null && StrKit.isBlank(s.getStr("open_id"))){
					s.set("open_id", fromUserName);
					s.update();
					Memberqrcode mq = Memberqrcode.dao.findFirst("select * from weixin_menberqrcode where scene_id = ?",event.substring(8, event.length()));
					if(mq!=null){
						mq.set("name", result.getStr("nickname"));
						mq.set("open_id",fromUserName);
						mq.update();
					}
					m.set("parent_id", "0");
				}else{
					s = Scene.dao.findFirst("SELECT a.*,CONCAT(IFNULL(b.`parents`,''),CONCAT('[',RIGHT(CONCAT('000000000',b.id),9),']')) AS parents FROM weixin_scene a,weixin_member b WHERE scene_id = ? and a.open_id = b.openid",event.substring(8, event.length()));
					if(s!=null)
					{
						m.set("parent_id", s.getStr("open_id"));
						m.set("parents", s.getStr("parents"));
					}else{
						m.set("parent_id", "0");
					}
				}
			}else{
				m.set("parent_id", "0");
			}
			m.save();
			//统计人数
			//statistics(m.getStr("openid"),m.getStr("parent_id"),1);
			Statistics.addStatistics(m.getStr("openid"), m.getStr("parent_id"), 1);
			//新建账户
			Wallet w=null;
			//判断账户是否已存在
			w = Wallet.dao.findFirst(" select * from weixin_wallet where open_id='"+fromUserName+"' ");
			if(w!=null){
				w.set("dr", 0).update();
			}
			else{
				w = new Wallet();
				w.set("open_id", fromUserName);
				w.set("amount", 0.0);
				w.set("dr", 0);
				w.set("wallet_id",  UuidUtil.get32UUID());
				w.save();
			}
			msg = "0";					
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
	


//	/**
//	 * 实现父类抽方法，处理文本消息
//	 * 本例子中根据消息中的不同文本内容分别做出了不同的响应，同时也是为了测试 jfinal weixin sdk的基本功能：
//	 *     本方法仅测试了 OutTextMsg、OutNewsMsg、OutMusicMsg 三种类型的OutMsg，
//	 *     其它类型的消息会在随后的方法中进行测试
//	 */
//	protected void processInTextMsg(InTextMsg inTextMsg) {
//		String msgContent = inTextMsg.getContent().trim();
//		// 帮助提示
//		if ("help".equalsIgnoreCase(msgContent) || "帮助".equals(msgContent)) {
//			OutTextMsg outMsg = new OutTextMsg(inTextMsg);
//			outMsg.setContent(helpStr);
//			render(outMsg);
//		}
//		// 图文消息测试
//		else if ("news".equalsIgnoreCase(msgContent) || "新闻".equalsIgnoreCase(msgContent)) {
//			OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
//			outMsg.addNews("JFinal 2.0 发布,JAVA 极速 WEB+ORM 框架", "本星球第一个极速开发框架", "https://mmbiz.qlogo.cn/mmbiz/KJoUl0sqZFS0fRW68poHoU3v9ulTWV8MgKIduxmzHiamkb3yHia8pCicWVMCaFRuGGMnVOPrrj2qM13u9oTahfQ9A/0?wx_fmt=png", "http://mp.weixin.qq.com/s?__biz=MzA4NjM4Mjk2Mw==&mid=211063163&idx=1&sn=87d54e2992237a3f791f08b5cdab7990#rd");
//			outMsg.addNews("JFinal 1.8 发布,JAVA 极速 WEB+ORM 框架", "现在就加入 JFinal 极速开发世界，节省更多时间去跟女友游山玩水 ^_^", "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq1ibBkhSA1BibMuMxLuHIvUfiaGsK7CC4kIzeh178IYSHbYQ5eg9tVxgEcbegAu22Qhwgl5IhZFWWXUw/0", "http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=200313981&idx=1&sn=3bc5547ba4beae12a3e8762ababc8175#rd");
//			outMsg.addNews("JFinal 1.6 发布,JAVA 极速 WEB+ORM 框架", "JFinal 1.6 主要升级了 ActiveRecord 插件，本次升级全面支持多数源、多方言、多缓", "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq0fcR8VmNCgugHXv7gVlxI6w95RBlKLdKUTjhOZIHGSWsGvjvHqnBnjIWHsicfcXmXlwOWE6sb39kA/0", "http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=200121522&idx=1&sn=ee24f352e299b2859673b26ffa4a81f6#rd");
//			render(outMsg);
//		}
//		// 音乐消息测试
//		else if ("music".equalsIgnoreCase(msgContent) || "音乐".equals(msgContent)) {
//			OutMusicMsg outMsg = new OutMusicMsg(inTextMsg);
//			outMsg.setTitle("When The Stars Go Blue-Venke Knutson");
//			outMsg.setDescription("建议在 WIFI 环境下流畅欣赏此音乐");
//			outMsg.setMusicUrl("http://www.jfinal.com/When_The_Stars_Go_Blue-Venke_Knutson.mp3");
//			outMsg.setHqMusicUrl("http://www.jfinal.com/When_The_Stars_Go_Blue-Venke_Knutson.mp3");
//			outMsg.setFuncFlag(true);
//			render(outMsg);
//		}
//		else if ("美女".equalsIgnoreCase(msgContent)) {
//			OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
//			outMsg.addNews(
//					"JFinal 宝贝更新喽",
//					"jfinal 宝贝更新喽，我们只看美女 ^_^",
//					"https://mmbiz.qlogo.cn/mmbiz/KJoUl0sqZFRHa3VrmibqAXRfYPNdiamFnpPTOvXoxsFlRoOHbVibGhmHOEUQiboD3qXWszKuzWpibFxsVW1RmNB9hPw/0?wx_fmt=jpeg",
//					"http://mp.weixin.qq.com/s?__biz=MzA4NjM4Mjk2Mw==&mid=211356950&idx=1&sn=6315a1a2848aa8cb0694bf1f4accfb07#rd");
//			// outMsg.addNews("秀色可餐", "JFinal Weixin 极速开发就是这么爽，有木有 ^_^", "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq2GJLC60ECD7rE7n1cvKWRNFvOyib4KGdic3N5APUWf4ia3LLPxJrtyIYRx93aPNkDtib3ADvdaBXmZJg/0", "http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=200987822&idx=1&sn=7eb2918275fb0fa7b520768854fb7b80#rd");
//
//			render(outMsg);
//		}
//		else if ("视频教程".equalsIgnoreCase(msgContent) || "视频".equalsIgnoreCase(msgContent)) {
//			renderOutTextMsg("\thttp://pan.baidu.com/s/1nt2zAT7  \t密码:824r");
//		}
//		// 其它文本消息直接返回原值 + 帮助提示
//		else {
//			renderOutTextMsg("\t文本消息已成功接收，内容为： " + inTextMsg.getContent() + "\n\n" + helpStr);
//		}
//	}
//
//	/**
//	 * 实现父类抽方法，处理图片消息
//	 */
//	protected void processInImageMsg(InImageMsg inImageMsg) {
//		OutImageMsg outMsg = new OutImageMsg(inImageMsg);
//		// 将刚发过来的图片再发回去
//		outMsg.setMediaId(inImageMsg.getMediaId());
//		render(outMsg);
//	}
//
//	/**
//	 * 实现父类抽方法，处理语音消息
//	 */
//	protected void processInVoiceMsg(InVoiceMsg inVoiceMsg) {
//		OutVoiceMsg outMsg = new OutVoiceMsg(inVoiceMsg);
//		// 将刚发过来的语音再发回去
//		outMsg.setMediaId(inVoiceMsg.getMediaId());
//		render(outMsg);
//	}
//
//	/**
//	 * 实现父类抽方法，处理视频消息
//	 */
//	protected void processInVideoMsg(InVideoMsg inVideoMsg) {
//		/* 腾讯 api 有 bug，无法回复视频消息，暂时回复文本消息代码测试
//		OutVideoMsg outMsg = new OutVideoMsg(inVideoMsg);
//		outMsg.setTitle("OutVideoMsg 发送");
//		outMsg.setDescription("刚刚发来的视频再发回去");
//		// 将刚发过来的视频再发回去，经测试证明是腾讯官方的 api 有 bug，待 api bug 却除后再试
//		outMsg.setMediaId(inVideoMsg.getMediaId());
//		render(outMsg);
//		*/
//		OutTextMsg outMsg = new OutTextMsg(inVideoMsg);
//		outMsg.setContent("\t视频消息已成功接收，该视频的 mediaId 为: " + inVideoMsg.getMediaId());
//		render(outMsg);
//	}
//
//	@Override
//	protected void processInShortVideoMsg(InShortVideoMsg inShortVideoMsg)
//	{
//		OutTextMsg outMsg = new OutTextMsg(inShortVideoMsg);
//		outMsg.setContent("\t视频消息已成功接收，该视频的 mediaId 为: " + inShortVideoMsg.getMediaId());
//		render(outMsg);
//	}
//
//	/**
//	 * 实现父类抽方法，处理地址位置消息
//	 */
//	protected void processInLocationMsg(InLocationMsg inLocationMsg) {
//		OutTextMsg outMsg = new OutTextMsg(inLocationMsg);
//		outMsg.setContent("已收到地理位置消息:" +
//							"\nlocation_X = " + inLocationMsg.getLocation_X() +
//							"\nlocation_Y = " + inLocationMsg.getLocation_Y() +
//							"\nscale = " + inLocationMsg.getScale() +
//							"\nlabel = " + inLocationMsg.getLabel());
//		render(outMsg);
//	}
//
//	/**
//	 * 实现父类抽方法，处理链接消息
//	 * 特别注意：测试时需要发送我的收藏中的曾经收藏过的图文消息，直接发送链接地址会当做文本消息来发送
//	 */
//	protected void processInLinkMsg(InLinkMsg inLinkMsg) {
//		OutNewsMsg outMsg = new OutNewsMsg(inLinkMsg);
//		outMsg.addNews("链接消息已成功接收", "链接使用图文消息的方式发回给你，还可以使用文本方式发回。点击图文消息可跳转到链接地址页面，是不是很好玩 :)" , "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq1ibBkhSA1BibMuMxLuHIvUfiaGsK7CC4kIzeh178IYSHbYQ5eg9tVxgEcbegAu22Qhwgl5IhZFWWXUw/0", inLinkMsg.getUrl());
//		render(outMsg);
//	}
//
//	@Override
//	protected void processInCustomEvent(InCustomEvent inCustomEvent)
//	{
//		System.out.println("processInCustomEvent() 方法测试成功");
//	}
//
//	/**
//	 * 实现父类抽方法，处理关注/取消关注消息
//	 */
//	protected void processInFollowEvent(InFollowEvent inFollowEvent) {
//		OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
//		outMsg.setContent("感谢关注 JFinal Weixin 极速开发服务号，为您节约更多时间，去陪恋人、家人和朋友 :) \n\n\n " + helpStr);
//		// 如果为取消关注事件，将无法接收到传回的信息
//		render(outMsg);
//	}
//
//	/**
//	 * 实现父类抽方法，处理扫描带参数二维码事件
//	 */
//	protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
//		OutTextMsg outMsg = new OutTextMsg(inQrCodeEvent);
//		outMsg.setContent("processInQrCodeEvent() 方法测试成功");
//		render(outMsg);
//	}
//
//	/**
//	 * 实现父类抽方法，处理上报地理位置事件
//	 */
//	protected void processInLocationEvent(InLocationEvent inLocationEvent) {
//		OutTextMsg outMsg = new OutTextMsg(inLocationEvent);
//		outMsg.setContent("processInLocationEvent() 方法测试成功");
//		render(outMsg);
//	}
//
//	@Override
//	protected void processInMassEvent(InMassEvent inMassEvent)
//	{
//		System.out.println("processInMassEvent() 方法测试成功");
//	}
//
//	/**
//	 * 实现父类抽方法，处理自定义菜单事件
//	 */
//	protected void processInMenuEvent(InMenuEvent inMenuEvent) {
//		renderOutTextMsg("processInMenuEvent() 方法测试成功");
//	}
//
//	/**
//	 * 实现父类抽方法，处理接收语音识别结果
//	 */
//	protected void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults) {
//		renderOutTextMsg("语音识别结果： " + inSpeechRecognitionResults.getRecognition());
//	}
//
//	// 处理接收到的模板消息是否送达成功通知事件
//	protected void processInTemplateMsgEvent(InTemplateMsgEvent inTemplateMsgEvent) {
//		String status = inTemplateMsgEvent.getStatus();
//		renderOutTextMsg("模板消息是否接收成功：" + status);
//	}
//	@Override
//	protected void processInShakearoundUserShakeEvent(InShakearoundUserShakeEvent inShakearoundUserShakeEvent) {
//		logger.debug("摇一摇周边设备信息通知事件：" + inShakearoundUserShakeEvent.getFromUserName());
//		OutTextMsg outMsg = new OutTextMsg(inShakearoundUserShakeEvent);
//		outMsg.setContent("摇一摇周边设备信息通知事件UUID：" + inShakearoundUserShakeEvent.getUuid());
//		render(outMsg);
//	}
//
//	@Override
//	protected void processInVerifySuccessEvent(InVerifySuccessEvent inVerifySuccessEvent) {
//		logger.debug("资质认证成功通知事件：" + inVerifySuccessEvent.getFromUserName());
//		OutTextMsg outMsg = new OutTextMsg(inVerifySuccessEvent);
//		outMsg.setContent("资质认证成功通知事件：" + inVerifySuccessEvent.getExpiredTime());
//		render(outMsg);
//	}
//
//	@Override
//	protected void processInVerifyFailEvent(InVerifyFailEvent inVerifyFailEvent){
//		logger.debug("资质认证失败通知事件：" + inVerifyFailEvent.getFromUserName());
//		OutTextMsg outMsg = new OutTextMsg(inVerifyFailEvent);
//		outMsg.setContent("资质认证失败通知事件：" + inVerifyFailEvent.getFailReason());
//		render(outMsg);
//	}
}







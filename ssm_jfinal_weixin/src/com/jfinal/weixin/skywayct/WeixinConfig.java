
package com.jfinal.weixin.skywayct;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.weixin.demo.WeixinApiController;
import com.jfinal.weixin.demo.WeixinPayController;
import com.jfinal.weixin.info.ActReportController;
import com.jfinal.weixin.info.CashController;
import com.jfinal.weixin.info.CheckSubscController;
import com.jfinal.weixin.info.CommissionController;
import com.jfinal.weixin.info.MyteamInfoController;
import com.jfinal.weixin.info.OrderRecordInfoController;
import com.jfinal.weixin.info.UserInfoController;
import com.jfinal.weixin.model.AccountLock;
import com.jfinal.weixin.model.Attribution;
import com.jfinal.weixin.model.Cash;
import com.jfinal.weixin.model.Commission;
import com.jfinal.weixin.model.CommissionConfig;
import com.jfinal.weixin.model.Dictionaries;
import com.jfinal.weixin.model.FeedBack;
import com.jfinal.weixin.model.Member;
import com.jfinal.weixin.model.Memberqrcode;
import com.jfinal.weixin.model.MyPoints;
import com.jfinal.weixin.model.Noticeboard;
import com.jfinal.weixin.model.Order;
import com.jfinal.weixin.model.OrderWarning;
import com.jfinal.weixin.model.PayAgainRecord;
import com.jfinal.weixin.model.PayResult;
import com.jfinal.weixin.model.Position;
import com.jfinal.weixin.model.Product;
import com.jfinal.weixin.model.ProductType;
import com.jfinal.weixin.model.ReqRes;
import com.jfinal.weixin.model.Scene;
import com.jfinal.weixin.model.Statistics;
import com.jfinal.weixin.model.Textmsg;
import com.jfinal.weixin.model.Wallet;
import com.jfinal.weixin.model.WarningRecord;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.skywayct.api.OrderController;
import com.jfinal.weixin.skywayct.api.ProductController;
import com.jfinal.weixin.skywayct.api.RechargeController;
import com.jfinal.weixin.skywayct.api.inteface.AllInterface;
import com.jfinal.weixin.skywayct.api.inteface.my.MyFlowServerHandler;

public class WeixinConfig extends JFinalConfig {
	
	/**
	 * 如果生产环境配置文件存在，则优先加载该配置，否则加载开发环境配置文件
	 * @param pro 生产环境配置文件
	 * @param dev 开发环境配置文件
	 */
	public void loadProp(String config) {
		try {
			PropKit.use(config);
		}
		catch (Exception e) {
			//PropKit.use(dev);
		}
	}
	
	public void configConstant(Constants me) {
		loadProp("config_pro.txt");
//		loadProp("config_pro.txt", "config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		//初始化接口处理器
		AllInterface.put(1,new MyFlowServerHandler());
		// ApiConfigKit 设为开发模式可以在开发阶段输出请求交互的 xml 与 json 数据
		ApiConfigKit.setDevMode(me.getDevMode());
		
	}
	
	public void configRoute(Routes me) {
		me.add("/msg", WeixinMsgController.class);
		me.add("/order", OrderController.class);//下单
		me.add("/recharge",RechargeController.class);//充值
		me.add("/api", WeixinApiController.class, "/api");
		me.add("/pay", WeixinPayController.class);
		me.add("/wxclient",UserInfoController.class);
		me.add("/product",ProductController.class);
		me.add("/ordrecord",OrderRecordInfoController.class);
		me.add("/myteam",MyteamInfoController.class);
		me.add("/cash",CashController.class);
		me.add("/comis",CommissionController.class);
		me.add("/transfers",WeixinTransfersController.class);
		me.add("/feedback",FeedBackController.class);
		me.add("/addmypoints", WeixinMyPointController.class);
		me.add("/payagain", WeixinPayAgainController.class);
		me.add("/redpack", RedPackApiController.class);
		me.add("/actreport", ActReportController.class);
		me.add("/checksub",CheckSubscController.class);
	}
	
	public void configPlugin(Plugins me) {
		 C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		 me.add(c3p0Plugin);
		
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		arp.setShowSql(true);
		me.add(arp);
	
		arp.addMapping("weixin_member","ordinarymember_id", Member.class);
		arp.addMapping("weixin_order","order_id", Order.class);
		arp.addMapping("weixin_product","product_id", Product.class);
		arp.addMapping("weixin_product_type","id", ProductType.class);
		arp.addMapping("mobile_attribution","id", Attribution.class);
		arp.addMapping("weixin_wallet","wallet_id", Wallet.class);
		arp.addMapping("weixin_acount_lock","acount_lock_id", AccountLock.class);
		arp.addMapping("i_interface_reqres","id", ReqRes.class);
		arp.addMapping("weixin_scene","scene_id", Scene.class);
		arp.addMapping("sys_dictionaries","dictionaries_id", Dictionaries.class); //
		arp.addMapping("weixin_commission","commission_id", Commission.class);  //佣金
		arp.addMapping("weixin_commission_discount","discount_id", CommissionConfig.class);  //佣金比例
		arp.addMapping("weixin_pay_result","pay_id", PayResult.class); 
		arp.addMapping("weixin_cash","open_id", Cash.class); //提现
		arp.addMapping("weixin_mypoints", "open_id", MyPoints.class);
		arp.addMapping("weixin_feedback_my", "id", FeedBack.class);
		arp.addMapping("weixin_textmsg", "textmsg_id", Textmsg.class);
		arp.addMapping("sys_noticeboard", "noticeboard_id", Noticeboard.class);
		arp.addMapping("weixin_member_statistics", "statistics_id", Statistics.class);
		arp.addMapping("weixin_payagain_record", "agin_id", PayAgainRecord.class);
		arp.addMapping("weixin_position", "position_id", Position.class);
		arp.addMapping("weixin_menberqrcode", "menberqrcode_id", Memberqrcode.class);
		arp.addMapping("weixin_order_warning", "warning_id", OrderWarning.class);
		arp.addMapping("weixin_warning_record", "warnrec_id", WarningRecord.class);
//			RedisPlugin redisPlugin = new RedisPlugin("weixin", "127.0.0.1");Commission
//			me.add(redisPlugin);
	}
	
	public void configInterceptor(Interceptors me) {
		
	}
	
	public void configHandler(Handlers me) {
		
	}
	
	public void afterJFinalStart() {
		// 1.5 之后支持redis存储access_token、js_ticket，需要先启动RedisPlugin
		//ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache());
		// 1.6新增的2种初始化
	//	ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache(Redis.use("weixin")));
//		ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache("weixin"));
	}

	public static void main(String[] args) {
		JFinal.start("webapp", 80, "/", 5);
	}
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

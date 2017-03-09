package com.jfinal.weixin.skywayct.api;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.aop.Clear;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.weixin.business.WeixinBusiness;
import com.jfinal.weixin.model.Order;
import com.jfinal.weixin.model.PayResult;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.jfinal.ApiController;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.jfinal.weixin.skywayct.api.common.FlowResponse;
import com.jfinal.weixin.skywayct.api.common.OrderResponse;
import com.jfinal.weixin.skywayct.api.common.WeixinConst;
import com.jfinal.weixin.skywayct.api.inteface.AllOrderHandler;
import com.jfinal.weixin.skywayct.api.inteface.my.MyFlowServerHandler;

/**
 * @author 
 * @version V1.0
 * @Title: OrderController.java
 * @date 
 * @Description:
 */
public class RechargeController extends ApiController {
	static Log log = Log.getLog(RechargeController.class);

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
	/**
	 * 产品订购操作
	 */
	@Clear
	// @Before({TokenValidator.class ,LogInterceptor.class})
	public void index() {
		
		String xmlMsg = HttpKit.readData(getRequest());
		System.out.println("支付通知="+xmlMsg);
		log.info("支付通知="+xmlMsg);
		Map<String, String> params = PaymentKit.xmlToMap(xmlMsg);
		//通信状态
		String status = params.get("status");
		//业务结果
		String result_code  = params.get("result_code");
		// 总金额
		String totalFee     = params.get("total_fee");
		// 商户订单号
		String orderId      = params.get("out_trade_no");
		// 微信支付订单号
		String transId      = params.get("transaction_id");
		// 支付完成时间，格式为yyyyMMddHHmmss
		String timeEnd      = params.get("time_end");
		//支付结果
		String pay_result = params.get("pay_result");
		
		//保存支付结果
		PayResult ps = new PayResult();
		ps.set("result_code", pay_result);
		ps.set("totalFee", totalFee);
		ps.set("order_id", orderId);
		ps.set("transaction_id", transId);
		ps.set("time_end", timeEnd);
		ps.save();
		
		// 注意重复通知的情况，同一订单号可能收到多次通知，请注意一定先判断订单状态
		// 避免已经成功、关闭、退款的订单被再次更新
		Order order = Order.dao.findById(orderId);
		if(order.getInt("status")==1||order.getInt("status")==0||
		   order.getInt("paystatus")==2||order.getInt("paystatus")==0 || order.getInt("paystatus") ==1){ //充值完成 或者失败  退款.不再充值
			renderText("success");
			return;
		}
		order.set("transaction_id", transId);
		order.update();
		if(PaymentKit.verifyNotify(params, WeixinConst.paternerKey)){
			if (("0").equals(result_code) && ("0").equals(pay_result)) {
				order.set("paystatus", 1).update();
				// 实际订购操作
				FlowResponse flag = AllOrderHandler.doOrder(orderId);
				if(!flag.isStatus()){
					//威富通退款
					WeixinBusiness b = new WeixinBusiness();
					try {
						b.refund(new String[]{order.getStr("order_id")});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				renderText("success");
				return;
			}
			order.set("paystatus", 0).update();
		}else{
			log.info("验证签名错误！");
			renderText("");
			return;
		}
	}
}

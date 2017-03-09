package com.jfinal.weixin.skywayct;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.weixin.business.WeixinBusiness;
import com.jfinal.weixin.model.Order;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.PaymentApi;
import com.jfinal.weixin.sdk.jfinal.ApiController;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.jfinal.weixin.skywayct.api.common.WeixinConst;
import com.skywayct.util.UuidUtil;

/**
 * @author
 * 后台手动退款
 */
public class WeixinTransfersController extends ApiController {
		static Log logger = Log.getLog(WeixinTransfersController.class);

		public void index() throws Exception {
			String order_ids = getPara("DATA_IDS");
			String Order_Id[] = order_ids.split(",");//要退款的订单ID数组
			WeixinBusiness business = new WeixinBusiness();
			String jsonObject = business.refund(Order_Id);
			renderJson(jsonObject);
//			Order order = null;
//			String price  = null;
//			Double amount  = 0.0;
//			String sign = null;
//			String result_code = null;
//			Map<String, String> params = new HashMap<String, String>();
//			
//			String failPhone = "";
//			String successPhone = "";
//			String phone = null;
//			JSONObject jsonObject = new JSONObject();
//			for(int i=0;i<Order_Id.length;i++){
//				order= Order.dao.findById( Order_Id[i]);
//				phone=order.getStr("phone");
//				amount= order.getDouble("amount") * 100 ;
//				if(amount<=0.0) continue;
//				price =(amount+"").substring(0,(amount+"").indexOf("."));
//	
//				// 随机字符串
//				params.put("nonce_str", System.currentTimeMillis() / 1000 + "");
//				// 商户号
//				params.put("mch_id", partner);
//				// 公众账号ID
//				params.put("appid", wxappid);
//				//transaction_id
//				params.put("transaction_id", order.getStr("transaction_id"));
//				//out_trade_no
//				params.put("out_trade_no", order.getStr("order_id"));
//				String out_refund_no = UuidUtil.get32UUID();
//				params.put("out_refund_no", out_refund_no);
//				order.set("out_refund_no", out_refund_no).update();
//				// 
//				params.put("total_fee", price);
//				// refund_fee
//				params.put("refund_fee", price);
//				
//				params.put("refund_fee_type", "CNY");
//				// partner
//				params.put("op_user_id", partner);
//				//创建签名
//				sign= PaymentKit.createSign(params, paternerKey);
//				params.put("sign", sign);
//				
//				try{
//				Map<String, String> result = PaymentApi.refund(params, paternerKey, certPath);
//
//				System.out.println(result);
//				params.clear();
//				result_code = result.get("result_code");
//				//
//				if (StrKit.isBlank(result_code) || !"SUCCESS".equals(result_code)) {
//					System.out.println("退款失败");
//					failPhone += phone +  ",";
//					order.set("refund_log", result.get("return_msg")).update();
//				} else {
//					System.out.println("退款成功");
//					successPhone += phone +  ",";
//					order.set("paystatus", 2).set("refund_log", "退款成功").update();
//					
//				}
//				}catch(Exception ex){
//					ex.printStackTrace();
//					jsonObject.put("failNo", "由于发生错误,其它订单退款失败.");
//					renderJson(jsonObject.toString());
//					return ;
//				}
//			}
//			
//			jsonObject.put("successNo", successPhone);
//			jsonObject.put("failNo", failPhone);
//			renderJson(jsonObject.toString());
//			
//			return ;
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

package com.jfinal.weixin.skywayct;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.model.Cash;
import com.jfinal.weixin.sdk.api.RedPackApi;
import com.jfinal.weixin.sdk.kit.IpKit;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.jfinal.weixin.skywayct.api.common.WeixinConst;
import com.skywayct.util.DateUtil;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * 微信红包
 * @author
 */
public class RedPackApiController extends Controller {
	// 商户相关资料
	private static String wxappid =  PropKit.get("appId");
	// 微信支付分配的商户号
	private static String partner = WeixinConst.wxpartner; 
	private static String sendName = WeixinConst.sendName;
	//API密钥
	private static String paternerKey = WeixinConst.wxpaternerKey;
	//微信证书路径
	private static String certPath = PropKit.get("certPath");
	
	
	
	
    @ActionKey("redpack")
	public void send() {
		// 接受红包的用户用户在wxappid下的openid
		String reOpenid = "";
		Cash cash = null;
		JSONObject resultJson = new JSONObject();
		String cashId = getPara("DATA_IDS");
		
		if( cashId != null && !cashId.equals("") ){
			cash = Cash.dao.findFirst("SELECT * FROM weixin_cash WHERE cash_id='" + cashId + "'");
		if( cash != null ){
		if( cash.getInt("status") != 2 ){
			   
		reOpenid = cash.getStr("open_id");
		// 商户订单号
		String mchBillno = System.currentTimeMillis() + "";
		String ip = IpKit.getRealIp(getRequest());

		Map<String, String> params = new HashMap<String, String>();
		// 随机字符串
		params.put("nonce_str", System.currentTimeMillis() / 1000 + "");
		// 商户订单号
		params.put("mch_billno", mchBillno);
		// 商户号
		params.put("mch_id", partner);
		// 公众账号ID
		params.put("wxappid", wxappid);
		// 商户名称
		params.put("send_name", sendName);
		// 用户OPENID
		params.put("re_openid", reOpenid);
		// 付款现金(单位分)
//		String amount = (cash.getDouble("amount").doubleValue()*100+"");
//		amount = amount.substring(0,amount.indexOf("."));
		params.put("total_amount", DateUtil.getTwoDecimals(cash.getDouble("amount").doubleValue()));
		// 红包发放总人数
		params.put("total_num", "1");
		// 红包祝福语
		params.put("wishing", "恭喜您提现成功!");
		// 终端IP
		params.put("client_ip", ip);
		// 活动名称
		params.put("act_name", "佣金提现");
		// 备注
		params.put("remark", "佣金提现");
		//创建签名
		String sign = PaymentKit.createSign(params, paternerKey);
		params.put("sign", sign);
        try{
		String xmlResult = RedPackApi.sendRedPack(params, certPath, partner);
		Map<String, String> result = PaymentKit.xmlToMap(xmlResult);
		System.out.println(result);
		//业务结果
		String result_code = result.get("result_code");
		//此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
		String return_code = result.get("return_code");
		//
		if (StrKit.isBlank(result_code) || !"SUCCESS".equals(result_code)) {
			System.out.println("发送失败");
			resultJson.put("msg", "抱歉,提现失败."+result);
		} else {
		
			System.out.println("发送成功");
			resultJson.put("msg", "恭喜,提现成功.");
		}
		//由于证书或其它问题会出现异常
        }catch(Exception ex){
        	ex.printStackTrace();
        	resultJson.put("msg", "抱歉,发生异常提现失败,请联系管理员.");
        }
        //账单已提现过去不予提现
		}else{
			resultJson.put("msg", "该账单已经提现过了，不能再提现了！");
			
		}
	    }else{
			resultJson.put("msg", "系统找不到单号所属人,请检查数据或联系管理员.");
	    }
		}
		

		renderJson(resultJson.toString());
	}

	public void query() {
		Map<String, String> params = new HashMap<String, String>();
		// 随机字符串
		params.put("nonce_str", System.currentTimeMillis() / 1000 + "");
		// 商户订单号
		params.put("mch_billno", "20160227083703842100294140");
		// 商户号
		params.put("mch_id", partner);
		// 公众账号ID
		params.put("appid", wxappid);
		params.put("bill_type", "MCHT");
		//创建签名
		String sign = PaymentKit.createSign(params, paternerKey);
		params.put("sign", sign);

		String xmlResult = RedPackApi.getHbInfo(params, certPath, partner);
		Map<String, String> result = PaymentKit.xmlToMap(xmlResult);
		System.out.println(result);
		renderJson(result);
	}

}
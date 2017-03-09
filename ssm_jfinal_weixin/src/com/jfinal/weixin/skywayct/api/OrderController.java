package com.jfinal.weixin.skywayct.api;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.weixin.model.Attribution;
import com.jfinal.weixin.model.Order;
import com.jfinal.weixin.model.OrderWarning;
import com.jfinal.weixin.model.Position;
import com.jfinal.weixin.model.Product;
import com.jfinal.weixin.model.Wallet;
import com.jfinal.weixin.model.WarningRecord;
import com.jfinal.weixin.sdk.api.PaymentApi;
import com.jfinal.weixin.sdk.kit.IpKit;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.jfinal.weixin.sdk.kit.ToolKit;
import com.jfinal.weixin.sdk.utils.JsonUtils;
import com.jfinal.weixin.skywayct.api.common.CommonResponse;
import com.jfinal.weixin.skywayct.api.common.FlowResponse;
import com.jfinal.weixin.skywayct.api.common.OrderRequest;
import com.jfinal.weixin.skywayct.api.common.WeixinConst;
import com.jfinal.weixin.skywayct.api.inteface.AllOrderHandler;
import com.skywayct.util.DateUtil;
import com.skywayct.util.UuidUtil;



/**
 * @author 
 * @version V1.0
 * @Title: OrderController.java
 * @date 
 * @Description:
 */
public class OrderController extends Controller {
	static Log logger = Log.getLog(OrderController.class);
	/**
	 * 下单
	 */
	@Clear
	// @Before({TokenValidator.class ,LogInterceptor.class})
	public void index() {
		String openid = getSessionAttr("open_id");
		if(StrKit.isBlank(openid))
		{
			renderText("{\"result_code\":-1,\"message\":\"请求错误！\"}");
			return;
		}
		
		if(!StrKit.isBlank(getPara("prepay_id"))){
			if( StrKit.notBlank( getPara("order_id") )){
				Order tmpOrd = Order.dao.findById( getPara("order_id") );
				if( tmpOrd == null ){
					renderText("{\"result_code\":-1,\"message\":\"请求错误！\"}");
					return;
				}
				if( tmpOrd.getStr("prepay_id") == null ){
					renderText("{\"result_code\":-1,\"message\":\"请求错误！\"}");
					return;					
				}
				renderText( tmpOrd.getStr("prepay_id") );
			}else{
				renderText("{\"result_code\":-1,\"message\":\"请求错误！\"}");
			}
			//renderText(weixinSign(getPara("prepay_id"),getPara("order_id")));
			return;
		}
		if(StrKit.notBlank(getPara("lat"),getPara("lng"),getPara("bdlat"),getPara("bdlng")) && ToolKit.isDouble(getPara("lat"),getPara("lng"),getPara("bdlat"),getPara("bdlng")))
			Position.addPosition(openid, Double.valueOf(getPara("lat")), Double.valueOf(getPara("lng")), Double.valueOf(getPara("bdlat")), Double.valueOf(getPara("bdlng")));
		
		OrderRequest req = new OrderRequest();
		req.setOpenid(openid);
		req.setPhone(getPara("phone"));
		req.setProduct_id(getPara("product_id"));
		
		req.setEffective_type("2");
		req.setSerial("");//微信订单号
		req.setSettlement_method(getPara("paytype")); //支付方式  1微信支付 2钱包支付
		
	    Map<String, String> paras = new HashMap<String, String>();
	    Product r = Product.dao.findById(req.getProduct_id());
	    //重新支付
		if(!StrKit.isBlank(getPara("orderid"))&&"2".endsWith(req.getSettlement_method())&&getPara("orderid")!=null&&!"null".equals(getPara("orderid"))){
			Order rec = Order.dao.findById(getPara("orderid"));
			r = Product.dao.findById(rec.getStr("product_id"));
			
			// 2 检查余额是否足够member wallet
			Wallet wallet = Wallet.dao.findFirst("select * from weixin_wallet where open_id = '"+rec.getStr("openid")+"'");		
	        if(wallet!=null&&wallet.getDouble("amount")!=null){
	        	//判断密码是否正确
	        	if(wallet.getStr("password")==null){
	        		paras.put("result_code", 6+"");
	            	paras.put("order_id",getPara("orderid"));
	        		paras.put("message", "请到个人中心设置密码!");
	                renderText(JsonUtils.toJson(paras));
	        		return;
	        	}
	        	if(!wallet.getStr("password").equals(getPara("pas"))){
	        		paras.put("result_code", 5+"");
	            	paras.put("order_id",getPara("orderid"));
	            	paras.put("message", "密码错误!");
	                renderText(JsonUtils.toJson(paras));
	        		return;
	        	}
	            if(wallet.getDouble("amount").doubleValue()<r.getDouble("settlement_price")){
	            	paras.put("result_code", 3+"");
	            	paras.put("order_id",getPara("orderid"));
	            	paras.put("message", "钱包余额不足,可以选择用微信支付!");
	                renderText(JsonUtils.toJson(paras));
	                return ;
	            }
	        }else{
	        	paras.put("result_code", 4+"");
            	paras.put("message", "钱包异常!");
                renderText(JsonUtils.toJson(paras));
	            return ;
	        }
			
			rec.set("settlement_method", 2).update();
			FlowResponse flag = AllOrderHandler.doOrder(getPara("orderid"));
			 if(flag.isStatus()){
        		 paras.put("result_code", 1+"");
            	 paras.put("message", "充值成功!");
                 renderText(JsonUtils.toJson(paras));
        		 return;
        	 }
        	 else{
        		 paras.put("result_code", 0+"");
	             paras.put("message", "充值失败!");
	             renderText(JsonUtils.toJson(paras));
        		 return;
        	 }
		}
		//钱包支付余额不足后 用微信支付
		if(!StrKit.isBlank(getPara("orderid"))&&"1".endsWith(req.getSettlement_method())&&getPara("orderid")!=null&&!"null".equals(getPara("orderid"))){
			Order rec = Order.dao.findById(getPara("orderid"));
			
			if(rec==null) return ;
			  //微信下单
	        String wxjson = doOrder(rec);
	        rec.set("settlement_method", 1).update();
	        renderText(wxjson);
	        return ;
		}
		
	
	

		
		CommonResponse res = new CommonResponse();

		if (StrKit.isBlank(req.getPhone())) {
			res.setReturn(50012);
			renderJson(res);
			return;
		}


		if (StrKit.isBlank(req.getProduct_id())) {
			res.setReturn(50010);
			renderJson(res);
			return;
		}

		if (req.checkPhone()) {
			res.setReturn(50015);
			renderJson(res);
			return;
		}

		 // 2 检查余额是否足够 API查询
		
		
		
		if (r==null) {
			res.setReturn(50013);
			renderJson(res);
			return;
		}

		if (req.getPhone().length() < 11) {
			res.setReturn(50015);
			renderJson(res);
			return;
		}
		Wallet wallet = Wallet.dao.findFirst("select * from weixin_wallet where open_id = '"+req.getOpenid()+"'");
	    if("2".endsWith(req.getSettlement_method())){
			//判断密码是否正确
	    	if(wallet.getStr("password")==null){
	    		paras.put("result_code", 6+"");
	    		paras.put("message", "请到个人中心设置密码!");
	            renderText(JsonUtils.toJson(paras));
	    		return;
	    	}
	    	if(!wallet.getStr("password").equals(getPara("pas"))){
	    		paras.put("result_code", 5+"");
	        	paras.put("message", "密码错误!");
	            renderText(JsonUtils.toJson(paras));
	    		return;
	    	}
	    }
    	
        //1  生成充值记录
        Order rec = new Order();
        String order_id = UuidUtil.get32UUID();
        
        String[] info = getAttribution(req.getPhone()) ;
        
        rec.set("order_id", order_id);
        rec.set("openid", req.getOpenid());
        rec.set("phone",req.getPhone() );
        rec.set("effective_type",req.getEffective_type() );
        rec.set("attribution",info[0]);//归属地
        rec.set("product_id",  req.getProduct_id());
        rec.set("amount", r.getDouble("settlement_price"));  //价格
        rec.set("settlement_price", r.getDouble("settlement_price"));//结算价
        rec.set("product_price", r.getDouble("product_price"));    //原价
        rec.set("cost_price", r.getDouble("cost_price"));         //成本价
        rec.set("settlement_method", req.getSettlement_method());//支付方式
        rec.set("profit", r.getDouble("profit"));         //利润
        rec.set("profit_price", r.getDouble("profit_price"));  
        rec.set("status",2 );  //处理中2 成功 1 失败 0 
        rec.set("paystatus",3);  //待付款
        rec.set("dr",0 ); 
        rec.set("recharge_dttm",new Date() );
        rec.set("note",r.getStr("product_name") );
        rec.set("serial",req.getSerial());
        //流量 0 话费1
        rec.set("type",getPara("type")==null?0:1);
        //运营商
        rec.set("chnnl_type",WeixinConst.getCardType(info[1]));
        //流量类型
        if(getPara("type")==null){
	        Record region = Db.findFirst(" select * from weixin_product_type where id='"+r.getStr("pid")+"' ");
	        rec.set("region", region==null?"":region.getInt("region"));
        }
        OrderWarning war = getSessionAttr("warning");
        if(warningCheck(war,rec))
        {
        	rec.clear();
        	paras.put("result_code", "-1");
        	paras.put("message", "无法下单!");
            renderText(JsonUtils.toJson(paras));
        	return;
        }
        else
        	rec.save();
        
    
        //钱包支付 直接充值;
        if("2".endsWith(req.getSettlement_method())){
        	// 2 检查余额是否足够member wallet
		
	        if(wallet!=null&&wallet.getDouble("amount")!=null){
	      
	            if(wallet.getDouble("amount").doubleValue()<r.getDouble("settlement_price")){
	            	paras.put("result_code", 3+"");
	            	paras.put("order_id",order_id);
	            	paras.put("message", "钱包余额不足,可以选择用微信支付!");
	                renderText(JsonUtils.toJson(paras));
	                return ;
	            }
	        }else{
	        	paras.put("result_code", 4+"");
	        	paras.put("message", "钱包异常!");
	        	renderText(JsonUtils.toJson(paras));
	            return ;
	        }
        	FlowResponse flag = AllOrderHandler.doOrder(rec.getStr("order_id"));
        	 if(flag.isStatus()){
        		 paras.put("result_code", 1+"");
            	 paras.put("message", "充值成功!");
                 renderText(JsonUtils.toJson(paras));
        		 return;
        	 }
        	 else{
        		 paras.put("result_code", 0+"");
	             paras.put("message", "充值失败!");
	             renderText(JsonUtils.toJson(paras));
        		 return;
        	 }
        }
        
        //微信下单
        String wxjson = doOrder(rec);
        
        renderText(wxjson);
        return;
	}

	  //获取归属地
    public static String[] getAttribution(String phone){
    	String info[] = new String[2];
		String flag7 = phone.substring(0, 7);
		Attribution a = Attribution.dao.findFirst("select * from mobile_attribution where number_section1 ='"+flag7+"'");
		if(a!=null){
			info[0]= (a.get("attribution")==null?"":a.get("attribution").toString())+"   "+(a.get("card_type")==null?"":a.get("card_type").toString());
			info[1]= (a.get("card_type")==null?"":a.get("card_type").toString());
		}
		 return info;
	}
    
    
  //商户相关资料

//  	private static String notify_url = "http://web.leedate.com/skywayct/recharge";
    public String doOrder(Order order) {
		// 威富通统一下单文档地址：https://pay.swiftpass.cn/pay/gateway
    	Map<String, String> params = new HashMap<String, String>();
    	
    	params.put("service", "pay.weixin.jspay");
		params.put("mch_id", WeixinConst.partner);
		params.put("body", order.getStr("note"));
		params.put("out_trade_no", order.getStr("order_id"));
		params.put("total_fee",DateUtil.getTwoDecimals(order.getDouble("settlement_price").doubleValue()));
		
		String ip = IpKit.getRealIp(getRequest());
		if (StrKit.isBlank(ip)) {
			ip = "127.0.0.1";
		}
		params.put("mch_create_ip", ip);
		params.put("is_raw", "1");
		params.put("nonce_str", System.currentTimeMillis() / 1000 + "");
		params.put("notify_url", WeixinConst.notify_url);  //后台通知地址
		//params.put("callback_url", SnsAccessTokenApi.getAuthorizeURL(PropKit.get("appId"), PropKit.get("host") +"/weixin/ordrecord.html",true));  //前端返回页面地址
		params.put("sub_openid", order.getStr("openid"));  //正式账号必须要传、测试账号不传
		String sign = PaymentKit.createSign(params, WeixinConst.paternerKey);
		params.put("sign", sign);
		
		String xmlResult = PaymentApi.wpushOrder(params);
		
		System.out.println(xmlResult);
		Map<String, String> result = PaymentKit.xmlToMap(xmlResult);
		
		String status = result.get("status");
		String return_msg = result.get("message");
		if(StrKit.isBlank(status) || !"0".equals(status)){
			logger.error("{\"result_code\":\""+status+"\",\"message\":\""+return_msg+"\"}");
			return "{\"result_code\":-1,\"message\":\"处理错误！\"}";
		}
		
		String result_code = result.get("result_code");
		if (StrKit.isBlank(result_code) || !"0".equals(result_code)) {
			logger.error("{\"result_code\":\""+result_code+"\",\"message\":\""+result.get("err_msg")+"\"}");
			return "{\"result_code\":-1,\"message\":\"处理错误！\"}";
		}
		
		// 以下字段在 status 和 result_code 都为 0 的时候有返回
		JSONObject payinfo = JSONObject.fromObject(result.get("pay_info"));
		
		//String prepayStr = payinfo.getString("package");
		//prepayStr.replaceAll("prepay_id=", "")
		//the save way is error.
		order.set("prepay_id", payinfo.toString()).update();
		
		return payinfo.toString();
	}
    
    public static String weixinSign(String prepay_id,String order_id){
    	//使用repay_id再次加密
		Map<String, String> packageParams = new HashMap<String, String>();
		packageParams.put("appId", PropKit.get("appId"));
		packageParams.put("timeStamp", System.currentTimeMillis() / 1000 + "");
		packageParams.put("nonceStr", System.currentTimeMillis() + "");
		packageParams.put("package", "prepay_id="+prepay_id);
		packageParams.put("signType", "MD5");
		String packageSign = PaymentKit.createSign(packageParams, WeixinConst.paternerKey);
		packageParams.put("paySign", packageSign);
		packageParams.put("orderid", order_id);
		String jsonStr = JsonUtils.toJson(packageParams);
		
    	return jsonStr;
    }
    
    public static boolean warningCheck(OrderWarning war,Order rec){
    	//写入警告消息
    	boolean flag = false;
    	Double oh = war.getDouble("order_hour");
    	Double mh = war.getDouble("money_hour");
    	Double mm = war.getDouble("max_consume_money");
    	int onum = war.getInt("max_order_num");
    	int mnup = war.getInt("max_unpay_num");
    	Order ord = null;
    	String sql ="",content ="";
    	WarningRecord warnrec = WarningRecord.dao.findFirst("select * from weixin_warning_record where warning_tagid=? and openid =? and ts > date_add(now(), interval "+(-oh*60)+" minute)",war.getStr("warning_tagid"),rec.getStr("openid"));
    	if(warnrec!=null){
    		if(warnrec.getStr("content").indexOf("未付款") >0) flag =true;
    		return flag;
    	}
    	warnrec = new WarningRecord();
    	if(oh > 0){
    		sql = "select if(sum(1) is null,0,sum(1)) as num ,if(sum(paystatus =3) is null,0,sum(paystatus =3)) unpay from weixin_order where openid =? and recharge_dttm > date_add(?, interval "+(-oh*60)+" minute)";
    		ord = Order.dao.findFirst(sql,rec.getStr("openid"),rec.getDate("recharge_dttm"));
    		if(ord.getBigDecimal("num").compareTo(new BigDecimal(onum)) >=0 ||  ord.getBigDecimal("unpay").compareTo(new BigDecimal(mnup))>=0 ) 
    		{	
    			flag = true;
    			warnrec.set("warning_tagid", war.getStr("warning_tagid"));
    			warnrec.set("openid", rec.getStr("openid"));
    			content = war.getStr("nick_name")+oh+"小时内下了["+ord.getBigDecimal("num")+"]单,有["+ord.getBigDecimal("unpay")+"]单未付款！";
    		}
    	}
    	if(mh > 0){
    		sql = "select if(sum(settlement_price) is null,0,sum(settlement_price)) as mcm from weixin_order where openid = ? and recharge_dttm > date_add(?,interval "+(-mh*60)+" minute)";
    		ord = Order.dao.findFirst(sql,rec.getStr("openid"),rec.getDate("recharge_dttm"));
    		if(new BigDecimal(ord.getDouble("mcm")).compareTo(new BigDecimal(mm)) >= 0){
    			if(content.equals("")){
    				warnrec.set("warning_tagid", war.getStr("warning_tagid"));
        			warnrec.set("openid", rec.getStr("openid"));
        			content = war.getStr("nick_name")+oh+"小时内消费了["+ord.getDouble("mcm")+"]";
    			}else
    				content = mh+"小时内消费了["+ord.getDouble("mcm")+"]";
    		}
    	}
    	if(!StrKit.isBlank(content))
    	{
    		warnrec.set("content", content);
    		warnrec.save();
    	}
    	return flag;
    }
}

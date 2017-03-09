package com.jfinal.weixin.info;

import net.sf.json.JSONObject;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.weixin.model.Order;

public class OrderRecordInfoController extends Controller{
	static Log logger = Log.getLog(OrderRecordInfoController.class);
	
	public void index() throws Exception {
		JSONObject json = new JSONObject();
		String open_id = getSessionAttr("open_id");
		if(StrKit.isBlank(open_id)){
			json.put("errcode", "-1");
			json.put("errmsg", "页面请求超时，请重新打开！");
		}else{
			json.put("errcode", "0");
			int pageNumber = getParaToInt("page") ==null ? 1:getParaToInt("page");
			int pageSize = getParaToInt("size") ==null ? 10:getParaToInt("size");
			StringBuilder builder = new StringBuilder(" from weixin_order where 1=1 and openid ='"+ open_id +"' order by recharge_dttm desc");
			Page<Order> page = Order.dao.paginate(pageNumber, pageSize, "select order_id,transaction_id,recharge_dttm,note,settlement_price,settlement_method,phone,attribution,paystatus,status,prepay_id ", builder.toString());
			json.put("records", JsonKit.toJson(page));
		}
		renderJson(json.toString());
	}
}

package com.jfinal.weixin.info;

import net.sf.json.JSONObject;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.weixin.model.Cash;
import com.jfinal.weixin.model.Wallet;
import com.skywayct.util.UuidUtil;

public class CashController extends Controller{
	public void index() throws Exception{
		JSONObject cash_json = new JSONObject();
		String open_id = getSessionAttr("open_id");
		if (StrKit.isBlank(open_id)) {
			cash_json.put("code", "-1");
			cash_json.put("msg", "页面请求超时，请重新打开！");
		}else{
			if("query".equals(getPara("do"))){
				cash_json.put("code", "0");
				int pageNumber = getParaToInt("page") ==null ? 1:getParaToInt("page");
				int pageSize = getParaToInt("size") ==null ? 10:getParaToInt("size");
				StringBuilder builder = new StringBuilder(" from weixin_cash  where 1=1 and dr= 0 and open_id = '"+ open_id+"'");
				Page<Cash> page = Cash.dao.paginate(pageNumber, pageSize, "select * ", builder.toString());
				cash_json.put("cashrecords", JsonKit.toJson(page));
			}else if("add".equals(getPara("do"))){
				
				Wallet wallet = Wallet.dao.findFirst("select * from weixin_wallet where open_id ='"+ open_id +"'");
				if(Double.valueOf(getPara("amount"))>0&&(wallet.getDouble("amount")-Double.valueOf(getPara("amount")) >= 0))
				{	
					Cash cash = new Cash();
					cash.set("open_id", open_id);
					cash.set("amount", getPara("amount"));
					cash.set("account", getPara("account"));
					cash.set("bank_name", new String(getPara("bank_name").getBytes("ISO-8859-1"),"UTF-8")); 
					cash.set("name", new String(getPara("name").getBytes("ISO-8859-1"),"UTF-8"));
					cash.set("bank_addr", new String(getPara("bank_addr").getBytes("ISO-8859-1"),"UTF-8"));
					cash.set("cash_id", UuidUtil.get32UUID());
					cash.set("cash_method", getPara("cash_method"));
					cash.set("cash_time", new java.sql.Timestamp(new java.util.Date().getTime()));
					cash.set("ts", new java.sql.Timestamp(new java.util.Date().getTime()));
					cash.set("status", 1);
					cash.set("dr", 0);
					cash.save();
					wallet.set("amount", wallet.getDouble("amount")-Double.valueOf(getPara("amount")));
					wallet.update();
					cash_json.put("code", "0");
					cash_json.put("msg", "success");
				}
				else{
					cash_json.put("code", "-1");
					cash_json.put("msg", "钱包余额不足！");
				}
			}
			
		}
		renderJson(cash_json.toString());
	}
}

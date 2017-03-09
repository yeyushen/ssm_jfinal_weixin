package com.jfinal.weixin.info;

import net.sf.json.JSONObject;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.weixin.model.Commission;

public class CommissionController extends Controller{
	
	public void index() throws Exception{
		JSONObject commis_json = new JSONObject();
		String open_id = getSessionAttr("open_id");
		if (StrKit.isBlank(open_id)) {
			commis_json.put("code", "-1");
			commis_json.put("msg", "页面请求超时，请重新打开！");
		}else{
			commis_json.put("code", "0");
			int pageNumber = getParaToInt("page") ==null ? 1:getParaToInt("page");
			int pageSize = getParaToInt("size") ==null ? 10:getParaToInt("size");
			StringBuilder builder = new StringBuilder(" FROM weixin_commission a LEFT JOIN weixin_order c ON a.order_id = c.order_id LEFT JOIN weixin_member d ON c.openid = d.openid WHERE 1 = 1 and a.open_id='"+ open_id +"'  order by a.create_date desc");
			Page<Commission> page = Commission.dao.paginate(pageNumber, pageSize, "select a.*,c.settlement_price,d.nick_name ", builder.toString());
			commis_json.put("commission", JsonKit.toJson(page));
		}
		renderJson(commis_json.toString());
	}
}

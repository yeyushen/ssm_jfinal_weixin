package com.jfinal.weixin.skywayct;

import net.sf.json.JSONObject;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.weixin.model.Order;
import com.jfinal.weixin.model.PayAgainRecord;
import com.jfinal.weixin.skywayct.api.common.FlowResponse;
import com.jfinal.weixin.skywayct.api.inteface.AllOrderHandler;
import com.jfinal.weixin.skywayct.api.inteface.my.MyFlowServerHandler;
import com.skywayct.util.Jurisdiction;

/**
 * 二次充值
 * @author Administrator
 *
 */
public class WeixinPayAgainController extends Controller{
	static Log log = Log.getLog(MyFlowServerHandler.class);
	
	public void index() throws Exception{
		
		String order_id = getPara("DATA_IDS");
		String Order_Id[] = order_id.split(",");//要充值的订单列表
		
		
		String failPhone = "";
		String successPhone = "";
		Order order = null;
		String phone = null;
		PayAgainRecord payAgain = null;
		
		for(int i=0;i<Order_Id.length;i++){
			order = Order.dao.findById(Order_Id[i]);
			order.set("status", 2).update();//由失败设置为处理中
			FlowResponse flag = AllOrderHandler.doOrder(Order_Id[i]);
			phone=order.getStr("phone");
			
			//增加二次充值记录
			payAgain = new PayAgainRecord();
			payAgain.set("order_id", Order_Id[i]);
			payAgain.set("create_name", Jurisdiction.getUsername());
			payAgain.set("pay_log", flag.getMsg() );
			if(flag.isStatus()){ 
				successPhone += phone +  ",";
				payAgain.set("status", 1);
			}
			else{
				 failPhone += phone +  ",";
				 payAgain.set("status", 0);
			}
			payAgain.save();
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("successNO", successPhone);
		jsonObject.put("failNo", failPhone);
		renderJson(jsonObject.toString());
		return ;
		
	}

}

package com.jfinal.weixin.skywayct.api.inteface;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.weixin.model.AccountLock;
import com.jfinal.weixin.model.Attribution;
import com.jfinal.weixin.model.Order;
import com.jfinal.weixin.model.Wallet;
import com.jfinal.weixin.skywayct.api.common.FlowResponse;
import com.jfinal.weixin.skywayct.api.common.OrderResponse;
import com.skywayct.util.UuidUtil;

import java.util.Date;
import java.util.List;

/**
 * @author 
 * @version V1.0
 * @Title: AllOrderHandler.java
 * @date 
 * @Description:
 */
public class AllOrderHandler{

	/**
     * 
     * @param Order 
     * @return
     */
    public static FlowResponse doOrder(String order_id){
    	Order order = Order.dao.findById(order_id);
	
    	if(order.getInt("settlement_method")==2 && order.getInt("type")!=2){
	        // 添加冻结金额
	        if(order.get("order_id")!=null){
	            AccountLock accountlock = new AccountLock();
	            accountlock.set("order_id",order.get("order_id") );
	            accountlock.set("amount",order.getDouble("amount") );
	            accountlock.save();
	        }
	        // 扣款
	        Db.update("update weixin_wallet set amount =amount -'" + order.getDouble("amount") + "' where open_id ='" + order.getStr("openid") + "'");
	        order.set("paystatus", 1).update();
	      
    	}
    	  //话费充值直接返回成功,type 0 流量，1话费，2流量压单后台充值
        if(order.getInt("type")!=null&&order.getInt("type")==1){
        	 FlowResponse res = new FlowResponse();
        	 res.setOrder(order);
        	 res.setDetail("提交成功");
         	 res.setReturn(20000);
         	 res.setStatus(true);
         	 OrderHandler.reNewOrder(order, true);
         	 return res;
        }
        return OrderHandler.exeOrder(order);
    }
}

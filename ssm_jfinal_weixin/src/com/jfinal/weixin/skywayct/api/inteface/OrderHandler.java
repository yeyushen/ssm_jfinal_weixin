package com.jfinal.weixin.skywayct.api.inteface;

import com.jfinal.aop.Before;
import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.weixin.model.Commission;
import com.jfinal.weixin.model.CommissionConfig;
import com.jfinal.weixin.model.Member;
import com.jfinal.weixin.model.Product;
import com.jfinal.weixin.model.Order;
import com.jfinal.weixin.model.Wallet;
import com.jfinal.weixin.skywayct.api.common.FlowResponse;
import com.jfinal.weixin.skywayct.api.common.FlowServerHandler;
import com.skywayct.controller.weixin.commission.CommissionController;
import com.skywayct.util.UuidUtil;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 
 * @version V1.0
 * @Title: OrderHandler.java
 * @date 
 * @Description:
 */
public class OrderHandler {

    public static Logger log = Logger.getLogger(OrderHandler.class);

    public static Map<Integer,Record> allRequestProduct;

    public static Map<Integer,Record> getAllRequestProduct(){
        if(allRequestProduct==null){
            allRequestProduct  = new HashMap<Integer, Record>();
        }
        return allRequestProduct;
    }

    public static void addProduct(Integer orderId,Record r){
        getAllRequestProduct().put(orderId,r);
    }

    public static void removeProduct(Integer orderId){
        getAllRequestProduct().remove(orderId);
    }

    public static Record getProduct(Integer orderId){
        return getAllRequestProduct().get(orderId);
    }


    /**
     * 处理订单
     * @param order
     */
    public static FlowResponse exeOrder(Order order){

        //获取订单信息
      //  Order order = Order.dao.findById(order_id);

        //验证订单状态
    	if(order.getInt("settlement_method")==2){
    		FlowResponse res = checkOrder(order);
	        res.setOrder(order);
	        if(res.getCode()!=null){
	            log.info(order.getStr("phone") + " 充值订单状态不正确！");
	
	            try{
	                OrderHandler.reNewOrder(order, false);
	            }catch (Exception ec){
	                ec.printStackTrace();
	                log.info(order.getStr("phone") + " 运营商下单失败，更新订单状态失败！");
	                res.setReturn(50019);
	            }
	            res.setStatus(false);
	            res.setDetail("订单异常!");
	            return res;
	        }
    	}

        //判断当前接口是否有效
        //2016.3.14 修改成判断代理商对应的运营商，所对应的接口是否正常
//        Integer route_type = null;
//        Integer chnnl_type = order.getInt("chnnl_type");
//        if(chnnl_type==1){
//            route_type = a.getInt("route_type");
//        }else if(chnnl_type==2){
//            route_type = a.getInt("route_type_unicom");
//        }else if(chnnl_type==3){
//            route_type = a.getInt("route_type_telecom");
//        }
//        if(route_type>0){
//            String sqlStr = "select * from i_interface where id = "+route_type+" ";
//            Record r_interface = Db.findFirst(sqlStr);
//            if(r_interface.getInt("interface_status")==2){
//                log.info(order.getStr("phone") + " 账户异常，请联系代理商！");
//                res.setMsg("接口异常，请联系代理商。");
//                res.setDetail("接口异常，请联系代理商。");
//                res.setCode(50022);
//                try{
//                    CmccGdOrderHandler.reNewOrder(order, false);
//                }catch (Exception ec){
//                    ec.printStackTrace();
//                    log.info(order.getStr("phone") + " 接口异常，更新订单状态失败！");
//                    res.setReturn(50019);
//                }
//                if(route_type!=null){
//                    order.set("interface_id",route_type);
//                    order.update();
//                }
//                return res;
//            }
//        }

        
  

        //从数据库里面获取产品的接口信息并封装
//        Record r =  CmccGdDao.getApiProduct(order.getInt("product_id"),route_type,chnnl_type);
        Record r = Db.findFirst("select * from weixin_product where product_id='"+order.getStr("product_id")+"'");
        OrderHandler.addProduct(order.getInt("id"),r);


        //获取接口处理程序
        FlowServerHandler handler = null;
//        if(r.getInt("interface_id")>=15 && r.getInt("interface_id")<=24){
//            handler = AllInterface.get(15).create();
//        }else{
//            handler =AllInterface.get(r.getInt("interface_id")).create();
//        }
        handler =AllInterface.get(1).create();
        //记录订单处理通道
        //一期固定MY接口
        order.set("interface_id",1);
        order.update();

        //订单处理程序开始处理
        handler.setOrder(order);
        handler.setProduct(r);
        FlowResponse allRes = handler.doFlowRecharge();
        OrderHandler.removeProduct(order.getInt("id"));
        return allRes;
    }

    /**
     * 校验订单状态是否正确
     * @param order
     * @return
     */
    public static FlowResponse checkOrder(Order order){
        FlowResponse res = new FlowResponse();
        String sqlStr = "select o.order_id from weixin_order as o,weixin_acount_lock as l \n" +
                "where o.status = 2 and o.order_id = l.order_id and o.amount = l.amount and o.order_id='"
                +order.getStr("order_id")+"'";
        List<Record> lst = Db.find(sqlStr);
        if(lst.size()!=1){
            res.setReturn(40001);
        }
        return res;
    }

    /**
     * 为更新订单状态方法添加特效
     * @param order
     * @param status
     */
    public static void reNewOrder(Order order,Boolean status){
        OrderHandler handler = Enhancer.enhance(OrderHandler.class, Tx.class);
        handler. reNewOrderOrim(order,status);
    }

    /**
     * 更新订单状态
     * @param order
     * @param status 成功true 失败false
     */
    @Before(Tx.class)
    protected static void reNewOrderOrim(Order order,Boolean status){
    	if(order.getInt("status")==1||order.getInt("status")==0) return;
        if(status){
            order.set("status", 1);//状态1成功
            order.set("log", "充值成功");
        }else{
            order.set("status",0);//状态0处理失败
        	if(order.getInt("settlement_method")==2){ //钱包支付
        		Db.update("update  weixin_wallet set amount= amount+ifnull((select amount from weixin_acount_lock where order_id = '"
                            +order.getStr("order_id")+"'),0) where open_id = '"+ order.get("openid")+"'");
        		order.set("paystatus", 2).update();
        	}
        }
        if(order.getInt("settlement_method")==2){ //钱包支付
    		Db.update("insert into weixin_acount_unlock select * from  weixin_acount_lock where order_id='"+order.getStr("order_id")+"'");
	        Db.update("delete from  weixin_acount_lock where order_id='"+order.getStr("order_id")+"'");
	        //插入流水账
	        if(status){
	        	Wallet w = Wallet.dao.findFirst("select * from weixin_wallet where open_id ='"+order.get("openid")+"'");
	        	Commission c = new Commission();
	     		c.set("commission_id", UuidUtil.get32UUID());
	     		c.set("open_id",order.get("openid"));
	     		c.set("commission", -order.getDouble("settlement_price"));
	     		c.set("order_id", order.getStr("order_id"));
	     		c.set("create_date", System.currentTimeMillis());
	     		c.set("dr", "0");
	     		c.set("lv", 4);
	     		c.set("balance", w.getDouble("amount"));
	     		c.save();
	        	
	        }
        
        }
        order.update();
       
        //返点     充值人没有返点,对上三级进行返点
        if(status){
        	Member m = Member.dao.findFirst("select * from weixin_member where openid='"+order.getStr("openid")+"'");//充值人
        	CommissionConfig cconfig = CommissionConfig.dao.findFirst("select * from weixin_commission_discount ");//佣金比例
        	BigDecimal discount =null;  //返佣比例
//        	BigDecimal profit = new BigDecimal(order.getDouble("settlement_price"))
//        					.multiply(new BigDecimal(order.getDouble("profit")));//利润 = 结算价*利润比例
        	
        	BigDecimal profit = new BigDecimal(order.getDouble("profit_price")==null?0.0:order.getDouble("profit_price"));//利润 
        	BigDecimal m_profit = null; //结算利润

        	//一级返点
        	if(m!=null&&m.getStr("parent_id")!=null&&!m.getStr("parent_id").equals("")){
        		m= Member.dao.findFirst("select * from weixin_member where openid='"+m.getStr("parent_id")+"'  ");//充值人
        		if(m==null) return;
        		discount = new BigDecimal(cconfig.getDouble("discount_1")==null?0.0:cconfig.getDouble("discount_1"));
        		m_profit = profit.multiply(discount);  //计算所得利润
        		if(m.getInt("dr")!=null&&m.getInt("dr")==0&&m_profit.doubleValue()>=0.0)
        			commission(m.getStr("openid"),m_profit,order.getStr("order_id"),1);
        		
        	}
        	
        	
        	//二级返点
        	if(m!=null&&m.getStr("parent_id")!=null&&!m.getStr("parent_id").equals("")){
        		m= Member.dao.findFirst("select * from weixin_member where openid='"+m.getStr("parent_id")+"' ");
        		 if(m==null) return;
        		 discount = new BigDecimal(cconfig.getDouble("discount_2")==null?0.0:cconfig.getDouble("discount_2"));
        		 m_profit = profit.multiply(discount);
        		 if(m.getInt("dr")!=null&&m.getInt("dr")==0)
        			 commission(m.getStr("openid"),m_profit,order.getStr("order_id"),2);
        	}
        	
        	
        	//三级返点
        	if(m!=null&&m.getStr("parent_id")!=null&&!m.getStr("parent_id").equals("")){
        		m= Member.dao.findFirst("select * from weixin_member where openid='"+m.getStr("parent_id")+"'  ");
        		 if(m==null) return;
        		 discount = new BigDecimal(cconfig.getDouble("discount_3")==null?0.0:cconfig.getDouble("discount_3"));
        		 m_profit = profit.multiply(discount);
        		 if(m.getInt("dr")!=null&&m.getInt("dr")==0)
        			 commission(m.getStr("openid"),m_profit,order.getStr("order_id"),3);
        	}
        }
    }
    /**
     * 
     * @param openid
     * @param m_profit
     * @param order_id
     */
    private static void commission(String openid,BigDecimal m_profit,String order_id,int lv){
    	if(m_profit.doubleValue()<=0) return;
    	Wallet w = Wallet.dao.findFirst("select * from weixin_wallet where open_id ='"+openid+"'");
    	Commission c = new Commission();
 		c.set("commission_id", UuidUtil.get32UUID());
 		c.set("open_id",openid);
 		c.set("commission", m_profit);
 		c.set("order_id", order_id);
 		c.set("create_date", System.currentTimeMillis());
 		c.set("dr", "0");
 		c.set("lv", lv);
 		c.set("balance", new BigDecimal(w.getDouble("amount")).add(m_profit));
 		c.save();
 		//订单记录返佣
 		Db.update(" update weixin_order set profit_price"+lv+" ="+m_profit +" where order_id='"+order_id+"'");
 		//钱包增加佣金
 		Db.update("update  weixin_wallet set amount= amount+"+m_profit+" where open_id='"+openid+"'");
 		
    }

}

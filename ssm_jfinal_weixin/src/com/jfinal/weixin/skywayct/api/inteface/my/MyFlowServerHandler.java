package com.jfinal.weixin.skywayct.api.inteface.my;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import net.sf.json.JSONObject;

import com.jfinal.kit.HttpKit;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.weixin.model.Order;
import com.jfinal.weixin.model.ReqRes;
import com.jfinal.weixin.skywayct.api.common.FlowResponse;
import com.jfinal.weixin.skywayct.api.common.FlowServerHandler;
import com.jfinal.weixin.skywayct.api.inteface.OrderHandler;
import com.skywayct.util.MD5;

/**
 * @author XZK
 * @version V1.0
 * @Title: MyFlowServerHandler.java
 * @date 
 * @Description:
 */
public class MyFlowServerHandler extends FlowServerHandler {
	static Log log = Log.getLog(MyFlowServerHandler.class);
	static AtomicInteger atom = new AtomicInteger(100);
    public MyFlowServerHandler create(){
        return new MyFlowServerHandler();
    }

    /**
     * 根据请求进行流量充值
     * @return
     */
    public FlowResponse doFlowRecharge() {
    	FlowResponse res = new FlowResponse();
        Order order = this.order;
        
        //检查是否压单并且不是后台进行压单充值，type 0流量，1话费，2流量压单后台充值
    	if(order.getInt("type")!=2 && product.getInt("ispress")==1){
    		order.set("type", 2);
    		order.update();
    		res.setDetail("系统压单");
        	res.setReturn(20000);
        	res.setStatus(true);
    		return res;
    	}
    	String appid = PropKit.get("apid");
    	String appsecret = PropKit.get("key");
    	String urlStr = PropKit.get("url");
    	if (atom.get()>=1000) {
			atom.set(100);
		}
    	String serial = "fsmp"+System.currentTimeMillis()+atom.getAndAdd(1);
    	String phone = order.getStr("phone");
    	String product_code = product.getStr("product_code");//order.getStr("product_id");
    	Record r = Db.findFirst("select p.product_id,p.product_name,p.settlement_price,t.chnnl_type,t.region from weixin_product p \n" +
    			"left join weixin_product_type t on p.pid = t.id where p.product_id='"+product.getStr("product_id")+"'");
    	
    	SortedMap<String, String> map = new TreeMap<String, String>();
    	if(r!=null&&(r.getInt("region")==3||r.getInt("region")==2))
    		map.put("roam", "1");
        else
        	map.put("roam", "0");
    	map.put("appid", appid);
    	map.put("serial", serial);
    	map.put("phone", phone);
    	map.put("product_code", product_code);
    	map.put("style", product.getInt("style") + "");
    	String temp = "";
    	for (Map.Entry entry : map.entrySet()) {
    		temp=(temp+entry.getKey().toString()+"="+entry.getValue().toString()+"&");
        }
    	temp=temp+("key="+appsecret);
    	String sign = MD5.md5(temp);
    	map.put("sign", sign);
    	

    	ReqRes ret =  new ReqRes();
        ret.set("request", JsonKit.toJson(map));
        ret.set("order_id", order.getStr("order_id"));
        ret.save();
        String json = "";
        try{
        	json = HttpKit.post(urlStr,map,"");
         	ret.set("response", json);
        	ret.update();
        	log.info(order.getStr("phone") + " 请求返回日志!"+json);
        }catch (Exception e){
        	  log.info(order.getStr("phone") + " 接口发送请求异常!");
            e.printStackTrace();
            res.setReturn(40000);
            log.info(order.getStr("phone") +" 报错内容："+e.getMessage());
            log.info(order.getStr("phone") +" 返回内容："+JsonKit.toJson(res));
            try{
                OrderHandler.reNewOrder(order, false);
            }catch (Exception ec){
                ec.printStackTrace();
                log.info(order.getStr("phone") + " 下单失败，本地更新订单状态失败！3");
                res.setReturn(50019);
            }
            res.setStatus(false);
            return res;
        }
        //分析返回json  
       JSONObject resultOut = JSONObject.fromObject(json);
       
       String status = resultOut.get("code")==null?"":resultOut.getString("code");
       String message = resultOut.get("msg")==null?"":resultOut.getString("msg");
       String desc = resultOut.get("desc")==null?"":resultOut.getString("desc");
       //更新订单返回请求
       order.set("log", status+message);
       order.set("taskid", serial);
//       order.set("flag", "");
       order.update();
       //如果请求不成功
       if(!status.equals("10000")){
    	   try{
    		   res.setDetail(status+message);
    		   res.setReturn(40000);
           	   res.setStatus(false);
               OrderHandler.reNewOrder(order, false);
           }catch (Exception ec){
               ec.printStackTrace();
               log.info(order.getStr("phone") + " 下单失败，本地更新订单状态失败！4");
               res.setReturn(new Integer(status));
           }
    	 
    	   return res;
       }

   		res.setDetail(status+message);
    	res.setReturn(20000);
    	res.setStatus(true);

        log.info(order.getStr("phone") + " 订单处理完成----------");
        log.info(order.getStr("phone") +" "+JsonKit.toJson(res));
        
        return res;    	
    	
    	
/*    	
        //String orderid = order.getStr("order_id")+"" ;
        Record r = Db.findFirst("select p.product_id,p.product_name,p.settlement_price,t.chnnl_type,t.region from weixin_product p \n" +
        			"left join weixin_product_type t on p.pid = t.id where p.product_id='"+product.getStr("product_id")+"'");

        res.setOrder(order);
        
        //组织接口所需要参数
        Map<String, String> Paras = new HashMap<String, String>();
        String url = PropKit.get("url");
        Paras.put("action", "charge");
        Paras.put("v", "1.1");
        if(r!=null&&(r.getInt("region")==3||r.getInt("region")==2))
        	Paras.put("range", "0");
        else
        	Paras.put("range", "1");
        Paras.put("account","流量地主");
//        Paras.put("account", PropKit.get("account"));
        Paras.put("mobile", order.getStr("phone"));
        Paras.put("package", product.getStr("product_code"));
       
//        String array[] = {Paras.get("account"),Paras.get("mobile"),Paras.get("package")};
//		Arrays.sort(array);
		String tempStr = new StringBuilder().append("account=流量地主&mobile="+Paras.get("mobile")+"&package="+product.getStr("product_code")+"").toString()+"&key="+""+ PropKit.get("key")+"";
//		String tempStr = new StringBuilder().append(array[0] +"&"+ array[1] +"&"+ array[2]).toString()+"&key="+"84f17e25a56d4eb397d26524d23bccc1";
		tempStr = tempStr.trim();
		Paras.put("sign",HashKit.md5(tempStr));
        
        // 保存发送及返回日志 
        ReqRes ret =  new ReqRes();
        ret.set("request", JsonKit.toJson(Paras));
        ret.set("order_id", order.getStr("order_id"));
        ret.save();
        String json = "";
        try{
        	json = HttpKit.post(url, Paras,"");
//        	order.set("flag", "post");
//        	order.update();
         	ret.set("response", json);
        	ret.update();
        	log.info(order.getStr("phone") + " 请求返回日志!"+json);
        }catch (Exception e){
        	  log.info(order.getStr("phone") + " 接口发送请求异常!");
            e.printStackTrace();
            res.setReturn(40000);
            log.info(order.getStr("phone") +" 报错内容："+e.getMessage());
            log.info(order.getStr("phone") +" 返回内容："+JsonKit.toJson(res));
            try{
                OrderHandler.reNewOrder(order, false);
            }catch (Exception ec){
                ec.printStackTrace();
                log.info(order.getStr("phone") + " 下单失败，本地更新订单状态失败！3");
                res.setReturn(50019);
            }
            res.setStatus(false);
            return res;
        }
        //分析返回json  
       JSONObject resultOut = JSONObject.fromObject(json);
       
       String status = resultOut.get("Code")==null?"":resultOut.getString("Code");
       String message = resultOut.get("Message")==null?"":resultOut.getString("Message");
       String TaskID = resultOut.get("TaskID")==null?"":resultOut.getString("TaskID");
       //更新订单返回请求
       order.set("log", message);
       order.set("taskid", TaskID);
//       order.set("flag", "");
       order.update();
       //如果请求不成功
       if(!status.equals("0")){
    	   try{
    		   res.setDetail(message);
    		   res.setReturn(40000);
           	   res.setStatus(false);
               OrderHandler.reNewOrder(order, false);
//               OrderHandler.reNewOrder(order, false);
           }catch (Exception ec){
               ec.printStackTrace();
               log.info(order.getStr("phone") + " 下单失败，本地更新订单状态失败！4");
               res.setReturn(new Integer(status));
           }
    	 
    	   return res;
       }
        //请求成功后
//        try{
       		res.setDetail(message);
        	res.setReturn(20000);
        	res.setStatus(true);
//            OrderHandler.reNewOrder(order, true);
//        }catch (Exception ec){
//            ec.printStackTrace();
//            log.info(order.getStr("phone") + " 下单成功，但是本地更新订单状态失败！5");
//            res.setReturn(new Integer(status));
//            return res;
//        }
//    
        
        
        log.info(order.getStr("phone") + " 订单处理完成----------");
        log.info(order.getStr("phone") +" "+JsonKit.toJson(res));
      
        
        return res;
        */
    }


 
}

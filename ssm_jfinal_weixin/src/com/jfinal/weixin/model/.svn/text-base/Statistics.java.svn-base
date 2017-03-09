package com.jfinal.weixin.model;

import com.jfinal.plugin.activerecord.Model;
import com.skywayct.util.DateUtil;

import java.util.List;

/**
 * @author 
 * @version V1.0
 * @Title: Attribution.java
 * @date 
 * @Description:
 */
public class Statistics extends Model<Statistics> {
	private static final long serialVersionUID = 1L;
	public static final Statistics dao = new Statistics();
	
	
	/**
     * 
     * @param openid
     * @param order_id
	 * @throws Exception 
     */
    public static void addStatistics(String openid,String parent_id,int num) throws Exception{
    	if(num>0){
    		Statistics s = Statistics.dao.findFirst(" select * from weixin_member_statistics where openid='"+openid+"'");
    		if(s==null){
    			s = new Statistics();
		 		s.set("openid",openid); 
		 		s.set("parent_id",parent_id==null?"0":parent_id);
		 		s.set("create_date",DateUtil.fomatDateTiem(System.currentTimeMillis()));
		 		s.set("lv1", 0);
		 		s.set("lv2", 0);
		 		s.set("lv3", 0);
		 		s.save();
    		}
    	}
 		Statistics ns = null;
 		for(int i = 1 ; i < 4 ; i++){
 			ns = Statistics.dao.findFirst(" select * from weixin_member_statistics where openid='"+parent_id+"'");
 			if(ns==null) break;
 			ns.set("lv"+i, ns.getInt("lv"+i)+num).update();
 			parent_id = ns.getStr("parent_id");
 		}
 		
    }

}
package com.jfinal.weixin.model;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.weixin.skywayct.api.OrderController;

public class Position extends Model<Position> {
    private static final long serialVersionUID = 1L;
    public static final Position dao = new Position();
    private static Log logger = Log.getLog(Position.class);
    
    public static void addPosition(String openid,Double latitude,Double longitude,Double bdlat,Double bdlng){
    	try {
    		logger.info("addPosition--start");
    		Position p = Position.dao.findFirst("select * from weixin_position where (to_days(now()) - to_days(ts)) =0 and openid=?",openid);
    		if(p!=null) return;
    		p = new Position();
        	p.set("openid", openid);
        	p.set("latitude", latitude);
        	p.set("longitude", longitude);
        	p.set("bdlat", bdlat);
        	p.set("bdlng", bdlng);
			p.save();
			logger.info("addPosition--end");
		} catch (Exception e) {
			e.printStackTrace();
		}
    		
    }
}

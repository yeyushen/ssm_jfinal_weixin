package com.jfinal.weixin.skywayct;



import java.sql.Timestamp;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.weixin.model.MyPoints;


/***
 * 会员分数
 * @author hmf
 *
 */
public class WeixinMyPointController extends Controller{
	
	
	/**
	 * 积分+5入口
	 * @throws Exception
	 */
	public void index() throws Exception{
		
		getRequest();
		String openId = getSessionAttr("open_id");
		if(StrKit.isBlank(openId))
		{
			renderText("还未关注或页面打开超时!");
			return;
		}
		boolean isSuccess = false;
		
		MyPoints myPoints = MyPoints.dao.findFirst("SELECT * FROM weixin_mypoints WHERE open_id='"+ openId +"' ");
		if( myPoints != null ){
			long  hasSign = Db.queryLong("SELECT if(to_days(now()) - to_days(c.ts) = 0,1,0) as sign FROM weixin_mypoints c WHERE open_id='"+ openId +"'");
			if( hasSign == 0){
			myPoints.set("points", myPoints.getDouble("points") + 5 );
			isSuccess = myPoints.update();
			}
		}else{
			myPoints = new MyPoints();
			myPoints.set("open_id", openId);
			myPoints.set("points", 5);
			myPoints.set("dr", 0);
			myPoints.set("ts", new Timestamp(System.currentTimeMillis()));
			isSuccess = myPoints.save();
		}
		if( isSuccess ){
		    renderText("success");
		}else{
			renderText("error");
		}
		return ;
		
	}

}

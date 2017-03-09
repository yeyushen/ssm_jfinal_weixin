package com.jfinal.weixin.skywayct.api.common;

/**
 * 项目名称：
 * @author:[skywayct]
 * 修改日期：
*/
public class WeixinConst {
	
	public static final String CMCC = "中国移动";
	public static final String UNICOM = "中国联通";
	public static final String TELECOM = "中国电信";
	
	public static final String partner = "101560005710";//"1332887001";
	public static final String paternerKey = "38d2132a3d708205333949174334913d";//"579cbf97fa7941f59e713fc19956c369";
	public static final String notify_url = "http://lldz.leedate.com/skywayct/recharge";
	public static final String sendName = "流量地主";
	public static final String wxpartner = "1332887001";//"1332887001";
	public static final String wxpaternerKey = "579cbf97fa7941f59e713fc19956c369";//"579cbf97fa7941f59e713fc19956c369";
	
	
	public static int getCardType(String t){
		if(t==null||t.equals(""))
			return 0;
		if(t.equals("中国移动"))
			return 1;
		if(t.equals("中国联通"))
			return 2;
		if(t.equals("中国电信"))
			return 3;
		return 0;
	}
	
	public static String SettlementMethod(int method){
		if(method==1)
			return "微信支付";
		if(method==2)
			return "钱包支付";
		return "未知";
	}
	
	
	
}

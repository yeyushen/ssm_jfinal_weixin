package com.jfinal.weixin.skywayct.api.returninfo;

import java.util.HashMap;

/**
 * @author CZJIE
 * @version V1.0
 * @Title: ReturnHandler.java
 * @date 2015/9/8 11:08
 * @Description:
 */
public class ReturnHandler {

    private static final HashMap<Integer, ReturnBean> msg;

    static{
        msg = new HashMap<Integer, ReturnBean>();
        msg.put(10000,new ReturnBean(10000,"请求成功","请求成功"));
        msg.put(20000,new ReturnBean(20000,"请求成功","正在处理"));
        msg.put(40000,new ReturnBean(40000,"订单接口发生异常","订单接口发生异常"));
        msg.put(40001,new ReturnBean(40001,"系统订单状态发生异常","订单金额和冻结资金数据异常"));
        msg.put(40002,new ReturnBean(40002,"系统订单状态发生异常","接口端报文返回解析出错"));
        msg.put(50000,new ReturnBean(50000,"客户端接口发生异常","订单接口发生异常"));
        msg.put(50001,new ReturnBean(50001,"客户端验证失败","服务器绑定IP不正确"));
        msg.put(50002,new ReturnBean(50002,"客户端验证失败","access_token验证失败，请通过接口重新获取。"));
        msg.put(50003,new ReturnBean(50003,"客户端验证失败","appid或appsecret不正确，请联系平台管理员。"));
        msg.put(50004,new ReturnBean(50004,"请求参数不全","流水号不能为空"));
        msg.put(50005,new ReturnBean(50005,"请求参数不全","access_token不能为空"));
        msg.put(50006,new ReturnBean(50006,"请求参数不全","appid不能为空"));
        msg.put(50007,new ReturnBean(50007,"请求参数不全","appsecret不能为空"));
        msg.put(50008,new ReturnBean(50008,"请求参数不全","流水号不规范"));
        msg.put(50009,new ReturnBean(50009,"请求参数不全","产品类目不正确"));
        msg.put(50010,new ReturnBean(50010,"请求参数不全","产品ID不能为空"));
        msg.put(50011,new ReturnBean(50011,"请求参数不全","生效方式不能为空"));
        msg.put(50012,new ReturnBean(50012,"请求参数不全","手机号码不能为空"));
        msg.put(50013,new ReturnBean(50013,"请求参数不全","产品ID不正确"));
        msg.put(50014,new ReturnBean(50014,"请求参数不全","生效方式不正确"));
        msg.put(50015,new ReturnBean(50015,"请求参数不全","手机号码不正确"));
        msg.put(50016,new ReturnBean(50016,"下单验证不通过","余额不足"));
        msg.put(50017,new ReturnBean(50017,"请求参数不全","流水号时间类型不正确"));
        msg.put(50018,new ReturnBean(50018,"请求参数不全","流水号时间延迟严重"));
        msg.put(50019,new ReturnBean(50019,"订单接口发生异常","移动下单失败，本地更新订单状态失败"));
        msg.put(50020,new ReturnBean(50020,"订单接口发生异常","移动下单成功，本地更新订单状态失败"));
        msg.put(50021,new ReturnBean(50021,"账户异常","账户异常，请联系代理商。"));
        msg.put(50022,new ReturnBean(50022,"账户异常","产品价格异常，请检查产品的费率套餐 。"));

        msg.put(1,new ReturnBean(1,"请求成功","充值成功"));
        msg.put(-2,new ReturnBean(-2,"订单接口发生异常","充值失败"));
        msg.put(-3,new ReturnBean(-3,"订单接口发生异常","用户名或密码有误，验证不通过"));
        msg.put(-4,new ReturnBean(-4,"订单接口发生异常","手机号码格式有误"));
        msg.put(-5,new ReturnBean(-5,"订单接口发生异常","用户余额不足"));
        msg.put(-6,new ReturnBean(-6,"订单接口发生异常","IP验证不通过"));
        msg.put(-7,new ReturnBean(-7,"订单接口发生异常","单号格式有误"));
        msg.put(0,new ReturnBean(0,"订单接口发生异常","通讯失败"));
        msg.put(-22,new ReturnBean(-22,"订单接口发生异常","-22充值失败"));
        
        msg.put(10,new ReturnBean(10,"请求成功","操作成功"));
        msg.put(-10,new ReturnBean(-10,"订单接口发生异常","用户名或密码为空或者其他错误"));
        msg.put(-20,new ReturnBean(-20,"订单接口发生异常","用户名或者密码错误"));
        msg.put(-30,new ReturnBean(-30,"订单接口发生异常","用户名被锁"));
        msg.put(-80,new ReturnBean(-80,"订单接口发生异常","余额不足"));
        msg.put(-90,new ReturnBean(-90,"订单接口发生异常","没有正确的号码"));
        msg.put(-100,new ReturnBean(-100,"订单接口发生异常","系统错误"));
        
    }

    /**
     * 通过返回码获取返回信息
     */
    public static ReturnBean get(int errCode){
         ReturnBean result = msg.get(errCode);
        return result !=null? result : new ReturnBean(40000,"系统发生未知异常","");
    }
}

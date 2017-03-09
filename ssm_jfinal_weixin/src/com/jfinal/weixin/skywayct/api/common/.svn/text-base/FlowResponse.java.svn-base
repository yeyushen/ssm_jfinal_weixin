package com.jfinal.weixin.skywayct.api.common;

import com.jfinal.weixin.model.Order;
import com.jfinal.weixin.skywayct.api.returninfo.ReturnBean;
import com.jfinal.weixin.skywayct.api.returninfo.ReturnHandler;



/**
 * @author CZJIE
 * @version V1.0
 * @Title: FlowResponse.java
 * @date 2015/9/6 20:01
 * @Description:
 */
public class FlowResponse {

    //订单状态true成功 false失败
    private boolean status;
    //处理编码
    private Integer code;
    //结果汇总信息
    private String msg;
    //结果详细信息
    private String detail;

    //充值手机号码
    private String Mobile;
    //移动接口流水号
    private String transIDO;
    //移动接口订单号
    private String CRMApplyCode;
    //接口返回编码
    private String apiCode;
    //需要处理的订单
    private Order order;

    public void setReturn(Integer error){
        ReturnBean r = ReturnHandler.get(error);
        this.setCode(r.getCode());
        this.setMsg(r.getDetail());
        if(this.detail==null){
            this.setDetail(r.getDetail());
        }
        if(error!=10000){
            this.status = false;
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getTransIDO() {
        return transIDO;
    }

    public void setTransIDO(String transIDO) {
        this.transIDO = transIDO;
    }

    public String getCRMApplyCode() {
        return CRMApplyCode;
    }

    public void setCRMApplyCode(String CRMApplyCode) {
        this.CRMApplyCode = CRMApplyCode;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
        if(this.order!=null){
            this.order.set("log",this.detail).update();
        }
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

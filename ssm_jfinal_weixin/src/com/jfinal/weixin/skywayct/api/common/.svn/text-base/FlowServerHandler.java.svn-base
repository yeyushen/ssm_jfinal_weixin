package com.jfinal.weixin.skywayct.api.common;

import com.jfinal.plugin.activerecord.Record;
import com.jfinal.weixin.model.Order;


/**
 * @author 
 * @version V1.0
 * @Title: FlowServerHandler.java
 * @date 
 * @Description:
 * 接口处理器抽象类，所有接入的接口均需要实现此类
 */
public abstract class FlowServerHandler {

    //需要处理的订单
    protected Order order = null;

    //订单对应的接口数据实例
    protected Record product = null;


    public FlowServerHandler(){
    }

    /**
     * 根据请求进行流量充值
     * @return
     */
    public abstract FlowResponse doFlowRecharge();

    /**
     * 获取当前处理器的处理对象
     * @return
     */
    public abstract FlowServerHandler create();

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Record getProduct() {
        return product;
    }

    public void setProduct(Record product) {
        this.product = product;
    }
}

/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.msg.in.event;

/**
 WIFI连网后下发消息  http://mp.weixin.qq.com/wiki/19/bac84e64da24f928c3e536c742d4e0b7.html
 <xml>
 <ToUserName><![CDATA[toUser]]></ToUserName>
 <FromUserName><![CDATA[FromUser]]></FromUserName>
 <CreateTime>123456789</CreateTime>
 <MsgType><![CDATA[event]]></MsgType>
 <Event><![CDATA[WifiConnected]]></Event>
 <ConnectTime>0</ConnectTime>
 <ExpireTime>0</ExpireTime>
 <VendorId>![CDATA[3001224419]]</VendorId>
 <ShopId>![CDATA[PlaceId]]</ShopId>
 <DeviceNo>![CDATA[DeviceNo]]</DeviceNo>
 </xml>
 */
public class InWifiEvent extends EventInMsg
{
    private String connectTime;
    private String expireTime;
    private String vendorId;
    private String shopId;

    public String getConnectTime()
    {
        return connectTime;
    }

    public void setConnectTime(String connectTime)
    {
        this.connectTime = connectTime;
    }

    public String getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(String expireTime)
    {
        this.expireTime = expireTime;
    }

    public String getVendorId()
    {
        return vendorId;
    }

    public void setVendorId(String vendorId)
    {
        this.vendorId = vendorId;
    }

    public String getShopId()
    {
        return shopId;
    }

    public void setShopId(String shopId)
    {
        this.shopId = shopId;
    }

    public String getDeviceNo()
    {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo)
    {
        this.deviceNo = deviceNo;
    }

    private String deviceNo;

    public InWifiEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event)
    {
        super(toUserName, fromUserName, createTime, msgType, event);
    }


}







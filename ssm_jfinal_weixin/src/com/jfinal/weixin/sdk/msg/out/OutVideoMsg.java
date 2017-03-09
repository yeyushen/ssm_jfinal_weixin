/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.msg.out;

import com.jfinal.weixin.sdk.msg.in.InMsg;

/**
	回复视频消息
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[fromUser]]></FromUserName>
		<CreateTime>12345678</CreateTime>
		<MsgType><![CDATA[video]]></MsgType>
			<Video>
				<MediaId><![CDATA[media_id]]></MediaId>
				<Title><![CDATA[title]]></Title>
				<Description><![CDATA[description]]></Description>
			</Video>
	</xml>
 */
public class OutVideoMsg extends OutMsg {
	
	private String mediaId;
	private String title;		// 不是必须
	private String description;	// 不是必须
	
	public OutVideoMsg() {
		this.msgType = "video";
	}
	
	public OutVideoMsg(InMsg inMsg) {
		super(inMsg);
		this.msgType = "video";
	}
	
	@Override
	protected void subXml(StringBuilder sb) {
		if (null == mediaId) {
			throw new NullPointerException("mediaId is null");
		}
		sb.append("<Video>\n");
		sb.append("<MediaId><![CDATA[").append(mediaId).append("]]></MediaId>\n");
		sb.append("<Title><![CDATA[").append(nullToBlank(title)).append("]]></Title>\n");
		sb.append("<Description><![CDATA[").append(nullToBlank(description)).append("]]></Description>\n");
		sb.append("</Video>\n");
	}
	
	public String getMediaId() {
		return mediaId;
	}
	
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

}








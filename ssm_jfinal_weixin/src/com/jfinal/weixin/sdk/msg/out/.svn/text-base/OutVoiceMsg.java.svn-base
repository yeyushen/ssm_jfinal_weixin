/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.msg.out;

import com.jfinal.weixin.sdk.msg.in.InMsg;

/**
	回复语音消息
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[fromUser]]></FromUserName>
		<CreateTime>12345678</CreateTime>
		<MsgType><![CDATA[voice]]></MsgType>
			<Voice>
				<MediaId><![CDATA[media_id]]></MediaId>
			</Voice>
	</xml>
 */
public class OutVoiceMsg extends OutMsg {
	
	private String mediaId;
	
	public OutVoiceMsg() {
		this.msgType = "voice";
	}
	
	public OutVoiceMsg(InMsg inMsg) {
		super(inMsg);
		this.msgType = "voice";
	}
	
	@Override
	protected void subXml(StringBuilder sb) {
		if (null == mediaId) {
			throw new NullPointerException("mediaId is null");
		}
		sb.append("<Voice>\n");
		sb.append("<MediaId><![CDATA[").append(mediaId).append("]]></MediaId>\n");
		sb.append("</Voice>\n");
	}
	
	public String getMediaId() {
		return mediaId;
	}
	
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}














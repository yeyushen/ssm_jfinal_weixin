/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.msg.out;

import com.jfinal.weixin.sdk.msg.in.InMsg;

/**
	回复文本消息
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[fromUser]]></FromUserName>
		<CreateTime>12345678</CreateTime>
		<MsgType><![CDATA[text]]></MsgType>
			<Content><![CDATA[你好]]></Content>
	</xml>
 */
public class OutTextMsg extends OutMsg {
	
	private String content;
	
	public OutTextMsg() {
		this.msgType = "text";
	}
	
	public OutTextMsg(InMsg inMsg) {
		super(inMsg);
		this.msgType = "text";
	}
	
	@Override
	protected void subXml(StringBuilder sb) {
		if (null == content) {
			throw new NullPointerException("content is null");
		}
		sb.append("<Content><![CDATA[").append(content).append("]]></Content>\n");
	}
	
	public String getContent() {
		return content;
	}
	
	public OutTextMsg setContent(String content) {
		this.content = content;
		return this;
	}

}



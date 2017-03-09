<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>微信营销活动</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="流量">
	<meta http-equiv="description" content="营销活动">
	<link rel="stylesheet" href="static/ace/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
	<link rel="stylesheet" type="text/css" href="static/easyui/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="static/easyui/css/icon.css">
	<style>
	#jxszdiv td{border-style:solid;border-color:#7EC2FA;border-width:0 1px 1px 0;padding:3px}	
	#jxszdiv input{box-sizing:border-box;width:100%}
	body .window table{font-size:12px;}
	</style>
  </head>
  

<body>		
	<div region="west" split="false" style="height:auto;padding:10px;width:auto">
		<div style="height:30px;font-size:10pt;color:#103770;overflow:hidden;" >
			
			<a class="easyui-linkbutton" iconCls="icon-add" href="javascript:void(0)" onClick="wx_hycjAdd(0)">大转盘</a>&nbsp;
			<a class="easyui-linkbutton" iconCls="icon-add" href="javascript:void(0)" onClick="wx_hycjAdd(1)">刮刮卡</a>&nbsp;
			<a class="easyui-linkbutton" iconCls="icon-shortcut" href="javascript:void(0)" onClick="wx_hycjDj()">兑奖</a>
			<img title="兑奖说明" style="cursor:pointer;vertical-align:middle;height:16px;width:16px;" src="static/easyui/css/icons/help.png" onClick="djHelp()">
		</div>
		<table id="wx_hycj"></table>	
	</div>	
	<div id="wx_hycj_add" class="easyui-window" closed="true" style="display:none;">
		<div style="margin:8px;padding-bottom:10px;border-bottom:1px solid #7EC2FA">
			<table>
				<tr>
					<td style="width:80px">抽奖类型：</td><td><a id="wx_hycj_cj_type"></a></td>
				</tr>
				<tr>
					<td>活动主题：</td><td><input id="wx_hycj_motif" class="easyui-validatebox" style="padding:2px;height:20px;width:590px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=120></input></td>
				</tr>
				<tr>
					<td style="width:80px" valign="top">活动说明：</td>
					<td>
						<textarea id="wx_hycj_des" rows="3" class="ta" style="font-size:9pt;width:590px;" maxlength="120" onpropertychange="onmyinput(this)" oninput="return onmyinput(this)" onPaste="return onmypaste(this);" onKeyPress="return onmykeypress(this);"></textarea><br>
						<select id="wx_hycj_bl" class="easyui-combobox" style="width:290px;">
						    <option value="[每人限抽次数]">每人限抽次数</option>
						    <option value="[个人已抽奖次数]">个人已抽奖次数</option>
						    <option value="[发行数量]">发行数量</option>
						    <option value="[累计抽奖数量]">累计抽奖数量</option>
						    <option value="[开始日期]">开始日期</option>
						    <option value="[结束日期]">结束日期</option>
						    <option value="[抽奖扣减积分]">抽奖扣减积分</option>
						</select>
						
						<a style="margin-right:0px" id="wx_hycj_bl_insert" class="easyui-linkbutton" iconCls="" href="javascript:void(0)" onClick="wx_hycjInsert()">插入选中的变量</a>
						
					</td>
				</tr>
				<tr>
					<td style="width:80px">活动时间：</td><td><input id="wx_hycj_cj_rq_begin" class="easyui-datebox" style="padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=120></input> — <input id="wx_hycj_cj_rq_end" class="easyui-datebox" style="padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=120></input></td>
				</tr>
				<tr>
					<td style="width:80px" valign="top">奖项设置：</td>
					<td>
						<div id="jxszdiv">
							<table style="border:1px solid #7EC2FA;border-width:1px 0 0 1px;width:596px;" cellpadding="0" cellspacing="0">
								<tr align="center"><td width=10%></td><td width=60%>奖品</td><td width=10%>奖品数量</td><td width=10%>已中奖</td><td width=10%>已兑奖</td></tr>	
								<tr><td align=center>一等奖</td>
									<td>
										<input id="wx_hycj_lv1_dis" disabled=disabled class="easyui-validatebox" style="width:280px;padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=50></input>
										<a class="easyui-linkbutton" iconCls="" href="javascript:wx_hycjjpset(1)" id="wx_hycj_jp1">设置</a>
										<input id="wx_hycj_lv1" type="hidden" class="easyui-validatebox" style="width:350px;padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=50></input>
									</td>
									<td><input id="wx_hycj_lv1_sl" class="easyui-numberbox" style="width:50px;text-align:center;padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=8></input></td>
									<td align=center><a id="wx_hycj_lv1_zjs"></a></td><td align=center><a id="wx_hycj_lv1_djs"></a></td>
								</tr>
								<tr><td align=center>二等奖</td>
									<td>
										<input id="wx_hycj_lv2_dis" disabled=disabled class="easyui-validatebox" style="width:280px;padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=50></input>
										<a class="easyui-linkbutton" iconCls="" href="javascript:wx_hycjjpset(2)" id="wx_hycj_jp2">设置</a>
										<input id="wx_hycj_lv2" type="hidden" class="easyui-validatebox" style="width:350px;padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=50></input></td>
									<td><input id="wx_hycj_lv2_sl" class="easyui-numberbox" style="width:50px;text-align:center;padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=8></input></td>
									<td align=center><a id="wx_hycj_lv2_zjs"></a></td><td align=center><a id="wx_hycj_lv2_djs"></a></td>
								</tr>
								<tr><td align=center>三等奖</td>
									<td>
										<input id="wx_hycj_lv3_dis" disabled=disabled class="easyui-validatebox" style="width:280px;padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=50></input>
										<a class="easyui-linkbutton" iconCls="" href="javascript:wx_hycjjpset(3)" id="wx_hycj_jp3">设置</a>
										<input id="wx_hycj_lv3" type="hidden" class="easyui-validatebox" style="width:350px;padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=50></input></td>
									<td><input id="wx_hycj_lv3_sl" class="easyui-numberbox" style="width:50px;text-align:center;padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=8></input></td>
									<td align=center><a id="wx_hycj_lv3_zjs"></a></td><td align=center><a id="wx_hycj_lv3_djs"></a></td>
								</tr>
								<tr id="tr_lv4"><td align=center>四等奖</td>
									<td>
										<input id="wx_hycj_lv4_dis" disabled=disabled class="easyui-validatebox" style="width:280px;padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=50></input>
										<a class="easyui-linkbutton" iconCls="" href="javascript:wx_hycjjpset(4)" id="wx_hycj_jp4">设置</a>
										<input id="wx_hycj_lv4" type="hidden" class="easyui-validatebox" style="width:350px;padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=50></input></td>
									<td><input id="wx_hycj_lv4_sl" class="easyui-numberbox" style="width:50px;text-align:center;padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=8></input></td>
									<td align=center><a id="wx_hycj_lv4_zjs"></a></td><td align=center><a id="wx_hycj_lv4_djs"></a></td>
								</tr>
								<tr id="tr_lv5"><td align=center>五等奖</td>
									<td>
										<input id="wx_hycj_lv5_dis" disabled=disabled class="easyui-validatebox" style="width:280px;padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=50></input>
										<a class="easyui-linkbutton" iconCls="" href="javascript:wx_hycjjpset(5)" id="wx_hycj_jp5">设置</a>
										<input id="wx_hycj_lv5" type="hidden" class="easyui-validatebox" style="width:350px;padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=50></input></td>
									<td><input id="wx_hycj_lv5_sl" class="easyui-numberbox" style="width:50px;text-align:center;padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=8></input></td>
									<td align=center><a id="wx_hycj_lv5_zjs"></a></td><td align=center><a id="wx_hycj_lv5_djs"></a></td>
								</tr>
							</table>
						</div>
					</td>
				</tr>	
				<tr>
					<td style="width:80px">发行数量：</td><td style="line-height:28px"><input id="wx_hycj_cj_sl" class="easyui-numberbox" style="padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA;width:47px;margin-right:10px;" maxlength=6></input>
						累计抽奖数量：<input id="wx_hycj_ycj_sl" disabled class="easyui-numberbox" style="padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA;width:47px;margin-right:10px" maxlength=6></input>
						每人限抽次数：<input id="wx_hycj_hy_can" class="easyui-numberbox" style="padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA;width:28px;margin-right:10px;" maxlength=4></input>
						抽奖扣减积分：<input id="wx_hycj_jf_need" class="easyui-numberbox" style="padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA;width:25px;margin-right:10px;" maxlength=4></input>
						
				</tr>
				<tr>
					<td style="width:80px" valign="top">兑奖说明：</td>
					<td>
						<textarea id="wx_hycj_dj_des" rows="3" class="ta" style="font-size:9pt;width:590px;" maxlength="120" onpropertychange="onmyinput(this)" oninput="return onmyinput(this)" onPaste="return onmypaste(this);" onKeyPress="return onmykeypress(this);"></textarea><br>						
					</td>
				</tr>
			</table>
		</div>
		<div style="text-align:center;margin-bottom:8px"><a style="margin-right:20px" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" id="wx_hycj_save">保存</a><a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onClick="wx_hycjAddCancel()">返回</a></div>
	</div>	
	<div id="w_wx_hycj_zjmd" class="easyui-window" closed="true" style="display:none;">
		<div><table id="wx_hycj_zjmd"></table></div>	
	</div>
	<div id="w_wx_hycj_jpset" class="easyui-window" closed="true" style="display:none;">
		<div style="margin:10px 10px 0 10px">
			<input type="radio" onchange="wx_hycjJpSetSelect(this.value? 1: 2)" id="wx_hycj_jp_wpradio" name="jptype" value="0" checked="false"/>物品：
			<input id="wx_hycj_jp_wpinput" disabled class="easyui-validatebox" style="width:390px;padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=50></input>
		</div>	
		<div style="margin:10px 10px 0 10px">
			<input type="radio" onchange="wx_hycjJpSetSelect(this.value? 2: 1)" id="wx_hycj_jp_jfradio" name="jptype" value="0" checked="false"/>积分：
			<input id="wx_hycj_jp_jfinput" disabled class="easyui-numberbox" style="width:390px;padding:2px;height:20px;margin:0;font-size:9pt;border:1px solid #7EC2FA" maxlength=50></input>
		</div>	
		<div style="text-align:center;margin:20px 10px 10px 10px"><a style="margin-right:20px" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" id="wx_hycjJpSetSave">保存</a><a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onClick="wx_hycjJpSetCancel()">返回</a></div>
	</div>
	<script src="static/easyui/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="static/easyui/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="static/easyui/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="weixin/js/skywayct.js"></script>
	<script type="text/javascript" src="static/easyui/js/activity.js"></script>
</body>
</html>


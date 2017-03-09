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
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="static/easyui/css/easyui.css">
<link rel="stylesheet" type="text/css" href="static/easyui/css/icon.css">
<link rel="stylesheet" href="static/ace/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />

<style>
	.menu_a_w,.menu_a,.menu_e,.menu_d,.menu_u,.menu_s{float:right;width:16px;height:16px;cursor:pointer;margin-top:4px;}
	.menu_a_w{background:url(weixin/images/menu_a_w.png) no-repeat;margin-top:4px;margin-right:8px}
	.menu_a{background:url(weixin/images/menu_a.png) no-repeat;}
	.menu_e{background:url(weixin/images/menu_e.png) no-repeat;}
	.menu_d{background:url(weixin/images/menu_d.png) no-repeat;}
	.menu_u{background:url(weixin/images/menu_u.png) no-repeat;}
	.menu_s{background:url(weixin/images/menu_s.png) no-repeat;}
	.menu_firstlevel{line-height:25px;background-color:#eee;font-size:11pt;border-bottom:1px solid #777;clear:both}
	.menu_secondlevel{margin-left: 30px;height:25px;line-height:25px;background-color:#fff;font-size:10pt;border-bottom:1px solid #eee;}
	.menu_ul{margin:0;background: white;padding:0;}
	.menu_sel{background:#FBEC88}
	

	table, input[type=text]{font-size:14px;border:1px solid #7EC2FA}
	#w_menu_fun td{line-height:22px;}
	#w_menu_fun input[type=text]{height:22px;}
</style>
</head>

<body>	
	
	<div  style="overflow-y:auto;overflow-x:hidden;padding:10px;">

		<div id="wx_menu" style="float:left;width:49%;margin-right:4px;border:1px solid #7EC2FA">
			<div style="background-color:#56A6C5;color:#fff;height:25px;line-height:25px;font-size:11pt;text-align:left;padding-left:15px;">菜单<div class="menu_a_w" title="添加一级菜单" onclick="wx_menu_add()"></div></div>
			<div id="wx_menu_menu" style="overflow:auto"></div>
		</div>
		<div id="wx_menufun" style="float:right;width:49%;border:1px solid #7EC2FA">
			<div style="background-color:#56A6C5;color:#fff;height:25px;line-height:25px;font-size:11pt;text-align:left;padding-left:15px;">对应功能&nbsp;&nbsp;<a id="aobjsel" style="font-size:10pt;"></a></div>
			<div id="wx_menufun_fun" style="vertical-align: middle;text-align: center;padding:20px;font-size:11pt"></div>
		</div>
	</div>	
	<div style="padding-left:12px;"><a class="btn btn-sm btn-info" onclick="save();">保存</a></div>
	<div id="w_menu_fun" class="easyui-window" closed="true" modal="true" style="display:none;">
		<div style="padding:15px;line-height:30px;text-align:center">
			<table style="border:0">
				<tr>
					<td style="width:100px" align="right" >菜单名称：</td>
					<td style="width:300px" align="left">
						<input id="wx_menu_name" class="easyui-validatebox" type="text" style="width:290px" onkeydown="if(event.keyCode==13){wx_kfzidsave()}" maxlength=16></input>
					</td>
				</tr>
				<tr>
					<td style="width:100px" align="right" >响应类型：</td>
					<td style="width:300px" align="left">
						<select id="wx_menu_type" class="easyui-combobox" style="width:290px;" editable="false">
						    <option value="sysview">访问系统网页</option>
						    <option value="click">执行命令</option>
						    <option value="view">访问自定义网页</option>
						</select>
					</td>
				</tr>
				<tr id="tr_key">
					<td style="width:100px" align="right" >命令选择：</td>
					<td style="width:300px" align="left">
						<select id="wx_menu_key" class="easyui-combobox" style="width:290px;" editable="false">
						    <option value="DKF">在线客服</option>
						    <option value="关键字">关键字</option>
						</select>
					</td>
				</tr>
				
				
				<tr id="tr_sysurl" style="display:none">
					<td style="width:100px" align="right" >系统网页：</td>
					<td style="width:200px" align="left">
						
						<select id="wx_menu_sysurl" class="easyui-combobox" style="width:290px;" editable="false">
							<!-- /*@dm@1【流量充值】 @dm@2【话费充值】 @dm@3【充值查询】@dm@4【个人中心】 @dm@5【推广名片】*/ -->
						    <option value="@dm@1">【流量充值】 </option>
						    <option value="@dm@2">【话费充值】 </option>
						    <option value="@dm@3">【充值查询】</option>
						    <option value="@dm@4">【个人中心】</option>
						    <option value="@dm@5">【推广名片】</option>
						</select>
					</td>
				</tr>
				
				<tr id="tr_url" style="display:none">
					<td style="width:100px" align="right" >网页地址：</td>
					<td style="width:200px" align="left">
						<input id="wx_menu_url" class="easyui-validatebox" type="text" style="width:290px" maxlength=500></input>
					</td>
				</tr>
				<tr id="tr_text" style="display:none">
					<td style="width:100px" align="right" >关键字：</td>
					<td style="width:200px" align="left">
						<input id="wx_menu_text" class="easyui-validatebox" type="text" style="width:290px" maxlength=500></input>
					</td>
				</tr>
				<tr id="tr_empty" style="display:none">
					<td colspan=2 align="center" ><a id="amenu_tip" style="color:#666">&nbsp;</a></td>					
				</tr>
			</table>
		</div>
		<div style="text-align:center;margin-bottom:8px"><a id="wx_menuok" class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)">确定</a>&nbsp;<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onClick="wx_menuCancel()">取消</a></div>
	</div>
</body>
<script src="static/easyui/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="static/easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/js/easyui-lang-zh_CN.js"></script>
<script>	
	function getDefault(){
			var json = '${varList}';
			if(json)
				return json;
			
			return  "{" + "\n" +
			"	\"button\":[" + "\n" +
			"	{" + "\n" +
			"	     \"name\":\"充值\"," + "\n" +
			"	     \"sub_button\":[" + "\n" +
			"	{" + "\n" +
			"	     \"type\":\"view\"," + "\n" +
			"	     \"name\":\"流量充值\"," + "\n" +
			"	     \"url\":\"@dm@1\"" +
			"	}]" + "\n" +				
			"	},{" + "\n" +
			"	     \"name\":\"服务中心\"," + "\n" +
			"	     \"sub_button\":[" + "\n" +
			"	{" + "\n" +
			"	     \"type\":\"click\"," + "\n" +
			"	     \"name\":\"在线客服\"," + "\n" +
			"	     \"key\":\"在线客服\"" + "\n" +
			"	},{" + "\n" +
			"	     \"type\":\"view\"," + "\n" +
			"	     \"name\":\"个人中心\"," + "\n" +
			"	     \"url\":\"@dm@4\"" + "\n" +
			"	},{" + "\n" +
			"	     \"type\":\"view\"," + "\n" +
			"	     \"name\":\"平台介绍\"," + "\n" +
			"	     \"url\":\"http://t.cn/R5c3uMn\"" + "\n" +
			"	},{" + "\n" +
			"	     \"type\":\"view\"," + "\n" +
			"	     \"name\":\"推广名片\"," + "\n" +
			"	     \"url\":\"@dm@5\"" + "\n" +
			"	}]" + "\n" +
			"	}]}";		
	}
	function clearMenu(){
		var divmenu = document.getElementById("wx_menu_menu");
		divmenu.innerHTML = "";
		document.getElementById("wx_menufun_fun").innerHTML = "";
	}
	function setMenu(obj){
		clearMenu();
		if(!obj){return;}	
		if(!obj.button){return;}		
		if (obj.menuid){$("#wx_menu").attr("menuid", obj.menuid)}
		var blen = obj.button.length;
		var divfirst, buttonsubs;
		for (var i = 0; i < blen; i++){
			divfirst = wx_menu_add(obj.button[i].name,obj.button[i].type,obj.button[i].url,obj.button[i].key);
			if(obj.button[i].sub_button){				
				buttonsubs = obj.button[i].sub_button;
				for (var j = 0; j < buttonsubs.length; j++){
					wx_menu_add_sub(divfirst.childNodes[0], buttonsubs[j].name, buttonsubs[j].type,buttonsubs[j].url,buttonsubs[j].key,buttonsubs[j].url)
				}				
			}
		}
	}
	function getMenu(){
		var divmenu = document.getElementById("wx_menu_menu");
		var o = divmenu.getElementsByTagName('div');
		if (o.length == 0){return "";}
		var ssub, retn = '{"button":[';
		
		for (var i = 0; i < o.length; i++){
			if (i > 0){retn += ',';}
			retn += '{"name":"' + getAttr(o[i],"mname") + '"';
			ssub = getSecondMenu(o[i]);			
			if (ssub != ""){
				retn += ssub;
			}else{
				if (o[i].attributes["mtype"]){retn += ',"type":"' + getAttr(o[i],"mtype") + '"';}
				if (o[i].attributes["murl"]){retn += ',"url":"' + getAttr(o[i],"murl") + '"';}
				if (o[i].attributes["mkey"]){retn += ',"key":"' + getAttr(o[i],"mkey") + '"';}
			}
			retn += '}';
		}
		retn += ']}';
		return retn;
	}
	function getSecondMenu(obj){
		var retn = "";
		var oul = obj.getElementsByTagName('ul');
		if (oul){
			olis = oul[0].getElementsByTagName('li');
			if( olis.length > 0){
				retn = ',"sub_button":[';
				for (var j = 0; j < olis.length; j++){
					if (j > 0){retn += ',';}				
					
					retn += '{"type":"' + getAttr(olis[j],"mtype") + '","name":"' + getAttr(olis[j],"mname") + '"';
					if (olis[j].attributes["murl"]){retn += ',"url":"' + getAttr(olis[j],"murl") + '"';}
					if (olis[j].attributes["mkey"]){retn += ',"key":"' + getAttr(olis[j],"mkey") + '"';}
					retn += '}';
				}
				retn += "]";				
			}
		}
		return retn;	
	}
	//属性设置
	function wx_set_menu_attr(li, tit, mtype,murl, mkey, msysurl){
		removeAttr(li, "mtype");
		removeAttr(li, "mkey");
		removeAttr(li, "murl");
		
		if (mtype){
			if(mtype == "sysview"){
				mtype = "view";
				murl = msysurl || murl;
			}
			attr(li, "mtype", mtype);
			if (mtype == "click"){
				if($('#wx_menu_text').val()!=""&&mkey!="DKF") 
					$('#wx_menu_text').val(mkey);
				if (mkey){attr(li,"mkey",mkey);}
			}else if (mtype == "scancode_push" || mtype == "scancode_waitmsg"){
				attr(li,"mkey","cdsm");
			}else{
				if (murl){attr(li,"murl",murl);}
			}			
		}
		attr(li,"mname",tit);
	}
	function wx_menu_add(tit, mtype,murl, mkey,msysurl){		
		var divmenu = document.getElementById("wx_menu_menu");
		var o = divmenu.getElementsByTagName('div');
		if (o.length >= 3){
			alert("最多只能设置3个一级菜单！")
			return;
		}
		if(!tit){ 
			wx_menu_editshow(1, function(){
				if (wx_menu_ok_check() == -1){return;}
				objsel(wx_menu_add_real(divmenu, $('#wx_menu_name').val(), $('#wx_menu_type').combobox('getValue'), $('#wx_menu_url').val(), $('#wx_menu_key').combobox('getValue'), $('#wx_menu_sysurl').combobox('getValue')));
			});
			var a
			return a; 
		}else{
			return wx_menu_add_real(divmenu, tit, mtype,murl, mkey, msysurl);
		}		
	}	
	function wx_menu_add_real(obj, tit, mtype,murl, mkey, msysurl){
		var li = document.createElement('div');
		li.className = "menu_firstlevel";
		wx_set_menu_attr(li, tit, mtype,murl, mkey, msysurl);
		addEvent(li,"click", function(){objsel(li)})
		li.innerHTML = '<span class="menu_s" title="删除菜单" onclick="wx_menu_delete(this)"></span><span class="menu_e" title="编辑菜单" onclick="wx_menu_edit(this)"></span><span class="menu_d" title="下移" onclick="wx_menu_move(this, 1)"></span><span class="menu_u" title="上移" onclick="wx_menu_move(this, -1)"></span><span class="menu_a" title="添加二级菜单" onclick="wx_menu_add_sub(this)"></span>&nbsp;<tit>' + tit + '</tit><ul class="menu_ul"></ul>';		
		obj.appendChild(li, obj.childNodes[0]);  		
		return li;
	}
	function wx_menu_add_sub(obj, tit, mtype, murl, mkey, msysurl){
		if (!obj){return;}
		var obj_p = obj.parentNode;
		if (!obj_p){return;}
		var oul = obj_p.getElementsByTagName('ul');
		if (!oul){return;}
		if (oul.length != 1){return;}
		oul = oul[0]
		var o = oul.getElementsByTagName('li');
		if (o.length >= 5){
			alert("每个一级菜单最多只能设置5个二级菜单！")
			return;
		}
		if(!tit){ 
			wx_menu_editshow(2, function(){
				if (wx_menu_ok_check() == -1){return;}
				objsel(wx_menu_add_sub_real(oul, $('#wx_menu_name').val(), $('#wx_menu_type').combobox('getValue'), $('#wx_menu_url').val(), $('#wx_menu_key').combobox('getValue'), $('#wx_menu_sysurl').combobox('getValue'))		);
			});
			var a
			return a;
		}else{
			return wx_menu_add_sub_real(oul, tit, mtype, murl, mkey, msysurl)
		}
	}
	function wx_menu_add_sub_real(obj, tit, mtype, murl, mkey, msysurl){
		var li = document.createElement('li');		
		li.className = "menu_secondlevel";
		li.innerHTML = '<span class="menu_s" title="删除菜单" onclick="wx_menu_delete(this)"></span><span class="menu_e" title="编辑菜单" onclick="wx_menu_edit(this)"></span><span class="menu_d" title="下移" onclick="wx_menu_move(this, 1)"></span><span class="menu_u" title="上移" onclick="wx_menu_move(this, -1)"></span><tit>' + tit + '</tit>';		
		wx_set_menu_attr(li, tit, mtype,murl, mkey, msysurl);
		attr(li,"mname",tit)
		addEvent(li,"click", function(){objsel(li)})
		if(ab_first){
			ab_first = false;
			prependChild(obj,li);
		}else{
			obj.appendChild(li, obj.childNodes[0]);  		
		}
		return li;
	}
	function prependChild(parent,newChild){
    if(parent.firstChild){
        parent.insertBefore(newChild,parent.firstChild);
    } else {
        parent.appendChild(newChild);
    }	    
    return parent;
	}
	
	
	//删除菜单
	function wx_menu_delete(obj){
		if (!obj){return;}
		var obj_p = obj.parentNode;
		if (!obj_p){return;}
		var obj_menu = obj_p.parentNode;
		if (!obj_menu){return;}
		obj_menu.removeChild(obj_p);
	}
	//菜单移动
	function wx_menu_move(obj, pos){
		if (!obj){return;}
		var obj_p = obj.parentNode;
		if (!obj_p){return;}
		var obj_menu = obj_p.parentNode;
		if (!obj_menu){return;}
		var tag = obj_p.tagName.toLowerCase();
		var bros = obj_menu.getElementsByTagName(tag);
		var innerhtml = "";
		unsel();
		
		if (pos == 1){ //下移			
			if (bros[bros.length - 1] == obj_p){
				if (tag == "div"){
					alert("该菜单项已经是当前级别下的最后一项了，无法再往下移动了！")
				}else{
					if($(obj_menu.parentNode).next()){
						ab_first = true;
						var obj_new = wx_menu_add_sub($(obj_menu.parentNode).next().find("ul")[0], getAttr(obj_p,"mname"), getAttr(obj_p,"mtype"), getAttr(obj_p,"murl"), getAttr(obj_p,"mkey"), getAttr(obj_p,"murl"));
						if(obj_new){
							$(obj_new).unbind().bind("click",function(){objsel(this)} )
							wx_menu_delete(obj);						
						}
					}else{
						alert("该菜单项已经是所有菜单的最后一项了，无法再往下移动了！")
					}
				}
				return;
			}			
			for (i = 0; i < bros.length;i++){
				if(bros[i] == obj_p){
					innerhtml += bros[i + 1].outerHTML + bros[i].outerHTML;
					i++;
				}else{
					innerhtml += bros[i].outerHTML;
				}			
			}
			obj_menu.innerHTML = innerhtml;
		}else if(pos == -1){ //上移
			if (bros[0] == obj_p){
				if (tag == "div"){
					alert("该菜单项已经是当前级别下的第一项了，无法再往上移动了！")
				}else{
					if($(obj_menu.parentNode).prev()){
						var obj_new = wx_menu_add_sub($(obj_menu.parentNode).prev().find("ul")[0], getAttr(obj_p,"mname"), getAttr(obj_p,"mtype"), getAttr(obj_p,"murl"), getAttr(obj_p,"mkey"), getAttr(obj_p,"murl"));
						if(obj_new){
							$(obj_new).unbind().bind("click",function(){objsel(this)} )
							wx_menu_delete(obj);						
						}
					}else{
						alert("该菜单项已经是所有菜单的第一项了，无法再往上移动了！")
					}
				}
				
				
				
				return;
			}				
			for (i = bros.length - 1; i >= 0 ; i-- ){
				if(bros[i] == obj_p){
					innerhtml = bros[i].outerHTML + bros[i - 1].outerHTML + innerhtml;
					i--;
				}else{
					innerhtml = bros[i].outerHTML + innerhtml;
				}				
			}
			obj_menu.innerHTML = innerhtml;	
		}
		bros = obj_menu.getElementsByTagName(tag);
		var subobjs;
		for(i = 0; i < bros.length;i++){
			$(bros[i]).unbind().bind("click",function(){objsel(this)} )
			subobjs = bros[i].getElementsByTagName("li");
			if (subobjs.length > 0){
				for(j = 0; j < subobjs.length;j++){
					$(subobjs[j]).unbind().bind("click",function(){objsel(this)} )
				}
			}
		}
		
		
		
	}
	function wx_menu_edit(obj){
		wx_menu_editshow(obj.parentNode, function(){
			if (wx_menu_ok_check() == -1){return;}
			wx_set_menu_attr(obj.parentNode, $('#wx_menu_name').val(), $('#wx_menu_type').combobox('getValue'), $('#wx_menu_url').val(), $('#wx_menu_key').combobox('getValue'), $('#wx_menu_sysurl').combobox('getValue'))		
			var tit = $('#wx_menu_name').val();
			if (obj.parentNode.getElementsByTagName("tit").length > 0){obj.parentNode.getElementsByTagName("tit")[0].innerText = tit}
			menu_display(obj.parentNode);  	
		});
	}
	function wx_menu_ok_check(){
		if($('#wx_menu_name').val() == ""){alert("请录入菜单名称！", function(){$('#wx_menu_name').focus()});return -1;}
		if($('#wx_menu_type').combobox('getValue') == "click"){
			if($('#wx_menu_key').combobox('getValue') == ""){alert("请选择命令！", function(){$('#wx_menu_key').combo("textbox").focus()});return -1;}
			if($('#wx_menu_key').combobox('getValue') != "DKF" && $('#wx_menu_text').val() == ""){alert("请录入关键字！", function(){$('#wx_menu_text').focus()});return -1;}
		}else if($('#wx_menu_type').combobox('getValue') == "view"){
			if($('#wx_menu_url').val() == ""){alert("请录入网页地址！", function(){$('#wx_menu_url').focus()});return -1;}
		}
		wx_menuCancel();
		return 1;
	}
	var HEIGHT = document.documentElement.clientHeight;
	var WIDTH=document.documentElement.clientWidth;
	function wx_menu_editshow(obj, fn){
		$('#w_menu_fun').window({
			title:"自定义菜单项设置",
			width: 500,
			height: 'auto',
			top:(HEIGHT - 216)/2,
			left:(WIDTH - 500) /2,
			closable:true,
			modal:true,
			collapsible:false,
			minimizable:false,
			maximizable:false,
			resizable:false
		});
		var mname,mtype,mkey,murl,menutip = "&nbsp;";		
		var type="";
		if(obj){type = typeof obj}
		if(obj && type == "object"){
			mname = getAttr(obj,"mname");
			mtype = getAttr(obj,"mtype");
			mkey = getAttr(obj,"mkey");
			murl = getAttr(obj,"murl");
			if(obj.tagName.toLowerCase() == "div"){				
				attr(document.getElementById("wx_menu_name"),"maxlength", "16");
				var o = obj.getElementsByTagName('li');
				if (o.length > 0){
					mtype = "";
					menutip = "包含二级菜单，无法设置对应功能"
					mtype = "";
					mkey = "";
					murl = ""
				}
			}else{
				attr(document.getElementById("wx_menu_name"),"maxlength", "40");				
			}
		}else{
			mname = "";
			mtype = "";
			mkey = "";
			murl = ""
		}
		if(mtype == 'view' && murl.substr(0,4) == "@dm@"){mtype = 'sysview'}
		$('#wx_menu_name').val(mname);
		$('#wx_menu_type').combobox('select',mtype);
		if(mkey && mkey!="DKF"){setTimeout(function(){$("#tr_text").show();$("#tr_text").val(mkey);},50);};
		if(mkey && mkey!="DKF") mkey="关键字";
		$('#wx_menu_key').combobox('select',mkey);
		$('#wx_menu_url').val(murl);
		if(murl.substr(0,4) == "@dm@"){
			$('#wx_menu_sysurl').combobox('select',murl);
		}
		menu_type(mtype);
		document.getElementById("amenu_tip").innerHTML = menutip;
		if (menutip != "&nbsp;"){
			$('#wx_menu_type').combobox('disable');
		}else{
			$('#wx_menu_type').combobox('enable');
		}
		$('#wx_menuok').unbind().bind("click", fn);
		
		
		$('#w_menu_fun').window('open');
		$('#w_menu_fun').show();
		$('#wx_menu_name').select().focus();
	}
	function wx_menuCancel(){
		$('#w_menu_fun').window('close');
	}
	var attr = function(ele,name,value){
    if(!ele) return;
    if(!!value){
        var _att = document.createAttribute(name);
        _att.nodeValue = value;
        ele.setAttributeNode(_att);
        return;
        }
    if(!!ele.getAttributeNode(name)){
        var _val = ele.getAttributeNode(name).nodeValue;
        return _val;
        }else{
        return null;
        }
    }
	var removeAttr = function(ele,name){
	  if(!ele) return;
	  switch(name){
	      case "class":
	      case "className":
	       ele.removeAttribute("class");
	       ele.removeAttribute("className");
	       break;
	      default: 
	       ele.removeAttribute(name);
	   }
  }
  function getAttr(obj, att){
  	if(!obj.attributes[att]){return ''}
  	return obj.attributes[att].nodeValue;
  }

	


	function wx_menuread(){
		confirm_ui("您确定要重新载入菜单吗？<br>重新载入后，您对当前分组所做的菜单修改都将被丢弃！", 
		function(){
			wx_menuReadReal();
		});
	}
  function wx_menudefault(){ 
	  	var menu_json = getDefault();
	  	setMenu(eval("(" + menu_json + ")"));  	
  }
  function initMenu(){  	
		wx_menudefault();
  }
  function hasClass(obj, cls) {
      return obj.className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)'));
  }

  function addClass(obj, cls) {
      if (!this.hasClass(obj, cls)) obj.className += " " + cls;
  }

  function removeClass(obj, cls) {
      if (hasClass(obj, cls)) {
          var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');
          obj.className = obj.className.replace(reg, '');
      }
  }
  function addEvent(el, evnt, func) {
		if(el.addEventListener) {
			el.addEventListener(evnt, func, false);
		} else if(el.attachEvent) {
			el.attachEvent('on'+evnt, func);
		}
	}
  function unsel(){
  	if(obj_seleted){removeClass(obj_seleted, "menu_sel")}
  }
  
  function objsel(obj){
  	if(!obj){return}
  	if(obj_seleted){removeClass(obj_seleted, "menu_sel")}
  	addClass(obj, "menu_sel");
  	obj_seleted = obj;
  	menu_display(obj);  	
  	//阻止事件冒泡
  	window.event.cancelBubble=true; //ie s
    window.event.stopPropagation(); //ff s
  }
  
  $("#wx_menu_type").combobox({  
	   onChange:function(newv,oldv){  
	     menu_type(newv);  
	   }
  });  

  $('#wx_menu_key').combobox({
	  onChange:function(newv,oldv){  
	     if(newv=="关键字"){
	    	 document.getElementById("tr_text").style.display = "";
	     }else{
	    	 document.getElementById("tr_text").style.display = "none";
	     } 
	   }
  });

    
  function menu_type(v){
  	if(v=="" || v=="scancode_push" || v == "scancode_waitmsg"){
  		document.getElementById("tr_empty").style.display = "";
  		document.getElementById("tr_key").style.display = "none";
  		document.getElementById("tr_url").style.display = "none";
  		document.getElementById("tr_sysurl").style.display = "none";  
  		document.getElementById("tr_text").style.display = "none";
  	}else if(v=="click"){
  		document.getElementById("tr_key").style.display = "";
  		document.getElementById("tr_empty").style.display = "none";
  		document.getElementById("tr_url").style.display = "none";
  		document.getElementById("tr_sysurl").style.display = "none";  
  		document.getElementById("tr_text").style.display = "none";
  	}else if(v=="view"){
  		document.getElementById("tr_url").style.display = "";
  		document.getElementById("tr_key").style.display = "none";
  		document.getElementById("tr_empty").style.display = "none";  
  		document.getElementById("tr_sysurl").style.display = "none";  	
  		document.getElementById("tr_text").style.display = "none";
  	}else if(v=="sysview"){
  		document.getElementById("tr_text").style.display = "none";
  		document.getElementById("tr_url").style.display = "none";
  		document.getElementById("tr_key").style.display = "none";
  		document.getElementById("tr_empty").style.display = "none";
  		document.getElementById("tr_sysurl").style.display = "";  
  	}
  }
  function menu_display(obj){
  	if(!obj){return;}
  	document.getElementById("aobjsel").innerHTML = "【" + getAttr(obj,"mname") + "】"
  	var o = obj.getElementsByTagName('li');
  	var ihtml = "";
  	if (o.length > 0){
  		ihtml = "该菜单已经有下级菜单，无法设置对应功能！"
  	}else if(obj.attributes["mtype"]){
  		if(getAttr(obj,"mtype") == "click"){
  			ihtml = "执行命令：";
  			switch(getAttr(obj,"mkey"))
				{
					case "在线客服":
						ihtml += "在线客服";
						break;
					default:
				} 			
  		}else{
  			if(getAttr(obj,"murl").indexOf("@dm@") == 0){
  				ihtml = "<div style='float:left;width:120px;'>访问系统网页：</div><div style='float:left;text-align:left;font-size:11pt;width:"+($('#wx_menufun_fun').width() - 130)+"px'><a>" + getSysurlText(getAttr(obj,"murl")) + "</a></div>";
  			}else{
  				ihtml = "<div style='float:left;width:80px;'>访问网页：</div><div style='float:left;text-align:left;font-size:9pt;width:"+($('#wx_menufun_fun').width() - 90)+"px'><a href='" + getAttr(obj,"murl") + "' target='_blank'>" + getAttr(obj,"murl") + "</a></div>"
  			}
  		}
  	}
  	document.getElementById("wx_menufun_fun").innerHTML = ihtml;
  }
  function getSysurlText(idss){
  	var cbdata = $('#wx_menu_sysurl').combobox("getData");
  	for(var n=0;n<cbdata.length;n++){
  		if(cbdata[n].value == idss){
  			return cbdata[n].text;
  			break;
  		}
  	}  	
		return "";
	}
  
  function fnUserGroup(){
	  setMenu(fnGetMenu(-1));
  	
  	$(".usergroup").show();
  	
  }
  function fnGetMenu(id){
  	id = parseInt(id);
  	$("#wx_menu").attr("groupid", id);
  	if(!obj_menu_read){return null}
  	if(id <= -1){
  		return obj_menu_read.menu;
  	}else{
  		if(obj_menu_read.conditionalmenu){
  			for(var n=0;n<obj_menu_read.conditionalmenu.length;n++){
  				if(obj_menu_read.conditionalmenu[n].matchrule.group_id == id){
  					return obj_menu_read.conditionalmenu[n];
  				}  				
  			}  			
  		}  		
  	}
  	return null;	
  }
  function fnChangeMenu(obj){
  	id = parseInt($("#wx_menu").attr("groupid") || "-1");
  	if(id <= -1){
  		obj_menu_read.menu = obj;
  	}else{
  		var ab = false;
  		if(obj_menu_read.conditionalmenu){
  			for(var n=0;n<obj_menu_read.conditionalmenu.length;n++){
  				if(obj_menu_read.conditionalmenu[n].matchrule.group_id == id){
  					ab = true;
  					obj_menu_read.conditionalmenu[n] = obj;
  					break;
  				}  				
  			}  
  			if (ab == false)			{
  				obj_menu_read.conditionalmenu.push(obj);
  			}
  		}  		
  	}
  }
  
  function save(){
	var menu_json = getMenu();
	console.log(menu_json);
	if (menu_json == ""){
		alert("自定义菜单为空，无法保存！");
		return;
	}
	$.post("mymenu/save","menu_json="+menu_json,function(data){
		if(data=="success"){	
			alert("保存成功!");
			fnChangeMenu(eval("(" + menu_json + ")"));	
	 	}
	 	else{alert("保存失败!");return;}
	});
  }
  
  var obj_seleted, ab_first = false, obj_menu_read, group_id, menuid;
</script>

</html>
<script>
	initMenu();
	$(top.hangge());//关闭加载状态
</script>
</html>

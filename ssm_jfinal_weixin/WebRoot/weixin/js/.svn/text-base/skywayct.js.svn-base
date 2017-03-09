function initWxJs(appId,timestamp,noncestr,signature,open_id){
	wx.config({
        debug: false,// 开启调试模式
        appId: appId,
        timestamp: timestamp,
        nonceStr: noncestr,
        signature: signature,
        jsApiList: [ 
	        'checkJsApi',
	        'onMenuShareTimeline',
	        'onMenuShareAppMessage',
	        'onMenuShareQQ',
	        'onMenuShareWeibo',
	        'onMenuShareQZone',
	        'hideMenuItems',
	        'showMenuItems',
	        'hideAllNonBaseMenuItem',
	        'showAllNonBaseMenuItem',
	        'translateVoice',
	        'startRecord',
	        'stopRecord',
	        'onRecordEnd',
	        'playVoice',
	        'pauseVoice',
	        'stopVoice',
	        'uploadVoice',
	        'downloadVoice',
	        'chooseImage',
	        'previewImage',
	        'uploadImage',
	        'downloadImage',
	        'getNetworkType',
	        'openLocation',
	        'getLocation',
	        'hideOptionMenu',
	        'showOptionMenu',
	        'closeWindow',
	        'scanQRCode',
	        'chooseWXPay',
	        'openProductSpecificView',
	        'addCard',
	        'chooseCard',
	        'openCard'
	      ] 
    });
	
	wx.ready(function(){
		var serverurl = 'lldz.leedate.com/skywayct';
		var logourl = 'http://'+serverurl+'/weixin/images/logo.jpg';
		var title = '流量地主';
		wx.hideMenuItems({
		    menuList:['menuItem:copyUrl','menuItem:originPage','menuItem:openWithQQBrowser','menuItem:openWithSafari','menuItem:favorite'] 
		});
		
//		wx.getLocation({
//		    type: 'wgs84', 
//		    success: function (res) {
//		        var x = res.longitude;
//		        var y = res.latitude;
//		        var ggPoint = new BMap.Point(x,y);
//	            var convertor = new BMap.Convertor();
//	            var pointArr = [];
//	            translateCallback = function (data){
//		          if(data.status === 0) {
//		        	localData.set("lat",y);
//			        localData.set("lng",x);
//		            localData.set("bdlat",data.points[0].lat);
//		            localData.set("bdlng",data.points[0].lng);
//		          }
//		        };
//	            pointArr.push(ggPoint);
//	            convertor.translate(pointArr, 1, 5, translateCallback);
//		    }
//		});
		wx.onMenuShareAppMessage({
		    title: title, // 分享标题
		    desc: '推荐分享', // 分享描述
		    link: 'http://'+serverurl+'/weixin/images/'+open_id+"_qr.png", // 分享链接
		    imgUrl: logourl, // 分享图标
		    type: '', 
		    dataUrl: '', 
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		
		wx.onMenuShareTimeline({
		    title: title, // 分享标题
		    link: 'http://'+serverurl+'/weixin/images/'+open_id+"_qr.png", // 分享链接
		    imgUrl: logourl, // 分享图标
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		
		wx.onMenuShareQQ({
		    title: title, // 分享标题
		    desc: '推荐分享', // 分享描述
		    link: 'http://'+serverurl+'/weixin/images/'+open_id+"_qr.png", // 分享链接
		    imgUrl: logourl, // 分享图标
		    success: function () { 
		       // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		       // 用户取消分享后执行的回调函数
		    }
		});
		
		wx.onMenuShareWeibo({
		    title: title, // 分享标题
		    desc: '推荐分享', // 分享描述
		    link: 'http://'+serverurl+'/weixin/images/'+open_id+"_qr.png", // 分享链接
		    imgUrl: logourl, // 分享图标
		    success: function () { 
		       // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		
		wx.onMenuShareQZone({
		    title: title, // 分享标题
		    desc: '推荐分享', // 分享描述
		    link: 'http://'+serverurl+'/weixin/images/'+open_id+"_qr.png", // 分享链接
		    imgUrl: logourl, // 分享图标
		    success: function () { 
		       // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		
		
	});
	
	
	wx.error(function(res){
	    
	});
}

function isMobile(){
	return /AppleWebKit.*Mobile/i.test(navigator.userAgent) || (/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE/.test(navigator.userAgent));	
}
function isWeiXin(){
  var ua = window.navigator.userAgent.toLowerCase();
  if(ua.match(/MicroMessenger/i) == 'micromessenger'){
      return true;
  }else{
      return false;
  }
}	
function ChangeDateToString(DateIn, ai)
{
	var Year      = DateIn.getFullYear();
	var Month     = DateIn.getMonth()+1;
	var Day       = DateIn.getDate();
	if(ai == 2){
		return Year + "-" + (Month < 10 ? "0" : "") + Month ;
	}else if(ai == 3){
		return Year + "第" + (parseInt((Month - 1) / 3) + 1) + "季";
	}else if(ai == 4){
		return Year + "" ;
	}else{
		return Year + "-" + (Month < 10 ? "0" : "") + Month + "-" + (Day < 10 ? "0" : "") + Day ;
	}
}
function getMFirst(ad){
	return new Date(ad.getFullYear() + "-" + (ad.getMonth()+1) + "-01");
}
function getSFirst(ad){
	return new Date(ad.getFullYear() + "-" + (parseInt(ad.getMonth() / 3) * 3 + 1) + "-01");
}
function getYFirst(ad){
	return new Date(ad.getFullYear() + "-01-01");
}
function dayAdd(ad, ai){
	var yesterday_milliseconds=ad.getTime() + ai *1000*60*60*24;      
	var yesterday = ad;      
	yesterday.setTime(yesterday_milliseconds);      
	var strYear=yesterday.getFullYear();   
	var strDay=yesterday.getDate();   
	var strMonth=yesterday.getMonth()+1;  	
	return new Date(strYear+"-"+strMonth+"-"+strDay); 
}
function monthAdd(ad, ai){
	var year_add = parseInt((ai + ad.getMonth()) / 12);
	var month_now = (ai + ad.getMonth()) % 12
	var nad = new Date(year_add + ad.getFullYear(), month_now, ad.getDate())
	if (nad.getMonth() != month_now && (12 + month_now) != nad.getMonth()){
		nad = dayAdd(getMFirst(nad), -1)
	}
	return nad;
}

function left(mainStr,lngLen) { 
 if (lngLen>0) {return mainStr.substring(0,lngLen)} 
 else{return ""} 
 }  

function right(mainStr,lngLen) { 
 if (mainStr.length-lngLen>=0 && mainStr.length>=0 && mainStr.length-lngLen<=mainStr.length) { 
 return mainStr.substring(mainStr.length-lngLen,mainStr.length)} 
 else{return ""} 
 } 
function mid(mainStr,starnum,endnum){ 
 if (mainStr.length>=0){ 
 return mainStr.substr(starnum,endnum) 
 }else{return ""} 
 }




function _attachEvent(obj, evt, func, eventobj) {
	eventobj = !eventobj ? obj : eventobj;
	if(obj.addEventListener) {
		obj.addEventListener(evt, func, false);
	} else if(eventobj.attachEvent) {
		obj.attachEvent('on' + evt, func);
	}
}	
function XMLHttpURL(url){
	var request;
	try {
        request = new XMLHttpRequest();
    } catch (trymicrosoft) {
        try {
            request = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (othermicrosoft) {
            try {
                request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (failed) {
                request = false;
            }
        }
    }
    if (!request) {
        fnPopTip("无法初始化XMLHttpRequest！");
	return "error"
    } else {
        request.open("GET", url, false);
        request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		//request.setRequestHeader("Connection", "Keep-Alive");
	request.send();
	return request.responseText;    
    }
}


var gs_last_url = "", gi_last_url = 0, gs_ori_url = "";
function XMLHttpURL_POST(url, fn){	
	var request;
	try {
        request = new XMLHttpRequest();
    } catch (trymicrosoft) {
        try {
            request = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (othermicrosoft) {
            try {
                request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (failed) {
                request = false;
            }
        }
    }
    if (!request) {
        fnPopTip("无法初始化XMLHttpRequest！");
        fn('{"code":-1,"msg":"无法初始化XMLHttpRequest！"}');
    } else {
			  try {
			    	request.onreadystatechange = function(){
				    	if (request.readyState == 4) {
					        if (request.status == 200) {
					            var aa = request.responseText;
					            request.abort();
					            fn(aa);
					        } else if(request.status == 0){
					        	fnPopTip("您操作得太快了，系统都跟不上你了，请放慢速度再操作一次！");
					        	fn('{"code":-1,"msg":"您操作得太快了，系统都跟不上你了，请放慢速度再操作一次！-'+url+'"}');
										searchEnd();
					        }else{
					        	fnPopTip("错误号（" + request.status + "）：" + url);
					        	if (request.status == 403){
					        		if(gs_last_url == url){
					        			gi_last_url ++;
					        			if (gi_last_url = 5){XMLHttpURL_POST(url, fn);}
					        		}else{
					        			gs_last_url = url;
					        			gi_last_url = 0;
					        		}
					        	}
					        	fnPopTip("错误号（" + request.status + "）：" + url);
					        	fn('{"code":-1,"msg":"错误号（' + request.status + '）：' + url + '"}');
										searchEnd();
					        }
					    }
				    };
			    	
			        request.open("POST", url, true);
			        request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
							request.send(null);
				} catch (e) {
               fnPopTip(e.description);
        }
    }
}
function searchEnd(){
	wait_begin();
}
Request = {
	QueryString : function(item){
		var svalue = location.search.match(new RegExp("[\?\&]" + item + "=([^\&]*)(\&?)","i"));
		svalue = svalue ? svalue[1] : svalue;
		return svalue == "null" || !svalue ? "" : svalue
	}
}
function dstr(d, ab){
	d = d || 0;
	if(ab == undefined){ab = true;}
	var str = d.toString();
	if (str.indexOf(".") == 0 ){str = "0" + str;}
	if (str.indexOf("-.") == 0 ){str = "-0" + str.substring(1, 100);}
	if(str == undefined){str = 0;}
	if (d < 0){
		return '<span style="color:red">' + str + "</span>"
	}else if(d > 0){
		return '<span style="color:blue">' + (ab ? "+" : "") + str + "</span>"
	}else{
		return '<span style="color:blue">' + str + "</span>"
	}

}

function fnClearWxUrl(){	
	if ('pushState' in history) {
		var url = window.location.href;
		gs_ori_url = url;
		url = url.replace("&isappinstalled=1", "");
		url = url.replace("&isappinstalled=0", "");
		url = url.replace("&from=timeline", "");
		url = url.replace("?from=timeline", "");
		url = url.replace("&from=groupmessage", "");
		url = url.replace("?from=groupmessage", "");
		url = url.replace("&from=singlemessage", "");
		url = url.replace("?from=singlemessage", "");
		var stateObj = { foo: "index" };
		history.pushState(stateObj, "", url);
	}		
}



function closeWindow() {
	history.go(-1);    
}

function getClientHeightPub()
{
	return $(window).height();
}
function wait_begin(str){
	if (str){
		var wb = document.getElementById('wait_begin')
		if (wb){wait_begin();}
		var div=document.createElement("div");//创建div对象
		div.id = "wait_begin"	
		if (document.all) {	
			pos = "absolute";
		}else{
			pos = "fixed"	;
		}	
		div.style.position = pos ;div.style.zIndex = popZindex++;div.style.left = "0";div.style.top = "0";
		div.style.width = document.body.scrollWidth +"px";div.style.height = getClientHeightPub() +"px"
		div.style.backgroundColor = "#000";div.style.filter = "alpha(opacity=40)";div.style.opacity = "0.4"
		document.body.appendChild(div);
		if (str != " " ){
			var wbmsg=document.createElement("div");//创建div对象
			wbmsg.id = "wait_begin_msg"		
			wbmsg.style.position = pos ;
			wbmsg.style.textalign = "center";
			wbmsg.style.zIndex = popZindex++;//wbmsg.style.left = (document.documentElement.scrollWidth - 100) / 2 + "px";wbmsg.style.top = (document.documentElement.scrollHeight - 80) / 2 + "px";
			wbmsg.style.width = "auto";wbmsg.style.height = "auto"
			wbmsg.style.backgroundColor = "#ffffff";wbmsg.style.border="2px solid #58A0DE";//div_yzm.style.filter = "alpha(opacity=100)";div_yzm.style.opacity = "1"
			wbmsg.style.padding="15px";			
			wbmsg.innerHTML = str;
			document.body.appendChild(wbmsg);
			wbmsg.style.left = (document.documentElement.clientWidth - wbmsg.scrollWidth) / 2 + "px"
	  	wbmsg.style.top = (document.documentElement.clientHeight - wbmsg.scrollHeight) / 2 + "px"
		}
	}
	else{
		var wb = document.getElementById('wait_begin');
		if (wb){
			var Pnode_response=wb.parentNode;			
			Pnode_response.removeChild(wb);
		}			
		var wbm = document.getElementById('wait_begin_msg');
		if (wbm){
			var Pnode_response=wbm.parentNode;			
			Pnode_response.removeChild(wbm);
		}
	}
}


function roundn(v,en){ 
	return Math.round(v*Math.pow(10,en))/Math.pow(10,en); 
}
function formatPrice(v, n){
	n = n || 1;
	var s = roundn(v,2).toFixed(2);
	if (s.charAt(s.length - 1) == "0"){
		s = roundn(v,n).toFixed(n);
	}
	return s;
}

function mAlert(str, fn, stitle, scolor){
	scolor = scolor || '#' + fnBkColor() + '';
	stitle = stitle || "提示";
	sAlert(str, stitle, fn, scolor)
}
function sAlert(str, stitle, fn, scolor){
	if($("#bgDiv").length > 0){
		$("#bgDiv").remove();
		$("#msgDiv").remove();		
	}
	scolor = scolor || "#1B9AF7";
	stitle = stitle || "提示";
  var msgw,msgh,bordercolor;
  msgw=300;//提示窗口的宽度
  msgh=150;//提示窗口的高度
  bordercolor=scolor;//提示窗口的边框颜色
  titlecolor="#ffffff";//提示窗口的标题颜色
   
  var sWidth,sHeight;
  sWidth=document.documentElement.offsetWidth;
  sHeight=getClientHeightPub();
  //sHeight = document.body.clientHeight;

  var bgObj=document.createElement("div");
  bgObj.setAttribute('id','bgDiv');
  bgObj.style.position="fixed";
  bgObj.style.top="0";
  bgObj.style.background="#000";
  bgObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";
  bgObj.style.opacity="0.6";
  bgObj.style.left="0";
  bgObj.style.width=sWidth + "px";
  bgObj.style.height=sHeight + "px";
  bgObj.style.zIndex = popZindex++;
  document.body.appendChild(bgObj);
  var msgObj=document.createElement("div")
  msgObj.setAttribute("id","msgDiv");
  msgObj.setAttribute("align","left");
  msgObj.style.borderRadius = "5px";
  msgObj.style.position="fixed";
  msgObj.style.background="white";
  msgObj.style.font="16pt 微软雅黑, Verdana, Geneva, Arial, Helvetica, sans-serif";
  msgObj.style.border="0px solid " + bordercolor;
  msgObj.style.width=msgw + "px";
  msgObj.style.height= "auto";
  msgObj.style.left=(sWidth-msgw)/2 + "px";
  msgObj.style.padding="2px";
  msgObj.style.zIndex = popZindex++;
  msgObj.style.color="#444";     
  var title=document.createElement("h4");
  title.setAttribute("id","msgTitle");
  title.setAttribute("align","right");
  title.style.borderTopLeftRadius = "5px";
  title.style.borderTopRightRadius= "5px";
  title.style.margin="0";
  title.style.padding="3px";
  title.style.background="#fff";
  title.style.filter="progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);";
  //title.style.opacity="0.75";
  title.style.borderBottom="1px solid #dcdcdc";
  title.style.height="32px";
  title.style.lineHeight="32px";
  title.style.font="16pt 微软雅黑, Verdana, Geneva, Arial, Helvetica, sans-serif";
  title.style.color="#444";     
  title.innerHTML="<div style='float:left;padding-left:3px;text-align:left'>" + stitle + "</div><div style='float:right;font-size: 30px;line-height: 30px;cursor: pointer;height: 30px;width: 30px;text-align: center;border-radius: 30px;border: 2px solid #fff;margin-top: -1px;' id='alert_close'>×</div>";
  document.body.appendChild(msgObj);
  document.getElementById("msgDiv").appendChild(title);
	document.getElementById("msgTitle").style.lineHeight = "32px";
  document.getElementById("alert_close").onclick=function(){
  	if(fn){fn()}
  	document.body.removeChild(bgObj);
  	document.getElementById("msgDiv").removeChild(title);
  	document.body.removeChild(msgObj);
  }
  
  
  
  var txt=document.createElement("div");
  txt.style.margin="8px 8px 24px 8px"
  txt.setAttribute("id","msgTxt");
  txt.innerHTML=str;
	document.getElementById("msgDiv").appendChild(txt);
	
	
	
  var bn=document.createElement("div");
  bn.style.margin="8px 8px 8px 8px"
  bn.style.background = bordercolor;
  bn.style.color = "#ffffff";
  bn.style.height = "40px";
  bn.style.textAlign = "center";
  bn.style.cursor = "pointer";
  bn.style.font = "18px 微软雅黑, Verdana, Geneva, Arial, Helvetica, sans-serif";
  //bn.style.opacity="0.75";
  bn.style.borderRadius="5px";
  bn.setAttribute("id","msgBtn");
  bn.innerHTML="确定";
  bn.onclick=function(oEvent){	 
  	//先执行完函数再移除，方便带输入框的弹出窗口取值
  	if(fn){fn()}
  	document.body.removeChild(bgObj);
  	document.getElementById("msgDiv").removeChild(title);
  	document.body.removeChild(msgObj);
  }	 
	document.getElementById("msgDiv").appendChild(bn);  	
  document.getElementById("msgBtn").style.lineHeight = "40px";  
	
	
	$("#msgDiv").animate({bottom:(document.documentElement.scrollTop + (sHeight-document.getElementById("msgDiv").offsetHeight)/2) + "px"}, 600);
  //document.getElementById("msgDiv").style.top=(document.documentElement.scrollTop + (sHeight-document.getElementById("msgDiv").offsetHeight)/2) + "px";
}
function isArray(obj) {   
  return Object.prototype.toString.call(obj) === '[object Array]';    
}  
function mConfirm(str, btn, fn, stitle, scolor){
	scolor = scolor || "#" + fnBkColor() + "";
	stitle = stitle || "提示";
	sConfirm(str, btn, fn, stitle, scolor)
}
function sConfirm(str, btn, fn, stitle, scolor){
	if($("#bgDivConfirm").length > 0){
		$("#bgDivConfirm").remove();
		$("#msgDivConfirm").remove();		
	}
	if(!isArray(btn) || !isArray(fn)){return}
	if(btn.length != fn.length){return}
	scolor = scolor || "#1A7FCF";
	stitle = stitle || "提示";
  var msgw,msgh,bordercolor;
  msgw=300;//提示窗口的宽度
  msgh=150;//提示窗口的高度
  bordercolor=scolor;//提示窗口的边框颜色
  titlecolor="#ffffff";//提示窗口的标题颜色
   
  var sWidth,sHeight;
  sWidth=document.documentElement.offsetWidth;
  sHeight=getClientHeightPub();
  //sHeight = document.body.clientHeight;

  var bgObj=document.createElement("div");
  bgObj.setAttribute('id','bgDivConfirm');
  bgObj.style.position="fixed";
  bgObj.style.top="0";
  bgObj.style.background="#000";
  bgObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";
  bgObj.style.opacity="0.6";
  bgObj.style.left="0";
  bgObj.style.width=sWidth + "px";
  bgObj.style.height=sHeight + "px";
  bgObj.style.zIndex = popZindex++;
  document.body.appendChild(bgObj);
  var msgObj=document.createElement("div")
  msgObj.setAttribute("id","msgDivConfirm");
  msgObj.setAttribute("align","left");
  msgObj.style.borderRadius = "5px";
  msgObj.style.position="fixed";
  msgObj.style.background="white";
  msgObj.style.font="16pt 微软雅黑, Verdana, Geneva, Arial, Helvetica, sans-serif";
  msgObj.style.border="0px solid " + bordercolor;
  msgObj.style.width=msgw + "px";
  msgObj.style.height= "auto";
  msgObj.style.left=(sWidth-msgw)/2 + "px";
  msgObj.style.padding="2px";
  msgObj.style.zIndex = popZindex++;
  msgObj.style.color="#444";     
  var title=document.createElement("h4");
  title.setAttribute("id","msgTitleConfirm");
  title.setAttribute("align","right");
  title.style.borderTopLeftRadius = "5px";
  title.style.borderTopRightRadius= "5px";
  title.style.margin="0";
  title.style.padding="3px";
  title.style.background="#fff";
  title.style.filter="progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);";
  //title.style.opacity="0.75";
  title.style.borderBottom="1px solid #dcdcdc";
  title.style.height="32px";
  title.style.font="16pt 微软雅黑, Verdana, Geneva, Arial, Helvetica, sans-serif";
  title.style.color="#444";     
  title.innerHTML="<div style='float:left;padding-left:3px;text-align:left'>" + stitle + "</div>";
  document.body.appendChild(msgObj);
  document.getElementById("msgDivConfirm").appendChild(title);
	document.getElementById("msgTitleConfirm").style.lineHeight = "32px";
  var txt=document.createElement("div");
  txt.style.margin="8px 8px 24px 8px";
  txt.setAttribute("id","msgTxtConfirm");
  txt.innerHTML=str;
	document.getElementById("msgDivConfirm").appendChild(txt);
  
  for(i = 0; i < btn.length; i++){
  	var bn=document.createElement("div");
	  bn.style.margin="8px 8px 8px 8px"
	  bn.style.background = bordercolor;
	  bn.style.color = "#ffffff";
	  bn.style.height = "40px";
	  bn.style.textAlign = "center";
	  bn.style.cursor = "pointer";
	  bn.style.font = "1rem 微软雅黑, Verdana, Geneva, Arial, Helvetica, sans-serif";
	  //bn.style.opacity="0.75";
  	bn.style.borderRadius="5px";
	  bn.setAttribute("id","msgBtnConfirm" + i);
	  bn.innerHTML=btn[i];
	  bn.onclick=function(oEvent){
	  	var oEvent = oEvent ? oEvent : window.event;
	  	var oElem = oEvent.toElement ? oEvent.toElement : oEvent.relatedTarget; // 此做法是为了兼容FF浏览器
        document.body.removeChild(bgObj);
	  	document.getElementById("msgDivConfirm").removeChild(title);
	  	document.body.removeChild(msgObj);
	  	var jj = oElem.id.substring(oElem.id.length-1,oElem.id.length);
	  	if(fn[jj]){fn[jj]()}
	  }
	  document.getElementById("msgDivConfirm").appendChild(bn);  	
	  document.getElementById("msgBtnConfirm" + i).style.lineHeight = "40px";
  }
  $("#msgDivConfirm").animate({bottom:(document.documentElement.scrollTop + (sHeight-document.getElementById("msgDivConfirm").offsetHeight)/2) + "px"}, 600);
  //document.getElementById("msgDivConfirm").style.top=(document.documentElement.scrollTop + (sHeight-document.getElementById("msgDivConfirm").offsetHeight)/2) + "px";
}
//阻止事件冒泡
//function stopEventBubble(event){
//    var e=event || window.event;
//
//    if (e && e.stopPropagation){
//        e.stopPropagation();    
//    }
//    else{
//        e.cancelBubble=true;
//    }
//}
//设置cookie
function setcookie(name,value){
	var Days=10000;
	var exp=new Date();
	exp.setTime(exp.getTime()+Days*24*60*60*1000);
	document.cookie=name+"="+escape(value)+";expires="+exp.toGMTString()+';path=/';
}	
//获取cookie
function getcookie(con){
	var arr=document.cookie.match(new RegExp("(^|)"+con+"=([^;]*)(;|$)"))
	if(arr!=null){
		return unescape(arr[2])
	}
	else {return ""}
}

//删除用户名
function delecookie(name){
	var exp=new Date();
	exp.setTime(exp.getTime()-100000);
	document.cookie=name+"=v;expires="+exp.toGMTString()+';path=/';
}


function replaceall(str, af, at){
	return str.replace(new RegExp(af,"gm"), at);
}
function parseFloatN(str){
	str = replaceall(str, ",", "")
	str = replaceall(str, " ", "")
	str = replaceall(str, "￥", "")
	return parseFloat(str)
}


//判断浏览器类型
	function getwebType(){
			  
			var Sys={}
			var ua=navigator.userAgent.toLowerCase();
			var rMsie = /(msie\s|trident.*rv:)([\w.]+)/;
			
			if(ua.search(rMsie)!=-1){
				  if(ua.match(/msie ([\d.]+)/) !=null)
				  	Sys.ie=ua.match(/msie ([\d.]+)/)[1]
				  else
				  	Sys.ie =ua.match(/trident\/([\d.]+)/)[1]
			}
			else if(ua.indexOf("maxthon") >=1)
					Sys.maxthon=ua.match(/maxthon\/([\d.]+)/)[1]
			else if(ua.indexOf("firefox")>=1)
					Sys.fireFox=ua.match(/firefox\/([\d.]+)/)[1]
			else if(window.opera)
					Sys.opera=ua.match(/opera\/([\d.]+)/)[1]
			else if(ua.indexOf("safari") >=1 && ua.indexOf("chrome") < 1)
					Sys.safari=ua.match(/version\/([\d.]+)/)[1];
			else if(ua.indexOf("mozilla") >=0)
					Sys.mozilla=ua.match(/mozilla\/([\d.]+)/)[1]
			else if(window.MessageEvent && !document.getBoxObjectFor)
					Sys.chrome=ua.match(/chrome\/([\d.]+)/)[1]
			
			return Sys
	}

//本地数据存储
     localData = {
         hname:location.hostname?location.hostname:'localStatus',
         isLocalStorage:window.localStorage?true:false,
         dataDom:null,
 
         initDom:function(){ //初始化userData
             if(!this.dataDom){
                 try{
                     this.dataDom = document.createElement('input');//这里使用hidden的input元素
                     this.dataDom.type = 'hidden';
                     this.dataDom.style.display = "none";
                     this.dataDom.addBehavior('#default#userData');//这是userData的语法
                     document.body.appendChild(this.dataDom);
                     var exDate = new Date();
                     exDate = exDate.getDate()+30;
                     this.dataDom.expires = exDate.toUTCString();//设定过期时间
                 }catch(err){
                     return false;
                 }finally{}
             }
             return true;
         },
         set:function(key,value){
         	if(getwebType().ie!=6 || getwebType().opera!=undefined){
             if(this.isLocalStorage){
                 window.localStorage.setItem(key,value);
             }else{
                 if(this.initDom()){
                     this.dataDom.load(this.hname);
                     this.dataDom.setAttribute(key,value);
                     this.dataDom.save(this.hname)
                 }
             }}
           else{
           		setcookie(key,value)
           	}
         },
         get:function(key){
          if(getwebType().ie!=6 || getwebType().opera!=undefined){
             if(this.isLocalStorage){
                 return window.localStorage.getItem(key);
                 
             }else{
                 if(this.initDom()){
                     this.dataDom.load(this.hname);
                     return this.dataDom.getAttribute(key);
                     
                 }
             }}
           else{
           		return getcookie(key)
           	}
         },
         remove:function(key){
         	if(getwebType().ie!=6 || getwebType().opera!=undefined){
             if(this.isLocalStorage){
                 localStorage.removeItem(key);
             }else{
                 if(this.initDom()){
                     this.dataDom.load(this.hname);
                     this.dataDom.removeAttribute(key);
                     this.dataDom.save(this.hname)
                 }
             }}
           else{
           		delecookie(key)
           	}
         }
     }
     
     
var url=window.location.href;


var PageMAX = 10;

function fnBkColor(){
	return "1B9AF7";
}

var spPerPage, spPerRow;

function fnGetSpPerRow(){
	return spPerRow || fnGetSpListN((document.getElementById("content") || document.getElementById("goodcontent")).clientWidth);
}

function fnGetSpPerPage(){
	return Math.round(10 / fnGetSpPerRow(), 0) * fnGetSpPerRow();
}


function fnGetSpListN(cwidth){
	return cwidth <= 600 ? 2 : (Math.floor(cwidth/300) + 1);
}
function fnGetSpW(cwidth){
	var perN = fnGetSpListN(cwidth);	
	return cwidth / perN - 32;
}
     
function goBack(){  
	if(navigator.userAgent.indexOf("iPhone") >0) 
		window.history.go( -1 ); //window.location.href = "wxuser.html?open_id ="+open_id;
	else
	{
		if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){ // IE  
	        if(history.length > 0){  
	            window.history.go( -1 );  
	        }else{  
	            window.opener=null;
	            window.close();  
	        }  
	    }else{ //非IE浏览器  
	           if (navigator.userAgent.indexOf('Firefox') >= 0 ||  
	            navigator.userAgent.indexOf('Opera') >= 0 ||  
	            navigator.userAgent.indexOf('Safari') >= 0 ||  
	            navigator.userAgent.indexOf('Chrome') >= 0 ||  
	            navigator.userAgent.indexOf('WebKit') >= 0){  
	        	if(history.length > 0){   		
	            	window.history.go( -1 );  
	            }else{  
	            	window.opener=null;
	            	window.open("","_self"); 
	            	window.close();  
	            }  
	        }else{ //未知的浏览器  
	            window.history.go( -1 );  
	        }  
	    } 
	}	
     
}  
function gotoTop(){
	var obj = document.body.scrollTop = 0;
}
	

function checkMobile(sjh, oid){
	if (sjh == ""){return 1;}
	if (sjh.length != 11){
		mAlert("请输入有效的手机号码！", function(){document.getElementById(oid).focus()});		
		return -1;
	}
	var r = sjh.match(/13[0-9]{9}|14[57][0-9]{8}|15[0-35-9][0-9]{8}|170[09][0-9]{7}|18[012356789][0-9]{8}/g);
	if ((r == null ? 0 : r.length) == 0){
		mAlert("请输入有效的手机号码！", function(){document.getElementById(oid).focus()});
		return -1;
	}
	return 1;
}



var gi_popTip = 0;
function fnPopTip(as, bok, atime){
	if (gi_popTip > 0){
		clearTimeout(gi_popTip);		
	}
	if($(".popTip").length > 0){$(".popTip").remove();}
	if (!as){return;}
	var dpl=document.createElement("div");
	dpl.className = "popTip";	
	dpl.style.zIndex = popTipZindex++;
	if(!bok)
		dpl.innerHTML = '<div style="text-align:center;"><i class="icon-circle-' + (bok ? 'check' : 'cross') + '" style="display: inline-block;height: 40px;font-size: 32px;"></i></div>' + as;
	else
		dpl.innerHTML = '<div style="text-align:center;"></div>' + as;
	document.body.appendChild(dpl);	
	$(".popTip").bind("click", function(){fnPopTip()}).show();
	$(".popTip").css("left", ($(window).width() - $(".popTip").outerWidth()) / 2 + "px");
	$(".popTip").css("top", ($(window).height() - $(".popTip").outerHeight()) / 2 + "px");
	var t;
	$(".popTip").animate({
    opacity:'1'
  }, 800, t, function(){
  	gi_popTip = setTimeout(function(){ 
  		
  		gi_popTip = 0;	
  		$(".popTip").animate({opacity:'0'}, 800, t, function(){$(".popTip").remove();});
  	}, atime || 1100); 
  	 	
  });
}
function fnPopMenu(shtml, obj){
	var t;
	if($(".popMenu").attr("poping") == "1"){return;}
	$(".popMenu").attr("poping", "1");
	if(shtml){
		var dpl=document.createElement("div");
		dpl.className = "popMenu";	
		dpl.style.zIndex = popZindex++;
		dpl.innerHTML = shtml;
		document.body.appendChild(dpl);			
		if(obj){
			$(".popMenu").css("right", ($(document).width() - $(obj).offset().left - $(obj).outerWidth() + 4) + "px").css("top", ($(obj).position().top + $(obj).outerHeight() > $(window).height() ? $(obj).position().top - $(".popMenu").outerHeight() : $(obj).position().top + $(obj).outerHeight()) + "px");			
		}
		if($(".popMenu").is(':hidden') == true){$(".popMenu").toggle(500, function(){$(".popMenu").attr("poping", "");$(".popMenu").focus()});}else{$(".popMenu").attr("poping", "");}
		$(".popMenu").attr("tabindex", "0").bind("blur", function(){fnPopMenu()});
	}else{
		if($(".popMenu").is(':hidden') == false){$(".popMenu").toggle(500, function(){$(".popMenu").attr("poping", "");$(".popMenu").remove()});}else{$(".popMenu").attr("poping", "");$(".popMenu").remove();}		
	}
	
}
function fnCartNumChange(obj){
	if (obj.id == "cartNum"){
		var objTo = "cartNumPop";
	}else{
		var objTo = "cartNum";
	}
	if(document.getElementById(objTo)){
		$("#" + objTo).val(obj.value);
	}
}


function fnEn13Barcode(as_bm){
	var ls_bm = "";
	var li_1 = 0, li_2 = 0;
	ls_bm = left(as_bm, 12);	
	if (isNaN(ls_bm)){ls_bm = '0';}	
	for(var li_i=1; li_i <= ls_bm.length; li_i++){
		if(li_i%2==0){
			li_2 += parseInt(mid(ls_bm, li_i - 1, 1));
		}else{
			li_1 += parseInt(mid(ls_bm, li_i - 1, 1));
		}
	}	
	li_i = li_2 * 3 + li_1;
	li_i = (10 - li_i%10) % 10;	
	ls_bm = ls_bm + left('000000000000', 12 - ls_bm.length) + li_i;	
	return ls_bm;	
}

function fnLoading1(obj, append, oheight){
	if(obj){
		var shtml = '<div id="loadBk"><div class="sk-circle"><div class="sk-circle1 sk-child"><div></div></div><div class="sk-circle2 sk-child"><div></div></div><div class="sk-circle3 sk-child"><div></div></div><div class="sk-circle4 sk-child"><div></div></div><div class="sk-circle5 sk-child"><div></div></div><div class="sk-circle6 sk-child"><div></div></div><div class="sk-circle7 sk-child"><div></div></div><div class="sk-circle8 sk-child"><div></div></div><div class="sk-circle9 sk-child"><div></div></div><div class="sk-circle10 sk-child"><div></div></div><div class="sk-circle11 sk-child"><div></div></div><div class="sk-circle12 sk-child"><div></div></div></div></div>';
		if(append){
			$(obj).append(shtml);
			$("#loadBk").css("height", "auto");
		}else{
			$(obj).html(shtml);
			if (oheight > 0){$("#loadBk").height(oheight)}
			$("#loadBk .sk-circle").css("marginTop", (Math.max(oheight || $(obj).height(), 120) / 2 - 40) + "px");
		}
		
		$("#loadBk").show();
	}else{
		$("#loadBk").remove();
	}
}


var popN = 0, popZindex = 1000000, popTipZindex = 2000000;
function fnPopWin(tit, shtml, pos, titCol, btn2htm, fnBtn2,fnclose,notoolbar,id){
	popN++;
	tit = tit || "";
	shtml = shtml || "";
	btn2htm = btn2htm || "";
	pos = pos || "t2b";
	titCol = titCol == "red" ? "redBK" : "";
	var dpl=document.createElement("div");
	dpl.className = "popWin";
	dpl.style.zIndex = popZindex++;
	if(!id)
		dpl.id = "popWin" + (popN);
	else
		dpl.id = "popWin" + id;
	if(notoolbar) 
		notoolbar ='<div class="fl"></div>';
	else
		notoolbar = '<div class="fl" onclick="fnClosePopWin(this,'+fnclose+')"><i class="icon-chevron-thin-left"></i></div>';
	dpl.innerHTML = '<div class="popWinSub ' + pos + '"><div class="popWinHd ' + titCol + '" style="display:'+(tit =="" ? "none;" : "block;")+'">'+notoolbar+'<b>' + tit + '</b><div class="fr">' + btn2htm + '</div></div><div class="popWinContent">' + shtml + '</div></div>';
	document.body.appendChild(dpl);
	
	if (fnBtn2){$("#popWin" + popN).find(".popWinHd .fr").bind("click", fnBtn2);}
		
	$(".popWinContent").height($(window).height() - 50);
	
	$(".popWin").show();
	
	var tclose;
	if (pos == "l2r" || pos == "r2l"){
		tclose = {width:"100%"};
	}else if (pos == "t2b" || pos == "b2t"){
		tclose = {height:"100%"};
	}else{
		tclose = {width:"100%", height:"100%"};
	}
	$("#popWin" + popN).find(".popWinSub").animate(tclose, 300);
	return $("#popWin" + popN);
}
function fnClosePopWin(obj,fnclose){
	var t;
	var obj_pSub = $(obj).parents(".popWinSub");
	var obj_p = $(obj_pSub).parents(".popWin");
	if (obj_p.length == 0){
		obj_p = obj;
		obj_pSub = obj_p.find(".popWinSub");
	}
	var tclose;
	if (obj_pSub.hasClass("l2r") || obj_pSub.hasClass("r2l")){
		tclose = {width:"0"};
	}else if (obj_pSub.hasClass("b2t") || obj_pSub.hasClass("t2b")){
		tclose = {height:"0"};
	}else{
		tclose = {width:"0",height:"0"};
	}
	obj_pSub.animate(tclose, 300, t, function(){obj_p.remove();popN--;});
	paytime = 60;
//	clearInterval(oTimer);
	if(fnclose)fnclose();
}

function fnPopDlg(tit, shtml, imgView, fnClose){
	popN++;
	var dpl=document.createElement("div");
	dpl.className = "popDlg";	
	dpl.style.zIndex = popZindex++;
	dpl.id = "popDlg" + popN;
	dpl.innerHTML = '<div class="popDlgContent"><div class="tit">' + tit + '<i class="icon-circle-cross fr f28"></i></div><div class="p12 posre" style="overflow:auto;">' + shtml + '</div></div>'
	document.body.appendChild(dpl);	
	$(".popDlgContent").find(".icon-circle-cross").bind("click", function(){
		fnClosePopDlg(this);
		if(fnClose){fnClose()}
	})
	if(imgView){
		$(".popDlgContent").css("width", "98%").css("height", "100%").css("margin-top", "1%") //.css("position","fixed");
	}
	//$(".popDlgContent").css("left", ($(window).width() - $(".popDlgContent").outerWidth()) / 2 + "px");
	//$(".popDlgContent").css("marginTop", ($(window).height() - $(".popDlgContent").outerHeight()) / 2 + "px");
	var t;
	$(".popDlg").animate({height:"100%"}, 300, t, function(){});
	
	return $("#popDlg" + popN);
}
function fnClosePopDlg(obj){
	var t;
	var obj_p = $(obj).parents(".popDlg");
	if (obj_p.length == 0){obj_p = obj}
	obj_p.animate({height:"0"}, 300, t, function(){obj_p.remove()});
}


/**
* 将json字符串转换为对象的方法。
* @public
* @param json字符串
* @return 返回object,array,string等对象
**/
jQuery.extend({ 
   evalJSON: function(strJson) { 
   	 if(strJson=="" || strJson=="验证失败，无法执行本次操作！"){return ""}
     return eval("("+ strJson +")"); 
   } 
}); 


/**
* 将javascript数据类型转换为json字符串的方法。
* @public
* @param  {object}  需转换为json字符串的对象, 一般为Json 【支持object,array,string,function,number,boolean,regexp *】
* @return 返回json字符串
**/
jQuery.extend({ 
   toJSONString: function(object) { 
   	 var type 
   	 if(object!=null){
      type=typeof object; 
     if ('object'== type) { 
       if (Array == object.constructor){type ='array';}
       else if (RegExp == object.constructor) {type ='regexp';}
       else {type ='object';}
			}
		 }else{type='unknown'}
     switch (type) { 
     case'undefined': 
     case'unknown': 
       //alert_ui("Unknown type")
       //return; 
       return "";
       break; 
     case'function': 
     case'boolean': 
     case'regexp': 
       return object.toString(); 
       break; 
     case'number': 
       return isFinite(object) ? object.toString() : 'null'; 
       break; 
     case'string': 
       return'"'+ object.replace(/(\\|\")/g, "\\$1").replace(/\n|\r|\t/g, function() { 
         var a = arguments[0]; 
         return (a =='\n') ?'\\n': (a =='\r') ?'\\r': (a =='\t') ?'\\t': "" 
       }) +'"'; 
       break; 
     case'object': 
       var results = []; 
       for (var property in object) { 
       	 var value
       	 if(object[property]!==null){
       	 		value = jQuery.toJSONString(object[property]); 
       	 	}else{value='""';}
         if (value !== null){
         	 results.push(jQuery.toJSONString(property) +':'+ value); 
         	}
       } 
       return'{'+ results.join(',') +'}'; 
       break; 
     case'array': 
       var results = []; 
       for (var i =0; i < object.length; i++) { 
         var value = jQuery.toJSONString(object[i]); 
         if (value !== undefined) results.push(value); 
       } 
       return'['+ results.join(',') +']'; 
       break; 
     } 
   } 
});

function fnGetSpTag(is_cx, je){	
	return ((is_cx == 7 || is_cx == 2 || is_cx == 160 || is_cx == 161 || is_cx == 162) ? '<div class="carttip">' + (is_cx == 7 || je == 0 ? (je == 0 ? '赠品' : '买赠') : (is_cx == 2 ? '换购' : (is_cx == 160 ? '会限' : (is_cx == 162 ? '砍价':'限量')))) + '</div>' : '')
}

function log(msg){
	if (window["console"]){
		console.log(msg);
	}
}



function fnPubSize(){
	$(".spflContent").height($(window).height() - 50);
	$(".csContent").height($(window).height() - 50 - $(".csContent").attr("hei"));
	$(".mdContent").height($(window).height() - 50);
	$(".txsqContent").height($(window).height() - 50);
	$(".searchContent").height($(window).height() - 101);
	$(".popWinContent").height($(window).height() - 50);
	$(".cartBillContent").height($(window).height() - 50);
	$(".filterContent").height($(window).height() - 50);	
	$("#gooddes").css("min-height", ($(window).height() - 102) + "px");
	$("#goodattr").css("min-height", ($(window).height() - 102) + "px");
	$(".cuthelp").css("min-height", ($(window).height() - 102) + "px");
	
	//$("#goodcontent").height($(window).height() - 50);
}

function fnloading(msg){
	var loading = '<div id="indexbk"><div class="sk-circle"><div class="sk-circle1 sk-child"><div></div></div><div class="sk-circle2 sk-child"><div></div></div><div class="sk-circle3 sk-child"><div></div></div><div class="sk-circle4 sk-child"><div></div></div><div class="sk-circle5 sk-child"><div></div></div><div class="sk-circle6 sk-child"><div></div></div><div class="sk-circle7 sk-child"><div></div></div><div class="sk-circle8 sk-child"><div></div></div><div class="sk-circle9 sk-child"><div></div></div><div class="sk-circle10 sk-child"><div></div></div><div class="sk-circle11 sk-child"><div></div></div><div class="sk-circle12 sk-child"><div></div></div></div><span id="indexTip"></span></div>';
	if($('#indexbk')[0]==undefined)
	{
		$('body').append(loading);
	}	
	if(msg)
		$('#indexbk').show();
	else
	{	
		$('#indexbk').hide();
		$('#indexbk').remove();
	}
	$('#indexTip').text("");
}


//发起支付请求，调用微信支付
var Flag = true;
function callpay()
{ 
	if(Flag){
		Flag = false;
		WeixinJSBridge.invoke('getBrandWCPayRequest',	
		{
			"appId": localData.get("appid"),
			"timeStamp": localData.get("timeStamp"),
			"nonceStr":  localData.get("nonceStr"),
			"package" :  localData.get("package"),
			"signType" : "MD5",
			"paySign" : localData.get("paySign")
		},function(res){
			WeixinJSBridge.log(res.err_msg);
			if(res.err_msg=="get_brand_wcpay_request:ok"){ //支付成功！改变订单状态,腾讯解释该返回值不一定可靠，还要用订单查询接口查询订单真实支付状态
				Flag = true
				fnPopTip("支付成功！"," ");
				fnClosePopWin(LlczMx_n_popwin,clearLlczMx);
				fnLlczMx(1,open_id);
			}else if(res.err_msg=="get_brand_wcpay_request:cancel"){
				//买家主动取消支付	
				Flag = true
			}else if(res.err_msg=="system:access_denied"){
				//不在测试白名单	
				Flag = true
				mAlert("微信支付处于测试阶段，您的个人微信号尚未添加到微信公众号的测试白名单中，无法进行支付！");
			}else if(res.err_msg=="get_brand_wcpay_request:fail"){//支付授权目录问题
				Flag = true
				//请检查微信配置的支付授权目录是否正确或者微信号是否添加到了支付测试白名单,或者订单超过了支付时间（prepay_id最长有效时间2小时）
				mAlert("申请支付失败！ ");
			}
		});
	}
};


//获取服务器返回内容
function XMLHttpURL_p(url, ab){
	var result
	$.ajax({
			type:'GET',
			url:url,
			cache:false,
			async:false,
			error:function(XMLHttpRequest,textStatus,ErrorThrown){
				if(XMLHttpRequest.status==404){
						if (ab) {alert("连接服务器失败！检查网络连接！")}
						return false
				 }
				else if(XMLHttpRequest.status==500){
						if (ab) {alert("服务器错误！")}
					  	return false
					}
				else if(XMLHttpRequest.status==12029){alert("网络不通！");return false}
				},
			success:function(data,textStatus){
				 if(data.indexOf("闲置时间过长")>=0){alert("闲置时间过长，自动退出商城！",function(){window.close()})}
				 if(data!=-11 && data!="验证失败，无法执行本次操作！"){result=data}
				 else{
				 		result=-11
				 	}
				}
	});
	return result
}

function fnChangeCzkPsw(abHavePsw){
	var obj = fnPopDlg("支付密码" + (abHavePsw ? "修改" : "设置"), (abHavePsw ? '<input type="password" class="mb12" placeholder="请录入旧的支付密码" id="czkNewPsw0"></input>' : '') + '<input type="password" class="mb12" placeholder="请设置新的支付密码" id="czkNewPsw1"></input><input class="mb12" type="password" placeholder="请再次确认新支付密码" id="czkNewPsw2"></input><div style="text-align:center"><span class="redbtn mt12" style="width:60%">确定</span></div>')
	$("#czkNewPsw" + (abHavePsw ? "0": "1")).focus();	
	obj.find(".redbtn").bind("click", function(){
			var p0 = $("#czkNewPsw0").val() || '';
			var p1 = $("#czkNewPsw1").val() || '';
			var p2 = $("#czkNewPsw2").val() || '';
			if (p1 != p2 ){
				mAlert("两次录入的新密码不一致，请重新录入！", function(){$("#czkNewPsw1").focus();});
				return;
			}
			if(!p1){
				mAlert("新密码不能为空！", function(){$("#czkNewPsw1").focus();});
				return;
			}
			if(!p2){
				mAlert("确认密码不能为空！", function(){$("#czkNewPsw2").focus();});
				return;
			}
			XMLHttpURL_POST("../wxclient?do=pas&o=" + $.md5(p0) + "&n=" + $.md5(p1), function(data){
				var objs = eval("(" + data + ")");
				if (objs.code == -1){
					return mAlert(objs.msg);
				}else{	
					if(objs.success){
						mAlert("支付密码" + (abHavePsw ? "修改" : "设置") + "成功！", function(){fnClosePopDlg(obj);$('#byspan2').unbind().bind("click",function(){fnChangeCzkPsw(true);});});
					}else{
						mAlert(objs.fail);
					}
				}				
			});
	});
}

$(document).ready(function(){
	document.body.addEventListener('touchstart', function(){});
});

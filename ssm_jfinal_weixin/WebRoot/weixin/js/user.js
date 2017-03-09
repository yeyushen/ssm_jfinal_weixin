var open_id,notice_content;
function loaded() {
	if(Request.QueryString("code"))
	{	
		fnloading("正在读取数据...");		
		XMLHttpURL_POST("../wxclient?code="+Request.QueryString("code")+"&url="+window.location, function(data){
			fnloading();
			var objdata = eval("(" + data + ")");
			if(objdata.code == "-1"){
				mAlert(objdata.msg);
				return;
			}else{
				getuserInfo();
				initWxJs(objdata.appid,objdata.timestamp,objdata.noncestr,objdata.signature,objdata.open_id); //微信也是ajax异步加载
			}
		});
	}
	else{
		fnPopTip("请通过微信公众号打开页面！"," ");
	}
}
document.addEventListener('DOMContentLoaded', loaded, false);


function getuserInfo(){
	setTimeout(function(){
		var objs = eval("(" + XMLHttpURL("../wxclient") + ")"); 
		if(objs.code){mAlert(objs.msg);}
		
		if(objs.photo){
    		$(".wxheadFr").css("background-image", "url(" + objs.photo+ ")");
    		$(".wxheadFr i").removeClass("icon-profile-female").removeClass("icon-profile-male");
    	}
		
		localData.set("qrcode", objs.qrcode);
    	$("#hyxm").text("昵称："+(objs.nickname == undefined ? " ":objs.nickname) || "匿名");
    	
    	//$("#wxkh").html("<span class='wxkhjs'>一级会员 "+(objs.lv1||0)+"</span><br><span class='wxkhjs'>二级会员 "+(objs.lv2||0)+"</span><br><span class='wxkhjs'>三级会员 "+(objs.lv3||0)+"</span>");
    	$('#skyye').html('<num id="amount">' + formatPrice((objs.amount||0),2) + '</num><br>我的钱包');
    	$("#wxkjf").html('<num>' + (objs.points||0) + '</num><br>微信积分');
    	$('#byspan1').html('<num class="icon-calendar"></num><div style="color:blue;">签到</div>');
    	if(objs.sign == "1") //已签到
    	{
    		$('#byspan1 div').css("color","gray");
    		$('#byspan1 num').css("color","gray");
    	}
    	else{
    		$('#byspan1').unbind().bind("click",function(){fnSign((objs.points||0));});
    	}
    		
        $('#byspan2').html('<num class="icon-keyboard"></num><div>钱包密码</div>');
        if(objs.pas == "1") 
        {
        	fnChangeCzkPsw();
        	$('#byspan2').unbind().bind("click",function(){fnChangeCzkPsw();});
        }
        else
        	$('#byspan2').unbind().bind("click",function(){fnChangeCzkPsw(true);});
        $('#skytel').attr("href","tel:02029016991");
		
        fnloading("");
    },500);
}

function fnEditInfo(){
	setTimeout(function(){
        subN ++;
        var dpl=document.createElement("div");
        dpl.className = "userinfoDivB userinfoDetail";
        popZindex++;
        dpl.style.zIndex = popZindex++;
        dpl.innerHTML = '<div class="userinfoHd"><div class="fl" onclick="fnCloseEditInfo()">取消</div><b id="userinfoDtitle' + subN + '">个人资料</b><div class="fr" id="userinfoSave" onclick="fnUserinfoSave(this)">提交</div></div><div class="userinfoContent"><div class="userinfoSubItem" id="userinfoSubItem' + subN +'"></div></div>';
        
        if(!document.getElementById("userinfoDiv")){
            var dpls=document.createElement("div");
            dpls.className = "userinfoDiv";
            dpls.style.zIndex = popZindex - 2;
            dpls.innerHTML = '<div id="userinfoDiv"></div>';
            document.body.appendChild(dpls);
            $(".userinfoDiv").show();
            $('#content').hide();
            $("#userinfoDiv").css("width","100%").css("height","100%");
        }

        document.getElementById("userinfoDiv").appendChild(dpl);
        $(".userinfoContent").height($(window).height() - 50);
        
        fnloading("读取个人资料...");
        XMLHttpURL_POST("../wxclient?do=cx", function(data){
        	fnloading();
        	var objdata = eval("(" + data + ")");
        	var userinfohtml =  '<div class="userinfoField" ><input id="name" placeHolder="姓名" value="'+(objdata.name||"")+'"/></div>'
  			  + '<div class="userinfoField">'
  			  + '	<input id="phone" placeHolder="手机号码" onkeyup="clearNonum(this)" onpaste="clearNonum(this)" value="'+(objdata.phone||"")+'"/>'
  			  + '</div><div>'
  			  + '<div class="userinfoField">银行转账提现信息</div>'
  			  + '<div class="userinfoField"><input id="bank_no" placeHolder="银行卡号" onkeyup="clearNonum(this)" onpaste="clearNonum(this)" value="'+(objdata.bank_no||"")+'"/></div>'
  			  + '<div class="userinfoField"><input id="bank_user" placeHolder="收款人姓名" value="'+(objdata.bank_user||"")+'"/></div>'
  			  + '<div class="userinfoField"><input id="bank_name" placeHolder="银行名称" value="'+(objdata.bank_name||"")+'"/></div>'
  			  + '<div class="userinfoField"><input id="bank_addr" placeHolder="开户行地址" value="'+(objdata.bank_addr||"")+'"/></div>'
  			  + '</div>';
			  $("#userinfoSubItem" + subN).append(userinfohtml);
			  if($(".userinfoDiv").is(":hidden")){
			      $(".userinfoDiv").show();
			      $('#content').hide();
			      $("#userinfoDiv").css("width","100%").css("height","100%");
			  }
        })
        
        
    },500);
}

function fnCloseEditInfo(){
	$("#userinfoDiv").animate({width:"0", height:"0"}, 300, null, function(){$(".userinfoDiv").toggle();});
	$('#content').show();
}

function fnUserinfoSave(){
	fnloading("保存修改...");
	var jsonstr = "&name="+$("#name").val()+"&phone="+$("#phone").val()+"&bank_name="+$('#bank_name').val()+"&bank_user="+$('#bank_user').val()+"&bank_addr="+$('#bank_addr').val()+"&bank_no=" +$('#bank_no').val();
	XMLHttpURL_POST("../wxclient?do=sv"+jsonstr, function(data){
		fnloading();
		var objdata = eval("(" + data + ")");
		if(objdata.code == "-1"){
			mAlert(objdata.msg);
			return;
		}else{
			mAlert("修改成功！",function(){fnCloseEditInfo();});
		}
	});
}


var subN = 0;
function fnCreateTxsq(){
    setTimeout(function(){
        subN ++;
        var dpl=document.createElement("div");
        dpl.className = "txsqDivB txsqDetail";
        popZindex++;
        dpl.style.zIndex = popZindex++;
        dpl.innerHTML = '<div class="txsqHd"><div class="fl" onclick="fnCloseTxsq()">取消</div><b id="txsqDtitle' + subN + '">提现申请</b><div class="fr" id="txsqSave" onclick="fnTxsqSave(this)">提交</div></div><div class="txsqContent"><div class="txsqSubItem" id="txsqSubItem' + subN +'"></div></div>';
        
        if(!document.getElementById("txsqDiv")){
            var dpls=document.createElement("div");
            dpls.className = "txsqDiv";
            dpls.style.zIndex = popZindex - 2;
            dpls.innerHTML = '<div id="txsqDiv"></div>';
            document.body.appendChild(dpls);
            $(".txsqDiv").show();
            $('#content').hide();
            $("#txsqDiv").css("width","100%").css("height","100%");
        }

        document.getElementById("txsqDiv").appendChild(dpl);
        $(".txsqContent").height($(window).height() - 50);
        fnloading("加载个人资料...");
        XMLHttpURL_POST("../wxclient?do=cx", function(data){
        	fnloading();
        	var objdata = eval("(" + data + ")");
        	var txsqhtml =  '<div class="txsqField" style="text-align:center;color:black;">钱包金额<span style="color:red;">￥'+$('#amount').text() +'</span></div><div class="txsqField" ><input id="txsqje" placeHolder="提现金额" onkeyup="clearNonum(this)" onpaste="clearNonum(this)"/></div><div class="txsqField" style="color:gray;font-size:16px;">'
        				//<option value="2">支付宝</option>
        			  + ' 提现方式  <select id="cashid" style="width:75%;height:32px;" onchange="fnchange(this)" placeholder="提现方式"><option value="1">微信红包 </option><option value="3">银行卡</option></select>'  
        			  + '</div><div class="cash_bank" style="display:none;">'
        			  + '<div class="txsqField"><input id="txsqbank" placeHolder="银行卡号" value="'+ (objdata.bank_no||"") +'"/></div>'
        			  + '<div class="txsqField"><input id="txsquser" placeHolder="收款人姓名" value="'+ (objdata.bank_user||"") +'"/></div>'
        			  + '<div class="txsqField"><input id="txsqbank_name" placeHolder="银行名称" value="'+ (objdata.bank_name||"") +'"/></div>'
        			  + '<div class="txsqField"><input id="txsqbank_addr" placeHolder="开户行地址" value="'+ (objdata.bank_addr||"") +'"/></div>'
        			  + '</div>'
        			  + '<div class="txsqField" style="height:auto;font-size:12px;">'
        			  + ' 提现须知：<br>'
        			  + ' <span>1、提现金额必须大于1或者1的整数倍</span><br>'
        			  + ' <span>2、微信红包提现一经审批立即到账</span><br>'
        			  + ' <span>3、银行转账提现当天16:00前申请提现的12小时内到账,超过16:00的24小时内到账<br></span>'
        			  + ' <span>4、提现一经提交申请不能撤销</span>'
        			  + '</div>';
        			  
        			  //+ '<div class="cash_wx"><div class="txsqField"><input id="wxpay" placeHolder="微信号"/></div></div>'
        			  //+ '<div class="cash_alipay" style="display:none;"><div class="txsqField"><input id="alipay" placeHolder="支付宝账号"/></div></div>';
        			  
	        $("#txsqSubItem" + subN).append(txsqhtml);
	        if($(".txsqDiv").is(":hidden")){
	            $(".txsqDiv").show();
	            $('#content').hide();
	            $("#txsqDiv").css("width","100%").css("height","100%");
	        }
	        
        });
    },500);
}

function fnchange(obj){
	$('.cash_bank').hide();
	$('.cash_alipay').hide();
	$('.cash_wx').hide();
	if($(obj).val()==1) $('.cash_wx').show();
	if($(obj).val()==2) $('.cash_alipay').show();
	if($(obj).val()==3) $('.cash_bank').show();
}


function fnCloseTxsq(){
	$("#txsqDiv").animate({width:"0", height:"0"}, 300, null, function(){$(".txsqDiv").toggle();});
	$('#content').show();
}

function fnTxsqSave(){
	if($('#txsqje').val()==""){
		mAlert("请输入提现金额",function(){$('#txsqje').focus();});
		return;
	}
	if($('#txsqje').val()< 1){
		mAlert("提现金额必须大于1或者是1的整数倍",function(){$('#txsqje').focus();});
		return;
	}
//	if($('#cashid').val()==1 && $('#wxpay').val() == ""){
//		mAlert("请输入微信号",function(){$('#wxpay').focus();});
//		return;
//	}
//	if($('#cashid').val()==2 && $('#alipay').val() == ""){
//		mAlert("请输入支付宝账号",function(){$('#alipay').focus();});
//		return;
//	}
	if($('#cashid').val()==3){
		if($('#txsqbank').val()==""){
			mAlert("请输入银行卡号",function(){$('#txsqbank').focus();});
			return;
		} 
		if($('#txsquser').val()==""){
			mAlert("请输入收款人姓名",function(){$('#txsquser').focus();});
			return;
		}
		if($('#txsqbank_name').val()==""){
			mAlert("请输入银行名称",function(){$('#txsqbank_name').focus();});
			return;
		}
		if($('#txsqbank_addr').val()==""){
			mAlert("请输入开户行地址",function(){$('#txsqbank_addr').focus();});
			return;
		}
	}
	fnloading("提交申请...");
	var account=0;
	if($('#cashid').val()==1) account = $('#wxpay').val();
	if($('#cashid').val()==2) account = $('#alipay').val();
	if($('#cashid').val()==3) account = $('#txsqbank').val();
	XMLHttpURL_POST("../cash?do=add&amount="+$('#txsqje').val()+"&account="+account+"&bank_name="+$('#txsqbank_name').val()+"&name="+$('#txsquser').val()+"&bank_addr="+$('#txsqbank_addr').val()+"&cash_method=" +$('#cashid').val(), function(data){
		fnloading();
		var objdata = eval("(" + data + ")");
		if(objdata.code == "-1"){
			mAlert(objdata.msg);
			return;
		}else{
			mAlert("提交成功！",function(){fnCloseTxsq();});
		}
	});
}

function clearNonum(obj){
	setTimeout(function(){$(obj).val($(obj).val().replace(/[^\d]/g,""));},200);
}

function fnMyQrcode(){
    var strqrcode = "../"+localData.get("qrcode")+"?"+Math.random();
    fnPopDlg("我的推荐码", '<div style="text-align:center"><img style="width:100%;height:100%;"  src="'+strqrcode+'"></div>',true);
}

function fnActivityDetail(){
	var strqrcode = "../weixin/images/actdetail.jpg?v=4";
    fnPopDlg("活动详情", '<div style="text-align:center"><img style="width:100%;height:100%;"  src="'+strqrcode+'"></div>',true);
}


function fnRecharge(){
	fnloading("加载中...");
	window.location.href = 'recharge.html?flag=1&code='+Request.QueryString("code");
	fnloading();
}


function fnAreaSelSet(){
	$("#txsqCity").val(obj_area.mc_city_sel);
	$("#txsqSave").attr("idcity", obj_area.id_city_sel);
	$("#txsqSave").attr("idsq", obj_area.id_sq_sel);
	$("#txsqSave").attr("idxq", obj_area.id_xq_sel);
}
function fnAreaSelDef(){
	obj_area.id_city_sel = $("#txsqSave").attr("idcity");
	obj_area.id_sq_sel = $("#txsqSave").attr("idsq");
	obj_area.id_xq_sel = $("#txsqSave").attr("idxq");		
}

var LlczMx_n_popwin,LlczMx_n_page = 1,LlczMx_n_totalpage;
function fnLlczMx(page){
	page = page ? page : 1;
	if(LlczMx_n_totalpage && LlczMx_n_page > LlczMx_n_totalpage){return;}
	fnloading("加载充值记录...");
	XMLHttpURL_POST("../ordrecord?page="+page+"&size=10", function(data){
			fnloading();
			var objdata = eval("(" + data + ")");
			if(objdata.errcode == "-1"){
				mAlert(objdata.errmsg);
				return;
			}
			var shtml = "";
		 	if(objdata.records.list.length > 0){
		 		LlczMx_n_totalpage = objdata.records.totalPage;
		 		
		 		for (var n = 0; n < objdata.records.list.length; n++ ){	 
		 			var strstatus = objdata.records.list[n].status =="0" ? '<span style="padding-left:10px;color:red">充值失败<span>' : (objdata.records.list[n].status =="1" ? '<span style="padding-left:10px;color:green">充值成功<span>' : (objdata.records.list[n].status =="2" ? '<span style="padding-left:10px;color:blue">处理中<span>' : '<span style="padding-left:10px;color:gray">已退款<span>'));
		 			var paystatus = objdata.records.list[n].paystatus =="0" ? '<span style="padding-left:10px;color:red">支付失败<span>' : (objdata.records.list[n].paystatus =="1" ? '<span style="padding-left:10px;color:green">支付成功<span>' : (objdata.records.list[n].paystatus =="3" ? '<span style="padding-left:10px;color:blue">待付款<span>' : '<span style="padding-left:10px;color:gray">已退款<span>'));
			  		shtml += '<div class="cartBillItem"><a href="javascript:void(0);"><div class="billInfo pr30"><table><tr><td>' + objdata.records.list[n].recharge_dttm + '</td><td>'+paystatus+'</td><td align="right" class="billState">'+strstatus+'</td></tr></table></div><div class="billInfo cartSPView">';
			  		var strorder = (objdata.records.list[n].transaction_id=="" || objdata.records.list[n].transaction_id ==null) ? ('单号：'+objdata.records.list[n].order_id) : ('微信单号 ：'+objdata.records.list[n].transaction_id);
			  		
			  		shtml += '<div>'+strorder+'<br>号码：' + objdata.records.list[n].phone +' ('+objdata.records.list[n].attribution+')' + '<br>套餐：'+objdata.records.list[n].note+'</div>';
			  		shtml += '</div><div class="billInfo"><div class="fr pricecolor price">￥' + formatPrice(objdata.records.list[n].settlement_price, 2) + '</div></div></a>';
			  		
			  		if(objdata.records.list[n].paystatus =="3")
			  		{	
			  			shtml += '<div class="billOperDiv">';
			  			shtml += '<span class="redbtn cancelbtn" onclick="fnwallPay(\''+objdata.records.list[n].order_id+'\',\''+objdata.records.list[n].phone+'\')"><i class="icon-coin-yen colorf"></i> 钱包支付</span>';
			  			shtml += '<span class="redbtn btnPay" onclick="fnweixinpay(\''+objdata.records.list[n].order_id+'\',\''+objdata.records.list[n].prepay_id+'\')"><span><i class="icon-wzf"></i></span> 微信支付</span>';
			  			shtml += '</div>'; 
			  		}
			  		shtml += '<div class="billXH">' + ((page - 1) * 10 + n + 1) + '</div></div>';
			  	}
		 		
		 		
				if(!LlczMx_n_popwin)	
					LlczMx_n_popwin = fnPopWin("充值记录", shtml, '', 'red','','',clearLlczMx);
				else
					$(LlczMx_n_popwin).find(".popWinContent").append(shtml);
				
                $(LlczMx_n_popwin).find(".popWinContent").scroll(function(){
                	if(LlczMx_n_totalpage && LlczMx_n_page > LlczMx_n_totalpage){return;}
                	var scrollTop = parseInt($(this).scrollTop());
                	var windowHeight = parseInt($(this).height());
                    var scrollHeight = parseInt($(this)[0].scrollHeight);
                    if((scrollTop + windowHeight) >= scrollHeight-50)
                    {
                    	LlczMx_n_page +=1;
                    	fnLlczMx(LlczMx_n_page);
                    }
                });
				
			}else{
				fnPopTip("没有任何充值记录"," ");
			}
	});
}

function fnweixinpay(orderid,prepayid){
	var timer = setTimeout(function(){fnloading();}, 50000);
	if(prepayid)
	{
		fnloading("请求支付中");
		XMLHttpURL_POST("../order?paytype=1&order_id="+orderid+"&prepay_id="+prepayid, function(data){
			var wxpay = eval("(" + data + ")");
			localData.set("appid", wxpay.appId);
			localData.set("timeStamp", wxpay.timeStamp);
			localData.set("nonceStr", wxpay.nonceStr);
			localData.set("package", wxpay.package);
			localData.set("paySign", wxpay.paySign);
			localData.set("orderid", wxpay.orderid);
			
			if(typeof WeixinJSBridge ==="undefined"){
				if(document.addEventListener){
					document.addEventListener("WeixinJSBridgeReady",callpay,false);
				  	fnloading();
				}
			}else{
				callpay();
				fnloading();
			}
			clearInterval(timer);
		})
	}else{
        /*fnloading("请求支付中");
		XMLHttpURL_POST("../order?paytype=2&order_id="+orderid, function(data){
			fnloading();
			clearInterval(timer);
			mAlert(data);
		})*/
		
	}	
}

function fnwallPay(orderid,phone){
	fnloading("请求支付中");
	var obj_czk = fnPopDlg("钱包支付", '<div style="text-align:center;font-size:24px;"><input type="password" placeholder="请录入支付密码" id="czkPayPsw" style="height:40px;margin-top:10px;margin-bottom:0px;"></input><div style="text-align:center"><span class="redbtn mt12" style="width:60%">支付</span></div>',null,function(){fnloading();});
	$("#czkPayPsw").focus();
	$("#czkPayPsw").bind("keydown", function(){
		 if(event.keyCode == 13){obj_czk.find(".redbtn").trigger("click");}
	});
	obj_czk.find(".redbtn").bind("click", function(){
		var pPsw = $("#czkPayPsw").val() || '';
		if(!pPsw){
			mAlert("请录入支付密码！", function(){$("#czkPayPsw").focus();});
			return;
		}
		obj_czk.remove();
		setTimeout(function(){
			XMLHttpURL_POST("../order?paytype=2&orderid="+orderid, function(data){
				fnloading();
				var result = eval("("+data+")");
				if(result.result_code =="0")
					mAlert(result.message,function(){clearAll();WXPAYWIN.remove();});
				else if(result.result_code =="3")
					mAlert(result.message,function(){localData.set("orderid",result.order_id);});
				else if(result.result_code =="1")
					mAlert(result.message,function(){clearAll();WXPAYWIN.remove();});
				else if(result.result_code =="5")
					mAlert(result.message,function(){});
				else if(result.result_code =="6")
					mAlert(result.message,function(){clearAll();WXPAYWIN.remove();});
				else
					mAlert(result.message,function(){});
				
			});
		}, 200);
	});
}

function fnwallpayok(){
	fnClosePopWin(LlczMx_n_popwin,clearLlczMx);
	fnLlczMx(1);
}

function clearLlczMx(){
	LlczMx_n_popwin =null;
	LlczMx_n_page = 1;
	LlczMx_n_totalpage=null;
}
var myteampage =1,scroll;
function fnMyTeam(js,openid,obj,page,totalpage){
	if(!js) js = 1;
	if(!page)page=1;
	openid = openid ? openid : "";
	myteampage = page;
	if(totalpage && myteampage > totalpage){return;}
	fnloading("加载我的团队...");
	XMLHttpURL_POST("../myteam?open_id="+openid+"&page="+page+"&size=11&js="+js, function(data){
		fnloading();
		var objs = eval("(" + data + ")");
		if (objs.code != 0){
			mAlert("获取我的团队失败！"+objs.msg);
			return;
		}else{
			var rs = objs.myteams.list;
			var lv2 =0,lv3 =0;
			var shtml = "";
		 	if(rs.length > 0){
		 		scroll = true;
				for (var n = 0; n < rs.length; n++ ){
					if(rs[n].lv1 > 0){lv2 = lv2 + parseInt(rs[n].lv1);}
					if(rs[n].lv2 > 0){lv3 = lv3 + parseInt(rs[n].lv2);}
					var icon = ((js==1 && rs[n].lv1 < 1) || (js==2 && rs[n].lv1 < 1) ||js > 2) ? '' : '<i class="icon-chevron-thin-right f20 col8"></i>';
					var dclick = ((js==1 && rs[n].lv1 < 1) || (js==2 && rs[n].lv1 < 1) ||js > 2) ? '' :   'fnMyTeam('+ (js+1) +',\''+ rs[n].openid +'\',this,1)';
					icon ='';
					dclick='';
					shtml += '<div class="mxItem" onclick="'+dclick+'"><table><tr><td><div class="mxBz"><font style="position:relative;top:12px;color:'+ (js == 1 ? '#47BB29' : (js == 2 ? '#ff3c25' : (js ==3 ? '#45A6FF' : ''))) +';">'+(n+1+(page-1)*11)+'</font><div style="margin-top:-6px;padding-left:20px;"><span class="stkxx">' + rs[n].nick_name + '</span>' + '</div></div></td><td class="mxJeIn"><strong>'+(js < 2 ? '<font color="#ff3c25">'+ (rs[n].lv1 > 0 ? rs[n].lv1 : 0) + '</font> /' : '') + (js < 3 ? ' <font color="#45A6FF">'+ (rs[n].lv1 > 0 ?  (js == "1" ? rs[n].lv2 : rs[n].lv1) : 0) : '') +'</font></td><td align=right width=50>'+ icon +'</td></tr></table></div>';
				}
				
				
				if($('#popWinjs'+js)[0]){
					$('#popWinjs'+js).find(".popWinContent").append(shtml);
				}else{
					fnPopWin((obj ? $(obj).find(".stkxx").text() : '我') + "的团队", shtml, '', 'red',null,null,null,null,"js"+js);
				}
				
				$('#popWinjs'+js).find(".popWinContent").scroll(function(){
					if(!scroll){return;}
                	if(myteampage > objs.myteams.totalPage){return;}
                	var scrollTop = parseInt($(this).scrollTop());
                	var windowHeight = parseInt($(this).height());
                    var scrollHeight = parseInt($(this)[0].scrollHeight);
                    if((scrollTop + windowHeight) >= scrollHeight-50)
                    {
                    	page +=1;
                    	scroll = false;
                    	fnMyTeam(js,openid,obj,page,objs.myteams.totalPage);
                    }
                });
			}else{
				fnPopTip((obj ? $(obj).find(".stkxx").text() : '您') + "还没有团队成员！"," ");
			}	
		}
	});		
}

var TXsq_n_popwin,TXsq_n_page = 1,TXsq_n_totalpage;
function fnTxsqRecords(page){
	page = page ? page : 1;
	if(TXsq_n_totalpage && TXsq_n_page > TXsq_n_totalpage){return;}
	fnloading("加载提现记录...");
	XMLHttpURL_POST("../cash?do=query&page="+page+"&size=10", function(data){
			fnloading();
			var objdata = eval("(" + data + ")");
			if(objdata.errcode == "-1"){
				mAlert(objdata.errmsg);
				return;
			}
			
			var shtml = "";
			if(objdata.cashrecords.list.length > 0){
				TXsq_n_totalpage = objdata.cashrecords.totalPage;
				for (var n = 0; n < objdata.cashrecords.list.length; n++ ){
					shtml += '<div class="mxItem" style="font-size:12px;"><table>';
					var strorder = objdata.cashrecords.list[n].cash_method=="1"  ? '提现方式：微信红包' : (objdata.cashrecords.list[n].cash_method=="2" ? '提现方式：支付宝' : (objdata.cashrecords.list[n].cash_method=="3" ? "提现方式：银行转账" : "") );
					var bankdetail = objdata.cashrecords.list[n].cash_method=="3" ? '<br>银行名称：' + objdata.cashrecords.list[n].bank_name + '<br>开户地址：' + objdata.cashrecords.list[n].bank_addr : '';
					shtml += '<tr><td><div class="mxBz">'+strorder+'<br>提现账号：'+objdata.cashrecords.list[n].account+(objdata.cashrecords.list[n].name ? ('<br>姓名：'+objdata.cashrecords.list[n].name+bankdetail) : '')+'</div>';
					var strstatus = objdata.cashrecords.list[n].status =="1" ? '<span style="padding-left:10px;color:blue">已提交<span>' : (objdata.cashrecords.list[n].status =="2" ? '<span style="padding-left:10px;color:green">已完成<span>' : (objdata.cashrecords.list[n].status =="3" ? '<span style="padding-left:10px;color:red">不通过<span>' : '<span style="padding-left:10px;color:gray"><span>'));
					shtml += '<div class="mxRq">'+objdata.cashrecords.list[n].ts+strstatus+' </div></td><td class="mxJeOut"><strong> ¥' + formatPrice(Math.abs(objdata.cashrecords.list[n].amount), 2) + '</strong></td></tr>';
					shtml += '</table></div>';
//					shtml += '<div class="billXH">' + ((page - 1) * 10 + n + 1) + '</div></div>';
				}
				
				if(!TXsq_n_popwin)	
					TXsq_n_popwin = fnPopWin("提现记录", shtml, '', 'red','','',clearTxsqrecord);
				else
					$(TXsq_n_popwin).find(".popWinContent").append(shtml);
				
	            $(TXsq_n_popwin).find(".popWinContent").scroll(function(){
	            	if(TXsq_n_totalpage && TXsq_n_page > TXsq_n_totalpage){return;}
	            	var scrollTop = parseInt($(this).scrollTop());
	            	var windowHeight = parseInt($(this).height());
	                var scrollHeight = parseInt($(this)[0].scrollHeight);
	                if((scrollTop + windowHeight) >= scrollHeight-50)
	                {
	                	TXsq_n_page +=1;
	                	fnTxsqRecords(TXsq_n_page);
	                }
	            });
			}else{
				fnPopTip("没有任何提现记录"," ");
			}
	});
}

function clearTxsqrecord(){
	TXsq_n_popwin =null;
	TXsq_n_page = 1;
	TXsq_n_totalpage=null;
	Comis_n_popwin = null;
	Comis_n_page = 1;
	Comis_n_totalpage =null;
}


var Comis_n_popwin,Comis_n_page = 1,Comis_n_totalpage;
function fnMycommission(page){
	page = page ? page : 1;
	if(Comis_n_totalpage && Comis_n_page > Comis_n_totalpage){return;}
	fnloading("加载我的佣金...");
	XMLHttpURL_POST("../comis?page="+page+"&size=10", function(data){
			fnloading();
			var objdata = eval("(" + data + ")");
			if(objdata.errcode == "-1"){
				mAlert(objdata.errmsg);
				return;
			}
			
			var shtml = "";
			if(objdata.commission.list.length > 0){
				Comis_n_totalpage = objdata.commission.totalPage;
				var rows = objdata.commission.list;
				for (var n = 0; n < rows.length; n++ ){
					shtml += '<div class="mxItem" style="font-size:12px;"><table>';
					var strorder = "名称："+ rows[n].nick_name;
					shtml += '<tr><td><div class="mxBz">'+strorder+'<br>订单金额：¥ '+formatPrice(Math.abs(rows[n].settlement_price), 2)+'</div>';
					var strstatus = rows[n].lv=="1" ? '<span style="padding-left:10px;color:red"><span>' : (rows[n].lv =="2" ? '<span style="padding-left:10px;color:red"><span>' : (rows[n].lv =="3" ? '<span style="padding-left:10px;color:red"><span>' : (rows[n].lv =="4" ? '<span style="padding-left:10px;color:#3EAD00;">(流量充值)<span>' : (rows[n].lv =="5" ? '<span style="padding-left:10px;color:#45A6FF;">(佣金提现)<span>' : ''))));
					shtml += '<div class="mxRq">'+rows[n].ts+strstatus+' </div></td>'+(rows[n].lv > 3 ?  '<td class="mxJeIn"><strong> ¥ ' + formatPrice(rows[n].commission, 2) + '</strong></td>' :'<td class="mxJeOut"><strong> ¥ ' + formatPrice(rows[n].commission, 2) + '</strong></td>')+'</tr>';
					shtml += '</table></div>';
				}
				
				if(!Comis_n_popwin)	
					Comis_n_popwin = fnPopWin("我的佣金", shtml, '', 'red','','',clearTxsqrecord);
				else
					$(Comis_n_popwin).find(".popWinContent").append(shtml);
				
	            $(Comis_n_popwin).find(".popWinContent").scroll(function(){
	            	if(Comis_n_totalpage && Comis_n_page > Comis_n_totalpage){return;}
	            	var scrollTop = parseInt($(this).scrollTop());
	            	var windowHeight = parseInt($(this).height());
	                var scrollHeight = parseInt($(this)[0].scrollHeight);
	                if((scrollTop + windowHeight) >= scrollHeight-50)
	                {
	                	Comis_n_page +=1;
	                	fnMycommission(Comis_n_page);
	                }
	            })
			}else{
				fnPopTip("没有任何佣金记录！"," ");
			}
	});
}

function fnSign(points){
	XMLHttpURL_POST("../addmypoints?open_id="+open_id, function(data){
		if(data =="success")
		{	
			fnPopTip("签到成功！+5"," ");
			$('#byspan1 div').css("color","gray");
			$('#byspan1 num').css("color","gray");
			$('#byspan1').css("cursor","none");
			$('#byspan1').unbind();
			$('#wxkjf').html('<num>' + (points+5) + '</num><br>微信积分');
		}else{
			fnPopTip(data," ");
		}
	});
}

function fnJfstore(){
	fnPopTip("积分商城建设中"," ");
}

function openTop(){
	fnloading("加载排行榜数据...");
	var shtml = '<div id="ranking-list" style="width:100%;height:100%;margin:0 auto; padding-bottom:70px;overflow:auto;background-color:#FF9800;">'		
		
		shtml += '<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-left" style="display:none;">'
			shtml +='<article class="a3"><h3 class="list-group-item-heading text-center" style="background:#ec5251;color:#fff;">奖品设置</h3><div class="prize3"id="jp_list"><ul>'
            shtml += '<li><div class="hdjptp"><div class="jptu"><img src="images/prize/prize_1.jpg"></div><div class="jpms"><p class="jpdj">一等奖</p><p class="jpmc">华为 P9 金色</p></div></div></li>'	 
            shtml += '<li><div class="hdjptp"><div class="jptu"><img src="images/prize/prize_2.jpg"></div><div class="jpms"><p class="jpdj">二等奖</p><p class="jpmc">OPPO R9 玫瑰金 </p></div></div></li>'
            shtml += '<li><div class="hdjptp"><div class="jptu"><img src="images/prize/prize_3.jpg"></div><div class="jpms"><p class="jpdj">三等奖</p><p class="jpmc">小米 Max 金色</p></div></div></li>'	
            shtml += '<li><div class="hdjptp"><div class="jptu"><img src="images/prize/prize_4.jpg"></div><div class="jpms"><p class="jpdj">四等奖</p><p class="jpmc">酷派大神1S 通话平板</p></div></div></li>'
            shtml += '<li><div class="hdjptp"><div class="jptu"><img src="images/prize/prize_5.jpg"></div><div class="jpms"><p class="jpdj">五等奖</p><p class="jpmc">台电（Teclast）X70R 平板电脑 </p></div></div></li>'
            shtml += '<li><div class="hdjptp"><div class="jptu"><img src="images/prize/prize_6.jpg"></div><div class="jpms"><p class="jpdj">六等奖</p><p class="jpmc">全国三网流量1GB</p></div></div></li>'
            shtml +='</ul></div></article>'	
        shtml += '</div>'
		
		shtml += '<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-left">'
		shtml += ' <h3 class="list-group-item-heading text-center" style="background:#ec5251;color:#fff;margin-bottom:0px;text-align:center;" id="topless">排行榜</h3>'
	    shtml += ' <ul class="list-group list-group-item2 prize-name">'
	    XMLHttpURL_POST("../actreport?page=1&size=50", function(data){	
	    	fnloading();
	    	var dataobj = eval("(" + data + ")");
	    	if(dataobj.code==-1){
	    		fnPopTip(data.msg, " ")
	    		return;
	    	}
	    	var  list = dataobj.myteams.list
	    	var  len = list.length;
	    	var  top = dataobj.top
	    	for(var i=0;i<len;i++){
	    		var bac = (top<=50 && list[i].openid==dataobj.openid) ? 'background-color: rgba(246, 247, 245, 0.45);':'';
	    		shtml += '	<li style="'+bac+'"><table style="width: 100%;"><tbody><tr><td style="text-align:left;color:red;font-style:italic;font-weight:bold;width:25%;"><span class="f10">Top</span> <span class="topn">'+(i+1)+'</span></td><td style="text-align: center;width:40%;"><div style="word-break:keep-all; white-space:nowrap;overflow:hidden;text-overflow:ellipsis;"><img src="'+ list[i].photo +'" width="30px;" height="30px;"><div style="width:120px;">&nbsp;'+list[i].nick_name+'</div></div></td><td align="right" class="billState" style="text-align: right;width:25%;"><span style="color:#FF006C">'+list[i].cout+'</span></td></tr></tbody></table></li>'
	    	}
	    	if(len==50) shtml += '	<li class="list-group-item" style="text-align:center;">...</li>'
	    	if(len==0)	shtml += '	<li class="list-group-item" style="text-align:center;">暂无记录</li>'
	    	shtml += ' </ul></div>'
	        shtml += '</div>'
	            
	        fnPopWin("活动排名", shtml, '', 'red','','');
	    	
	    	if(parseInt(top) > 100) $('#topless').html("排行榜(您与第100名相差"+dataobj.less+"粉丝)");
	    	if(parseInt(top) <= 100) $('#topless').html("排行榜(<span style='font-size:16px;'>当前排名"+top+"</span>)");
	    	
	    	
	    })      
}







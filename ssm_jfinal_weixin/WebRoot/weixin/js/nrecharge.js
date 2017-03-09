//	if(!isWeiXin()){window.location.href="../main/404.jsp";}
	if(!Request.QueryString("flag")){
		$('.sliderOuterWrapper').show();
	}else{
		$('#header').show();
		$('.websiteWrapper').css("margin-top", "50px");
//		$('.websiteWrapper').css("position", "fixed");
		$('.websiteWrapper').css("width", "100%");
	}
	clearAll(true);
	if(Request.QueryString("code"))
	{
		fnloading("正在读取数据...");		
		XMLHttpURL_POST("../wxclient?code="+Request.QueryString("code")+"&url="+window.location, function(data){
			fnloading();

			var objdata = eval("(" + data + ")");
			if(objdata.errcode == "-1"){
				mAlert(objdata.errmsg);
				return;
			}else{
				fndefaulthtml(objdata.notice);
				initWxJs(objdata.appid,objdata.timestamp,objdata.noncestr,objdata.signature,objdata.open_id);
			}
		});
	}else{
		fnPopTip("请通过微信公众号打开页面！"," ");
	}
	
	
	
	function fndefaulthtml(notice_content){
		if(notice_content) $('#notice').html(notice_content);
		XMLHttpURL_POST("../product?phone=default2", function(data){
			fnloading();
            var objs = eval("(" + data + ")");
            var producthtml =  '';
	        var disint = 0;
	        for(var l=0;l < 3;l++){
	        	disint = l+1;
	        	var cutproducthtml ='<div id="cuthelp'+disint+'" class="cuthelp"style="display: none; min-height: 120px;">';
	        	for(var i =0;i < objs.list[l].content.length;i++)
	        		cutproducthtml += getStrhtml(objs.list[l].content[i],l+1);
	        	if(cutproducthtml.indexOf("fnColSel") < 0) cutproducthtml += "<div style='width:100%;height:150px;'>当前服务商没有可用流量套餐</div>";
	        	cutproducthtml +='</div>'
	        	producthtml += cutproducthtml;
	        }
	        if(objs.list[0].content.length > 0) disint =1;
	        if(objs.list[1].content.length > 0 && disint==0) disint =2;
	        if(objs.list[2].content.length > 0 && disint==0) disint =3;
	        
	        
	        $('#goodinfoDet').html(producthtml);
	        $('.recfoot').show();
	        $('.buytoolbar').hide();
	        fnShowProduct(disint,true);
	        $('.body-div .citem div').css("width",($('#cuthelp1').width()/3-8-10));
		});
	}
	
     
    
	function fnColSel(obj) {
    	if(!fnchekphone()) return;
        $(".citem").find(".sel").removeClass("sel");
        $(obj).addClass("sel");
        var desc = (($(obj).attr("name")=="" || $(obj).attr("name")=="null") ? ($(".ngoodcuttab").find(".tabsel").text() + $(obj).text()) : $(obj).attr("name"));
        $('.pro_type').text(desc); 
        $('.pro_price').html('<span style="color:#4cb0f9">'+$(obj).attr("product_name")+'</span>'+" ￥"+$(obj).attr("price")+'&nbsp;&nbsp;&nbsp;<del><span style="color:gray;">￥'+ $(obj).attr("proprice") +'</span></del>');
        
        if($('.buy').attr("pid") && $('.buy').attr("pid")!=$(obj).attr("id")){
        	localData.remove("orderid");
        	localData.remove("appid");
        }
        
        $('.buy').attr("pid",$(obj).attr("id"));
        $('.buy').attr("price",$(obj).attr("price"));
        $('.buytoolbar').show();
        $('.recfoot').hide();
        $('#goodinfo2').css("margin-bottom","50px");
    }
    
	
	// 粘贴事件监控
    $.fn.pasteEvents = function( delay ) {
        if (delay == undefined) delay = 10;
        return $(this).each(function() {
            var $el = $(this);
            $el.on("paste", function() {
                $el.trigger("prepaste");
                setTimeout(function() { $el.trigger("postpaste"); }, delay);
            });
        });
    };
    // 使用
    $("#phone").on("postpaste", function() {
    	clearNoNum();
    }).pasteEvents();
    
    $("#phone").keyup(function(event){
    	if(event.keyCode!=8 || (event.keyCode==8 && $('#phone').val().length==0)) 
    		clearNoNum();
    });
    
    var FLAG = true;
    function clearNoNum()
    {
    	//$('#phone').val($('#phone').val().replace(/[^\d]/g,"").replace(/....(?!$)/g, '$& '));
    	if($('#phone').val().length>0)$('#phone').val(doFilter($('#phone').val().replace(/[^\d]/g,"")));
        $('#type_card').text("");
        $(".citem").find(".sel").removeClass("sel");
        $('.buy').attr("pid","");
        if(($('#phone').val().length ==13) && FLAG){
        	$('#phone').blur();
        	FLAG = false;
        	localData.remove("appid");
        	localData.remove("orderid");
        	fnloading("获取流量包数据...");
            XMLHttpURL_POST("../product?phone="+$('#phone').val().replace(/\s+/g,"")+"&t=2", function(data){
            	FLAG = true;
            	fnloading();
                var objs = eval("(" + data + ")");
                var producthtml =  '';
    	        var disint = 0;
    	        if(objs.list[0].title=="中国移动") disint=1;
    	        if(objs.list[0].title=="中国联通") disint=2;
    	        if(objs.list[0].title=="中国电信") disint=3;
    	        
    	        var cutproducthtml ='<div id="cuthelp'+disint+'" class="cuthelp" style="display: none; min-height: 120px;">';
	        	for(var i =0;i < objs.list[0].content.length;i++)
	        		cutproducthtml += getStrhtml(objs.list[0].content[i],disint);
	        	if(cutproducthtml.indexOf("fnColSel") < 0) cutproducthtml += "<div style='width:100%;height:150px;'>当前服务商没有可用流量套餐</div>";
	        	cutproducthtml +='</div>'
	        	producthtml += cutproducthtml;
                
	        	$('#goodinfoDet').html(producthtml);
                fnShowProduct(disint,true);
                $('#type_card').text(objs.city+" "+objs.card_type);
                $('.buy').attr("amount",objs.amount||0);
                $('.body-div .citem div').css("width",($('.cuthelp').width()/3-8-10));
            });
        }else if(($('#phone').val().length ==0) && FLAG){
        	fndefaulthtml();
        }
    }
    
    function doFilter(value) {
        var temp = [];
        temp[0] = value.slice(0, 3);
        temp[1] = value.slice(3, 7);
        temp[2] = value.slice(7);
        return temp.join(" ");
    }
    
    $('.buy').unbind().bind("click", function() {
    	if(!fnchekphone()) return;
    	if($('.buy').attr("pid")=="")
    	{
    		mAlert("请选择要购买的套餐");
    		return;
    	}
        fnOnlinePay($('.buy').attr("pid"));
    });
    
   function getStrhtml(rows,n){
	   if(!rows)	return "";
	   var typename = rows.typename;
	   var rows = rows.products;
	   console.log(typename+"--"+rows.length)
	   if(rows.length < 1) return "";
	   var producthtml =  '<div></div><div class="typename">'+typename+'</div><div class="citem">';
	   for(var i = 0 ; i < rows.length; i++){
	   	var recomm = rows[i].is_recommend==1 ? '<img class="recimg" src="images/rec.png?v=1">':'';
	   	var cls = rows[i].is_recommend==1 ? 'recommend':'';
	   	producthtml += '<div class="'+cls+'" onclick="fnColSel(this)" id="'+rows[i].product_id+'" price="'+rows[i].settlement_price+'" name="'+rows[i].specify_name+'" product_name="'+rows[i].product_name+'" proprice="'+ rows[i].product_price +'">'+recomm+rows[i].product_name +'<br><span>￥'+rows[i].settlement_price+'</span></div>';
	   }
	   producthtml += '</div>';
	   return producthtml;
   }
    
    function fnchekphone(){
    	if($('#phone').val().length < 11)
    	{
    		fnPopTip("请输入有效的手机号码"," ");
    		$('#phone').focus();
    		return false;
    	}
    	return true;
    }
    
    var WXPAYWIN; 
    function fnOnlinePay(product_id){

        var spay = '', shtml = '<div id="havePay" ><div class="p12 bcolorW f16 itemDiv bdB"><div class="fl">请支付</div><div class="fr price">¥<span id="payJeSy">' + formatPrice($('.buy').attr("price"), 2) + '</span></div></div>';
        spay += '<div class="p12 bcolorW f16 itemDiv bdB payLQB hand mt12"><table><tr><td width="45"><span class="iconBK bcolorB"><i class="icon-coin-yen colorf"></i></span></td><td width="100%">我的钱包支付<div style="font-size: 12px;color: #aaa;margin-top: 5px;">(余额：¥<span id="payLQBYe">' + formatPrice($('.buy').attr("amount"), 2) + '</span>)</div></td><td width="18" align="right"><i class="icon-chevron-thin-right colora"></i></td></tr></table></div>';
        spay += '<div class="p12 bcolorW f16 itemDiv bdB payWX hand"><table><tr><td width="45"><span class="iconBK colorWzf"><i class="icon-wzf"></i></span></td><td width="100%">微信支付</td><td width="18" align="right"><i class="icon-chevron-thin-right colora"></i></td></tr></table></div>';
        spay +=  '<div></div>';
        WXPAYWIN = fnPopWin("收银台", shtml + spay, "lt2rb", "",  '', '',fnclearAppid);

        WXPAYWIN.find(".popWinContent .payLQB").unbind().bind("click", function(){
        	if( parseFloat(formatPrice($('.buy').attr("amount"), 2)) < parseFloat(formatPrice($('.buy').attr("price"), 2)) ){
    			fnPopTip("钱包余额不足"," ");
    			return;
    		}
    		fnloading("请求支付中");
			var obj_czk = fnPopDlg("钱包支付", '<div style="text-align:center;font-size:24px;"><input type="password" placeholder="请录入支付密码" id="czkPayPsw" style="height:40px;margin-bottom:0px;""></input><div style="text-align:center"><span class="redbtn mt12" style="width:60%">支付</span></div>',null,function(){fnloading();});
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
				fnloading("请求支付中");
				var orderid = localData.get("orderid");
				if(orderid=="undefined") orderid ="";
				XMLHttpURL_POST("../order?paytype=2&pas="+$.md5(pPsw)+"&phone="+ $('#phone').val().replace(/[^\d]/g,"") +"&product_id="+product_id+"&orderid="+orderid+"&lat="+localData.get("lat")+"&lng="+localData.get("lng")+"&bdlat="+localData.get("bdlat")+"&bdlng="+localData.get("bdlng"), function(data){
					fnloading();
					var result = eval("("+data+")");
					if(result.result_code =="0")
						mAlert(result.message,function(){clearAll();WXPAYWIN.remove();});
					else if(result.result_code =="3")
						mAlert(result.message,function(){localData.set("orderid",result.order_id);});
					else if(result.result_code =="1")
						mAlert("充值提交成功！",function(){clearAll();WXPAYWIN.remove();});
					else if(result.result_code =="5")
						mAlert(result.message,function(){});
					else if(result.result_code =="6")
						mAlert(result.message,function(){clearAll();WXPAYWIN.remove();});
					else
						mAlert(result.message,function(){});
				});
			});
    			
        });
        
        WXPAYWIN.find(".popWinContent .payWX").unbind().bind("click", function(){
           if(!isWeiXin())
           {
           		mAlert("只能在手机微信客户端发起支付请求");
           		return;
           }
           if(localData.get("appid")){
        	   callpay();
        	   return;
           }
        	fnloading("请求支付中");
			XMLHttpURL_POST("../order?paytype=1&phone="+ $('#phone').val().replace(/[^\d]/g,"") +"&product_id="+product_id+"&orderid="+localData.get("orderid")+"&lat="+localData.get("lat")+"&lng="+localData.get("lng")+"&bdlat="+localData.get("bdlat")+"&bdlng="+localData.get("bdlng"), function(data){
				var wxpay = eval("(" + data + ")");
				if(wxpay.result_code){
					mAlert(wxpay.message);
					return;
				}
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
			});
			setTimeout(function(){fnloading();}, 50000);
        });

    }
    
    function fnclearAppid(){
    	localData.remove('orderid');
        localData.remove('appid');
    }
    
    
    function fnShowProduct(n,f){
    	if($("#phone").val().length>1&&!f){
    		fnPopTip("当前手机号码只能选择当前服务商", " ");
    		return;
    	}
    	$(".ngoodcuttab").find(".tabsel").removeClass("tabsel");
    	$(".ngoodcuttab").find("span:eq("+(n-1)+")").addClass("tabsel");
    	$(".cuthelp").hide();	
    	$("#cuthelp"+n).show();
    }
    
    function clearAll(flag){
    	$('#type_card').val("");
    	$('#phone').val("");
    	if(!flag)fndefaulthtml();
    	$('.buy').removeAttr("pid");
        $('.buy').removeAttr("price");
        $('.pro_type').text("");
        $('.pro_price').text("");
        localData.remove("orderid");
        localData.remove("appid");
        if(WXPAYWIN)WXPAYWIN.remove();
    }
    
    //滚动事件监控
//    $(document).ready(function() {
//	  $(window).scroll(function() {
//		if ($(document).scrollTop()-50 <= $(document).height() - $(window).height()) {
//	      $(".buytoolbar").show();
//	    }
//	    if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
//	      if(!$('.recfoot').is(':hidden'))$(".buytoolbar").hide();
//	    }
//	  });
//	});
    
    
  //发起支付请求，调用微信支付
    var Flag = true;
    function callpay()
    { 
    	if(Flag){
    		Flag = false;
    		WeixinJSBridge.invoke('getBrandWCPayRequest',	
    		{
    			"appId": localData.get("appid"),
    			"timeStamp": localData.get("timeStamp")+"",
    			"nonceStr":  localData.get("nonceStr")+"",
    			"package" :  localData.get("package")+"",
    			"signType" : "MD5",
    			"paySign" : localData.get("paySign")
    		},function(res){
    			WeixinJSBridge.log(res.err_msg);
    			if(res.err_msg=="get_brand_wcpay_request:ok"){ //支付成功！改变订单状态,腾讯解释该返回值不一定可靠，还要用订单查询接口查询订单真实支付状态
    				Flag = true;
    				mAlert("支付成功！",function(){clearAll();});
    			}else if(res.err_msg=="get_brand_wcpay_request:cancel"){
    				//买家主动取消支付	
    				Flag = true;
    			}else if(res.err_msg=="system:access_denied"){
    				//不在测试白名单	
    				Flag = true;
    				mAlert("微信支付处于测试阶段，您的个人微信号尚未添加到微信公众号的测试白名单中，无法进行支付！");
    			}else if(res.err_msg=="get_brand_wcpay_request:fail"){//支付授权目录问题
    				Flag = true;
    				//请检查微信配置的支付授权目录是否正确或者微信号是否添加到了支付测试白名单,或者订单超过了支付时间（prepay_id最长有效时间2小时）
    				mAlert("申请支付失败！ ");
    			}
    		});
    	}
    };

	
	
	
//	if(!isWeiXin()){window.location.href="../main/404.jsp";}
	if(!Request.QueryString("flag")){
		$('.sliderOuterWrapper').show();
	}else{
		$('#header').show();
		$('.websiteWrapper').css("margin-top", "50px");
		$('.websiteWrapper').css("position", "fixed");
		$('.websiteWrapper').css("width", "100%");
	}
	localData.remove("orderid");
	localData.remove("appid");
	var notice_content;
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
				notice_content = objdata.notice;
				fndefaulthtml();
				initWxJs(objdata.appid,objdata.timestamp,objdata.noncestr,objdata.signature,objdata.open_id);
			}
		});
	}
	
	
	
	function fndefaulthtml(){
		strhtml =  '<div id="goodinfo2" style="display: block;"><div class="goodcuttab"id="goodcuttab"><div class="fl" onclick="fnShowProduct(1)" style="width:99%"><span class="tabsel">（暂支持）广东移动</span></div><div class="fl"onclick="fnShowProduct(2)" style="display:none;"><span class="">国内流量</span></div><div class="fr" onclick="fnShowProduct(3)" style="width:48%;display:none;"><span class="">全国流量</span></div></div>';
		     strhtml += '<div id="goodinfoDet">';
		     strhtml += '<div id="cuthelp1"class="cuthelp"style="display: block; min-height: 120px;">'+
					     '<div class="citem">' +
					     '<span>&nbsp;&nbsp;&nbsp;只限本省号码充值，在本省使用，不能跨省漫游。</span>'+
					     '<marquee style="height:40px;margin-top:30px;" scrollamount="3" direction="left" >'+
					     	notice_content+
					     '</marquee >'+
					     '</div>' 
			         +'</div>';
			 strhtml += '<div id="cuthelp2"class="cuthelp"style="min-height: 120px; display: none;">'+
					     '<div class="citem">' +
					     '&nbsp;&nbsp;&nbsp;只限本省号码充值，能在全国漫游使用。'+
					     '</div>'
			         +'</div>';
			 strhtml += '<div id="cuthelp3"class="cuthelp"style="min-height: 120px; display: none;">'+
					     '<div class="citem">' +
					     '<span>&nbsp;&nbsp;&nbsp;全国号码都可以充值，全国漫游使用，不含港澳台。</span>'+
					     '<marquee style="height:40px;margin-top:30px;" scrollamount="5" direction="left" >'+
					     	notice_content + 
					     '</marquee >'+ 
					     '</div>'
			         +'</div>';
			 strhtml += '</div>';
		            
		    $('.testimonialWrapper').html(strhtml);
	}
     
    
    function fnColSel(obj) {
    	if(!fnchekphone()) return;
        $(".citem").find(".sel").removeClass("sel");
        $(obj).addClass("sel");
        var desc = "套餐： " + (($(obj).attr("name")=="" || $(obj).attr("name")=="null") ? ($(".goodcuttab").find(".tabsel").text() + $(obj).text()) : $(obj).attr("name"));
        $('.pro_type').text(desc); 
        $('.pro_price').text("￥"+$(obj).attr("price"));
        
        $('.buy').attr("pid",$(obj).attr("id"));
        $('.buy').attr("price",$(obj).attr("price"));
    }

	
	var FLAG = true;
    function clearNoNum(obj)
    {
        obj.value = obj.value.replace(/[^\d]/g,"");
        $('#type_card').val("");
        $(".citem").find(".sel").removeClass("sel");
        $('.buy').attr("pid","");
        if((obj.value.length ==11) && FLAG){
        	$('#phone').blur();
        	FLAG = false;
        	fnloading("获取流量包数据...");
            XMLHttpURL_POST("../product?type=1&phone="+obj.value, function(data){
            	FLAG = true;
            	fnloading();
                var objs = eval("(" + data + ")");
                var producthtml =  '<div id="goodinfo2" style="display: block;"><div class="goodcuttab"id="goodcuttab"><div class="fl" onclick="fnShowProduct(1)" style="width:99%"><span class="tabsel">（暂支持）广东移动</span></div><div class="fl"onclick="fnShowProduct(2)" style="display:none;"><span class="">国内流量</span></div><div class="fr" onclick="fnShowProduct(3)" style="width:48%;display:none;"><span class="">全国流量</span></div></div>';
                	producthtml += '<div id="goodinfoDet">';
                var disint = 0;
                if(objs.s_products.length > 0) disint =1;
                producthtml += getStrhtml(objs.s_products,1);
//                if(objs.h_products.length > 0 && disint ==0) disint = 2;
//                producthtml += getStrhtml(objs.h_products,2);
//                if(objs.g_products.length > 0 && disint ==0) disint =3;
//                producthtml += getStrhtml(objs.g_products,3);
                
                producthtml += '</div>';
                if(producthtml.indexOf("fnColSel") < 0)
                	$('.testimonialWrapper').html("<div style='width:100%;height:150px;'>当前服务商没有可用流量套餐</div>");
                else
                	$('.testimonialWrapper').html(producthtml);
                
                fnShowProduct(disint);
                $('#type_card').val(objs.city+" "+objs.card_type);
                $('.buy').attr("amount",(objs.amount||0));
            });
        }
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
    	 var producthtml =  '<div id="cuthelp'+n+'" class="cuthelp"style="display: none; min-height: 120px;"><div class="citem">';
         for(var i = 0 ; i < rows.length; i++){
             producthtml += '<div onclick="fnColSel(this)" id="'+rows[i].product_id+'" price="'+rows[i].settlement_price+'" name="'+rows[i].specify_name+'">'+rows[i].product_name+'</div>';
         }
         producthtml += '</div></div>';
         return producthtml;
    }
    
    function fnchekphone(){
    	if($('#phone').val().length < 11)
    	{
    		mAlert("请输入有效的手机号码",function(){$('#phone').focus();});
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
			var obj_czk = fnPopDlg("钱包支付", '<div style="text-align:center;font-size:24px;"><input type="password" placeholder="请录入支付密码" id="czkPayPsw" style="height:40px;margin-bottom:0px;"></input><div style="text-align:center"><span class="redbtn mt12" style="width:60%">支付</span></div>',null,function(){fnloading();});
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
				XMLHttpURL_POST("../order?type=1&paytype=2&pas="+$.md5(pPsw)+"&phone="+ $('#phone').val() +"&product_id="+product_id+"&orderid="+localData.get("orderid"), function(data){
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
			XMLHttpURL_POST("../order?type=1&paytype=1&phone="+ $('#phone').val() +"&product_id="+product_id+"&orderid="+localData.get("orderid"), function(data){
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
			});
			setTimeout(function(){fnloading();}, 50000);
        });

    }
    
    function fnclearAppid(){
    	localData.remove('orderid');
        localData.remove('appid');
    }
    
    
    function fnShowProduct(n){
    	$(".goodcuttab").find(".tabsel").removeClass("tabsel");
    	$(".goodcuttab").find("span:eq("+(n-1)+")").addClass("tabsel");
    	$(".cuthelp").hide();	
    	$("#cuthelp"+n).show();
    }
    
    function clearAll(){
    	$('#type_card').val("");
    	$('#phone').val("");
    	fndefaulthtml();
    	$('.buy').removeAttr("pid");
        $('.buy').removeAttr("price");
        $('.pro_type').text("套餐: ");
        $('.pro_price').text("");
        localData.remove("orderid");
        localData.remove("appid");
        WXPAYWIN.remove();
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


    
	
	
	
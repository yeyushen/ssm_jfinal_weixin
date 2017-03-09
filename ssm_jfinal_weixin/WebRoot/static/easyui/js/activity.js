setTimeout(function(){
		wx_hycjcomsize();	
		$('#wx_hycj').datagrid({
		width:'auto',
		height:dg_height,
		nowrap: true,
		fitColumns: true,
		pagination:true,
		remoteSort: false,
		singleSelect:true,
		showFooter:true,
		rownumbers:true,
		url:'/asp/hycj.asp?action=query&sj='+localData.get("wx_id") + "&yema=1&yedx="+yedx,
		idField:'id',				
		columns:[[
		   {field:'cj_type',title:'类型',width:60,sortable:true,align:'center',
		  		formatter:function(value,rec){
	  				if(value==undefined){return}
	  				if(value==1){return "刮刮乐"}
	  				return "大转盘";
	  			} },
		   {field:'motif',title:'活动主题',width:100,sortable:true,align:'left',
		   		formatter:function(value,rec){
		   			if(value==undefined){return}
		  				if(value==""){return}
		   			return '<a href="javascript:void(0)" onclick="wx_hycjLook('+rec.id +')">'+value+'</a>';	
		   			} },
		   {field:'lv1',title:'一等奖',width:100,sortable:true,align:'center',
		  		formatter:function(value,rec){
		  				if(value==undefined){return}
		  				if(value==""){return}
		  				if (value.substr(0, 5) == "@WJF@"){value = "奖励 <a style='color:#f60'>" + value.substr(5, 100) + "</a> 积分"}
		  				return value + "<br>("+rec.lv1_djs+"/"+rec.lv1_zjs+"/"+rec.lv1_sl+")";
		  			}
		  	},
			{field:'lv2',title:'二等奖',width:100,sortable:true,align:'center',
		  		formatter:function(value,rec){
		  				if(value==undefined){return}
		  				if(value==""){return}
		  				if (value.substr(0, 5) == "@WJF@"){value = "奖励 <a style='color:#f60'>" + value.substr(5, 100) + "</a> 积分"}
		  				return value + "<br>("+rec.lv2_djs+"/"+rec.lv2_zjs+"/"+rec.lv2_sl+")";
		  			}
		  	},
			{field:'lv3',title:'三等奖',width:100,sortable:true,align:'center',
		  		formatter:function(value,rec){
		  				if(value==undefined){return}
		  				if(value==""){return}
		  				if (value.substr(0, 5) == "@WJF@"){value = "奖励 <a style='color:#f60'>" + value.substr(5, 100) + "</a> 积分"}
		  				return value + "<br>("+rec.lv3_djs+"/"+rec.lv3_zjs+"/"+rec.lv3_sl+")";
		  			}
		  	},
			{field:'lv4',title:'四等奖',width:100,sortable:true,align:'center',
		  		formatter:function(value,rec){
		  				if(value==undefined){return}
		  				if(value==""){return}
		  				if (value.substr(0, 5) == "@WJF@"){value = "奖励 <a style='color:#f60'>" + value.substr(5, 100) + "</a> 积分"}
		  				return value + "<br>("+rec.lv4_djs+"/"+rec.lv4_zjs+"/"+rec.lv4_sl+")";
		  			}
		  	},
			{field:'lv5',title:'五等奖',width:100,sortable:true,align:'center',
		  		formatter:function(value,rec){
		  				if(value==undefined){return}
		  				if(value==""){return}
		  				if (value.substr(0, 5) == "@WJF@"){value = "奖励 <a style='color:#f60'>" + value.substr(5, 100) + "</a> 积分"}
		  				return value + "<br>("+rec.lv5_djs+"/"+rec.lv5_zjs+"/"+rec.lv5_sl+")";
		  			}
		  	}  ,
			{field:'jf_need',title:'扣减积分',width:40,sortable:true,align:'right'
		  	}     ,
			{field:'cj_sl',title:'参与情况',width:100,sortable:true,align:'center',
		  		formatter:function(value,rec){
		  				if(value==undefined){return}
		  				return rec.ycj_sl+"/"+value;
		  			}
		  	}     ,
			{field:'cj_rq_begin',title:'活动时间',width:170,sortable:true,align:'center',
		  		formatter:function(value,rec){
		  				if(value==undefined){return}
		  				return paserdate(value) + " - " + paserdate(rec.cj_rq_end);
		  			}
		  	}  ,  
			{field:'is_stop',title:'中止',width:40,sortable:true,align:'center',
		  		formatter:function(value,rec){
		  				if(value==undefined){return}
		  				return checkBoxs(value);
		  			}
		  	},
			{field:'cz',title:'操作',width:150,align:'center',
				formatter:function(value,rec){
					if (rec.id==undefined || rec.id == ""){return ""}
					var srtn
					if(rec.is_stop == 1){
						srtn =  '<a style="color:#aaa">修改</a>';	
						srtn += '/<a style="color:#aaa">中止</a>'
					}else{
						srtn =  '<a href="javascript:void(0)" onclick="wx_hycjEdit('+rec.id +',' + rec.cj_type + ')">修改</a>';	
						srtn += '/<a href="javascript:void(0)" onclick="wx_hycjStop('+rec.id +')">中止</a>'
					}
					
					srtn += '/<a href="javascript:void(0)" onclick="wx_hycjZjmd('+rec.id +')">中奖名单</a>'
					return srtn;
				}
			}        	          	
			]],					
			onLoadSuccess:function(data){
     					datagrid_LoadError($(this),false);
					if(data.message!=undefined){
							
						  setTimeout(function(){$('#tabs').tabs('close','会员抽奖');})
						}
				},
			onLoadError:function(){
					datagrid_LoadError($(this),true);
			},
   				rowStyler:function(index,row,css){ //行样式
				return 'height:35px;';
			}
		});
	var p = $('#wx_hycj').datagrid('getPager');
	wx_hycjmakepage(p,1)
},0);
//分页
function wx_hycjmakepage(p,page){
if (p){
	$(p).pagination({
		displayMsg:'每页{to}条/共{total}记录',
		pageSize:yedx,
		pageNumber:page,
		showPageList:false,
		beforePageText:'第',
		afterPageText:'/{pages}页',
		onSelectPage:function(pageNumber,pageSize){
			$('#wx_hycj').datagrid({url:'/asp/hycj.asp?action=query&yema='+pageNumber+'&yedx='+pageSize+'&sj='+localData.get("wx_id")})
			var p=$('#wx_hycj').datagrid('getPager');
			wx_hycjmakepage(p,pageNumber);
		}
	});
}
}

function wx_hycjresize(){
wx_hycjcomsize();
$('#wx_hycj').datagrid('resize',{
	width:dg_width,
	height:dg_height
})
}
function wx_hycjcomsize(){
dgheightYedx(84, 35);
}
function dgheightYedx(hdiff,rowheight,titleheight,footrows){
	//hdiff dg的高度是tabs的高度减掉hdiff
	//rowheight 明细数据每行的高度 默认为21px
	//titleheight 标题栏和分页栏高度	默认为55px
	//footrows 合计行数	默认为2行
	if(!hdiff){hdiff = 0;}
	if(!rowheight){rowheight = 21;}
	if(!titleheight){titleheight = 55;}
	if(!footrows && footrows != 0){footrows = 2;}
	dg_height = $('#page').height - hdiff;
	dg_width = "auto";
	
	if(getwebType().ie){dg_width=$('#page').width-20;}
	if(isNaN(dg_height) || dg_height < 200){dg_height=200;}
	yedx = parseInt((dg_height - titleheight - 1 - 21 * footrows) / rowheight);
	if(yedx < 1 ){yedx=1;}	
}
function wx_QueryByID(id){	
var hycjlook = eval("(" + XMLHttpURL_p("/asp/hycj.asp?action=look&id="+id) + ")");
if (hycjlook.success == false) {
	alert_ui(hycjlook.error);
	return -1;
}
if(hycjlook.cj_type == 0){
	$('#wx_hycj_cj_type').text("大转盘");
	$('#wx_hycj_cj_type').attr("cj_type", "0");
}else{
	$('#wx_hycj_cj_type').text("刮刮卡");
	$('#wx_hycj_cj_type').attr("cj_type", "1");
}
$('#wx_hycj_motif').val(hycjlook.motif);
$('#wx_hycj_des').val(hycjlook.des);
$('#wx_hycj_cj_rq_begin').datebox("setValue", paserdateD(hycjlook.cj_rq_begin));
$('#wx_hycj_cj_rq_end').datebox("setValue", paserdateD(hycjlook.cj_rq_end));
$('#wx_hycj_lv1').val(hycjlook.lv1);
$('#wx_hycj_lv1_sl').val(hycjlook.lv1_sl);
$('#wx_hycj_lv1_zjs').text(hycjlook.lv1_zjs);
$('#wx_hycj_lv1_djs').text(hycjlook.lv1_djs);
$('#wx_hycj_lv2').val(hycjlook.lv2);
$('#wx_hycj_lv2_sl').val(hycjlook.lv2_sl);
$('#wx_hycj_lv2_zjs').text(hycjlook.lv2_zjs);
$('#wx_hycj_lv2_djs').text(hycjlook.lv2_djs);
$('#wx_hycj_lv3').val(hycjlook.lv3);
$('#wx_hycj_lv3_sl').val(hycjlook.lv3_sl);
$('#wx_hycj_lv3_zjs').text(hycjlook.lv3_zjs);
$('#wx_hycj_lv3_djs').text(hycjlook.lv3_djs);
$('#wx_hycj_lv4').val(hycjlook.lv4);
$('#wx_hycj_lv4_sl').val(hycjlook.lv4_sl);
$('#wx_hycj_lv4_zjs').text(hycjlook.lv4_zjs);
$('#wx_hycj_lv4_djs').text(hycjlook.lv4_djs);
$('#wx_hycj_lv5').val(hycjlook.lv5);
$('#wx_hycj_lv5_sl').val(hycjlook.lv5_sl);
$('#wx_hycj_lv5_zjs').text(hycjlook.lv5_zjs);
$('#wx_hycj_lv5_djs').text(hycjlook.lv5_djs);
$('#wx_hycj_cj_sl').val(hycjlook.cj_sl);
$('#wx_hycj_ycj_sl').val(hycjlook.ycj_sl);
$('#wx_hycj_hy_can').val(hycjlook.hy_can);
$('#wx_hycj_dj_des').val(hycjlook.dj_des);
$("#wx_hycj_show_zjs").attr("checked",(hycjlook.show_zjs == 1));
$('#wx_hycj_jf_need').val(hycjlook.jf_need);


wx_hycjSetJpDis(hycjlook.lv1, 1);
wx_hycjSetJpDis(hycjlook.lv2, 2);
wx_hycjSetJpDis(hycjlook.lv3, 3);
wx_hycjSetJpDis(hycjlook.lv4, 4);
wx_hycjSetJpDis(hycjlook.lv5, 5);
return 1;
}

function wx_hycjSetJpDis(as, ai){
	if (ai < 1 || ai > 5){return}
	if (as.substr(0, 5) == "@WJF@"){
		as = "奖励 " + as.substr(5, 100) + " 积分";
	}
	$('#wx_hycj_lv' + ai + '_dis').val(as);
}

function wx_hycjLook(id){
	if (wx_QueryByID(id) != 1){return}
	wx_hycjSetDisabled(true);		
	wx_hycjOpen($('#wx_hycj_cj_type').attr("cj_type"));
}
function wx_hycjStop(id){
	var jdata = $('#wx_hycj').datagrid('getData');
	var rowindex = $('#wx_hycj').datagrid('getRowIndex',id);
	if(jdata && rowindex >= 0){
		if (jdata.rows[rowindex].is_stop == 1){
			alert_ui("该活动已经中止了！")
			return;
		}
	} 		
	confirm_ui("活动中止后将立即失效，同时也无法重新启动！<br>您确定要中止该抽奖活动吗？", function(){
		var canadd = XMLHttpURL_p("/asp/hycj.asp?action=stop&id="+id+"&sj="+localData.get("wx_id"))
		if(canadd != "1"){
			alert_ui(canadd);
			return;
		}		
		alert_ui("抽奖活动已经成功中止！")
		$('#wx_hycj').datagrid('updateRow',{index:$('#wx_hycj').datagrid('getRowIndex',id),row:{is_stop: 1}});
	});		
}
function wx_hycjEdit(id, ai){	
	var canadd = XMLHttpURL_p("/asp/hycj.asp?action=canedit&id="+id+"&sj="+localData.get("wx_id") + "&tp=" + ai)
	if(canadd != "1"){
		alert_ui(canadd);
		return;
	}						
	if (wx_QueryByID(id) != 1){return}
	wx_hycjSetDisabled(false);	
	if (parseInt($('#wx_hycj_ycj_sl').val()) > 0){
	
	
		$('#wx_hycj_jp1').linkbutton('disable');
		$('#wx_hycj_lv1_sl').attr('disabled', true);
		$('#wx_hycj_jp2').linkbutton('disable');
		$('#wx_hycj_lv2_sl').attr('disabled', true);
		$('#wx_hycj_jp3').linkbutton('disable');
		$('#wx_hycj_lv3_sl').attr('disabled', true);
		$('#wx_hycj_jp4').linkbutton('disable');
		$('#wx_hycj_lv4_sl').attr('disabled', true);
		$('#wx_hycj_jp5').linkbutton('disable');
		$('#wx_hycj_lv5_sl').attr('disabled', true);
		
		$('#wx_hycj_cj_rq_begin').datebox('disable');
	}		
	
	
	$('#wx_hycj_save').unbind().bind("click", function(){
		wx_hycjsave(id)
	});
	wx_hycjOpen($('#wx_hycj_cj_type').attr("cj_type"));
}
function wx_hycjSetDisabled(ab){
	$('#wx_hycj_motif').attr('disabled', ab);
	$('#wx_hycj_des').attr('disabled', ab);
	$('#wx_hycj_lv1_sl').attr('disabled', ab);
	$('#wx_hycj_lv2_sl').attr('disabled', ab);
	$('#wx_hycj_lv3_sl').attr('disabled', ab);
	$('#wx_hycj_lv4_sl').attr('disabled', ab);
	$('#wx_hycj_lv5_sl').attr('disabled', ab);
	$('#wx_hycj_cj_sl').attr('disabled', ab);
	$('#wx_hycj_hy_can').attr('disabled', ab);
	$('#wx_hycj_dj_des').attr('disabled', ab);	
	$("#wx_hycj_show_zjs").attr("disabled",ab);
	$("#wx_hycj_jf_need").attr("disabled",ab);
	if (ab){
		$('#wx_hycj_bl').combobox('disable');
		$('#wx_hycj_cj_rq_begin').datebox('disable');
		$('#wx_hycj_cj_rq_end').datebox('disable');
		$('#wx_hycj_bl_insert').linkbutton('disable'); 	
		$('#wx_hycj_save').hide();		
		$('#wx_hycj_jp1').linkbutton('disable');
		$('#wx_hycj_jp2').linkbutton('disable');
		$('#wx_hycj_jp3').linkbutton('disable');
		$('#wx_hycj_jp4').linkbutton('disable');
		$('#wx_hycj_jp5').linkbutton('disable');	
	}else{
		$('#wx_hycj_bl').combobox('enable');
		$('#wx_hycj_cj_rq_begin').datebox('enable');
		$('#wx_hycj_cj_rq_end').datebox('enable');
		$('#wx_hycj_bl_insert').linkbutton('enable'); 	
		$('#wx_hycj_save').show();	
		$('#wx_hycj_jp1').linkbutton('enable');
		$('#wx_hycj_jp2').linkbutton('enable');
		$('#wx_hycj_jp3').linkbutton('enable');
		$('#wx_hycj_jp4').linkbutton('enable');
		$('#wx_hycj_jp5').linkbutton('enable');	
	}
	if($('#wx_hycj_cj_type').attr("cj_type") == "0"){
		$('#tr_lv4').css('display', "none"); 		
		$('#tr_lv5').css('display', "none"); 		
	}else{
		$('#tr_lv4').css('display', ""); 		
		$('#tr_lv5').css('display', ""); 		
	}
	$(".datebox :text").attr("readonly","readonly");

}
function wx_hycjAdd(ai){			
	
	if(ai == 0){
		$('#wx_hycj_cj_type').text("大转盘");
		$('#wx_hycj_cj_type').attr("cj_type", "0");
	}else{
		$('#wx_hycj_cj_type').text("刮刮卡");
		$('#wx_hycj_cj_type').attr("cj_type", "1");
	}
	$('#wx_hycj_motif').val('');
	$('#wx_hycj_des').val('');
	$('#wx_hycj_cj_rq_begin').datebox('setValue', "");
	$('#wx_hycj_cj_rq_end').datebox('setValue', "");
	$('#wx_hycj_lv1').val('');
	$('#wx_hycj_lv1_dis').val('');
	$('#wx_hycj_lv1_sl').val('');
	$('#wx_hycj_lv1_zjs').text('');
	$('#wx_hycj_lv1_djs').text('');
	$('#wx_hycj_lv2').val('');
	$('#wx_hycj_lv2_dis').val('');
	$('#wx_hycj_lv2_sl').val('');
	$('#wx_hycj_lv2_zjs').text('');
	$('#wx_hycj_lv2_djs').text('');
	$('#wx_hycj_lv3').val('');
	$('#wx_hycj_lv3_dis').val('');
	$('#wx_hycj_lv3_sl').val('');
	$('#wx_hycj_lv3_zjs').text('');
	$('#wx_hycj_lv3_djs').text('');
	$('#wx_hycj_lv4').val('');
	$('#wx_hycj_lv4_dis').val('');
	$('#wx_hycj_lv4_sl').val('');
	$('#wx_hycj_lv4_zjs').text('');
	$('#wx_hycj_lv4_djs').text('');
	$('#wx_hycj_lv5').val('');
	$('#wx_hycj_lv5_dis').val('');
	$('#wx_hycj_lv5_sl').val('');
	$('#wx_hycj_lv5_zjs').text('');
	$('#wx_hycj_lv5_djs').text('');
	$('#wx_hycj_cj_sl').val('');
	$('#wx_hycj_ycj_sl').val('');
	$('#wx_hycj_hy_can').val('');
	$('#wx_hycj_dj_des').val('');
	$('#wx_hycj_jf_need').val('');
	wx_hycjSetDisabled(false);
	$('#wx_hycj_save').unbind().bind("click", function(){
		wx_hycjsave(0);			
	});
	wx_hycjOpen(ai);
}
function wx_hycjsave(id){
	var cj_type = parseInt($('#wx_hycj_cj_type').attr("cj_type"));
	var motif = $('#wx_hycj_motif').val();
	if (motif == ""){alert_ui("请输入【活动主题】！", function(){$('#wx_hycj_motif').focus()});return;}
	
	var des = $('#wx_hycj_des').val();
	if (des == ""){alert_ui("请输入【活动说明】！", function(){$('#wx_hycj_des').focus()});return;}
	var dj_des = $('#wx_hycj_dj_des').val();
	
	var cj_rq_begin = $('#wx_hycj_cj_rq_begin').datebox('getValue') || "";
	var cj_rq_end = $('#wx_hycj_cj_rq_end').datebox('getValue') || "";
	if (isDate(cj_rq_begin) == false){alert_ui("请输入有效的【开始日期】！", function(){$('#wx_hycj_cj_rq_begin').focus()});return;}
	if (isDate(cj_rq_end) == false){alert_ui("请输入有效的【结束日期】！", function(){$('#wx_hycj_cj_rq_end').focus()});return;}
	if (cj_rq_end < cj_rq_begin){alert_ui("【结束日期】不能早于【开始日期】！", function(){$('#wx_hycj_cj_rq_end').focus()});return;}
	
	var lv1 = $('#wx_hycj_lv1').val() || "";
	if (lv1 == ""){alert_ui("请输入【一等奖】对应的奖品！", function(){$('#wx_hycj_lv1').focus()});return;}
	var lv1_sl = parseInt($('#wx_hycj_lv1_sl').val()) || 0;
	if (lv1_sl < 1){alert_ui("请输入【一等奖】对应的奖品数量！", function(){$('#wx_hycj_lv1_sl').focus()});return;}		
	var lv2 = $('#wx_hycj_lv2').val() || "";
	var lv3 = $('#wx_hycj_lv3').val() || "";
	var lv4 = $('#wx_hycj_lv4').val() || "";
	var lv5 = $('#wx_hycj_lv5').val() || "";
	var lv2_sl = parseInt($('#wx_hycj_lv2_sl').val()) || 0;
	var lv3_sl = parseInt($('#wx_hycj_lv3_sl').val()) || 0;
	var lv4_sl = parseInt($('#wx_hycj_lv4_sl').val()) || 0;
	var lv5_sl = parseInt($('#wx_hycj_lv5_sl').val()) || 0;		
	if(lv2 == "" && (cj_type == 0 || lv3 != "" || lv4 != "" || lv5 != "" || lv2_sl > 0)){alert_ui("请输入【二等奖】对应的奖品！", function(){$('#wx_hycj_lv2').focus()});return;}
	if (lv2_sl < 1 && lv2 != ""){alert_ui("请输入【二等奖】对应的奖品数量！", function(){$('#wx_hycj_lv2_sl').focus()});return;}
	if(lv3 == "" && (cj_type == 0 || lv4 != "" || lv5 != "" || lv3_sl > 0)){alert_ui("请输入【三等奖】对应的奖品！", function(){$('#wx_hycj_lv3').focus()});return;}
	if (lv3_sl < 1 && lv3 != ""){alert_ui("请输入【三等奖】对应的奖品数量！", function(){$('#wx_hycj_lv3_sl').focus()});return;}
	if (cj_type == 1){
		if(lv4 == "" && ( lv5 != "" || lv4_sl > 0)){alert_ui("请输入【四等奖】对应的奖品！", function(){$('#wx_hycj_lv4').focus()});return;}
		if (lv4_sl < 1 && lv4 != ""){alert_ui("请输入【四等奖】对应的奖品数量！", function(){$('#wx_hycj_lv4_sl').focus()});return;}
		if(lv5 == "" && ( lv5_sl > 0)){alert_ui("请输入【五等奖】对应的奖品！", function(){$('#wx_hycj_lv5').focus()});return;}
		if (lv5_sl < 1 && lv5 != ""){alert_ui("请输入【五等奖】对应的奖品数量！", function(){$('#wx_hycj_lv5_sl').focus()});return;}
	}
	
	lv2 = (lv2 == undefined ? "": lv2);
	lv3 = (lv3 == undefined ? "": lv3);
	lv4 = (lv4 == undefined ? "": lv4);
	lv5 = (lv5 == undefined ? "": lv5);	
	
	
	var cj_sl = parseInt($('#wx_hycj_cj_sl').val()) || 0;
	if (cj_sl < 1){alert_ui("请输入【发行数量】！", function(){$('#wx_hycj_cj_sl').focus()});return;}
	
	
	
	var lv1_wzjs = lv1_sl - (parseInt($('#wx_hycj_lv1_zjs').text()) || 0);
	var lv2_wzjs = lv2_sl - (parseInt($('#wx_hycj_lv2_zjs').text()) || 0);
	var lv3_wzjs = lv3_sl - (parseInt($('#wx_hycj_lv3_zjs').text()) || 0);
	var lv4_wzjs = lv4_sl - (parseInt($('#wx_hycj_lv4_zjs').text()) || 0);
	var lv5_wzjs = lv5_sl - (parseInt($('#wx_hycj_lv5_zjs').text()) || 0);			
	var ycj_sl = lv1_wzjs + lv2_wzjs + lv3_wzjs + lv4_wzjs + lv5_wzjs + (parseInt($('#wx_hycj_ycj_sl').val()) || 0);
	if (cj_sl < ycj_sl){alert_ui("【发行数量】不得小于【累计抽奖数量】与奖品剩余数量的总和：<a class='tipNum'>" + ycj_sl + "</a> ！", function(){$('#wx_hycj_cj_sl').focus()});return;}
	
	
	var hy_can = parseInt($('#wx_hycj_hy_can').val()) || 0;
	if (hy_can < 1){alert_ui("请输入【每人限抽数量】！", function(){$('#wx_hycj_hy_can').focus()});return;}
	
	if($("#wx_hycj_show_zjs").attr("checked") == true){
		show_zjs = 1;
	}else{
		show_zjs = 0;
	}
	var jf_need = parseInt($('#wx_hycj_jf_need').val()) || 0;		
	if (jf_need < 0){alert_ui("抽奖扣减积分不能小于0！", function(){$('#wx_hycj_jf_need').focus()});return;}
	
	wait_begin("正在保存活动信息，请稍候...");
	des = des.replace(new RegExp("\n","gm"), "<br>");
	dj_des = dj_des.replace(new RegExp("\n","gm"), "<br>");  //modify by jwt 用<br>替换换行符，@vbCrLf@在asp输出，json里面解析错误
	var strjson='{"sj":"'+localData.get("wx_id")+'","id":'+id+',"cj_type":'+cj_type+',"motif":"'+escape(motif)+'","des":"'+escape(des)+'","dj_des":"'+escape(dj_des)+'","cj_rq_begin":"'+cj_rq_begin+'","cj_rq_end":"'+cj_rq_end+'","show_zjs":' + show_zjs + ',"cj_sl":' + cj_sl + ',"hy_can":' + hy_can + ',"lv1":"'+escape(lv1)+'","lv1_sl":' + lv1_sl + ',"lv2":"'+escape(lv2)+'","lv2_sl":' + lv2_sl + ',"lv3":"'+escape(lv3)+'","lv3_sl":' + lv3_sl + ',"lv4":"'+escape(lv4)+'","lv4_sl":' + lv4_sl + ',"lv5":"'+escape(lv5)+'","lv5_sl":' + lv5_sl + ',"jf_need":' + jf_need + '}'
	$.post('/asp/hycj.asp?action=save',strjson,function(data){				
		if(data>= 0){
			wait_begin()
			alert_ui("活动信息保存成功！");	 		
			wx_hycjgrantnow();		
			$("#wx_hycj").datagrid("reload");	
			wx_hycjAddCancel();
	 	}
	 	else{wait_begin();alert_ui("活动信息保存失败，请重试！\r\n" + data);return;}
	})
	wait_begin()

}
function wx_hycjOpen(ai){
	var h = 520;
	if(ai == 0){
		h -= 54;
	}
	if (getwebType().ie){h += 25}
	$('#wx_hycj_add').window({
	title:"会员抽奖设置",
		width: 720,
		height: h,
		minimizable:false,
		maximizable:false,
		resizable:false,
		modal:true,
		top:(HEIGHT - h) / 2,
		left:(WIDTH - 720) / 2
	});	
	$('#wx_hycj_add').show();
	$('#wx_hycj_add').window('open');		
}
function wx_hycjAddCancel(){
	stopDjInterval();
	$('#wx_hycj_add').window('close');
}	

function wx_hycjInsert(){
	var v = $('#wx_hycj_bl').combobox('getValue');
	if (v != ""){
		$("#wx_hycj_des").insertContent(v)	
	}
}
function wx_hycjDj(){
	var canadd = eval("(" + XMLHttpURL_p("/asp/hycj.asp?action=dj&sj="+localData.get("wx_id")) + ")")
	if(canadd.success == false){
		alert_ui(canadd.error);
		return;
	}		
	var stip = "本次兑奖码为：&nbsp;&nbsp;&nbsp;&nbsp;<a id='wxdjm' class='tipNum' style='margin-right:20px;font-size:36pt;font-weight:bold'>" + canadd.djm + "</a><a id='wxdjmsx' style='font-size:14pt;'>300 秒后失效</a> <br><br>请将该兑奖码告知兑奖者，并请兑奖者直接在微信帐号中回复该兑奖码，完成兑奖操作！"
	if(canadd.ticket != ""){
		stip = "兑奖方法一：[<a style='color:green'>手动回复兑奖码进行兑奖</a>]<div style='border-bottom:1px solid #7EC2FA;padding:5px 5px 5px 30px;margin-bottom:10px'>"+stip+"</div>"
		stip += "兑奖方法二：[<a style='color:green'>微信扫描二维码进行兑奖</a>]<div style='height:215px;margin:0px 5px -10px 5px;text-align:center'><img align='center' width='215px' src='https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+encodeURIComponent(canadd.ticket)+"'></img></div>"
	}
	stip += "<div style='padding:5px 5px 5px 5px;margin-top:10px;border-top:1px solid #7EC2FA'>任意电脑访问以下网址，均可以进行兑奖操作：<br><br><a target='_blank' href='dj.html?u="+localData.get("token")+"'>http://wx.jys.com.cn/dj.html?u="+localData.get("token")+"</a></div>"
	stip = "<div style='margin:5px 0px 5px 0px;padding-bottom:5px;border-bottom:1px solid #7EC2FA ;'><a style='color:red;font-size:11pt;line-height:18px;'>如果会员在5分钟内完成兑奖，当前界面会自动弹出兑奖商品列表，在弹出之前请勿关闭当前窗口，以免兑错奖品！</a></div>" + stip
	alert_noicon(stip,stopDjInterval)//"本次兑奖码为：<a class='tipNum'>" + canadd.djm + "</a> <br><br>请将该兑奖码告知兑奖者，并请兑奖者直接在微信帐号中回复该兑奖码，完成兑奖操作！<br><img align='center' width='250px' src='https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+canadd.ticket+"'></img>")		
	stopDjInterval();
	ii_interval = setInterval(function(){		
		var rtn = XMLHttpURL_p("/asp/hycj.asp?action=checkdj&sj="+localData.get("wx_id") + "&djm=" + canadd.djm)
		//兑奖码已经无效
		if (rtn == "-1"){
			document.getElementById("wxdjmsx").innerText = "已失效"
			document.getElementById("wxdjm").style.textDecoration = "line-through"
			stopDjInterval();
		}else if (rtn != "0"){
			stopDjInterval();
			$('.messager-body').window('close');
			alert_noicon(rtn,function(){wx_hycjDj();});
		}		
		ii_times ++;
		document.getElementById("wxdjmsx").innerText = (300 - ii_times * 5) + " 秒后失效"
		//最多执行5分钟
		if (ii_times >= 60){
			document.getElementById("wxdjmsx").innerText = "已失效"
			document.getElementById("wxdjm").style.textDecoration = "line-through"
			stopDjInterval();
		}
	},5000);
}
function stopDjInterval(){
	if(ii_interval > 0){clearInterval(ii_interval);ii_interval = 0}
	ii_times = 0;
}
var ii_interval = 0, ii_times;
function wx_hycjZjmd(id){
$('#wx_hycj_zjmd').datagrid({
	width:'auto',
	height:HEIGHT*(0.8),
	fitColumns: true,
	singleSelect:true,
	pagination:true,
	showFooter:false,
	url:'/asp/hycj.asp?action=zjmd&id='+id+"&sj=" ,
	remoteSort: false,
	rownumbers:true,
	columns:[[
		{field:'mobile',title:'微信卡号',width:60,sortable:true,align:'center'},
		{field:'nickname',title:'昵称',width:60,sortable:true,align:'left'},
		{field:'lv_zj',title:'奖项',width:30,sortable:true,align:'center',
			formatter:function(value,rec){
				return "一二三四五".substr(parseInt(value) - 1, 1 ) + "等奖"
			}},
		{field:'rq_zj',title:'中奖时间',width:120,sortable:true,align:'center',
			formatter:function(value,rec){
				if(value!="" && value!=undefined && value!=null){
		  			return paserdate(value)
		  	}
			}
		},
		{field:'rq_dj',title:'兑奖时间',width:120,sortable:true,align:'center',
			formatter:function(value,rec){
				if(value!="" && value!=undefined && value!=null){
		  			return paserdate(value)
		  	}
			}
		}
	]],
	onLoadSuccess:function(data){
			datagrid_LoadError($(this),false);
		},
	onLoadError:function(){
			datagrid_LoadError($(this),true);
		}
});
$('#w_wx_hycj_zjmd').show()
$('#w_wx_hycj_zjmd').window({
		title:"中奖名单",
		width: 700,
		minimizable:false,
		maximizable:false,
		resizable:false,
		modal:true,
		height: HEIGHT*(0.8)+38,
		top:(HEIGHT - (HEIGHT*(0.8)+38)) / 2,
		left:(WIDTH - 700) / 2
})
$('#w_wx_hycj_zjmd').window('open')
}

function djHelp(){
	alert_noicon("兑奖操作说明：");
}

function wx_hycjjpset(ai){
	$('.l-btn-text').removeClass("l-btn-focus");
	var s = $('#wx_hycj_lv' + ai).val();
		$("#wx_hycj_jp_wpradio").attr("checked", s.substr(0, 5) != "@WJF@")
		$("#wx_hycj_jp_jfradio").attr("checked", s.substr(0, 5) == "@WJF@")
	if (s.substr(0, 5) == "@WJF@"){
		$("#wx_hycj_jp_jfinput").val(s.substr(5, 100));
		$("#wx_hycj_jp_wpinput").val('');	
		wx_hycjJpSetSelect(2);
	}else{
		$("#wx_hycj_jp_jfinput").val('');
		$("#wx_hycj_jp_wpinput").val(s);		
		wx_hycjJpSetSelect(1);
	}
	
	$('#wx_hycjJpSetSave').unbind().bind("click", function(){
		var ss;
		if ($("#wx_hycj_jp_wpradio").attr("checked")){
			ss = $("#wx_hycj_jp_wpinput").val();		
			if (ss == ""){
				alert_ui("请录入物品名称！", function(){$("#wx_hycj_jp_wpinput").focus().select()});
				return
			}
		}else{
			ss = $("#wx_hycj_jp_jfinput").val();		
			if (ss == ""){
				alert_ui("请录入积分值！", function(){$("#wx_hycj_jp_jfinput").focus().select()});
				return
			}
			if (parseFloat(ss) <= 0){
				alert_ui("积分值必须大于0！", function(){$("#wx_hycj_jp_jfinput").focus().select()});
				return
			}
			if (parseFloat(ss) != parseInt(ss)){
				alert_ui("积分值必须为整数！", function(){$("#wx_hycj_jp_jfinput").focus().select()});
				return
			}
			ss = "@WJF@" + ss ;
		}
		$('#wx_hycj_lv' + ai).val(ss);
		wx_hycjSetJpDis(ss, ai);	
		wx_hycjJpSetCancel();				
	});
	
	
	$('#w_wx_hycj_jpset').show()
	$('#w_wx_hycj_jpset').window({
			title:"【" + "一二三四五".substr(ai - 1, 1) + "等奖】奖品设置",
			width: 500,
			minimizable:false,
			maximizable:false,
			resizable:false,
			modal:true,
			height: 180,
			top:(HEIGHT - 180) / 2,
			left:(WIDTH - 500) / 2
	})
	$('#w_wx_hycj_jpset').window('open')
}
function wx_hycjJpSetCancel(){
	$('#w_wx_hycj_jpset').window('close');
}
function wx_hycjJpSetSelect(ai){
	$("#wx_hycj_jp_wpinput").attr("disabled", ai == 2)
	$("#wx_hycj_jp_jfinput").attr("disabled", ai == 1)
	setTimeout(function(){
		if (ai == 1){
			$("#wx_hycj_jp_wpinput").focus().select();	
		}else{
			$("#wx_hycj_jp_jfinput").focus().select();	
		}
	},100);
}

//datagrid加载错误或成功时的处理
function datagrid_LoadError(dg, ab){
	if (ab){
		var dgp = dg.parent();
		if(!dgp){
			alert_ui("数据加载失败！");
			return;
		}
		if($('#dgerror_'+dg.attr("id"))[0]){
			$('#dgerror_'+dg.attr("id"))[0].style.left=dg.datagrid("getPanel").position().left+"px";
			$('#dgerror_'+dg.attr("id"))[0].style.top=dg.datagrid("getPanel").position().top+"px";
			$('#dgerror_'+dg.attr("id"))[0].style.width=dg.datagrid("getPanel").width()+"px";
			$('#dgerror_'+dg.attr("id"))[0].style.height=dg.datagrid("getPanel").height()+"px";
			$('#dgerror_'+dg.attr("id")).show();
		}
		else{
			var div_err = "<div id='dgerror_"+dg.attr("id")+"' align='center' style='left:"+dg.datagrid("getPanel").position().left+"px;top:"+dg.datagrid("getPanel").position().top+"px;width:"+dg.datagrid("getPanel").width()+"px;height:"+dg.datagrid("getPanel").height()+"px;line-height:"+dg.datagrid("getPanel").height()+"px;background:#fff;font-size:20px;' >数据加载失败！</div>"
			$(div_err).appendTo(dgp);
		}
	}
	else{ //隐藏
		if($('#dgerror_'+dg.attr("id"))[0]){$('#dgerror_'+dg.attr("id")).hide();}
	}	
}

function alert_ui(strin, fn, icon){
	alert_ui(strin, fn, icon, "提示")
}
function alert_ui(strin, fn, icon, strtitle){
	$.messager.defaults ={ok:'确定',cancel:'取消'}
	var str = String(strin);
	var reg=new RegExp("\r\n","gm")
	var reg1=new RegExp("\n","gm")
	var reg2=new RegExp("\r","gm")
	str=str.replace(reg,"<br>")
	str=str.replace(reg1,"<br>")
	str=str.replace(reg2,"<br>")
	if(icon == undefined){icon="info"}
	if(strtitle == undefined){strtitle="提示"}
	
	$.messager.alert(strtitle,str, icon,function(){
		if(fn){fn()}
 	});
 	setTimeout(function(){
 		$('div.messager-button').find('a.l-btn').focus();
 		$('div.messager-button').find("a.l-btn").keydown(function(e){
 			if(e.keyCode == 27){$('.messager-body').window('close')}
 		})
 	},50)
}
(function($) {
	  $.fn.insertContent = function(myValue, t) {
		var $t = $(this)[0];
		if (document.selection) { //ie
			this.focus();
			var sel = document.selection.createRange();
			sel.text = myValue;
			this.focus();
			sel.moveStart('character', -l);
			var wee = sel.text.length;
			if (arguments.length == 2) {
				var l = $t.value.length;
				sel.moveEnd("character", wee + t);
				t <= 0 ? sel.moveStart("character", wee - 2 * t - myValue.length) : sel.moveStart("character", wee - t - myValue.length);

				sel.select();
			}
		} else if ($t.selectionStart || $t.selectionStart == '0') {
			var startPos = $t.selectionStart;
			
			var endPos = $t.selectionEnd;
			var scrollTop = $t.scrollTop;
			$t.value = $t.value.substring(0, startPos) + myValue + $t.value.substring(endPos, $t.value.length);
			this.focus();
			$t.selectionStart = startPos + myValue.length;
			$t.selectionEnd = startPos + myValue.length;
			$t.scrollTop = scrollTop;
			if (arguments.length == 2) {

				$t.setSelectionRange(startPos - t, $t.selectionEnd + t);
				this.focus();
			}
		}
		else {
			this.value += myValue;
			this.focus();
		}       
	  };
	})(jQuery);
function alert_noicon(strin, fn){
	alert_ui(strin, fn,"")
}
function onmyinput(o)
{
 if(o.value.length>=o.getAttribute("maxlength"))
 {
  if(o.value.length>o.getAttribute("maxlength"))
   o.value = o.value.substring(0,o.getAttribute("maxlength"));
  return false;
 }
 return true;
}
var HEIGHT,WIDTH;
function getDSize(){
	HEIGHT=document.documentElement.clientHeight;
	WIDTH=document.documentElement.clientWidth;
}
getDSize();
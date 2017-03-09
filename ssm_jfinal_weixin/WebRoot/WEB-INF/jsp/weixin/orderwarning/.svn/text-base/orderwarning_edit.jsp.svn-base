<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en"> 
<head>
	<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/index/top.jsp"%>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					
					<form action="orderwarning/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" id="warning_tagid" name="warning_tagid" value="${pd.warning_tagid }" />
						<input type="hidden" id="tr" name="tr" value="${pd.tr }" />
						<div id="zhongxin">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:155px;text-align: right;padding-top: 13px;"><b>设置项名称:</b></td>
								<c:if test="${pd.warning_tagid != '0' }"> 
								<td>
								<input type="text" name="warning_tagname" id="warning_tagname" value="${pd.warning_tagname}" maxlength="255" placeholder="这里输入设置项名称" title="设置项名称" style="width:98%;" />
								</td>
								</c:if>
								<c:if test="${pd.warning_tagid == '0' }"> 
								<td>
								<b>${pd.warning_tagname}</b>
								</td>
								</c:if>
							</tr>
							<tr>
								<td style="width:155px;text-align: right;padding-top: 13px;"><b>下单小时段设置:</b></td>
								<td>
								<input type="text" name="order_hour" id="order_hour" value="${pd.order_hour}" maxlength="255" placeholder="这里输入下单小时段" title="下单小时段设置" style="width:98%;" />
								</td>
							</tr> 
							<tr>
								<td style="width:155px;text-align: right;padding-top: 13px;"><b>下单数阀值:</b></td>
								<td>
								<input type="text" name="max_order_num" id="max_order_num" value="${pd.max_order_num}" maxlength="255" placeholder="这里输入下单数阀值" title="下单数阀值" style="width:98%;" />
								</td>							
							</tr>
							<tr>
								<td style="width:155px;text-align: right;padding-top: 13px;"><b>未支付订单数阀值:</b></td>
								<td>
								<input type="text" name="max_unpay_num" id="max_unpay_num" value="${pd.max_unpay_num}" maxlength="255" placeholder="这里输入未支付订单数阀值" title="未支付订单数阀值" style="width:98%;" />
								</td>							
							</tr>
							<tr>
								<td style="width:155px;text-align: right;padding-top: 13px;"><b>消费金额小时段设置:</b></td>
								<td>
								<input type="text" name="money_hour" id="money_hour" value="${pd.money_hour}" maxlength="255" placeholder="这里输入消费金额小时段" title="消费金额小时段" style="width:98%;" />
								</td>							
							</tr>
							<tr>
								<td style="width:155px;text-align: right;padding-top: 13px;"><b>超过金额阀值:</b></td>
								<td>
								<input type="text" name="max_consume_money" id="max_consume_money" value="${pd.max_consume_money}" maxlength="255" placeholder="这里输入超过金额发出通知阀值" title="超过金额发出通知阀值" style="width:98%;" />
								</td>							
							</tr>
							<tr>
								<td style="width:155px;text-align: right;padding-top: 13px;"><b>发出通知还是阻止下单:</b></td>
								<td id="is_order_continueId">
									<select class="chosen-select form-control" name="is_order_continue"
									id="is_order_continue" data-placeholder="请选择是否阻止会员继续下单"
									style="vertical-align:top;" style="width:98%;">
										<option value="0"
											<c:if test="${pd.is_order_continue==0 }">selected</c:if>>仅通知</option>
										<option value="1"
											<c:if test="${pd.is_order_continue==1 }">selected</c:if>>通知并阻止下单</option>
								</select>
								</td>							
							</tr>
							<tr>
								<td class="center" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
						</div>
						
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
						
					</form>
	
					<div id="zhongxin2" class="center" style="display:none"><img src="static/images/jzx.gif" style="width: 50px;" /><br/><h4 class="lighter block green"></h4></div>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</div>
<!-- /.main-container -->


	<!-- 页面底部js¨ -->
	
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 删除时确认窗口 -->
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		
				//下拉框
		if(!ace.vars['touch']) {
			$('.chosen-select').chosen({allow_single_deselect:true}); 
			$(window)
			.off('resize.chosen')
			.on('resize.chosen', function() {
				$('.chosen-select').each(function() {
					 var $this = $(this);
					 $this.next().css({'width': $this.parent().width()});
				});
			}).trigger('resize.chosen');
			$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
				if(event_name != 'sidebar_collapsed') return;
				$('.chosen-select').each(function() {
					 var $this = $(this);
					 $this.next().css({'width': $this.parent().width()});
				});
			});
			$('#chosen-multiple-style .btn').on('click', function(e){
				var target = $(this).find('input[type=radio]');
				var which = parseInt(target.val());
				if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
				 else $('#form-field-select-4').removeClass('tag-input-style');
			});
		}
		
		function save(){
			var reg=/^[-\+]?\d+(\.\d+)?$/;		//js正则表达式,校验double类型
			if($("#warning_tagname").val()==""){
						$("#warning_tagname").tips({
							side:3,
				            msg:'请输入设置项名称',
				            bg:'#AE81FF',
				            time:1
				        });
						$("#warning_tagname").focus();
					return false;
				}
		
			if($("#order_hour").val()==""){
					$("#order_hour").tips({
						side:3,
			            msg:'请输入订单预警限制小时数',
			            bg:'#AE81FF',
			            time:1
			        });
					$("#order_hour").focus();
				return false;
			}
			
			var order_hour = document.getElementById("order_hour");  
    		if(!reg.test(order_hour.value)){  
       		$("#order_hour").tips({
				side:3,
	            msg:'格式错误！请输入数字类型',
	            bg:'#AE81FF',
	            time:1
	        });
			$("#order_hour").focus();
				return false;
			}
			
			if($("#max_order_num").val()==""){
					$("#max_order_num").tips({
						side:3,
			            msg:'下单数阀值',
			            bg:'#AE81FF',
			            time:1
			        });
					$("#max_order_num").focus();
				return false;
			}
			
			var max_order_num = document.getElementById("max_order_num");  
    		if(!reg.test(max_order_num.value)){  
       		$("#max_order_num").tips({
				side:3,
	            msg:'格式错误！请输入数字类型',
	            bg:'#AE81FF',
	            time:1
	        });
			$("#max_order_num").focus();
				return false;
			}
			
			if($("#max_unpay_num").val()==""){
					$("#max_unpay_num").tips({
						side:3,
			            msg:'未支付订单数阀值',
			            bg:'#AE81FF',
			            time:1
			        });
					$("#max_unpay_num").focus();
				return false;
			}
			
			var max_unpay_num = document.getElementById("max_unpay_num");  
    		if(!reg.test(max_unpay_num.value)){  
       		$("#max_unpay_num").tips({
				side:3,
	            msg:'格式错误！请输入数字类型',
	            bg:'#AE81FF',
	            time:1
	        });
			$("#max_unpay_num").focus();
				return false;
			}
			
			if($("#money_hour").val()==""){
					$("#money_hour").tips({
						side:3,
			            msg:'请输入预警金额限制小时数',
			            bg:'#AE81FF',
			            time:1
			        });
					$("#money_hour").focus();
				return false;
			}
			
			var money_hour = document.getElementById("money_hour");  
    		if(!reg.test(money_hour.value)){  
       		$("#money_hour").tips({
				side:3,
	            msg:'格式错误！请输入数字类型',
	            bg:'#AE81FF',
	            time:1
	        });
			$("#money_hour").focus();
				return false;
			}
			
			if($("#max_consume_money").val()==""){
					$("#max_consume_money").tips({
						side:3,
			            msg:'超过金额发出通知阀值',
			            bg:'#AE81FF',
			            time:1
			        });
					$("#max_consume_money").focus();
				return false;
			}
			
			var max_consume_money = document.getElementById("max_consume_money");  
    		if(!reg.test(max_consume_money.value)){  
       		$("#max_consume_money").tips({
				side:3,
	            msg:'格式错误！请输入数字类型',
	            bg:'#AE81FF',
	            time:1
	        });
			$("#max_consume_money").focus();
				return false;
			}
			
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
			
		}
		</script>
</body>
</html>
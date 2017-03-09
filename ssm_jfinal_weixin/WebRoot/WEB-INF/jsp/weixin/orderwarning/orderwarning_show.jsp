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
								<td style="width:70px;text-align: right;padding-top: 13px;"><b>设置项名称:</b></td>
								<td style="width:70px;text-align: left;padding-top: 13px;"><b>${pd.warning_tagname}</b>
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;"><b>下单小时段设置:</b></td>
								<td style="width:70px;text-align: left;padding-top: 13px;"><b>${pd.order_hour}小时内</b>
								</td>
							</tr> 
							</tr> 
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;"><b>下单数阀值:</b></td>
								<td style="width:70px;text-align: left;padding-top: 13px;"><b>${pd.max_order_num}</b>
								</td>							
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;"><b>未支付订单数阀值:</b></td>
								<td style="width:70px;text-align: left;padding-top: 13px;"><b>${pd.max_unpay_num}</b>
								</td>						
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;"><b>消费金额小时段设置:</b></td>
								<td style="width:70px;text-align: left;padding-top: 13px;"><b>${pd.money_hour}小时内</b>
								</td>						
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;"><b>超过金额发出通知阀值:</b></td>
								<td style="width:70px;text-align: left;padding-top: 13px;"><b>${pd.max_consume_money}</b>
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
		</script>
</body>
</html>
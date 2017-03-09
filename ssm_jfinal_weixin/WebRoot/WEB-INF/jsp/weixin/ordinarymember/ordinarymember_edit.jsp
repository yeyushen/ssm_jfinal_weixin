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
					
					<form action="ordinarymember/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="openid" id="open_id" value="${pd.openid}"/>
						<input type="hidden" name="parent_id" id="parent_id" value="${null == pd.parent_id ? id:pd.parent_id}"/>
						<div id="zhongxin">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<%-- <tr>
								<td style="width:70px;text-align: right;padding-top: 13px;"><b>openid:</b></td>
								<td style="width:70px;text-align: left;padding-top: 13px;"><b>${pd.openid}</b></td>
							</tr> --%>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;"><b>昵称:</b></td>
								<td style="width:70px;text-align: left;padding-top: 13px;"><b>${pd.nick_name}</b>
								</td>
							</tr> 
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;"><b>姓名:</b></td>
								<td style="width:70px;text-align: left;padding-top: 13px;"><b>${pd.nick_name}</b>
								</td>							
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;"><b>钱包余额:</b></td>
								<td style="width:70px;text-align: left;padding-top: 13px;"><b>${pd.amount}</b>
								</td>							
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;"><b>会员级别:</b></td>
								<td>			
								<select class="chosen-select form-control" name="level_id" id="level_id" data-placeholder="请选择会员级别" maxlength="255" placeholder="这里输入邮箱" title="邮箱" style="width:98%;">
											<option value=""></option>
										<c:forEach items="${levelList}" var="level">
											<option <c:if test="${pd.level_id==level.id }">selected</c:if> value="${level.id }">${level.level_name }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;"><b>电话号码:</b></td>
								<td>
								<input type="text" name="phone" id="phone" value="${pd.phone}" maxlength="255" placeholder="这里输入电话号码" title="电话号码" style="width:98%;" /></td>
								</td>							
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;"><b>邮箱:</b></td>
								<td>
								<input type="text" name="email" id="email" value="${pd.email}" maxlength="255" placeholder="这里输入邮箱" title="邮箱" style="width:98%;" /></td>
								</td>							
							</tr>
							
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;"><b>加入时间:</b></td>
								<td style="width:70px;text-align: left;padding-top: 13px;"><b>${pd.create_date}</b>
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
			if($("#openid").val()==""){
					$("#openid").tips({
						side:3,
			            msg:'请输入openid',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#openid").focus();
				return false;
			}
			
			if($("#name").val()==""){
					$("#name").tips({
						side:3,
			            msg:'请输入姓名',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#name").focus();
				return false;
			}
		
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
			
		}
		</script>
</body>
</html>
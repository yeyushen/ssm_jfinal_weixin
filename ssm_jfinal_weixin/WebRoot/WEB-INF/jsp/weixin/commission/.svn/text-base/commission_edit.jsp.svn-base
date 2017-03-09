<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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

							<form action="ordinarymember/${msg }.do" name="Form" id="Form"
								method="post">
								<input type="hidden" name="ordinarymember_id"
									id="ordinarymember_id" value="${pd.ordinarymember_id}" />
								<div id="zhongxin" style="padding-top: 13px;">
									<table id="table_report"
										class="table table-striped table-bordered table-hover">
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">openid:</td>
											<td><input type="text" name="openid" id="openid"
												value="${pd.openid}" maxlength="255"
												placeholder="这里输入微信Openid" title="微信Openid"
												style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">昵称:</td>
											<td><input type="text" name="nick_name" id="nick_name"
												value="${pd.nick_name}" maxlength="255"
												placeholder="这里输入会员昵称" title="会员昵称" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">姓名:</td>
											<td><input type="text" name="name" id="name"
												value="${pd.name}" maxlength="255" placeholder="这里输入会员姓名"
												title="会员姓名" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">性别:</td>
											<td><input type="text" name="sex" id="sex"
												value="${pd.sex}" maxlength="255" placeholder="这里输入会员性别"
												title="会员性别" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">头像:</td>
											<td><input type="text" name="photo" id="photo"
												value="${pd.photo}" maxlength="255" placeholder="这里输入会员头像"
												title="会员头像" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">手机号码:</td>
											<td><input type="text" name="phone" id="phone"
												value="${pd.phone}" maxlength="255" placeholder="这里输入手机号码"
												title="手机号码" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">电话号码:</td>
											<td><input type="text" name="mobile" id="mobile"
												value="${pd.mobile}" maxlength="255" placeholder="这里输入电话号码"
												title="电话号码" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">邮箱:</td>
											<td><input type="text" name="email" id="email"
												value="${pd.email}" maxlength="255" placeholder="这里输入邮箱"
												title="会员邮箱" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">加入时间:</td>
											<td><input class="span10 date-picker" name="create_date"
												id="create_date" value="${pd.create_date}" type="text"
												data-date-format="yyyy-mm-dd" readonly="readonly"
												placeholder="加入时间" title="加入时间" style="width:98%;" /></td>
										</tr>
										<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">加入时间:</td>
								<td><input type="text" name="create_date" id="create_date" value="${pd.create_date}" maxlength="255" placeholder="这里输入加入时间" title="加入时间" style="width:98%;"/></td>
							</tr> --%>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">激活状态:</td>
											<td>
												<%-- <input type="text" name="canceled" id="canceled" value="${pd.canceled}" maxlength="255" placeholder="这里输入激活状态" title="激活状态" style="width:98%;"/> --%>
												<select class="chosen-select form-control" name="canceled"
												id="canceled" data-placeholder="请选择激活状态"
												style="vertical-align:top;" style="width:98%;">
													<option value="">请选择激活状态</option>
													<option value="0"
														<c:if test="${pd.canceled==0 }">selected</c:if>>是</option>
													<option value="1"
														<c:if test="${pd.canceled==1 }">selected</c:if>>否</option>
											</select>
											</td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">会员等级:</td>
											<td>
												<%-- <input type="text" name="level_id" id="level_id" value="${pd.level_id}" maxlength="255" placeholder="这里输入会员等级" title="会员等级" style="width:98%;"/> --%>
												<%-- <input type="text" name="level_name" id="level_name" value="${pd.level_name}" maxlength="255" placeholder="这里输入会员等级" title="会员等级" style="width:98%;"/> --%>
												<select class="chosen-select form-control" name="level_id"
												id="level_id" data-placeholder="请选择角色"
												style="vertical-align:top;" style="width:98%;">
													<option value="">请选择会员级别</option>
													<c:forEach items="${levelList}" var="level">
														<option value="${level.id }"
															<c:if test="${level.id == pd.level_id }">selected</c:if>>${level.level_name }</option>
													</c:forEach>
											</select> <%--下拉 <select class="chosen-select form-control" name="ROLE_ID" id="role_id" data-placeholder="请选择角色" style="vertical-align:top;" style="width:98%;" >
											<option value=""></option>
											<c:forEach items="${roleList}" var="role">
												<option value="${role.ROLE_ID }" <c:if test="${role.ROLE_ID == pd.ROLE_ID }">selected</c:if>>${role.ROLE_NAME }</option>
											</c:forEach>
									</select> --%>

											</td>
										</tr>
										<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">会员头像:</td>
								<td><input type="text" name="PIC" id="PIC" value="${pd.PIC}" maxlength="255" placeholder="这里输入会员头像" title="会员头像" style="width:98%;"/></td>
							</tr>
					
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">激活状态:</td>
								<td>
									<select class="chosen-select form-control" name="STATUS" id="STATUS" data-placeholder="请选择">
										<option value="是">请选择</option>
										<option value="是">是</option>
										<option value="否">否</option>
									</select>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">加入时间:</td>
								<td><input class="span10 date-picker" name="JOINTIME" id="JOINTIME" value="${pd.JOINTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="加入时间" title="加入时间" style="width:98%;"/></td>
							</tr> --%>
										<tr>
											<td style="text-align: center;" colspan="10"><a
												class="btn btn-mini btn-primary" onclick="save();">保存</a> <a
												class="btn btn-mini btn-danger"
												onclick="top.Dialog.close();">取消</a></td>
										</tr>
									</table>
								</div>
								<div id="zhongxin2" class="center" style="display:none">
									<br />
									<br />
									<br />
									<br />
									<br />
									<img src="static/images/jiazai.gif" /><br />
									<h4 class="lighter block green">提交中...</h4>
								</div>
							</form>
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
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
		$(top.hangge());
		//保存
		function save() {
			if ($("#openid").val() == "") {
				$("#openid").tips({
					side : 3,
					msg : '请输入微信Openid',
					bg : '#AE81FF',
					time : 2
				});
				$("#openid").focus();
				return false;
			}
			if ($("#nick_name").val() == "") {
				$("#nick_name").tips({
					side : 3,
					msg : '请输入会员昵称',
					bg : '#AE81FF',
					time : 2
				});
				$("#nick_name").focus();
				return false;
			}
			if ($("#name").val() == "") {
				$("#name").tips({
					side : 3,
					msg : '请输入会员姓名',
					bg : '#AE81FF',
					time : 2
				});
				$("#name").focus();
				return false;
			}
			/* 	if($("#JOINTIME").val()==""){
					$("#JOINTIME").tips({
						side:3,
			            msg:'请输入加入时间',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#JOINTIME").focus();
				return false;
				} */
			/* 	if($("#PIC").val()==""){
					$("#PIC").tips({
						side:3,
			            msg:'请输入会员头像',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#PIC").focus();
				return false;
				}
				if($("#STATUS").val()==""){
					$("#STATUS").tips({
						side:3,
			            msg:'请输入激活状态',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#STATUS").focus();
				return false;
				}  */
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}

		$(function() {
			//日期框
			$('.date-picker').datepicker({
				autoclose : true,
				todayHighlight : true
			});
		});
	</script>
</body>
</html>
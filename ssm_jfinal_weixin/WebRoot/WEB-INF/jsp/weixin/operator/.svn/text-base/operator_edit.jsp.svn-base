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
					
					<form action="operator/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="id" id="id" value="${pd.id}"/>
						<input type="hidden" name="parent_id" id="parent_id" value="${null == pd.parent_id ? id:pd.parent_id}"/>
						<div id="zhongxin">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:79px;text-align: right;padding-top: 13px;">所属分类:</td>
								<td>
									<div class="col-xs-4 label label-lg label-light arrowed-in arrowed-right">
										<b>${null == pds.type_name ?'(无) 此分类为顶级':pds.type_name}</b>
									</div>
								</td>
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;">名称:</td>
								<td><input type="text" name="type_name" id="type_name" value="${pd.type_name}" maxlength="50" placeholder="这里输入名称" title="名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;">编码:</td>
								<td><input type="text" name="type_code" id="type_code" value="${pd.type_code}" maxlength="50" placeholder="这里输入英文" title="英文" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">运营商:</td>
								<td id="chnnltypeId">
									<select class="chosen-select form-control" name="chnnl_type" id="chnnl_type" data-placeholder="请选择运营商" style="vertical-align:top;" style="width:98%;" >
											<option value="" ></option>
											<option value="1" <c:if test="${pd.chnnl_type==1 }">selected</c:if>>移动</option>
											<option value="2" <c:if test="${pd.chnnl_type==2 }">selected</c:if>>联通</option>
											<option value="3" <c:if test="${pd.chnnl_type==3 }">selected</c:if>>电信</option>
									</select>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">范围:</td>
								<td id="regionId">
									<select class="chosen-select form-control" name="region" id="region" data-placeholder="请选择范围" style="vertical-align:top;" style="width:98%;" >
											<option value="" ></option>
											<option value="1" <c:if test="${pd.region==1 }">selected</c:if>>省内</option>
											<option value="2" <c:if test="${pd.region==2 }">selected</c:if>>国内</option>
											<option value="3" <c:if test="${pd.region==3 }">selected</c:if>>全国</option>
									</select>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">地区:</td>
								<td id="dictionariesId">
									<select class="chosen-select form-control" name="dictionaries_id" id="dictionaries_id" data-placeholder="请选择地区" style="vertical-align:top;"  title="地区" style="width:98%;" >
										<option value=""></option>
										<c:forEach items="${dictionariesList}" var="dictionaries">
											<option value="${dictionaries.dictionariesId }" <c:if test="${dictionaries.dictionariesId == pd.dictionaries_id }">selected</c:if>>${dictionaries.dictionaries_name }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<c:if test="${level == 2}">
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">微信显示:</td>
									<td id="regionId">
										<select class="chosen-select form-control" name="showwx" id="showwx" data-placeholder="请选择是否显示" style="vertical-align:top;" style="width:98%;" >
												<option value="0" <c:if test="${pd.is_show_wx==0 }">selected</c:if>>否</option>
												<option value="1" <c:if test="${pd.is_show_wx==1 }">selected</c:if>>是</option>
										</select>
									</td>
								</tr>
							</c:if>
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
		<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存			
		function save(){
		
		if($("#type_name").val()==""){
			$("#type_name").tips({
				side:3,
	            msg:'请输入名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#type_name").focus();
		return false;
	}
		
			if($("#chnnl_type").val()==""){
			$("#chnnltypeId").tips({
				side:3,
	            msg:'请选择运营商',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#chnnltypeId").focus();
		return false;
	}
		
			if($("#region").val()==""){
			$("#regionId").tips({
				side:3,
	            msg:'请选择范围',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#regionId").focus();
		return false;
	}
	
	
		
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
			
		});
		</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

							<form action="pt/${msg }.do" name="Form" id="Form"
								method="post">
								<%-- <input type="hidden" name="pid" id="pid" value="${pd.pid}"/> --%>
								<input type="hidden" name="pid" id="pid" value="${pd.id}"/>
								<input type="hidden" name="product_id" id="product_id"
									value="${pd.product_id}" />
								<%-- <input type="hidden" name="pid" id="pid"
									value="${pd.pid}" /> --%>
								<div id="zhongxin" style="padding-top: 13px;">
									<table id="table_report"
										class="table table-striped table-bordered table-hover">
										<%-- <tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">产品分类:</td>
											<td id="js">
											<select class="chosen-select form-control" name="pid" id="pid" data-placeholder="请选择产品分类" style="vertical-align:top;"  title="产品分类" style="width:98%;" >
											<option value=""></option>
											<c:forEach items="${operatorList}" var="operator">
												<option value="${operator.id }" <c:if test="${operator.id == pd.pid }">selected</c:if>>${operator.type_name }</option>
											</c:forEach>
											</select>
											</td>
										</tr> --%>
										<tr>
								<td style="width:79px;text-align: right;padding-top: 13px;">产品分类:</td>
								<td>
									<div class="col-xs-4 label label-lg label-light arrowed-in arrowed-right">
										<b>${null == pd.type_name ?'(无) 此分类为顶级不能新增':pd.type_name}</b>
									</div>
								</td>
							</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">产品名称:</td>
											<td><input type="text" name="product_name"
												id="product_name" value="${pd.product_name}" maxlength="255"
												placeholder="这里输入产品名称" title="产品名称" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">产品类型:</td>
											<td id="style_td">
												<select class="chosen-select form-control" name="style"
												id="style" data-placeholder="请选择产品类型"
												style="vertical-align:top;" style="width:98%;">
												<option value=""></option>
													<option value="0"
														<c:if test="${pd.style==0 }">selected</c:if>>标准包</option>
													<option value="1"
														<c:if test="${pd.style==1 }">selected</c:if>>红包</option>
													<option value="2"
														<c:if test="${pd.style==2 }">selected</c:if>>转赠</option>																										
											</select>
											</td>
										</tr>										
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">详细名称:</td>
											<td><input type="text" name="specify_name"
												id="specify_name" value="${pd.specify_name}" maxlength="255"
												placeholder="这里输入产品名称" title="产品名称" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">产品编码:</td>
											<td><input type="text" name="product_code"
												id="product_code" value="${pd.product_code}" maxlength="255"
												placeholder="这里输入产品编码" title="产品编码" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">产品价格:</td>
											<td><input type="text" name="product_price"
												id="product_price" value="${pd.product_price}"
												maxlength="255" placeholder="这里输入产品价格" title="产品价格"
												style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">结算价格:</td>
											<td><input type="text" name="settlement_price"
												id="settlement_price" value="${pd.settlement_price}"
												maxlength="255" placeholder="这里输入结算价格" title="结算价格"
												style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">成本价格:</td>
											<td><input type="text" name="cost_price" id="cost_price"
												value="${pd.cost_price}" maxlength="255"
												placeholder="这里输入成本价格" title="成本价格" style="width:98%;" /></td>
										</tr>
										<tr> 
											<td style="width:75px;text-align: right;padding-top: 13px;">佣金比例:</td>
											<td><input type="text" name="profit" id="profit" onblur="updateProfitPrice()"
												value="${pd.profit}" maxlength="255" placeholder="这里输入佣金比例"
												title="佣金比例" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">佣金:</td>
											<td><input type="text" name="profit_price" id="profit_price"
												value="${pd.profit_price}" maxlength="255" placeholder="这里输入佣金"
												title="佣金" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">修改人:</td>
											<td><input type="text" name="modifier" id="modifier"
												value="${pd.modifier}" maxlength="255" placeholder="这里输入修改人"
												title="修改人" style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">是否压单:</td>
											<td>
												<%-- <input type="text" name="canceled" id="canceled" value="${pd.canceled}" maxlength="255" placeholder="这里输入激活状态" title="激活状态" style="width:98%;"/> --%>
												<select class="chosen-select form-control" name="ispress"
												id="ispress" data-placeholder="请选择压单状态"
												style="vertical-align:top;" style="width:98%;">
													<option value="0"
														<c:if test="${pd.ispress==0 }">selected</c:if>>否</option>
													<option value="1"
														<c:if test="${pd.ispress==1 }">selected</c:if>>是</option>
											</select>
											</td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">是否推荐:</td>
											<td id="recommendId">
												<%-- <input type="text" name="canceled" id="canceled" value="${pd.canceled}" maxlength="255" placeholder="这里输入激活状态" title="激活状态" style="width:98%;"/> --%>
												<select class="chosen-select form-control" name="is_recommend"
												id="is_recommend" data-placeholder="请选择是否推荐"
												style="vertical-align:top;" style="width:98%;">
												<option value=""></option>
													<option value="2"
														<c:if test="${pd.is_recommend==2 }">selected</c:if>>否</option>
													<option value="1"
														<c:if test="${pd.is_recommend==1 }">selected</c:if>>是</option>
											</select>
											</td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">是否默认:</td>
											<td id="defaultId">
												<%-- <input type="text" name="canceled" id="canceled" value="${pd.canceled}" maxlength="255" placeholder="这里输入激活状态" title="激活状态" style="width:98%;"/> --%>
												<select class="chosen-select form-control" name="is_default"
												id="is_default" data-placeholder="请选择是否默认"
												style="vertical-align:top;" style="width:98%;">
												<option value=""></option>
													<option value="2"
														<c:if test="${pd.is_default==2 }">selected</c:if>>否</option>
													<option value="1"
														<c:if test="${pd.is_default==1 }">selected</c:if>>是</option>
											</select>
											</td>
										</tr>										 
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">产品状态:</td>
											<td id="statusId">
												<%-- <input type="text" name="canceled" id="canceled" value="${pd.canceled}" maxlength="255" placeholder="这里输入激活状态" title="激活状态" style="width:98%;"/> --%>
												<select class="chosen-select form-control" name="status"
												id="status" data-placeholder="请选择产品状态"
												style="vertical-align:top;" style="width:98%;">
													<option value=""></option>
													<option value="0"
														<c:if test="${pd.status==0 }">selected</c:if>>启用</option>
													<option value="1"
														<c:if test="${pd.status==1 }">selected</c:if>>下架</option>
											</select>
											</td>
										</tr>
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
		
		function updateProfitPrice(){
		if($("#settlement_price").val()==""){
			$("#settlement_price").tips({
				side:3,
	            msg:'输入结算价格价格才可以自动计算佣金！',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#settlement_price").focus();
				return false;
			}
			
			var reg=/^[-\+]?\d+(\.\d+)?$/;		//js正则表达式,校验double类型
			var a=document.getElementById("settlement_price").value;
			var b=document.getElementById("profit").value
			if(!reg.test(a)){
			$("#settlement_price").tips({
				side:3,
	            msg:'格式错误！输入数字类型才可以自动计算佣金',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#settlement_price").focus();
				return false;
			}
			
			if(b!=""){
			if(!reg.test(b)){
			$("#profit").tips({
				side:3,
	            msg:'格式错误！输入数字类型才可以自动计算佣金',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#profit").focus();
				return false;
			}
			}
			
			var c = a*b;
			var d = c.toFixed(3);
			document.getElementById("profit_price").value=d;
			}
		
		//保存
		function save() {
			if($("#product_name").val()==""){
			$("#product_name").tips({
				side:3,
	            msg:'请输入产品名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#product_name").focus();
			return false;
			}
			if($("#style").val()==""){
				$("#style").tips({
					side:3,
		            msg:'请选择产品类型',
		            bg:'#AE81FF',
		            time:2
		        });
				return false;
				}			
			
			if($("#specify_name").val()==""){
			$("#specify_name").tips({
				side:3,
	            msg:'请输入产品详细名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#specify_name").focus();
			return false;
			}
			
			if($("#product_code").val()==""){
			$("#product_code").tips({
				side:3,
	            msg:'请输入产品编码',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#product_code").focus();
			return false;
			}
			
			if($("#product_price").val()==""){
			$("#product_price").tips({
				side:3,
	            msg:'请输入产品价格',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#product_price").focus();
				return false;
			}
			
			if($("#settlement_price").val()==""){
			$("#settlement_price").tips({
				side:3,
	            msg:'请输入结算价格',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#settlement_price").focus();
			return false;
			}
			
			if($("#cost_price").val()==""){
			$("#cost_price").tips({
				side:3,
	            msg:'请输入成本价格',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#cost_price").focus();
			return false;
			}
			
			if($("#profit").val()==""){
			$("#profit").tips({
				side:3,
	            msg:'请输入佣金比例',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#profit").focus();
			return false;
			}
	
			
		var reg=/^[-\+]?\d+(\.\d+)?$/;		//js正则表达式,校验double类型
		
        var obj = document.getElementById("product_price");  
    	if(!reg.test(obj.value)){  
       $("#product_price").tips({
				side:3,
	            msg:'格式错误！请输入数字类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#product_price").focus();
				return false;
			}
			
		 var obj = document.getElementById("settlement_price");
		 if(!reg.test(obj.value)){  
       $("#settlement_price").tips({
				side:3,
	            msg:'格式错误！请输入数字类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#settlement_price").focus();
				return false;
			}
			
		 var obj = document.getElementById("cost_price");
		 if(!reg.test(obj.value)){  
       $("#cost_price").tips({
				side:3,
	            msg:'格式错误！请输入数字类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#cost_price").focus();
				return false;
			}
			
		var obj = document.getElementById("profit");
		if($("#profit").val()!=""){
		if(!reg.test(obj.value)){  
       $("#profit").tips({
				side:3,
	            msg:'格式错误！请输入数字类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#profit").focus();
				return false;
			}
			}
			
		 var obj = document.getElementById("profit_price");
		 if($("#profit_price").val()!=""){
		 if(!reg.test(obj.value)){  
       $("#profit_price").tips({
				side:3,
	            msg:'格式错误！请输入数字类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#profit_price").focus();
				return false;
			}
			}
			
			if($("#is_recommend").val()==""){
			$("#recommendId").tips({
				side:3,
	            msg:'请选择是否推荐',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#is_recommend").focus();
				return false;
			}
			
			if($("#is_default").val()==""){
			$("#defaultId").tips({
				side:3,
	            msg:'请选择是否默认',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#is_default").focus();
				return false;
			}
			  			
		if($("#status").val()==""){
			$("#statusId").tips({
				side:3,
	            msg:'请选择产品状态',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#status").focus();
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
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
		<!-- jsp文件头和头部 -->
		<%@ include file="../../system/index/top.jsp"%>
		
	
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
					
					<form action="discount/edit.do" name="Form" id="Form" method="post">
						<input type="hidden" name="id" id="id" value="${pd.id}"/>
						<input type="hidden" name="parent_id" id="parent_id" value="${null == pd.parent_id ? id:pd.parent_id}"/>
						<div id="zhongxin">
						<c:if test="${QX.cha == 1 }">
						<table id="table_report" class="table table-striped table-bordered table-hover">	
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">一级返佣比例:</td>
								<td style="width:100px;text-align: right;padding-top: 13px;"><input type="text" name="discount_1" id="discount_1" value="${pd.discount_1}" maxlength="50" placeholder="这里输入入一级返佣比例" title="返佣比例" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;">二级返佣比例:</td>
								<td style="width:100px;text-align: right;padding-top: 13px;"><input type="text" name="discount_2" id="discount_2" value="${pd.discount_2}" maxlength="50" placeholder="这里输入入二级返佣比例" title="返佣比例" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;">三级返佣比例:</td>
								<td style="width:100px;text-align: right;padding-top: 13px;"><input type="text" name="discount_3" id="discount_3" value="${pd.discount_3}" maxlength="50" placeholder="这里输入入三级返佣比例" title="返佣比例" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;">四级返佣比例:</td>
								<td style="width:100px;text-align: right;padding-top: 13px;"><input type="text" name="discount_4" id="discount_4" value="${pd.discount_4}" maxlength="50" placeholder="这里输入入四级返佣比例" title="返佣比例" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;">五级返佣比例:</td>
								<td style="width:100px;text-align: right;padding-top: 13px;"><input type="text" name="discount_5" id="discount_5" value="${pd.discount_5}" maxlength="50" placeholder="这里输入入五级级返佣比例" title="返佣比例" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;">自定义选项一:</td>
								<td  style="width:100px;text-align: right;padding-top: 13px;"><input type="text" name="discount_a" id="discount_a" value="${pd.discount_a}" maxlength="50" placeholder="这里输入入自定义返佣比例" title="返佣比例" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;">自定义选项二:</td>
								<td style="width:100px;text-align: right;padding-top: 13px;"><input type="text" name="discount_b" id="discount_b" value="${pd.discount_b}" maxlength="50" placeholder="这里输入入自定义返佣比例" title="返佣比例" style="width:98%;"/></td>
							</tr>
							<tr>
								<td class="center" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<!-- <a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a> -->
								</td>
							</tr>
						</table>
						</c:if>
						<c:if test="${QX.cha !=1 }">
								<tr>
									<td colspan="100" class="center">您无权查看</td>
									<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
								</tr>		
						</c:if>
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
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			var reg=/^[-\+]?\d+(\.\d+)?$/;		//js正则表达式,校验double类型
			
			if($("#discount_1").val()==""){
				$("#discount_1").tips({
					side:3,
		            msg:'请输入一级返佣比例',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#discount_1").focus();
			return false;
			}
			
			var discount_1 = document.getElementById("discount_1");  
    		if(!reg.test(discount_1.value)){  
       		$("#discount_1").tips({
				side:3,
	            msg:'格式错误！请输入数字类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#discount_1").focus();
				return false;
			} 
			
			if($("#discount_2").val()==""){
				$("#discount_2").tips({
					side:3,
		            msg:'请输入二级返佣比例',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#discount_2").focus();
			return false;
			}
			
			var discount_2 = document.getElementById("discount_2");  
    		if(!reg.test(discount_2.value)){  
       		$("#discount_2").tips({
				side:3,
	            msg:'格式错误！请输入数字类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#discount_2").focus();
				return false;
			}
			
			if($("#discount_3").val()==""){
				$("#discount_3").tips({
					side:3,
		            msg:'请输入三级返佣比例',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#discount_3").focus();
			return false;
			}
			
			var discount_3 = document.getElementById("discount_3");  
    		if(!reg.test(discount_3.value)){  
       		$("#discount_3").tips({
				side:3,
	            msg:'格式错误！请输入数字类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#discount_3").focus();
				return false;
			}

			if($("#discount_4").val()==""){
				$("#discount_4").tips({
					side:3,
		            msg:'请输入四级返佣比例',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#discount_4").focus();
			return false;
			}
			
			var discount_4 = document.getElementById("discount_4");  
    		if(!reg.test(discount_4.value)){  
       		$("#discount_4").tips({
				side:3,
	            msg:'格式错误！请输入数字类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#discount_4").focus();
				return false;
			}
			
			if($("#discount_5").val()==""){
				$("#discount_5").tips({
					side:3,
		            msg:'请输入入五级返佣比例',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#discount_5").focus();
			return false;
			}
			
			var discount_5 = document.getElementById("discount_5");  
    		if(!reg.test(discount_5.value)){  
       		$("#discount_5").tips({
				side:3,
	            msg:'格式错误！请输入数字类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#discount_5").focus();
				return false;
			}
			
			if($("#discount_a").val()==""){
				$("#discount_a").tips({
					side:3,
		            msg:'请输入自定义选项一返佣比例',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#discount_a").focus();
			return false;
			}
			
			var discount_a = document.getElementById("discount_a");  
    		if(!reg.test(discount_a.value)){  
       		$("#discount_a").tips({
				side:3,
	            msg:'格式错误！请输入数字类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#discount_3").focus();
				return false;
			}
			
			if($("#discount_b").val()==""){
				$("#discount_b").tips({
					side:3,
		            msg:'请输入自定义选项二返佣比例',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#discount_b").focus();
			return false;
			}
			
			var discount_b = document.getElementById("discount_b");  
    		if(!reg.test(discount_b.value)){  
       		$("#discount_b").tips({
				side:3,
	            msg:'格式错误！请输入数字类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#discount_3").focus();
				return false;
			}
		
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
			
		}
		</script>
</body>
</html>
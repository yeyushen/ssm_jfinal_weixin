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
					
					<form action="memberqrcode/${msg }.do" name="Form" id="Form" method="post">
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">生成数目:</td>
								<td>
                                <input type="number" name="qrcodeCount" id="qrcodeCount" value="1" placeholder="请输入生成数目" title="请输入正整数" class="col-xs-10 col-sm-5" />
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注:</td>
								<td><input type="text" name="bz" id="bz" value="${pd.bz}" maxlength="255" placeholder="这里输入备注" title="备注" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-sm btn-primary" onclick="save();">批量生成二维码</a>&nbsp;&nbsp;&nbsp;
									<a class="btn btn-sm btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
						</div>
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
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
	<!-- 弹出框  -->
	<script src="static/ace/js/bootbox.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
	   		
		//保存
		function save(){
			if($("#qrcodeCount").val()==""){
				$("#qrcodeCount").tips({
					side:1,
		            msg:'请输入二维码生成数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#qrcodeCount").focus();
				return false;
			}
			if($("#qrcodeCount").val() >= 50){
				$("#qrcodeCount").tips({
					side:1,
		            msg:'输入生成二维码数过多',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#qrcodeCount").focus();
				return false;			
			}
			
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
		function changeText(){
		var chooseText = $("#TITLE").find("option:selected").text();
		$("#menberName").val(chooseText);
		$.ajax({
		    type: "post",
		    url: '<%=basePath%>memberqrcode/qrcodeexist.do?tm='+new Date().getTime(),
		    data: {DATA_ID:$("#TITLE").val()},
		    dataType: 'json',
		    cache: false,
		    success:function(data){
		          var result = data.msg;
		          if(result == "no"){
		             bootbox.alert("该会员二维码已存在，请返回列表页查询.");
		          }
		    }
		});
		}
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>
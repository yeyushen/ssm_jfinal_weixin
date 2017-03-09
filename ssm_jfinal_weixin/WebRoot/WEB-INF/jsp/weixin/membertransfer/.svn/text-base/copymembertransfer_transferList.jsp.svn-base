<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
							<!-- 检索  -->

							<table id="simple-table"
								class="table table-striped table-bordered table-hover"
								style="margin-top:5px;">
								<tr>
									<h5>
										<a class="btn btn-sm btn-success">第1步:</a> </br>
										</br><a>将要转移以下会员</a>
									</h5>
								</tr>
								<tr>
									<!-- 开始循环 -->
									<c:forEach items="${varList}" var="var" varStatus="vs">
										<td class='center'>${var.nick_name}</td>
									</c:forEach>
								</tr>
							</table>
							<form action="membertransfer/listChoiceAndParent.do"
								method="post" name="Form1" id="Form1">
								<input type="hidden" name="DATA_IDS" value="${DATA_IDS}">
								<table>
									<tr>
										<h5><a class="btn btn-sm btn-success">第2步:</a></h5>
									</tr>
								</table>
								<table>
									<input type="text" placeholder="搜索会员" class="nav-search-input"
										id="search();" autocomplete="off" name="keywords"
										value="${pd.keywords }" placeholder="" 搜索会员"" />
									<button class="btn btn-sm btn-success" onclick="()">搜索</button>
								</table>
							</form>
							</br>
							<form action="membertransfer/updateAllParent.do" method="post"
								name="Form2" id="Form2">
								<input type="hidden" name="DATA_IDS" value="${DATA_IDS}">
								<table>
									<tr >
										<h5 >
											<a class="btn btn-sm btn-success">第3步:</a></br>
											</br><a>搜索后下拉选择</a>
										</h5>
									</tr>
								</table>
								<table>
									<tr>
										<td><select class="chosen-select form-control"
											name="openid" id="openid" data-placeholder="请选择角色"
											style="vertical-align:top;" style="width:98%;">
												<c:forEach items="${varList1}" var="var">
													<option value="${var.openid }">${var.nick_name }</option>
												</c:forEach>
										</select></td>
									</tr>
								</table>
								</br>
								<table>
									<h5><a class="btn btn-sm btn-success">第4步:</a></h5>
									<a class="btn btn-xs btn-danger" onclick="trans();">确认转移</a>
									<!-- <button class="btn btn-xs btn-danger" onclick="copyText()">确认转移</button> -->
								</table>
								<br>
							<table style="width:100%;">
							<tr>
								<td style="vertical-align:top;">
									<a class="btn btn-sm btn-success" onclick="goBack();">返回重选</a>
								</td>
							</tr>
						</table>

							</form>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
</body>
</html>
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
		//返回
		function goBack(){
			top.jzts();
			window.history.back(-1);
		};
		
		function trans() { 
			var msg = "您真的确定要转移吗？请确认！"; 
			if(confirm(msg)==true){ 
			$("#Form2").submit();; 
			}else{ 
			return false; 
			} 
} 
</script>
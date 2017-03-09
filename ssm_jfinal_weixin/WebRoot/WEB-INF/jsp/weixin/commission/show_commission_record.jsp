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
					
					<form action="ordinarymember/goCommission.do" method="post" name="Form" id="Form">
                     <input type="hidden" id="open_id" name="open_id" value="${pd.key_value}"/>
                     <input type="hidden" id="points" name="points" value="${pd.points}"/>
						<div id="zhongxin">
						<br/>
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th class="center" style="width:50px;">序号</th>
									<th class="center">会员名称</th>
									<th class="center">佣金</th>
									<th class="center">来源</th>
									<th class="center">钱包余额</th>
									<th class="center">记录时间</th>
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty varList}">
									<c:forEach items="${varList}" var="var" varStatus="vs">
									<c:choose>
									<c:when test="${ var.lv == 5 or var.lv == 4 }">
										<tr style="color: red">
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class='center'>${var.nick_name}</td>
											<td class='center'>${var.commission}</td>
											<td class='center'>
											<c:if test="${var.lv == 4 }">充值业务</c:if>
											<c:if test="${var.lv == 5 }">提现业务</c:if>											
											</td>
											<td class='center'>${var.balance}</td>
											<td class='center'>
											<fmt:formatDate value="${var.ts}" type="both" pattern="yyyy-MM-dd HH:mm"/>
											</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class='center'>${var.nick_name}</td>
											<td class='center'>${var.commission}</td>
											<td class='center'>
											<c:if test="${var.lv ==1 }">一级佣金</c:if>
											<c:if test="${var.lv ==2 }">二级佣金</c:if>
											<c:if test="${var.lv ==3 }">三级佣金</c:if>											
											</td>
											<td class='center'>${var.balance}</td>
											<td class='center'>
											<fmt:formatDate value="${var.ts}" type="both" pattern="yyyy-MM-dd HH:mm"/>
											</td>
										</tr>									
									</c:otherwise>
									</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr class="main_info">
										<td colspan="100" class="center" >没有相关数据</td>
									</tr>
								</c:otherwise>
							</c:choose>
							<tr>
								<td class="center" colspan="2">会员钱包余额为:</td>
								<td class="left">${pd.wallet}</td>							
								<td class="center">会员总积分为:</td>
								<td class="left" colspan="2">${pd.points}</td>
							</tr>							
							</tbody>
						</table>
						<div class="page-header position-relative">
						<table style="width:100%;">
							<tr>
								<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
							</tr>
							<tr>
								<td class="center">
								    <a class="btn btn-sm btn-primary" onclick="top.Dialog.close();">返回</a>
								</td>
							</tr>
						</table>
						</div>						
						</div>
						
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">...</h4></div>
						
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
		

		</script>
</body>
</html>
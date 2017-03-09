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
						<form action="noticeboard/edit.do" method="post" name="Form" id="Form">

					
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
		
													
							<tbody>
							<!-- 开始循环 -->	
							<c:if test="${QX.cha == 1 }">
							<tr height="80%">
							<td style="width:20%;text-align: right;padding-top: 13px;">公共栏内容:</td>
							<td style="width:50%;text-align: left;padding-left: 23px;">
							<span style="color: rgb(255, 0, 0);">温馨提示:回车短换行请按Shift+Enter.</span><br/>
							<%-- <textarea id="content" name="content" rows="12" cols="100" placeholder="这里输入内容">${pd.content}</textarea> --%> 
                            <script id="container" name="content" type="text/plain">${pd.content}</script>							
							</td>
							<td style="width:30%;text-align: left;padding-left: 23px;"></td>
							</tr>
							<tr>
								<td  class="center" style="vertical-align:top;" colspan="3">
									<c:if test="${QX.add == 1}">
									<a class="btn btn-sm btn-success"  onclick="add();">保存</a>
									</c:if>
								</td>
							</tr>
							</c:if>
									<c:if test="${QX.cha == 0 }">
										<tr>
											<td colspan="100" class="center">您无权查看</td>
										</tr>
									</c:if>

							</tbody>
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
		<!-- /.main-content -->

		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!--提示框-->
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ueditor编辑器  -->
	<script type="text/javascript" src="plugins/ueditor1.4/ueditor.config.js"></script>
	<script type="text/javascript" src="plugins/ueditor1.4/ueditor.all.min.js"></script>
	
	
	<script type="text/javascript">
		$(top.hangge());//关闭加载状态
		//检索
       var ue = UE.getEditor('container');
		//新增
		function add(){
		var str = ue.getContent();
		//var str = ue.getContentTxt();
			if(str ==""){
			   bootbox.alert("请填写内容.");
			   ue.focus();
			   return ;
			}
			top.jzts();
			$.ajax({
			   type: "POST",
			   url: '<%=basePath%>noticeboard/edit.do?tm='+new Date().getTime(),
			   data: {DATA_IDS:str},
			   dataType:'json',
			   cache: false,
			   success: function(data){
			   top.hangge();
			   
					 }
			     });
		}
		
		
	</script>


</body>
</html>
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
						<form action="memberrelation/list.do" method="post" name="Form" id="Form">
						<input type="hidden" name="openid" id="openid" value="${openid}"/>
						<%-- <c:if test="${null == pd.openid || pd.openid == ''}"> --%>
						<table style="margin-top:5px;">
							<tr>
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" placeholder="这里输入关键词" class="nav-search-input" id="keywords" name="keywords" autocomplete="off" value="${page.pd.keywords }" />
											<i class="ace-icon fa fa-search nav-search-icon"></i>
										</span>
									</div>
								</td>
								<%-- <td>&nbsp;
									<select name="openid" id="openid">
										<option value="${openid}" <c:if test="${openid != ''}">selected</c:if>>本层搜索</option>
										<option value="" <c:if test="${openid == ''}">selected</c:if>>全部搜索</option>
									</select>
								</td> --%>
								<td id="openidId" style="padding-left:2px;">&nbsp;
									<select class="chosen-select form-control" name="openid"
												id="openid" data-placeholder="排序方式"
												style="vertical-align:top;" style="width:98%;">
													<option value=""></option>
													<option value="${openid}" <c:if test="${openid != ''}">selected</c:if>>本层搜索</option>
													<option value="" <c:if test="${openid == ''}">selected</c:if>>全部搜索</option>
									</select>
								</td>
								<td id="sortId" style="padding-left:2px;">&nbsp;
									<select class="chosen-select form-control" name="sort"
												id="sort" data-placeholder="排序方式"
												style="vertical-align:top;" style="width:98%;">
													<option value=""></option>
													<option value="1" <c:if test="${sort==1 }">selected</c:if>>一级人数最多</option>
													<option value="2" <c:if test="${sort==2 }">selected</c:if>>二级人数最多</option>
													<option value="3" <c:if test="${sort==3 }">selected</c:if>>三级人数最多</option>
									</select>
								</td>
								<c:if test="${QX.cha == 1 }">
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-xs btn-info" onclick="gsearch();"  title="检索">搜索</a></td>
								</c:if>
							</tr>
						</table>
						<%-- </c:if> --%>
						<!-- 检索  -->
					
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:50px;">序号</th>	
									<th class="center">姓名</th>			
									<th class="center">会员昵称</th>
									<th class="center">地区</th>
									<th class="center">上级</th>
									<th class="center">钱包</th>
									<th class="center">一级人数</th>
									<th class="center">二级人数</th>	
									<th class="center">三级人数</th>	
									<th class="center">加入时间</th>
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty varList}">
									<c:if test="${QX.cha == 1 }">
									<c:forEach items="${varList}" var="var" varStatus="vs">
										<tr>
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class='center'><a href="javascript:goSondict('${var.openid }')"><i class="ace-icon fa fa-share bigger-100"></i>&nbsp;${var.name}</a></td>					
											<td class='center'>${var.nick_name}</td>
											<td class='center'>${var.country}${var.province}${var.city}</td>
											<td class='center'>
											<c:if test="${var.parent_name==null }"><a class="btn btn-sm btn-danger">无</a></c:if>
											<c:if test="${var.parent_name!=null }"><a class="btn btn-sm btn-success">${var.parent_name}</a></c:if>
											</td>
											<td class='center'>${var.amount}</td>
											<td class='center'>${var.lv1 }</td>	
											<td class='center'>${var.lv2 }</td>	
											<td class='center'>${var.lv3 }</td>																					
											<td class='center'>${var.create_date}</td>
										</tr>
									
									</c:forEach>
									</c:if>
									<c:if test="${QX.cha == 0 }">
										<tr>
											<td colspan="100" class="center">您无权查看</td>
										</tr>
									</c:if>
								</c:when>
								<c:otherwise>
									<tr class="main_info">
										<td colspan="100" class="center" >没有相关数据</td>
									</tr>
								</c:otherwise>
							</c:choose>
							</tbody>
						</table>
						<div class="page-header position-relative">
						<table style="width:100%;">
							<tr>
								<td style="vertical-align:top;">
									<c:if test="${null != pdById.openid && pdById.openid != ''}">
									<a class="btn btn-sm btn-success" onclick="goSondict('${pdById.parent_id}');">返回</a>
									<!-- <a class="btn btn-sm btn-success" onclick="goBack();">返回</a> -->
									</c:if>
								</td>
								<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
							</tr>
						</table>
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


		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
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
		$(top.hangge());//关闭加载状态
		
				$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
			
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
			
			//复选框全选控制
			var active_class = 'active';
			$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
				var th_checked = this.checked;//checkbox inside "TH" table header
				$(this).closest('table').find('tbody > tr').each(function(){
					var row = this;
					if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
					else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
				});
			});
		});
		
		//检索
		function gsearch(){
			top.jzts();
			$("#Form").submit();
		}
		
		//返回
		function goBack(){
			top.jzts();
			window.history.back(-1);
		};
		
		//去此ID下子级列表
		function goSondict(openid){
			top.jzts();
			window.location.href="<%=basePath%>memberrelation/list.do?openid="+openid;
		};
	</script>


</body>
</html>
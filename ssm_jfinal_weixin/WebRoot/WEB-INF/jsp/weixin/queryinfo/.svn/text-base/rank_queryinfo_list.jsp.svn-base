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
						<form action="queryinfo/list.do" method="post" name="Form" id="Form">
						<table style="margin-top:5px;">
							<tr>
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" placeholder="这里输入关键词" class="nav-search-input" id="keywords" autocomplete="off" name="keywords" value="${page.pd.keywords}" placeholder="这里输入关键词"/>
											<i class="ace-icon fa fa-search nav-search-icon"></i>
										</span>
									</div>
								</td>
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-xs btn-info" title="清空" onclick="clearkeywords();">清空</a></td>
								<td style="padding-left:2px;"><input class="span10 date-picker" name="lastStart" id="lastStart"  value="${pd.lastStart}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="开始日期" title="开始日期"/></td>
								<td style="padding-left:2px;"><input class="span10 date-picker" name="lastEnd" name="lastEnd"  value="${pd.lastEnd}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="结束日期" title="结束日期"/></td>
								<td style="vertical-align:top;padding-left:2px;">
								 	<select class="chosen-select form-control" name="chooseType" id="chooseType" data-placeholder="选择排行方式" style="vertical-align:top;width: 120px;">
									<option value="1" <c:if test="${pd.chooseType==1 }">selected</c:if>>消费排行</option>
									<option value="2" <c:if test="${pd.chooseType==2 }">selected</c:if>>订单排行</option>
								  	</select>
								</td>
								<td>&nbsp;</td>
									<!-- 金额范围查询 -->
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" name="as_head" id="as_head" placeholder="输入最低金额" class="nav-search-input" autocomplete="off" value="${pd.as_head}" onblur="validatehead()" >
										</span>
									</div>
								</td>
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" name="as_end" id="as_end" placeholder="输入最高金额" class="nav-search-input" autocomplete="off" value="${pd.as_end}" onblur="validateend()" >
										</span>
									</div>
								</td>
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-xs btn-info" title="清空" onclick="clearnumber();">清空</a></td>							
								<c:if test="${QX.cha == 1 }">
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-xs btn-info" onclick="tosearch();"  title="检索">搜索</a></td>
								</c:if>
								<c:if test="${QX.toExcel == 1 }"><td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="ace-icon fa fa-download bigger-110 nav-search-icon blue"></i></a></td></c:if>
							</tr>
						</table>
						<!-- 检索  -->
						
						<br/>
						<a class="btn btn-sm btn-success" style="width:100px;">${pd.rankmsg}</a>
						
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:50px;">排行</th>
									<th class="center">会员名称</th>
									<th class="center">订单数</th>
									<th class="center">总金额</th>
									<!-- <th class="center">是否代理</th> -->
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty varList}">
									<c:if test="${QX.cha == 1 }">
									<c:forEach items="${varList}" var="var" varStatus="vs">
										<tr onclick="showDetail('${var.openid}')">
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class='center'>${var.name}</td>
											<td class="center">${var.ordercount}</td>
											<td class="center">${var.momey}</td>
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
						<table style="width:100%;">
							<tr>
								<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
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
	<script type="text/javascript">
		$(top.hangge());//关闭加载状态
		//检索
		function tosearch(){
			top.jzts();
			$("#Form").submit();
		}
		$(function() {
		
			//日期框
			$('.date-picker').datepicker({
				autoclose: true,
				todayHighlight: true
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
			
						//行高亮控制  info  success  active
			var active_class = 'active';    
			$("#simple-table tr").mouseover(function(){
			             $(this).addClass(active_class);
			             $(this).css("cursor", "pointer");
			}).mouseout(function(){
			             $(this).removeClass(active_class);
			});	
		});
		
		
		function validatehead(){
		    var msHead = $("#as_head").val();
			if(msHead != "" &&  ! $.isNumeric(msHead)){
			$("#as_head").tips({
				side:3,
	            msg:'格式错误！输入数字类型',
	            bg:'#AE81FF',
	            time:2
	        });
	        document.getElementById("as_head").value = "";
		}
		}
		
		function validateend(){
		   var msEnd = $("#as_end").val();
			if(msEnd != "" &&  ! $.isNumeric(msEnd)){
			$("#as_end").tips({
				side:3,
	            msg:'格式错误！输入数字类型',
	            bg:'#AE81FF',
	            time:2
	        });
	        document.getElementById("as_end").value = "";
		}
		}
		
		//显示默认所有下级人员
		function showDetail(id){
			 top.jzts();         
             var time1 = $("#lastStart").val();
             var time2 =  $("#lastEnd").val();
          if( typeof(time2) == "undefined" ){
            time2 = "${pd.lastEnd}";
            time2 = time2.substring(0, 10);
             }
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title = '会员订单明细';
			 diag.URL = '<%=basePath%>agentinfo/childdetaillist.do?open_id=' + id +'&lastStart='+time1+'&lastEnd='+time2;
			 diag.Width = 800;
			 diag.Height = 660;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//清空搜索关键字
		function clearkeywords(){
		document.getElementById("keywords").value = "";
		}
				
		//清空金额
		function clearnumber(){

			document.getElementById("as_head").value = "";
			document.getElementById("as_end").value = "";
			
		    $("#lastStart").val("");
		    $("#lastEnd").val("");
		}
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>queryinfo/excel.do';
		}
	</script>


</body>
</html>
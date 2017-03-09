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
						<form action="agentprofit/list.do" method="post" name="Form" id="Form">
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
								<td style="padding-left:2px;"><input class="span10 date-picker" name="lastStart" id="lastStart"  value="${pd.lastStart}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="开始日期" title="开始日期"/></td>
								<td style="padding-left:2px;"><input class="span10 date-picker" name="lastEnd" name="lastEnd"  value="${pd.lastEnd}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="结束日期" title="结束日期"/></td>			
								<td id="methodId" style="padding-left:2px;">&nbsp;
									<select class="chosen-select form-control" name="settlement_method"
												id="settlement_method" data-placeholder="支付方式"
												style="vertical-align:top;" style="width:98%;">
													<option value=""></option>
													<option value="1" <c:if test="${pd.settlement_method==1 }">selected</c:if>>微信支付</option>
													<option value="2" <c:if test="${pd.settlement_method==2 }">selected</c:if>>钱包支付</option>
									</select>
								</td>
								<c:if test="${QX.cha == 1 }">
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-xs btn-info" onclick="gsearch();"  title="检索">搜索</a></td>
								</c:if>
							</tr>
						</table>
						<!-- 检索  -->
					
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center">订单号</th>
									<th class="center">充值用户</th>
									<th class="center">产品名称</th>
									<th class="center">充值电话</th>
									<th class="center">充值日期</th>
									<th class="center">支付方式</th>
									<th class="center">产品价格</th>
									<th class="center">成本价格</th>
									<th class="center">结算价格</th>
									<th class="center">利润</th>
									<th class="center">佣金</th>
									<th class="center">一级返佣</th>
									<th class="center">二级返佣</th>
									<th class="center">三级返佣</th>		
									<!-- <th class="center">操作</th> -->
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty varList}">
								<c:if test="${QX.cha == 1 }">
									<c:forEach items="${varList}" var="var" varStatus="vs">
										<tr>
											<td class='center'>${var.order_no}</td>
											<td class='center'>${var.nick_name}</a></td>
											<td class='center'>${var.product_name}</td>
											<td class='center'>${var.phone}</td>
											<td class='center'>
											<fmt:formatDate value="${var.recharge_dttm}" type="both" pattern="yyyy-MM-dd HH:mm"/>
											</td>
											<td class='center'>
											<c:if test="${var.settlement_method==1 }">微信支付</c:if>
											<c:if test="${var.settlement_method==2 }">钱包支付</c:if>
											</td>
											<td class='center'>${var.product_price}</td>
											<td class='center'>${var.cost_price}</td>	
											<td class='center'>${var.settlement_price}</td>
											<td class='center'>${var.commission}</td>
											<td class='center'>${var.profit_price}</td>
											<td class='center'>${var.profit_price1}</td>
											<td class='center'>${var.profit_price2}</td>
											<td class='center'>${var.profit_price3}</td>
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
						<table>
						<a class="btn btn-sm btn-success" style="width:100px;">订单统计</a>
						</table>
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:0px;">
							<thead>
								<tr>
									
									<th class="center" style="width:100px;">统计项</th>
									<th class="center">总订单数</th>
									<th class="center">总产品价格</th>
									<th class="center">总结算价格</th>
									<th class="center">总成本价格</th>
									<th class="center">总利润</th>
									<th class="center">总佣金</th>
									<th class="center">总一级返佣</th>
									<th class="center">总二级返佣</th>
									<th class="center">总三级返佣</th>
									
								</tr>
							</thead>
							<tr>
								<td class='center' style="width: 30px;">小计</td>
								<td class='center'>${total}</td>
								<td class='center'>${totalProductPrice}</td>
								<td class='center'>${totalSettlementPrice}</td>
								<td class='center'>${totalCostPrice}</td>
								<td class='center'>${totalcommission}</td>
								<td class='center'>${totalProfitPrice}</td>
								<td class='center'>${totalProfitPrice1}</td>
								<td class='center'>${totalProfitPrice2}</td>
								<td class='center'>${totalProfitPrice3}</td>
							</tr>
						</table>
						<table style="width:100%;">
							<tr>
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
	</script>


</body>
</html>
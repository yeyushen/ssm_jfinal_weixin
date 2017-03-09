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
						<form action="ch/list.do" method="post" name="Form" id="Form">
						<table style="margin-top:5px;">
							<tr>
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" placeholder="这里输入姓名" class="nav-search-input" id="keywords" name="keywords" autocomplete="off" value="${page.pd.keywords }" />
											<i class="ace-icon fa fa-search nav-search-icon"></i>
										</span>
									</div>
								</td>
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-xs btn-info" title="清空" onclick="clearkeywords();">清空</a></td>
								<td style="padding-left:2px;"><input class="span10 date-picker" name="lastStart" id="lastStart"  value="${pd.lastStart}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="开始日期" title="开始日期"/></td>
								<td style="padding-left:2px;"><input class="span10 date-picker" name="lastEnd" name="lastEnd"  value="${pd.lastEnd}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="结束日期" title="结束日期"/></td>			
								<td id="cash_methodId" style="padding-left:2px;">&nbsp;
									<select class="chosen-select form-control" name="cash_method"
												id="cash_method" data-placeholder="提现方式"
												style="vertical-align:top;" style="width:98%;">
													<option value=""></option>
													<option value="1" <c:if test="${pd.cash_method==1 }">selected</c:if>>微信红包</option>
													<option value="2" <c:if test="${pd.cash_method==2 }">selected</c:if>>支付宝</option>
													<option value="3" <c:if test="${pd.cash_method==3 }">selected</c:if>>银行转账</option>
									</select>
								</td>
								<td id="statusId" style="padding-left:2px;">&nbsp;
									<select class="chosen-select form-control" name="status"
												id="status" data-placeholder="发放状态"
												style="vertical-align:top;" style="width:98%;">
													<option value=""></option>
													<option value="1" <c:if test="${pd.status==1 }">selected</c:if>>已提交</option>
													<option value="2" <c:if test="${pd.status==2 }">selected</c:if>>已完成</option>
													<option value="3" <c:if test="${pd.status==3 }">selected</c:if>>不通过</option>
									</select>
								</td>
								<!-- 提现金额范围查询 -->
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" name="money_scope_head" id="money_scope_head" placeholder="输入最低提现金额" class="nav-search-input" autocomplete="off" value="${pd.as_head }" onblur="validatehead()" >
											<%-- <input type="text" placeholder="输入最低金额" class="nav-search-input" id="min" name="min" autocomplete="off" value="${page.pd.min }" onblur="validatemin()" />	 --%>
										</span>
									</div>
								</td>
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" name="money_scope_end" id="money_scope_end" placeholder="输入最高提现金额" class="nav-search-input" autocomplete="off" value="${pd.as_end}" onblur="validateend()" >
											<%-- <input type="text" placeholder="输入最高金额" class="nav-search-input" id="max" name="max" autocomplete="off" value="${page.pd.max }" onblur="validatemax()" /> --%>
										</span>
									</div>
								</td>
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-xs btn-info" title="清空" onclick="clearnumber();">清空</a></td>
								<c:if test="${QX.cha == 1 }">
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-xs btn-info" onclick="gsearch();"  title="检索">搜索</a></td>
								</c:if>
							</tr>
						</table>
						<!-- 检索  -->
					
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:35px;">
										<label class="pos-rel"><input type="checkbox" class="ace" id="zcheckbox" /><span class="lbl"></span></label>
									</th>
									<th class="center" style="width:50px;">序号</th>
									<th class="center">会员名称</th>
									<th class="center">姓名</th>
									<th class="center">申请金额</th>
									<th class="center">申请时间</th>
									<th class="center">提现方式</th>
									<!-- <th class="center">openid</th> -->
									<th class="center">银行名称</th>
									<th class="center">收款账号</th>
									<th class="center">开户行地址</th>
									<th class="center">审批人</th>
									<th class="center">审批时间</th>  
									<th class="center">发放状态</th>
									<th class="center">备注</th>
					  				<!-- <th class="center"><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>加入时间</th> -->
									<th class="center">操作</th>
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty varList}">
								<c:if test="${QX.cha == 1 }">
									<c:forEach items="${varList}" var="var" varStatus="vs">
										<tr>
											<td class='center'>
												<label class="pos-rel"><input type='checkbox' name='ids' value="${var.cash_id}" class="ace" /><span class="lbl"></span></label>
											</td>
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class='center'>
											    <button type="button" class="btn btn-link" onclick="showCommission('${var.open_id}');">
											    ${var.nick_name}
											    </button>
											</td>
											<td class='center'>${var.name}</td>
											<td class='center'>${var.amount}</td>
											<td class='center'>
											<fmt:formatDate value="${var.cash_time}" type="both" pattern="yyyy-MM-dd HH:mm"/>
											</td>
											<td class='center'>
											<c:if test="${var.cash_method==1 }">微信红包</c:if>
											<c:if test="${var.cash_method==2 }">支付宝</c:if>
											<c:if test="${var.cash_method==3 }">银行转账</c:if>
											</td>
											<%-- <td class='center'>${var.open_id}</td> --%>
											<td class='center'>${var.bank_name}</td>
											<td class='center'>${var.account}</td>
											<td class='center'>${var.bank_addr}</td>		
											<td class='center'>${var.approve}</td>
											<td class='center'>
											<fmt:formatDate value="${var.approve_time}" type="both" pattern="yyyy-MM-dd HH:mm"/>
											</td>											
											<td class='center'>
											<c:if test="${var.status==1 }">已提交</c:if>
											<c:if test="${var.status==2 }">已完成</c:if>
											<c:if test="${var.status==3 }">不通过</c:if>
											</td>
											<td class='center'>${var.note}</td>
											<td class="center">
												<c:if test="${QX.edit != 1 }">
													<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group">
												<c:if test="${QX.edit == 1 }">
													<a class="btn btn-xs btn-success" title="编辑" onclick="edit('${var.cash_id}');">
														<i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
													</a>
													</c:if>
												</div>
												<div class="hidden-md hidden-lg">
													<div class="inline pos-rel">
														<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
															<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
														</button>
			
														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
														<c:if test="${QX.edit == 1 }">
															<li>
																<a style="cursor:pointer;" onclick="edit('${var.cash_id}');" class="tooltip-success" data-rel="tooltip" title="修改">
																	<span class="green">
																		<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																	</span>
																</a>
															</li>
														</c:if>
														</ul>
													</div>
												</div>
											</td>
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
									<c:if test="${QX.del == 1 }">
									<a class="btn btn-sm btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" >选择删除<!-- <i class='ace-icon fa fa-trash-o bigger-120'></i> --></a>
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
		
		function validatehead(){
		    var msHead = $("#money_scope_head").val();
			if(msHead != "" &&  ! $.isNumeric(msHead)){
			$("#money_scope_head").tips({
				side:3,
	            msg:'格式错误！输入数字类型',
	            bg:'#AE81FF',
	            time:2
	        });
	        document.getElementById("money_scope_head").value = "";
		}
		}
		
		function validateend(){
		   var msEnd = $("#money_scope_end").val();
			if(msEnd != "" &&  ! $.isNumeric(msEnd)){
			$("#money_scope_end").tips({
				side:3,
	            msg:'格式错误！输入数字类型',
	            bg:'#AE81FF',
	            time:2
	        });
	        document.getElementById("money_scope_end").value = "";
		}
		}
		
				//清空搜索关键字
		function clearkeywords(){
		document.getElementById("keywords").value = "";
		}
		
		//清空金额
		function clearnumber(){
			document.getElementById("money_scope_head").value = "";
			document.getElementById("money_scope_end").value = "";
		}
		
				//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增会员";
			 diag.URL = '<%=basePath%>ordinarymember/goAdd.do';
			 diag.Width = 800;
			 diag.Height = 500;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		}	
		//删除
		function del(cash_id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>ordinarymember/updateDrById.do?cash_id="+cash_id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			});
		}
		
		
				//修改
		function edit(cash_id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>ch/goEdit.do?cash_id=' + cash_id;
			 diag.Width = 700;
			 diag.Height = 600;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
		}
		
		function showCommission(openId){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="会员佣金明细";
			 diag.URL = '<%=basePath%>ch/goCommission.do?open_id=' + openId;
			 diag.Width = 700;
			 diag.Height = 600;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
		}		
		
		
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++){
					  if(document.getElementsByName('ids')[i].checked){
					  	if(str=='') str += document.getElementsByName('ids')[i].value;
					  	else str += ',' + document.getElementsByName('ids')[i].value;
					  }
					}
					if(str==''){
						bootbox.dialog({
							message: "<span class='bigger-110'>您没有选择任何内容!</span>",
							buttons: 			
							{ "button":{ "label":"确定", "className":"btn-sm btn-success"}}
						});
						$("#zcheckbox").tips({
							side:1,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>ch/updateAllDr.do?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											nextPage(${page.currentPage});
									 });
								}
							});
						}
					}
				}
			});
		};
	</script>


</body>
</html>
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
						<form action="pt/list.do" method="post" name="Form" id="Form">
						<input type="hidden" id="inputpid" value="${pid }">
						<table style="margin-top:5px;">
							<tr>
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" placeholder="请输入数字,如 0.2" class="nav-search-input" id="pct" name="pct" autocomplete="off" value="${page.pd.pct }" />
										</span>
									</div>
								</td>
								<c:if test="${QX.edit == 1 }">
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-light btn-xs" onclick="update('${pid}');"  title="操作"><i id="nav-search-icon" ></i>点击修改</a></td>	
								</c:if>					
							</tr>
						</table>
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
								<td id="recommendId" style="padding-left:2px;">
									<select class="chosen-select form-control" name="is_recommend"
												id="" data-placeholder="是否推荐"
												style="vertical-align:top;" style="width:98%;">
													<option value=""></option>
													<option value="1" <c:if test="${page.pd.is_recommend==1 }">selected</c:if>>是推荐</option>	
													<option value="2" <c:if test="${page.pd.is_recommend==2 }">selected</c:if>>不是推荐</option>																					
									</select>
								</td>	
								<td id="methodId" style="padding-left:2px;">
									<select class="chosen-select form-control" name="is_default"
												id="is_default" data-placeholder="是否默认"
												style="vertical-align:top;" style="width:98%;">
													<option value=""></option>
													<option value="1" <c:if test="${page.pd.is_default==1 }">selected</c:if>>是默认</option>	
													<option value="2" <c:if test="${page.pd.is_default==2 }">selected</c:if>>不是默认</option>																					
									</select>
								</td>
								<td>&nbsp;
										<select name="pid" id="pid">
											<option value="${pid}" <c:if test="${pid != ''}">selected</c:if>>本级</option>
											<option value="" <c:if test="${pid == ''}">selected</c:if>>全部</option>
										</select>
								</td>
								<c:if test="${QX.cha == 1 }">
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-xs btn-info" onclick="gsearch();"  title="检索">搜索</a></td>
								</c:if>	
								<td style="vertical-align:top;padding-left:20px;" id="updatetd1">
									<c:if test="${QX.edit == 1 }">
									<a class="btn btn-sm btn-success" style="width:100px;" onclick="updateProductStatus1('确定要上架吗?');">批量上架</a>
									</c:if>
								</td>
								<td style="vertical-align:top;padding-left:20px;" id="updatetd2">
									<c:if test="${QX.edit == 1 }">
									<a class="btn btn-sm btn-success" style="width:100px;" onclick="updateProductStatus2('确定要下架吗?');">批量下架</a>
									</c:if>
								</td>
								<td style="vertical-align:top;padding-left:20px;" id="updatetd3">
									<c:if test="${QX.edit == 1 }">
									<a class="btn btn-sm btn-success" style="width:100px;" onclick="updateProductStatus3('确定要压单吗?');">批量压单</a>
									</c:if>
								</td>
								<td style="vertical-align:top;padding-left:20px;" id="updatetd4">
									<c:if test="${QX.edit == 1 }">
									<a class="btn btn-sm btn-success" style="width:100px;" onclick="updateProductStatus4('确定要取消压单吗?');">批量取消压单</a>
									</c:if>
								</td>						
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
										<th class="center">产品名称</th>
										<th class="center">详细名称</th>
										<th class="center">产品类型</th>
										<th class="center">产品编码</th>
										<th class="center">产品价格</th>
										<th class="center">结算价格</th>
										<th class="center">成本价格</th>
										<th class="center">佣金比例</th>
										<th class="center">佣金</th>
										<th class="center">产品状态</th>
										<th class="center">默认/推荐</th>
										<th class="center">是否压单</th>
										<th class="center">修改人</th>
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
														<label class="pos-rel"><input type='checkbox' name='ids' value="${var.product_id}" class="ace" /><span class="lbl"></span></label>
													</td>
													<td class='center' style="width: 30px;">${vs.index+1}</td>
													<td class='center'>${var.product_name}</td>
													<td class='center'>${var.specify_name}</td>
													<td class='center'>${var.style_name}</td>
													<td class='center'>${var.product_code}</td>
													<td class='center'>${var.product_price}</td>
													<td class='center'>${var.settlement_price}</td>
													<td class='center'>${var.cost_price}</td>
													<td class='center'>${var.profit}</td>
													<td class='center'>${var.profit_price}</td>
													<td class='center'>${var.status==0?"启用":"下架" }</td>
													<td class='center'>${var.is_default==1?"是":"否" } / ${var.is_recommend==1?"是":"否" }</td>
													<td class='center'>${var.ispress==1?"是":"否" }</td>
													<td class='center'>${var.modifier}</td>
													<td class="center">
														<c:if test="${QX.edit != 1 && QX.del != 1 }">
														<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
														</c:if>
														<div class="hidden-sm hidden-xs btn-group">
														<c:if test="${QX.edit == 1 }">
															<a class="btn btn-xs btn-success" title="编辑" onclick="edit('${var.product_id}');">
																<i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
															</a>
														</c:if>
														<c:if test="${QX.del == 1 }">
															<a class="btn btn-xs btn-danger" onclick="del('${var.product_id}');">
																<i class="ace-icon fa fa-trash-o bigger-120" title="删除"></i>
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
																		<a style="cursor:pointer;" onclick="edit('${var.product}');" class="tooltip-success" data-rel="tooltip" title="修改">
																			<span class="green">
																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			</span>
																		</a>
																	</li>
																	</c:if>
																	<c:if test="${QX.del == 1 }">
																	<li>
																		<a style="cursor:pointer;" onclick="del('${var.product}');" class="tooltip-error" data-rel="tooltip" title="删除">
																			<span class="red">
																				<i class="ace-icon fa fa-trash-o bigger-120"></i>
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
													<td colspan="100" class="center">没有相关数据</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
								<div class="page-header position-relative">
								<table style="width:100%;">
									<tr>
										<td style="vertical-align:top;">
											<c:if test="${QX.add == 1 }">
											<c:if test="${null != pid && pid!=''}">
											<a class="btn btn-sm btn-success" onclick="add('${pid}');">新增</a>
											</c:if>
											</c:if>
											<%-- <c:if test="${null != pd.id && pd.id != ''}">
											<a class="btn btn-sm btn-success" onclick="goSondict('${pd.parent_id}');">返回</a>
											</c:if> --%>
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
		//检索
		function gsearch(){
			top.jzts();
			$("#Form").submit();
		}
		
		$(function() {
			
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
		
		//新增
		function add(pid){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>pt/goAdd.do?pid='+pid;
			 diag.Width = 750;
			 diag.Height = 800;
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
		function del(Id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>pt/updateDrById.do?product_id="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			});
		}
		
		//批量修改价格
		function update(pid){
			var pct= document.getElementById("pct").value;
			if( pct == null || pct == '' || isNaN(pct) ){
			   bootbox.alert("请按要求填写信息!");
			   //$("#pct").focus();
			   return ;
			}
			bootbox.dialog({
			      message: "请点击选择将何种价格按比例修改,您修改的比例值为: <font size=\'3\' color=\'red\'>" + pct + "</font>",
			      title: "请选择",
			      buttons:{
			         productBtn:{
			            label: "修改结算价格",
			            className: "btn-primary",
			            callback:function(){
			            updateToServer(pid, pct, 1);
			            }
			         },
			         accountBtn:{
			            label: "修改成本价格",
			            className: "btn-primary",
			            callback:function(){
			            updateToServer(pid, pct, 2);
			            }
			         },
			         commissionBtn:{
			            label: "修改佣金",
			            className: "btn-primary",
			            callback:function(){
			            updateToServer(pid, pct, 3);
			            }
			         },
			         cancelBtn:{
			             label: "取消",
			             className: "btn-default",
			             callback:function(){
			             }
			         }
			      }
			});
		}
		//批量修改的选择到服务器
		function updateToServer(pid, pct, type){
					top.jzts();
					var url = "<%=basePath%>pt/updateCostPrice.do?pid="+ pid + "&pct=" + pct + "&chooseType=" + type;
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});		
		}
		
		//修改
		function edit(product_id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>pt/goEdit.do?product_id=' + product_id;
			 diag.Width = 750;
			 diag.Height = 800;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
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
			
					//批量上架
		function updateProductStatus1(msg){
			if($("#inputpid").val()==""){
				$("#updatetd1").tips({
					side:3,
		            msg:'请选择左边树节点再操作',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#inputpid").focus();
			return false;
			}
	
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
						if(msg == '确定要上架吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>pt/updateProductStatus1.do?tm='+new Date().getTime(),
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
		
		//批量下架
		function updateProductStatus2(msg){
			if($("#inputpid").val()==""){
				$("#updatetd2").tips({
					side:3,
		            msg:'请选择左边树节点再操作',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#inputpid").focus();
			return false;
			}
			
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
						if(msg == '确定要下架吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>pt/updateProductStatus2.do?tm='+new Date().getTime(),
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
		
		//批量压单
		function updateProductStatus3(msg){
			if($("#inputpid").val()==""){
				$("#updatetd2").tips({
					side:3,
		            msg:'请选择左边树节点再操作',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#inputpid").focus();
			return false;
			}
			
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
						if(msg == '确定要压单吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>pt/updateProductStatus3.do?tm='+new Date().getTime(),
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
		
		
		
		//批量取消压单
		function updateProductStatus4(msg){
			if($("#inputpid").val()==""){
				$("#updatetd2").tips({
					side:3,
		            msg:'请选择左边树节点再操作',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#inputpid").focus();
			return false;
			}
			
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
						if(msg == '确定要取消压单吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>pt/updateProductStatus4.do?tm='+new Date().getTime(),
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
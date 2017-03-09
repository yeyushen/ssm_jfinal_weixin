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
					
					<form action="ch/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="cash_id" id="cash_id" value="${pd.cash_id}"/>
						<input type="hidden" name="cash_method" id="cash_method" value="${pd.cash_method}"/>
						<div id="zhongxin">
						<br/>
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;"><b>姓名:</b></td>
								<td style="width:100px;text-align: left;padding-top: 13px;"><b>
<%-- 								<c:choose>
								<c:when test="${pd.name == ''} ">
								${pd.nick_name}
								</c:when>
								<c:otherwise>
								${pd.name}
								</c:otherwise>
								</c:choose> --%>
								${pd.nick_name}
								</b>
								</td>
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;"><b>申请金额:</b></td>
								<td style="width:100px;text-align: left;padding-top: 13px;"><b>${pd.amount}</b>
								</td>							
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;"><b>申请时间:</b></td>
								<td style="width:100px;text-align: left;padding-top: 13px;"><b>${pd.cash_time}</b>
								</td>							
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;"><b>提现方式:</b></td>
								<td style="width:100px;text-align: left;padding-top: 13px;">
											<c:if test="${pd.cash_method==1 }"><b>微信红包</b></c:if>
											<c:if test="${pd.cash_method==2 }"><b>支付宝</b></c:if>
											<c:if test="${pd.cash_method==3 }"><b>银行转账</b></c:if>
								</td>						
							</tr>
							<%-- <tr>
								<td style="width:100px;text-align: right;padding-top: 13px;"><b>openid:</b></td>
								<td style="width:100px;text-align: left;padding-top: 13px;"><b>${pd.open_id}</b></td>
							</tr> --%> 
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;"><b>开户行:</b></td>
								<td style="width:100px;text-align: left;padding-top: 13px;"><b>${pd.bank_addr}</b>
								</td>							
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;"><b>收款账号:</b></td>
								<td style="width:100px;text-align: left;padding-top: 13px;"><b>${pd.account}</b>
								</td>							
							</tr>
							
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;"><b>审批人:</b></td>
								<td style="width:100px;text-align: left;padding-top: 13px;"><b>${pd.approve}</b>
								</td>							
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;"><b>审批时间:</b></td>
								<td style="width:100px;text-align: left;padding-top: 13px;"><b>${pd.approve_time}</b>
								</td>							
							</tr>							
							<tr>
							<td style="width:100px;text-align: right;padding-top: 13px;"><b>发放状态:</b></td>
							<td id="statusId" style="padding-left:2px;">&nbsp;
							<c:choose>
							<c:when test="${pd.status == 1 }">
								   <select class="chosen-select form-control" name="status"
												id="status" data-placeholder="发放状态"
												style="vertical-align:top;" style="width:98%;">
													<option value=""></option>
													<option value="1" <c:if test="${pd.status==1 }">selected</c:if>>已提交</option>
													<option value="2" <c:if test="${pd.status==2 }">selected</c:if>>已完成</option>
													<option value="3" <c:if test="${pd.status==3 }">selected</c:if>>不通过</option>
									</select>								
	                        </c:when>
							<c:otherwise>
							
							<c:if test="${pd.status == 2 }">
							<font style="font-weight:bold;color:red;">已 完 成</font>
						       <!-- <input type="text" name="status" id="status" class="nav-search-input" autocomplete="off" value="已完成" readonly="readonly"> -->
						    </c:if>
							<c:if test="${pd.status == 3 }">
							<font style="font-weight:bold;color:red;">不 通 过</font>
						       <!-- <input type="text" name="status" id="status" class="nav-search-input" autocomplete="off" value="不通过" readonly="readonly"> -->
						    </c:if>						    
						    
							</c:otherwise>
							</c:choose>

								</td>							
							</tr>
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;"><b>备注:</b></td>
								<td>
								<input type="text" name="note" id="note" value="${pd.note}" maxlength="255" placeholder="这里输入备注" title="备注" style="width:98%;" /></td>
								</td>							
							</tr>
							
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;"><b>加入时间:</b></td>
								<td style="width:100px;text-align: left;padding-top: 13px;"><b>${pd.create_date}</b>
								</td>					
							</tr> --%>

							<tr>
								<td class="center" colspan="10">
								<c:choose>
								<c:when test="${pd.status == 1 }">
									<a class="btn btn-sm btn-primary" onclick="save();">确定审核</a>&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;<a class="btn btn-sm btn-danger" onclick="top.Dialog.close();">取消</a>
								</c:when>
								<c:otherwise>
								    <a class="btn btn-sm btn-primary" onclick="top.Dialog.close();">返回</a>
								</c:otherwise>
								</c:choose>
								</td>
							</tr>
						</table>
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
		//保存
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
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
		
		function save(){
		    var sta = $("#status").val();
		    var way = $("#cash_method").val();
		    //红包提现方式
		    if( sta == 2 && way == 1)
		    {
		       var str = $("#cash_id").val();
		       top.jzts();
		       $.ajax({
		         type:"post",
		         url:'<%=basePath%>redpack?tm=' + new Date().getTime(),
		         data:{DATA_IDS:str},
		         dataType:'json',
		         cache:false,
		         success:function(data){
		          top.hangge();
				 bootbox.alert(data.msg, function(){
				 if( data.msg == '恭喜,提现成功.'){
			                    $("#Form").submit();
			                  $("#zhongxin").hide();
			                 $("#zhongxin2").show();
			          }				  
				 });      
		         }
		       });
		       
		    }else{
			   $("#Form").submit();
			   $("#zhongxin").hide();
			   $("#zhongxin2").show();
			}
			
		}
		</script>
</body>
</html>
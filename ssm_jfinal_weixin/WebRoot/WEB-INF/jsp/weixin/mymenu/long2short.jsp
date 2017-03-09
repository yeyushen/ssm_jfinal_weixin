<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/index/top.jsp"%>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
  </head>
  
  <body>
    <div class="col-xs-12 col-sm-9 col-lg-10">
										

<div class="clearfix">
	<form class="form form-horizontal" action="" method="post">
		<input type="hidden" name="id" value="">
		<div class="panel panel-default">
			<div class="panel-heading">
				长链接转短链接
			</div>
			<div class="panel-body">
				<div class="form-group">
					<label class="col-xs-12 col-sm-3 col-md-2 control-label">请输入长链接:</label>
					<div class="col-sm-9 col-xs-12">
						<input type="text" name="longurl" class="form-control" id="longurl" value="" placeholder="请输入长链接" autocomplete="off">
						<div class="help-block">请输入您要转换的长链接，支持http://、https://、weixin://wxpay 格式的url</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-3 col-md-2 control-label"></label>
					<div class="col-sm-9 col-xs-12">
						<div class="input-group">
							<span id="change" class="btn btn-primary">立即转换</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-3 col-md-2 control-label">生成的短连接 </label>
					<div class="col-sm-9 col-xs-12">
						<input type="text" name="shorturl" id="shorturl" value="" class="form-control" readonly="">
					</div>
				</div>
				
			</div>
		</div>
	</form>
</div>
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
<script>
	//点击选择【系统连接】事件
	$('#longurl_but').click(function(){
		var but = $(this);
		require(['util'], function(util){
			var ipt = but.parent().prev();
			util.linkBrowser(function(href){
				var site_url = "http://wz.leedate.com/";
				if(href.substring(0, 4) == 'tel:') {
					util.message('长链接不能设置为一键拨号');
					return;
				} else if(href.indexOf("http://") == -1 && href.indexOf("https://") == -1) {
					href = href.replace('./index.php?', '/index.php?');
					href = site_url + 'app' + href;
				}
				ipt.val(href);
			});
		});
	});
	$(function(){
		$('#change').click(function(){
			var longurl = $('#longurl').val().trim();
			if(longurl == '') {
				alert('请输入长链接');
				return;
			} else if(longurl.indexOf("http://") == -1 && longurl.indexOf("https://") == -1 && longurl.indexOf("weixin://") == -1) {
				alert('请输入有效的长链接');
				return;
			}
			var change = $(this);
			var img_url = "./index.php?c=platform&a=url2qr&do=qr&";
			change.html('<i class="fa fa-spinner"></i> 转换中');
			$.post("mymenu/do2short", {'longurl' : longurl}, function(data){
				if(data != 'err') {
					data = $.parseJSON(data);
				}
				if(data.errcode == '-1') {
					alert(data.errmsg);
					change.html('转换失败');
					return;
				} else {
					$('#shorturl').val(data.short_url);
					$('#qrsrc').attr('src', img_url + 'url=' + data.short_url);
					change.html('立即转换');
				}
			});
		});
	});
	
	$(top.hangge());//关闭加载状态
</script>
			</div>
  </body>
</html>

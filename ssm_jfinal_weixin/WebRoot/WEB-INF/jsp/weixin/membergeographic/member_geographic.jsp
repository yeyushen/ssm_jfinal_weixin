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
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<!-- 日期框 -->
<link rel="stylesheet" href="static/ace/css/datepicker.css" />
<!-- jsp文件头和头部 -->
<%@ include file="../../system/index/top.jsp"%>

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
						<form action="membergeographic/list.do" method="post" name="Form" id="Form">
						<table style="margin-top:5px;">
							<tr>
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" placeholder="这里输入会员名称" class="nav-search-input" id="keywords" name="keywords" autocomplete="off" value="${pd.keywords}" />
											<i class="ace-icon fa fa-search nav-search-icon"></i>
										</span>
									</div>
								</td>
								<td style="padding-left:2px;"><input class="span10 date-picker" name="lastStart" id="lastStart"  value="${pd.lastStart}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="开始日期" title="开始日期"/></td>
								<td style="padding-left:2px;"><input class="span10 date-picker" name="lastEnd" name="lastEnd"  value="${pd.lastEnd}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="结束日期" title="结束日期"/></td>								
								<c:if test="${QX.cha == 1 }">
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-xs btn-info" title="清空" onclick="clear();">清空</a></td>		
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-xs btn-info" onclick="gsearch();"  title="检索">搜索</a></td>
								</c:if>
							</tr>
						</table>
						<!-- 检索  -->
					
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:50px;">会员地理信息分布图</th>
								</tr>
							</thead>
													
							<tbody>
										<tr>
										    <td class="center">
										    <div id="map" style="width:100%;height:680px"></div>
											</td>
										</tr>
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

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 删除时确认窗口 -->
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!-- 百度地图js -->
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=G5IducoC64P4FkSIEs0UAca0zNGstyNg"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>	
	<script type="text/javascript">
		
		var map = new BMap.Map("map");
		var point = new BMap.Point(113.397,23.126);
		//map.centerAndZoom(point, 15);
		var locations = '${mLocation}';
		var locationsJson = eval('(' + locations +')');
		//alert(locations);
		map.centerAndZoom("广州");
		map.enableScrollWheelZoom();
		var marker;
/* 		for(var i = 0; i < locationsJson.length; i++){
		    alert(locations[i].lng + "---" +locations[i].lat)
		} */
		for(var i in locationsJson){
          marker = new BMap.Marker(new BMap.Point(locationsJson[i].bdlng, locationsJson[i].bdlat));
          var label = new BMap.Label(locationsJson[i].membername, {offset:new BMap.Size(20,-10)});
          marker.setLabel( label );
		  map.addOverlay(marker);
		}
     $(top.hangge());//关闭加载状态
     
		$(function() {
		
			//日期框
			$('.date-picker').datepicker({
				autoclose: true,
				todayHighlight: true
			});
		});     
     
     
     function gsearch(){
     if( $("#lastStart").val() == "" &&  $("#lastEnd").val() == "" && $("#keywords").val() == "" ){
               bootbox.alert("请填写搜索信息.");
               $("#keywords").focus();
               return;     
     }
     
/*           if( $("#keywords").val() == "" ){
               bootbox.alert("请填写会员名称.");
               $("#keywords").focus();
               return;
          } */
<%--           if( $("#lastStart").val() == "" &&  $("#lastEnd").val() == "" ){
          $.ajax({
            type: "post",
            url: '<%=basePath%>membergeographic/search.do?tm='+new Date().getTime(),
            data: {member_name: $("#keywords").val() },
            cache: false,
            dataType: 'json',
            success: function(data){
                 if( data.msg == "noname" ){
                 bootbox.alert("请您填写会员名称.");
                 }else if( data.msg == "nodata"){
                 bootbox.alert("没有此会员地理信息.");
                 }else if( data.msg == "success"){
                 var lng = data.lng;
                 var lat = data.lat;
                 map.panTo(new BMap.Point(lng, lat));
                 map.zoomIn();
                 
                 }else{
                 bootbox.alert("抱歉,查询发生错误,请重试或联系管理员.");
                 }
            }
          });
          }else{ --%>
			top.jzts();
			$("#Form").submit();
//          }
     
     }
     
		//重置
		function clearnumber(){
			document.getElementById("keywords").value = "";
			document.getElementById("lastStart").value = "";
			document.getElementById("lastEnd").value = "";
		}     
     
     
	</script>


</body>
</html>
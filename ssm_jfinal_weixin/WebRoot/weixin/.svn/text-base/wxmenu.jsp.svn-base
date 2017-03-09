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
<html>
    <head>
        <title>H5地理位置Demo</title>
        <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    </head>
    <body>
        <div id="map" style="width:600px; height:400px">
        </div>
    </body>
    <script type="text/javascript">
        
        //ajax 跨域请求,javaScript
        $.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js', function(_result) {
            if (remote_ip_info.ret == '1') {
            	console.log('国家：' + remote_ip_info.country + ' 省：' + remote_ip_info.province + ' 市：' + remote_ip_info.city + ' 区：' + remote_ip_info.district + ' ISP：' + remote_ip_info.isp + ' 类型：' + remote_ip_info.type + ' 其他：' + remote_ip_info.desc);
            } else {
                console.log('没有找到匹配的IP地址信息！');
            }
        });
        
        
        //ajax 跨域请求,jsonp
        var url = 'http://chaxun.1616.net/s.php?type=ip&output=json&callback=?&_=' + Math.random();
        $.getJSON(url, function(data) {
            console.log(data.Ip);
        });
    </script>
</html>


﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
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
    <meta http-equiv="Expires" CONTENT="0">  
	<meta http-equiv="Cache-Control" CONTENT="no-cache">  
	<meta http-equiv="Pragma" CONTENT="no-cache"> 
	<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1" >
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta name="App-Config" content="fullscreen=yes,useHistoryState=yes,transition=yes">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<style>
	</style>
  </head>
  
  <body>
    <img id="img_qrcode" style="position:fixed;" src=""  height="100%" width="100%" /> 
  </body>

  <script>
	
  </script>
</html>

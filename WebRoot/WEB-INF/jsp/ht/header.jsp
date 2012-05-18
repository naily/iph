<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<!-- -->
	<link rel="stylesheet" type="text/css" href="header.css">
	
  </head>
  
  <body>
    <div id="ht_header_div">
    	<table width="80%" border="0">
	  <tr>
	    <td>&nbsp;登录</td>
	    <td>&nbsp;系统设置</td>
	    <td>&nbsp;数据管理</td>
	    <td>&nbsp;用户管理</td>
	    <td>&nbsp;访问权限管理</td>
	    <td>&nbsp;管理日志</td>
	    <td>&nbsp;系统服务</td>
	    <td>&nbsp;新闻管理</td>
	  </tr>
	</table>

    </div>
  </body>
</html>

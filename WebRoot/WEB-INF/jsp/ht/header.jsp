<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../jstl.jsp" flush="true" />
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
    	<div> <a href="ht/lang/zh_CN.do">中文</a> <a href="ht/lang/en_US.do">英文</a> </div>
    	<table width="80%" border="0">
		  <tr>
		    <td>&nbsp;${msg['ht_menu_login']}</td>
		    <td>&nbsp;<a href='ht/admins.do'>${msg['ht_menu_sysseting']}</a></td>
		    <td>&nbsp;${msg['ht_menu_datamgr']}</td>
		    <td>&nbsp;${msg['ht_menu_usermgr']}</td>
		    <td>&nbsp;${msg['ht_menu_protect']}</td>
		    <td>&nbsp;${msg['ht_menu_logmgr']}</td>
		    <td>&nbsp;${msg['ht_menu_sysservice']}</td>
		    <td>&nbsp;${msg['ht_menu_newsmgr']}</td>
		  </tr>
		</table>

    </div>
  </body>
</html>

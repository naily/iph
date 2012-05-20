<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台登陆</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/jquery/jqueryAjaxBox.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
  </head>
  
  <body>
    <jsp:include page="header.jsp" flush="true" />
    
    <div id="content">
    	<table width="600" border="1">
    	<tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;${msg['ht_login_username']} <input name="username" type="text"></td>
      </tr>
      <tr>
        <td>&nbsp;${msg['ht_login_password']} <input name="mypassword" type="password" ></td>
      </tr>
      <tr>
        <td>&nbsp;${msg['ht_login_code']} <input name="code" type="text" ><img src="" border=0/> </td>
      </tr>
      <tr>
        <td>&nbsp; <input id="but-login" type="button" value="${msg['ht_login_submit']}"></td>
      </tr>
      
    </table>

    </div>
  </body>
</html>

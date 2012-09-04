<%@ page language="java" import="java.util.*,cn.fam1452.dao.pojo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="../jstl.jsp" %>
<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统设置</title>
	<!--
	-->
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css">
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/ht_base_layout.css">
	<link rel="stylesheet" type="text/css" href="css/default/toolbar.css">
	<script type="text/javascript" src="js/admins.js"></script>
	
  </head>
  
  <body>
    <jsp:include page="header.jsp" flush="true" />
    
    <div id="pageleft">
    	<!-- 左侧菜单 -->
    	<div class="left2_1">管理员列表</div>
    	<div class="left2_1"><a href="javascript:modifpass.open();" class="a3">修改密码</a></div>
    </div>
    <div id="center_right">
    	<!-- 右侧内容 -->
    	<div>
	    	<span id="toolbar" class="om-widget-header om-corner-all">
		        <a id="createbut" href="javascript:void(0)">添加</a>
		        <a id="del"  href="javascript:void(0)">删除</a>
	    	</span>
    		
    		<div id="createadmin">
    			<p>登录名:<input id="dlmId" empt="登录名不能为空"/></p>
    			<p> 密码 :&nbsp;&nbsp;<input id="mmId" type="password" empt="密码不能为空"/> </p>
    			<p> 姓名 :&nbsp;&nbsp;<input id="xmId" empt="姓名不能为空"/></p>
    			<p> 角色 :&nbsp;&nbsp;<input id="jsId" /> </p>
    			<p><div id="info" class="errorMessages">&nbsp;</div></p>
    			<p class="p_align"><button onclick="savedata();">保存</button></p>
    			
    		</div>
    		<div id="modifPassword">
    			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   原密码 : <input id="oldpass" type="password" empt="密码不能为空"/></p>
    			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   新密码 : <input id="newpass" type="password" empt="新密码不能为空"/> </p>
    			<p>新密码确认 : <input id="newpass2" type="password" empt="确认新密码"/></p>
    			<p><div id="info_modif" class="errorMessages">&nbsp;</div></p>
    			<p class="p_align"><button onclick="modifpass.submit() ;">修改</button></p>
    			
    		</div>
    		
    	</div>
    	<table id="list0"></table>
    </div>
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

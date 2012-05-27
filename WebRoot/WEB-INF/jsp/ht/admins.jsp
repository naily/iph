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
	<script type="text/javascript" src="js/admins.js"></script>
  </head>
  
  <body>
    <jsp:include page="header.jsp" flush="true" />
    <div id="pageleft">
    	<!-- 左侧菜单 -->
    	<p>管理员列表</p>
    	<p><a href="javascript:;">修改密码</a></p>
    </div>
    <div id="content">
    	<!-- 右侧内容 -->
    	<div>
    		<button onclick="showModelessDialog();">增加</button>
    		<div id="createadmin">
    			<p>登录名:<input id="dlmId" empt="登录名不能为空"/></p>
    			<p>密码 :&nbsp;&nbsp;<input id="mmId" type="password" empt="密码不能为空"/> </p>
    			<p>姓名 :&nbsp;&nbsp;<input id="xmId" /></p>
    			<p>角色 :&nbsp;&nbsp;<input id="jsId" /> </p>
    			<p id="info" class="font_red"></p>
    			<p class="p_align"><button onclick="savedata();">保存</button></p>
    			
    		</div>
    		<button onclick=" ;">删除</button>
    	</div>
    	<table id="list0"></table>
    </div>
  </body>
</html>

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
    <title>电离层频高图管理</title>
	<!--
	-->
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css">
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/ht_base_layout.css">
	<script type="text/javascript" src="js/pgtlist.js"></script>
  </head>
  
  <body>
    <jsp:include page="header.jsp" flush="true" />
    
    <div id="imagePreview" title="图片预览"> </div> 
    
    <div id="center" >
    	<span id="toolbar" class="om-widget-header om-corner-all">
	        <a id="createbut" href="javascript:void(0)">添加</a>
	        <a id="updatebut"  href="javascript:void(0)">修改</a>
	        <a id="del"  href="javascript:void(0)">删除</a>
    	</span>
    	
    	<table id="list0"></table>
    </div>
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

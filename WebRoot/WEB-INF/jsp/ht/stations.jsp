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
    <title>观测站管理</title>
	<!--
	-->
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css">
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/ht_base_layout.css">
	
	<script type="text/javascript" src="js/Global.js"></script>
	<script type="text/javascript" src="js/stations.js"></script>
  </head>
  
  <body>
    <jsp:include page="header.jsp" flush="true" />
    <div id="createblock">
    	<p class="p_input_wid400">名称:<input id="mcId" /> 位置:<input id="wzId" /></p>
    	<p class="p_input_wid400">单位:<input id="dwmcId" />  经度:<input id="jdId" /></p>
    	<p class="p_input_wid400">通讯地址:<input id="txdzId" /> 纬度:<input id="wdId" /></p>
    	<p class="p_input_wid400">联系电话:<input id="lxdhId" /> 时区:<input id="sqId" /></p>
    	<p class="p_input_wid400">Email:<input id="emId" /> 主页:<input id="zyId" /></p>
    	<p class="p_input_wid220">邮编:<input id="ybId" />   </p>
    	<p class="p_input_wid400">介绍:<textarea id="jsId" rows="3" cols="48"></textarea> </p>
    	<p><div id="info" class="errorMessages">&nbsp;</div></p>
    	<p class="p_align"><button onclick="save.save();">保存</button>
    	<button onclick="save.clear(); ">清空</button></p>
    </div>
    <div id="center" class="loginbox1">
    	<button id="createbut">添加</button>
    	<button >修改</button>
    	<button onclick="">删除</button>
    	<table id="list0"></table>
    </div>
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>   
    <title></title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
    	var basepath = "<%=basePath%>" ;
    </script>
	<script type="text/javascript" src="js/index_left.js"></script>

  </head>
  
  <body>
   <div  id="left">
<div class="title1">${msg['qt_data_navigation']}</div>
<div class="title2">${msg['qt_Ionospheric_ionogram']}</div>
  <div id="leftTree1"  style="width:185px; height:120px; overflow:auto">
   <ul id="pgtTree"></ul>     
</div> 
<div class="title2">${msg['qt_Ionospheric_parameters']}</div>
<div id="leftTree2"  style="width:185px; height:120px; overflow:auto">
   <ul id="paraTree"></ul>     
</div> 
<div class="title2">${msg['qt_Report_scan']}</div>
<div id="leftTree32"  style="width:185px; height:120px; overflow:auto">
   <ul id="scanpicTree"></ul>     
</div> 
<div class="title3">${msg['qt_links']}</div>
<table width="170" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" class="fontstyle2"><a href="http://www.igsnrr.ac.cn" title="中国科学院地理科学与资源研究所" target="_blank" class="a3">中国科学院地理科学...</a></td>
  </tr>
  <tr>
    <td height="30" class="fontstyle2"><a href="http://www.most.gov.cn/" target="_blank" class="a3">国家科技部</a></td>
  </tr>
  <tr>
    <td height="30" class="fontstyle2"><a href="http://www.mlr.gov.cn/" target="_blank" class="a3">国土资源部</a></td>
  </tr>
  <tr>
    <td height="30" class="fontstyle2"><a href="http://www.cas.ac.cn/" target="_blank" class="a3">中国科学院</a></td>
  </tr>
  <tr>
    <td height="30" class="fontstyle2"><a href="http://www.irsa.ac.cn/" target="_blank" class="a3">遥感应用研究所</a></td>
  </tr>
  <tr>
    <td height="30" class="fontstyle2"><a href="http://www.rcees.ac.cn/" target="_blank" class="a3">生态环境研究中心</a></td>
  </tr>
</table>
</div>
  </body>
</html>

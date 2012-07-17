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
    <title>新闻管理</title>
	<!--
	-->
<link href="images/1.css" type="text/css" rel="stylesheet" />
<link href="css/index.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/default/om-default.css"/>
<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	
	<style type="text/css">
       label.error{
        background: #fff6bf url(images/errorIcon.png) center no-repeat;
		background-position: 5px 50%;
		text-align: left;
		padding: 2px 20px 2px 25px;
		border: 1px solid #ffd324;
		display: none;
		width: 200px;
		margin-left: 10px;
       }
    </style>
	
  </head>
  
  <body>
    <jsp:include page="header.jsp" flush="true" />
    
   <div  id="right">
<div class="title8">新闻列表</div>
<div class="newsbox">
<li><a href="#" class="a2">电离层频高图电离层频高图电离层频高图电离层频高图</a></li>
<li><a href="#" class="a2">电离层频高图电离层频高图电离层频高图电离层频高图</a></li>
<li><a href="#" class="a2">电离层频高图电离层频高图电离层频高图电离层频高图</a></li>
<li><a href="#" class="a2">电离层频高图电离层频高图电离层频高图电离层频高图</a></li>
<li><a href="#" class="a2">电离层频高图电离层频高图电离层频高图电离层频高图</a></li>
<li><a href="#" class="a2">电离层频高图电离层频高图电离层频高图电离层频高图</a></li>
<li><a href="#" class="a2">电离层频高图电离层频高图电离层频高图电离层频高图</a></li>
<li><a href="#" class="a2">电离层频高图电离层频高图电离层频高图电离层频高图</a></li>
<li><a href="#" class="a2">电离层频高图电离层频高图电离层频高图电离层频高图</a></li>
<li><a href="#" class="a2">电离层频高图电离层频高图电离层频高图电离层频高图</a></li>
<div class="newspage"><a href="#" class="a3">首页</a> <a href="#" class="a3">上一页</a>  当前<span class="fontstyle3">4</span>/5页  <a href="#" class="a3">下一页</a> <a href="#" class="a3">末页</a></div>
</div>
</div>
<!--right 结束-->

<div  id="left">
<div class="title1">数据导航</div>
<div class="title2">电离层频高图</div>
<table width="170" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" class="fontstyle2"><a href="#" class="a3">武汉站</a></td>
  </tr>
  <tr>
    <td height="30" align="right" class="fontstyle2"><a href="#"  class="a3">1946年</a></td>
  </tr>
  <tr>
    <td height="30" align="right" class="fontstyle2"><a href="#"  class="a3">1946年</a></td>
  </tr>
  <tr>
     <td height="30" align="right" class="fontstyle2"><a href="#"  class="a3">...</a></td>
  </tr>
</table>
<div class="title2">电离层参数</div>
<table width="170" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" class="fontstyle2"><a href="#" class="a3">武汉站</a></td>
  </tr>
  <tr>
    <td height="30" align="right" class="fontstyle2"><a href="#"  class="a3">1946年</a></td>
  </tr>
  <tr>
    <td height="30" align="right" class="fontstyle2"><a href="#"  class="a3">1946年</a></td>
  </tr>
  <tr>
     <td height="30" align="right" class="fontstyle2"><a href="#"  class="a3">...</a></td>
  </tr>
</table>
<div class="title2">区域电离层参数特征图</div>
<table width="170" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" class="fontstyle2"><a href="#" class="a3">武汉站</a></td>
  </tr>
  <tr>
    <td height="30" align="right" class="fontstyle2"><a href="#"  class="a3">1946年</a></td>
  </tr>
  <tr>
    <td height="30" align="right" class="fontstyle2"><a href="#"  class="a3">1946年</a></td>
  </tr>
  <tr>
     <td height="30" align="right" class="fontstyle2"><a href="#"  class="a3">...</a></td>
  </tr>
</table>
<div class="title3">相关链接</div>
<table width="170" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" class="fontstyle2"><a href="#" class="a3">...</a></td>
  </tr>
  <tr>
    <td height="30" class="fontstyle2"><a href="#"  class="a3">...</a></td>
  </tr>
  <tr>
    <td height="30" class="fontstyle2"><a href="#"  class="a3">...</a></td>
  </tr>
  <tr>
    <td height="30" class="fontstyle2"><a href="#"  class="a3">...</a></td>
  </tr>
</table>
</div><!--left 结束-->
<div id="bottom">
版权所有：***<br />
联系电话：010-51888888  地址：*** 邮编：***
</div><%--
    
    <jsp:include page="footer.jsp" flush="true" />
  --%></body>
</html>

<%@ page language="java" import="java.util.*,cn.fam1452.dao.pojo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="../jstl.jsp" %>
<!DOCTYPE HTML >
<html>
  <head>
  <meta http-equiv="X-UA-Compatible" content="IE=8">
    <base href="<%=basePath%>">
    <title>管理用户评论信息</title>
	<!--
	-->
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css">
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/ht_base_layout.css">
	<link rel="stylesheet" type="text/css" href="css/default/toolbar.css">
	
	<script type="text/javascript" src="js/Global.js"></script>
	<script type="text/javascript" src="js/qtcm.js"></script>
	<style type="text/css">
		label.error{
        background: #fff6bf url(images/errorIcon.png) center no-repeat;
		background-position: 5px 50%;
		text-align: left;
		padding: 2px 2px 2px 22px;
		border: 1px solid #ffd324;
		display: none;
		width: 50px;
		float : right ;
		margin-left: 4px;
       }
    </style>
    <script type="text/javascript">
    	var basepath = "<%=basePath%>" ;
    </script>
	
  </head>
  
  <body>
    <jsp:include page="header.jsp" flush="true" />
    
    <div id="center" >
		<div id="buttonbar"></div>
    	<table id="list0"></table>
    	
    </div>
    
    <div id="qtcm_feedback">
    	<span id="titinfo"></span>
  		<p id="cmtcontent"></p>
  		<p><textarea id="feedbackcontent" rows="4" cols="60"></textarea></p>
  		<input id="cid" type="hidden">
		<input id="saveFeedbackBut" src="images/baocun.png" type="image" title="提交" />
	</div>
    
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

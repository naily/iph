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
    <title>新闻管理</title>
	<!--
	新闻预览模板1
	-->
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
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
    
    <div id="pageleft">
    	<!-- 左侧菜单 -->
    	<p> </p>
    </div>
    <div id="center_right">
    	<!-- 右侧内容
    	<span id="toolbar" class="om-widget-header om-corner-all">
	        <a id="updatebut"  href="javascript:void(0)">修改</a>
	        <a id="del"  href="javascript:void(0)">删除</a>
    	</span>
    	 -->
        <div class="newsbox" style="margin: auto;">
			<div class="newstitle">${obj.title }</div>
			<div class="newsinfo"><!--  作者：*** 来源：*** --> 时间： <fmt:formatDate value="${ obj.publishDate }"  type="both" /></div>
			<div class="newstext">
			${obj.content }
			</div>
		</div>
        
    </div>
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

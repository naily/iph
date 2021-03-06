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
    <title>数据操作日志</title>
	<!--
	-->
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css">
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	
	<script type="text/javascript" src="js/library/highcharts225/highcharts.js"></script>
	<script type="text/javascript" src="js/library/highcharts225/modules/exporting.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/ht_base_layout.css">
	<link rel="stylesheet" type="text/css" href="css/default/toolbar.css">
	
	<script type="text/javascript" src="js/Global.js"></script>
	<script type="text/javascript" src="js/dStats.js"></script>
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
    	<div id="make-tab" >
	        <ul>
	            <li><a href="#tab1">详细记录</a></li>
	            <li><a href="#tab2">访问统计</a></li>
	            <li><a href="#tab3">下载统计</a></li>
	            <!--  <li><a href="#tab4">c++语言</a></li>  -->
	        </ul>
	        
	        <div id="tab1">
	        	<div class="butBottom"><input id = "comboActionType" />
		    			<!--  <input id = "comboAdmin" /> -->
		    			<input id="searchbut" align="middle" src="images/chaxun.png"  type="image" title="查询" />
		 		</div>
				<div id="buttonbar"></div>
		    	<table id="list0"></table>
	        </div>
	        <div id="tab2">
	        	<table id="list2"></table>
	        	<div id="statsvisitbar" style="width:800px;   margin: 0 auto"> </div>
	        </div>
	        <div id="tab3">
	        	<table id="list3"></table>
	        	<div id="statsdownbar" style="width:800px;   margin: 0 auto"> </div>
	        </div>
	        <!--  
	        <div id="tab4"></div>
	        -->
		</div>
    
    </div>
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

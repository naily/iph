<%@ page language="java" import="java.util.*,cn.fam1452.dao.pojo.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="../jstl.jsp"%>
<!DOCTYPE HTML >
<html>
	<head>
		<base href="<%=basePath%>">
		<title>电离层专题数据库管理系统</title>
		<link href="images/1.css" type="text/css" rel="stylesheet" />
		<link href="css/index.css" type="text/css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="css/default/om-default.css" />
		<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
		<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
		<script type="text/javascript" src="js/index_global_zn.js"></script>
		<script type="text/javascript" src="js/reportData.js"></script>

	</head>
<script type="text/javascript">
    	var basepath = "<%=basePath%>" ;
    </script>
	<body>
		<jsp:include page="header.jsp" flush="true" />
		<div id="right">
			<div class="title8">
				报表动态生成
			</div>
			<div class="reportbox">
				<!--  <div class="newstext">-->
					<table  class="report_table" align="center">
						<tr>
							<td height="30">
								观测站:
								<input id="stationId" name="stationId" class="boxinput_report"/>
							    &nbsp;&nbsp;
								年份：
								<input id="year" name="year" class="boxinput_report"/>
								&nbsp;&nbsp;
								月份：
								<input id="month" name="month" class="boxinput_report"/>
								&nbsp;
								<input type="checkbox" id=""/>所有月份
								&nbsp;&nbsp;
								参数:
								<input id="parameter" name="parameter" class="boxinput_report"/>
								&nbsp;
								<input type="checkbox" id=""/>所有参数 
							</td>
						</tr>
						<tr>						
							<td align="center">
								<input type="button" id="loadReportData" value="月报生成"/> 
								<input type="button" id="downloadReportData" value="月报下载"/>
							</td>
						</tr>
					</table>
					<br/>
					 <table id="reportGrid"></table>					
					
				</div>
		   <!-- </div> -->
		</div>
		<!--right 结束-->
		<jsp:include page="left.jsp" flush="true" />
		<!--left 结束-->
		<jsp:include page="../ht/footer.jsp" flush="true" />
	</body>
</html>

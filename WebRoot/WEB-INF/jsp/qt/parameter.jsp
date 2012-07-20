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
		
		<script type="text/javascript" src="js/reportData.js"></script>

	</head>

	<body>
		<jsp:include page="header.jsp" flush="true" />
		<div id="right">
			<div class="title8">
				报表动态生成
			</div>
			<div class="reportbox">
				<!--  <div class="newstext">-->
					<table  class="report_table">
						<tr>
							<td>
								选择观测站:
								<input id="stationid" name="stationid" class="boxinput_report"/>
							</td>
							<td>
								选择年份：
								<input id="year" name="year" class="boxinput_report"/>
							</td>
							<td>
								选择月份：
								<input id="month" name="month" class="boxinput_report"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								选&nbsp;择&nbsp;参&nbsp;数:
								<input id="parameter" name="parameter" class="boxinput_report"/>&nbsp;
								<input type="checkbox" id=""/>所有参数 
							</td>
							<td>
								月报生成 月报下载
							</td>
						</tr>
					</table>
					<table class="report_table">
					<tr>
					  <td>days</td>
					  <td class="report_table_td">00</td><td class="report_table_td">01</td>
					  <td class="report_table_td">02</td><td class="report_table_td">03</td>
					  <td class="report_table_td">04</td><td class="report_table_td">05</td>
					  <td class="report_table_td">06</td><td class="report_table_td">07</td>
					  <td class="report_table_td">08</td><td class="report_table_td">09</td>
					  <td class="report_table_td">10</td><td class="report_table_td">11</td>
					  <td class="report_table_td">12</td><td class="report_table_td">13</td>
					  <td class="report_table_td">14</td><td class="report_table_td">15</td>
					  <td class="report_table_td">16</td><td class="report_table_td">17</td>
					  <td class="report_table_td">18</td><td class="report_table_td">19</td>
					  <td class="report_table_td">20</td><td class="report_table_td">21</td>
					  <td class="report_table_td">22</td><td class="report_table_td">23</td>
					</tr>
					<tr>
					  <td>1</td><td>0</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td>
					</tr>
					</table>
				</div>
		   <!-- </div> -->
		</div>
		<!--right 结束-->
		<jsp:include page="left.jsp" flush="true" />
		<!--left 结束-->
		<jsp:include page="../ht/footer.jsp" flush="true" />
	</body>
</html>

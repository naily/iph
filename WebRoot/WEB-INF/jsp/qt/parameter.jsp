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
		<title>${msg['site_name']}</title>

		<link href="images/1.css" type="text/css" rel="stylesheet" />
		<link href="css/index.css" type="text/css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="css/default/om-default.css" />
		<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
		<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
		<script type="text/javascript" src="js/index_global.js"></script>
		<c:choose>
			 <c:when test='${msg.lang=="zh"}'> 
			 	<script type="text/javascript" src="js/index_global_zn.js"></script>
			</c:when>
			<c:otherwise>
				<script type="text/javascript" src="js/index_global_en.js"></script>
			</c:otherwise>
		</c:choose>	
		

	</head>
	<script type="text/javascript">
    	var basepath = "<%=basePath%>" ;
    </script>
	<body>
		<jsp:include page="header.jsp" flush="true" />
		<script type="text/javascript" src="js/reportData.js"></script>
		<div id="right">
			<div class="title8">
				${msg['qt_month_report_title']}
			</div>
			<div class="reportbox">
				<!--  <div class="newstext">-->
					<table  class="report_table" align="center">
						<tr>
							<td height="30">
								${msg['qt_month_report_station']}:
								<input id="stationId" name="stationId" class="boxinput_report"/>
							    &nbsp;&nbsp;
								${msg['qt_month_report_year']}:
								<input id="year" name="year" class="boxinput_report"/>
								&nbsp;&nbsp;
								${msg['qt_month_report_month']}:
								<input id="month" name="month" class="boxinput_report"/>
								&nbsp;
								<input type="checkbox" id="allMonth" value="allMonth"/>${msg['qt_month_report_allmonth']}
								&nbsp;&nbsp;							
							</td>
						</tr>
							<tr>
							<td height="30">																
								${msg['qt_month_report_selectpara']}:
								<input id="parameter" name="parameter" class="boxinput_report"/>
								&nbsp;
								<input type="checkbox" id="allPara" value="allPara"/>${msg['qt_month_report_allpara']} 
							</td>
						</tr>
						<tr>						
							<td align="center">
								<%--
								<input type="button" id="loadReportData" value="${msg['qt_month_report_press']}"/> 
								<input type="button" id="downloadReportData" value="${msg['qt_month_report_download']}"/>
								--%>
								<a href="javascript:void(0)"  id="loadReportData"><img src="images/yuebaosc.png"  border="0" /></a>
								<a href="javascript:void(0)"  id="downloadReportData"><img src="images/yuebaoxz.png"  border="0" /></a>
							</td>
						</tr>
					</table>
					
					<table id="showPages" style="display:none;"><tr><td><a href="javascript:void(0)" onclick="Previous()">上一页</a>&nbsp;&nbsp;<span id="pageNumber"></span>/<span id="pagesNum"></span> 页&nbsp;&nbsp;<a href="javascript:void(0)" onclick="Next()">下一页</a>&nbsp;&nbsp;</td></tr></table>
					<input type="hidden" id="parameterIndex"/>
					<input type="hidden" id="monthIndex" value="0"/>
					
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

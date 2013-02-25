<%@ page language="java" import="java.util.*,cn.fam1452.dao.pojo.*" pageEncoding="UTF-8"%>
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
		<script type="text/javascript" src="js/library/FusionCharts.js"></script>	
		<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
		<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
		<script type="text/javascript" src="js/library/highcharts225/highcharts.js"></script>
		<script type="text/javascript" src="js/library/highcharts225/modules/exporting.js"></script>
		<script type="text/javascript" src="js/library/highcharts225/themes/grid.js"></script>
		<script type="text/javascript" src="js/index_global.js"></script>
		<c:choose>
			 <c:when test='${msg.lang=="zh"}'> 
			 	<script type="text/javascript" src="js/index_global_zn.js"></script>
			</c:when>
			<c:otherwise>
				<script type="text/javascript" src="js/index_global_en.js"></script>
			</c:otherwise>
		</c:choose>
		<%--<script type="text/javascript" src="js/reportData.js"></script>
		--%>
		<script type="text/javascript" src="js/parameterChart2.js"></script>
	</head>
<script type="text/javascript">
    	var basepath = "<%=basePath%>" ;
    </script>
	<body>
		<jsp:include page="header.jsp" flush="true" />
		<div id="right" style="height:auto;">
			<div class="title8">
				${msg['qt_Ionospheric_parameters_graph']}
			</div>
			<div class="reportbox_chart" id="rightChartContent" ><!-- style="min-height:760px;" -->
					<table height="290" class="report_table" align="center">
						<tr>
						   	<td height="30" valign="top">
								${msg['qt_month_report_station']}：							
							</td>
							<td height="30"  valign="top">
								<div id="selectorPara" ></div>
								<input id="stationIds"  type="hidden"/>							 
							</td>
							<td height="30" valign="top">
							  <table width="100%">
							     <tr><td width="120">${msg['qt_month_report_selectpara']}:：</td><td><input id="parameter" name="parameter" class="boxinput_report"/></td></tr>
							     <tr><td >${msg['qt_parameter_start_date']}：</td><td><input id="startDate" name="startDate" class="boxinput_report"/></td></tr>
							     <tr><td >${msg['qt_parameter_end_date']}：</td><td><input id="endDate" name="endDate" class="boxinput_report"/></td></tr>
							     <tr>
							         <td>
							           ${msg['qt_month_report_hour']}：<br>			          
							         </td>
							          <td>
							          <input id="hourForChart" name="hour" class="boxinput_report"/>							          
							          </td>							
							      </tr>
							     <tr><td colspan="2" height="50" align="center">
							      <c:choose>
									 <c:when test='${msg.lang=="zh"}'> 
									 	 <a href="javascript:void(0)"  id="pressParaChart2"><img src="images/qxtsc.png"  border="0" /></a>		
									</c:when>
									<c:otherwise>
										 <input type="button" id="pressParaChart2" value="${msg['qt_parameters_graph_press']}"/> 
									</c:otherwise>
								</c:choose>	
							    
								</td></tr>
							   </table>												
							</td>
						
						</tr>								
					</table>
					<br/>		
					
					<div id="paraDataChart0" style="min-width: 400px; margin: 0 auto;height: 400px;"></div>
				
					
					<!--  height: 300px; -->					
				</div>
		   <!-- </div> -->
		</div>
		
		<!--right 结束-->
		<jsp:include page="left.jsp" flush="true" />
		<!--left 结束-->
	<c:choose>
		<c:when test='${msg.lang=="zh"}'> 
		 <jsp:include page="../ht/footer.jsp" flush="true" />
         </c:when>
         <c:otherwise> <jsp:include page="footer.jsp" flush="true" /></c:otherwise>				
	  </c:choose>
	</body>
</html>

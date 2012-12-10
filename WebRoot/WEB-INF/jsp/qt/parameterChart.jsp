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
	<meta http-equiv="X-UA-Compatible" content="IE=8">
		<base href="<%=basePath%>">
		<title>${msg['site_name']}</title>
		<link href="images/1.css" type="text/css" rel="stylesheet" />
		<link href="css/index.css" type="text/css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="css/default/om-default.css" />
		<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="js/library/FusionCharts.js"></script>	
		<script language="JavaScript"  src="js/library/FusionCharts.js"></script>	
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
		<script type="text/javascript" src="js/reportData.js"></script>
		<script type="text/javascript" src="js/parameterChart.js"></script>



	</head>
<script type="text/javascript">
    	var basepath = "<%=basePath%>" ;
    </script>
	<body>
		<jsp:include page="header.jsp" flush="true" />
		<%--<table align='center' border="1">  
        <caption>图</caption>  
          
            <tr>  
                <td>  
                    <div id='Pie' align="center">  
                        <script type="text/javascript">  
                            var chart =   
                            new FusionCharts("swf/FC_2_3_Pie2D.swf", "Server Performance", "400", "300");  
                            chart.setDataURL("data.xml"); 
                                           
                            chart.render('Pie');  
                          
</script>  
                    </div>  
                </td>  
            </tr>  
          
    </table>  
		--%><div id="right" style="height:auto;">
			<div class="title8">
				${msg['qt_Ionospheric_parameters_graph']}
			</div>
			<div class="reportbox_chart" id="rightChartContent" ><!-- style="min-height:760px;" -->
					<table height="290" class="report_table" align="center">
						<tr>
						   	<td height="30" valign="top">
								${msg['qt_month_report_selectpara']}：							
							</td>
							<td height="30"  valign="top">
								<div id="selectorPara" ></div>
								<input id="parameter" type="hidden"/> 
							</td>
							<td height="30" valign="top">
							  <table width="100%">
							     <tr><td width="120">${msg['qt_month_report_station']}:：</td><td><input id="stationId" type="hidden" name="stationId" class="boxinput_report"/></td></tr>
							     <tr><td>${msg['qt_month_report_year']}：</td><td><input id="year" name="year" class="boxinput_report"/></td></tr>
							     <tr>
							         <td>
							           ${msg['qt_month_report_month']}：<br>
							          
							         </td>
							          <td>
							          <input type="checkbox"  id="select_all" value="0"/> ${msg['qt_month_report_allmonth']}<br>
							          <input name="months" type="checkbox"  value="1"/> ${msg['qt_month_1']}&nbsp;&nbsp;<input name="months" type="checkbox"  value="7"/> ${msg['qt_month_7']}<br>
							          <input name="months" type="checkbox"  value="2"/> ${msg['qt_month_2']}&nbsp;&nbsp;<input name="months" type="checkbox"  value="8"/> ${msg['qt_month_8']}<br>
							          <input name="months" type="checkbox"  value="3"/> ${msg['qt_month_3']}&nbsp;&nbsp;<input name="months" type="checkbox"  value="9"/> ${msg['qt_month_9']}<br>
							          <input name="months" type="checkbox"  value="4"/> ${msg['qt_month_4']}&nbsp;&nbsp;<input name="months" type="checkbox"  value="10"/> ${msg['qt_month_10']}<br>
							          <input name="months" type="checkbox"  value="5"/> ${msg['qt_month_5']}&nbsp;&nbsp;<input name="months" type="checkbox"  value="11"/> ${msg['qt_month_11']}<br>
							          <input name="months" type="checkbox"  value="6"/> ${msg['qt_month_6']}&nbsp;&nbsp;<input name="months" type="checkbox"  value="12"/> ${msg['qt_month_12']}
							          
							          
							          
							          </td>
							         <!-- <td><input id="monthForChart" name="months" class="boxinput_report"/>(支持多选)</td> -->
							      </tr>
							     <tr><td colspan="2" height="50" align="center">
							     <%--
							     <input type="button" id="pressParaChart" value="${msg['qt_parameters_graph_press']}"/> 
							     --%>
							     <a href="javascript:void(0)"  id="pressParaChart"><img src="images/qxtsc.png"  border="0" /></a>
								<!--<input type="button" id="paraData" value="曲线图下载"/>  --></td></tr>
							   </table>												
							</td>
						
						</tr>								
					</table>
					<br/>
					<div id="topChart" style="min-width: 400px;  margin: 0 auto"></div><!-- height: 250px; -->				
					
					<div id="paraDataChart0" style="min-width: 400px; margin: 0 auto;"></div>
					<div id="scatterChart0"  class="paracharts" ></div>
					<div id="paraDataChart1" class="paracharts"></div>
					<div id="scatterChart1" class="paracharts"></div>
					<div id="paraDataChart2" class="paracharts"></div>
					<div id="scatterChart2" class="paracharts"></div>
					<div id="paraDataChart3" class="paracharts"></div>
					<div id="scatterChart3" class="paracharts"></div>
					<div id="paraDataChart4" class="paracharts"></div>
					<div id="scatterChart4" class="paracharts"></div>
					<div id="paraDataChart5" class="paracharts"></div>
					<div id="scatterChart5" class="paracharts"></div>
					<div id="paraDataChart6" class="paracharts"></div>
					<div id="scatterChart6" class="paracharts"></div>
					<div id="paraDataChart7" class="paracharts"></div>
					<div id="scatterChart7" class="paracharts"></div>
					<div id="paraDataChart8" class="paracharts"></div>
					<div id="scatterChart8" class="paracharts"></div>
					<div id="paraDataChart9" class="paracharts"></div>
					<div id="scatterChart9" class="paracharts"></div>
					<div id="paraDataChart10" class="paracharts"></div>
					<div id="scatterChart10" class="paracharts"></div>		
					<div id="paraDataChart11" class="paracharts"></div>
					<div id="scatterChart11" class="paracharts"></div>
					<div id="downChart" class="paracharts"></div>
					
					<!--  height: 300px; -->					
				</div>
		   <!-- </div> -->
		</div>
		
		<!--right 结束-->
		<jsp:include page="left.jsp" flush="true" />
		<!--left 结束-->
		<jsp:include page="../ht/footer.jsp" flush="true" />
	</body>
</html>

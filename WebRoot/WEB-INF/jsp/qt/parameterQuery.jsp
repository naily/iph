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
		<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
		<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
		<script type="text/javascript" src="js/library/highcharts225/highcharts.js"></script>
		<script type="text/javascript" src="js/library/highcharts225/modules/exporting.js"></script>
		<script type="text/javascript" src="js/library/highcharts225/themes/grid.js"></script>
		<script type="text/javascript" src="js/index_global.js"></script>
		<script type="text/javascript" src="js/Global.js"></script>
		<c:choose>
			 <c:when test='${msg.lang=="zh"}'> 
			 	<script type="text/javascript" src="js/index_global_zn.js"></script>
			</c:when>
			<c:otherwise>
				<script type="text/javascript" src="js/index_global_en.js"></script>
			</c:otherwise>
		</c:choose>
		<script type="text/javascript" src="js/reportData.js"></script>
		<script type="text/javascript" src="js/parameterQuery.js"></script>
		



	</head>
<script type="text/javascript">
    	var basepath = "<%=basePath%>" ;
    </script>
	<body>
		<jsp:include page="header.jsp" flush="true" />
		<input id="startDateV" type="hidden" value="${requestScope.para.startDate}"/> 
		<input id="endDateV" type="hidden" value="${requestScope.para.endDate}"/> 
		<input id="stationIDV" type="hidden" value="${requestScope.para.stationID}"/> 
		<div id="right">
			<div class="title8">
				${msg['qt_data_query_title']}
			</div>
			<div class="reportbox_">
					<table height="335" class="report_table" align="center">
						<tr>					   
							<%--<td height="30"  valign="top">						
								<div id="selectorStation" ></div>
								<input id="stationIDs" type="hidden"/> 
							</td>
							--%><td height="30" width="50%" align="left" valign="top">
						
								<div id="selectorParaS" style="margin-left:30px;" ></div>
								<input id="parameter" type="hidden" /> 
							</td>
							<td>
							  <table width="100%">
							     <tr><td align="right">${msg['qt_month_report_station']}:</td><td><input id="stationIdParaQuery" name="stationIdParaQuery" class="boxinput_report"/></td></tr>
							     <tr><td align="right">${msg['qt_query_data_type']}：</td><td><input id="selectDataType" name="selectDataType" class="boxinput_report"/></td></tr>
							     <tr><td width="100" align="right">${msg['qt_parameter_select_date']}：</td><td><input id="allDate" type="checkbox" name="allDate" />${msg['qt_parameter_selectAlldate']}</td></tr>
							     <tr><td align="right">${msg['qt_parameter_start_date']}：</td><td><input id="startDate" name="startDate" class="boxinput_report"/></td></tr>
							     <tr><td align="right">${msg['qt_parameter_end_date']}：</td><td><input id="endDate" name="endDate" class="boxinput_report"/></td></tr>
							     <tr><td align="right">${msg['qt_parameter_orderby']}：</td><td><input id="orderCol" name="orderCol" class="boxinput_report"/></td></tr>
							     <tr><td align="right">${msg['qt_parameter_pagesize']}：</td><td><input id="showNum" name="showNum" class="boxinput_report"/></td></tr>
							     <tr><td colspan="2" height="50" align="center">
							     <!--  <input type="button" id="paraDataQuery" value="${msg['qt_query_button']}"/>-->
							      <a href="javascript:void(0)" id="paraDataQuery">
								      <c:choose>
										<c:when test='${msg.lang=="zh"}'>
										 <img src="images/chaxun.png"  border="0" />
									   </c:when>		
										<c:otherwise>
										<img src="images/d11.jpg"  border="0" />
										</c:otherwise>
									  </c:choose>							    
								      </a>
							     </td></tr>								
							     <tr><td colspan="2" height="100" align="center">
							     <!-- <input type="button" style="display:none;" id="downloadParaData" value="${msg['qt_download_para_excel']}"/> -->
							     <a href="javascript:void(0)" style="display:none;"  id="downloadParaData"><img src="images/daochu.png"  border="0" /></a>
							     </td></tr>								
							   </table>	
							</td>
						</tr>								
					</table>
					<br/>
					<div id="imagePreview" title="图片预览"><table id="paraQueryGrid2"></table></div> 					
					<div id="imagePreview2" title="图片预览"><table id="paraQueryGrid3"></table> </div> 
					<table id="paraQueryGrid"></table>					
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

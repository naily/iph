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
		<script type="text/javascript" src="js/library/highcharts225/highcharts.js"></script>
		<script type="text/javascript" src="js/library/highcharts225/modules/exporting.js"></script>
		<script type="text/javascript" src="js/library/highcharts225/themes/grid.js"></script>
		
		<script type="text/javascript" src="js/index_global_zn.js"></script>
		
		<script type="text/javascript" src="js/parameterChart.js"></script>
		



	</head>
<script type="text/javascript">
    	var basepath = "<%=basePath%>" ;
    </script>
	<body>
		<jsp:include page="header.jsp" flush="true" />
		<div id="right">
			<div class="title8">
				电离层参数变化曲线图
			</div>
			<div class="reportbox">
					<table height="290" class="report_table" align="center">
						<tr>
						   
							<td height="30" valign="top">
							  <table width="100%">
							     <tr><td width="100">选择观测站：</td><td><input id="stationId" name="stationId" class="boxinput_report"/></td></tr>
							     <tr><td>选择年份：</td><td><input id="year" name="year" class="boxinput_report"/></td></tr>
							     <tr>
							         <td>
							            选择月份：<br>
							          
							         </td>
							          <td>
							          <input type="checkbox"  id="select_all" value="0"/> 所有月份<br>
							          <input name="months" type="checkbox"  value="1"/> 1月&nbsp;&nbsp;<input name="months" type="checkbox"  value="7"/> 7月<br>
							          <input name="months" type="checkbox"  value="2"/> 2月&nbsp;&nbsp;<input name="months" type="checkbox"  value="8"/> 8月<br>
							          <input name="months" type="checkbox"  value="3"/> 3月&nbsp;&nbsp;<input name="months" type="checkbox"  value="9"/> 9月<br>
							          <input name="months" type="checkbox"  value="4"/> 4月&nbsp;&nbsp;<input name="months" type="checkbox"  value="10"/> 10月<br>
							          <input name="months" type="checkbox"  value="5"/> 5月&nbsp;&nbsp;<input name="months" type="checkbox"  value="11"/> 11月<br>
							          <input name="months" type="checkbox"  value="6"/> 6月&nbsp;&nbsp;<input name="months" type="checkbox"  value="12"/> 12月
							          
							          
							          
							          </td>
							         <!-- <td><input id="monthForChart" name="months" class="boxinput_report"/>(支持多选)</td> -->
							      </tr>
							     <tr><td colspan="2" height="50" align="center"><input type="button" id="pressParaChart" value="曲线图生成"/> 
								<input type="button" id="paraData" value="曲线图下载"/></td></tr>
							   </table>												
							</td>
							<td height="30" valign="top">
								选择参数：							
							</td>
							<td height="30"  valign="top">
								<div id="selectorPara" ></div>
								<input id="parameter" type="hidden"/> 
							</td>
						</tr>								
					</table>
					<br/>
					<div id="paraDataChart" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
					
				</div>
		   <!-- </div> -->
		</div>
		<!--right 结束-->
		<jsp:include page="left.jsp" flush="true" />
		<!--left 结束-->
		<jsp:include page="../ht/footer.jsp" flush="true" />
	</body>
</html>

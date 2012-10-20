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
		<script type="text/javascript" src="js/qt_pgt.js"></script>
		<script type="text/javascript">
	var dataAry = new Array();
	<% 
	if(null!=request.getAttribute("pgtlist")){	
	  List<IronoGram>  list  = (List<IronoGram>)request.getAttribute("pgtlist");
	  IronoGram irg = null;
	  String imgPath ="";
	  for(int i=0;i<list.size();i++){
		   irg = (IronoGram)list.get(i);
		   imgPath =irg.getGramPath();
		  %>
		  dataAry[<%=i%>] ='<%=imgPath%>';
		  <%
	  }
	}
	%>
	 
	</script>
		<script type="text/javascript" src="js/Global.js"></script>
	</head>

	<body>
	
		<jsp:include page="header.jsp" flush="true" />
		<div id="right">
			<div class="title8">
				${msg['qt_pgt_list_title']}
			</div>
			
			<div class="loginbox1">
			 
  <table width="800" border="0" align="left" cellpadding="0" cellspacing="0" class="fontstyle4">
   <form action="qt/listPGT.do" name="queryName" method="POST" onsubmit="return checkValue(this);">
    <tr>
      <td width="300" height="50" align="center">
      <input type="hidden" id="stationIDV" value="${requestScope.irg.stationID}"/>
         ${msg['qt_month_report_station']}：<input id="comboStation"   name="stationID"  class="boxinput_report"/></td>
      <td width="600">
        ${msg['qt_parameter_start_date']}：<input id="startDate" name="startDate"  value="${requestScope.irg.startDate}"  class="boxinput_report"/>&nbsp;&nbsp;${msg['qt_parameter_end_date']}：<input id="endDate" name="endDate" value="${requestScope.irg.endDate}" class="boxinput_report"/></td>
      <td width="100">
     
      <input type="button" name="Submit" value="${msg['qt_query_button']}"  onclick="checkValue('pgt');"/>
     
      <%--
      <input type="Submit" name="Submit" value="${msg['qt_query_button']}" />
      --%></td>
    </tr>
  </form>
    <tr>
      <td height="40" colspan="3" align="right"><a href="qt/listPGT.do"  class="a2">${msg['qt_show_all']}>></a><br>
      <table width="800" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CEE7FF">
        <tr><td></td></tr>
        <tr>
          <td width="44" height="35" align="center" bgcolor="#E8F7FF"><strong>${msg['qt_list_order']}</strong></td>
          <td width="344" align="center" bgcolor="#E8F7FF"><strong>${msg['qt_list_pgt_name']}</strong></td>
          <td width="134" align="center" bgcolor="#E8F7FF"><strong>${msg['qt_list_station']}</strong></td>
          <td width="121" align="center" bgcolor="#E8F7FF"><strong>${msg['qt_list_date']}</strong></td>
          <td colspan="2" align="center" bgcolor="#E8F7FF"><strong>${msg['qt_list_operate']}</strong></td>
        </tr>
        <c:forEach items="${pgtlist}" var="pgt" varStatus="varStatusPgt">	
        <tr>
          <td height="35" align="center" valign="middle" bgcolor="#FFFFFF">${varStatusPgt.index+1}</td>
          <td align="center" valign="middle" bgcolor="#FFFFFF"> ${pgt.gramTitle}</td>
          <td align="center" valign="middle" bgcolor="#FFFFFF"> ${pgt.station.name}</td>
          <td align="center" valign="middle" bgcolor="#FFFFFF"><fmt:formatDate value="${pgt.createDate}"   type="date" dateStyle="default" /></td>
          <td width="37" align="center" valign="middle" bgcolor="#FFFFFF"><a href="javascript:previewImageA('dataAry1',${varStatusPgt.index},'1');" class="a4">${msg['qt_list_Browse']}</a></td>
          <td width="43" align="center" valign="middle" bgcolor="#FFFFFF"><a href="qt/downloadPGT.do?gramID=${pgt.gramID}" class="a4">${msg['qt_list_download']}</a></td>
        </tr>
        </c:forEach>
        
      </table></td>
    </tr>
    <tr>
      <td height="40" colspan="3" align="center">
      <div class="newspage">
					<a href="qt/listPGT.do" class="a3">${msg['qt_page_first']}</a>
					  <c:choose>
				    	<c:when test="${requestScope.page.pageNumber>1}" >
				    	  <a href="qt/listPGT.do?queryYear=${requestScope.queryYear}&stationID=${requestScope.irg.stationID}&startDate=${requestScope.irg.startDate}&endDate=${requestScope.irg.endDate}&pageNumber=${requestScope.page.pageNumber-1}" class="a3">${msg['qt_page_prepage']}</a>
				    	</c:when>
				    	<c:otherwise>
						  <a href="javascript:void(0)" class="a3">${msg['qt_page_prepage']}</a>	       
			    		</c:otherwise>
			    	</c:choose>
					 ${msg['qt_page_local']}
					<span class="fontstyle3">${ requestScope.page.pageNumber}</span>/${ requestScope.page.pageCount} ${msg['qt_page_unit']}
					<c:choose>
				    	<c:when test="${requestScope.page.pageNumber<requestScope.page.pageCount}" >
				    	  <a href="qt/listPGT.do?queryYear=${requestScope.queryYear}&stationID=${requestScope.irg.stationID}&startDate=${requestScope.irg.startDate}&endDate=${requestScope.irg.endDate}&pageNumber=${ requestScope.page.pageNumber+1}" class="a3">${msg['qt_page_nextpage']}</a>
				    	</c:when>
				    	<c:otherwise>
						  <a href="javascript:void(0)" class="a3">${msg['qt_page_nextpage']}</a>	       
			    		</c:otherwise>
			    	</c:choose>
					
					<a href="qt/listPGT.do?queryYear=${requestScope.queryYear}&stationID=${requestScope.irg.stationID}&startDate=${requestScope.irg.startDate}&endDate=${requestScope.irg.endDate}&pageNumber=${ requestScope.page.pageCount}" class="a3">${msg['qt_page_last']}</a>
				</div>
	  </td>
      </tr>
  </table>
  </div>
</div>
<div id="imagePreview" title="图片预览"> </div> 
		<!--right 结束-->
		<jsp:include page="left.jsp" flush="true" />
		<!--left 结束-->
		<jsp:include page="../ht/footer.jsp" flush="true" />
	</body>
</html>

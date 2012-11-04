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
	if(null!=request.getAttribute("smtlist")){			
	  List<Scanpic>  list  = (List<Scanpic>)request.getAttribute("smtlist");
	  String  imgPath ="";
	  Scanpic spic=null;
	  for(int i=0;i<list.size();i++){
		   spic = (Scanpic)list.get(i);
		   imgPath =spic.getGramPath();		 
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
				${msg['qt_smt_list_title']}
			</div>
			<div class="loginbox1">
			 <div id="imagePreview" title="图片预览"> </div> 
  <table width="800" border="0" align="left" cellpadding="0" cellspacing="0" class="fontstyle4">
     <form action="qt/listScanPic.do" name="queryName" method="POST" onsubmit="return checkValue(this);">
    <tr>
      <td width="300" height="50" align="center">
      <input type="hidden" id="stationIDV" value="${requestScope.scp.ids}"/>
       <input type="hidden" id="queryYear" value="${requestScope.queryYear}"/>
         ${msg['qt_month_report_station']}：<input id="comboStation"   name="ids"  class="boxinput_report"/></td>
      <td width="600">
        ${msg['qt_parameter_start_date']}：<input id="startDate" name="startDate"  value="${requestScope.scp.startDate}"  class="boxinput_report"/>&nbsp;&nbsp;${msg['qt_parameter_end_date']}：<input id="endDate" name="endDate" value="${requestScope.scp.endDate}" class="boxinput_report"/></td>
      <td width="100">
     <a href="javascript:void(0)"  id="ssss" onclick="checkValue('smt');"><img src="images/chaxun.png"  border="0" /></a>
      
     
      <%--
      <input type="button" name="Submit" value="${msg['qt_query_button']}"  onclick="checkValue('smt');"/>
      <input type="Submit" name="Submit" value="${msg['qt_query_button']}" />
      --%></td>
    </tr>
  </form>
    <tr>
      <td height="40" colspan="3" align="right"><a href="qt/listScanPic.do"  class="a2">${msg['qt_show_all']}>></a><br>
      <table width="800" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CEE7FF">
        <tr><td></td></tr>
        <tr>
          <td width="44" height="35" align="center" bgcolor="#E8F7FF"><strong>${msg['qt_list_order']}</strong></td>
          <td width="310" align="center" bgcolor="#E8F7FF"><strong>${msg['qt_list_smt_name']}</strong></td>
          <td width="134" align="center" bgcolor="#E8F7FF"><strong>${msg['qt_list_station']}</strong></td>
          <td width="121" align="center" bgcolor="#E8F7FF"><strong>${msg['qt_list_date']}</strong></td>
          <td colspan="2" align="center" bgcolor="#E8F7FF"><strong>${msg['qt_list_operate']}</strong></td>
        </tr>
        <c:forEach items="${smtlist}" var="smt" varStatus="varStatusSmt">	
        <tr>
          <td height="35" align="center" valign="middle" bgcolor="#FFFFFF">${varStatusSmt.index+1}</td>
          <td align="center" valign="middle" bgcolor="#FFFFFF"> ${smt.scanPicTitle}</td>
          <td align="center" valign="middle" bgcolor="#FFFFFF"> ${smt.station.name}</td>
          <td align="center" valign="middle" bgcolor="#FFFFFF"><fmt:formatDate value="${smt.createDate}"   type="date" dateStyle="default" /></td>
          <td width="67" align="center" valign="middle" bgcolor="#FFFFFF"><a href="javascript:previewImageA('dataAry1',${varStatusSmt.index},'扫描图图查看');" class="a4">${msg['qt_list_Browse']}</a></td>
          <td width="67" align="center" valign="middle" bgcolor="#FFFFFF"><a href="qt/downloadScanpic.do?scanPicID=${smt.scanPicID}" class="a4">${msg['qt_list_download']}</a></td>
        </tr>
        </c:forEach>
        
      </table></td>
    </tr>
    <tr>
      <td height="60" colspan="3" align="center">
      <div class="newspage">
					<a href="qt/listScanPic.do" class="a3">${msg['qt_page_first']}</a>
					  <c:choose>
				    	<c:when test="${requestScope.page.pageNumber>1}" >
				    	  <a href="qt/listScanPic.do?queryYear=${requestScope.queryYear}&stationID=${requestScope.irg.stationID}&startDate=${requestScope.irg.startDate}&endDate=${requestScope.irg.endDate}&pageNumber=${ requestScope.page.pageNumber-1}" class="a3">${msg['qt_page_prepage']}</a>
				    	</c:when>
				    	<c:otherwise>
						  <a href="javascript:void(0)" class="a3">${msg['qt_page_prepage']}</a>	       
			    		</c:otherwise>
			    	</c:choose>
					 ${msg['qt_page_local']}
					
					<%--<span class="fontstyle3">${ requestScope.page.pageNumber}</span>/${ requestScope.page.pageCount} ${msg['qt_page_unit']}
					--%>
					
					<select id="" name="gotopages" onchange="goPages(this,'smt');">
					<c:forEach var="i" begin="1" end="${requestScope.page.pageCount}" step="1"> 
				       <option value="${i}" <c:if test="${i==requestScope.page.pageNumber}">selected</c:if>>${i}/${requestScope.page.pageCount}</option>${msg['qt_page_unit']}
				    </c:forEach>
					
					</select>
					${msg['qt_page_unit']}
					<c:choose>
				    	<c:when test="${requestScope.page.pageNumber<requestScope.page.pageCount}" >
				    	  <a href="qt/listScanPic.do?queryYear=${requestScope.queryYear}&stationID=${requestScope.irg.stationID}&startDate=${requestScope.irg.startDate}&endDate=${requestScope.irg.endDate}&pageNumber=${ requestScope.page.pageNumber+1}" class="a3">${msg['qt_page_nextpage']}</a>
				    	</c:when>
				    	<c:otherwise>
						  <a href="javascript:void(0)" class="a3">${msg['qt_page_nextpage']}</a>	       
			    		</c:otherwise>
			    	</c:choose>
					
					<a href="qt/listScanPic.do?queryYear=${requestScope.queryYear}&stationID=${requestScope.irg.stationID}&startDate=${requestScope.irg.startDate}&endDate=${requestScope.irg.endDate}&pageNumber=${ requestScope.page.pageCount}" class="a3">${msg['qt_page_last']}</a>
				</div>
	  </td>
      </tr>
  </table>
  </div>
</div>

		<!--right 结束-->
		<jsp:include page="left.jsp" flush="true" />
		<!--left 结束-->
		<jsp:include page="../ht/footer.jsp" flush="true" />
	</body>
</html>

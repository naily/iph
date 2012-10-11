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
	</head>
	<body>
		<jsp:include page="header.jsp" flush="true" />
		<!-- <script type="text/javascript" src="js/saclist.js"></script> -->
		<script type="text/javascript">
		function previewSac(path){
			if(path){
				$( "#imagePreview").html('<img src=".'+ path +'" border=0 height=500 width=600  / >');
				
				$( "#imagePreview").omDialog({title:'报表扫描图查看',height: 'auto' ,width :'auto'});
				$( "#imagePreview").omDialog('open');
			}
		}
		</script>
		
		<div id="right">
			<div class="title8">
				${msg['qt_smt_list_title']}
			</div>
			<div class="loginbox1">
			 <div id="imagePreview" title="图片预览"> </div> 
  <table width="800" border="0" align="left" cellpadding="0" cellspacing="0" class="fontstyle4">
    <%--<tr>
      <td width="277" height="50" align="right">选择查询关键字：</td>
      <td width="227"><select name="select"  class="select1">
          <option>请选择</option>
      </select></td>
      <td width="226"><input type="submit" name="Submit" value="查询" /></td>
    </tr>
    --%><tr>
      <td height="40" colspan="3" align="right"><a href="qt/listScanPic.do"  class="a2">${msg['qt_show_all']}>></a><br>
      <table width="800" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CEE7FF">
        <tr><td></td></tr>
        <tr>
          <td width="44" height="35" align="center" bgcolor="#E8F7FF"><strong>${msg['qt_list_order']}</strong></td>
          <td width="344" align="center" bgcolor="#E8F7FF"><strong>${msg['qt_list_smt_name']}</strong></td>
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
          <td width="37" align="center" valign="middle" bgcolor="#FFFFFF"><a href="javascript:previewSac('${smt.gramPath}');" class="a4">${msg['qt_list_Browse']}</a></td>
          <td width="43" align="center" valign="middle" bgcolor="#FFFFFF"><a href="qt/downloadScanpic.do?scanPicID=${smt.scanPicID}" class="a4">${msg['qt_list_download']}</a></td>
        </tr>
        </c:forEach>
        
      </table></td>
    </tr>
    <tr>
      <td height="40" colspan="3" align="center">
      <div class="newspage">
					<a href="qt/listScanPic.do" class="a3">${msg['qt_page_first']}</a>
					  <c:choose>
				    	<c:when test="${requestScope.page.pageNumber>1}" >
				    	  <a href="qt/listScanPic.do?pageNumber=${ requestScope.page.pageNumber-1}" class="a3">${msg['qt_page_prepage']}</a>
				    	</c:when>
				    	<c:otherwise>
						  <a href="javascript:void(0)" class="a3">${msg['qt_page_prepage']}</a>	       
			    		</c:otherwise>
			    	</c:choose>
					 ${msg['qt_page_local']}
					<span class="fontstyle3">${ requestScope.page.pageNumber}</span>/${ requestScope.page.pageCount} ${msg['qt_page_unit']}
					<c:choose>
				    	<c:when test="${requestScope.page.pageNumber<requestScope.page.pageCount}" >
				    	  <a href="qt/listScanPic.do?pageNumber=${ requestScope.page.pageNumber+1}" class="a3">${msg['qt_page_nextpage']}</a>
				    	</c:when>
				    	<c:otherwise>
						  <a href="javascript:void(0)" class="a3">${msg['qt_page_nextpage']}</a>	       
			    		</c:otherwise>
			    	</c:choose>
					
					<a href="qt/listScanPic.do?pageNumber=${ requestScope.page.pageCount}" class="a3">${msg['qt_page_last']}</a>
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

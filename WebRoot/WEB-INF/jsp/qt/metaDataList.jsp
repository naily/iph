<%@ page language="java" import="java.util.*,cn.fam1452.dao.pojo.*"  %>
<%@ page contentType="text/html;charset=UTF-8"%>
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
		<!--
	-->
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="images/1.css" type="text/css" rel="stylesheet" />
		<link href="css/index.css" type="text/css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css"
			href="css/default/om-default.css" />
		<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
		<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
		<script type="text/javascript" src="js/Global.js"></script>
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

	<body>

		<jsp:include page="header.jsp" flush="true" />
	<div  id="right">
<div class="title8">${msg['qt_parameter_title']}</div>
<table width="800" border="0" align="left" cellpadding="0" cellspacing="0">
<c:forEach items="${metaDataList}" var="metaData" varStatus="varStatusMeta">	
  <tr>
    <td height="35" colspan="2" bgcolor="#f5f5f5" class="fontstyle2"> &nbsp;<a href="qt/metaDataPriview.do?mdId=${metaData.mdId}" class="a3">${metaData.title}</a></td>
    </tr>
  <tr>
    <td width="202" height="110" align="center" valign="top"><div class="rightbox2_1_img"><img src='${metaData.thumbnailFilePath}' width='171' height='118'></div></td>
    <td width="598" align="left" valign="top"><div class="news3text">${metaData.summary}</div></td>
  </tr>
  </c:forEach>

  
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
	<div class="newspage">
					<a href="qt/metaDataList.do?title=${requestScope.keyword}" onclick="return linkClick(this)" class="a3">首页</a>
					  <c:choose>
				    	<c:when test="${requestScope.page.pageNumber>1}" >
				    	  <a href="qt/metaDataList.do?title=${requestScope.keyword}&pageNumber=${ requestScope.page.pageNumber-1}" onclick="return linkClick(this)" class="a3">上一页</a>
				    	</c:when>
				    	<c:otherwise>
						  <a href="javascript:void(0)" class="a3">上一页</a>	       
			    		</c:otherwise>
			    	</c:choose>
					 当前
					<span class="fontstyle3">${ requestScope.page.pageNumber}</span>/${ requestScope.page.pageCount}页
					<c:choose>
				    	<c:when test="${requestScope.page.pageNumber<requestScope.page.pageCount}" >
				    	  <a href="qt/metaDataList.do?title=${requestScope.keyword}&pageNumber=${ requestScope.page.pageNumber+1}" onclick="return linkClick(this)" class="a3">下一页</a>
				    	</c:when>
				    	<c:otherwise>
						  <a href="javascript:void(0)" class="a3">下一页</a>	       
			    		</c:otherwise>
			    	</c:choose>
					
					<a href="qt/metaDataList.do?title=${requestScope.keyword}&pageNumber=${requestScope.page.pageCount}" onclick="return linkClick(this)" class="a3">末页</a>
				</div>
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

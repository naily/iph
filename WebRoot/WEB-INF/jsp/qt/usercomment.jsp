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
		<title>${msg['site_name']}</title><%--
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
		<script type="text/javascript" src="js/index_usercomment.js"></script>

	</head>
	<script type="text/javascript">
    	var basepath = "<%=basePath%>" ;
    </script>
	--%>
	<body>
		<jsp:include page="header.jsp" flush="true" />
		<script type="text/javascript" src="js/index_usercomment.js"></script>
		<div id="right">
			<div class="title8">
				${msg['qt_usercomment_title']}
			</div>
			<div class="reportbox">
				
					 <table id="reportGrid"></table>					
					
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

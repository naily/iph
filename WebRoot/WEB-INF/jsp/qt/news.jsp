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
		<!--
	-->
		<link href="images/1.css" type="text/css" rel="stylesheet" />
		<link href="css/index.css" type="text/css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="css/default/om-default.css" />
		<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
		<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>

		<style type="text/css"></style>

	</head>

	<body>
		<jsp:include page="header.jsp" flush="true" />
		<div  id="right">
			<div class="title8">${msg['qt_news_title']}</div>
			<div class="newsbox">
				<div class="newstitle">${obj.title }</div>
				<div class="newsinfo">${msg['qt_news_time']}：<fmt:formatDate value="${ obj.publishDate }"  type="both" /></div>
				<div class="newstext">${obj.content }</div>
			</div>
		</div>
		<!--right 结束-->
		<jsp:include page="left.jsp" flush="true" />
		<!--left 结束-->
		<jsp:include page="../ht/footer.jsp" flush="true" />
	</body>
</html>

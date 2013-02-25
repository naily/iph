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
		<!--
	-->
		<link href="images/1.css" type="text/css" rel="stylesheet" />
		<link href="css/index.css" type="text/css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css"
			href="css/default/om-default.css" />
		<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
		<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
		<script type="text/javascript" src="js/userRegist.js"></script>
		<style type="text/css">
       label.error{
        background: #fff6bf url(images/errorIcon.png) center no-repeat;
		background-position: 5px 50%;
		text-align: left;
		padding: 2px 20px 2px 25px;
		border: 1px solid #ffd324;
		display: none;
		width: 200px;
		margin-left: 10px;
       }
    </style>

	</head>

	<body>
		<jsp:include page="header.jsp" flush="true" />
		<div id="right">
			<div class="title8">
				新闻列表
			</div>
			<div class="newsbox">
			<c:forEach items="${newslist}" var="news" varStatus="varStatusNews">		
				<li>
					<a href="qt/newspreview.do?newsId=${news.newsId}" class="a2">${news.title}</a>
				</li>
			</c:forEach>
				
				<div class="newspage">
					<a href="qt/news.do" class="a3">首页</a>
					  <c:choose>
				    	<c:when test="${requestScope.page.pageNumber>1}" >
				    	  <a href="qt/news.do?pageNumber=${ requestScope.page.pageNumber-1}" class="a3">上一页</a>
				    	</c:when>
				    	<c:otherwise>
						  <a href="javascript:void(0)" class="a3">上一页</a>	       
			    		</c:otherwise>
			    	</c:choose>
					 当前
					<span class="fontstyle3">${ requestScope.page.pageNumber}</span>/${ requestScope.page.pageCount}页
					<c:choose>
				    	<c:when test="${requestScope.page.pageNumber<requestScope.page.pageCount}" >
				    	  <a href="qt/news.do?pageNumber=${ requestScope.page.pageNumber+1}" class="a3">下一页</a>
				    	</c:when>
				    	<c:otherwise>
						  <a href="javascript:void(0)" class="a3">下一页</a>	       
			    		</c:otherwise>
			    	</c:choose>
					
					<a href="qt/news.do?pageNumber=${ requestScope.page.pageCount}" class="a3">末页</a>
				</div>
			</div>
		</div>
		<!--right 结束-->
		<jsp:include page="left.jsp" flush="true" />
		<!--left 结束-->
		<jsp:include page="../ht/footer.jsp" flush="true" />
	</body>
</html>

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
		<!--
	-->
		<link href="images/1.css" type="text/css" rel="stylesheet" />
		<link href="css/index.css" type="text/css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css"
			href="css/default/om-default.css" />
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
				数据查询
			</div>
			<table width="750" border="0" align="center" cellpadding="0"
				cellspacing="0" class="">
				<tr>
					<td width="141" height="35" bgcolor="#edf7fb">
						<strong> &nbsp;&nbsp;数据集名称</strong>：
					</td>
					<td width="659" bgcolor="#edf7fb">
						${obj.title }
					</td>
				</tr>
				<tr>
					<td height="35">
						<strong> &nbsp;&nbsp; 关键词：</strong>
					</td>
					<td>
						${obj.keyword }
					</td>
				</tr>
				<tr>
					<td height="35" bgcolor="#edf7fb">
						<strong>&nbsp;&nbsp;摘要：</strong>
					</td>
					<td bgcolor="#edf7fb">
						<div style="word-break:break-all;width:500px;">
							${obj.summary }
						</div>
					</td>
				</tr>

			
			<tr>
					<td height="35"  colspan="2">					
							<iframe src="${obj.fullContentFilePath}" frameborder="0" height="650" scrolling="no" width="800"></iframe>
					
					</td>
				</tr>
			</table>

		</div>
		<!--right 结束-->
		<jsp:include page="left.jsp" flush="true" />
		<!--left 结束-->
		<jsp:include page="../ht/footer.jsp" flush="true" />
	</body>
</html>

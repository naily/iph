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
						<strong> &nbsp;&nbsp;名称</strong>：
					</td>
					<td width="659" bgcolor="#edf7fb">
						${obj.title }
					</td>
				</tr>
				<tr>
					<td height="35">
						<strong> &nbsp;&nbsp;所属服务系统：</strong>
					</td>
					<td>
						<iframe src="${obj.fullContentFilePath }"></iframe>
					</td>
				</tr>
				<tr>
					<td height="35" bgcolor="#edf7fb">
						<strong>&nbsp;&nbsp;URI：</strong>
					</td>
					<td bgcolor="#edf7fb">
						www
					</td>
				</tr>
				<tr>
					<td height="35">
						<strong> &nbsp;&nbsp;关键词：</strong>
					</td>
					<td>
						电站
					</td>
				</tr>
				<tr>
					<td height="35" bgcolor="#edf7fb">
						<strong>&nbsp;&nbsp;简介：</strong>
					</td>
					<td bgcolor="#edf7fb">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="35">
						<strong>&nbsp;&nbsp;目的：</strong>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="35" bgcolor="#edf7fb">
						<strong>&nbsp;&nbsp;数据分类：</strong>
					</td>
					<td bgcolor="#edf7fb">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="35" colspan="2">
						<table width="700" border="0" align="right" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="121" height="35">
									<strong>&nbsp;&nbsp;项目名称：</strong>
								</td>
								<td width="579">
									名称
								</td>
							</tr>
							<tr>
								<td height="35" bgcolor="#edf7fb">
									<strong>&nbsp;&nbsp;分类列表：</strong>
								</td>
								<td bgcolor="#edf7fb">
									表
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td height="35">
						&nbsp;&nbsp;
						<strong>范围</strong>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="35" colspan="2">
						<table width="700" border="0" align="right" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="121" height="35" bgcolor="#edf7fb">
									<strong>&nbsp;&nbsp;时间范围：</strong>
								</td>
								<td width="579" bgcolor="#edf7fb">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td height="35">
									<strong>&nbsp;&nbsp;空间范围表：</strong>
								</td>
								<td>
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td height="35" bgcolor="#edf7fb">
						<strong>&nbsp;&nbsp;数据来源：</strong>
					</td>
					<td bgcolor="#edf7fb">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="35">
						<strong>&nbsp;&nbsp;数据类型：</strong>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="35" bgcolor="#edf7fb">
						<strong>&nbsp;&nbsp;创建者：</strong>
					</td>
					<td bgcolor="#edf7fb">
						&nbsp;
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

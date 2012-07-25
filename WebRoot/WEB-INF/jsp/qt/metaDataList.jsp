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
	<div  id="right">
<div class="title8">数据查询</div>
<table width="800" border="0" align="left" cellpadding="0" cellspacing="0">
  <tr>
    <td height="35" colspan="2" bgcolor="#f5f5f5" class="fontstyle2"> &nbsp;数据标题</td>
    </tr>
  <tr>
    <td width="202" height="110" align="center" valign="top"><div class="rightbox2_1_img"></div></td>
    <td width="598" align="left" valign="top"><div class="news3text">内容</div></td>
  </tr>
  <tr>
    <td height="35" colspan="2" bgcolor="#f5f5f5" class="fontstyle2"> &nbsp;数据标题</td>
  </tr>
  <tr>
    <td height="110" align="center" valign="top"><div class="rightbox2_1_img"></div></td>
    <td align="left" valign="top"><div class="news3text">内容</div></td>
  </tr>
  
  <tr>
    <td height="35" colspan="2" bgcolor="#f5f5f5" class="fontstyle2"> &nbsp;数据标题</td>
  </tr>
  <tr>
    <td height="110" align="center" valign="top"><div class="rightbox2_1_img"></div></td>
    <td align="left" valign="top"><div class="news3text">内容</div></td>
  </tr>
  
  <tr>
    <td height="35" colspan="2" bgcolor="#f5f5f5" class="fontstyle2"> &nbsp;数据标题</td>
  </tr>
  <tr>
    <td height="110" align="center" valign="top"><div class="rightbox2_1_img"></div></td>
    <td align="left" valign="top"><div class="news3text">内容</div></td>
  </tr>
  
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>

</div>
<!--right 结束-->
		<jsp:include page="left.jsp" flush="true" />
		<!--left 结束-->
		<jsp:include page="../ht/footer.jsp" flush="true" />
	</body>
</html>

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
				频高图列表
			</div>
			<div class="loginbox1">
  <table width="730" border="0" align="center" cellpadding="0" cellspacing="0" class="fontstyle4">
    <tr>
      <td width="277" height="50" align="right">选择查询字段：</td>
      <td width="227"><select name="select"  class="select1">
          <option>请选择</option>
      </select></td>
      <td width="226"><input type="submit" name="Submit" value="查询" /></td>
    </tr>
    <tr>
      <td height="40" colspan="3" align="right"><table width="730" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CEE7FF">
        <tr>
          <td width="44" height="35" align="center" bgcolor="#E8F7FF"><strong>序号</strong></td>
          <td width="344" align="center" bgcolor="#E8F7FF"><strong>频高图名称</strong></td>
          <td width="134" align="center" bgcolor="#E8F7FF"><strong>所属观测站</strong></td>
          <td width="121" align="center" bgcolor="#E8F7FF"><strong>观测日期</strong></td>
          <td colspan="2" align="center" bgcolor="#E8F7FF"><strong>操作</strong></td>
        </tr>
        <c:forEach items="${pgtlist}" var="pgt" varStatus="varStatusPgt">	
        <tr>
          <td height="35" align="center" valign="middle" bgcolor="#FFFFFF">1</td>
          <td align="center" valign="middle" bgcolor="#FFFFFF"> ${pgt.gramTitle}</td>
          <td align="center" valign="middle" bgcolor="#FFFFFF"> ${pgt.station.name}</td>
          <td align="center" valign="middle" bgcolor="#FFFFFF"><fmt:formatDate value="${pgt.createDate}"   type="date" dateStyle="default" /></td>
          <td width="37" align="center" valign="middle" bgcolor="#FFFFFF"><a href="#" class="a4">浏览</a></td>
          <td width="43" align="center" valign="middle" bgcolor="#FFFFFF"><a href="qt/downloadPGT.do?ids=${pgt.ids}" class="a4">下载</a></td>
        </tr>
        </c:forEach>
        
      </table></td>
    </tr>
    <tr>
      <td height="40" colspan="3" align="center">
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

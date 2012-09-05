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
	</head>
	<body>
		<jsp:include page="header.jsp" flush="true" />
		<script type="text/javascript" src="js/saclist.js"></script>
		<div id="right">
			<div class="title8">
				报表扫描图列表
			</div>
			<div class="loginbox1">
			 <div id="imagePreview" title="图片预览"> </div> 
  <table width="730" border="0" align="center" cellpadding="0" cellspacing="0" class="fontstyle4">
    <%--<tr>
      <td width="277" height="50" align="right">选择查询关键字：</td>
      <td width="227"><select name="select"  class="select1">
          <option>请选择</option>
      </select></td>
      <td width="226"><input type="submit" name="Submit" value="查询" /></td>
    </tr>
    --%><tr>
      <td height="40" colspan="3" align="right"><a href="qt/listScanPic.do"  class="a2">显示所有>></a><br>
      <table width="730" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CEE7FF">
        <tr><td></td></tr>
        <tr>
          <td width="44" height="35" align="center" bgcolor="#E8F7FF"><strong>序号</strong></td>
          <td width="344" align="center" bgcolor="#E8F7FF"><strong>报表扫描图名称</strong></td>
          <td width="134" align="center" bgcolor="#E8F7FF"><strong>所属观测站</strong></td>
          <td width="121" align="center" bgcolor="#E8F7FF"><strong>日期</strong></td>
          <td colspan="2" align="center" bgcolor="#E8F7FF"><strong>操作</strong></td>
        </tr>
        <c:forEach items="${smtlist}" var="smt" varStatus="varStatusSmt">	
        <tr>
          <td height="35" align="center" valign="middle" bgcolor="#FFFFFF">${varStatusSmt.index+1}</td>
          <td align="center" valign="middle" bgcolor="#FFFFFF"> ${smt.scanPicTitle}</td>
          <td align="center" valign="middle" bgcolor="#FFFFFF"> ${smt.station.name}</td>
          <td align="center" valign="middle" bgcolor="#FFFFFF"><fmt:formatDate value="${smt.createDate}"   type="date" dateStyle="default" /></td>
          <td width="37" align="center" valign="middle" bgcolor="#FFFFFF"><a href="javascript:previewSac('${smt.gramPath}');" class="a4">浏览</a></td>
          <td width="43" align="center" valign="middle" bgcolor="#FFFFFF"><a href="qt/downloadScanpic.do?scanPicID=${smt.scanPicID}" class="a4">下载</a></td>
        </tr>
        </c:forEach>
        
      </table></td>
    </tr>
    <tr>
      <td height="40" colspan="3" align="center">
      <div class="newspage">
					<a href="qt/listScanPic.do" class="a3">首页</a>
					  <c:choose>
				    	<c:when test="${requestScope.page.pageNumber>1}" >
				    	  <a href="qt/listScanPic.do?pageNumber=${ requestScope.page.pageNumber-1}" class="a3">上一页</a>
				    	</c:when>
				    	<c:otherwise>
						  <a href="javascript:void(0)" class="a3">上一页</a>	       
			    		</c:otherwise>
			    	</c:choose>
					 当前
					<span class="fontstyle3">${ requestScope.page.pageNumber}</span>/${ requestScope.page.pageCount}页
					<c:choose>
				    	<c:when test="${requestScope.page.pageNumber<requestScope.page.pageCount}" >
				    	  <a href="qt/listScanPic.do?pageNumber=${ requestScope.page.pageNumber+1}" class="a3">下一页</a>
				    	</c:when>
				    	<c:otherwise>
						  <a href="javascript:void(0)" class="a3">下一页</a>	       
			    		</c:otherwise>
			    	</c:choose>
					
					<a href="qt/listScanPic.do?pageNumber=${ requestScope.page.pageCount}" class="a3">末页</a>
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

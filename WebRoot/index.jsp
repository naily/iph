﻿<%@ page language="java" import="java.util.*,cn.fam1452.dao.pojo.*"  %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="WEB-INF/jsp/jstl.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${msg['site_name']}</title>
<link href="images/1.css" type="text/css" rel="stylesheet" />
<link href="css/index.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/default/om-default.css"/>
</head>

<body>
<jsp:include page="WEB-INF/jsp/qt/header.jsp" flush="true" />
<script type="text/javascript" src="js/indexNews.js"></script>
<script type="text/javascript" src="js/indexMetaData.js"></script>
<script type="text/javascript" src="js/index_usercomment.js"></script>
<script type="text/javascript" src="js/index_right_data_query.js"></script>

<div  id="right">
<div class="rightbox1">
<div class="title4">${msg['index_project_description']}</div>
由于地球磁场的特殊位形，极区空间物理的观测研究在日地能量传输过程的研究中占有极其重要的地位。我国南极中山站（69°22′S，76°23′E，不变磁纬74.5°，L=13.9）白天处于磁层极隙区，晚上处于极盖区，可以观测到丰富的日地能量传输过程的电离层征兆和极光现象，是开展高空大气物理观测研究的理想之地。通过与日本、澳大利亚等的国际合作，自1995年起我国在南极中山站逐步建立了高空大气综合观测系统。该系统由电离层、极光、地磁等方面的8台观测仪器组成。自1995年起我国在南极中山站逐步建立了高空大气综合观测系统。</div>
<!--rightbox1 项目简介 结束-->
<div class="rightbox2">
<div class="rightbox2_1">
<div class="rightbox2_1_img" id="indexRightNewsImg"></div>
<div class="title5">${msg['index_pic_news']}</div>
<div class="rightbox2_1_text"><a href="javascript:void(0)" id="showPicNews" class="a2"><div id="newsBrief"></div></a></div>
</div><!--rightbox2_1 结束-->
<div class="rightbox2_2">
<div class="title6">${msg['index_laster_data']}</div>
<div class="rightbox2_2_text" id="lastDataUpdate">
<%--<a href="#" class="a2">电离层专题数据库管理系统数据更新</a><br />
<a href="#" class="a2">电离层专题数据库管理系统数据更新</a><br />
<a href="#" class="a2">电离层专题数据库管理系统数据更新</a><br />
<a href="#" class="a2">电离层专题数据库管理系统数据更新</a><br />
<a href="#" class="a2">电离层专题数据库管理系统数据更新</a>
--%></div>
</div><!--rightbox2_2 结束-->

<div class="rightbox2_3">
<div class="title6" style="margin-top:1px;">${msg['index_Data_Access_Statistics']}</div>
<div class="rightbox2_3_text">
${msg['qt_reg_user_num']}：<span id="regUserNum"></span><br />
${msg['qt_site_visit_num']}：<span id="visitNum"></span><br />
${msg['qt_data_query_num']}：<span id="queryNum"></span><br />
${msg['qt_data_download_num']}：<span id="downloadNum"></span><br />
${msg['qt_data_download_quantity']}：<span id="downloadAmount"></span>
</div>
</div><!--rightbox2_3 结束-->

<div class="rightbox2_4">
<div class="title7">${msg['qt_your_comments']}</div>

<form id="userCommentForm" name="userCommentForm" action="qt/userComment.do" method="post" onSubmit="return userComment()"> 
<table width="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="76%" height="70"><label>
      <textarea name="content" id="userCommentContent"  rows="4" class="textarea1"></textarea>
    </label></td>
    <td width="24%" valign="bottom">
    <c:choose>
	    	<c:when test="${sessionScope.qt_account.login==true}" >
	    	<a href="qt/userCommentList.do">[${msg['qt_your_comments_old']}]</a> <br/><br/>
	    	</c:when>
	</c:choose>
	<a href="javascript:void(0)" id="userCommentSubmit">
	  <img 
	   <c:choose>
		<c:when test='${msg.lang=="zh"}'>  src="images/d10.jpg"</c:when>		
		<c:otherwise> src="images/d12.jpg"</c:otherwise>
	  </c:choose>
	  border="0"/></a>
	<%--
   <input name="userCommentSubmit"  id="userCommentSubmit" type="image" value="" src="images/d10.jpg"/></a></td>
  --%></tr>
</table>
</form>
</div>
<!--rightbox2_4 结束-->
</div><!--rightbox2 结束-->
<div  class="rightbox3_1" id="rightbox3_1">
<!-- <div class="rightbox3_1_text">
<a href="#" class="a2">电离层专题数据库管理系统数据</a><br />
<a href="#" class="a2">电离层专题数据库管理系统数据更新</a><br />
<a href="#" class="a2">电离层专题数据库管理系统数据更新</a><br />
<a href="#" class="a2">电离层专题数据库管理系统数据更新</a><br />
<a href="#" class="a2">电离层专题数据库管理系统数据更新</a>
</div>
<div class="rightbox3_1_text">
<a href="#" class="a2">电离层专题数据库管理系统数据更新</a><br />
<a href="#" class="a2">电离层专题数据库管理系统数据更新</a><br />
<a href="#" class="a2">电离层专题数据库管理系统数据更新</a><br />
<a href="#" class="a2">电离层专题数据库管理系统数据更新</a><br />
<a href="#" class="a2">电离层专题数据库管理系统数据更新</a>
</div> -->
</div>

<div class="rightbox3_2"> 
<form id="metaDataQueryForm"  name="metaDataQueryForm" action="qt/metaDataList.do" method="post" onSubmit="return queryMetaData()">
 <table width="99%" border="0" cellspacing="0" cellpadding="0" class="rightbox3_2_m">
    <tr>
      <td width="46" height="34" align="right" class="fontstyle2" ><img src="images/d09.jpg" width="41" height="34" /></td>
      <td width="88" align="left" class="fontstyle2" >${msg['qt_metadata_search']}</td>
      <td width="301"><label></label>
      	
          <input type="text" name="title" id="metaDataKeyword" class="boxinput2" /></td>
      <td width="67"> 
      
      <input name="metaSubmit"  id="metaSubmit" type="image" value="" 
      <c:choose>
		<c:when test='${msg.lang=="zh"}'>  src="images/d08.jpg"</c:when>		
		<c:otherwise> src="images/d11.jpg"</c:otherwise>
	  </c:choose>
     
      
       />
      
      <%--<img src="images/d08.jpg" width="67" height="28" border="0" />--%>
      </td>
    </tr>
  </table>
  </form>
  <!--搜索结束-->
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" colspan="2" valign="bottom" class="fontstyle2">&nbsp;<span id="metaData_Title0"></span></td>
    </tr>
  <tr>
    <td width="135" ><div class="rightbox3_img" id="metaData_img0"></div></td>
    <td><div class="rightbox3_text" id="metaData_Summary0"></div></td>
  </tr>
    <tr>
    <td height="30" colspan="2" valign="bottom" class="fontstyle2">&nbsp;<span id="metaData_Title1"></span></td>
    </tr>
  <tr>
    <td width="135" ><div class="rightbox3_img" id="metaData_img1"></div></td>
    <td><div class="rightbox3_text" id="metaData_Summary1"></div></td>
  </tr>  
  
    <tr>
    <td height="30" colspan="2" valign="bottom" class="fontstyle2">&nbsp;<span id="metaData_Title2"></span></td>
    </tr>
  <tr>
    <td width="135" ><div class="rightbox3_img" id="metaData_img2"></div></td>
    <td><div class="rightbox3_text" id="metaData_Summary2"></div></td>
  </tr>
  
</table>

</div>
</div>
		<!--right 结束-->
		<jsp:include page="WEB-INF/jsp/qt/left.jsp" flush="true" />
		<!--left 结束-->
		<jsp:include page="WEB-INF/jsp/ht/footer.jsp" flush="true" />
<%--<div id="bottom">
版权所有：中国科学院地理科学与资源研究所-科技基础性工作专项重点项目 &nbsp;&nbsp; 
<a href="ht/index.do" class="a1" target="blank">[管理员]</a><br />
联系电话：010-51888888  &nbsp;&nbsp; 通讯地址：  邮编： 
</div>
--%></body>
</html>

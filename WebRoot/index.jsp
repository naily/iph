<%@ page language="java" import="java.util.*,cn.fam1452.dao.pojo.*"  %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="WEB-INF/jsp/jstl.jsp" %>
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${msg['site_name']}</title>
<link href="images/1.css" type="text/css" rel="stylesheet" />
<link href="css/index.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/default/om-default.css"/>
<script type="text/javascript">
	var fg = '<%=request.getParameter("login")%>' ;
	if(fg == 'no'){
		<c:choose>
		 <c:when test='${msg.lang=="zh"}'> 
		 	alert("请登录!") ;
		</c:when>
		<c:otherwise>
			alert("Please login !") ;
		</c:otherwise>
		</c:choose>
		
	}
</script>

</head>

<body>
<jsp:include page="WEB-INF/jsp/qt/header.jsp" flush="true" />
<script type="text/javascript" src="js/indexNews.js"></script>
<script type="text/javascript" src="js/indexMetaData.js"></script>
<script type="text/javascript" src="js/index_usercomment.js"></script>
<script type="text/javascript" src="js/index_right_data_query.js"></script>

<div id="right">
	<div class="rightbox1">
	<div class="title4">${msg['index_project_description']}</div>
	<c:choose>
		 <c:when test='${msg.lang=="zh"}'> 
			<p>电离层专题数据库是国家科技基础性工作专项重点项目“电离层历史资料整编和电子浓度剖面及区域特性图集编研” （项目编号：2008FY120100）的电离层数据整理和编研后形成的数据应用系统，该项目是通过对武汉电离层站60年历史积累的电离层测高仪手动人工观测、胶片频高图和数字频高图资料，
			按国际新标准统一进行度量、处理和参数提取，获得完整的我国中部（武汉）电离层参数、频高图和电子浓度剖面等数据集。同时收集我国及周边有关台站资料，通过定点定期流动观测，获得我国电离层观测空白区资料，在此基础上研究编制中国电离层地区特性变化图，形成我国历史最久、连续性最好，
			符合国际标准规范的电离层特性数据集和图集，它们能全面地反映我国电离层时间和空间多尺度变化特性，为我国电离层地区特性等基础科学研究，提供重要的基础性资料，同时也为我国地球空间环境预报和相关空间技术研究，提供多种电离层参量信息保障。</p>
			<p>项目由中国科学院地质与地球物理研究所承担，负责项目的组织实施和有关资料收集、数字化、录入、分析处理、校正和编研。并委托合作单位中科院地理科学与资源研究所开展元数据标准和数据建库规范等研制，完成项目需要的电离层专题数据库开发和建设。</p>
		</c:when>
		<c:otherwise>
			Special bit-shaped due to the Earth's magnetic field, the observations of the polar region of space physics research in the study of energy transfer processes in the Sun-Earth occupies an extremely important position. China's Antarctic Zhongshan Station (69 ° 22'S, 76 ° 23'E constant magnetic latitude 74.5 °, L = 13.9) during the day and in the magnetospheric cusp, night is polar cap, can be observed rich day of energy sign of ionospheric and auroral phenomena in the transmission process, it is the ideal place to carry out observational studies of upper atmospheric physics. Through international cooperation with Japan, Australia, since 1995 our country in the Antarctic Zhongshan Station to gradually establish the upper atmosphere observation system. The system is composed by eight of the ionosphere, auroras, geomagnetic observation instruments. Zhongshan Station in Antarctica since 1995, China has gradually established upper atmospheric observing systems.							
		</c:otherwise>
	</c:choose>	
		
	</div>
<!--rightbox1 项目简介 结束-->
<!--新闻开始 -->
	<c:if test='${msg.lang=="zh"}'>
	
	  <div class="rightbox3_1" style="float: left;height:130px;overflow: hidden;padding-top:0px;">
      <table width="100%" height="100" border="0">
      <tr>
        <td ><span class="title5" style="float:left;">${msg['index_pic_news']}</span></td>
        <td  rowspan="2" valign="top">
        <div class="rightbox2_1_img" id="indexRightNewsImg" style="margin:2px; border:red 0px solid;"></div></td>
        <td  rowspan="2" valign="top">
        <div  class="rightbox3_0" id="rightbox3_1" style="padding-top:2px;overflow: hidden;border:0px;"></div>
        </td>
      </tr>
      <tr>
        <td>
        <div class="rightbox2_1_text" style="height: 120px ;float:right;overflow: hidden;">
			<a href="javascript:void(0)" id="showPicNews" class="a2">
				<span id="newsBrief"></span>
			</a>
		</div>
        </td>
        
      </tr>
    </table>
	</div>
	<!--rightbox2_1 结束-->
		
	</c:if>
	<!--新闻结束 -->

<div class="rightbox2">
<div class="rightbox2_5">
<div class="title6">${msg['index_taizhan_title']}</div>
<div class="rightbox2_5_img" id="">
<a href="qt/smap.do" target="blank"><img src="images/mapmini.png" alt="" border="0" height="110" align="left"/></a></div>
		<div class="rightbox2_5_text">
			<a href="qt/smap.do" id="" class="a2" target="blank">
				${msg['index_taizhan_jianjie']}
			</a>
		</div>	
</div><!--rightbox2_5 结束 台站分布-->
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

<div class="rightbox3_2" style="height: 310px;overflow: hidden;"> 
<form id="metaDataQueryForm"  name="metaDataQueryForm" action="qt/metaDataList.do" method="post" >
 <table width="99%" border="0" cellspacing="0" cellpadding="0" class="rightbox3_2_m">
    <tr>
      <td width="46" height="34" align="right" class="fontstyle2" ><img src="images/d09.jpg" width="41" height="34" /></td>
      <td width="88" align="left" class="fontstyle2" >${msg['qt_metadata_search']}</td>
      <td width="301"><label></label>
          <input type="text" name="title" id="metaDataKeyword" class="boxinput2 inputtext" value="${msg['index_query_key_alert']}"  />
      </td><!--  onblur="if(this.value==''){this.value='请输入你要查找的信息...'}" -->
      
      <td width="67"> 
      <input name="metaSubmit"  id="metaSubmit" type="image" value="" 
	      <c:choose>
			<c:when test='${msg.lang=="zh"}'>  src="images/d08.jpg"</c:when>		
			<c:otherwise> src="images/d11.jpg"</c:otherwise>
		  </c:choose>
      />
      
      </td>
    </tr>
  </table>
  </form>
  <!--搜索结束-->
<table id="index_metadata_table" width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="20" colspan="2" valign="bottom" class="fontstyle2">&nbsp;<span id="metaData_Title0"></span></td>
    </tr>
  <tr>
    <td width="135" ><div class="rightbox3_img" id="metaData_img0"></div></td>
    <td><p class="rightbox3_text" id="metaData_Summary0"></p></td>
  </tr>
    <tr>
    <td height="20" colspan="2" valign="bottom" class="fontstyle2">&nbsp;<span id="metaData_Title1"></span></td>
    </tr>
  <tr>
    <td width="135" ><div class="rightbox3_img" id="metaData_img1"></div></td>
    <td><p class="rightbox3_text" id="metaData_Summary1"></p></td>
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
<div class="rightbox3_1" style="margin-top: 10px;height: 135px;padding-top: 2px;overflow: hidden;">
	<div class="title5" >${msg['index_news_showspace']}</div>
	<ul id="zskj_news"> <li>Found no data</li> </ul>
</div>
</div>
		<!--right 结束-->
		<jsp:include page="WEB-INF/jsp/qt/left.jsp" flush="true" />
		<!--left 结束-->
		
		<c:choose>
		<c:when test='${msg.lang=="zh"}'> 
		 <jsp:include page="WEB-INF/jsp/ht/footer.jsp" flush="true" />
         </c:when>
         <c:otherwise> <jsp:include page="WEB-INF/jsp/qt/footer.jsp" flush="true" /></c:otherwise>				
	  </c:choose>
<%--<div id="bottom">
版权所有：中国科学院地理科学与资源研究所-科技基础性工作专项重点项目 &nbsp;&nbsp; 
<a href="ht/index.do" class="a1" target="blank">[管理员]</a><br />
联系电话：010-51888888  &nbsp;&nbsp; 通讯地址：  邮编： 
</div>
--%></body>
</html>

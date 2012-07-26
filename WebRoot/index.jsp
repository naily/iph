<%@ page language="java" import="java.util.*,cn.fam1452.dao.pojo.*"  %>
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
<title>电离层专题数据库管理系统</title>
<link href="images/1.css" type="text/css" rel="stylesheet" />
<link href="css/index.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/default/om-default.css"/>
<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
<script type="text/javascript" src="js/Global.js"></script>
<script type="text/javascript" src="js/index_global_zn.js"></script>
<script type="text/javascript" src="js/userRegist.js"></script>
<script type="text/javascript" src="js/indexNews.js"></script>
<script type="text/javascript" src="js/indexMetaData.js"></script>
</head>

<body>
<!-- 用户注册 start -->
    <div id="userRegDialog"  title="用户注册">
        <div >
	    		<form id="userRegForm"  name="userRegForm">
	    		<input name="optType" id="optTpyeID" type="hidden"/>
	    		<table   border="0" align="center" cellpadding="0" cellspacing="0"  class="loginbox_bor">
				  <tr>
				    <td height="10" colspan="3" align="center">&nbsp;</td>
				    </tr>
				  <tr>
				    <td class="userregtd">${msg['qt_regist_username']}：</td>
				    <td class="userregtd2"><input name="loginId" id="loginId" class="boxinput3" /><!-- <span class="redcolor">*</span> --></td>
				    <td class="userregtd_img"><span class="errorImg"></span><span class="errorMsg"></span></td>
				    
				    </tr>
				  <tr>
				    <td class="userregtd">${msg['qt_regist_password']}：</td>
				    <td ><input name="password" id="password" class="boxinput3" /><!-- <span class="redcolor">*</span> --></td>
				    <td class="userregtd_img"><span class="errorImg"></span><span class="errorMsg"></span></td>
				    </tr>
				  <tr>
				    <td class="userregtd">${msg['qt_regist_realname']}：</td>
				    <td><input name="name" id="name" class="boxinput3" /><!-- <span class="redcolor">*</span> --></td>
				    <td class="userregtd_img"><span class="errorImg"></span><span class="errorMsg"></span></td>
				    </tr>
				     <tr>
				    <td class="userregtd">${msg['qt_regist_gender']}：</td>
				    <td><input name="gender" id="gender" class="boxinput3" /><!-- <span class="redcolor">*</span> --></td>
				    <td class="userregtd_img"><span class="errorImg"></span><span class="errorMsg"></span></td>
				    </tr>
				     <tr>
				    <td class="userregtd">${msg['qt_regist_degree']}：</td>
				    <td ><input name="eduBackground" id="eduBackground" class="boxinput3" /><!-- <span class="redcolor">*</span> --></td>
				    <td class="userregtd_img"><span class="errorImg"></span><span class="errorMsg"></span></td>
				    </tr>
				     <tr>
				    <td class="userregtd">${msg['qt_regist_email']}：</td>
				    <td><input name="email" id="email" class="boxinput3" /><!-- <span class="redcolor">*</span> --></td>
				    <td class="userregtd_img"><span class="errorImg"></span><span class="errorMsg"></span></td>
				    </tr>
				     <tr>
				    <td class="userregtd">${msg['qt_regist_industry']}：</td>
				    <td><input name="vocation" id="vocation" class="boxinput3" /><!-- <span class="redcolor">*</span> --></td>
				    <td class="userregtd_img"><span class="errorImg"></span><span class="errorMsg"></span></td>
				    </tr>
				     <tr>
				    <td class="userregtd">${msg['qt_regist_country']}：</td>
				    <td><input name="country" id="country" class="boxinput3" /></td>
				    <td class="userregtd_img"></td>
				    </tr>
				  <tr>
				    <td class="userregtd">${msg['qt_regist_region']}：</td>
				    <td><input name="region" id="region" class="boxinput3" /><!-- <span class="redcolor">*</span> --></td>
				    <td class="userregtd_img"><span class="errorImg"></span><span class="errorMsg"></span></td>
				    </tr>
				   <tr>
				    <td class="userregtd">${msg['qt_regist_company']}：</td>
				    <td ><input name="workunit" id="workunit" class="boxinput3" /></td>
				    <td class="userregtd_img"></td>
				    </tr>
				    <tr>
				    <td class="userregtd">${msg['qt_regist_address']}：</td>
				    <td><input name="address" id="address" class="boxinput3" /></td>
				    <td class="userregtd_img"></td>
				    </tr>
				    <tr>
				    <td class="userregtd">${msg['qt_regist_zipcode']}：</td>
				    <td><input name="zipcode" id="zipcode" class="boxinput3" /></td>
				    <td class="userregtd_img"></td>
				    </tr> 
				     <tr>
				    <td class="userregtd">${msg['qt_regist_telephone']}：</td>
				    <td><input name="telephone" id="telephone" class="boxinput3" /></td>
				    <td class="userregtd_img"></td>
				    </tr> 
				    <tr>
				    <td  class="userregtd">${msg['ht_login_code']}：</td>
				    <td ><input type="text" name="code" id="code" class="boxinput4"  /><span class="y_img"><img src="ht/logincode.do" align="absmiddle" width="90" height="22" border="0" id="vailcode"/></span><a href="javascript:void(0)" onclick="reloadimage('vailcode');" class="a4"> 换一张</a></td>
				    <td class="userregtd_img"></td>
				    
				  </tr>				  
				  <tr>
				    <td height="30" colspan="3" align="center">
				    
				    <input name="regSubmit"  id="regSubmit" type="image" value="ee" src="images/d10.jpg" />
				    
				    </td>
				  </tr>
				</table>
				</form>
				</div>
    </div>
    <!-- 找回密码 start -->
    <div id="getUserPassword"  title="找回密码">
        <div >
	    		<form id="getUserPasswordForm" name="getUserPasswordForm">
	    		<table border="0" align="center" cellpadding="0" cellspacing="0"  class="loginbox_bor">
				  <tr>
				    <td height="10" colspan="3" align="center">&nbsp;</td>
				    </tr>
				  <tr>
				    <td class="userregtd">${msg['qt_regist_username']}：</td>
				    <td class="userregtd2">
				      <input name="loginId" id="loginId_" class="boxinput3" /><!-- <span class="redcolor">*</span> -->
				    </td>
				    <td class="userregtd_img"><span class="errorImg"></span><span class="errorMsg"></span></td>
				    
				    </tr>
				  
				     <tr>
				    <td class="userregtd">${msg['qt_regist_email']}：</td>
				    <td><input name="email" id="email_" class="boxinput3" /><!-- <span class="redcolor">*</span> --></td>
				   <td class="userregtd_img"><span class="errorImg"></span><span class="errorMsg"></span></td>
				    </tr>				     
				    <tr>
				    <td  class="userregtd">${msg['ht_login_code']}：</td>
				    <td><input type="text" name="code1" id="code1" class="boxinput4"  /><span class="y_img"><img src="ht/logincode.do" align="absmiddle" width="70" height="22" border="0" id="vailcode_1"/></span><a href="javascript:void(0)" onclick="reloadimage('vailcode_1');" class="a4">换一张</a></td>
				  <td class="userregtd_img"></td>
				    
				  </tr>				  
				  <tr>
				    <td height="30" colspan="3" align="center">				 
				    <input name="getUserPasswordSubmit"  id="getUserPasswordSubmit" type="image" value="getpassword" src="images/d10.jpg" />				    
				    </td>
				  </tr>
				</table>
				</form>
				</div>
    </div>
     <!-- 找回密码 end -->
<div id="top">
<div class="top_right" >
<table width="518" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="508" height="35"  align="right" class="fontstyle1">
   <div id="user_login_form">
       <c:choose>
	    	<c:when test="${sessionScope.qt_account.login==true}" ><!-- 已经登录 -->
	    	欢迎您：<a href="javascript:void(0)" onclick="getRegInfo()" class="a1">${sessionScope.qt_account.loginId}</a> &nbsp;&nbsp;<a href="qt/logout.do" class="a1">退出登录</a>
	    	</c:when>
	    	<c:otherwise><!-- 未登录 -->
					        用户名<input type="text" id="userLoginId" name="userLoginId" class="boxinput1" />
					        密    码<input type="text" id="loginPassword"  name="loginPassword" class="boxinput1" />
                       <a href="javascript:void(0)" id="userLoginHref" class="a1">[登录]</a>
    	</c:otherwise>
    	</c:choose>
    </div>
    </td>
    <td width="5"></td>
    <td width="130"><a href="javascript:void(0)" id="userRegHref" class="a1">[注册]</a>&nbsp;<a href="javascript:void(0)" id="getUserPasswordHref" class="a1">忘记密码？</a>
    </td>
  </tr>
  <tr>
    <td height="67" colspan="6">&nbsp;</td>
    </tr>
  <tr>
    <td height="30" colspan="6">
	<div class="nav">
	<a href="#" class="a2 nav_float">首页</a>
	<a href="#" class="a2 nav_float">找数据</a>
	<a href="qt/report.do" class="a2 nav_float">报表</a>
	<a href="#" class="a2 nav_float">曲线图</a>
	<a href="#" class="a2 nav_float">关于我们</a>
	</div>
	
	</td>
    </tr>
</table>
</div><!--top右侧结束-->
<div class="top_language"><a href="javascript:;" class="a1">中文</a>   <a href="javascript:;" class="a1">English</a> </div>
</div><!--top结束-->

<div  id="right">
<div class="rightbox1">
<div class="title4">项目简介</div>
由于地球磁场的特殊位形，极区空间物理的观测研究在日地能量传输过程的研究中占有极其重要的地位。我国南极中山站（69°22′S，76°23′E，不变磁纬74.5°，L=13.9）白天处于磁层极隙区，晚上处于极盖区，可以观测到丰富的日地能量传输过程的电离层征兆和极光现象，是开展高空大气物理观测研究的理想之地。通过与日本、澳大利亚等的国际合作，自1995年起我国在南极中山站逐步建立了高空大气综合观测系统。该系统由电离层、极光、地磁等方面的8台观测仪器组成。自1995年起我国在南极中山站逐步建立了高空大气综合观测系统。</div>
<!--rightbox1 项目简介 结束-->
<div class="rightbox2">
<div class="rightbox2_1">
<div class="rightbox2_1_img" id="indexRightNewsImg"></div>
<div class="title5">图片新闻</div>
<div class="rightbox2_1_text"><a href="javascript:void(0)" id="showPicNews" class="a2"><div id="newsBrief"></div></a></div>
</div><!--rightbox2_1 结束-->
<div class="rightbox2_2">
<div class="title6">最新数据更新</div>
<div class="rightbox2_2_text">
<a href="#" class="a2">电离层专题数据库管理系统数据更新</a><br />
<a href="#" class="a2">电离层专题数据库管理系统数据更新</a><br />
<a href="#" class="a2">电离层专题数据库管理系统数据更新</a><br />
<a href="#" class="a2">电离层专题数据库管理系统数据更新</a><br />
<a href="#" class="a2">电离层专题数据库管理系统数据更新</a>
</div>
</div><!--rightbox2_2 结束-->

<div class="rightbox2_3">
<div class="title6" style="margin-top:1px;">数据访问统计</div>
<div class="rightbox2_3_text">
注册用户：152588<br />
网站访问量：8586<br />
数据查询次数：5858<br />
数据下载次数：8859<br />
数据下载数量：
</div>
</div><!--rightbox2_3 结束-->

<div class="rightbox2_4">
<div class="title7">您的意见</div>
<table width="99%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="76%" height="70"><label>
      <textarea name="textarea" rows="4" class="textarea1"></textarea>
    </label></td>
    <td width="24%" valign="bottom"><a href="#"><img src="images/d10.jpg" width="48" height="21" border="0" /></a></td>
  </tr>
</table>
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
      <td width="88" align="left" class="fontstyle2" >元数据搜索</td>
      <td width="301"><label></label>
      	
          <input type="text" name="title" id="metaDataKeyword" class="boxinput2" /></td>
      <td width="67"> <input name="metaSubmit"  id="metaSubmit" type="image" value="" src="images/d08.jpg" /><%--<img src="images/d08.jpg" width="67" height="28" border="0" /></td>--%>
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
</div><!--right 结束-->

<div  id="left">
<div class="title1">数据导航</div>
<div class="title2">电离层频高图</div>
<table width="170" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" class="fontstyle2"><a href="#" class="a3">武汉站</a></td>
  </tr>
  <tr>
    <td height="30" align="right" class="fontstyle2"><a href="#"  class="a3">1946年</a></td>
  </tr>
  <tr>
    <td height="30" align="right" class="fontstyle2"><a href="#"  class="a3">1946年</a></td>
  </tr>
  <tr>
     <td height="30" align="right" class="fontstyle2"><a href="#"  class="a3">...</a></td>
  </tr>
</table>
<div class="title2">电离层参数</div>
<table width="170" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" class="fontstyle2"><a href="#" class="a3">武汉站</a></td>
  </tr>
  <tr>
    <td height="30" align="right" class="fontstyle2"><a href="#"  class="a3">1946年</a></td>
  </tr>
  <tr>
    <td height="30" align="right" class="fontstyle2"><a href="#"  class="a3">1946年</a></td>
  </tr>
  <tr>
     <td height="30" align="right" class="fontstyle2"><a href="#"  class="a3">...</a></td>
  </tr>
</table>
<div class="title2">区域电离层参数特征图</div>
<table width="170" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" class="fontstyle2"><a href="#" class="a3">武汉站</a></td>
  </tr>
  <tr>
    <td height="30" align="right" class="fontstyle2"><a href="#"  class="a3">1946年</a></td>
  </tr>
  <tr>
    <td height="30" align="right" class="fontstyle2"><a href="#"  class="a3">1946年</a></td>
  </tr>
  <tr>
     <td height="30" align="right" class="fontstyle2"><a href="#"  class="a3">...</a></td>
  </tr>
</table>
<div class="title3">相关链接</div>
<table width="170" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" class="fontstyle2"><a href="#" class="a3">...</a></td>
  </tr>
  <tr>
    <td height="30" class="fontstyle2"><a href="#"  class="a3">...</a></td>
  </tr>
  <tr>
    <td height="30" class="fontstyle2"><a href="#"  class="a3">...</a></td>
  </tr>
  <tr>
    <td height="30" class="fontstyle2"><a href="#"  class="a3">...</a></td>
  </tr>
</table>
</div><!--left 结束-->
<div id="bottom">
版权所有：*** &nbsp;&nbsp;&nbsp;&nbsp;<a href="ht/index.do" class="a1" target="blank">[管理员]</a><br />
联系电话：010-51888888  地址：*** 邮编：***
</div>
</body>
</html>

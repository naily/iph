<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="../jstl.jsp" %>
<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<!-- -->
	<link href="images/1.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="css/header.css" />
<link href="css/index.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/default/om-default.css"/>
<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>

<script type="text/javascript" src="js/index_global_zn.js"></script>
<script type="text/javascript" src="js/userRegist.js"></script>
<title></title>
  </head>
  
  <body>
 <!-- 用户注册 start -->
<div id="userRegDialog"  title="用户注册">
        <div >
	    		<form id="userRegForm"  name="userRegForm">
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
	    	欢迎您：${sessionScope.qt_account.loginId} &nbsp;&nbsp;<a href="qt/logout.do" class="a1">退出登录</a>
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
		<a href="#" class="a2 nav_float">${msg['qt_mu_home']}</a>
		<a href="#" class="a2 nav_float">${msg['qt_mu_fetch']}</a>
		<a href="qt/report.do" class="a2 nav_float">${msg['qt_mu_report']}</a>
		<a href="#" class="a2 nav_float">${msg['qt_mu_graphs']}</a>
		<a href="#" class="a2 nav_float">${msg['qt_mu_me']}</a>
	</div>
	
	</td>
    </tr>
</table>
</div><!--top右侧结束-->
<div class="top_language"><a href="qt/lang/zh_CN.do" class="a1">中文</a>   
						  <a href="qt/lang/en_US.do" class="a1">English</a> </div>
</div><!--top结束-->

  </body>
</html>

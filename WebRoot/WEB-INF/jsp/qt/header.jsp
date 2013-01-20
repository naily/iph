<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="../jstl.jsp" %>
<!DOCTYPE HTML >
<html>
  <head>
  <meta http-equiv="X-UA-Compatible" content="IE=8">
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<!-- -->
	
	<link rel="stylesheet" type="text/css" href="css/header.css" />
	<link href="css/index.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css"/>
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	<script type="text/javascript" src="js/index_global.js"></script>
	<c:choose>
			 <c:when test='${msg.lang=="zh"}'> 
			 	<link href="images/1.css" type="text/css" rel="stylesheet" />
			 	<script type="text/javascript" src="js/index_global_zn.js"></script>
			</c:when>
			<c:otherwise>
				<link href="images/en.css" type="text/css" rel="stylesheet" />
				<script type="text/javascript" src="js/index_global_en.js"></script>
			</c:otherwise>
	</c:choose>
	<script type="text/javascript">
 		var isUserLogin=${sessionScope.qt_account.login==true}
 		/*funArray = new Array();
		$(document).ready(function(){
        	for(var i in funArray){
           		funArray[i]();
       		 }        
    	})*/
	</script>
	<script type="text/javascript" src="js/userRegist.js"></script>
	<script type="text/javascript" src="js/visitNum.js"></script>
	<title></title>
  </head>
  
  <body>
 <!-- 用户注册 start -->
<div id="userRegDialog"  title="${msg['qt_regist_title']}" style="display: none;">
        <div >
	    		<form id="userRegForm"  name="userRegForm">
	    		<table   border="0" align="center" cellpadding="0" cellspacing="0">
				  <tr>
				    <td height="30" colspan="3" align="">&nbsp; 
      <c:choose>
		<c:when test='${msg.lang=="zh"}'> 提示：标有 <font color="red">*</font>  的项为必填项</c:when>
		<c:otherwise>Tip: items marked * required</c:otherwise>
	</c:choose>
                    
                    
                    <br></td>
				    </tr>
				  <tr>
				    <td class="userregtd" ><font color="red">*</font>${msg['qt_regist_username']}：</td>
				    <td class="userregtd2"><input name="loginId" id="loginId" class="boxinput3" /><!-- <span class="redcolor">*</span> --></td>
				    <td class="userregtd_img"><span class="errorImg"></span><span class="errorMsg"></span></td>
				    
				    </tr>
				  <tr>
				    <td class="userregtd"><font color="red">*</font>${msg['qt_regist_password']}：</td>
				    <td ><input name="password" id="password" class="boxinput3" /><!-- <span class="redcolor">*</span> --></td>
				    <td class="userregtd_img"><span class="errorImg"></span><span class="errorMsg"></span></td>
				    </tr>
				  <tr>
				    <td class="userregtd"><font color="red">*</font>${msg['qt_regist_realname']}：</td>
				    <td><input name="name" id="name" class="boxinput3" /><!-- <span class="redcolor">*</span> --></td>
				    <td class="userregtd_img"><span class="errorImg"></span><span class="errorMsg"></span></td>
				    </tr>
				     <tr>
				    <td class="userregtd"><font color="red">*</font>${msg['qt_regist_gender']}：</td>
				    <td><input name="gender" id="gender" class="boxinput3" /><!-- <span class="redcolor">*</span> --></td>
				    <td class="userregtd_img"><span class="errorImg"></span><span class="errorMsg"></span></td>
				    </tr>
				     <tr>
				    <td class="userregtd"><font color="red">*</font>${msg['qt_regist_degree']}：</td>
				    <td ><input name="eduBackground" id="eduBackground" class="boxinput3" /><!-- <span class="redcolor">*</span> --></td>
				    <td class="userregtd_img"><span class="errorImg"></span><span class="errorMsg"></span></td>
				    </tr>
				     <tr>
				    <td class="userregtd"><font color="red">*</font>${msg['qt_regist_email']}：</td>
				    <td><input name="email" id="email" class="boxinput3" /><!-- <span class="redcolor">*</span> --></td>
				    <td class="userregtd_img"><span class="errorImg"></span><span class="errorMsg"></span></td>
				    </tr>
				     <tr>
				    <td class="userregtd"><font color="red">*</font>${msg['qt_regist_industry']}：</td>
				    <td><input name="vocation" id="vocation" class="boxinput3" /><!-- <span class="redcolor">*</span> --></td>
				    <td class="userregtd_img"><span class="errorImg"></span><span class="errorMsg"></span></td>
				    </tr>
				     <tr>
				    <td class="userregtd">${msg['qt_regist_country']}：</td>
				    <td><input name="country" id="country" class="boxinput3" /></td>
				    <td class="userregtd_img"></td>
				    </tr>
				  <tr>
				    <td class="userregtd"><font color="red">*</font>${msg['qt_regist_region']}：</td>
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
				    <td  class="userregtd"><font color="red">*</font>${msg['ht_login_code']}：</td>
				    <td ><input type="text" name="code" id="code" class="boxinput4"  /><span class="y_img"><img src="ht/logincode.do" align="absmiddle" width="90" height="22" border="0" id="vailcode"/></span><a href="javascript:void(0)" onClick="reloadimage('vailcode');" class="a4"> ${msg['index_pic_change']}</a></td>
				    <td class="userregtd_img"></td>
				    
				  </tr>				  
				  <tr>
				    <td height="30" colspan="3" align="center">
				    
				    <input name="regSubmit"  id="regSubmit" type="image" value="ee" 
                     <c:choose>
                     	<c:when test='${msg.lang=="zh"}'>src="images/d10.jpg"</c:when>
						<c:otherwise>src="images/d12.jpg"</c:otherwise>
					 </c:choose>
                    />
				    
				    </td>
				  </tr>
				</table>
				</form>
				</div>
    </div>
    <!-- 找回密码 start -->
    <div id="getUserPassword"  title="${msg['qt_get_password']}" style="display: none;">
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
				    <td><input type="text" name="code1" id="code1" class="boxinput4"  /><span class="y_img"><img src="ht/logincode.do" align="absmiddle" width="70" height="22" border="0" id="vailcode_1"/></span><a href="javascript:void(0)" onClick="reloadimage('vailcode_1');" class="a4">${msg['index_pic_change']}</a></td>
				  <td class="userregtd_img"></td>
				    
				  </tr>				  
				  <tr>
				    <td height="30" colspan="3" align="center">				 
				    <input name="getUserPasswordSubmit"  id="getUserPasswordSubmit" type="image" value="getpassword" 
      <c:choose>
		<c:when test='${msg.lang=="zh"}'> src="images/d10.jpg" />	</c:when>
		<c:otherwise>src="images/d12.jpg" />	</c:otherwise>
	</c:choose>
                    			    
				    </td>
				  </tr>
				</table>
				</form>
				</div>
    </div>
     <!-- 找回密码 end -->
<div id="top"
<c:choose>
		<c:when test='${msg.lang=="zh"}'> style="background:url(images/d01.jpg) no-repeat;"</c:when>
		<c:otherwise>style="background:url(images/d13.jpg) no-repeat;"</c:otherwise>
	</c:choose>
>
<div class="top_right" >
<table width="518" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="350" height="35"  align="right" class="fontstyle1" valign="baseline">
	   <div id="user_login_form">
	       <c:choose>
		    	<c:when test="${sessionScope.qt_account.login==true}" ><!-- 已经登录 -->
		    		${msg['qt_login_welcome']}：${sessionScope.qt_account.loginId} &nbsp;&nbsp;<a href="qt/logout.do" class="a1">${msg['qt_login_out']}</a>
		    	</c:when>
		    	<c:otherwise><!-- 未登录 -->
			        ${msg['qt_login_username']}: <input type="text" id="userLoginId" name="userLoginId" class="boxinput1_1" />
			        ${msg['qt_login_password']}: <input type="password" id="loginPassword"  name="loginPassword" class="boxinput1_1" />
                    <a href="javascript:void(0)" id="userLoginHref" class="a1">[${msg['qt_login_submit']}]</a>
	    		</c:otherwise>
	    	</c:choose>
	    </div>
    </td>
    <td width="5">&nbsp;</td>
    <td width="160" height="35" align="left" class="fontstyle1" valign="baseline">
    	<a href="javascript:void(0)" id="userRegHref" class="a1">[${msg['qt_register']}]</a><a href="javascript:void(0)" id="getUserPasswordHref" class="a1">${msg['qt_login_forget']} ?</a>
    </td>
  </tr>
  <tr>
    <td height="67" colspan="6">&nbsp;</td>
    </tr>
  <tr>
    <td height="30" colspan="6">
	<div class="nav">
		<a href="index.jsp" class="a2 nav_float">${msg['qt_mu_home']}</a>
		<a href="qt/paraDataQuery.do" class="a2 nav_float">${msg['qt_mu_fetch']}</a>
		<a href="qt/report.do" class="a2 nav_float">${msg['qt_mu_report']}</a>
		<a href="qt/paraDataChart.do" class="a2 nav_float">${msg['qt_mu_graphs']}</a>
		<a href="qt/about.do" class="a2 nav_float">${msg['qt_mu_me']}</a>
	</div>
	
	</td>
    </tr>
</table>
</div><!--top右侧结束-->
<div class="top_language"><a href="qt/lang/zh_CN.do" class="a3">中文</a>   
						  <a href="qt/lang/en_US.do" class="a3">English</a> </div>
</div><!--top结束-->

  </body>
</html>

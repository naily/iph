<%@ page language="java" import="java.util.*,cn.fam1452.dao.pojo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="../jstl.jsp" %>
<!DOCTYPE HTML  >
<html>
  <head>
  <meta http-equiv="X-UA-Compatible" content="IE=8">
    <base href="<%=basePath%>">
    <title>后台登陆</title>
	<!-- -->
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css">
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	
	<script type="text/javascript" src="js/login.js"></script>
	<script type="text/javascript">
		var s = '${sessionScope.ht_account.login}' ;
	</script>
	
	<c:if test="${!sessionScope.ht_account.login}" >
		<link href="images/1.css" type="text/css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="css/header.css" />
	</c:if>
  </head>
  
  <body>
  	<c:if test="${sessionScope.ht_account.login}" >
		<jsp:include page="header.jsp"></jsp:include>
	</c:if>
  	<c:if test="${!sessionScope.ht_account.login}" >
		<div id="top2">
			<div class="top_right2"></div>
			<!--top右侧结束-->
			<div class="top_language">
			<!--  
			<a href="ht/lang/zh_CN.do" class="a1">中文</a>   
			<a href="ht/lang/en_US.do" class="a1">English</a> 
			-->
			</div>
		  </div><!--top结束-->
	</c:if>
    <div id="content_login" class="loginbox">
    	<c:choose>
	    	<c:when test="${sessionScope.ht_account.login}" >
	    	<!-- 已经登录 -->
	    		<div >
					<table width="400" height="210" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="0" class="loginbox_bor">
					  <tr>
					    <td colspan="4" align="center">&nbsp;
					    <h1 style="color:#FF8C00; ">登录成功!</h1>
					    </td>
				     </tr>
					  <tr>
					    <td colspan="4" align="center">欢迎你：&nbsp;&nbsp; ${sessionScope.ht_account.name}<br/></td>
				    </tr>
					  <tr>
					    <td colspan="4" height="40" align="right">
					    <!--  
					    <a href="ht/logout.do"><h4>退出</h4></a>&nbsp;&nbsp; --></td>
				    </tr>
					  <tr>
					    <td colspan="4" height="10"  align="center">&nbsp;</td>
				    </tr>
				</table>
				</div>
	    	</c:when>
	    	<c:otherwise>
	    	<!-- 未登录 -->
	    		<div >
	    		<table width="400" height="210" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="0" class="loginbox_bor">
				  <tr>
				    <td height="10" colspan="4" align="center">&nbsp;</td>
				    </tr>
				  <tr>
				    <td width="96" height="40" align="right" >${msg['ht_login_username']}：</td>
				    <td colspan="3"><label>
				      <input type="text" name="username" class="boxinput3" />
				    </label></td>
				    </tr>
				  <tr>
				    <td height="40" align="right" >${msg['ht_login_password']}：</td>
				    <td colspan="3"><input name="mypassword" class="boxinput3" type="password"/></td>
				    </tr>
				  <tr>
				    <td height="40" align="right" >${msg['ht_login_code']}：</td>
				    <td width="95"><input type="text" name="code" class="boxinput4" /></td>
				    <td width="84"> <div class="y_img"><img src="ht/logincode.do" width="100" height="22" border="0" id="vailcode"/></div></td>
				    <td width="119" align="center"><a href="javascript:;" onclick="reloadimage();" class="a4">看不清 换一张</a></td>
				  </tr>
				  <tr>
				    <td colspan="4" height="30"><div id="errorMessages" class="errorMessages"></div>
				    </td>
				  </tr>
				  <tr>
				    <td height="60" colspan="4" align="center"><a href="javascript:;" id="but-login">
				    <img src="images/d15.jpg" width="67" height="28" border="0" /></a>
				    </td>
				  </tr>
				</table>
				</div>
	    	</c:otherwise>
    	</c:choose>
    </div>
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

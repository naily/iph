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
    <title>警告</title>
	<!-- -->
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css">
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			function showWarning(){
	            $.omMessageBox.alert({
	                 type:'warning',
	                 title:'警告',
	                 content:'你无权操作该功能，请返回！',
	                 onClose:function(v){
	                 	history.go(-1) ;
	                 }
	             });
	         }
	         showWarning() ;
		})
		
	</script>
  </head>
  
  <body>
  	<jsp:include page="header.jsp" flush="true" />

    <div id="content_login" class="loginbox">
	    	<!-- 已经登录 -->
	    		<div >
					<table width="400" height="210" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="0" class="loginbox_bor">
					  <tr>
					    <td colspan="4" align="center">&nbsp;
					    <h1 style="color:#FF8C00; ">警告</h1>
					    </td>
				     </tr>
					  <tr>
					    <td colspan="4" align="center">&nbsp;&nbsp; ${sessionScope.ht_account.name} </td>
				    </tr>
					  <tr>
					    <td colspan="4" height="10"  align="center">&nbsp;你没有权限操作该功能！</td>
				    </tr>
				</table>
				</div>
	    	
    </div>
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

<%@ page language="java" import="java.util.*,cn.fam1452.dao.pojo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="../jstl.jsp" %>
<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    <title>管理注册账户</title>
	<!--
	-->
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css">
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/ht_base_layout.css">
	<link rel="stylesheet" type="text/css" href="css/default/toolbar.css">
	
	<script type="text/javascript" src="js/Global.js"></script>
	<script type="text/javascript" src="js/index_global_zn.js"></script>
	<script type="text/javascript" src="js/qtum.js"></script>
	<style type="text/css">
		label.error{
        background: #fff6bf url(images/errorIcon.png) center no-repeat;
		background-position: 5px 50%;
		text-align: left;
		padding: 2px 2px 2px 22px;
		border: 1px solid #ffd324;
		display: none;
		width: 50px;
		float : right ;
		margin-left: 4px;
       }
    </style>
    <script type="text/javascript">
    	var basepath = "<%=basePath%>" ;
    </script>
	
  </head>
  
  <body>
    <jsp:include page="header.jsp" flush="true" />
    
    <div id="center" >
    	<div class="butBottom">
    			<input id = "comboActionType" align="middle"/>
    			<input id = "comboAdmin" align="middle"/>
    			<input id="searchbut" align="middle" src="images/chaxun.png"  type="image" title="查询" />
    	</div>
    	<table id="list0"></table>
    	
    </div>
    
    <div id="qtum_update">
  		
  		<input name="optType" id="optTpyeID" type="hidden"/>
  		<table   border="0" align="center" cellpadding="0" cellspacing="0" >
	  <tr>
	    <td height="10" colspan="3" align="center">&nbsp;</td>
	    </tr>
	  <tr>
	    <td class="userregtd">${msg['qt_regist_username']}：</td>
	    <td class="userregtd2"><input id="loginId" class="boxinput3" disabled="disabled" readonly="readonly"/><!-- <span class="redcolor">*</span> --></td>
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
	    <td height="30" colspan="3" align="center">
	    	<input id="updateUserSubmit" src="images/baocun.png" type="image" title="提交" />
	    </td>
	  </tr>
	</table>
	
	</div>
    
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

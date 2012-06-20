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
    <title>电离层频高图管理</title>
	<!--
	-->
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css">
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/ht_base_layout.css">
	<script type="text/javascript" src="js/pgt.js"></script>
  </head>
  
  <body>
    <jsp:include page="header.jsp" flush="true" />
    
    <div id="imagePreview" title="图片预览"> </div> 
    <span><a href="./ht/pgtlist.do" class="a3">管理</a></span>
    <div id="center" class="loginbox1">
    	<table height="312" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="0" class="loginbox_bor2" style="width:600px;">
		  <tr>
		    <td height="10" colspan="3" align="center">&nbsp; </td>
		    </tr>
		  <tr >
		    <td width="20" align="left" height="40">&nbsp;</td>
		    <td width="119" height="40" align="left">选择频高图文件：</td>
		    <td><label>
		      <input type="file" name="file_upload" id="file_upload" />
		    </label></td>
		    </tr>
		  <tr>
		    <td align="left">&nbsp;</td>
		    <td height="40" align="left">所属观测站：</td>
		    <td><label><input id = "comboStation" class="boxinput3"/>
		    </label></td>
		    </tr>
		  <tr>
		    <td align="left">&nbsp;</td>
		    <td height="40" align="left">观测日期：</td>
		    <td><input id="actionDateId" type="text" name="textfield4" class="boxinput3" /></td>
		    </tr>
		  <tr>
		    <td align="left">&nbsp;</td>
		    <td height="42" align="left">频高图标题：</td>
		    <td><input id="pgtTitleId" type="text" name="textfield2" class="boxinput3" /></td>
		    </tr>
		  <tr>
		    <td align="left">&nbsp;</td>
		    <td height="40" align="left">频高图类型：</td>
		    <td><label><input id = "comboPgtType" class="boxinput3"/> </label>
		    </td>
		    </tr>
		  <tr>
		    <td align="left">&nbsp;</td>
		    <td colspan="2"><span id='errormsg' class="errorMessages"> </span>
		    </td>
	     </tr>
		  <tr>
		    <td height="60" colspan="3" align="center"><label>
		      <input id="reset" type="button" name="重置" value="重置" />&nbsp;&nbsp;
		      <input id="save"  type="button" name="保存" value="保存" />&nbsp;&nbsp;
			  <input id="preview" type="button" name="预览" value="预览" />
		    </label></td>
		    </tr>
		</table>
    </div>
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

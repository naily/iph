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
	<script type="text/javascript" src="js/pgtlist.js"></script>
  </head>
  
  <body>
    <jsp:include page="header.jsp" flush="true" />
    
    <div id="pageleft">
    	<!-- 左侧菜单 -->
    	<p><a href="./ht/pgt.do" class="a3">高频图录入</a></p>
    	<p>高频图管理</p>
    </div>
    
    <div id="imagePreview" title="图片预览"> </div> 
    
    <div id="center_right" >
    	<span id="toolbar" class="om-widget-header om-corner-all">
	        <a id="updatebut"  href="javascript:void(0)">修改</a>
	        <a id="deletebut"  href="javascript:void(0)">删除</a>
    	</span>
    	
    	<table id="list0"></table>
    </div>
    <div id="tab1">
        	<table height="312" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="0" class="loginbox_bor2" style="width:600px;">
			  <tr>
			    <td height="10" colspan="3" align="center">&nbsp; </td>
			    </tr>
			  <tr >
			    <td width="20" align="left" height="40">&nbsp;</td>
			    <td width="119" height="40" align="left"> 频高图文件：</td>
			    <td><label id="pgtfile"> </label></td>
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
			    <td height="60" colspan="3" align="center">
			      <input id="save"  type="button" name="保存" value="保存" style="height: 22px;" />&nbsp;&nbsp;
				 <!-- <input id="preview" type="button" name="预览" value="预览" style="height: 22px;"/>  -->
			    </td>
			  </tr>
		</table>
        </div>
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

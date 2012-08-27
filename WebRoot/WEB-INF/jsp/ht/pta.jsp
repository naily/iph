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
    <title>数据保护期管理</title>
	<!--
	-->
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css">
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/ht_base_layout.css">
	<link rel="stylesheet" type="text/css" href="css/default/toolbar.css">
	
	<script type="text/javascript" src="js/Global.js"></script>
	<script type="text/javascript" src="js/pta.js"></script>
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

	
  </head>
  
  <body>
    <jsp:include page="header.jsp" flush="true" />
    
    <div id="center" >
		<div id="buttonbar"></div>
    	<table id="list0"></table>
    	
    	<div id="createblock">
	    	<table width="400" border="0">
	    		<tr>
                <td align="right">&nbsp;选择数据表:</td>
                <td>&nbsp;<input name="dataTable" id = "comboTableName" class="boxinput3"/><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td align="right">&nbsp;选择观测站:</td>
                <td>&nbsp;<input name="dataStation" id = "comboStation" class="boxinput3"/></td>
              </tr>
              <tr>
                <td align="right">&nbsp;保护开始日期:</td>
                <td>&nbsp;<input name="dataSDate" id="sDate" type="text" class="boxinput3" /></td>
              </tr>
              <tr>
                <td align="right">&nbsp;保护结束日期:</td>
                <td>&nbsp;<input name="dataEDate" id="eDate" type="text" class="boxinput3" /></td>
              </tr>
              <tr>
                <td align="right">&nbsp;开放日期:</td>
                <td>&nbsp;<input name="publicDate" id="pubDate" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              
              <tr>
                <td align="right">&nbsp; </td>
                <td>&nbsp; <span id="errormsg" class="errorMessages"></span></td>
              </tr>
              <tr>
                <td colspan="2" align="center">&nbsp;&nbsp; 
                <input id="savebut"  type="submit" name="保存" value="保存" style="height: 22px;" /> 
                &nbsp;&nbsp;&nbsp;&nbsp; 
                <!--  
                <input id="clearbut"  type="button" name="清空" value="清空" style="height: 22px;" /> 
                -->
                </td>
              </tr>
            </table>
    	</div>
    </div>
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

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
    <title>电离层参数</title>
	<!--
	-->
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css">
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/ht_base_layout.css">
	<link rel="stylesheet" type="text/css" href="css/default/toolbar.css">
	<script type="text/javascript" src="js/pamlist.js"></script>
	
  </head>
  
  <body>
    <jsp:include page="header.jsp" flush="true" />
    
    <div id="pageleft">
    	<!-- 左侧菜单 -->
    	<p><a href="ht/pam.do" class="a3">参数录入</a></p>
    	<p>参数管理</p>
    </div>
    <div id="center_right">
    	<!-- 右侧内容
    	<span id="toolbar" class="om-widget-header om-corner-all">
	        <a id="updatebut"  href="javascript:void(0)">修改</a>
	        <a id="del"  href="javascript:void(0)">删除</a>
    	</span>
    	 -->
    	<div id="buttonbar"></div>
    	
    	<table id="list0"></table>
        
        <div id="tab1">
        	<table width="400" border="0">
              <tr>
                <td align="right">&nbsp;所属观测站:</td>
                <td>&nbsp;<input id = "comboStation" class="boxinput3"/></td>
              </tr>
              <tr>
                <td align="right">&nbsp;观测日期:</td>
                <td>&nbsp;<input id="actionDate" type="text" class="boxinput3" /></td>
              </tr>
              <tr>
                <td align="right">&nbsp;foF2:</td>
                <td>&nbsp;<input id="ip1" type="text" name="" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;h1F2:</td>
                <td>&nbsp;<input id="ip2" type="text" name="" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;foF1:</td>
                <td>&nbsp;<input id="ip3" type="text" name="" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;h1F1:</td>
                <td>&nbsp;<input id="ip4" type="text" name="" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;foF:</td>
                <td>&nbsp;<input id="ip5" type="text" name="" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;h1F:</td>
                <td>&nbsp;<input id="ip6" type="text" name="" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;foE:</td>
                <td>&nbsp;<input id="ip7" type="text" name="" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;h1E:</td>
                <td>&nbsp;<input id="ip8" type="text" name="" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;foEs:</td>
                <td>&nbsp;<input id="ip9" type="text" name="" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;h1Es:</td>
                <td>&nbsp;<input id="ip10" type="text" name="" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp; </td>
                <td>&nbsp; <span id="errormsg" class="errorMessages"></span></td>
              </tr>
              <tr>
                <td colspan="2" align="center">&nbsp;&nbsp; 
                <input id="savebut"  type="button" name="保存" value="保存" style="height: 22px;" /> 
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

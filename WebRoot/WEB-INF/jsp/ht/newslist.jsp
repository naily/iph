<%@ page language="java" import="java.util.*,cn.fam1452.dao.pojo.*" pageEncoding="UTF-8"%>
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
    <title>新闻管理</title>
	<!--
	-->
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css">
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/ht_base_layout.css">
	<link rel="stylesheet" type="text/css" href="css/default/toolbar.css">
	<script type="text/javascript" src="js/library/editor/omeditor.js"></script>
	<script type="text/javascript" src="js/newslist.js"></script>
	
  </head>
  
  <body>
    <jsp:include page="header.jsp" flush="true" />
    
  <div id="pageleft">
    	<!-- 左侧菜单 -->
    	<div class="left2_1"><a href="ht/news.do" class="a3">添加新闻</a></div>
    	<div class="left2_1">新闻管理</div>
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
        
        <div id="tab1" style="display: none;">
        	
        	<table width="790" border="0">
        	<tr>
                <td align="left">&nbsp; </td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td  class="table_td_fieldlab">&nbsp;新闻标题:</td>
                <td>&nbsp;<input id = "title" name="title" class="boxinput3"/><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td  class="table_td_fieldlab">&nbsp;推荐为图片新闻:</td>
                <td>&nbsp; <input type="checkbox" id = "isPicNews" name="isPicNews" /> </td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;新闻内容:</td>
                <td>&nbsp;<textarea id="contentId" name="content" class="editor"></textarea> <span class="red_asterisk">*</span></td>
              </tr>
              
              <tr>
                <td >&nbsp; </td>
                <td>&nbsp; <span id="errormsg" class="errorMessages"></span></td>
              </tr>
              <tr>
                <td colspan="2" align="center">&nbsp;&nbsp; 
                <input id="savebut"  src="images/baocun.png" type="image" title="保存" /> 
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

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
	<script type="text/javascript" src="js/news.js"></script>
	
	<style type="text/css">
       label.error{
        background: #fff6bf url(images/errorIcon.png) center no-repeat;
		background-position: 5px 50%;
		text-align: left;
		padding: 2px 20px 2px 25px;
		border: 1px solid #ffd324;
		display: none;
		width: 200px;
		margin-left: 10px;
       }
    </style>
	
  </head>
  
  <body>
    <jsp:include page="header.jsp" flush="true" />
    
    <div id="pageleft">
    	<!-- 左侧菜单 -->
    	<p>添加新闻</p>
    	<p><a href="ht/newslist.do" class="a3">新闻管理</a></p>
    </div>
    <div id="center_right">
    	<!-- 右侧内容
    	<span id="toolbar" class="om-widget-header om-corner-all">
	        <a id="updatebut"  href="javascript:void(0)">修改</a>
	        <a id="del"  href="javascript:void(0)">删除</a>
    	</span>
    	 -->
        
        <div id="tab1" style="height: 800px;">
        <form action="#" id="savenewsfm" method="post">
        	<table width="840" border="0" style="border:#bfd0e1 2px solid">
        	<tr>
                <td align="left">&nbsp;</td>
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
                <input id="savebut"  type="submit" value=" 保  存 " /> 
                &nbsp;&nbsp;&nbsp;&nbsp; 
                <!--  
                <input id="clearbut"  type="button" name="清空" value="清空" style="height: 22px;" /> 
                -->
                </td>
              </tr>
            </table>
            </form>
        </div>
        
    </div>
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

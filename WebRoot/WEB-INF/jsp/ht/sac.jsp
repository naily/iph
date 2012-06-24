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
    <title>报表扫描图上传</title>
	<!--
	-->
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css">
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/ht_base_layout.css">
	<link rel="stylesheet" type="text/css" href="css/default/toolbar.css">
	<script type="text/javascript" src="js/sac.js"></script>
	
  </head>
  
  <body>
    <jsp:include page="header.jsp" flush="true" />
    
    <div id="pageleft">
    	<!-- 左侧菜单 -->
    	<p>报表扫描图上传</p>
    	<p><a href="ht/pamlist.do">报表扫描图管理</a></p>
    </div>
    <div id="center_right">
    	<!-- 右侧内容 -->
    	<div id="make-tab" style="margin: auto;">
	        <ul>
	            <li><a href="#tab1">单记录录入</a></li>
	            <li><a href="#tab2">批量导入</a></li>
	        </ul>
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
                <td>&nbsp;<input id="ip1" type="text" name="textfield2" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;h1F2:</td>
                <td>&nbsp;<input id="ip2" type="text" name="textfield2" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;foF1:</td>
                <td>&nbsp;<input id="ip3" type="text" name="textfield2" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;h1F1:</td>
                <td>&nbsp;<input id="ip4" type="text" name="textfield2" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;foF:</td>
                <td>&nbsp;<input id="ip5" type="text" name="textfield2" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;h1F:</td>
                <td>&nbsp;<input id="ip6" type="text" name="textfield2" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;foE:</td>
                <td>&nbsp;<input id="ip7" type="text" name="textfield2" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;h1E:</td>
                <td>&nbsp;<input id="ip8" type="text" name="textfield2" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;foEs:</td>
                <td>&nbsp;<input id="ip9" type="text" name="textfield2" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;h1Es:</td>
                <td>&nbsp;<input id="ip10" type="text" name="textfield2" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td colspan="2" align="center">&nbsp;&nbsp; 
                <input id="savebut"  type="button" name="保存" value="保存" style="height: 22px;" /> 
                &nbsp;&nbsp;&nbsp;&nbsp; 
                <input id="clearbut"  type="button" name="清空" value="清空" style="height: 22px;" /> 
                </td>
              </tr>
            </table>
        </div>
        <!-- 导入 -->
        <div id="tab2">
        	<table height="400" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="0" style="width:600px;">
	        	<tr>
	        	<td> 
		        	<div style="height:360px;overflow-y:scroll; border: red solid 0px; ">
		        		<div style="width:100px; float:left;  margin-top: 6px;">选择文件:</div>
		        		<input type="file" name="file_upload" id="file_upload_more" /> 
		        	</div>
	        	</td>
	        	</tr>
	        	<tr>
		        	<td align="center">
		        		<span id="errormsg2" class="errorMessages"></span>
		        	</td>
	        	</tr>
	        	<tr>
		        	<td align="center">
		        		<input id="save_more"  type="button" name="保存" value="保存" style="height: 22px;" /> 
		        	</td>
	        	</tr>
        	</table>
        </div>
        
        </div>
        
    </div>
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

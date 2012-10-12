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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
    	<div class="left2_1">报表扫描图上传</div>
    	<div class="left2_1"><a href="ht/saclist.do" class="a3">报表扫描图管理</a></div>
    </div>
    <div id="center_right">
    	<!-- 右侧内容 -->
    	<div id="make-tab" style="margin: auto;">
	        <ul>
	            <li><a href="#tab1">单记录录入</a></li>
	            <li><a href="#tab2">批量导入</a></li>
	        </ul>
	        <div id="tab1" class="hidediv">
        	<table width="400" border="0">
        	<tr>
                <td align="right">&nbsp;选择文件:</td>
                <td>&nbsp;<input type="file" name="file_upload" id="file_upload" /></td>
              </tr>
              <tr>
                <td align="right">&nbsp;所属观测站:</td>
                <td>&nbsp;<input id = "comboStation" class="boxinput3"/></td>
              </tr>
              <tr>
                <td align="right">&nbsp;观测日期:</td>
                <td>&nbsp;<input id="actionDate" type="text" class="boxinput3" /></td>
              </tr>
              <tr>
                <td align="right">&nbsp;标题:</td>
                <td>&nbsp;<input id="sacTitleId" type="text" name="textfield2" class="boxinput3" /></td>
              </tr>
              <tr>
			    <td align="left">&nbsp;</td>
			    <td colspan="2"><span id='errormsg' class="errorMessages"> </span>
			    </td>
		     </tr>
              <tr>
                <td colspan="2" align="center">&nbsp;&nbsp; 
                <input id="savebut"  type="image" src="images/baocun.png" name="保存" value="保存"  /> &nbsp;&nbsp;&nbsp;&nbsp; 
                <input id="clearbut"  type="image" src="images/qingkong.png" name="清空" value="清空"   /> &nbsp;&nbsp;&nbsp;&nbsp; 
                <input id="preview" type="image" src="images/yulan.png" name="预览" value="预览"  />
                </td>
              </tr>
            </table>
            <div id="imagePreview" title="图片预览"> </div> 
        </div>
        <!-- 导入 -->
        <div id="tab2" class="hidediv">
        	<table height="350" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="0" style="width:600px;">
	        	<tr>
		        	<td align="center">&nbsp;<span class="fontgreen">温馨提示: 单次上传文件请不要超过20个，选择太多文件可能导致长时间无响应或浏览器崩溃！
		        		<br/>上传文件的速度，取决于你电脑配置及运行速度！</span>
		        	</td>
	        	</tr>
	        	<tr>
	        	<td> 
		        	<div style="height:260px;overflow-y:scroll; border: red solid 0px; ">
		        		<div style="width:100px; float:left;  margin-top: 6px;">选择文件:</div>
		        		<input type="file" name="file_upload" id="file_upload_more" /> 
		        	</div>
	        	</td>
	        	</tr>
	        	<tr>
		        	<td align="center">
		        		<div id="errorfilename" style="display: none;"></div>
		        		<div id="msgtable" class="hidediv">
		        			<table style="BORDER-COLLAPSE: collapse" borderColor=#000000 height=40 cellPadding=1 width=200 align=center border=1 >
		        				<tr><td align="right">提交文件总数</td><td width="30%"><span id="ce1"></span></td></tr>
		        				<tr><td align="right">成功</td><td width="30%">       <span id="ce2"></span></td></tr>
		        				<tr><td align="right">失败</td><td width="30%">       <span id="ce3"></span></td></tr>
		        				<tr><td align="center" bgcolor="red" colspan="2">失败原因：</td></tr>
		        				<tr><td align="right">文件已存在</td><td width="30%">  <span id="ce4"></span></td></tr>
		        				<tr><td align="right">文件名无法解析</td><td width="30%"><span id="ce5"></span></td></tr>
		        				<tr><td align="right">程序异常</td><td width="30%">     <span id="ce6"></span></td></tr>
		        			</table>
		        		</div>
		        	</td>
	        	</tr>
	        	<tr>
		        	<td align="center">&nbsp;</td>
	        	</tr>
        	</table>
        </div>
        
        </div>
        
    </div>
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

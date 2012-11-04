<%@ page language="java" import="java.util.*,cn.fam1452.dao.pojo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="../jstl.jsp" %>
<!DOCTYPE HTML >
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>">
    <title>电离层频高图管理</title>
	<!--
	-->
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css">
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/ht_base_layout.css">
	<link rel="stylesheet" type="text/css" href="css/default/toolbar.css">
	<script type="text/javascript" src="js/pgt.js"></script>
  </head>
  
  <body>
  	<!--  
  	<iframe frameborder=0 src="../ht/loadhd.do" width="100%" height="100%"></iframe> 
  	-->
    <jsp:include page="header.jsp" flush="true" />
    
    <div id="pageleft" >
    	<!-- 左侧菜单 -->
    	<div class="left2_1">频高图录入</div>
    	<div class="left2_1"><a href="./ht/pgtlist.do" class="a3">频高图管理</a></div>
    </div>
    
    <div id="imagePreview" title="图片预览" class="hidediv"> </div>
    
    <div id="center_right">
    	<div id="make-tab" style="margin: auto;" >
        <ul>
            <li><a href="#tab1">单个录入</a></li>
            <li><a href="#tab2">批量导入</a></li>
        </ul>
        <div id="tab2" class="hidediv">
        	<table height="350" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="0" style="width:600px;">
	        	<tr>
		        	<td align="center">&nbsp;
		        	<!-- <span class="fontgreen">温馨提示: 单次上传文件请不要超过20个，选择太多文件可能导致长时间无响应或浏览器崩溃！
		        	<br/>上传文件的速度，取决于你电脑配置及运行速度！</span>  -->
		        	</td>
	        	</tr>
	        	<tr>
	        	<td> 
		        	<div style="height:260px;overflow-y:scroll; border: red solid 0px; ">
		        		<div style="width:100px; float:left;  margin-top: 10px;">选择频高图:</div>
		        		<input type="file" name="file_upload_more" id="file_upload_more" /> 
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
        
        <div id="tab1" class="hidediv">
        	<table height="312" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="0" style="width:600px;">
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
			    <td><label><input id = "comboStation" class="boxinput3"/><span class="red_asterisk">*</span>
			    </label></td>
			    </tr>
			  <tr>
			    <td align="left">&nbsp;</td>
			    <td height="40" align="left">观测日期：</td>
			    <td><input id="actionDateId" type="text" name="textfield4" class="boxinput3" /><span class="red_asterisk">*</span></td>
			    </tr>
			  <tr>
			    <td align="left">&nbsp;</td>
			    <td height="42" align="left">频高图标题：</td>
			    <td><input id="pgtTitleId" type="text" name="textfield2" class="boxinput3" /></td>
			    </tr>
			  <tr>
			    <td align="left">&nbsp;</td>
			    <td height="40" align="left">频高图类型：</td>
			    <td><label><input id = "comboPgtType" class="boxinput3"/><span class="red_asterisk">*</span> </label>
			    </td>
			    </tr>
			  <tr>
			    <td align="left">&nbsp;</td>
			    <td colspan="2"><span id='errormsg' class="errorMessages"> </span>
			    </td>
		     </tr>
			  <tr>
			    <td height="60" colspan="3" align="center">
			      <input id="reset" type="image" name="重置" src="images/qingkong.png" value="重置" style="height: 22px;" />&nbsp;&nbsp;
			      <input id="save"  type="image" src="images/baocun.png" name="保存" value="保存" style="height: 22px;" />&nbsp;&nbsp;
				  <input id="preview" type="image" src="images/yulan.png" name="预览" value="预览" style="height: 22px;"/>
			    </td>
			  </tr>
		</table>
        </div>
        
        
       </div>
    	
    	
    	
    </div>
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

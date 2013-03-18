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
    <title>相关文档上传</title>
	<!--
	-->
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css">
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/ht_base_layout.css">
	<link rel="stylesheet" type="text/css" href="css/default/toolbar.css">
	<script type="text/javascript" src="js/library/editor/omeditor.js"></script>
	<script type="text/javascript" src="js/news.js">
	$(document).ready(function(){
		//初始化单文件上传文件组件
	$('#file_upload').omFileUpload({
        action : '../ht/pgtuploads.do'+ "?timestamp=" + new Date().getTime() ,
        swf : 'swf/om-fileupload.swf' + "?timestamp=" + new Date().getTime() ,
	  	fileExt  : '*.jpg;*.bmp',
	  	fileDesc : 'Image Files(*.jpg,*.bmp)' ,
	  	method   : 'POST',
        onComplete : function(ID,fileObj,response,data,event){
        	var jsonData = eval("("+response+")");
        	
        	fileName = fileObj.name ;
            if(fileName && fileName.length >= 10){
                
            }
        },
        onError :function(ID, fileObj, errorObj, event){
        	alert('文件'+fileObj.name+'上传失败。错误类型：'+errorObj.type+'。原因：'+errorObj.info);
        },
        onSelect:function(ID,fileObj,event){
        	//alert('你选择了文件：'+fileObj.name);
            //选择文件后立即上传
        	$('#preview').attr('disabled' , false) ;
        	$('#errormsg').html('') ;
        },
        //actionData : { 'action' :'fileupload' } ,
        removeCompleted : true ,
        autoUpload : true  //自动上传
    });
	});
	</script>
	
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
    	<div class="left2_1"><a href="ht/news.do" class="a3">添加新闻</a></div>
    	<div class="left2_1"><a href="ht/newslist.do" class="a3">新闻管理</a></div>
    	<div class="left2_1">相关文档上传</div>
    	<div class="left2_1"><a href="ht/doclist.do" class="a3">相关文档管理</a></div>
    </div>
    <div id="center_right">
        
        <div id="tab1">
        <table height="312" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="0" style="width:600px;">
			  <tr>
			    <td height="10" colspan="3" align="left">
			    <%@ include file="./info/singleBatchInfo.jsp" %></td>
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
			    <td height="42" align="left">频高图标题：</td>
			    <td><input id="pgtTitleId" type="text" name="textfield2" class="boxinput3" /></td>
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
			    </td>
			  </tr>
		</table>
        </div>
        
    </div>
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

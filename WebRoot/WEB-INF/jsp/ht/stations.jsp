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
    <title>观测站管理</title>
	<!--
	-->
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css">
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/ht_base_layout.css">
	<link rel="stylesheet" type="text/css" href="css/default/toolbar.css">
	
	<script type="text/javascript" src="js/Global.js"></script>
	<script type="text/javascript">
		var basepath = '<%=basePath%>' ;
	</script>
	<script type="text/javascript" src="js/stations.js"></script>
	
	
  </head>
  
  <body>
    <jsp:include page="header.jsp" flush="true" />
    
    <div id="center" >
    	<div id="buttonbar"></div>
    	
    	<div id="viewdetail"></div>
    	<table id="list0"></table>
    	
    	<div id="createblock" class="hidediv">
    		<table width="520" border="0" style="float: left;">
              <tr>
                <td align="right" width="19%">&nbsp;观测站编码:</td>
                <td >&nbsp;<input id="stId" size="20"/><span class="red_asterisk">*</span></td>
                <td align="center" colspan="2">&nbsp;提示：标有<span class="red_asterisk">*</span>的项为必填项</td>
              </tr>
              <tr>
              	<td align="right">&nbsp;地理位置描述:</td>
                <td colspan="3">&nbsp;<input id="wzId"  size="59"/><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
              	<td align="right">&nbsp;观测站中文名称:</td>
                <td colspan="3">&nbsp;<input id="mcId" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
              	<td align="right">&nbsp;英文名称:</td>
                <td>&nbsp;<input id="mcengId" /><span class="red_asterisk">*</span></td>
                <td align="right">&nbsp;单位:</td>
                <td>&nbsp;<input id="dwmcId" /> </td>
              </tr>
              <tr>
                <td align="right">&nbsp;经度:</td>
                <td>&nbsp;<input id="jdId" /><span class="red_asterisk">*</span></td>
                <td align="right">&nbsp;地址:</td>
                <td>&nbsp;<input id="txdzId" /></td>
              </tr>
              <tr>
                <td align="right">&nbsp;纬度:</td>
                <td>&nbsp;<input id="wdId" /><span class="red_asterisk">*</span></td>
                <td align="right">&nbsp;电话:</td>
                <td>&nbsp;<input id="lxdhId" /></td>
              </tr>
              <tr>
                <td align="right">&nbsp;时区:</td>
                <td>&nbsp;<input id="sqId" /><span class="red_asterisk">*</span></td>
                <td align="right">&nbsp;Email:</td>
                <td>&nbsp;<input id="emId" /></td>
              </tr>
              <tr>
                <td align="right">&nbsp;主页:</td>
                <td>&nbsp;<input id="zyId" /></td>
                <td align="right">&nbsp;邮编:</td>
                <td>&nbsp;<input id="ybId" /></td>
              </tr>
              <!--  
              <tr>
                <td align="right">&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              -->
              <tr>
              	<td align="right">&nbsp;简要介绍:</td>
                <td colspan="3">&nbsp;<textarea id="jsId" rows="3" cols="48"></textarea></td>
              </tr>
              <tr >
              	<td align="right">&nbsp;图片:</td>
                <td colspan="2">&nbsp;
                	<input id = "uploadpic" />
             		<input id ="picPath" name="picPath" type="hidden" />
                </td>
                <td align="left" >
                <div id="minuploadpicdiv" style="overflow:hidden;width:50px; height:80px; ">
                <img src="/images/s.gif" border="0" id="minuploadpic" width="50"/> </div></td>
              </tr>
              <tr>
              	<td align="right">&nbsp;<!-- 错误信息提示 --></td>
                <td colspan="3"><div id="info" class="errorMessages">&nbsp;</div></td>
              </tr>
              <tr>
              	<td >&nbsp;</td>
                <td align="center">&nbsp;<input type="image" onclick="save.savedata();"  src = "images/baocun.png" /></td>
                <td align="center">&nbsp;<input type="image" onclick="save.clear();" src="images/qingkong.png" /></td>
                <td>&nbsp;</td>
           </table>
    	</div>
    </div>
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

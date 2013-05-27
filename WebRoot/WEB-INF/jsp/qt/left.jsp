<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>   
  	<meta http-equiv="X-UA-Compatible" content="IE=8">
    <title></title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
    	var basepath = "<%=basePath%>" ;
    </script>
	<script type="text/javascript" src="js/index_left.js"></script>
	 <style type="text/css">
    .hrefTiele{
	 white-space: nowrap;
	 width:16px; 
	 overflow: hidden; 
	 text-overflow: ellipsis;
   }
 
 /* IE下的样式 */ 
.hrefTiele{
display: block;
width:160px;/*对宽度的定义,根据情况修改*/
overflow: hidden;
white-space: nowrap;
text-overflow: ellipsis;
}


 
</style>
  </head>
  
  <body>
   <div  id="left">
<div class="title1">${msg['qt_data_navigation']}</div>
<div class="title2">${msg['qt_Ionospheric_ionogram']}</div>
  <div id="leftTree1"  style="margin_top:0px;width:185px; height:120px; overflow:auto">
   <ul id="pgtTree"></ul>     
</div> 
<div class="title2">${msg['qt_Ionospheric_parameters']}</div>
<div id="leftTree2"  style="width:185px; height:120px; overflow:auto">
   <ul id="paraTree"></ul>     
</div> 
<div class="title2">${msg['qt_Report_scan']}</div>
<div id="leftTree32"  style="width:185px; height:120px; overflow:auto">
   <ul id="scanpicTree"></ul>     
</div> 
<div class="title3">${msg['qt_links']}</div>
<table width="170" border="0" align="center" cellpadding="0" cellspacing="0">
  <c:choose>
	 <c:when test='${msg.lang=="zh"}'> 
	 	<tr>
		    <td height="30" ><a href="http://www.igg.cas.cn" title="${msg['index_href_zkydzwls']}" target="_blank" class="a2"><span class="hrefTiele">中国科学院地质与地球物理研究所</span></a></td>
		  </tr>
		  <tr>
		    <td height="30" ><a href="http://www.igsnrr.ac.cn/" title="${msg['index_href_zkydls']}" target="_blank" class="a2"><span class="hrefTiele">中国科学院地理科学与资源研究所</span></a></td>
		  </tr>
		  <tr>
		    <td height="30" ><a href="http://www.cas.cn/" title="${msg['index_href_zgkxy']}" target="_blank" class="a2"><span class="hrefTiele">中国科学院</span></a></td>
		  </tr>
		  <tr>
		    <td height="30" ><a href="http://www.most.gov.cn/" title="${msg['index_href_zhkjb']}" target="_blank" class="a2"><span class="hrefTiele">中华人民共和国科学技术部</span></a></td>
		  </tr>
		  <tr>
		    <td height="30" ><a href="http://space.iggcas.ac.cn/" title="${msg['index_href_kjgcl']}" target="_blank" class="a2"><span class="hrefTiele">空间观测链</span></a></td>
		  </tr>
		  <tr>
		    <td height="30" ><a href="http://www.geodata.cn/" title="${msg['index_href_dqkxsjgxpt']}" target="_blank" class="a2"><span class="hrefTiele">地球系统科学数据共享平台</span></a></td>
		  </tr>
	</c:when>
	<c:when test='${msg.lang=="en"}'> 
		<tr>
		    <td height="30" ><a href="http://madrigal.iggcas.ac.cn/madrigal" target="_blank" class="a2"><span class="hrefTiele">Madrigal Database at IGGCAS</span></a></td>
		  </tr>
		  <tr>
		    <td height="30" ><a href="http://wdc.nict.go.jp/IONO/HP2009/ISDJ/index-E.html"  target="_blank" class="a2"><span class="hrefTiele">Ionosonde Data in JAPAN</span></a></td>
		  </tr>
		  <tr>
		    <td height="30" ><a href="http://giro.uml.edu/"  target="_blank" class="a2"><span class="hrefTiele">Global Ionospheric Radio Observatory</span></a></td>
		  </tr>
		  <tr>
		    <td height="30" ><a href="http://spidr.ngdc.noaa.gov/spidr/"  target="_blank" class="a2"><span class="hrefTiele">Space Physics Interactive Data Resource</span></a></td>
		  </tr>
		  <tr>
		    <td height="30" ><a href="http://www.ips.gov.au/"   target="_blank" class="a2"><span class="hrefTiele">IPS - Radio and Space Services</span></a></td>
		  </tr>
		  <tr>
		    <td height="30" ><a href="http://www.swpc.noaa.gov/today.html"  target="_blank" class="a2"><span class="hrefTiele">Today's Space Weather</span></a></td>
		  </tr>
	</c:when>
  </c:choose>
  
</table>
</div>
  </body>
</html>

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
    <title>元数据录入</title>
	<!--
	-->
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css">
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/ht_base_layout.css">
	<link rel="stylesheet" type="text/css" href="css/default/toolbar.css">
	<script type="text/javascript" src="js/med.js"></script>
	
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
    	<p>元数据录入</p>
    	<p><a href="ht/medlist.do" class="a3">元数据管理</a></p>
    </div>
    <div id="center_right">
    	<!-- 右侧内容
    	<span id="toolbar" class="om-widget-header om-corner-all">
	        <a id="updatebut"  href="javascript:void(0)">修改</a>
	        <a id="del"  href="javascript:void(0)">删除</a>
    	</span>
    	 -->
        
        <div id="tab1" style="height: 800px;">
        <form action="#" id="savemetadata" method="post">
        	<table width="680" border="0">
        	<tr>
                <td align="left">&nbsp;提示：标有*的项为必填项</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td  class="table_td_fieldlab">&nbsp;数据集名称:</td>
                <td>&nbsp;<input id = "name" name="name" class="boxinput3"/><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;关键词:</td>
                <td>&nbsp;<input id="keys" name="keys" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据集摘要:</td>
                <td>&nbsp;<textarea id="zhaiyao" name="zhaiyao" rows="4" cols="40"></textarea> <span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据集语种:</td>
                <td>&nbsp;<input id="yuzhongCombox" type="text" name="yuzhong" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据集字符:</td>
                <td>&nbsp;<input id="zifuCombox" type="text" name="zifu" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;目的:</td>
                <td>&nbsp;<textarea id="mude" name="mude" rows="4" cols="40"></textarea><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;表示方法:</td>
                <td>&nbsp;<input id="fangfaCombox" type="text" name="fangfa" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td align="left">&nbsp;数据集格式信息</td>
                <td>&nbsp; </td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据集格式名称:</td>
                <td>&nbsp;<input id="ip7" type="text" name="geshiname" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据集格式版本:</td>
                <td>&nbsp;<input id="ip8" type="text" name="geshiversion" class="boxinput3" /> </td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;安全限制分级:</td>
                <td>&nbsp;<input id="anquanCombox" type="text" name="anquan" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td align="left">&nbsp;数据集负责方</td>
                <td>&nbsp; </td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;联系人姓名:</td>
                <td>&nbsp;<input id="ip10" type="text" name="contacts" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;联系人单位:</td>
                <td>&nbsp;<input id="ip11" type="text" name="employer" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;电话:</td>
                <td>&nbsp;<input id="ip12" type="text" name="phone" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;传真:</td>
                <td>&nbsp;<input id="ip13" type="text" name="fax" class="boxinput3" /> </td>
              </tr>
              
              <tr>
                <td >&nbsp; </td>
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
            </form>
        </div>
        
    </div>
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

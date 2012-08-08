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
	<script type="text/javascript" src="js/pam.js"></script>
	
  </head>
  
  <body>
    <jsp:include page="header.jsp" flush="true" />
    
    <div id="pageleft">
    	<!-- 左侧菜单 -->
    	<p>参数录入</p>
    	<p><a href="ht/pamlist.do" class="a3">参数管理</a></p>
    </div>
    <div id="center_right">
    	<!-- 右侧内容 -->
    	<div id="make-tab" style="margin: auto;">
	        <ul>
	            <li><a href="#tab1">单记录录入</a></li>
	            <li><a href="#tab2">批量导入</a></li>
	        </ul>
	        <div id="tab1">
        	<table width="500" border="0">
              <tr>
                <td align="right">&nbsp;所属观测站:</td>
                <td>&nbsp;<input id = "comboStation" class="boxinput3"/><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td align="right">&nbsp;观测日期:</td>
                <td>&nbsp;<input id="actionDate" type="text" class="boxinput3" />
                <span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td align="right">&nbsp;F2层临界频率(foF2):</td>
                <td>&nbsp;<input id="ip1" type="text" name="foF2" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;F2层最低虚高(hlF2):</td>
                <td>&nbsp;<input id="ip2" type="text" name="hlF2" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;F1层临界频率(foF1):</td>
                <td>&nbsp;<input id="ip3" type="text" name="foF1" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;F1层最低虚高(hlF1):</td>
                <td>&nbsp;<input id="ip4" type="text" name="hlF1" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;F层最低虚高(hlF):</td>
                <td>&nbsp;<input id="ip5" type="text" name="hlF" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;F层的真实高度估计(hpF):</td>
                <td>&nbsp;<input id="ip6" type="text" name="hpF" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;E区临界频率(foE):</td>
                <td>&nbsp;<input id="ip7" type="text" name="foE" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;E层最低虚高(hlE):</td>
                <td>&nbsp;<input id="ip8" type="text" name="hlE" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;Es层寻常波的最高频率(foEs):</td>
                <td>&nbsp;<input id="ip9" type="text" name="foEs" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;Es层最低虚高(hlEs):</td>
                <td>&nbsp;<input id="ip10" type="text" name="hlEs" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;Es层遮蔽频率(fbEs):</td>
                <td>&nbsp;<input id="ip101" type="text" name="fbEs" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;记录到的最低频率(Fmin):</td>
                <td>&nbsp;<input id="ip102" type="text" name="Fmin" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;F2层3000公里传输因子(M3000F2):</td>
                <td>&nbsp;<input id="ip11" type="text" name="M3000F2" class="boxinput3" /> </td>
              </tr>
              <tr>
                <td align="right">&nbsp;F2层1500公里传输因子(M1500F2):</td>
                <td>&nbsp;<input id="ip111" type="text" name="M1500F2" class="boxinput3" /> </td>
              </tr>
              <tr>
                <td align="right">&nbsp;F1层3000公里传输因子(M3000F1):</td>
                <td>&nbsp;<input id="ip112" type="text" name="M3000F1" class="boxinput3" /> </td>
              </tr>
              <tr>
                <td align="right">&nbsp;F层3000公里传输因子(M3000F):</td>
                <td>&nbsp;<input id="ip113" type="text" name="M3000F" class="boxinput3" /> </td>
              </tr>
              <tr>
                <td align="right">&nbsp; </td>
                <td>&nbsp; <span id="errormsg" class="errorMessages"></span></td>
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
		        		<div style="width:100px; float:left;  margin-top: 6px;">选择数据文件:</div>
		        		<input type="file" name="file_upload_access" id="file_upload_access" /> 
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

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
    <title>电离层参数</title>
	<!--
	-->
	<link rel="stylesheet" type="text/css" href="css/default/om-default.css">
	<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>
	<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
	<script type="text/javascript" src="js/library/jquery.progressbar.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/ht_base_layout.css">
	<link rel="stylesheet" type="text/css" href="css/default/toolbar.css">
	<script type="text/javascript" src="js/pam.js"></script>
	
  </head>
  
  <body>
    <jsp:include page="header.jsp" flush="true" />
    
    <div id="pageleft">
    	<!-- 左侧菜单 -->
    	<div class="left2_1">参数录入</div>
    	<div class="left2_1"><a href="ht/pamlist.do" class="a3">参数管理</a></div>
    </div>
    <div id="center_right">
    	<!-- 右侧内容 -->
    	<div id="make-tab" style="margin: auto;">
	        <ul>
	            <li><a href="#tab1">单记录录入</a></li>
	            <li><a href="#tab2">批量导入</a></li>
	        </ul>
	        <div id="tab1" class="hidediv">
	        
        	<table width="520" border="0" style="float: left;">
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
              <!--  
              <tr>
                <td align="right">&nbsp;fxF2:</td>
                <td>&nbsp;<input id="ip1.1" type="text" name="fxF2" class="boxinput3" /></td>
              </tr>
              
              <tr>
                <td align="right">&nbsp;fxl:</td>
                <td>&nbsp;<input id="ip1.2" type="text" name="fxl" class="boxinput3" /></td>
              </tr>
              <tr>
                <td align="right">&nbsp;hpF2:</td>
                <td>&nbsp;<input id="ip2.1" type="text" name="hpF2" class="boxinput3" /></td>
              </tr>
              -->
              <tr>
                <td align="right">&nbsp;F2层最低虚高(h'F2):</td>
                <td>&nbsp;<input id="ip2" type="text" name="hlF2" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;F1层临界频率(foF1):</td>
                <td>&nbsp;<input id="ip3" type="text" name="foF1" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;F层最低虚高(h'F):</td>
                <td>&nbsp;<input id="ip5" type="text" name="hlF" class="boxinput3" />(公里)</td>
              </tr>
              <!-- 
              <tr>
                <td align="right">&nbsp;F1层最低虚高(h'F1):</td>
                <td>&nbsp;<input id="ip4" type="text" name="hlF1" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;F层的真实高度估计(hpF):</td>
                <td>&nbsp;<input id="ip6" type="text" name="hpF" class="boxinput3" />(公里)</td>
              </tr>
              -->
              <tr>
                <td align="right">&nbsp;E区临界频率(foE):</td>
                <td>&nbsp;<input id="ip7" type="text" name="foE" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;E层最低虚高(h'E):</td>
                <td>&nbsp;<input id="ip8" type="text" name="hlE" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;Es层寻常波的最高频率(foEs):</td>
                <td>&nbsp;<input id="ip9" type="text" name="foEs" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;Es层最低虚高(h'Es):</td>
                <td>&nbsp;<input id="ip10" type="text" name="hlEs" class="boxinput3" />(公里)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;Es层遮蔽频率(fbEs):</td>
                <td>&nbsp;<input id="ip101" type="text" name="fbEs" class="boxinput3" />(兆周/秒)</td>
              </tr>
              <tr>
                <td align="right">&nbsp;Es-type:</td>
                <td>&nbsp;<input id="ip101.1" type="text" name="Es" class="boxinput3" /></td>
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
                <td align="right">&nbsp;F1层3000公里传输因子(M3000F1):</td>
                <td>&nbsp;<input id="ip112" type="text" name="M3000F1" class="boxinput3" /> </td>
              </tr>
              <!--
              <tr>
                <td align="right">&nbsp;F2层1500公里传输因子(M1500F2):</td>
                <td>&nbsp;<input id="ip111" type="text" name="M1500F2" class="boxinput3" /> </td>
              </tr>
              <tr>
                <td align="right">&nbsp;F层3000公里传输因子(M3000F):</td>
                <td>&nbsp;<input id="ip113" type="text" name="M3000F" class="boxinput3" /> </td>
              </tr>
                
              <tr>
                <td align="right">&nbsp;MUF3000F1:</td>
                <td>&nbsp;<input id="ip114" type="text" name="MUF3000F1" class="boxinput3" /> </td>
              </tr>
              <tr>
                <td align="right">&nbsp;MUF3000F2:</td>
                <td>&nbsp;<input id="ip115" type="text" name="MUF3000F2" class="boxinput3" /> </td>
              </tr>
              -->
              <tr>
                <td align="right">&nbsp; </td>
                <td>&nbsp; <span id="errormsg" class="errorMessages"></span></td>
              </tr>
              <tr>
                <td colspan="2" align="center">&nbsp;&nbsp; 
                <input id="savebut"  type="image" src="images/baocun.png" name="保存" value="保存" style="height: 22px;" /> 
                &nbsp;&nbsp;&nbsp;&nbsp; 
                <input id="clearbut"  type="image" src="images/qingkong.png" name="清空" value="清空" style="height: 22px;" /> 
                </td>
              </tr>
            </table>
            <div id="fieldsInfo" style="float:right;width:280px;">
            	<p>f(min)--Minimum frequeney at which echo is observed</p>
				<p>A--Blanketing by Es</p>
				<p>B -- Absorption</p>
				<p>C--Equipment failure</p>
				<p>D--foF2 higher than the upper frequency limit of the recorder</p>
				<p>E--foF1 less than the lower frequency limit of the recorder</p>
				<p>F--Spread echoes (scattered reflections)present</p>
				<p>G--foF2 less than foF1</p>
				<p>H--Stratification within the layer</p>
				<p>J--foF2 derived from fxF2 by the relationship fo - fx -fh/2</p>
				<p>K--Ionospheric storm</p>
				<p>L--Inflection of the curve insufficient for scaling foF1</p>
				<p>M--No observation</p>
				<p>N--Uable to make logical interpretation</p>
				<p>P--Trace extrapolated to a foF2</p>
				<p>Q--F1 layer not present as a disinct layer</p>
				<p>R--Curve becomes incoherent near the foF2</p>
				<p>S--Interference</p>
				<p>V--Forked record</p>
				<p>Z--Triple split near foF2</p>
				<p>()--Doubtful value</p>
				<p>[]--Interpolated value from diurnal curves</p>
				<p>P(foEs)--Percentage of foEs greater than a particular value</p>
			</div>
        </div>
        <!-- 导入 -->
        <div id="tab2" class="hidediv">
        	<table height="350" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="0" style="width:600px;">
	        	<tr><td align="right" colspan="2">&nbsp;</td> </tr>
	          <tr>
                <td align="right">&nbsp;观测站:</td>
                <td>&nbsp;<input id="mdbStation" type="text" name="st" class="boxinput3" /> </td>
              </tr>
              <tr>
                <td align="right">&nbsp;mdb数据表名:</td>
                <td>&nbsp;<input id="mdbtn" type="text" name="M3000F" class="boxinput3" /> <span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td align="right">&nbsp;mdb数据日期字段名:</td>
                <td>&nbsp;<input id="datefield" type="text" name="M3000F" class="boxinput3" /> <span class="red_asterisk">*</span></td>
              </tr>
              <tr><td align="right" colspan="2">&nbsp;</td> </tr>
              <tr>
                <td align="right" valign="top">&nbsp;<br>选择数据文件:</td>
	        	<td valign="top"> 
		        	<div style="height:100px;overflow-y:scroll; border: red solid 0px; ">
		        		<input type="file" name="file_upload_access" id="file_upload_access" />
		        		<input type="hidden" id="mdbpath" name="mdbpath"/>
		        	</div>
	        	</td>
	        	</tr>
	        	<tr>
		        	<td align="center" colspan="2">
		        		<span id="errormsg2" class="errorMessages"></span>
		        	</td>
	        	</tr>
	        	<tr>
		        	<td align="center" colspan="2">
		        		<div id="procbar"></div>
		        		<span id="errormsg3" ></span>
		        	</td>
	        	</tr>
	        	<tr>
		        	<td align="center" colspan="2">
		        		<input id="saveMdbFile"  src="images/baocun.png" type="image" title="保存" /> 
		        	</td>
	        	</tr>
        	</table>
        </div>
        
        </div>
        
    </div>
    
    <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>

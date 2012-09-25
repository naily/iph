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
    	<div class="left2_1">元数据录入</div>
    	<div class="left2_1"><a href="ht/medlist.do" class="a3">元数据管理</a></div>
    </div>
    <div id="center_right" >
    	<!-- 右侧内容
    	<span id="toolbar" class="om-widget-header om-corner-all">
	        <a id="updatebut"  href="javascript:void(0)">修改</a>
	        <a id="del"  href="javascript:void(0)">删除</a>
    	</span>
    	 -->
        
        <div id="tab1" style="height: auto;">
        <form action="#" id="savemetadata" method="post">
        	<table width="680" border="0">
        	<tr>
                <td align="left">&nbsp;提示：标有*的项为必填项</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td  class="table_td_fieldlab">&nbsp;上传缩略图:</td>
                <td>&nbsp;<input id = "uploadpic" />
                		  <input id ="thumbnailFilePath" name="thumbnailFilePath" type="hidden" />
                </td>
              </tr>
              <tr>
                <td align="left">&nbsp;标识信息</td>
                <td>&nbsp; </td>
              </tr>
              <tr>
                <td  class="table_td_fieldlab">&nbsp;数据集标识:</td>
                <td>&nbsp;<input id = "datId" name="datId" class="boxinput3"/><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td  class="table_td_fieldlab">&nbsp;数据集名称:</td>
                <td>&nbsp;<input id = "resTitle" name="resTitle" class="boxinput3"/><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td  class="table_td_fieldlab">&nbsp;数据集英文名称:</td>
                <td>&nbsp;<input id = "engTitle" name="engTitle" class="boxinput3"/><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;关键词:</td>
                <td>&nbsp;<input id="keyword" name="keyword" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据集语种:</td>
                <td>&nbsp;<input id="yuzhongCombox" type="text" name="language" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td align="left">&nbsp;内容信息</td>
                <td>&nbsp; </td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据集摘要:</td>
                <td>&nbsp;<textarea id="abstract1" name="abstract1" rows="4" cols="40"></textarea> <span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据集分类名称:</td>
                <td>&nbsp;<input id="cateName" name="cateName" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据集分类类目编码:</td>
                <td>&nbsp;<input id="cateCode" name="cateCode" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据集分类标准:</td>
                <td>&nbsp;<input id="cateStd" name="cateStd" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;左下角坐标:</td>
                <td>&nbsp;<input id="lftBtmCoord" name="lftBtmCoord" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;最大经度:</td>
                <td>&nbsp;<input id="rightTopCoord" name="rightTopCoord" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              
              <tr>
                <td class="table_td_fieldlab">&nbsp;坐标系统:</td>
                <td>&nbsp;<input id="corSys" name="corSys" type="text" class="boxinput3" /></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;投影系统:</td>
                <td>&nbsp;<input id="proSys" name="proSys" type="text" class="boxinput3" /></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;等效比例尺分母:</td>
                <td>&nbsp;<input id="equScale" name="equScale" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;栅格分辨率:</td>
                <td>&nbsp;<input id="gridSize" name="gridSize" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;位置描述:</td>
                <td>&nbsp;<input id="posDes" name="posDes" type="text" class="boxinput3" /></td>
              </tr>
              
              <tr>
                <td class="table_td_fieldlab">&nbsp;垂向最大值:</td>
                <td>&nbsp;<input id="topVer" name="topVer" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;垂向最小值:</td>
                <td>&nbsp;<input id="lowVer" name="lowVer" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;海拨高度位置:</td>
                <td>&nbsp;<input id="altPoint" name="altPoint" type="text" class="boxinput3" /></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;海拨高度描述:</td>
                <td>&nbsp;<input id="altDesc" name="altDesc" type="text" class="boxinput3" /></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;高程系统:</td>
                <td>&nbsp;<input id="atiSys" name="atiSys" type="text" class="boxinput3" /></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;垂向采样间隔:</td>
                <td>&nbsp;<input id="atiInterval" name="atiInterval" type="text" class="boxinput3" /></td>
              </tr>
              
              <tr>
                <td class="table_td_fieldlab">&nbsp;起始时间:</td>
                <td>&nbsp;<input id="begDate" name="begDate" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;结束时间:</td>
                <td>&nbsp;<input id="endDate" name="endDate" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;时间点:</td>
                <td>&nbsp;<input id="timePoint" name="timePoint" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;时间采样间隔:</td>
                <td>&nbsp;<input id="timeInterval" name="timeInterval" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据量:</td>
                <td>&nbsp;<input id="dataQuant" name="dataQuant" type="text" class="boxinput3" /></td>
              </tr>
              
              <tr>
                <td align="left">&nbsp;来源信息</td>
                <td>&nbsp; </td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据源类型:</td>
                <td>&nbsp;<input id="srcType" name="srcType" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;观测站编号:</td>
                <td>&nbsp;<input id="siteID" name="siteID" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;观测站名称:</td>
                <td>&nbsp;<input id="siteName" name="siteName" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;观测站地理位置描述:</td>
                <td>&nbsp;<input id="sitePosDesc" name="sitePosDesc" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;观测站负责方:</td>
                <td>&nbsp;<input id="siteContact" name="siteContact" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;观测仪器编号:</td>
                <td>&nbsp;<input id="instID" name="instID" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;观测仪器名称:</td>
                <td>&nbsp;<input id="instName" name="instName" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;观测仪器类别:</td>
                <td>&nbsp;<input id="instCat" name="instCat" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;观测仪器型号:</td>
                <td>&nbsp;<input id="instSpec" name="instSpec" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;生产商负责方:</td>
                <td>&nbsp;<input id="instContact" name="instContact" type="text" class="boxinput3" /></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据处理方法名称:</td>
                <td>&nbsp;<input id="methodName" name="methodName" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;处理方法描述:</td>
                <td>&nbsp;<input id="methodDesc" name="methodDesc" type="text" class="boxinput3" /></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;原始数据来源说明:</td>
                <td>&nbsp;<input id="originalDesc" name="originalDesc" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;原始数据来源方:</td>
                <td>&nbsp;<input id="originalContact" name="originalContact" type="text" class="boxinput3" /></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据质量描述:</td>
                <td>&nbsp;<input id="qaDesc" name="qaDesc" type="text" class="boxinput3" /></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据提供方:</td>
                <td>&nbsp;<input id="prdContact" name="prdContact" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              
              <tr>
                <td align="left">&nbsp;分发信息</td>
                <td>&nbsp; </td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据存储结构类型:</td>
                <td>&nbsp;<input id="dataType" name="dataType" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据格式:</td>
                <td>&nbsp;<input id="dataFormat" name="dataFormat" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据格式版本:</td>
                <td>&nbsp;<input id="formatVersion" name="formatVersion" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;服务类型:</td>
                <td>&nbsp;<input id="serviceType" name="serviceType" type="text" class="boxinput3" /><span class="red_asterisk">*</span>
                <p id="serviceLink"></p></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;服务参数名:</td>
                <td>&nbsp;<input id="paraName" name="paraName" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;服务参数值:</td>
                <td>&nbsp;<input id="paraValue" name="paraValue" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据访问分级:</td>
                <td>&nbsp;<input id="accessClass" name="accessClass" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据使用:</td>
                <td>&nbsp;<input id="useDesc" name="useDesc" type="text" class="boxinput3" /></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;知识产权描述:</td>
                <td>&nbsp;<input id="copyRightDesc" name="copyRightDesc" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据引用方式:</td>
                <td>&nbsp;<input id="citation" name="citation" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;数据分发方:</td>
                <td>&nbsp;<input id="dissContact" name="dissContact" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              
              <tr>
                <td align="left">&nbsp;元数据描述信息</td>
                <td>&nbsp; </td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;元数据维护方:</td>
                <td>&nbsp;<input id="mdContact" name="mdContact" type="text" class="boxinput3" /> </td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;元数据更新日期:</td>
                <td>&nbsp;<input id="mdDateUpd" name="mdDateUpd" type="text" class="boxinput3" /> </td>
              </tr>
              
              <tr>
                <td align="left">&nbsp;元数据类型实体</td>
                <td>&nbsp; </td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;经度:</td>
                <td>&nbsp;<input id="pointLong" name="pointLong" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;纬度:</td>
                <td>&nbsp;<input id="pointLat" name="pointLat" type="text" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td align="left">&nbsp;数据集负责方</td>
                <td>&nbsp; </td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;单位名称:</td>
                <td>&nbsp;<input id="orgName" type="text" name="orgName" class="boxinput3" /><span class="red_asterisk">*</span></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;联系电话:</td>
                <td>&nbsp;<input id="phone" name="phone" type="text" class="boxinput3" /></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;通讯地址:</td>
                <td>&nbsp;<input id="address" name="address" type="text" class="boxinput3" /></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;邮编:</td>
                <td>&nbsp;<input id="postcode" name="postcode" type="text" class="boxinput3" /></td>
              </tr>
              <tr>
                <td class="table_td_fieldlab">&nbsp;电子邮件:</td>
                <td>&nbsp;<input id="eMail" name="eMail" type="text" class="boxinput3" /></td>
              </tr>
              
              
                         
              <tr>
                <td >&nbsp; </td>
                <td>&nbsp; <span id="errormsg" class="errorMessages"></span></td>
              </tr>
              <tr>
                <td colspan="2" align="center">&nbsp;&nbsp; 
                <input id="savebut"  type="image" src="images/baocun.png" value=" 保   存 " style="height: 22px;" /> 
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

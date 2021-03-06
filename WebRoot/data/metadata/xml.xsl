<?xml version="1.0" encoding="gb2312" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/TR/WD-xsl">
	<xsl:template match="/">
		<html>
			<head>
				<title></title>
				<link href="../../../images/1.css" type="text/css" rel="stylesheet" />
				<script type="text/javascript" src="../../../js/library/jquery-1.7.1.min.js"></script>
				<script>
				    $(document).ready(function() {     
				           /*
				           //加载时隐藏子菜单       
				          $('li ul').hide();       
				            //不包含子菜单时鼠标指针和项目图标  
				            $('li').css({'cursor': 'default', 'list-style-image': 'none',' font-weight':'normal' });        
				              $('li:not(:has(ul))').css({'cursor': 'default', 'list-style-image': 'none' });  
				           //包含子菜单时鼠标指针和项目图标 
				              $('li:has(ul)').css({ 'cursor': 'pointer', 'list-style-image': 'url(../../../images/plus.gif)' }); 
				              //单击含子菜单的项   
				          $('li:has(ul)').click(function (event) {       
				                   if (this == event.target) {           
				                            if ($(this).children().is(':hidden')) {                                                                     
				                                $(this).css('list-style-image', 'url(../../../images/minus.gif)').children().show();                            
									      } else {  
						                    $(this).css('list-style-image', 'url(../../../images/plus.gif)').children().hide();  
				                          }   
				                   }
				                   })
				           */
				           //加载时显示子菜单            
				            //不包含子菜单时鼠标指针和项目图标  
				            $('li').css({'cursor': 'default', 'list-style-image': 'none',' font-weight':'normal' });        
				              $('li:not(:has(ul))').css({'cursor': 'default', 'list-style-image': 'none' });  
				           //包含子菜单时鼠标指针和项目图标 
				              $('li:has(ul)').css({ 'cursor': 'pointer', 'list-style-image': 'url(../../../images/minus.gif)' }); 
				              //单击含子菜单的项   
				          $('li:has(ul)').click(function (event) {       
				                   if (this == event.target) {           
				                            if ($(this).children().is(':hidden')) {                                                                     
				                                $(this).css('list-style-image', 'url(../../../images/minus.gif)').children().show();                            
									      } else {  
						                    $(this).css('list-style-image', 'url(../../../images/plus.gif)').children().hide();  
				                          }   
				                   }
				                   })
				          })
				          
				     
    </script>
    <style>
     .li_no_img{height:20px;list-style-type:none;margin:0px;}
     .metaTitle{height:20px;font-weight:bold; width:110px;float:left}
     
    </style>
			</head>
			<body style="bgcolor:red;">
							<li style="line-height:15px">元数据标识信息
							   <ul>
							       <li class="li_no_img">
										<span class="metaTitle">服务地址:</span>
										<span id="serviceLinkText" style="display:none;"><xsl:value-of select="metadata/DissInfo/serviceInfo/serviceLink"/></span>										
										   <a  id="serviceLinkHref" style="float:left" target="_blank">浏览</a>																				
										<script>
										    if($("#serviceLinkText").html()){
										      $("#serviceLinkHref").attr("href",$("#serviceLinkText").html());
										    }
										</script>
									</li>			
									<li class="li_no_img">
										<span class="metaTitle">数据集标识符:</span>
										<xsl:value-of select="metadata/IdInfo/datId" />
									</li>								
									<li class="li_no_img">
										<span class="metaTitle">数据集名称:</span>
										<xsl:value-of select="metadata/IdInfo/resTitle" />
									</li>
									<xsl:if test="metadata/IdInfo/engTitle">
									<li class="li_no_img">
										<span class="metaTitle">数据集英文名称:</span>
										<xsl:value-of select="metadata/IdInfo/engTitle" />
									</li>
									</xsl:if>
									<li class="li_no_img">
										<span class="metaTitle">数据集语言:</span>
										<xsl:value-of select="metadata/IdInfo/language" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">关键词:</span>
										<xsl:value-of select="metadata/IdInfo/keyword" />
									</li>
									<xsl:if test="metadata/IdInfo/keywordEng">										
									<li class="li_no_img">
										<span class="metaTitle">英文关键词:</span>
										<xsl:value-of select="metadata/IdInfo/keywordEng" />
									</li>
									</xsl:if>								
									<li class="li_no_img">
										<span class="metaTitle">最新修改日期:</span>
										<xsl:value-of select="metadata/IdInfo/lastAlterDate" />
									</li>										
								</ul>
							</li>						
							<li>元数据内容信息
								<ul>
									<li class="li_no_img">
										<span class="metaTitle">数据集摘要:</span>
										<xsl:value-of select="metadata/ContInfo/abstract" />
									</li>
									<xsl:if test="metadata/IdInfo/abstract1Eng">										
									<li class="li_no_img">
										<span class="metaTitle">数据集英文摘要:</span>
										<xsl:value-of select="metadata/IdInfo/abstract1Eng" />
									</li>
									</xsl:if>											
									<li class="li_no_img">
										<span class="metaTitle">数据集分类名称:</span>
										<xsl:value-of select="metadata/ContInfo/tpCat/cateName" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">数据集分类类目编码:</span>
										<xsl:value-of select="metadata/ContInfo/tpCat/cateCode" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">数据集分类标准:</span>
										<xsl:value-of select="metadata/ContInfo/tpCat/cateStd" />
									</li>																	
									<li class="li_no_img">
										<span class="metaTitle">左下角坐标:</span>
										<xsl:value-of select="metadata/ContInfo/spatialCov/planeCov/coordCov/lftBtmCoord" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">最大经度:</span>
										<xsl:value-of select="metadata/ContInfo/spatialCov/planeCov/coordCov/rightTopCoord" />
									</li>									
									<li class="li_no_img">
										<span class="metaTitle">坐标系统:</span>
										<xsl:value-of select="metadata/ContInfo/spatialCov/planeCov/corSys" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">投影系统:</span>
										<xsl:value-of select="metadata/ContInfo/spatialCov/planeCov/proSys" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">等效比例尺分母:</span>
										<xsl:value-of select="metadata/ContInfo/spatialCov/planeCov/spaRes/equScale" />
									</li>									
									<li class="li_no_img">
										<span class="metaTitle">栅格分辨率:</span>
										<xsl:value-of select="metadata/ContInfo/spatialCov/planeCov/gridSize" />
									</li>									
									<li class="li_no_img">
										<span class="metaTitle">坐标位置:</span>
										<xsl:value-of select="metadata/ContInfo/spatialCov/planeCov/posDes" />
									</li>																		
									<li class="li_no_img">
										<span class="metaTitle">垂向最大值:</span>
										<xsl:value-of select="metadata/ContInfo/spatialCov/verCov/altCov/topVer" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">垂向最小值:</span>
										<xsl:value-of select="metadata/ContInfo/spatialCov/verCov/altCov/lowVer" />
									</li>
								    <li class="li_no_img">
										<span class="metaTitle">海拔高度位置:</span>
										<xsl:value-of select="metadata/ContInfo/spatialCov/verCov/altPoint" />
								    </li>
								    <li class="li_no_img">
										<span class="metaTitle">海拔高度位置:</span>
										<xsl:value-of select="metadata/ContInfo/spatialCov/verCov/altDesc" />
								    </li>		
									 <li class="li_no_img">
										<span class="metaTitle">高程系统:</span>
										<xsl:value-of select="metadata/ContInfo/spatialCov/verCov/atiSys" />
								    </li>			
									<li class="li_no_img">
										<span class="metaTitle">垂直采样间隔:</span>
										<xsl:value-of select="metadata/ContInfo/spatialCov/verCov/atiInterval" />
								    </li>												
									<li class="li_no_img">
										<span class="metaTitle">开始时间:</span>
										<xsl:value-of select="metadata/ContInfo/timeCov/timePeriod/begDate" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">结束时间:</span>
										<xsl:value-of select="metadata/ContInfo/timeCov/timePeriod/endDate" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">时间点:</span>
										<xsl:value-of select="metadata/ContInfo/timeCov/timePoint" />
									</li>								
									<li class="li_no_img">
										<span class="metaTitle">数据量:</span>
										<xsl:value-of select="metadata/ContInfo/dataQuant" />
									</li>
								</ul>							
							</li>																
							<li>来源信息
								<ul>
									<li class="li_no_img">
										<span class="metaTitle">数据源类型:</span>
										<xsl:value-of select="metadata/SrcInfo/srcType" />
									</li>									
									<li class="li_no_img">
										<span class="metaTitle">观测站编号:</span>
										<xsl:value-of select="metadata/SrcInfo/site/siteID" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">观测站名称:</span>
										<xsl:value-of select="metadata/SrcInfo/site/siteName" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">地理位置描述:</span>
										<xsl:value-of select="metadata/SrcInfo/site/sitePosDesc" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">观测站负责方:</span>
										<xsl:value-of select="metadata/SrcInfo/site/siteContact" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">观测仪器编号:</span>
										<xsl:value-of select="metadata/SrcInfo/instrument/instID" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">观测仪器名称:</span>
										<xsl:value-of select="metadata/SrcInfo/instrument/instName" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">观测仪器类别:</span>
										<xsl:value-of select="metadata/SrcInfo/instrument/instCat" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">观测仪器型号:</span>
										<xsl:value-of select="metadata/SrcInfo/instrument/instSpec" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">生产商负责方:</span>
										<xsl:value-of select="metadata/SrcInfo/instrument/instContact" />
									</li>									
									<li class="li_no_img">
										<span class="metaTitle">数据处理方法名称:</span>
										<xsl:value-of select="metadata/SrcInfo/prcMethod/methodName" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">数据处理方法描述:</span>
										<xsl:value-of select="metadata/SrcInfo/prcMethod/methodDesc" />
									</li>										
									<li class="li_no_img">
										<span class="metaTitle">原始数据来源说明:</span>
										<xsl:value-of select="metadata/SrcInfo/prcMethod/originalSrc/originalDesc" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">数据来源方:</span>
										<xsl:value-of select="metadata/SrcInfo/prcMethod/originalSrc/originalContact" />
									</li>									
									<li class="li_no_img">
										<span class="metaTitle">数据质量描述:</span>
										<xsl:value-of select="metadata/SrcInfo/qaDesc" />
									</li>								
									<li class="li_no_img">
										<span class="metaTitle">数据提供方:</span>
										<xsl:value-of select="metadata/SrcInfo/prdContact" />
									</li>
								</ul>
							</li>
							
							<li>分发信息
								<ul>							
									<li class="li_no_img">
										<span class="metaTitle">数据存储结构类型:</span>
										<xsl:value-of select="metadata/DissInfo/dataStorage/dataType" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">数据格式:</span>
										<xsl:value-of select="metadata/DissInfo/dataStorage/dataFormat" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">数据格式版本:</span>
										<xsl:value-of select="metadata/DissInfo/dataStorage/formatVersion" />
									</li>																
									<li class="li_no_img">
										<span class="metaTitle">数据服务类型:</span>
										<xsl:value-of select="metadata/DissInfo/serviceInfo/serviceType" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">服务参数名:</span>
										<xsl:value-of select="metadata/DissInfo/serviceInfo/serviceParas/paraName" />
									</li>							
									<li class="li_no_img">
										<span class="metaTitle">服务参数值:</span>
										<xsl:value-of select="metadata/DissInfo/serviceInfo/serviceParas/paraValue" />
									</li>	
									<li class="li_no_img">
										<span class="metaTitle">数据访问分级:</span>
										<xsl:value-of select="metadata/DissInfo/serviceInfo/accessClass" />
									</li>
									
									<li class="li_no_img">
										<span class="metaTitle">数据使用:</span>
										<xsl:value-of select="metadata/DissInfo/useDesc" />
									</li>									
									<li class="li_no_img">
										<span class="metaTitle">知识产权描述:</span>
										<xsl:value-of select="metadata/DissInfo/copyRight/copyRightDesc" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">数据引用方式说明:</span>
										<xsl:value-of select="metadata/DissInfo/copyRight/citation" />
									</li>
							
									<li class="li_no_img">
										<span class="metaTitle">数据分发方:</span>
										<xsl:value-of select="metadata/DissInfo/dissContact" />
									</li>
								</ul>
							</li>						
							<li>元数据描述信息
								<ul>
									<li class="li_no_img">
										<span class="metaTitle">元数据维护方:</span>
										<xsl:value-of select="metadata/MdDesp/MdContact" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">元数据更新日期:</span>
										<xsl:value-of select="metadata/MdDesp/mdDateUpd" />
									</li>
								</ul>
							</li>							
							<li>元数据类型实体
								<ul>
									<li class="li_no_img">
										<span class="metaTitle">经度:</span>
										<xsl:value-of select="metadata/PointCoordType/pointLong" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">纬度:</span>
										<xsl:value-of select="metadata/PointCoordType/pointLat" />
									</li>
								</ul>
							</li>							
								<li>数据集负责方
								<ul>
									<li class="li_no_img">
										<span class="metaTitle">单位名称:</span>
										<xsl:value-of select="metadata/respParty/orgName" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">联系电话:</span>
										<xsl:value-of select="metadata/respParty/phone" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">通讯地址:</span>
										<xsl:value-of select="metadata/respParty/address" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">邮编:</span>
										<xsl:value-of select="metadata/respParty/postcode" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">电子邮件:</span>
										<xsl:value-of select="metadata/respParty/email" />
									</li>
								</ul>
							</li>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>

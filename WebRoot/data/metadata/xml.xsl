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
				          })
    </script>
    <style>
     .li_no_img{height:30px;list-style-type:none;}
     .metaTitle{font-weight:bold; width:100px;}
    </style>
			</head>
			<body style="bgcolor:red;">
							<li> 元数据标识信息
								<ul>
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
									
								</ul>
							</li>
							
							<li>元数据内容信息
								<ul>
									<li class="li_no_img">
										<span class="metaTitle">数据集摘要:</span>
										<xsl:value-of select="metadata/ContInfo/abstract" />
									</li>
									<li>数据集分类
										<ul>
											<li class="li_no_img">
												<span class="metaTitle">类目名称:</span>
												<xsl:value-of select="metadata/ContInfo/tpCat/cateName" />
											</li>
											<li class="li_no_img">
												<span class="metaTitle">类目编码:</span>
												<xsl:value-of select="metadata/ContInfo/tpCat/cateCode" />
											</li>
											<li class="li_no_img">
												<span class="metaTitle">分类标准:</span>
												<xsl:value-of select="metadata/ContInfo/tpCat/cateStd" />
											</li>
										</ul>
									</li>
									<li>空间范围	
										<ul>
										<li>平面范围	
											<ul>
											<li class="li_no_img">
												<span class="metaTitle">左下角坐标:</span>
												<xsl:value-of select="metadata/ContInfo/spatialCov/planeCov/coordCov/lftBtmCoord" />
											</li>
											<li class="li_no_img">
												<span class="metaTitle">最大经度:</span>
												<xsl:value-of select="metadata/ContInfo/spatialCov/planeCov/coordCov/rightTopCoord" />
											</li>
											<li class="li_no_img">
												<span class="metaTitle">栅格分辨率:</span>
												<xsl:value-of select="metadata/ContInfo/spatialCov/planeCov/gridSize" />
											</li>
											<li class="li_no_img">
												<span class="metaTitle">等效比例尺分母:</span>
												<xsl:value-of select="metadata/ContInfo/spatialCov/planeCov/spaRes/equScale" />
											</li>
											<li class="li_no_img">
												<span class="metaTitle">坐标位置:</span>
												<xsl:value-of select="metadata/ContInfo/spatialCov/planeCov/coordPoint" />
											</li>
											</ul>
										</li>
										<li>垂向范围	
											<ul>
												<li class="li_no_img">
												<span class="metaTitle">垂向最大值:</span>
												<xsl:value-of select="metadata/ContInfo/spatialCov/verCov/altCov/topVer" />
												</li>
												<li class="li_no_img">
												<span class="metaTitle">垂向最小值:</span>
												<xsl:value-of select="metadata/ContInfo/spatialCov/verCov/altCov/lowVer" />
												</li>
											</ul>
											
										</li>
										
										
										</ul>
									</li>
									<li>时间范围
										<ul>
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
										</ul>
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
									<li>观测站
										<ul>
											<li class="li_no_img">
												<span class="metaTitle">编号:</span>
												<xsl:value-of select="metadata/SrcInfo/site/siteID" />
											</li>
											<li class="li_no_img">
												<span class="metaTitle">名称:</span>
												<xsl:value-of select="metadata/SrcInfo/site/siteName" />
											</li>
											<li class="li_no_img">
												<span class="metaTitle">经纬度坐标:</span>
												<xsl:value-of select="metadata/SrcInfo/site/siteCoordinate" />
											</li>
											<li class="li_no_img">
												<span class="metaTitle">地理位置描述:</span>
												<xsl:value-of select="metadata/SrcInfo/site/sitePosDesc" />
											</li>
											<li class="li_no_img">
												<span class="metaTitle">负责方:</span>
												<xsl:value-of select="metadata/SrcInfo/site/siteContact" />
											</li>
										</ul>
									</li>
									<li>观测仪器
										<ul>
											<li class="li_no_img">
												<span class="metaTitle">编号:</span>
												<xsl:value-of select="metadata/SrcInfo/instrument/instID" />
											</li>
											<li class="li_no_img">
												<span class="metaTitle">名称:</span>
												<xsl:value-of select="metadata/SrcInfo/instrument/instName" />
											</li>
											<li class="li_no_img">
												<span class="metaTitle">类别:</span>
												<xsl:value-of select="metadata/SrcInfo/instrument/instCat" />
											</li>
											<li class="li_no_img">
												<span class="metaTitle">型号:</span>
												<xsl:value-of select="metadata/SrcInfo/instrument/instSpec" />
											</li>
											<li class="li_no_img">
												<span class="metaTitle">生产商负责方:</span>
												<xsl:value-of select="metadata/SrcInfo/instrument/instContact" />
											</li>
										</ul>
									</li>
									<li>数据处理方法
										<ul>
											<li class="li_no_img">
												<span class="metaTitle">方法名称:</span>
												<xsl:value-of select="metadata/SrcInfo/prcMethod/methodName" />
											</li>
											<li class="li_no_img">
												<span class="metaTitle">描述:</span>
												<xsl:value-of select="metadata/SrcInfo/prcMethod/methodDesc" />
											</li>
											<li>原始数据来源
												<ul>
													<li class="li_no_img">
														<span class="metaTitle">原始数据来源说明:</span>
														<xsl:value-of select="metadata/SrcInfo/prcMethod/originalSrc/originalDesc" />
													</li>
													<li class="li_no_img">
														<span class="metaTitle">来源方:</span>
														<xsl:value-of select="metadata/SrcInfo/prcMethod/originalSrc/originalContact" />
													</li>
												</ul>
											</li>
										</ul>
									</li>
									<xsl:if test="metadata/SrcInfo/qaDesc">
									<li class="li_no_img">
										<span class="metaTitle">数据质量描述:</span>
										<xsl:value-of select="metadata/SrcInfo/qaDesc" />
									</li>
									</xsl:if>
									<li class="li_no_img">
										<span class="metaTitle">数据提供方:</span>
										<xsl:value-of select="metadata/SrcInfo/prdContact" />
									</li>
								</ul>
							</li>
							
							<li>分发信息
								<ul>
									<li>数据存储
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
										</ul>
									</li>
									<li>数据访问服务
										<ul>
											<li class="li_no_img">
												<span class="metaTitle">服务类型:</span>
												<xsl:value-of select="metadata/DissInfo/serviceInfo/serviceType" />
											</li>
											<li>服务参数
												<ul>
												
												</ul>
											</li>
											<li class="li_no_img">
												<span class="metaTitle">数据访问分级:</span>
												<xsl:value-of select="metadata/DissInfo/serviceInfo/accessClass" />
											</li>
										</ul>
									</li>
									<li class="li_no_img">
										<span class="metaTitle">数据使用:</span>
										<xsl:value-of select="metadata/DissInfo/useDesc" />
									</li>
									<li>知识产权
										<ul>
											<li class="li_no_img">
												<span class="metaTitle">数据产权说明:</span>
												<xsl:value-of select="metadata/DissInfo/copyRight/copyRightDesc" />
											</li>
											<li class="li_no_img">
												<span class="metaTitle">数据引用方式说明:</span>
												<xsl:value-of select="metadata/DissInfo/copyRight/citation" />
											</li>
										</ul>
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
										<span class="metaTitle">元数据标示符:</span>
										<xsl:value-of select="metadata/MdDesp/mdId" />
									</li>
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
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>

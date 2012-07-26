<?xml version="1.0" encoding="gb2312" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/TR/WD-xsl">
	<xsl:template match="/">
		<html>
			<head>
				<title></title>
				<link href="../../../images/1.css" type="text/css" rel="stylesheet" />
				<script type="text/javascript"
					src="../../../js/library/jquery-1.7.1.min.js">
				</script>	
					<script>
				    $(document).ready(function () {     
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
			<body style="bgcolor=red">
				<xsl:for-each select="/metadata">
							<li>
								详细信息
								<ul>
									<li class="li_no_img">
										<span class="metaTitle">数据集名称:</span>
										<xsl:value-of select="name" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">关键词:</span><xsl:value-of select="keys" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">数据集摘要:</span>
										<xsl:value-of select="zhaiyao" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">数据集语种:</span><xsl:value-of select="yuzhong" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">数据集字符:</span><xsl:value-of select="zifu" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">目的:</span><xsl:value-of select="mudi" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">表示方法:</span><xsl:value-of select="biaoshi" />
									</li>																				
									<li class="li_no_img">																													
										<span class="metaTitle">数据集格式名称:</span><xsl:value-of select="geshiname" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle"> 数据集格式版本:</span><xsl:value-of select="geshiversion" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">安全限制分级:</span><xsl:value-of select="anquan" />
									</li>									
									<li class="li_no_img">
										<span class="metaTitle">联系人姓名:</span><xsl:value-of select="contacts" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">联系人单位:</span><xsl:value-of select="employer" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">电话:</span><xsl:value-of select="phone" />
									</li>
									<li class="li_no_img">
										<span class="metaTitle">传真:</span><xsl:value-of select="fax" />
									</li>
								</ul>
							</li>
						</xsl:for-each>					
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>

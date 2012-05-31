<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../jstl.jsp" flush="true" />
<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<!-- -->
	<link href="images/1.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="css/header.css" />
	<script type="text/javascript">
		$(document).ready(function(){
		    $("#M1,#M2,#M0").click(function() {
		
		        $(this).parent().find(".menu_body").slideDown('fast').show();
		
		        $(this).parent().hover(function() {}, 
			        function(){
			            $(this).parent().find(".menu_body").slideUp('slow');
			    	});
		     });
		});
	</script>
  </head>
  
  <body>
  <div id="top2">
	<div class="top_right2"></div>
	<!--top右侧结束-->
	<div class="top_language"><a href="ht/lang/zh_CN.do" class="a1">中文</a>   <a href="ht/lang/en_US.do" class="a1">English</a> </div>
  </div><!--top结束-->
  <div class="menu">
	<div class="menutext"><a href="ht/index.do" class="a3">${msg['ht_menu_login']}</a></div>
	<div class="menutext"><a href='javascript:;' class="a3" id="M0">${msg['ht_menu_sysseting']}</a>
		<div class="menu_body">
	      <a href="ht/admins.do" title="管理员">管理员信息</a>
	      <a href="ht/stationload.do" title="观测站管理">观测站</a>
	    </div>
	</div>
	<div class="menutext"><a href="javascript:;" class="a3" id="M1">数据管理</a>
		<div class="menu_body">
	      <a href="ht/pgt.do" title="电离层频高图录入与管理维护">电离层频高图管理</a>
	      <a href="#" title="电离层参数录入与管理维护">电离层参数管理</a>
	      <a href="#" title="区域电离层特性图管理维护">区域电离层特性图</a> 
	      <a href="#" title="元数据录入与管理维护">元数据管理</a>
	    </div></div>
	<div class="menutext"><a href="javascript:;" class="a3" id="M2">用户管理</a>
		<div class="menu_body">
	      <a href="#" title="用户管理与统计">用户管理</a>
	      <a href="#" title="查看用户反馈，对用户反馈进行回复或删除清理">用户反馈</a>
	    </div>
	</div>
	<div class="menutext1"><a href="javascript:;" class="a3">访问权限管理</a></div>
	<div class="menutext"><a href="javascript:;" class="a3">管理日志</a></div>
	<div class="menutext"><a href="javascript:;" class="a3">服务系统</a></div>
	<div class="menutext"><a href="javascript:;" class="a3">新闻管理</a></div>
  </div>
  
    <!-- 
    <table width="80%" border="0">
		  <tr>
		    <td>&nbsp;</td>
		    <td>&nbsp;<a href='ht/admins.do'>${msg['ht_menu_sysseting']}</a></td>
		    <td>&nbsp;${msg['ht_menu_datamgr']}</td>
		    <td>&nbsp;${msg['ht_menu_usermgr']}</td>
		    <td>&nbsp;${msg['ht_menu_protect']}</td>
		    <td>&nbsp;${msg['ht_menu_logmgr']}</td>
		    <td>&nbsp;${msg['ht_menu_sysservice']}</td>
		    <td>&nbsp;${msg['ht_menu_newsmgr']}</td>
		  </tr>
		</table>
     -->
  </body>
</html>

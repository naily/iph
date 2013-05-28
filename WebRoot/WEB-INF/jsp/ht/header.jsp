<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="../jstl.jsp" %>
<!DOCTYPE HTML >
<html>
<meta http-equiv="X-UA-Compatible" content="IE=8">
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
	<div class="top_language">
	<!--  
	<a href="ht/lang/zh_CN.do" class="a1">中文</a>   
	<a href="ht/lang/en_US.do" class="a1">English</a> 
	-->
	</div>
  </div><!--top结束-->
  <div class="menu">
	<div class="menutext">
		<c:choose>
	    	<c:when test="${sessionScope.ht_account.login}" >
	    		<a href="ht/logout.do" class="a3">退出</a>
	    	</c:when>
	    	<c:otherwise>
				<a href="ht/index.do" class="a3">登录</a>
	    	</c:otherwise>
    	</c:choose>
	</div>
	
	<div class="menutext"><a href='javascript:;' class="a3" id="M0">系统设置</a>
		<div class="menu_body">
	      <a href="ht/admins.do" title="管理员">管理员信息</a>
	      <a href="ht/stationload.do" title="观测站管理">观测站</a>
	    </div>
	</div>
	<div class="menutext"><a href="javascript:;" class="a3" id="M1">数据管理</a>
		<div class="menu_body">
	      <a href="ht/pgt.do" title="电离层频高图录入与管理维护">电离层频高图</a>
	      <a href="ht/sac.do" title="原始观测报表管理维护">原始观测报表</a> 
	      <a href="ht/pam.do" title="电离层参数录入与管理维护">电离层参数管理</a>
	      <a href="ht/med.do" title="元数据录入与管理维护">元数据管理</a>
	    </div></div>
	    
	<div class="menutext1"><a href="ht/pta.do" class="a3">数据权限管理</a></div>
	
	<div class="menutext"><a href="javascript:;" class="a3" id="M2">用户管理</a>
		<div class="menu_body">
	      <a href="ht/qtum.do" title="用户管理与统计">用户管理</a>
	      <a href="ht/qtcm.do" title="查看用户反馈，对用户反馈进行回复或删除清理">用户反馈</a>
	    </div>
	</div>
	
	<div class="menutext"><a href="ht/dlog.do" class="a3">管理日志</a></div>
	<div class="menutext"><a href="ht/dstats.do" class="a3">访问统计</a></div>
	<div class="menutext"><a href="ht/news.do" class="a3">新闻管理</a></div>
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

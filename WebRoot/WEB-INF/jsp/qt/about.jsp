<%@ page language="java" import="java.util.*,cn.fam1452.dao.pojo.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="../jstl.jsp"%>
<!DOCTYPE HTML >
<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=8">
		<base href="<%=basePath%>">
		<title>${msg['site_name']}</title>
		<!--
	-->
		<link href="images/1.css" type="text/css" rel="stylesheet" />
		<link href="css/index.css" type="text/css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="css/default/om-default.css" />
		<script type="text/javascript" src="js/library/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="js/library/jqueryAjaxBox.js"></script>
		<script type="text/javascript" src="js/library/operamasks-ui.min.js"></script>

		<style type="text/css"></style>

	</head>

	<body>
		<jsp:include page="header.jsp" flush="true" />
		<div  id="right">
			<div class="title8">${msg['qt_mu_me']}</div>
			<div class="newsbox">
				
				<div class="newstext">
				<p>
				<strong>中国科学院地质与地球物理研究所</strong>是从事固体地球科学研究与教育的综合性国家学术机构。研究所的地磁与空间物理学科，在包括电离层在内的空间环境观测上具有悠久的观测和研究历史，目前该学科依托北京空间环境国家野外科学观测研究站和<strong>中科院电离层空间环境重点实验室</strong>，建有北起漠河，南到三亚，并延伸到地球南北二极的<strong>空间环境综合观测台链</strong>，拥有电离层、中高层大气和地磁等多种观测手段，能对我国空间环境不同纬度，不同层次和不同特性参量进行综合观测和实时监测的能力，并利用这些观测数据，在电离层、中高层大气和磁层物理等方面取得了一系列研究成果，为本项目的实施和开展我国有关区域进行的定期补点流动观测，提供数据分析研究和观测技术保障。项目合作单位<strong>中科院地理科学与资源研究所</strong>在地球系统资源数据管理和共享方面有很好的工作基础， 目前正负责实施科技部<strong>地球系统科学数据共享网</strong>建设和运行，为本项目的数据共享应用和管理提供了良好的工作基础。
				</p>
				</div>
			</div>
		</div>
		<!--right 结束-->
		<jsp:include page="left.jsp" flush="true" />
		<!--left 结束-->
		<jsp:include page="../ht/footer.jsp" flush="true" />
	</body>
</html>

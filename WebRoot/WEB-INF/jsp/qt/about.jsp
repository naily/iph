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
				
				<div class="newstext">中国科学院地理科学与资源研究所于1999年9月经中国科学院批准，由中国科学院地理研究所（前身是1940年成立的中国地理研究所）和自然资源综合考察委员会（1956年成立）整合而成，纳入中国科学院知识创新工程试点。<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;半个多世纪以来，中国科学院地理科学与资源研究所及其前身-中国科学院地理研究所与自然资源综合考察委员会，为中国地理科学与资源科学的发展做出了开拓性的贡献。在区域、国家和全球尺度上，围绕社会经济发展目标，在自然资源合理利用、生态环境保护、国土综合整治、区域可持续发展、资源与环境信息系统等重要领域，取得了一批国家级重大科研成果。在上述重大科学问题和国家需求方面做出了社会公认的、不可替代的贡献，研究成果代表了我国地理科学与资源科学发展的方向与水平。<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地理科学与资源研究所现有职工520人，其中科技人员358人，研究员112人、副研究员和其他高级专业技术人员153人，进入知识创新工程274人。陈述彭、吴传均、孙鸿烈、阳含熙、刘昌明、郑度、陆大道7位中国科学院院士和石玉林、李文华、孙九林3位中国工程院院士在该所工作。<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地理科学与资源研究所设地理学和生态学博士后流动站，是地理学一级学科与生态学、环境科学和农业经济管理三个二级学科博士学位培养点，以及自然地理学、人文地理学、地图学与地理信息系统、生态学、气象学、环境科学和农业经济管理硕士学位培养点。目前共有研究生导师109人，其中博士生导师67人，硕士生导师42人；在学研究生504人，其中博士生350人，硕士研究生154人，另有博士后73人。连续多次被中国科学院评为博士生重点培养基地，在2003年全国学位与研究生教育评估所一级学科整体水平评估中，研究所地理学一级学科名列全国第二，其中科学研究方面名列全国第一。</div>
			</div>
		</div>
		<!--right 结束-->
		<jsp:include page="left.jsp" flush="true" />
		<!--left 结束-->
		<jsp:include page="../ht/footer.jsp" flush="true" />
	</body>
</html>

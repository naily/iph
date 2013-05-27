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
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3"></script>
		<style type="text/css">
			#container{
			height:530px;
			width:720px;}
		</style>
	<script type="text/javascript"><!--
	var lang = ${msg.lang=="zh"} ;
	$(document).ready(function(){
		var map = new BMap.Map("container");          // 创建地图实例
		var point = new BMap.Point(108.235, 37.520);  // 创建点坐标
		map.centerAndZoom(point, 5);                 // 初始化地图，设置中心点坐标和地图级别
		//map.disableDragging() ;
		map.disableScrollWheelZoom();
		map.disableDoubleClickZoom();
		map.disableKeyboard();
		var opts = {type: BMAP_NAVIGATION_CONTROL_SMALL}    
		map.addControl(new BMap.NavigationControl(opts)); 
		
		<c:forEach items="${obj}" var="st" varStatus="i">
		var marker${st.id} = new BMap.Marker(new BMap.Point(${st.longitude}, ${st.latitude})); 
		map.addOverlay(marker${st.id}); 
		
		//var infoWindow1 = ;
		marker${st.id}.addEventListener("click", function(){
			var html = new Array() ;
			if(lang){
				html.push('${st.name}');
				html.push('经度：' + '${st.longitude}');
				html.push('纬度：' + '${st.latitude}');
				html.push('地理位置：' + '${st.location}');
			}else{
				html.push('${st.nameEng}');
				html.push('Longitude：' + '${st.longitude}');
				html.push('Latitude：' + '${st.latitude}');
				html.push('Address：' + '${st.location}');
			}
			
			this.openInfoWindow(new BMap.InfoWindow(html.join('<p>')));
		});
        </c:forEach> 
		
		/**
		$("#but").click(function(){
			alert("当前地图中心点：" + map.getCenter().lng + "," + map.getCenter().lat);
		});
		*/
		function showInfo(e){
			$("#info").html(e.point.lng + ", " + e.point.lat) ;
		}
		map.addEventListener("click", showInfo);
		
	});
	
	--></script>
	</head>

	<body>
		<jsp:include page="header.jsp" flush="true" />
		<div  id="right">
			<div class="title8">台站分布</div>
			<div class="newsbox">
			<!--  
				<div class="newstext">
				</div>-->
				<div id="container"></div>
				<span id="info"></span>
			</div>
		</div>
		<!--right 结束-->
		<jsp:include page="left.jsp" flush="true" />
		<!--left 结束-->
		<jsp:include page="../ht/footer.jsp" flush="true" />
	</body>
</html>

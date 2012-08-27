/**
 * 首页右侧数据查询
 * 1、后台最新数据记录显示
 * 2、统计相关数据（用户数、数据下载等）
 * */
$(document).ready(function() {
		//最新数据更新
	var updateVisitData = {
		url : 'qt/updateVisitNum.do',
		params : {
			
		},
		callback : function(json) {		
			
		}
	}
	ajaxpost(updateVisitData);
});
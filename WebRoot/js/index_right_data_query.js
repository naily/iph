/**
 * 首页右侧数据查询
 * 1、后台最新数据记录显示
 * 2、统计相关数据（用户数、数据下载等）
 * */
$(document).ready(function() {
	//最新数据更新
	var indexLasterData = {
		url : 'qt/latestDataUpdate.do',
		params : {
			
		},
		callback : function(json) {		
			if(json.success){
				var divstr='';
				$.each(json.titeList,function(idx,item){ 
				 divstr+=item.title+"<br/>";
				});
			   $('#lastDataUpdate').html(divstr);
			}
		}
	}
	ajaxpost(indexLasterData);
	//数据访问统计
	var indexVisterData = {
		url : 'qt/visitData.do',
		params : {
			
		},
		callback : function(json) {		
			$('#regUserNum').html(json.regUserNum);
			$('#visitNum').html(json.visitNum);
			$('#queryNum').html(json.queryNum);
			$('#downloadNum').html(json.downloadNum);
			$('#downloadAmount').html(json.downloadAmount);
		}
	}
	ajaxpost(indexVisterData);
	
	
});
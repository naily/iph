/**
 *前台新闻管理，二级新闻列表，三级新闻页面
 * */
$(document).ready(function() {	
	var data = {
		url : 'qt/newsList.do',
		params : {
			
		},
		callback : function(json) {
			if (json.success) {						
				$('#newsBrief').html(json.newsBrief);
				$('#indexRightNewsImg').html(json.imgStr);
				$("div#indexRightNewsImg img").css({"width":"180px","height":"120px"});
			} else {
				
			}
		}
	}
	ajaxpost(data);

});

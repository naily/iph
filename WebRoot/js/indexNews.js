$(document).ready(function() {
	
	/**
	 * 首页新闻
	 */
	var data = {
		url : 'qt/userLogin.do',
		params : {
			
		},
		callback : function(json) {
			if (json.success) {						
				$('#newsBrief').html('新闻内容新闻内容新闻内容新闻内容新内容1...');
				$('#indexRightNewsImg').html('<img src="images/yc33_2.jpg"/>');
			} else {
				
			}

		}
	}
	ajaxpost(data);

});

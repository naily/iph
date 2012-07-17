$(document).ready(function() {
	
	/**
	 * 首页新闻
	 */
	var data = {
		url : 'qt/indexPicNews.do',
		params : {
			
		},
		callback : function(json) {
			if (json.success) {						
				$('#newsBrief').html(json.newsBrief);
				$('#indexRightNewsImg').html(json.imgStr);
			} else {
				
			}

		}
	}
	ajaxpost(data);

});

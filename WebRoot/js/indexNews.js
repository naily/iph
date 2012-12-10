/**
 *首页新闻
 * */
$(document).ready(function() {	
	//列表新闻
	var indexNewsData = {
		url : 'qt/indexNewsList.do',
		params : {
			
		},
		callback : function(json) {		
			//var json=eval("("+jsonStr+")");//转换为json对象 
			var divstart='<div class="rightbox3_1_text_">';
			var divend='</div>';
			var divstr='';
			 divstr+=divstart;
			if (json.success) {	
			$.each(json.newsList,function(idx,item){ 
				/*if(idx==0){ 
				 return true;
			     } */			
			     divstr+='<a href="qt/newspreview.do?newsId='+item.newsId+'" target="_blank" class="index_a2" title="'+item.title+'">'+item.title+'</a>&nbsp;&nbsp;&nbsp;&nbsp;<i style="font-size:10px;">'+item.publishDate;
			     divstr+='</i><br />';
			     /*    if(idx<4 || (idx>4 && idx<9)){
			      divstr+='<br />';
			     }			    
			   	if(idx==4){			   
			   		divstr+=divend;
			   		divstr+=divstart;
			   	}	*/	   			   
			
			}) 
			    divstr+=divend;				   
				$('#rightbox3_1').html(divstr);
				
			}
		}
	}
	ajaxpost(indexNewsData);
	
	//展示空间新闻列表
	var indexShowSpaceNewsData = {
		url : 'qt/indexshowspacenews.do',
		params : {},
		callback : function(json) {		
			var divstart='<div class="rightbox3_1_text_">';
			var divend='</div>';
			var divstr = divstart;
			if (json.success) {	
				$.each(json.newsList,function(idx,item){ 
				     divstr+='<li><a href="qt/newspreview.do?newsId='+item.newsId+'" target="_blank" class="index_a2" title="'+item.title+'">'+item.title+'</a>&nbsp;&nbsp;&nbsp;&nbsp;<i style="font-size:10px;">'+item.publishDate;
				     divstr+='</i></li>';
				
				}) 
			    divstr+=divend;				   
				$('#zskj_news').html(divstr);
			}
		}
	}
	ajaxpost(indexShowSpaceNewsData);
	
	//图片新闻
	var picNewsData = {
		url : 'qt/indexPicNews.do',
		params : {},
		callback : function(json) {
			if (json.success) {						
				$('#newsBrief').html(json.newsBrief);
				$('#indexRightNewsImg').html(json.imgStr);
				$("div#indexRightNewsImg img").css({"width":"180px","height":"120px"});				
				$('#showPicNews').bind('click', function() {
				   location.href='qt/newspreview.do?newsId='+json.newsId;
				});
			}
		}
	}
	ajaxpost(picNewsData);

});

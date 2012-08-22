$(document).ready(function() {
$('#reportGrid').omGrid({
                title : '',
                dataSource : 'qt/loadUserCommentList.do',
                limit:20,
                height:625,
                method : 'POST' ,
                //showIndex : false,
                colModel : [ {header:'内容',   name:'content', width:'380'}, 
                              {header:'日期',   name:'commentDate',width:120 ,align : 'center' },
                         {header:'回复状态',   name:'cmtstatus',width:60 , renderer :function(val, rowData, rowIndex){
                                if (val == 1) {
                                    return '<span style="color:green;"><b>已回复</b></span>';
                                 }else{
                                    return '<span><b>否</b></span>' ;
                                 }
                             }},
                         {header:'操作',name:'operation',width:150 ,align : 'center' ,
	                         renderer: function(colValue, rowData, rowIndex){
	                         	if(rowData.cmtstatus){
	                         	  var id = rowData.id ;
	                         	  return '<a href="javascript:openCmtInfo(\''+id+'\');" >查看 </a>';
	                         	}else{
	                         	  return '';
	                         	}
	                         	
	                         		  
	                         }
                         }
                             ]
            });
           
           
     /**
	 *提交用户评论
	 */
	$('#userCommentSubmit').bind('click', function(){
		var userContent = $('#userCommentContent').val();
		if (userComment()) {
			var userCommentData = {
				url : 'qt/userComment.do',
				params : {
					content : userContent
				},
				callback : function(json) {
					if (json.success) {
						 $('#userCommentContent').attr({value:' '});
						 at({cont: '评论成功！' , type : 'alert'});
					} else {
						at({cont: '评论失败！' , type : 'error'});
					}
				 }
				}
			ajaxpost(userCommentData);
		}                    
   });
            
});
/** 
 * 用户评论
 * 1、必须登录，否则不能评论
 * 2、登陆后可以查看，以往评论及管理员回复。
 *  */
function userComment() {
	if(isUserLogin) {
		var commComment = $('#userCommentContent').val();
		if (!commComment) {
			//alertMsg('不能发表空内容');
			at({cont : '请输入您要发表的意见！',type : 'error'});
			return false;
		}
		return true;
	} else {
		at({cont : '请登录后，再发表意见！',type : 'error'});
		return false;
	}

}
/**
 * 打开评论回复窗口
 * @param {评论ID} uid
 */
function openCmtInfo(uid) {
	var data_1 = {
		url : 'qt/getUserComment.do',
		params : {id : uid},
		callback : function(json) {
			if (json.success) {
			
				/*var cid = json.obj.id ;
				$('#cmtcontent').html(json.obj.content);//
				$('#titinfo').html( '用户: '+ json.obj.userId + " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IP: " + json.obj.uerIP);
				$('#cid').val(json.obj.id) ;
                
                if(json.feedbackarray){
                    $('#cmtcontent').append('<p class="borderbg"> '+json.feedbackarray[0].adminId+'的回复： '+ json.feedbackarray[0].feedback+'</p>') ;
                }
                
				$("#qtcm_feedback").omDialog('open');*/
				 if(json.feedbackarray){
					at({cont : json.feedbackarray[0].feedback,type : 'alert',title:'管理员回复'});
				 }
			} else {
				
				at({cont : '访问出现错误！',type : 'error'});
			}

		}
	}
	ajaxpost(data_1);
}



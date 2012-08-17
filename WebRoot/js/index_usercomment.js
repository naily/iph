$(document).ready(function() {
$('#reportGrid').omGrid({
                title : '',
                dataSource : 'qt/loadUserCommentList.do?userId=',
                limit:20,
                height:625,
                showIndex : false,
                colModel : [ {header:'内容',   name:'content', width:'150'}, 
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
	                         	var id = rowData.id ;
	                         	return '<a href="javascript:openCmtInfo(\''+id+'\');" >查看 </a>';
	                         		  
	                         }
                         }
                             ]
            });
   }) ;
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




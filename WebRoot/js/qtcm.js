$(document).ready(function(){
    $('#list0').omGrid({
         width : '99.8%',
         method : 'POST' ,
         limit : pageslimit, //分页显示，每页显示8条
         //singleSelect : false, //出现checkbox列，可以选择同时多行记录
         colModel : [    {header:'用户名', name:'userId' , align : 'center', width:150},
                         {header:'内容',   name:'content', width:'autoExpand',
	                         renderer : function(val, rowData, rowIndex){
	                            if (val == '86') {
                                 	return '<span style="color:green;"><b>中国</b></span>';
	                             }else{
                                 	return val ;
                                 }
	                         } 
                         } ,
                         {header:'IP',    name:'uerIP', align : 'center',   width:100},
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
	                         	return '<a href="javascript:openCmtInfo(\''+id+'\');" >回复 </a>&nbsp;&nbsp;&nbsp;&nbsp;' +
	                         		   '<a href="javascript:deleteCmt(\''+id+'\');" >删除 </a>' ;
	                         }
                         }
         ],
         dataSource : 'ht/qtcmlist.do' 
     });
     
	$( "#qtcm_feedback").omDialog({
            autoOpen: false,
            resizable: false ,
            height:'auto' ,
            width : 400 ,
            title:'回复评论'
     });
     $('#saveFeedbackBut').omButton({
        label : '保  存' ,
        width : 150, 
        onClick : saveFeedback
	 });
    
});

function deleteCmt(id){
	var delt = {
        url : 'ht/delqtcm.do',
        params : {ids: id}  ,
        callback : function(json){
            if(json.success){
                $('#list0').omGrid('reload');
                tipOK() ;
            }else{
                at({cont: json.info , type : 'error'});
            }
        }
    }
    //提示
    $.omMessageBox.confirm({
        title:'删除',
        content:'删除用户,你确定要这样做吗?',
        onClose:function(value){
            if(value){
                ajaxpost(delt);
            }
        }
    });
}

/**
 * 打开评论回复窗口
 * @param {评论ID} uid
 */
function openCmtInfo(uid) {
	var data = {
		url : 'ht/getqtcm.do',
		params : {id : uid},
		callback : function(json) {
			if (json.success) {
				var cid = json.obj.id ;
				$('#cmtcontent').html(json.obj.content);//
				$('#titinfo').html( '用户: '+ json.obj.userId + " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IP: " + json.obj.uerIP);
				$('#cid').val(json.obj.id) ;
                
                if(json.feedbackarray){
                    $('#cmtcontent').append('<p class="borderbg"> '+json.feedbackarray[0].adminId+'的回复： '+ json.feedbackarray[0].feedback+'</p>') ;
                }
                
				$("#qtcm_feedback").omDialog('open');
			} else {
				showError(json.info);
			}

		}
	}
	ajaxpost(data);
}

/**
 * 保存对评论的回复
 */
function saveFeedback() {
    
	var data = {
		url : "ht/savefeedback.do",
		params : {
			commentId : '',
			feedback : ''
		},
		callback : function(json) {
			if (json.success) {
				//showWaiting(); 
                $("#qtcm_feedback").omDialog('close');
                $('#list0').omGrid('reload');
			} else {
				showError(json.info);

			}
		}
	}
    
    var fbc = $('#feedbackcontent').val();
    var cid = $('#cid').val() ;
    if(fbc && cid){
        data.params.feedback = fbc ;
        data.params.commentId = cid ;
        ajaxpost(data);
	    //alert(data.params.feedback);
    }else{
        at({cont: ' 回复内容不能为空' , type : 'error'});
    }
	
}

function showWaiting() {
	$.omMessageBox.waiting({
		title : '请等待',
		content : '服务器正在处理请求，请稍等...'
	});
}

function showError(v) {
	$.omMessageBox.alert({
		type : 'error',
		title : '失败',
		content : v,
		onClose : function(v) {
			// showResult('我知道了！');
		}
	});
}

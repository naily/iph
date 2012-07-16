$(document).ready(function(){

	var editor = $('#contentId').omEditor({
		width : 600 ,
		filebrowserImageUploadUrl : './omEditorImageUpload.do'

     });
	function clearfm (){
		$( '#contentId' ).omEditor('setData' , '') ;
		$('#title').val('') ;
		$('#isPicNews').removeAttr('checked') ;
	}
	//表单验证
     var notempty = "不能为空" ;
    $("#savenewsfm").validate({
        rules : {
          title : {required : true } 
        },
        messages : {
          title : {required : notempty }
        },
        submitHandler : function(){
            if( $( '#contentId' ).omEditor('getData') ){
                var data = {
                    url : 'ht/newsSave.do' ,
                    params : {title : $('#title').val(),
                              isPicNews: $('#isPicNews').attr('checked') ? true : false,
                              content : $( '#contentId' ).omEditor('getData')
                        },
                    callback : function(json){
                        if(json.success){
                            //$('#list0').omGrid('reload');
                        	clearfm() ;
                        	$.omMessageTip.show({
					                type:'success',
					                title:'提醒',
					                timeout : 3000 ,
					                content:'保存成功'
					        });
                        }else{
                            at({cont: json.info , type : 'error'});
                        }
                    }
                } 
                //alert(data.params.content) ;
	        	ajaxpost(data) ;
            }
        	return false;
                    //
                    //$(this)[0].currentForm.reset();
                    
        }

     });
});



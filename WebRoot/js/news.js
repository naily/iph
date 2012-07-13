$(document).ready(function(){

	var editor = $('#contentId').omEditor({
		width : 600 ,
		filebrowserImageUploadUrl : '../../../omEditorImageUpload.do?type=Images'
     });
	
	//表单验证
     var notempty = "不能为空" ;
    $("#savemetadata").validate({
        rules : {
          
          title : {required : true } ,
          content : {required : true } 
        },
        messages : {
          
          title : {required : notempty } ,
          content : {required : notempty } 
        },
        submitHandler : function(){
        	$("#savemetadata").omAjaxSubmit({
        		url : './ht/newsSave.do' ,
        		method:'POST' ,
        		resetForm: true ,
        		success : function(responseText, statusText, xhr, $form){
        			alert('提交成功！');
        		}
        	}) ;
        	return false;
                    //
                    //$(this)[0].currentForm.reset();
                    
        }

     });
});



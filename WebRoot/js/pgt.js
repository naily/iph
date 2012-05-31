$(document).ready(function(){
//初始化上传文件组件
	$('#file_upload').omFileUpload({
        action : '../ht/pgtuploads.do',
        swf : 'swf/om-fileupload.swf',
	  	fileExt  : '*.jpg;*.bmp',
	  	fileDesc : 'Image Files(*.jpg,*.bmp)' ,
	  	method   : 'GET',
        onComplete : function(ID,fileObj,response,data,event){
            alert('文件'+fileObj.name+'上传完毕');
        },
        onError :function(ID, fileObj, errorObj, event){
        	alert('文件'+fileObj.name+'上传失败。错误类型：'+errorObj.type+'。原因：'+errorObj.info);
        },
        onSelect:function(ID,fileObj,event){
        	alert('你选择了文件：'+fileObj.name);
        }
    });
    
    $('#reset').click(function(){
    	$('#file_upload').omFileUpload('cancel');
    	
    });
    $('#save').click(function(){
    	$('#file_upload').omFileUpload({'actionData':{'username':123}});
    	$('#file_upload').omFileUpload('upload');
    	
    });
});



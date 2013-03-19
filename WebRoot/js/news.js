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
    
    $('#comboCateg').omCombo({
        dataSource:[{text:'动态新闻' , value:1},{text:'相关文档' , value:2}] ,
        value : 1 ,
        width :100
    }) ;
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
                              content : $('#contentId' ).omEditor('getData') ,
                              category: $('#comboCateg').omCombo('value')
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
     
    //初始化单文件上传文件组件
	$('#file_upload').omFileUpload({
        action : '../ht/docfileuploads.do'+ "?timestamp=" + new Date().getTime() ,
        swf : 'swf/om-fileupload.swf' + "?timestamp=" + new Date().getTime() ,
	  	fileExt  : '*.rar;*.pdf;*.doc',
	  	fileDesc : 'Upload Files(*.rar,*.pdf,*.doc)' ,
	  	method   : 'POST',
        onComplete : function(ID,fileObj,response,data,event){
        	var jsonData = eval("("+response+")");
        	
        	//fileName = fileObj.name ;
        	//alert(jsonData.path) ;
        	//alert(fileName) ;
        	var pa = 'fn=' +jsonData.path + '&dn=' + encodeURI(encodeURI(fileObj.name)) ;
            $('#contentId').omEditor('insertHtml' , '<a target="blank" href="./ht/news/docdown.do?'+pa+'">'+fileObj.name+ '</a>') ;
        },
        onError :function(ID, fileObj, errorObj, event){
        	alert('文件'+fileObj.name+'上传失败。错误类型：'+errorObj.type+'。原因：'+errorObj.info);
        },
        onSelect:function(ID,fileObj,event){
        	//alert('你选择了文件：'+fileObj.name);
            //选择文件后立即上传
        	//$('#preview').attr('disabled' , false) ;
        	//$('#errormsg').html('') ;
        },
        removeCompleted : true ,
        autoUpload : true  //自动上传
    });
});

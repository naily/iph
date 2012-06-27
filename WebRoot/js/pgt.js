$(document).ready(function(){
	var fileName = '' ;
	//初始化单文件上传文件组件
	$('#file_upload').omFileUpload({
        action : '../ht/pgtuploads.do',
        swf : 'swf/om-fileupload.swf',
	  	fileExt  : '*.jpg;*.bmp',
	  	fileDesc : 'Image Files(*.jpg,*.bmp)' ,
	  	method   : 'POST',
        onComplete : function(ID,fileObj,response,data,event){
            //alert('文件'+fileObj.name+'上传完毕');
            //上传完毕才可以预览
        	var jsonData = eval("("+response+")");
        	$( "#imagePreview").html('<img src=".'+ jsonData.imgpath+'" border=0 height=500 / >');
        	
        	fileName = fileObj.name ;
        },
        onError :function(ID, fileObj, errorObj, event){
        	alert('文件'+fileObj.name+'上传失败。错误类型：'+errorObj.type+'。原因：'+errorObj.info);
        },
        onSelect:function(ID,fileObj,event){
        	//alert('你选择了文件：'+fileObj.name);
            //选择文件后立即上传
        	$('#preview').attr('disabled' , false) ;
        	$('#errormsg').html('') ;
        },
        //actionData : { 'action' :'fileupload' } ,
        removeCompleted : true ,
        autoUpload : true  //自动上传
    });
    
    //初始化多文件上传 
    $('#file_upload_more').omFileUpload({
        action : '../ht/pgtmulti.do',
        swf : 'swf/om-fileupload.swf',
        fileExt  : '*.jpg;*.bmp',
        fileDesc : 'Image Files(*.jpg,*.bmp)' ,
        method   : 'POST',
        onComplete : function(ID,fileObj,response,data,event){
            //上传完毕才可以预览
            var jsonData = eval("("+response+")");
            //$( "#imagePreview").html('<img src=".'+ jsonData.imgpath+'" border=0 height=500 / >');
            if(jsonData.success ){
	            alert('文件'+fileObj.name+'上传完毕');
                
            }else{
	            alert(jsonData.info);
                
            }
        },
        onError :function(ID, fileObj, errorObj, event){
            alert('文件'+fileObj.name+'上传失败。错误类型：'+errorObj.type+'。原因：'+errorObj.info);
        },
        onSelect:function(ID,fileObj,event){
            //alert('你选择了文件：'+fileObj.name);
            //选择文件后立即上传
            //$('#errormsg2').html('') ;
        },
        //actionData : { 'action' :'fileupload' } ,
        multi : true ,
        removeCompleted : true ,
        autoUpload : true  //自动上传
    });
    
    $('#reset').click(function(){
    	$('#errormsg').html('') ;//清除错误提示
    	$('#pgtTitleId').val('') ;
    	$('#file_upload').omFileUpload('cancel');
    	
    });
    //保存表单
    $('#save').click(function(){
        
    	if(fileName == ''){
    		$('#errormsg').html('请选择频高图文件').show();
    		return false ;
    	}else{
    		
    		if( !$('#pgtTitleId').val()){
    			$('#errormsg').html('请输入频高图标题').show();
    			return false ;
    		}
    		
    		var data = {
	            url :'ht/pgtuploads.do' ,
	            params :{'stationID' :$('#comboStation').omCombo('value') ,
        				 'gramTitle' : $('#pgtTitleId').val() ,
        				 'createDate': $.omCalendar.formatDate($('#actionDateId').omCalendar('getDate'), 'yy-mm-dd') ,
        				 'type'      : $('#comboPgtType').omCombo('value'),
        				 'gramFileName':fileName ,
        				 'action'      : 'savedata'},
	            callback : function(json){
	                if(json.success){
	                	$('#errormsg').html('') ;//清除错误提示
	                	$('#pgtTitleId').val('') ;
	                	$('#file_upload').omFileUpload('cancel');
	                	$.omMessageTip.show({
			                type:'success',
			                title:'提醒',
			                timeout : 3000 ,
			                content:'保存成功'
			            });
	                }else{
	                    $('#errormsg').html(json.info).show();
	                }
	                
	            }
	        }
	        
	        ajaxpost(data);
    	}
    	
    });
    
    //观测站下拉框
    $('#comboStation').omCombo({
        dataSource:'ht/stationlistall.do' ,
        valueField : 'id' ,
        optionField :'name' ,
        value: 'WHA'
    }) ;
    //频高徒类型下拉框
    $('#comboPgtType').omCombo({
        dataSource:[{text:'手动',value:'1'},{text:'胶版',value:'2'},{text:'数字',value:'3'},{text:'打印',value:'4'}] ,
        valueField : 'value' ,
        optionField :'text',
        value:'1'
        
    }) ;
    //观测日期
    $('#actionDateId').omCalendar();
    
    //图片预览弹出
    $( "#imagePreview").omDialog({
        autoOpen: false,
        height: 'auto' ,
        width :'auto'
    });
	//预览按钮
    $('#preview').click(function(){
    	//$('#file_upload').omFileUpload( {'actionData':{'action':'fileupload' } } );
        //$('#file_upload').omFileUpload('upload');
        $( "#imagePreview").omDialog('open');
    	
	});
    $('#preview').attr('disabled' , true) ;
    
    
    //选项卡
    $('#make-tab').omTabs({
                //width : 620,
                height : 500,
                lazyLoad : false
    });
});



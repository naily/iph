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
            //上传完毕才可以预览
        },
        onError :function(ID, fileObj, errorObj, event){
        	alert('文件'+fileObj.name+'上传失败。错误类型：'+errorObj.type+'。原因：'+errorObj.info);
        },
        onSelect:function(ID,fileObj,event){
        	//alert('你选择了文件：'+fileObj.name);
            //选择文件后立即上传
        	$('#file_upload').omFileUpload( {'actionData':{'action':'fileupload' } } );
        	$('#file_upload').omFileUpload('upload');
        }
    });
    
    $('#reset').click(function(){
    	$('#file_upload').omFileUpload('cancel');
    	
    });
    //保存表单
    $('#save').click(function(){
        
    	$('#file_upload').omFileUpload({'actionData':{'stationID':$('#comboStation').omCombo('value') ,
        'gramTitle': $('#pgtTitleId').val() ,
        'createDate': $.omCalendar.formatDate($('#actionDateId').omCalendar('getDate'), 'yy-mm-dd') ,
        'type': $('#comboPgtType').omCombo('value')}});
    	
        $('#file_upload').omFileUpload('upload');
    	
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
    
});



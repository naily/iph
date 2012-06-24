$(document).ready(function(){
    //选项卡
    $('#make-tab').omTabs({
                //width : 620,
                height : 480,
                lazyLoad : false
    });
    //观测站下拉框
    $('#comboStation').omCombo({
        dataSource:'ht/stationlistall.do' ,
        valueField : 'id' ,
        optionField :'name' ,
        value: 'WHA'
    }) ;
    //观测日期
    $('#actionDate').omCalendar();
    
    $('#ip1').omNumberField();
	$('#ip2').omNumberField();
	$('#ip3').omNumberField();
	$('#ip4').omNumberField();
	$('#ip5').omNumberField();
	$('#ip6').omNumberField();
	$('#ip7').omNumberField();
	$('#ip8').omNumberField();
	$('#ip9').omNumberField();
	$('#ip10').omNumberField();
    
    //批量导入文件
    $('#file_upload_more').omFileUpload({
        action : '../ht/zz.do',
        swf : 'swf/om-fileupload.swf',
        fileExt  : '*.mdb',
        fileDesc : 'Microsoft Access Database(*.mdb)' ,
        method   : 'POST',
        onComplete : function(ID,fileObj,response,data,event){
            //alert('文件'+fileObj.name+'上传完毕');
            //上传完毕才可以预览
            var jsonData = eval("("+response+")");
        },
        onError :function(ID, fileObj, errorObj, event){
            alert('文件'+fileObj.name+'上传失败。错误类型：'+errorObj.type+'。原因：'+errorObj.info);
        },
        onSelect:function(ID,fileObj,event){
            //alert('你选择了文件：'+fileObj.name);
            //选择文件后立即上传
        },
        actionData : { 'action' :'fileupload' } ,
        multi : true ,
        autoUpload : true  //自动上传
    });
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
});



$(document).ready(function(){
    $('#yuzhongCombox').omCombo({
                dataSource : [ {text : 'zh_CN', value : 'zh_CN'}, 
                               {text : 'en_US', value :  'en_US'}] ,
               editable : false,
               emptyText : '选择语种',
               value : 'zh_CN'
     });
     $('#timeInterval').omCombo({
//                dataSource : [ {text : '年', value : '01'}, 
//                               {text : '半年', value :  '02'},
//                               {text : '季度', value :  '03'},
//                               {text : '双月', value :  '04'},
//                               {text : '月', value :  '05'},
//                               {text : '旬', value :  '06'},
//                               {text : '周', value :  '07'},
//                               {text : '日', value :  '08'},
//                               {text : '小时', value :  '09'},
//                               {text : '半小时', value :  '10'},
//                               {text : '分钟', value :  '11'},
//                               {text : '半分钟', value :  '12'},
//                               {text : '秒', value :  '13'},
//                               {text : '其他', value :  '99'}] ,
                dataSource : [ {text : '年', value : '年'}, 
                               {text : '半年', value :  '半年'},
                               {text : '季度', value :  '季度'},
                               {text : '双月', value :  '双月'},
                               {text : '月', value :  '月'},
                               {text : '旬', value :  '旬'},
                               {text : '周', value :  '周'},
                               {text : '日', value :  '日'},
                               {text : '小时', value :  '小时'},
                               {text : '半小时', value :  '半小时'},
                               {text : '分钟', value :  '分钟'},
                               {text : '半分钟', value :  '半分钟'},
                               {text : '秒', value :  '秒'},
                               {text : '其他', value :  '其他'}] ,
               editable : false,
               emptyText : '采样间隔' 
     });
    $('#srcType').omCombo({
                dataSource : [ {text : '仪器监测', value : '仪器监测'} ,
                			   {text : '处理分析', value :  '处理分析'},
                               {text : '模型模拟', value :  '模型模拟'},
                               {text : '合作交换/镜像', value :  '合作交换'},
                               {text : '网络收集', value :  '网络收集'},
                               {text : '其他', value :  '其他'}] ,
               editable : false,
               emptyText : '选择数据来源' 
     });
    $('#serviceType').omCombo({
                /*dataSource : [ {text : '在线服务', value : '01'},
                			   {text : '文件下载', value :  '0101'},
                               {text : '数据查询', value :  '0102'},
                               {text : 'FTP下载', value :  '0103'},
                               {text : '数据访问接口', value :  '0104'},
                               {text : '在线其他服务', value :  '0199'},
                               {text : '离线服务', value :  '02'},
                               {text : '其他', value :  '99'}] ,*/
                               
               dataSource : [ {text : '在线服务', value : '在线服务'},
                			   {text : '文件下载', value :  '文件下载'},
                               {text : '数据查询', value :  '数据查询'},
                               {text : 'FTP下载', value :  'FTP下载'},
                               {text : '数据访问接口', value :  '数据访问接口'},
                               {text : '在线其他服务', value :  '在线其他服务'},
                               {text : '离线服务', value :  '离线服务'},
                               {text : '其他', value :  '其他'}] ,
               editable : false,
               emptyText : '选择数据服务类型' ,
               onValueChange:function(target,newValue,oldValue,event){
               		if('在线服务' == newValue || '文件下载' == newValue || 'FTP下载' == newValue){
               			$('#serviceLink').html('地址:<input name="serviceLink" type="text" class="boxinput3" /> e.g:http://www.baidu.com');
               		}else{
               			$('#serviceLink').html('');
               		}
               }
     });
     $('#accessClass').omCombo({
//                dataSource : [ {text : '公开级', value : '001'},
//				                {text : '限制级', value : '002'},
//				                {text : '秘密级', value : '003'},
//				                {text : '机密级', value : '004'},
//				                {text : '绝密级', value : '005'}] ,
                dataSource : [ {text : '公开级', value : '公开级'},
				                {text : '限制级', value : '限制级'},
				                {text : '秘密级', value : '秘密级'},
				                {text : '机密级', value : '机密级'},
				                {text : '绝密级', value : '绝密级'}] ,
               editable : false,
               emptyText : '选择安全限制级别' 
     });
    
    //表单验证
     var notempty = "不能为空" ;
     $("#savemetadata").validate({
        rules : {
          datId : {required : true } ,
          resTitle : {required : true } ,
          engTitle : {required : true } ,
          keyword : {required : true } ,
          keywordEng : {required : true } ,
          abstract1 : {required : true } ,
          abstract1Eng : {required : true } ,
          lastAlterDate : {required : true } ,
          yuzhongCombox : {required : true } ,
//          cateCode : {required : true } ,
          
          posDes : {required : true } ,
          begDate : {required : true } ,
          endDate : {required : true } ,
          dataQuant : {required : true } ,
          siteName : {required : true } ,
          
          instName : {required : true } ,
          dataFormat : {required : true } ,
          srcType : {required : true } ,
          serviceType : {required : true } ,
          prdContact : {required : true } ,
          dissContact : {required : true } ,
          thumbnailFilePath : {required : true } 
        },
        messages : {
          datId : {required : notempty } ,
          resTitle : {required : notempty } ,
          engTitle : {required : notempty } ,
          keyword : {required : notempty } ,
          keywordEng : {required : notempty } ,
          abstract1 : {required : notempty } ,
          abstract1Eng : {required : notempty } ,
          lastAlterDate : {required : notempty } ,
          yuzhongCombox : {required : notempty } ,
          
          posDes : {required : notempty } ,
          begDate : {required : notempty } ,
          endDate : {required : notempty } ,
          siteName : {required : notempty } ,
          instName : {required : notempty } ,
          dataQuant : {required : notempty } ,
          
          dataFormat : {required : notempty } ,
          srcType : {required : notempty } ,
          prdContact : {required : notempty } ,
          serviceType : {required : notempty } ,
          dissContact : {required : notempty },
          thumbnailFilePath : {required : "请上传缩略图"} 
        },
        submitHandler : function(){
        	//$(this)[0].currentForm.action = "./ht/medSave.do" ;
        	//alert($(this)[0].currentForm.action) ;
        	$("#savebut").attr("disabled" , "disabled"); //禁用提交按钮
            var posturl = './ht/medSave.do' ;
            var mdid = $('#mdid').val()  ;
            if( mdid ){
                posturl = './ht/medUpdate.do' ;
            }
            
            
        	$("#savemetadata").omAjaxSubmit({
        		url : posturl ,
        		method:'POST' ,
        		resetForm: true ,
        		dataType :'json' ,
        		success : function(responseText, statusText, xhr, $form){
        			var s = '保存成功' ;
        			if(responseText.success){
        			}else{
        				s = (responseText.info) ;
        			}
    				$("#errormsg").html(s).show() ;
        			$.omMessageTip.show({
			                type:'success',
			                title:'提醒',
			                timeout : 3000 ,
			                content:s
			        });
                    if( mdid ){
                        $("#errormsg").html('').hide();
		                $( "#tab1").omDialog('close');
		            }
		            
		            $("#savebut").removeAttr("disabled" );
        		}
        	}) ;
        	return false;
                    
        }

     });
	
     initfileupload() ;
    
    
    
    
    
    
});

//初始化缩略图上传组件
function initfileupload(){
	$('#uploadpic').omFileUpload({
        action : '../ht/uploadthumbnail.do',
        swf : 'swf/om-fileupload.swf',
        fileExt  : '*.jpg;*.bmp',
	  	fileDesc : 'Image Files(*.jpg,*.bmp)' ,
        onComplete : function(ID,fileObj,response,data,event){
            var json = eval("("+response+")");
            if(json.success){
            	$("#thumbnailFilePath").val(json.info) ;
            	$("#imgpath") .attr("src", "./" + json.info ) ;
            	$("#imgpath") .show() ;
            }else{
            	$("#thumbnailFilePath").val("") ;
            }
            $("#savemetadata").validate().element( "#thumbnailFilePath" );
        	$("#errormsg").html("文件上传: "+ json.info).show() ;
        },
        onError :function(ID, fileObj, errorObj, event){
            alert('文件'+fileObj.name+'上传失败。错误类型：'+errorObj.type+'。原因：'+errorObj.info);
        },
        autoUpload : true  //自动上传
    });
}

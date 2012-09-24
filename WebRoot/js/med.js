$(document).ready(function(){
    $('#yuzhongCombox').omCombo({
                dataSource : [ {text : 'zh_CN', value : 'zh_CN'}, 
                               {text : 'en_US', value :  'en_US'}] ,
               editable : false,
               emptyText : '选择语种',
               value : 'zh_CN'
     });
     $('#timeInterval').omCombo({
                dataSource : [ {text : '年', value : '01'}, 
                               {text : '半年', value :  '02'},
                               {text : '季度', value :  '03'},
                               {text : '双月', value :  '04'},
                               {text : '月', value :  '05'},
                               {text : '旬', value :  '06'},
                               {text : '周', value :  '07'},
                               {text : '日', value :  '08'},
                               {text : '小时', value :  '09'},
                               {text : '半小时', value :  '10'},
                               {text : '分钟', value :  '11'},
                               {text : '半分钟', value :  '12'},
                               {text : '秒', value :  '13'},
                               {text : '其他', value :  '99'}] ,
               editable : false,
               emptyText : '采样间隔' 
     });
    $('#srcType').omCombo({
                dataSource : [ {text : '仪器监测', value : '01'} ,
                			   {text : '处理分析', value :  '02'},
                               {text : '模型模拟', value :  '03'},
                               {text : '合作交换/镜像', value :  '04'},
                               {text : '网络收集', value :  '05'},
                               {text : '其他', value :  '99'}] ,
               editable : false,
               emptyText : '选择数据来源' 
     });
    $('#serviceType').omCombo({
                dataSource : [ {text : '在线服务', value : '01'},
                			   {text : '文件下载', value :  '0101'},
                               {text : '数据查询', value :  '0102'},
                               {text : 'FTP下载', value :  '0103'},
                               {text : '数据访问接口', value :  '0104'},
                               {text : '在线其他服务', value :  '0199'},
                               {text : '离线服务', value :  '02'},
                               {text : '其他', value :  '99'}] ,
               editable : false,
               emptyText : '选择数据服务类型' 
     });
     $('#accessClass').omCombo({
                dataSource : [ {text : '公开级', value : '001'},
                {text : '限制级', value : '002'},
                {text : '秘密级', value : '003'},
                {text : '机密级', value : '004'},
                {text : '绝密级', value : '005'}] ,
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
          abstract1 : {required : true } ,
          cateName : {required : true } ,
          cateCode : {required : true } ,
          
          cateStd : {required : true } ,
          lftBtmCoord : {required : true } ,
          rightTopCoord : {required : true } ,
          equScale : {required : true } ,
          gridSize : {required : true } ,
          
          topVer : {required : true } ,
          lowVer : {required : true } ,
          begDate : {required : true } ,
          endDate : {required : true } ,
          timePoint : {required : true } ,
          //timeInterval : {required : true } ,
          //srcType : {required : true } ,
          siteID : {required : true } ,
          siteName : {required : true } ,
          sitePosDesc : {required : true } ,
          siteContact : {required : true } ,
          instID : {required : true } ,
          instName : {required : true } ,
          instCat : {required : true } ,
          instSpec : {required : true } ,
          methodName : {required : true } ,
          originalDesc : {required : true } ,
          prdContact : {required : true } ,
          
          dataFormat : {required : true } ,
          formatVersion : {required : true } ,
          //serviceType : {required : true } ,
          paraName : {required : true } ,
          paraValue : {required : true } ,
          //accessClass : {required : true } ,
          copyRightDesc : {required : true } ,
          citation : {required : true } ,
          
          dissContact : {required : true } ,
          pointLong : {required : true } ,
          pointLat : {required : true } ,
          orgName : {required : true } ,
          thumbnailFilePath : {required : true} ,
          dataType : {required : true }
        },
        messages : {
          datId : {required : notempty } ,
          resTitle : {required : notempty } ,
          engTitle : {required : notempty } ,
          keyword : {required : notempty } ,
          abstract1 : {required : notempty } ,
          cateName : {required : notempty } ,
          cateCode : {required : notempty } ,
          
          cateStd : {required : notempty } ,
          lftBtmCoord : {required : notempty } ,
          rightTopCoord : {required : notempty } ,
          equScale : {required : notempty } ,
          gridSize : {required : notempty } ,
          
          topVer : {required : notempty } ,
          lowVer : {required : notempty } ,
          begDate : {required : notempty } ,
          endDate : {required : notempty } ,
          timePoint : {required : notempty } ,
          //timeInterval : {required : notempty } ,
          //srcType : {required : notempty } ,
          siteID : {required : notempty } ,
          siteName : {required : notempty } ,
          sitePosDesc : {required : notempty } ,
          siteContact : {required : notempty } ,
          instID : {required : notempty } ,
          instName : {required : notempty } ,
          instCat : {required : notempty } ,
          instSpec : {required : notempty } ,
          methodName : {required : notempty } ,
          originalDesc : {required : notempty } ,
          prdContact : {required : notempty } ,
          
          dataFormat : {required : notempty } ,
          formatVersion : {required : notempty } ,
          //serviceType : {required : notempty } ,
          paraName : {required : notempty } ,
          paraValue : {required : notempty } ,
          //accessClass : {required : notempty } ,
          copyRightDesc : {required : notempty } ,
          citation : {required : notempty } ,
          
          dissContact : {required : notempty } ,
          pointLong : {required : notempty } ,
          pointLat : {required : notempty } ,
          orgName : {required : notempty } ,
          thumbnailFilePath : {required : "请上传缩略图"} ,
          dataType : {required : notempty }
        },
        submitHandler : function(){
        	//$(this)[0].currentForm.action = "./ht/medSave.do" ;
        	//alert($(this)[0].currentForm.action) ;
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

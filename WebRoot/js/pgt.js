$(document).ready(function(){
	var fileName = '' ;
	//初始化单文件上传文件组件
	$('#file_upload').omFileUpload({
        action : '../ht/pgtuploads.do'+ "?timestamp=" + new Date().getTime() ,
        swf : 'swf/om-fileupload.swf' + "?timestamp=" + new Date().getTime() ,
	  	fileExt  : '*.jpg;*.bmp',
	  	fileDesc : 'Image Files(*.jpg,*.bmp)' ,
	  	method   : 'POST',
        onComplete : function(ID,fileObj,response,data,event){
            //alert('文件'+fileObj.name+'上传完毕');
            //上传完毕才可以预览
        	var jsonData = eval("("+response+")");
        	$( "#imagePreview").html('<img src=".'+ jsonData.imgpath+'" border=0 height=500 / >');
        	
        	//上传结束通过文件名解析出来参数
        	fileName = fileObj.name ;
            if(fileName && fileName.length >= 10){
            	/**
            	 * 从文件名中分别检索 "wh" , "WU430_"
            	 */
            	var d ;
            	if(fileName.indexOf("WU430_") != -1){
            		$('#comboStation').omCombo('value', "WU430")  ;
            		d = $.omCalendar.parseDate(fileName.substring(6 , fileName.indexOf(".", 6)) , "yymmddHi") ;
            	}else if(fileName.indexOf("wh") != -1){
					$('#comboStation').omCombo('value', "WU430")  ;
					d = $.omCalendar.parseDate(fileName.substring(2 , fileName.indexOf(".", 2)) , "yymmddH") ;
					d.setMinutes(0) ;
            	}
                //$('#comboStation').omCombo('value', fileName.substring(0,2) )  ;
                $('#pgtTitleId').val( fileName ) ;
                if(d){
                	$('#actionDateId').omCalendar('setDate' , d) ;
                	$('#actionDateId').val( $.omCalendar.formatDate( d , "yy-mm-dd H:i") );
                }
                
                //手动频高图日期范围
                var pt1min = new Date() ; 
                pt1min.setFullYear(1946) ;
                pt1min.setMonth(0) ; //1月
                var pt1max = new Date() ; 
                pt1max.setFullYear(1956) ;
                pt1max.setMonth(11) ; //12月
                
                //胶片类型
                var pt2min = new Date() ; 
                pt2min.setFullYear(1957) ;
                pt2min.setMonth(5) ; //6
                var pt2max = new Date() ; 
                pt2max.setFullYear(1991) ;
                pt2max.setMonth(9) ; //9
                
                if(pt1max >= d && pt1min <=d){
                	$('#comboPgtType').omCombo('value', 1) ;
                }
                
                if(pt2max >= d && pt2min <=d){
                	$('#comboPgtType').omCombo('value', 2) ;
                }
            }
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
    
    function multiinfo() {
        this.ok = 0; //成功
        this.err2 = 0 ;//文件已存在
        this.err3 = 0 ;//文件名无法解析
        this.err4 = 0 ; //程序异常
    }
    var tmp ;
    //初始化多文件上传 
    $('#file_upload_more').omFileUpload({
        action : '../ht/pgtmulti.do' + "?timestamp=" + new Date().getTime() ,
        swf : 'swf/om-fileupload.swf'+ "?timestamp=" + new Date().getTime() ,
        fileExt  : '*.jpg;*.bmp',
        fileDesc : 'Image Files(*.jpg,*.bmp)' ,
        method   : 'POST',
        onComplete : function(ID,fileObj,response,data,event){
            //上传完毕才可以预览
            var jsonData = eval("("+response+")");
            //$( "#imagePreview").html('<img src=".'+ jsonData.imgpath+'" border=0 height=500 / >');
            if(jsonData.success ){
	            //alert('文件'+fileObj.name+'上传完毕');
                tmp.ok += 1 ; 
            }else{
	            //alert(jsonData.info);
                if(jsonData.error ==2){
                    tmp.err2 += 1 ;
                }
                if(jsonData.error ==3){
                    tmp.err3 += 1 ;
                }
                if(jsonData.error ==4){
                    tmp.err4 += 1 ;
                }
            }
            
        },
        onAllComplete:function(data,event){
           /*var s = "<p>提交文件总数：" + (tmp.ok +tmp.err2 +tmp.err3 +tmp.err4) + ', 成功:'+tmp.ok +', 失败：'+(tmp.err2 +tmp.err3 +tmp.err4) 
           + '</p><p>失败原因：文件已存在['+tmp.err2+ '], 文件名无法解析[' + tmp.err3 +'], 程序异常['+tmp.err4 + ']</p>'; */
           //$('#msgtip').html(s) ;
           $('#ce1').html( (tmp.ok +tmp.err2 +tmp.err3 +tmp.err4) ) ;
           $('#ce2').html( tmp.ok ) ;
           $('#ce3').html( (tmp.err2 +tmp.err3 +tmp.err4)  ) ;
           $('#ce4').html( tmp.err2 ) ;
           $('#ce5').html( tmp.err3 ) ;
           $('#ce6').html( tmp.err4 ) ;
           $('#msgtable').show() ;
        } ,
        onError :function(ID, fileObj, errorObj, event){
            alert('文件'+fileObj.name+'上传失败。错误类型：'+errorObj.type+'。原因：'+errorObj.info);
        },
        onSelect:function(ID,fileObj,event){
            //alert('你选择了文件：'+fileObj.name);
            //选择文件后立即上传
            $('#msgtip').html('') ;
            tmp = new multiinfo() ;
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
	            params :{'stationID' : $('#comboStation').omCombo('value') ,
        				 'gramTitle' : $('#pgtTitleId').val() ,
        				 'createDate': $('#actionDateId').val() ,
        				 'type'      : $('#comboPgtType').omCombo('value'),
        				 'gramFileName': fileName ,
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
	        //alert(data.params.createDate) ;
	        ajaxpost(data);
    	}
    	
    });
    
    //观测站下拉框
    $('#comboStation').omCombo({
        dataSource:'ht/stationlistall.do' ,
        valueField : 'id' ,
        optionField :'name'  
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



$(document).ready(function(){
    var fileName = '' ;
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
        optionField :'name' 
    }) ;
    //观测日期
    $('#actionDate').omCalendar();
    
    function multiinfo() {
        this.ok = 0; //成功
        this.err2 = new Array() ;//文件已存在
        this.err3 = new Array() ;//文件名无法解析
        this.err4 = new Array() ; //程序异常
    }
    var tmp ;
    //初始化多文件上传 
    $('#file_upload_more').omFileUpload({
        action : '../ht/sacmulti.do' + "?timestamp=" + new Date().getTime() ,
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
                    //tmp.err2 += 1 ;
                	tmp.err2.push(fileObj.name) ;
                }
                if(jsonData.error ==3){
                    //tmp.err3 += 1 ;
                    tmp.err3.push(fileObj.name) ;
                }
                if(jsonData.error ==4){
                    //tmp.err4 += 1 ;
                    tmp.err4.push(fileObj.name) ;
                }
            }
            
        },
        onAllComplete:function(data,event){
           /*var s = "<p>提交文件总数：" + (tmp.ok +tmp.err2 +tmp.err3 +tmp.err4) + ', 成功:'+tmp.ok +', 失败：'+(tmp.err2 +tmp.err3 +tmp.err4) 
           + '</p><p>失败原因：文件已存在['+tmp.err2+ '], 文件名无法解析[' + tmp.err3 +'], 程序异常['+tmp.err4 + ']</p>'; */
           //$('#errormsg2').html(s) ;
           
           $('#ce1').html( (tmp.ok +tmp.err2.length +tmp.err3.length +tmp.err4.length) ) ;
           $('#ce2').html( tmp.ok ) ;
           $('#ce3').html( (tmp.err2.length +tmp.err3.length +tmp.err4.length)  ) ; 
           $('#ce4').html( tmp.err2.length + (tmp.err2.length > 0 ? "&nbsp;<a href='javascript:openMultiinfo()'>详细</a>" : "")) ;
           $('#ce5').html( tmp.err3.length + (tmp.err3.length > 0 ? "&nbsp;<a href='javascript:openMultiinfo()'>详细</a>" : "" )) ;
           $('#ce6').html( tmp.err4.length + (tmp.err4.length > 0 ? "&nbsp;<a href='javascript:openMultiinfo()'>详细</a>" : "" )) ;
           $('#msgtable').show() ;
           if(tmp.err2.length > 0){
           	$('#errorfilename').append("<p>文件已存在<br/>"+tmp.err2.join("<br />") +"</p>") ;
           }
           if(tmp.err3.length > 0){
           	$('#errorfilename').append("<p>文件名无法解析<br/>"+tmp.err3.join("<br />") +"</p>") ;
           }
           if(tmp.err4.length > 0){
           	$('#errorfilename').append("<p>程序异常<br/>"+tmp.err4.join("<br />") +"</p>") ;
           }
        } ,
        onError :function(ID, fileObj, errorObj, event){
            alert('文件'+fileObj.name+'上传失败。错误类型：'+errorObj.type+'。原因：'+errorObj.info);
        },
        onSelect:function(ID,fileObj,event){
            //alert('你选择了文件：'+fileObj.name);
            //选择文件后立即上传
            $('#errormsg2').html('') ;
            $('#errorfilename').html('') ;
            tmp = new multiinfo() ;
        },
        //actionData : { 'action' :'fileupload' } ,
        multi : true ,
        removeCompleted : true ,
        autoUpload : true  //自动上传
    });
    
    $('#file_upload').omFileUpload({
        action : '../ht/sacsingsave.do'+ "?timestamp=" + new Date().getTime() ,
        swf : 'swf/om-fileupload.swf'  + "?timestamp=" + new Date().getTime() ,
	  	fileExt  : '*.jpg;*.bmp',
	  	fileDesc : 'Image Files(*.jpg,*.bmp)' ,
	  	method   : 'POST',
        onComplete : function(ID,fileObj,response,data,event){
            //alert('文件'+fileObj.name+'上传完毕');
        	var jsonData = eval("("+response+")");
        	$( "#imagePreview").html('<img src=".'+ jsonData.imgpath+'" border=0 height=500 / >');
        	
        	fileName = fileObj.name ;
            if(fileName && fileName.length >= 10){
                //$('#comboStation').omCombo('value', fileName.substring(0,5) )  ;
                $('#sacTitleId').val( fileName ) ;
               
                //$('#actionDate').val( $.omCalendar.formatDate($.omCalendar.parseDate(fileName.substring(6,18) , "yymmddHi") , "yy-mm-dd H:i") ); 
                $('#actionDate').val( $.omCalendar.formatDate($.omCalendar.parseDate(fileName.substring(0,8) , "yymmdd") , "yy-mm-dd") );
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
    
    //图片预览弹出
    $( "#imagePreview").omDialog({
        autoOpen: false,
        height: 'auto' ,
        width :'auto'
    });
    //预览按钮
    $('#preview').click(function(){
        $( "#imagePreview").omDialog('open');
        
    });
    $('#preview').attr('disabled' , true) ;
    
    //保存表单
    $('#savebut').click(function(){
        if(fileName == ''){
            $('#errormsg').html('请选择报表扫描图文件').show();
            return false ;
        }else{
            
            if( !$('#sacTitleId').val()){
                $('#errormsg').html('请输入报表扫描图标题').show();
                return false ;
            }
            
            if( !$('#comboStation').omCombo('value')){
            	$('#errormsg').html('请选择观测站').show();
                return false ;
            }
            
            var save = {
                url :'ht/sacsingsave.do' ,
                params :{'stationID' :$('#comboStation').omCombo('value') ,
                         'scanPicTitle' : $('#sacTitleId').val() ,
                         'createDate':$('#actionDate').val(),
                         'scanPicFileName':fileName ,
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
            ajaxpost(save);
        }
        
    });
    
    
    $('#clearbut').click(function(){
        $('#errormsg').html('') ;//清除错误提示
        $('#pgtTitleId').val('') ;
        $('#file_upload').omFileUpload('cancel');
        
    });
    
    
    //错误的文件名
    $( "#errorfilename" ).omDialog({
            autoOpen : false, 
            resizable: false,
            height:200,
            title : '上传失败的文件' ,
            modal: false,
            buttons: [{
                text : "关闭", 
                click : function () {
                    $("#errorfilename" ).omDialog("close");
                }
            }]
    }) ;
    
    
    
    
    //解析服务器目录
    $('#savebyserverdir').click(function(){
        alert('请测试目录路径');
    });
    
    $('#testdir').click(function(){
        function altmsg(v , msg){
            if(!v){
                alert(msg) ;
            }
        }
        //检测必要输入参数
        var ssd = $('#station_serverdir').omCombo('value') ;//观测站
        var fw = $('#fileway').omCombo('value') ;//复制还是剪切
        //var dt = $('#dataTypeCombox').omCombo('value') ;//数据类型决定解析规则
        var ff = $('#fileprefix').val() ;//文件名前缀
        var fsd = $('#file_serverdir').val() ;//路径
        if(!fsd){
            alert( '请填写文件目录路径') ;
            return ;
        }
        if(!ssd){
            alert( '请选择观测站') ;
            return ;
        }
        if(!fw){
            alert( '请选择处理方式') ;
            return ;
        }
//        if(!dt){
//            alert( '请选择数据类型') ;
//            return ;
//        }
        if(!ff){
            alert( '请填写文件名前缀') ;
            return ;
        }
        
        var data = {
                url :'ht/sactestserverpath.do' ,
                params :{'path' : fsd , 'sid': ssd,'fileway': fw ,   'fileprefix' : ff},
                callback : function(json){
                    if(json.success){
                        //显示测试信息
                        var html = new Array();
                        html.push("<p><b>测试结果</b>") ;
                        html.push("<p>频高图解析方式： "+ ('1' == json.pgttype ? '(手动)' : '(胶片)')) ;
                        html.push("<p>年份数： "+json.yearTotal ) ;
                        html.push("<p>年份名称： "+json.info ) ;
                        html.push("<p>文件总数： "+json.fileTotal ) ;
                        html.push("<p><b>核对以上信息，若正确请点击‘保存’按钮继续操作</b>") ;
                        $('#reviewinfo-id').html(html.join('</p>')).removeClass() ;
                        //alert(html.join('</p>')) ;
                        
                        //$('#year_serverdir').val(json.year);
                        
                        //$('#station_serverdir').omCombo('value' , json.stationid) ;
                        $("#savebyserverdir").unbind( "click" ) ;
                        $('#savebyserverdir').click(function(){
                            //$("#reviewinfo-id").html("<img src='images/waiting.gif' width=100 border=0><p>正在导入...</p>").addClass('aligncenter');
                            var data1 = {
                                url :'ht/sacsaveserverpath.do' ,
                                params :{'path' : json.path ,'fileway': fw ,  'fileprefix' : ff , stationId: ssd},
                                callback : function(json4){
                                    $("#savebyserverdir").unbind( "click" ) ;
                                    $('#reviewinfo-id').html('').removeClass() ;
                                    if(json4.success){
                                        html = new Array() ;
                                        html.push("<p><b>导入完成，详细信息：</b>") ;
                                        html.push("<p>处理文件总数： "+json4.total ) ;
                                        html.push("<p>其中失败： "+json4.fail ) ;
                                        if(json4.fail && json4.fail > 0){
                                            html.push("<p>失败的文件： "+json4.failfile ) ;
                                        }
                                        html.push("<p>用时(秒)： "+json4.usedtime ) ;
                                        $('#reviewinfo-id').html(html.join('</p>')) ;
                                        $('#errormsg3').html('') ;
                                    }else{
                                        alert(json4.info) ;
                                    }
                                    
                                }
                            }
                            ajaxpost(data1);
                            //$("#savebyserverdir").attr("disabled" , "disabled"); //禁用提交按钮
                            $("#reviewinfo-id").progressBar();
                            setTimeout(getimportstatus , 2000) ;
                            function getimportstatus(){
                                $("#reviewinfo-id").fadeIn();
                                var data = {
                                    url : 'ht/getsacprogress.do' ,
                                    params : {'total': json.fileTotal} ,
                                    callback : function(json5){
                                        $('#errormsg3').html(json5.info + ' -&gt; ' + json5.total) ;
                                        $("#reviewinfo-id").progressBar(json5.percentage);
                                        if(json5.success){
                                            setTimeout(getimportstatus , 2000) ;
                                        }else{}
                                    }
                                }
                                
                                ajaxpost(data) ;
                                
                            }
                        });
                    }else{
                        if(json.info){
                            alert(json.info) ;
                        }else{
                            alert("测试失败,请检查文件路径") ;
                        }
                    }
                    
                }
        }
        
        ajaxpost(data);
    });
    
    $('#dataTypeCombox').omCombo({
        dataSource:[{text:'手动',value:'1'},{text:'胶版',value:'2'} ] ,
        valueField : 'value' ,
        optionField :'text',
        value:'1',
        width : 160
    }) ;
    $('#fileway').omCombo({
        dataSource:[{text:'复制',value:'copy'},{text:'剪切',value:'cut'} ] ,
        valueField : 'value' ,
        optionField :'text',
        value:'copy',
        width : 160
    }) ;
    $('#station_serverdir').omCombo({
                            dataSource:'ht/stationlistall.do' ,
                            valueField : 'id' ,
                            optionField :'name'  ,
                            width : 160
                        }) ;
    
    
    
    
});

var openMultiinfo = function(){
	$("#errorfilename" ).omDialog("open");
}

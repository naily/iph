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
        optionField :'name' 
    }) ;
    $('#mdbStation').omCombo({
        width : 222 ,
        dataSource:'ht/stationlistall.do' ,
        valueField : 'id' ,
        optionField :'name' 
    }) ;
    //观测日期
    $('#actionDate').omCalendar({showTime : true});
    
    //$('#ip11').omNumberField();
	//$('#ip2').omNumberField();
	//$('#ip3').omNumberField();
	//$('#ip4').omNumberField();
	//$('#ip5').omNumberField();
	//$('#ip6').omNumberField();
	//$('#ip7').omNumberField();
	//$('#ip8').omNumberField();
	//$('#ip9').omNumberField();
	//$('#ip10').omNumberField();
    
    //上传批量导入的数据文件
    $('#file_upload_access').omFileUpload({
        action : '../ht/uploadaccessdata.do'+ "?timestamp=" + new Date().getTime() ,
        swf : 'swf/om-fileupload.swf'+ "?timestamp=" + new Date().getTime() ,
        fileExt  : '*.mdb',
        fileDesc : 'Microsoft Access Database(*.mdb)' ,
        method   : 'POST',
        onComplete : function(ID,fileObj,response,data,event){
            //alert('文件'+fileObj.name+'上传完毕');
            var json = eval("("+response+")");
            if(json.success){
            	$("#errormsg2").html("上传Access完毕" ).show() ;
                $("#mdbpath").val(json.info ) ;
            }else{
            	$("#errormsg2").html("上传失败<br\> Info: "+ json.info).show() ;
            }
        },
        onError :function(ID, fileObj, errorObj, event){
            alert('文件'+fileObj.name+'上传失败。错误类型：'+errorObj.type+'。原因：'+errorObj.info);
        },
        onSelect:function(ID,fileObj,event){
            //alert('你选择了文件：'+fileObj.name);
            //选择文件后立即上传
        },
        actionData : { 'action' :'fileupload' } ,
        autoUpload : true  //自动上传
    });
    
    $('#savebut').click(function(){
    	var st = $('#comboStation').omCombo('value') ;
    	var ad = $.omCalendar.formatDate($('#actionDate').omCalendar('getDate'), 'yy-mm-dd H:i:s') ;
    	var foF2 = $("input[name='foF2']").val();
    	var hlF2 = $("input[name='hlF2']").val();
    	var foF1 = $("input[name='foF1']").val();
    	var hlF1 = $("input[name='hlF1']").val();
    	var hlF = $("input[name='hlF']").val();
    	var hpF = $("input[name='hpF']").val();
    	var foE = $("input[name='foE']").val();
    	var hlE = $("input[name='hlE']").val();
    	var foEs = $("input[name='foEs']").val();
    	var hlEs = $("input[name='hlEs']").val();
        var fbEs = $("input[name='fbEs']").val();
        var Fmin = $("input[name='Fmin']").val();
        var M3000F2 = $("input[name='M3000F2']").val();
        var M1500F2 = $("input[name='M1500F2']").val();
        var M3000F1 = $("input[name='M3000F1']").val();
        var M3000F  = $("input[name='M3000F']").val();
        var array = new Array('foF2', 'hlF2','foF1', 'hlF1','hlF','hpF','foE','hlE','foEs','hlEs','fbEs','Fmin','M3000F2','M1500F2','M3000F1','M3000F' );
    	
    	var data = {
    		url : 'ht/pamsave.do' ,
    		params : {'stationID':st, 'createDate': ad,
    				  'foF2':foF2, 
    				  'hlF2':hlF2,
    				  'foF1':foF1,
    				  'hlF1':hlF1,
    				  'hlF':hlF,
    				  'hpF':hpF,
    				  'foE':foE,
    				  'hlE':hlE,
    				  'foEs':foEs,
    				  'hlEs':hlEs,
    				  'fbEs': fbEs,
    				  'Fmin': Fmin,
    				  'M3000F2': M3000F2,
    				  'M1500F2': M1500F2,
    				  'M3000F1': M3000F1,
    				  'M3000F':  M3000F} ,
    		callback : function(json){
    			if(json.success){
    				$('#errormsg').hide();
    				$.omMessageTip.show({
			                type:'success',
			                title:'提醒',
			                timeout : 3000 ,
			                content:'保存成功'
			        });
			        clearFields(array);
    			}else{
    				$('#errormsg').html(json.info).show();
    			}
    		}
    	}
    	
        if( data.params.foF2){
            //alert(data.params.createDate) ;
            ajaxpost(data);
        }else{
            $('#errormsg').html("请输入").show();
        }
    	
    }) ;
    
	function clearFields(arry){
		if(arry)
			for(var fn in arry){
				$("input[name='"+ arry[fn] +"']").val('');
			}
	}
    
    $("#saveMdbFile").bind("click" , function(){
    
        var mdbst = $('#mdbStation').omCombo('value') ;
        var tn =    $("#mdbtn").val() ;
        var df =    $("#datefield").val() ;
        var mdb =   $("#mdbpath").val() ;
        if(!mdbst){
            alert("请选择观测站") ;
            return ;
        }
        if(!tn){
            alert("请输入mdb数据表名") ;
            return ;
        }
        if(!df){
            alert("请输入mdb数据日期字段名") ;
            return ;
        }
        
        if(!mdb){
            alert("请上传mdb文件") ;
            return ;
        }
        
        var save = {
            url : "ht/savepamdata.do" ,
            params : {mdbPath : mdb ,stationId : mdbst,mdbTableName: tn,dateField : df} ,
            callback :function(json){
                if(json.success){
                    $("#errormsg2").html("导入成功<br>记录数： "+json.insertRow +"<br>用时： " +json.usetime).show() ;
                }else{
                    $("#errormsg2").html(json.info).show() ;
                }
                $("#saveMdbFile").removeAttr("disabled" );
            }
        }
        $("#errormsg2").removeClass().html("<img src='images/waiting.gif' width=100 border=0><p>正在导入...</p>").show() ;
        $("#saveMdbFile").attr("disabled" , "disabled"); //禁用提交按钮
        
        ajaxpost(save) ;
    });
	
});



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
    //观测日期
    $('#actionDate').omCalendar({showTime : true});
    
    $('#ip11').omNumberField();
	$('#ip2').omNumberField();
	//$('#ip3').omNumberField();
	//$('#ip4').omNumberField();
	$('#ip5').omNumberField();
	$('#ip6').omNumberField();
	//$('#ip7').omNumberField();
	$('#ip8').omNumberField();
	$('#ip9').omNumberField();
	$('#ip10').omNumberField();
    
    //上传批量导入的数据文件
    $('#file_upload_access').omFileUpload({
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
        autoUpload : true  //自动上传
    });
    
    $('#savebut').click(function(){
    	var st = $('#comboStation').omCombo('value') ;
    	var ad = $.omCalendar.formatDate($('#actionDate').omCalendar('getDate'), 'yy-mm-dd H:i:s') ;
    	var ip1 = $('#ip1').val();
    	var ip2 = $('#ip2').val();
    	var ip3 = $('#ip3').val();
    	var ip4 = $('#ip4').val();
    	var ip5 = $('#ip5').val();
    	var ip6 = $('#ip6').val();
    	var ip7 = $('#ip7').val();
    	var ip8 = $('#ip8').val();
    	var ip9 = $('#ip9').val();
    	var ip10 = $('#ip10').val();
        var ip11 = $('#ip11').val();
    	
    	var data = {
    		url : 'ht/pamsave.do' ,
    		params : {'stationID':st, 'createDate': ad,
    				  'foF2':ip1, 
    				  'h1F2':ip2,
    				  'foF1':ip3,
    				  'h1F1':ip4,
    				  'hlF':ip5,
    				  'hpF':ip6,
    				  'foE':ip7,
    				  'hlE':ip8,
    				  'foEs':ip9,
    				  'hlEs':ip10,
                      'M3000F2' :ip11 } ,
    		callback : function(json){
    			if(json.success){
    				$('#errormsg').hide();
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
    	
        if( data.params.foF2){
            //alert(data.params.createDate) ;
            ajaxpost(data);
        }else{
            $('#errormsg').html("请输入").show();
        }
    	
    }) ;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
});



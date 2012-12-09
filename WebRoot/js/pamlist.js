$(document).ready(function(){

    $('#list0').omGrid({
         width : '99.8%',
         method : 'POST' ,
         limit : pageslimit, //分页显示，每页显示8条
         singleSelect : false, //出现checkbox列，可以选择同时多行记录
         colModel : [    {header:'<b>ID</b>',    align : 'center',  name:'ID',width:30 },
                         {header:'<b>观测站</b>',   name:'stationName' , width:'autoExpand' ,align : 'center' ,
                         	renderer: function(colValue, rowData, rowIndex){
	                         	return '<a href="javascript:previewStation(\''+rowData.stationID+'\');" class="a3">'+rowData.stationName+' </a>&nbsp;&nbsp;&nbsp;&nbsp;'   ;
	                         }
	                     } ,
                         {header:'<b>日期</b>',  align : 'center',  name:'createDate',width:120} ,
                         {header:'<b>foF2</b>', align : 'center',  name:'foF2' ,width:30} ,
                         {header:'<b>fxF2</b>', align : 'center',  name:'fxF2' ,width:30} ,
                         {header:'<b>h\'F2</b>', align : 'center', name:'hlF2' ,width:30} ,
                         {header:'<b>hpF2</b>', align : 'center', name:'hpF2' ,width:30} ,
                         
                         {header:'<b>foF1</b>', align : 'center',  name:'foF1' ,width:30} ,
                         {header:'<b>h\'F1</b>', align : 'center',  name:'hlF1' ,width:30} ,
                         {header:'<b>h\'F</b>',  align : 'center', name:'hlF' ,width:30} ,
                         {header:'<b>hpF</b>',  align : 'center', name:'hpF' ,width:30} ,
                         {header:'<b>foE</b>',  align : 'center', name:'foE' ,width:30} ,
                         {header:'<b>h\'E</b>',  align : 'center', name:'hlE' ,width:30} ,
                         {header:'<b>foEs</b>', align : 'center',  name:'foEs' ,width:30} ,
                         {header:'<b>h\'Es</b>', align : 'center',  name:'hlEs' ,width:30} //,
                         //{header:'fbEs',   name:'fbEs' } ,
                         //{header:'Fmin',   name:'Fmin' } 
         ],
         dataSource : {}
     });
     
     $('#actionDate').omCalendar({disabled  : true });
     //观测站下拉框
     $('#comboStation').omCombo({
        dataSource:'ht/stationlistall.do' ,
        valueField : 'id' ,
        disabled  : true ,
        optionField :'name' 
    }) ;
    //按观测站查询电离参数
     $('#comboStationQuery').omCombo({
        dataSource:'ht/stationlistall.do' ,
        valueField : 'id' ,
        width : 150 ,
        optionField :'name' ,
        emptyText  : '选择观测站查询' ,
        onValueChange:function(target,newValue,oldValue,event){ 
		     //do something
            $('#list0').omGrid('setData','ht/pamlist.do?sid='+newValue);
		 }
    }) ;
    
    $('#updatebut').omButton({
        width : 50 ,
        onClick:function(){
                                var ss = $('#list0').omGrid('getSelections',true);
                                var sid = $('#comboStationQuery').omCombo('value') ;
                                if(ss.length != 1 ){
                                    at({cont:'请选择一条记录修改！' , type : 'error'});
                                    return;
                                }else{
                                    var igid = ss[0].ID ;
                                    var getpgt = {
                                                url : 'ht/pamget.do',
                                                params : {id : igid ,table : sid}  ,
                                                callback : function(json){
                                                    if(json.success){
                                                        //$('#list0').omGrid('reload');
                                                    }else{
                                                        //at({cont: json.info , type : 'error'});
                                                    }
                                                    var cd = json.createDate ;
                                                    $('#actionDate').val( cd.substring(0,16) );
                                                    $('#comboStation').omCombo('value', json.stationID);
                                                    $("input[name='foF2']").val(json.foF2);
                                                    $("input[name='hlF2']").val(json.hlF2);
                                                    $("input[name='foF1']").val(json.foF1);
                                                    $("input[name='hlF1']").val(json.hlF1);
                                                    $("input[name='hlF']").val(json.hlF);
                                                    $("input[name='hpF']").val(json.hpF);
                                                    $("input[name='foE']").val(json.foE);
                                                    $("input[name='hlE']").val(json.hlE);
                                                    $("input[name='foEs']").val(json.foEs);
                                                    $("input[name='hlEs']").val(json.hlEs);
                                                    $("input[name='fbEs']").val(json.fbEs);
                                                    $("input[name='Fmin']").val(json.Fmin);
                                                    $("input[name='M3000F2']").val(json.M3000F2);
                                                    $("input[name='M1500F2']").val(json.M1500F2);
                                                    $("input[name='M3000F1']").val(json.M3000F1);
                                                    $("input[name='M3000F']").val(json.M3000F);
                                                    
                                                    $("input[name='MUF3000F1']").val(json.MUF3000F1);
											        $("input[name='MUF3000F2']").val(json.MUF3000F2);
											        $("input[name='Es']").val(json.Es);
											        $("input[name='fxF2']").val(json.fxF2);
											        $("input[name='fxl']").val(json.fxl);
											        $("input[name='hpF2']").val(json.hpF2);
                                                    
                                                    $( "#tab1").omDialog('open');
                                                    
                                                    $('#savebut').omButton({
                                                        onClick : function(){
                                                            json.createDate = $.omCalendar.formatDate($('#actionDate').omCalendar('getDate'), 'yy-mm-dd');
                                                            json.stationID = sid;
                                                            
                                                            json.foF2 = $("input[name='foF2']").val();
                                                            json.hlF2 = $("input[name='hlF2']").val();
                                                            json.foF1 = $("input[name='foF1']").val();
                                                            json.hlF1 = $("input[name='hlF1']").val();
                                                            json.hlF = $("input[name='hlF']").val();
                                                            json.hpF = $("input[name='hpF']").val();
                                                            json.foE = $("input[name='foE']").val();
                                                            json.hlE = $("input[name='hlE']").val();
                                                            json.foEs = $("input[name='foEs']").val();
                                                            json.hlEs = $("input[name='hlEs']").val();
                                                            json.fbEs = $("input[name='fbEs']").val();
                                                            json.Fmin = $("input[name='Fmin']").val();
                                                            json.M3000F2 = $("input[name='M3000F2']").val();
                                                            json.M1500F2 = $("input[name='M1500F2']").val();
                                                            json.M3000F1 = $("input[name='M3000F1']").val();
                                                            json.M3000F  = $("input[name='M3000F']").val();
                                                            
                                                            json.MUF3000F1  = $("input[name='MUF3000F1']").val();
                                                            json.MUF3000F2  = $("input[name='MUF3000F2']").val();
                                                            json.Es  = $("input[name='Es']").val();
                                                            json.fxF2  = $("input[name='fxF2']").val();
                                                            json.fxl  = $("input[name='fxl']").val();
                                                            json.hpF2  = $("input[name='hpF2']").val();
                                                            
                                                            var updatepgt = {
                                                                            url : 'ht/pamupdate.do',
                                                                            params : json  ,
                                                                            callback : function(json){
                                                                                if(json.success){
                                                                                    $("#tab1").omDialog('close');
                                                                                    $('#list0').omGrid('reload');
                                                                                    $.omMessageTip.show({
                                                                                        type:'success',
                                                                                        title:'提醒',
                                                                                        timeout : 3000 ,
                                                                                        content:'修改成功'
                                                                                    });
                                                                                }else{
                                                                                    at({cont: json.info , type : 'error'});
                                                                                }
                                                                            }
                                                            }
                                                            ajaxpost(updatepgt);
                                                        }
                                                     })
                                                }
                                    }
                                    ajaxpost(getpgt);
                                }
                            }
    }) ;
    $('#del').omButton({
        width : 50 ,
        onClick:function(){
                                var ss = $('#list0').omGrid('getSelections',true);
                                var sid = $('#comboStationQuery').omCombo('value') ;
                                if(ss.length < 1 ){
                                    at({cont:'请选择删除的记录！' , type : 'error'});
                                    return;
                                }else{
                                    var arry = new Array( ) ;
                                    for(var i=0 ; i<ss.length ; i++){
                                        arry.push(ss[i].ID );
                                    }
                                    
                                    var delt = {
                                                url : 'ht/pamdel.do',
                                                params : {ids: arry.join(";") , stationID: sid}  ,
                                                callback : function(json){
                                                    if(json.success){
                                                        $('#list0').omGrid('reload');
                                                        $.omMessageTip.show({
                                                            type:'success',
                                                            title:'提醒',
                                                            timeout : 3000 ,
                                                            content:'删除成功'
                                                        });
                                                    }else{
                                                        at({cont: json.info , type : 'error'});
                                                    }
                                                }
                                            }
                                            //提示
                                            $.omMessageBox.confirm({
                                                title:'删除确认',
                                                content:'删除记录 '+ss.length+'条 ,你确定要这样做吗?',
                                                onClose:function(value){
                                                    if(value){
                                                        ajaxpost(delt);
                                                    }
                                                }
                                            });
                                }
                            }
    }) ;
    
    
    $( "#tab1").omDialog({
            autoOpen: false,
            resizable: false ,
            width:'auto' ,
            title:'修改电离层参数信息'
     });
    
    //图片预览弹出
    $( "#imagePreview").omDialog({
        autoOpen: false,
        height: 'auto' ,
        width :'auto'
    });
    
});

function previewStation(sid){
	if(sid){
		var ie = '<iframe width="760" height="600" align="center" frameborder="0" scrolling="1" src="ht/viewstation.do?sid=999"></iframe>' ;
		$( "#imagePreview").html(ie.replace("999" , sid));
		
		$( "#imagePreview").omDialog({title:'观测站信息查看'});
		$( "#imagePreview").omDialog('open');
	}
}

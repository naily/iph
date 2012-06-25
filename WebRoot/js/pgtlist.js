$(document).ready(function(){
	
	$('#list0').omGrid({
        //title : '观测站列表' ,
         //height : 250,
         width : '99.8%',
         method : 'POST' ,
         limit : 5, //分页显示，每页显示8条
         singleSelect : false, //出现checkbox列，可以选择同时多行记录
         colModel : [    {header:'ID', name:'gramID' ,   width:100},
                         {header:'频高图标题',name:'gramTitle',  width:200  },
                         {header:'观测站',name:'stationName'  } ,
                         {header:'类型',name:'type', width:100,renderer:function(value,rowData,rowIndex){ 
                         	if('1' == value){
	                         	return '<b> 手动 </b>'; 
                         	}
                         	if('2' == value){
	                         	return '<b>胶版</b>'; 
                         	}
                         	if('3' == value){
	                         	return '<b>数字</b>'; 
                         	}
                         	if('4' == value){
	                         	return '<b>打印</b>'; 
                         	}
                         } } ,
                         {header:'日期',name:'createDate',width:100 } ,
                         {header:'文件名',name:'gramFileName', width:100,width:'autoExpand' } 
         ],
         dataSource : 'ht/pgtlist.do' 
     });
     
     //按钮绑定删除
     $('#deletebut').omButton({
     	
     	onClick : function(){
     		var ss = $('#list0').omGrid('getSelections',true);
     		if(dels.length < 1 ){
			    at({cont:'请选择删除的记录！' , type : 'error'});
			    return;
			}else{
				var arry = new Array( ) ;
				for(var i=0 ; i<ss.length ; i++){
					array.push(ss[i].gramID );
				}
				
				var delt = {
	                        url : 'ht/pgtdel.do',
	                        params : {ids: arry.join(";")}  ,
	                        callback : function(json){
	                            if(json.success){
	                                $('#list0').omGrid('reload');
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
     });
     
     $( "#tab1").omDialog({
            autoOpen: false,
            resizable: false ,
            title:'修改频高图信息'
     });
     
     $('#actionDateId').omCalendar();
     //观测站下拉框
     $('#comboStation').omCombo({
        dataSource:'ht/stationlistall.do' ,
        valueField : 'id' ,
        optionField :'name' ,
        value: 'WHA'
    }) ;

     $('#updatebut').omButton({
     	
     	onClick : function(){
     		var ss = $('#list0').omGrid('getSelections',true);
     		if(dels.length != 1 ){
			    at({cont:'请选择一条记录修改！' , type : 'error'});
			    return;
			}else{
				var igid = ss[0].gramID ;
				var getpgt = {
	                        url : 'ht/pgtget.do',
	                        params : {id : igid }  ,
	                        callback : function(json){
	                            if(json.success){
	                                //$('#list0').omGrid('reload');
	                            }else{
	                               	//at({cont: json.info , type : 'error'});
	                            }
	                            alert(json.gramID);
	                            $('#comboStation').omCombo('value', json.stationID);
	                            $('#actionDateId').omCalendar('setDate', new Date(json.createDate));
	                            $('#pgtTitleId').val(json.gramTitle) ;
	                            $( "#tab1").omDialog('open');
	                        }
                }
                ajaxpost(getpgt);
			}
     	}
     });
     
});



var itemId = '' ;
$(document).ready(function(){
    /* */
    $('#list0').omGrid({
         //title : '设置列表' ,
         //height : 250,
         width : '99.8%',
         method : 'POST' ,
         limit : pageslimit, //分页显示，每页显示8条
         //singleSelect : false, //出现checkbox列，可以选择同时多行记录
         colModel : [    {header:'表名', name:'dataTableFull' ,  width:'autoExpand'},
                         {header:'保护开始',name:'dataSDate',  width:200  },
                         {header:'保护结束',name:'dataEDate', width:200 } ,
                         {header:'观测站',name:'stationName',   width:100 } ,
                         {header:'开放日期',name:'publicDate',width:200  } 
         ],
         dataSource : 'ht/ptalist.do' //后台取数的URL
     });
	
	$('#buttonbar').omButtonbar({
		width : '99.8%',
		btns : [{
			label : '添加' ,
			onClick:function(){
                $( "#createblock").omDialog({title:"添加记录"}) ;
				$( "#createblock").omDialog("open") ;
                clearInputValue() ;
			}
		},{
			label : '修改' ,
			//disabled : true ,
			onClick:function(){
                var ss = $('#list0').omGrid('getSelections',true);
                if(ss.length != 1){
                    at({cont:'请选择一条记录修改！' , type : 'error'});
                    return;
                }else{
                    setInputValue(ss[0]) ;
                    $( "#createblock").omDialog({title:"修改记录"}) ;
                    $( "#createblock").omDialog("open") ;
                }
                
            }
		},{
			label : '删除' ,
			onClick:function(){ 
				var ss = $('#list0').omGrid('getSelections',true);
	     		if(ss.length < 1 ){
				    at({cont:'请选择删除的记录！' , type : 'error'});
				    return;
				}else{
					var arry = new Array( ) ;
					for(var i=0 ; i<ss.length ; i++){
						arry.push(ss[i].id );
					}
					
					var delt = {
		                        url : 'ht/ptadel.do',
		                        params : {ids: arry.join(";")}  ,
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
		}] 
	}) ;
	
	$( "#createblock").omDialog({
        autoOpen: false,
        resizable: false ,
        width : 500 ,
        title:'添加记录' 
     });
     //观测站下拉框
    $('#comboStation').omCombo({
        dataSource:'ht/stationlistall.do' ,
        valueField : 'id' ,
        optionField :'name'  
    }) ;
    $('#comboTableName').omCombo({
        dataSource : [ {text : '电离层频高图表', value : "T_IRONOGRAM"}, 
                       {text : '电离层参数数据表', value : "T_PARAMETER"},
                       {text : '电离层报表扫描图表', value : "T_SCANPIC"}] ,
       editable : false,
       emptyText : '选择数据表'
     });
     $('#sDate').omCalendar();
     $('#eDate').omCalendar();
     $('#pubDate').omCalendar();
     
     var notempty = "不能为空" ;
     
     $('#savebut').bind("click" , function(event){
     		var st = $('#comboStation').omCombo('value') ;
     		var tn = $('#comboTableName').omCombo("value") ;
     		
     		var sd = $('#sDate').val() ? $.omCalendar.formatDate($('#sDate').omCalendar('getDate'), 'yy-mm-dd') : '' ;
     		var ed = $('#eDate').val() ? $.omCalendar.formatDate($('#eDate').omCalendar('getDate'), 'yy-mm-dd') : ''  ;
     		var pd = $('#pubDate').val() ? $.omCalendar.formatDate($('#pubDate').omCalendar('getDate'), 'yy-mm-dd') : ''  ;
     		
     		if( sd && ed){
     			if( $('#sDate').omCalendar('getDate') >= $('#eDate').omCalendar('getDate') ){
     				$('#errormsg').html('开始日期必须小于结束日期!').show() ;
     				return  ;
     			}
     			
     		}
     		if(!sd && !ed){
     			$('#errormsg').html('开始日期和结束日期不能同时为空').show() ;
     			return ;
     		}
     		
     		if(!( tn && pd) ){
     			$('#errormsg').html(notempty).show() ;
     			return  ;
     		}else{
     			$('#errormsg').hide() ;
                
     			var save = {
	                url :'ht/ptaSave.do' ,
	                params :{'dataTable' :   tn ,
	                         'dataStation' : st ,
	                         'dataSDate':  sd,
	                         'dataEDate':  ed ,
	                         'publicDate' : pd},
	                callback : function(json){
	                    if(json.success){
	                        $('#list0').omGrid('reload') ;
	                    	
	                        $( "#createblock").omDialog("close") ;
	                        $.omMessageTip.show({
	                            type:'success',
	                            title:'提醒',
	                            timeout : 3000 ,
	                            content:'保存成功'
	                        });
	                        //清空值
	                        $('#sDate').val('');
	                        $('#eDate').val('') ;
	                        $('#pubDate').val('');
	                    }else{
	                        $('#errormsg').html(json.info).show();
	                    }
	                    
	                }
	            }
                if(itemId){
                    save.params.id = itemId ;
                }
	            //alert(save.params.dataTable) ;
	            ajaxpost(save);
     		}
     	}
     ) ;
});

function setInputValue(data){
    itemId = data.id ;
    $('#comboStation').omCombo('value' , data.dataStation) ;
    $('#comboTableName').omCombo("value" , data.dataTable) ; 
    
     $('#sDate').val(data.dataSDate)  ;//? $.omCalendar.formatDate($('#sDate').omCalendar('getDate'), 'yy-mm-dd') : '' ;
     $('#eDate').val(data.dataEDate) ;//? $.omCalendar.formatDate($('#eDate').omCalendar('getDate'), 'yy-mm-dd') : ''  ;
     $('#pubDate').val(data.publicDate) ;//? $.omCalendar.formatDate($('#pubDate').omCalendar('getDate'), 'yy-mm-dd') : ''  ;
    $('#sDate').omCalendar('setDate' , $.omCalendar.parseDate(data.dataSDate , 'yy-mm-dd'));
    $('#eDate').omCalendar('setDate' , $.omCalendar.parseDate(data.dataEDate , 'yy-mm-dd'));
    $('#pubDate').omCalendar('setDate' , $.omCalendar.parseDate(data.publicDate , 'yy-mm-dd'));
}

function clearInputValue(){
    $('#comboStation').omCombo('value' , '') ;
    $('#comboTableName').omCombo("value" , '') ;
    $('#sDate').val('')  ;
    $('#eDate').val('') ;
    $('#pubDate').val('') ;
    itemId = '' ;
}



$(document).ready(function(){
    /* 数据操作日志*/
    $('#list0').omGrid({
         width : '99.8%',
         method : 'POST' ,
         limit : pageslimit, //分页显示，每页显示8条
         //singleSelect : false, //出现checkbox列，可以选择同时多行记录
         colModel : [    
                         {header:'操作类型',name:'actionType',  width:100  ,
	                         renderer : function(val, rowData, rowIndex){
	                            if (val == '01') {
	                                 return '<span style="color:green;"><b>查询</b></span>';
	                             }  
                                 if (val == '02') {
                                     return '<span style="color:green;"><b>浏览</b></span>';
                                 } 
                                 if (val == '03') {
                                     return '<span style="color:green;"><b>下载</b></span>';
                                 } 
	                         }
                         },
                         {header:'表名', name:'dataTable' ,  width:'autoExpand'},
                         {header:'记录数',name:'resultNum', width:100 } ,
                         {header:'操作者',name:'userId',width:100  } ,
                         {header:'IP',    name:'userIP',width:150  } ,
                         {header:'日期',name:'serviceDate', width:200 } 
         ],
         //extraData: { adminId : $('#comboAdmin').omCombo('value') ,actionType:$('#comboActionType').omCombo('value')} ,
         dataSource : 'ht/dataStatsList.do' //后台取数的URL
     });
	
	$('#buttonbar').omButtonbar({
		width : '99.8%',
		btns : [{
			label : '导出日志' ,
			onClick:function(){
                window.open(basepath + '/ht/downloadAllLog.do')
			}
		},{
			label : '清空全部日志' ,
			onClick:function(){ 
					
				var delt = {
	                        url : 'ht/dLogDelAll.do',
	                        params : {}  ,
	                        callback : function(json){
                                $('#list0').omGrid('reload');
	                            if(json.success){
	                                $.omMessageTip.show({
						                type:'success',
						                title:'提醒',
						                timeout : 3000 ,
						                content:'操作成功'
						            });
	                            }else{
	                                at({cont: json.info , type : 'error'});
	                            }
	                        }
	                    }
                    //提示
                    $.omMessageBox.confirm({
                        title:'操作确认',
                        content:'将删除全部日志记录 ,你确定要这样做吗?',
                        onClose:function(value){
                            if(value){
                                ajaxpost(delt);
                            }
                        }
                    });
                
				}
		}] 
	}) ;
	
    $('#comboAdmin').omCombo({
        dataSource:'ht/adminlistall.do' ,
        valueField : 'id' ,
        optionField :'name'  ,
        //editable : false,
        emptyText : '选择操作者'
    }) ;
    $('#comboActionType').omCombo({
        dataSource : [ {text : '查询', value : "01"}, 
                       {text : '浏览', value : "02"},
                       {text : '下载', value : "03"}] ,
       //editable : false,
       emptyText : '选择操作类型'
     });
     
     var notempty = "不能为空" ;
     
     $('#searchbut').omButton({
        width : 100,
        label : '查询' ,
        onClick : function(){
            var admin = $('#comboAdmin').omCombo('value') ;
            var act = $('#comboActionType').omCombo('value') ;
            
            $('#list0').omGrid({extraData: { adminId : admin ,actionType:act}}) ;
            $('#list0').omGrid('reload') ;
        }
     });
     
});




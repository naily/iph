$(document).ready(function(){
    /* 数据操作日志*/
    $('#list0').omGrid({
         width : '99.8%',
         method : 'POST' ,
         limit : pageslimit, //分页显示，每页显示8条
		 onPageChange:function(type,newPage,event){
	         //alert('will goto page '+newPage);
	     },
         colModel : [    {header:'表名', name:'dataTable' ,  width:'autoExpand'},
                         {header:'操作类型',name:'actionType',  width:200  ,
	                         renderer : function(val, rowData, rowIndex){
	                            if (val == '01') {
	                                 return '<span style="color:green;"><b>录入</b></span>';
	                             }  
                                 if (val == '02') {
                                     return '<span style="color:green;"><b>编辑</b></span>';
                                 } 
                                 if (val == '03') {
                                     return '<span style="color:green;"><b>删除</b></span>';
                                 } 
	                         }
                         },
                         {header:'操作日期',name:'logDate', width:200 } ,
                         {header:'操作者',name:'adminId',width:200  } 
         ],
         //extraData: { adminId : $('#comboAdmin').omCombo('value') ,actionType:$('#comboActionType').omCombo('value')} ,
         dataSource : 'ht/dataLogList.do' //后台取数的URL
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
        dataSource : [ {text : '录入', value : "01"}, 
                       {text : '编辑', value : "02"},
                       {text : '删除', value : "03"}] ,
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




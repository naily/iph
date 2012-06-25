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
     
     
});



$(document).ready(function(){

    $('#content').omGrid({
         height : 250,
         width : 600,
         limit : 5, //分页显示，每页显示8条
         //singleSelect : false, //出现checkbox列，可以选择同时多行记录
         colModel : [    {header:'登录名',name:'loginId',  align : 'center' ,width:'autoExpand'},
                         {header:'名称',name:'name', width:250, align : 'left',wrap:true},
                         {header:'级别',name:'super',width:100, renderer:function(value,rowData,rowIndex){ 
                            var str = '管理员' ;
                            if(value ){
                                str = '超级管理员' ;
                            } 
                         return '<b>'+str+'</b>'; }}
         ],
         dataSource : 'ht/adminlist.do' //后台取数的URL
     });

});

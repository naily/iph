$(document).ready(function(){
    
    /*
    $('#list0').omGrid({
        title : '管理员列表' ,
         //height : 250,
         //width : 600,
         limit : 5, //分页显示，每页显示8条
         //singleSelect : false, //出现checkbox列，可以选择同时多行记录
         colModel : [    {header:'登录名',name:'loginId',  align : 'center' ,width:'autoExpand'},
                         {header:'名称',name:'name', width:250, align : 'left',wrap:true},
                         {header:'角色',name:'super',width:100, renderer:function(value,rowData,rowIndex){ 
                            var str = '管理员' ;
                            if(value ){
                                str = '超级管理员' ;
                            } 
                         return '<b>'+str+'</b>'; }}
         ],
         dataSource : 'ht/adminlist.do' //后台取数的URL
     });
     
     */
    
    save.init();
    
    
});

var save ={
    init:function(){
        $( "#createblock").omDialog({
            autoOpen: false,
            resizable: false ,
            width : 440 ,
            title:'添加观测站' 
         });
         
        $("#createbut").click(this.open) ;
    },
    open:function(){
        $( "#createblock").omDialog('open');
    } ,
    save :function(){
        alert(1);
    }
} 


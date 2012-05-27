$(document).ready(function(){

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
     
     $( "#createadmin").omDialog({
            autoOpen: false,
            title:'新增管理员'
     });
     
     $('#jsId').omCombo({
                dataSource : [ {text : '普通管理员', value : false}, 
                               {text : '超级管理员', value : true}] ,
               editable : false,
               emptyText : '选择角色'
     });
      
    

});

function showModelessDialog(){
       $( "#createadmin").omDialog('open');
}
function checking(){
    var s = true ;
    //数据验证
    $('#createadmin :input').each(function(i , n){
        var o = $(n) ; 
        if(!o.val() && i <4){
            var e = o.attr('empt');
            if(e){
                $('#info').html(e);
            }
            s = false ;
            return  s;
        }
    });
    return s ;
}
function savedata(){
    if(checking()){
        $('#info').html('');
	    var data = {
            url :'ht/adminsave.do' ,
            params :{loginId:$('#dlmId').val() , password: $('#mmId').val(), name: $('#xmId').val(), issuper:$('#jsId').omCombo('value')},
            callback : function(json){
                if(json.success){
                    location.reload() ;
                }else{
                    //at({cont:json , type : 'error'});
                    $('#info').html(json.info);
                    
                }
            }
        }
        
        ajaxpost(data);
    } 
    
}


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
            resizable: false ,
            title:'新增管理员'
     });
     
     $( "#modifPassword").omDialog({
            autoOpen: false,
            resizable: false ,
            title:'修改密码'
     });
     
     $('#jsId').omCombo({
                dataSource : [ {text : '普通管理员', value : false}, 
                               {text : '超级管理员', value : true}] ,
               editable : false,
               emptyText : '选择角色'
     });
    
     
	$('#del').click(function(){
            	var dels = $('#list0').omGrid('getSelections' , true);
            	if(dels.length < 1 ){
            		at({cont:'请选择删除的记录！' , type : 'error'});
            		return;
            	}else{
            		var delt = {
            			url : 'ht/admindel.do',
            			params : {loginId: dels[0].loginId}  ,
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
		                title:'确认删除',
		                content:'删除用户,你确定要这样做吗?',
		                onClose:function(value){
		                    if(value){
			            		ajaxpost(delt);
		                    }
		                }
		            });

            	}
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
            params :{loginId:$('#dlmId').val() , password: $('#mmId').val(), name: $('#xmId').val(), isSuper:$('#jsId').omCombo('value')},
            callback : function(json){
                if(json.success){
                    //location.reload() ;
                	$('#list0').omGrid('reload');
                	
                	$('#dlmId').val('') ;
                	$('#mmId').val('') ;
                	$('#xmId').val('') ;
                	$("#createadmin").omDialog('close');
                	$('#info').html('');
                }else{
                    //at({cont:json , type : 'error'});
                    $('#info').html(json.info);
                }
                
            }
        }
        
        ajaxpost(data);
    } 
    
}

var modifpass = {
	open : function(){
		$( "#modifPassword").omDialog('open');
	},
	submit :function(){
		var op = $('#oldpass').val();
		var np = $('#newpass').val();
		var np2 = $('#newpass2').val();
		
		if(isEmpty(op)){
			$('#info_modif').html($('#oldpass').attr('empt'));
			return  ;
		}
		if(isEmpty(np)){
			$('#info_modif').html($('#newpass').attr('empt'));
			return  ;
		}
		if(isEmpty(np2)){
			$('#info_modif').html($('#newpass2').attr('empt'));
			return  ;
		}
		
		var dta = {
			url:'ht/adminmodifpass.do' ,
			params :{password : np} ,
			callback : function(json){
				if(json.success){
                	$('#oldpass').val('') ;
                	$('#newpass').val('') ;
                	$('#newpass2').val('') ;
                	$("#modifPassword").omDialog('close');
                	$('#info_modif').html('');
                }else{
                    $('#info_modif').html(json.info);
                }
			}
		}
		
		ajaxpost(dta) ;
	}
}


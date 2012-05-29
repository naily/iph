$(document).ready(function(){
	
    $('#but-login').bind('click' , function(){
        
        var n = $(':input[name="username"]').val() ;
        var p = $(':input[name="mypassword"]').val() ;
        var c = $(':input[name="code"]').val() ;
        
        if(!n){
            //alert('请输入用户名') ;
        	//at({cont:'请输入用户名'});
        	$('#errorMessages').html('请输入用户名').show();
            return  ;
        }
        if(!p){
            //at({cont:'请输入密码'});
            $('#errorMessages').html('请输入密码').show();
            return  ;
        }
        if(!c){
            //at({cont:'请输入验证码'});
            $('#errorMessages').html('请输入验证码').show();
            return  ;
        }
        
        var login = {
            url :'ht/login.do' ,
            params : {loginId:n , password:p ,code :c} ,
            callback :function(json){
                if(json.success){
                    location.reload() ;
                }else{
	                //at({cont:json.info , type : 'error'});
                	$('#errorMessages').html(json.info).show();
	                
	                $.omMessageTip.show({
		                type:'error',
		                title:'提醒',
		                timeout : 2000 ,
		                content:json.info
		            });
                }
            }
        }
        
        ajaxpost(login);
    });
    
});

function reloadimage(){
	$('#vailcode').attr('src' , 'ht/logincode.do?'+new Date().getTime());
}

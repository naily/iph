$(document).ready(function(){
	var statusLock = true ;  //防止在服务器未响应前重复请求
	function loginaction(){
        
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
            	statusLock = true ;
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
        
        if(statusLock){
        	statusLock = false ;
	        ajaxpost(login);
        }else{
        	$.omMessageTip.show({
                type:'error',
                title:'提醒',
                timeout : 1000 ,
                content :"稍安勿躁"
            });
            return  ;
        }
    }
	
	$("body").keydown(function(event) {  
		if(event.keyCode==13){
			loginaction() ;
		}
    }) ;
	
    $('#but-login').bind('click' , loginaction);
    
});

function reloadimage(){
	$('#vailcode').attr('src' , 'ht/logincode.do?'+new Date().getTime());
}

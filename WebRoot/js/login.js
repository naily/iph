$(document).ready(function(){
    $('#but-login').bind('click' , function(){
        
        var n = $(':input[name="username"]').val() ;
        var p = $(':input[name="mypassword"]').val() ;
        var c = $(':input[name="code"]').val() ;
        
        if(!n){
            alert('请输入用户名') ;
            return  ;
        }
        if(!p){
            alert('请输入密码') ;
            return  ;
        }
        if(!c){
            alert('请输入验证码') ;
            return  ;
        }
        
        var login = {
            url :'ht/login.do' ,
            params : {loginId:n , password:p ,code :c} ,
            callback :function(json){
                if(json.success){
                    
                }else{
                    
                }
                alert(json.info) ;
            }
        }
        
        ajaxpost(login);
    });
    
});

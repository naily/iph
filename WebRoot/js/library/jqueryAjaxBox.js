/*
 * *简易封装jquery $ajax post 调用方法
 */
function ajaxpost(config){
	$.ajax({
		dataType : 'JSON' ,
		type : 'POST' ,
		url : config.url + "?timestamp=" + new Date().getTime() ,
		data : config.params ,
		contentType: "application/x-www-form-urlencoded;charset=UTF-8" ,
		success: config.callback
	}); 
}

function notEmpty(s){
    if(''!= s && null != s && undefined != s){
        return true ;
    }else{
        return false ;
    }
}
//为空则返回true
function isEmpty(s){
    if(s){
        return false ;
    }else{
        return true ;
    }
}


function at(config){
        $.omMessageBox.alert({
                type: config.type ? config.type : 'alert',
                title:'警告',
                content: config.cont 
            });

    }
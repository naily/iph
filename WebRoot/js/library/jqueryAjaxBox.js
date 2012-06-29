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
/**
 * 一些全局变量
 * @param {} s
 * @return {Boolean}
 */
var pageslimit = 10 ; //grid每页显示条数

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
	if(true){
        $.omMessageBox.alert({
                type: config.type ? config.type : 'alert',
                title:'警告',
                content: config.cont 
            });
	}
}

//右下角信息提示
function msgTip(config){
	if(true){
		$.omMessageTip.show({
			                type    : config.type  ? config.type : 'alert',
			                title   : config.title ? config.title :'提醒',
			                timeout : config.timeout ? config.timeout : 2000 ,
			                content : config.content
			            });
	}
}
var global = {
    fsave  : "保存" ,
    fclear : "清空"
    
}
function alertMsg(txt){
  $.omMessageBox.alert({
                type:'error',
                title:'提示',
                content:txt,
                onClose:function(value){
                   // alert('do something');
                }
      });
}

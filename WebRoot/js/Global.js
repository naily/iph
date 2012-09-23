var global = {
	fsave : "保存",
	fclear : "清空"

}
function alertMsg(txt) {
	$.omMessageBox.alert({
				type : 'error',
				title : '提示',
				content : txt,
				onClose : function(value) {
					// alert('do something');
				}
			});
}

function tipOK(){
	$.omMessageTip.show({
        type:'success',
        title:'提醒',
        timeout : 3000 ,
        content:'删除成功'
    });
}

/*
 * 图片浏览器
 * gridId ： gridID
 * i      :  索引序号
 */
function previewImage(gridId , i , fidldName,title_){
	if(gridId && i > -1){
		var store = $(gridId).omGrid('getData');
		
		if( i < store.rows.length){
			//当前图片
			var gpath = (store.rows)[i][fidldName] ;
			$( "#imagePreview").html('<img src=".'+ gpath +'" border=0 height=500 / >' +
				'<p><input id="but1" type="button" value="上一张" /><input id="but2" type="button" value="放大" />'+(i+1)+'/'+store.rows.length+'<input id="but3" type="button" value="缩小" /><input id="but4" type="button" value="下一张" /></p>');
			
			$( "#imagePreview").omDialog({title:title_});
			if( !$("#imagePreview").omDialog('isOpen')){
				$( "#imagePreview").omDialog('open');
			}
			
			//绑定事件
			$("#but1").one("click", function(){
				previewImage(gridId , i-1,fidldName,title_) ;
			});
			$("#but4").one("click", function(){
				previewImage(gridId , i+1,fidldName,title_) ;
			});
			
			$("#but2").bind("click", function(){
				var w = $( "#imagePreview img").attr("width");
				var h = $( "#imagePreview img").attr("height");
				if(w){
					$( "#imagePreview img").attr("width" , w*1.2);
				}
				if(h){
					$( "#imagePreview img").attr("height" , h*1.2);
				}
			});
			$("#but3").bind("click", function(){
				var w = $( "#imagePreview img").attr("width");
				var h = $( "#imagePreview img").attr("height");
				if(w){
					$( "#imagePreview img").attr("width" , w/1.2);
				}
				if(h){
					$( "#imagePreview img").attr("height" , h/1.2);
				}
			});
		}else{
			alert('没有下一张了' ) ;
		}
	}else{
		alert('没有上一张了' ) ;
	
	}
	
}




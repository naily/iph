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
			
			$( "#imagePreview").omDialog({title:title_ ,height: 'auto' ,width :'auto'});
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

/*
 * 图片浏览器（图片地址，弹出窗口标题）
 *  支持缩放，不翻页
 */
function previewImageForScanpic(filePath_,title_){			
			$( "#imagePreview").html('<img src=".'+ filePath_ +'" border=0 height=500 / >' +
				'<p><input id="but2" type="button" value="放大" />&nbsp;&nbsp;<input id="but3" type="button" value="缩小" /></p>');
			
			$( "#imagePreview").omDialog({title:title_, height: 'auto' , width :'auto'});
			if( !$("#imagePreview").omDialog('isOpen')){
				$( "#imagePreview").omDialog('open');
			}						
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

	
}
/*
 * 图片浏览器（通过数组存储数据）
 * dataAry ： 图片数组
 * i      :  索引序号
 */
function previewImageA(dataAry , i ,title_){
	if(dataAry && i > -1){
		
		if( i < dataAry.length){
			//当前图片
			var gpath = dataAry[i] ;
			$( "#imagePreview").html('<img src=".'+ gpath +'" border=0 height=500 / >' +
				'<p><input id="but1" type="button" value="上一张" /><input id="but2" type="button" value="放大" />'+(i+1)+'/'+dataAry.length+'<input id="but3" type="button" value="缩小" /><input id="but4" type="button" value="下一张" /></p>');
			
			$( "#imagePreview").omDialog({title:title_ ,height: 'auto' ,width :'auto'});
			if( !$("#imagePreview").omDialog('isOpen')){
				$( "#imagePreview").omDialog('open');
			}
			
			//绑定事件
			$("#but1").one("click", function(){
				previewImageA(gridId , i-1,title_) ;
			});
			$("#but4").one("click", function(){
				previewImageA(gridId , i+1,title_) ;
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

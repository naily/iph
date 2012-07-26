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
//将超链接转化为表单提交
function linkClick(linkObject) {
	var formObject = document.createElement('form');
	document.body.appendChild(formObject);
	formObject.setAttribute('method', 'post');
	var url = linkObject.href;
	var uri = '';
	var i = url.indexOf('?');

	if (i == -1) {
		formObject.action = url;
	} else {
		formObject.action = url.substring(0, i);
	}

	if (i >= 0 && url.length >= i + 1) {
		uri = url.substring(i + 1, url.length);
	}

	var sa = uri.split('&');

	for (var i = 0; i < sa.length; i++) {
		var isa = sa[i].split('=');
		var inputObject = document.createElement('input');
		inputObject.setAttribute('type', 'hidden');
		inputObject.setAttribute('name', isa[0]);
		inputObject.setAttribute('value', isa[1]);
		formObject.appendChild(inputObject);
	}

	formObject.submit();

	return false;
}
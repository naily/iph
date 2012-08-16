

/* 查询元数据 */
function userComment() { 
	var commComment = $('#userCommentContent').val();
	alert(commComment);
	if(!commComment) {
		alertMsg('不能发表空内容');
		return false;
	}
	return true;
}

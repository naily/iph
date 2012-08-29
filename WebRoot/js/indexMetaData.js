/**
 * 首页元数据
 */
$(document).ready(function() {
	// 首页元数据列表（默认3条记录）
	var indexMetaData1 = {
		url : 'qt/mataDataIndex.do',
		params : {

		},
		callback : function(jsona) {
			if (jsona.success) {
				$.each(jsona.metaList, function(idx_, item_) {
							$('#metaData_Title' + idx_)
									.html('<a href="qt/metaDataPriview.do?mdId='
											+ item_.mdId
											+ '" class="a3">'
											+ item_.title + '</a>');
							if(item_.thumbnailFilePath){
							    $('#metaData_img' + idx_).html('<img src="'+item_.thumbnailFilePath+'" width="120" height="90"/>');
							}else{
								 $('#metaData_img' + idx_).html('');
							}
							
							$('#metaData_Summary' + idx_).html(item_.summary);
						});

			}
		}
	}
	ajaxpost(indexMetaData1);

});
/* 查询元数据 */
function queryMetaData() {

	var queryKey = $('#metaDataKeyword').val();
	if (queryKey) {
		

	} else {
		alertMsg('请输入检索关键字');
		return false;
	}
}

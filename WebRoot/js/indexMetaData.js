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
	if($('#index_metadata_table')){
		ajaxpost(indexMetaData1);
	}
	
	/**
	 * 处理首页元数据查询按钮
	 */
	var defval = $('#metaDataKeyword').val() ;
	$('#metaDataKeyword').focus(function(){
		$('#metaDataKeyword').removeClass('inputtext') ;
		if($(this) && $(this).val() != defval){
		}else{
			$('#metaDataKeyword').val('') ;
		}
	});
	$('#metaDataKeyword').blur(function(){
		
		if($(this).val() && $(this).val() != defval){
			
		}else{
			$('#metaDataKeyword').addClass('inputtext') ;
			$('#metaDataKeyword').val(defval) ;
		}
	});
	
	/* 查询元数据触发查询按钮时 */
	$('#metaDataQueryForm').submit(function(){
		var va = $('#metaDataKeyword').val();
		if(va == defval){
			$('#metaDataKeyword').val('') ;
		}
		
		return true ;
	}) ;
});



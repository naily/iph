/**
 *首页元数据
 * */
$(document).ready(function() {	
	//首页元数据列表（默认3条记录）
	var indexMetaData1 = {
		url : 'qt/mataDataIndex.do',
		params : {
			
		},
		callback : function(jsona) {
		  if(jsona.success) {					
			$.each(jsona.metaList, function(idx_, item_) {			
				$('#metaData_Title'+idx_).html('<a href="qt/metaDataPriview.do?mdId='+item_.mdId+'" class="a3">'+item_.title+'</a>');
				$('#metaData_img'+idx_).html('');
				$('#metaData_Summary'+idx_).html(item_.summary);
			});
			
			}
		}
	}
	ajaxpost(indexMetaData1);

});
/*查询元数据*/
function queryMetaData(){
	var queryKey =$('#metaDataKeyword').val();
  if(queryKey==''){
     alertMsg('请输入检索关键字');       
  }else{
     location.href='qt/metaDataList.do?title='+queryKey;
  }
}

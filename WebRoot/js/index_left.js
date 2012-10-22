/**
 * 
 * 网页左侧导航 1、频高图 2、电离参数 3、扫描图
 * @author gls
 * @date 2012-08-24
 */
$(document).ready(function() {
			loadLeftTree('T_IRONOGRAM', 'pgtTree');// 1
			loadLeftTree('T_PARAMETER', 'paraTree');// 2
			loadLeftTree('T_SCANPIC', 'scanpicTree');// 3

		});
/**
 * paraName:参数类型（对应数据库表）
 * domID：页面节点id
 * */
function loadLeftTree(paraName, domID) {
	/*
	 * var datasource = [{ "text" : "武汉观测站", "expanded" : true, "children" : [{"text" : "1946年" }, { "text" : "1947年" }] }];
	 */
	var datasource;
	var ajax_data = {
		url : 'qt/indexLeftTree.do',
		params : {
			dataTable : paraName
		},
		callback : function(json) {
			if (json.success) {
				datasource = json.data;
			}
			$("#" + domID).omTree({
						dataSource : datasource,
						showCheckbox : false,
						showIcon : false,
						simpleDataModel:true,
						onClick : function(node, event) {
							var stationid;
							if(node.pid){
								stationid =node.pid;
							}
							if(node.id){
								stationid =node.id;
							}
							//alert(node.pid+''+node.id);
							
							if(paraName=='T_IRONOGRAM'){//频高图
								location.href = basepath+'qt/listPGT.do?queryYear='+ node.text+'&ids='+stationid;
							}else if(paraName=='T_PARAMETER'){//电离参数
								location.href = basepath+'qt/paraDataQuery.do';
							}else{//报表扫描图
								location.href = basepath+'qt/listScanPic.do?queryYear='+ node.text+'&ids='+stationid;
							}
							
						}
					});
			//$("#mytree").omTree({simpleDataModel:true});
		}
	}
	ajaxpost(ajax_data);
}
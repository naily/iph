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
						onClick : function(node, event) {							
							if(paraName=='T_IRONOGRAM'){//频高图
								location.href = 'qt/listPGT.do?gramTitle='+ node.text;
							}else if(paraName=='T_PARAMETER'){//电离参数
								location.href = 'qt/paraDataQuery.do';
							}else{//报表扫描图
								location.href = 'qt/listScanPic.do?gramTitle='+ node.text;
							}
							
						}
					});
		}
	}
	ajaxpost(ajax_data);
}
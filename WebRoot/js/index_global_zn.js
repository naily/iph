//性别下拉列表
gender_omCombo_datasource=[{text : '男',value : '男'}, {text : '女',value : '女'} ]
//学历下拉列表
eduBackground_omCombo_datasource=[{text : '博士',value : '01'},{text : '硕士',value : '02'}, {text : '本科',value : '03'}, {text : '本科以下',value : '04'}]
//
vocation_omCombo_datasource=[{text : '科研院所',value : '01'},{text : '教育院校',value : '02'},{text : '政府机关',value : '03'},{text : '企事业单位',value : '04'}, {text : '民间组织',value : '05'}, {text : '其他',value : '06'}]
//国家
country_omCombo_datasource=[{text : '中国',value : '86'}, {text : '美国',value : '01'}]
/**
 * 通过国家级联地区
 */
function getCityRecords() {
	var country = $('#country').omCombo('value');
	if (country == '86') {// 中国
		return [{
					'text' : '北京市',
					'value' : '110000'
				}, {
					'text' : '江苏省',
					'value' : '320000'
				}];
	} else if (country == '01') {// 美国
		return [{
					'text' : '夏威夷',
					'value' : 'hawaii'
				}, {
					'text' : '佛罗里达',
					'value' : 'florida'
				}];
	} else {
		return [];
	}
}
var select_station='选择观测站'
var selected_station="已选观测站"
var select_parameter='选择参数'
var selected_parameter='已选参数'
var select_type_parameter='电离层参数'
var select_type_ionogram='电离层频高图'
var select_type_Report_scan='报表扫描图'
var select_query_type='选择查询类型'
var order_by_station='观测站'
var order_by_date='观测日期'




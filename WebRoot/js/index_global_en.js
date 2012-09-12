//性别下拉列表
gender_omCombo_datasource=[{text : 'Male',value : '男'}, {text : 'Female',value : '女'} ]
//学历下拉列表
eduBackground_omCombo_datasource=[
							{text : '博士',		value : '01'},
							{text : '硕士',		value : '02'},
							{text : '本科',		value : '03'}, 
							{text : '本科以下',	value : '04'}]
//
vocation_omCombo_datasource=[
							{text : '科研院所',	value : '01'},
							{text : '教育院校',	value : '02'},
							{text : '政府机关',	value : '03'},
							{text : '企事业单位',	value : '04'},
							{text : '民间组织',	value : '05'},
							{text : '其他',		value : '06'}]
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
    var select_station='Station'
	var selected_station="Selected"
	var select_parameter='Parameter'
	var selected_parameter='Selected'
	var select_type_parameter='parameters'
	var select_type_ionogram='ionogram'
	var select_type_Report_scan='Report scan'
	var select_query_type='select type'
	var order_by_station='Station'
	var order_by_date='Date'

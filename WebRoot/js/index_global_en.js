//性别下拉列表
gender_omCombo_datasource=[{text : 'Male',value : '男'}, {text : 'Female',value : '女'} ]
//学历下拉列表
eduBackground_omCombo_datasource=[
							{text : 'Doctor',		value : '01'},
							{text : 'Master',		value : '02'},
							{text : 'undergraduate',		value : '03'}, 
							{text : 'Below the undergraduate',	value : '04'}]
//
vocation_omCombo_datasource=[
							{text : 'scientific research institutions',	value : '01'},
							{text : 'Educational institutions',	value : '02'},
							{text : 'government agency',	value : '03'},
							{text : 'enterprise and public institution',	value : '04'},
							{text : 'non-governmental sector',	value : '05'},
							{text : 'Other',		value : '06'}]
//国家
country_omCombo_datasource=[{text : 'China',value : '86'}, {text : 'America(USA)',value : '01'} ,{text : 'Japan',value : '81'}]

/**
 * 通过国家级联地区
 */
function getCityRecords() {
	var country = $('#country').omCombo('value');
	if (country == '86') {// 中国
		return [{text : '北京' , value:'1100000'},
				{text : '天津' , value:'1200000'},
				{text : '河北' , value:'1300000'},
				{text : '山西' , value:'1400000'},
				{text : '辽宁' , value:'2100000'},
				{text : '吉林' , value:'2200000'},
				{text : '上海' , value:'3100000'},
				{text : '江苏' , value:'3200000'},
				{text : '浙江' , value:'3300000'},
				{text : '安徽' , value:'3400000'},
				{text : '福建' , value:'3500000'},
				{text : '江西' , value:'3600000'},
				{text : '山东' , value:'3700000'},
				{text : '河南' , value:'4100000'},
				{text : '湖北' , value:'4200000'},
				{text : '湖南' , value:'4300000'},
				{text : '广东' , value:'4400000'},
				{text : '广西' , value:'4500000'},
				{text : '海南' , value:'4600000'},
				{text : '重庆' , value:'5000000'},
				{text : '四川' , value:'5100000'},
				{text : '贵州' , value:'5200000'},
				{text : '云南' , value:'5300000'},
				{text : '西藏' , value:'5400000'},
				{text : '陕西' , value:'6100000'},
				{text : '甘肃' , value:'6200000'},
				{text : '青海' , value:'6300000'},
				{text : '宁夏' , value:'6400000'},
				{text : '新疆' , value:'6500000'},
				{text : '内蒙古' , value:'1500000'},
				{text : '黑龙江' , value:'2300000'},
				{text : '青岛市' , value:'9600000'},
				{text : '厦门市' , value:'9700000'},
				{text : '宁波市' , value:'9800000'},
				{text : '大连市' , value:'9900000'}
				];
	} else if (country == '01') {// 美国
		return [{
					'text' : 'New York',
					'value' : '01001'
				}, {
					'text' : 'Los Angeles',
					'value' : '01002'
				}, {
					'text' : 'Chicago',
					'value' : '01003'
				}, {
					'text' : 'Houston',
					'value' : '01004'
				}, {
					'text' : 'Phoenix',
					'value' : '01005'
				}, {
					'text' : 'Philadelphia',
					'value' : '01006'
				}, {
					'text' : 'San Antonio',
					'value' : '01007'
				}, {
					'text' : 'Dallas',
					'value' : '01008'
				}, {
					'text' : 'San Diego',
					'value' : '01009'
				}, {
					'text' : 'San Jose',
					'value' : '01010'
				}];
	}else if (country == '81') {// 日本
		return [{
					'text' : 'Tokyo',
					'value' : '810001'
				}, {
					'text' : 'Yokohama',
					'value' : '810002'
				},{
					'text' : 'Osaka ',
					'value' : '810003'
				},{
					'text' : 'Nagoya',
					'value' : '810004'
				},{
					'text' : 'Sapporo',
					'value' : '810005'
				},{
					'text' : 'Kobe ',
					'value' : '810006'
				},{
					'text' : 'Kyoto',
					'value' : '810007'
				},{
					'text' : 'Fukuoka',
					'value' : '810008'
				},{
					'text' : 'Kawasaki ',
					'value' : '810009'
				},{
					'text' : 'Saitama ',
					'value' : '810010'
				},{
					'text' : 'Hiroshima',
					'value' : '810011'
				},{
					'text' : 'Sendai',
					'value' : '810012'
				}];
	} else {
		return [{
					'text' : 'other',
					'value' : '0'
				}];
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
	var selected_only_sation='Can select only one station'
	var option_button='Operating'

//性别下拉列表
gender_omCombo_datasource=[{text : '男',value : '男'}, {text : '女',value : '女'} ]
//学历下拉列表
eduBackground_omCombo_datasource=[{text : '博士',value : '01'},{text : '硕士',value : '02'}, {text : '本科',value : '03'}, {text : '本科以下',value : '04'}]
//
vocation_omCombo_datasource=[{text : '科研院所',value : '01'},{text : '教育院校',value : '02'},{text : '政府机关',value : '03'},{text : '企事业单位',value : '04'}, {text : '民间组织',value : '05'}, {text : '其他',value : '06'}]
//国家
country_omCombo_datasource=[{text : '中国',value : '86'}, {text : '美国',value : '01'} ,{text : '日本',value : '81'}   ]
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
					'text' : '纽约市',
					'value' : '01001'
				}, {
					'text' : '洛杉矶',
					'value' : '01002'
				}, {
					'text' : '芝加哥',
					'value' : '01003'
				}, {
					'text' : '休斯敦',
					'value' : '01004'
				}, {
					'text' : '菲尼克斯',
					'value' : '01005'
				}, {
					'text' : '费城',
					'value' : '01006'
				}, {
					'text' : '圣安东尼奥',
					'value' : '01007'
				}, {
					'text' : '达拉斯',
					'value' : '01008'
				}, {
					'text' : '圣迭戈',
					'value' : '01009'
				}, {
					'text' : '圣何塞',
					'value' : '01010'
				}];
	}else if (country == '81') {// 日本
		return [{
					'text' : '东京',
					'value' : '810001'
				}, {
					'text' : '横滨市',
					'value' : '810002'
				},{
					'text' : '大阪市',
					'value' : '810003'
				},{
					'text' : '名古屋市',
					'value' : '810004'
				},{
					'text' : '札幌市',
					'value' : '810005'
				},{
					'text' : '神户市',
					'value' : '810006'
				},{
					'text' : '京都市',
					'value' : '810007'
				},{
					'text' : '福冈市',
					'value' : '810008'
				},{
					'text' : '川崎市',
					'value' : '810009'
				},{
					'text' : '埼玉市',
					'value' : '810010'
				},{
					'text' : '广岛市',
					'value' : '810011'
				},{
					'text' : '仙台市',
					'value' : '810012'
				}];
	} else {
		return [{
					'text' : '其它',
					'value' : '0'
				}];
	}
}
var select_station='选择观测站'
var selected_station="已选观测站"
var select_parameter='选择参数'
var selected_parameter='已选参数'
var select_type_parameter='电离层参数'
var select_type_ionogram='电离层频高图'
var select_type_Report_scan='原始观测报表'
var select_query_type='选择查询类型'
var order_by_station='观测站'
var order_by_date='观测日期'
var selected_only_sation='只能选择一个观测站'
var option_button='操作'




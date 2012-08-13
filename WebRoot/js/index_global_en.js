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
//年份
year_omCombo_datasource=[
							{text : "2009", value : "2009"},
							{text : "2010", value : "2010"},
							{text : "2011", value : "2011"},
							{text : "2012", value : "2012"}]
//月份
month_omCombo_datasource= [ {text : '1',  value : '1' },
							{text : '2',  value : '2' },
							{text : '3',  value : '3' },
							{text : '4',  value : '4' },
							{text : '5',  value : '5' },
							{text : '6',  value : '6' },
							{text : '7',  value : '7' },
							{text : '8',  value : '8' },
							{text : '9',  value : '9' },
							{text : '10', value : '10' },
							{text : '11', value : '11' },
							{text : '12', value : '12' }]

//电离参数
parameter_omCombo_datasource= [ { text : 'foF2',  value : 'foF2'}, 
					           { text : 'hlF2',  value : 'hlF2'}, 
					           { text : 'foF1',  value : 'foF1'}, 
					           { text : 'hlF1',  value : 'hlF1'}, 
					           { text : 'hlF',   value : 'hlF'}, 
					           { text : 'hpF',   value : 'hpF'}, 
					           { text : 'foE',   value : 'foE'}, 
					           { text : 'hlE',   value : 'hlE'}, 
					           { text : 'foEs',  value : 'foEs'}, 
					           { text : 'hlEs',  value : 'hlEs'}, 
					           { text : 'fbEs',  value : 'fbEs'}, 
					           { text : 'Fmin',  value : 'Fmin'}, 
					           { text : 'M3000F2',  value : 'M3000F2'}, 
					           { text : 'M1500F2',  value : 'M1500F2'}, 
					           { text : 'M3000F1',  value : 'M3000F1'}, 
					           { text : 'M3000F',   value : 'M3000F'}
					           ]


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


$(document).ready(function() {
	// 观测站下拉列表
	$('input[name=stationid]').omCombo({ // 初始化Combo
		dataSource : [ { text : '武汉', value : '1' }, {
						text : '上海', value : '2' } ]
												,
		width : 100,
		value:'武汉',
		onValueChange : function() {
			$('input[name=stationid]').focus();

		}
	});

	// 年份
	$('input[name=year]').omCombo({ // 初始化Combo
		dataSource : [ { text : '2011', value : '2011' }, {
						text : '2012',  value : '2012' } ]
												,
		width : 80,
		value:2012,
		onValueChange : function() {
			$('input[name=year]').focus();
		}
	});

	// 月份
	$('input[name=month]').omCombo({ // 初始化Combo
		dataSource : [ { text : '1', value : '1' }, {
						text : '2',  value : '2' } ],
		width : 80,
		value:1,
		onValueChange : function() {
			$('input[name=month]').focus();
		}
	});

	// 国家下拉列表
	$('input[name=parameter]').omCombo({ // 初始化Combo
		dataSource : [ { text : 'foF2', value : 'foF2' }, {
						text : 'h1F2',  value : 'h1F2' } ],
		width : 100,
		value:'foF2',
		onValueChange : function() {
			 $('input[name=parameter]').focus();
			
		}
	});
	

});

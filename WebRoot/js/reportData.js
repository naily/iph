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
		dataSource : "year_list.json",
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
	
	
	$('#reportGrid').omGrid({
                //title : '表格',
                dataSource : 'qt/loadReport.do',
               limit:0,
                colModel : [ {header : 'days', name : 'days', width : 20, align : 'center'}, 

                             {header : 'h00', name : 'h00',  align : 'center',width : 20}, 
                             {header : 'h01', name : 'h01',  align : 'center',width : 20}, 
                             {header : 'h02', name : 'h02',  align : 'center',width : 20}, 
                             {header : 'h03', name : 'h03',  align : 'center',width : 20},
                             {header : 'h04', name : 'h04',  align : 'center',width : 20}, 
                             {header : 'h05', name : 'h05',  align : 'center',width : 20},
                             {header : 'h06', name : 'h06',  align : 'center',width : 20}, 
                             {header : 'h07', name : 'h07',  align : 'center',width : 20},
                             {header : 'h08', name : 'h08',  align : 'center',width : 20}, 
                             {header : 'h09', name : 'h09',  align : 'center',width : 20},
                             {header : 'h10', name : 'h10',  align : 'center',width : 20}, 
                             {header : 'h11', name : 'h11',  align : 'center',width : 20},
                             {header : 'h12', name : 'h12',  align : 'center',width : 20}, 
                             {header : 'h13', name : 'h13',  align : 'center',width : 20},
                             {header : 'h14', name : 'h14',  align : 'center',width : 20}, 
                             {header : 'h15', name : 'h15',  align : 'center',width : 20},
                             {header : 'h16', name : 'h16',  align : 'center',width : 20}, 
                             {header : 'h17', name : 'h17',  align : 'center',width : 20},
                             {header : 'h18', name : 'h18',  align : 'center',width : 20}, 
                             {header : 'h19', name : 'h19',  align : 'center',width : 20},
                             {header : 'h20', name : 'h20',  align : 'center',width : 20}, 
                             {header : 'h21', name : 'h21',  align : 'center',width : 20},
                             {header : 'h22', name : 'h22',  align : 'center',width : 20}, 
                             {header : 'h23', name : 'h23',  align : 'center',width : 20}
                             
                             ]
            });


});

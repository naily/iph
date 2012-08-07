$(document).ready(function() {
	// 选择观测站
	$('#selectorStation').omItemSelector({
				availableTitle : '选择观测站',
				selectedTitle : '已选观测站',
				dataSource : 'qt/listAllStation.do',
				// value:[],
				onItemSelect : function(itemDatas, event) {
					var stationValue = '';
					if (itemDatas.length >= 1) {
						stationValue = itemDatas[0].value
					}
					for (var i = 1; i < itemDatas.length; i++) {
						stationValue += "," + itemDatas[i].value;
					}
					$('#stationIDs').attr({
								value : stationValue
							});

				},
				width : 270,
				height : 345

			});

	// 选择电离参数
	$('#selectorParaS').omItemSelector({
				availableTitle : '选择参数',
				selectedTitle : '已选择参数',
				dataSource : parameter_omCombo_datasource,
				// value:[],
				onItemSelect : function(itemDatas1, event) {
					var paraValue = '';
					if (itemDatas1.length >= 1) {
						paraValue = itemDatas1[0].value
					}
					for (var j = 1; j < itemDatas1.length; j++) {
						paraValue += "," + itemDatas1[j].value;
					}
					$('#parameter').attr({
								value : paraValue
							});
				},
				width : 270,
				height : 345

			});

	$('#startDate').omCalendar();
	$('#endDate').omCalendar();
	$('#showNum').omCombo({
				dataSource : [{
							text : '20',
							value : '20'
						}, {
							text : '50',
							value : '50'
						}, {
							text : '100',
							value : '100'
						}, {
							text : '150',
							value : '150'
						}]
			});

	var colModel_ = [{
				header : '观测站',
				name : 'station.name',
				width : 80
			}, {
				header : '观测日期',
				name : 'createDate',
				align : 'center',
				width : 80
			}, {
				header : 'foF2',
				name : 'foF2',
				align : 'center',
				width : 50
			}];

	// 电离参数列表显示
	$("#paraDataQuery").click(function() {

		var stationId = $('#stationIDs').val();
		var parameter = $('#parameter').val();
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		var allDate;
		if ($("#allDate").attr("checked")) {
			allDate = 'all';
			$('#startDate').attr({
						value : ''
					});
			$('#endDate').attr({
						value : ''
					})
		}
		var pageSize = $('#showNum').val();

		if (stationId && parameter && (startDate || endDate || allDate)) {

			var dUrl = 'qt/doParaDataQuery.do?ids=' + stationId + '&startDate='
					+ startDate + '&endDate=' + endDate + '&allDate=all';

			var cols = getColmModel();
			$('#paraQueryGrid').omGrid({
						title : '电离层参数查询',
						dataSource : dUrl,
						// limit:0,
						height : 325,
						showIndex : false,
						colModel : cols
					});
		} else { // 有查询条件，显示查询数据

			at({
						cont : '请选择条件！',
						type : 'error'
					});
			$('#paraQueryGrid').omGrid("setData", 'qt/paraDataQuery.do');
		}

			// alert($('#parameter').val());
	});

	function getColmModel() {
		var container = new Array();
		container[0] = {
			header : '观测站',
			name : 'station.name',
			width : 80
		}
		container[1] = {
			header : '观测日期',
			name : 'createDate',
			width : 80
		}
		var str = new Array();
		var selectPara = $('#parameter').val();
		str = selectPara.split(",");
		for (i = 0; i < str.length; i++) {
			container[i + 2] = {
				header : str[i],
				name : str[i],
				width : 50
			}
		}

		return container;

	}

});

$(document).ready(function() {
	// 选择观测站
	$('#selectorStation').omItemSelector({
				availableTitle : '选择观测站',
				selectedTitle : '已选观测站',
				dataSource : 'qt/listAllStation.do',
				// value:[],
				/*onItemSelect : function(itemDatas, event) {
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

				},*/
				width : 270,
				height : 345

			});

	// 选择电离参数
	$('#selectorParaS').omItemSelector({
				availableTitle : '选择参数',
				selectedTitle : '已选择参数',
				dataSource : parameter_omCombo_datasource,
				// value:[],
			/*	onItemSelect : function(itemDatas1, event) {
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
				},*/
				width : 270,
				height : 345

			});
	// 选择查询数据类型
	$('#selectDataType').omCombo({
				dataSource:[
                {text:'电离层参数',value:'1'},
                {text:'电离层频高图',value:'2'},
                {text:'报表扫描图',value:'3'}             
		        ],
		       /* optionField:function(data,index){
		            return '<font color="red">'+index+'：</font>'+data.text+'('+data.value+')';
		        },*/
		        emptyText:'选择查询类型',
		      //  value:'1',
		     //   editable:false,
		      //  lazyLoad:true,
		        listMaxHeight:65

			});
		
	
	// 开始时间
	$('#startDate').omCalendar();
	// 截止时间
	$('#endDate').omCalendar();
	// 排序方式选择
	$('#orderCol').omCombo({
				dataSource : [{
							text : '观测站',
							value : 'stationID'
						}, {
							text : '观测日期',
							value : 'createDate'
						}]
			});
	// 页面显示记录
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
			}];

	// 电离参数列表显示

	/*var dUrl = 'qt/doParaDataQuery.do';
	$('#paraQueryGrid').omGrid({
				// title : '电离层参数查询',
				dataSource : dUrl,
				// limit:0,
				height : 325,
				showIndex : false,
				colModel : colModel_
			});*/
	$("#paraDataQuery").click(function() {
        //alert($('#selectorParaS').omItemSelector('value'));
		//var stationId = $('#stationIDs').val();
		var stationId = $('#selectorStation').omItemSelector('value');
		//var parameter = $('#parameter').val();
		var parameter = $('#selectorParaS').omItemSelector('value');
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		var allDate = '';
		if ($("#allDate").attr("checked")) {
			allDate = 'all';
		}
		var pageSize = $('#showNum').val();
		var orderBy = $('#orderCol').val();
		var queryDataType =$('#selectDataType').val();
	    
		if ((queryDataType!=1 ||  parameter) && stationId  && (startDate || endDate || allDate)) {
			var tableCols,datasourceUrl;			
			tableCols=getColmModel(queryDataType,parameter);
			if(queryDataType==1){//电离层参数查询
				datasourceUrl='qt/doParaDataQuery.do'
			}else if(queryDataType==2){//电离层频高图查询				
				datasourceUrl='qt/queryPGT.do'
			}else{//报表扫描图查询
			    datasourceUrl='qt/queryScanpic.do'
			}
			 datasourceUrl+='?ids=' + stationId + '&startDate='+ startDate + '&endDate=' + endDate + '&selectAllDate='+ allDate + '&orderBy=' + orderBy + '&pageSize=' + pageSize;
			 $('#paraQueryGrid').omGrid({
				//title : '电离层参数查询',
			 	dataSource :datasourceUrl, // limit:0, 
			 	height : 325, 
			 	showIndex : false,
			 	colModel :tableCols
			 	});
			 
			//$('#paraQueryGrid').omGrid('setData', dUrl);

	    	} else { // 有查询条件，显示查询数据

			at({
						cont : '请选择条件！',
						type : 'error'
					});
			$('#paraQueryGrid').omGrid("setData", 'qt/paraDataQuery.do');
		}

			// alert($('#parameter').val());
	});
  /**
   * 根据查询类型，组装查询数据的表头
   * */
	function getColmModel(queryType,paraCol) {
		var container = new Array();//数据表格的表头数据
		container[0] = {
			header : '所属观测站',
			name : 'station.name',
			width : 80
		}
		container[1] = {
			header : '观测日期',
			name : 'createDate',
			width : 80
		}
		if(queryType==1){//电离参数
				var str = new Array();
				//var selectPara = $('#parameter').val();
				//str = selectPara.split(",");			
				//for (i = 0; i < str.length; i++) {
				for (i = 0; i < paraCol.length; i++) {
					container[i + 2] = {
						header : paraCol[i],
						name : paraCol[i],
						width : 40
					}
				}
			container[paraCol.length+2]={
		           header : '操作',
						name : 'operateTYpe',
						renderer: function(colValue, rowData, rowIndex){
	                         	return '<a href="javascript:previewStation(\''+rowData.stationID+'\');" class="a3">报表扫描图</a>&nbsp;<a href="javascript:previewStation(\''+rowData.stationID+'\');" class="a3">电离频高图</a>'   ;
	                         },
						width : 150
			 }
		}else if(queryType==2){//电离频高图
				container[2] = {
						header : '电离层频高图名称',
						name : 'gramTitle',
						width : 100
					}
			  container[3]={
		           header : '操作',
						name : 'operateTYpe',
						renderer: function(colValue, rowData, rowIndex){
	                         	return '<a href="javascript:previewStation(\'\');" class="a3">报表扫描图</a>&nbsp;<a href="javascript:previewStation(\'\');" class="a3">电离层参数</a>'   ;
	                         },
						width : 150
			 }
		}else{//报表扫描图
		
		      container[2] = {
						header : '报表扫描图名称',
						name : 'scanPicTitle',
						width : 100
					}
			container[3]={
		           header : '操作',
						name : 'operateTYpe',
						renderer: function(colValue, rowData, rowIndex){
	                         	return '<a href="javascript:previewStation(\'\');" class="a3">电离层参数</a>&nbsp;<a href="javascript:previewStation(\'\');" class="a3">电离频高图</a>'   ;
	                         },
						width : 150
			 }
		}
		
		return container;

	}

});

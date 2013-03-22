$(document).ready(function() {
	
	    //var stationId = $('#stationIdParaQuery').val();	
	    var stationId = $('#stationIDV').val();	
		var startDateV = $('#startDateV').val();
		var endDateV = $('#endDateV').val();
		var selectTypeValue='';
		var initParaValue = new Array();
		if(stationId && startDateV && endDateV){
			initParaValue =parameter_array;
			$('#startDate').val(startDateV);
			$('#endDate').val(endDateV);
			selectTypeValue='1';
			//$('#selectDataType').val('1');
			tableCols_=getParaColmModel(1,parameter_array_text);	
			 datasourceUrl='qt/doParaDataQuery.do?ids=' + stationId + '&startDate='+ startDateV + '&endDate=' + endDateV;
			 $('#paraQueryGrid').omGrid({
				//title : '电离层参数查询',
			 	dataSource :datasourceUrl, // limit:0, 
			 	height : 415, 
			 	limit:pageslimit,
			 	showIndex : false,
			 	colModel :tableCols_
			 	});
			//$("#downloadParaData").show();
		}
/*	var paraValue;
	var selectOk=true;
	// 选择观测站
	$('#selectorStation').omItemSelector({
				availableTitle : select_station,
				selectedTitle : selected_station,
				dataSource : 'qt/listAllStation.do',
				value:[stationId],
				  onBeforeItemSelect:function(itemDatas, event){
	                if(itemDatas.length<=1 && selectOk){
	                	   paraValue=itemDatas[0].value;
	                	   $('#stationIDs').attr({value:itemDatas[0].value});
	                	    selectOk=false;
	                	}else{	           			
	           			  at({cont:selected_only_sation , type : 'error'});	                	
	                	  return false;
	                	}
                },
                 onItemDeselect:function(itemDatas, event){ 
                 	  selectOk=true;
                	 $('#stationIDs').attr({value:''});
                 },
				width : 270,
				height : 300

			});*/
//观测站下拉框
    $('input[name=stationIdParaQuery]').omCombo({       
    	dataSource:'qt/listAllStation.do' ,
        valueField : 'value' ,
        optionField :'text',
        value:stationId,
        width : 100	
    }) ;
	// 选择电离参数
	$('#selectorParaS').omItemSelector({
				availableTitle : select_parameter,
				selectedTitle : selected_parameter,
				//dataSource : parameter_omCombo_datasource,
				dataSource : parameter_month_omCombo_datasource,
				
				value:initParaValue,
				//value:parameter_array,
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
				width : 300,
				height : 300

			});
	// 选择查询数据类型
	$('#selectDataType').omCombo({
				dataSource:[
                {text:select_type_parameter,value:'1'},
                {text:select_type_ionogram,value:'2'},
                {text:select_type_Report_scan,value:'3'}             
		        ],
		       /* optionField:function(data,index){
		            return '<font color="red">'+index+'：</font>'+data.text+'('+data.value+')';
		        },*/
		        emptyText:select_query_type,
		         value:selectTypeValue,
		     //   editable:false,
		      //  lazyLoad:true,
		        listMaxHeight:65

			});
		
	
	// 开始时间
	$('#startDate').omCalendar();
	// 截止时间
	$('#endDate').omCalendar();
	// 排序方式选择
/*	$('#orderCol').omCombo({
				dataSource : [{
							text : order_by_station,
							value : 'stationID'
						}, {
							text : order_by_date,
							value : 'createDate'
						}]
			});*/
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
/*			   //图片预览弹出
    $( "#imagePreview").omDialog({
        autoOpen: false,
        height: 'auto' ,
        width :'auto'
    });
$('#paraQueryGrid2').omGrid({
						//title : '电离层参数查询',
					 	//dataSource :datasourceUrl_,
					 	limit:30, 
					 	//height : 380, 
					 	//showIndex : false,
					 	colModel :getParaColmModel(1,parameter_array) 

					 	});*/
/*	var colModel_ = [{
				header : '观测站',
				name : 'station.name',
				width : 80
			}, {
				header : '观测日期',
				name : 'createDate',
				align : 'center',
				width : 80
			}];*/
$("#allDate").click(function() {
	  if($("#allDate").attr("checked")){
	  	$('#startDate').val('');
		$('#endDate').val('');
	  }
		
		});
	// 电离参数列表显示
	$("#paraDataQuery").click(function() {
		//var stationId = $('#stationIDs').val();
		var stationId = $('#stationIdParaQuery').val();
		//var stationId = $('#selectorStation').omItemSelector('value');
		var parameter = $('#selectorParaS').omItemSelector('value');
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		var allDate = '';
		if($("#allDate").attr("checked")) {
			allDate = 'all';
			startDate='';
			endDate='';
		}
		var pageSize = $('#showNum').val();
		if(pageSize){
		  pageslimit=pageSize;
		}
		//var orderBy = $('#orderCol').val();
		var orderBy = '';
		var queryDataType =$('#selectDataType').val();

		//if ((queryDataType!=1 ||  parameter) && stationId  && (startDate && endDate || allDate)) {
		if ((queryDataType!=1 ||  parameter) || stationId  || (startDate && endDate || allDate)) {
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
			 	 onSuccess:function(data,testStatus,XMLHttpRequest,event){
				  if(data.protectArea){
					  //alert('您查看的数据区间与本系统设置的保护期有重叠，保护期区间为：['+data.protectArea+']，保护期内的数据仅显示前50条记录。');
					  at({
						  title:'提示',
						  cont : '您查看的数据区间与本系统设置的保护期有重叠，保护期区间为：['+data.protectArea+']，保护期内的数据仅显示前50条记录。',
						  type : 'alert'
						});
				  }
		         //alert('fetch data success,got '+data.rows+' rows');
		     },
			 	height : 415, 
			 	limit:pageslimit,
			 	showIndex : false,
			 	colModel :tableCols
			 	});
			 
			//$('#paraQueryGrid').omGrid('setData', dUrl);
			 if(queryDataType==1){
			 	$("#downloadParaData").show();
			 }else{
			 	$("#downloadParaData").hide();
			 }
			  
	    	} else { // 有查询条件，显示查询数据

			at({
						cont : '请检查查询条件，“观测站"、"查询类型","日期条件"必须选择！',
						type : 'error'
					});
			$('#paraQueryGrid').omGrid("setData", 'qt/paraDataQuery.do');
		}

			// alert($('#parameter').val());
	});
	$("#downloadParaData").click(function() {
		//var stationId = $('#selectorStation').omItemSelector('value');
		var stationId = $('#stationIdParaQuery').val();
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
		 datasourceUrl='qt/downloadParaData.do?ids=' + stationId + '&startDate='+ startDate + '&endDate=' + endDate + '&selectAllDate='+ allDate + '&orderBy=' + orderBy + '&pageSize=' + pageSize;
		window.open(basepath + datasourceUrl)
		
		   //图片预览弹出
    $( "#imagePreview").omDialog({
        autoOpen: false,
        height: 'auto' ,
        width :'auto'
    });
		
	});
  /**
   * 根据查询类型，组装查询数据的表头
   * 
   * */
	function getColmModel(queryType,paraCol) {
		var container = new Array();//数据表格的表头数据
		/*container[0] = {
			//header : '所属观测站',
			header : order_by_station+'112',
			name : 'station.name',
			//width : 80
			width : 0
		}*/
		//container[1] = {
		container[0] = {
			//header : '观测日期',
			header : order_by_date,
			name : 'createDate',
			width : 140
		}
		if(queryType==1){//电离参数
				var str = new Array();
				//var selectPara = $('#parameter').val();
				//str = selectPara.split(",");			
				//for (i = 0; i < str.length; i++) {
				
				for (i = 0; i < paraCol.length; i++) {
					container[i + 1] = {
						header : paraCol[i],
						name : paraCol[i],
						width : 60
					}
				}
			container[paraCol.length+1]={
		           header : option_button,
						name : 'operateTYpe',
						renderer: function(colValue, rowData, rowIndex){
							var optHrefStr='';
							//alert('address='+rowData.station.address,"homepage="+rowData.station.homepage);
							if(rowData.station.address=='1'){//此处address=1 表示 有对应的扫描图
							  optHrefStr='<a href="javascript:previewScanpic(\''+rowData.stationID+'\',\''+rowData.createDate+'\');" class="a3">'+select_type_Report_scan+'</a>';
							}
							
							if(rowData.station.homepage=='1'){//此处homepage=1 表示 有对应的频高图
								optHrefStr+='&nbsp;<a href="javascript:previewPgt(\''+rowData.stationID+'\',\''+rowData.createDate+'\');" class="a3">'+select_type_ionogram+'</a>';
							}
	                        return  optHrefStr ;
	                         },
						width : 150
			 }
		}else if(queryType==2){//电离频高图
				container[1] = {
						//header : '电离层频高图名称',
						header : select_type_ionogram,
						name : 'gramTitle',
						width : 300
					}
			  container[2]={
		           header : option_button,
						name : 'operateTYpe',
						renderer: function(colValue, rowData, rowIndex){
							var optHrefStr='<a href="javascript:previewImage(\'#paraQueryGrid\','+rowIndex+',\'gramID\',\'频高图查看\',\'pgt\');" class="a3">'+select_type_ionogram+'</a>';
	                       if(rowData.station.address=='1'){//此处address=1 表示 有对应的扫描图
							  optHrefStr+='&nbsp;<a href="javascript:previewScanpic(\''+rowData.stationID+'\',\''+rowData.createDate+'\');" class="a3">'+select_type_Report_scan+'</a>';
							}
							if(rowData.station.homepage=='1'){//此处homepage=1 表示 有对应的电离参数
								optHrefStr+='&nbsp;<a href="javascript:showParaData(\''+rowData.stationID+'\',\''+rowData.createDate+'\');" class="a3">'+select_type_parameter+'</a>'   ;
							}
							return optHrefStr;
	                         },
						width : 200
			 }
		}else{//报表扫描图
		
		      container[1] = {
						//header : '报表扫描图名称',
						header : select_type_Report_scan,
						name : 'scanPicTitle',
						width : 300
					}
			container[2]={
		           header : option_button,
						name : 'operateTYpe',
						renderer: function(colValue, rowData, rowIndex){
							var optHrefStr='<a href="javascript:previewImage(\'#paraQueryGrid\','+rowIndex+',\'scanPicID\',\'扫描图查看\',\'sac\');" class="a3">'+select_type_Report_scan+'</a>';
	                         if(rowData.station.address=='1'){//此处homepage=1 表示 有对应的频高图
								optHrefStr+='&nbsp;<a href="javascript:previewPgt(\''+rowData.stationID+'\',\''+rowData.createDate+'\');" class="a3">'+select_type_ionogram+'</a>';
							}
							 if(rowData.station.homepage=='1'){//电离参数
							optHrefStr+='&nbsp;<a href="javascript:showParaData(\''+rowData.stationID+'\',\''+rowData.createDate+'\');" class="a3">'+select_type_parameter+'</a>'   ;
	                        
							 }
							 return optHrefStr;
	                         },
						width : 200
			 }
		}
		
		return container;

	}

});

/**
 * 在电离参数或扫描图列表中，关联查看频高图
 * */
/*function previewPgt(stationId,createDate){
	if(stationId && createDate){
		var data = {
		url :  basepath+'qt/showPGT.do',
		params : {
			gramTitle:createDate,
			stationID:stationId
		},
		callback : function(json) {
			if (json.success) {						
				if(json.data.gramPath){
					$( "#imagePreview").html('<img src=".'+ json.data.gramPath +'" border=0  / 

>');					
					$( "#imagePreview").omDialog({title:'频高图查看'});
					$( "#imagePreview").omDialog('open');
				}
			} else {
				at({
						cont : '没有找到对应的电离频高图！',
						type : 'alert'
					});
			}
		}
	}
	ajaxpost(data);
	}
}*/
/**
 *  在电离参数或扫描图列表中，关联查看频高图
 * */
function previewPgt(stationId,createDate){
	if(stationId && createDate){
			var tableCols_,datasourceUrl_;			
					tableCols_=getScanpicColmModel();					
					datasourceUrl_=basepath+'qt/queryPGTByDate.do?stationID='+stationId+'&createDate='+createDate;	
					$("#imagePreview2").html('<table id="paraQueryGrid3"></table>');	
					$('#paraQueryGrid3').omGrid({
						//title : '电离层参数查询',
					 	dataSource :datasourceUrl_,
					 	limit:0, 
					 	height : 325, 
					 	showIndex : false,
					 	colModel :tableCols_
					 	});
									
					$( "#imagePreview2").omDialog({title:'电离频高图查看',height:'auto',width:'auto'});
					$( "#imagePreview2").omDialog('open');
	}
}
/*
    * 频高图列表表头，在电离参数或扫描图关联频高图时使用
    * */
	function getScanpicColmModel() {
		var container_ = new Array();//数据表格的表头数据
		/*container_[0] = {
			header : '所属观测站',
			name : 'station.name',
			width : 80
		}*/
		container_[0] = {
			header : '观测日期',
			name : 'createDate',
			width : 160
		}
		container_[1] = {
						header : '电离层频高图名称',
						name : 'gramTitle',
						width : 300
					}
			  container_[2]={
		           header : '操作',
						name : 'operateTYpe',
						renderer: function(colValue, rowData, rowIndex){
	                         return '<a href="javascript:previewImage(\'#paraQueryGrid3\','+rowIndex+',\'gramID\',\'频高图查看\' ,\'pgt\');" class="a3">查看频高图 </a>';
	                         },
						width : 200
			 }
		return container_;
}


/**
 * 在电离参数或频高图列表中，关联查看报表扫描图
 * */
function previewScanpic(stationId,createDate){
	if(stationId && createDate){
		var data = {
		url : basepath+'qt/showScanpic.do',
		params : {
			scanPicTitle:createDate,
			stationID:stationId
		},
		callback : function(json) {			
			if (json.success) {						
				/*if(json.data.gramPath){
					$( "#imagePreview").html('<img src=".'+ json.data.gramPath +'" border=0  

height=500 width=600 / >');					
					$( "#imagePreview").omDialog({title:'扫描图查看',height: 'auto' ,width 

:'auto'});
					$( "#imagePreview").omDialog('open');
				}*/
				previewImageForScanpic(json.data.scanPicID,'扫描图查看');
			} else {
				at({
						cont : '没有找到对应的报表扫描图！',
						type : 'alert'
					});
			}
		}
	}
	ajaxpost(data);
	}
}

/**
 * 在电离频高图或报表扫描图列表中，关联查看电离层参数
 * */
var firstOpt=true;
function showParaData(stationId,createDate){
	if(stationId && createDate){
			var tableCols_,datasourceUrl_;	
					//tableCols_=getParaColmModel(1,parameter_array_text);				
					tableCols_=getParaColmModel(0,parameter_array_text);	//0:不带出“操作一列”			
datasourceUrl_=basepath+'qt/showParaData.do?stationID='+stationId+'&createDate='+createDate;	
					//$("#imagePreview").html('<table id="paraQueryGrid2"></table>');
					if(firstOpt){					 
					  $("#imagePreview1").html('<table id="paraQueryGrid2"></table>');
					  $('#paraQueryGrid2').omGrid({
						//title : '电离层参数查询',
					 	dataSource :datasourceUrl_,
					 	limit:0, 
  						//height : 335, 
					 	//showIndex : false,
					 	colModel :tableCols_			 	
					 	});
					 	firstOpt=false;
					}else{
					  $('#paraQueryGrid2').omGrid('setData', datasourceUrl_);
					}				
				 	//$("#imagePreview").omDialog('close');			
					$( "#imagePreview1").omDialog({title:'电离层参数查看',modal: true,height:'auto',width:'auto'});
					$( "#imagePreview1").omDialog('open');
	}
}
   /*
    * 电离参数列表表头，在频高图及扫描图关联电离参数时使用
    * */
	function getParaColmModel(queryType,paraCol_) {
		var container_ = new Array();//数据表格的表头数据
		/*container_[0] = {
			header : '所属观测站',
			name : 'station.name',
			width : 80
		}*/
		container_[0] = {
			header : '观测日期',
			name : 'createDate',
			width : 160
		}
		//if(queryType==1){//电离参数
		   if(paraCol_){
		   	 //alert(paraCol_);
				var str = new Array();
				for (i = 0; i < paraCol_.length; i++) {
					container_[i + 1] = {
						header : paraCol_[i],
						name : paraCol_[i],
						width : 60
					}
				}
			//===================add by 2013-02-25======	
			if(queryType!=0){
			container_[paraCol_.length+1]={
		           header : option_button,
						name : 'operateTYpe',
						renderer: function(colValue, rowData, rowIndex){
							var optHrefStr='';
							//alert('address='+rowData.station.address,"homepage="+rowData.station.homepage);
							if(rowData.station.address=='1'){//此处address=1 表示 有对应的扫描图
							  optHrefStr='<a href="javascript:previewScanpic(\''+rowData.stationID+'\',\''+rowData.createDate+'\');" class="a3">'+select_type_Report_scan+'</a>';
							}
							
							if(rowData.station.homepage=='1'){//此处homepage=1 表示 有对应的频高图
								optHrefStr+='&nbsp;<a href="javascript:previewPgt(\''+rowData.stationID+'\',\''+rowData.createDate+'\');" class="a3">'+select_type_ionogram+'</a>';
							}
	                        return  optHrefStr ;
	                         },
						width : 150
			 }
			}				
			 //=========================
		}
		return container_;
}


/*
 * 图片浏览器
 *  电离层参数列表中，查看报表扫描图，不翻页width=600
 */
function previewImageForScanpic(sacId_,title_){		
	var filePath_ = gpath = "/qt/getimage.do?imageid=" + sacId_ + "&tab=sac" ;
			$( "#imagePreview").html('<img src=".'+ filePath_ +'" border=0 height=500 / >' +'<p><input id="but2" type="button" value="放大" />&nbsp;&nbsp;<input id="but3" type="button" value="缩小" /></p>');			
			$( "#imagePreview").omDialog({title:title_, height: 'auto' , width :'auto'});
			if( !$("#imagePreview").omDialog('isOpen')){
				$( "#imagePreview").omDialog('open');
			}						
			$("#but2").bind("click", function(){
				var w = $( "#imagePreview img").attr("width");
				var h = $( "#imagePreview img").attr("height");
				if(w){
					$( "#imagePreview img").attr("width" , w*1.2);
				}
				if(h){
					$( "#imagePreview img").attr("height" , h*1.2);
				}
			});
			$("#but3").bind("click", function(){
				var w = $( "#imagePreview img").attr("width");
				var h = $( "#imagePreview img").attr("height");
				if(w){
					$( "#imagePreview img").attr("width" , w/1.2);
				}
				if(h){
					$( "#imagePreview img").attr("height" , h/1.2);
				}
			});

	
}




 $(document).ready(function() {
 	
 	   //选择月份
	$('#monthForChart').omCombo({ // 初始化Combo
		dataSource :month_omCombo_datasource,
		width : 200,
		//value:1,
		 multi : true            
	});
	
	var paraValue;
	var selectOk=true;
 	/*
 	 * 选择电离参数
 	 * 一次只选择一个单因子或者一组多因子，不允许多选
 	 * */
       var sss=$('#selectorPara').omItemSelector({
                availableTitle : '可选择参数',
                selectedTitle : '已选择参数',
                dataSource : parameter_omCombo_datasource2,
                value:[],
                onBeforeItemSelect:function(itemDatas, event){
	                if(itemDatas.length<=1 && selectOk){
	                	   paraValue=itemDatas[0].value;
	                	   $('#parameter').attr({value:itemDatas[0].value});
	                	    selectOk=false;
	                	}else{	           			
	           			  at({cont:'只能选择一个单因子或者一组多因子！' , type : 'error'});	                	
	                	  return false;
	                	}
                },
                 onItemDeselect:function(itemDatas, event){ 
                 	  selectOk=true;
                	 $('#parameter').attr({value:''});
                 },
                width:350,
        		height:250

            });
     //全选月份    
    $("#select_all").click(function() {
      if($("#select_all").attr("checked")){
      	$("input[type='checkbox']").attr("checked", true);//$("input[type='checkbox']").attr("checked", $(this).attr("checked"));
      }else{
        $("input[type='checkbox']").attr("checked", false);
      }
	   
    });
     
      //生成电离参数曲线图      
       $("#pressParaChart").click(function(){
               var stationId=$('#stationId').val();
			   var year=$('#year').val();
			   var chk_value =[];    
				  $('input[name="months"]:checked').each(function(){    
				     chk_value.push($(this).val());    
				  });    

			  // var month=$('#monthForChart').val();
			   var month=chk_value.toString();
			   month=chk_value[0];//暂支持一个月的数据，多选时选择第一个选中的月份
			   var parameter=$('#parameter').val();	
			   	
                if(stationId && year && month && parameter){
                     var data = {
								url : 'qt/loadParaChartData.do',
								params : {
								stationID : stationId,
								year :year,
								month:month,
								paraType:parameter
							},
							callback : function(json) {											
								if (json.success) {									
									   var chart;									  
										chart = new Highcharts.Chart({
											chart: {
												renderTo: 'paraDataChart',
												type: 'line',
												marginRight: 120,
												marginBottom: 25
											},
											title: {
												text: json.chartTitle,
												x: -20 //center
											},
											subtitle: {
												text: year+'.'+month+'&nbsp;&nbsp;'+location+'&nbsp;&nbsp;'+jingweidu,
												x: -20
											},
											xAxis: {
												categories: parameter_chart_xAxis_hour
											},
											yAxis: {
												title: {
													text: json.yAxis+getUnit(json.paraName)
												},
												plotLines: [{
													value: 0,
													width: 1,
													color: '#808080'
												}]
											},
											tooltip: {
												formatter: function() {
														return '<b>'+ this.series.name +'</b><br/>'+
														this.x +': '+ this.y ;
												}
											},
											legend: {
												layout: 'vertical',
												align: 'right',
												verticalAlign: 'top',
												x: -10,
												y: 100,
												borderWidth: 0
											},
											series: json.rows
											/*series: [{
												name: 'foF2',
												data: ["7.0", "6.9", "9.5", "14.5"]
											}]*/
										});
								} else {
									at({cont:'没有数据！' , type : 'error'});
								}
							}
						  }
						ajaxpost(data);
                	
                	}else{ //有查询条件，显示查询数据  
                	at({cont:'请选择条件！' , type : 'error'});
                    
                }

            });
            
            
   

         
            
            
            
        });

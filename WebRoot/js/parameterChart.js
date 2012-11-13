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
                availableTitle : select_parameter,
                selectedTitle : selected_parameter,
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
			   var parameter=$('#parameter').val();	
			   var chk_value =[];    
				  $('input[name="months"]:checked').each(function(){    
				     chk_value.push($(this).val());    
				  });    
			   var month=chk_value.toString();		
			   //month=chk_value[0];//暂支持一个月的数据，多选时选择第一个选中的月份	
			   if(chk_value.length>1 && (parameter=='foF2,foF1,foEs,foE' || parameter=='M3000F2,P（foEs）,hiEs') ){
			    at({cont:'查看多因子曲线图时，只能选择一个月份！' , type : 'error'});
			    return;
			   }
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
									var chart;	//曲线图
									var chartS;//散点图
									var chartHeight = 250;//单个曲线图的高度
									var rigthDivHeight =700;//显示曲线图div的默认高度
									/**
									 * 清除页面曲线图位置节点中的内容
									 * */
								    $("#topChart").html('');
								    $("#topChart").height(0);
								    for(var ind=0;ind<12;ind++){
								      $("#paraDataChart"+ind).html('');
								      $("#paraDataChart"+ind).height(0);								      
								    }									
									$("#downChart").html('');
									$("#downChart").height(0);
									$("#rightChartContent").height(rigthDivHeight);
									/**
									 * 单因子曲线图生成（支持多月份选择）
									 * */
									//=======================单因子曲线图（单因子曲线显示）=============================================
									if(json.paraFlag==1){		
										//alert(chartHeight*json.SingleFactor.length);
										if(json.SingleFactor.length>1){
											//if(chartHeight*json.SingleFactor.length>rigthDivHeight){
											 $("#rightChartContent").height(chartHeight*json.SingleFactor.length+350);										
										}else{
											 $("#rightChartContent").height(rigthDivHeight);
							
										}
									  
									 	$.each( json.SingleFactor, function (i, object) {
									            // alert("text:" + object.monthValue );
									           /* $.each( object.text, function (i, o1) {
									             alert("text:" + o1.name + ", value:"  + o1.data);
									             
												});*/								
									 		
									 		
									 		
									 		
									 		$("#paraDataChart"+i).height(250);
									 		$("#paraDataChart"+i).width(700);
									 		$("#paraDataChart"+i).css({"margin-top":"10px"})
									 		
									 		 //根据你所需要的图形类型选择不同的swf，如3d柱状图为FCF_Column3D.swf，2d饼状图为FCF_Pie2D.swf
									 		 chartS = new FusionCharts("swf/SelectScatter.swf", "SelectScatter", "700", "400", "0", "1");
		   									//chartS.setDataURL("Data.xml");
									       //chartS = new FusionCharts("swf/FC_2_3_MSScatter.swf ", "FC_2_3_MSScatter", "600", "350");
									      // chartS.setDataXML("");
									       chartS.setDataURL("data/data.xml");									    
									       chartS.render("scatterChart"+i);
									       
								 		
									 		chart = new Highcharts.Chart({
											chart: {
												renderTo: 'paraDataChart'+i,
												type: 'line',
												marginRight: 90,
												marginBottom: 25
											},
											title: {
												text: object.chartTitle,//json.chartTitle
												x: -20 //center
											},
											subtitle: {
												text: '',//year+'.'+month+'  '+locations+'     '+jingweidu
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
											series: object.mutiMonth
											/*series: [{
												name: 'foF2',
												data: ["7.0", "6.9", "9.5", "14.5"]
											}]*/
										});
									 		
											});									 																	   								  										
									 }else{//===============================多因子======================================
											$("#topChart").height(250); //.css(height,250);
											$("#topChart").width(700);
									 	 //====================组合1，2共用曲线，最上方的曲线=========================================
										if(json.topChart){																							
											var topChart;												
										    topChart = new Highcharts.Chart({
											chart: {
												renderTo: 'topChart',
												type: 'line',
												marginRight: 90,
												marginBottom: 25
											},
											title: {
												text: json.top_chartTitle,
												x: -20 //center
											},
											subtitle: {
												text: year+'.'+month+'  '+locations+'     '+jingweidu,
												x: -20
											},
											xAxis: {
												categories: parameter_chart_xAxis_hour
											},
											yAxis: {
												title: {
													text: json.top_yAxis+getUnit(json.top_paraName)//json.yAxis+getUnit(json.paraName)
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
											series: json.topChart
										});
										  
										}								
											//=======================组合1，下方的曲线=============================================
									 if(json.MultiFactor1){									 	
									        $("#rightChartContent").height(870);
									        
									 		$("#paraDataChart0").height(250);
									 		$("#paraDataChart0").css({"margin-top":"10px"})
									 		$("#paraDataChart0").width(700);
									 		chart = new Highcharts.Chart({
											chart: {
												renderTo: 'paraDataChart0',
												type: 'line',
												marginRight: 90,
												marginBottom: 25
											},
											title: {
												text: '',//json.chartTitle
												x: -20 //center
											},
											subtitle: {
												text: '',//year+'.'+month+'  '+locations+'     '+jingweidu
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
											series: json.MultiFactor1
											/*series: [{
												name: 'foF2',
												data: ["7.0", "6.9", "9.5", "14.5"]
											}]*/
										});							   								  									
									 }
									 						
										 if(json.MultiFactor2){
									        $("#rightChartContent").height(970);									 	
									 		$("#paraDataChart0").height(200);
									 		$("#paraDataChart0").width(700);
									 		$("#paraDataChart0").css({"margin-top":"10px"})
									 		chart = new Highcharts.Chart({
											chart: {
												renderTo: 'paraDataChart0',
												type: 'line',
												marginRight: 90,
												marginBottom: 25
											},
											title: {
												text: '',//json.chartTitle
												x: -20 //center
											},
											subtitle: {
												text: '',//year+'.'+month+'  '+locations+'     '+jingweidu
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
											series: json.MultiFactor2
											/*series: [{
												name: 'foF2',
												data: ["7.0", "6.9", "9.5", "14.5"]
											}]*/
										});							   								  									
									 }
										if(json.downChart){									
									   	 $("#downChart").height(200);	
									   	  $("#downChart").width(700);
									   	  $("#downChart").css({"margin-top":"10px"})
									   		chart = new Highcharts.Chart({
								            chart: {
								                renderTo: 'downChart',
								                type: 'column',
								               // margin: [ 30, 100, 50, 50]
								                marginRight: 90,
												marginBottom: 25
								            },
								            title: {
								                text: ''
								            },
								            xAxis: {
								                categories: parameter_chart_xAxis_hour
								                /*labels: {
								                    rotation: -45,
								                    align: 'right',
								                    style: {
								                        fontSize: '13px',
								                        fontFamily: 'Verdana, sans-serif'
								                    }
								                }*/
								            },
								            yAxis: {
								                min: 0,
								                title: {
								                    text: 'Population (millions)'
								                }
								            },
								            legend: {
								                enabled: false
								            },
								            tooltip: {
								                formatter: function() {
								                    return '<b>'+ this.x +': '+ this.y;
								                }
								            },
								            series:json.downChart
								          /*     series: [{
								                name: "h'Es",
								                data: [1, 0, 0, 0, 0, 0, 0, 0, 18,1, 16, 15, 14, 14, 13, 12, 12, 11,11, 11,2,2,3,4],
								                    //data:json.downChart.data,
								                dataLabels: {
								                    enabled: true,
								                    rotation: -90,
								                    color: '#FFFFFF',
								                    align: 'right',
								                    x: -3,
								                    y: 10,
								                    formatter: function() {
								                        return this.y;
								                    },
								                    style: {
								                        fontSize: '13px',
								                        fontFamily: 'Verdana, sans-serif'
								                    }
								                }
								            }]*/
								        });
 
									   }
									}
									
										
								
									 
										  //=========================================================================
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

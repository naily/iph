 $(document).ready(function() {
 	
 	   //选择月份
	$('#monthForChart').omCombo({ // 初始化Combo
		dataSource :month_omCombo_datasource,
		width : 200,
		//value:1,
		 multi : true            
	});
	
	var paraValue;
 	//选择电离参数
       var sss=$('#selectorPara').omItemSelector({
                availableTitle : '可选择参数',
                selectedTitle : '已选择参数',
                dataSource : parameter_omCombo_datasource,
                value:[],
                onItemSelect:function(itemDatas, event){
                	paraValue=itemDatas[0].value;
                	//alert(itemDatas.length);
                	//alert(itemDatas[0].value);
                	$('#parameter').attr({value:paraValue});
                	
                },
                width:250,
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
			   var parameter=$('#parameter').val();	
			   	
                if(stationId && year && month && parameter){
                     var data = {
								url : 'qt/loadParaData.do',
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
												marginRight: 130,
												marginBottom: 25
											},
											title: {
												text: '武汉观测站电离层参数曲线图',
												x: -20 //center
											},
											subtitle: {
												text: 'Source: WorldClimate.com',
												x: -20
											},
											xAxis: {
												categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
													'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
											},
											yAxis: {
												title: {
													text: ''//Temperature (°C)
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
														this.x +': '+ this.y +'°C';
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
											series: [{
												name: 'foF2',
												data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
											}]
										});
								} else {
									at({cont:'服务器错误！' , type : 'error'});
								}
							}
						  }
						ajaxpost(data);
                	
                	}else{ //有查询条件，显示查询数据  
                	at({cont:'请选择条件！' , type : 'error'});
                    
                }

            });
            
            
   

         
            
            
            
        });

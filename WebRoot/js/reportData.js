$(document).ready(function() {
	// 观测站下拉列表
	/*$('input[name=stationId]').omCombo({ // 初始化Combo
		dataSource : [ { text : '武汉观测站', value : '1' }, {
						text : '上海', value : '2' } ]
		width : 100,
		value:'武汉',
		onValueChange : function() {
			$('input[name=stationId]').focus();

		}
	});*/
	 //观测站下拉框
    $('input[name=stationId]').omCombo({
        dataSource:'ht/stationlistall.do' ,
        valueField : 'id' ,
        optionField :'name',
        onValueChange : function(target, newValue, oldValue, event) {
			//$('input[name=year]').focus();
        	alert(newValue);
        	var getSation = {
                        url : 'ht/getstation.do',
                        params : {id: newValue}  ,
                        callback : function(json){
                            if(json.success){  
                            	var stationStr=json.data.location;
                            	stationStr+="&nbsp;&nbsp;&nbsp;&nbsp;";
                            	stationStr+=json.data.name+"("+json.data.longitude+" &nbsp;"+json.data.latitude+")";
                               
                                $('#reportHeadStation').html(stationStr);
                            }else{
                                //at({cont: json.info , type : 'error'});
                            }
                        }
                    }
                    ajaxpost(getSation);
        }
		
    }) ;

	// 年份
	$('input[name=year]').omCombo({ // 初始化Combo
		dataSource :year_omCombo_datasource,
		width : 60,
		value:2012,
		onValueChange : function() {
			$('input[name=year]').focus();
		}
	});

	// 月份
	$('input[name=month]').omCombo({ // 初始化Combo
		dataSource :month_omCombo_datasource,
		width : 40,
		value:1,
		onValueChange : function() {
			$('input[name=month]').focus();
		}
	});

	// 国家下拉列表
	$('input[name=parameter]').omCombo({ // 初始化Combo
		dataSource : [ { text : 'foF2',  value : 'foF2'}, 
			           { text : 'h1F2',  value : 'h1F2'}, 
			           { text : 'foF1',  value : 'foF1'}, 
			           { text : 'hlF1',  value : 'hlF1'}, 
			           { text : 'hlF',   value : 'hlF'}, 
			           { text : 'hpF',   value : 'hpF'}, 
			           { text : 'foE',   value : 'foE'}, 
			           { text : 'hlE',   value : 'hlE'}, 
			           { text : 'foes',  value : 'foes'}, 
			           { text : 'hlEs',  value : 'hlEs'}, 
			           { text : 'fbEs',  value : 'fbEs'}, 
			           { text : 'Fmin',  value : 'Fmin'}, 
			           { text : 'M3000F2',  value : 'M3000F2'}, 
			           { text : 'M1500F2',  value : 'M1500F2'}, 
			           { text : 'M3000F1',  value : 'M3000F1'}, 
			           { text : 'M3000F',   value : 'M3000F'}
			         
			           
						 ],
		width : 80,
		value:'foF2',
		onValueChange : function() {
			 $('input[name=parameter]').focus();
			
		}
	});
	
	
	$('#reportGrid').omGrid({
                title : '<span id="reportPara">&nbsp;</span><span  id="reportHeadStation">&nbsp;</span>',
                dataSource : 'qt/loadReport.do',
                limit:0,
                height:655,
                showIndex : false,
                colModel : [ {header : '<img src="images/table_head.jpg" border="0"/>', name : 'days',width : 39}, 
                             {header : '00', name : 'h00',  align : 'center',width : 20}, 
                             {header : '01', name : 'h01',  align : 'center',width : 20}, 
                             {header : '02', name : 'h02',  align : 'center',width : 20}, 
                             {header : '03', name : 'h03',  align : 'center',width : 20},
                             {header : '04', name : 'h04',  align : 'center',width : 20}, 
                             {header : '05', name : 'h05',  align : 'center',width : 20},
                             {header : '06', name : 'h06',  align : 'center',width : 20}, 
                             {header : '07', name : 'h07',  align : 'center',width : 20},
                             {header : '08', name : 'h08',  align : 'center',width : 20}, 
                             {header : '09', name : 'h09',  align : 'center',width : 20},
                             {header : '10', name : 'h10',  align : 'center',width : 20}, 
                             {header : '11', name : 'h11',  align : 'center',width : 20},
                             {header : '12', name : 'h12',  align : 'center',width : 20}, 
                             {header : '13', name : 'h13',  align : 'center',width : 20},
                             {header : '14', name : 'h14',  align : 'center',width : 20}, 
                             {header : '15', name : 'h15',  align : 'center',width : 20},
                             {header : '16', name : 'h16',  align : 'center',width : 20}, 
                             {header : '17', name : 'h17',  align : 'center',width : 20},
                             {header : '18', name : 'h18',  align : 'center',width : 20}, 
                             {header : '19', name : 'h19',  align : 'center',width : 20},
                             {header : '20', name : 'h20',  align : 'center',width : 20}, 
                             {header : '21', name : 'h21',  align : 'center',width : 20},
                             {header : '22', name : 'h22',  align : 'center',width : 20}, 
                             {header : '23', name : 'h23',  align : 'center',width : 20}
                             
                             ]
            });

         $("#loadReportData").click(function(){
               var stationId=$('#stationId').val();
			   var year=$('#year').val();
			   var month=$('#month').val();
			   var parameter=$('#parameter').val();
			  
                if(stationId && year && month && parameter){
                      $('#reportGrid').omGrid("setData", 'qt/loadReport.do?stationId='+stationId+'&year='+year+'&month='+month+'&paraType='+parameter);
                }else{ //有查询条件，显示查询数据                 
                     $('#reportGrid').omGrid("setData", 'qt/loadReport.do');
                }

            });

});




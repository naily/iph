var locations,jingweidu;
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
        width : 120,
        onValueChange : function(target, newValue, oldValue, event) {
			//$('input[name=year]').focus();
          if(newValue){
           var getSation = {
                        url : 'ht/getstation.do',
                        params : {id: newValue}  ,
                        callback : function(json){                      
                            if(json.success){  
                            	locations=json.data.timeZone;//timeZone//location
                            	jingweidu=json.data.name+"("+json.data.longitude+"&nbsp;"+json.data.latitude+")";
                            	$('#location').html(locations);
                                $('#jingweidu').html(jingweidu);
                            }else{
                                //at({cont: json.info , type : 'error'});
                            }
                        }
                    }
                    ajaxpost(getSation);
           	
           }
        	
        }
		
    }) ;

	// 年份
	$('input[name=year]').omCombo({ // 初始化Combo
		dataSource :'qt/yearList.do',//year_omCombo_datasource,
		width : 100,
		//value:2012,
		onValueChange : function() {
			$('input[name=year]').focus();
		}
	});

	// 月份
	$('input[name=month]').omCombo({ // 初始化Combo
		dataSource :month_omCombo_datasource,
		width : 80,
		//value:1,
		onValueChange : function(target, newValue, oldValue, event) {
			$('input[name=month]').focus();
			$('#month_year').html(getMonthEn(newValue)+"&nbsp;"+$('#year').val());
		}
	});

	// 电离参数下拉列表
	$('input[name=parameter]').omCombo({ // 初始化Combo
		dataSource :parameter_omCombo_datasource,
		width : 120,
		//value:'foF2',
		onValueChange : function(target, newValue, oldValue, event) {
			 $('input[name=parameter]').focus();
			$('#para_unit').html(newValue+getUnit(newValue));
		}
	});
	
	
	$('#reportGrid').omGrid({
                title : '<table border="0" width="780"><tr><td  width="120" align="center" id="para_unit">&nbsp;</td><td width="120" align="center" id="month_year">&nbsp;</td><td width="270" align="center" id="location">&nbsp;</td><td  width="280" align="center" id="jingweidu">&nbsp;</td></tr></table',
                dataSource : 'qt/loadReport.do',
                limit:0,
                height:625,
                showIndex : false,
                colModel : [ {header : '<img src="images/table_head.jpg" border="0"/>',align : 'center', name : 'days',width : 39}, 
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
		/**
		 * 电离月报生成（单月月报生成、所有参数及所有月份报表生成）
		 * 选择所有时，遍历参数逐个显示
		 * */
         $("#loadReportData").click(function(){
               var stationId=$('#stationId').val();
			   var year=$('#year').val();
			   var month=$('#month').val();
			   var parameter=$('#parameter').val();	
			   var showPageTbale=false;
			   var totalPages=1;
			   var pageNumber=1;
			   if(isMonthSelectAll()){
			   	  totalPages=12;
			   	  month=month_array[0];
			   	  setTitleAndParaVaule(0,-1);// 
			   	  showPageTbale=true;
			   }
 			   if(isParaSelectAll()){
 			   	  totalPages*=16;
 			      parameter=parameter_array[0];
 			       setTitleAndParaVaule(-1,0);// 
 			       showPageTbale=true;
 			   }
 			  
                if(stationId && year && month && parameter){
                	 if(showPageTbale){
		 			   	$("#pagesNum").html(totalPages);
		 			   	$("#pageNumber").html(pageNumber);
		 			   	$("#showPages").show();
		 			   }else{
		 			     $("#pagesNum").html('');
		 			     $("#pageNumber").html('');
		 			   	 $("#showPages").hide();
		 			   }
                      $('#reportGrid').omGrid("setData", 'qt/loadReport.do?stationID='+stationId+'&year='+year+'&month='+month+'&paraType='+parameter);
                }else{ //有查询条件，显示查询数据  
                	at({cont:'请选择条件！' , type : 'error'});
                     $('#reportGrid').omGrid("setData", 'qt/loadReport.do');
                }

            });
            
            $("#downloadReportData").click(function(){
               var stationId=$('#stationId').val();
			   var year=$('#year').val();
			   var month=$('#month').val();
			   var parameter=$('#parameter').val();
			   if($("#allMonth").attr("checked")){
			   	  month='all';
			   }
 			   if($("#allPara").attr("checked")){
 			      parameter='all';
 			   }
                if(stationId && year && month && parameter){
                      window.open(basepath +'/qt/downloadReportData.do?stationID='+stationId+'&year='+year+'&month='+month+'&paraType='+parameter);
                }else{ //有查询条件，显示查询数据  
                	at({cont:'请选择条件！' , type : 'error'});
                   
                }

            });

});
/**
 * 是否全选电离参数
 */

 function isParaSelectAll(){
  if($("#allPara").attr("checked")){
  	 return true;
  }else{
  	 return false;
  }
 }
 /**
 * 是否全选月份
 */

 function isMonthSelectAll(){
  if($("#allMonth").attr("checked")){
  	 return true;
  }else{
  	 return false;
  }
 }
/**
 * setTitleAndParaVaule（月份数组下标，电离参数数组下标）
 * 1、设置月报表头（电离参数和月份）
 * 2、设置电离参数及月份数组的下标（用于全选参数或月份时的分页）
 *   说明：当页面选择了某一参数或某一月份时，则不遍历相应的数值，参数传递时传入-1
 * */
  function setTitleAndParaVaule(monthIdx,paraIdx){
  	//alert('monthIdx='+monthIdx+',paraIdx='+paraIdx);
  	if(monthIdx!='-1' && monthIdx!=-1){
	  	$('#month_year').html(getMonthEn(month_array[monthIdx])+"&nbsp;"+$('#year').val());//月份显示 	
	  	$("#monthIndex").val(monthIdx); //月份下标显示
  	}   
  	if(paraIdx!='-1' && paraIdx!=-1){
	  	$('#para_unit').html(parameter_array[paraIdx]+getUnit(parameter_array[paraIdx]));//参数显示
	  	$("#parameterIndex").val(paraIdx);//电离参数下标设置
  	}
  }
  /**
   * 下个月报显示
   *   需要判断是否全选了参数或者月份
   * */
  function Next(){ 	
  	var parameterIndex=-1,monthIndex=-1;
  	var pageNumber = $("#pageNumber").html();
  	var totalPages =$("#pagesNum").html();
  	pageNumber = parseInt(pageNumber);
  	pageNumber=pageNumber+1
  	if(pageNumber>=parseInt(totalPages)){
  	  pageNumber=totalPages;
  	}
  	$("#pageNumber").html(pageNumber);
  	if(isParaSelectAll() && isMonthSelectAll()){//全选参数且月份等于12时，遍历下个参数
	  	    parameterIndex=$("#parameterIndex").val();
	  	    monthIndex=$("#monthIndex").val();
	  	    monthIndex=parseInt(monthIndex)+1;
	  	    if(monthIndex>11){
	  	      monthIndex=0;
	  	      parameterIndex=parseInt(parameterIndex)+1;
	  	    }	  	   
  	}
     if(isMonthSelectAll() && !isParaSelectAll()){//全选月份,参数固定
  	        monthIndex=$("#monthIndex").val();
	  	    monthIndex=parseInt(monthIndex)+1;
	  	    if(monthIndex>11){
	  	      monthIndex=0;	  	     
	  	    }	  	  
	     	
  	}
  	 if(isMonthSelectAll()==false && isParaSelectAll()==true){//全选参数,月份固定
  	        parameterIndex=$("#parameterIndex").val();
	  	    parameterIndex=parseInt(parameterIndex)+1;	  	  
	     	
  	}
  	
  	setTitleAndParaVaule(monthIndex,parameterIndex);
  	showParameterMonth();
  	
  }
  /**
   * 上个月报显示
   * */
  function Previous(){
   	var parameterIndex=-1,monthIndex=-1;
   	var pageNumber = $("#pageNumber").html();
  	pageNumber = parseInt(pageNumber);
  	pageNumber=pageNumber-1
  	if(pageNumber<=0){
  	  pageNumber=1
  	}
  	$("#pageNumber").html(pageNumber);
  	if(isParaSelectAll() && isMonthSelectAll()){//全选参数且月份等于12时，遍历下个参数
	  	    parameterIndex=$("#parameterIndex").val();
	  	    monthIndex=$("#monthIndex").val();
	  	    monthIndex=parseInt(monthIndex)-1;
	  	    if(monthIndex<0){
	  	      monthIndex=11;
	  	      parameterIndex=parseInt(parameterIndex)-1;
	  	    }	  	   
  	}
     if(isMonthSelectAll() && !isParaSelectAll()){//全选月份,参数固定
  	        monthIndex=$("#monthIndex").val();
	  	    monthIndex=parseInt(monthIndex)-1;
	  	    if(monthIndex<0){
	  	      monthIndex=11;	  	     
	  	    }	  	  
	     	
  	}
  	 if(isMonthSelectAll()==false && isParaSelectAll()==true){//全选参数,月份固定
  	        parameterIndex=$("#parameterIndex").val();
	  	    parameterIndex=parseInt(parameterIndex)-1;	  	  
	     	
  	}
  	
  	setTitleAndParaVaule(monthIndex,parameterIndex);
  	showParameterMonth();
		
  }
  /**
   * 月报生成
   * */
  function showParameterMonth(){
  	 var stationId=$('#stationId').val();
	 var year=$('#year').val();
	 var month,parameter;
	 
	 if(isMonthSelectAll()){
	 	month=month_array[$("#monthIndex").val()];
	 }else{
		month=$('#month').val();
	 }
	 if(isParaSelectAll()){
	 	parameter=parameter_array[$("#parameterIndex").val()];	
	 }else{
		parameter=$('#parameter').val();
	 }				  
     if(stationId && year && month && parameter){       
              $('#reportGrid').omGrid("setData", 'qt/loadReport.do?stationID='+stationId+'&year='+year+'&month='+month+'&paraType='+parameter);
     }
  
  }



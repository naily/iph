$(document).ready(function() {
	// 开始时间
	$('#startDate').omCalendar();
	// 截止时间
	$('#endDate').omCalendar();
	  //观测站下拉框
     $('#comboStation').omCombo({
        dataSource:'qt/listAllStation.do' ,
        valueField : 'value' ,
        optionField :'text', 
        value : $('#stationIDV').val()

    }) ;



    
});
function checkValue(p){
  var stationId = $('#comboStation').val();
  var startDate = $('#startDate').val();
  var endDate = $('#endDate').val();
   if(!stationId){
    at({cont : '请选择观测站！',type : 'error'});
    return false;
   }
   if((startDate!='' &&  endDate!='') || (startDate=='' &&  endDate=='')){
    
    }else{
  		at({cont : '选择日期条件时，必须同时选择开始和结束日期！',type : 'error'});
      return false;
  }
  //return true;
  /*	var data = {
		url : basepath+'qt/listPGT.do',
		params : {
			startDate:startDate,
			endDate:endDate,
			stationID:stationId
		},
		callback : function(json) {		
			
           window.location='pgtlist.jsp';
		}
	}
	ajaxpost(data);*/
  if(p=='pgt'){
    window.location.href=basepath+'qt/listPGT.do?startDate='+startDate+'&endDate='+endDate+'&ids='+stationId;
  }else{
   window.location.href=basepath+'qt/listScanPic.do?startDate='+startDate+'&endDate='+endDate+'&ids='+stationId;
  }
	
}

function goPages(e,type){
  var stationId = $('#comboStation').val();
  var startDate = $('#startDate').val();
  var endDate = $('#endDate').val();
  var queryYear= $('#queryYear').val();
  if(type=='pgt'){
    window.location.href=basepath+'qt/listPGT.do?queryYear='+queryYear+'&startDate='+startDate+'&endDate='+endDate+'&ids='+stationId+'&pageNumber='+e.value;
  }else{
   window.location.href=basepath+'qt/listScanPic.do?queryYear='+queryYear+'&startDate='+startDate+'&endDate='+endDate+'&ids='+stationId+'&pageNumber='+e.value;
  }
}

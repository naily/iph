 $(document).ready(function() {	
 	//选择观测站 
     $('#selectorStation').omItemSelector({
                availableTitle : '选择观测站',
                selectedTitle : '已选观测站',
                dataSource : 'qt/listAllStation.do',
              //  value:[],
                onItemSelect:function(itemDatas, event){
                	var stationValue='';
                	if(itemDatas.length>=1){
                		stationValue=itemDatas[0].value
                	}
                	for(var i=1;i<itemDatas.length;i++){
                	  stationValue+=","+itemDatas[i].value;
                	}
                	$('#stationIDs').attr({value:itemDatas});
                	
                },
                width:270,
        		height:345

            });
        
 	//选择电离参数
       $('#selectorParaS').omItemSelector({
                availableTitle : '选择参数',
                selectedTitle : '已选择参数',
                dataSource : parameter_omCombo_datasource,
               // value:[],
                onItemSelect:function(itemDatas1, event){
                	 var paraValue='';
                	if(itemDatas1.length>=1){
                		paraValue=itemDatas1[0].value
                	}
                	for(var j=1;j<itemDatas1.length;j++){
                	  paraValue+=","+itemDatas1[j].value;
                	}
                	$('#parameter').attr({value:paraValue});				                 	
                },
                width:270,
        		height:345

            });
            
      $('#startDate').omCalendar();
      $('#endDate').omCalendar();

      var colModel_=[{header : '观测站', name : 'stationID',width : 80}, {header : '观测日期', name : 'createDate',  align : 'center',width : 50}, {header : 'foF2', name : 'foF2',  align : 'center',width : 50}];
      
    //  colModel+="]";
      $('#paraQueryGrid').omGrid({
                title : 'eqwewq',
                dataSource : 'qt/doParaDataQuery.do?',
               // limit:0,
                height:325,
                showIndex : false,
                colModel : colModel_
            });

      
      
         //电离参数列表显示      
       $("#paraDataQuery").click(function(){
       
               var stationId=$('#stationIDs').val();
			   var parameter=$('#parameter').val();
			   var startDate=$('#startDate').val();
			   var endDate=$('#endDate').val();
			   var allDate=$('#allDate').val();
			   var pageSize=$('#showNum').val();		  
                if(stationId && parameter &&(startDate || endDate || allDate)){
                      $('#paraQueryGrid').omGrid("setData", 'qt/paraDataQuery.do?ids='+stationId+'&parameterID='+parameter+'&month='+month+'&paraType='+parameter);
                }else{ //有查询条件，显示查询数据  
                	at({cont:'请选择条件！' , type : 'error'});
                     $('#paraQueryGrid').omGrid("setData", 'qt/paraDataQuery.do');
                }

       
       	  // alert($('#parameter').val());
       });
            
        //  paraQueryGrid  
            
        });

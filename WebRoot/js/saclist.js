$(document).ready(function(){
	$('#list0').omGrid({
         width : '99.8%',
         method : 'POST' ,
         limit : pageslimit, //分页显示，每页显示8条
         singleSelect : false, //出现checkbox列，可以选择同时多行记录
         colModel : [    {header:'ID', name:'ID' ,   width:100},
                         {header:'文件标题',name:'Title',  width:200  },
                         {header:'观测站',name:'stationName' ,align : 'center' ,
                         	renderer: function(colValue, rowData, rowIndex){
	                         	return '<a href="javascript:previewStation(\''+rowData.stationID+'\');" class="a3">'+rowData.stationName+' </a>&nbsp;&nbsp;&nbsp;&nbsp;'   ;
	                         }
	                     } ,
                         {header:'日期',name:'createDate',width:100 } ,
                         {header:'文件名',name:'FileName', width:100,width:'autoExpand' } ,
                         {header:'操作',name:'operation',width:60 ,align : 'center' ,
	                         renderer: function(colValue, rowData, rowIndex){
	                         	return '<a href="javascript:previewSac(\''+rowData.gramPath+'\');" class="a3">预览 </a>&nbsp;&nbsp;&nbsp;&nbsp;'   ;
	                         }
                         }
         ],
         dataSource : 'ht/saclist.do' 
     });
    $('#buttonbar').omButtonbar({
            	width : '99.8%',
            	btns : [{label:"删除"  ,
            		     id   :"button1" ,
            	 		 onClick:function(){
					     		var ss = $('#list0').omGrid('getSelections',true);
					     		if(ss.length < 1 ){
								    at({cont:'请选择删除的记录！' , type : 'error'});
								    return;
								}else{
									var arry = new Array( ) ;
									for(var i=0 ; i<ss.length ; i++){
										arry.push(ss[i].ID );
									}
									
									var delt = {
						                        url : 'ht/sacdel.do',
						                        params : {ids: arry.join(";")}  ,
						                        callback : function(json){
						                            if(json.success){
						                                $('#list0').omGrid('reload');
						                                $.omMessageTip.show({
											                type:'success',
											                title:'提醒',
											                timeout : 3000 ,
											                content:'删除成功'
											            });
						                            }else{
						                                at({cont: json.info , type : 'error'});
						                            }
						                        }
						                    }
						                    //提示
						                    $.omMessageBox.confirm({
						                        title:'删除确认',
						                        content:'删除记录 '+ss.length+'条 ,你确定要这样做吗?',
						                        onClose:function(value){
						                            if(value){
						                                ajaxpost(delt);
						                            }
						                        }
						                    });
								}
								
            	 		 	}
            			},
            			{label:"修改",
            		     id:"button2" , 
            	 		 onClick:function(){
					     		var ss = $('#list0').omGrid('getSelections',true);
					     		if(ss.length != 1 ){
								    at({cont:'请选择一条记录修改！' , type : 'error'});
								    return;
								}else{
									var igid = ss[0].ID ;
									var getpgt = {
						                        url : 'ht/sacget.do',
						                        params : {id : igid }  ,
						                        callback : function(json){
						                            if(json.success){
						                                //$('#list0').omGrid('reload');
						                            }else{
						                               	//at({cont: json.info , type : 'error'});
						                            }
					                                var cd = json.createDate ;
					                                $('#actionDateId').val( cd.substring(0,11) );
					                                
					                                $('#pgtfile').html(json.scanPicFileName);
						                            $('#comboStation').omCombo('value', json.stationID);
						                            $('#pgtTitleId').val(json.scanPicTitle) ;
						                            $( "#tab1").omDialog('open');
						                            
						                            $('#updatesavebut').omButton({
												     	onClick : function(){
												     		json.createDate = $.omCalendar.formatDate($('#actionDateId').omCalendar('getDate'), 'yy-mm-dd');
								                            json.stationID = $('#comboStation').omCombo('value');
								                            json.scanPicTitle = $('#pgtTitleId').val();
								                            
												     		var updatepgt = {
													                        url : 'ht/sacupdate.do',
													                        params : json  ,
													                        callback : function(json){
													                            if(json.success){
													                                $("#tab1").omDialog('close');
													                                $('#list0').omGrid('reload');
													                                $.omMessageTip.show({
																		                type:'success',
																		                title:'提醒',
																		                timeout : 3000 ,
																		                content:'修改成功'
																		            });
													                            }else{
													                               	at({cont: json.info , type : 'error'});
													                            }
													                        }
												     		}
												     		ajaxpost(updatepgt);
												     	}
												     })
						                        }
					                }
					                ajaxpost(getpgt);
								}
            	 		 	}
            			}
            	]

            });

    $( "#tab1").omDialog({
            autoOpen: false,
            resizable: false ,
            width:'auto' ,
            title:'修改报表扫描图信息'
     });
    
     $('#actionDateId').omCalendar();
     //观测站下拉框
     $('#comboStation').omCombo({
        dataSource:'ht/stationlistall.do' ,
        valueField : 'id' ,
        optionField :'name'  
    }) ;
    
    
    
    
    
    //图片预览弹出
    $( "#imagePreview").omDialog({
        autoOpen: false,
        height: 'auto' ,
        width :'auto'
    });
    
});
function previewSac(path){
	if(path){
		$( "#imagePreview").html('<img src=".'+ path +'" border=0 height=500 / >');
		$( "#imagePreview").omDialog('open');
	}
}

function previewStation(sid){
	if(sid){
		var ie = '<iframe width="760" height="600" align="center" frameborder="0" scrolling="1" src="ht/viewstation.do?sid=999"></iframe>' ;
		$( "#imagePreview").html(ie.replace("999" , sid));
		$( "#imagePreview").omDialog('open');
	}
}


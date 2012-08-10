$(document).ready(function(){

    $('#list0').omGrid({
         width : '99.8%',
         method : 'POST' ,
         limit : pageslimit, //分页显示，每页显示8条
         singleSelect : false, //出现checkbox列，可以选择同时多行记录
         colModel : [    {header:'ID',      name:'ID' , width:'autoExpand'},
                         {header:'观测站',   name:'stationName'  ,width:80 } ,
                         {header:'日期',    name:'createDate',width:120} ,
                         {header:'foF2',   name:'foF2' ,width:30} ,
                         {header:'hlF2',   name:'hlF2' ,width:30} ,
                         {header:'foF1',   name:'foF1' ,width:30} ,
                         {header:'hlF1',   name:'hlF1' ,width:30} ,
                         {header:'hlF',   name:'hlF' ,width:30} ,
                         {header:'hpF',   name:'hpF' ,width:30} ,
                         {header:'foE',   name:'foE' ,width:30} ,
                         {header:'hlE',   name:'hlE' ,width:30} ,
                         {header:'foEs',   name:'foEs' ,width:30} ,
                         {header:'hlEs',   name:'hlEs' ,width:30} //,
                         //{header:'fbEs',   name:'fbEs' } ,
                         //{header:'Fmin',   name:'Fmin' } 
         ],
         dataSource : 'ht/pamlist.do' 
     });
     
     $('#actionDate').omCalendar({disabled  : true });
     //观测站下拉框
     $('#comboStation').omCombo({
        dataSource:'ht/stationlistall.do' ,
        valueField : 'id' ,
        disabled  : true ,
        optionField :'name' 
    }) ;
    
    
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
						                        url : 'ht/pamdel.do',
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
            			{label:"修改"  ,
            		     id   :"button2" ,
            	 		 onClick:function(){
            	 		 		var ss = $('#list0').omGrid('getSelections',true);
					     		if(ss.length != 1 ){
								    at({cont:'请选择一条记录修改！' , type : 'error'});
								    return;
								}else{
									var igid = ss[0].ID ;
									var getpgt = {
						                        url : 'ht/pamget.do',
						                        params : {id : igid }  ,
						                        callback : function(json){
						                            if(json.success){
						                                //$('#list0').omGrid('reload');
						                            }else{
						                               	//at({cont: json.info , type : 'error'});
						                            }
					                                var cd = json.createDate ;
					                                $('#actionDate').val( cd.substring(0,16) );
						                            $('#comboStation').omCombo('value', json.stationID);
						                            $('#ip1').val(json.foF2) ;
                                                    $('#ip2').val(json.h1F2) ;
                                                    $('#ip3').val(json.foF1) ;
                                                    $('#ip4').val(json.h1F1) ;
                                                    $('#ip5').val(json.hlF) ;
                                                    $('#ip6').val(json.hpF) ;
                                                    $('#ip7').val(json.foE) ;
                                                    $('#ip8').val(json.hlE) ;
                                                    $('#ip9').val(json.foEs) ;
                                                    $('#ip10').val(json.hlEs) ;
                                                    $('#ip11').val(json.M3000F2) ;
                                                    
						                            $( "#tab1").omDialog('open');
						                            
						                            $('#savebut').omButton({
												     	onClick : function(){
												     		json.createDate = $.omCalendar.formatDate($('#actionDate').omCalendar('getDate'), 'yy-mm-dd');
								                            json.stationID = $('#comboStation').omCombo('value');
                                                            
								                            json.foF2 = $('#ip1').val();
                                                            json.h1F2 = $('#ip2').val();
                                                            json.foF1 = $('#ip3').val();
                                                            json.h1F1 = $('#ip4').val();
                                                            json.hlF = $('#ip5').val();
                                                            json.hpF = $('#ip6').val();
                                                            json.foE = $('#ip7').val();
                                                            json.hlE = $('#ip8').val();
                                                            json.foEs = $('#ip9').val();
                                                            json.hlEs = $('#ip10').val();
                                                            json.M3000F2 = $('#ip11').val();
								                            
												     		var updatepgt = {
													                        url : 'ht/pamupdate.do',
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
            title:'修改电离层参数信息'
     });
    
    
    
});



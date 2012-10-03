$(document).ready(function(){
	//图片预览弹出
    $( "#imagePreview").omDialog({
        autoOpen: false,
        height: 'auto' ,
        width :'auto'
    });
	
	$('#list0').omGrid({
        //title : '观测站列表' ,
         //height : 250,
         width : '99.8%',
         method : 'POST' ,
         limit : pageslimit, 
         singleSelect : false, //出现checkbox列，可以选择同时多行记录
         colModel : [    {header:'<b>ID</b>', name:'gramID' , align : 'center',  width:100},
                         {header:'<b>频高图标题</b>',name:'gramTitle',align : 'center',  width:200  },
                         {header:'<b>观测站</b>',name:'stationName' , align : 'center' ,
                         	renderer: function(colValue, rowData, rowIndex){
	                         	return '<a href="javascript:previewStation(\''+rowData.stationID+'\');" class="a3">'+rowData.stationName+' </a>&nbsp;&nbsp;&nbsp;&nbsp;'   ;
	                         	//return '<a href="javascript:previewImage(\'#list0\','+rowIndex+',\'gramPath\',\'观测站查看\');" class="a3">'+rowData.stationName+' </a>&nbsp;&nbsp;&nbsp;&nbsp;'   ;
	                         }
	                     } ,
                         {header:'<b>类型</b>',name:'type',align : 'center', width:50,renderer:function(value,rowData,rowIndex){ 
                         	if('1' == value){
	                         	return '<b> 手动 </b>'; 
                         	}
                         	if('2' == value){
	                         	return '<b>胶版</b>'; 
                         	}
                         	if('3' == value){
	                         	return '<b>数字</b>'; 
                         	}
                         	if('4' == value){
	                         	return '<b>打印</b>'; 
                         	}
                         } } ,
                         {header:'<b>日期</b>',name:'createDate',align : 'center',width:100 } ,
                         {header:'<b>文件名</b>',name:'gramFileName',align : 'center', width:100,width:'autoExpand' } ,
                         {header:'<b>操作</b>',name:'operation',width:60 ,align : 'center' ,
	                         renderer: function(colValue, rowData, rowIndex){
	                         	//传参：当前grid的ID，rowIndex
	                         	return '<a href="javascript:previewImage(\'#list0\','+rowIndex+',\'gramPath\',\'频高图查看\');" class="a3">预览 </a>&nbsp;&nbsp;&nbsp;&nbsp;'   ;
	                         }
                         }
         ],
         dataSource : 'ht/pgtlist.do' 
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
									arry.push(ss[i].gramID );
								}
								
								var delt = {
					                        url : 'ht/pgtdel.do',
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
            			} ,
            			{label:"修改"  ,
            		     id   :"button2" ,
            	 		 onClick:function(){
            	 		 	var ss = $('#list0').omGrid('getSelections',true);
				     		if(ss.length != 1 ){
							    at({cont:'请选择一条记录修改！' , type : 'error'});
							    return;
							}else{
								var igid = ss[0].gramID ;
								var getpgt = {
					                        url : 'ht/pgtget.do',
					                        params : {id : igid }  ,
					                        callback : function(json){
					                            if(json.success){
					                                //$('#list0').omGrid('reload');
					                            }else{
					                               	//at({cont: json.info , type : 'error'});
					                            }
				                                var cd = json.createDate ;
				                                $('#actionDateId').val( cd.substring(0,11) );
				                                
				                                $('#pgtfile').html(json.gramFileName);
					                            $('#comboStation').omCombo('value', json.stationID);
				                                $('#comboPgtType').omCombo('value', json.type);
					                            //$('#actionDateId').omCalendar('setDate', new Date() );
				                                //parseInt(cd.substring(0,4)),parseInt(cd.substring(5,7)),parseInt(cd.substring(8,10))
					                            $('#pgtTitleId').val(json.gramTitle) ;
					                            $( "#tab1").omDialog('open');
					                            
					                            $('#updatesavebut').omButton({
					                            	
											     	onClick : function(){
											     		json.createDate = $.omCalendar.formatDate($('#actionDateId').omCalendar('getDate'), 'yy-mm-dd');
							                            json.stationID = $('#comboStation').omCombo('value');
							                            json.gramTitle = $('#pgtTitleId').val();
							                            
											     		var updatepgt = {
												                        url : 'ht/pgtupdate.do',
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
     })
     //按钮绑定删除
     $('#deletebut').omButton({
     	
     	onClick : function(){
     		
     	}
     });
     
     $( "#tab1").omDialog({
            autoOpen: false,
            resizable: false ,
            width:'auto' ,
            title:'修改频高图信息'
     });
     
     $('#actionDateId').omCalendar();
     //观测站下拉框
     $('#comboStation').omCombo({
        dataSource:'ht/stationlistall.do' ,
        valueField : 'id' ,
        optionField :'name' 
    }) ;
    //频高徒类型下拉框
    $('#comboPgtType').omCombo({
        dataSource:[{text:'手动',value:'1'},{text:'胶版',value:'2'},{text:'数字',value:'3'},{text:'打印',value:'4'}] ,
        valueField : 'value' ,
        optionField :'text',
        value:'1'
        
    }) ;

     $('#updatebut').omButton({
     	
     	onClick : function(){
     		
     	}
     });
     
     
});

function previewPgt(gridId , i, fidldName,title ){
	if(gridId && i > -1){
		var store = $(gridId).omGrid('getData');
		
		if( i < store.rows.length){
			//当前图片
			var gpath = (store.rows)[i].gramPath ;
			$( "#imagePreview").html('<img src=".'+ gpath +'" border=0 height=500 / >' +
				'<p><input id="but1" type="button" value="上一张" /><input id="but2" type="button" value="放大" />'+(i+1)+'/'+store.rows.length+'<input id="but3" type="button" value="缩小" /><input id="but4" type="button" value="下一张" /></p>');
		
			$( "#imagePreview").omDialog({title:'频高图查看'});
			$( "#imagePreview").omDialog('open');
			
			//绑定事件
			$("#but1").one("click", function(){
				previewPgt(gridId , i-1) ;
			});
			$("#but4").one("click", function(){
				previewPgt(gridId , i+1) ;
			});
			
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
		}else{
			alert('没有下一张了' ) ;
		}
	}else{
		alert('没有上一张了' ) ;
	
	}
	
}

function previewStation(sid){
	if(sid){
		var ie = '<iframe width="760" height="600" align="center" frameborder="0" scrolling="1" src="ht/viewstation.do?sid=999"></iframe>' ;
		$( "#imagePreview").html(ie.replace("999" , sid));
		
		$( "#imagePreview").omDialog({title:'观测站信息查看'});
		$( "#imagePreview").omDialog('open');
	}
}

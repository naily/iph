$(document).ready(function(){

    $('#list0').omGrid({
         width : '99.8%',
         method : 'POST' ,
         limit : pageslimit, //分页显示，每页显示8条
         singleSelect : false, //出现checkbox列，可以选择同时多行记录
         colModel : [    {header:'<b>标题</b>', align : 'center',     name:'title',width:'autoExpand'},
                        {header:'<b>新闻类别</b>',align : 'center',   name:'category' ,width:100 ,
                            renderer : function(colValue, rowData, rowIndex){
                            if (colValue == 2) {
                                 return '<span >展示空间</span>';
                             } else {
                                 return '<span >新闻栏目</span>';
                             }
                         }} ,
                         {header:'<b>图片新闻</b>',align : 'center',   name:'picNews' ,width:80 ,
                         renderer : function(colValue, rowData, rowIndex){
                         	if (colValue) {
                                 return '<span style="color:green;"><b>是</b></span>';
                             } else {
                                 return '<span style="color:red;"><b>否</b></span>';
                             }
                         }} ,
                         
                         {header:'<b>日期</b>',  align : 'center',  name:'publishDate', width:140}  ,
                         {header:'<b>操作</b>', align : 'center',   name:'operation', width:100 ,renderer: function(colValue, rowData, rowIndex){
                         	return '<a href="./ht/preview.do?nid='+rowData.newsId+'" target="_blank">预览 </a>' ;
                         }}
         ],
         dataSource : 'ht/newslist.do' 
     });
     
     var editor = $('#contentId').omEditor({
        width : 600 ,
        filebrowserImageUploadUrl : './omEditorImageUpload.do'

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
										arry.push(ss[i].newsId );
									}
									
									var delt = {
						                        url : 'ht/newsdel.do',
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
									var igid = ss[0].newsId ;
									var getpgt = {
						                        url : 'ht/newsget.do',
						                        params : {id : igid }  ,
						                        callback : function(json){
						                            if(json){
						                            	$('#title').val(json.title) ;
						                            	$('#contentId').omEditor('setData' , json.content) ;
						                            	if(json.isPicNews ){
						                            		$('#isPicNews').attr('checked' , true) ;
						                            	}else{
						                            		$('#isPicNews').removeAttr('checked') ;
						                            	}
						                            	
						                            	$( "#tab1").omDialog('open');
						                            }else{
						                            	at({cont: "查询文章失败" , type : 'error'});
						                            }
						                            
						                            $('#savebut').bind('click' , function(){
								                            json.title = $('#title').val() ;
							                                json.isPicNews = $('#isPicNews').attr('checked') ? true : false ;
							                                json.content = $( '#contentId' ).omEditor('getData') ;
								                            
												     		var updatepgt = {
													                        url : 'ht/newsupdate.do',
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
												    ) ;
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
            title:'修改新闻'
     });
    
    
    
});



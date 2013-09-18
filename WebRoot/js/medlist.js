$(document).ready(function(){

    $('#list0').omGrid({
         width : '99.8%',
         method : 'POST' ,
         autoFit: true ,
         limit : pageslimit, //分页显示，每页显示8条
         singleSelect : false, //出现checkbox列，可以选择同时多行记录
         colModel : [    
                         {header:'<b>标题</b>',	align : 'center',	name:'title'  ,width:150} ,
                         {header:'<b>关键词</b>', align : 'center',  name:'keyword' ,width:200} ,
                         {header:'<b>摘要</b>',  align : 'center',  name:'summary' ,width:200} ,
                         {header:'<b>创建日期</b>',align : 'center', name:'mdDate',width:"autoExpand" } 
         ],
         dataSource : 'ht/medlist.do' 
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
										arry.push(ss[i].mdId );
									}
									
									var delt = {
						                        url : 'ht/meddel.do',
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
            			{	label:"修改"  ,
            		     	id   :"button2" ,
            	 		 	onClick:function(){
            	 		 		var ss = $('#list0').omGrid('getSelections',true);
					     		if(ss.length != 1 ){
								    at({cont:'请选择一条记录修改！' , type : 'error'});
								    return;
								}else{
									var igid = ss[0].mdId ;
									var getpgt = {
						                        url : 'ht/medget.do',
						                        params : {id : igid }  ,
						                        callback : function(json){
						                            if(json){
						                                //$('#list0').omGrid('reload');
						                                var ay = $(':input') ;
						                                //alert(1 + " \t " + ay.length) ;
						                                ay.each(function(i , n ){
						                                	//alert(i) ;
						                                	$(n).val('') ;
						                                });
						                            }else{
						                                alert(2) ;
						                               	//at({cont: json.info , type : 'error'});
						                            }
					                                var cd = json.title ; 
                                                    
					                                parseXML(json.fullContentFilePath);
                                                    
                                                    $('textarea[name="abstract1"]' ).val(json.summary);
                                                    $('textarea[name="abstract1Eng"]' ).val(json.summaryEng);
                                                    $('#mdid' ).val(json.mdId);
                                                    $("#imgpath").attr("src", json.thumbnailFilePath) ;
                                                    $("#thumbnailFilePath").val( json.thumbnailFilePath) ;
                                                    //alert( $("#thumbnailFilePath").val() ) ;
                                                    
                                                    $("#tab1").omDialog('open');
                                                    
					                                /*$('#actionDate').val( );
						                            
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
												     })*/
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
            title:'修改元数据信息'
     });
    
    
});


function parseXML(xmlpath){
    //1.JAVASCRIPT中的创建对象
    var xmlhttp ;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
      xmlhttp =new XMLHttpRequest();
    }else{// code for IE6, IE5
      xmlhttp =new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    if(xmlhttp){
        xmlhttp.open("GET", xmlpath,false);
        xmlhttp.send();
        
        var xmlDoc=xmlhttp.responseXML;  
        
        var items = xmlDoc.getElementsByTagName("metadata");
        
        if(hasChild(items[0]) ){
           //traverseDOM(items);
           //alert(Sarissa.getText(items , true) );
	       /* for(var i=0 ; i< items[0].childNodes[0].childNodes.length ;i++ ){
	            var node = (items[0].childNodes[0]).childNodes[i] ;
	            var name = node.nodeName ;
	            var lname = "metadata/IdInfo/"+name ;
	            alert(lname);
	            alert(Sarissa.getText(node , false) );
	            
	        }*/
            var cn =  (items[0]);
            traverseDOM(cn);
            //alert(array.length) ;
            //alert(array.join("\r\n") ) ;
            
            //alert(cn.nodeName + ' : ' +  cn.text );
        } 
        

        
    }
}
var array = new Array() ;
//遍历节点
function traverseDOM(node){
    if(hasChild(node) ){
        //alert(1) ;alert('length : '+node.childNodes.length) ;
        for(var k=0; k< node.childNodes.length ; k++){
	        traverseDOM(node.childNodes[k]);
        }
    }else{
        
        //alert(2) ;
        if(node){
            /*if(node.nodeType == 1){ //元素节点
                array.push(node.nodeName) ;
            }else */
            
            //只关注有值的节点
            if(node.nodeType == 3){ //文本节点
	            //var s = node.parentNode.nodeName + ' : '+ node.nodeValue ; 
	            //array.push(s) ;
                
                var nn = node.parentNode.nodeName ;
                if('language' == nn ){
                    $('#yuzhongCombox').omCombo('value' , node.nodeValue) ;
                }else if('srcType' == nn ){
                    $('#srcType').omCombo('value' , node.nodeValue) ;
                }else if('serviceType' == nn ){
                    $('#serviceType').omCombo('value' , node.nodeValue) ;
                }else if('timeInterval' == nn){
                    $('#timeInterval').omCombo('value' , node.nodeValue) ;
                }else if('accessClass' == nn){
                    $('#accessClass').omCombo('value' , node.nodeValue) ;
                }else{
	                $('input[name="xxx"]'.replace('xxx' , nn)).val(node.nodeValue);
                }
            }
	        //alert(Sarissa.getText(node , false) );
        }
    }
    return ;
}

//检查是否有子节点
function hasChild(obj){
    
    if( obj && obj.childNodes && obj.childNodes.length > 0){
        return true ;
    }else{
        return false ;
    }
}


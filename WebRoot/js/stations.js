$(document).ready(function(){
    
    /* */
    $('#list0').omGrid({
        title : '观测站列表' ,
         //height : 250,
         width : '99.8%',
         limit : 5, //分页显示，每页显示8条
         //singleSelect : false, //出现checkbox列，可以选择同时多行记录
         colModel : [    {header:'ID', name:'id' ,   width:100},
                         {header:'名称',name:'name',  width:200  },
                         {header:'地理位置',name:'location', width:'autoExpand'} ,
                         {header:'时区',name:'timeZone',   width:100 } ,
                         {header:'单位',name:'administrator',width:100 } ,
                         {header:'电话',name:'phone',        width:100 } 
         ],
         dataSource : 'ht/stationlist.do' //后台取数的URL
     });
    
    save.init();
});

var save ={
    init:function(){
        $( "#createblock").omDialog({
            autoOpen: false,
            resizable: false ,
            width : 440 ,
            title:'添加观测站' 
         });
         
        $("#createbut").click(this.open) ;
        $("#updatebut").click(this.modif) ;
        
        //$("#savebut").click(this.savedata);
        
        $('#del').click(function(){
                var dels = $('#list0').omGrid('getSelections' , true);
                if(dels.length < 1 ){
                    at({cont:'请选择删除的记录！' , type : 'error'});
                    return;
                }else{
                    var delt = {
                        url : 'ht/stationdel.do',
                        params : {id: dels[0].id}  ,
                        callback : function(json){
                            if(json.success){
                                $('#list0').omGrid('reload');
                            }else{
                                at({cont: json.info , type : 'error'});
                            }
                        }
                    }
                    //提示
                    $.omMessageBox.confirm({
                        title:'确认删除',
                        content:'删除观测站,你确定要删除吗?',
                        onClose:function(value){
                            if(value){
                                ajaxpost(delt);
                            }
                        }
                    });

                }
        });
    },
    open:function(){
        save.clear();
        
        this.action = 'save' ;
        $( "#createblock").omDialog('open');
    } ,
    action : 'save',
    savedata :function(){
    	if(this.check()){
    		
	        
    	}
    },
    check : function(){
    	var mc = $('#mcId') ; //
    	var dw = $('#dwmcId') ;
    	var txdz = $('#txdzId') ;
    	var lxdh = $('#lxdhId') ;
    	var em = $('#emId') ;
    	var yb = $('#ybId') ;
    	var js = $('#jsId') ;
    	var wz = $('#wzId') ;//
    	var jd = $('#jdId') ;//
    	var wd = $('#wdId') ;//
    	var sq = $('#sqId') ;//
    	var zy = $('#zyId') ;
    	
    	if(!mc.val()){
    		$('#info').html('请输入名称').show();
    		mc.focus();
    		return false ;
    	}
    	
    	if(!wz.val()){
    		$('#info').html('请输入地址').show();
    		wz.focus();
    		return false ;
    	}
    	
    	if(!jd.val()){
    		$('#info').html('请输入经度').show();
    		jd.focus();
    		return false ;
    	}
    	
    	if(!wd.val()){
    		$('#info').html('请输入纬度').show();
    		wd.focus();
    		return false ;
    	}
    	
    	if(!sq.val()){
    		$('#info').html('请输入所在时区').show();
    		sq.focus();
    		return false ;
    	}
    	
        var data = {
            url :'ht/stationsave.do' ,
            params :{name:mc.val() , location: wz.val(), longitude: jd.val(), 
            latitude:wd.val() , timeZone:sq.val(), introduction:js.val(), administrator:dw.val(), 
            address:txdz.val() , zipCode:yb.val(), phone:lxdh.val(), email:em.val(), homepage:zy.val()
            , picPath:'' ,action :this.action},
            
            callback : function(json){
                if(json.success){
                    $('#list0').omGrid('reload');
                    
                    save.clear();
                    $("#createblock").omDialog('close');
                }else{
                    $('#info').html(json.info).show();
                }
                
            }
        }
        
        ajaxpost(data);
    	//return true ;
    } ,
    clear : function(){
    	$('#mcId').val('') ; //
    	$('#dwmcId').val('') ;
    	$('#txdzId').val('') ;
    	$('#lxdhId').val('') ;
    	$('#emId').val('') ;
    	$('#ybId').val('') ;
    	$('#jsId').val('') ;
    	$('#wzId').val('') ;//
    	$('#jdId').val('') ;//
    	$('#wdId').val('') ;//
    	$('#sqId').val('') ;//
    	$('#zyId').val('') ;
    	
    	$('#info').html('');
    	//$("#createblock").omDialog('close');
    },
    modif :function(){
        var data = $('#list0').omGrid('getSelections' , true);
                if(data.length < 1 ){
                    at({cont:'请选择一条记录！' , type : 'error'});
                    return;
                }else{
                    var getitem = {
                        url : 'ht/getstation.do',
                        params : {id: data[0].id}  ,
                        callback : function(json){
                            if(json.success){
                                //$('#list0').omGrid('reload');
                                //alert(json.data.name ) ;
                                save.modifOpen(json.data);
                            }else{
                                at({cont: json.info , type : 'error'});
                            }
                        }
                    }
                    ajaxpost(getitem);
                    //提示
                    /*
                    $.omMessageBox.confirm({
                        title:'确认删除',
                        content:'删除用户,你确定要这样做吗?',
                        onClose:function(value){
                            if(value){
                                //ajaxpost(delt);
                            }
                        }
                    });
                    
                    */
                }
    },
    modifOpen :function(data){
        
        $( "#createblock").omDialog('open');
        this.clear();
        this.action = data.id ;
        
        $('#mcId').val(data.name) ; //
        $('#dwmcId').val(data.administrator) ;
        $('#txdzId').val(data.address) ;
        $('#lxdhId').val(data.phone) ;
        $('#emId').val(data.email) ;
        $('#ybId').val(data.zipCode) ;
        $('#jsId').val(data.introduction) ;
        $('#wzId').val(data.location) ;//
        $('#jdId').val(data.longitude) ;//
        $('#wdId').val(data.latitude) ;//
        $('#sqId').val(data.timeZone) ;//
        $('#zyId').val(data.homepage) ;
    }
} 


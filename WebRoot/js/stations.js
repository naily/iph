
$(document).ready(function(){
    
    /* 观测站管理*/
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
         
          $('a#createbut').omButton({
            onClick : this.open
          })  ;
          $('a#updatebut').omButton({
            onClick : this.modif
          })  ;
          $('a#del').omButton({
            onClick : function(){
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
        }
          })  
        //$("#createbut").click(this.open) ;
        //$("#updatebut").click(this.modif) ;
        //$("#savebut").click(this.savedata);
        
    },
    open:function(){
        save.clear() ;
        save.action = 'save' ;
        
        //$('#stId').val('').attr('readonly' , false) ;
        $('#stId').val('').removeAttr('disabled') ;
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
        
        var st = $('#stId') ;//观测站编码
        
        if(!st.val()){
            $('#info').html('请输入观测站编码').show();
            st.focus();
            return false ;
        }
    	
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
            , picPath:'' ,action :this.action , id :st.val()},
            
            callback : function(json){
                if(json.success){
                    $('#list0').omGrid('reload');
                    
                    save.clear();
                    $('#stId').removeAttr('disabled') ;
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
        //$('#stId').val('').attr('readonly' , false) ;
    	
    	$('#info').html('').hide();
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
        
        $('#stId').val(data.id).attr('disabled' , true) ;
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

var tzdatasource = [
{text:"UTC-12                                         ", value:"UTC-12"},
{text:"UTC-11                                         ", value:"UTC-11"},
{text:"UTC-10(HST - 夏威夷－阿留申标准时间)               ", value:"UTC-10"},
{text:"UTC-9:30                                       ", value:"UTC-9:30"},
{text:"UTC-9(AKST - 阿拉斯加标准时间)                    ", value:"UTC-9"},
{text:"UTC-8(PST - 太平洋标准时间)                       ", value:"UTC-8"},
{text:"UTC-7(MST - 北美山区标准时间)                     ", value:"UTC-7"},
{text:"UTC-6(CST - 北美中部标准时间)                     ", value:"UTC-6"},
{text:"UTC-5(EST - 北美东部标准时间)                     ", value:"UTC-5"},
{text:"UTC-4:30                                       ", value:"UTC-4:30"},
{text:"UTC-4(AST - 大西洋标准时间)                       ", value:"UTC-4"},
{text:"UTC-3:30(NST - 纽芬兰岛标准时间)                  ", value:"UTC-3:30"},
{text:"UTC-3                                          ", value:"UTC-3"},
{text:"UTC-2                                          ", value:"UTC-2"},
{text:"UTC-1                                          ", value:"UTC-1"},
{text:"UTC(WET - 欧洲西部时区，GMT - 格林尼治标准时间)",       value:"UTC"},
{text:"UTC+1(CET - 欧洲中部时区)                      ", value:"UTC+1"},
{text:"UTC+2(EET - 欧洲东部时区)                      ", value:"UTC+2"},
{text:"UTC+3(MSK - 莫斯科时区)                        ", value:"UTC+3"},
{text:"UTC+3:30                                       ", value:"UTC+3:30"},
{text:"UTC+4                                          ", value:"UTC+4"},
{text:"UTC+4:30                                       ", value:"UTC+4:30"},
{text:"UTC+5                                          ", value:"UTC+5"},
{text:"UTC+5:30                                       ", value:"UTC+5:30"},
{text:"UTC+5:45                                       ", value:"UTC+5:45"},
{text:"UTC+6                                          ", value:"UTC+6"},
{text:"UTC+6:30                                       ", value:"UTC+6:30"},
{text:"UTC+7                                          ", value:"UTC+7"},
{text:"UTC+8(CST - 中国标准时间)                      ", value:"UTC+8"},
{text:"UTC+9(KST- 韩国标准时间)                       ", value:"UTC+9"},
{text:"UTC+9:30(ACST - 澳洲中部标准时间)              ", value:"UTC+9:30"},
{text:"UTC+10(AEST - 澳洲东部标准时间)                ", value:"UTC+10"},
{text:"UTC+10:30                                      ", value:"UTC+10:30"},
{text:"UTC+11                                         ", value:"UTC+11"},
{text:"UTC+11:30                                      ", value:"UTC+11:30"},
{text:"UTC+12                                         ", value:"UTC+12"},
{text:"UTC+12:45                                      ", value:"UTC+12:45"},
{text:"UTC+13                                         ", value:"UTC+13"},
{text:"UTC+14                                         ", value:"UTC+14"}
] ;

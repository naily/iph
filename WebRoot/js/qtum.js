$(document).ready(function(){
    /*注册账户列表*/
    $('#list0').omGrid({
         width : '99.8%',
         method : 'POST' ,
         limit : pageslimit, //分页显示，每页显示8条
         //singleSelect : false, //出现checkbox列，可以选择同时多行记录
         colModel : [    {header:'登录名', name:'loginId' ,  width:150},
                         {header:'姓名',name:'name',    width:100},
                         {header:'国家',name:'country', width:100,
	                         renderer : function(val, rowData, rowIndex){
	                            if (val == '86') {
	                                 return '<span style="color:green;"><b>中国</b></span>';
	                             }  
                                 if (val == '01') {
                                     return '<span style="color:green;"><b>美国</b></span>';
                                 } 
                                 if (val == '03') {
                                     return '<span style="color:green;"><b>其它</b></span>';
                                 } else{
                                 	return val ;
                                 }
	                         } 
                         } ,
                         {header:'EMAIL',name:'email',width:'autoExpand'  } ,
                         {header:'电话',name:'telephone',width:100  } ,
                         {header:'注册日期',name:'regDate',width:140  }  ,
                         {header:'操作',name:'operation',width:150 ,
	                         renderer: function(colValue, rowData, rowIndex){
	                         	var id = rowData.loginId ;
	                         	return '<a href="javascript:updateUser(\''+id+'\');" >修改 </a>&nbsp;&nbsp;&nbsp;&nbsp;' +
	                         		   '<a href="javascript:deleteUser(\''+id+'\');" >删除 </a>' ;
	                         }
                         }
         ],
         dataSource : 'ht/qtumlist.do' 
     });
     
	$('#buttonbar').omButtonbar({
		width : '99.8%',
		btns : [{
			label : '导出用户' ,
			onClick:function(){
                window.open(basepath + '/ht/downloadAllUser.do')
			}
		} ] 
	}) ;
	
    $('#comboAdmin').omCombo({
        dataSource:'ht/allusername.do' ,
        valueField : 'id' ,
        optionField :'name'  ,
        //editable : false,
        emptyText : '选择名称'
    }) ;
    var dateselect = false ;
    $('#comboActionType').omCalendar({onSelect : function(date,event) {dateselect = true ;}});
     
     $('#searchbut').bind('click' ,  function(){
            var un = $('#comboAdmin').omCombo('value') ;
            var act = $('#comboActionType').omCalendar('getDate') ;
            if(dateselect){
            	act = $.omCalendar.formatDate(act, "yy-mm-dd") ;
            	dateselect = false
            }else{
            	act = '' ;
            }
            
            $('#list0').omGrid({extraData: { name : un ,regDate:act }}) ;
            $('#list0').omGrid('reload') ;
        }
    );
     
     
     $( "#qtum_update").omDialog({
            autoOpen: false,
            resizable: false ,
            width:'auto' ,
            title:'修改用户资料'
     });
     
     $('#updateUserSubmit').omButton({
        //icons : {left:'images/help.png',right:'images/edit_add.png'},
        label : '提  交' ,
        width : 150, 
        onClick : saveUserUpdate
    });
    
    // 性别下拉列表
    $('input[name=gender]').omCombo({ // 初始化Combo
        dataSource : gender_omCombo_datasource/*
                                                 * [ { text : '男', value : '男' }, {
                                                 * text : '女', value : '女' } ]
                                                 */,
        width : 225,
        onValueChange : function() {
            $('input[name=gender]').focus();

        }
    });

    // 学历下拉列表：博士、硕士、本科、本科以下
    $('input[name=eduBackground]').omCombo({ // 初始化Combo
        dataSource : eduBackground_omCombo_datasource,
        width : 225,
        onValueChange : function() {
            $('input[name=eduBackground]').focus();
        }
    });

    // 所在行业：科研院所、教育院校、政府机关、企事业单位、民间组织、其他
    $('input[name=vocation]').omCombo({ // 初始化Combo
        dataSource : vocation_omCombo_datasource,
        width : 225,
        onValueChange : function() {
            $('input[name=vocation]').focus();
        }
    });

    // 国家下拉列表
    $('input[name=country]').omCombo({ // 初始化Combo
        dataSource : country_omCombo_datasource,
        width : 225,
        onValueChange : function() {
            // 根据第1个combo的当前值算出第2个combo的值
            // $('input[name=country]').focus();
            var records = getCityRecords();
            // 将算出的值设置为第2个combo的数据源
            $('#region').val('').omCombo('setData', records);
        }
    });
    // 区域下拉列表，省份
    $('input[name=region]').omCombo({
                width : 225
            });
    
});

function deleteUser(id){
	var delt = {
        url : 'ht/delqtuser.do',
        params : {ids: id}  ,
        callback : function(json){
            if(json.success){
                $('#list0').omGrid('reload');
                tipOK() ;
            }else{
                at({cont: json.info , type : 'error'});
            }
        }
    }
    //提示
    $.omMessageBox.confirm({
        title:'确认删除',
        content:'删除用户,你确定要这样做吗?',
        onClose:function(value){
            if(value){
                ajaxpost(delt);
            }
        }
    });
}

function updateUser(uid){
	getUserInfo(uid);
	//$("#qtum_update").omDialog('open');
}

function getUserInfo(uid) {
	var data = {
		url : 'ht/getuser.do',
		params : {loginId : uid},
		callback : function(json) {
			if (json.success) {
				/**
				 * 用户注册信息
				 */
				$('#loginId').attr("value", json.user.loginId);//
				$('#name').attr("value", json.user.name);				
				$('input[name=gender]').omCombo({ // 初始化性别
					dataSource : gender_omCombo_datasource,
					width : 225,
					onValueChange : function() {
						$('input[name=gender]').focus();
					},
					value : json.user.gender
				});
				
	   		   $('input[name=eduBackground]').omCombo({ // 初始化学历
					dataSource : eduBackground_omCombo_datasource,
					width : 225,
					onValueChange : function() {
						$('input[name=eduBackground]').focus();
					},
					value : json.user.eduBackground
				});
				
				$('#email').attr("value", json.user.email);
				
			    $('input[name=vocation]').omCombo({ // 初始化职业
					dataSource : vocation_omCombo_datasource,
					width : 225,
					onValueChange : function() {
						$('input[name=vocation]').focus();
					},
					value : json.user.vocation
				});
				$('input[name=country]').omCombo({ // 初始化城市
					dataSource : country_omCombo_datasource,
					width : 225 ,
					onValueChange : function() {
						$('input[name=country]').focus();
						var records = getCityRecords();
						// 将算出的值设置为第2个combo的数据源
						$('#region').val('').omCombo('setData', records);
					},
					value : json.user.country
				});

				$('input[name=region]').omCombo({
							value : json.user.region
				});
				$('#workunit').attr("value", json.user.workunit);
				$('#zipcode').attr("value", json.user.zipcode);
				$('#telephone').attr("value", json.user.telephone);
				$('#address').attr("value", json.user.address);	
				
				$('#optTpyeID').attr("value", 'update');//
				
				$("#qtum_update").omDialog('open');
			} else {
				showError(json.info);
			}

		}
	}
	ajaxpost(data);

}


function saveUserUpdate() {
    
	var data = {
		url : "ht/updateqtuser.do",
		params : {
			loginId : $('#loginId').val(),
			name : $('#name').val(),
			gender : $('#gender').omCombo('value'),
			eduBackground : $('#eduBackground').omCombo('value'),
			email : $('#email').val(),
			vocation : $('#vocation').omCombo('value'),
			country : $('#country').omCombo('value'),
			region : $('#region').omCombo('value'),
			workunit : $('#workunit').val(),
			zipcode : $('#zipcode').val(),
			telephone : $('#telephone').val(),
			address : $('#address').val() 

		},
		callback : function(json) {
			if (json.success) {
				//showWaiting(); 
                $('#list0').omGrid('reload');
                $("#qtum_update").omDialog('close');
			} else {
				showError(json.info);

			}
		}
	}
    
    //alert(data.params.address);
	ajaxpost(data);
}

function showWaiting() {
	$.omMessageBox.waiting({
		title : '请等待',
		content : '服务器正在处理请求，请稍等...'
	});
}

function showError(v) {
	$.omMessageBox.alert({
		type : 'error',
		title : '失败',
		content : v,
		onClose : function(v) {
			// showResult('我知道了！');
		}
	});
}

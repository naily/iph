$(document).ready(function() {
					/**
					 * 用户登录
					 * */
					$('#userLoginHref').bind('click', function() {
					    var data = {
					            url :'qt/userLogin.do' ,
					            params :{loginId:$('#userLoginId').val() , password: $('#loginPassword').val()},
					            callback : function(json){
					                if(json.success){
					                	var loginName=$('#userLoginId').val();
					                	$('#userLoginId').val('') ;
					                	$('#loginPassword').val('');
					                	$('#user_login_form').html('欢迎您：'+loginName+'&nbsp;&nbsp;<a href="qt/logout.do" class="a1">退出登录</a>');
					                }else{
					                    //at({cont:json , type : 'error'});
					                    $('#info').html(json.info).show();
					                }
					                
					            }
					        }	
					        ajaxpost(data);
					});
					
	
					/**
					 * 用户注册
					 * */
					$('#userRegHref').bind('click', function() {
						$("#userRegDialog").omDialog('open');
					});

					$("#userRegDialog").omDialog( {
						autoOpen : false,
						height : 540,
						width : 520
					});
					/*
					 * 用户注册表单验证
					 */
					$('#userRegForm').validate(
									{
										rules : {
											loginId : {
												required : true
											},
											password : {
												required : true
											},
											name : {
												required : true
											},
											gender : {
												required : true
											},
											eduBackground : {
												required : true
											},
											email : {
												required : true
											},
											vocation : {
												required : true
											},
											region : {
												required : true
											}

										},
										messages : {
											loginId : {
												required : '请输入用户名'
											},
											password : {
												required : '请输入密码'
											},
											name : {
												required : '请输入姓名'
											},
											gender : {
												required : '请选择性别'
											},
											eduBackground : {
												required : '请选择学历'
											},
											email : {
												required : '请输入email'
											},
											vocation : {
												required : '请选择职业'
											},
											region : {
												required : '请选择区域'
											}
										},
										errorPlacement:errorPlacement,
										showErrors :showErrors,		
										submitHandler : function() {											
											saveRegData();
											return false;
									}
									});

					$('.errorImg').bind('mouseover', function() {
						$(this).next().css('display', 'block');
					}).bind('mouseout', function() {
						$(this).next().css('display', 'none');
					});

					// 性别下拉列表
					$('input[name=gender]').omCombo( { // 初始化Combo
								dataSource : [ {
									text : '男',
									value : '男'
								}, {
									text : '女',
									value : '女'
								} ],
								width : 225,
								onValueChange : function() {
									$('input[name=gender]').focus();

								}
							});

					// 学历下拉列表：博士、硕士、本科、本科以下
					$('input[name=eduBackground]').omCombo( { // 初始化Combo
								dataSource : [ {
									text : '博士',
									value : '01'
								}, {
									text : '硕士',
									value : '02'
								}, {
									text : '本科',
									value : '03'
								}, {
									text : '本科以下',
									value : '04'
								} ],
								width : 225,
								onValueChange : function() {
									$('input[name=eduBackground]').focus();
								}
							});

					// 所在行业：科研院所、教育院校、政府机关、企事业单位、民间组织、其他
					$('input[name=vocation]').omCombo( { // 初始化Combo
								dataSource : [ {
									text : '科研院所',
									value : '01'
								}, {
									text : '教育院校',
									value : '02'
								}, {
									text : '政府机关',
									value : '03'
								}, {
									text : '企事业单位',
									value : '04'
								}, {
									text : '民间组织',
									value : '05'
								}, {
									text : '其他',
									value : '06'
								} ],
								width : 225,
								onValueChange : function() {
									$('input[name=vocation]').focus();
								}
							});

					// 国家下拉列表
					$('input[name=country]').omCombo( { // 初始化Combo
								dataSource : [ {
									text : '中国',
									value : '86'
								}, {
									text : '美国',
									value : '01'
								} ],
								width : 225,
								onValueChange : function() {
									// 根据第1个combo的当前值算出第2个combo的值
								// $('input[name=country]').focus();
								var records = getCityRecords();
								// 将算出的值设置为第2个combo的数据源
								$('#region').val('')
										.omCombo('setData', records);
							}
							});
					// 区域下拉列表，省份
					$('input[name=region]').omCombo( {
						width : 225
					});

					// $("#regSubmit1").omButton();
					//$("#reset").omButton();
					
					
					

					/**
					 *找回密码
					 * */
					$('#getUserPasswordHref').bind('click', function() {
						$("#getUserPassword").omDialog('open');
					});

					$("#getUserPassword").omDialog( {
						autoOpen : false,
						height : 240,
						width : 550
					});
					/*
					 * 用户注册表单验证
					 */
					$('#getUserPasswordForm').validate(
									{
										rules : {
											loginId : {
												required : true
											},
											
											email : {
												required : true
											}
										},
										messages : {
											loginId : {
												required : '请输入用户名'
											},
											
											email : {
												required : '请输入email'
											}
										},
										errorPlacement:errorPlacement,
										showErrors :showErrors,									
										submitHandler : function() {
											getUerPassword();
										//saveRegData();
										// $(this)[0].currentForm.reset();

										return false;
									}
									});
					//
				});


errorPlacement=function(error,element) {
	if (error.html()) {
		$(element).parents().map(
						function() {
							if (this.tagName.toLowerCase() == 'td') {
								var attentionElement = $(this).next().children().eq(0);
								attentionElement.css('display','block');
								attentionElement.next().html(error);
							}
						});
	}
}

showErrors=function(errorMap,errorList) {
	if (errorList&& errorList.length > 0) {
		$.each(errorList,function(index,obj) {
			var msg = this.message;
				$(obj.element).parents().map(
					function() {
						if (this.tagName.toLowerCase() == 'td') {
							var attentionElement = $(this).next().children().eq(0);
								attentionElement.show();
								attentionElement.next().html(msg);
								}
						});
			});
	} else {
		$(this.currentElements).parents().map(
					function() {
						if (this.tagName.toLowerCase() == 'td') {
								$(this).next().children().eq(0).hide();
							}
						});
	}
	this.defaultShowErrors();
}
/**
 * 通过国家级联地区
 */
function getCityRecords() {
	var country = $('#country').omCombo('value');
	if (country == '86') {// 中国
		return [ {
			'text' : '北京市',
			'value' : '110000'
		}, {
			'text' : '江苏省',
			'value' : '320000'
		} ];
	} else if (country == '01') {// 美国
		return [ {
			'text' : '夏威夷',
			'value' : 'hawaii'
		}, {
			'text' : '佛罗里达',
			'value' : 'florida'
		} ];
	} else {
		return [];
	}
}
/**
 * 注册信息保存
 * 
 */
function saveRegData() {
	var data = {
		url : 'qt/userReg.do',
		params : {
			loginId : $('#loginId').val(),
			password : $('#password').val(),
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
			address : $('#address').val(),
			code : $('#code').val()

		},
		callback : function(json) {
			if (json.success) {
				showWaiting();
				setTimeout("$.omMessageBox.waiting('close');showRegSuccess();", 3000);
			} else {
				showError(json.info);

			}

		}
	}
	ajaxpost(data);

}

/**
 * 请求找回密码
 * 
 */
function getUerPassword() {
	var data = {
		url : 'qt/getPassword.do',
		params : {
			loginId : $('#loginId_').val(),			
			email : $('#email_').val()		
		},
		callback : function(json) {
			if (json.success) {
				showWaiting();
				setTimeout("$.omMessageBox.waiting('close');getPasswordSuccess();", 3000);
			} else {
				showError(json.info);

			}

		}
	}
	ajaxpost(data);

}
function showWaiting() {
	$.omMessageBox.waiting( {
		title : '请等待',
		content : '服务器正在处理请求，请稍等...'
	});	
}
function showRegSuccess() {
	$.omMessageBox.alert( {
		type : 'success',
		title : '成功',
		content : '注册成功！',
		onClose : function(v) {
			$('#userRegDialog').omDialog('close');
			$('#userRegForm')[0].reset();
		}
	});
}
function getPasswordSuccess() {
	$.omMessageBox.alert( {
		type : 'success',
		title : '成功',
		content : '密码已经发送至您的邮箱，请登录邮箱查看！',
		onClose : function(v) {
			$('#getUserPassword').omDialog('close');
			$('#getUserPasswordForm')[0].reset();
		}
	});
}

function showError(v) {
	$.omMessageBox.alert( {
		type : 'error',
		title : '失败',
		content : v,
		onClose : function(v) {
			// showResult('我知道了！');
	}
	});
}
/**
 * 重载验证码
 * */
function reloadimage(codeImageID){
	$('#'+codeImageID).attr('src' , 'ht/logincode.do?'+new Date().getTime());
}

$(document).ready(function(){
    $('#yuzhongCombox').omCombo({
                dataSource : [ {text : 'zh_CN', value : 'zh_CN'}, 
                               {text : 'en_US', value :  'en_US'}] ,
               editable : false,
               emptyText : '选择语种',
               value : 'zh_CN'
     });
     $('#zifuCombox').omCombo({
                dataSource : [ {text : 'GB2312', value : 'GB2312'}, 
                               {text : 'GBK', value :  'GBK'}] ,
               editable : false,
               emptyText : '选择字符集',
               value : 'GB2312'
     });
    $('#fangfaCombox').omCombo({
                dataSource : [ {text : '方法1', value : '1'} ] ,
               editable : false,
               emptyText : '选择表示方法',
               value : '1'
     });
    $('#anquanCombox').omCombo({
                dataSource : [ {text : '安全分级1', value : '1'} ] ,
               editable : false,
               emptyText : '选择安全限制分级',
               value : '1'
     });
    
    //表单验证
     var notempty = "不能为空" ;
    $("#savemetadata").validate({
        rules : {
          name : {required : true } ,
          keys : {required : true } ,
          zhaiyao : {required : true } ,
          yuzhong : {required : true } ,
          zifu : {required : true } ,
          mude : {required : true } ,
          fangfa : {required : true } ,
          
          geshiname : {required : true } ,
          anquan : {required : true } ,
          
          contacts : {required : true } ,
          employer : {required : true } ,
          phone : {required : true } 
        },
        messages : {
          name : { required : notempty },
          keys : {required : notempty } ,
          zhaiyao : {required : notempty } ,
          yuzhong : {required : notempty } ,
          zifu : {required : notempty } ,
          mude : {required : notempty } ,
          fangfa : {required : notempty } ,
          
          geshiname : {required : notempty } ,
          anquan : {required : notempty } ,
          
          contacts : {required : notempty } ,
          employer : {required : notempty } ,
          phone : {required : notempty } 
        },
        submitHandler : function(){
        	//$(this)[0].currentForm.action = "./ht/medSave.do" ;
        	//alert($(this)[0].currentForm.action) ;
        	$("#savemetadata").omAjaxSubmit({
        		url : './ht/medSave.do' ,
        		method:'POST' ,
        		resetForm: true ,
        		success : function(responseText, statusText, xhr, $form){
        			alert('提交成功！');
        		}
        	}) ;
        	return false;
                    //
                    //$(this)[0].currentForm.reset();
                    
        }

     });

    
    
    
    
    
    
});



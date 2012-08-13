$(document).ready(function(){
    $('#yuzhongCombox').omCombo({
                dataSource : [ {text : 'zh_CN', value : 'zh_CN'}, 
                               {text : 'en_US', value :  'en_US'}] ,
               editable : false,
               emptyText : '选择语种',
               value : 'zh_CN'
     });
     $('#timeInterval').omCombo({
                dataSource : [ {text : '年', value : '01'}, 
                               {text : '半年', value :  '02'},
                               {text : '季度', value :  '03'},
                               {text : '双月', value :  '04'},
                               {text : '月', value :  '05'},
                               {text : '旬', value :  '06'},
                               {text : '周', value :  '07'},
                               {text : '日', value :  '08'},
                               {text : '小时', value :  '09'},
                               {text : '半小时', value :  '10'},
                               {text : '分钟', value :  '11'},
                               {text : '半分钟', value :  '12'},
                               {text : '秒', value :  '13'},
                               {text : '其他', value :  '99'}] ,
               editable : false,
               emptyText : '采样间隔' 
     });
    $('#srcType').omCombo({
                dataSource : [ {text : '方法1', value : '1'} ] ,
               editable : false,
               emptyText : '选择表示方法',
               value : '1'
     });
    $('#serviceType').omCombo({
                dataSource : [ {text : '安全分级1', value : '1'} ] ,
               editable : false,
               emptyText : '选择安全限制分级',
               value : '1'
     });
     $('#accessClass').omCombo({
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



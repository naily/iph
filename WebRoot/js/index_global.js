/**
 * 此处定义的参数及方法不区分中英文
 * */

//年份
year_omCombo_datasource=[
							{text : "2009", value : "2009"},
							{text : "2010", value : "2010"},
							{text : "2011", value : "2011"},
							{text : "2012", value : "2012"}]
//月份
month_omCombo_datasource= [ {text : '1',  value : '1' },
							{text : '2',  value : '2' },
							{text : '3',  value : '3' },
							{text : '4',  value : '4' },
							{text : '5',  value : '5' },
							{text : '6',  value : '6' },
							{text : '7',  value : '7' },
							{text : '8',  value : '8' },
							{text : '9',  value : '9' },
							{text : '10', value : '10' },
							{text : '11', value : '11' },
							{text : '12', value : '12' }]
//小时
hour_omCombo_datasource= [  {text : '请选择', value : '' },
							{text : '00', value : '00' },
							{text : '01', value : '01' },
							{text : '02', value : '02' },
							{text : '03', value : '03' },
							{text : '04', value : '04' },
							{text : '05', value : '05' },
							{text : '06', value : '06' },
							{text : '07', value : '07' },
							{text : '08', value : '08' },
							{text : '09', value : '09' },
							{text : '10', value : '10'},
							{text : '11', value : '11'},
							{text : '12', value : '12' },
							{text : '13', value : '13' },
							{text : '14', value : '14' },
							{text : '15', value : '15' },
							{text : '16', value : '16' },
							{text : '17', value : '17' },
							{text : '18', value : '18' },
							{text : '19', value : '19' },
							{text : '20', value : '20' },
							{text : '21', value : '21' },
							{text : '22', value : '22' },
							{text : '23', value : '23' }
							]
//电离参数(日报)
parameter_omCombo_datasourceOld= [ { text : 'foF2',  value : 'foF2'}, 
					           { text : 'h\'F2',  value : 'hlF2'}, 
					           { text : 'foF1',  value : 'foF1'}, 
					           { text : 'h\'F1',  value : 'hlF1'}, 
					           { text : 'h\'F',   value : 'hlF'}, 
					           { text : 'hpF',   value : 'hpF'}, 
					           { text : 'foE',   value : 'foE'}, 
					           { text : 'h\'E',   value : 'hlE'}, 
					           { text : 'foEs',  value : 'foEs'}, 
					           { text : 'h\'Es',  value : 'hlEs'}, 
					           { text : 'fbEs',  value : 'fbEs'}, 
					           { text : 'Fmin',  value : 'fmin'}, 
					           { text : 'M3000F2',  value : 'm3000F2'}, 
					           { text : 'M1500F2',  value : 'm1500F2'}, 
					           { text : 'M3000F1',  value : 'm3000F1'}, 
					           { text : 'M3000F',   value : 'm3000F'}
					           ]
//电离参数(日报) 20121214新修改
parameter_omCombo_datasource= [ { text : 'foF2',  value : 'foF2'}, 
					           { text : 'fxF2',  value : 'fxF2'},
					           { text : 'h\'F2',  value : 'hlF2'},
					           { text : 'hpF2',  value : 'hpF2'},
					           { text : 'foF1',  value : 'foF1'}, 
					           { text : 'hxl',  value : 'hxl'},					          
					           { text : 'h\'F',   value : 'hlF'}, 					            
					           { text : 'hpF',   value : 'hpF'}, 
					           { text : 'foE',   value : 'foE'}, 
					           { text : 'h\'E',   value : 'hlE'},
					           { text : 'foEs',  value : 'foEs'}, 
					           { text : 'h\'Es',  value : 'hlEs'}, 
					           { text : 'fbEs',  value : 'fbEs'},
					           { text : 'Es-Type',  value : 'es'},
					           { text : 'Fmin',  value : 'fmin'}, 
					           { text : 'MUF3000F1',  value : 'MUF3000F1'}, 
					           { text : 'MUF3000F2',  value : 'MUF3000F2'}, 
					           { text : 'M3000F1',  value : 'm3000F1'}, 
					           { text : 'M3000F2',   value : 'm3000F2'}
					           ]					           
//电离参数(月报) 20121214新增
parameter_month_omCombo_datasource= [ 
							   { text : 'foF2',  value : 'foF2'}, 
					           { text : 'h\'F2',  value : 'hlF2'}, 
					           { text : 'foF1',  value : 'foF1'}, 					  
					           { text : 'h\'F',   value : 'hlF'}, 
					           { text : 'foE',   value : 'foE'}, 
					           { text : 'h\'E',   value : 'hlE'}, 				           					           
					           { text : 'foEs',  value : 'foEs'},  					           
					           { text : 'h\'Es',  value : 'hlEs'},
					           { text : 'fbEs',  value : 'fbEs'},  					           					         
					           { text : 'Es-Type',  value : 'es'},
					           { text : 'Fmin',  value : 'fmin'},				           
					           { text : 'M3000F2',   value : 'm3000F2'},
					           { text : 'M3000F1',  value : 'm3000F1'}
					           ]
/*
 * 曲线图参数选择
 * 1、带多因子选择参数
 * 2、只能选择一个单因子或者一组多因子
 * 3、一次不可以选择多个参数
 * */
parameter_omCombo_datasource2=[ 
					   { text : 'foF2',  value : 'foF2'}, 
			           { text : 'h\'F2',  value : 'hlF2'}, 
			           { text : 'foF1',  value : 'foF1'}, 
			           { text : 'h\'F1',  value : 'hlF1'}, 
			           { text : 'h\'F',   value : 'hlF'}, 
			           { text : 'hpF',   value : 'hpF'}, 
			           { text : 'foE',   value : 'foE'}, 
			           { text : 'h\'E',   value : 'hlE'}, 
			           { text : 'foEs',  value : 'foEs'}, 
			           { text : 'h\'Es',  value : 'hlEs'}, 
			           { text : 'fbEs',  value : 'fbEs'}, 
			           { text : 'Fmin',  value : 'fmin'}, 
			           { text : 'M3000F2',  value : 'm3000F2'}, 
			           { text : 'M1500F2',  value : 'm1500F2'}, 
			           { text : 'M3000F1',  value : 'm3000F1'}, 
			           { text : 'M3000F',   value : 'm3000F'},
			           { text : 'foF2.foF1.foEs.foE',   value : "foF2,foF1,foEs,foE"},
			           { text : "P（foEs）.h'Es",   value : "M3000F2,P（foEs）,h\'Es"}
			         
			           
			           ]
			     

  parameter_array=     ['foF2','hlF2','foF1','hlF','foE','hlE','foEs','hlEs','fbEs','es','fmin','m3000F2','m3000F1']
  parameter_array_text=['foF2','h\'F2','foF1','h\'F','foE','h\'E','foEs','h\'Es','fbEs','Es-Type','Fmin','M3000F2','M3000F1']	
 
                       
 
 
 month_array=['1','2','3','4','5','6','7','8','9','10','11','12']
 /*
 * 电离曲线图的x轴数据列（小时序列）
 * */			           
parameter_chart_xAxis_hour=['00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23']
 /*
 * 电离曲线图的x轴数据列（月份序列）
 * */	
parameter_chart_xAxis_month=['1','2','3','4','5','6','7','8','9','10','11','12']
/*根据参数类型，返回该参数的单位*/
function getUnit(ptype){
	var retValue;
	var km="(KM)";
	var mhz="(MHZ)";
	var kmArry= ['hlF2','hlF1','hlF','hpF', 'hlE','hlEs'];
	var mhzArray=['foF2','foF1','foE','foEs','fbEs','fmin'];
	            // {'m3000F2', 'M1500F2','m3000F1','m3000F'}
	if(kmArry.toString().indexOf(ptype) > -1){
		retValue =km;
	}else if(mhzArray.toString().indexOf(ptype) > -1){
		retValue=mhz;
	}else{
		retValue='';
	}
	return retValue;
}

/*根据月份的数字，返回该月份的英文*/
function getMonthEn(mdata){
	var retValue;	
	var monthArry= ['Jan','Feb','Mar','Apr', 'May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'];
    retValue =monthArry[mdata-1]
	return retValue;
}
//将超链接转化为表单提交
function linkClick(linkObject) {
	var formObject = document.createElement('form');
	document.body.appendChild(formObject);
	formObject.setAttribute('method', 'post');
	var url = linkObject.href;
	var uri = '';
	var i = url.indexOf('?');

	if (i == -1) {
		formObject.action = url;
	} else {
		formObject.action = url.substring(0, i);
	}

	if (i >= 0 && url.length >= i + 1) {
		uri = url.substring(i + 1, url.length);
	}

	var sa = uri.split('&');

	for (var i = 0; i < sa.length; i++) {
		var isa = sa[i].split('=');
		var inputObject = document.createElement('input');
		inputObject.setAttribute('type', 'hidden');
		inputObject.setAttribute('name', isa[0]);
		inputObject.setAttribute('value', isa[1]);
		formObject.appendChild(inputObject);
	}

	formObject.submit();

	return false;
}

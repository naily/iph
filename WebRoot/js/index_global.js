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

//电离参数
parameter_omCombo_datasource= [ { text : 'foF2',  value : 'foF2'}, 
					           { text : 'hlF2',  value : 'hlF2'}, 
					           { text : 'foF1',  value : 'foF1'}, 
					           { text : 'hlF1',  value : 'hlF1'}, 
					           { text : 'hlF',   value : 'hlF'}, 
					           { text : 'hpF',   value : 'hpF'}, 
					           { text : 'foE',   value : 'foE'}, 
					           { text : 'hlE',   value : 'hlE'}, 
					           { text : 'foEs',  value : 'foEs'}, 
					           { text : 'hlEs',  value : 'hlEs'}, 
					           { text : 'fbEs',  value : 'fbEs'}, 
					           { text : 'Fmin',  value : 'fmin'}, 
					           { text : 'M3000F2',  value : 'm3000F2'}, 
					           { text : 'M1500F2',  value : 'm1500F2'}, 
					           { text : 'M3000F1',  value : 'm3000F1'}, 
					           { text : 'M3000F',   value : 'm3000F'}
					           ]

/*
 * 曲线图参数选择
 * 1、带多因子选择参数
 * 2、只能选择一个单因子或者一组多因子
 * 3、一次不可以选择多个参数
 * */
parameter_omCombo_datasource2=[ 
					   { text : 'foF2',  value : 'foF2'}, 
			           { text : 'hlF2',  value : 'hlF2'}, 
			           { text : 'foF1',  value : 'foF1'}, 
			           { text : 'hlF1',  value : 'hlF1'}, 
			           { text : 'hlF',   value : 'hlF'}, 
			           { text : 'hpF',   value : 'hpF'}, 
			           { text : 'foE',   value : 'foE'}, 
			           { text : 'hlE',   value : 'hlE'}, 
			           { text : 'foEs',  value : 'foEs'}, 
			           { text : 'hlEs',  value : 'hlEs'}, 
			           { text : 'fbEs',  value : 'fbEs'}, 
			           { text : 'Fmin',  value : 'fmin'}, 
			           { text : 'M3000F2',  value : 'm3000F2'}, 
			           { text : 'M1500F2',  value : 'm1500F2'}, 
			           { text : 'M3000F1',  value : 'm3000F1'}, 
			           { text : 'M3000F',   value : 'm3000F'},
			           { text : 'foF2.foF1.foEs.foE',   value : "foF2,foF1,foEs,foE"},
			           { text : "P（foEs）.h'Es",   value : "M3000F2,P（foEs）,hiEs"}
			         
			           
			           ]
 parameter_array=['foF2','hlF2','foF1','hlF1','hlF','hpF','foE','hlE','foEs','hlEs','fbEs','fmin','m3000F2','m1500F2','m3000F1','m3000F']			         
 month_array=['1','2','3','4','5','6','7','8','9','10','11','12']
 /*
 * 电离曲线图的x轴数据列（小时序列）
 * */			           
parameter_chart_xAxis_hour=['00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23']
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
	var monthArry= ['Jan','Feb','Mar','Apr', 'May','Jun','Jui','Aug','Sept','Oct','Nov','Dec'];
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

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
    <title>JS读取XMLDemo</title> 

    <script language="javascript" type="text/javascript"> 


        function NewInfoAction() 
        { 
            var dom; 
            var type=1;                                         //默认为IE浏览器 
            var data = new Array();                             //存储实体的数组    
            
            if(navigator.userAgent.indexOf("MSIE")>0) 
            { 
                dom = new ActiveXObject("Microsoft.XMLDOM");    //实例化dom对象 
               
                /* 
                    这个地方很多朋友不明白，我写详细点 
                    设置异步处理 
                    本函数不需要在XML文件读取完成之前进行任何操作， 
                    因此关闭异步处理功能。 
                */ 
                dom.async = false;                              
                dom.load("a.xml");                        //加载xml文件 
            } 
            else if(isFirefox=navigator.userAgent.indexOf("Firefox") > 0) 
            { 
                type=2; 
                dom = document.implementation.createDocument("", "", null);         //火狐不支持ActiveXObject 
                dom.async = false; 
                dom.load("NewInfo.xml"); 
                /*  
                    这是火狐的另外一中加载xml的方式 
                    var oXmlHttp = new XMLHttpRequest() ; 
                    oXmlHttp.open( "GET", "test.xml", false ) ; 
                    oXmlHttp.send(null) ; 
                    //oXmlHttp.responseXML就是xml对象了。 
                */ 
            } 
            else 
            { 
                window.alert('暂不识别该浏览器!'); 
                return; 
            } 
            
            if(dom) 
            { 
                var node; 
               
                if(type == 1)   //判断是否为IE浏览器 
                { 
                alert(dom);
					alert(dom.getElementsByTagName('Title').text);
                    node = dom.documentElement.childNodes;      //这里的node大家可以理解为net中的表,方便大家理解 
                    alert('node');
                    for(var i=0;i<node.length;i++) 
                    { 
                        var title = node[i].childNodes[0].text;                 //取出i行中的字段的值，大家这样理解更方便 
                        var content = node[i].childNodes[1].text; 
                        var date = node[i].childNodes[2].text; 
                        data.push({title:title,content:content,date:date});     //在这里，我们使用使用json，把数据库存储在里面 
                    } 
                } 
                else 
                {   
                alert('type <> 1');
                    var node = dom.getElementsByTagName("News"); 
                    for(var i=0;i<node.length;i++) 

                    { 

//                        window.alert(dom.getElementsByTagName("News")[i].childNodes[1].textContent); 
//                        window.alert(dom.getElementsByTagName("News")[i].childNodes[3].textContent); 
//                        window.alert(dom.getElementsByTagName("News")[i].childNodes[5].textContent); 

                        var value = dom.getElementsByTagName("News")[i].textContent.split(' '); 
                        var title = value[4]; 
                        var content = value[8]; 
                        var date = value[12]; 
                        data.push({title:title,content:content,date:date});     //在这里，我们使用使用json，把数据库存储在里面 
                    } 
                } 
            } 
            else 
            { 
                window.alert("dom对象为空，失败了!"); 
                return; 
            } 
            
            if(data.length != 0) 
            { 
                var shtml = ''; 
                for(var i=0;i<data.length;i++) 
                { 

                    shtml += '<div>'; 
                    shtml += '<div style="float:left; background-color:Red; width:80px; height:15px">'; 
                    shtml += data[i].title; 
                    shtml += '</div>'; 
                    shtml += '<div>'; 
                    shtml += '<div style="float:left; background-color:Green;width:150px; height:15px">'; 
                    shtml += data[i].content; 
                    shtml += '</div>'; 
                    shtml += '<div style="float:left; background-color:Green;width:120px; height:15px">'; 
                    shtml += data[i].date; 
                    shtml += '</div>'; 
                    shtml += '</div>'; 
                    shtml += '</div>'; 
                    shtml += '<br/>'; 
                } 
                document.getElementById('textDiv').innerHTML = shtml; 
            } 
            else 
            { 
                document.getElementById('textDiv').innerHTML = '没有信息!'; 
            } 
        } 
    </script> 

</head> 
<body onload="NewInfoAction()"> 
<div id="textDiv" > 

</div> 
</body> 

</html> 


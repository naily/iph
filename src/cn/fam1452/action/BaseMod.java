package cn.fam1452.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class BaseMod {
	
	protected Logger log = Logger.getLogger(this.getClass()) ;  
	
	//i18n ， 通过key取得语言字符串
	protected String getMsgByKey(HttpServletRequest req , String key){
		Map<String,String> msgmap  =  msgmap = (Map<String,String>)req.getAttribute ("msg") ;
		return msgmap.get(key) ;
	}
}

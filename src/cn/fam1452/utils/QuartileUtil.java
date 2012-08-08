package cn.fam1452.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import cn.fam1452.action.bo.Pages;
import cn.fam1452.dao.pojo.*;

/**
 * 四分位数
 * 四分位数（Quartile），即统计学中，把所有数值由小到大排列并分成四等份，处于三个分割点位置的得分就是四分位数。
 * 算法实现参考维基百科：
 * http://zh.wikipedia.org/wiki/%E5%9B%9B%E5%88%86%E4%BD%8D%E6%95%B0#cite_note-0
 * Class QuartileUtil
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:Aug 8, 2012 8:48:03 PM $
 */
public class QuartileUtil<T>{
	//private <T> List<T>  srcdata ;
	
	public QuartileUtil(){}
	
	/**
	 * 统计出T中属性(有有效值)的个数
	 * @Author Derek
	 * @Date Aug 8, 2012
	 * @param list
	 */
	public Map<String, Integer> getCNT(List<T> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Object o = list.get(0) ;
		Field[] fs = getFields(o) ;
		Map<String, Integer> map = new HashMap<String, Integer> () ;
		
		for (Field f : fs) {
			String fn = f.getName() ;
			int ct = 0 ;
			for (T t : list) {
				//Object tmp = PropertyUtils.getSimpleProperty(t, f.getName()) ;
				if(null != PropertyUtils.getSimpleProperty(t, f.getName())){
					ct++ ;
				}
			}
			map.put(fn, ct) ;
			
		}
		return map ;
	}
	
	public Field[] getFields(Object o){
		Class c = o.getClass() ;
		Field[] fieldArray = c.getDeclaredFields() ;
		
		return fieldArray ;
	}
	
	public Method[] getMethods(Object o){
		Class c = o.getClass() ;
		Method[] methods = c.getDeclaredMethods();
		return methods ;
	}
	
	public List<Number> getValueArrayByField(List<T> list , String fieldName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List arry = new ArrayList() ;
		for (T t : list) {
			Object va = PropertyUtils.getSimpleProperty(t, fieldName) ;
			if(va instanceof Number){
				arry.add(va) ;
			}
		}
		Arrays.sort(arry.toArray()) ;
		return arry ;
	}
	/**
	 * 获取中位数
	 * @Author Derek
	 * @Date Aug 8, 2012
	 * @param list
	 * @return
	 */
	public Number getMed(List<Number> list){
		int t = list.size() ;
		
		Number m = t *0.5 ;
		if(m instanceof Float){
			
		}
		return m ;
	}
	
	public static void main(String[] a) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		QuartileUtil util = new QuartileUtil() ;
		
		Object o = new Log() ;
		Field[] fs =  util.getFields(o) ;
		for (Field f : fs) {
			System.out.print (f.getName() + " "); 
			System.out.println(PropertyUtils.getSimpleProperty(o, f.getName()) ); 
		}
	}
	
}

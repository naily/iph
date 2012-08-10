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

import cn.fam1452.action.bo.MetaDataBo;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.action.bo.ParameterMonthDateBo;
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
	private final double q1 = 0.25 ;
	private final double q2 = 0.5 ;
	private final double q3 = 0.75 ;
	
	public QuartileUtil(){}
	
	/**
	 * 统计出T中属性(有效值)的个数
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
	
	public static String[] filterFields(Field[] fa , String[] ft){
		if(null == fa || fa.length ==0){
			return null ;
		}
		
		List<String> list = new ArrayList<String>() ;
		for (int i = 0 ; i< fa.length ; i++) {
			String fn = fa[i].getName() ;
			if( !isExist(ft , fn) ){
				list.add(fn) ;
			}
		}
		String[] re = {} ;
		if(list.size() > 0){
			String[] array = new String[list.size()] ;
			re = list.toArray(array) ;
		}
		
		return re ;
	}
	/**
	 * 检查数组中是否包含指定的字符串
	 * @Author Derek
	 * @Date Aug 9, 2012
	 * @param list
	 * @param s
	 * @return 若找到返回true
	 */
	private static boolean isExist(String[] list , String s){
		if(null == list || list.length == 0)
			return false ;
		
		for (String string : list) {
			if(s.equals(string))
				return true ;
		}
		return false ;
	}
	
	public static Field[] getFields(Object o){
		Class c = o.getClass() ;
		Field[] fa = c.getDeclaredFields() ;
		return fa ;
	}
	
	public Method[] getMethods(Object o){
		Class c = o.getClass() ;
		Method[] methods = c.getDeclaredMethods();
		return methods ;
	}
	
	public List<Number> getValueArrayByField(List list , String fieldName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List arry = new ArrayList() ;
		for (Object t : list) {
			Object va = PropertyUtils.getSimpleProperty(t, fieldName) ;
			
			if(va instanceof String){
				String s = StringUtil.replaceLetter(va.toString()) ;
				if(StringUtil.checkNotNull(s)){
					arry.add(Double.parseDouble(s) );
				}
			}else if(va instanceof Number){
					arry.add(va) ;
			}
		}
		Arrays.sort(arry.toArray()) ;
		return arry ;
	}
	/**
	 * 获取一个一维数组的中位数
	 * @Author Derek
	 * @Date Aug 8, 2012
	 * @param list
	 * @return
	 */
	public Number getMed(List<Number> list){
		int t = list.size() ;
		
		Number med = quartile(list , q2)  ;
		
		return med ;
	}
	/**
	 * 计算四分位数
	 * @return
	 * q1 0.25 , q2 0.5, q3 0.75
	 */
	private Number quartile(List<Number> list ,double d){
		if(null == list || list.size() == 0)
			return null ;
		Number m = list.size() * d ;
		int i = m.intValue() ;
		float f = m.floatValue() ;
		Number med = 0 ;
		
		if( (f-i) > 0 ){
			//m不是整数
			//情况2: 如果 L 不是一个整数，则取下一个最近的整数。（比如 ， 则取 2 ）
			med = list.get(i) ; 
			
		}else{
			//情况1: 如果 L 是一个整数，则取 第 L 和 第 L+1 的平均值
			Number m1 = list.get(i) ; 
			Number m2 = list.get(i + 1) ; 
			med = (m1.floatValue() + m2.floatValue())/2 ;
		}
		
		return med ;
	}
	
	/**
	 * 四分位数统计实现
	 * @Author Derek
	 * @Date Aug 9, 2012
	 * @param list  
	 * @param field 排除的属性
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 */
	public QuartileBean mianCallMe(List<Object> list , String[] field) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
		if( null == list || list.size() <2){
			return null ;
		}
		
		String[] fields = this.filterFields(this.getFields(list.get(0)), field) ;
		
		Class clas = (list.get(0)).getClass() ;
		Object cnt = clas.newInstance() ;
		Object q1 = clas.newInstance() ;
		Object q2 = clas.newInstance() ;
		Object q3 = clas.newInstance() ;
		
		//Map<String , List<Number>> map = new HashMap<String, List<Number>>();
		/*
		 * */
		for(String f : fields){
			List<Number> ln = this.getValueArrayByField(list, f) ;
			//map.put(f, ln) ;
			PropertyUtils.setSimpleProperty(cnt, f, StringUtil.getNotNullStr(String.valueOf(ln.size()) )) ;
			PropertyUtils.setSimpleProperty(q1, f, StringUtil.getNotNullStr(String.valueOf(this.quartile(ln, this.q1))));
			PropertyUtils.setSimpleProperty(q2, f, StringUtil.getNotNullStr(String.valueOf(this.quartile(ln, this.q2)))) ;
			PropertyUtils.setSimpleProperty(q3, f, StringUtil.getNotNullStr(String.valueOf(this.quartile(ln, this.q3)))) ;
		}
		QuartileBean qb = new QuartileBean(cnt , q1 ,q2 ,q3) ;
//		qb.printQuartile() ;
		
		return qb ;
	}
	/**
	 * 功能：返回完整电离月报报表（每天的电离值+四分位值）
	 * @param list ：1-31天的电力参数值
	 * @param field ：排除的属性
	 * @param headTitle ：四分位数的四个标题对应的bean属性
	 * */
	public List  monthIonosphericDate(List<Object> list , String[] field,String headTitle) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
		//List rtList = list;//返回带四分位数的list
		QuartileBean quartBean = this.mianCallMe(list, field);
		//为四分位数赋标题值
		PropertyUtils.setSimpleProperty(quartBean.getQ1(),headTitle,"UQ");		
		PropertyUtils.setSimpleProperty(quartBean.getQ3(),headTitle,"LQ");
		PropertyUtils.setSimpleProperty(quartBean.getQ2(),headTitle,"MED");
		PropertyUtils.setSimpleProperty(quartBean.getCnt(),headTitle,"CNT");
		
		//quartBean.printQuartile();
		//重新组装电离月报数据
		list.add(quartBean.getQ1());
		list.add(quartBean.getQ3());
		list.add(quartBean.getQ2());
		list.add(quartBean.getCnt());

		return list;
		
	}
	
	
	public static void printList(List list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(null == list || list.size() == 0)
			return ;
		Field[] fs = QuartileUtil.getFields(list.get(0)) ;
		String[] farray = QuartileUtil.filterFields(fs, null) ;
		
		for(Object o : list){
			for(String f : farray){
				Object t = PropertyUtils.getSimpleProperty(o, f) ;
				if(null != t)
					System.out.print(t.toString() + "\t");
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] a) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		QuartileUtil util = new QuartileUtil() ;
		
		Pages o = new Pages() ;
		List<MetaDataBo> list = new ArrayList () ;
		
		for (int i = 0 ; i < 10 ; i++) {
			MetaDataBo p = new MetaDataBo() ;
			p.setAnquan ( String.valueOf(Math.round( (Math.random() *100)) )+"L") ;
			p.setContacts(String.valueOf(Math.round(Math.random() *100))) ;
			list.add(p) ;
		}
		util.printList(list) ;
		
		try {
			QuartileBean q = util.mianCallMe(list, null) ;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

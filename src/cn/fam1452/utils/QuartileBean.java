package cn.fam1452.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

public class QuartileBean {
	private Object cnt ;
	private Object q1 ;
	private Object q2 ;
	private Object q3 ;
	public QuartileBean(Object c , Object q1 , Object q2 , Object q3){
		this.cnt = c ;
		this.q1 = q1 ;
		this.q2 = q2 ;
		this.q3 = q3 ;
	}
	
	public void printQuartile(String[] fields) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(null != cnt && null != q1 && null != q2 && null != q3){
			StringBuilder csb = new StringBuilder("CNT: ") ;
			StringBuilder q1sb = new StringBuilder("Q1: ") ;
			StringBuilder q2sb = new StringBuilder("Q2: ") ;
			StringBuilder q3sb = new StringBuilder("Q3: ") ;
			for(String f : fields){
				csb.append(PropertyUtils.getSimpleProperty(cnt, f)).append("\t") ;
				
				q1sb.append(PropertyUtils.getSimpleProperty(q1, f)).append("\t") ;
				
				q2sb.append(PropertyUtils.getSimpleProperty(q2, f)).append("\t") ;
				
				q3sb.append(PropertyUtils.getSimpleProperty(q3, f)).append("\t") ;
			}
			
			System.out.println(csb);
			System.out.println(q1sb);
			System.out.println(q2sb);
			System.out.println(q3sb);
		}
	}
	
	public void printQuartile() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Field[] fs = QuartileUtil.getFields(cnt) ;
		String[] farray = QuartileUtil.filterFields(fs, null) ;
		printQuartile(farray) ;
	}

	public Object getCnt() {
		return cnt;
	}

	public void setCnt(Object cnt) {
		this.cnt = cnt;
	}

	public Object getQ1() {
		return q1;
	}

	public void setQ1(Object q1) {
		this.q1 = q1;
	}

	public Object getQ2() {
		return q2;
	}

	public void setQ2(Object q2) {
		this.q2 = q2;
	}

	public Object getQ3() {
		return q3;
	}

	public void setQ3(Object q3) {
		this.q3 = q3;
	}
}

package cn.fam1452.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import cn.fam1452.action.bo.MetaDataBo;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.action.bo.ParameteDataBo;
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
	/**
	 * 统计出T中属性(有效值)的个数
	 * @Author Derek
	 * @Date Aug 8, 2012
	 * @param list
	 */
	public Map<String, String> getCNTStr(List<T> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Object o = list.get(0) ;
		Field[] fs = getFields(o) ;
		Map<String, String> map = new HashMap<String, String> () ;
		
		for (Field f : fs) {
			String fn = f.getName() ;
			int ct = 0 ;
			for (T t : list) {
				//Object tmp = PropertyUtils.getSimpleProperty(t, f.getName()) ;
				if(null != PropertyUtils.getSimpleProperty(t, f.getName())){
					ct++ ;
				}
			}
			map.put(fn, String.valueOf(ct)) ;
			
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
				if(StringUtil.checkNotNull(s) ){
					//arry.add(Double.parseDouble(s) );
					arry.add(getFloatNum2(s) );
				}
			}else if(va instanceof Number){
					arry.add(getFloatNum2(va.toString())) ;
			}
		}
		//System.out.println(arry.toString());
		//Arrays.sort(arry.toArray()) ;
		 Collections.sort(arry);
		//System.out.println(arry.toString());
		return arry ;
	}
	/**
	 * 返回float数据，保留两位小数
	 * */
	public static float getFloatNum2(String s) {
		if (StringUtil.checkNotNull(s)) {
			s = s.trim();
			s = s.replace("-", "") ;
		}
		float f = 0f;
		try {
			BigDecimal bigDec = new BigDecimal(s);
			f = bigDec.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(s + "\t" + e.getLocalizedMessage());
		} finally {
			return f;
		}
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
		med=getFloatNum2(med.toString());
		return med ;
	}
	/**
	 * 计算四分位数
	 * @return
	 * q1 0.25 , q2 0.5, q3 0.75
	 */
	private Number quartile(List<Number> list ,double d){
		if(null == list || list.size() < 1 )
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
			/*Number m1 = list.get(i) ; 
			Number m2 = list.get(i + 1) ; */
			Number m1 = list.get(i-1) ; 
			Number m2 = list.get(i) ; 
			med = (m1.floatValue() + m2.floatValue())/2 ;
		}
		med=getFloatNum2(med.toString());
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
			if(null!=ln && ln.size()>0){
				PropertyUtils.setSimpleProperty(cnt, f, StringUtil.getNotNullStr(String.valueOf(ln.size()))) ;
				PropertyUtils.setSimpleProperty(q1, f, StringUtil.getNotNullStr(String.valueOf(this.quartile(ln, this.q1))));
				PropertyUtils.setSimpleProperty(q2, f, StringUtil.getNotNullStr(String.valueOf(this.quartile(ln, this.q2)))) ;
				PropertyUtils.setSimpleProperty(q3, f, StringUtil.getNotNullStr(String.valueOf(this.quartile(ln, this.q3)))) ;
			}else{
				PropertyUtils.setSimpleProperty(cnt, f, "0") ;
				PropertyUtils.setSimpleProperty(q1, f, "0");
				PropertyUtils.setSimpleProperty(q2, f, "0") ;
				PropertyUtils.setSimpleProperty(q3, f, "0") ;
			}
			
			
			
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
		if(null!=quartBean){
			PropertyUtils.setSimpleProperty(quartBean.getQ1(),headTitle,"LQ");		
			PropertyUtils.setSimpleProperty(quartBean.getQ3(),headTitle,"UQ");
			PropertyUtils.setSimpleProperty(quartBean.getQ2(),headTitle,"MED");
			PropertyUtils.setSimpleProperty(quartBean.getCnt(),headTitle,"CNT");
			
			//quartBean.printQuartile();
			//重新组装电离月报数据
			list.add(quartBean.getQ1());
			list.add(quartBean.getQ3());
			list.add(quartBean.getQ2());
			list.add(quartBean.getCnt());

		}
		
		return list;
		
	}
	/**
	 * 功能：返回电离月报四分位值中的中位值
	 * @param list ：1-31天的电力参数值
	 * @param field ：排除的属性
	 * @param headTitle ：四分位数的四个标题对应的bean属性
	 * */
	public Map  monthIonosphericMedDate(List<Object> list , String[] field,String headTitle) {
		QuartileBean quartBean = null;
		Map map =null;
		float[] pValue = null;
		try {
			quartBean = this.mianCallMe(list, field);
			PropertyUtils.setSimpleProperty(quartBean.getQ2(),headTitle,"MED");
			
					if(null!=quartBean){
					    map = new HashMap();
						Field[] fields = quartBean.getQ2().getClass().getDeclaredFields();
						String[] farray = QuartileUtil.filterFields(fields, field) ;
						pValue = new float[farray.length];
						for(int i=0;i<farray.length;i++){
							String filedName =farray[i];
							Object va = PropertyUtils.getSimpleProperty(quartBean.getQ2(), filedName) ;
							pValue[i]=Float.parseFloat(va.toString());
						}
						//map.put("name",paraValue);
						map.put("data", pValue);
					}							
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//为四分位数赋标题值
		
		return map;
		
	}
	/**
	 * 功能：返回某月某时刻的中位值（如：1月份 每天12点的中位值）
	 * 
	 * 多月份可以循环调用此方法（得到各个月在某时刻的电离参数中位值）
	 *
	 * */
	public float  monthIonosphericMedDateForHour(List<Object> list , String[] field,String headTitle,String hourStr) {
		QuartileBean quartBean = null;
		float pValue=0;
		try {
			quartBean = this.mianCallMe(list, field);
			PropertyUtils.setSimpleProperty(quartBean.getQ2(),headTitle,"MED");
			Object va = PropertyUtils.getSimpleProperty(quartBean.getQ2(), hourStr) ;
			pValue=Float.parseFloat(va.toString());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pValue;
		
	}
	/**
	 * 功能：返回电离月报三个四分位值
	 * @param list ：1-31天的电力参数值
	 * @param field ：排除的属性
	 * @param headTitle ：四分位数的四个标题对应的bean属性
	 * */
	@SuppressWarnings("unchecked")
	public List  monthIonosphericMedDateOne(List<Object> list , String[] field,String headTitle) {
		List medList= new ArrayList();
		QuartileBean quartBean = null;
		try {
			quartBean = this.mianCallMe(list, field);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		Field[] fields = quartBean.getQ1().getClass().getDeclaredFields();
		String[] farray = QuartileUtil.filterFields(fields, field) ;
		
		 float[] lqValue = new float[farray.length];
		 float[] medValue = new float[farray.length];
		 float[] uqValue = new float[farray.length];
		 Object lq,med,uq;
		 for(int i=0;i<farray.length;i++){
			String filedName =farray[i];
			 try {
				lq = PropertyUtils.getSimpleProperty(quartBean.getQ1(), filedName) ;
				lqValue[i]=Float.parseFloat(lq.toString());
				 med = PropertyUtils.getSimpleProperty(quartBean.getQ2(), filedName) ;			
				 medValue[i]=Float.parseFloat(med.toString());
				 uq = PropertyUtils.getSimpleProperty(quartBean.getQ3(), filedName) ;			
				 uqValue[i]=Float.parseFloat(uq.toString());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			 
		}
		Map map = new HashMap();
		map.put("name", "UQ");
		map.put("data", uqValue);		
		medList.add(map);
		map = new HashMap();
	    map.put("name", "MED");
		map.put("data", medValue);
		medList.add(map);
		map = new HashMap();
		map.put("name", "LQ");
		map.put("data", lqValue);
		medList.add(map);
		//为四分位数赋标题值
	
		return medList;
		
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
		List list = new ArrayList () ;
		
		for (int i = 0 ; i < 5 ; i++) {
			MetaDataBo p = new MetaDataBo() ;
//			p.setId(id)
//			p.setStart((int)Math.round(Math.random() *100)) ;
//			p.setLimit((int)Math.round(Math.random() *100)) ;
			p.setId(String.valueOf(Math.round(Math.random() *10))) ;
			p.setIdInfo(String.valueOf(Math.round(Math.random() *10))) ;
			list.add(p) ;
		}
		util.printList(list) ;
		
		try {
			QuartileBean q = util.mianCallMe(list, null) ;
			q.printQuartile() ;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*根据电离参数返回单位名称*/
	public static String getUnit(String ptype){
		String retValue="";
		String km="(KM)";
		String mhz="(MHZ)";
		String[] kmArry= {"hlF2","hlF1","hlF","hpF", "hlE","hlEs"};
		String[] mhzArray={"foF2","foF1","foE","foEs","fbEs","fmin"};
		            // {'m3000F2', 'M1500F2','m3000F1','m3000F'}
		for(String ptype_:kmArry){
			if(ptype.equals(ptype_)){
				retValue=km;
				break;
			}
		}
		if(StringUtil.checkNull(retValue)){
			for(String ptype_2:mhzArray){
				if(ptype.equals(ptype_2)){
					retValue=mhz;
					break;
				}
			}
		}
		
		return retValue;
	}
	/**
	 * 功能：P(foEs) 函数值 foEs>3MHz、foEs>5MHz、foEs>7MHz
	 * @param list ：1-31天的电力参数值
	 * @param field ：排除的属性
	 * @param headTitle ：四分位数的四个标题对应的bean属性
	 * h'Es ( KM )出现的次数， 即h'Es表格中的的CNT（CNT含义参见问题1）。
	 * */
	public Map  pfoes(List<Object> list , String[] field,String headTitle) {
		QuartileBean quartBean = null;
		Map map =null;
		int[] pValue = null;
		try {
			quartBean = this.mianCallMe(list, field);
			//PropertyUtils.setSimpleProperty(quartBean.getCnt(),headTitle,"CNT");
			
					if(null!=quartBean){
					    map = new HashMap();
						Field[] fields = quartBean.getCnt().getClass().getDeclaredFields();
						String[] farray = QuartileUtil.filterFields(fields, field) ;
						pValue = new int[farray.length];
						for(int i=0;i<farray.length;i++){
							String filedName =farray[i];
							Object va = PropertyUtils.getSimpleProperty(quartBean.getCnt(), filedName) ;
							pValue[i]=Integer.parseInt(va.toString());
						}
						//map.put("name",paraValue);
						map.put("data", pValue);
					}							
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//为四分位数赋标题值
		
		return map;
		
	}
	/**
	 * 功能：P(foEs) 函数值 foEs>3MHz、foEs>5MHz、foEs>7MHz
	 * @param list ：1-31天的电力参数值
	 * @param field ：排除的属性
	 * @param headTitle ：四分位数的四个标题对应的bean属性
	 * h'Es ( KM )出现的次数， 即h'Es表格中的的CNT。
	 * */
	public Map getPFoEs(List<T> list,int values) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Object o = list.get(0) ;//获取list中的对象
		Field[] fs = getFields(o) ;//获取对象字段（属性）
		Map map = new HashMap() ;
		
		int greatThen;//大于比较值的数量
		int totalNum;//有效值总数
		float ratioValue=0;//比值结果百分比(greatThen/totalNum)*100
		int[] pValue = null;
		pValue = new int[fs.length-1];//排除days属性
		for (int i=1;i<fs.length;i++) {//遍历属性
			//for (Field f : fs) {//遍历属性
			Field f =fs[i];
			//System.out.println(f.getName());
			String fn = f.getName() ;
		   
		if(StringUtil.checkNotNull(fn) && !"days".endsWith(fn)){
			    totalNum = 0 ;
				greatThen=0;
				float paraValue;
			for (T t : list) {
				
				if(null != PropertyUtils.getSimpleProperty(t, fn) && !"".equals(PropertyUtils.getSimpleProperty(t, fn)) && !" ".equals(PropertyUtils.getSimpleProperty(t, fn)) && !"days".equals(PropertyUtils.getSimpleProperty(t,fn))){
					totalNum++ ;
					//System.out.println("PropertyUtils.getSimpleProperty(t, f.getName()="+PropertyUtils.getSimpleProperty(t, f.getName()));
					String sf = StringUtil.replaceLetter(String.valueOf(PropertyUtils.getSimpleProperty(t, fn)) ) ;
					if(StringUtil.checkNotNull(sf)){
						try{
							paraValue = Float.parseFloat( sf );
						}catch(java.lang.NumberFormatException e){
							//System.out.println(sf);
							//e.printStackTrace() ;
							paraValue = 0 ;
						}
					}else{
						paraValue = 0 ;
					}
					if(paraValue > values){
						greatThen++;
					}					
				}				
			}
			if(totalNum>0){
				ratioValue=getFloatNum(greatThen/totalNum)*100;
				
			}else{
				ratioValue=0;
			}
			int intV =(int)ratioValue;
			pValue[i-1]=intV;
		}
			
			//map.put(fn, String.valueOf(ratioValue)) ;
			
		}
		map.put("data", pValue);
		return map ;
	}
	/**
	 * 返回float数据，保留两位小数
	 * */
	public static float getFloatNum(float s){
		BigDecimal  bigDec =new   BigDecimal(s); 
		float   rs   =   bigDec.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();		
		return rs;
	   }
	/**
	 * 功能：返回电离月报四分位值中的CNT
	 * @param list ：1-31天的电力参数值
	 * @param field ：排除的属性
	 * @param headTitle ：四分位数的四个标题对应的bean属性
	 * */
	public Map  monthIonosphericCntDate(List<Object> list , String[] field,String headTitle) {
		QuartileBean quartBean = null;
		Map map =null;
		int[] pValue = null;
		try {
			quartBean = this.mianCallMe(list, field);
			PropertyUtils.setSimpleProperty(quartBean.getCnt(),headTitle,"CNT");
			
					if(null!=quartBean){
					    map = new HashMap();
						Field[] fields = quartBean.getCnt().getClass().getDeclaredFields();
						String[] farray = QuartileUtil.filterFields(fields, field) ;
						pValue = new int[farray.length];
						for(int i=0;i<farray.length;i++){
							String filedName =farray[i];
							Object va = PropertyUtils.getSimpleProperty(quartBean.getCnt(), filedName) ;
							pValue[i]=Integer.parseInt(va.toString());
						}
						//map.put("name",paraValue);
						map.put("data", pValue);
					}							
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//为四分位数赋标题值
		
		return map;
		
	}
	
	public String getFushionChartData(List<ParameterMonthDateBo> list,String chartTitle){
		StringBuffer sb = new StringBuffer("<graph caption='"+chartTitle+"' canvasBgColor='FFFFFF' numDivLines='13' yAxisName='' xAxisName='' rotateNames='0' showLegend='1' showNames='1' xAxisMaxValue='23' xAxisMinValue='00' yAxisMaxValue='20' decimalPrecision='1'>");
		sb.append("<categories verticalLineColor='666666' verticalLineThickness='1'>");
		sb.append("<category name='00' x='00' showVerticalLine='1'/>");
		sb.append("<category name='01' x='01' showVerticalLine='1'/>");
		sb.append("<category name='02' x='02' showVerticalLine='1'/>");
		sb.append("<category name='03' x='03' showVerticalLine='1'/>");
		sb.append("<category name='04' x='04' showVerticalLine='1'/>");
		sb.append("<category name='05' x='05' showVerticalLine='1'/>");
		sb.append("<category name='06' x='06' showVerticalLine='1'/>");
		sb.append("<category name='07' x='07' showVerticalLine='1'/>");
		sb.append("<category name='08' x='08' showVerticalLine='1'/>");
		sb.append("<category name='09' x='09' showVerticalLine='1'/>");
		sb.append("<category name='10' x='10' showVerticalLine='1'/>");
		sb.append("<category name='11' x='11' showVerticalLine='1'/>");
		sb.append("<category name='12' x='12' showVerticalLine='1'/>");
		sb.append("<category name='13' x='13' showVerticalLine='1'/>");
		sb.append("<category name='14' x='14' showVerticalLine='1'/>");
		sb.append("<category name='15' x='15' showVerticalLine='1'/>");
		sb.append("<category name='16' x='16' showVerticalLine='1'/>");
		sb.append("<category name='17' x='17' showVerticalLine='1'/>");
		sb.append("<category name='18' x='18' showVerticalLine='1'/>");
		sb.append("<category name='19' x='19' showVerticalLine='1'/>");
		sb.append("<category name='20' x='20' showVerticalLine='1'/>");
		sb.append("<category name='21' x='21' showVerticalLine='1'/>");
		sb.append("<category name='22' x='22' showVerticalLine='1'/>");
		sb.append("<category name='23' x='23' showVerticalLine='0'/>");
		sb.append("</categories>");
		sb.append("<dataSet seriesName='' anchorSides='4' anchorRadius='4' anchorBgColor='D5FFD5' anchorBorderColor='009900' >");
/*		sb.append("<set y='2.4' x='0' />");
		sb.append("<set y='3.4' x='0' />");
		sb.append("<set y='4.4' x='1' />");
		sb.append("<set y='4.4' x='2' />");
		sb.append("<set y='4.4' x='3' />");
		sb.append("<set y='4.4' x='4' />");
		sb.append("<set y='4.4' x='5' />");
		sb.append("<set y='4.4' x='6' />");
		sb.append("<set y='4.4' x='7' />");
		sb.append("<set y='4.4' x='8' />");
		sb.append("<set y='4.4' x='9' />");
		sb.append("<set y='4.4' x='10' />");
		sb.append("<set y='4.4' x='11' />");
		sb.append("<set y='4.4' x='12' />");
		sb.append("<set y='4.4' x='13' />");
		sb.append("<set y='4.4' x='14' />");
		sb.append("<set y='4.4' x='15' />");
		sb.append("<set y='4.4' x='16' />");
		sb.append("<set y='5.4' x='21' />");
		sb.append("<set y='2.4' x='22' />");*/
		
		Object o = list.get(0) ;
		Field[] fs = getFields(o) ;
		Map<String, String> map = new HashMap<String, String> () ;
		List<Number> listV;
		for (Field f : fs) {
			String fn = f.getName() ;
			 try {
				if(!"days".equals(fn)){
					listV = getValueArrayByField(list , fn);
					String xStr = fn.replace("h", "");
					for(Number n:listV){
						sb.append("<set y='"+n.toString()+"' x='"+xStr+"' />");						
					}
					
				}
				
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sb.append("</dataSet>");  
		sb.append("</graph>");
		
	
		
		return sb.toString();
	}
}

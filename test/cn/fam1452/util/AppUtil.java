package cn.fam1452.util;

import java.util.Locale;

import org.junit.Test;

import cn.fam1452.dao.pojo.User;
import cn.fam1452.utils.StationUtil;
import cn.fam1452.utils.StringUtil;

public class AppUtil {

	@Test
	public void app(){
		String f = "abc.jpg" ;
		String s = f.substring(0, f.lastIndexOf(".") ) ;
		
		System.out.println(f.substring(f.lastIndexOf(".")));
		
		System.out.println(f.lastIndexOf("."));
		String [] arr = f.split(".") ;
		System.out.println(arr.length );
		System.out.println(s);
		
		Number f1 = 1.3 ;
		Number f2 = 1.9 ;
		
		System.out.println("intValue");
		System.out.println(f1.intValue());
		System.out.println(f2.intValue());
		
		System.out.println("floatValue");
		System.out.println(f1.doubleValue());
		System.out.println(f2.floatValue());
		
		System.out.println("longValue");
		System.out.println(f1.longValue());
		System.out.println(f2.longValue());
		
		
		String fileName = "WU430_195801010330.bmp" ;
		int i =  6 ;
		int l = fileName.lastIndexOf(".") ;
		System.out.println("K "+ fileName.substring(i, l));
		
		
		
		
		String dot = "2.3." ;
		System.out.println(dot);
		System.out.println("dot: "+ StringUtil.replaceLetter(dot));
		
	}
	
	@Test
	public void app1(){
		String dot = "abc.doc" ;
		System.out.println("dot: "+ StationUtil.removeSuffix(dot));
	}
	
	@Test
	public void aap2(){
		BeanTest b = new BeanTest() ;
		String s  = String.format("保存失败,[%1$b]该观测站ID已经存在", "ABC" , "999" ) ;
		System.out.println(s);
		System.out.println("ABC".hashCode());
		System.out.println(Integer.toHexString("ABC".hashCode()) );;
		
		
		Locale loc = Locale.getDefault() ;
		System.out.println(loc.getCountry());
	}
	@Test
	public void aap3(){
		
		
		Locale loc = Locale.getDefault() ;
		System.out.println(loc.getCountry());
		System.out.println(loc.getDisplayCountry());
		System.out.println(loc.getLanguage());
		System.out.println(loc.getVariant());
	}
	
}


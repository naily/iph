package cn.fam1452.util;

import org.junit.Test;

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
		
		
		
		
		String dot = "." ;
		System.out.println(dot);
		System.out.println("dot: "+dot.substring(1));
	}
}

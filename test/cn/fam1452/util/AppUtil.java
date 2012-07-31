package cn.fam1452.util;

import org.junit.Test;

public class AppUtil {

	@Test
	public void app(){
		String f = "abc.jpg" ;
		String s = f.substring(0, f.lastIndexOf(".") ) ;
		
		System.out.println(f.lastIndexOf("."));
		String [] arr = f.split(".") ;
		System.out.println(arr.length );
		System.out.println(s);
	}
}

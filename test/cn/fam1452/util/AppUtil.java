package cn.fam1452.util;

import org.junit.Test;

public class AppUtil {

	@Test
	public void app(){
		String f = "abc.jpg" ;
		String s = f.substring(0, f.lastIndexOf(".") ) ;
		
		System.out.println(s);
	}
}

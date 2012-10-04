package cn.fam1452.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import cn.fam1452.dao.pojo.Parameter;

public class BeanTest {
	
	@Test
	public void beanUtil(){
		Parameter p = new Parameter() ;
		try {
			BeanUtils.setProperty(p, "M3000F2", "12") ; //字段名首字母是需要小写的，如果大写将无法把值射入
			System.out.println("m3000F2: "+p.getM3000F2());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

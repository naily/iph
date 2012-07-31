package cn.fam1452.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.ContinueLoop;
import org.nutz.lang.Each;
import org.nutz.lang.ExitLoop;
import org.nutz.lang.LoopException;

import cn.fam1452.dao.pojo.Administrator;
import cn.fam1452.dao.pojo.Log;
import cn.fam1452.dao.pojo.User;
import cn.fam1452.utils.DateUtil;

@IocBean(name = "userService")
public class UserService extends Base{
	
	public Administrator queryAdmin(String name){
		return dao.fetch(Administrator.class, name) ;
	}
	
	/**
	 * 查询一个USER
	 * @param loginId
	 * @return
	 */
	public User queryUser(String loginId){
		return dao.fetch(User.class, loginId) ;
	}
	/**
	 * 所有用户
	 * @return
	 */
	public List<User> queryAllUser(){
		return dao.query(User.class, null) ;
	}
	
	
	
public Workbook exportToHSSFWorkbook(){
		
		Workbook wb = new HSSFWorkbook();
		final CreationHelper createHelper = wb.getCreationHelper();
		
		final Sheet sheet = wb.createSheet("注册账户");
		sheet.setColumnWidth( 0,   15*256);//.autoSizeColumn(0 ); // 调整第一列宽度  
        sheet.setColumnWidth( 1  , 17*256);  
        sheet.setColumnWidth( 3  , 17*256);  
        sheet.autoSizeColumn( 2 );
        
        Row row = sheet.createRow((short)1);
	    Cell cell = row.createCell(0); 
	    cell.setCellValue("登录名");
	    row.createCell(1).setCellValue("姓名");
	    row.createCell(2).setCellValue( createHelper.createRichTextString("地址"));
	    row.createCell(3).setCellValue("注册日期日期");
	    row.createCell(4).setCellValue("性别");
	    
	    //List<Log> list = this.logList() ;
	    int t = this.dao.each(User.class, null, new Each<User>(){
			public void invoke(int i, User ele, int length) throws ExitLoop, ContinueLoop, LoopException {
				// TODO Auto-generated method stub
				Row rwt = sheet.createRow(i+2);  //向后偏移2行
	    		
	    		rwt.createCell(0).setCellValue(createHelper.createRichTextString(ele.getLoginId())) ;
	    		rwt.createCell(1).setCellValue(ele.getName()) ;
	    		rwt.createCell(2).setCellValue(  ele.getAddress() ) ;
	    		rwt.createCell(3).setCellValue(DateUtil.convertDateToString(ele.getRegDate(), DateUtil.pattern2) ) ;
	    		rwt.createCell(4).setCellValue(ele.getGender()) ;
				
			}
			
		})  ;
	    if(t > 0){
	    	
	    }else{
	    	Row r1 = sheet.createRow((short)2);
	    	r1.createCell(0).setCellValue(createHelper.createRichTextString("没有数据")) ;
	    }
		
	    return wb ;
	}
}

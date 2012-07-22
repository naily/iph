package cn.fam1452.poi.hssf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.junit.Test;

import cn.fam1452.dao.DataSource;
import cn.fam1452.dao.pojo.Log;
import cn.fam1452.utils.DateUtil;

public class Test1 extends DataSource{

	@Test
	public void createex(){
		Workbook wb = new HSSFWorkbook();
		CreationHelper createHelper = wb.getCreationHelper();
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillPattern(CellStyle.NO_FILL);
		
		Sheet sheet = wb.createSheet("数据日志");
	    Sheet sheet2 = wb.createSheet("second sheet");
	    String safeName = WorkbookUtil.createSafeSheetName("[O'Brien's sales*?]"); // returns " O'Brien's sales   "
	    Sheet sheet3 = wb.createSheet(safeName);
		
	    Header header = sheet.getHeader();
	    header.setCenter("Center Header");
	    header.setLeft("Left Header");
	    header.setRight(HSSFHeader.font("Stencil-Normal", "Italic") +
	                    HSSFHeader.fontSize((short) 16) + "Right w/ Stencil-Normal Italic font and size 16");

	    sheet.setColumnWidth( 0, 15*256) ;//.autoSizeColumn(0 ); // 调整第一列宽度  
        sheet.setColumnWidth( 1  , 17*256); // 调整第二列宽度  
        sheet.setColumnWidth( 3  , 17*256); // 调整第三列宽度  
        sheet.autoSizeColumn( 2 ); // 调整第四列宽度
	    
	    // Create a row and put some cells in it. Rows are 0 based.
	    Row row = sheet.createRow((short)1);
	    // Create a cell and put a value in it.
	    Cell cell = row.createCell(0); 
	    cell.setCellValue("ID");
	    cell.setCellStyle(cellStyle) ;

	    // Or do it on one line.
	    row.createCell(1).setCellValue("表名");
	    row.createCell(2).setCellValue( createHelper.createRichTextString("操作类型"));
	    row.createCell(3).setCellValue("操作日期");
	    row.createCell(4).setCellValue("操作者");
	    
	    List<Log> list = this.logList() ;
	    Row rw ;
	    for (int i = 2 ; i < list.size() ; i++) {
			Log log = list.get(i) ;
			rw = sheet.createRow((short)i);
			
			rw.createCell(0).setCellValue(createHelper.createRichTextString(log.getId())) ;
			rw.createCell(1).setCellValue(log.getDataTable()) ;
			rw.createCell(2).setCellValue(this.convertActionType( log.getActionType() )) ;
			rw.createCell(3).setCellValue(DateUtil.convertDateToString(log.getLogDate(), DateUtil.pattern2) ) ;
			rw.createCell(4).setCellValue(log.getAdminId()) ;
			
		}
	    
		
		String bp = this.getClass().getResource("/").toString() ;
		try {
			System.out.println(bp);
			File f = new File( "workbook.xls") ; 
			FileOutputStream fileOut = new FileOutputStream(f );
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private List<Log> logList(){
		List<Log> list = this.dao.query(Log.class, null) ;
		
		return list  ;
	}
	
	private String convertActionType(String s){
		if("01".equals(s)){
			s = "录入" ;
		}else if("02".equals(s)){
			s = "编辑" ;
		}else if("03".equals(s)){
			s = "删除" ;
		}
		
		return s ;
	}
}

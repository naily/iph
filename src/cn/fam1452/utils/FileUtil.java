package cn.fam1452.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class FileUtil {

	/**
	 * @author:Derek
	 * @date:Oct 18, 2011
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * 清空一个文件目录
	 * @author:Derek
	 * @date:Oct 18, 2011
	 */
	public static void clearDirectory(String dir){
		//System.out.println("dir: "+dir);
		if(StringUtil.checkNotNull(dir)){
			File d = new File(dir);
			if(d.exists() &&  d.isDirectory()){
				 File[] fs = d.listFiles();
				 if(null != fs && fs.length > 0){
					 for (File file : fs) {
						file.delete() ;
					}
				 }
			}
		}
	}
	
	/**
	 * 复制文件
	 * @author: ygf
	 * @date: 2012/3/22
	 */
	public static boolean copyfile(String srFile, String dtFile) {
		try {
			File source = new File(srFile);
			File target = new File(dtFile);
			
			return copyWithStreams(source, target, false);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * 复制文件操作
	 * @author: ygf
	 * @date: 2012/3/22
	 */
	public static boolean copyWithStreams(File aSourceFile, File aTargetFile,
			boolean aAppend) {
		ensureTargetDirectoryExists(aTargetFile.getParentFile());
		InputStream inStream = null;
		OutputStream outStream = null;
		try {
			try {
				byte[] bucket = new byte[32 * 1024];
				inStream = new BufferedInputStream(new FileInputStream(
						aSourceFile));
				outStream = new BufferedOutputStream(new FileOutputStream(
						aTargetFile, aAppend));
				int bytesRead = 0;
				while (bytesRead != -1) {
					bytesRead = inStream.read(bucket); // -1, 0, or more
					if (bytesRead > 0) {
						outStream.write(bucket, 0, bytesRead);
					}
				}
			} finally {
				if (inStream != null)
					inStream.close();
				if (outStream != null)
					outStream.close();
			}
			return true;
		} catch (FileNotFoundException ex) {
			System.out.println("File not found: " + ex);
			return false;
		} catch (IOException ex) {
			System.out.println(ex);
			return false;
		}
	}

	/**
	 * 确保文件目存在
	 * @author: ygf
	 * @date: 2012/3/22
	 */
	public static void ensureTargetDirectoryExists(File aTargetDir) {
		if (!aTargetDir.exists()) {
			aTargetDir.mkdirs();
		}
	}
	
	/**
	 * 删除一个文件或者文件夹
	 * @Author Derek
	 * @Date Jan 19, 2013
	 */
	public static boolean deleteFile(String filepath){
		boolean b = false ;
		if(StringUtil.checkNotNull(filepath)){
			File f = new File(filepath) ;
			b = deleteFile(f) ;
		}
		
		return b ;
	}
	
	/**
	 * 删除一个文件或者文件夹
	 * @Author Derek
	 * @Date Jan 19, 2013
	 */
	public static boolean deleteFile(File file){
		boolean b = false ;
		if(null != file && file.exists()){
			b = file.delete() ;
		}
		
		return b ;
	}
	
	/**
	 * 通过一个图像绝对路径，构建BufferedImage对象
	 * @Author Derek
	 * @Date Mar 19, 2013
	 * @param fnm
	 * @return
	 */
	public static BufferedImage getImg(String fnm) {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new File(fnm));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bi;
    }
	
	/**
	 * 获取文件后缀名（带.）
	 * @param f
	 * @return
	 */
	public static String getFileSuffix(String fn){
		if(StringUtil.checkNotNull(fn)){
			int i = fn.lastIndexOf(".") ;
			if(-1 != i){
				fn = fn.substring(i) ; //取到带点的扩展名
			}
		}
		
		return fn ;
	}
}

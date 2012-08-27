package cn.fam1452.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

public class FileDownload {
	public static void main(String args[]) {
		String name = new SimpleDateFormat("yyyyMMddHHmmssSSS")
				.format(new Date());
		String path = "D:/image/";
		File dir = new File(path);
		if (!dir.exists())
			dir.mkdir();
		path += name + ".jpg";
		download(
				"http://192.168.1.2:8083/cjj//uploadImages/gongqiu/2010-03-09-1268125786187.jpg",
				path);
	}

	/**
	 * 文件下载：参数说明（网络路径，本地存储路径<绝对路径>）
	 * 
	 * @author gls
	 * @date 2011-10-18
	 * */

	public static boolean download(String strUrl, String path) {
		// System.out.println("下载地址："+strUrl+",存储地址："+path);
		// boolean optFlag=true;
		URL url = null;
		try {
			url = new URL(strUrl);
		} catch (MalformedURLException e2) {
			// e2.printStackTrace();
			return false;
		}

		InputStream is = null;
		try {
			is = url.openStream();
		} catch (IOException e1) {
			// e1.printStackTrace();
			return false;
		}

		OutputStream os = null;
		try {
			// output = new BufferedOutputStream(new FileOutputStream(aOutputFileName));
			// os = new FileOutputStream(path);
			try {

				os = new BufferedOutputStream(new FileOutputStream(path));
				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
			} finally {
				os.close();
				is.close();
			}
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			return false;
		} catch (IOException e) {
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	public static String fileDownLoad(HttpServletRequest request,
			HttpServletResponse response, String filepath) throws IOException {
		response.setContentType(CONTENT_TYPE);
		String downloadfile = request.getSession().getServletContext().getRealPath(filepath);
		if (downloadfile != null && !downloadfile.equals("")) {
			try {
				File file = new File(downloadfile);
				if (file.exists() && file.length() > 0) {
					String filename = file.getName();
					response.setContentType("application/x-msdownload");// 设置response的编码方式
					response.setContentLength((int) file.length());// 写明要下载的文件的大小
					response.setHeader("Content-Disposition",
							"attachment;filename=" + filename);// 设置附加文件名
					FileInputStream fis = new FileInputStream(file);// 读出文件到i/o流
					BufferedInputStream buff = new BufferedInputStream(fis);
					byte[] b = new byte[1024];// 相当于我们的缓存
					long k = 0;// 该值用于计算当前实际下载了多少字节
					OutputStream myout = response.getOutputStream();// 从response对象中得到输出流,准备下载
					// 开始循环下载
					while (k < file.length()) {
						int j = buff.read(b, 0, 1024);
						k += j;
						// 将b中的数据写到客户端的内存
						myout.write(b, 0, j);
					}
					// 将写入到客户端的内存的数据,刷新到磁盘
					myout.flush();
					myout.close();
					fis.close();
					return null;
				} else {
					System.err.print("file not find");
					return "file not find";
				}
			} catch (IOException e) {
				// e.printStackTrace();
				System.err.print("download excepion");
				return "download excepion";
			}
		}
		return "success";

	}
	public static long fileDownLoads(HttpServletRequest request,
			HttpServletResponse response, String filepath) throws IOException {
		response.setContentType(CONTENT_TYPE);
		String downloadfile = request.getSession().getServletContext().getRealPath(filepath);
		long k = 0;// 该值用于计算当前实际下载了多少字节
		if (downloadfile != null && !downloadfile.equals("")) {
			try {
				File file = new File(downloadfile);
				if (file.exists() && file.length() > 0) {
					String filename = file.getName();
					response.setContentType("application/x-msdownload");// 设置response的编码方式
					response.setContentLength((int) file.length());// 写明要下载的文件的大小
					response.setHeader("Content-Disposition",
							"attachment;filename=" + filename);// 设置附加文件名
					FileInputStream fis = new FileInputStream(file);// 读出文件到i/o流
					BufferedInputStream buff = new BufferedInputStream(fis);
					byte[] b = new byte[1024];// 相当于我们的缓存
					//long k = 0;// 该值用于计算当前实际下载了多少字节
					OutputStream myout = response.getOutputStream();// 从response对象中得到输出流,准备下载
					// 开始循环下载
					while (k < file.length()) {
						int j = buff.read(b, 0, 1024);
						k += j;
						// 将b中的数据写到客户端的内存
						myout.write(b, 0, j);
					}
					// 将写入到客户端的内存的数据,刷新到磁盘
					myout.flush();
					myout.close();
					fis.close();
					
				} else {
					System.err.print("file not find");
					//return "file not find";
				}
			} catch (IOException e) {
				// e.printStackTrace();
				System.err.print("download excepion");
				//return "download excepion";
			}
		}
		return k;

	}
}


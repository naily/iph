package cn.fam1452.action;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 字符集过滤
 * @author gls
 * @date 2010-11-29
 * */
public class SetUTFCharacterEncoding implements Filter {

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterchain) throws IOException, ServletException {
		//request.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");;
		filterchain.doFilter(request, response);
	}
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
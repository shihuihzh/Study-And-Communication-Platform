package com.study.platform.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

public class LocalRequestFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		String basePathNoScheme = (String) session.getAttribute("basePathNoScheme");
		if(StringUtils.isEmpty(basePathNoScheme)) {
			String path = ((HttpServletRequest)request).getContextPath();
			basePathNoScheme = "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			
		}
		
		System.out.println(((HttpServletRequest)request).getRequestURI());
		//System.out.println(((HttpServletRequest)request).getRequestURL());
		String uri = ((HttpServletRequest)request).getRequestURI();
		int fun = 0;
		if(uri.contains("/group")) {
			fun = 1;
		} else if(uri.contains("/user") || uri.contains("/u/" )) {
			fun = 2;
		}
		
		request.setAttribute("basePath", request.getScheme() + basePathNoScheme);			
		request.setAttribute("fun", fun);			
		filterChain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}

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
		
		request.setAttribute("basePath", request.getScheme() + basePathNoScheme);			
		filterChain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}

package com.study.platform.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.study.platform.dto.UploadImageForm;
import com.study.platform.pojo.User;
import com.study.platform.service.UploadImageService;

public class PreFileUploadFilter implements Filter {
	
	private UploadImageService uploadImageService;
		
	@Override
	public void destroy() {
		uploadImageService = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = ((HttpServletRequest)request);
		String savePath = httpRequest.getServletContext().getRealPath("/");
		String basePath = (String) httpRequest.getAttribute("basePath");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		JSONObject json = null;
		
		StringBuffer requestUrl = httpRequest.getRequestURL();
		//System.out.println(requestUrl);
		String fun = requestUrl.substring(requestUrl.lastIndexOf("/") +1);
		UploadImageForm uif = null;
		if(fun.equals("avatar")) {
			uif = new UploadImageForm(httpRequest.getInputStream());
			json = uploadImageService.saveAvatar(uif, user, savePath, basePath);
		}
		
		if(json != null) {
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			httpResponse.setContentType("application/json; charset=UTF-8");
			httpResponse.getWriter().print(json.toString());
			httpResponse.getWriter().close();
		} 
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		uploadImageService = (UploadImageService) WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext()).getBean("uploadImageService");
	}

}

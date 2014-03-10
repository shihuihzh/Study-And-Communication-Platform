package com.study.platform.taglib;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

public class OnTopAlertTag extends TagSupport {
	public static final String ALERT_MSG = "alertMsg";
	
	@Override
	public int doStartTag() throws JspException {
		HttpSession session = this.pageContext.getSession();
		String msg = (String) session.getAttribute(ALERT_MSG);
		if(!StringUtils.isEmpty(msg)) {
			JspWriter out = this.pageContext.getOut();
			StringBuilder sb = new StringBuilder();
			sb.append("<div id=\"msg-barStickyWrapper\">\n");
			sb.append("	<div id=\"msg-bar\" class=\"warn\">\n");
			sb.append("		<a href=\"#\" title=\"关闭\" class=\"close i-cancel msg-close right\">×</a>\n			");
			sb.append(msg);
			sb.append("\n	</div>\n");
			sb.append("</div>\n");	
			session.setAttribute(ALERT_MSG, null);
			try {
				out.print(sb.toString());
			} catch (IOException e) {
				throw new JspException(e);
			}
		}
		
		return SKIP_BODY;
	}
	
}

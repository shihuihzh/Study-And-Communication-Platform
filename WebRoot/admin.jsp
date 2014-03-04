<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
	request.setAttribute("basePath", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>测试SSH框架</title>
<script src="http://cdn.bootcss.com/jquery/2.1.0/jquery.min.js"></script>
</head>

<body>
	欢迎，<sec:authentication property="name"/><br>
	这是管理员页面<br>
	
	<% 
		Enumeration<String> names = request.getSession().getAttributeNames();
		while(names.hasMoreElements()) {
			String key = names.nextElement();
			out.print(key+"<br>");
			out.print(request.getSession().getAttribute(key)+"<br>");
		}
	 %>
	<a href="j_spring_security_logout">退出系统</a>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

</head>

<body>

	<% 
		Enumeration<String> names = request.getSession().getAttributeNames();
		while(names.hasMoreElements()) {
			String key = names.nextElement();
			out.print(key+"<br>");
			out.print(request.getSession().getAttribute(key)+"<br>");
		}
	 %>
	<form action="${basePath}api/upload/avatar" enctype="multipart/form-data" method="post">
		<table>
			<caption>登陆测试</caption>
			<tr>
				<td>用户名:</td>
				<td><input type="text" name="j_email" />
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="j_password" />
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="submit" value="登陆" /> 
					<input type="reset" value="重置" />
				</td>
			</tr>
		</table>
		<input type="file" name="avatar">
	</form>
</body></html>


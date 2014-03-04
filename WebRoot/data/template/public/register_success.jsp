<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<title>注册成功 - 学习交流社区</title>
</head>
<body id="body">

	<div class="wrap">

		<div class="min-inner session-edge session-finished">
			<h2 class="big-title">注册成功</h2>
			<p>
				欢迎您 <s:property value="nickname"/>，我们给您的邮箱 <s:property value="email"/> <br /> 发送了一封激活邮件，记得查收并确认哦
			</p>
			<p>
				<a href="${basePath }">回首页</a> 或 <a target="_blank"
					href="http://mail.<s:property value="%{email.substring(email.lastIndexOf('@')+1)}"/>">前往邮箱 &rarr;</a>
			</p>
		</div>

	</div>

</body>
</html>
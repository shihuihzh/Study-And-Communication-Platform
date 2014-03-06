<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE>
<html>
<head>

<title>${operationTitle }</title>
</head>

<body>
	<div class="wrap">

		<div class="min-inner session-edge session-finished">
			<!-- ${operationTitle } -->
			<h2 class="big-title">${operationTitle }邮件已发送</h2>
			<p>
				我们已向 ${email } 发送了一封激活邮件，<br> 请按邮件指示完成密码重置操作
			</p>
			<p>
				<a href="${basePath }">回首页</a> 或 <a target="_blank"
					href="http://mail.${fn:substring(email, fn:indexOf(email,'@')+1, fn:length(email)) }">前往邮箱 →</a>
			</p>
		</div>

	</div>
</body>
</html>

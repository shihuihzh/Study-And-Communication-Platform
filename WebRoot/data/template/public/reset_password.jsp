<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>

<title>找回密码</title>
</head>

<body>
	<div class="container wrap">

		<div class="row min-inner session-edge">
			<div class="col-xs-12 col-md-offset-1 col-md-10">


				<h2 class="big-title">密码修改</h2>
				<p class="title-description">重置${user.nickname } 的密码</p>

				<form class="session-form" action="reset_password_post" method="post">
					<p>
						<label for="password" class="hid">新密码</label> <input
							type="password" name="password" placeholder="新密码"
							class="form-control input-lg text-34" required="">
					</p>
					<p>
						<label for="confirm_password" class="hid">重复新密码</label> <input
							type="password" name="confirm_password" placeholder="重复新密码"
							class="form-control input-lg text-34 <c:if test='${error != null }'>input-error</c:if>" required="">
						<c:if test="${error != null }"><span class="text-error">${error }</span></c:if>
					</p>
					<div class="form-action">
						<input type="submit" class="btn btn-primary btn-lg btn-xl"
							value="提交">
					</div>
				</form>
			</div>
		</div>

	</div>
</body>
</html>

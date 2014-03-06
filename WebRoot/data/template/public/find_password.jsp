<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>

<title>找回密码</title>
</head>

<body>
	<div class="container wrap">

		<div class="row min-inner session-edge">
			<div class="col-xs-12 col-md-offset-1 col-md-10">

				<h2 class="big-title">密码重置</h2>
				<!-- <p class="title-description">如果您绑定了第三方帐号，可直接用第三方帐号登录</p> -->

				<form class="session-form" action="${basePath }user/find_password_post" method="post">
					<p>
						<label for="mail" class="hid">Email 地址</label> <input type="email"
							name="email" value="" placeholder="Email 地址"
							class="form-control input-lg text-34" size="49" required="">
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

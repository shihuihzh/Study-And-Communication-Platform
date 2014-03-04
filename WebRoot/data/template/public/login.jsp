<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<title>登录 - 学习交流社区</title>
</head>
<body id="body">

<div class="container wrap">
  <div class="row min-inner session-edge">
    <div class="col-xs-12 col-md-offset-1 col-md-10">
      <h2 class="big-title">登录</h2>
      <h3 class="slug">最方便的学习交流社区</h3>
      <!-- <p class="title-description">如果你有下列任何一个帐号，点击对应按钮登录，无需注册</p> -->
      <div class="sfid-login"> 或者使用本站帐号登录 ( <a href="${basePath }user/register">注册</a> 或 <a href="${basePath }user/forgot">找回密码</a> )
        <form action="${basePath }j_spring_security_check" method="post">
          <p>
            <label for="mail" class="hid">Email 地址</label>
            <input class="form-control input-lg text-34" type="email" name="j_email" placeholder="Email 地址" value="" required="">
          </p>
          <p>
            <label for="password" class="hid">登录密码</label>
            <input class="form-control input-lg text-34" type="password" name="j_password" placeholder="登录密码" required="">
          </p>
          <button class="btn btn-primary btn-lg btn-xl" type="submit">登录</button>
          <div class="remember">
            <input type="checkbox" id="remember" name="remember" value="1" checked="">
            <label for="remember">下次自动登录</label>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
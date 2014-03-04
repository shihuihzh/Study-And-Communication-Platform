<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<title>注册 - 学习交流社区</title>
</head>
<body id="body">
<div class="container wrap">
  <div class="row min-inner session-edge">
    <div class="col-xs-12 col-md-offset-1 col-md-10">
      <h2 class="big-title">注册</h2>
      <p class="title-description">您正在注册本站独立帐号，我们推荐您使用第三方帐号登录</p>
      <form class="session-form" action="${basePath }user/register_post" method="post">
        <div class="session-aside"> 如果您有下列网站帐号，可使用这些帐号<a href="${basePath}user/login">直接登录</a>，无需注册 <a style="display: block; margin-top: 6px; text-decoration: none;" href="${basePath}user/login"> <i class="i-google">Google</i> <i class="i-github">Github</i> <i class="i-weibo">新浪微博</i> <i class="i-tqq">腾讯微博</i> <i class="i-twitter">Twitter</i> <i class="i-facebook">Facebook</i> <i class="i-renren">人人</i> <i class="i-douban">豆瓣</i> </a> </div>
        <p>
          <label for="mail" class="hid">Email 地址</label>
          <input type="email" name="email" placeholder="Email 地址" value="<s:property value="email"/>" class="form-control input-lg text-34 <s:if test="errors.email != null">input-error</s:if>" required="">
	      <s:if test="errors.email != null"><span class="text-error"><s:property value="errors.email"/></span> </s:if>
        </p>     
        <p>
          <label for="password" class="hid">登录密码</label>
          <input type="password" name="password" placeholder="登录密码" class="form-control input-lg text-34 <s:if test="errors.password != null">input-error</s:if>" required="">
          <s:if test="errors.password != null"><span class="text-error"><s:property value="errors.password"/></span> </s:if>
        </p>
        <p>
          <label for="name" class="hid">常用昵称或真名</label>
          <input type="text" name="nickname" maxlength="32" placeholder="常用昵称或真名" value="<s:property value="nickname"/>" class="form-control input-lg text-34 <s:if test="errors.nickname != null">input-error</s:if>" required="">
          <s:if test="errors.nickname != null"><span class="text-error"><s:property value="errors.nickname"/></span> </s:if>
        </p>
        <div class="form-action"> <span class="left">您将同意并接受<a href="${basePath}license" target="_blank">《服务条款》</a></span>
          <input type="submit" class="btn btn-primary btn-lg btn-xl" value="注册">
        </div>
      </form>
    </div>
  </div>
</div>
<s:debug></s:debug>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html>
<head>

<title>密码和绑定账号</title>
<script src="${basePath }/js/jcrop.js"></script>
<script src="${basePath }/js/fileupload.js"></script>
<style>.jcrop-holder{text-align:left}.jcrop-vline,.jcrop-hline{font-size:0;position:absolute;background:#007349}.jcrop-vline{height:100%;width:1px!important}.jcrop-hline{width:100%;height:1px!important}.jcrop-vline.right{right:0}.jcrop-hline.bottom{bottom:0}.jcrop-handle{font-size:1px;width:7px!important;height:7px!important;border:1px #eee solid;background-color:#333}.jcrop-tracker{width:100%;height:100%}.custom .jcrop-vline,.custom .jcrop-hline{background:yellow}.custom .jcrop-handle{border-color:black;background-color:#c7bb00;-moz-border-radius:3px;-webkit-border-radius:3px}</style>
</head>

<body>

    <div id="main" class="settings col-md-9 col-md-pull-3">
        <form action="${basePath }/user/settings?tab=auth" method="post" class="session-form">
                                    <p>
                <label for="old-password" class="required">当前密码</label>
                <input name="old_password" id="old-password" type="password" class="form-control text-32 <c:if test="${error_wrong != null }">input-error</c:if> " required />
               <c:if test="${error_wrong != null }"><span class="text-error">${error_wrong }</span></c:if> 
                            </p>
                        <p>
                <label for="password" class="required">新密码</label>
                <input name="password" id="password" type="password" class="form-control text-32 <c:if test="${error_toShort != null }">input-error</c:if>" required />
                <c:if test="${error_toShort != null }"><span class="text-error">${error_toShort }</span></c:if>
                            </p>
            <p>
                <label for="confirm-password" class="required">重复新密码</label>
                <input name="confirm_password" id="confirm-password" type="password" class="form-control text-32 <c:if test="${error_notSame != null }">input-error</c:if> " required />
                <c:if test="${error_notSame != null }"><span class="text-error">${error_notSame }</span></c:if>
                            </p>

            
                        <%-- <p id="unbinded" class="bind">
                <label>未绑定</label>
                                <a class="auth-small" href="https://segmentfault.com/user/oauth/google"><i class="i-google"></i>Google</a>
                                <a class="auth-small" href="https://segmentfault.com/user/oauth/twitter"><i class="i-twitter"></i>Twitter</a>
                                <a class="auth-small" href="https://segmentfault.com/user/oauth/douban"><i class="i-douban"></i>豆瓣</a>
                                <a class="auth-small" href="https://segmentfault.com/user/oauth/weibo"><i class="i-weibo"></i>新浪微博</a>
                                <a class="auth-small" href="https://segmentfault.com/user/oauth/qq"><i class="i-qq"></i>腾讯QQ</a>
                                <a class="auth-small" href="https://segmentfault.com/user/oauth/tqq"><i class="i-tqq"></i>腾讯微博</a>
                                <a class="auth-small" href="https://segmentfault.com/user/oauth/renren"><i class="i-renren"></i>人人网</a>
                                <a class="auth-small" href="https://segmentfault.com/user/oauth/github"><i class="i-github"></i>GitHub</a>
                                <a class="auth-small" href="https://segmentfault.com/user/oauth/facebook"><i class="i-facebook"></i>Facebook</a>
                            </p> --%>
            
            
                        <div class="form-action">
                <input type="submit" class="btn btn-xl btn-primary" value="提交" />
            </div>
                    </form>
        

    </div><!-- end #main -->

</body>
</html>

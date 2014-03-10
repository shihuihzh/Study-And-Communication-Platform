<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="elf" uri="/WEB-INF/elfun.tld"%>
<%@ taglib prefix="mt" uri="/mytaglib"%>

<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">  
  <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
  <meta name="renderer" content="webkit">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="description" content="">
  <meta name="keywords" content="">

  <link rel="stylesheet" href="${basePath }assets/main.css">

  <!--[if gt IE 8]>
  <link rel="stylesheet" href="${basePath}css/ie.css" />
  <![endif]-->
  <!--[if lt IE 9]>
  <script src="${basePath}js/html5shiv.js"></script>
  <script src="${basePath}js/respond.js"></script>
  <link rel="stylesheet" href="${basePath}css/old-ie.css" />
  <![endif]-->
  <title><decorator:title default="学习交流社区"/></title> 
  <script src="${basePath }js/jquery.js"></script>
  <script src="${basePath }js/bootstrap.js"></script>
  <script src="${basePath }js/lib.js"></script>
  <script src="${basePath }js/other.js"></script>
<%-- <script src="${basePath }assets//main.js"></script>
--%>
  <decorator:head/>
</head>
<body id="body">

  <!--[if lt IE 9]>
  <div class="error chromeframe">
    您的浏览器版本 <strong>很旧很旧</strong>，为了正常地访问网站，请升级您的浏览器
    <a target="_blank" href="http://browsehappy.com">立即升级</a>
  </div>
  <![endif]-->
  <mt:onTopAlert/>
  <div class="global-nav">
    <nav class="global-nav container">
      <a href="###" id="site-nav-btn" class="visible-xs">导航</a>
      <ul class="site-nav hidden-xs">
        <li>
          <a href="${basePath}">问答</a>
        </li>
        <li>
          <a href="${basePath}blogs">博客</a>
        </li>
        <li>
          <a href="${basePath}tags">标签</a>
        </li>
        <li>
          <a href="${basePath}users">用户</a>
        </li>
        <li>
          <a href="${basePath}badges">徽章</a>
        </li>
        <li>
          <a href="${basePath}events">活动</a>
        </li>
      </ul>
      <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
        <ul class="user-nav">
          <li class="has-dropdown">
            <a href="#">撰写</a>
            <ul class="dropdown-item">
              <li>
                <a href="${basePath}ask">提出问题</a>
              </li>
              <li>
                <a href="${basePath}write">撰写博客</a>
              </li>
              <li class="hr">
                <a href="${basePath}drafts">草稿</a>
              </li>
            </ul>
          </li>
          <li class="has-dropdown">
            <a id="msg-link" href="${basePath}user/events">消息</a>
          </li>
          <li class="has-dropdown">
            <a href="#">
              <sec:authentication property="principal.nickname"/> <i class="i-arrow-s"></i>
            </a>
            <ul class="dropdown-item">
              <li>
                <a href="${basePath}u/zhanhaowong">我的主页</a>
              </li>
              <li>
                <a href="${basePath}user/settings">帐号设置</a>
              </li>
              <li class="hr">
                <a href="${basePath}user/logout">退出</a>
              </li>
            </ul>
          </li>
        </ul>
      </sec:authorize>

      <sec:authorize  ifAllGranted="ROLE_ANONYMOUS">
        <ul class="user-nav">
          <li>
            <a id="login-link" href="${basePath}user/login">登录</a>
          </li>
          <li>
            <a href="${basePath}user/register">注册</a>
          </li>
        </ul>
      </sec:authorize>
    </nav>
    <!-- end .global-nav --> </div>
  <header class="site-header">
    <div class="container inner clearfix">
      <div class="head-name">
        <a href="${basePath}users">
          <img alt="SegmentFault" src="${basePath }img/StudyFun.png"> <strong>用户</strong>
        </a>
      </div>
      <div class="head-search">
        <form id="search" action="${basePath}search">
          <input type="text" class="form-control text-27 input-search" name="q" autocomplete="off" spellcheck="false" placeholder="搜索问题，标签，用户">
          <button class="btn-search" type="submit">搜索</button>
          <input type="hidden" name="tab" value="user"></form>
      </div>
      <ul class="head-nav">
        <li>
          <a href="${basePath}users">用户排行</a>
        </li>
      </ul>
    </div>
  </header>
  <!-- end .site-header -->
    <% 
    Enumeration<String> names = request.getSession().getAttributeNames();
    while(names.hasMoreElements()) {
      String key = names.nextElement();
      out.print(key+"<br>");
      out.print(request.getSession().getAttribute(key)+"<br>");
    }
   %>
  <div class="container wrap">

		<div class="row inner edge">

			<div class="col-md-12">
				<c:choose>
					<c:when test="${tab == 'base' }"><h2 class="common-title">我的个人资料</h2></c:when>
					<c:when test="${tab == 'record' }"><h2 class="common-title">工作教育经历</h2></c:when>
					<c:when test="${tab == 'mail' }"><h2 class="common-title">Email 地址</h2></c:when>
					<c:when test="${tab == 'auth' }"><h2 class="common-title">邮件通知提醒</h2></c:when>
					<c:when test="${tab == 'notify' }"><h2 class="common-title">我的个人资料</h2></c:when>
					<c:when test="${tab == 'widget' }"><h2 class="common-title">名片生成器</h2></c:when>
					<c:otherwise><h2 class="common-title">我的个人资料</h2></c:otherwise>
				</c:choose>
				
			</div>
			
			
			
			
			
			
			<div id="secondary" class="col-md-3 col-md-push-9">
				<nav class="navlist-group navlist-group-right">
					<a class="navlist-group-item <c:if test="${tab == 'base'}">active</c:if>" href="${basePath}user/settings">我的个人资料</a> 
					<a class="navlist-group-item <c:if test="${tab == 'record'}">active</c:if>" href="${basePath}user/settings?tab=record">工作教育经历</a>
					<a class="navlist-group-item <c:if test="${tab == 'mail'}">active</c:if>" href="${basePath}user/settings?tab=mail">Email 地址</a>
					<a class="navlist-group-item <c:if test="${tab == 'auth'}">active</c:if>" href="${basePath}user/settings?tab=auth">密码和绑定帐号</a>
					<a class="navlist-group-item <c:if test="${tab == 'notify'}">active</c:if>" href="${basePath}user/settings?tab=notify">邮件通知提醒</a>
					<a class="navlist-group-item <c:if test="${tab == 'widget'}">active</c:if>" href="${basePath}user/settings?tab=widget">名片生成器</a>
				</nav>
			</div>
			<!-- end #secondary -->
			
			<decorator:body/>
			
			</div>

	</div>

  

   
  <!-- end .wrap -->
  <footer id="footer">
    <div class="container">
    <div class="copyright">
      Copyright © 2013-2014 黄展豪 仲恺农业工程学院
      <br></div>
  </div>
</footer>
<a id="backtop" class="mobi-hide hidden" href="#body">回顶部</a>
<div class="s-loading" style="display: none;">加载中</div>

<script>
(function() {
	SF.cropUpload = function(settings) {
		var settings = $.extend({
			'selector': null,
			'url': null,
			'maxSize': 2E8,
			'button': null,
			'title': null,
			'uploadChange': function() {},
			'startUpload': function() {},
			'completeUpload': function() {},
			'uploadError': function() {},
			'fallback': function() {},
			'maxWidth': 360,
			'maxHeight': 360,
			'minWidth': 240,
			'minHeight': 240,
		}, settings),
			element = $(settings.selector);
		if ($.imageUploadSupported || $.imageUploadAsBase64Supported) {
			element
				.fileUpload({
					'fileType': /^(gif|jpe?g|png|tiff?)$/i,
					'fileMaxSize': settings.maxSize,
					'type': 'POST',
					'url': settings.url,
					'allowDataInBase64': true,
					'dataType': 'json',
					'fileError': settings.uploadError,
					'beforeSend': settings.startUpload,
					'success': settings.completeUpload,
					'resizeImage': function(img, canvas, cb) {
						var api, w, h;
						if (img.width > img.height) {
							var width = Math.min(Math.max(
									img.width, settings.minWidth),
								settings.maxWidth);
							$(img).css({
								width: width,
								height: img.height * width / img.width
							});
						} else {
							var height = Math.min(
								Math.max(img.height,
									settings.minHeight),
								settings.maxHeight);
							$(img).css({
								width: img.width * height / img.height,
								height: height
							});
						}
						$('<p></p>')
							.css('text-align', 'center')
							.append(img)
							.modal({
								'title': settings.title,
								'action': settings.button,
								'auto': true,
								'onShow': function(c) {
									w = $(img)
										.innerWidth();
									h = $(img)
										.innerHeight();
									var size = parseInt(Math
										.min(w, h) * 2 / 3);
									api = $
										.Jcrop(
											img, {
												aspectRatio: 1,
												bgColor: 'whilte',
												bgOpacity: 0.4,
												setSelect: [
													parseInt((w - size) / 2),
													parseInt((h - size) / 2),
													parseInt((w + size) / 2),
													parseInt((h + size) / 2)
												]
											});
								},
								'onAction': function(c) {
									var pos = api
										.tellSelect(),
										ratio = img.width / w,
										canvas = document
											.createElement("canvas"),
										width = pos.w > 0 ? parseInt(pos.w * ratio) : img.width,
										height = pos.h > 0 ? parseInt(pos.h * ratio) : img.height;
									canvas.width = width;
									canvas.height = height;
									var ctx = canvas
										.getContext('2d');
									ctx
										.drawImage(
											img,
											pos.x != pos.x2 ? parseInt(pos.x * ratio) : 0,
											pos.y != pos.y2 ? parseInt(pos.y * ratio) : 0,
											width,
											height,
											0,
											0,
											width,
											height);
									cb(canvas);
									c.trigger('close');
								},
								'onClose': function() {
									element.val('');
								}
							});
					}
				});
		} else {
			settings.fallback();
			element.frameFileUpload({
				'url': settings.url,
				'onChange': settings.uploadChange,
				'onUpload': settings.startUpload,
				'onComplete': settings.completeUpload
			});
		}
	};
})();		
</script>
<script>
	$(document)
		.ready(
			function() {
				$('input[name=birthday]').mask('9999-99-99');
				$('#binded a.unbind')
					.click(
						function() {
							var t = $(this);
							$
								.post(
									'${basePath}api/user', {
										'do': 'unbind',
										'type': t
											.data('type'),
										'id': t
											.data('bid')
									},
									function(o) {
										if (!o.status) {
											t
												.parent()
												.remove();
											if (0 == $('#unbinded').length) {
												$(
													'<p id="unbinded" class="bind"><label>未绑定</label></p>')
													.insertAfter(
														$('#binded'));
											}
											$(
												'<a class="auth-small" href="${basePath}user/oauth/' + o.data.type + '"><i class="i-' + o.data.type + '"></i>' + o.data.name + '</a>')
												.appendTo(
													$('#unbinded'));
										}
									}, 'json');
							return false;
						});
				var slugInput = $('#setting-slug,#setting-blog-slug');
				if (slugInput.length > 0) {
					var slugInputPreview = $('#setting-slug-preview');
					setInterval(function() {
						slugInputPreview
							.html(SF.slug(slugInput.val(), '_')
								.toLowerCase());
					}, 100);
				}
				if ('upload' == SF.param('ref') && $('.change-avatar').length > 0 && $.imageUploadSupported && $.imageUploadAsBase64Supported) {
					$
						.getJSON(
							'${basePath}api/user', {
								'do': 'detectAvatar'
							},
							function(o) {
								if (!o.status) {
									$(
										'<p style="text-align: center"><img src="' + o.data[1] + '" /></p>')
										.modal({
											title: '检测到您在' + o.data[0] + '上的头像',
											action: '使用此头像',
											onAction: function(
												c) {
												$
													.post(
														'${basePath}api/upload/remoteAvatar', {
															'img': o.data[1]
														},
														function(
															o) {
															if (!o.status) {
																$(
																	'.session-aside img.avatar-128')
																	.attr(
																		'src',
																		o.data);
															} else {
																$(
																	'<p>' + o.data + '</p>')
																	.modal({
																		'title': '获取图像时出现错误'
																	});
															}
														});
												c
													.trigger('close');
											}
										});
								}
							});
				}

				function attachEvent(type, a, b, aw, bw) {
					var f = $('#setting-' + type),
						c = $(
							'#setting-' + a, f),
						p = $('#setting-' + b, f);

					function addRemoveEvent(el) {
						$('.i-cancel', el).click(function() {
							$('<p>您确认删除这条记录吗?</p>').modal({
								'title': '删除确认',
								'action': '删除',
								'data': el,
								'onAction': function(b, d) {
									var w = $(this);
									$.post('${basePath}api/user', {
										'do': 'removeInfo',
										'type': type,
										'pos': d.prevAll().length
									}, function() {
										w.trigger('close');
										d.fadeOut(function() {
											$(this).remove();
										});
									});
								}
							});
							return false;
						});
					}
					$('.user-record-list li', f).each(function() {
						addRemoveEvent($(this));
					});
					$('.btn', f)
						.click(
							function() {
								$
									.post(
										'${basePath}api/user', {
											'do': 'addInfo',
											'type': type,
											'prefix': c
												.val(),
											'suffix': p
												.val()
										},
										function(o) {
											if (!o.status) {
												addRemoveEvent($(
														'<li><a href="#" class="i-cancel close" title="删除">&times;</a><strong>' + c
														.val() + '</strong> - ' + p
														.val() + '</li>')
													.prependTo(
														$(
															'.user-record-list',
															f)));
												c
													.val('');
												p
													.val('');
											} else if (4 == o.status) {
												c
													.error('请输入' + aw);
											} else if (5 == o.status) {
												c
													.error(aw + '长度非法');
											} else if (6 == o.status) {
												c
													.error('请不要使用特殊符号');
											} else if (7 == o.status) {
												p
													.error('请输入' + bw);
											} else if (8 == o.status) {
												p
													.error(bw + '长度非法');
											} else if (9 == o.status) {
												p
													.error('请不要使用特殊符号');
											}
										});
								return false;
							});
				}
				attachEvent('employment', 'company', 'position',
					'公司名称', '职位名称');
				attachEvent('education', 'school', 'department',
					'学校名称', '专业学位');
				if (SF.cropUpload) {
					var avatar = $('.session-aside .change-avatar'),
						cancel, uploadAction, loading = $(
							'<div class="change-avatar loading hidden">上传中</div>')
							.insertAfter(avatar),
						isBlog = $('#setting-blog-name').length > 0;
					SF
						.cropUpload({
							'selector': '.session-aside .file',
							'url': isBlog ? '${basePath}api/upload/thumbnail' : '${basePath}api/upload/avatar',
							'button': isBlog ? '上传博客图标' : '上传头像',
							'title': isBlog ? '请选择合适的区域作为博客图标' : '请选择合适的区域作为头像',
							'fallback': function() {
								cancel = $(
									'<div class="change-avatar hidden"><a href="#" class="btn-m">上传</a> &nbsp; <a href="#" class="cancel">取消</a></div>')
									.insertAfter(avatar);
								$('a.cancel', cancel)
									.click(
										function() {
											$(
												'.session-aside .file')
												.val(
													'');
											cancel
												.addClass('hidden');
											avatar
												.removeClass('hidden');
											return false;
										});
								$('a.btn-m', cancel).click(
									function() {
										if (uploadAction) {
											uploadAction();
										}
									});
							},
							'uploadChange': function(upload) {
								var val = $(this).val();
								if (val.length > 0) {
									cancel
										.removeClass('hidden');
									avatar.addClass('hidden');
								}
								uploadAction = upload;
							},
							'startUpload': function() {
								if (cancel) {
									cancel.addClass('hidden');
								} else {
									avatar.addClass('hidden');
								}
								loading.removeClass('hidden');
							},
							'completeUpload': function(o) {
								avatar.removeClass('hidden');
								loading.addClass('hidden');
								if (!o.status) {
									$(
										'.session-aside img.avatar-128')
										.attr('src', o.data);
								} else {
									$('<p>' + o.data + '</p>')
										.modal({
											'title': '上传出现错误'
										});
								}
							},
							'uploadError': function() {
								$(
									'<p>请选择一张正确的图片上传, 图片尺寸不要超过 2 MB</p>')
									.modal({
										'title': '上传出现错误'
									});
							}
						});
				}
			});	
</script>
</body>
</html>
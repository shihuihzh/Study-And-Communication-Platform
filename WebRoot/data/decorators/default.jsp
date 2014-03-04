<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>

<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="description" content="">
<meta name="keywords" content="">

<link rel="stylesheet" href="${basePath }assets/main.css">
<script src="${basePath }assets/main.js"></script>
<!--[if gt IE 8]>
    <link rel="stylesheet" href="${basePath}css/ie.css" />
<![endif]-->
<!--[if lt IE 9]>
    <script src="${basePath}js/html5shiv.js"></script>
    <script src="${basePath}js/respond.js"></script>
    <link rel="stylesheet" href="${basePath}css/old-ie.css" />
<![endif]-->
<title><decorator:title default="学习交流社区"/></title>
<decorator:head/>
</head>
<body id="body">

<!--[if lt IE 9]>
    <div class="error chromeframe">您的浏览器版本<strong>很旧很旧</strong>，为了正常地访问网站，请升级您的浏览器 <a target="_blank" href="http://browsehappy.com">立即升级</a></div>
<![endif]-->

<div class="global-nav">
  <nav class="global-nav container"> <a href="###" id="site-nav-btn" class="visible-xs">导航</a>
    <ul class="site-nav hidden-xs">
      <li><a href="${basePath}">问答</a></li>
      <li><a href="${basePath}blogs">博客</a></li>
      <li><a href="${basePath}tags">标签</a></li>
      <li><a href="${basePath}users">用户</a></li>
      <li><a href="${basePath}badges">徽章</a></li>
      <li><a href="${basePath}events">活动</a></li>
    </ul>
    <ul class="user-nav">
      <li> <a id="login-link" href="${basePath}user/login">登录</a> </li>
      <li> <a href="${basePath}user/register">注册</a> </li>
    </ul>
  </nav>
  <!-- end .global-nav --> 
</div>
<header class="site-header">
  <div class="container inner clearfix">
    <div class="head-name"> <a href="${basePath}users"> <img alt="SegmentFault" src="http://s.segmentfault.com/img/logo.png?14.1.23.1"> <strong>用户</strong> </a> </div>
    <div class="head-search">
      <form id="search" action="${basePath}search">
        <input type="text" class="form-control text-27 input-search" name="q" autocomplete="off" spellcheck="false" placeholder="搜索问题，标签，用户">
        <button class="btn-search" type="submit">搜索</button>
        <input type="hidden" name="tab" value="user">
      </form>
    </div>
    <ul class="head-nav">
      <li><a href="${basePath}users">用户排行</a></li>
    </ul>
  </div>
</header>
<!-- end .site-header -->

<decorator:body/>

<!-- end .wrap -->
<footer id="footer">
  <div class="container"> 
    <!--<div class="row inner hidden-xs">
      <dl class="col-sm-2 site-link">
        <dt>网站相关</dt>
        <dd><a href="${basePath}about">关于我们</a></dd>
        <dd><a href="${basePath}tos">服务条款</a></dd>
        <dd><a href="${basePath}faq">帮助中心</a></dd>
        <dd><a href="${basePath}q/1010000000187808">编辑器语法</a></dd>
      </dl>
      <dl class="col-sm-2 site-link">
        <dt>联系合作</dt>
        <dd><a href="${basePath}link">合作伙伴</a></dd>
        <dd><a href="${basePath}press">媒体报道</a></dd>
        <dd><a href="http://0x.segmentfault.com">建议反馈</a></dd>
      </dl>
      <dl class="col-sm-2 site-link">
        <dt>常用链接</dt>
        <dd><a href="http://blog.segmentfault.com/devblog" target="_blank">开发日志</a></dd>
        <dd><a href="${basePath}mobileapp">移动应用</a></dd>
        <dd><a href="${basePath}events?category=3">黑客马拉松</a></dd>
        <dd><a href="http://yumiwan.com" target="_blank">玉米湾域名</a></dd>
      </dl>
      <dl class="col-sm-2 site-link">
        <dt>关注我们</dt>
        <dd><a href="${basePath}feeds">问答 RSS 订阅</a></dd>
        <dd><a href="http://weibo.com/segmentfault" target="_blank">新浪微博</a></dd>
        <dd><a href="http://twitter.com/segment_fault" target="_blank">Twitter</a></dd>
        <dd><a href="http://t.qq.com/segmentfault" target="_blank">腾讯微博</a></dd>
        <dd><a href="http://page.renren.com/699146294" target="_blank">人人网</a></dd>
      </dl>
      <dl class="col-sm-4 site-link" id="license">
        <dt>内容许可</dt>
        <dd>除特别说明外，用户内容均采用 <a rel="license" target="_blank" href="http://creativecommons.org/licenses/by-sa/3.0/cn/">知识共享署名-相同方式共享 3.0 中国大陆许可协议</a> 进行许可</dd>
        <dd>本站由 <a target="_blank" href="http://elinkhost.com/">Elinkhost</a> 提供 IDC 服务<br>
          <a target="_blank" href="https://www.upyun.com/?utm_source=segmentfault&amp;utm_medium=link&amp;utm_campaign=upyun&amp;md=segmentfault">又拍云</a> 提供 CDN 存储服务</dd>
      </dl>
    </div>-->
    <div class="copyright"> Copyright © 2011-2014 SegmentFault. 当前呈现版本 14.1.21 <br>
      京ICP备12004937号, 京公网安备110108008332号 </div>
  </div>
</footer>
<a id="backtop" class="mobi-hide hidden" href="#body">回顶部</a> 
<div class="s-loading" style="display: none;">加载中</div>
</body>
</html>


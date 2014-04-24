<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<title>${groupVo.group.groupName }最新问题 - 学习交流社区</title>
</head>
<body id="body">
	<div class="container wrap">

<div class="row inner edge">
    <div id="main" class="col-xs-12 col-md-9" data-current="${groupVo.group.groupId }">
                <div id="tag-detail">
            <div class="tag-detail-img">
                <img class="big-tag-img" src="${basePath }${groupVo.group.groupLogo}" alt="${groupVo.group.groupName }" />
                            </div>
            
            <h1>${groupVo.group.groupName }</h1>

            <div class="excerpt">
                <p>
                  ${groupVo.desc }              <a href="${basePath }group/show?id=${groupVo.group.groupId}&type=info">(详细...)</a>
                </p>

                
                <div class="post-action">
                                                                                                                    </div>
            </div>
        </div><!-- end #tag-detail -->
        
        <div class="tab-nav clearfix">
            <div style="margin-bottom: 10px">
                <a href="http://segmentfault.com/ask?tag=ios" class="btn btn-default btn-l sub-ask">+ 提问</a>
                <form class="sub-search" action="http://segmentfault.com/t/ios/search">
                    <label for="text" class="hid">在标签下搜索</label>
                    <input type="text" name="q" autocomplete="off" placeholder="在标签下搜索" class="form-control text-27 input-search" value="" />
                </form>
            </div>
            <nav class="sub-tab">
            	
                <a <c:if test="${empty param.order}">class="current"</c:if> href="${basePath }group/show?id=${groupVo.group.groupId}">最新的</a>
                <a <c:if test="${param.order == 'votes'}">class="current"</c:if> href="${basePath }group/show?id=${groupVo.group.groupId}&order=votes">投票最多的</a>
                <a <c:if test="${param.order == 'accepted'}">class="current"</c:if> href="${basePath }group/show?id=${groupVo.group.groupId}&order=accepted">已完结的</a>
                <a <c:if test="${param.order == 'unanswered'}">class="current"</c:if> href="${basePath }group/show?id=${groupVo.group.groupId}&order=unanswered">未回答的</a>
            </nav>
        </div>

        <div id="content">
                                <article class="post" id="q-1010000000474809">
                <div class="status">
                    <span class="answer answered" title="1 个答案">1</span>
                    <span class="vote" title="0 个投票">0</span>
                </div>
                <div class="p-summary">
                    <a class="author" data-toggle="tooltip" data-placement="bottom" rel="tooltip" href="http://segmentfault.com/u/wuzhou" title="WuZhou &bull; 170"><img class="avatar-40" src="http://sfault-avatar.b0.upaiyun.com/741/170/741170324-1030000000149872_medium40" alt="WuZhou" /></a>
                    <h2><a href="http://segmentfault.com/q/1010000000474809" title="Xcode 5在装了XVim之后文件不自动保存了，怎么恢复设置？">Xcode 5在装了XVim之后文件不自动保存了，怎么恢复设置？</a></h2>
                    <div class="meta">
                        <span class="views"><i class="i-view"></i> 70 次浏览</span>&nbsp;
                                                <ul class="meta-tags">
                            <li><i class="i-tag"></i></li>
                                                        <li>
                                <a data-tid="1040000000089442" href="http://segmentfault.com/t/ios">ios</a>,                             </li>
                                                        <li>
                                <a data-tid="1040000000089579" href="http://segmentfault.com/t/xcode">xcode</a>,                             </li>
                                                        <li>
                                <a data-tid="1040000000474807" href="http://segmentfault.com/t/xvim">xvim</a>                            </li>
                                                    </ul>&nbsp;
                                                <span class="datetime"><i class="i-time"></i> 5小时前</span>
                    </div>
                                    </div>
            </article>
            
                <div class="page-nav"><ul class="pagination"><li class="active"><a href="http://segmentfault.com/t/ios/newest?page=1">1</a></li><li><a href="http://segmentfault.com/t/ios/newest?page=2">2</a></li><li><a href="http://segmentfault.com/t/ios/newest?page=3">3</a></li><li><a href="http://segmentfault.com/t/ios/newest?page=4">4</a></li><li><a href="http://segmentfault.com/t/ios/newest?page=5">5</a></li><li class="disabled"><span>&hellip;</span></li><li><a class="next" href="http://segmentfault.com/t/ios/newest?page=2">下一页</a></li></ul></div>        </div><!-- end #content -->

    </div><!-- end #main -->
    <div id="secondary" class="col-md-3 hidden-xs hidden-sm">
        <aside class="box">
            <div class="d-action">
            	<c:choose>
            		<c:when test="${!groupVo.followed}"> <a href="###" data-tid="${groupVo.group.groupId}" class="btn btn-default btn-l follow">加关注</a></c:when>
            		<c:otherwise><a href="###" data-tid="${groupVo.group.groupId}" class="btn btn-default btn-l unfollow">取消关注</a></c:otherwise>
            	</c:choose>
                             
            </div>
                    <hr class="space" />
            <h3><a href="http://segmentfault.com/t/ios/followers">601</a> 人关注了该标签</h3>
                        <a href="http://segmentfault.com/u/wong66" data-toggle="tooltip" data-placement="bottom" rel="tooltip" title="蟑螂Wong" ><img class="avatar-24" src="http://sfault-avatar.b0.upaiyun.com/680/664/680664983-1030000000481692_tiny24" alt="蟑螂Wong" /></a>
                        <a href="http://segmentfault.com/u/deexie" data-toggle="tooltip" data-placement="bottom" rel="tooltip" title="DeeXie" ><img class="avatar-24" src="http://sfault-avatar.b0.upaiyun.com/270/535/2705356021-1030000000480304_tiny24" alt="DeeXie" /></a>
                        <a href="http://segmentfault.com/u/evlinlee" data-toggle="tooltip" data-placement="bottom" rel="tooltip" title="EvlinLee" ><img class="avatar-24" src="http://sfault-avatar.b0.upaiyun.com/811/391/811391438-1030000000480622_tiny24" alt="EvlinLee" /></a>
                        <a href="http://segmentfault.com/u/ganjindeer" data-toggle="tooltip" data-placement="bottom" rel="tooltip" title="赶紧得儿" ><img class="avatar-24" src="http://sfault-avatar.b0.upaiyun.com/879/404/879404602-1030000000479117_tiny24" alt="赶紧得儿" /></a>
                        <a href="http://segmentfault.com/u/jason_zheng" data-toggle="tooltip" data-placement="bottom" rel="tooltip" title="Jason zheng" ><img class="avatar-24" src="http://sfault-avatar.b0.upaiyun.com/245/568/2455686498-1030000000455358_tiny24" alt="Jason zheng" /></a>
                        <a href="http://segmentfault.com/u/langqixu" data-toggle="tooltip" data-placement="bottom" rel="tooltip" title="langqixu" ><img class="avatar-24" src="http://s.segmentfault.com/img/user-24.png?14.3.24.1" alt="langqixu" /></a>
                        <a href="http://segmentfault.com/u/freecoder" data-toggle="tooltip" data-placement="bottom" rel="tooltip" title="于磊" ><img class="avatar-24" src="http://sfault-avatar.b0.upaiyun.com/326/709/3267094849-1030000000478339_tiny24" alt="于磊" /></a>
                            </aside>
                <aside class="box">
           <%--  <h3>30 天贡献排名</h3>
            <ol class="ranking-list">
                            <li><a href="http://segmentfault.com/u/portwatcher"><img class="avatar-24" src="http://sfault-avatar.b0.upaiyun.com/276/980/2769807229-1030000000146176_tiny24" alt="">PortWatcher</a><span>+42 声望</span></li>
                            <li><a href="http://segmentfault.com/u/yuli"><img class="avatar-24" src="http://s.segmentfault.com/img/user-24.png?14.3.24.1" alt="">于立</a><span>+31 声望</span></li>
                            <li><a href="http://segmentfault.com/u/weakish"><img class="avatar-24" src="http://sfault-avatar.b0.upaiyun.com/266/538/2665388308-1030000000323597_tiny24" alt="">weakish</a><span>+28 声望</span></li>
                            <li><a href="http://segmentfault.com/u/coneboy"><img class="avatar-24" src="http://sfault-avatar.b0.upaiyun.com/192/400/192400139-1030000000186591_tiny24" alt="">Coneboy</a><span>+23 声望</span></li>
                            <li><a href="http://segmentfault.com/u/shiweifu"><img class="avatar-24" src="http://sfault-avatar.b0.upaiyun.com/232/607/2326074990-1030000000155579_tiny24" alt="">shiweifu</a><span>+23 声望</span></li>
                            <li><a href="http://segmentfault.com/u/yang_fang"><img class="avatar-24" src="http://sfault-avatar.b0.upaiyun.com/331/679/331679569-1030000000091746_tiny24" alt="">Yang Fang</a><span>+22 声望</span></li>
                            <li><a href="http://segmentfault.com/u/tangyunlou"><img class="avatar-24" src="http://sfault-avatar.b0.upaiyun.com/350/433/3504330111-1030000000437862_tiny24" alt="">唐云楼</a><span>+20 声望</span></li>
                            <li><a href="http://segmentfault.com/u/light"><img class="avatar-24" src="http://sfault-avatar.b0.upaiyun.com/170/973/1709735210-1030000000091460_tiny24" alt="">LIGHT</a><span>+17 声望</span></li>
                            <li><a href="http://segmentfault.com/u/edagarli"><img class="avatar-24" src="http://sfault-avatar.b0.upaiyun.com/379/516/3795165902-1030000000357089_tiny24" alt="">edagarli</a><span>+16 声望</span></li>
                            <li><a href="http://segmentfault.com/u/ryan_hoo"><img class="avatar-24" src="http://sfault-avatar.b0.upaiyun.com/302/962/3029622988-1030000000137192_tiny24" alt="">Ryan Hoo</a><span>+16 声望</span></li>
                        </ol> --%>
        </aside>
        
                <aside class="box">
           <%--  <h3>相关标签</h3>
            <ul class="tag-list show-pop-tag">
                    <li>
                <a data-tid="1040000000090209" href="http://segmentfault.com/t/objective-c" class="tag">objective-c</a>
                    </li>
        <li>
                <a data-tid="1040000000089498" href="http://segmentfault.com/t/iphone" class="tag">iphone</a>
                    </li>
        <li>
                <a data-tid="1040000000089579" href="http://segmentfault.com/t/xcode" class="tag tag-img" style="background-image: url(http://sfault-avatar.b0.upaiyun.com/942/050/942050221-i-1040000000089579_icon);">xcode</a>
                    </li>
        <li>
                <a data-tid="1040000000089658" href="http://segmentfault.com/t/android" class="tag tag-img" style="background-image: url(http://sfault-avatar.b0.upaiyun.com/676/908/676908637-i-1040000000089658_icon);">android</a>
                    </li>
        <li>
                <a data-tid="1040000000119985" href="http://segmentfault.com/t/ios6" class="tag">ios6</a>
                    </li>
        <li>
                <a data-tid="1040000000089401" href="http://segmentfault.com/t/stackoverflow" class="tag">stackoverflow</a>
                    </li>
        <li>
                <a data-tid="1040000000197235" href="http://segmentfault.com/t/%E7%BF%BB%E8%AF%91" class="tag">翻译</a>
                    </li>
        <li>
                <a data-tid="1040000000128760" href="http://segmentfault.com/t/cocoa-touch" class="tag">cocoa-touch</a>
                    </li>
        <li>
                <a data-tid="1040000000090818" href="http://segmentfault.com/t/%E5%BE%AE%E4%BF%A1" class="tag">微信</a>
                    </li>
        <li>
                <a data-tid="1040000000090505" href="http://segmentfault.com/t/webview" class="tag">webview</a>
                    </li>
                    </ul> --%>
        </aside>
            </div><!-- end #secondary -->
</div>


</div><!-- end .wrap -->
	
<script src="${basePath }js/group.js"></script>

</body>
</html>
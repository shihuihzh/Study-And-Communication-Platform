<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<title>群组导航 - 学习交流社区</title>
</head>
<body id="body">
	<div class="container wrap">

		<div class="row inner edge">
			<div class="col-xs-12">
				<h1 class="top-title">群组导航</h1>
				<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
				<a href="${basePath }group/add" class="btn-l btn btn-default" style="margin-bottom: 20px">添加群组</a>
				</sec:authorize>
			</div>
			<div class="col-sm-12 col-md-12 tag-tree">
				<section>
					<ul class="tag-ranking-list">
					<c:forEach items="${groups }" var="g">
						<li><a href="${basePath }group/show?type=main&id=${g.groupId}" class="tag tag-img" style="background-image: url(${basePath}${g.groupSmallLogo });" data-tid="${g.groupId }">${g.groupName }</a></li>
					</c:forEach>	
					</ul>
				</section>
			</div>
		</div>
	</div>
	<!-- end .wrap dd-->

</body>
</html>
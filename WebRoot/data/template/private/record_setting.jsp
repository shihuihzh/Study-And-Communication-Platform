<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html>
<head>

<title>工作教育经历</title>
<script src="${basePath }/js/jcrop.js"></script>
<script src="${basePath }/js/fileupload.js"></script>
<style>.jcrop-holder{text-align:left}.jcrop-vline,.jcrop-hline{font-size:0;position:absolute;background:#007349}.jcrop-vline{height:100%;width:1px!important}.jcrop-hline{width:100%;height:1px!important}.jcrop-vline.right{right:0}.jcrop-hline.bottom{bottom:0}.jcrop-handle{font-size:1px;width:7px!important;height:7px!important;border:1px #eee solid;background-color:#333}.jcrop-tracker{width:100%;height:100%}.custom .jcrop-vline,.custom .jcrop-hline{background:yellow}.custom .jcrop-handle{border-color:black;background-color:#c7bb00;-moz-border-radius:3px;-webkit-border-radius:3px}</style>
</head>

<body>

    <div id="main" class="settings col-md-9 col-md-pull-3">
        <form action="http://segmentfault.com/user/settings?tab=record" method="post" class="session-form">
            
            <div class="p" id="setting-employment">
                <label for="setting-company">工作经历</label>
                <div class="fix-inline-msg w-m">
                    <input name="" id="setting-company" type="text" placeholder="公司或组织名称" value="" class="form-control text-32 w-xxl" />
                </div> &nbsp;
                <div class="fix-inline-msg w-m">
                    <input name="" id="setting-position" type="text" placeholder="职位头衔" value="" class="form-control text-32 w-xxl" />
                </div> &nbsp;
                <button class="btn btn-l btn-primary">添加</button>

                <ul class="user-record-list">
                <c:forEach var="exp" items="${exps }">
                	<c:if test="${exp.type == 0 }">
                	<li><a href="#" class="i-cancel close" title="删除">×</a><strong>${exp.prefix }</strong> - ${exp.suffix }</li>
                	</c:if>
                </c:forEach>
                </ul>
            </div>
            <div class="p" id="setting-education">
                <label for="setting-school">教育经历</label>
                <div class="fix-inline-msg w-m">
                    <input name="" id="setting-school" type="text" placeholder="学校或教育机构名称" value="" class="form-control text-32 w-xxl" />
                </div> &nbsp;
                <div class="fix-inline-msg w-m">
                    <input name="" id="setting-department" type="text" placeholder="专业方向" value="" class="form-control text-32 w-xxl" />
                </div> &nbsp;
                <button class="btn btn-l btn-primary">添加</button>

                <ul class="user-record-list">
                <c:forEach var="exp" items="${exps }">
                	<c:if test="${exp.type == 1 }">
                	<li><a href="#" class="i-cancel close" title="删除">×</a><strong>${exp.prefix }</strong> - ${exp.suffix }</li>
                	</c:if>
                </c:forEach>
                </ul>
            </div>
            
            
                    </form>
        

    </div><!-- end #main -->

</body>
</html>

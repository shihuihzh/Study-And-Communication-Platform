<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html>
<head>

<title>添加群组</title>
<script type="text/javascript" charset="utf-8" src="${basePath }umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath }umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="${basePath }umeditor/lang/zh-cn/zh-cn.js"></script>
<link href="${basePath }umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
</head>

<body>
<div class="container wrap">

<div class="row inner edge">
    <div class="col-xs-12">
        <h2 class="common-title">添加群组</h2>
    </div>

<form id="ask" action="${basePath }group/add_post" method="post" enctype="multipart/form-data" onsubmit="return toSubmit()">
        <div id="edit-main" class="col-xs-12 col-md-8">
        <div id="content" class="edit-post">
            <div class="p">
                <label for="title" class="hid">名称</label>
                <input type="text" name="groupName" id="groupName" tabindex="1" value="" class="form-control input-lg text-34" autocomplete="off" spellcheck="false" placeholder="群组名称" />
            </div>

			<div class="p">
              <label>Logo</label> <input type="file" name="groupLogo" id="groupLogo">
            </div>
            <div class="p">
               <script type="text/plain" id="myEditor" style="width:630px;height:240px;"></script>
               <input type="hidden" name="groupDesc" id ="groupDesc"/>
            </div>

           
        </div><!-- end #content -->
    </div><!-- end #edit-main -->

    <div id="edit-secondary" class="col-xs-12 col-md-4">
        <aside class="warn edit-guide">
            <h3>编辑指南</h3>
            <ul>
            	 <li>尽量详尽的信息</li>
                <li>良好的排版</li>
            </ul>
        </aside>
 
        <div class="p">
            <input type="submit" class="btn btn-primary btn-lg btn-xl action w-xxl ask-q-submit" tabindex="7" value="添加群组" />
                    </div>
    </div><!-- end #edit-secondary -->
</form>
</div>

</div><!-- end .wrap -->
<script type="text/javascript">
//实例化编辑器
    var um = UM.getEditor('myEditor');
   
   
   function toSubmit() {
   		$("#groupDesc").val(UM.getEditor('myEditor').getContent());
   		return true;
   }
</script>
</body>
</html>

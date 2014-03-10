<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html>
<head>

<title>我的个人资料</title>
<script src="${basePath }/js/jcrop.js"></script>
<script src="${basePath }/js/fileupload.js"></script>
<style>
.jcrop-holder {
	text-align: left
}

.jcrop-vline,.jcrop-hline {
	font-size: 0;
	position: absolute;
	background: #007349
}

.jcrop-vline {
	height: 100%;
	width: 1px !important
}

.jcrop-hline {
	width: 100%;
	height: 1px !important
}

.jcrop-vline.right {
	right: 0
}

.jcrop-hline.bottom {
	bottom: 0
}

.jcrop-handle {
	font-size: 1px;
	width: 7px !important;
	height: 7px !important;
	border: 1px #eee solid;
	background-color: #333
}

.jcrop-tracker {
	width: 100%;
	height: 100%
}

.custom .jcrop-vline,.custom .jcrop-hline {
	background: yellow
}

.custom .jcrop-handle {
	border-color: black;
	background-color: #c7bb00;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px
}
</style>
</head>

<body>


	<div id="main" class="settings col-md-9 col-md-pull-3">
		<form action="${basePath}user/settings?tab=base" method="post"
			class="session-form">
			<s:token />
			<div class="session-aside">
				<img class="avatar-128" src="${basePath }${user.avatarHuge }"
					alt="头像" />
				<div class="change-avatar">
					<input type="file" name="avatar" class="file" /> <a href="#"
						class="btn btn-default btn-m">修改头像</a>
				</div>
				从电脑中选择图片上传, 图像大小不要超过 2 MB
			</div>
			<p>
				<label for="setting-name" class="required">称呼</label> <input
					name="nickname" id="setting-name" type="text" maxlength="32"
					placeholder="常用昵称或真名" class="form-control text-32"
					value="${user.nickname }" required />
			</p>
			<%-- <div class="p">
						<label for="setting-slug" class="required">个性网址</label>
						<div>
							<input name="slug" id="setting-slug" type="text" maxlength="32"
								placeholder="缩略名" style="width: 150px"
								class="form-control mono text-28" value="hzh" required />
						</div>
						<code class="input-desc input-preview">
							segmentfault.com/u/<strong id="setting-slug-preview">hzh</strong>
						</code>
					</div> --%>
			<p class="fix-size">
				<label>性别</label> <input name="sexual" type="radio" id="sex-none"
					value="0"
					<c:if test="${user.sexual == 0}">checked</c:if> />
				<label for="sex-none">保密</label>&nbsp;&nbsp; <input name="sexual"
					type="radio" id="sex-male" value="1"
					<c:if test="${user.sexual == 1 }">checked</c:if> /> <label
					for="sex-male">男</label> &nbsp;&nbsp; <input name="sexual"
					type="radio" id="sex-female" value="2"
					<c:if test="${user.sexual == 2 }">checked</c:if> /> <label
					for="sex-female">女</label>
			</p>
			<p>
				<label for="setting-birthday">生日</label> <input name="birthday"
					id="setting-birthday" type="text" placeholder="格式 YYYY-MM-DD"
					value="${user.birthday }" class="form-control text-32" />
			</p>
			<p class="fix-size">
				<label for="setting-city">所在城市</label> <select name="stayNow"
					id="setting-city">
					<option value="86-010" selected>北京</option>
					<option value="86-021">上海</option>
					<option value="86-022">天津</option>
					<option value="86-023">重庆</option>
					<option value="852">香港</option>
					<option value="853">澳门</option>
					<option value="886">台湾</option>
					<option value="65">新加坡</option>
					<optgroup label="河北">
						<option value="86-0310">邯郸</option>
						<option value="86-0311">石家庄</option>
						<option value="86-0312">保定</option>
						<option value="86-0313">张家口</option>
						<option value="86-0314">承德</option>
						<option value="86-0315">唐山</option>
						<option value="86-0316">廊坊</option>
						<option value="86-0317">沧州</option>
						<option value="86-0318">衡水</option>
						<option value="86-0319">邢台</option>
						<option value="86-0335">秦皇岛</option>
					</optgroup>
					<optgroup label="浙江">
						<option value="86-0570">衢州</option>
						<option value="86-0571">杭州</option>
						<option value="86-0572">湖州</option>
						<option value="86-0573">嘉兴</option>
						<option value="86-0574">宁波</option>
						<option value="86-0575">绍兴</option>
						<option value="86-0576">台州</option>
						<option value="86-0577">温州</option>
						<option value="86-0578">丽水</option>
						<option value="86-0579">金华</option>
						<option value="86-0580">舟山</option>
					</optgroup>
					<optgroup label="辽宁">
						<option value="86-024">沈阳</option>
						<option value="86-0410">铁岭</option>
						<option value="86-0411">大连</option>
						<option value="86-0412">鞍山</option>
						<option value="86-0413">抚顺</option>
						<option value="86-0414">本溪</option>
						<option value="86-0415">丹东</option>
						<option value="86-0416">锦州</option>
						<option value="86-0417">营口</option>
						<option value="86-0418">阜新</option>
						<option value="86-0419">辽阳</option>
						<option value="86-0421">朝阳</option>
						<option value="86-0427">盘锦</option>
						<option value="86-0429">葫芦岛</option>
					</optgroup>
					<optgroup label="湖北">
						<option value="86-027">武汉</option>
						<option value="86-0710">襄城</option>
						<option value="86-0711">鄂州</option>
						<option value="86-0712">孝感</option>
						<option value="86-0713">黄州</option>
						<option value="86-0714">黄石</option>
						<option value="86-0715">咸宁</option>
						<option value="86-0716">荆沙</option>
						<option value="86-0717">宜昌</option>
						<option value="86-0718">恩施</option>
						<option value="86-0719">十堰</option>
						<option value="86-0722">随枣</option>
						<option value="86-0724">荆门</option>
						<option value="86-0728">江汉</option>
					</optgroup>
					<optgroup label="江苏">
						<option value="86-025">南京</option>
						<option value="86-0510">无锡</option>
						<option value="86-0511">镇江</option>
						<option value="86-0512">苏州</option>
						<option value="86-0513">南通</option>
						<option value="86-0514">扬州</option>
						<option value="86-0515">盐城</option>
						<option value="86-0516">徐州</option>
						<option value="86-0517">淮安</option>
						<option value="86-0518">连云港</option>
						<option value="86-0519">常州</option>
						<option value="86-0523">泰州</option>
					</optgroup>
					<optgroup label="内蒙古">
						<option value="86-0470">海拉尔</option>
						<option value="86-0471">呼和浩特</option>
						<option value="86-0472">包头</option>
						<option value="86-0473">乌海</option>
						<option value="86-0474">集宁</option>
						<option value="86-0475">通辽</option>
						<option value="86-0476">赤峰</option>
						<option value="86-0477">东胜</option>
						<option value="86-0478">临河</option>
						<option value="86-0479">锡林浩特</option>
						<option value="86-0482">乌兰浩特</option>
						<option value="86-0483">阿拉善左旗</option>
					</optgroup>
					<optgroup label="江西">
						<option value="86-0790">新余</option>
						<option value="86-0791">南昌</option>
						<option value="86-0792">九江</option>
						<option value="86-0793">上饶</option>
						<option value="86-0794">临川</option>
						<option value="86-0795">宜春</option>
						<option value="86-0796">吉安</option>
						<option value="86-0797">赣州</option>
						<option value="86-0798">景德镇</option>
						<option value="86-0799">萍乡</option>
						<option value="86-0701">鹰潭</option>
					</optgroup>
					<optgroup label="山西">
						<option value="86-0350">忻州</option>
						<option value="86-0351">太原</option>
						<option value="86-0352">大同</option>
						<option value="86-0353">阳泉</option>
						<option value="86-0354">榆次</option>
						<option value="86-0355">长治</option>
						<option value="86-0356">晋城</option>
						<option value="86-0357">临汾</option>
						<option value="86-0358">离石</option>
						<option value="86-0359">运城</option>
					</optgroup>
					<optgroup label="甘肃">
						<option value="86-0930">临夏</option>
						<option value="86-0931">兰州</option>
						<option value="86-0932">定西</option>
						<option value="86-0933">平凉</option>
						<option value="86-0934">西峰</option>
						<option value="86-0935">武威</option>
						<option value="86-0936">张掖</option>
						<option value="86-0937">酒泉</option>
						<option value="86-0938">天水</option>
						<option value="86-0941">甘南州</option>
						<option value="86-0943">白银</option>
					</optgroup>
					<optgroup label="山东">
						<option value="86-0530">菏泽</option>
						<option value="86-0531">济南</option>
						<option value="86-0532">青岛</option>
						<option value="86-0533">淄博</option>
						<option value="86-0534">德州</option>
						<option value="86-0535">烟台</option>
						<option value="86-0536">淮坊</option>
						<option value="86-0537">济宁</option>
						<option value="86-0538">泰安</option>
						<option value="86-0539">临沂</option>
					</optgroup>
					<optgroup label="黑龙江">
						<option value="86-0450">阿城</option>
						<option value="86-0451">哈尔滨</option>
						<option value="86-0452">齐齐哈尔</option>
						<option value="86-0453">牡丹江</option>
						<option value="86-0454">佳木斯</option>
						<option value="86-0455">绥化</option>
						<option value="86-0456">黑河</option>
						<option value="86-0457">加格达奇</option>
						<option value="86-0458">伊春</option>
						<option value="86-0459">大庆</option>
					</optgroup>
					<optgroup label="福建">
						<option value="86-0591">福州</option>
						<option value="86-0592">厦门</option>
						<option value="86-0593">宁德</option>
						<option value="86-0594">莆田</option>
						<option value="86-0595">晋江</option>
						<option value="86-0596">漳州</option>
						<option value="86-0597">龙岩</option>
						<option value="86-0598">三明</option>
						<option value="86-0599">南平</option>
					</optgroup>
					<optgroup label="广东">
						<option value="86-020">广州</option>
						<option value="86-0751">韶关</option>
						<option value="86-0752">惠州</option>
						<option value="86-0753">梅州</option>
						<option value="86-0754">汕头</option>
						<option value="86-0755">深圳</option>
						<option value="86-0756">珠海</option>
						<option value="86-0757">佛山</option>
						<option value="86-0758">肇庆</option>
						<option value="86-0759">湛江</option>
						<option value="86-0760">中山</option>
						<option value="86-0762">河源</option>
						<option value="86-0763">清远</option>
						<option value="86-0765">顺德</option>
						<option value="86-0766">云浮</option>
						<option value="86-0768">潮州</option>
						<option value="86-0769">东莞</option>
						<option value="86-0660">汕尾</option>
						<option value="86-0661">潮阳</option>
						<option value="86-0662">阳江</option>
						<option value="86-0663">揭西</option>
					</optgroup>
					<optgroup label="四川">
						<option value="86-028">成都</option>
						<option value="86-0810">涪陵</option>
						<option value="86-0811">重庆</option>
						<option value="86-0812">攀枝花</option>
						<option value="86-0813">自贡</option>
						<option value="86-0814">永川</option>
						<option value="86-0816">绵阳</option>
						<option value="86-0817">南充</option>
						<option value="86-0818">达县</option>
						<option value="86-0819">万县</option>
						<option value="86-0825">遂宁</option>
						<option value="86-0826">广安</option>
						<option value="86-0827">巴中</option>
						<option value="86-0830">泸州</option>
						<option value="86-0831">宜宾</option>
						<option value="86-0832">内江</option>
						<option value="86-0833">乐山</option>
						<option value="86-0834">西昌</option>
						<option value="86-0835">雅安</option>
						<option value="86-0836">康定</option>
						<option value="86-0837">马尔康</option>
						<option value="86-0838">德阳</option>
						<option value="86-0839">广元</option>
						<option value="86-0840">泸州</option>
					</optgroup>
					<optgroup label="湖南">
						<option value="86-0730">岳阳</option>
						<option value="86-0731">长沙</option>
						<option value="86-0732">湘潭</option>
						<option value="86-0733">株州</option>
						<option value="86-0734">衡阳</option>
						<option value="86-0735">郴州</option>
						<option value="86-0736">常德</option>
						<option value="86-0737">益阳</option>
						<option value="86-0738">娄底</option>
						<option value="86-0739">邵阳</option>
						<option value="86-0743">吉首</option>
						<option value="86-0744">张家界</option>
						<option value="86-0745">怀化</option>
						<option value="86-0746">永州冷</option>
					</optgroup>
					<optgroup label="河南">
						<option value="86-0370">商丘</option>
						<option value="86-0371">郑州</option>
						<option value="86-0372">安阳</option>
						<option value="86-0373">新乡</option>
						<option value="86-0374">许昌</option>
						<option value="86-0375">平顶山</option>
						<option value="86-0376">信阳</option>
						<option value="86-0377">南阳</option>
						<option value="86-0378">开封</option>
						<option value="86-0379">洛阳</option>
						<option value="86-0391">焦作</option>
						<option value="86-0392">鹤壁</option>
						<option value="86-0393">濮阳</option>
						<option value="86-0394">周口</option>
						<option value="86-0395">漯河</option>
						<option value="86-0396">驻马店</option>
						<option value="86-0398">三门峡</option>
					</optgroup>
					<optgroup label="云南">
						<option value="86-0870">昭通</option>
						<option value="86-0871">昆明</option>
						<option value="86-0872">大理</option>
						<option value="86-0873">个旧</option>
						<option value="86-0874">曲靖</option>
						<option value="86-0875">保山</option>
						<option value="86-0876">文山</option>
						<option value="86-0877">玉溪</option>
						<option value="86-0878">楚雄</option>
						<option value="86-0879">思茅</option>
						<option value="86-0691">景洪</option>
						<option value="86-0692">潞西</option>
						<option value="86-0881">东川</option>
						<option value="86-0883">临沧</option>
						<option value="86-0886">六库</option>
						<option value="86-0887">中甸</option>
						<option value="86-0888">丽江</option>
					</optgroup>
					<optgroup label="安徽">
						<option value="86-0550">滁州</option>
						<option value="86-0551">合肥</option>
						<option value="86-0552">蚌埠</option>
						<option value="86-0553">芜湖</option>
						<option value="86-0554">淮南</option>
						<option value="86-0555">马鞍山</option>
						<option value="86-0556">安庆</option>
						<option value="86-0557">宿州</option>
						<option value="86-0558">阜阳</option>
						<option value="86-0559">黄山</option>
						<option value="86-0561">淮北</option>
						<option value="86-0562">铜陵</option>
						<option value="86-0563">宣城</option>
						<option value="86-0564">六安</option>
						<option value="86-0565">巢湖</option>
						<option value="86-0566">贵池</option>
					</optgroup>
					<optgroup label="宁夏">
						<option value="86-0951">银川</option>
						<option value="86-0952">石嘴山</option>
						<option value="86-0953">吴忠</option>
						<option value="86-0954">固原</option>
					</optgroup>
					<optgroup label="吉林">
						<option value="86-0431">长春</option>
						<option value="86-0432">吉林</option>
						<option value="86-0433">延吉</option>
						<option value="86-0434">四平</option>
						<option value="86-0435">通化</option>
						<option value="86-0436">白城</option>
						<option value="86-0437">辽源</option>
						<option value="86-0438">松原</option>
						<option value="86-0439">浑江</option>
						<option value="86-0440">珲春</option>
					</optgroup>
					<optgroup label="广西">
						<option value="86-0770">防城港</option>
						<option value="86-0771">南宁</option>
						<option value="86-0772">柳州</option>
						<option value="86-0773">桂林</option>
						<option value="86-0774">梧州</option>
						<option value="86-0775">玉林</option>
						<option value="86-0776">百色</option>
						<option value="86-0777">钦州</option>
						<option value="86-0778">河池</option>
						<option value="86-0779">北海</option>
					</optgroup>
					<optgroup label="贵州">
						<option value="86-0851">贵阳</option>
						<option value="86-0852">遵义</option>
						<option value="86-0853">安顺</option>
						<option value="86-0854">都均</option>
						<option value="86-0855">凯里</option>
						<option value="86-0856">铜仁</option>
						<option value="86-0857">毕节</option>
						<option value="86-0858">六盘水</option>
						<option value="86-0859">兴义</option>
					</optgroup>
					<optgroup label="陕西">
						<option value="86-029">西安</option>
						<option value="86-0910">咸阳</option>
						<option value="86-0911">延安</option>
						<option value="86-0912">榆林</option>
						<option value="86-0913">渭南</option>
						<option value="86-0914">商洛</option>
						<option value="86-0915">安康</option>
						<option value="86-0916">汉中</option>
						<option value="86-0917">宝鸡</option>
						<option value="86-0919">铜川</option>
					</optgroup>
					<optgroup label="青海">
						<option value="86-0971">西宁</option>
						<option value="86-0972">海东</option>
						<option value="86-0973">同仁</option>
						<option value="86-0974">共和</option>
						<option value="86-0975">玛沁</option>
						<option value="86-0976">玉树</option>
						<option value="86-0977">德令哈</option>
					</optgroup>
					<optgroup label="海南">
						<option value="86-0890">儋州</option>
						<option value="86-0898">海口</option>
						<option value="86-0899">三亚</option>
					</optgroup>
					<optgroup label="西藏">
						<option value="86-0891">拉萨</option>
						<option value="86-0892">日喀则</option>
						<option value="86-0893">山南</option>
					</optgroup>
					<optgroup label="新疆">
						<option value="86-0991">乌鲁木齐</option>
						<option value="86-0990">克拉玛依</option>
						<option value="86-0995">吐鲁番</option>
						<option value="86-0902">哈密</option>
						<option value="86-0994">昌吉</option>
						<option value="86-0901">塔城</option>
						<option value="86-0903">和田</option>
						<option value="86-0906">阿勒泰</option>
						<option value="86-0908">阿图什</option>
						<option value="86-0909">博乐</option>
						<option value="86-0992">奎屯</option>
						<option value="86-0993">石河子</option>
						<option value="86-0996">库尔勒</option>
						<option value="86-0997">阿克苏</option>
						<option value="86-0998">喀什</option>
						<option value="86-0999">伊宁</option>
					</optgroup>
				</select>
			</p>

			<%-- <div class="p">
						<label for="setting-address">通讯地址</label>
						<div>
							<input name="address" id="setting-address" type="text"
								maxlength="32" placeholder="详细通信地址" class="form-control text-32"
								value="" />
						</div>
						<span class="input-desc">此地址将用于寄送纪念品以及活动报名使用, 不会公开给第三方</span>
					</div> --%>
			<p class="fix-size">
				<label for="setting-city">职业</label> 
				<select name="occupation" id="occupation">
					<option value="0" <c:if test="${user.occupation == 0}">selected</c:if> >学生</option>
					<option value="1" <c:if test="${user.occupation == 1}">selected</c:if> >教师</option>
					<option value="2" <c:if test="${user.occupation == 2}">selected</c:if> >其他</option>
				</select>
			</p>
			<p>
				<label for="setting-homepage">个人网站</label> <input
					name="personalSite" id="setting-homepage" type="url"
					placeholder="http://example.com" value="${user.personalSite }"
					class="form-control mono text-32" />
			</p>
			<p>
				<label for="setting-description">自我简介</label>
				<textarea name="selfIntroduction" id="setting-description"
					class="form-control textarea-14" rows="4">${user.selfIntroduction }</textarea>
			</p>


			<div class="form-action">
				<input type="submit" class="btn btn-xl btn-primary" value="提交" />
			</div>
		</form>


	</div>
	<!-- end #main -->


	<script type="text/javascript">
		$(document).ready(function() {
			$("option").each(function(i) {
				if ($(this).val() == "${user.stayNow}") {
					$(this).attr("selected", "selected");
				} else {
					$(this).removeAttr("checked");
				}
			});

		});
	</script>
	
</body>
</html>

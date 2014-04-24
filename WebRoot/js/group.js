$(document)
		.ready(
				function() {
					$('.d-action a')
							.click(
									function() {
										var url = 'http://localhost:8080/Study_And_Communication_Platform/api/group', t = $(this);
										$
												.post(
														url,
														{
															'do' : 'follow',
															'id' : t
																	.data('tid'),
															'cancel' : t
																	.hasClass('unfollow') ? 1
																	: 0
														},
														function(o) {
															if (!o.status) {
																if (t
																		.hasClass('unfollow')) {
																	$('#main')
																			.trigger(
																					'unfollow',
																					[ o.data.id ]);
																} else {
																	$('#main')
																			.trigger(
																					'follow',
																					[ o.data.id ]);
																}
															}
														}, 'json');
										return false;
									});
					$('#main')
							.bind(
									{
										'follow' : function(e, tagId) {
											if ($(this).data('current') == tagId) {
												$('.d-action a').removeClass(
														'follow').addClass(
														'unfollow')
														.html('取消关注');
											}
										},
										'unfollow' : function(e, tagId) {
											if ($(this).data('current') == tagId) {
												$('.d-action a').removeClass(
														'unfollow').addClass(
														'follow').html('加关注');
											}
										}
									});
					$('#tag-detail .post-action .delete')
							.click(
									function() {
										$(
												'<p>您确认要删除群组「<strong>'
														+ $(this).data('tname')
														+ '</strong>」吗?</p>')
												.modal(
														{
															'title' : '删除确认',
															'action' : '删除',
															'data' : $(this)
																	.data('tid'),
															'onAction' : function(
																	c, data) {
																$
																		.post(
																				'http://localhost:8080/Study_And_Communication_Platform/api/group',
																				{
																					'do' : 'delete',
																					'id' : data
																				},
																				function(
																						o) {
																					if (!o.status) {
																						window.location.href = 'http://segmentfault.com/';
																					}
																				},
																				'json');
															}
														});
										return false;
									});
					$('#tag-detail .post-action .merge')
							.click(
									function() {
										var cancel = 0, id = $(this)
												.data('tid');
										function tagMerge(merge) {
											$
													.post(
															'http://localhost:8080/Study_And_Communication_Platform/api/group',
															{
																'do' : 'merge',
																'id' : id,
																'merge' : merge
																		.val(),
																'cancel' : cancel
															},
															function(o) {
																if (!o.status) {
																	window.location
																			.reload();
																} else if (4 == o.status) {
																	merge
																			.error('请填写群组');
																} else if (5 == o.status) {
																	merge
																			.error('群组不存在');
																} else if (6 == o.status) {
																	merge
																			.error('此群组不在别名列表中');
																} else if (7 == o.status) {
																	merge
																			.error('此群组本身存在别名, 不能循环设置');
																}
															}, 'json');
										}
										$(
												'<p><input class="form-control text-34" name="name" placeholder="群组名称" style="width: 180px" /> '
														+ '<input class="btn-xl merge" type="button" value="设为别称" /> '
														+ '<input class="btn-xl unmerge" type="button" value="解除" /></p>')
												.modal(
														{
															'title' : '群组别称',
															'onShow' : function(
																	c) {
																var name = $(
																		'input[name=name]',
																		c);
																name
																		.placeholder();
																$('.merge', c)
																		.click(
																				function() {
																					cancel = 0;
																					tagMerge(name);
																				});
																$('.unmerge', c)
																		.click(
																				function() {
																					cancel = 1;
																					tagMerge(name);
																				});
															}
														});
										return false;
									});
					$('#tag-detail .post-action .upload')
							.click(
									function() {
										var t = $(this), iconUrl = t
												.data('icon'), tagId = t
												.data('tid');
										$(
												'<p id="tab-upload" class="file-upload">'
														+ '<input type="file" name="icon" class="file" /><input type="text" class="text-28" readonly />'
														+ ' <a href="#" class="btn-m">选择图标</a>'
														+ '<br /><br />图标会自动缩放为16x16格式'
														+ (iconUrl ? ' (原图标: <img width="16" height="16" style="margin-bottom: -3px" src="'
																+ iconUrl
																+ '" /> )'
																: '') + '</p>')
												.modal(
														{
															'title' : '上传图标',
															'action' : '上传',
															'onShow' : function(
																	c) {
																$('.file', c)
																		.frameFileUpload(
																				{
																					'url' : 'http://segmentfault.com/api/upload/icon?id='
																							+ tagId,
																					'trigger' : $(
																							'.pop-foot .action',
																							c),
																					'onChange' : function() {
																						var parts = $(
																								this)
																								.val()
																								.split(
																										/(\/|\\)/);
																						fileName = parts[parts.length - 1];
																						$(
																								'#tab-upload .text-28',
																								c)
																								.val(
																										fileName);
																					},
																					'onUpload' : function() {
																						$(
																								'#tab-upload .text-28',
																								c)
																								.addClass(
																										'loading');
																					},
																					'onComplete' : function(
																							o) {
																						$(
																								'#tab-upload .text-28',
																								c)
																								.removeClass(
																										'loading');
																						if (!o.status) {
																							t
																									.data(
																											'icon',
																											o.data);
																							c
																									.trigger('close');
																						} else {
																							$(
																									'#tab-upload .text-28',
																									c)
																									.error(
																											o.data);
																						}
																					}
																				});
															}
														});
										return false;
									});
					var usersMore = $('.stream-list .btn-loadmore'), usersPage = 2;
					if (usersMore.length > 0) {
						usersMore
								.click(function() {
									var $this = $(this);
									$
											.getJSON(
													'http://localhost:8080/Study_And_Communication_Platform/api/group',
													{
														'do' : 'listFollowers',
														'page' : usersPage,
														'id' : $(this).data(
																'tid')
													},
													function(o) {
														if (!o.status) {
															if (o.data.length > 0) {
																for (var i = 0; i < o.data.length; i++) {
																	var u = o.data[i], html = '<article id="u-'
																			+ u.id
																			+ '" class="post">';
																	html += '<a class="stream-img" href="'
																			+ u.url
																			+ '">'
																			+ '<img class="avatar-40" src="'
																			+ u.avatarUrl
																			+ '" alt="'
																			+ u.name
																			+ '" /></a><div class="p-summary">'
																			+ '<h2><a href="'
																			+ u.url
																			+ '" title="'
																			+ u.name
																			+ '">'
																			+ u.name
																			+ '</a></h2>';
																	if (u.excerpt) {
																		html += '<div class="p-excerpt"><p>'
																				+ u.excerpt
																				+ '</p></div>';
																	}
																	html += '<div class="meta">'
																			+ u.rankWord
																			+ ' 声望 &bull; '
																			+ u.awards
																			+ ' 徽章</div></div></article>';
																	var li = $(
																			html)
																			.insertBefore(
																					usersMore);
																}
															}
															if (o.data.length < 15) {
																usersMore
																		.remove();
															}
															usersPage++;
														}
													}).always(function() {
												$this.button('reset');
											});
									return false;
								});
					}
					if (SF.cropUpload) {
						var tagThumbnail = $('.tag-detail-img img'), tagId = $(
								'#main').data('current');
						SF
								.cropUpload({
									'selector' : '.tag-detail-img input[name=thumbnail]',
									'url' : 'http://segmentfault.com/api/upload/thumbnail?id='
											+ tagId,
									'button' : '上传图片',
									'title' : '请选择合适的区域作为群组图片',
									'uploadChange' : function(upload) {
										upload();
									},
									'startUpload' : function() {
										tagThumbnail.hide();
									},
									'completeUpload' : function(o) {
										if (!o.status) {
											tagThumbnail.attr('src', o.data);
										} else {
											$('<p>' + o.data + '</p>').modal({
												'title' : '上传出现错误'
											});
										}
										tagThumbnail.show();
									},
									'uploadError' : function() {
										$('<p>请选择一张正确的图片上传, 图片尺寸不要超过 2 MB</p>')
												.modal({
													'title' : '上传出现错误'
												});
									}
								});
					}
				});
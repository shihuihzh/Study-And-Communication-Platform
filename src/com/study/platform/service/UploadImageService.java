package com.study.platform.service;

import java.io.IOException;

import net.sf.json.JSONObject;

import com.study.platform.dto.UploadImageForm;
import com.study.platform.pojo.User;

public interface UploadImageService {

	/**
	 * 保存头像
	 * @param uif	上传图片数据详情
	 * @param user  修改头像的用户
	 * @param savePath 保存路径
	 * @param basePath 网站路径
	 * @return
	 * @throws IOException
	 */
	JSONObject saveAvatar(UploadImageForm uif, User user, String savePath, String basePath) throws IOException;

}

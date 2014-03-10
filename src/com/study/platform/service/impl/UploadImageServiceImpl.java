package com.study.platform.service.impl;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.sf.json.JSONObject;

import org.apache.struts2.json.bridge.StringBridge;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.platform.dao.UserDao;
import com.study.platform.dto.UploadImageForm;
import com.study.platform.pojo.User;
import com.study.platform.service.UploadImageService;
import com.study.platform.service.UserService;

@Service("uploadImageService")
public class UploadImageServiceImpl implements UploadImageService {

	@Resource(name = "userDao")
	private UserDao userDao;

	@Transactional
	@Override
	public JSONObject saveAvatar(UploadImageForm uif, User user,
			String savePath, String basePath) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(uif.getImage());
		
		String uuid = UUID.randomUUID().toString();
		StringBuilder avatarPath = new StringBuilder();
		avatarPath.append("userinfo/avatar/");
		avatarPath.append(uuid);
		
		String hugeAvatarPath = avatarPath.toString() + "_huge.png";
		String liteAvatarPath = avatarPath.toString() + "_lite.png";
		
		BufferedImage image = ImageIO.read(bais);
		Thumbnails.of(image).size(128, 128).outputFormat("png").toFile(savePath+hugeAvatarPath);
		Thumbnails.of(image).size(40, 40).outputFormat("png").toFile(savePath+liteAvatarPath);
		bais.close();
		
		user.setAvatarHuge(hugeAvatarPath);
		user.setAvatarLite(liteAvatarPath);
		userDao.updateUser(user);
		
		JSONObject json = new JSONObject();
		json.element("status", 0);
		json.element("data", basePath + hugeAvatarPath);
		
		return json;
	}



}

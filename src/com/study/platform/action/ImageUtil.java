package com.study.platform.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import net.coobird.thumbnailator.Thumbnails;

import com.study.platform.util.DateUtils;

public class ImageUtil {
	
	/**
	 * 保存图片
	 * @param thumb 是否调整大小
	 * @throws IOException 
	 */
	public static String saveImage(File file, String pathFix, boolean thumb, int width, int height) throws IOException {
		if(file == null) {
			return "";
		}
		
		String pathDir = "/attachment/images/" + DateUtils.dateToString(new Date(), "yyyyMM") + "/";
		
		File dir = new File(pathFix + pathDir);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String path = pathDir + UUID.randomUUID().toString() +".jpg";
		
		if(thumb) {
			Thumbnails.of(file).size(width, height).outputFormat("jpg").toFile(pathFix + path);			
		} else {
			Thumbnails.of(file).scale(1f).outputFormat("jpg").toFile(pathFix + path);						
		}
		
		return path;

	}
	
	/**
	 * 保存图片,强制大小
	 * @param thumb 是否调整大小
	 * @throws IOException 
	 */
	public static String saveForceImage(File file, String pathFix, boolean thumb, int width, int height) throws IOException {
		if(file == null) {
			return "";
		}
		
		String pathDir = "/attachment/images/" + DateUtils.dateToString(new Date(), "yyyyMM") + "/";
		
		File dir = new File(pathFix + pathDir);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String path = pathDir + UUID.randomUUID().toString() +".jpg";
		
		if(thumb) {
			Thumbnails.of(file).forceSize(width, height).outputFormat("jpg").toFile(pathFix + path);			
		} else {
			Thumbnails.of(file).scale(1f).outputFormat("jpg").toFile(pathFix + path);						
		}
		
		return path;

	}
}

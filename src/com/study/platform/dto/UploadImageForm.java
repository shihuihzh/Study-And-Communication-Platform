package com.study.platform.dto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.codec.Base64;

public class UploadImageForm {

	private Map<String, String> contentDisposition;
	private String contentTransferEncoding ;
	private String contentType;
	
	private byte[] image;

	public Map<String, String> getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(Map<String, String> contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public String getContentTransferEncoding() {
		return contentTransferEncoding;
	}

	public void setContentTransferEncoding(String contentTransferEnconding) {
		this.contentTransferEncoding = contentTransferEnconding;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public UploadImageForm(InputStream inputStream) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		br.readLine();	// 第一行分界线忽略
		parseDisposition(br.readLine());				// 第二行文件相关信息
		contentTransferEncoding = br.readLine().split(":")[1];			// 第三行传输编码类型
		contentType = br.readLine().split(":")[1];;						// 文件类型
		br.readLine();	// 空行
		String base64Img = br.readLine();						// 正文，编码图片
		image = Base64.decode(base64Img.getBytes());
	}

	private Map<String, String> parseDisposition(String disposition) {
		contentDisposition = new HashMap<String, String>();
		String[] obj = disposition.split(";");
		for(int i = 1; i < obj.length && obj.length > 1; i++) {
			String[] entry = obj[i].split("=");
			contentDisposition.put(entry[0], entry[1].substring(1,entry[1].length() - 1));
		}
		return contentDisposition;
	}
	
}

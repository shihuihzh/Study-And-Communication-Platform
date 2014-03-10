package com.study.platform.util;

import java.io.IOException;
import java.util.Properties;

public class WebSitePropUtil {
	
	public static Properties webSiteProp;

	static {
		webSiteProp = new Properties();
		try {
			webSiteProp.load(WebSitePropUtil.class.getClassLoader().getResourceAsStream("website.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private WebSitePropUtil(){};
}

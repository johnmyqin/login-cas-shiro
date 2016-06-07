package com.xxx.shiro.utils;

import javax.servlet.http.HttpServletRequest;

public class CommonUtil {
	// 判断是否 AJAX 请求
	public static boolean useAjax(HttpServletRequest request) {
		return "XMLHttpRequest".equalsIgnoreCase(request
				.getHeader("X-Requested-With"));
	}
}
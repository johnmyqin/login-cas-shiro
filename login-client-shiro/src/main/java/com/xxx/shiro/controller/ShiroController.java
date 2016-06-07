package com.xxx.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShiroController {
	public ShiroController() {
		System.out.println("ShiroController");
	}

	@RequestMapping(value = { "/index", "/" })
	public String toIndex() {
		return "index.htm";
	}

	// TODO 需要修改，为了支持 AJAX，不能让用户看到我们的页面代码
	@RequestMapping("/login")
	public String toLogin() {
		Subject currentUser = SecurityUtils.getSubject();

		// 如果已经登录，则转入登录成功的页面，防止继续登录
		if (currentUser.isAuthenticated()) {
			return "redirect:success";
		}

		// 如果是 GET 则显示登录页面，
		// 如果是 POST 则进入登录逻辑处理 UserFormAuthenticationFilter.executeLogin()
		return "login.htm";
	}

	// 访问 admin 页面，需要 Admin 的权限
	@RequestMapping("/admin")
	public String toAdmin() {
		return "admin.htm";
	}

	// 访问 register 页面，需要 Register 权限，Admin 同时有 Register 权限
	@RequestMapping("/register")
	public String toRegister() {
		return "register.htm";
	}

	@RequestMapping("/success")
	public String toSuccess() {
		return "success.htm";
	}

	// 提示无权访问
	// TODO 需要修改，为了支持 AJAX，不能让用户看到我们的页面代码
	@RequestMapping("/unauthorized")
	public String toUnauthorized() {
		return "unauthorized.htm";
	}

	@RequestMapping("/permission-ajax")
	public String toPermissionAjax() {
		return "permission-ajax.htm";
	}
}
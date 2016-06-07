package com.xxx.shiro.filter;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Refer: http://www.ibm.com/developerworks/cn/java/j-lo-shiro/ Shiro 从
 * Realm获取安全数据(如用户、角色、权限), 就是说 SecurityManager 要验证用户身份, 那么它需要从 Realm
 * 获取相应的用户进行比较以确定用户身份是否合法。 也需要从 Realm 得到用户相应的角色/权限进行验证用户是否能进行操作; 可以把 Realm 看成
 * DataSource, 即安全数据源。
 */
public class MyShiroRealm extends AuthorizingRealm {
	private static Logger logger = LoggerFactory.getLogger(MyShiroRealm.class
			.getName());

	private Map<String, String> usernamePasswords;
	private Map<String, List<String>> usernameRoles;

	@Autowired
	UserMapper userMapper;

	public MyShiroRealm() {
		// 测试示例，用户密码写死在代码中，实际上应该保存在数据库查询获取
		usernamePasswords = new HashMap<String, String>();
		usernameRoles = new HashMap<String, List<String>>();

		usernamePasswords.put("admin", "password");
		usernamePasswords.put("register", "password");

		usernameRoles.put("admin", Arrays.asList("Register", "Admin"));
		usernameRoles.put("register", Arrays.asList("Register"));
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// 用户需要提供 principals (身份)和 credentials(证明)给 shiro,从而应用能验证用户身份
		// 最常见的 principals 和 credentials 组合就是用户名/密码了
		// String username = (String)
		// principals.fromRealm(getName()).iterator().next();
		String username = (String) principals.getPrimaryPrincipal();

		// Authentication 成功后查询用户授权信息.
		List<String> roles = queryRoles(username);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		logger.debug("Username: {}, Roles: {}", username, roles);

		if (roles != null && !roles.isEmpty()) {
			// info.addStringPermissions(permissions);
			info.addRoles(roles);
		}

		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername(); // 通过表单接收的用户名

		if (username.equals("test")) {
			throw new LockedAccountException("Account is locked"); // 帐号锁定
		}

		User user = userMapper.findUserById(1);
		System.out.println(user);

		// 取得预先定义的用户名密码对
		return new SimpleAuthenticationInfo(username, queryPassword(username),
				getName());
	}

	private String queryPassword(String username) {
		return usernamePasswords.get(username);
	}

	private List<String> queryRoles(String username) {
		return usernameRoles.get(username);
	}
}
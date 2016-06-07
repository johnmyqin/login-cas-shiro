package com.xxx.shiro.filter;

/**
 * 
 * @author Nic.Hsu
 *
 * @code 生成Builder代码
 * 
 *       \w+ (\w+) (\w+);
 * 
 *       public Builder $2($1 $2) { this.$2 = $2; return this; }
 * 
 *       this.$2 = builder.$2;
 * 
 * 
 */
public class User {
	private Integer userId;
	private String email;
	private String loginName;
	private String userName;
	private String password;

	public User() {

	}

	private User(Builder builder) {
		this.userId = builder.userId;
		this.email = builder.email;
		this.loginName = builder.loginName;
		this.userName = builder.userName;
		this.password = builder.password;
	}

	public static Builder builder() {
		return new Builder();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static class Builder implements com.xxx.shiro.Builder<User> {
		private Integer userId;
		private String email;
		private String loginName;
		private String userName;
		private String password;

		public Builder userId(Integer userId) {
			this.userId = userId;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder loginName(String loginName) {
			this.loginName = loginName;
			return this;
		}

		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		@Override
		public User build() {
			return new User(this);
		}
	}
}

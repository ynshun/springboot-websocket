package com.ynshun.domain;

import java.io.Serializable;

public class UserInfo implements Serializable {
	private static final long serialVersionUID = -6221737773056916275L;

	String username;

	String nickname;

	String password;

	String icon;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}

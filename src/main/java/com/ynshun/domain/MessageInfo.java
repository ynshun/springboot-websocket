package com.ynshun.domain;

import java.io.Serializable;

public class MessageInfo implements Serializable {
	private static final long serialVersionUID = 8262520435578435342L;

	UserInfo from_user;

	UserInfo to_user;

	String send_time;

	String message_content;

	public UserInfo getFrom_user() {
		return from_user;
	}

	public void setFrom_user(UserInfo from_user) {
		this.from_user = from_user;
	}

	public UserInfo getTo_user() {
		return to_user;
	}

	public void setTo_user(UserInfo to_user) {
		this.to_user = to_user;
	}

	public String getSend_time() {
		return send_time;
	}

	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

}

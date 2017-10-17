package com.uangteman.dto;

public class Messages {
	public enum NotifType {
		ERROR, INFO
	}

	private NotifType type;

	private String message;

	public NotifType getType() {
		return type;
	}

	public void setType(NotifType type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

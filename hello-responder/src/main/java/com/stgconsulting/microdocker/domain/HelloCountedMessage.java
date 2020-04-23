package com.stgconsulting.microdocker.domain;

public class HelloCountedMessage {

	private Long count;
	private String name;
	public Long getCount() {
		return count;
	}
	public String getName() {
		return name;
	}
	public void setCount(Long messageId) {
		this.count = messageId;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

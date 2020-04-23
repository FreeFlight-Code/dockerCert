package com.stgconsulting.microdocker.domain;

public class HelloResponse {

	private String message;

	public HelloResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String response) {
		this.message = response;
	}

}

package com.stgconsulting.microdocker.domain;

public class HelloResponse {

	private long count;
	private String name;

	public HelloResponse(HelloMessage message, long count) {
		this.name = message.getName();
		this.count = count;

	}

	public long getCount() {
		return count;
	}

	public String getName() {
		return name;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "HelloResponse [name=" + name + ", count=" + count + "]";
	}

}

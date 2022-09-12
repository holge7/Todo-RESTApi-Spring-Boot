package com.todo.api.exception;

public class ApiException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String resource;
	private String reference;
	private String msg;
	
	
	public ApiException(String resource, String reference, String msg) {
		super(String.format("%s [%s]: %s", resource, reference, msg));
		this.resource = resource;
		this.reference = reference;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
	
}

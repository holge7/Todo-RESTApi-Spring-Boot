package com.todo.api.util;

public class ApiResponse {
	
	private boolean error;
	private Object data;
	private String msg;
	
	public ApiResponse() {}
	
	public ApiResponse(Object data) {
		this.data = data;
		this.error = false;
		this.msg = "";
	}
	
	public ApiResponse(boolean error, String msg) {
		this.error = error;
		this.msg = msg;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
	
	

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}

package com.revature.craleigh318.ers.app;

public class FormResponse {
	
	public enum Code {
		NOT_SUBMITTED, SUCCEEDED, FAILED
	}
	
	private Code code;
	private String username;
	
	public FormResponse(Code code, String username) {
		this.code = code;
		this.username = username;
	}
	
	public Code getCode() {
		return code;
	}
	
	public String getUsername() {
		return username;
	}
}

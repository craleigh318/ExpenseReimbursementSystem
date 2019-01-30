package com.revature.craleigh318.expense_reimbursement_system.app.employee_registration;

class RegisterUserResponse {
	
	enum Code {
		NOT_SUBMITTED, SUCCEEDED, FAILED
	}
	
	private Code code;
	private String username;
	
	RegisterUserResponse (Code code, String username) {
		this.code = code;
		this.username = username;
	}
	
	Code getCode() {
		return code;
	}
	
	String getUsername() {
		return username;
	}
}

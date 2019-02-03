package com.revature.craleigh318.ers.app.employee_registration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletResponse;

class EmployeeRegistrationView {
	
	private static final String SEGMENT_1 = "<!DOCTYPE html><html><head><title>Expense Reimbursement System</title></head><body><h1>Register New Employee</h1><form method=\"post\"><table><tr><td>Username:</td><td><input name=\"username\" type=\"text\" /></td></tr><tr><td>Temporary Password:</td><td><input name=\"password\" type=\"password\" /></td></tr></table><input type=\"submit\" value=\"Register\" /></form>";
	private static final String SEGMENT_2 = "</body></html>";
	private static final String SUCCESS_MSG = "%s registered successfully!";
	private static final String FAILURE_MSG = "User registration failed.";
	
	static void show(ServletResponse servResp, RegisterUserResponse ruResp) throws IOException {
		servResp.setContentType("text/html");
		PrintWriter writer = servResp.getWriter();
		String html = createHtml(ruResp);
		writer.print(html);
	}
	
	private static String createHtml(RegisterUserResponse resp) {
		StringBuilder sb = new StringBuilder(SEGMENT_1);
		String message;
		RegisterUserResponse.Code responseCode = resp.getCode();
		switch (responseCode) {
		case SUCCEEDED:
			String username = resp.getUsername();
			message = String.format(SUCCESS_MSG, username);
			break;
		case FAILED:
			message = FAILURE_MSG;
			break;
			default:
				message = null;
				break;
		}
		if (message != null) {
			sb.append(message);
		}
		sb.append(SEGMENT_2);
		return sb.toString();
	}
	
	private EmployeeRegistrationView() { }
}

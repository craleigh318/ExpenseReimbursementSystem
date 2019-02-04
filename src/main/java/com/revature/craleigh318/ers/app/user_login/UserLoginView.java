package com.revature.craleigh318.ers.app.user_login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletResponse;

import com.revature.craleigh318.ers.app.FormResponse;

class UserLoginView {

	/*private static final String SEGMENT_1 = "<!DOCTYPE html><html><head><title>Expense Reimbursement System</title></head><body><h1>Expense Reimbursement System</h1><h2>Log In</h2><form method=\"post\"><table><tr><td>Username:</td><td><input name=\"username\" type=\"text\" /></td></tr><tr><td>Password:</td><td><input name=\"password\" type=\"password\" /></td></tr></table><input type=\"submit\" value=\"Log In\" /></form><div>Not registered? Ask your manager to add you.</div>";
	private static final String SEGMENT_2 = "</body></html>";
	private static final String FAILURE_MSG = "Incorrect username or password.";
	
	static void show(ServletResponse servResp) throws IOException {
		servResp.setContentType("text/html");
		PrintWriter writer = servResp.getWriter();
		String html = createHtml(ruResp);
		writer.print(html);
	}
	
	private static String createHtml(FormResponse resp) {
		StringBuilder sb = new StringBuilder(SEGMENT_1);
		String message;
		FormResponse.Code responseCode = resp.getCode();
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
	
	private UserLoginView() { }*/
}

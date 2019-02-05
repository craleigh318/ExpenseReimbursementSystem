package com.revature.craleigh318.ers.view;

import com.revature.craleigh318.ers.utils.AttributeNames;
import com.revature.craleigh318.ers.utils.FormResponse;

public class UserLoginView {

	private static final String BASE_HTML = "<h1>Expense Reimbursement System</h1><h2>Log In</h2><form method=\"post\"><table><tr><td>Username:</td><td><input name=\""+AttributeNames.LOGIN_USERNAME+"\" type=\"text\" /></td></tr><tr><td>Password:</td><td><input name=\""+AttributeNames.LOGIN_PASSWORD+"\" type=\"password\" /></td></tr></table><input type=\"submit\" value=\"Log In\" /></form><p>Not registered? Ask your manager to add you.</p>%s";
	private static final String FAILURE_MSG = "<p>Incorrect username or password.</p>";
	
	public static String createHtml(FormResponse.Code responseCode) {
		String html = BASE_HTML;
		String message;
		switch (responseCode) {
		case FAILED:
			message = FAILURE_MSG;
			break;
		default:
			message = "";
			break;
		}
		html = String.format(html, message);
		return html;
	}
	
	private UserLoginView() { }
}

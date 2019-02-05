package com.revature.craleigh318.ers.view;

import com.revature.craleigh318.ers.utils.FormResponse;

public class EmployeeRegistrationView {
	
	private static final String BASE_HTML = "<h1>Register New Employee</h1><form method=\"post\"><table><tr><td>Username:</td><td><input name=\"registrationUsername\" type=\"text\" /></td></tr><tr><td>Temporary Password:</td><td><input name=\"registrationPassword\" type=\"password\" /></td></tr></table><input type=\"submit\" value=\"Register\" /></form>%s";
	private static final String SUCCESS_MSG = "<p>%s registered successfully!</p>";
	private static final String FAILURE_MSG = "<p>User registration failed.</p>";
	
	public static String createHtml(FormResponse resp) {
		String html = BASE_HTML;
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
			message = "";
			break;
		}
		html = String.format(html, message);
		return html;
	}
	
	private EmployeeRegistrationView() { }
}

package com.revature.craleigh318.ers.app.employee_registration;

import java.io.IOException;

import javax.servlet.ServletResponse;

import com.revature.craleigh318.ers.app.ErsView;
import com.revature.craleigh318.ers.app.FormResponse;

class EmployeeRegistrationView {
	
	private static final String BASE_HTML = "<!DOCTYPE html><html><head><title>Expense Reimbursement System</title></head><body><h1>Register New Employee</h1><form method=\"post\"><table><tr><td>Username:</td><td><input name=\"registrationUsername\" type=\"text\" /></td></tr><tr><td>Temporary Password:</td><td><input name=\"registrationPassword\" type=\"password\" /></td></tr></table><input type=\"submit\" value=\"Register\" /></form>%s</body></html>";
	private static final String SUCCESS_MSG = "<p>%s registered successfully!</p>";
	private static final String FAILURE_MSG = "<p>User registration failed.</p>";
	
	static void show(ServletResponse servResp, FormResponse ruResp) throws IOException {
		String html = createHtml(ruResp);
		ErsView.writeHtml(servResp, html);
	}
	
	private static String createHtml(FormResponse resp) {
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

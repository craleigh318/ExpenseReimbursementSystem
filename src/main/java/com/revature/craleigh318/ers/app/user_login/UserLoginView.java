package com.revature.craleigh318.ers.app.user_login;

import java.io.IOException;

import javax.servlet.ServletResponse;

import com.revature.craleigh318.ers.app.ErsView;
import com.revature.craleigh318.ers.app.FormResponse;

class UserLoginView {

	private static final String BASE_HTML = "<!DOCTYPE html><html><head><title>Expense Reimbursement System</title></head><body><h1>Expense Reimbursement System</h1><h2>Log In</h2><form method=\"post\"><table><tr><td>Username:</td><td><input name=\"loginUsername\" type=\"text\" /></td></tr><tr><td>Password:</td><td><input name=\"loginPassword\" type=\"password\" /></td></tr></table><input type=\"submit\" value=\"Log In\" /></form><p>Not registered? Ask your manager to add you.</p>%s</body></html>";
	private static final String FAILURE_MSG = "<p>Incorrect username or password.</p>";
	
	static void show(ServletResponse servResp, FormResponse.Code responseCode) throws IOException {
		String html = createHtml(responseCode);
		ErsView.writeHtml(servResp, html);
	}
	
	private static String createHtml(FormResponse.Code responseCode) {
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

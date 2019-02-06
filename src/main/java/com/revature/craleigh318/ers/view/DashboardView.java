package com.revature.craleigh318.ers.view;

import java.io.IOException;

import javax.servlet.ServletResponse;

import com.revature.craleigh318.ers.utils.FormResponse;

public class DashboardView {
	private static final String BASE_HTML = "<!DOCTYPE html><html><head><title>Expense Reimbursement System</title></head><body><h1>Register New Employee</h1><form method=\"post\"><table><tr><td>Username:</td><td><input name=\"registrationUsername\" type=\"text\" /></td></tr><tr><td>Temporary Password:</td><td><input name=\"registrationPassword\" type=\"password\" /></td></tr></table><input type=\"submit\" value=\"Register\" /></form>%s</body></html>";
	private static final String SUCCESS_MSG = "<p>%s registered successfully!</p>";
	private static final String FAILURE_MSG = "<p>User registration failed.</p>";
	private static final String EMPLOYEE_DASHBOARD = "<h1>Employee Dashboard</h1><h2>My Requests</h2>%s<h2>Request Reimbursement</h2>%s";
	private static final String MANAGER_DASHBOARD = "<h1>Manager Dashboard</h1><h2>All Requests</h2>%s<h2>Register New Employee</h2>%s";
	
	public static String employeeDashboard() {
		return String.format(EMPLOYEE_DASHBOARD, "", NewRequestView.createHtml());
	}
	
	public static String managerDashboard(String reimbursementRequestsHtml, String employeeRegistrationHtml) {
		return String.format(MANAGER_DASHBOARD, reimbursementRequestsHtml, employeeRegistrationHtml);
	}
	
	public static void show(ServletResponse servResp, FormResponse ruResp) throws IOException {
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
	
	private DashboardView() { }
}

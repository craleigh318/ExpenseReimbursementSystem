package com.revature.craleigh318.ers.view;

import com.revature.craleigh318.ers.utils.AttributeNames;

public class DashboardView {

	private static final String EMPLOYEE_DASHBOARD = "<h1>Employee Dashboard</h1><h2>My Requests</h2>%s<h2>Request Reimbursement</h2>%s";
	private static final String MANAGER_DASHBOARD = "<h1>Manager Dashboard</h1><h2>All Requests</h2>%s<h2>Register New Employee</h2>%s";
	private static final String BUTTON_LOGOUT = "<button type=\"submit\" value=\""+AttributeNames.BUTTON_LOGOUT+"\">Log Out</button>";
	
	public static String employeeDashboard(String reimbursementRequestsHtml, String newRequestHtml) {
		String html = String.format(EMPLOYEE_DASHBOARD, reimbursementRequestsHtml, newRequestHtml);
		html += BUTTON_LOGOUT;
		return html;
	}
	
	public static String managerDashboard(String reimbursementRequestsHtml, String employeeRegistrationHtml) {
		String html = String.format(MANAGER_DASHBOARD, reimbursementRequestsHtml, employeeRegistrationHtml);
		html += BUTTON_LOGOUT;
		return html;
	}
	
	private DashboardView() { }
}

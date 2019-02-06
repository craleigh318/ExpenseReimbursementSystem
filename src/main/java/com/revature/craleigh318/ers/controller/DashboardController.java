package com.revature.craleigh318.ers.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.craleigh318.ers.view.DashboardView;
import com.revature.craleigh318.ers.view.ErsView;

public class DashboardController implements IErsViewController {

	private static DashboardController instance = new DashboardController();

	public static DashboardController getInstance() {
		return instance;
	}
	
	@Override
	public void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String html = managerDashboard(req, resp);
		ErsView.writeHtmlWithOuter(resp, html);
	}
	
	private static String employeeDashboard(HttpServletRequest req, HttpServletResponse resp) {
		return DashboardView.employeeDashboard();
	}
	
	private static String managerDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String employeeRegistrationHtml = EmployeeRegistrationController.getInstance().htmlFromRequest(req, resp);
		return DashboardView.managerDashboard("", employeeRegistrationHtml);
	}

	private DashboardController() { }
}

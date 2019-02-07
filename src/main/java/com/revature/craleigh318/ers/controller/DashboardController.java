package com.revature.craleigh318.ers.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.craleigh318.ers.app.ErsFrontControllerDispatcher;
import com.revature.craleigh318.ers.data.ErsDao;
import com.revature.craleigh318.ers.utils.AttributeNames;
import com.revature.craleigh318.ers.utils.DispatchType;
import com.revature.craleigh318.ers.view.DashboardView;
import com.revature.craleigh318.ers.view.ErsView;

public class DashboardController implements IErsViewController {
	
	private static final String NO_USER_ID = "<p>ERROR: Invalid session!</p>";

	private static DashboardController instance = new DashboardController();

	public static DashboardController getInstance() {
		return instance;
	}
	
	@Override
	public void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean logoutButtonPressed = (req.getParameter(AttributeNames.BUTTON_LOGOUT) != null);
		if (logoutButtonPressed) {
			toLoginScreen(req, resp);
			return;
		}
		
		Object objUserId = req.getSession().getAttribute(AttributeNames.USER_ID);
		String html;
		if ((objUserId == null) || !(objUserId instanceof Integer)) {
			html = NO_USER_ID;
		} else {
			int userId = (int) objUserId;
			try {
				boolean isManager = ErsDao.userIsManager(userId);
				if (isManager) {
					html = managerDashboard(req, resp);
				} else {
					html = employeeDashboard(req, resp, userId);
				}
			} catch (SQLException e) {
				html = NO_USER_ID;
			}
		}
		ErsView.writeHtmlWithOuter(resp, html);
	}
	
	private static String employeeDashboard(HttpServletRequest req, HttpServletResponse resp, int userId) throws ServletException, IOException {
		String reimbursementTableHtml = ReimbursementTableController.getInstance().htmlFromRequest(req, resp, userId);
		String newRequestHtml = NewRequestController.getInstance().htmlFromRequest(req, resp, userId);
		return DashboardView.employeeDashboard(reimbursementTableHtml, newRequestHtml);
	}
	
	private static String managerDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String reimbursementTableHtml = ReimbursementTableController.getInstance().htmlFromRequest(req, resp, -1);
		String employeeRegistrationHtml = EmployeeRegistrationController.getInstance().htmlFromRequest(req, resp);
		return DashboardView.managerDashboard(reimbursementTableHtml, employeeRegistrationHtml);
	}
	
	private void toLoginScreen(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ErsFrontControllerDispatcher.setRequestDispatchType(req, DispatchType.USER_LOGIN);
		req.getRequestDispatcher("/").forward(req, resp);
	}

	private DashboardController() { }
}

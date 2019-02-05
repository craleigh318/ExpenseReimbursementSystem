package com.revature.craleigh318.ers.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.craleigh318.ers.data.ErsDao;
import com.revature.craleigh318.ers.utils.FormResponse;
import com.revature.craleigh318.ers.view.EmployeeRegistrationView;

public class EmployeeRegistrationController implements IErsViewController {
	
	private static EmployeeRegistrationController instance = new EmployeeRegistrationController();

	public static EmployeeRegistrationController getInstance() {
		return instance;
	}
	
	@Override
	public void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FormResponse result = registerUser(req);
		EmployeeRegistrationView.show(resp, result);
	}
	
	private FormResponse registerUser(HttpServletRequest req) {
		String username = req.getParameter("registrationUsername");
		String password = req.getParameter("registrationPassword");
		if ((username == null) && (password == null)) {
			return new FormResponse(FormResponse.Code.NOT_SUBMITTED, null);
		}
		try {
			ErsDao.registerUser(username, password);
		} catch (SQLException | IOException e) {
			return new FormResponse(FormResponse.Code.FAILED, null);
		}
		return new FormResponse(FormResponse.Code.SUCCEEDED, username);
	}
	
	private EmployeeRegistrationController() { }
}

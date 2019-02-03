package com.revature.craleigh318.ers.app.employee_registration;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.craleigh318.ers.app.employee_registration.RegisterUserResponse;
import com.revature.craleigh318.ers.data.ErsDao;

public class EmployeeRegistrationController extends HttpServlet {
	
	private static final long serialVersionUID = 3826403289775647723L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		serveResponse(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		serveResponse(req, resp);
	}
	
	private RegisterUserResponse registerUser(HttpServletRequest req) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if ((username == null) && (password == null)) {
			return new RegisterUserResponse(RegisterUserResponse.Code.NOT_SUBMITTED, null);
		}
		try {
			ErsDao.registerUser(username, password);
		} catch (SQLException | IOException e) {
			return new RegisterUserResponse(RegisterUserResponse.Code.FAILED, null);
		}
		return new RegisterUserResponse(RegisterUserResponse.Code.SUCCEEDED, username);
	}
	
	private void serveResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		RegisterUserResponse result = registerUser(req);
		EmployeeRegistrationView.show(resp, result);
	}

}

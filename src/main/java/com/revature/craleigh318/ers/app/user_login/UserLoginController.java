package com.revature.craleigh318.ers.app.user_login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.craleigh318.ers.app.FormResponse;
import com.revature.craleigh318.ers.app.IErsViewController;
import com.revature.craleigh318.ers.app.employee_registration.EmployeeRegistrationController;
import com.revature.craleigh318.ers.data.ErsDao;

public class UserLoginController implements IErsViewController {

	private static UserLoginController instance = new UserLoginController();

	public static UserLoginController getInstance() {
		return instance;
	}
	
	@Override
	public void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	private FormResponse logInUser(HttpServletRequest req) {
		FormResponse.Code responseCode;
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if ((username == null) && (password == null)) {
			responseCode = FormResponse.Code.NOT_SUBMITTED;
		} else {
			boolean correctLogin;
			try {
				int userId = ErsDao.selectUserId(username);
				correctLogin = ErsDao.compareUserPassword(userId, password);
			} catch (SQLException | IOException e) {
				correctLogin = false;
			}
			if (correctLogin) {
				responseCode = FormResponse.Code.SUCCEEDED;
			} else {
				responseCode = FormResponse.Code.FAILED;
			}
		}
		return new FormResponse(responseCode, username);
	}
}

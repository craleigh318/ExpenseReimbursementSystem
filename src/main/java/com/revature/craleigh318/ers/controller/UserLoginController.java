package com.revature.craleigh318.ers.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.craleigh318.ers.app.ErsFrontControllerDispatcher;
import com.revature.craleigh318.ers.data.ErsDao;
import com.revature.craleigh318.ers.utils.DispatchType;
import com.revature.craleigh318.ers.utils.FormResponse;
import com.revature.craleigh318.ers.view.UserLoginView;

public class UserLoginController implements IErsViewController {

	private static UserLoginController instance = new UserLoginController();

	public static UserLoginController getInstance() {
		return instance;
	}
	
	@Override
	public void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FormResponse result = logInUser(req);
		FormResponse.Code resultCode = result.getCode();
		if (resultCode == FormResponse.Code.SUCCEEDED) {
			toUserScreen(req, resp);
		} else {
			UserLoginView.show(resp, resultCode);
		}
	}
	
	private FormResponse logInUser(HttpServletRequest req) {
		FormResponse.Code responseCode;
		String username = req.getParameter("loginUsername");
		String password = req.getParameter("loginPassword");
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
	
	private void toUserScreen(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ErsFrontControllerDispatcher.setRequestDispatchType(req, DispatchType.EMPLOYEE_REGISTRATION);
		req.getRequestDispatcher("/").forward(req, resp);
	}
}

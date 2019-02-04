package com.revature.craleigh318.ers.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.craleigh318.ers.app.employee_registration.EmployeeRegistrationController;

class ErsFrontControllerDispatcher {

	static void dispatch(DispatchType type, HttpServletRequest req, HttpServletResponse resp) {
		getSubcontroller(type).doRequest(req, resp);
	}
	
	private static IErsViewController getSubcontroller(DispatchType type) {
		switch (type) {
		case EMPLOYEE_REGISTRATION:
			return EmployeeRegistrationController.getInstance();
		default:
			return null;
		}
	}
}

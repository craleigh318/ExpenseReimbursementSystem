package com.revature.craleigh318.ers.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.craleigh318.ers.app.employee_registration.EmployeeRegistrationController;

public class ErsFrontControllerDispatcher {
	
	private static final String DISPATCH_TYPE_ATTRIBUTE_NAME = "dispatch-type";
	private static final DispatchType DEFAULT_DISPATCH_TYPE = DispatchType.EMPLOYEE_REGISTRATION;

	public static void setRequestDispatchType(HttpServletRequest req, DispatchType newType) {
		req.getSession().setAttribute(DISPATCH_TYPE_ATTRIBUTE_NAME, newType);
	}
	
	static void dispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DispatchType dispType = getRequestDispatchType(req);
		getSubcontroller(dispType).doRequest(req, resp);
	}
	
	private static DispatchType getRequestDispatchType(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Object objDispatchType = session.getAttribute(DISPATCH_TYPE_ATTRIBUTE_NAME);
		if ((objDispatchType != null) && (objDispatchType instanceof DispatchType)) {
			return (DispatchType) objDispatchType;
		}
		return DEFAULT_DISPATCH_TYPE;
	}
	
	private static IErsViewController getSubcontroller(DispatchType type) {
		switch (type) {
		case EMPLOYEE_REGISTRATION:
			return EmployeeRegistrationController.getInstance();
		default:
			return EmployeeRegistrationController.getInstance();
		}
	}
}

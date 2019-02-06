package com.revature.craleigh318.ers.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.craleigh318.ers.controller.DashboardController;
import com.revature.craleigh318.ers.controller.IErsViewController;
import com.revature.craleigh318.ers.controller.UserLoginController;
import com.revature.craleigh318.ers.utils.AttributeNames;
import com.revature.craleigh318.ers.utils.DispatchType;

public class ErsFrontControllerDispatcher {
	
	private static final DispatchType DEFAULT_DISPATCH_TYPE = DispatchType.USER_LOGIN;

	public static void setRequestDispatchType(HttpServletRequest req, DispatchType newType) {
		req.getSession().setAttribute(AttributeNames.DISPATCH_TYPE, newType);
	}
	
	static void dispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DispatchType dispType = getRequestDispatchType(req);
		getSubcontroller(dispType).doRequest(req, resp);
	}
	
	private static DispatchType getRequestDispatchType(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Object objDispatchType = session.getAttribute(AttributeNames.DISPATCH_TYPE);
		if ((objDispatchType != null) && (objDispatchType instanceof DispatchType)) {
			return (DispatchType) objDispatchType;
		}
		return DEFAULT_DISPATCH_TYPE;
	}
	
	private static IErsViewController getSubcontroller(DispatchType type) {
		switch (type) {
		case USER_DASHBOARD:
			return DashboardController.getInstance();
		default:
			return UserLoginController.getInstance();
		}
	}
}

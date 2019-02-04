package com.revature.craleigh318.ers.app;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErsFrontController extends HttpServlet {
	
	private static final long serialVersionUID = -4533287704576978012L;
	private static final Logger LOGGER = Logger.getLogger(ErsFrontController.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dispatch(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dispatch(req, resp);
	}
	
	private void dispatch(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ErsFrontControllerDispatcher.dispatch(req, resp);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
	}
}

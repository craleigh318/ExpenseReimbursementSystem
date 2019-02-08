package com.revature.craleigh318.ers.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.craleigh318.ers.data.ErsDao;
import com.revature.craleigh318.ers.model.ReimbursementRequest;
import com.revature.craleigh318.ers.utils.AttributeNames;
import com.revature.craleigh318.ers.view.ReimbursementTableView;

public class ReimbursementTableController {
	
	private static final Logger LOGGER = Logger.getLogger(ReimbursementTableController.class.getName());
	private static final String EXCEPTION_MESSAGE = "<p>Table data could not be retreived.</p>";
	
	private static ReimbursementTableController instance = new ReimbursementTableController();

	public static ReimbursementTableController getInstance() {
		return instance;
	}
	
	public String htmlFromRequest(HttpServletRequest req, HttpServletResponse resp, int userId) {
		checkButtonPress(req);
		Map<Integer, ReimbursementRequest> reimbursementRequests;
		try {
			reimbursementRequests = fetchRequests(userId);
		} catch (SQLException | IOException e) {
			return EXCEPTION_MESSAGE;
		}
		boolean manager = (userId < 0);
		return ReimbursementTableView.createTable(reimbursementRequests, manager);
	}
	
	private Map<Integer, ReimbursementRequest> fetchRequests(int userId) throws SQLException, IOException {
		Map<Integer, ReimbursementRequest> map;
		if (userId < 0) {
			map = ErsDao.getAllReimbursementRequests();
		} else {
			map = ErsDao.getReimbursementRequestsForUser(userId);
		}
		return map;
	}
	
	private void checkButtonPress(HttpServletRequest servletRequest) {
		Enumeration<String> parameterNames = servletRequest.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String nextParameterName = parameterNames.nextElement();
			boolean isApproval = (nextParameterName.startsWith(AttributeNames.APPROVE_REQUEST));
			if (isApproval) {
				onButtonApprove(nextParameterName);
				return;
			}
			boolean isDenial = (nextParameterName.startsWith(AttributeNames.DENY_REQUEST));
			if (isDenial) {
				onButtonDeny(nextParameterName);
				return;
			}
		}
	}
	
	private int idFromParameterName(String parameterName, int nameLength) {
		String substring = parameterName.substring(nameLength);
		return Integer.parseInt(substring);
	}
	
	private void onButtonApprove(String parameterName) {
		int reimbursementRequestId = idFromParameterName(parameterName, AttributeNames.APPROVE_REQUEST.length());
		try {
			ErsDao.approveReimbursementRequest(reimbursementRequestId, true);
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
	}
	
	private void onButtonDeny(String parameterName) {
		int reimbursementRequestId = idFromParameterName(parameterName, AttributeNames.DENY_REQUEST.length());
		try {
			ErsDao.approveReimbursementRequest(reimbursementRequestId, false);
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
	}
	
	private ReimbursementTableController() { }
}

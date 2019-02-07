package com.revature.craleigh318.ers.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.craleigh318.ers.data.ErsDao;
import com.revature.craleigh318.ers.model.ReimbursementRequest;
import com.revature.craleigh318.ers.view.ReimbursementTableView;

public class ReimbursementTableController {
	
	private static final String EXCEPTION_MESSAGE = "<p>Table data could not be retreived.</p>";
	
	private static ReimbursementTableController instance = new ReimbursementTableController();

	public static ReimbursementTableController getInstance() {
		return instance;
	}
	
	public String htmlFromRequest(HttpServletRequest req, HttpServletResponse resp, int userId) {
		//boolean buttonPressed = (req.getParameter(AttributeNames.BUTTON_REGISTER_EMPLOYEE) != null);
		Iterable<ReimbursementRequest> reimbursementRequests;
		try {
			reimbursementRequests = fetchRequests(userId);
		} catch (SQLException | IOException e) {
			return EXCEPTION_MESSAGE;
		}
		boolean manager = (userId < 0);
		return ReimbursementTableView.createTable(reimbursementRequests, manager);
	}
	
	private Iterable<ReimbursementRequest> fetchRequests(int userId) throws SQLException, IOException {
		Map<Integer, ReimbursementRequest> map;
		if (userId < 0) {
			map = ErsDao.getAllReimbursementRequests();
		} else {
			map = ErsDao.getReimbursementRequestsForUser(userId);
		}
		return map.values();
	}
	
	private ReimbursementTableController() { }
}

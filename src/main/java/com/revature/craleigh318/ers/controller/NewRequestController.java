package com.revature.craleigh318.ers.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.craleigh318.ers.data.ErsDao;
import com.revature.craleigh318.ers.utils.AttributeNames;
import com.revature.craleigh318.ers.utils.FormResponse;
import com.revature.craleigh318.ers.view.NewRequestView;

public class NewRequestController {
	
	private static NewRequestController instance = new NewRequestController();

	public static NewRequestController getInstance() {
		return instance;
	}
	
	public String htmlFromRequest(HttpServletRequest req, HttpServletResponse resp, int userId) throws ServletException, IOException {
		boolean buttonPressed = (req.getParameter(AttributeNames.BUTTON_REQUEST_REIMBURSEMENT) != null);
		FormResponse.Code result;
		if (buttonPressed) {
			result = sendReimbursementRequest(req, userId);
		} else {
			result = FormResponse.Code.NOT_SUBMITTED;
		}
		return NewRequestView.createHtml(result);
	}
	
	private FormResponse.Code sendReimbursementRequest(HttpServletRequest req, int userId) {
		try {
			String strAmount = req.getParameter(AttributeNames.NEW_AMOUNT);
			BigDecimal amount = new BigDecimal(strAmount);
			String strRequestDate = req.getParameter(AttributeNames.NEW_DATE);
			Object requestDate = LocalDate.parse(strRequestDate);
			String description = req.getParameter(AttributeNames.NEW_DESCRIPTION);
			ErsDao.requestReimbursement(userId, amount, requestDate, description);
			return FormResponse.Code.SUCCEEDED;
		} catch (Exception e) {
			return FormResponse.Code.FAILED;
		}
	}
	
	private NewRequestController() { }
}

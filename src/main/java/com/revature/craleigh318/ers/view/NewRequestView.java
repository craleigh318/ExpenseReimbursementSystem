package com.revature.craleigh318.ers.view;

import com.revature.craleigh318.ers.utils.AttributeNames;
import com.revature.craleigh318.ers.utils.FormResponse;

public class NewRequestView {
	
	private static final String BASE_HTML = "<form method=\"post\"><table><tr><th>Amount</th><td>$<input type=\"number\" name=\""+AttributeNames.NEW_AMOUNT+"\" /></td></tr><tr><th>Date</th><td><input type=\"date\" name=\""+AttributeNames.NEW_DATE+"\" /></td></tr><tr><th>Description</th><td><input type = \"text\" name=\""+AttributeNames.NEW_DESCRIPTION+"\" /></td></tr></table><input type=\"submit\" name=\""+AttributeNames.BUTTON_REQUEST_REIMBURSEMENT+"\" value=\"Submit\" /></form>%s";
	private static final String SUCCESS_MSG = "<p>Request added!</p>";
	private static final String FAILURE_MSG = "<p>Request submission failed.</p>";
	
	public static String createHtml(FormResponse.Code responseCode) {
		String html = BASE_HTML;
		String message;
		switch (responseCode) {
		case SUCCEEDED:
			message = SUCCESS_MSG;
			break;
		case FAILED:
			message = FAILURE_MSG;
			break;
		default:
			message = "";
			break;
		}
		html = String.format(html, message);
		return html;
	}
	
	private NewRequestView() { }
}

package com.revature.craleigh318.ers.view;

import com.revature.craleigh318.ers.utils.AttributeNames;

public class NewRequestView {
	
	private static final String HTML = "<form><table><tr><th>Amount</th><td>$<input type=\"number\" name=\""+AttributeNames.NEW_AMOUNT+"\" /></td></tr><tr><th>Date</th><td><input type=\"date\" name=\""+AttributeNames.NEW_DATE+"\" /></td></tr><tr><th>Description</th><td><input type = \"text\" name=\""+AttributeNames.NEW_DESCRIPTION+"\" /></td></tr></table><input type=\"submit\" name=\""+AttributeNames.BUTTON_REQUEST_REIMBURSEMENT+"\" value=\"Submit\" /></form>";

	public static String createHtml() {
		return HTML;
	}
	
	private NewRequestView() { }
}

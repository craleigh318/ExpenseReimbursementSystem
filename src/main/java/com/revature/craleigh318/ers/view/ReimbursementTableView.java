package com.revature.craleigh318.ers.view;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Map;

import com.revature.craleigh318.ers.model.ReimbursementRequest;
import com.revature.craleigh318.ers.utils.AttributeNames;

public class ReimbursementTableView {

	private static final String APRV_NULL = "Pending";
	private static final String APRV_TRUE = "Approved";
	private static final String APRV_FALSE = "Denied";
	private static final String BUTTONS_APRV = "<form method=\"post\"><input type=\"submit\" name=\"%s\" value=\"Approve\" /><input type=\"submit\" name=\"%s\" value=\"Deny\" /></form>";
	private static final String TH_EMPLOYEE = "<th>Employee ID</th>";
	private static final String TABLE_HEADERS = "<tr>%s<th>Amount</th><th>Date</th><th>Description</th><th>Status</th></tr>";
	private static final String TABLE_HEADERS_EMPLOYEE = String.format(TABLE_HEADERS, "");
	private static final String TABLE_HEADERS_MANAGER = String.format(TABLE_HEADERS, TH_EMPLOYEE);
	private static final String TABLE_CELL = "<td>%s</td>";
	private static final String TABLE_TEMPLATE = "<table>%s</table>";
	private static final String ROW_TEMPLATE = "<tr>%s</tr>";

	public static String createTable(Map<Integer, ReimbursementRequest> requestMap, boolean manager) {
		String headers;
		if (manager) {
			headers = TABLE_HEADERS_MANAGER;
		} else {
			headers = TABLE_HEADERS_EMPLOYEE;
		}
		StringBuilder tableBuilder = new StringBuilder(headers);
		Iterable<Integer> requestKeys = requestMap.keySet();
		for (Integer requestId : requestKeys) {
			String nextRow;
			ReimbursementRequest request = requestMap.get(requestId);
			if (manager) {
				nextRow = createManagerRow(requestId, request);
			} else {
				nextRow = createEmployeeRow(request);
			}
			nextRow = formatRow(nextRow);
			tableBuilder.append(nextRow);
		}
		String table = tableBuilder.toString();
		table = formatTable(table);
		return table;
	}
	
	private static String formatRow(String row) {
		return String.format(ROW_TEMPLATE, row);
	}
	
	private static String formatTable(String table) {
		return String.format(TABLE_TEMPLATE, table);
	}
	
	private static String createMinimalRow(ReimbursementRequest request) {
		BigDecimal amount = request.getAmount();
		String strAmount = NumberFormat.getCurrencyInstance().format(amount);
		String tdAmount = String.format(TABLE_CELL, strAmount);
		StringBuilder sb = new StringBuilder(tdAmount);
		Date date = request.getRequestDate();
		String tdDate = String.format(TABLE_CELL, date.toString());
		sb.append(tdDate);
		String description = request.getDescription();
		String tdDescription = String.format(TABLE_CELL, description);
		sb.append(tdDescription);
		return sb.toString();
	}
	
	private static String createEmployeeRow(ReimbursementRequest request) {
		String tdEmployee = createMinimalRow(request);
		StringBuilder sb = new StringBuilder(tdEmployee);
		String approvalCell = createApprovalCell(-1, request, false);
		sb.append(approvalCell);
		return sb.toString();
	}
	
	private static String createManagerRow(Integer requestId, ReimbursementRequest request) {
		int userId = request.getUserId();
		String strUserId = Integer.toString(userId);
		String tdUserId = String.format(TABLE_CELL, strUserId);
		StringBuilder sb = new StringBuilder(tdUserId);
		String tdEmployee = createMinimalRow(request);
		sb.append(tdEmployee);
		String approvalCell = createApprovalCell(requestId, request, true);
		sb.append(approvalCell);
		return sb.toString();
	}
	
	private static String createApprovalCell(Integer requestId, ReimbursementRequest request, boolean manager) {
		Boolean status = request.getApproved();
		String contents;
		if (status == null) {
			if (manager) {
				contents = createApproveDenyButtons(requestId);
			} else {
				contents = APRV_NULL;
			}
		} else if (status == true) {
			contents = APRV_TRUE;
		} else {
			contents = APRV_FALSE;
		}
		return String.format(TABLE_CELL, contents);
	}
	
	private static String createApproveDenyButtons(int requestId) {
		String approveName = String.format(AttributeNames.BUTTON_APPROVE_REQUEST, requestId);
		String denyName = String.format(AttributeNames.BUTTON_DENY_REQUEST, requestId);
		return String.format(BUTTONS_APRV, approveName, denyName);
	}
	
	private ReimbursementTableView() { }
}

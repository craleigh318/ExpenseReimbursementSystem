package com.revature.craleigh318.ers.view;

import java.math.BigDecimal;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.revature.craleigh318.ers.model.ReimbursementRequest;

public class ReimbursementTableView {

	private static final String TH_EMPLOYEE = "<th>Employee ID</th>";
	private static final String TABLE_HEADERS = "<tr>%s<th>Amount ($)</th><th>Date</th><th>Description</th><th>Status</th></tr>";
	private static final String TABLE_HEADERS_EMPLOYEE = String.format(TABLE_HEADERS, "");
	private static final String TABLE_HEADERS_MANAGER = String.format(TABLE_HEADERS, TH_EMPLOYEE);
	private static final String TABLE_CELL = "<td>%s</td>";
	private static final String TABLE_TEMPLATE = "<table>%s</table>";
	private static final String ROW_TEMPLATE = "<tr>%s</tr>";
	private static final int EMPLOYEE_TABLE_ROW_LENGTH = 3;
	private static final int MANAGER_TABLE_ROW_LENGTH = EMPLOYEE_TABLE_ROW_LENGTH + 2;
	
	//private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String createTable(Iterable<ReimbursementRequest> requests, boolean manager) {
		String headers;
		if (manager) {
			headers = TABLE_HEADERS_MANAGER;
		} else {
			headers = TABLE_HEADERS_EMPLOYEE;
		}
		StringBuilder tableBuilder = new StringBuilder(headers);
		for (ReimbursementRequest r : requests) {
			String nextRow;
			if (manager) {
				nextRow = createManagerRow(r);
			} else {
				nextRow = createEmployeeRow(r);
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
	
	private static String createEmployeeRow(ReimbursementRequest request) {
		BigDecimal amount = request.getAmount();
		String tdAmount = String.format(TABLE_CELL, amount.toString());
		StringBuilder sb = new StringBuilder(tdAmount);
		Date date = request.getRequestDate();
		String tdDate = String.format(TABLE_CELL, date.toString());
		sb.append(tdDate);
		String description = request.getDescription();
		String tdDescription = String.format(TABLE_CELL, description);
		sb.append(tdDescription);
		return sb.toString();
	}
	
	private static String createManagerRow(ReimbursementRequest request) {
		int userId = request.getUserId();
		String strUserId = Integer.toString(userId);
		String tdUserId = String.format(TABLE_CELL, strUserId);
		StringBuilder sb = new StringBuilder(tdUserId);
		String tdEmployee = createEmployeeRow(request);
		sb.append(tdEmployee);
		return sb.toString();
	}
	
	private ReimbursementTableView() { }
}

package com.revature.craleigh318.ers.view;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class ReimbursementTableView {

	private static final String TABLE_HEADERS = "<tr>%s<th>Amount ($)</th><th>Date</th><th>Description</th>%s</tr>";
	private static final String TH_EMPLOYEE = "<th>Employee ID</th>";
	private static final String TH_ACTION = "<th>Action</th>";
	private static final String TABLE_CELL = "<td>%s</td>";
	private static final int EMPLOYEE_TABLE_ROW_LENGTH = 3;
	private static final int MANAGER_TABLE_ROW_LENGTH = EMPLOYEE_TABLE_ROW_LENGTH + 2;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static String createEmployeeRow(BigDecimal amount, Date date, String description) {
		String tdAmount = String.format(TABLE_CELL, amount.toString());
		StringBuilder sb = new StringBuilder(tdAmount);
		String tdDate = String.format(TABLE_CELL, dateFormat.format(date));
		sb.append(tdDate);
		String tdDescription = String.format(TABLE_CELL, description);
		sb.append(tdDescription);
		return sb.toString();
	}
	
	private static String createManagerRow(int userId, BigDecimal amount, Date date, String description) {
		String tdUserId = Integer.toString(userId);
		StringBuilder sb = new StringBuilder(tdUserId);
		String tdEmployee = createEmployeeRow(amount, date, description);
		sb.append(tdEmployee);
		return sb.toString();
	}
	
	private ReimbursementTableView() { }
}

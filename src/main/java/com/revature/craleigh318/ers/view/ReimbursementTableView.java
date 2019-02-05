package com.revature.craleigh318.ers.view;

public class ReimbursementTableView {

	private static final String TABLE_HEADERS = "<tr>%s<th>Amount ($)</th><th>Date</th><th>Description</th>%s</tr>";
	private static final String TH_EMPLOYEE = "<th>Employee ID</th>";
	private static final String TH_ACTION = "<th>Action</th>";
	private static final String TABLE_CELL = "<td>%s</td>";
	private static final int EMPLOYEE_TABLE_ROW_LENGTH = 3;
	private static final int MANAGER_TABLE_ROW_LENGTH = EMPLOYEE_TABLE_ROW_LENGTH + 2;
	
	private ReimbursementTableView() { }
}

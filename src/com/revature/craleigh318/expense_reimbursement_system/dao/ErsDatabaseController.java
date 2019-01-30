package com.revature.craleigh318.expense_reimbursement_system.dao;

import java.io.IOException;
import java.sql.SQLException;

public class ErsDatabaseController {

	public void userRegister(String username, String temporaryPassword) throws SQLException, IOException {
		ErsDatabaseProcedures.insertEmployee(username, temporaryPassword).execute();
	}
	
	private ErsDatabaseController() { }
}

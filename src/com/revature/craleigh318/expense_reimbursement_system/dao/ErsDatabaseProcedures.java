package com.revature.craleigh318.expense_reimbursement_system.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ErsDatabaseProcedures {
	
	public CallableStatement insertEmployee(String username, String password) throws SQLException, IOException {
		CallableStatement stmt = getConnection().prepareCall("{CALL PROC_INSERT_EMPLOYEE(?,?)}");  
		stmt.setString(1, username);  
		stmt.setString(2, password);
		return stmt;
	}
	
	private Connection getConnection() throws SQLException, IOException {
		return DataHandler.getInstance().getConnection();
	}
	
	private ErsDatabaseProcedures() { }
}

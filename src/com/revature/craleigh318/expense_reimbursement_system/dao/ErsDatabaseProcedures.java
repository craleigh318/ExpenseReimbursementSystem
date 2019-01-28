package com.revature.craleigh318.expense_reimbursement_system.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class ErsDatabaseProcedures {
	
	public static CallableStatement insertEmployee(String username, String password) throws SQLException, IOException {
		CallableStatement stmt = getConnection().prepareCall("{CALL PROC_INSERT_EMPLOYEE(?, ?)}");
		stmt.setString(1, username);
		stmt.setString(2, password);
		return stmt;
	}
	
	public static CallableStatement selectUserId(String username) throws SQLException, IOException {
		CallableStatement stmt = getConnection().prepareCall("{CALL PROC_SELECT_USER_ID(?, ?)}");
		stmt.setString(1, username);
		stmt.registerOutParameter(2, Types.INTEGER);
		return stmt;
	}
	
	public static CallableStatement updateUserPassword(int userId, String newPassword) throws SQLException, IOException {
		CallableStatement stmt = getConnection().prepareCall("{CALL PROC_UPDATE_USER_PASSWORD(?, ?)}");
		stmt.setInt(1, userId);
		stmt.setString(2, newPassword);
		return stmt;
	}
	
	private static Connection getConnection() throws SQLException, IOException {
		return DataHandler.getInstance().getConnection();
	}
	
	private ErsDatabaseProcedures() { }
}

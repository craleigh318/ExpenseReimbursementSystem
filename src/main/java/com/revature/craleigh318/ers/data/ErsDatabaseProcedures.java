package com.revature.craleigh318.ers.data;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

class ErsDatabaseProcedures {
	
	static CallableStatement insertEmployee(String username, String password) throws SQLException, IOException {
		CallableStatement stmt = getConnection().prepareCall("{CALL PROC_INSERT_EMPLOYEE(?, ?)}");
		stmt.setString(1, username);
		stmt.setString(2, password);
		return stmt;
	}
	
	static CallableStatement insertReimbursementRequest(int userId, BigDecimal amount, Date requestDate, String description) throws SQLException, IOException {
		CallableStatement stmt = getConnection().prepareCall("{CALL PROC_INSERT_REQUEST(?, ?, ?, ?)}");
		stmt.setInt(1, userId);
		stmt.setBigDecimal(2, amount);
		stmt.setDate(3, requestDate);
		stmt.setString(4, description);
		return stmt;
	}
	
	static CallableStatement selectUserId(String username) throws SQLException, IOException {
		CallableStatement stmt = getConnection().prepareCall("{CALL PROC_SELECT_USER_ID(?, ?)}");
		stmt.setString(1, username);
		stmt.registerOutParameter(2, OracleTypes.INTEGER);
		return stmt;
	}
	
	static CallableStatement compareUserPassword(int userId, String enteredPassword) throws SQLException, IOException {
		CallableStatement stmt = getConnection().prepareCall("{CALL PROC_COMPARE_USER_PASSWORD(?, ?, ?)}");
		stmt.setInt(1, userId);
		stmt.setString(2, enteredPassword);
		stmt.registerOutParameter(3, OracleTypes.INTEGER);
		return stmt;
	}
	
	static CallableStatement userIsManager(int userId) throws SQLException, IOException {
		CallableStatement stmt = getConnection().prepareCall("{CALL PROC_IS_MANAGER(?, ?)}");
		stmt.setInt(1, userId);
		stmt.registerOutParameter(2, OracleTypes.NUMBER);
		return stmt;
	}
	
	static CallableStatement selectAllReimbursementRequests() throws SQLException, IOException {
		CallableStatement stmt = getConnection().prepareCall("{CALL PROC_SELECT_ALL_REQUESTS(?)}");
		stmt.registerOutParameter(1, OracleTypes.CURSOR);
		return stmt;
	}
	
	static CallableStatement selectUsersReimbursementRequests(int userId) throws SQLException, IOException {
		CallableStatement stmt = getConnection().prepareCall("{CALL PROC_SELECT_USERS_REQUESTS(?, ?)}");
		stmt.setInt(1, userId);
		stmt.registerOutParameter(2, OracleTypes.CURSOR);
		return stmt;
	}
	
	static CallableStatement updateUserPassword(int userId, String newPassword) throws SQLException, IOException {
		CallableStatement stmt = getConnection().prepareCall("{CALL PROC_UPDATE_USER_PASSWORD(?, ?)}");
		stmt.setInt(1, userId);
		stmt.setString(2, newPassword);
		return stmt;
	}
	
	static CallableStatement updateRequestApproved(int requestId, int approved) throws SQLException, IOException {
		CallableStatement stmt = getConnection().prepareCall("{CALL PROC_REQUESTS_UPDATE_APPROVED(?, ?)}");
		stmt.setInt(1, requestId);
		stmt.setInt(2, approved);
		return stmt;
	}
	
	private static Connection getConnection() throws SQLException, IOException {
		return DataHandler.getInstance().getConnection();
	}
	
	private ErsDatabaseProcedures() { }
}

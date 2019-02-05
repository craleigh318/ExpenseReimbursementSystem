package com.revature.craleigh318.ers.data;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class ErsDao {

	public static void registerUser(String username, String temporaryPassword) throws SQLException, IOException {
		ErsDatabaseProcedures.insertEmployee(username, temporaryPassword).execute();
	}
	
	public static int selectUserId(String username) throws SQLException, IOException {
		CallableStatement stmt = ErsDatabaseProcedures.selectUserId(username);
		stmt.execute();
		return stmt.getInt(2);
	}
	
	public static boolean compareUserPassword(int userId, String enteredPassword) throws SQLException, IOException {
		CallableStatement stmt = ErsDatabaseProcedures.compareUserPassword(userId, enteredPassword);
		stmt.execute();
		return stmt.getBoolean(3);
	}
	
	private ErsDao() { }
}

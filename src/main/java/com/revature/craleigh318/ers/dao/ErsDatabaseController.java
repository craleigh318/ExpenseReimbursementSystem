package com.revature.craleigh318.ers.dao;

import java.io.IOException;
import java.sql.SQLException;

public class ErsDatabaseController {

	public static void registerUser(String username, String temporaryPassword) throws SQLException, IOException {
		ErsDatabaseProcedures.insertEmployee(username, temporaryPassword).execute();
	}
	
	private ErsDatabaseController() { }
}

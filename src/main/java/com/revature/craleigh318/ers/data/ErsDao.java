package com.revature.craleigh318.ers.data;

import java.io.IOException;
import java.sql.SQLException;

public class ErsDao {

	public static void registerUser(String username, String temporaryPassword) throws SQLException, IOException {
		ErsDatabaseProcedures.insertEmployee(username, temporaryPassword).execute();
	}
	
	private ErsDao() { }
}

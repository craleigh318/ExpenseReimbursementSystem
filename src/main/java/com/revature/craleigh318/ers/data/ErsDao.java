package com.revature.craleigh318.ers.data;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.revature.craleigh318.ers.model.ReimbursementRequest;

import oracle.jdbc.OracleTypes;

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
		int intComparison = stmt.getInt(3);
		return (intComparison != 0);
	}
	
	public static Map<Integer, ReimbursementRequest> getAllReimbursementRequests() throws SQLException, IOException {
		CallableStatement stmt = ErsDatabaseProcedures.selectAllReimbursementRequests();
		stmt.execute();
		ResultSet rs = (ResultSet) stmt.getObject(1);
		return getMapForAllReimbursementRequests(rs);
	}
	
	private static Map<Integer, ReimbursementRequest> getMapForAllReimbursementRequests(ResultSet resultSet) throws SQLException {
		TreeMap<Integer, ReimbursementRequest> map = new TreeMap<>();
		while (resultSet.next()) {
			Integer requestId = resultSet.getInt(1);
			int userId = resultSet.getInt(2);
			BigDecimal amount = resultSet.getBigDecimal(3);
			Date requestDate = resultSet.getDate(4);
			String description = resultSet.getString(5);
			Object objApproved = resultSet.getObject(6);
			Boolean approved = integerObjectToBoolean(objApproved);
			ReimbursementRequest reimbursementRequest = new ReimbursementRequest(userId, amount, requestDate, description, approved);
			map.put(requestId, reimbursementRequest);
		}
		return map;
	}
	
	private static Boolean integerObjectToBoolean(Object object) {
		Integer bigInteger = (Integer) object;
		if (bigInteger != null) {
			return bigInteger != 0;
		} else {
			return null;
		}
	}
	
	private ErsDao() { }
}

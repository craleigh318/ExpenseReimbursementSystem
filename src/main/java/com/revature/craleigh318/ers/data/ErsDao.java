package com.revature.craleigh318.ers.data;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import com.revature.craleigh318.ers.model.ReimbursementRequest;

public class ErsDao {

	public static void registerUser(String username, String temporaryPassword) throws SQLException, IOException {
		ErsDatabaseProcedures.insertEmployee(username, temporaryPassword).execute();
	}
	
	public static void requestReimbursement(int userId, BigDecimal amount, LocalDate requestDate, String description) throws SQLException, IOException {
		Date sqlRequestDate = Date.valueOf(requestDate);
		ErsDatabaseProcedures.insertReimbursementRequest(userId, amount, sqlRequestDate, description);
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
		return getMapForAllReimbursementRequests(rs, -1);
	}
	
	public static Map<Integer, ReimbursementRequest> getReimbursementRequestsForUser(int userId) throws SQLException, IOException {
		CallableStatement stmt = ErsDatabaseProcedures.selectUsersReimbursementRequests(userId);
		stmt.execute();
		ResultSet rs = (ResultSet) stmt.getObject(1);
		return getMapForAllReimbursementRequests(rs, userId);
	}
	
	public static boolean userIsManager(int userId) throws SQLException, IOException {
		CallableStatement stmt = ErsDatabaseProcedures.userIsManager(userId);
		stmt.execute();
		int intComparison = stmt.getInt(2);
		return (intComparison != 0);
	}
	
	private static Map<Integer, ReimbursementRequest> getMapForAllReimbursementRequests(ResultSet resultSet, int existingUserId) throws SQLException {
		TreeMap<Integer, ReimbursementRequest> map = new TreeMap<>();
		while (resultSet.next()) {
			int i = 1;
			Integer requestId = resultSet.getInt(i++);
			int userId;
			if (existingUserId < 0) {
				userId = resultSet.getInt(i++);
			} else {
				userId = existingUserId;
			}
			BigDecimal amount = resultSet.getBigDecimal(i++);
			Date requestDate = resultSet.getDate(i++);
			String description = resultSet.getString(i++);
			Object objApproved = resultSet.getObject(i++);
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

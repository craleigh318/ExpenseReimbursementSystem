package com.revature.craleigh318.ers.data;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class ErsDaoTest {

	@Test
	void testGetAllReimbursementRequests() {
		try {
			System.out.println(ErsDao.getAllReimbursementRequests());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}

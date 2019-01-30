package com.revature.craleigh318.expense_reimbursement_system;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Main extends HttpServlet {

	static final long serialVersionUID = 2654788560284698787L;
	private static final String SEGMENT_1 = "<!DOCTYPE html><html><head><title>Expense Reimbursement System</title></head><body><h1>Register New Employee</h1><form><table><tr><td>Username:</td><td><input type=\"text\" /></td></tr><tr><td>Temporary Password:</td><td><input type=\"password\" /></td></tr></table><input type=\"submit\" value=\"Register\" /></form>";
	private static final String SEGMENT_2 = "</body></html>";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//super.doGet(req, resp);
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		writer.print(SEGMENT_1 + SEGMENT_2);
	}
}

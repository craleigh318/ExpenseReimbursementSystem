package com.revature.craleigh318.ers.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletResponse;

public class ErsView {
	
	private static final String OUTER_HTML = "<!DOCTYPE html><html><head><title>Expense Reimbursement System</title></head><body>%s</body></html>";

	public static String addOuterHtml(String innerHtml) {
		return String.format(OUTER_HTML, innerHtml);
	}

	public static void writeHtml(ServletResponse response, String html) throws IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print(html);
	}
	
	private ErsView() { }
}

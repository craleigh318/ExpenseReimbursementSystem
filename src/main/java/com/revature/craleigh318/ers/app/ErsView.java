package com.revature.craleigh318.ers.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletResponse;

public class ErsView {

	public static void writeHtml(ServletResponse response, String html) throws IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print(html);
	}
	
	private ErsView() { }
}

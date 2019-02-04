package com.revature.craleigh318.ers.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IErsViewController {
	public void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}

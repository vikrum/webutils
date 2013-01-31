package com.flourishworks.webutils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractRESTServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AbstractRESTServlet() {
		super();
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addCorsHeaders(req, resp);
	}
	
	protected void addCorsHeaders(HttpServletRequest req, HttpServletResponse resp) {
		String allowMethods = "OPTIONS,GET,PUT,POST,DELETE,TRACE";
		resp.setHeader("Allow", allowMethods);
		resp.setHeader("Access-Control-Allow-Methods", allowMethods);
		if(req.getHeader("Origin") != null) {
			resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));	
		}
		else {
			resp.setHeader("Access-Control-Allow-Origin", "*");
		}
	}

}
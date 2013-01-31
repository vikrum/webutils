package com.flourishworks.webutils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class RequestDumperServlet extends AbstractRESTServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeRequest(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeRequest(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeRequest(req, resp);
	}
	
	@Override
	protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeRequest(req, resp);
	}
	
	private void writeRequest(HttpServletRequest req, HttpServletResponse resp) {
		addCorsHeaders(req, resp);
		resp.setContentType("text/plain");
		PrintWriter writer;
		try {
			writer = resp.getWriter();
			writer.println("Addr: " + req.getRemoteAddr());
			writer.print("Path: " + req.getRequestURI());
			if(req.getQueryString() != null) {
				writer.println("?" + req.getQueryString());
			}
			else {
				writer.println();
			}
			
			@SuppressWarnings("rawtypes")
			Enumeration headerNames = req.getHeaderNames();
			while(headerNames.hasMoreElements()) {
				String name = (String)headerNames.nextElement();
				@SuppressWarnings("rawtypes")
				Enumeration values = req.getHeaders(name);
				if(values != null) {
					while(values.hasMoreElements()) {
						String value = (String)values.nextElement();
						writer.println(name + ": " + value);
					}
				}
				else {
					writer.println(name + ": (null)");
				}
			}
			writer.flush();
			writer.close();
		}
		catch (Exception e) {
		}
	}
	
}

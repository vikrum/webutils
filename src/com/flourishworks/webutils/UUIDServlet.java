package com.flourishworks.webutils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONObject;


@SuppressWarnings("serial")
public class UUIDServlet extends AbstractRESTServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeUuid(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeUuid(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeUuid(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeUuid(req, resp);
	}
	
	@Override
	protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeUuid(req, resp);
	}
	
	private void writeUuid(HttpServletRequest req, HttpServletResponse resp) {
		addCorsHeaders(req, resp);
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter writer;
		try {
			writer = resp.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("uuid", UUID.randomUUID().toString());
			writer.print(jsonObject);
			writer.flush();
			writer.close();
		}
		catch (Exception e) {
		}		
	}
}


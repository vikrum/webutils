package com.flourishworks.webutils;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class IPDumperServlet extends AbstractRESTServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeIp(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeIp(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeIp(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeIp(req, resp);
	}
	
	@Override
	protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeIp(req, resp);
	}
	
	private void writeIp(HttpServletRequest req, HttpServletResponse resp) {
		addCorsHeaders(req, resp);
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter writer;
		try {
			writer = resp.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("remoteaddr", req.getRemoteAddr());
			writer.print(jsonObject.toString());
			writer.flush();
			writer.close();
		}
		catch (Exception e) {
		}		
	}
}


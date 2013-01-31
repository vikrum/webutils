package com.flourishworks.webutils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class DNSServlet extends AbstractRESTServlet {
	
	private static final String baseHost = "webutils.flourishworks.com";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeDns(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeDns(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeDns(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeDns(req, resp);
	}
	
	@Override
	protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		writeDns(req, resp);
	}
	
	private void writeDns(HttpServletRequest req, HttpServletResponse resp) {
		addCorsHeaders(req, resp);
		if(baseHost.equals(req.getHeader("Host"))) {
			Date date = new Date();
			SecureRandom secureRandom = new SecureRandom();
			StringBuilder redir = new StringBuilder();
			redir.append("http://").append("t").append(date.getTime()).append(".").append("r").append(secureRandom.nextLong()).append(".").append(baseHost).append("/dns");
			try {
				resp.sendRedirect(redir.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			resp.setContentType("application/json; charset=utf-8");
			PrintWriter writer;
			try {
				writer = resp.getWriter();
				  URL url = new URL("http://firedns.firebaseio.com/" + getHashForHost(req) + "/.json");
		            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		            String line;
		            while ((line = reader.readLine()) != null) {
		            	writer.println(line);
		            }
		            reader.close();	            
				writer.flush();
				writer.close();
			}
			catch (Exception e) {
			}					
		}
	}
	
	private String getHashForHost(HttpServletRequest req) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	    byte[] hash = md.digest(req.getHeader("Host").getBytes());
	    StringBuffer sb = new StringBuffer();
	    for(byte b : hash) {
	    	sb.append(String.format("%02x", b));
	    }
	    return sb.toString();
	}
}


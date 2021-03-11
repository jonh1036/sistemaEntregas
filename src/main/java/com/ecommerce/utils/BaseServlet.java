package com.ecommerce.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 7615518155177459511L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        super.doGet(req, resp);
    }
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		super.doPost(req, resp);
	}

}

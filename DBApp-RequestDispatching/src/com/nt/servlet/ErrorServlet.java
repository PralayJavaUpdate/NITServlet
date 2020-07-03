package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/errorurl")
public class ErrorServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("ErrorServlet.doGet()");
		PrintWriter pw=null;
		//get Printwriter
		pw=res.getWriter();
		//set response content type
		res.setContentType("text/html");
		//display messages
		pw.println("<h1 style='color:red;text-align:center'>Internal problem--Please try again</h1>");
		pw.println("<br><a href='input.html'>Home</a>");
		
		//close the stream
		pw.close();
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}

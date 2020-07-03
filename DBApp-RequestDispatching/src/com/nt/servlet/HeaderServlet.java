package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/headerurl")
public class HeaderServlet extends HttpServlet {
  
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		
		//get the PrintWriter
		pw=res.getWriter();
		
		//set ContentType
		res.setContentType("text/html");
		
		pw.println("<h1 style='color:red;text-align:center'><marquee>NARESHIT</marquee></h1><hr>");
		//do not close the stream
		//pw.close();
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}

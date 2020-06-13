package com.nt.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class  DateServlet extends HttpServlet
	{
			protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			PrintWriter pw=null;
			//get PrintWriter 
			pw=res.getWriter();
			//set response content type
			res.setContentType("text/html");
			//get System date and time (request processing logic)
				
			pw.println("<h1 style='color:blue;tesxt:center'>Date and time::"+ new java.util.Date()+"</br></h1>");
			res.setHeader("refresh","2");

			//close stream
			pw.close();
	   }
	}
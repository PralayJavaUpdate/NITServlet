package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WishServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		PrintWriter pw=null;
		Calendar calendar=null;
		int hour=0;
		//get PrintWriter stream
		pw=res.getWriter();
		//set contentType
		res.setContentType("text/html");
		//get system date
		calendar=Calendar.getInstance();
		hour=calendar.get(Calendar.HOUR_OF_DAY);
		if(hour<012)
			pw.println("<h1 style='color:red'>Good Morning</h1>");
		else if(hour<017)
			pw.println("<h1 style='color:green'>Good Afternoon</h1>");
		else if(hour<20)
			pw.println("<h1 style='color:yellow'>Good Evening</h1>");
		else
			pw.println("<h1 style='color:yellow'>Good Night</h1>");
		
		//generate the Hyperlink
		pw.println("<br><a href='http://localhost:3030/WishApp/page.html'>Home</a>");
		
		//close stream
		pw.close();
		
	}//service(-,-)
}//class

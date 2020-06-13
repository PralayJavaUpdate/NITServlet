package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VoterServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		PrintWriter pw=null;
		String name=null,tage=null;
		int age=0;
		int hour=0;
		//get PrintWriter stream
		pw=res.getWriter();
		//set contentType
		res.setContentType("text/html");
		//get the request parameter value from form data
		name=req.getParameter("name");
		tage=req.getParameter("age");
		age=Integer.parseInt(tage);
		
		//write business logic
		if(age>18)
			pw.println(name+"<h1 style='color:green'> You are eligible for vote</h1>");
		else
			pw.println(name+"<h1 style='color:red'> You are not eligible for vote</h1>");
		
		//generate the Hyperlink
		pw.println("<br><a href='input.html'><image src='vote.jpg' width='100' height='100'></a>");
		
		//close stream
		pw.close();
		
	}//service(-,-)
}//class

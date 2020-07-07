package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/searchurl2")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		PrintWriter pw=null;
		String ss=null,s_engine=null;
		String url=null;
		//get PrintWriter object
		pw=res.getWriter();
		//set response ContentType
		res.setContentType("text/html");
		//read form data
		ss=req.getParameter("ss");
		s_engine=req.getParameter("s_engine");
		//prepare URL based on the selected search engine
		if(s_engine.equals("google"))
			url="https://www.google.com/search?q="+ss;
		else if(s_engine.equals("yahoo"))
			url="https://www.bing.com/search?q="+ss;
		else if(s_engine.equals("bing"))
			url="https://in.search.yahoo.com/search?p="+ss;
		//perform sendRedirection 
		System.out.println("Befoe res.sendRedirect(-) method");
		pw.println("<b>Before</b>");
		res.sendRedirect(url);
		System.out.println("After res.sendRedirect(-) method");
		pw.println("<b>After</b>");
		
		
		//res.sendRedirect(url);
		RequestDispatcher rd=req.getRequestDispatcher("/xyz.html");
		rd.include(req,res);
		
		
		//close stream
		pw.close();
	}	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}

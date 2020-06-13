package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/LinkServlet")
public class LinkServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String link=null;
		Locale locales[]=null;
		//get PrintWriter
		pw=res.getWriter();
		//set contentType to browser
		res.setContentType("text/html");
		//read "s1" req param value to know the hyperlink that is clicked
		link=req.getParameter("s1");
		if(link.equalsIgnoreCase("link1")){
			//get all language
			locales=Locale.getAvailableLocales();
			for(Locale lc:locales){
				pw.println(lc.getDisplayLanguage()+"<br>");
			}
		}
		else if(link.equalsIgnoreCase("link2")){
			//get all countries
			locales=Locale.getAvailableLocales();
			for(Locale lc:locales){
				pw.println(lc.getDisplayCountry()+"<br>");
			}
		}
		else{
			pw.println("Date and Time: "+new Date());
		}
		//add hyperlink
		pw.println("<h1 style='color:red'><a href='page.html'>Home</h1>");
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}

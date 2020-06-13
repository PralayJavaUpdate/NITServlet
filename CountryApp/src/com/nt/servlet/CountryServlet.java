package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@WebServlet("/countryurl")
public class CountryServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		String IndiaStates[]={"WB","AP","TS","MP","UP"};
		String UsStates[]={"CA","LA","NY","CR","AZ"};
		String AustraliaStates[]={"TMS","BB","CBR"};
		String country=null;
		
		//read the form data
		country=req.getParameter("country");
		//get PrintWriter stream from res object
		PrintWriter pw=res.getWriter();
		//Set contentType
		res.setContentType("text/html");
		//business logic
		if(country.equals("India"))
			pw.println("states="+Arrays.toString(IndiaStates));
		else if(country.equals("us"))
			pw.println("states="+Arrays.toString(UsStates));
		else
			pw.println("states="+Arrays.toString(AustraliaStates));
		pw.println("<br><br><br><b><a href='page.html' style='color:red'>Home</a></b>");
	
	//close the stream
	pw.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		doGet(req,res);
	}
}
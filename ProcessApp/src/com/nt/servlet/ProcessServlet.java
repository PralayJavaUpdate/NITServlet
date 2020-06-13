package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/processurl")
public class ProcessServlet extends HttpServlet {
	
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String comp=null;
		int val1=0,val2=0;
    	//getPrintWriter stream
    	pw=res.getWriter();
    	//set contentType
    	res.setContentType("text/html");
    	
    	//read s1 req param value to know comps that are used to send the req
    	comp=req.getParameter("s1");
    	if(comp.equals("link1")){ //sys date
    		pw.println("System Date and Time: "+ new Date());
    	}
    	else if(comp.equals("link2")){
    		pw.println("System Properties: "+System.getProperties());
    	}
    	else if(comp.equals("Add")){
    		val1=Integer.parseInt(req.getParameter("v1"));
    		val2=Integer.parseInt(req.getParameter("v2"));
    		pw.println("Addition: "+ (val1+val2));
    	}
    	else if(comp.equals("Sub")){
    		val1=Integer.parseInt(req.getParameter("v1"));
    		val2=Integer.parseInt(req.getParameter("v2"));
    		pw.println("Substraction: "+ (val1-val2));
    	}

    	else{
    		val1=Integer.parseInt(req.getParameter("v1"));
    		val2=Integer.parseInt(req.getParameter("v2"));
    		pw.println("Multiplication: "+ (val1*val2));
    	}
    	
    	//Add hyperlink
    	pw.println("<h1 style='color:req'><a href='form.html'>Home</a></h1>");

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}

package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/LCTestServlet")
public class LCTestServlet extends HttpServlet {
	
	static{
		System.out.println("LCTestServlet: static block");
	}
    public LCTestServlet() {
        System.out.println("LCTestServlet: 0-param constructor");
    }

    @Override
    public void init(ServletConfig cg) throws ServletException {
    	System.out.println("LCTestServlet: init(ServletConfig cg)");
    	System.out.println("Logical name of our servlet class"+cg.getServletName());
    	System.out.println("input param email value"+cg.getInitParameter("email"));
    }
    
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
    	//set contentType
    	res.setContentType("text/html");
    	
    	//get PrintWriter
    	PrintWriter pw=res.getWriter();
    	
    	//write response
    	Date d=new Date();
    	pw.println("<b><i><center>Date and Time is "+ d +"</center></i></b>");
    	
    	//close stream 
    	pw.close();
    }
    
    public void destroy(){
    	System.out.println("LCTestServlet: destroy()");
    }
    
    
}

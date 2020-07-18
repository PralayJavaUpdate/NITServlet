package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null,ms=null;
		int age=0;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form1 /req1 data
		name=req.getParameter("pname");
		age=Integer.parseInt(req.getParameter("page"));
		ms=req.getParameter("ms");
		if(ms==null)
			ms="single";
		//generate form2 dynamically based marital status
		if(ms.equalsIgnoreCase("married")) {
			//generate dynamic form here
			pw.println("<h1 style='color:red'> Enter Marriage life details </h1>");
			pw.println("<form  action='secondurl' method='POST'>");
			pw.println(" spouse name:: <input type='text' name='f2t1'><br>");
			pw.println(" no.of kids :: <input type='text' name='f2t2'><br>");
			
			pw.println("<input type='hidden' name='hname' value="+name+"><br>");
			pw.println("<input type='hidden' name='hage'value="+age+"><br>");
			pw.println("<input type='hidden' name='ms'value="+ms+"><br>");
			pw.println("<input type='submit' value='register'>");
			pw.println("</form>");
		}
		else {
			//generate dynamic form here
			pw.println("<h1 style='color:red'> Enter Golden  life details </h1>");
			pw.println("<form  action='secondurl' method='POST'>");
			pw.println("when do u want to marry?:: <input type='text' name='f2t1'><br>");
			pw.println(" why do u want to marry?:: <input type='text' name='f2t2'><br>");
			pw.println("<input type='submit' value='register'>");
			pw.println("</form>");
		}
		//close stream
		pw.close();
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}
}

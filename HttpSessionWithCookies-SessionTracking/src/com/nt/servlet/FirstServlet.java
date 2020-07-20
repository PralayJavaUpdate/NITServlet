package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/furl")
public class FirstServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		
		//create PrintWriter object
		pw=res.getWriter();
		//set ContentType
		res.setContentType("text/html");
		
		//read req1/form1 data
		String name=req.getParameter("name");
		String addrs=req.getParameter("addrs");
		int age=Integer.parseInt(req.getParameter("age"));
		
		//create session object for browser
		HttpSession session=req.getSession(true);
		
		//keep req1/form1 data into session attribute
		session.setAttribute("name", name);
		session.setAttribute("addrs", addrs);
		session.setAttribute("age", age);
		
		//create form2 dynamically
		pw.println("<body bgcolor='lightblue'>");
		pw.println("<form action='surl' method='POST'>");
		pw.println("<h2><center><font color='sky'>PERSONAL DETAILS</font></center></h2>");
		
		pw.println("<br><table border=0 align='center' bgcolor='pink'>");
		pw.println("<tr>");
		pw.println("<td>Enter no of Experience(year): </td>" + "<td><input type='text' name='exp'></td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<br><td>Select Skills: </td>" + "<td><select name='skills'>");
		pw.println("<option value='JAVA'>JAVA/J2EE</option>");
		pw.println("<option value='.NET'>.Net</option>");
		pw.println("<option value='ORACLE'>ORACLE</option>");
		pw.println("</select>");
		pw.println("</td></tr>");
		
		pw.println("<tr><td>");
		pw.println("<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		pw.println("<input type='submit' value='Continue'>");
		pw.println("</td></tr>");
		
		pw.println("</table></form></body>");
		
		//close stream
		pw.close();
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}

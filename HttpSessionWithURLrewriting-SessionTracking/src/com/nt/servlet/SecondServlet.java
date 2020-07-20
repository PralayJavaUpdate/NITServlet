package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/surl")
public class SecondServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		
		//create PrintWriter object
		pw=res.getWriter();
		//set ContentType
		res.setContentType("text/html");
		
		//read req2/form2 data
		int exp=Integer.parseInt(req.getParameter("exp"));
		String skills=req.getParameter("skills");
		
		//get access to session object
		HttpSession session=req.getSession(false);
		
		String name=(String)session.getAttribute("name");
		String addrs=(String)session.getAttribute("addrs");
		int age=(int)session.getAttribute("age");
		pw.println(name);
		pw.println(addrs);
		pw.println(age);

		//keep req2/form2 data into session attribute
		session.setAttribute("exp", exp);
		session.setAttribute("skills", skills);
		
		//create form2 dynamically
		pw.println("<body bgcolor='lightblue'>");
		//pw.println("<form action='turl' method='POST'>");
		pw.println("<form action="+res.encodeURL("turl")+" method='POST'>");
		pw.println("<h2><center><font color='sky'>Provide City and Salary</font></center></h2>");
		
		pw.println("<br><table border=0 align='center' bgcolor='pink'>");
		pw.println("<tr>");
		pw.println("<td>Enter preference City: </td>" + "<td><input type='text' name='city'></td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td>Enter expected Salary: </td>" + "<td><input type='text' name='salary'></td>");
		pw.println("</tr>");
		
		pw.println("<tr><td>");
		pw.println("<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		pw.println("<input type='submit' value='Submit'>");
		pw.println("</td></tr>");
		
		pw.println("</table></form></body>");
		
		//close stream
		pw.close();
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}


}

//PlainServlet.java
package com.nt.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ExcelServlet extends HttpServlet 
{
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		//get PrintWriter 
		pw=res.getWriter();
		//set response content type
		res.setContentType("application/vnd.ms-excel");
		//write output message to response obj
		pw.println("<table border='1' align='center'>");
		pw.println("<tr><th>Politician</th><th>Party </th><th>Role</th></tr>");
		pw.println("<tr><td>Modi</td><td>BJP </td><td> PM </td></tr>");
		pw.println("<tr><td>Amit Sha</td><td>BJP </td><td> Chanakya </td></tr>");
		pw.println("<tr><td>Rahul Gandhi</td><td>Congress </td><td> MP </td></tr>");
		pw.println("<tr><td>Arvindh Kejriwal</td><td>AAP </td><td> CM </td></tr>");
		pw.println("</table>");
		//close stream
		pw.close();
	}//service(-,-)
}//class

package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null,ms=null;
		String age=null;
		String f2val1=null,f2val2=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form1 /req1 data
		name=req.getParameter("hname");
		age=req.getParameter("hage");
		ms=req.getParameter("ms");
		//read form2/res2 data
		f2val1=req.getParameter("f2t1");
		f2val2=req.getParameter("f2t2");
		//display  form1/req1 data and form2/req2 data
		pw.println("<b> from1/req1 data::</b>"+name+"..."+age+"..."+ms);
		pw.println("<br><b> from2/req2 data::</b>"+f2val1+"..."+f2val2);
		//add hyperlink
		pw.println("<br><h1 style='color:red'><a href='details.html'>home</a></h1>");
		//close stream
		pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}
}

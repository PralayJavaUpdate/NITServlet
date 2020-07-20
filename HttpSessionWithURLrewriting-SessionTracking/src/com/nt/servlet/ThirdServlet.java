package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/turl")
public class ThirdServlet extends HttpServlet {
	private static final String insert_info="INSERT INTO INFO VALUES(?,?,?,?,?,?,?)";
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		
		//create PrintWriter object
		pw=res.getWriter();
		//set ContentType
		res.setContentType("text/html");
		
		//read req3/form3 data
		String city=req.getParameter("city");
		int sal=Integer.parseInt(req.getParameter("salary"));
		
		//get access to session object
		HttpSession session=req.getSession(false);
		
		//keep req3/form3 data into session attribute
		String name=(String)session.getAttribute("name");
		String addrs=(String)session.getAttribute("addrs");
		int age=(int)session.getAttribute("age");
		int exp=(int)session.getAttribute("exp");
		String skills=(String)session.getAttribute("skills");

		
		//insert from1/req1, form2/req2, form3/req3 values into DB
		try {
			//register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SCOTT","TIGER");
			
			//create PreparedStatement object
			PreparedStatement ps=con.prepareStatement(insert_info);
			
			//set values to query string
			ps.setString(1, name);
			ps.setString(2, addrs);
			ps.setInt(3, age);
			ps.setInt(4, exp);
			ps.setString(5, skills);
			ps.setString(6, city);
			ps.setInt(7, sal);
			
			//send and execute SQL query
			int count=ps.executeUpdate();
			
			//invalidate the session
			session.invalidate();
			
			//process the result
			if(count>0) {
				pw.println("<h1><b><center><font color='green' >Sucessfully Inserted</font></center></b></h1>");
				pw.println("<a href='personal.html'>Home</a>");
			}
			else {
				pw.println("<h1><b><center><font color='red'>Please Try Again..</font></center></b></h1>");
				pw.println("<a href='personal.html'>Home</a>");
			}
			
			//close stream
			pw.close();
			
		}
		catch(Exception e) {
			
		}
		
		//close stream
		pw.close();
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}

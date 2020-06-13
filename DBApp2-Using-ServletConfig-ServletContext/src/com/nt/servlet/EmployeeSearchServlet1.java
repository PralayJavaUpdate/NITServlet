package com.nt.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeSearchServlet1 extends HttpServlet {
	//prepare SQL query
	private static final String GET_EMP_DETAILS="SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE EMPNO=?";
	
	PrintWriter pw=null;
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	public void init(){
		
		try{
			//Get access to ServletConfig object
			ServletContext sc=getServletContext();
			
			//read the init-param value from web.xml
			String s1=sc.getInitParameter("driver");
			String s2=sc.getInitParameter("dburl");
			String s3=sc.getInitParameter("dbusername");
			String s4=sc.getInitParameter("dbpwd");
			
			//register JDBC driver class
			Class.forName(s1);
			
			//establish the connection
			con=DriverManager.getConnection(s2,s3,s4);
			
			//create PreparedStatement object
			ps=con.prepareStatement(GET_EMP_DETAILS);
			
		}
		catch(SQLException se){
			se.printStackTrace();
			pw.println("<h1 style='color:red'>Internal DB problem</h1>");
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
			pw.println("<h1 style='color:red'>Internal problem</h1>");
		}
		catch(Exception e){
			e.printStackTrace();
			pw.println("<h1 style='color:red'>Internal problem</h1>");
		}
		
		
		
	}//init(-)
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try{
			//read the form data from form page
			int no=Integer.parseInt(req.getParameter("empno"));
			
			//get PrintWriter stream
			pw=res.getWriter();
			
			//set the contentType
			res.setContentType("text/html");
			
			
			
			//set values to parameter of the query
			ps.setInt(1,no);
			
			//send and execute the query
			rs=ps.executeQuery();
			
			//get the result
			//process the Result
			if(rs.next()){
				pw.println("<h1>Employee Details are</h1>");
				pw.println("<b>Emp No:: </b>"+rs.getInt(1)+"<br>");
				pw.println("<b>Emp Name:: </b>"+rs.getString(2)+"<br>");
				pw.println("<b>Emp Job:: </b>"+rs.getString(3)+"<br>");
				pw.println("<b>Emp Salary:: </b>"+rs.getFloat(4)+"<br>");
				pw.println("<b>Emp Dept No:: </b>"+rs.getInt(5)+"<br>");
			}
			else{
				pw.println("<h1 style='color:red'>Employee not found</h1>");
			}
			
			//close the stream
			rs.close();
		}
		catch(SQLException se){
			se.printStackTrace();
			pw.println("<h1 style='color:red'>Internal DB problem</h1>");
		}
		catch(Exception e){
			e.printStackTrace();
			pw.println("<h1 style='color:red'>Internal problem</h1>");
		}
	}//doGet(-,-)
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
	public void destroy(){
		//close JDBC objects
		
		try{
			if(ps!=null)
				ps.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		try{
			if(con!=null)
				con.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		try{
			if(con!=null)
				con.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		
		//home hyperlink
		pw.println("<br><br><a href='input.html'>Home</a>");
	}//destroy()

}//class

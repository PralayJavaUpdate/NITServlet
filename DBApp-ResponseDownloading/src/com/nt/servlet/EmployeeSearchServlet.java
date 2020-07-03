package com.nt.servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;//jdbc api


public class EmployeeSearchServlet extends HttpServlet {
	private static final String GET_EMP_DETAILS="SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE EMPNO=?";
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		int eno=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			//printWriter object
			pw=res.getWriter();
			
			//set contentType
			res.setContentType("application/msword");
			//set response from web comp as downloadable file
			res.addHeader("Content-Disposition", "attachment;fileName=result.doc");
			
			//read from data
			eno=Integer.parseInt(req.getParameter("eno"));
			
			//register JDBC driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SCOTT","TIGER");
			
			//create prepareStatement object having pre-compiled query
			if(con!=null)
				ps=con.prepareStatement(GET_EMP_DETAILS);
			
			//set query param value
			ps.setInt(1, eno);
				
			//send and execute query
			if(ps!=null)
				rs=ps.executeQuery();
			
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
		}//try
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
		
		finally{
			//close JDBC objects
			try{
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
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
			try{
				if(pw!=null)
					pw.close();
			}
			catch(Exception ioe){
				ioe.printStackTrace();
			}
			//home hyperlink
			pw.println("<br><br><a href='input.html'>Home</a>");
			
		}//finally
	}//doGet(-,-)
			

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

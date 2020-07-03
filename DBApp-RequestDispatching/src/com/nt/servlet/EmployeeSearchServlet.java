package com.nt.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class EmployeeSearchServlet extends HttpServlet {
	//prepare SQL query
	private static final String GET_EMP_DETAILS="SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE EMPNO=?";
	
	
		
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("EmployeeSearchServlet.doGet()");
		PrintWriter pw=null;
		int no=0;
		ServletConfig cg=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		RequestDispatcher rd=null, rd1=null, rd2=null;
		String s1=null,s2=null,s3=null,s4=null;
		try{
			//get PrintWriter stream
			pw=res.getWriter();
			
			//set the contentType
			res.setContentType("text/html");
			
			//Add header content
			 rd1=req.getRequestDispatcher("/headerurl");
			 rd1.include(req,res);
			
			//read the form data from form page
			no=Integer.parseInt(req.getParameter("eno"));
			
			//Get access to ServletConfig object
			cg=getServletConfig();
			
			//read the init-param value from web.xml
			s1=cg.getInitParameter("driver");
			s2=cg.getInitParameter("dburl");
			s3=cg.getInitParameter("dbusername");
			s4=cg.getInitParameter("dbpwd");
			pw.println("<b>Searching and giving detail</b><br>");
			//register JDBC driver class
			Class.forName(s1);
			
			//establish the connection
			con=DriverManager.getConnection(s2,s3,s4);
			
			//create PreparedStatement object
			ps=con.prepareStatement(GET_EMP_DETAILS);
						
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
				pw.println("<h1 style='color:red; text-align:center'>Employee not found</h1>");
			}
			
			//Add footer content
			 rd2=req.getRequestDispatcher("/footer.html");
			 rd2.include(req,res);
			
		}//try
		
		
		  catch(Exception se){ 
			 rd=req.getRequestDispatcher("/errorurl");
			 System.out.println("EmployeeSearchServlet.doGet() ---before rd.forward(-,-)");
			 pw.println("<b>EmployeeSearchServlet.doGet() ---before rd.forward(-,-)</b>");
			 rd.forward(req, res);
			 System.out.println("EmployeeSearchServlet.doGet() ---after rd.forward(-,-)");
			 pw.println("<b>EmployeeSearchServlet.doGet() ---after rd.forward(-,-)</b>");
		  }
		 
		 
		
		/*
		 * catch(SQLException se){ se.printStackTrace();
		 * pw.println("<h1 style='color:red'>Internal DB problem</h1>"); }
		 * catch(ClassNotFoundException cnf){ cnf.printStackTrace();
		 * pw.println("<h1 style='color:red'>Internal problem</h1>"); } catch(Exception
		 * e){ e.printStackTrace();
		 * pw.println("<h1 style='color:red'>Internal problem</h1>"); }
		 */
		 
		finally {	
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

			
		}//finally()
		
	}//doGet(-,-)
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
		
		

}//class

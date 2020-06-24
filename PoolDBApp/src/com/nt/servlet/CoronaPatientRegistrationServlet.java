package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


//@WebServlet("/CoronaPatientRegistrationServlet")
public class CoronaPatientRegistrationServlet extends HttpServlet {
	private static final String INSERT_CORONA_QUERY="INSERT INTO corona_registration VALUES(corona_patId_Seq.NEXTVAL,?,?,?,?,?)";
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		PrintWriter pw=null;
		String patName=null,patAddrs=null,gender=null,stage=null;
		int age=0;
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
				
		//get PrintWriter object
		pw=res.getWriter();
		
		//set content type
		res.setContentType("text/html");
		
		//read form data
		patName=req.getParameter("patName");
		patAddrs=req.getParameter("patAddrs");
		gender=req.getParameter("gender");
		age=Integer.parseInt(req.getParameter("patAge"));
		stage=req.getParameter("stage");
		
		try {
			con=getPooledConnection();
		
			//create PreparedStatement object
			ps=con.prepareStatement(INSERT_CORONA_QUERY);
			
			//set values to query param
			ps.setString(1,patName);
			ps.setString(2,patAddrs);
			ps.setString(3,gender);
			ps.setInt(4,age);
			ps.setString(5,stage);

			//execute the quey
			count=ps.executeUpdate();
			
			//process the result
			if(count==0)
				pw.println("<h1 style='color:red; text-align:center'>Registration Failed!!!</h1>");
			else
				pw.println("<h1 style='color:green; text-align:center'>Registration Successed</h1>");
				
		}
		catch(SQLException se) {
			se.printStackTrace();
			pw.println("<h1 style='color:red; text-align:center'>!!!Registration Failed</h1>");
		}
		catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1 style='color:red; text-align:center'>Unknown Problems</h1>");
		}
		finally {
			//close Stream
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		pw.println("<h1><a href='register.html'>Home</a></h1>");
		try {
			if(pw!=null)
				pw.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGet(req, res);
	}
	
	private Connection getPooledConnection() throws Exception {
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		//Create initialContext object
		ic=new InitialContext();
		//get DataSource object through lookup operation
		//ds=(DataSource)ic.lookup("DsJndi"); //GlassFish5
		//ds=(DataSource)ic.lookup("java:/DsJndi"); //WildFly17
		ds=(DataSource)ic.lookup("java:/comp/env/DsJndi"); //Tomcat9
		//get Pooled JDBC con object
		con=ds.getConnection();
		return con;
	}

}

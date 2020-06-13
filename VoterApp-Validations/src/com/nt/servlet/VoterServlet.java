package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class VoterServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		PrintWriter pw=null;
		String vStatus=null, name=null, age=null ;
		
		int tage=0;
		//get PrintWriter stream
		pw=res.getWriter();
		//set contentType
		res.setContentType("text/html");
		//read the data from form page
		name=req.getParameter("pname");
		age=req.getParameter("page");
		vStatus=req.getParameter("vflag");
		
		if(vStatus.equals("no")){  //if client-side validations are not done
			//Server-side validation
				if(name.equals("") || name==null || name.length()==0){
					pw.println("<font color=red>Person name is mandatory</font>");
					return;
				}
				else {
					if(!name.matches("[a-zA-Z]+")){
						pw.println("<font color=red>Name must be only Character value</font>");
						return;
					}
				}
						
			if(age.equals("") || age==null || age.length()==0){
				pw.println("<font color=red>Person age is mandatory</font>");
				return;
			}
			else { //check whether age value is numeric or not
				try{
					//convert given age value to numeric value
					tage=Integer.parseInt(age);
				}
				catch (Exception e) {
					pw.println("<font color=red>Age must be numeric value</font>");
					return;
				}	
			}//else
			System.out.println("Server-side validations are done");
		}//if of server-side validation
		else{ //when client-side validation is done
			tage=Integer.parseInt(age);
		}//else of server-side validation
		
		//Write the business logic
		if(tage<18)
			pw.println(name+"<h1 style='color:red'> You are not eligible for vote</h1>");
		else
			pw.println(name+"<h1 style='color:green'> You are eligible for vote</h1>");
		
		//generate the Hyperlink
		pw.println("<br><a href='input.html'><image src='vote.jpg' width='100' height='100'></a>");
		
		//close the stream
		pw.close();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		doGet(req,res);
	}
}

package com.nt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dto.EmployeeDTO;
import com.nt.service.EmployeeMgmtService;
import com.nt.service.EmployeeMgmtServiceImpl;
import com.nt.vo.EmployeeVO;

@WebServlet("/controller")
public class MainControllerServlet extends HttpServlet {
	
	private EmployeeMgmtService service=null;
	@Override
	public void init() throws ServletException{
		service=new EmployeeMgmtServiceImpl(); 
	}
			
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//String name=null, addrs=null,doj=null,basicSalary=null;
		EmployeeVO vo=null;
		PrintWriter pw=null;
		EmployeeDTO dto=null;
		String result=null;
		//ServletOutputStream sos=null;
		
		//read form data store into VO class object
		vo=new EmployeeVO();
		vo.setEname(req.getParameter("ename"));
		vo.setEadd(req.getParameter("eadd"));
		vo.setDoj(req.getParameter("doj"));
		vo.setBasicSalary(req.getParameter("basicSalary"));
		
		//sos=res.getOutputStream();
		//get PrintWriter stream
		pw=res.getWriter();
		//set content type
		res.setContentType("text/html");
		
		
		//convert VO class object to DTO class object
		dto=new EmployeeDTO();
		dto.setEname(vo.getEname());
		dto.setEadd(vo.getEadd());
		dto.setBasicSalary(Float.parseFloat(vo.getBasicSalary()));
		dto.setDoj(java.sql.Date.valueOf(vo.getDoj()));
		
		pw.println(dto.getEname());
		pw.println(dto.getEadd());
		pw.println(dto.getBasicSalary());
		pw.println(dto.getDoj());
		
		//use service class
		try {
			result=service.registerEmployee(dto);
			pw.println("<h1 style='color:green; text-align:center'>Result::"+result+"</h1>");
		}//try
		catch(Exception e) {
			pw.println("Internal problem--please try again");
			e.printStackTrace();
		}
		
		//add hyperlink
		pw.println("<br><br><a href='employee_register.html'>Home</a>");
		//close stream
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}

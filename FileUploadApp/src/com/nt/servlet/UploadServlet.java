package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadParameters;

@WebServlet("/uploadurl")
public class UploadServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MultipartFormDataRequest nreq=null;
		UploadBean bean=null;
		Vector vector=null;
		UploadParameters param=null;
		//get PrintWriter obj
		PrintWriter pw=res.getWriter();
		//set content type
		res.setContentType("text/html");
		
		try {
			//create MultipartFormDataRequest object
			nreq=new MultipartFormDataRequest(req);
			
			//perform file uploading
			bean=new UploadBean();
			bean.setFolderstore("D:/store");
			
			//restriction
			bean.setOverwrite(false);
			bean.setMaxfiles(10);
			bean.setFilesizelimit(100*1024);
			bean.setBlacklist("*.exe,*.zip");
			
			//complete file uploading
			bean.store(nreq);
			pw.println("<h1 style='color:green'>Files are uploadedsuccessfully!!!..</h1>");
			
			//display the names of the uploaded files
			vector=bean.getHistory();
			for(int i=0;i<vector.size();++i) {
				param=(UploadParameters)vector.elementAt(i);
				pw.println("<br><b>File name: "+param.getFilename()+"size:"+param.getFilesize()+"type: "+param.getContenttype()+"model: "+param.getStoreinfo()+"</b>");
			}
		}
		catch(Exception e) {
			pw.println("<h2 style='color:red;text-align:center'>Problem in uploading--"+e.getMessage()+"</h2>");
			e.printStackTrace();
		}
		pw.println("<br><a href='upload.html'>Home</a>");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}

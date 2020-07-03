package com.nt.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.IO;

@WebServlet("/downloadurl")
public class DownloadServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		File f1=null;
		String fname=null;
		ServletContext sc=null;
		String mimeType=null;
		InputStream is=null;
		OutputStream os=null;
		
		
		
		//read file name to be download as additional req param value from hyperlinks
		fname=req.getParameter("filename");
		
		
		//locate the file location for downloading
		f1=new File("D:/store/"+fname);
		
		//get the length of the file and make it as response content lenth
		res.setContentLengthLong(f1.length());
		
		//get ServletContext obj
		sc=req.getServletContext();
		
		//get MIME type of the file and make response MIME type
		mimeType=sc.getMimeType("D:/store/"+fname);
		res.setContentType(mimeType!=null?mimeType:"application/octet-stream");
		
		//create InputStream pointing to the file to be downloaded
		is=new FileInputStream(f1);
		
		//create InputStream pointing to response object
		os=res.getOutputStream();
		
		//set values to content-disposition response header
		res.setHeader("Content-Disposition", "attachment;fileName="+f1);
		
		//copy the file content to response object 
		IOUtils.copy(is,os);
		
		//close the streams
		is.close();
		os.close();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}

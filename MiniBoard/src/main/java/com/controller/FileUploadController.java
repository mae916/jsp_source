package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;

import com.core.FileManager;

/**
 * 파일 업로드 처리 
 *
 * GET - 업로드 양식 
 * POST - 업로드 처리 
 */
public class FileUploadController extends HttpServlet {
	
	private static final long serialVersionUID = -1L;
	
	/** 업로드 양식 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/board/upload.jsp");
		rd.include(request, response);	
	}
	
	/** 업로드 처리 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileManager.upload(request);
	}
}	
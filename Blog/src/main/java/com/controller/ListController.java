package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.BlogDao;
import com.model.dto.Blog;

public class ListController extends HttpServlet {

	private static final long serialVersionUID = -1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BlogDao dao = new BlogDao();
		ArrayList<Blog> list = dao.getList();
		request.setAttribute("list", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("/blog/list.jsp");
		rd.include(request, response);
	}
}

package com.bitacademy.guestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.guestbook.dao.GuestbookDao;
import com.bitacademy.guestbook.vo.GuestbookVo;


public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		request
		.getRequestDispatcher("/WEB-INF/index.jsp")
		.forward(request, response);
		
		
		String action = request.getParameter("");
		if("deleteform".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteform.jsp");
			rd.forward(request, response);
		}else if("add".equals(action)) {
			request.setCharacterEncoding("utf-8");
			String Name = request.getParameter("name");
			String Password = request.getParameter("password");
			String Contents = request.getParameter("contents");
			
			GuestbookVo vo = new GuestbookVo();
			vo.setName(Name);
			vo.setPassword(Password);
			vo.setContents(Contents);
			
			new GuestbookDao().insert(vo);
			
			response.sendRedirect("/guestbook02");
			
		}else if("delete".equals(action)) {
			request.setCharacterEncoding("utf-8");
			String sno = request.getParameter("no");
			Long no = Long.parseLong(sno);
			String password = request.getParameter("password");
			
			new GuestbookDao().deleteByNoAndPassword(no, password);
			
			response.sendRedirect("/guestbook02");
		}else {
			List<GuestbookVo> list = new GuestbookDao().findAll();
			
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

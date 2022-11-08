<%@page import="com.bitacademy.guestbook.dao.GuestbookDao"%>
<%@page import="com.bitacademy.guestbook.vo.GuestbookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String Name = request.getParameter("name");
	String Password = request.getParameter("password");
	String Contents = request.getParameter("contents");
	
	GuestbookVo vo = new GuestbookVo();
	vo.setName(Name);
	vo.setPassword(Password);
	vo.setContents(Contents);
	
	new GuestbookDao().insert(vo);
	
	response.sendRedirect("/guestbook01");
%>
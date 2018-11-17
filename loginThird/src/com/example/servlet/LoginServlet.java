package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.bean.User;
import com.example.dao.UserDao;
import com.example.dao.UserDaoImpl;

public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/**
		 * Http������������ȫ����װ����req����������
		 * ��������ı���Ϊutf-8
		 */
		req.setCharacterEncoding("utf-8");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println("�û�����"+username+"\n���룺"+password);
		
		/**
		 * ͨ��text/html���н��������������ַ�����Ϊutf-8
		 */
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		UserDao userDao = new UserDaoImpl();
		User user = userDao.getUserByUsernameAndPassword(username, password);
		if(user == null){
			out.println("<h1><font color='red'>Login Faild����¼ʧ�ܣ�</font></h1>");
		} else {
			out.println("<h1>Login Success����¼�ɹ���</h1>");
		}
		
	}
	
}

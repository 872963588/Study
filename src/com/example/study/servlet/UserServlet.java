package com.example.study.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.study.service.UserService;
import com.google.gson.Gson;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");

		UserService userService = new UserService();
//		User user = new User();
//		UserDao userDao = new UserDao();
//		if (request.getParameter("userEmail") != null && request.getParameter("userPassword") != null) {
//			user = userDao.getUserByEmail(request.getParameter("userEmail"), request.getParameter("userPassword"));
//		} else {
//			user = null;
//		}
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		Map<String, Object> map = new LinkedHashMap<String, Object>();

//		map.put("status", "ok");
//		map.put("query", "Lucy");
//		map.put("user", user);
		if (request.getParameter("userEmail") != null && request.getParameter("userPassword") != null) {
			map = userService.loginByEmail(request.getParameter("userEmail"), request.getParameter("userPassword"));
			out.print(g.toJson(map));
		} else {
			out.print("参数为空");
		}

		out.close();
	}

}

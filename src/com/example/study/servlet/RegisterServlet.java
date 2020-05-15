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

import com.example.study.model.User;
import com.example.study.service.UserService;
import com.google.gson.Gson;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
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
		User user = new User();
		user.setNumber(request.getParameter("number"));
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setSchool(request.getParameter("school"));
		user.setSex(request.getParameter("sex"));
		// TODO user头像的处理

		UserService userService = new UserService();

		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map = userService.register(user);
		out.print(g.toJson(map));

		out.close();

	}

}

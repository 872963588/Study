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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
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

		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		if (request.getParameter("email") != null && request.getParameter("password") != null) {
			map = userService.login(request.getParameter("email"), request.getParameter("password"));

		} else {
			map.put("status", "false");
		}
		out.print(g.toJson(map));
		out.close();
	}

}

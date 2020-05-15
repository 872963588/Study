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

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		UserService userService = new UserService();

		// 获取课程班级的学习用户 TODO 这个暂时没用到
		if (request.getParameter("courseId") != null && request.getParameter("classId") != null) {
			map = userService.getUsersIdByCourse(Integer.parseInt(request.getParameter("courseId")),
					Integer.parseInt(request.getParameter("classId")));

		} else if (request.getParameter("id") != null) {
			// 获取指定Id的用户信息
			map = userService.getUserById(Integer.parseInt(request.getParameter("id")));
		} else {
			map.put("status", "false");
		}
		out.print(g.toJson(map));
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService = new UserService();
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		String type = request.getParameter("type");
		if (type != null) {
			// System.out.println(type);
			// 修改用户信息
			if ("info".equals(type)) {
				User user = new User();
				user.setId(Integer.parseInt(request.getParameter("id")));
				user.setNumber(request.getParameter("number"));
				user.setName(request.getParameter("name"));
				// user.setPassword(request.getParameter("password"));
				user.setEmail(request.getParameter("email"));
				user.setSchool(request.getParameter("school"));
				user.setSex(request.getParameter("sex"));
				map = userService.updateUserInfo(user);
			} else if ("password".equals(type)) {
				// 修改用户密码
				map = userService.updatePassword(request.getParameter("password"),
						Integer.parseInt(request.getParameter("id")));
			} else {
				map.put("status", "false");
			}

		} else {
			map.put("status", "false");
		}
		out.print(g.toJson(map));
		out.close();
	}

}

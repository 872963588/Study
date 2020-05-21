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

import com.example.study.dao.CourseDao;
import com.example.study.dao.UserDao;
import com.example.study.service.CourseService;
import com.example.study.service.UserService;
import com.google.gson.Gson;

@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Test() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.setContentType("text/html;charset=utf-8");

		CourseDao courseDao = new CourseDao();
//		List<Course> courses = new ArrayList<Course>();
//		if (request.getParameter("userId") != null) {
//			// courses =
//			// courseDao.getCourseByUserId(Integer.parseInt(request.getParameter("userId")));
//		} else {
//			courses = courseDao.getAllCourses();
//		}
//		// List<Course> courses = courseDao.getCourseByUserId(1);
//		PrintWriter out = response.getWriter();
//		Gson g = new Gson();
//		Map<String, Object> map = new LinkedHashMap<String, Object>();
//		//
//		TaskDao taskDao = new TaskDao();
//		List<Task> tasks = new ArrayList<Task>();
//		// tasks = taskDao.getTaskByCourseId(1);
//
//		map.put("status", "ok");
//		map.put("query", "Lucy");
//		map.put("courses", courses);
//		map.put("tasks", tasks);
//
//		out.print(g.toJson(map));
////		for (Course course : courses) {
////			out.print(course.toString());
////		}
//
//		out.close();
		courseDao.updateCourseImg(1, "123456");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.setContentType("text/html;charset=utf-8");
//		User user = new User();
//		UserDao userDao = new UserDao();
//		if (request.getParameter("userEmail") != null) {
//			// user = userDao.getUserByEmail(request.getParameter("userEmail"));
//		} else {
//			user = null;
//		}
//		PrintWriter out = response.getWriter();
//		Gson g = new Gson();
//		Map<String, Object> map = new LinkedHashMap<String, Object>();
//
//		map.put("status", "ok");
//		map.put("query", "Lucy");
//		map.put("user", user);
//
//		out.print(g.toJson(map));
//		out.close();

		// 测试更新user密码
//		UserService userService = new UserService();
//		PrintWriter out = response.getWriter();
//		Gson g = new Gson();
//		Map<String, Object> map = new LinkedHashMap<String, Object>();
//
//		map = userService.updatePassword(1);
//		out.print(g.toJson(map));
//
//		out.close();

		// 测试更新user信息
//		User user = new User();
//		user.setNumber(request.getParameter("number"));
//		user.setName(request.getParameter("name"));
//		// user.setPassword(request.getParameter("password"));
//		user.setEmail(request.getParameter("email"));
//		user.setSchool(request.getParameter("school"));
//		user.setSex(request.getParameter("sex"));
//
		UserService userService = new UserService();
//		PrintWriter out = response.getWriter();
//		Gson g = new Gson();
//		Map<String, Object> map = new LinkedHashMap<String, Object>();
//
//		map = userService.updateUserInfo(user, Integer.parseInt(request.getParameter("id")));
//		out.print(g.toJson(map));
//
//		out.close();

		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		UserDao userDao = new UserDao();
		// 测试获取课程相应班级的用户
//		List<Integer> usersId = new ArrayList<Integer>();
//		usersId = userDao.getUsersIdByCourse(1, 1);
//
//		map.put("status", "ok");
//		map.put("query", "Lucy");
//		map.put("courses", courses);
//		map.put("tasks", tasks);

		// map = userService.updateUserInfo(user,
		// Integer.parseInt(request.getParameter("id")));

		// 测试获取课程相应班级 此处应该在class表 先延后一下

		// 测试根据用户Id获取用户信息

//		if (request.getParameter("id") != null) {
//			map = userService.getUserById(Integer.parseInt(request.getParameter("id")));
//			out.print(g.toJson(map));
//		} else {
//			out.print("参数为空");
//		}
		// 测试删除课程
		CourseService courseService = new CourseService();
		if (request.getParameter("id") != null) {
			map = courseService.deleteCourseById(Integer.parseInt(request.getParameter("id")));

		} else if (request.getParameter("name") != null) {
			map = courseService.searchCoursesByName(request.getParameter("name"));
		} else {
			map.put("status", "false");
		}
		map.put("status", "true");
		out.print(g.toJson(map));
		out.close();

	}

}

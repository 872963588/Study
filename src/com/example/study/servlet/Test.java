package com.example.study.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.study.dao.CourseDao;
import com.example.study.dao.TaskDao;
import com.example.study.dao.UserDao;
import com.example.study.model.Course;
import com.example.study.model.Task;
import com.example.study.model.User;
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
		response.setContentType("text/html;charset=utf-8");

		CourseDao courseDao = new CourseDao();
		List<Course> courses = new ArrayList<Course>();
		if (request.getParameter("userId") != null) {
			// courses =
			// courseDao.getCourseByUserId(Integer.parseInt(request.getParameter("userId")));
		} else {
			courses = courseDao.getAllCourses();
		}
		// List<Course> courses = courseDao.getCourseByUserId(1);
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		//
		TaskDao taskDao = new TaskDao();
		List<Task> tasks = new ArrayList<Task>();
		// tasks = taskDao.getTaskByCourseId(1);

		map.put("status", "ok");
		map.put("query", "Lucy");
		map.put("courses", courses);
		map.put("tasks", tasks);

		out.print(g.toJson(map));
//		for (Course course : courses) {
//			out.print(course.toString());
//		}

		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		User user = new User();
		UserDao userDao = new UserDao();
		if (request.getParameter("userEmail") != null) {
			// user = userDao.getUserByEmail(request.getParameter("userEmail"));
		} else {
			user = null;
		}
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", "ok");
		map.put("query", "Lucy");
		map.put("user", user);

		out.print(g.toJson(map));
		out.close();
	}

}

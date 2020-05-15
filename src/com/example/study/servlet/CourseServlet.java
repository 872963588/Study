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

import com.example.study.model.Course;
import com.example.study.service.CourseService;
import com.google.gson.Gson;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CourseServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		CourseService courseService = new CourseService();
		// TODO 这里可不可以用switch

		// 获取用户学习的课程
		if (request.getParameter("userId") != null) {
			map = courseService.getCoursesByUserId(Integer.parseInt(request.getParameter("userId")));

		} else if (request.getParameter("id") != null) {
			// 获取课程信息
			map = courseService.getCourseById(Integer.parseInt(request.getParameter("id")));
		} else if (request.getParameter("name") != null) {
			// 根据课程名搜索课程
			map = courseService.searchCoursesByName(request.getParameter("name"));
		} else if (request.getParameter("owner") != null) {
			// 获取用户创建的课程
			map = courseService.getCoursesByOwner(Integer.parseInt(request.getParameter("owner")));
		} else if (request.getParameter("all") != null) {
			// 获取用户创建的课程
			map = courseService.getAllCourses();
		} else {
			map.put("status", "false");
		}
		out.print(g.toJson(map));
		out.close();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request.setCharacterEncoding("UTF-8");
		CourseService courseService = new CourseService();
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		String type = request.getParameter("type");
		if (type != null) {
			if ("add".equals(type)) {
				// 添加课程
				Course course = new Course();
				course.setName(request.getParameter("name"));
				course.setDetail(request.getParameter("detail"));
				course.setOwner(request.getParameter("owner"));
				course.setSort(request.getParameter("sort"));
				// course.setPicture(request.getParameter("picture"));

				map = courseService.addCourse(course);

			} else if ("delete".equals(type)) {
				// 删除课程
				map = courseService.deleteCourseById(Integer.parseInt(request.getParameter("id")));
			} else if ("update".equals(type)) {
				// 修改课程信息
				Course course = new Course();
				course.setId(Integer.parseInt(request.getParameter("id")));
				course.setName(request.getParameter("name"));
				course.setDetail(request.getParameter("detail"));
				course.setSort(request.getParameter("sort"));
				map = courseService.updateCourseInfo(course);
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

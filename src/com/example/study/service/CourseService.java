package com.example.study.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.study.dao.CourseDao;
import com.example.study.model.Course;

public class CourseService {
	CourseDao courseDao = new CourseDao();

	public Map<String, Object> getCoursesByUserId(int userId) {

		// TODO 没有判断参数是否为空。
		List<Course> courses = new ArrayList<Course>();
		// CourseDao courseDao = new CourseDao();
		courses = courseDao.getCoursesByUserId(userId);
		String status = "true";
		if (courses == null) {
			status = "false";
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);
		map.put("query", userId);
		map.put("courses", courses);

		return map;

	}

	public Map<String, Object> addCourse(Course course) {
		// CourseDao courseDao = new CourseDao();
		courseDao.addCourse(course);
		String status = "false";
		if (course.getId() > 0) {
			// status = String.valueOf(course.getId());
			status = "course_" + course.getId();
			// status = "true";
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);
		return map;

	}

	public Map<String, Object> updateCourseInfo(Course course) {

		// UserDao userDao = new UserDao();

		String status = courseDao.updateCourseInfo(course);

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);

		return map;

	}

	public Map<String, Object> getCourseById(int id) {

		// TODO 没有判断参数是否为空。

		Course course = courseDao.getCourseById(id);
		String status = "true";
		if (course == null) {
			status = "false";
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);
		map.put("query", id);
		map.put("course", course);

		return map;

	}

	public Map<String, Object> deleteCourseById(int id) {

		// UserDao userDao = new UserDao();

		String status = courseDao.deleteCourseById(id);

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);

		return map;

	}

	public Map<String, Object> searchCoursesByName(String name) {

		// TODO 没有判断参数是否为空。
		List<Course> courses = new ArrayList<Course>();
		// CourseDao courseDao = new CourseDao();
		courses = courseDao.searchCoursesByName(name);
		String status = "true";
		if (courses == null) {
			status = "false";
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);
		map.put("query", name);
		map.put("courses", courses);

		return map;

	}

	public Map<String, Object> getCoursesByOwner(int owner) {

		// TODO 没有判断参数是否为空。
		List<Course> courses = new ArrayList<Course>();
		// CourseDao courseDao = new CourseDao();
		courses = courseDao.getCoursesByOwner(owner);
		String status = "true";
		if (courses == null) {
			status = "false";
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);
		map.put("query", owner);
		map.put("courses", courses);

		return map;

	}

	public Map<String, Object> getAllCourses() {

		// TODO 没有判断参数是否为空。
		List<Course> courses = new ArrayList<Course>();
		// CourseDao courseDao = new CourseDao();
		courses = courseDao.getAllCourses();
		String status = "true";
		if (courses == null) {
			status = "false";
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);
		map.put("query", "all");
		map.put("courses", courses);

		return map;

	}

}

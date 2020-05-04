package com.example.study.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.study.dao.CourseDao;
import com.example.study.model.Course;

public class CourseService {

	public Map<String, Object> getCoursesByUserId(int userId) {

		// TODO 没有判断参数是否为空。
		List<Course> courses = new ArrayList<Course>();
		CourseDao courseDao = new CourseDao();
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

}

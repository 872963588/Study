package com.example.study.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.study.dao.CommentDao;
import com.example.study.model.Comment;
import com.example.study.model.CommentResponse;

public class CommentService {

	CommentDao commentDao = new CommentDao();

	public Map<String, Object> getCommentsByCourseId(int courseId) {

		// TODO 没有判断参数是否为空。
		List<CommentResponse> comments = new ArrayList<CommentResponse>();
		comments = commentDao.getCommentsByCourseId(courseId);
		String status = "true";
		if (comments == null) {
			status = "false";
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);
		map.put("query", courseId);
		map.put("comments", comments);

		return map;

	}

	public Map<String, Object> getCommentsByTaskId(int taskId) {

		// TODO 没有判断参数是否为空。
		List<CommentResponse> comments = new ArrayList<CommentResponse>();
		// CommentDao commentDao = new CommentDao();
		comments = commentDao.getCommentsByTaskId(taskId);
		String status = "true";
		if (comments == null) {
			status = "false";
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);
		map.put("query", taskId);
		map.put("comments", comments);

		return map;

	}

	public Map<String, Object> addCourseComment(Comment comment) {
		// CourseDao courseDao = new CourseDao();
		commentDao.addCourseComment(comment);
		String status = "false";
		if (comment.getId() > 0) {
			status = "true";
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);
		return map;

	}

	public Map<String, Object> addTaskComment(Comment comment) {
		// CourseDao courseDao = new CourseDao();
		commentDao.addTaskComment(comment);
		String status = "false";
		if (comment.getId() > 0) {
			status = "true";
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);
		return map;

	}

}

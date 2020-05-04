package com.example.study.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.study.dao.CommentDao;
import com.example.study.model.Comment;

public class CommentService {

	public Map<String, Object> getCommentsByCourseId(int courseId) {

		// TODO 没有判断参数是否为空。
		List<Comment> comments = new ArrayList<Comment>();
		CommentDao commentDao = new CommentDao();
		comments = commentDao.getCommentsByCourseId(courseId);
		String status = "true";
		if (comments == null) {
			status = "false";
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);
		map.put("query", courseId);
		map.put("comment", comments);

		return map;

	}

	public Map<String, Object> getCommentsByTaskId(int courseId, int taskId) {

		// TODO 没有判断参数是否为空。
		List<Comment> comments = new ArrayList<Comment>();
		CommentDao commentDao = new CommentDao();
		comments = commentDao.getCommentsByTaskId(courseId, taskId);
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

}

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

import com.example.study.model.Comment;
import com.example.study.service.CommentService;
import com.google.gson.Gson;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		CommentService commentService = new CommentService();

		if (request.getParameter("courseId") != null) {
			map = commentService.getCommentsByCourseId(Integer.parseInt(request.getParameter("courseId")));

		} else if (request.getParameter("taskId") != null) {
			map = commentService.getCommentsByTaskId(Integer.parseInt(request.getParameter("taskId")));

		} else {
			map.put("status", "false");
		}
		out.print(g.toJson(map));
		out.close();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommentService commentService = new CommentService();
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		if (request.getParameter("courseId") != null) {
			Comment comment = new Comment();
			comment.setUserId(request.getParameter("userId"));
			comment.setDetail(request.getParameter("detail"));
			comment.setCourseId(request.getParameter("courseId"));
			map = commentService.addCourseComment(comment);

		} else if (request.getParameter("taskId") != null) {
			Comment comment = new Comment();
			comment.setUserId(request.getParameter("userId"));
			comment.setDetail(request.getParameter("detail"));
			comment.setTaskId(request.getParameter("taskId"));
			map = commentService.addTaskComment(comment);
		} else {
			map.put("status", "false");
		}
		out.print(g.toJson(map));
		out.close();

	}

}

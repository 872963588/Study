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

import com.example.study.model.Task;
import com.example.study.service.TaskService;
import com.google.gson.Gson;

@WebServlet("/task")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TaskServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		TaskService taskService = new TaskService();

		if (request.getParameter("courseId") != null) {
			// 根据课程Id获取任务
			// TODO 有些课程任务数为0 应该怎么显示？
			map = taskService.getTasksByCourseId(Integer.parseInt(request.getParameter("courseId")));
		} else {
			map.put("status", "false");
		}
		out.print(g.toJson(map));
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		TaskService taskService = new TaskService();
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		String type = request.getParameter("type");
		if (type != null) {
			if ("add".equals(type)) {
				// 添加任务
				Task task = new Task();
				task.setName(request.getParameter("name"));
				// task.setTime(request.getParameter("time"));// TODO 这个时间可以改为数据库时间
				task.setDetail(request.getParameter("detail"));
				task.setCourseId(request.getParameter("courseId"));
				// task.setFileType("");
				// task.setFileUrl(fileUrl);

				map = taskService.addTask(task);

			} else if ("delete".equals(type)) {
				// 删除课程
				map = taskService.deleteTaskById(Integer.parseInt(request.getParameter("id")));
			} else {
				map.put("status", "false");
			}

		} else {
			map.put("status", "false");
		}
		out.print(g.toJson(map));
		out.close();
		out.print(g.toJson(map));

		out.close();

	}

}

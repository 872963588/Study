package com.example.study.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.study.dao.TaskDao;
import com.example.study.model.Task;

public class TaskService {

	TaskDao taskDao = new TaskDao();

	public Map<String, Object> getTasksByCourseId(int courseId) {

		// TODO 没有判断参数是否为空。
		List<Task> tasks = new ArrayList<Task>();

		tasks = taskDao.getTasksByCourseId(courseId);
		String status = "true";
		if (tasks == null) {
			status = "false";
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);
		map.put("query", courseId);
		map.put("tasks", tasks);

		return map;

	}

	public Map<String, Object> addTask(Task task) {
		taskDao.addTask(task);
		String status = "false";
		if (task.getId() > 0) {
			status = "true";
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);
		return map;

	}

	public Map<String, Object> deleteTaskById(int id) {

		String status = taskDao.deleteTaskById(id);

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);

		return map;

	}

	// TODO 修改先不加 以后再说

}

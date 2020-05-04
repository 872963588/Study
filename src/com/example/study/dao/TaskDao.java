package com.example.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.study.exception.DBException;
import com.example.study.model.Task;
import com.example.study.util.DBUtil;

public class TaskDao {

	public List<Task> getTasksByCourseId(int courseId) {
		List<Task> tasks = new ArrayList<Task>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Task task = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM task WHERE course_id=?");
			stmt.setInt(1, courseId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				task = new Task();
				task.setId(rs.getInt("id"));
				task.setTaskName(rs.getString("task_name"));
				task.setTaskTime(rs.getString("task_time"));
				task.setTaskDetail(rs.getString("task_detail"));
				task.setCourseId(rs.getString("course_id"));
				task.setFileType(rs.getString("file_type"));
				task.setFileUrl(rs.getString("file_url"));
				tasks.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return tasks;

	}

}

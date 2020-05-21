package com.example.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.study.exception.DBException;
import com.example.study.model.Task;
import com.example.study.util.DBUtil;

public class TaskDao {

	// TODO这里要和班级结合起来
	// TODO 加一个排序

	public List<Task> getTasksByCourseId(int courseId) {
		List<Task> tasks = new ArrayList<Task>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Task task = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM task WHERE course_id=? order by id desc");
			stmt.setInt(1, courseId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				task = new Task();
				task.setId(rs.getInt("id"));
				task.setName(rs.getString("name"));
				task.setTime(rs.getString("time"));
				task.setDetail(rs.getString("detail"));
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

	public void addTask(Task task) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(
					"INSERT INTO task(name,time,detail,course_id,file_type,file_url) VALUES(?,now(),?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, task.getName());
			// stmt.setString(2, task.getTime());
			stmt.setString(2, task.getDetail());
			stmt.setString(3, task.getCourseId());
			stmt.setString(4, task.getFileType());
			stmt.setString(5, task.getFileUrl());
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				task.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}

	}

	public String deleteTaskById(int id) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String status = "false";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("DELETE FROM task WHERE id=?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			status = "true";

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return status;
	}

	public String updateTaskFile(int id, String name) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String status = "false";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("UPDATE task SET file_type=?,file_url=? where id=?");
			stmt.setString(1, name.substring(name.lastIndexOf(".") + 1));
			stmt.setString(2, "http://47.93.59.28:8080/Study/files/" + name);
			stmt.setInt(3, id);

			stmt.executeUpdate();
			status = "true";

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}

		return status;

	}

}

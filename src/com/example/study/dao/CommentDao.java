package com.example.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.study.exception.DBException;
import com.example.study.model.Comment;
import com.example.study.model.CommentResponse;
import com.example.study.util.DBUtil;

public class CommentDao {

	public List<CommentResponse> getCommentsByCourseId(int courseId) {
		List<CommentResponse> commentsResponse = new ArrayList<CommentResponse>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CommentResponse commentResponse = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(
					"select c.id,c.user_id,c.time,c.detail,u.name,u.picture from comment as c join user as u on u.id=c.user_id WHERE c.course_id=? AND c.task_id=0 order by c.id desc");
			stmt.setInt(1, courseId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				commentResponse = new CommentResponse();
				commentResponse.setId(rs.getInt("id"));
				commentResponse.setUserId(rs.getString("user_id"));
				commentResponse.setTime(rs.getString("time"));
				commentResponse.setDetail(rs.getString("detail"));
				commentResponse.setName(rs.getString("name"));
				commentResponse.setPicture(rs.getString("picture"));
				// comment.setCourseId(rs.getString("course_id"));
				// comment.setTaskId(rs.getString("task_id"));
				commentsResponse.add(commentResponse);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return commentsResponse;

	}

	public List<CommentResponse> getCommentsByTaskId(int taskId) {
		List<CommentResponse> commentsResponse = new ArrayList<CommentResponse>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CommentResponse commentResponse = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(
					"select c.id,c.user_id,c.time,c.detail,u.name,u.picture from comment as c join user as u on u.id=c.user_id WHERE c.task_id=? order by c.id desc");
			stmt.setInt(1, taskId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				commentResponse = new CommentResponse();
				commentResponse.setId(rs.getInt("id"));
				commentResponse.setUserId(rs.getString("user_id"));
				commentResponse.setTime(rs.getString("time"));
				commentResponse.setDetail(rs.getString("detail"));
				commentResponse.setName(rs.getString("name"));
				commentResponse.setPicture(rs.getString("picture"));
				// comment.setCourseId(rs.getString("course_id"));
				// comment.setTaskId(rs.getString("task_id"));
				commentsResponse.add(commentResponse);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return commentsResponse;

	}

	public void addCourseComment(Comment comment) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("INSERT INTO comment(user_id,time,detail,course_id) VALUES(?,now(),?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, comment.getUserId());
			// stmt.setString(2, now());
			stmt.setString(2, comment.getDetail());
			stmt.setString(3, comment.getCourseId());

			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				comment.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}

	}

	public void addTaskComment(Comment comment) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("INSERT INTO comment(user_id,time,detail,task_id) VALUES(?,now(),?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, comment.getUserId());
			// stmt.setString(2, "123456");
			stmt.setString(2, comment.getDetail());
			stmt.setString(3, comment.getTaskId());

			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				comment.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}

	}

}

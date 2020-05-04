package com.example.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.study.exception.DBException;
import com.example.study.model.Comment;
import com.example.study.util.DBUtil;

public class CommentDao {

	public List<Comment> getCommentsByCourseId(int courseId) {
		List<Comment> comments = new ArrayList<Comment>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Comment comment = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM comment WHERE course_id=? AND task_id=0");
			stmt.setInt(1, courseId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setUserId(rs.getString("user_id"));
				comment.setCommentTime(rs.getString("comment_time"));
				comment.setCommentDetail(rs.getString("comment_detail"));
				// comment.setCourseId(rs.getString("course_id"));
				// comment.setTaskId(rs.getString("task_id"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return comments;

	}

	public List<Comment> getCommentsByTaskId(int courseId, int taskId) {
		List<Comment> comments = new ArrayList<Comment>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Comment comment = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM comment WHERE course_id=? AND task_id=?");
			stmt.setInt(1, courseId);
			stmt.setInt(1, taskId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setUserId(rs.getString("user_id"));
				comment.setCommentTime(rs.getString("comment_time"));
				comment.setCommentDetail(rs.getString("comment_detail"));
				// comment.setCourseId(rs.getString("course_id"));
				// comment.setTaskId(rs.getString("task_id"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return comments;

	}

}

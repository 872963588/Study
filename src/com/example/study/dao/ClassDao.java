package com.example.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.study.exception.DBException;
import com.example.study.util.DBUtil;

public class ClassDao {

	public List<Integer> getClassNameByCourse(int courseId) {
		List<Integer> usersId = new ArrayList<Integer>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT user_id FROM user_course_class WHERE course_id = ?");
			stmt.setInt(1, courseId);

			rs = stmt.executeQuery();
			while (rs.next()) {

				int userId = rs.getInt("user_id");

				usersId.add(userId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return usersId;

	}

}

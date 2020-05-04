package com.example.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.study.exception.DBException;
import com.example.study.model.User;
import com.example.study.util.DBUtil;

public class UserDao {

	public User getUserByEmail(String userEmail, String password) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM user WHERE user_email = ? AND user_password = ?");
			stmt.setString(1, userEmail);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUserNumber(rs.getString("user_number"));
				user.setUserName(rs.getString("user_name"));
				// user.setPassword(rs.getString("user_password"));
				user.setUserEmail(rs.getString("user_email"));
				user.setUserSchool(rs.getString("user_school"));
				user.setUserSex(rs.getString("user_sex"));
				user.setUserImg(rs.getString("user_img"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return user;

	}

	public User getUserByName(String userName) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		try {

			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM user WHERE user_name = ?");
			stmt.setString(1, userName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("user_name"));
				// service层判断密码是否正确
				user.setPassword(rs.getString("user_password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return user;

	}
}

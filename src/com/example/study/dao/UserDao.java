package com.example.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.study.exception.DBException;
import com.example.study.model.User;
import com.example.study.util.DBUtil;

public class UserDao {

	public User login(String userEmail, String password) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");
			stmt.setString(1, userEmail);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setNumber(rs.getString("number"));
				user.setName(rs.getString("name"));
				// user.setPassword(rs.getString("user_password"));
				user.setEmail(rs.getString("email"));
				user.setSchool(rs.getString("school"));
				user.setSex(rs.getString("sex"));
				user.setPicture(rs.getString("picture"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return user;

	}

	public void register(User user) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("INSERT INTO user(number,name,password,email,school,sex) VALUES(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getNumber());
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getSchool());
			stmt.setString(6, user.getSex());
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				user.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}

	}

	public String updatePassword(String password, int id) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String status = "false";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("UPDATE user SET password=? where id=?");
			stmt.setString(1, password);
			stmt.setInt(2, id);
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

	public String updateUserInfo(User user) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String status = "false";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("UPDATE user SET number=?,name=?,email=?,school=?,sex=? where id=?");
			stmt.setString(1, user.getNumber());
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getSchool());
			stmt.setString(5, user.getSex());
			stmt.setInt(6, user.getId());
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

	public List<Integer> getUsersIdByCourse(int courseId, int classId) {
		List<Integer> usersId = new ArrayList<Integer>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT user_id FROM user_course_class WHERE course_id = ?&&class_id=?");
			stmt.setInt(1, courseId);
			stmt.setInt(2, classId);
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

	public User getUserById(int id) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM user WHERE id= ?");
			stmt.setInt(1, id);

			rs = stmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setNumber(rs.getString("number"));
				user.setName(rs.getString("name"));
				// user.setPassword(rs.getString("user_password"));
				user.setEmail(rs.getString("email"));
				user.setSchool(rs.getString("school"));
				user.setSex(rs.getString("sex"));
				user.setPicture(rs.getString("picture"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return user;

	}

//	public User getUserByName(String userName) {
//
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		User user = null;
//		try {
//
//			conn = DBUtil.getConnection();
//			stmt = conn.prepareStatement("SELECT * FROM user WHERE user_name = ?");
//			stmt.setString(1, userName);
//			rs = stmt.executeQuery();
//			if (rs.next()) {
//				user = new User();
//				user.setId(rs.getInt("id"));
//				user.setUserName(rs.getString("user_name"));
//				// service层判断密码是否正确
//				user.setPassword(rs.getString("user_password"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DBException();
//		} finally {
//			DBUtil.close(rs, stmt, conn);
//		}
//		return user;
//
//	}
	public String updateCourseImg(int id, String name) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String status = "false";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("UPDATE user SET picture=? where id=?");
			stmt.setString(1, "http://47.93.59.28:8080/Study/images/" + name);
			stmt.setInt(2, id);

			// TODO 照片
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

	// 用户加入课程
	public String addStudyCourse(int userId, int courseId) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String status = "false";

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("INSERT INTO user_course_class(user_id,course_id) VALUES(?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, userId);
			stmt.setInt(2, courseId);
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				status = "true";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return status;

	}

	// 用户退出课程
	public String delStudyCourse(int userId, int courseId) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String status = "false";

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("DELETE FROM user_course_class WHERE user_id=? AND course_id=?");

			stmt.setInt(1, userId);
			stmt.setInt(2, courseId);
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

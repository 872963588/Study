package com.example.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.study.exception.DBException;
import com.example.study.model.Course;
import com.example.study.util.DBUtil;

public class CourseDao {

	// 获取所有课程
	public List<Course> getAllCourses() {
		List<Course> courses = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Course course = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(
					"select c.id,c.name,c.detail,u.name as owner,c.picture,c.sort from course as c join user as u on u.id=c.owner");
			rs = stmt.executeQuery();
			while (rs.next()) {
				course = new Course();
				course.setId(rs.getInt("id"));
				course.setName(rs.getString("name"));
				course.setDetail(rs.getString("detail"));
				course.setOwner(rs.getString("owner"));
				course.setPicture(rs.getString("picture"));
				course.setSort(rs.getString("sort"));
				courses.add(course);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return courses;

	}

	// 获取学习课程的学生
	public List<Course> getCoursesByUserId(int userId) {
		List<Course> courses = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Course course = null;
		try {
			conn = DBUtil.getConnection();
			// TODO 太长了 优化一下
			stmt = conn.prepareStatement(
					"select c.id,c.name,c.detail,u.name as owner,c.picture,c.sort from course as c join user as u on u.id=c.owner where c.id IN (SELECT course_id FROM user_course_class WHERE user_id =?)");
			stmt.setInt(1, userId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				course = new Course();
				course.setId(rs.getInt("id"));
				course.setName(rs.getString("name"));
				course.setDetail(rs.getString("detail"));
				course.setOwner(rs.getString("owner"));
				course.setPicture(rs.getString("picture"));
				course.setSort(rs.getString("sort"));
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return courses;

	}

	// 添加课程
	public void addCourse(Course course) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("INSERT INTO course(name,detail,owner,sort) VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, course.getName());
			stmt.setString(2, course.getDetail());
			stmt.setString(3, course.getOwner());
			stmt.setString(4, course.getSort());
			// TODO 照片的处理
			// stmt.setString(4, course.getPicture());
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				course.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}

	}

	// 更新课程信息
	public String updateCourseInfo(Course course) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String status = "false";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("UPDATE course SET name=?,detail=?,sort=? where id=?");
			stmt.setString(1, course.getName());
			stmt.setString(2, course.getDetail());
			stmt.setInt(3, course.getId());
			stmt.setString(4, course.getSort());

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

	// 获取指定Id的课程信息
	public Course getCourseById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Course course = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM course WHERE id =?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				course = new Course();
				course.setId(rs.getInt("id"));
				course.setName(rs.getString("name"));
				course.setDetail(rs.getString("detail"));
				course.setOwner(rs.getString("owner"));
				course.setPicture(rs.getString("picture"));
				course.setSort(rs.getString("sort"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return course;

	}

	// 删除指定Id的课程
	public String deleteCourseById(int id) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String status = "false";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("DELETE FROM course WHERE id=?");
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

	// 模糊查询
	public List<Course> searchCoursesByName(String name) {
		List<Course> courses = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Course course = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(
					"select c.id,c.name,c.detail,u.name as owner,c.picture,c.sort from course as c join user as u on u.id=c.owner WHERE c.name LIKE '%"
							+ name + "%'");
			rs = stmt.executeQuery();
			// stmt.setString(1, name);
			while (rs.next()) {
				course = new Course();
				course.setId(rs.getInt("id"));
				course.setName(rs.getString("name"));
				course.setDetail(rs.getString("detail"));
				course.setOwner(rs.getString("owner"));
				course.setPicture(rs.getString("picture"));
				course.setSort(rs.getString("sort"));
				courses.add(course);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return courses;

	}

	// 获取用户创建的课程
	public List<Course> getCoursesByOwner(int owner) {
		List<Course> courses = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Course course = null;
		try {
			conn = DBUtil.getConnection();

			stmt = conn.prepareStatement(
					"select c.id,c.name,c.detail,u.name as owner,c.picture,c.sort from course as c join user as u on u.id=c.owner where owner=?");
			stmt.setInt(1, owner);
			rs = stmt.executeQuery();
			while (rs.next()) {
				course = new Course();
				course.setId(rs.getInt("id"));
				course.setName(rs.getString("name"));
				course.setDetail(rs.getString("detail"));
				course.setOwner(rs.getString("owner"));
				course.setPicture(rs.getString("picture"));
				course.setSort(rs.getString("sort"));
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return courses;

	}

	// 更新图片
	public String updateCourseImg(int id, String name) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String status = "false";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("UPDATE course SET picture=? where id=?");
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

}

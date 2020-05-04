package com.example.study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.study.exception.DBException;
import com.example.study.model.Course;
import com.example.study.util.DBUtil;

public class CourseDao {

	public List<Course> getAllCourses() {
		List<Course> courses = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Course course = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM course");
			rs = stmt.executeQuery();
			while (rs.next()) {
				course = new Course();
				course.setId(rs.getInt("id"));
				course.setCourseName(rs.getString("course_name"));
				course.setCourseDetail(rs.getString("course_detail"));
				course.setCourseOwner(rs.getString("course_owner"));
				course.setCourseImg(rs.getString("course_img"));
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

	public List<Course> getCoursesByUserId(int userId) {
		List<Course> courses = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Course course = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(
					"SELECT * FROM course WHERE course.id IN (SELECT course_id FROM user_course_class WHERE user_id =?);");
			stmt.setInt(1, userId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				course = new Course();
				course.setId(rs.getInt("id"));
				course.setCourseName(rs.getString("course_name"));
				course.setCourseDetail(rs.getString("course_detail"));
				course.setCourseOwner(rs.getString("course_owner"));
				course.setCourseImg(rs.getString("course_img"));
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

}

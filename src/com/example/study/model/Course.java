package com.example.study.model;

public class Course {

	private int id;
	private String courseName;
	private String courseDetail;
	private String courseOwner;
	private String courseImg;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDetail() {
		return courseDetail;
	}

	public void setCourseDetail(String courseDetail) {
		this.courseDetail = courseDetail;
	}

	public String getCourseOwner() {
		return courseOwner;
	}

	public void setCourseOwner(String courseOwner) {
		this.courseOwner = courseOwner;
	}

	public String getCourseImg() {
		return courseImg;
	}

	public void setCourseImg(String courseImg) {
		this.courseImg = courseImg;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", courseDetail=" + courseDetail + ", courseOwner="
				+ courseOwner + ", courseImg=" + courseImg + "]";
	}

}

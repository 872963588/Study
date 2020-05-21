package com.example.study.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.study.dao.UserDao;
import com.example.study.model.User;

public class UserService {

//	public User login(String userName, String password) throws ParameterException, ServiceException {
//
//		ParameterException parameterException = new ParameterException();
//
//		if (StringUtil.isEmpty(userName)) {
//			parameterException.addErrorFields("errorUserName", "User name is required");
//		}
//		if (StringUtil.isEmpty(password)) {
//			parameterException.addErrorFields("errorPassword", "Password is required");
//		}
//		if (parameterException.isErrorField()) {
//			throw parameterException;
//		}
//
//		UserDao userDao = new UserDao();
//		User user = userDao.getUserByName(userName);
//		if (user == null) {
//			throw new ServiceException(1000, "用户名或密码不正确");
//		}
//		if (!password.equals(user.getPassword())) {
//			throw new ServiceException(1000, "用户名或密码不正确");
//		}
//		return user;
//
//	}
	UserDao userDao = new UserDao();

	public Map<String, Object> login(String userEmail, String password) {

		// TODO 没有判断参数是否为空。

		User user = userDao.login(userEmail, password);
		String status = "true";
		if (user == null) {
			status = "false";
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);
		map.put("query", userEmail);
		map.put("user", user);

		return map;

	}

	public Map<String, Object> register(User user) {

		// UserDao userDao = new UserDao();
		userDao.register(user);
		String status = "false";
		if (user.getId() > 0) {
			status = "true";
		}

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);

		return map;

	}

	public Map<String, Object> updatePassword(String password, int id) {

		// UserDao userDao = new UserDao();

		String status = userDao.updatePassword(password, id);

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);

		return map;

	}

	public Map<String, Object> updateUserInfo(User user) {

		// UserDao userDao = new UserDao();

		String status = userDao.updateUserInfo(user);

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);

		return map;

	}

	public Map<String, Object> getUsersIdByCourse(int courseId, int classId) {

		// UserDao userDao = new UserDao();
		List<Integer> usersId = userDao.getUsersIdByCourse(courseId, classId);
		String status = "true";
		if (usersId == null) {
			status = "false";
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);
		map.put("query", courseId);
		map.put("usersId", usersId);

		return map;

	}

	public Map<String, Object> getUserById(int id) {

		// TODO 没有判断参数是否为空。

		User user = userDao.getUserById(id);
		String status = "true";
		if (user == null) {
			status = "false";
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("status", status);
		map.put("query", id);
		map.put("user", user);

		return map;

	}

	public Map<String, Object> addStudyCourse(int userId, int courseId) {

		String status = userDao.addStudyCourse(userId, courseId);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("status", status);
		return map;

	}

	public Map<String, Object> delStudyCourse(int userId, int courseId) {

		String status = userDao.delStudyCourse(userId, courseId);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("status", status);
		return map;

	}
}

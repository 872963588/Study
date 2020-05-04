package com.example.study.service;

import java.util.LinkedHashMap;
import java.util.Map;

import com.example.study.dao.UserDao;
import com.example.study.exception.ParameterException;
import com.example.study.exception.ServiceException;
import com.example.study.model.User;
import com.example.study.util.StringUtil;

public class UserService {

	public User login(String userName, String password) throws ParameterException, ServiceException {

		ParameterException parameterException = new ParameterException();

		if (StringUtil.isEmpty(userName)) {
			parameterException.addErrorFields("errorUserName", "User name is required");
		}
		if (StringUtil.isEmpty(password)) {
			parameterException.addErrorFields("errorPassword", "Password is required");
		}
		if (parameterException.isErrorField()) {
			throw parameterException;
		}

		UserDao userDao = new UserDao();
		User user = userDao.getUserByName(userName);
		if (user == null) {
			throw new ServiceException(1000, "用户名或密码不正确");
		}
		if (!password.equals(user.getPassword())) {
			throw new ServiceException(1000, "用户名或密码不正确");
		}
		return user;

	}

	public Map<String, Object> loginByEmail(String userEmail, String password) {

		// TODO 没有判断参数是否为空。

		UserDao userDao = new UserDao();
		User user = userDao.getUserByEmail(userEmail, password);
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

}

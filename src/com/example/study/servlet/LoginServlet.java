package com.example.study.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.study.Constants;
import com.example.study.exception.ParameterException;
import com.example.study.exception.ServiceException;
import com.example.study.model.User;
import com.example.study.service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";

	public LoginServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		try {
			UserService userService = new UserService();
			User user = null;
			user = userService.login(userName, password);
			HttpSession session = request.getSession();
			session.setAttribute("USER", user);
			response.sendRedirect(request.getContextPath() + "/mycourse");
		} catch (ParameterException parameterException) {
			Map<String, String> errorFields = parameterException.getErrorFields();
			request.setAttribute(Constants.ERROR_FIELDS, errorFields);
			request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
		} catch (ServiceException serviceException) {
			request.setAttribute(Constants.ERROR_MESSAGE,
					serviceException.getMessage() + "[" + serviceException.getCode() + "]");
			request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
		}

	}
}

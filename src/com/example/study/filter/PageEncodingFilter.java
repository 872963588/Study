package com.example.study.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/PageEncodingFilter")
public class PageEncodingFilter implements Filter {

	private String encoding = "UTF-8";
	protected FilterConfig filterConfig;

	public PageEncodingFilter() {
	}

	@Override
	public void destroy() {
		this.encoding = null;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		request.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset=utf-8");
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		if (filterConfig.getInitParameter("encoding") != null) {
			encoding = filterConfig.getInitParameter("encoding");
		}
	}
}

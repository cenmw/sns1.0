package com.cenmw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cenmw.admin.po.User;
import com.cenmw.util.WebUtil;

public class CheckSysFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = WebUtil.getRequestURI(req);
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("adminInfo");
		String redirectURL = "/manager/index.jsp";
		if (user == null) {
			res.sendRedirect(req.getContextPath() + "/admin/index.jsp");
			return;
		} else {
			if (user.getStatus() != null && user.getStatus() == 1) {
				chain.doFilter(request, response);
				return;
			} else {
				res.sendRedirect(req.getContextPath() + redirectURL);
				return;
			}
		}

	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

}

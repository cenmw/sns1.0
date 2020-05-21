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

import com.cenmw.util.WebUtil;

public class CheckLoginFilter implements Filter {
	private String redirectURL = null;
	private String sessionKey = null;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = WebUtil.getRequestURI(req);
		HttpSession session = req.getSession();
		if (sessionKey == null) {
			chain.doFilter(request, response);
			return;
		}
		Object adminer = session.getAttribute(sessionKey);
		if (adminer != null) {
			try{
				chain.doFilter(request, response);
			}finally{
			}
			return;
		} else {
			res.sendRedirect(req.getContextPath() + redirectURL);
			return;
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		redirectURL = filterConfig.getInitParameter("redirectURL");
		sessionKey = filterConfig.getInitParameter("sessionKey");
	}

}

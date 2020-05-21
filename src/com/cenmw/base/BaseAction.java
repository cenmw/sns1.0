package com.cenmw.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.cenmw.admin.po.User;
import com.cenmw.util.PageBean;
import com.cenmw.util.RootdirectoryPopUtil;
import com.cenmw.util.cache.ehcache.CacheManager;

public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware, SessionAware, ServletContextAware {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String, Object> session;
	protected ServletContext servletContext;

	protected Integer pageSize = 10;
	protected Integer currentPage = 1;
	protected PageBean pageBean;

	public void responseJavaScriptAjax(String responseHTML) {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("application/x-javascript;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(responseHTML);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void responseHTMLAjax(String responseHTML) {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(responseHTML);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void responseJSONAjax(String json) {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("application/json;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void responseXMLAjax(String json) {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("application/xml;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 验证用户权限
	 * 
	 * @param action
	 *            该功能识别的权限
	 * @param user
	 *            用户信息
	 * @return
	 */
	public static boolean checkUserAction(String action, User user) {
		if (user.getStatus() == 1) {
			return true;
		}
		String cachename = "User";
		String cachekey = "User" + user.getId();

		Object obj = CacheManager.get(cachename, cachekey);
		if (obj == null) {
			System.out.println("用户" + user.getId() + ":没有得到用户权限");
			return false;
		} else {
			List actionList = (List) obj;
			if (actionList != null && !actionList.isEmpty()) {
				for (int i = 0; i < actionList.size(); i++) {
					String actionVal = (String) actionList.get(i);
					if (actionVal.equals(action)) {
						return true;
					}
				}
				return false;
			} else {
				return false;
			}
		}
	}

	protected static String getIndexPath(String beanName) {
		String indexDir = RootdirectoryPopUtil.rootdirectorys
				.get("luceneBaseDir") + "\\" + beanName + "";
		return indexDir;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		response = arg0;
	}

	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}

	public void setServletContext(ServletContext context) {
		this.servletContext = context;
	}

}

package com.cenmw.util;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class PageBean implements Serializable {
	private static final long serialVersionUID = 7936825571753320405L;
	private Integer pageCount=0;// 总页数
	private Integer currentPage=0;// 当前页数
	private Integer rows=0;// 总记录数
	private Integer firstRow=0;// 当前页的起始记录数
	private Integer lastRow=0;// 当前页的结束记录数
	private String parameters = "";// 链接参数
	private List pageList;// 某页列表
	private String action = "";// 请求的action链接
	private String gopage = "";
	private String simplegopage = "";
	private Integer pageSize =0;
	private String skipurl = "";
	private String backurl = "";
	private String gopageTemplate = "";// 为绝对地址
	private String gopagehtml = "";
	private String sort = "";// 排序方式
	private String sorturl = "";
	private String orderstr = "";
	private int idorder;
	private String prevPageLink = "";
	private String nextPageLink = "";

	private String homeHide = "";
	private String precHide = "";
	private String nextHide = "";
	private String lastHide = "";
	private String nextArrPageHide = "";

	private Integer nextArrPageNum = 1;

	private Integer navSize = 5;
	private String currentCss = "CurrentPageCss";

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List getPageList() {
		return pageList;
	}

	public void setPageList(List pageList) {
		this.pageList = pageList;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(Integer firstRow) {
		this.firstRow = firstRow;
	}

	public Integer getLastRow() {
		return lastRow;
	}

	public void setLastRow(Integer lastRow) {
		this.lastRow = lastRow;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setGopageTemplate(String gopageTemplate) {
		this.gopageTemplate = gopageTemplate;
	}

	public void setGopageTemplate(HttpServletRequest request) {
		this.gopageTemplate = request.getRealPath("/common/gopage/gopage.html");
	}

	public void setGopageTemplate(HttpServletRequest request,
			String gopageTemplate) {
		this.gopageTemplate = request.getRealPath(gopageTemplate);
	}

	/**
	 * <!--HomePageLink--><!--HomePage--><!--HomePageCss-->
	 * <!--PrevPageLink--><!--PrevPage--><!--PrevPageCss-->
	 * 
	 * <!--GoPageList-->
	 * <!--CurrentPageCss--><!--GoPageLink--><!--GoPageHref--><!--PageIndex-->
	 * <!--/GoPageList-->
	 * 
	 * <!--NextPageLink--><!--NextPage--><!--NextPageCss-->
	 * 
	 * @param html
	 * @return
	 */
	/**
	 * @param html
	 * @return
	 */
	/**
	 * @param html
	 * @return
	 */
	private String replaceGopage(String html) {
		int navLen = (pageCount + navSize - 1) / navSize;
		int curNav = 1;
		curNav = (currentPage + navSize - 1) / navSize;
		int begin = (curNav - 1) * navSize + 1;
		int end = curNav * navSize;
		if (end > pageCount)
			end = pageCount;
		if (!parameters.equals("")) {
			parameters = parameters + "&";
		}
		String HomePageLink = "首页";
		String HomePage = "javascript:";
		String HomePageCss = "disabled";
		if (currentPage == 1) {
		} else {
			HomePage = action + "?" + parameters + "currentPage=" + 1
					+ "&pageSize=" + pageSize;
			HomePageLink = "<a href=\"" + HomePage + "\">首页</a>";
			HomePageCss = "";
		}
		html = html.replaceAll("<!--HomePageLink-->", HomePageLink);
		html = html.replaceAll("<!--HomePage-->", HomePage);
		html = html.replaceAll("<!--HomePageCss-->", HomePageCss);
		int prevPage = currentPage - 1;
		if (prevPage < 1) {
			prevPage = 1;
		}
		String PrevPageLink = "上一页";
		String PrevPage = "javascript:";
		String PrevPageCss = "disabled";
		if (currentPage == 1) {
		} else {
			PrevPage = action + "?" + parameters + "currentPage=" + prevPage
					+ "&pageSize=" + pageSize;
			PrevPageLink = "<a href=\"" + PrevPage + "\">上一页</a>";
			PrevPageCss = "";
		}
		html = html.replaceAll("<!--PrevPageLink-->", PrevPageLink);
		html = html.replaceAll("<!--PrevPage-->", PrevPage);
		html = html.replaceAll("<!--PrevPageCss-->", PrevPageCss);
		String PrevNavLink1 = "";
		String PageCountLink = "";
		if ((pageCount - currentPage) > 3) {
			PrevNavLink1 = "<li>...</li>";
		}
		if ((pageCount - currentPage) > 2) {
			String PrevNav1 = action + "?" + parameters + "currentPage="
					+ pageCount + "&pageSize=" + pageSize;
			PageCountLink = "<li><a href=\"" + PrevNav1 + "\">" + pageCount
					+ "</a></li>";
		}
		html = html.replaceAll("<!--PrevNavLink-->", PrevNavLink1);
		html = html.replaceAll("<!--PageCountLink-->", PageCountLink);
		prevPageLink = PrevPage;
		Pattern p = Pattern
				.compile("<!--GoPageList-->([\\s\\S]*?)<!--/GoPageList-->");
		Matcher m = p.matcher(html);

		StringBuffer sb = new StringBuffer();
		String pageBody = "";
		while (m.find()) {
			pageBody = m.group(1);
		}
		String PrevNavLink = "";
		String PrevNav = "javascript:";
		if (begin > navSize) {
			int prevNavPage = begin - 1;
			PrevNav = action + "?" + parameters + "currentPage=" + prevNavPage
					+ "&pageSize=" + pageSize;
			PrevNavLink = "<a href=\"" + PrevNav + "\">...</a>";
		}
		html = html.replaceAll("<!--PrevNavLink-->", PrevNavLink);//
		html = html.replaceAll("<!--PrevNav-->", PrevNav);
		int newbegin = 1;
		int newend = pageCount;
		if (currentPage > 2) {
			newbegin = currentPage - 2;
			if ((pageCount - currentPage) == 0 && pageCount > 4) {
				newbegin = currentPage - 4;
			} else if ((pageCount - currentPage) == 0 && pageCount > 3) {
				newbegin = currentPage - 3;
			} else if ((pageCount - currentPage) == 1 && pageCount > 3) {
				newbegin = currentPage - 3;
			}
		}
		if ((pageCount - currentPage) > 2) {
			newend = currentPage + 2;
			if (currentPage == 1 && (pageCount - currentPage) > 4) {
				newend = currentPage + 4;
			} else if (currentPage == 1 && (pageCount - currentPage) > 3) {
				newend = currentPage + 3;
			} else if (currentPage == 2 && (pageCount - currentPage) > 3) {
				newend = currentPage + 3;
			}
		}
		for (int i = newbegin; i <= newend; i++) {
			String str = pageBody;
			String CurrentPageCss = "";
			String GoPageLink = "";
			String GoPageHref = "javascript:";
			if (currentPage != i) {
				GoPageHref = action + "?" + parameters + "currentPage=" + i
						+ "&pageSize=" + pageSize;
				GoPageLink = "<a href=\"" + GoPageHref + "\">" + i + "</a>";
			} else {
				GoPageLink = "<a href=\"javascript:\">" + i + "</a>";
			}
			if (i == currentPage) {
				CurrentPageCss = currentCss;
				GoPageLink = "<span class=\"" + CurrentPageCss + "\">" + i
						+ "</span>";
			}
			str = str.replaceAll("<!--CurrentPageCss-->", CurrentPageCss);
			str = str.replaceAll("<!--GoPageLink-->", GoPageLink);
			str = str.replaceAll("<!--GoPageHref-->", GoPageHref);
			str = str.replaceAll("<!--PageIndex-->", "" + i);
			sb.append(str);
		}
		html = html.replaceAll(
				"<!--GoPageList-->([\\s\\S]*?)<!--/GoPageList-->",
				sb.toString());
		String NextNavLink = "";
		String NextNav = "javascript:";
		if (end < pageCount) {
			int nextNavPage = end + 1;
			NextNav = action + "?" + parameters + "currentPage=" + nextNavPage
					+ "&pageSize=" + pageSize;
			NextNavLink = "<a href=\"" + NextNav + "\">...</a>";
		}
		html = html.replaceAll("<!--NextNavLink-->", NextNavLink);//
		html = html.replaceAll("<!--NextNav-->", NextNav);

		int nextPage = currentPage + 1;
		if (nextPage > pageCount) {
			nextPage = pageCount;
		}
		String NextPageLink = "下一页";
		String NextPage = "javascript:";
		String NextPageCss = "disabled";
		if (currentPage == pageCount || pageCount == 0) {
		} else {
			NextPage = action + "?" + parameters + "currentPage=" + nextPage
					+ "&pageSize=" + pageSize;
			NextPageLink = "<a href=\"" + NextPage + "\">下一页</a>";
			NextPageCss = "";
		}
		html = html.replaceAll("<!--NextPageLink-->", NextPageLink);//
		html = html.replaceAll("<!--NextPage-->", NextPage);
		html = html.replaceAll("<!--NextPageCss-->", NextPageCss);
		nextPageLink = NextPage;
		String LastPageLink = "末页";
		String LastPage = "javascript:";
		String LastPageCss = "disabled";
		String LastPageNumLink = "";
		if (currentPage == pageCount || pageCount == 0) {
		} else {
			LastPage = action + "?" + parameters + "currentPage=" + pageCount
					+ "&pageSize=" + pageSize;
			LastPageLink = "<a href=\"" + LastPage + "\">末页</a>";

			if (navSize < pageCount) {
				LastPageNumLink = "<a href=\"" + LastPage + "\">" + pageCount
						+ "</a>";
			}
			LastPageCss = "";
		}
		html = html.replaceAll("<!--LastPageLink-->", LastPageLink);
		html = html.replaceAll("<!--LastPageNumLink-->", LastPageNumLink);
		html = html.replaceAll("<!--LastPage-->", LastPage);
		html = html.replaceAll("<!--HomePageCss-->", HomePageCss);
		html = html.replaceAll("<!--PageCount-->", "" + pageCount);
		html = html.replaceAll("<!--CurrentPage-->", "" + currentPage);
		html = html.replaceAll("<!--Rows-->", "" + rows);
		html = html.replaceAll("<!--SkipUrl-->", "" + getSkipurl());
		return html;
	}

	public String getGopagehtml() {
		String html = "";
		html = TemplateUtils.getTemplateHTML(gopageTemplate);
		html = replaceGopage(html);
		return html;
	}

	public String getGopageHTML(HttpServletRequest request, String gt) {
		String html = "";
		String htmlpath = request.getRealPath(gt);
		html = TemplateUtils.getTemplateHTML(htmlpath);
		html = replaceGopage(html);
		return html;
	}

	public String getGopage() {
		int navLen = (pageCount + navSize - 1) / navSize;
		int curNav = 1;
		curNav = (currentPage + navSize - 1) / navSize;
		int begin = (curNav - 1) * 5 + 1;
		int end = curNav * 5;
		if (end > pageCount)
			end = pageCount;
		if (!parameters.equals("")) {
			parameters = parameters + "&";
		}
		String tmphtml = "";
		if (currentPage == 1) {
			tmphtml = "<span>首页</span>";
		} else {
			tmphtml = "<a href=\"" + action + "?" + parameters + "currentPage="
					+ 1 + "&pageSize=" + pageSize + "&sort=" + sort
					+ "\">首页</a>&nbsp;";
		}
		int prevPage = currentPage - 1;
		if (prevPage < 1) {
			prevPage = 1;
		}
		if (currentPage == 1) {
			tmphtml += "<span>上一页</span>";
		} else {
			tmphtml += "<a href=\"" + action + "?" + parameters
					+ "currentPage=" + prevPage + "&pageSize=" + pageSize
					+ "&sort=" + sort + "\">上一页</a>&nbsp;";
		}

		for (int i = begin; i <= end; i++) {
			if (i == currentPage) {
				tmphtml += "<a href=\"" + action + "?" + parameters
						+ "currentPage=" + i + "&pageSize=" + pageSize
						+ "&sort=" + sort + "\" class=\"currentpage\">" + i
						+ "</a>&nbsp;";
			} else {
				tmphtml += "<a href=\"" + action + "?" + parameters
						+ "currentPage=" + i + "&pageSize=" + pageSize
						+ "&sort=" + sort + "\">" + i + "</a>&nbsp;";
			}
		}

		int nextPage = currentPage + 1;
		if (nextPage > pageCount) {
			nextPage = pageCount;
		}
		if (currentPage == pageCount || pageCount == 0) {
			tmphtml += "<span>下一页</span>";
		} else {
			// tmphtml+="<a href=\""+action+"?"+parameters+"currentPage="+nextPage+"&pageSize="+pageSize+"\">下一页</a>&nbsp;";
			if (pageCount > 1) {
				tmphtml += "<a href=\"" + action + "?" + parameters
						+ "currentPage=" + nextPage + "&pageSize=" + pageSize
						+ "&sort=" + sort + "\">下一页</a>&nbsp;";
			}
		}
		if (currentPage == pageCount || pageCount == 0) {
			tmphtml += "<span>末页</span>";
		} else {
			if (pageCount > 1) {
				tmphtml += "<a href=\"" + action + "?" + parameters
						+ "currentPage=" + pageCount + "&pageSize=" + pageSize
						+ "&sort=" + sort + "\">末页</a>";
			}
		}
		return tmphtml;
	}

	public String getSimplegopage() {
		int navLen = (pageCount + navSize - 1) / navSize;
		int curNav = 1;
		curNav = (currentPage + navSize - 1) / navSize;
		int begin = (curNav - 1) * 5 + 1;
		int end = curNav * 5;
		if (end > pageCount)
			end = pageCount;
		if (!parameters.equals("")) {
			parameters = parameters + "&";
		}
		String tmphtml = "";
		if (currentPage == 1) {
			tmphtml = "<span>首页</span>";
		} else {
			tmphtml = "<a href=\"" + action + "?" + parameters + "currentPage="
					+ 1 + "&pageSize=" + pageSize + "&sort=" + sort
					+ "\">首页</a>&nbsp;";
		}
		int prevPage = currentPage - 1;
		if (prevPage < 1) {
			prevPage = 1;
		}
		if (currentPage == 1) {
			tmphtml += "<span>上一页</span>";
		} else {
			tmphtml += "<a href=\"" + action + "?" + parameters
					+ "currentPage=" + prevPage + "&pageSize=" + pageSize
					+ "&sort=" + sort + "\">上一页</a>&nbsp;";
		}

		int nextPage = currentPage + 1;
		if (nextPage > pageCount) {
			nextPage = pageCount;
		}
		if (currentPage == pageCount || pageCount == 0) {
			tmphtml += "<span>下一页</span>";
		} else {
			if (pageCount > 1) {
				tmphtml += "<a href=\"" + action + "?" + parameters
						+ "currentPage=" + nextPage + "&pageSize=" + pageSize
						+ "&sort=" + sort + "\">下一页</a>&nbsp;";
			}
		}
		if (currentPage == pageCount || pageCount == 0) {
			tmphtml += "<span>末页</span>";
		} else {
			// tmphtml+="<a href=\""+action+"?"+parameters+"currentPage="+nextPage+"&pageSize="+pageSize+"\">下一页</a>&nbsp;";
			if (pageCount > 1) {
				tmphtml += "<a href=\"" + action + "?" + parameters
						+ "currentPage=" + pageCount + "&pageSize=" + pageSize
						+ "&sort=" + sort + "\">末页</a>";
			}
		}
		return tmphtml;
	}

	public String getSkipurl() {
		String op = "";
		if (!parameters.equals("")) {
			op = "&";
		}
		String url = action + "?" + parameters + op + "pageSize=" + pageSize
				+ "&sort=" + sort + "&currentPage=";
		return url;
	}

	public String getBackurl() {
		String op = "";
		if (!parameters.equals("")) {
			op = "&";
		}
		String url = action + "?" + parameters + op + "pageSize=" + pageSize
				+ "&currentPage=" + currentPage + "&sort=" + sort;
		return url;
	}

	public String getSorturl() {
		String s = "desc";
		if (sort.toLowerCase().equals("desc")) {
			s = "asc";
		}
		String op = "";
		if (!parameters.equals("")) {
			op = "&";
		}
		String url = action + "?" + parameters + op + "pageSize=" + pageSize
				+ "&currentPage=" + currentPage + "&sort=" + s;
		return url;
	}

	public void setSort(String sort) {
		if (sort == null || sort.trim().length() == 0) {
			sort = "asc";
		}
		this.sort = sort;
	}

	public String getOrderstr() {
		return orderstr;
	}

	public void setOrderstr(String orderstr) {
		this.orderstr = orderstr;
	}

	public int getIdorder() {
		return idorder;
	}

	public void setIdorder(int idorder) {
		this.idorder = idorder;
	}

	public String getPrevPageLink() {
		return prevPageLink;
	}

	public String getNextPageLink() {
		return nextPageLink;
	}

	public void setNavSize(Integer navSize) {
		this.navSize = navSize;
	}

	public void setCurrentCss(String currentCss) {
		this.currentCss = currentCss;
	}

}

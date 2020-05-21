package com.cenmw.util;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class LucenePageBean implements Serializable {
	private Integer pageCount;// 总页数
	private Integer currentPage;// 当前页数
	private Integer rows;// 总记录数
	private List pageList;// 某页列表
	private Integer pageSize;
	private Integer ordertype;
	private String nextPage;
	private String prevPage;
	private String topPage;
	private String lastPage;
	private String href;
	private String gopageTemplate;
	private String gopage;
	private String pagelink;

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

	public List getPageList() {
		return pageList;
	}

	public void setPageList(List pageList) {
		this.pageList = pageList;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public String getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(String prevPage) {
		this.prevPage = prevPage;
	}

	public String getTopPage() {
		return topPage;
	}

	public void setTopPage(String topPage) {
		this.topPage = topPage;
	}

	public String getLastPage() {
		return lastPage;
	}

	public void setLastPage(String lastPage) {
		this.lastPage = lastPage;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getGopageTemplate() {
		return gopageTemplate;
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

	public String getGopage() {
		String html = "";
		html = TemplateUtils.getTemplateHTML(gopageTemplate);
		int navSize = 5;
		int navLen = (pageCount + navSize - 1) / navSize;
		int curNav = 1;
		curNav = (currentPage + navSize - 1) / navSize;
		int begin = (curNav - 1) * navSize + 1;
		int end = curNav * navSize;
		if (end > pageCount)
			end = pageCount;
		String HomePageLink = "首页";
		String HomePage = "javascript:";
		if (currentPage == 1) {
		} else {
			HomePageLink = "<a href=\"" + getTopPage() + "\">首页</a>";
			HomePage = getTopPage();
		}
		html = html.replaceAll("<!--HomePageLink-->", HomePageLink);
		html = html.replaceAll("<!--HomePage-->", HomePage);
		int prevPage = currentPage - 1;
		if (prevPage < 1) {
			prevPage = 1;
		}
		String PrevPageLink = "上一页";
		String PrevPage = "javascript:";
		if (currentPage == 1) {
		} else {
			PrevPageLink = "<a href=\"" + getPrevPage() + "\">上一页</a>";
			PrevPage = getPrevPage();
		}
		html = html.replaceAll("<!--PrevPageLink-->", PrevPageLink);
		html = html.replaceAll("<!--PrevPage-->", PrevPage);

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
			PrevNav = getPagelink(prevNavPage);
			PrevNavLink = "<a href=\"" + PrevNav + "\">...</a>";
		}
		html = html.replaceAll("<!--PrevNavLink-->", PrevNavLink);//
		html = html.replaceAll("<!--PrevNav-->", PrevNav);

		for (int i = begin; i <= end; i++) {
			String str = pageBody;
			String CurrentPageCss = "";
			String GoPageLink = "";
			String GoPageHref = "javascript:";
			if (i == currentPage) {
				CurrentPageCss = "CurrentPageCss";
			}
			GoPageLink = "<a href=\"" + getPagelink(i) + "\">" + i + "</a>";
			GoPageHref = getPagelink(i);
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
			NextNav = getPagelink(nextNavPage);
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
		if (currentPage == pageCount || pageCount == 0) {
		} else {
			NextPageLink = "<a href=\"" + getNextPage() + "\">下一页</a>";
			NextPage = getNextPage();
		}
		html = html.replaceAll("<!--NextPageLink-->", NextPageLink);
		html = html.replaceAll("<!--NextPage-->", NextPage);
		String LastPageLink = "末页";
		String LastPage = "javascript:";
		if (currentPage == pageCount || pageCount == 0) {
		} else {
			LastPageLink = "<a href=\"" + getLastPage() + "\">末页</a>";
			LastPage = getLastPage();
		}
		html = html.replaceAll("<!--LastPageLink-->", LastPageLink);
		html = html.replaceAll("<!--LastPage-->", LastPage);
		html = html.replaceAll("<!--PageCount-->", "" + pageCount);
		html = html.replaceAll("<!--CurrentPage-->", "" + currentPage);
		return html;
	}

	public void setGopage(String gopage) {
		this.gopage = gopage;
	}

	public String getPagelink() {
		return pagelink;
	}

	public String getPagelink(int num) {
		String link = pagelink + num;
		return link;
	}

	public void setPagelink(String pagelink) {
		this.pagelink = pagelink;
	}

}

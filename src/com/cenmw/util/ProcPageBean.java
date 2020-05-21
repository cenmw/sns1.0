package com.cenmw.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ProcPageBean implements Serializable {
	private Integer pageCount;// 总页数
	private Integer currentPage;// 当前页数
	private Integer rows;// 总记录数
	private Integer firstRow;// 当前页的起始记录数
	private Integer lastRow;// 当前页的结束记录数
	private String parameters = "";// 链接参数
	private List pageList;// 某页列表
	private String action = "";// 请求的action链接
	private String gopage = "";
	private String simplegopage = "";
	private Integer pageSize;
	private String skipurl = "";
	private String backurl = "";
	private String gopageTemplate = "";// 为绝对地址
	private String gopagehtml = "";
	private String sorturl = "";
	private String orderstr = "";
	private int idorder;

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

	public String getGopagehtml() {
		String html = "";
		File input = new File(gopageTemplate);
		try {
			Document doc = Jsoup.parse(input, "UTF-8");
			html = doc.body().html();
			html = html.replaceAll("&lt;", "<");
			html = html.replaceAll("&gt;", ">");
			html = html.replaceAll("&amp;", "&");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int navSize = 5;
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
		if (currentPage == 1) {
		} else {
			HomePageLink = "<a href=\"" + action + "?" + parameters
					+ "orderstr=" + orderstr + "&idorder=" + idorder
					+ "&currentPage=" + 1 + "&pageSize=" + pageSize
					+ "\">首页</a>";
			HomePage = action + "?" + parameters + "orderstr=" + orderstr
					+ "&idorder=" + idorder + "&currentPage=" + 1
					+ "&pageSize=" + pageSize;
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
			PrevPageLink = "<a href=\"" + action + "?" + parameters
					+ "orderstr=" + orderstr + "&idorder=" + idorder
					+ "&currentPage=" + prevPage + "&pageSize=" + pageSize
					+ "\">上一页</a>";
			PrevPage = action + "?" + parameters + "orderstr=" + orderstr
					+ "&idorder=" + idorder + "&currentPage=" + prevPage
					+ "&pageSize=" + pageSize;
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

		for (int i = begin; i <= end; i++) {
			String str = pageBody;
			String CurrentPageCss = "";
			String GoPageLink = "";
			String GoPageHref = "javascript:";
			if (i == currentPage) {
				CurrentPageCss = "CurrentPageCss";
			}
			GoPageLink = "<a href=\"" + action + "?" + parameters + "orderstr="
					+ orderstr + "&idorder=" + idorder + "&currentPage=" + i
					+ "&pageSize=" + pageSize + "\">" + i + "</a>";
			GoPageHref = action + "?" + parameters + "orderstr=" + orderstr
					+ "&idorder=" + idorder + "&currentPage=" + i
					+ "&pageSize=" + pageSize;
			str = str.replaceAll("<!--CurrentPageCss-->", CurrentPageCss);
			str = str.replaceAll("<!--GoPageLink-->", GoPageLink);
			str = str.replaceAll("<!--GoPageHref-->", GoPageHref);
			str = str.replaceAll("<!--PageIndex-->", "" + i);
			sb.append(str);
		}
		html = html.replaceAll(
				"<!--GoPageList-->([\\s\\S]*?)<!--/GoPageList-->",
				sb.toString());

		int nextPage = currentPage + 1;
		if (nextPage > pageCount) {
			nextPage = pageCount;
		}
		String NextPageLink = "下一页";
		String NextPage = "javascript:";
		if (currentPage == pageCount || pageCount == 0) {
		} else {
			NextPageLink = "<a href=\"" + action + "?" + parameters
					+ "orderstr=" + orderstr + "&idorder=" + idorder
					+ "&currentPage=" + nextPage + "&pageSize=" + pageSize
					+ "\">下一页</a>";
			NextPage = action + "?" + parameters + "orderstr=" + orderstr
					+ "&idorder=" + idorder + "&currentPage=" + nextPage
					+ "&pageSize=" + pageSize;
		}
		html = html.replaceAll("<!--NextPageLink-->", NextPageLink);
		html = html.replaceAll("<!--NextPage-->", NextPage);
		String LastPageLink = "末页";
		String LastPage = "javascript:";
		if (currentPage == pageCount || pageCount == 0) {
		} else {
			LastPageLink = "<a href=\"" + action + "?" + parameters
					+ "orderstr=" + orderstr + "&idorder=" + idorder
					+ "&currentPage=" + pageCount + "&pageSize=" + pageSize
					+ "\">末页</a>";
			LastPage = action + "?" + parameters + "orderstr=" + orderstr
					+ "&idorder=" + idorder + "&currentPage=" + pageCount
					+ "&pageSize=" + pageSize;
		}
		html = html.replaceAll("<!--LastPageLink-->", LastPageLink);
		html = html.replaceAll("<!--LastPage-->", LastPage);
		return html;
	}

	public String getGopage() {
		int navSize = 5;
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
			tmphtml = "<a href=\"" + action + "?" + parameters + "&orderstr="
					+ orderstr + "&idorder=" + idorder + "&currentPage=" + 1
					+ "&pageSize=" + pageSize + "\">首页</a>&nbsp;";
		}
		int prevPage = currentPage - 1;
		if (prevPage < 1) {
			prevPage = 1;
		}
		if (currentPage == 1) {
			tmphtml += "<span>上一页</span>";
		} else {
			tmphtml += "<a href=\"" + action + "?" + parameters + "&orderstr="
					+ orderstr + "&idorder=" + idorder + "&currentPage="
					+ prevPage + "&pageSize=" + pageSize + "\">上一页</a>&nbsp;";
		}

		for (int i = begin; i <= end; i++) {
			if (i == currentPage) {
				tmphtml += "<a href=\"" + action + "?" + parameters
						+ "&orderstr=" + orderstr + "&idorder=" + idorder
						+ "&currentPage=" + i + "&pageSize=" + pageSize
						+ "\" class=\"currentpage\">" + i + "</a>&nbsp;";
			} else {
				tmphtml += "<a href=\"" + action + "?" + parameters
						+ "&orderstr=" + orderstr + "&idorder=" + idorder
						+ "&currentPage=" + i + "&pageSize=" + pageSize + "\">"
						+ i + "</a>&nbsp;";
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
						+ "&orderstr=" + orderstr + "&idorder=" + idorder
						+ "&currentPage=" + nextPage + "&pageSize=" + pageSize
						+ "\">下一页</a>&nbsp;";
			}
		}
		if (currentPage == pageCount || pageCount == 0) {
			tmphtml += "<span>末页</span>";
		} else {
			if (pageCount > 1) {
				tmphtml += "<a href=\"" + action + "?" + parameters
						+ "&orderstr=" + orderstr + "&idorder=" + idorder
						+ "&currentPage=" + pageCount + "&pageSize=" + pageSize
						+ "\">末页</a>";
			}
		}
		return tmphtml;
	}

	public String getSimplegopage() {
		int navSize = 5;
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
			tmphtml = "<a href=\"" + action + "?" + parameters + "&orderstr="
					+ orderstr + "&idorder=" + idorder + "&currentPage=" + 1
					+ "&pageSize=" + pageSize + "\">首页</a>&nbsp;";
		}
		int prevPage = currentPage - 1;
		if (prevPage < 1) {
			prevPage = 1;
		}
		if (currentPage == 1) {
			tmphtml += "<span>上一页</span>";
		} else {
			tmphtml += "<a href=\"" + action + "?" + parameters + "&orderstr="
					+ orderstr + "&idorder=" + idorder + "&currentPage="
					+ prevPage + "&pageSize=" + pageSize + "\">上一页</a>&nbsp;";
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
						+ "&orderstr=" + orderstr + "&idorder=" + idorder
						+ "&currentPage=" + nextPage + "&pageSize=" + pageSize
						+ "\">下一页</a>&nbsp;";
			}
		}
		if (currentPage == pageCount || pageCount == 0) {
			tmphtml += "<span>末页</span>";
		} else {
			// tmphtml+="<a href=\""+action+"?"+parameters+"currentPage="+nextPage+"&pageSize="+pageSize+"\">下一页</a>&nbsp;";
			if (pageCount > 1) {
				tmphtml += "<a href=\"" + action + "?" + parameters
						+ "&orderstr=" + orderstr + "&idorder=" + idorder
						+ "&currentPage=" + pageCount + "&pageSize=" + pageSize
						+ "\">末页</a>";
			}
		}
		return tmphtml;
	}

	public String getSkipurl() {
		String op = "";
		if (!parameters.equals("")) {
			op = "&";
		}
		String url = action + "?" + parameters + op + "&orderstr=" + orderstr
				+ "&idorder=" + idorder + "&pageSize=" + pageSize
				+ "&currentPage=";
		return url;
	}

	public String getBackurl() {
		String op = "";
		if (!parameters.equals("")) {
			op = "&";
		}
		String url = action + "?" + parameters + op + "orderstr=" + orderstr
				+ "&idorder=" + idorder + "&pageSize=" + pageSize
				+ "&currentPage=" + currentPage;
		return url;
	}

	public String getSorturl() {
		String op = "";
		if (!parameters.equals("")) {
			op = "&";
		}
		String url = action + "?" + parameters + op + "&idorder="
				+ "&pageSize=" + pageSize + "&currentPage=" + currentPage
				+ "&orderstr=" + orderstr;
		return url;
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
}

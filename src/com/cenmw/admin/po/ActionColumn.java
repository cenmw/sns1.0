package com.cenmw.admin.po;

// default package

/**
 * ActionColumn entity. @author MyEclipse Persistence Tools
 */

public class ActionColumn implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer actioncolumnid;
	private String actioncolumnname;
	private String actioncolumnlink;
	private String actioncolumnpic;
	private double sort;
	private Integer viewmode;

	// Constructors

	/** default constructor */
	public ActionColumn() {
	}

	/** full constructor */
	public ActionColumn(String actioncolumnname, String actioncolumnlink,
			String actioncolumnpic, double sort) {
		this.actioncolumnname = actioncolumnname;
		this.actioncolumnlink = actioncolumnlink;
		this.actioncolumnpic = actioncolumnpic;
		this.sort = sort;
	}

	// Property accessors

	public Integer getActioncolumnid() {
		return this.actioncolumnid;
	}

	public void setActioncolumnid(Integer actioncolumnid) {
		this.actioncolumnid = actioncolumnid;
	}

	public String getActioncolumnname() {
		return this.actioncolumnname;
	}

	public void setActioncolumnname(String actioncolumnname) {
		this.actioncolumnname = actioncolumnname;
	}

	public String getActioncolumnlink() {
		return this.actioncolumnlink;
	}

	public void setActioncolumnlink(String actioncolumnlink) {
		this.actioncolumnlink = actioncolumnlink;
	}

	public String getActioncolumnpic() {
		return this.actioncolumnpic;
	}

	public void setActioncolumnpic(String actioncolumnpic) {
		this.actioncolumnpic = actioncolumnpic;
	}

	public double getSort() {
		return this.sort;
	}

	public void setSort(double sort) {
		this.sort = sort;
	}

	public Integer getViewmode() {
		return viewmode;
	}

	public void setViewmode(Integer viewmode) {
		this.viewmode = viewmode;
	}

}
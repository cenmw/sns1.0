package com.cenmw.admin.po;

// default package

/**
 * Action entity. @author MyEclipse Persistence Tools
 */

public class Action implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer actionid;
	private String actionname;
	private Integer actioncolumnid;
	private String action;

	// Constructors

	/** default constructor */
	public Action() {
	}

	/** full constructor */
	public Action(String actionname, Integer actioncolumnid, String action) {
		this.actionname = actionname;
		this.actioncolumnid = actioncolumnid;
		this.action = action;
	}

	// Property accessors

	public Integer getActionid() {
		return this.actionid;
	}

	public void setActionid(Integer actionid) {
		this.actionid = actionid;
	}

	public String getActionname() {
		return this.actionname;
	}

	public void setActionname(String actionname) {
		this.actionname = actionname;
	}

	public Integer getActioncolumnid() {
		return this.actioncolumnid;
	}

	public void setActioncolumnid(Integer actioncolumnid) {
		this.actioncolumnid = actioncolumnid;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
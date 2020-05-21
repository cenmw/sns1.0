package com.cenmw.admin.po;

// default package

import java.util.Date;

/**
 * ActionGroup entity. @author MyEclipse Persistence Tools
 */

public class ActionGroup implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer id;
	private String action;
	private String actionname;
	private Integer groupid;
	private String groupname;
	private Integer createid;
	private String createname;
	private Date ctime;

	// Constructors

	/** default constructor */
	public ActionGroup() {
	}

	/** full constructor */
	public ActionGroup(String action, Integer groupid, Integer createid,
			String createname, Date ctime) {
		this.action = action;
		this.groupid = groupid;
		this.createid = createid;
		this.createname = createname;
		this.ctime = ctime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public Integer getCreateid() {
		return this.createid;
	}

	public void setCreateid(Integer createid) {
		this.createid = createid;
	}

	public String getCreatename() {
		return this.createname;
	}

	public void setCreatename(String createname) {
		this.createname = createname;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getActionname() {
		return actionname;
	}

	public void setActionname(String actionname) {
		this.actionname = actionname;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

}
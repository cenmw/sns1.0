package com.cenmw.admin.po;

// default package

import java.util.Date;

/**
 * GroupManager entity. @author MyEclipse Persistence Tools
 */

public class GroupManager implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer groupid;
	private String groupname;
	private String groupinfo;
	private Integer createid;
	private String createname;
	private Date ctime;

	// Constructors

	/** default constructor */
	public GroupManager() {
	}

	/** full constructor */
	public GroupManager(String groupname, String groupinfo, Integer createid,
			String createname, Date ctime) {
		this.groupname = groupname;
		this.groupinfo = groupinfo;
		this.createid = createid;
		this.createname = createname;
		this.ctime = ctime;
	}

	// Property accessors

	public Integer getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getGroupinfo() {
		return this.groupinfo;
	}

	public void setGroupinfo(String groupinfo) {
		this.groupinfo = groupinfo;
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

}
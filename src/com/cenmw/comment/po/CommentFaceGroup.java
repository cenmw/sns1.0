package com.cenmw.comment.po;

import java.io.Serializable;

public class CommentFaceGroup implements Serializable {
	private Integer id;
	private String groupname;

	public CommentFaceGroup() {

	}

	public CommentFaceGroup(Integer id, String groupname) {
		super();
		this.id = id;
		this.groupname = groupname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

}

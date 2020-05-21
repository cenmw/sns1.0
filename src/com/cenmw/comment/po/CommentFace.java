package com.cenmw.comment.po;

import java.io.Serializable;
import java.util.Date;

public class CommentFace implements Serializable {
	private Integer id;
	private String facecode;
	private String facepic;
	private Integer sort;
	private Integer facegroupid;
	private Date ctime;
	public CommentFace(){
		
	}

	public CommentFace(Integer id, String facecode, String facepic,
			Integer sort, Integer facegroupid, Date ctime) {
		super();
		this.id = id;
		this.facecode = facecode;
		this.facepic = facepic;
		this.sort = sort;
		this.facegroupid = facegroupid;
		this.ctime = ctime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFacecode() {
		return facecode;
	}

	public void setFacecode(String facecode) {
		this.facecode = facecode;
	}

	public String getFacepic() {
		return facepic;
	}

	public void setFacepic(String facepic) {
		this.facepic = facepic;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getFacegroupid() {
		return facegroupid;
	}

	public void setFacegroupid(Integer facegroupid) {
		this.facegroupid = facegroupid;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}

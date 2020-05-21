package com.cenmw.comment.vo;

public class CommentFaceVo {
	private String title;
	private String pic;

	public CommentFaceVo() {
	}

	public CommentFaceVo(String title, String pic) {
		super();
		this.title = title;
		this.pic = pic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}

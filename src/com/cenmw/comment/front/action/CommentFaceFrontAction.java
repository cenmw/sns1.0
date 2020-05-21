package com.cenmw.comment.front.action;

import java.util.ArrayList;
import java.util.List;

import com.cenmw.base.BaseAction;
import com.cenmw.comment.front.service.CommentFaceFrontService;
import com.cenmw.comment.po.CommentFace;
import com.cenmw.comment.vo.CommentFaceVo;
import com.cenmw.util.JsonUtil;

public class CommentFaceFrontAction extends BaseAction {
	private CommentFaceFrontService commentFaceFrontService;

	/**
	 * 表情列表返回json
	 * 
	 * @return
	 */
	public String facelist() {
		List<CommentFaceVo> vlist = new ArrayList<CommentFaceVo>();
		List<CommentFace> clist = commentFaceFrontService
				.getCommentFaceDefaultList();
		for (CommentFace c : clist) {
			vlist.add(new CommentFaceVo(c.getFacecode(), c.getFacepic()));
		}
		String json = JsonUtil.getJsonStrByVOArray(vlist);
		responseJSONAjax(json);
		return null;
	}

	public CommentFaceFrontService getCommentFaceFrontService() {
		return commentFaceFrontService;
	}

	public void setCommentFaceFrontService(
			CommentFaceFrontService commentFaceFrontService) {
		this.commentFaceFrontService = commentFaceFrontService;
	}

}

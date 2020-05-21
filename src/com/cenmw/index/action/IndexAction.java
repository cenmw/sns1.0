package com.cenmw.index.action;

import java.util.List;

import com.cenmw.base.BaseAction;
import com.cenmw.content.front.service.ContentInfoFrontService;
import com.cenmw.member.front.service.MemberInfoFrontService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.ConstantUtils;

public class IndexAction extends BaseAction {
	private MemberInfoFrontService memberInfoFrontService;
	private MemberInfo memberInfo;
	private Integer id;
	
	// 首页
	private List indexbiglist; // 首页大图
	private ContentInfoFrontService contentInfoFrontService;

	/**
	 * 注册用户
	 * 
	 * @return
	 */
	public String index() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (memberInfo != null) {
			return "login";
		}
		// 首页大图
		indexbiglist = contentInfoFrontService.findContentInfoInList(7, 10);
		return SUCCESS;
	}
	
	public MemberInfoFrontService getMemberInfoFrontService() {
		return memberInfoFrontService;
	}

	public void setMemberInfoFrontService(
			MemberInfoFrontService memberInfoFrontService) {
		this.memberInfoFrontService = memberInfoFrontService;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List getIndexbiglist() {
		return indexbiglist;
	}

	public void setIndexbiglist(List indexbiglist) {
		this.indexbiglist = indexbiglist;
	}

	public ContentInfoFrontService getContentInfoFrontService() {
		return contentInfoFrontService;
	}

	public void setContentInfoFrontService(
			ContentInfoFrontService contentInfoFrontService) {
		this.contentInfoFrontService = contentInfoFrontService;
	}


}

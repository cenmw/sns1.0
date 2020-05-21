package com.cenmw.member.center.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.comment.front.service.CommentInfoFrontService;
import com.cenmw.member.center.service.MemberFriendCenterService;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.center.service.MemberMoodCenterService;
import com.cenmw.member.center.service.MemberPraiseCenterService;
import com.cenmw.member.center.service.MemberReportCenterService;
import com.cenmw.member.center.service.MemberStatusCenterService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.member.po.MemberMood;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberMoodCenterAction extends BaseAction {
	/**
	 * 会员心情 模块
	 */
	private MemberMoodCenterService memberMoodCenterService;
	private MemberInfoCenterService memberInfoCenterService;
	private MemberStatusCenterService memberStatusCenterService;
	private MemberFriendCenterService memberFriendCenterService;
	private MemberPraiseCenterService memberPraiseCenterService;
	private CommentInfoFrontService commentInfoFrontService;
	private MemberReportCenterService memberReportCenterService;
	private MemberMood memberMood;
	private MemberInfo memberInfo;
	private MemberInfo cmemberInfo;
	private int id;
	private String ids;
	private String backUrl;
	private String no = "";
	// 搜索条件
	private String searchcontent = "";

	/**
	 * 会员心情删除功能
	 * 
	 * @return
	 */
	public String delete() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberMood = memberMoodCenterService.getMemberMood(new Integer(
						idarrs[i].trim()));
				memberMood.setIsdel(new Integer(1));
				memberMoodCenterService.updateMemberMood(memberMood);
				// 删除动态信息
				memberStatusCenterService.deleteMemberStatus(
						memberInfo.getId(), 1, new Integer(idarrs[i].trim()));
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员心情添加功能
	 * 
	 * @return
	 */
	public String save() {
		String msg = "提交成功！";
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (memberReportCenterService.getIsMemberReport(memberInfo.getId()
				.intValue())) {
			msg = "操作失败，您的账号，正在拘留当中！"; // 操作失败，您的账号，正在拘留当中。
		} else {
			if (memberInfo != null) {
				memberMood = new MemberMood();
				memberMood.setMid(memberInfo.getId());
				memberMood.setIsdel(new Integer(0));
				memberMood.setCtime(new Date());
				memberMood.setViewnumber(new Integer(0));
				memberMood.setContent(no);
				memberMoodCenterService.saveMemberMood(memberMood);
				// 添加会员动态信息 2013-08-09
				memberStatusCenterService.saveMemberStatus(memberInfo.getId(),
						1, memberMood.getId(), no, 0);
			}
		}
		session.put("saveinfomsg", msg);
		return SUCCESS;
	}

	/**
	 * 会员心情添加功能
	 * 
	 * @return
	 */
	public String msave() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String msg = "修改成功！";
		if (memberReportCenterService.getIsMemberReport(memberInfo.getId()
				.intValue())) {
			msg = "操作失败，您的账号，正在拘留当中！";
		} else {
			if (memberMood.getId() == null) {
				msg = "添加成功！";
				memberMood.setMid(memberInfo.getId());
				memberMood.setIsdel(new Integer(0));
				memberMood.setCtime(new Date());
				memberMood.setViewnumber(new Integer(0));
				memberMoodCenterService.saveMemberMood(memberMood);
				// 添加会员动态信息 2013-08-09
				memberStatusCenterService.saveMemberStatus(memberInfo.getId(),
						9, memberMood.getId(), no, memberMood.getQx());
			} else {
				memberMoodCenterService.updateMemberMood(memberMood);
				// 同步更新会员动态信息 2013-10-09
				memberStatusCenterService.updateMemberStatus(
						memberInfo.getId(), 1, memberMood.getId());
			}
			memberMoodCenterService.updateMemberMood(memberMood);
		}
		session.put("saveinfomsg", msg);
		return SUCCESS;
	}

	/**
	 * 会员心情修改功能
	 * 
	 * @return
	 */
	public String info() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			memberMood = memberMoodCenterService.getMemberMood(id);
			memberMood.setMemberInfo(memberInfoCenterService
					.getMemberInfo(memberMood.getMid()));
		}
		// 初始化信息
		if (memberMood == null) {
			memberMood = new MemberMood();
			memberMood.setIsdel(new Integer(0));
		}
		return SUCCESS;
	}

	/**
	 * 会员心情查看功能
	 * 
	 * @return
	 */
	public String showinfo() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			memberMood = memberMoodCenterService.getMemberMood(id);
			memberMood.setMemberInfo(memberInfoCenterService
					.getMemberInfo(memberMood.getMid()));
			memberMood.setPraisenumber(memberPraiseCenterService
					.getMemberPraiseInListNumber(1, memberMood.getId()
							.intValue()));
			memberMood.setRcnumber(memberMoodCenterService
					.getMemberMoodInListNumber(memberMood.getId().intValue()));
			memberMood.setCommentnumber(commentInfoFrontService
					.getCommentNumber(memberMood.getId().intValue(), 1));
			memberMood.setMemberPraise(memberPraiseCenterService
					.findMemberPraiseAll(memberInfo.getId().intValue(), 1,
							memberMood.getId().intValue()));
			// 更新点击量
			if (memberMood.getMid().intValue() != memberInfo.getId().intValue()) {
				memberMood.setViewnumber(memberMood.getViewnumber() == null ? 0
						: memberMood.getViewnumber() + 1);
			}
			memberMoodCenterService.updateMemberMood(memberMood);
		}
		return SUCCESS;
	}

	/**
	 * 会员列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		cmemberInfo = memberInfo;
		String hql = " from MemberMood where isdel=0 and mid="
				+ memberInfo.getId();
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

		if (searchcontent != null && searchcontent.length() > 0) {
			hql += " and content like '%" + searchcontent + "%'";
			HqlBean hqlBean = new HqlBean(searchcontent, "=", "and", "String");
			hqlBean.setIshql(1);
			map.put("searchcontent", hqlBean);
			try {
				parameter += "&searchcontent="
						+ StringUtil.URLEncode(searchcontent);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		// 判断排序条件
		pageBean = memberMoodCenterService.findMemberMoodHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/membercenter/moodlist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 好友列表查看功能
	 * 
	 * @return
	 */
	public String flist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		cmemberInfo = memberInfo;
		String fids = memberFriendCenterService.findMemberFriends(memberInfo
				.getId());
		if (fids.length() > 0) {
			String hql = " from MemberMood where isdel=0 and mid in (" + fids
					+ ")";
			Map map = new HashMap();
			String parameter = "";
			// 默认排序 最新时间
			String sortstr = "";
			sortstr = " id desc ";

			if (searchcontent != null && searchcontent.length() > 0) {
				hql += " and content like '%" + searchcontent + "%'";
				HqlBean hqlBean = new HqlBean(searchcontent, "=", "and",
						"String");
				hqlBean.setIshql(1);
				map.put("searchcontent", hqlBean);
				try {
					parameter += "&searchcontent="
							+ StringUtil.URLEncode(searchcontent);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}

			// 判断排序条件
			pageBean = memberMoodCenterService.findMemberMoodHQLList(hql,
					sortstr, map, currentPage, pageSize);
			pageBean.setAction("/membercenter/fmoodlist");
			backUrl = pageBean.getAction() + "?pageSize="
					+ pageBean.getPageSize() + "&currentPage="
					+ pageBean.getCurrentPage() + parameter;
			pageBean.setGopageTemplate(request
					.getRealPath("/member/center/gopage/gopage.html"));
			pageBean.setPageList(getnewlist(pageBean.getPageList()));
		}
		return SUCCESS;
	}

	/**
	 * 重新封装list
	 * 
	 * @return
	 */
	public List getnewlist(List pagelist) {
		List newlist = new ArrayList();
		if (pagelist != null && pagelist.size() > 0) {
			for (int i = 0; i < pagelist.size(); i++) {
				MemberMood mmood = (MemberMood) pagelist.get(i);
				mmood.setMemberInfo(memberInfoCenterService.getMemberInfo(mmood
						.getMid()));
				mmood.setPraisenumber(memberPraiseCenterService
						.getMemberPraiseInListNumber(1, mmood.getId()
								.intValue()));
				mmood.setRcnumber(memberMoodCenterService
						.getMemberMoodInListNumber(mmood.getId().intValue()));
				mmood.setCommentnumber(commentInfoFrontService
						.getCommentNumber(mmood.getId().intValue(), 1));
				mmood.setMemberPraise(memberPraiseCenterService
						.findMemberPraiseAll(memberInfo.getId().intValue(), 1,
								mmood.getId().intValue()));
				newlist.add(mmood);
			}
		}
		return newlist;
	}

	public MemberMoodCenterService getMemberMoodCenterService() {
		return memberMoodCenterService;
	}

	public void setMemberMoodCenterService(
			MemberMoodCenterService memberMoodCenterService) {
		this.memberMoodCenterService = memberMoodCenterService;
	}

	public MemberMood getMemberMood() {
		return memberMood;
	}

	public void setMemberMood(MemberMood memberMood) {
		this.memberMood = memberMood;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public MemberInfoCenterService getMemberInfoCenterService() {
		return memberInfoCenterService;
	}

	public void setMemberInfoCenterService(
			MemberInfoCenterService memberInfoCenterService) {
		this.memberInfoCenterService = memberInfoCenterService;
	}

	public String getSearchcontent() {
		return searchcontent;
	}

	public void setSearchcontent(String searchcontent) {
		this.searchcontent = searchcontent;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public MemberStatusCenterService getMemberStatusCenterService() {
		return memberStatusCenterService;
	}

	public void setMemberStatusCenterService(
			MemberStatusCenterService memberStatusCenterService) {
		this.memberStatusCenterService = memberStatusCenterService;
	}

	public MemberFriendCenterService getMemberFriendCenterService() {
		return memberFriendCenterService;
	}

	public void setMemberFriendCenterService(
			MemberFriendCenterService memberFriendCenterService) {
		this.memberFriendCenterService = memberFriendCenterService;
	}

	public MemberPraiseCenterService getMemberPraiseCenterService() {
		return memberPraiseCenterService;
	}

	public void setMemberPraiseCenterService(
			MemberPraiseCenterService memberPraiseCenterService) {
		this.memberPraiseCenterService = memberPraiseCenterService;
	}

	public CommentInfoFrontService getCommentInfoFrontService() {
		return commentInfoFrontService;
	}

	public void setCommentInfoFrontService(
			CommentInfoFrontService commentInfoFrontService) {
		this.commentInfoFrontService = commentInfoFrontService;
	}

	public MemberInfo getCmemberInfo() {
		return cmemberInfo;
	}

	public void setCmemberInfo(MemberInfo cmemberInfo) {
		this.cmemberInfo = cmemberInfo;
	}

	public MemberReportCenterService getMemberReportCenterService() {
		return memberReportCenterService;
	}

	public void setMemberReportCenterService(
			MemberReportCenterService memberReportCenterService) {
		this.memberReportCenterService = memberReportCenterService;
	}

}

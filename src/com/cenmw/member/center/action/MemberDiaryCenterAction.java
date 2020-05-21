package com.cenmw.member.center.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.comment.front.service.CommentInfoFrontService;
import com.cenmw.integral.front.service.IntegralConfigFrontService;
import com.cenmw.integral.front.service.IntegralInfoFrontService;
import com.cenmw.integral.po.IntegralConfig;
import com.cenmw.integral.po.IntegralInfo;
import com.cenmw.member.center.service.MemberFriendCenterService;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.center.service.MemberDiaryCenterService;
import com.cenmw.member.center.service.MemberLLJLCenterService;
import com.cenmw.member.center.service.MemberPraiseCenterService;
import com.cenmw.member.center.service.MemberReportCenterService;
import com.cenmw.member.center.service.MemberStatusCenterService;
import com.cenmw.member.po.MemberDiary;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.DateUtil;

public class MemberDiaryCenterAction extends BaseAction {
	/**
	 * 会员日志 模块
	 */
	private MemberDiaryCenterService memberDiaryCenterService;
	private MemberInfoCenterService memberInfoCenterService;
	private MemberStatusCenterService memberStatusCenterService;
	private IntegralInfoFrontService integralInfoFrontService;
	private IntegralConfigFrontService integralConfigFrontService;
	private MemberFriendCenterService memberFriendCenterService;
	private MemberPraiseCenterService memberPraiseCenterService;
	private CommentInfoFrontService commentInfoFrontService;
	private MemberReportCenterService memberReportCenterService;
	private MemberLLJLCenterService memberLLJLCenterService;
	private MemberDiary memberDiary;
	private MemberInfo memberInfo;
	private MemberInfo cmemberInfo;
	private int id;
	private String ids;
	private List classlist;
	private String backUrl;

	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员日志删除功能
	 * 
	 * @return
	 */
	public String delete() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberDiary = memberDiaryCenterService.getMemberDiary(new Integer(
						idarrs[i].trim()));
				memberDiary.setIsdel(new Integer(1));
				memberDiaryCenterService.updateMemberDiary(memberDiary);
				// 删除动态信息
				memberStatusCenterService.deleteMemberStatusByCid(2,
						memberDiary.getId(), memberInfo.getId());
				// 删除获得的积分
				IntegralInfo integralInfo = integralInfoFrontService
						.deleteIntegralInfoByCid(3, memberDiary.getId(),
								memberInfo.getId());
				if (integralInfo != null) {
					session.put(ConstantUtils.MEMBERINFO, memberInfoCenterService
							.getMemberInfo(memberInfo.getId()));
				}
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员日志添加功能
	 * 
	 * @return
	 */
	public String save() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String msg = "修改成功！";
		if (memberReportCenterService.getIsMemberReport(memberInfo.getId()
				.intValue())) {
			msg = "操作失败，您的账号，正在拘留当中！";
		} else {
			if (memberDiary.getId() == null) {
				msg = "添加成功！";
				memberDiary.setMid(memberInfo.getId());
				memberDiary.setIsdel(new Integer(0));
				memberDiary.setCtime(new Date());
				memberDiary.setViewnumber(new Integer(0));
				memberDiaryCenterService.saveMemberDiary(memberDiary);
				// 添加会员动态信息 2013-08-13
				memberStatusCenterService.saveMemberStatus(memberInfo.getId(),
						2, memberDiary.getId(), DateUtil.getFormatDate(memberDiary.getPtime(), "yyyy-MM-dd")+"日记",0);
				// 添加获得积分
				IntegralConfig ic = integralConfigFrontService
						.findIntegralConfigInList(3);
				if (ic != null) {
					IntegralInfo integralInfo = new IntegralInfo(
							memberInfo.getId(), memberInfo.getAccount(), 3,
							memberDiary.getId(), ic.getScore());
					integralInfoFrontService.saveIntegralInfo(integralInfo);
					session.put(ConstantUtils.MEMBERINFO, memberInfoCenterService
							.getMemberInfo(memberInfo.getId()));
				}
			} else {
				// 同步更新会员动态信息 2013-10-09
				memberStatusCenterService.updateMemberStatus(
						memberInfo.getId(), 2, memberDiary.getId());
			}
			memberDiaryCenterService.updateMemberDiary(memberDiary);
		}
		session.put("saveinfomsg", msg);
		return SUCCESS;
	}

	/**
	 * 会员日志修改功能
	 * 
	 * @return
	 */
	public String info() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			memberDiary = memberDiaryCenterService.getMemberDiary(id);
			memberDiary.setMemberInfo(memberInfoCenterService
					.getMemberInfo(memberDiary.getMid()));
		}
		// 初始化信息
		if (memberDiary == null) {
			memberDiary = new MemberDiary();
			memberDiary.setPtime(new Date());
		}
		return SUCCESS;
	}

	/**
	 * 会员日志查看功能
	 * 
	 * @return
	 */
	public String showinfo() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			memberDiary = memberDiaryCenterService.getMemberDiary(id);
			int qx = memberDiary.getQx() == null ? 0 : memberDiary.getQx();
			if (qx == 1) { // 判断是不是好友
				if (memberInfo.getId().intValue() != memberDiary.getMid()
						.intValue()
						&& !memberFriendCenterService.findMemberFriends(
								memberInfo.getId(), memberDiary.getMid())) {
					return "qx1";
				}
			} else if (qx == 2) { // 判断是不是自己
				if (memberInfo.getId().intValue() != memberDiary.getMid()
						.intValue()) {
					return "qx2";
				}
			}
			memberDiary.setMemberInfo(memberInfoCenterService
					.getMemberInfo(memberDiary.getMid()));
			memberDiary.setPraisenumber(memberPraiseCenterService
					.getMemberPraiseInListNumber(2, memberDiary.getId()
							.intValue()));
			memberDiary.setRcnumber(memberDiaryCenterService
					.getMemberDiaryInListNumber(memberDiary.getId().intValue()));
			memberDiary.setCommentnumber(commentInfoFrontService
					.getCommentNumber(memberDiary.getId().intValue(), 2));
			memberDiary.setMemberPraise(memberPraiseCenterService
					.findMemberPraiseAll(memberInfo.getId().intValue(), 2,
							memberDiary.getId().intValue()));
			// 更新访问量
			if (memberDiary.getMid().intValue() != memberInfo.getId().intValue()) {
				memberDiary.setViewnumber(memberDiary.getViewnumber() == null ? 0
						: memberDiary.getViewnumber() + 1);
			}
			memberDiaryCenterService.updateMemberDiary(memberDiary);
			// 添加浏览记录
			memberLLJLCenterService.saveMemberLLJL(memberInfo.getId(), "[日记]"
					+ memberDiary.getPtime(), "/membercenter/showdiaryinfo?id="
					+ memberDiary.getId());
		}
		// 初始化信息
		if (memberDiary == null) {
			memberDiary = new MemberDiary();
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
		String hql = " from MemberDiary where isdel=0 and mid="
				+ memberInfo.getId();
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = " ptime desc, ctime desc ";

		// 判断排序条件
		pageBean = memberDiaryCenterService.findMemberDiaryHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/membercenter/diarylist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 好友日志列表查看功能
	 * 
	 * @return
	 */
	public String flist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		cmemberInfo = memberInfo;
		String fids = memberFriendCenterService.findMemberFriends(memberInfo
				.getId());
		if (fids.length() > 0) {
			String hql = " from MemberDiary where isdel=0 and mid in ("
					+ fids + ")";
			Map map = new HashMap();
			String parameter = "";
			// 默认排序 最新时间
			String sortstr = " ptime desc, ctime desc ";

			// 判断排序条件
			pageBean = memberDiaryCenterService.findMemberDiaryHQLList(hql,
					sortstr, map, currentPage, pageSize);
			pageBean.setAction("/membercenter/fdiarylist");
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
				MemberDiary mdiary = (MemberDiary) pagelist.get(i);
				mdiary.setMemberInfo(memberInfoCenterService.getMemberInfo(mdiary
						.getMid()));
				mdiary.setPraisenumber(memberPraiseCenterService
						.getMemberPraiseInListNumber(2, mdiary.getId()
								.intValue()));
				mdiary.setRcnumber(memberDiaryCenterService
						.getMemberDiaryInListNumber(mdiary.getId().intValue()));
				mdiary.setCommentnumber(commentInfoFrontService
						.getCommentNumber(mdiary.getId().intValue(), 2));
				mdiary.setMemberPraise(memberPraiseCenterService
						.findMemberPraiseAll(memberInfo.getId().intValue(), 2,
								mdiary.getId().intValue()));
				newlist.add(mdiary);
			}
		}
		return newlist;
	}

	public MemberDiaryCenterService getMemberDiaryCenterService() {
		return memberDiaryCenterService;
	}

	public void setMemberDiaryCenterService(
			MemberDiaryCenterService memberDiaryCenterService) {
		this.memberDiaryCenterService = memberDiaryCenterService;
	}

	public MemberDiary getMemberDiary() {
		return memberDiary;
	}

	public void setMemberDiary(MemberDiary memberDiary) {
		this.memberDiary = memberDiary;
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

	public String getSearchtitle() {
		return searchtitle;
	}

	public void setSearchtitle(String searchtitle) {
		this.searchtitle = searchtitle;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public List getClasslist() {
		return classlist;
	}

	public void setClasslist(List classlist) {
		this.classlist = classlist;
	}

	public MemberStatusCenterService getMemberStatusCenterService() {
		return memberStatusCenterService;
	}

	public void setMemberStatusCenterService(
			MemberStatusCenterService memberStatusCenterService) {
		this.memberStatusCenterService = memberStatusCenterService;
	}

	public IntegralInfoFrontService getIntegralInfoFrontService() {
		return integralInfoFrontService;
	}

	public void setIntegralInfoFrontService(
			IntegralInfoFrontService integralInfoFrontService) {
		this.integralInfoFrontService = integralInfoFrontService;
	}

	public IntegralConfigFrontService getIntegralConfigFrontService() {
		return integralConfigFrontService;
	}

	public void setIntegralConfigFrontService(
			IntegralConfigFrontService integralConfigFrontService) {
		this.integralConfigFrontService = integralConfigFrontService;
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

	public MemberLLJLCenterService getMemberLLJLCenterService() {
		return memberLLJLCenterService;
	}

	public void setMemberLLJLCenterService(
			MemberLLJLCenterService memberLLJLCenterService) {
		this.memberLLJLCenterService = memberLLJLCenterService;
	}

}

package com.cenmw.member.center.action;

import java.io.UnsupportedEncodingException;
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
import com.cenmw.member.center.service.MemberBlogClassCenterService;
import com.cenmw.member.center.service.MemberFriendCenterService;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.center.service.MemberBlogCenterService;
import com.cenmw.member.center.service.MemberLLJLCenterService;
import com.cenmw.member.center.service.MemberMoodCenterService;
import com.cenmw.member.center.service.MemberPraiseCenterService;
import com.cenmw.member.center.service.MemberReportCenterService;
import com.cenmw.member.center.service.MemberStatusCenterService;
import com.cenmw.member.po.MemberBlog;
import com.cenmw.member.po.MemberBlogClass;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.member.po.MemberMood;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberBlogCenterAction extends BaseAction {
	/**
	 * 会员日志 模块
	 */
	private MemberBlogCenterService memberBlogCenterService;
	private MemberInfoCenterService memberInfoCenterService;
	private MemberBlogClassCenterService memberBlogClassCenterService;
	private MemberStatusCenterService memberStatusCenterService;
	private IntegralInfoFrontService integralInfoFrontService;
	private IntegralConfigFrontService integralConfigFrontService;
	private MemberFriendCenterService memberFriendCenterService;
	private MemberPraiseCenterService memberPraiseCenterService;
	private CommentInfoFrontService commentInfoFrontService;
	private MemberReportCenterService memberReportCenterService;
	private MemberMoodCenterService memberMoodCenterService;
	private MemberLLJLCenterService memberLLJLCenterService;

	private MemberBlog memberBlog;
	private MemberMood memberMood;
	private MemberInfo memberInfo;
	private MemberInfo cmemberInfo;
	private int id;
	private String ids;
	private List classlist;
	private String backUrl;

	// 搜索条件
	private String searchtitle = "";

	// 学习结果，添加到日志中
	private String title;
	private String content;
	private Integer lsid; // 学习结果id

	/**
	 * 学习结果，添加到会员说说功能
	 * 
	 * @return
	 */
	public String learnlogblogsave() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String msg = "发表说说成功";
		memberMood = new MemberMood();
		memberMood.setMid(memberInfo.getId());
		memberMood.setIsdel(new Integer(0));
		memberMood.setCtime(new Date());
		memberMood.setViewnumber(new Integer(0));
		memberMood.setContent(title+":"+content);
		memberMoodCenterService.saveMemberMood(memberMood);
		// 添加会员动态信息 2013-08-13
		memberStatusCenterService.saveMemberStatus(memberInfo.getId(),
				1, memberMood.getId(),title,0);
		session.put("saveinfomsg", msg);
		return SUCCESS;
	}

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
				memberBlog = memberBlogCenterService.getMemberBlog(new Integer(
						idarrs[i].trim()));
				memberBlog.setIsdel(new Integer(1));
				memberBlogCenterService.updateMemberBlog(memberBlog);
				// 删除动态信息
				memberStatusCenterService.deleteMemberStatusByCid(2,
						memberBlog.getId(), memberInfo.getId());
				// 删除获得的积分
				IntegralInfo integralInfo = integralInfoFrontService
						.deleteIntegralInfoByCid(3, memberBlog.getId(),
								memberInfo.getId());
				if (integralInfo != null) {
					session.put(ConstantUtils.MEMBERINFO, memberInfoCenterService
							.getMemberInfo(memberInfo.getId()));
				}
			}
			String msg = "删除成功";
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
			if (memberBlog.getId() == null) {
				msg = "添加成功！";
				memberBlog.setMid(memberInfo.getId());
				memberBlog.setIsdel(new Integer(0));
				memberBlog.setCtime(new Date());
				memberBlog.setSort(new Integer(0));
				memberBlog.setType(1);
				memberBlog.setViewnumber(new Integer(0));
				memberBlogCenterService.saveMemberBlog(memberBlog);
				// 添加会员动态信息 2013-08-13
				memberStatusCenterService.saveMemberStatus(memberInfo.getId(),
						2, memberBlog.getId(), memberBlog.getTitle(),memberBlog.getQx());
				// 添加获得积分
				IntegralConfig ic = integralConfigFrontService
						.findIntegralConfigInList(3);
				if (ic != null) {
					IntegralInfo integralInfo = new IntegralInfo(
							memberInfo.getId(), memberInfo.getAccount(), 3,
							memberBlog.getId(), ic.getScore());
					integralInfoFrontService.saveIntegralInfo(integralInfo);
					session.put(ConstantUtils.MEMBERINFO, memberInfoCenterService
							.getMemberInfo(memberInfo.getId()));
				}
			} else {
				// 同步更新会员动态信息 2013-10-09
				memberStatusCenterService.updateMemberStatus(
						memberInfo.getId(), 2, memberBlog.getId());
			}
			int cid = memberBlog.getCid();
			MemberBlogClass memberBlogClass = memberBlogClassCenterService
					.getMemberBlogClass(cid);
			if (memberBlogClass != null) {
				memberBlog.setClassname(memberBlogClass.getTitle());
			}
			memberBlogCenterService.updateMemberBlog(memberBlog);
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
		classlist = memberBlogClassCenterService.findMemberBlogClassInList(
				memberInfo.getId(), 1);
		if (id > 0) {
			memberBlog = memberBlogCenterService.getMemberBlog(id);
			memberBlog.setMemberInfo(memberInfoCenterService
					.getMemberInfo(memberBlog.getMid()));
		}
		// 初始化信息
		if (memberBlog == null) {
			memberBlog = new MemberBlog();
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
		classlist = memberBlogClassCenterService.findMemberBlogClassInList(
				memberInfo.getId(), 1);
		if (id > 0) {
			memberBlog = memberBlogCenterService.getMemberBlog(id);
			int qx = memberBlog.getQx() == null ? 0 : memberBlog.getQx();
			if (qx == 1) { // 判断是不是好友
				if (memberInfo.getId().intValue() != memberBlog.getMid()
						.intValue()
						&& !memberFriendCenterService.findMemberFriends(
								memberInfo.getId(), memberBlog.getMid())) {
					return "qx1";
				}
			} else if (qx == 2) { // 判断是不是自己
				if (memberInfo.getId().intValue() != memberBlog.getMid()
						.intValue()) {
					return "qx2";
				}
			}
			memberBlog.setMemberInfo(memberInfoCenterService
					.getMemberInfo(memberBlog.getMid()));
			memberBlog.setPraisenumber(memberPraiseCenterService
					.getMemberPraiseInListNumber(2, memberBlog.getId()
							.intValue()));
			memberBlog.setRcnumber(memberBlogCenterService
					.getMemberBlogInListNumber(memberBlog.getId().intValue()));
			memberBlog.setCommentnumber(commentInfoFrontService
					.getCommentNumber(memberBlog.getId().intValue(), 2));
			memberBlog.setMemberPraise(memberPraiseCenterService
					.findMemberPraiseAll(memberInfo.getId().intValue(), 2,
							memberBlog.getId().intValue()));
			// 更新访问量
			if (memberBlog.getMid().intValue() != memberInfo.getId().intValue()) {
				memberBlog.setViewnumber(memberBlog.getViewnumber() == null ? 0
						: memberBlog.getViewnumber() + 1);
			}
			memberBlogCenterService.updateMemberBlog(memberBlog);
			if (memberInfo.getId().intValue() == memberBlog.getMid().intValue()
					&& memberBlog.getType() == 1) {
				commentInfoFrontService.updateCommentInfoInList(2, id);
			}
			// 添加浏览记录
			memberLLJLCenterService.saveMemberLLJL(memberInfo.getId(), "[日志]"
					+ memberBlog.getTitle(), "/membercenter/showbloginfo?id="
					+ memberBlog.getId());
		}
		// 初始化信息
		if (memberBlog == null) {
			memberBlog = new MemberBlog();
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
		String hql = " from MemberBlog where type=1 and isdel=0 and mid="
				+ memberInfo.getId();
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = " sort desc, ctime desc ";

		if (searchtitle != null && searchtitle.length() > 0) {
			hql += " and title like '%" + searchtitle + "%'";
			HqlBean hqlBean = new HqlBean(searchtitle, "=", "and", "String");
			hqlBean.setIshql(1);
			map.put("searchtitle", hqlBean);
			try {
				parameter += "&searchtitle="
						+ StringUtil.URLEncode(searchtitle);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		// 判断排序条件
		pageBean = memberBlogCenterService.findMemberBlogHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/membercenter/bloglist");
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
			String hql = " from MemberBlog where type=1 and isdel=0 and mid in ("
					+ fids + ")";
			Map map = new HashMap();
			String parameter = "";
			// 默认排序 最新时间
			String sortstr = " sort desc, ctime desc ";

			if (searchtitle != null && searchtitle.length() > 0) {
				hql += " and title like '%" + searchtitle + "%'";
				HqlBean hqlBean = new HqlBean(searchtitle, "=", "and", "String");
				hqlBean.setIshql(1);
				map.put("searchtitle", hqlBean);
				try {
					parameter += "&searchtitle="
							+ StringUtil.URLEncode(searchtitle);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}

			// 判断排序条件
			pageBean = memberBlogCenterService.findMemberBlogHQLList(hql,
					sortstr, map, currentPage, pageSize);
			pageBean.setAction("/membercenter/fbloglist");
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
				MemberBlog mblog = (MemberBlog) pagelist.get(i);
				mblog.setMemberInfo(memberInfoCenterService.getMemberInfo(mblog
						.getMid()));
				mblog.setPraisenumber(memberPraiseCenterService
						.getMemberPraiseInListNumber(2, mblog.getId()
								.intValue()));
				mblog.setRcnumber(memberBlogCenterService
						.getMemberBlogInListNumber(mblog.getId().intValue()));
				mblog.setCommentnumber(commentInfoFrontService
						.getCommentNumber(mblog.getId().intValue(), 2));
				mblog.setMemberPraise(memberPraiseCenterService
						.findMemberPraiseAll(memberInfo.getId().intValue(), 2,
								mblog.getId().intValue()));
				newlist.add(mblog);
			}
		}
		return newlist;
	}

	public MemberBlogCenterService getMemberBlogCenterService() {
		return memberBlogCenterService;
	}

	public void setMemberBlogCenterService(
			MemberBlogCenterService memberBlogCenterService) {
		this.memberBlogCenterService = memberBlogCenterService;
	}

	public MemberBlog getMemberBlog() {
		return memberBlog;
	}

	public void setMemberBlog(MemberBlog memberBlog) {
		this.memberBlog = memberBlog;
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

	public MemberBlogClassCenterService getMemberBlogClassCenterService() {
		return memberBlogClassCenterService;
	}

	public void setMemberBlogClassCenterService(
			MemberBlogClassCenterService memberBlogClassCenterService) {
		this.memberBlogClassCenterService = memberBlogClassCenterService;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getLsid() {
		return lsid;
	}

	public void setLsid(Integer lsid) {
		this.lsid = lsid;
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
	
	public MemberLLJLCenterService getMemberLLJLCenterService() {
		return memberLLJLCenterService;
	}

	public void setMemberLLJLCenterService(
			MemberLLJLCenterService memberLLJLCenterService) {
		this.memberLLJLCenterService = memberLLJLCenterService;
	}
}

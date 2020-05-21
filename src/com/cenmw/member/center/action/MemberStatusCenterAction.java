package com.cenmw.member.center.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.comment.front.service.CommentInfoFrontService;
import com.cenmw.member.center.service.MemberBlogCenterService;
import com.cenmw.member.center.service.MemberBlogClassCenterService;
import com.cenmw.member.center.service.MemberDiaryCenterService;
import com.cenmw.member.center.service.MemberFriendCenterService;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.center.service.MemberMoodCenterService;
import com.cenmw.member.center.service.MemberPhotoCenterService;
import com.cenmw.member.center.service.MemberPhotoGroupCenterService;
import com.cenmw.member.center.service.MemberPraiseCenterService;
import com.cenmw.member.center.service.MemberStatusCenterService;
import com.cenmw.member.po.MemberBlog;
import com.cenmw.member.po.MemberDiary;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.member.po.MemberMood;
import com.cenmw.member.po.MemberPhoto;
import com.cenmw.member.po.MemberStatus;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.DateUtil;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;
import com.cenmw.vedio.front.service.VedioClassFrontService;
import com.cenmw.vedio.front.service.VedioInfoFrontService;
import com.cenmw.vedio.po.VedioInfo;

public class MemberStatusCenterAction extends BaseAction {
	/**
	 * 会员动态 模块
	 */
	private MemberStatusCenterService memberStatusCenterService;
	private MemberInfoCenterService memberInfoCenterService;
	private MemberFriendCenterService memberFriendCenterService;

	private MemberMoodCenterService memberMoodCenterService;
	private MemberBlogCenterService memberBlogCenterService;
	private MemberBlogClassCenterService memberBlogClassCenterService;
	private MemberPhotoCenterService memberPhotoCenterService;
	private MemberPhotoGroupCenterService memberPhotoGroupCenterService;
	private VedioInfoFrontService vedioInfoFrontService;
	private VedioClassFrontService vedioClassFrontService;

	private MemberPraiseCenterService memberPraiseCenterService;
	private CommentInfoFrontService commentInfoFrontService;
	private MemberDiaryCenterService memberDiaryCenterService;
	private MemberStatus memberStatus;
	private MemberInfo memberInfo;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员动态删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberStatus = memberStatusCenterService
						.getMemberStatus(new Integer(idarrs[i].trim()));
				memberStatus.setIsdel(new Integer(1));
				memberStatusCenterService.updateMemberStatus(memberStatus);
			}
			String msg = "删除成功";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员动态查看功能
	 * 
	 * @return
	 */
	public String info() {
		if (id > 0) {
			memberStatus = memberStatusCenterService.getMemberStatus(id);
			memberStatus.setMemberInfo(memberInfoCenterService
					.getMemberInfo(memberStatus.getMid()));
		}
		// 初始化信息
		if (memberStatus == null) {
			memberStatus = new MemberStatus();
			memberStatus.setIsdel(new Integer(0));
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
		String hql = " from MemberStatus where isdel=0 and mid="
				+ memberInfo.getId();
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

		if (searchtitle != null && searchtitle.length() > 0) {
			hql += " and classname like '%" + searchtitle + "%'";
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
		pageBean = memberStatusCenterService.findMemberStatusHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/statuslist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 好友动态列表查看功能
	 * 
	 * @return
	 */
	public String flist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String fids = memberFriendCenterService.findMemberFriends(memberInfo
				.getId());
		if (fids.length() > 0) {
			String hql = " from MemberStatus where isdel=0 and mid in (" + fids
					+ ")";
			Map map = new HashMap();
			String parameter = "";
			// 默认排序 最新时间
			String sortstr = "";
			sortstr = " mid desc ,id desc ";

			if (searchtitle != null && searchtitle.length() > 0) {
				hql += " and classname like '%" + searchtitle + "%'";
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
			pageBean = memberStatusCenterService.findMemberStatusHQLList(hql,
					sortstr, map, currentPage, pageSize);
			pageBean.setAction("/membercenter/fstatuslist");
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
				MemberStatus mstatus = (MemberStatus) pagelist.get(i);
				mstatus.setMemberInfo(memberInfoCenterService
						.getMemberInfo(mstatus.getMid()));
				int type = mstatus.getType() == null ? 0 : mstatus.getType();
				if (type == 1) {
					MemberMood mmood = memberMoodCenterService
							.getMemberMood(mstatus.getCid().intValue());
					mstatus.setMemberMood(mmood);
					if (mmood != null) {
						mstatus.setTitle(mmood.getContent());
						mstatus.setRcid(mmood.getRcid());
						mstatus.setUrl("/membercenter/showmoodinfo?id="
								+ mmood.getId().intValue());
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(1, mmood.getId()
										.intValue()));
						mstatus.setRcnumber(memberMoodCenterService
								.getMemberMoodInListNumber(mmood.getId()
										.intValue()));
						mstatus.setCommentnumber(commentInfoFrontService
								.getCommentNumber(mmood.getId().intValue(), 1));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 1, mmood.getId()
										.intValue()));
					}
				} else if (type == 2) { // 日记
					MemberDiary mdiary = memberDiaryCenterService
							.getMemberDiary(mstatus.getCid().intValue());
					mstatus.setMemberDiary(mdiary);
					if (mdiary != null) {
						mstatus.setTitle(DateUtil.getFormatDate(mdiary
								.getPtime(), "yyyy-MM-dd"));
						mstatus.setContent("<a target=\"_blank\" href=\""
								+ mstatus.getUrl()
								+ "\">"
								+ DateUtil.getFormatDate(mdiary.getPtime(),
										"yyyy-MM-dd") + "</a>"
								+ "<div class=\"small-pic mar-t10\">日记</div>");
						if (mdiary.getRcid() != null && mdiary.getRcid() > 0) {
							mstatus
									.setContent("<a target=\"_blank\" href=\""
											+ mstatus.getUrl()
											+ "\">【转载】"
											+ DateUtil.getFormatDate(mdiary
													.getPtime(), "yyyy-MM-dd")
											+ "</a>"
											+ "<div class=\"small-pic mar-t10\">日记</div>");
						}
						mstatus.setMemberInfo(memberInfoCenterService
								.getMemberInfo(mdiary.getMid()));
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(2, mdiary.getId()
										.intValue()));
						mstatus.setRcnumber(memberDiaryCenterService
								.getMemberDiaryInListNumber(mdiary.getId()
										.intValue()));
						mstatus
								.setCommentnumber(commentInfoFrontService
										.getCommentNumber(mdiary.getId()
												.intValue(), 2));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 2, mdiary.getId()
										.intValue()));
						mstatus.setId(mdiary.getId());
					}
				} else if (type == 3) {
					MemberBlog mblog = memberBlogCenterService
							.getMemberBlog(mstatus.getCid().intValue());
					mstatus.setMemberBlog(mblog);
					if (mblog != null) {
						mstatus.setMemberBlogClass(memberBlogClassCenterService
								.getMemberBlogClass(mblog.getCid()));
						mstatus.setTitle(mblog.getTitle());
						mstatus.setContent(mblog.getDescription());
						mstatus.setRcid(mblog.getRcid());
						mstatus.setUrl("/membercenter/showcontentinfo?id="
								+ mblog.getId().intValue());
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(3, mblog.getId()
										.intValue()));
						mstatus.setRcnumber(memberBlogCenterService
								.getMemberBlogInListNumber(mblog.getId()
										.intValue()));
						mstatus.setCommentnumber(commentInfoFrontService
								.getCommentNumber(mblog.getId().intValue(), 3));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 3, mblog.getId()
										.intValue()));
					}
				} else if (type == 4) {
					MemberPhoto mphoto = memberPhotoCenterService
							.getMemberPhoto(mstatus.getCid().intValue());
					mstatus.setMemberPhoto(mphoto);
					if (mphoto != null) {
						mstatus
								.setMemberPhotoGroup(memberPhotoGroupCenterService
										.getMemberPhotoGroup(mphoto.getCid()));
						mstatus.setTitle(mphoto.getTitle());
						mstatus
								.setContent(mphoto.getDescription()
										+ "<br/><img onload=\"fixImage(this,300,300)\" src='"
										+ mphoto.getPicpath() + "' />");
						mstatus.setUrl("/membercenter/showphotoinfo?id="
								+ mphoto.getId().intValue());
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(4, mphoto.getId()
										.intValue()));
						mstatus.setRcnumber(0);
						mstatus
								.setCommentnumber(commentInfoFrontService
										.getCommentNumber(mphoto.getId()
												.intValue(), 4));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 4, mphoto.getId()
										.intValue()));
					}
				} else if (type == 5) {
					VedioInfo vinfo = vedioInfoFrontService
							.getVedioInfo(mstatus.getCid().intValue());
					mstatus.setVedioInfo(vinfo);
					if (vinfo != null) {
						mstatus.setVedioClass(vedioClassFrontService
								.getVedioClass(vinfo.getCid()));
						mstatus.setTitle(vinfo.getTitle());
						mstatus
								.setContent(vinfo.getDescription()
										+ "<br/><img onload=\"fixImage(this,300,300)\" src='"
										+ vinfo.getPicpath() + "' />");
						mstatus.setRcid(vinfo.getRcid());
						mstatus.setUrl("/membercenter/showvedioinfo?id="
								+ vinfo.getId().intValue());
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(5, vinfo.getId()
										.intValue()));
						mstatus.setRcnumber(vedioInfoFrontService
								.getMemberVedioInListNumber(vinfo.getId()
										.intValue()));
						mstatus.setCommentnumber(commentInfoFrontService
								.getCommentNumber(vinfo.getId().intValue(), 5));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 5, vinfo.getId()
										.intValue()));
					}
				}
				newlist.add(mstatus);
			}
		}
		return newlist;
	}

	public MemberStatusCenterService getMemberStatusCenterService() {
		return memberStatusCenterService;
	}

	public void setMemberStatusCenterService(
			MemberStatusCenterService memberStatusCenterService) {
		this.memberStatusCenterService = memberStatusCenterService;
	}

	public MemberStatus getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(MemberStatus memberStatus) {
		this.memberStatus = memberStatus;
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

	public MemberFriendCenterService getMemberFriendCenterService() {
		return memberFriendCenterService;
	}

	public void setMemberFriendCenterService(
			MemberFriendCenterService memberFriendCenterService) {
		this.memberFriendCenterService = memberFriendCenterService;
	}

	public MemberMoodCenterService getMemberMoodCenterService() {
		return memberMoodCenterService;
	}

	public void setMemberMoodCenterService(
			MemberMoodCenterService memberMoodCenterService) {
		this.memberMoodCenterService = memberMoodCenterService;
	}

	public MemberBlogCenterService getMemberBlogCenterService() {
		return memberBlogCenterService;
	}

	public void setMemberBlogCenterService(
			MemberBlogCenterService memberBlogCenterService) {
		this.memberBlogCenterService = memberBlogCenterService;
	}

	public MemberBlogClassCenterService getMemberBlogClassCenterService() {
		return memberBlogClassCenterService;
	}

	public void setMemberBlogClassCenterService(
			MemberBlogClassCenterService memberBlogClassCenterService) {
		this.memberBlogClassCenterService = memberBlogClassCenterService;
	}

	public MemberPhotoCenterService getMemberPhotoCenterService() {
		return memberPhotoCenterService;
	}

	public void setMemberPhotoCenterService(
			MemberPhotoCenterService memberPhotoCenterService) {
		this.memberPhotoCenterService = memberPhotoCenterService;
	}

	public MemberPhotoGroupCenterService getMemberPhotoGroupCenterService() {
		return memberPhotoGroupCenterService;
	}

	public void setMemberPhotoGroupCenterService(
			MemberPhotoGroupCenterService memberPhotoGroupCenterService) {
		this.memberPhotoGroupCenterService = memberPhotoGroupCenterService;
	}

	public VedioInfoFrontService getVedioInfoFrontService() {
		return vedioInfoFrontService;
	}

	public void setVedioInfoFrontService(
			VedioInfoFrontService vedioInfoFrontService) {
		this.vedioInfoFrontService = vedioInfoFrontService;
	}

	public VedioClassFrontService getVedioClassFrontService() {
		return vedioClassFrontService;
	}

	public void setVedioClassFrontService(
			VedioClassFrontService vedioClassFrontService) {
		this.vedioClassFrontService = vedioClassFrontService;
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

	public MemberDiaryCenterService getMemberDiaryCenterService() {
		return memberDiaryCenterService;
	}

	public void setMemberDiaryCenterService(
			MemberDiaryCenterService memberDiaryCenterService) {
		this.memberDiaryCenterService = memberDiaryCenterService;
	}

}

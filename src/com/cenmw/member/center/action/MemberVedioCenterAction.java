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
import com.cenmw.member.center.service.MemberFriendCenterService;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.center.service.MemberLLJLCenterService;
import com.cenmw.member.center.service.MemberPraiseCenterService;
import com.cenmw.member.center.service.MemberReportCenterService;
import com.cenmw.member.center.service.MemberStatusCenterService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;
import com.cenmw.vedio.front.service.VedioClassFrontService;
import com.cenmw.vedio.front.service.VedioInfoFrontService;
import com.cenmw.vedio.po.VedioClass;
import com.cenmw.vedio.po.VedioInfo;

public class MemberVedioCenterAction extends BaseAction {
	/**
	 * 会员视频 模块
	 */
	private VedioInfoFrontService vedioInfoFrontService;
	private MemberInfoCenterService memberInfoCenterService;
	private VedioClassFrontService vedioClassFrontService;
	private MemberStatusCenterService memberStatusCenterService;
	private IntegralInfoFrontService integralInfoFrontService;
	private IntegralConfigFrontService integralConfigFrontService;
	private MemberFriendCenterService memberFriendCenterService;
	private MemberPraiseCenterService memberPraiseCenterService;
	private CommentInfoFrontService commentInfoFrontService;
	private MemberReportCenterService memberReportCenterService;
	private MemberLLJLCenterService memberLLJLCenterService;
	private VedioInfo vedioInfo;
	private MemberInfo memberInfo;
	private MemberInfo cmemberInfo;
	private int id;
	private String ids;
	private List classlist;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员视频删除功能
	 * 
	 * @return
	 */
	public String delete() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				vedioInfo = vedioInfoFrontService.getVedioInfo(new Integer(
						idarrs[i].trim()));
				vedioInfo.setIsdel(new Integer(1));
				vedioInfoFrontService.updateVedioInfo(vedioInfo);
				// 删除动态信息
				memberStatusCenterService.deleteMemberStatusByCid(5,
						vedioInfo.getId(), memberInfo.getId());
				// 删除获得的积分
				IntegralInfo integralInfo = integralInfoFrontService
						.deleteIntegralInfoByCid(5, vedioInfo.getId(),
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
	 * 会员视频添加功能
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
		if (vedioInfo.getId() == null) {
			msg = "添加成功！";
			vedioInfo.setMid(memberInfo.getId());
			vedioInfo.setIsdel(new Integer(0));
			vedioInfo.setCtime(new Date());
			vedioInfo.setPtime(new Date());
		    //默认是 学生创建视频
			vedioInfo.setType(new Integer(1));
			if(memberInfo.getType() == 1){ //机构创建视频
				vedioInfo.setState(new Integer(0));
			}else{ //机构创建视频
				vedioInfo.setState(new Integer(1));
			}
			vedioInfo.setSort(new Integer(0));
			vedioInfo.setViewnumber(new Integer(0));
			vedioInfoFrontService.saveVedioInfo(vedioInfo);
			// 添加会员动态信息 2013-08-13
			memberStatusCenterService.saveMemberStatus(memberInfo.getId(), 5,
					vedioInfo.getId(), vedioInfo.getTitle(),vedioInfo.getQx());
			// 添加获得积分
			IntegralConfig ic = integralConfigFrontService
					.findIntegralConfigInList(5);
			if (ic != null) {
				IntegralInfo integralInfo = new IntegralInfo(
						memberInfo.getId(), memberInfo.getAccount(), 5,
						vedioInfo.getId(), ic.getScore());
				integralInfoFrontService.saveIntegralInfo(integralInfo);
				session.put(ConstantUtils.MEMBERINFO, memberInfoCenterService
						.getMemberInfo(memberInfo.getId()));
			}
		} else {
			// 同步更新会员动态信息 2013-10-09
			memberStatusCenterService.updateMemberStatus(memberInfo.getId(),
					5, vedioInfo.getId());
		}
		int cid = vedioInfo.getCid();
		VedioClass vedioClass = vedioClassFrontService.getVedioClass(cid);
		if (vedioClass != null) {
			vedioInfo.setClassname(vedioClass.getTitle());
		}
		vedioInfoFrontService.updateVedioInfo(vedioInfo);
		}
		session.put("saveinfomsg", msg);
		return SUCCESS;
	}

	/**
	 * 会员视频查看功能
	 * 
	 * @return
	 */
	public String info() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			vedioInfo = vedioInfoFrontService.getVedioInfo(id);
			vedioInfo.setMemberInfo(memberInfoCenterService
					.getMemberInfo(vedioInfo.getMid()));
		}
		// 初始化信息
		if (vedioInfo == null) {
			vedioInfo = new VedioInfo();
			vedioInfo.setIsdel(new Integer(0));
			vedioInfo.setPicpath("/member/images/common/no_photo.png");
		}
		// 获取分类
		if(memberInfo.getType() == 1){  //机构会员
			classlist = vedioClassFrontService.findVedioClassInList(2);
		}else{
		    classlist = vedioClassFrontService.findVedioClassInList(
				memberInfo.getId(), 1);
		}
		return SUCCESS;
	}

	/**
	 * 会员视频查看功能
	 * 
	 * @return
	 */
	public String showinfo() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		classlist = vedioClassFrontService.findVedioClassInList(
				memberInfo.getId(), 1);
		if (id > 0) {
			vedioInfo = vedioInfoFrontService.getVedioInfo(id);
			int qx = vedioInfo.getQx() == null ? 0 : vedioInfo.getQx();
			if (qx == 1) { // 判断是不是好友
				if (memberInfo.getId().intValue() != vedioInfo.getMid()
						.intValue()
						&& !memberFriendCenterService.findMemberFriends(
								memberInfo.getId(), vedioInfo.getMid())) {
					return "qx1";
				}
			} else if (qx == 2) { // 判断是不是自己
				if (memberInfo.getId().intValue() != vedioInfo.getMid()
						.intValue()) {
					return "qx2";
				}
			}
			if (vedioInfo.getMid().intValue() != memberInfo.getId().intValue()) {
				vedioInfo.setViewnumber(vedioInfo.getViewnumber() == null ? 0
						: vedioInfo.getViewnumber() + 1);
			}
			vedioInfoFrontService.updateVedioInfo(vedioInfo);
			vedioInfo.setMemberInfo(memberInfoCenterService
					.getMemberInfo(vedioInfo.getMid()));
			vedioInfo.setPraisenumber(memberPraiseCenterService
					.getMemberPraiseInListNumber(5, vedioInfo.getId()
							.intValue()));
			vedioInfo.setRcnumber(vedioInfoFrontService
					.getMemberVedioInListNumber(vedioInfo.getId().intValue()));
			vedioInfo.setCommentnumber(commentInfoFrontService
					.getCommentNumber(vedioInfo.getId().intValue(), 5));
			vedioInfo.setMemberPraise(memberPraiseCenterService
					.findMemberPraiseAll(memberInfo.getId().intValue(), 5,
							vedioInfo.getId().intValue()));
			// 添加浏览记录
			memberLLJLCenterService.saveMemberLLJL(memberInfo.getId(), "[视频]"
					+ vedioInfo.getTitle(), "/membercenter/showvedioinfo?id="
					+ vedioInfo.getId());
		}
		// 初始化信息
		if (vedioInfo == null) {
			vedioInfo = new VedioInfo();
			vedioInfo.setIsdel(new Integer(0));
		}
		return SUCCESS;
	}

	/**
	 * 会员视频列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		cmemberInfo = memberInfo;
		String hql = " from VedioInfo where isdel=0 and mid="
				+ memberInfo.getId();
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " sort desc , ptime desc , id desc ";

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
		pageSize = 12;
		// 判断排序条件
		pageBean = vedioInfoFrontService.findVedioInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/membercenter/vediolist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 好友视频列表查看功能
	 * 
	 * @return
	 */
	public String flist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		cmemberInfo = memberInfo;
		String fids = memberFriendCenterService.findMemberFriends(memberInfo
				.getId());
		if (memberInfo.getType().intValue() == 1) {
			fids = memberFriendCenterService.findMemberAllCFriends(memberInfo
					.getId().intValue(), memberInfo.getType().intValue());
		}
		if (fids.length() > 0) {
			String hql = " from VedioInfo where type<2 and isdel=0 and state=1 and mid in ("
					+ fids + ")";
			Map map = new HashMap();
			String parameter = "";
			// 默认排序 最新时间
			String sortstr = "";
			sortstr = " sort desc , ptime desc , id desc ";

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
			pageBean = vedioInfoFrontService.findVedioInfoHQLList(hql, sortstr,
					map, currentPage, pageSize);
			pageBean.setAction("/membercenter/fvediolist");
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
	 * 企业视频列表查看功能
	 * 
	 * @return
	 */
	public String clist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		cmemberInfo = memberInfo;
		String fids = memberFriendCenterService.findMemberAllCFriends(
				memberInfo.getId().intValue(), memberInfo.getType().intValue());
		if (fids.length() > 0) {
			String hql = " from VedioInfo where type<2 and isdel=0 and state=1 and mid in ("
					+ fids + ")";
			Map map = new HashMap();
			String parameter = "";
			// 默认排序 最新时间
			String sortstr = "";
			sortstr = " sort desc , ptime desc , id desc ";

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
			pageBean = vedioInfoFrontService.findVedioInfoHQLList(hql, sortstr,
					map, currentPage, pageSize);
			pageBean.setAction("/membercenter/cvediolist");
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
				VedioInfo vedioInfo = (VedioInfo) pagelist.get(i);
				vedioInfo.setMemberInfo(memberInfoCenterService
						.getMemberInfo(vedioInfo.getMid()));
				vedioInfo.setPraisenumber(memberPraiseCenterService
						.getMemberPraiseInListNumber(5, vedioInfo.getId()
								.intValue()));
				vedioInfo.setRcnumber(vedioInfoFrontService
						.getMemberVedioInListNumber(vedioInfo.getId()
								.intValue()));
				vedioInfo.setCommentnumber(commentInfoFrontService
						.getCommentNumber(vedioInfo.getId().intValue(), 5));
				vedioInfo.setMemberPraise(memberPraiseCenterService
						.findMemberPraiseAll(memberInfo.getId().intValue(), 5,
								vedioInfo.getId().intValue()));
				newlist.add(vedioInfo);
			}
		}
		return newlist;
	}

	public VedioInfoFrontService getVedioInfoFrontService() {
		return vedioInfoFrontService;
	}

	public void setVedioInfoFrontService(
			VedioInfoFrontService vedioInfoFrontService) {
		this.vedioInfoFrontService = vedioInfoFrontService;
	}

	public VedioInfo getVedioInfo() {
		return vedioInfo;
	}

	public void setVedioInfo(VedioInfo vedioInfo) {
		this.vedioInfo = vedioInfo;
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

	public VedioClassFrontService getVedioClassFrontService() {
		return vedioClassFrontService;
	}

	public void setVedioClassFrontService(
			VedioClassFrontService vedioClassFrontService) {
		this.vedioClassFrontService = vedioClassFrontService;
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

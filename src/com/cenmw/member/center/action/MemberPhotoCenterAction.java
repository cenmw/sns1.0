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
import com.cenmw.member.center.service.MemberPhotoCenterService;
import com.cenmw.member.center.service.MemberPhotoGroupCenterService;
import com.cenmw.member.center.service.MemberPraiseCenterService;
import com.cenmw.member.center.service.MemberReportCenterService;
import com.cenmw.member.center.service.MemberStatusCenterService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.member.po.MemberPhoto;
import com.cenmw.member.po.MemberPhotoGroup;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberPhotoCenterAction extends BaseAction {
	/**
	 * 会员相片 模块
	 */
	private MemberPhotoCenterService memberPhotoCenterService;
	private MemberInfoCenterService memberInfoCenterService;
	private MemberPhotoGroupCenterService memberPhotoGroupCenterService;
	private MemberStatusCenterService memberStatusCenterService;
	private IntegralInfoFrontService integralInfoFrontService;
	private IntegralConfigFrontService integralConfigFrontService;
	private MemberFriendCenterService memberFriendCenterService;
	private MemberPraiseCenterService memberPraiseCenterService;
	private CommentInfoFrontService commentInfoFrontService;
	private MemberReportCenterService memberReportCenterService;
	private MemberLLJLCenterService memberLLJLCenterService;
	private MemberPhoto memberPhoto;
	private MemberInfo memberInfo;
	private MemberInfo cmemberInfo;
	private MemberPhotoGroup memberPhotoGroup;
	private int cid = 0;
	private int id;
	private String ids;
	private List classlist;
	private String backUrl;
	private String ln; // 上一个，下一个
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员相片删除功能
	 * 
	 * @return
	 */
	public String delete() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberPhoto = memberPhotoCenterService
						.getMemberPhoto(new Integer(idarrs[i].trim()));
				memberPhoto.setIsdel(new Integer(1));
				memberPhotoCenterService.updateMemberPhoto(memberPhoto);
				// 删除动态信息
				memberStatusCenterService.deleteMemberStatusByCid(4,
						memberPhoto.getId(), memberInfo.getId());
				// 删除获得的积分
				IntegralInfo integralInfo = integralInfoFrontService
						.deleteIntegralInfoByCid(6, memberPhoto.getId(),
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
	 * 会员相片添加功能
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
			if (memberPhoto.getId() == null) {
				msg = "添加成功！";
				memberPhoto.setMid(memberInfo.getId());
				memberPhoto.setIsdel(new Integer(0));
				memberPhoto.setCtime(new Date());
				memberPhoto.setSort(new Integer(0));
				memberPhoto.setViewnumber(new Integer(0));
				memberPhotoCenterService.saveMemberPhoto(memberPhoto);
				// 添加会员动态信息 2013-08-09
				memberPhotoGroup = memberPhotoGroupCenterService
						.getMemberPhotoGroup(memberPhoto.getCid());
				memberStatusCenterService.saveMemberStatus(memberInfo.getId(),
						4, memberPhoto.getId(), memberPhotoGroup.getTitle(),memberPhoto.getQx());
				// 添加获得积分
				IntegralConfig ic = integralConfigFrontService
						.findIntegralConfigInList(6);
				if (ic != null) {
					IntegralInfo integralInfo = new IntegralInfo(
							memberInfo.getId(), memberInfo.getAccount(), 6,
							memberPhoto.getId(), ic.getScore());
					integralInfoFrontService.saveIntegralInfo(integralInfo);
					session.put(ConstantUtils.MEMBERINFO, memberInfoCenterService
							.getMemberInfo(memberInfo.getId()));
				}
			} else {
				// 同步更新会员动态信息 2013-10-09
				memberStatusCenterService.updateMemberStatus(
						memberInfo.getId(), 4, memberPhoto.getId());
			}
			if (memberPhoto.getIsindex() == 1) {
				memberPhotoCenterService.updateMemberPhotos(memberPhoto
						.getCid());
			}
			memberPhotoCenterService.updateMemberPhoto(memberPhoto);
		}
		session.put("saveinfomsg", msg);
		return SUCCESS;
	}

	/**
	 * 会员相片修改功能
	 * 
	 * @return
	 */
	public String info() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		classlist = memberPhotoGroupCenterService
				.findMemberPhotoGroupInList(memberInfo.getId());
		if (id > 0) {
			memberPhoto = memberPhotoCenterService.getMemberPhoto(id);
			memberPhoto.setMemberInfo(memberInfoCenterService
					.getMemberInfo(memberPhoto.getMid()));
			memberPhoto.setPraisenumber(memberPraiseCenterService
					.getMemberPraiseInListNumber(4, memberPhoto.getId()
							.intValue()));
			memberPhoto.setMemberPraise(memberPraiseCenterService
					.findMemberPraiseAll(memberInfo.getId().intValue(), 4,
							memberPhoto.getId().intValue()));
		}
		// 初始化信息
		if (memberPhoto == null) {
			memberPhoto = new MemberPhoto();
			memberPhoto.setIsdel(new Integer(0));
			memberPhoto.setIsindex(new Integer(0));
			memberPhoto.setPicpath("/member/images/common/no_photo.png");
			if (cid > 0) {
				memberPhoto.setCid(cid);
			}
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
		classlist = memberPhotoGroupCenterService
				.findMemberPhotoGroupInList(memberInfo.getId());
		if (id > 0) {
			memberPhoto = memberPhotoCenterService.getMemberPhoto(id);
			int qx = memberPhoto.getQx() == null ? 0 : memberPhoto.getQx();
			if (qx == 1) { // 判断是不是好友
				if (memberInfo.getId().intValue() != memberPhoto.getMid()
						.intValue()
						&& !memberFriendCenterService.findMemberFriends(
								memberInfo.getId(), memberPhoto.getMid())) {
					return "qx1";
				}
			} else if (qx == 2) { // 判断是不是自己
				if (memberInfo.getId().intValue() != memberPhoto.getMid()
						.intValue()) {
					return "qx2";
				}
			}
			memberPhotoCenterService.updateMemberPhoto(memberPhoto);
			if (ln != null && !ln.isEmpty()) {
				if (ln.equals("lnkprev")) {
					// 上一个
					MemberPhoto p_memberPhoto = memberPhotoCenterService
							.findLastMemberPhoto(memberPhoto.getCid()
									.intValue(), memberPhoto.getId().intValue());
					if (p_memberPhoto != null) {
						memberPhoto = p_memberPhoto;
					}
				} else if (ln.equals("lnknext")) {
					// 下一个
					MemberPhoto n_memberPhoto = memberPhotoCenterService
							.findNextMemberPhoto(memberPhoto.getCid()
									.intValue(), memberPhoto.getId().intValue());
					if (n_memberPhoto != null) {
						memberPhoto = n_memberPhoto;
					}
				}
			}
			memberPhoto.setMemberInfo(memberInfoCenterService
					.getMemberInfo(memberPhoto.getMid()));
			memberPhoto.setPraisenumber(memberPraiseCenterService
					.getMemberPraiseInListNumber(4, memberPhoto.getId()
							.intValue()));
			memberPhoto.setCommentnumber(commentInfoFrontService
					.getCommentNumber(memberPhoto.getId().intValue(), 4));
			memberPhoto.setMemberPraise(memberPraiseCenterService
					.findMemberPraiseAll(memberInfo.getId().intValue(), 4,
							memberPhoto.getId().intValue()));
			// 更新访问量
			if (memberPhoto.getMid().intValue() != memberInfo.getId()
					.intValue()) {
				memberPhoto
						.setViewnumber(memberPhoto.getViewnumber() == null ? 0
								: memberPhoto.getViewnumber() + 1);
			}
			id = memberPhoto.getId().intValue();
			// 添加浏览记录
			memberLLJLCenterService.saveMemberLLJL(memberInfo.getId(), "[相片]"
					+ memberPhoto.getTitle(), "/membercenter/showphotoinfo?id="
					+ memberPhoto.getId());
		}
		return SUCCESS;
	}

	/**
	 * 会员相片列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		if (cid > 0) {
			memberPhotoGroup = memberPhotoGroupCenterService
					.getMemberPhotoGroup(cid);
			memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
			cmemberInfo = memberInfo;
			String hql = " from MemberPhoto where isdel=0 and cid=" + cid
					+ " and mid=" + memberInfo.getId();
			Map map = new HashMap();
			String parameter = "";
			// 默认排序 最新时间
			String sortstr = "";
			sortstr = " id desc ";

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
			pageBean = memberPhotoCenterService.findMemberPhotoHQLList(hql,
					sortstr, map, currentPage, pageSize);
			pageBean.setAction("/membercenter/photolist");
			backUrl = pageBean.getAction() + "?cid=" + cid + "&pageSize="
					+ pageBean.getPageSize() + "&currentPage="
					+ pageBean.getCurrentPage() + parameter;
			pageBean.setGopageTemplate(request
					.getRealPath("/member/center/gopage/gopage.html"));
			pageBean.setPageList(getnewlist(pageBean.getPageList()));
		}
		return SUCCESS;
	}

	/**
	 * 好友相片列表查看功能
	 * 
	 * @return
	 */
	public String flist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		cmemberInfo = memberInfo;
		String fids = memberFriendCenterService.findMemberFriends(memberInfo
				.getId());
		if (fids.length() > 0) {
			if (cid > 0) {
				memberPhotoGroup = memberPhotoGroupCenterService
						.getMemberPhotoGroup(cid);
				String hql = " from MemberPhoto where isdel=0 and cid=" + cid
						+ " and mid in (" + fids + ")";
				Map map = new HashMap();
				String parameter = "";
				// 默认排序 最新时间
				String sortstr = "";
				sortstr = " id desc ";

				if (searchtitle != null && searchtitle.length() > 0) {
					hql += " and title like '%" + searchtitle + "%'";
					HqlBean hqlBean = new HqlBean(searchtitle, "=", "and",
							"String");
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
				pageBean = memberPhotoCenterService.findMemberPhotoHQLList(hql,
						sortstr, map, currentPage, pageSize);
				pageBean.setAction("/membercenter/fphotolist");
				backUrl = pageBean.getAction() + "?cid=" + cid + "&pageSize="
						+ pageBean.getPageSize() + "&currentPage="
						+ pageBean.getCurrentPage() + parameter;
				pageBean.setGopageTemplate(request
						.getRealPath("/member/center/gopage/gopage.html"));
				pageBean.setPageList(getnewlist(pageBean.getPageList()));
			}
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
				MemberPhoto mphoto = (MemberPhoto) pagelist.get(i);
				mphoto.setMemberInfo(memberInfoCenterService
						.getMemberInfo(mphoto.getMid()));
				mphoto.setPraisenumber(memberPraiseCenterService
						.getMemberPraiseInListNumber(4, mphoto.getId()
								.intValue()));
				mphoto.setCommentnumber(commentInfoFrontService
						.getCommentNumber(mphoto.getId().intValue(), 4));
				mphoto.setMemberPraise(memberPraiseCenterService
						.findMemberPraiseAll(memberInfo.getId().intValue(), 4,
								mphoto.getId().intValue()));
				newlist.add(mphoto);
			}
		}
		return newlist;
	}

	public MemberPhotoCenterService getMemberPhotoCenterService() {
		return memberPhotoCenterService;
	}

	public void setMemberPhotoCenterService(
			MemberPhotoCenterService memberPhotoCenterService) {
		this.memberPhotoCenterService = memberPhotoCenterService;
	}

	public MemberPhoto getMemberPhoto() {
		return memberPhoto;
	}

	public void setMemberPhoto(MemberPhoto memberPhoto) {
		this.memberPhoto = memberPhoto;
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

	public MemberPhotoGroupCenterService getMemberPhotoGroupCenterService() {
		return memberPhotoGroupCenterService;
	}

	public void setMemberPhotoGroupCenterService(
			MemberPhotoGroupCenterService memberPhotoGroupCenterService) {
		this.memberPhotoGroupCenterService = memberPhotoGroupCenterService;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public MemberPhotoGroup getMemberPhotoGroup() {
		return memberPhotoGroup;
	}

	public void setMemberPhotoGroup(MemberPhotoGroup memberPhotoGroup) {
		this.memberPhotoGroup = memberPhotoGroup;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
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

	public String getLn() {
		return ln;
	}

	public void setLn(String ln) {
		this.ln = ln;
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

package com.cenmw.comment.front.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.comment.front.service.CommentInfoFrontService;
import com.cenmw.comment.po.CommentInfo;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.center.service.MemberReportCenterService;
import com.cenmw.member.center.service.MemberStatusCenterService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.member.po.MemberStatus;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class CommentInfoFrontAction extends BaseAction {
	/**
	 * 评论信息 模块
	 */
	private CommentInfoFrontService commentInfoFrontService;
	private MemberInfoCenterService memberInfoCenterService;
	private MemberReportCenterService memberReportCenterService;
	private MemberStatusCenterService memberStatusCenterService;
	private CommentInfo commentInfo;
	private MemberInfo memberInfo;
	private int id;
	private String ids;
	private String backUrl;
	private int type;
	private int cid;
	private String no = "";
	// 搜索条件
	private String searchaccount = "";
	private int searchtype = -1;

	/**
	 * 评论信息删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				commentInfo = commentInfoFrontService
						.getCommentInfo(new Integer(idarrs[i].trim()));
				commentInfo.setIsdel(new Integer(1));
				commentInfoFrontService.updateCommentInfo(commentInfo);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 评论信息添加功能
	 * 
	 * @return
	 */
	public String save() {
		int status = 0;// 0需要会员登录 1:提交成功
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (memberReportCenterService.getIsMemberReport(memberInfo.getId()
				.intValue())) {
			responseHTMLAjax("2");
		} else {
			if (memberInfo != null) {
				commentInfo = new CommentInfo();
				commentInfo.setMid(memberInfo.getId());
				commentInfo.setIsdel(new Integer(0));
				commentInfo.setCtime(new Date());
				commentInfo.setIsopen(new Integer(0));
				commentInfo.setContent(no);
				commentInfo.setType(type);
				commentInfo.setCid(cid);
				switch (type) {
				case 0:
					commentInfo.setTypename("回复评论");
					break;
				case 1:
					commentInfo.setTypename("说说评论");
					break;
				case 2:
					commentInfo.setTypename("日志评论");
					break;
				case 3:
					commentInfo.setTypename("文章评论");
					break;
				case 4:
					commentInfo.setTypename("相片评论");
					break;
				case 5:
					commentInfo.setTypename("视频评论");
					break;
				}
				status = 1;
				commentInfoFrontService.saveCommentInfo(commentInfo);
				// 更新最新动态
				MemberStatus memberStatus = memberStatusCenterService
						.getMemberStatusByCid(type, cid);
				if (memberStatus != null) {
					memberStatus.setPtime(new Date());
					memberStatusCenterService.updateMemberStatus(memberStatus);
				}
			}
			responseHTMLAjax("" + status);
		}
		return SUCCESS;
	}

	/**
	 * 评论信息查看功能
	 * 
	 * @return
	 */
	public String info() {
		if (id > 0) {
			commentInfo = commentInfoFrontService.getCommentInfo(id);
		}
		// 初始化信息
		if (commentInfo == null) {
			commentInfo = new CommentInfo();
			commentInfo.setIsdel(new Integer(0));
			commentInfo.setType(new Integer(1));
		}
		return SUCCESS;
	}

	/**
	 * 包涵评论列表
	 * 
	 * @return
	 */
	public String inccomment() {
		// 评论列表
		String hql = " from CommentInfo where isdel=0 and type=" + type
				+ " and cid=" + cid;
		// 默认排序 最新时间
		String sortstr = "";
		Map map = new HashMap();
		sortstr = " id desc ";
		if (cid > 0) {
			HqlBean hqlBean = new HqlBean(id, "=", "and", "Integer");
			hqlBean.setIshql(1);
			map.put("id", hqlBean);
		}
		if (backUrl != null && backUrl.length() > 0) {
			HqlBean hqlBean = new HqlBean(backUrl, "=", "and", "String");
			hqlBean.setIshql(1);
			map.put("backUrl", hqlBean);
		}
		String action = "/membercenter/showmoodinfo";
		if (type == 1) {
			action = "/membercenter/showmoodinfo";
		} else if (type == 2) {
			action = "/membercenter/showbloginfo";
		} else if (type == 3) {
			action = "/membercenter/showcontentinfo";
		}
		pageSize = 5;
		// 判断排序条件
		pageBean = commentInfoFrontService.findCommentInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction(action);
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewcommentlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 重新封装评论list
	 * 
	 * @return
	 */
	public List getnewcommentlist(List pagelist) {
		List newlist = new ArrayList();
		if (pagelist != null && pagelist.size() > 0) {
			for (int i = 0; i < pagelist.size(); i++) {
				CommentInfo ci = (CommentInfo) pagelist.get(i);
				ci.setMemberInfo(memberInfoCenterService.getMemberInfo(ci
						.getMid()));
				newlist.add(ci);
			}
		}
		return newlist;
	}

	/**
	 * 评论列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		String hql = " from CommentInfo where isdel=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

		if (searchaccount != null && searchaccount.length() > 0) {
			hql += " and account like '%" + searchaccount + "%'";
			HqlBean hqlBean = new HqlBean(searchaccount, "=", "and", "String");
			hqlBean.setIshql(1);
			map.put("searchaccount", hqlBean);
			try {
				parameter += "&searchaccount="
						+ StringUtil.URLEncode(searchaccount);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		if (searchtype > -1) {
			hql += " and type =" + searchtype;
			HqlBean hqlBean = new HqlBean(searchtype, "=", "and", "Integer");
			hqlBean.setIshql(1);
			map.put("searchtype", hqlBean);
			parameter += "&searchtype=" + searchtype;
		}

		// 判断排序条件
		pageBean = commentInfoFrontService.findCommentInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/comment/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request.getRealPath("/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 重新封装list
	 * 
	 * @return
	 */
	public List getnewlist(List pagelist) {
		return pagelist;
	}

	public CommentInfoFrontService getCommentInfoFrontService() {
		return commentInfoFrontService;
	}

	public void setCommentInfoFrontService(
			CommentInfoFrontService commentInfoFrontService) {
		this.commentInfoFrontService = commentInfoFrontService;
	}

	public CommentInfo getCommentInfo() {
		return commentInfo;
	}

	public void setCommentInfo(CommentInfo commentInfo) {
		this.commentInfo = commentInfo;
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

	public String getSearchaccount() {
		return searchaccount;
	}

	public void setSearchaccount(String searchaccount) {
		this.searchaccount = searchaccount;
	}

	public int getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(int searchtype) {
		this.searchtype = searchtype;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public MemberInfoCenterService getMemberInfoCenterService() {
		return memberInfoCenterService;
	}

	public void setMemberInfoCenterService(
			MemberInfoCenterService memberInfoCenterService) {
		this.memberInfoCenterService = memberInfoCenterService;
	}

	public MemberReportCenterService getMemberReportCenterService() {
		return memberReportCenterService;
	}

	public void setMemberReportCenterService(
			MemberReportCenterService memberReportCenterService) {
		this.memberReportCenterService = memberReportCenterService;
	}

	public MemberStatusCenterService getMemberStatusCenterService() {
		return memberStatusCenterService;
	}

	public void setMemberStatusCenterService(
			MemberStatusCenterService memberStatusCenterService) {
		this.memberStatusCenterService = memberStatusCenterService;
	}

}

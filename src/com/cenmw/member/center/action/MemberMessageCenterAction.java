package com.cenmw.member.center.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.center.service.MemberFriendCenterService;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.center.service.MemberMessageCenterService;
import com.cenmw.member.center.service.MemberReportCenterService;
import com.cenmw.member.po.MemberFriend;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.member.po.MemberMessage;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberMessageCenterAction extends BaseAction {
	/**
	 * 会员消息 模块
	 */
	private MemberMessageCenterService memberMessageCenterService;
	private MemberInfoCenterService memberInfoCenterService;
	private MemberFriendCenterService memberFriendCenterService;
	private MemberReportCenterService memberReportCenterService;
	private MemberMessage memberMessage;
	private MemberInfo memberInfo;
	private int id;
	private String ids;
	private String backUrl;
	private int type = 1;
	private String typename = "";
	private List rlist; // 查看收消息记录
	private int rid; // 回复短信的ID
	private String rids = ""; // 回复短信的ID
	private String accounts = ""; // 回复短信的ID
	private MemberMessage rmemberMessage; // 回复短信
	private String reviceids; // 发送对象的ids
	private List fmemberlist; // 通讯录
	// 搜索条件
	private String searchcontent = "";

	/**
	 * 通讯录
	 * 
	 * @return
	 */
	public String fmemberlist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		// 得到通讯录
		fmemberlist = memberFriendCenterService
				.findMemberFriendInList(memberInfo.getId());
		List newlist = new ArrayList();
		if (fmemberlist != null && fmemberlist.size() > 0) {
			for (int i = 0; i < fmemberlist.size(); i++) { 
				MemberFriend mfriend = (MemberFriend) fmemberlist.get(i);
				if (memberInfo.getId().intValue() == mfriend.getFid()) {
					mfriend.setMemberInfo(memberInfoCenterService
							.getMemberInfo(mfriend.getFid()));
					mfriend.setRmemberInfo(memberInfoCenterService
							.getMemberInfo(mfriend.getMid()));
				} else {
					mfriend.setMemberInfo(memberInfoCenterService
							.getMemberInfo(mfriend.getMid()));
					mfriend.setRmemberInfo(memberInfoCenterService
							.getMemberInfo(mfriend.getFid()));
				}
				newlist.add(mfriend);
			}
		}
		rlist = newlist;
		return SUCCESS;
	}

	/**
	 * 会员消息删除功能
	 * 
	 * @return
	 */
	public String delete() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberMessage = memberMessageCenterService
						.getMemberMessage(new Integer(idarrs[i].trim()));
				memberMessage.setIsdel(new Integer(1));
				memberMessageCenterService.updateMemberMessage(memberMessage);
			}
			if (type == 1) {
				typename = "messagelist";
			} else if (type == 2) {
				typename = "smessagelist";
			} else if (type == 3) {
				typename = "rmessagelist";
			} else if (type == 4) {
				typename = "messageinfo";
			}
			String msg = "删除成功";
			session.put("deleteinfomsg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员发送消息功能
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
			if (memberMessage.getId() == null) {
				msg = "添加成功！";
				if (reviceids != null && reviceids.length() > 0) {
					String[] reviceidsarr = reviceids.split(",");
					for (int i = 0; i < reviceidsarr.length; i++) {
						MemberMessage newmessage = new MemberMessage();
						newmessage.setMid(memberInfo.getId());
						newmessage.setIsdel(new Integer(0));
						newmessage.setIsopen(new Integer(0));
						newmessage.setCtime(new Date());
						newmessage.setReviceid(new Integer(reviceidsarr[i]));
						newmessage.setContent(memberMessage.getContent());
						memberMessageCenterService
								.saveMemberMessage(newmessage);
					}
				}
				if (type == 1) {
					typename = "sendmessageinfo";
				}
			}
		}
		session.put("saveinfomsg", msg);
		return SUCCESS;
	}

	/**
	 * 会员消息查看功能
	 * 
	 * @return
	 */
	public String sendinfo() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (rid > 0) {
			rmemberMessage = memberMessageCenterService.getMemberMessage(rid);
			if (rmemberMessage != null) {
				rmemberMessage.setMemberInfo(memberInfoCenterService
						.getMemberInfo(rmemberMessage.getMid()));
				rmemberMessage.setRmemberInfo(memberInfoCenterService
						.getMemberInfo(rmemberMessage.getReviceid()));
				rids = "" + rmemberMessage.getMid();
				accounts = rmemberMessage.getMemberInfo().getAccount();
			}
		} else if (rids != null && !rids.isEmpty() && rids.length() > 0) {
			String[] ridsarr = rids.split(",");
			for (int i = 0; i < ridsarr.length; i++) {
				MemberInfo mi = memberInfoCenterService
						.getMemberInfo(new Integer(ridsarr[i]));
				if (mi != null) {
					accounts += "" + mi.getAccount() + ";";
				}
			}
			if (accounts.length() > 1) {
				accounts = accounts.substring(0, accounts.length() - 1);
			}
		}
		// 发送短消息
		memberMessage = new MemberMessage();
		return SUCCESS;
	}

	/**
	 * 会员消息查看功能
	 * 
	 * @return
	 */
	public String info() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			memberMessage = memberMessageCenterService.getMemberMessage(id);
			memberMessage.setMemberInfo(memberInfoCenterService
					.getMemberInfo(memberMessage.getMid()));
			memberMessage.setRmemberInfo(memberInfoCenterService
					.getMemberInfo(memberMessage.getReviceid()));

			rlist = memberMessageCenterService.findMemberMessagerList(
					memberInfo.getId(), memberMessage.getMid());
			List newlist = new ArrayList();
			if (rlist != null && rlist.size() > 0) {
				for (int i = 0; i < rlist.size(); i++) {
					MemberMessage mmessage = (MemberMessage) rlist.get(i);
					mmessage.setMemberInfo(memberInfoCenterService
							.getMemberInfo(mmessage.getMid()));
					mmessage.setRmemberInfo(memberInfoCenterService
							.getMemberInfo(mmessage.getReviceid()));
					newlist.add(mmessage);
					if (memberMessage.getReviceid().intValue() == mmessage
							.getReviceid().intValue()) {
						// 更新未读短信，变为已读状态。
						mmessage.setIsopen(new Integer(1));
						memberMessageCenterService
								.updateMemberMessage(mmessage);
					}
				}
			}
			rlist = newlist;
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
		String hql = " from MemberMessage where isdel=0 and isopen=0 and reviceid="
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
		pageBean = memberMessageCenterService.findMemberMessageHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/messagelist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 发件箱列表查看功能
	 * 
	 * @return
	 */
	public String slist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String hql = " from MemberMessage where isdel=0 and mid="
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
		pageBean = memberMessageCenterService.findMemberMessageHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/smessagelist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 收件箱列表查看功能
	 * 
	 * @return
	 */
	public String rlist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String hql = " from MemberMessage where isdel=0 and reviceid="
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
		pageBean = memberMessageCenterService.findMemberMessageHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/rmessagelist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
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
				MemberMessage mmessage = (MemberMessage) pagelist.get(i);
				mmessage.setMemberInfo(memberInfoCenterService
						.getMemberInfo(mmessage.getMid()));
				mmessage.setRmemberInfo(memberInfoCenterService
						.getMemberInfo(mmessage.getReviceid()));
				newlist.add(mmessage);
			}
		}
		return newlist;
	}

	public MemberMessageCenterService getMemberMessageCenterService() {
		return memberMessageCenterService;
	}

	public void setMemberMessageCenterService(
			MemberMessageCenterService memberMessageCenterService) {
		this.memberMessageCenterService = memberMessageCenterService;
	}

	public MemberMessage getMemberMessage() {
		return memberMessage;
	}

	public void setMemberMessage(MemberMessage memberMessage) {
		this.memberMessage = memberMessage;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public List getRlist() {
		return rlist;
	}

	public void setRlist(List rlist) {
		this.rlist = rlist;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public MemberMessage getRmemberMessage() {
		return rmemberMessage;
	}

	public void setRmemberMessage(MemberMessage rmemberMessage) {
		this.rmemberMessage = rmemberMessage;
	}

	public String getReviceids() {
		return reviceids;
	}

	public void setReviceids(String reviceids) {
		this.reviceids = reviceids;
	}

	public List getFmemberlist() {
		return fmemberlist;
	}

	public void setFmemberlist(List fmemberlist) {
		this.fmemberlist = fmemberlist;
	}

	public MemberFriendCenterService getMemberFriendCenterService() {
		return memberFriendCenterService;
	}

	public void setMemberFriendCenterService(
			MemberFriendCenterService memberFriendCenterService) {
		this.memberFriendCenterService = memberFriendCenterService;
	}

	public MemberReportCenterService getMemberReportCenterService() {
		return memberReportCenterService;
	}

	public void setMemberReportCenterService(
			MemberReportCenterService memberReportCenterService) {
		this.memberReportCenterService = memberReportCenterService;
	}

	public String getRids() {
		return rids;
	}

	public void setRids(String rids) {
		this.rids = rids;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

}

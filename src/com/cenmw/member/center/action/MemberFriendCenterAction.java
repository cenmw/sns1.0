package com.cenmw.member.center.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.center.service.MemberFriendCenterService;
import com.cenmw.member.center.service.MemberStatusCenterService;
import com.cenmw.member.po.MemberFriend;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberFriendCenterAction extends BaseAction {
	/**
	 * 会员好友申请 模块
	 */
	private MemberFriendCenterService memberFriendCenterService;
	private MemberInfoCenterService memberInfoCenterService;
	private MemberStatusCenterService memberStatusCenterService;
	private MemberFriend memberFriend;
	private MemberInfo memberInfo;
	private MemberInfo fmemberInfo;
	private int id;
	private int isagree;
	private String ids;
	private String backUrl;
	private int fid; // 好友申请
	private String content; // 好友申请
	private int mfid = 0;
	private int type;
	private String typename;
	// 搜索条件
	private String searchcontent = "";

	/**
	 * 对企业加关注
	 * 
	 * @return
	 */
	public String addMemberFriendAJAX() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		memberFriend = new MemberFriend();
		memberFriend.setMid(memberInfo.getId());
		memberFriend.setType(1);
		memberFriend.setCid(new Integer(0));
		memberFriend.setFid(fid);
		memberFriend.setContent("加关注");
		memberFriend.setIsagree(new Integer(1));
		memberFriend.setIsdel(new Integer(0));
		memberFriend.setCtime(new Date());
		memberFriendCenterService.saveMemberFriend(memberFriend);
		responseHTMLAjax("1");
		return null;
	}

	/**
	 * 同意，拒绝
	 * 
	 * @return
	 */
	public String updateMemberFriendAJAX() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		memberFriend = memberFriendCenterService.getMemberFriend(id);
		if (memberFriend != null && isagree > 0) {
			memberFriend.setIsagree(isagree);
			memberFriendCenterService.updateMemberFriend(memberFriend);
		}
		responseHTMLAjax("1");
		return null;
	}

	/**
	 * 会员好友申请删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberFriend = memberFriendCenterService
						.getMemberFriend(new Integer(idarrs[i].trim()));
				memberFriend.setIsdel(new Integer(1));
				memberFriendCenterService.updateMemberFriend(memberFriend);
			}
			if (type == 0) {
				typename = "friendlist";
			} else {
				typename = "cfriendlist";
			}
		}
		return SUCCESS;
	}

	/**
	 * 会员好友申请添加功能
	 * 
	 * @return
	 */
	public String save() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String msg = "申请成功！";
		if (fid > 0 && content != null && content.length() > 0) {
			memberFriend = new MemberFriend();
			memberFriend.setMid(memberInfo.getId());
			memberFriend.setType(type);
			memberFriend.setCid(new Integer(0));
			memberFriend.setFid(fid);
			memberFriend.setContent(content);
			memberFriend.setIsagree(new Integer(0));
			memberFriend.setIsdel(new Integer(0));
			memberFriend.setCtime(new Date());
			memberFriendCenterService.saveMemberFriend(memberFriend);
			session.put("saveinfomsg", msg);
			mfid = memberFriend.getId().intValue();
		}
		return SUCCESS;
	}

	/**
	 * 会员好友申请查看功能
	 * 
	 * @return
	 */
	public String addfriend() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		fmemberInfo = memberInfoCenterService.getMemberInfo(fid);
		if (memberInfo.getId().intValue() == fid) {
			mfid = fid;
		} else {
			memberFriend = memberFriendCenterService.findMemberFriendInList(
					memberInfo.getId().intValue(), fid);
			if (fmemberInfo != null && fmemberInfo.getType() == 1) {
				if (memberFriend == null) {
					memberFriend = new MemberFriend();
					memberFriend.setMid(memberInfo.getId());
					memberFriend.setType(1);
					memberFriend.setCid(new Integer(0));
					memberFriend.setFid(fid);
					memberFriend.setContent("加关注");
					memberFriend.setIsagree(new Integer(1));
					memberFriend.setIsdel(new Integer(0));
					memberFriend.setCtime(new Date());
					memberFriendCenterService.saveMemberFriend(memberFriend);
				}
			}
			if (memberFriend != null) {
				mfid = memberFriend.getId().intValue();
			}
		}
		return SUCCESS;
	}

	/**
	 * 会员好友查看功能
	 * 
	 * @return
	 */
	public String showfriend() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		fmemberInfo = memberInfoCenterService.getMemberInfo(fid);
		return SUCCESS;
	}

	/**
	 * 好友列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String hql = " from MemberFriend where isdel=0 and type=0 and isagree=1 and (mid="
				+ memberInfo.getId() + " or fid=" + memberInfo.getId() + " )";
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
		pageSize = 12;
		// 判断排序条件
		pageBean = memberFriendCenterService.findMemberFriendHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/friendlist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 等待审核好友列表查看功能
	 * 
	 * @return
	 */
	public String dlist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String hql = " from MemberFriend where isdel=0 and type=0 and isagree=0 and fid="
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
		pageSize = 12;
		// 判断排序条件
		pageBean = memberFriendCenterService.findMemberFriendHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/dfriendlist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 添加好友列表查看功能
	 * 
	 * @return
	 */
	public String alist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String fids = memberFriendCenterService.findMemberFriendsSH(memberInfo
				.getId().intValue());
		String hql = " from MemberInfo where isdel=0 and type=0 and status=1";
		if (fids != null && !fids.isEmpty() && fids.length() > 0) {
			hql = " from MemberInfo where isdel=0 and type=0 and status=1 and id not in("
					+ fids + ")";
		}
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

		if (searchcontent != null && searchcontent.length() > 0) {
			hql += " and account like '%" + searchcontent + "%'";
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
		pageSize = 12;
		// 判断排序条件
		pageBean = memberInfoCenterService.findMemberInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/membercenter/afriendlist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewmlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 企业好友列表查看功能
	 * 
	 * @return
	 */
	public String clist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String hql = " from MemberFriend where isdel=0 and type=1 and isagree=1 and fid="
				+ memberInfo.getId();
		if (memberInfo.getType().intValue() == 0) {
			hql = " from MemberFriend where isdel=0 and type=1 and isagree=1 and mid="
					+ memberInfo.getId();
		}
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
		pageBean = memberFriendCenterService.findMemberFriendHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/cfriendlist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 添加企业列表查看功能
	 * 
	 * @return
	 */
	public String calist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String fids = memberFriendCenterService.findMemberAllCFriends(
				memberInfo.getId().intValue(), memberInfo.getType().intValue());
		String hql = " from MemberInfo where isdel=0 and type=1 and status=1";
		if (fids != null && !fids.isEmpty() && fids.length() > 0) {
			hql = " from MemberInfo where isdel=0 and type=1 and status=1 and id not in("
					+ fids + ")";
		}

		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

		if (searchcontent != null && searchcontent.length() > 0) {
			hql += " and account like '%" + searchcontent + "%'";
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
		pageSize = 12;
		// 判断排序条件
		pageBean = memberInfoCenterService.findMemberInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/membercenter/cafriendlist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewmlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 重新封装list
	 * 
	 * @return
	 */
	public List getnewlist(List pagelist) {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		List newlist = new ArrayList();
		if (pagelist != null && pagelist.size() > 0) {
			for (int i = 0; i < pagelist.size(); i++) {
				MemberFriend mfriend = (MemberFriend) pagelist.get(i);
				if (memberInfo.getId().intValue() == mfriend.getMid()
						.intValue()) {
					mfriend.setMemberInfo(memberInfoCenterService
							.getMemberInfo(mfriend.getMid()));
					mfriend.setRmemberInfo(memberInfoCenterService
							.getMemberInfo(mfriend.getFid()));
					mfriend.setRmemberStatus(memberStatusCenterService
							.getMemberStatusByMid(mfriend.getFid()));
				} else if (memberInfo.getId().intValue() == mfriend.getFid()
						.intValue()) {
					mfriend.setMemberInfo(memberInfoCenterService
							.getMemberInfo(mfriend.getFid()));
					mfriend.setRmemberInfo(memberInfoCenterService
							.getMemberInfo(mfriend.getMid()));
					mfriend.setRmemberStatus(memberStatusCenterService
							.getMemberStatusByMid(mfriend.getMid()));
				}

				newlist.add(mfriend);
			}
		}
		return newlist;
	}

	/**
	 * 重新封装list
	 * 
	 * @return
	 */
	public List getnewmlist(List pagelist) {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		List newlist = new ArrayList();
		if (pagelist != null && pagelist.size() > 0) {
			for (int i = 0; i < pagelist.size(); i++) {
				MemberInfo minfo = (MemberInfo) pagelist.get(i);
				minfo = memberInfoCenterService.getMemberInfo(minfo.getId()
						.intValue());
				minfo.setMemberStatus(memberStatusCenterService
						.getMemberStatusByMid(minfo.getId().intValue()));
				newlist.add(minfo);
			}
		}
		return newlist;
	}

	public MemberFriendCenterService getMemberFriendCenterService() {
		return memberFriendCenterService;
	}

	public void setMemberFriendCenterService(
			MemberFriendCenterService memberFriendCenterService) {
		this.memberFriendCenterService = memberFriendCenterService;
	}

	public MemberFriend getMemberFriend() {
		return memberFriend;
	}

	public void setMemberFriend(MemberFriend memberFriend) {
		this.memberFriend = memberFriend;
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

	public MemberStatusCenterService getMemberStatusCenterService() {
		return memberStatusCenterService;
	}

	public void setMemberStatusCenterService(
			MemberStatusCenterService memberStatusCenterService) {
		this.memberStatusCenterService = memberStatusCenterService;
	}

	public int getIsagree() {
		return isagree;
	}

	public void setIsagree(int isagree) {
		this.isagree = isagree;
	}

	public MemberInfo getFmemberInfo() {
		return fmemberInfo;
	}

	public void setFmemberInfo(MemberInfo fmemberInfo) {
		this.fmemberInfo = fmemberInfo;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getMfid() {
		return mfid;
	}

	public void setMfid(int mfid) {
		this.mfid = mfid;
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

}

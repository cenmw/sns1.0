package com.cenmw.member.center.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.util.ConstantUtils;
import org.apache.commons.lang.xwork.StringUtils;

import com.cenmw.base.BaseAction;
import com.cenmw.consult.front.service.ConsultClassFrontService;
import com.cenmw.consult.front.service.ConsultInfoFrontService;
import com.cenmw.consult.front.service.ConsultReplyInfoFrontService;
import com.cenmw.consult.po.ConsultClass;
import com.cenmw.consult.po.ConsultInfo;
import com.cenmw.consult.po.ConsultReplyInfo;
import com.cenmw.integral.front.service.IntegralInfoFrontService;
import com.cenmw.integral.po.IntegralInfo;
import com.cenmw.member.center.service.MemberFriendCenterService;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.center.service.MemberStatusCenterService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberConsultCenterAction extends BaseAction {
	/**
	 * 咨询信息 模块
	 */
	private ConsultInfoFrontService consultInfoFrontService;
	private ConsultClassFrontService consultClassFrontService;
	private ConsultReplyInfoFrontService consultReplyInfoFrontService;
	private MemberInfoCenterService memberInfoCenterService;
	private IntegralInfoFrontService integralInfoFrontService;
	private MemberFriendCenterService memberFriendCenterService;
	private MemberStatusCenterService memberStatusCenterService;
	private ConsultInfo consultInfo;
	private MemberInfo memberInfo;
	private int id;
	private String ids;
	private List classlist;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";
	private int searchcid = 0;
	private int searchtype = -1;
	private List consultclasslist; // 管理员自定义咨询分类
	private List consultreplylist; // 咨询回复信息
	private int replyid;
	private String content; // 回复的内容
	private int cid;

	/**
	 * 同意咨询回复添加功能
	 * 
	 * @return
	 */
	public String updatereply() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			consultInfo = consultInfoFrontService.getConsultInfo(id);
		}
		if (replyid > 0) {
			ConsultReplyInfo consultReplyInfo = consultReplyInfoFrontService
					.getConsultReplyInfo(replyid);
			if (consultReplyInfo != null) {
				consultReplyInfo.setIsagree(new Integer(1));
				consultReplyInfoFrontService
						.updateConsultReplyInfo(consultReplyInfo);
				// 并给当前会员添加积分操作
				// 分两步操作，给同意的添加积分，扣除自己付出的积分
				if (consultInfo != null) {
					int mid = consultInfo.getMid().intValue();
					MemberInfo mi = memberInfoCenterService.getMemberInfo(mid);
					if (mi != null) {
						IntegralInfo integralInfo = new IntegralInfo(
								mi.getId(), mi.getAccount(), 2,
								consultInfo.getId(), -consultInfo.getScore());
						integralInfoFrontService.saveIntegralInfo(integralInfo);
					}
				}
				// 给同意的对象添加积分
				MemberInfo mi = memberInfoCenterService
						.getMemberInfo(consultReplyInfo.getMid().intValue());
				if (mi != null) {
					IntegralInfo integralInfo = new IntegralInfo(mi.getId(),
							mi.getAccount(), 2, consultReplyInfo.getId(),
							consultInfo.getScore());
					integralInfoFrontService.saveIntegralInfo(integralInfo);
				}
			}
		}
		return SUCCESS;
	}

	/**
	 * 同意咨询回复添加功能
	 * 
	 * @return
	 */
	public String savereply() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (cid > 0 && content != null && content.length() > 0) {
			ConsultReplyInfo consultReplyInfo = new ConsultReplyInfo();
			consultReplyInfo.setMid(memberInfo.getId().intValue());
			consultReplyInfo.setCid(cid);
			consultReplyInfo.setContent(content);
			consultReplyInfo.setSort(0);
			consultReplyInfo.setIsagree(0);
			consultReplyInfo.setIsdel(0);
			consultReplyInfo.setCtime(new Date());
			consultReplyInfoFrontService.saveConsultReplyInfo(consultReplyInfo);
			consultInfo = consultInfoFrontService.getConsultInfo(cid);
		}
		return SUCCESS;
	}

	/**
	 * 咨询信息删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				consultInfo = consultInfoFrontService
						.getConsultInfo(new Integer(idarrs[i].trim()));
				consultInfo.setIsdel(new Integer(1));
				consultInfoFrontService.updateConsultInfo(consultInfo);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 咨询信息添加功能
	 * 
	 * @return
	 */
	public String save() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String msg = "修改成功！";
		if (consultInfo.getId() == null) {
			msg = "添加成功！";
			consultInfo.setMid(memberInfo.getId());
			consultInfo.setIsdel(new Integer(0));
			consultInfo.setCtime(new Date());
			consultInfoFrontService.saveConsultInfo(consultInfo);
			consultInfo.setSort(consultInfo.getId());
			// 添加会员动态信息 2015-12-18
			memberStatusCenterService.saveMemberStatus(memberInfo.getId(),
					8, consultInfo.getId(), consultInfo.getTitle(), consultInfo.getQx());
		}
		int cid = consultInfo.getCid().intValue();
		if (cid > 0) {
			ConsultClass consultClass = consultClassFrontService
					.getConsultClass(cid);
			if (consultClass != null) {
				consultInfo.setClassname(consultClass.getTitle());
			}
		}
		consultInfoFrontService.updateConsultInfo(consultInfo);
		session.put("saveinfomsg", msg);
		return SUCCESS;
	}

	/**
	 * 咨询信息查看功能
	 * 
	 * @return
	 */
	public String info() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		classlist = consultClassFrontService.findConsultClassInList(2);
		consultclasslist = consultClassFrontService.findConsultClassInList(2);
		if (id > 0) {
			consultInfo = consultInfoFrontService.getConsultInfo(id);
			consultreplylist = consultReplyInfoFrontService
					.findConsultReplyInfoInList(id);
			consultInfo.setReplyisgreecount(consultReplyInfoFrontService
					.findConsultReplyInfoIsgreeCount(consultInfo.getId()));
		}
		// 初始化信息
		if (consultInfo == null) {
			consultInfo = new ConsultInfo();
			consultInfo.setIsdel(new Integer(0));
			consultInfo.setCid(new Integer(0));
			consultInfo.setTitle("");
			consultInfo.setDescription("");
			consultInfo.setContent("");
			consultInfo.setState(new Integer(1));
			consultInfo.setSort(new Integer(0));
			consultInfo.setMid(memberInfo.getId());
			consultInfo.setType(new Integer(0));
			consultInfo.setScore(new Integer(5));
		}
		return SUCCESS;
	}

	/**
	 * 咨询信息查看功能
	 * 
	 * @return
	 */
	public String showinfo() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		classlist = consultClassFrontService.findConsultClassInList(2);
		consultclasslist = consultClassFrontService.findConsultClassInList(2);
		if (id > 0) {
			consultInfo = consultInfoFrontService.getConsultInfo(id);
			consultreplylist = consultReplyInfoFrontService
					.findConsultReplyInfoInList(id);
			consultInfo.setReplyisgreecount(consultReplyInfoFrontService
					.findConsultReplyInfoIsgreeCount(consultInfo.getId()));
			consultInfo.setMemberInfo(memberInfoCenterService
					.getMemberInfo(consultInfo.getMid()));
		}
		return SUCCESS;
	}

	/**
	 * 基础列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		consultclasslist = consultClassFrontService.findConsultClassInList(2);
		String hql = " from ConsultInfo where isdel=0 and state=1 and mid="
				+ memberInfo.getId();
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " sort desc , id desc ";

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

		if (searchtype > -1) {
			hql += " and type =" + searchtype;
			HqlBean hqlBean = new HqlBean(searchtype, "=", "and", "Integer");
			hqlBean.setIshql(1);
			map.put("searchtype", hqlBean);
			parameter += "&searchtype=" + searchtype;
		}

		if (searchcid > 0) {
			hql += " and cid =" + searchcid;
			HqlBean hqlBean = new HqlBean(searchcid, "=", "and", "Integer");
			hqlBean.setIshql(1);
			map.put("searchcid", hqlBean);
			parameter += "&searchcid=" + searchcid;
		}

		// 判断排序条件
		pageBean = consultInfoFrontService.findConsultInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/membercenter/consultlist");
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
				ConsultInfo cinfo = (ConsultInfo) pagelist.get(i);
				cinfo.setReplycount(consultReplyInfoFrontService
						.findConsultReplyInfoCount(cinfo.getId()));
				cinfo.setReplyisgreecount(consultReplyInfoFrontService
						.findConsultReplyInfoIsgreeCount(cinfo.getId()));
				cinfo.setMemberInfo(memberInfoCenterService.getMemberInfo(cinfo.getMid()));
				newlist.add(cinfo);
			}
		}
		return newlist;
	}

	/**
	 * 基础列表查看功能   -- 好友咨询
	 * 
	 * @return
	 */
	public String flist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String fids = null;
		if (memberInfo.getType().intValue() == 0) { // 普通会员
			fids = memberFriendCenterService
					.findMemberFriends(memberInfo.getId());
		}
		if(StringUtils.isNotBlank(fids)){
			consultclasslist = consultClassFrontService.findConsultClassInList(2);
			String hql = " from ConsultInfo where isdel=0 and state=1 and mid in("
					+ fids+")";
			Map map = new HashMap();
			String parameter = "";
			// 默认排序 最新时间
			String sortstr = "";
			sortstr = " sort desc , id desc ";

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

			if (searchtype > -1) {
				hql += " and type =" + searchtype;
				HqlBean hqlBean = new HqlBean(searchtype, "=", "and", "Integer");
				hqlBean.setIshql(1);
				map.put("searchtype", hqlBean);
				parameter += "&searchtype=" + searchtype;
			}

			if (searchcid > 0) {
				hql += " and cid =" + searchcid;
				HqlBean hqlBean = new HqlBean(searchcid, "=", "and", "Integer");
				hqlBean.setIshql(1);
				map.put("searchcid", hqlBean);
				parameter += "&searchcid=" + searchcid;
			}

			// 判断排序条件
			pageBean = consultInfoFrontService.findConsultInfoHQLList(hql, sortstr,
					map, currentPage, pageSize);
			pageBean.setAction("/membercenter/consultlist");
			backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
					+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
			pageBean.setGopageTemplate(request
					.getRealPath("/member/center/gopage/gopage.html"));
			pageBean.setPageList(getnewlist(pageBean.getPageList()));
		}
		
		return SUCCESS;
	}

	
	public ConsultInfoFrontService getConsultInfoFrontService() {
		return consultInfoFrontService;
	}

	public void setConsultInfoFrontService(
			ConsultInfoFrontService consultInfoFrontService) {
		this.consultInfoFrontService = consultInfoFrontService;
	}

	public ConsultInfo getConsultInfo() {
		return consultInfo;
	}

	public void setConsultInfo(ConsultInfo consultInfo) {
		this.consultInfo = consultInfo;
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

	public String getSearchtitle() {
		return searchtitle;
	}

	public void setSearchtitle(String searchtitle) {
		this.searchtitle = searchtitle;
	}

	public int getSearchcid() {
		return searchcid;
	}

	public void setSearchcid(int searchcid) {
		this.searchcid = searchcid;
	}

	public ConsultClassFrontService getConsultClassFrontService() {
		return consultClassFrontService;
	}

	public void setConsultClassFrontService(
			ConsultClassFrontService consultClassFrontService) {
		this.consultClassFrontService = consultClassFrontService;
	}

	public List getConsultclasslist() {
		return consultclasslist;
	}

	public void setConsultclasslist(List consultclasslist) {
		this.consultclasslist = consultclasslist;
	}

	public int getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(int searchtype) {
		this.searchtype = searchtype;
	}

	public ConsultReplyInfoFrontService getConsultReplyInfoFrontService() {
		return consultReplyInfoFrontService;
	}

	public void setConsultReplyInfoFrontService(
			ConsultReplyInfoFrontService consultReplyInfoFrontService) {
		this.consultReplyInfoFrontService = consultReplyInfoFrontService;
	}

	public List getConsultreplylist() {
		return consultreplylist;
	}

	public void setConsultreplylist(List consultreplylist) {
		this.consultreplylist = consultreplylist;
	}

	public int getReplyid() {
		return replyid;
	}

	public void setReplyid(int replyid) {
		this.replyid = replyid;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public MemberInfoCenterService getMemberInfoCenterService() {
		return memberInfoCenterService;
	}

	public void setMemberInfoCenterService(
			MemberInfoCenterService memberInfoCenterService) {
		this.memberInfoCenterService = memberInfoCenterService;
	}

	public IntegralInfoFrontService getIntegralInfoFrontService() {
		return integralInfoFrontService;
	}

	public void setIntegralInfoFrontService(
			IntegralInfoFrontService integralInfoFrontService) {
		this.integralInfoFrontService = integralInfoFrontService;
	}

	public MemberFriendCenterService getMemberFriendCenterService() {
		return memberFriendCenterService;
	}

	public void setMemberFriendCenterService(
			MemberFriendCenterService memberFriendCenterService) {
		this.memberFriendCenterService = memberFriendCenterService;
	}

	public MemberStatusCenterService getMemberStatusCenterService() {
		return memberStatusCenterService;
	}

	public void setMemberStatusCenterService(
			MemberStatusCenterService memberStatusCenterService) {
		this.memberStatusCenterService = memberStatusCenterService;
	}

}

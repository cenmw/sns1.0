package com.cenmw.member.center.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.center.service.MemberPraiseCenterService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.member.po.MemberPraise;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberPraiseCenterAction extends BaseAction {
	/**
	 * 会员赞 模块
	 */
	private MemberPraiseCenterService memberPraiseCenterService;
	private MemberInfoCenterService memberInfoCenterService;
	private MemberPraise memberPraise;
	private MemberInfo memberInfo;
	private int id;
	private String ids;
	private String backUrl;
	private int type;// 赞的类型 1:会员说说 2:会员日志 3:会员文集 4：会员相册 5:会员视频
	private int cid;// 赞类型的id
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 赞，取消赞
	 * 
	 * @return
	 */
	public String updateMemberPraiseAJAX() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		memberPraise = memberPraiseCenterService.findMemberPraise(memberInfo
				.getId().intValue(), type, cid);
		int isdel = 0;
		if (memberPraise == null) {
			memberPraise = new MemberPraise();
			memberPraise.setMid(memberInfo.getId().intValue());
			memberPraise.setType(type);
			memberPraise.setCid(cid);
			memberPraise.setCtime(new Date());
			memberPraise.setIsdel(0);
			memberPraiseCenterService.saveMemberPraise(memberPraise);
		} else {
			isdel = memberPraise.getIsdel().intValue();
			if (isdel == 0) {
				memberPraise.setIsdel(1);
			} else {
				memberPraise.setIsdel(0);
			}
			memberPraiseCenterService.updateMemberPraise(memberPraise);
			isdel = memberPraise.getIsdel().intValue();
		}
		responseHTMLAjax(isdel
				+ "-"
				+ memberPraiseCenterService.getMemberPraiseInListNumber(type,
						cid));
		return null;
	}

	/**
	 * 会员赞删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberPraise = memberPraiseCenterService
						.getMemberPraise(new Integer(idarrs[i].trim()));
				memberPraise.setIsdel(new Integer(1));
				memberPraiseCenterService.updateMemberPraise(memberPraise);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员赞添加功能
	 * 
	 * @return
	 */
	public String save() {
		String msg = "修改成功！";
		if (memberPraise.getId() == null) {
			msg = "添加成功！";
			memberPraise.setIsdel(new Integer(0));
			memberPraise.setCtime(new Date());
			memberPraiseCenterService.saveMemberPraise(memberPraise);
		}
		memberPraiseCenterService.updateMemberPraise(memberPraise);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员赞查看功能
	 * 
	 * @return
	 */
	public String info() {
		if (id > 0) {
			memberPraise = memberPraiseCenterService.getMemberPraise(id);
			memberPraise.setMemberInfo(memberInfoCenterService
					.getMemberInfo(memberPraise.getMid()));
		}
		// 初始化信息
		if (memberPraise == null) {
			memberPraise = new MemberPraise();
			memberPraise.setIsdel(new Integer(0));
		}
		return SUCCESS;
	}

	/**
	 * 会员列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		String hql = " from MemberPraise where isdel=0";
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
		pageSize = 20;
		// 判断排序条件
		pageBean = memberPraiseCenterService.findMemberPraiseHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/center/memberpraise/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/center/gopage/gopage.html"));
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
				MemberPraise mpraise = (MemberPraise) pagelist.get(i);
				mpraise.setMemberInfo(memberInfoCenterService
						.getMemberInfo(mpraise.getMid()));
				newlist.add(mpraise);
			}
		}
		return newlist;
	}

	public MemberPraiseCenterService getMemberPraiseCenterService() {
		return memberPraiseCenterService;
	}

	public void setMemberPraiseCenterService(
			MemberPraiseCenterService memberPraiseCenterService) {
		this.memberPraiseCenterService = memberPraiseCenterService;
	}

	public MemberPraise getMemberPraise() {
		return memberPraise;
	}

	public void setMemberPraise(MemberPraise memberPraise) {
		this.memberPraise = memberPraise;
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

}

package com.cenmw.member.center.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.labor.front.service.LaborInfoFrontService;
import com.cenmw.labor.front.service.LaborReplyInfoFrontService;
import com.cenmw.labor.po.LaborInfo;
import com.cenmw.labor.po.LaborReplyInfo;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.center.service.MemberLLJLCenterService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberLaborCenterAction extends BaseAction {
	/**
	 * 会员活动 模块
	 */
	private LaborInfoFrontService laborInfoFrontService;
	private MemberInfoCenterService memberInfoCenterService;
	private LaborReplyInfoFrontService laborReplyInfoFrontService;
	private MemberLLJLCenterService memberLLJLCenterService;
	private LaborInfo laborInfo;
	private MemberInfo memberInfo;
	private MemberInfo rmemberInfo;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";
	// 内容页，参数该活动的人员信息
	private List laborreplylist; // 活动回复信息
	private int cid = 0;
	private String rids = ""; // 群发报名会员id串

	/**
	 * 报名活动
	 * 
	 * @return
	 */
	public String addToSignupAJAX() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (cid > 0) {
			laborInfo = laborInfoFrontService.getLaborInfo(cid);
			if (laborInfo != null
					&& laborInfo.getState().intValue() == 1
					&& laborInfo.getMid().intValue() != memberInfo.getId()
							.intValue() && laborInfo.getIsend() == 1) {
				int rcount = laborReplyInfoFrontService
						.findLaborReplyInfoCount(cid, memberInfo.getId()
								.intValue());
				if (rcount == 0) {
					LaborReplyInfo laborReplyInfo = new LaborReplyInfo();
					laborReplyInfo.setMid(memberInfo.getId());
					laborReplyInfo.setCid(cid);
					laborReplyInfo.setContent("");
					laborReplyInfo.setSort(new Integer(0));
					laborReplyInfo.setIsdel(new Integer(0));
					laborReplyInfo.setCtime(new Date());
					laborReplyInfoFrontService
							.saveLaborReplyInfo(laborReplyInfo);
					responseHTMLAjax("1");
				} else {
					responseHTMLAjax("2");
				}
			} else {
				responseHTMLAjax("0");
			}

		}

		return null;
	}

	/**
	 * 会员活动删除功能
	 * 
	 * @return
	 */
	public String delete() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				laborInfo = laborInfoFrontService.getLaborInfo(new Integer(
						idarrs[i].trim()));
				laborInfo.setIsdel(new Integer(1));
				laborInfoFrontService.updateLaborInfo(laborInfo);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员活动添加功能
	 * 
	 * @return
	 */
	public String save() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String msg = "修改成功！";
		if (laborInfo.getId() == null) {
			msg = "添加成功！";
			laborInfo.setIsdel(new Integer(0));
			laborInfo.setMid(memberInfo.getId());
			laborInfo.setCtime(new Date());
			laborInfo.setSort(new Integer(0));
			laborInfo.setViewnumber(new Integer(0));
			laborInfoFrontService.saveLaborInfo(laborInfo);
		}
		laborInfoFrontService.updateLaborInfo(laborInfo);
		session.put("saveinfomsg", msg);
		return SUCCESS;
	}

	/**
	 * 会员活动查看功能
	 * 
	 * @return
	 */
	public String info() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			laborInfo = laborInfoFrontService.getLaborInfo(id);
		}
		// 初始化信息
		if (laborInfo == null) {
			laborInfo = new LaborInfo();
			laborInfo.setIsdel(new Integer(0));
			laborInfo.setCid(new Integer(0));
			laborInfo.setScore(new Integer(0));
			laborInfo.setState(new Integer(0));
		}
		return SUCCESS;
	}

	/**
	 * 会员活动查看功能
	 * 
	 * @return
	 */
	public String showinfo() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			laborInfo = laborInfoFrontService.getLaborInfo(id);
			laborInfo.setReplycount(laborReplyInfoFrontService
					.findLaborReplyInfoCount(laborInfo.getId()));
			laborInfo.setMemberInfo(memberInfoCenterService
					.getMemberInfo(laborInfo.getMid()));
			laborreplylist = getnewRlist(laborReplyInfoFrontService
					.findLaborReplyInfoInList(id));

			// 更新访问量
			if (laborInfo.getMid().intValue() != memberInfo.getId().intValue()) {
				laborInfo.setViewnumber(laborInfo.getViewnumber() == null ? 0
						: laborInfo.getViewnumber() + 1);
			}
			laborInfoFrontService.updateLaborInfo(laborInfo);
			// 添加浏览记录
			memberLLJLCenterService.saveMemberLLJL(memberInfo.getId(), "[活动]"
					+ laborInfo.getTitle(), "/membercenter/showlaborinfo?id="
					+ laborInfo.getId());
		}
		return SUCCESS;
	}

	/**
	 * 会员活动列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		rmemberInfo = memberInfo;
		String hql = " from LaborInfo where isdel=0 and mid ="
				+ memberInfo.getId() + "";// 企业会员，我的活动，就是企业发布的活动信息。
		if (memberInfo.getType() == 0) { // 普通会员，我的活动，就是参加了某个活动，然后显示的活动。
			String lids = laborReplyInfoFrontService
					.findLaborReplyInfos(memberInfo.getId());
			if (lids.length() > 0) {
				hql = " from LaborInfo where isdel=0 and id in (" + lids + ")";
			}
		}
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

		// 判断排序条件
		pageBean = laborInfoFrontService.findLaborInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/membercenter/laborlist");
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
	public List getnewRlist(List pagelist) {
		List newlist = new ArrayList();
		if (pagelist != null && pagelist.size() > 0) {
			for (int i = 0; i < pagelist.size(); i++) {
				LaborReplyInfo cinfo = (LaborReplyInfo) pagelist.get(i);
				MemberInfo mi = memberInfoCenterService.getMemberInfo(cinfo
						.getMid());
				cinfo.setMemberInfo(mi);
				rids += mi.getId() + ",";
				newlist.add(cinfo);
			}
		}
		if (rids.length() > 1) {
			rids = rids.substring(0, rids.length() - 1);
		}
		return newlist;
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
				LaborInfo cinfo = (LaborInfo) pagelist.get(i);
				cinfo.setReplycount(laborReplyInfoFrontService
						.findLaborReplyInfoCount(cinfo.getId()));
				if(cinfo.getMid() != null){
					cinfo.setMemberInfo(memberInfoCenterService.getMemberInfo(cinfo
							.getMid()));
				}
				newlist.add(cinfo);
			}
		}
		return newlist;
	}

	public LaborInfoFrontService getLaborInfoFrontService() {
		return laborInfoFrontService;
	}

	public void setLaborInfoFrontService(
			LaborInfoFrontService laborInfoFrontService) {
		this.laborInfoFrontService = laborInfoFrontService;
	}

	public LaborInfo getLaborInfo() {
		return laborInfo;
	}

	public void setLaborInfo(LaborInfo laborInfo) {
		this.laborInfo = laborInfo;
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

	public LaborReplyInfoFrontService getLaborReplyInfoFrontService() {
		return laborReplyInfoFrontService;
	}

	public void setLaborReplyInfoFrontService(
			LaborReplyInfoFrontService laborReplyInfoFrontService) {
		this.laborReplyInfoFrontService = laborReplyInfoFrontService;
	}

	public List getLaborreplylist() {
		return laborreplylist;
	}

	public void setLaborreplylist(List laborreplylist) {
		this.laborreplylist = laborreplylist;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public MemberInfo getRmemberInfo() {
		return rmemberInfo;
	}

	public void setRmemberInfo(MemberInfo rmemberInfo) {
		this.rmemberInfo = rmemberInfo;
	}

	public String getRids() {
		return rids;
	}

	public void setRids(String rids) {
		this.rids = rids;
	}

	public MemberLLJLCenterService getMemberLLJLCenterService() {
		return memberLLJLCenterService;
	}

	public void setMemberLLJLCenterService(
			MemberLLJLCenterService memberLLJLCenterService) {
		this.memberLLJLCenterService = memberLLJLCenterService;
	}

}

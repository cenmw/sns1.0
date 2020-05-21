package com.cenmw.store.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.learn.manager.service.LearnInfoManagerService;
import com.cenmw.member.manager.service.MemberInfoManagerService;
import com.cenmw.member.po.MemberStore;
import com.cenmw.store.manager.service.MemberStoreManagerService;
import com.cenmw.topic.manager.service.TopicInfoManagerService;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberStoreManagerAction extends BaseAction {
	/**
	 * 会员充值交易记录 模块
	 */
	private MemberStoreManagerService memberStoreManagerService;
	private MemberInfoManagerService memberInfoManagerService;
	private TopicInfoManagerService topicInfoManagerService;
	private LearnInfoManagerService learnInfoManagerService;
	private MemberStore memberStore;
	private int id;
	private String ids;
	private String backUrl;

	// 搜索条件
	private String searchname = "";

	/**
	 * 会员充值交易记录删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberStore = memberStoreManagerService
						.getMemberStore(new Integer(idarrs[i].trim()));
				memberStore.setIsdel(new Integer(1));
				memberStoreManagerService.updateMemberStore(memberStore);
			}
			String msg = "删除成功";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员充值交易记录修改功能
	 * 
	 * @return
	 */
	public String info() {
		if (id > 0) {
			memberStore = memberStoreManagerService.getMemberStore(id);
		}
		return SUCCESS;
	}

	/**
	 * 会员列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		String hql = " from MemberStore where isdel=0 ";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = " ctime desc ";
		if (searchname != null && searchname.length() > 0) {
			hql += " and code like '%" + searchname + "%'";
			HqlBean hqlBean = new HqlBean(searchname, "=", "and", "String");
			hqlBean.setIshql(1);
			map.put("searchname", hqlBean);
			try {
				parameter += "&searchname=" + StringUtil.URLEncode(searchname);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		pageSize = 20;
		// 判断排序条件
		pageBean = memberStoreManagerService.findMemberStoreHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/manager/store/list");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/manager/gopage/gopage.html"));
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
				MemberStore mi = (MemberStore) pagelist.get(i);
				if (mi.getType() == 1) { // 学习消费
					int tid = mi.getTid() == null ? 0 : mi.getTid();
					if (tid > 0) {
						mi.setLearnInfo(learnInfoManagerService
								.getLearnInfo(tid));
					}
				} else if (mi.getType() == 2) { // 测试试卷消费
					int tid = mi.getTid() == null ? 0 : mi.getTid();
					if (tid > 0) {
						mi.setTopicInfo(topicInfoManagerService
								.getTopicInfo(tid));
					}
				} else if (mi.getType() == 3) { // 52周 为家庭消费
					mi.setTitle("52周：为家庭消费");
				} else if (mi.getType() == 3) { // 52周 为自己消费
					mi.setTitle("52周：为自己消费");
				}
				int mid = mi.getMid() == null ? 0 : mi.getMid();
				if (mid > 0) {
					mi.setMemberInfo(memberInfoManagerService
							.getMemberInfo(mid));
				}
				newlist.add(mi);
			}
		}
		return newlist;
	}

	public MemberStoreManagerService getMemberStoreManagerService() {
		return memberStoreManagerService;
	}

	public void setMemberStoreManagerService(
			MemberStoreManagerService memberStoreManagerService) {
		this.memberStoreManagerService = memberStoreManagerService;
	}

	public MemberStore getMemberStore() {
		return memberStore;
	}

	public void setMemberStore(MemberStore memberStore) {
		this.memberStore = memberStore;
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

	public MemberInfoManagerService getMemberInfoManagerService() {
		return memberInfoManagerService;
	}

	public void setMemberInfoManagerService(
			MemberInfoManagerService memberInfoManagerService) {
		this.memberInfoManagerService = memberInfoManagerService;
	}

	public TopicInfoManagerService getTopicInfoManagerService() {
		return topicInfoManagerService;
	}

	public void setTopicInfoManagerService(
			TopicInfoManagerService topicInfoManagerService) {
		this.topicInfoManagerService = topicInfoManagerService;
	}

	public String getSearchname() {
		return searchname;
	}

	public void setSearchname(String searchname) {
		this.searchname = searchname;
	}

	public LearnInfoManagerService getLearnInfoManagerService() {
		return learnInfoManagerService;
	}

	public void setLearnInfoManagerService(
			LearnInfoManagerService learnInfoManagerService) {
		this.learnInfoManagerService = learnInfoManagerService;
	}

}

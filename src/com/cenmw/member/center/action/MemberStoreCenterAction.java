package com.cenmw.member.center.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.learn.front.service.LearnInfoFrontService;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.center.service.MemberStoreCenterService;
import com.cenmw.member.po.MemberStore;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.topic.front.service.TopicInfoFrontService;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.DateUtil;

public class MemberStoreCenterAction extends BaseAction {
	/**
	 * 会员充值交易记录 模块
	 */
	private MemberStoreCenterService memberStoreCenterService;
	private MemberInfoCenterService memberInfoCenterService;
	private TopicInfoFrontService topicInfoFrontService;
	private LearnInfoFrontService learnInfoFrontService;
	
	private MemberStore memberStore;
	private MemberInfo memberInfo;
	private int id;
	private String ids;
	private String backUrl;

	/**
	 * 会员充值交易记录删除功能
	 * 
	 * @return
	 */
	public String delete() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberStore = memberStoreCenterService
						.getMemberStore(new Integer(idarrs[i].trim()));
				memberStore.setIsdel(new Integer(1));
				memberStoreCenterService.updateMemberStore(memberStore);
			}
			String msg = "删除成功";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员充值交易记录添加功能
	 * 
	 * @return
	 */
	public String save() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (memberStore.getId() == null) {
			memberStore.setMid(memberInfo.getId());
			memberStore.setAccount(memberInfo.getAccount());
			memberStore.setEmail(memberInfo.getEmail());
			memberStore.setIsdel(new Integer(0));
			memberStore.setCtime(new Date());
			memberStore.setState(new Integer(0));
			memberStore.setType(new Integer(0)); // 充值
			memberStore.setTid(new Integer(0)); // 充值时，对应的消费测试id为0
			String c_date = DateUtil.getFormatDate(new Date(), "yyyyMMdd");
			String partner = "2088002325980432"; // 支付宝合作商id
			long code_10 = System.currentTimeMillis(); // 10为一个随机码
			memberStore.setCode(c_date + "-" + partner + "-" + code_10);
			memberStoreCenterService.saveMemberStore(memberStore);
		}
		memberStoreCenterService.updateMemberStore(memberStore);
		return SUCCESS;
	}

	/**
	 * 会员充值交易记录修改功能
	 * 
	 * @return
	 */
	public String info() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			memberStore = memberStoreCenterService.getMemberStore(id);
		}
		// 初始化信息
		if (memberStore == null) {
			memberStore = new MemberStore();
			memberStore.setPrice(0.0);
		}
		return SUCCESS;
	}

	/**
	 * 会员充值交易记录查看功能
	 * 
	 * @return
	 */
	public String showinfo() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			memberStore = memberStoreCenterService.getMemberStore(id);
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
		String hql = " from MemberStore where isdel=0 and mid="
				+ memberInfo.getId();
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = " ctime desc ";

		// 判断排序条件
		pageBean = memberStoreCenterService.findMemberStoreHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/storelist");
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
				MemberStore mi = (MemberStore) pagelist.get(i);
				if (mi.getType() == 1) { // 学习消费
					int tid = mi.getTid() == null ? 0 : mi.getTid();
					if (tid > 0) {
						mi.setLearnInfo(learnInfoFrontService
								.getLearnInfo(tid));
					}
				} else if (mi.getType() == 2) { // 测试试卷消费
					int tid = mi.getTid() == null ? 0 : mi.getTid();
					if (tid > 0) {
						mi.setTopicInfo(topicInfoFrontService
								.getTopicInfo(tid));
					}
				} else if (mi.getType() == 3) { // 52周 为家庭消费
					mi.setTitle("52周：为家庭消费");
				} else if (mi.getType() == 3) { // 52周 为自己消费
					mi.setTitle("52周：为自己消费");
				}
				int mid = mi.getMid() == null ? 0 : mi.getMid();
				if (mid > 0) {
					mi.setMemberInfo(memberInfoCenterService
							.getMemberInfo(mid));
				}
				newlist.add(mi);
			}
		}
		return newlist;
	}

	public MemberStoreCenterService getMemberStoreCenterService() {
		return memberStoreCenterService;
	}

	public void setMemberStoreCenterService(
			MemberStoreCenterService memberStoreCenterService) {
		this.memberStoreCenterService = memberStoreCenterService;
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

	public MemberInfoCenterService getMemberInfoCenterService() {
		return memberInfoCenterService;
	}

	public void setMemberInfoCenterService(
			MemberInfoCenterService memberInfoCenterService) {
		this.memberInfoCenterService = memberInfoCenterService;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public TopicInfoFrontService getTopicInfoFrontService() {
		return topicInfoFrontService;
	}

	public void setTopicInfoFrontService(TopicInfoFrontService topicInfoFrontService) {
		this.topicInfoFrontService = topicInfoFrontService;
	}

	public LearnInfoFrontService getLearnInfoFrontService() {
		return learnInfoFrontService;
	}

	public void setLearnInfoFrontService(LearnInfoFrontService learnInfoFrontService) {
		this.learnInfoFrontService = learnInfoFrontService;
	}

}

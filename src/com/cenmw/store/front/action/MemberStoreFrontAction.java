package com.cenmw.store.front.action;

import com.cenmw.base.BaseAction;
import com.cenmw.member.front.service.MemberInfoFrontService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.member.po.MemberStore;
import com.cenmw.store.front.service.MemberStoreFrontService;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.StringUtil;

public class MemberStoreFrontAction extends BaseAction {
	/**
	 * 会员充值交易记录 模块
	 */
	private MemberStoreFrontService memberStoreFrontService;
	private MemberInfoFrontService memberInfoFrontService;
	private Integer id = 0;
	private MemberStore memberStore;
	private MemberInfo memberInfo;
	private String out_trade_no; // 充值记录编码
	private String partner; // 合作商账号
	private String result;

	/**
	 * 会员充值交易 notify_url
	 * 
	 * @return
	 */
	public String paymentCheckAlipay() {
		if (!StringUtil.isEmpty(out_trade_no)) {
			memberStore = memberStoreFrontService.getMemberStore(out_trade_no);
			if (memberStore != null) {
				memberStore.setState(1);
				memberStoreFrontService.updateMemberStore(memberStore);
				id = memberStore.getId();
				int mid = memberStore.getMid();
				if (mid > 0) {
					memberInfo = memberInfoFrontService.getMemberInfoById(mid);
					session.put(ConstantUtils.MEMBERINFO, memberInfo);
				}
			}
		}
		return SUCCESS;
	}

	/**
	 * 会员充值交易 return_url
	 * 
	 * @return
	 */
	public String paymentResultAlipay() {
		result = "充值失败";
		if (id != null && id != 0) {
			memberStore = memberStoreFrontService.getMemberStore(id);
			if (memberStore != null) {
				if (memberStore.getState() == 1) {
					result = "充值成功";
				}
			}
		}
		return SUCCESS;
	}

	public MemberStoreFrontService getMemberStoreFrontService() {
		return memberStoreFrontService;
	}

	public void setMemberStoreFrontService(
			MemberStoreFrontService memberStoreFrontService) {
		this.memberStoreFrontService = memberStoreFrontService;
	}

	public MemberStore getMemberStore() {
		return memberStore;
	}

	public void setMemberStore(MemberStore memberStore) {
		this.memberStore = memberStore;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public MemberInfoFrontService getMemberInfoFrontService() {
		return memberInfoFrontService;
	}

	public void setMemberInfoFrontService(
			MemberInfoFrontService memberInfoFrontService) {
		this.memberInfoFrontService = memberInfoFrontService;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

}

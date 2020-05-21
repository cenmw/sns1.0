package com.cenmw.member.center.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.comment.front.service.CommentInfoFrontService;
import com.cenmw.learn.front.service.LearnClassFrontService;
import com.cenmw.learn.front.service.LearnInfoFrontService;
import com.cenmw.learn.front.service.LearnLogFrontService;
import com.cenmw.learn.front.service.LearnWhyFrontService;
import com.cenmw.learn.front.service.LearnWhyLogFrontService;
import com.cenmw.learn.po.LearnInfo;
import com.cenmw.learn.po.LearnLog;
import com.cenmw.learn.po.LearnWhyLog;
import com.cenmw.member.center.service.MemberLLJLCenterService;
import com.cenmw.member.center.service.MemberPraiseCenterService;
import com.cenmw.member.center.service.MemberStoreCenterService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.member.po.MemberStore;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.DateUtil;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberLearnCenterAction extends BaseAction {
	/**
	 * 学习中心 模块
	 */
	private LearnInfoFrontService learnInfoFrontService;
	private LearnClassFrontService learnClassFrontService;
	private MemberPraiseCenterService memberPraiseCenterService;
	private CommentInfoFrontService commentInfoFrontService;
	private LearnLogFrontService learnLogFrontService;
	private LearnWhyFrontService learnWhyFrontService;
	private LearnWhyLogFrontService learnWhyLogFrontService;
	private MemberStoreCenterService memberStoreCenterService;
	private MemberLLJLCenterService memberLLJLCenterService;
	private LearnInfo learnInfo;
	private LearnInfo nextlearnInfo;
	private LearnInfo lastlearnInfo;
	private LearnLog learnLog;
	private MemberInfo memberInfo;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";
	private int searchcid = 0;
	private List classlist; // 管理员自定义视频分类
	private Integer lid; // 学习id
	private String result; // 学习结果
	private List learnwhyloglist; // 原因列表

	/**
	 * 学习中心记录添加功能
	 * 
	 * @return
	 */
	public String learnlogsave() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (lid != null && lid.intValue() > 0) {
			learnInfo = learnInfoFrontService.getLearnInfo(lid);
			if (learnInfo != null) {
				int money = learnInfo.getCost();
				if (money > 0) {
					// 判断余额是否大于当前消费的金额，
					Double sumprice = memberStoreCenterService
							.getMemberStoreSumPrice(memberInfo.getId().intValue());
					if (sumprice < money) { // 余额 小于 后台设置的 金额 ，就让进入充值界面
						return "store";
					}
					// 添加扣除金额信息
					MemberStore memberStore = new MemberStore();
					memberStore.setMid(memberInfo.getId());
					memberStore.setAccount(memberInfo.getAccount());
					memberStore.setEmail(memberInfo.getEmail());
					memberStore.setIsdel(new Integer(0));
					memberStore.setCtime(new Date());
					memberStore.setState(new Integer(1));
					memberStore.setType(new Integer(1)); // 学习消费
					memberStore.setTid(new Integer(lid));
					memberStore.setPrice(new Double(-money));
					String c_date = DateUtil.getFormatDate(new Date(), "yyyyMMdd");
					long code_10 = System.currentTimeMillis(); // 10为一个随机码
					memberStore.setCode(c_date + "-" + code_10);
					memberStoreCenterService.saveMemberStore(memberStore);
					// 添加完成
				}
			
	
			String l_result = learnInfo.getResult() == null ? "" : learnInfo
					.getResult();
			String l_whyids = learnInfo.getWhyids() == null ? "" : learnInfo
					.getWhyids();
			String[] l_result_all = l_result.split("\\|\\|");
			String[] l_whyids_all = l_whyids.split("\\|\\|");
			String[] result_all = result.split("\\|\\|");

			if (l_result_all.length == result_all.length) {
				int cnumber = 0; // 正确个数
				int enumber = 0; // 错误个数
				int correct = 0; // 正确率
				for (int i = 0; i < l_result_all.length; i++) {
					if (l_result_all[i].equals(result_all[i])) {
						cnumber++;
					} else { // 错误
						enumber++;

					}
				}
				int llid = 0;
				if (cnumber > 0 || enumber > 0) {
					correct = cnumber * 100 / (cnumber + enumber);
					learnLog = new LearnLog();
					learnLog.setMid(memberInfo.getId());
					learnLog.setLid(lid);
					learnLog.setResult(result);
					learnLog.setCnumber(cnumber);
					learnLog.setEnumber(enumber);
					learnLog.setCorrect(correct);
					learnLog.setIsdel(new Integer(0));
					learnLog.setCtime(new Date());
					learnLogFrontService.saveLearnLog(learnLog);
					llid = learnLog.getId().intValue();
				}
				if (l_result_all.length == l_whyids_all.length && llid > 0) {
					// 记录错误原因id,并且次数
					LearnWhyLog learnWhyLog = null;
					for (int i = 0; i < l_whyids_all.length; i++) {
						if (!l_result_all[i].equals(result_all[i])) {
							learnWhyLog = learnWhyLogFrontService
									.getLearnWhyLog(llid, new Integer(
											l_whyids_all[i]));
							if (learnWhyLog != null) {
								learnWhyLog
										.setNumber(learnWhyLog.getNumber() + 1);
								learnWhyLogFrontService
										.updateLearnWhyLog(learnWhyLog);
							} else {
								learnWhyLog = new LearnWhyLog();
								learnWhyLog.setLlid(llid);
								learnWhyLog.setWhyid(new Integer(
										l_whyids_all[i]));
								learnWhyLog.setNumber(1);
								learnWhyLogFrontService
										.saveLearnWhyLog(learnWhyLog);
							}
						}
					}
				}
			}
		}
		}
		return SUCCESS;
	}

	/**
	 * 学习记录查看功能
	 * 
	 * @return
	 */
	public String learnlogsuccess() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			learnLog = learnLogFrontService.getLearnLog(id);
			learnInfo = learnInfoFrontService.getLearnInfo(learnLog.getLid());
			nextlearnInfo = learnInfoFrontService.findNextLearnInfo(learnInfo
					.getNextcode() == null ? 0 : learnInfo.getNextcode());
			lastlearnInfo = learnInfoFrontService.findNextLearnInfo(learnInfo
					.getLastcode() == null ? 0 : learnInfo.getLastcode());
			// 出现最多次数的原因
			List list = learnWhyLogFrontService.findLearnWhyLogInList(id, 3);
			learnwhyloglist = new ArrayList();
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					LearnWhyLog learnwhylog = (LearnWhyLog) list.get(i);
					learnwhylog.setLearnWhy(learnWhyFrontService
							.getLearnWhy(learnwhylog.getWhyid()));
					learnwhyloglist.add(learnwhylog);
				}
			}
		}
		return SUCCESS;
	}

	/**
	 * 学习中心查看功能
	 * 
	 * @return
	 */
	public String showinfo() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			learnInfo = learnInfoFrontService.getLearnInfo(id);
			if (learnInfo != null) {
				int money = learnInfo.getCost();
				if (money > 0) {
					// 判断余额是否大于当前消费的金额
					Double sumprice = memberStoreCenterService
							.getMemberStoreSumPrice(memberInfo.getId().intValue());
					if (sumprice < money) { // 判断余额 小于 当前消费的金额，，就让进入充值界面
						return "store";
					}
				}
			}
			learnInfo.setPraisenumber(memberPraiseCenterService
					.getMemberPraiseInListNumber(6, learnInfo.getId()
							.intValue()));
			learnInfo.setRcnumber(learnInfoFrontService
					.getMemberLearnInListNumber(learnInfo.getId().intValue()));
			learnInfo.setCommentnumber(commentInfoFrontService
					.getCommentNumber(learnInfo.getId().intValue(), 6));
			learnInfo.setMemberPraise(memberPraiseCenterService
					.findMemberPraiseAll(memberInfo.getId().intValue(), 6,
							learnInfo.getId().intValue()));
			// 更新访问量
			learnInfo.setViewnumber(learnInfo.getViewnumber() == null ? 0
					: learnInfo.getViewnumber() + 1);
			learnInfoFrontService.updateLearnInfo(learnInfo);
			// 添加浏览记录
			memberLLJLCenterService.saveMemberLLJL(memberInfo.getId(), "[学习]"
					+ learnInfo.getTitle(), "/membercenter/showlearninfo?id="
					+ learnInfo.getId());
		}

		return SUCCESS;
	}

	/**
	 * 学习中心列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		classlist = learnClassFrontService.findLearnClassInList();
		String hql = " from LearnInfo where isdel=0 and state=1 ";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " code asc ";

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

		if (searchcid > 0) {
			hql += " and cid =" + searchcid;
			HqlBean hqlBean = new HqlBean(searchcid, "=", "and", "Integer");
			hqlBean.setIshql(1);
			map.put("searchcid", hqlBean);
			parameter += "&searchcid=" + searchcid;
		}

		// 判断排序条件
		pageBean = learnInfoFrontService.findLearnInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/membercenter/learnlist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 学习中心分类列表查看功能
	 * 
	 * @return
	 */
	public String classlist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String hql = " from LearnClass where isdel=0 ";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " sort desc , id desc ";

		// 判断排序条件
		pageBean = learnClassFrontService.findLearnClassHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/membercenter/learnclasslist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(pageBean.getPageList());
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
				LearnInfo mi = (LearnInfo) pagelist.get(i);
				mi.setClassname(learnClassFrontService.getLearnClass(
						mi.getCid()).getTitle());
				mi.setPraisenumber(memberPraiseCenterService
						.getMemberPraiseInListNumber(6, mi.getId().intValue()));
				mi.setRcnumber(learnInfoFrontService
						.getMemberLearnInListNumber(mi.getId().intValue()));
				mi.setCommentnumber(commentInfoFrontService.getCommentNumber(mi
						.getId().intValue(), 6));
				mi.setMemberPraise(memberPraiseCenterService
						.findMemberPraiseAll(memberInfo.getId().intValue(), 6,
								mi.getId().intValue()));
				mi.setCountsum(learnLogFrontService
						.getMemberLearnLogListNumber(mi.getId().intValue()));
				mi
						.setMaxcorrect(learnLogFrontService
								.getMemberLearnLogListMaxcorrect(mi.getId()
										.intValue()));
				mi.setM_countsum(learnLogFrontService
						.getMemberLearnLogListNumberMid(memberInfo.getId()
								.intValue(), mi.getId().intValue()));
				mi.setM_maxcorrect(learnLogFrontService
						.getMemberLearnLogListMaxcorrectMid(memberInfo.getId()
								.intValue(), mi.getId().intValue()));
				newlist.add(mi);
			}
		}
		return newlist;
	}

	public LearnInfoFrontService getLearnInfoFrontService() {
		return learnInfoFrontService;
	}

	public void setLearnInfoFrontService(
			LearnInfoFrontService learnInfoFrontService) {
		this.learnInfoFrontService = learnInfoFrontService;
	}

	public LearnInfo getLearnInfo() {
		return learnInfo;
	}

	public void setLearnInfo(LearnInfo learnInfo) {
		this.learnInfo = learnInfo;
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

	public LearnClassFrontService getLearnClassFrontService() {
		return learnClassFrontService;
	}

	public void setLearnClassFrontService(
			LearnClassFrontService learnClassFrontService) {
		this.learnClassFrontService = learnClassFrontService;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	public int getSearchcid() {
		return searchcid;
	}

	public void setSearchcid(int searchcid) {
		this.searchcid = searchcid;
	}

	public List getClasslist() {
		return classlist;
	}

	public void setClasslist(List classlist) {
		this.classlist = classlist;
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

	public LearnLogFrontService getLearnLogFrontService() {
		return learnLogFrontService;
	}

	public void setLearnLogFrontService(
			LearnLogFrontService learnLogFrontService) {
		this.learnLogFrontService = learnLogFrontService;
	}

	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public LearnLog getLearnLog() {
		return learnLog;
	}

	public void setLearnLog(LearnLog learnLog) {
		this.learnLog = learnLog;
	}

	public LearnInfo getNextlearnInfo() {
		return nextlearnInfo;
	}

	public void setNextlearnInfo(LearnInfo nextlearnInfo) {
		this.nextlearnInfo = nextlearnInfo;
	}

	public LearnInfo getLastlearnInfo() {
		return lastlearnInfo;
	}

	public void setLastlearnInfo(LearnInfo lastlearnInfo) {
		this.lastlearnInfo = lastlearnInfo;
	}

	public LearnWhyLogFrontService getLearnWhyLogFrontService() {
		return learnWhyLogFrontService;
	}

	public void setLearnWhyLogFrontService(
			LearnWhyLogFrontService learnWhyLogFrontService) {
		this.learnWhyLogFrontService = learnWhyLogFrontService;
	}

	public List getLearnwhyloglist() {
		return learnwhyloglist;
	}

	public void setLearnwhyloglist(List learnwhyloglist) {
		this.learnwhyloglist = learnwhyloglist;
	}

	public LearnWhyFrontService getLearnWhyFrontService() {
		return learnWhyFrontService;
	}

	public void setLearnWhyFrontService(
			LearnWhyFrontService learnWhyFrontService) {
		this.learnWhyFrontService = learnWhyFrontService;
	}

	public MemberStoreCenterService getMemberStoreCenterService() {
		return memberStoreCenterService;
	}

	public void setMemberStoreCenterService(
			MemberStoreCenterService memberStoreCenterService) {
		this.memberStoreCenterService = memberStoreCenterService;
	}

	public MemberLLJLCenterService getMemberLLJLCenterService() {
		return memberLLJLCenterService;
	}

	public void setMemberLLJLCenterService(
			MemberLLJLCenterService memberLLJLCenterService) {
		this.memberLLJLCenterService = memberLLJLCenterService;
	}

}

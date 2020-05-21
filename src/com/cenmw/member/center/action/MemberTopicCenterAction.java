package com.cenmw.member.center.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.comment.front.service.CommentInfoFrontService;
import com.cenmw.topic.front.service.TopicClassFrontService;
import com.cenmw.topic.front.service.TopicInfoFrontService;
import com.cenmw.topic.front.service.TopicLearnClassLogFrontService;
import com.cenmw.topic.front.service.TopicLifeAdviceFrontService;
import com.cenmw.topic.front.service.TopicLifeAdviceLogFrontService;
import com.cenmw.topic.front.service.TopicLogFrontService;
import com.cenmw.topic.front.service.TopicWhyLogFrontService;
import com.cenmw.topic.po.TopicInfo;
import com.cenmw.topic.po.TopicLearnClassLog;
import com.cenmw.topic.po.TopicLifeAdviceLog;
import com.cenmw.topic.po.TopicLog;
import com.cenmw.topic.po.TopicWhyLog;
import com.cenmw.learn.front.service.LearnClassFrontService;
import com.cenmw.learn.front.service.LearnWhyFrontService;
import com.cenmw.member.center.service.MemberLLJLCenterService;
import com.cenmw.member.center.service.MemberStoreCenterService;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.member.po.MemberStore;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.DateUtil;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;

public class MemberTopicCenterAction extends BaseAction {
	/**
	 * 测试中心 模块
	 */
	private TopicInfoFrontService topicInfoFrontService;
	private TopicClassFrontService topicClassFrontService;
	private CommentInfoFrontService commentInfoFrontService;
	private TopicLogFrontService topicLogFrontService;
	private LearnWhyFrontService learnWhyFrontService;
	private TopicWhyLogFrontService topicWhyLogFrontService;
	private TopicLifeAdviceFrontService topicLifeAdviceFrontService;
	private TopicLifeAdviceLogFrontService topicLifeAdviceLogFrontService;
	private LearnClassFrontService learnClassFrontService;
	private TopicLearnClassLogFrontService topicLearnClassLogFrontService;
	private MemberStoreCenterService memberStoreCenterService;
	private MemberLLJLCenterService memberLLJLCenterService;
	private TopicInfo topicInfo;
	private MemberInfo memberInfo;
	private TopicLog topicLog;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";
	private int searchcid = 0;
	private List classlist; // 管理员自定义视频分类
	private Integer tid; // 测试id
	private String result; // 学习结果
	private List topicwhyloglist; // 原因列表
	private List topiclearnclassloglist; // 推荐课程列表
	private List topiclifeadviceloglist; // 生活建议列表

	/**
	 * 测试中心删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				topicInfo = topicInfoFrontService.getTopicInfo(new Integer(
						idarrs[i].trim()));
				topicInfo.setIsdel(new Integer(1));
				topicInfoFrontService.updateTopicInfo(topicInfo);
			}
			String msg = "删除成功";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 测试中心添加功能
	 * 
	 * @return
	 */
	public String topiclogsave() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (tid != null && tid.intValue() > 0) {
			topicInfo = topicInfoFrontService.getTopicInfo(tid);
			if (topicInfo != null) {
			int money = topicInfo.getCost();
			if (money > 0) {
				// 判断余额是否大于当前消费的金额
				Double sumprice = memberStoreCenterService
						.getMemberStoreSumPrice(memberInfo.getId().intValue());
				if (sumprice < money) { // 判断余额 小于 当前消费的金额，就让进入充值界面
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
				memberStore.setType(new Integer(2)); // 测试试卷消费
				memberStore.setTid(new Integer(tid));
				memberStore.setPrice(new Double(-money));
				String c_date = DateUtil.getFormatDate(new Date(), "yyyyMMdd");
				long code_10 = System.currentTimeMillis(); // 10为一个随机码
				memberStore.setCode(c_date + "-" + code_10);
				memberStoreCenterService.saveMemberStore(memberStore);
				// 添加完成
			}
		
			String l_result = topicInfo.getResult() == null ? "" : topicInfo
					.getResult();
			String l_whyids = topicInfo.getWhyids() == null ? "" : topicInfo
					.getWhyids();
			String l_ttais = topicInfo.getTtaids() == null ? "" : topicInfo
					.getTtaids();
			String l_cids = topicInfo.getLcids() == null ? "" : topicInfo
					.getLcids();
			String[] l_result_all = l_result.split("\\|\\|");
			String[] l_whyids_all = l_whyids.split("\\|\\|");
			String[] l_ttais_all = l_ttais.split("\\|\\|");
			String[] l_cids_all = l_cids.split("\\|\\|");
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
				int tlid = 0;
				if (cnumber > 0 || enumber > 0) {
					correct = cnumber * 100 / (cnumber + enumber);
					topicLog = new TopicLog();
					topicLog.setMid(memberInfo.getId());
					topicLog.setTid(tid);
					topicLog.setResult(result);
					topicLog.setCnumber(cnumber);
					topicLog.setEnumber(enumber);
					topicLog.setCorrect(correct);
					topicLog.setIsdel(new Integer(0));
					topicLog.setCtime(new Date());
					topicLogFrontService.saveTopicLog(topicLog);
					tlid = topicLog.getId().intValue();
				}
				// 分析数据入库 原因，推荐课程，生活建议
				if (l_result_all.length == l_whyids_all.length && tlid > 0) {
					// 记录错误原因id,并且次数
					TopicWhyLog topicWhyLog = null;
					// 记录生活建议，并且次数
					TopicLifeAdviceLog topicLifeAdviceLog = null;
					// 记录课程建议，并且次数
					TopicLearnClassLog topicLearnClassLog = null;

					//
					for (int i = 0; i < l_whyids_all.length; i++) {
						if (!l_result_all[i].equals(result_all[i])) {
							// 记录原因信息入库
							topicWhyLog = topicWhyLogFrontService
									.getTopicWhyLog(tlid, new Integer(
											l_whyids_all[i]));
							if (topicWhyLog != null) {
								topicWhyLog
										.setNumber(topicWhyLog.getNumber() + 1);
								topicWhyLogFrontService
										.updateTopicWhyLog(topicWhyLog);
							} else {
								topicWhyLog = new TopicWhyLog();
								topicWhyLog.setTlid(tlid);
								topicWhyLog.setWhyid(new Integer(
										l_whyids_all[i]));
								topicWhyLog.setNumber(1);
								topicWhyLogFrontService
										.saveTopicWhyLog(topicWhyLog);
							}
							// 记录生活建议入库
							topicLifeAdviceLog = topicLifeAdviceLogFrontService
									.getTopicLifeAdviceLog(tlid, new Integer(
											l_ttais_all[i]));
							if (topicLifeAdviceLog != null) {
								topicLifeAdviceLog.setNumber(topicLifeAdviceLog
										.getNumber() + 1);
								topicLifeAdviceLogFrontService
										.updateTopicLifeAdviceLog(topicLifeAdviceLog);
							} else {
								topicLifeAdviceLog = new TopicLifeAdviceLog();
								topicLifeAdviceLog.setTlid(tlid);
								topicLifeAdviceLog.setTtaid(new Integer(
										l_ttais_all[i]));
								topicLifeAdviceLog.setNumber(1);
								topicLifeAdviceLogFrontService
										.saveTopicLifeAdviceLog(topicLifeAdviceLog);
							}
							// 记录课程建议入库
							topicLearnClassLog = topicLearnClassLogFrontService
									.getTopicLearnClassLog(tlid, new Integer(
											l_cids_all[i]));
							if (topicLearnClassLog != null) {
								topicLearnClassLog.setNumber(topicLearnClassLog
										.getNumber() + 1);
								topicLearnClassLogFrontService
										.updateTopicLearnClassLog(topicLearnClassLog);
							} else {
								topicLearnClassLog = new TopicLearnClassLog();
								topicLearnClassLog.setTlid(tlid);
								topicLearnClassLog.setLcid(new Integer(
										l_cids_all[i]));
								topicLearnClassLog.setNumber(1);
								topicLearnClassLogFrontService
										.saveTopicLearnClassLog(topicLearnClassLog);
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
	 * 测试记录查看功能
	 * 
	 * @return
	 */
	public String topiclogsuccess() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			topicLog = topicLogFrontService.getTopicLog(id);
			topicInfo = topicInfoFrontService.getTopicInfo(topicLog.getTid());
			// 出现最多次数的原因
			List list = topicWhyLogFrontService.findTopicWhyLogInList(id, 3);
			topicwhyloglist = new ArrayList();
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					TopicWhyLog topicwhylog = (TopicWhyLog) list.get(i);
					topicwhylog.setLearnWhy(learnWhyFrontService
							.getLearnWhy(topicwhylog.getWhyid()));
					topicwhyloglist.add(topicwhylog);
				}
			}
			// 出现最多次数的课程推荐
			List list_learnclass = topicLearnClassLogFrontService
					.findTopicLearnClassLogInList(id, 3);
			topiclearnclassloglist = new ArrayList();
			if (list_learnclass != null && !list_learnclass.isEmpty()) {
				for (int i = 0; i < list_learnclass.size(); i++) {
					TopicLearnClassLog topiclclog = (TopicLearnClassLog) list_learnclass
							.get(i);
					topiclclog.setLearnClass(learnClassFrontService
							.getLearnClass(topiclclog.getLcid()));
					topiclearnclassloglist.add(topiclclog);
				}
			}
			// 出现最多次数的生活建议
			List list_lifeadvice = topicLifeAdviceLogFrontService
					.findTopicLifeAdviceLogInList(id, 3);
			topiclifeadviceloglist = new ArrayList();
			if (list_lifeadvice != null && !list_lifeadvice.isEmpty()) {
				for (int i = 0; i < list_lifeadvice.size(); i++) {
					TopicLifeAdviceLog topiclalog = (TopicLifeAdviceLog) list_lifeadvice
							.get(i);
					topiclalog.setTopicLifeAdvice(topicLifeAdviceFrontService
							.getTopicLifeAdvice(topiclalog.getTtaid()));
					topiclifeadviceloglist.add(topiclalog);
				}
			}
		}
		return SUCCESS;
	}

	/**
	 * 测试中心查看功能
	 * 
	 * @return
	 */
	public String showinfo() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		// 判断金额是否大于 1，
		Double sumprice = memberStoreCenterService
				.getMemberStoreSumPrice(memberInfo.getId().intValue());
		if (sumprice < 1) { // 小于 1元，就让进入充值界面
			return "store";
		}
		if (id > 0) {
			topicInfo = topicInfoFrontService.getTopicInfo(id);
			topicInfo.setCommentnumber(commentInfoFrontService
					.getCommentNumber(topicInfo.getId().intValue(), 6));
			// 更新访问量
			topicInfo.setViewnumber(topicInfo.getViewnumber() == null ? 0
					: topicInfo.getViewnumber() + 1);
			topicInfoFrontService.updateTopicInfo(topicInfo);
			// 添加浏览记录
			memberLLJLCenterService.saveMemberLLJL(memberInfo.getId(), "[测试]"
					+ topicInfo.getTitle(), "/membercenter/showtopicinfo?id="
					+ topicInfo.getId());
		}

		return SUCCESS;
	}

	/**
	 * 测试中心列表查看功能
	 * 
	 * @return
	 */
	public String list() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		classlist = topicClassFrontService.findTopicClassInList();
		String hql = " from TopicInfo where isdel=0 and state=1 ";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " sort desc , ctime desc , id desc ";

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
		pageBean = topicInfoFrontService.findTopicInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/membercenter/topiclist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 测试中心分类列表查看功能
	 * 
	 * @return
	 */
	public String classlist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String hql = " from TopicClass where isdel=0 ";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id asc ";

		// 判断排序条件
		pageBean = topicClassFrontService.findTopicClassHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/membercenter/topicclasslist");
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
				TopicInfo mi = (TopicInfo) pagelist.get(i);
				mi.setClassname(topicClassFrontService.getTopicClass(
						mi.getCid()).getTitle());
				mi.setCommentnumber(commentInfoFrontService.getCommentNumber(mi
						.getId().intValue(), 7));
				mi.setCountsum(topicLogFrontService
						.getMemberTopicLogListNumber(mi.getId().intValue()));
				mi.setMaxcorrect(topicLogFrontService
						.getMemberTopicLogListMaxcorrect(mi.getId().intValue()));
				mi.setM_countsum(topicLogFrontService
						.getMemberTopicLogListNumberMid(memberInfo.getId()
								.intValue(), mi.getId().intValue()));
				mi.setM_maxcorrect(topicLogFrontService
						.getMemberTopicLogListMaxcorrectMid(memberInfo.getId()
								.intValue(), mi.getId().intValue()));
				newlist.add(mi);
			}
		}
		return newlist;
	}

	public TopicInfoFrontService getTopicInfoFrontService() {
		return topicInfoFrontService;
	}

	public void setTopicInfoFrontService(
			TopicInfoFrontService topicInfoFrontService) {
		this.topicInfoFrontService = topicInfoFrontService;
	}

	public TopicInfo getTopicInfo() {
		return topicInfo;
	}

	public void setTopicInfo(TopicInfo topicInfo) {
		this.topicInfo = topicInfo;
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

	public TopicClassFrontService getTopicClassFrontService() {
		return topicClassFrontService;
	}

	public void setTopicClassFrontService(
			TopicClassFrontService topicClassFrontService) {
		this.topicClassFrontService = topicClassFrontService;
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

	public CommentInfoFrontService getCommentInfoFrontService() {
		return commentInfoFrontService;
	}

	public void setCommentInfoFrontService(
			CommentInfoFrontService commentInfoFrontService) {
		this.commentInfoFrontService = commentInfoFrontService;
	}

	public TopicLogFrontService getTopicLogFrontService() {
		return topicLogFrontService;
	}

	public void setTopicLogFrontService(
			TopicLogFrontService topicLogFrontService) {
		this.topicLogFrontService = topicLogFrontService;
	}

	public LearnWhyFrontService getLearnWhyFrontService() {
		return learnWhyFrontService;
	}

	public void setLearnWhyFrontService(
			LearnWhyFrontService learnWhyFrontService) {
		this.learnWhyFrontService = learnWhyFrontService;
	}

	public TopicWhyLogFrontService getTopicWhyLogFrontService() {
		return topicWhyLogFrontService;
	}

	public void setTopicWhyLogFrontService(
			TopicWhyLogFrontService topicWhyLogFrontService) {
		this.topicWhyLogFrontService = topicWhyLogFrontService;
	}

	public TopicLog getTopicLog() {
		return topicLog;
	}

	public void setTopicLog(TopicLog topicLog) {
		this.topicLog = topicLog;
	}

	public List getTopicwhyloglist() {
		return topicwhyloglist;
	}

	public void setTopicwhyloglist(List topicwhyloglist) {
		this.topicwhyloglist = topicwhyloglist;
	}

	public TopicLifeAdviceLogFrontService getTopicLifeAdviceLogFrontService() {
		return topicLifeAdviceLogFrontService;
	}

	public void setTopicLifeAdviceLogFrontService(
			TopicLifeAdviceLogFrontService topicLifeAdviceLogFrontService) {
		this.topicLifeAdviceLogFrontService = topicLifeAdviceLogFrontService;
	}

	public TopicLearnClassLogFrontService getTopicLearnClassLogFrontService() {
		return topicLearnClassLogFrontService;
	}

	public void setTopicLearnClassLogFrontService(
			TopicLearnClassLogFrontService topicLearnClassLogFrontService) {
		this.topicLearnClassLogFrontService = topicLearnClassLogFrontService;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public LearnClassFrontService getLearnClassFrontService() {
		return learnClassFrontService;
	}

	public void setLearnClassFrontService(
			LearnClassFrontService learnClassFrontService) {
		this.learnClassFrontService = learnClassFrontService;
	}

	public TopicLifeAdviceFrontService getTopicLifeAdviceFrontService() {
		return topicLifeAdviceFrontService;
	}

	public void setTopicLifeAdviceFrontService(
			TopicLifeAdviceFrontService topicLifeAdviceFrontService) {
		this.topicLifeAdviceFrontService = topicLifeAdviceFrontService;
	}

	public List getTopiclearnclassloglist() {
		return topiclearnclassloglist;
	}

	public void setTopiclearnclassloglist(List topiclearnclassloglist) {
		this.topiclearnclassloglist = topiclearnclassloglist;
	}

	public List getTopiclifeadviceloglist() {
		return topiclifeadviceloglist;
	}

	public void setTopiclifeadviceloglist(List topiclifeadviceloglist) {
		this.topiclifeadviceloglist = topiclifeadviceloglist;
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

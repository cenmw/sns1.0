package com.cenmw.member.center.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.member.center.service.*;
import com.cenmw.member.po.*;
import com.cenmw.util.*;
import org.apache.commons.lang.xwork.StringUtils;

import com.cenmw.base.BaseAction;
import com.cenmw.comment.front.service.CommentInfoFrontService;
import com.cenmw.consult.front.service.ConsultInfoFrontService;
import com.cenmw.consult.front.service.ConsultReplyInfoFrontService;
import com.cenmw.consult.po.ConsultInfo;
import com.cenmw.integral.front.service.IntegralConfigFrontService;
import com.cenmw.integral.front.service.IntegralInfoFrontService;
import com.cenmw.integral.po.IntegralConfig;
import com.cenmw.integral.po.IntegralInfo;
import com.cenmw.labor.front.service.LaborInfoFrontService;
import com.cenmw.labor.front.service.LaborReplyInfoFrontService;
import com.cenmw.labor.po.LaborInfo;
import com.cenmw.labor.po.LaborReplyInfo;
import com.cenmw.store.po.StoreConfig;
import com.cenmw.vedio.front.service.VedioClassFrontService;
import com.cenmw.vedio.front.service.VedioInfoFrontService;
import com.cenmw.vedio.po.VedioClass;
import com.cenmw.vedio.po.VedioInfo;

public class MemberInfoCenterAction extends BaseAction {
	/**
	 * 会员中心 模块
	 */
	private MemberInfoCenterService memberInfoCenterService;
	private MemberLogCenterService memberLogCenterService;
	private MemberMoodCenterService memberMoodCenterService;
	private MemberBlogCenterService memberBlogCenterService;
	private MemberBlogClassCenterService memberBlogClassCenterService;
	private MemberPhotoCenterService memberPhotoCenterService;
	private MemberPhotoGroupCenterService memberPhotoGroupCenterService;
	private VedioInfoFrontService vedioInfoFrontService;
	private VedioClassFrontService vedioClassFrontService;
	private MemberStatusCenterService memberStatusCenterService;
	private MemberFriendCenterService memberFriendCenterService;
	private MemberPraiseCenterService memberPraiseCenterService;
	private CommentInfoFrontService commentInfoFrontService;
	private MemberMessageCenterService memberMessageCenterService;
	private MemberReportCenterService memberReportCenterService;
	private LaborInfoFrontService laborInfoFrontService;
	private LaborReplyInfoFrontService laborReplyInfoFrontService;
	private MemberStoreCenterService memberStoreCenterService;
	private MemberDiaryCenterService memberDiaryCenterService;
	private IntegralInfoFrontService integralInfoFrontService;
	private IntegralConfigFrontService integralConfigFrontService;
	private ConsultInfoFrontService consultInfoFrontService;
	private ConsultReplyInfoFrontService consultReplyInfoFrontService;
	private MemberDiary52CenterService memberDiary52CenterService;
	private MemberDiary52ContentCenterService memberDiary52ContentCenterService;
	private StoreConfigCenterService storeConfigCenterService;
	private MemberDiaryXGYCCenterService memberDiaryXGYCCenterService;
	private MemberInfo memberInfo;
	private MemberInfo cmemberInfo;
	private MemberMood memberMood;
	private MemberBlog memberBlog;
	private MemberDiary memberDiary;
	private VedioInfo vedioInfo;
	private LaborInfo laborInfo;
	private MemberDiary52 memberDiary52;
	private MemberDiaryXGYC memberDiaryXGYC;
	private int id;
	private int cid;
	private String pwd;
	private String newpwd;
	private String msg;
	private int type = 0;
	private String typename;

	private int classid; // 转载选择的分类
	private List classlist; // 转载分类

	// 首页
	private List allstatuslist; // 首页 全部动态
	private List fstatuslist; // 首页 好友动态
	private List fcstatuslist; // 首页 机构动态

	private List mayfriendlist; // 首页可能认识的朋友
	private List zxfriendlist;// 首页 在线好友 前八个
	private List zxcfriendlist;// 首页 在线机构 前八个
	private List hotcontentlist;// 首页 热门转帖 转帖最多 前六条
	private List hotccontentlist;// 首页 热点帖子 访问量最多，评论第二多 前六条
	private int isindex = 0;

	private int ismessagenum = 0; // 查询 未读消息条数
	private int shfriendnum; // 查询待审核好友信息条数 更改为普通好友个数
	private String mobile;// 手机
	private int activitynum = 0; // 查询待审核好友信息条数
	private int cfriendnum; // 企业好友个数
	private int diarycommentnum; // 日志最新评论个数
	private int page = 1; // 当前页数
	private int qx = 0; // 转载

	private List lljllist;// 浏览记录
	private String backUrl;
	private String ids;
	private MemberLLJLCenterService memberLLJLCenterService;
	private int dpage = 0; // 当前页数

	// 搜索条件
	private String searchtitle = "";
	// 内容页，参数该活动的人员信息
	private List laborreplylist; // 活动回复信息
	private String rids = ""; // 群发报名会员id串

	private ConsultInfo consultInfo;
	private List consultreplylist; // 咨询回复信息

	int day = 1; // 52周，第几天
	List contentList = null; // 52周页面展示内容

	/**
	 * 重新封装list,得到更多字符串
	 * 
	 * @return
	 */
	public String getmore9list(List pagelist) {
		StringBuffer sbu = new StringBuffer();
		if (pagelist != null && pagelist.size() > 0) {
			for (int i = 0; i < pagelist.size(); i++) {
				sbu.append("<div class=\"person-sorts mar-t20\">");
				MemberStatus mstatus = (MemberStatus) pagelist.get(i);
				mstatus.setMemberInfo(memberInfoCenterService
						.getMemberInfo(mstatus.getMid()));
				sbu.append("<div class=\"sorts-img fl\">");
				sbu.append("<img src=\""
						+ mstatus.getMemberInfo().getAvatar_small()
						+ "\" height=\"50\" width=\"50\" border=\"0\" />");
				sbu.append("</div>");

				sbu.append("<div class=\"sort-content fl\">");
				sbu.append("<div class=\"user-publish\">");
				int type = mstatus.getType() == null ? 0 : mstatus.getType();
				if (type == 9) {
					MemberMood mmood = memberMoodCenterService
							.getMemberMood(mstatus.getCid().intValue());
					mstatus.setMemberMood(mmood);
					if (mmood != null) {
						mstatus.setTitle(mmood.getContent());
						mstatus.setContent("<a target=\"_blank\" href=\""
										+ mstatus.getUrl()
										+ "\">" + mmood
								.getContent()
								.replaceAll(
										"<img ",
										"<img class=\"zoombig\" onclick=\"zoom_image(this)\" border=\"0\" style=\"max-width:100px;\" onload=\"fixImage(this,400)\" ")+"</a>");
						mstatus.setMemberInfo(memberInfoCenterService
								.getMemberInfo(mmood.getMid()));
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(1, mmood.getId()
										.intValue()));
						mstatus.setRcnumber(memberMoodCenterService
								.getMemberMoodInListNumber(mmood.getId()
										.intValue()));
						mstatus.setCommentnumber(commentInfoFrontService
								.getCommentNumber(mmood.getId().intValue(), 1));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 1, mmood.getId()
										.intValue()));
						mstatus.setId(mmood.getId());
					}
				} else if (type == 8) {
					ConsultInfo cinfo = consultInfoFrontService
							.getConsultInfo(mstatus.getCid().intValue());
					if (cinfo != null) {
						mstatus.setTitle(cinfo.getTitle());
						mstatus.setMemberInfo(memberInfoCenterService
								.getMemberInfo(cinfo.getMid()));
						mstatus.setId(cinfo.getId());
					}
				}else if (type == 10) { // 习惯养成
					MemberDiaryXGYC mdiary = memberDiaryXGYCCenterService
							.getMemberDiaryXGYC(mstatus.getCid().intValue());
					mstatus.setMemberDiaryXGYC(mdiary);
					if (mdiary != null) {
						mstatus.setTitle(mdiary.getPtime());
						mstatus.setContent("<a target=\"_blank\" href=\""
								+ mstatus.getUrl()
								+ "\">"
								+ mdiary.getPtime() + "</a>"
								+ "<div class=\"small-pic mar-t10\">习惯养成</div>");
						if (mdiary.getRcid() != null && mdiary.getRcid() > 0) {
							mstatus.setContent("<a target=\"_blank\" href=\""
									+ mstatus.getUrl()
									+ "\">【转载】"
									+ mdiary.getPtime()
									+ "</a>"
									+ "<div class=\"small-pic mar-t10\">习惯养成</div>");
						}
						mstatus.setMemberInfo(memberInfoCenterService
								.getMemberInfo(mdiary.getMid()));
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(2, mdiary.getId()
										.intValue()));
						mstatus.setRcnumber(memberDiaryCenterService
								.getMemberDiaryInListNumber(mdiary.getId()
										.intValue()));
						mstatus.setCommentnumber(commentInfoFrontService
								.getCommentNumber(mdiary.getId().intValue(), 2));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 2, mdiary.getId()
										.intValue()));
						mstatus.setId(mdiary.getId());
						mstatus.setTypename("习惯养成");
					}
				}
				sbu.append("<a class=\"blue\">"
						+ mstatus.getMemberInfo().getAccount() + " :</a>");
				sbu.append(" " + mstatus.getContent());
				sbu.append("</div>");
				sbu.append("<div class=\"user-talk\">");
				sbu.append("<div class=\"fl gray\">"
						+ DateUtil.getFormatDate(mstatus.getCtime(),
						"yyyy-MM-dd HH:mm") + "</div>");
				sbu.append("<div class=\"fr\">");
					sbu.append("<a id=\"praisenumber"
							+ mstatus.getId().intValue()
							+ "\" class=\"blue\" href=\"javascript:updateMemberPraiseAJAX("+type+","
							+ mstatus.getId().intValue()
							+ ",'praisenumber"
							+ mstatus.getId().intValue()
							+ "')\">赞("
							+ mstatus.getPraisenumber()
							+ ")</a>┊<a class=\"blue\" href=\"javascript:fowardBack('"
							+ mstatus.getUrl()
							+ "&backUrl=','')\">评论("
							+ mstatus.getCommentnumber()
							+ ")</a>┊<a class=\"blue\" href=\"javascript:zz(\"+type+\",'"
							+ mstatus.getId().intValue()
							+ "','"
							+ mstatus.getMemberInfo().getId()
							+ "','"
							+ memberInfo.getId()
							+ "')\">转载("
							+ mstatus.getRcnumber()
							+ ")</a>┊<a id=\"praisenumber"
							+ mstatus.getId().intValue()
							+ "\" class=\"blue\" href=\"javascript:addMemberCollectionAJAX(\"+type+\","
							+ mstatus.getId().intValue()
							+ ",'"
							+ mstatus.getMemberInfo().getId()
							+ "','"
							+ memberInfo.getId()
							+ "')\">收藏</a>┊<a class=\"blue\" href=\"javascript:jb(\"+type+\",'"
							+ mstatus.getId().intValue() + "','"
							+ memberInfo.getId() + "','"
							+ mstatus.getMemberInfo().getId() + "')\">举报</a>");
				sbu.append("</div>");
				sbu.append("</div>");
				sbu.append("</div>");
				sbu.append("<div class=\"clear\"></div>");
				sbu.append("</div>");
			}
		}
		return sbu.toString();
	}

	/**
	 * 更多 作业 9 所有人批改
	 * 
	 * @return
	 */
	public String getMoreState9Ajax() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String morecontent = "";
		id = memberInfo.getId();
		if (memberInfo.getType().intValue() == 0) { // 普通会员
			String fids = memberFriendCenterService
					.findMemberFriends(memberInfo.getId()); // 普通好友
			String cfids = memberFriendCenterService.findMemberAllCFriends(
					memberInfo.getId(), memberInfo.getType().intValue()); // 企业好友
			// 全部动态
			if (fids.length() > 0 || cfids.length() > 0) {
				String allfids = "";
				if (fids.length() > 0 && cfids.length() == 0) {
					allfids = fids;
				}
				if (fids.length() == 0 && cfids.length() > 0) {
					allfids = cfids;
				} else {
					allfids = fids + "," + cfids;
				}
				morecontent = getmore9list(memberStatusCenterService
						.findMemberStatusHQLList(allfids + ","
								+ memberInfo.getId().intValue(), page, 30, qx,
								type));

			}
		}
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(morecontent);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 更多动态 分享
	 * 
	 * @return
	 */
	public String getMoreStateAjax() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String morecontent = "";
		id = memberInfo.getId();
		if (memberInfo.getType().intValue() == 0) { // 普通会员
			String fids = memberFriendCenterService
					.findMemberFriends(memberInfo.getId()); // 普通好友
			String cfids = memberFriendCenterService.findMemberAllCFriends(
					memberInfo.getId(), memberInfo.getType().intValue()); // 企业好友
			if (type == 0) {
				// 全部动态
				if (fids.length() > 0 || cfids.length() > 0) {
					String allfids = "";
					if (fids.length() > 0 && cfids.length() == 0) {
						allfids = fids;
					}
					if (fids.length() == 0 && cfids.length() > 0) {
						allfids = cfids;
					} else {
						allfids = fids + "," + cfids;
					}
					morecontent = getmorelist(memberStatusCenterService
							.findMemberStatusHQLList(allfids + ","
									+ memberInfo.getId().intValue(), page, 30,
									0));

				}
			} else if (type == 1) {
				// 好友动态
				if (fids.length() > 0) {
					morecontent = getmorelist(memberStatusCenterService
							.findMemberStatusHQLList(fids + ","
									+ memberInfo.getId().intValue(), page, 30,
									1));
				}
			} else if (type == 2) {
				// 机构动态
				if (cfids.length() > 0) {
					morecontent = getmorelist(memberStatusCenterService
							.findMemberStatusHQLList(cfids + ","
									+ memberInfo.getId().intValue(), page, 20,
									0));
				}
			} else if (type == 3) {
				// 特殊分享（仅龙爸爸可见）
				if (cfids.length() > 0) {
					morecontent = getmorelist(memberStatusCenterService
							.findMemberStatusHQLList(cfids + ","
									+ memberInfo.getId().intValue(), page, 20,
									3));
				}
			}
		} else {// 企业账号
			String fids = memberFriendCenterService.findMemberAllCFriends(
					memberInfo.getId(), memberInfo.getType().intValue()); // 我的小组
			// 小组动态
			if (fids.length() > 0) {
				morecontent = getmorelist(memberStatusCenterService
						.findMemberStatusHQLList(fids + ","
								+ memberInfo.getId().intValue(), page, 30, 0));
			}
		}

		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(morecontent);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 验证手机是否被注册
	 * 
	 * @return
	 */
	public String checkMobileAjax() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		int result = 0;// result 0 无相同用户 1有相同的用户
		if (memberInfoCenterService.getMemberInfoByMobile(mobile, memberInfo
				.getId().intValue()) > 0) {
			result = 1;
		}
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 会员中心头部
	 * 
	 * @return
	 */
	public String top() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (memberInfo.getType().intValue() == 1) {
			return "company";
		} else {
			return "personal";
		}
	}

	/**
	 * 会员中心头部
	 * 
	 * @return
	 */
	public String left() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		// 得到当前会员的金额
		memberInfo.setSumprice(memberStoreCenterService
				.getMemberStoreSumPrice(memberInfo.getId().intValue()));
		// 完毕
		if (memberInfo.getType().intValue() == 0) {
			// 查询待审核好友信息条数 更改为 好友个数
			shfriendnum = memberFriendCenterService
					.findMemberFriendsCount(memberInfo.getId().intValue());
			cfriendnum = memberFriendCenterService
					.findMemberCFriendsCount(memberInfo.getId().intValue());
			diarycommentnum = commentInfoFrontService.getCommentInfoCount(2,
					memberInfo.getId().intValue());
		}
		// 查询 未读消息条数
		ismessagenum = memberMessageCenterService
				.findMemberMessageCount(memberInfo.getId().intValue());
		// 同步拘留时间已满的用户，需要对其释放
		memberReportCenterService.updateMemberReportAll(memberInfo.getId()
				.intValue());

		// 查询企业发布的活动到期之后，没有添加宣传内容的。
		if (memberInfo.getType().intValue() == 1) {
			activitynum = laborInfoFrontService.findLaborInfoListNum(memberInfo
					.getId().intValue(), 1, "");
		}
		// 查询普通会员参加活动，此活动还差一天就要开始的。
		if (memberInfo.getType().intValue() == 0) {
			String lids = laborReplyInfoFrontService
					.findLaborReplyInfos(memberInfo.getId());
			activitynum = laborInfoFrontService.findLaborInfoListNum(memberInfo
					.getId().intValue(), 0, lids);
		}
		if (memberInfo.getType().intValue() == 1 && isindex == 1) {
			return "companyindex";
		} else if (memberInfo.getType().intValue() == 1 && isindex == 0) {
			return "company";
		} else if (memberInfo.getType().intValue() == 0 && isindex == 1) {
			return "personalindex";
		} else {
			return "personal";
		}
	}

	/**
	 * 会员中心首页
	 * 
	 * @return
	 */
	public String index() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		cmemberInfo = memberInfo;
		id = memberInfo.getId();
		if (memberInfo.getType().intValue() == 0) { // 普通会员
			String fids = memberFriendCenterService
					.findMemberFriends(memberInfo.getId()); // 普通好友
			// String cfids = memberFriendCenterService.findMemberAllCFriends(
			// memberInfo.getId(), memberInfo.getType().intValue()); // 企业好友
			// if (type == 0) {
			// // 全部动态
			// if (fids.length() > 0 || cfids.length() > 0) {
			// String allfids = "";
			// if (fids.length() > 0 && cfids.length() == 0) {
			// allfids = fids;
			// }
			// if (fids.length() == 0 && cfids.length() > 0) {
			// allfids = cfids;
			// } else {
			// allfids = fids + "," + cfids;
			// }
			// allstatuslist = getnewlist(memberStatusCenterService
			// .findFCMemberStatusInList(allfids + ","
			// + memberInfo.getId().intValue(), 30, 0));
			// }
			// } else if (type == 1) {
			// // 好友动态
			// if (fids.length() > 0) {
			// fstatuslist = getnewlist(memberStatusCenterService
			// .findFCMemberStatusInList(fids, 30, 1));
			// }
			// } else if (type == 2) {
			// // 机构动态
			// if (cfids.length() > 0) {
			// fcstatuslist = getnewlist(memberStatusCenterService
			// .findFCMemberStatusInList(cfids, 20, 0));
			// }
			// } else if (type == 3) {
			// // 特殊分享（仅龙爸爸可见）
			// // 全部动态
			// if (fids.length() > 0 || cfids.length() > 0) {
			// String allfids = "";
			// if (fids.length() > 0 && cfids.length() == 0) {
			// allfids = fids;
			// }
			// if (fids.length() == 0 && cfids.length() > 0) {
			// allfids = cfids;
			// } else {
			// allfids = fids + "," + cfids;
			// }
			// allstatuslist = getnewlist(memberStatusCenterService
			// .findFCMemberStatusInList(allfids + ","
			// + memberInfo.getId().intValue(), 30, 3));
			// }
			// }
			// 在线好友
			if (fids != null && fids.length() > 0) {
				zxfriendlist = memberInfoCenterService
						.findOnlineFMemberInfoList(0, "" + fids, 8);
				if (zxfriendlist.size() < 8) {
					List list = memberInfoCenterService
							.findOnlineMemberInfoList(0, ""
									+ memberInfo.getId().intValue(),
									8 - zxfriendlist.size());
					if (list != null && !list.isEmpty()) {
						for (int i = 0; i < list.size(); i++) {
							zxfriendlist.add((MemberInfo) list.get(i));
						}
					}
				}
			} else {
				zxfriendlist = memberInfoCenterService
						.findOnlineMemberInfoList(0, ""
								+ memberInfo.getId().intValue(), 8);
			}
			// // 在线企业
			// if (cfids != null && cfids.length() > 0) {
			// zxcfriendlist = memberInfoCenterService
			// .findOnlineFMemberInfoList(1, "" + cfids, 8);
			// if (zxcfriendlist.size() < 8) {
			// List list = memberInfoCenterService
			// .findOnlineMemberInfoList(1, ""
			// + memberInfo.getId().intValue(),
			// 8 - zxcfriendlist.size());
			// if (list != null && !list.isEmpty()) {
			// for (int i = 0; i < list.size(); i++) {
			// zxcfriendlist.add((MemberInfo) list.get(i));
			// }
			// }
			// }
			// } else {
			// zxcfriendlist = memberInfoCenterService
			// .findOnlineMemberInfoList(1, ""
			// + memberInfo.getId().intValue(), 8);
			// }
			// 已经发过申请，或者已经成为好友的
			String fidsSH = memberFriendCenterService
					.findMemberFriendsSH(memberInfo.getId()); // 发过申请普通好友
			// 可能认识的好友
			mayfriendlist = memberInfoCenterService.findMayMemberInfoInList(
					memberInfo, fidsSH, 6);
			if (fids.length() > 0) {
				fids += "," + memberInfo.getId().intValue();
			}

			// if (cfids.length() > 0) {
			// cfids += "," + memberInfo.getId().intValue();
			// }

		} else {// 企业账号
		// String fids = memberFriendCenterService.findMemberAllCFriends(
		// memberInfo.getId(), memberInfo.getType().intValue()); // 我的小组
			// 小组动态
			// if (fids.length() > 0) {
			// fstatuslist = getnewlist(memberStatusCenterService
			// .findFCMemberStatusInList(fids, 30, 0));
			// }
			// 热门帖子，热点推荐
			hotcontentlist = memberBlogCenterService.findHotZTMemberBlogList(6);
			hotccontentlist = laborInfoFrontService.findLaborInfoList(6);
			return "company";
		}
		// 热门帖子，热点推荐
//		hotcontentlist = memberBlogCenterService.findHotZTMemberBlogList(6);
//		hotccontentlist = laborInfoFrontService.findLaborInfoList(6);
		List list = memberDiaryXGYCCenterService.findHotVIEWMemberDiaryXGYCList(6);
		if(list != null){
			hotcontentlist = new ArrayList();
			for(int i=0;i<list.size();i++){
				MemberDiaryXGYC xgyc = (MemberDiaryXGYC) list.get(i);
				if(xgyc != null && xgyc.getMid() != null){
					xgyc.setMemberInfo(memberInfoCenterService.getMemberInfo(xgyc.getMid()));
					hotcontentlist.add(xgyc);
				}
			}
		}
		hotccontentlist = memberMoodCenterService.findHotZTMemberMoodList(6);
		return SUCCESS;
	}

	/**
	 * 转载 说说，日志，文章，相册
	 * 
	 * @return
	 */
	public String zz() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (type == 2) { // 转载日志
			memberDiary = memberDiaryCenterService.getMemberDiary(memberInfo
					.getId().intValue(), cid);
			typename = "blog";
		} else if (type == 3) { // 转载文集
			classlist = memberBlogClassCenterService.findMemberBlogClassInList(
					memberInfo.getId(), 2);
			memberBlog = memberBlogCenterService.getMemberBlog(memberInfo
					.getId().intValue(), cid);
			typename = "content";
		} else if (type == 5) { // 转载视频
			classlist = vedioClassFrontService.findVedioClassInList(
					memberInfo.getId(), 1);
			vedioInfo = vedioInfoFrontService.getVedioInfo(memberInfo.getId()
					.intValue(), cid);
			typename = "vedio";
		} else if (type == 6) { // 转载学习中心文集
			classlist = memberBlogClassCenterService.findMemberBlogClassInList(
					memberInfo.getId(), 2);
			memberBlog = memberBlogCenterService.getMemberBlog(memberInfo
					.getId().intValue(), cid);
			typename = "content";
		}

		return SUCCESS;
	}

	/**
	 * 转载 说说，日志，文章，相册
	 * 
	 * @return
	 */
	public String zzsave() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		msg = "转载成功！";
		if (memberReportCenterService.getIsMemberReport(memberInfo.getId()
				.intValue())) {
			msg = "操作失败，您的账号，正在拘留当中！";
		} else {
			if (type == 2) { // 转载日志
				MemberDiary oldmm = memberDiaryCenterService
						.getMemberDiary(cid);
				if (memberDiary == null) {
					memberDiary = new MemberDiary();
					memberDiary.setMid(memberInfo.getId().intValue());
					memberDiary.setPtime(oldmm.getPtime());
					memberDiary.setFssq(oldmm.getFssq());
					memberDiary.setHzbx(oldmm.getHzbx());
					memberDiary.setBxjj(oldmm.getBxjj());
					memberDiary.setFzkj(oldmm.getFzkj());
					memberDiary.setWdgs(oldmm.getWdgs());
					memberDiary.setNxxq(oldmm.getNxxq());
					memberDiary.setRcid(cid);
					memberDiary.setCtime(new Date());
					memberDiary.setIsdel(new Integer(0));
					memberDiary.setViewnumber(new Integer(0));
					memberDiary.setRcnumber(new Integer(0));
					memberDiary.setQx(qx);
					memberDiaryCenterService.saveMemberDiary(memberDiary);
					// 添加动态
					// 添加会员动态信息 2013-08-13
					memberStatusCenterService.saveMemberStatus(memberInfo
							.getId(), 2, memberDiary.getId(),
							DateUtil.getFormatDate(memberDiary.getPtime(),
									"yyyy-MM-dd"), 0);
				}
			} else if (type == 3) { // 转载文集
				MemberBlog oldmm = memberBlogCenterService.getMemberBlog(cid);
				if (memberBlog == null) {
					memberBlog = new MemberBlog();
					memberBlog.setMid(memberInfo.getId().intValue());
					memberBlog.setType(new Integer(1));
					if (type == 3) {
						memberBlog.setType(new Integer(2));
					}
					memberBlog.setTitle(oldmm.getTitle());
					memberBlog.setKeyword(oldmm.getKeyword());
					memberBlog.setDescription(oldmm.getDescription());
					memberBlog.setContent(oldmm.getContent());
					memberBlog.setRcid(cid);
					memberBlog.setCid(id);
					memberBlog.setCtime(new Date());
					memberBlog.setIsdel(new Integer(0));
					memberBlog.setSort(new Integer(0));
					memberBlog.setViewnumber(new Integer(0));
					memberBlog.setQx(qx);
					memberBlog.setRcnumber(new Integer(0));
					memberBlogCenterService.saveMemberBlog(memberBlog);
					MemberBlogClass memberBlogClass = memberBlogClassCenterService
							.getMemberBlogClass(id);
					if (memberBlogClass != null) {
						memberBlog.setClassname(memberBlogClass.getTitle());
					}
					memberBlogCenterService.updateMemberBlog(memberBlog);
					// 添加动态
					// 添加会员动态信息 2013-08-13
					if (type == 2) {
						memberStatusCenterService.saveMemberStatus(
								memberInfo.getId(), 2, memberBlog.getId(),
								memberBlog.getTitle(), qx);
					} else {
						memberStatusCenterService.saveMemberStatus(
								memberInfo.getId(), 3, memberBlog.getId(),
								memberBlog.getTitle(), qx);
					}
				}
			} else if (type == 5) { // 转载视频
				VedioInfo oldvi = vedioInfoFrontService.getVedioInfo(cid);
				if (vedioInfo == null) {
					vedioInfo = new VedioInfo();
					vedioInfo.setMid(memberInfo.getId().intValue());
					vedioInfo.setType(new Integer(0));
					vedioInfo.setTitle(oldvi.getTitle());
					vedioInfo.setKeyword(oldvi.getKeyword());
					vedioInfo.setDescription(oldvi.getDescription());
					vedioInfo.setContent(oldvi.getContent());
					vedioInfo.setAuthor(oldvi.getAuthor());
					vedioInfo.setPtime(oldvi.getPtime());
					vedioInfo.setPicpath(oldvi.getPicpath());
					vedioInfo.setRcid(cid);
					vedioInfo.setCid(id);
					vedioInfo.setCtime(new Date());
					vedioInfo.setIsdel(new Integer(0));
					vedioInfo.setSort(new Integer(0));
					vedioInfo.setState(new Integer(1));
					vedioInfo.setViewnumber(new Integer(0));
					vedioInfo.setQx(qx);
					vedioInfoFrontService.saveVedioInfo(vedioInfo);
					VedioClass vedioClass = vedioClassFrontService
							.getVedioClass(vedioInfo.getCid());
					if (vedioClass != null) {
						vedioInfo.setClassname(vedioClass.getTitle());
					}
					vedioInfoFrontService.updateVedioInfo(vedioInfo);
					// 添加动态
					// 添加会员动态信息 2013-08-13
					memberStatusCenterService.saveMemberStatus(
							memberInfo.getId(), 5, vedioInfo.getId(),
							vedioInfo.getTitle(), qx);
				}
			}
		}
		session.put("saveinfomsg", msg);
		return SUCCESS;
	}

	/**
	 * 会员中心基本信息
	 * 
	 * @return
	 */
	public String info() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		return SUCCESS;
	}

	/**
	 * 会员中心基本信息 保存
	 * 
	 * @return
	 */
	public String save() {
		if (type == 1) {
			typename = "basis";
			if (memberInfo.getAccount() != null
					&& memberInfo.getAccount().trim().length() > 0
					&& memberInfoCenterService.getMemberInfoByAccount(memberInfo
					.getAccount(), memberInfo.getId().intValue()) > 0) {
				msg = "该名称已经存在，请更换名称！";
				session.put("saveinfomsg", msg);
				return SUCCESS;
			}
		} else if (type == 2) {
			typename = "contact";
			if (memberInfo.getMobile() != null
					&& memberInfo.getMobile().trim().length() > 0
					&& memberInfoCenterService.getMemberInfoByMobile(memberInfo
							.getMobile(), memberInfo.getId().intValue()) > 0) {
				msg = "该手机号已经绑定其他用户，请更换手机号！";
				session.put("saveinfomsg", msg);
				return SUCCESS;
			}
		} else if (type == 3) {
			typename = "head";
		} else {
			typename = "basis";
		}
		msg = "修改成功！";
		session.put("saveinfomsg", msg);
		memberInfoCenterService.updateMemberInfo(memberInfo);
		memberInfo = memberInfoCenterService.getMemberInfo(memberInfo.getId());
		session.put(ConstantUtils.MEMBERINFO, memberInfo);
		return SUCCESS;
	}

	/**
	 * 会员中心退出
	 * 
	 * @return
	 */
	public String loginout() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		session.remove(ConstantUtils.MEMBERINFO);
		// 添加 退出记录表
		MemberLog memberLog = new MemberLog(memberInfo.getId(), 2, 0, "");
		memberLogCenterService.saveMemberLog(memberLog);
		/* 同时清除当前登录用户cookie */
		CookieUtils.delCookie(request, response, ConstantUtils.TOKEN);
		return SUCCESS;
	}

	/**
	 * 会员中心 修改密码页面
	 * 
	 * @return
	 */
	public String password() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		return SUCCESS;
	}

	/**
	 * 会员中心 修改密码
	 * 
	 * @return
	 */
	public String updatepassword() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		MD5 md5 = new MD5();
		String p = md5.getMD5ofStr(pwd);
		String encryptedPassword = md5.getMD5ofStr(newpwd);
		if (!p.equalsIgnoreCase(memberInfo.getPassword())) {
			msg = "原密码输入错误！";
		} else {
			memberInfo.setPassword(encryptedPassword);
			memberInfoCenterService.updateMemberInfo(memberInfo);
			msg = "密码修改成功！";
		}
		session.put("passwordmsg", msg);
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
				MemberStatus mstatus = (MemberStatus) pagelist.get(i);
				mstatus.setMemberInfo(memberInfoCenterService
						.getMemberInfo(mstatus.getMid()));
				int type = mstatus.getType() == null ? 0 : mstatus.getType();
				if (type == 1) { // 说说
					MemberMood mmood = memberMoodCenterService
							.getMemberMood(mstatus.getCid().intValue());
					mstatus.setMemberMood(mmood);
					if (mmood != null) {
						mstatus.setTitle(mmood.getContent());
						String content = mmood.getContent();
						if (content.indexOf("<img") >= 0) {
							content = content
									.replaceAll(
											"<img",
											"<img class=\"zoombig\" border=\"0\"  onload=\"fixImage(this,400)\" style=\"max-width:100px;\" onclick=\"zoom_image(this)\"");
						}
						mstatus.setContent(content);
						mstatus.setMemberInfo(memberInfoCenterService
								.getMemberInfo(mmood.getMid()));
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(1, mmood.getId()
										.intValue()));
						mstatus.setRcnumber(memberMoodCenterService
								.getMemberMoodInListNumber(mmood.getId()
										.intValue()));
						mstatus.setCommentnumber(commentInfoFrontService
								.getCommentNumber(mmood.getId().intValue(), 1));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 1, mmood.getId()
										.intValue()));
						mstatus.setId(mmood.getId());
						mstatus.setTypename("说说");
					}
				} else if (type == 2) { // 疏导
					MemberDiary mdiary = memberDiaryCenterService
							.getMemberDiary(mstatus.getCid().intValue());
					mstatus.setMemberDiary(mdiary);
					if (mdiary != null) {
						mstatus.setTitle(DateUtil.getFormatDate(
								mdiary.getPtime(), "yyyy-MM-dd"));
						mstatus.setContent("<a target=\"_blank\" href=\""
								+ mstatus.getUrl()
								+ "\">"
								+ DateUtil.getFormatDate(mdiary.getPtime(),
										"yyyy-MM-dd") + "</a>"
								+ "<div class=\"small-pic mar-t10\">疏导</div>");
						if (mdiary.getRcid() != null && mdiary.getRcid() > 0) {
							mstatus.setContent("<a target=\"_blank\" href=\""
									+ mstatus.getUrl()
									+ "\">【转载】"
									+ DateUtil.getFormatDate(mdiary.getPtime(),
											"yyyy-MM-dd")
									+ "</a>"
									+ "<div class=\"small-pic mar-t10\">疏导</div>");
						}
						mstatus.setMemberInfo(memberInfoCenterService
								.getMemberInfo(mdiary.getMid()));
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(2, mdiary.getId()
										.intValue()));
						mstatus.setRcnumber(memberDiaryCenterService
								.getMemberDiaryInListNumber(mdiary.getId()
										.intValue()));
						mstatus.setCommentnumber(commentInfoFrontService
								.getCommentNumber(mdiary.getId().intValue(), 2));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 2, mdiary.getId()
										.intValue()));
						mstatus.setId(mdiary.getId());
						mstatus.setTypename("疏导");
					}
				} else if (type == 3) { // 文集
					MemberBlog mblog = memberBlogCenterService
							.getMemberBlog(mstatus.getCid().intValue());
					mstatus.setMemberBlog(mblog);
					if (mblog != null) {
						if (mblog.getCid() != null
								&& memberBlogClassCenterService
										.getMemberBlogClass(mblog.getCid()) != null) {
							mstatus.setMemberBlogClass(memberBlogClassCenterService
									.getMemberBlogClass(mblog.getCid()));
						}
						mstatus.setTitle(mblog.getTitle());
						mstatus.setContent("<a target=\"_blank\" href=\""
								+ mstatus.getUrl() + "\">" + mblog.getTitle()
								+ "</a>" + "<div class=\"small-pic mar-t10\">"
								+ mblog.getDescription() + "</div>");
						if (mblog.getRcid() != null && mblog.getRcid() > 0) {
							mstatus.setContent("<a target=\"_blank\" href=\""
									+ mstatus.getUrl() + "\">【转载】"
									+ mblog.getTitle() + "</a>"
									+ "<div class=\"small-pic mar-t10\">"
									+ mblog.getDescription() + "</div>");
						}
						mstatus.setMemberInfo(memberInfoCenterService
								.getMemberInfo(mblog.getMid()));
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(3, mblog.getId()
										.intValue()));
						mstatus.setRcnumber(memberBlogCenterService
								.getMemberBlogInListNumber(mblog.getId()
										.intValue()));
						mstatus.setCommentnumber(commentInfoFrontService
								.getCommentNumber(mblog.getId().intValue(), 3));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 3, mblog.getId()
										.intValue()));
						mstatus.setId(mblog.getId());
						mstatus.setTypename("文集");
					}
				} else if (type == 4) { // 相册
					MemberPhoto mphoto = memberPhotoCenterService
							.getMemberPhoto(mstatus.getCid().intValue());
					mstatus.setMemberPhoto(mphoto);
					if (mphoto != null) {
						mstatus.setMemberPhotoGroup(memberPhotoGroupCenterService
								.getMemberPhotoGroup(mphoto.getCid()));
						mstatus.setTitle(mphoto.getTitle());
						mstatus.setContent("<a target=\"_blank\" href=\""
								+ mstatus.getUrl()
								+ "\">"
								+ mphoto.getTitle()
								+ "</a>"
								+ "<div class=\"small-pic mar-t10\"><img class=\"zoombig\" onclick=\"zoom_image(this)\" border=\"0\" style=\"max-width:100px;\" onload=\"fixImage(this,400)\" src='"
								+ mphoto.getPicpath() + "' /></div>");
						mstatus.setMemberInfo(memberInfoCenterService
								.getMemberInfo(mphoto.getMid()));
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(4, mphoto.getId()
										.intValue()));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 4, mphoto.getId()
										.intValue()));
						mstatus.setId(mphoto.getId());
						mstatus.setTypename("相册");
					}
				} else if (type == 5) { // 视频
					VedioInfo vinfo = vedioInfoFrontService
							.getVedioInfo(mstatus.getCid().intValue());
					mstatus.setVedioInfo(vinfo);
					if (vinfo != null) {
						mstatus.setVedioClass(vedioClassFrontService
								.getVedioClass(vinfo.getCid()));
						mstatus.setTitle(vinfo.getTitle());
						mstatus.setContent("<a target=\"_blank\" href=\""
								+ mstatus.getUrl()
								+ "\">"
								+ vinfo.getTitle()
								+ "</a>"
								+ "<div class=\"small-pic mar-t10\"><a target=\"_blank\" href=\""
								+ mstatus.getUrl()
								+ "\"><img title='点击看视频' alt='点击看视频' border=\"0\" style=\"max-width:200px;\" onload=\"fixImage(this,200)\" src='"
								+ vinfo.getPicpath() + "' /></a></div>");
						mstatus.setMemberInfo(memberInfoCenterService
								.getMemberInfo(vinfo.getMid()));
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(5, vinfo.getId()
										.intValue()));
						mstatus.setRcnumber(vedioInfoFrontService
								.getMemberVedioInListNumber(vinfo.getId()
										.intValue()));
						mstatus.setCommentnumber(commentInfoFrontService
								.getCommentNumber(vinfo.getId().intValue(), 5));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 5, vinfo.getId()
										.intValue()));
						if (vinfo.getRcid() != null && vinfo.getRcid() > 0) {
							mstatus.setContent("<a target=\"_blank\" href=\""
									+ mstatus.getUrl()
									+ "\">【转载】"
									+ vinfo.getTitle()
									+ "</a>"
									+ "<div class=\"small-pic mar-t10\"><a target=\"_blank\" href=\""
									+ mstatus.getUrl()
									+ "\"><img title='点击看视频' alt='点击看视频' border=\"0\" style=\"max-width:400px;\" onload=\"fixImage(this,400)\" src='"
									+ vinfo.getPicpath() + "' /></a></div>");
						}
						mstatus.setId(vinfo.getId());
					}
					mstatus.setTypename("视频");
				} else if (type == 7) { // 52周
					MemberDiary52 mdiary = memberDiary52CenterService
							.getMemberDiary52(mstatus.getCid().intValue());
					mstatus.setMemberDiary52(mdiary);
					if (mdiary != null) {
						mstatus.setTitle(DateUtil.getFormatDate(
								mdiary.getPtime(), "yyyy-MM-dd"));
						String typename = "与家庭有关";
						if (mdiary.getType() == 1) {
							typename = "与自己有关";
						}
						mstatus.setContent("<a target=\"_blank\" href=\""
								+ mstatus.getUrl()
								+ "\">"
								+ DateUtil.getFormatDate(mdiary.getPtime(),
										"yyyy-MM-dd") + "</a>"
								+ "<div class=\"small-pic mar-t10\">52周（"
								+ typename + "）</div>");
						if (mdiary.getRcid() != null && mdiary.getRcid() > 0) {
							mstatus.setContent("<a target=\"_blank\" href=\""
									+ mstatus.getUrl()
									+ "\">【转载】"
									+ DateUtil.getFormatDate(mdiary.getPtime(),
											"yyyy-MM-dd")
									+ "</a>"
									+ "<div class=\"small-pic mar-t10\">52周</div>");
						}
						mstatus.setMemberInfo(memberInfoCenterService
								.getMemberInfo(mdiary.getMid()));
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(2, mdiary.getId()
										.intValue()));
						mstatus.setRcnumber(memberDiaryCenterService
								.getMemberDiaryInListNumber(mdiary.getId()
										.intValue()));
						mstatus.setCommentnumber(commentInfoFrontService
								.getCommentNumber(mdiary.getId().intValue(), 2));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 2, mdiary.getId()
										.intValue()));
						mstatus.setId(mdiary.getId());
						mstatus.setTypename("52周");
					}
				} else if (type == 10) { // 习惯养成
					MemberDiaryXGYC mdiary = memberDiaryXGYCCenterService
							.getMemberDiaryXGYC(mstatus.getCid().intValue());
					mstatus.setMemberDiaryXGYC(mdiary);
					if (mdiary != null) {
						mstatus.setTitle(mdiary.getPtime());
						mstatus.setContent("<a target=\"_blank\" href=\""
								+ mstatus.getUrl()
								+ "\">"
								+ mdiary.getPtime() + "</a>"
								+ "<div class=\"small-pic mar-t10\">习惯养成</div>");
						if (mdiary.getRcid() != null && mdiary.getRcid() > 0) {
							mstatus.setContent("<a target=\"_blank\" href=\""
									+ mstatus.getUrl()
									+ "\">【转载】"
									+ mdiary.getPtime()
									+ "</a>"
									+ "<div class=\"small-pic mar-t10\">习惯养成</div>");
						}
						mstatus.setMemberInfo(memberInfoCenterService
								.getMemberInfo(mdiary.getMid()));
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(2, mdiary.getId()
										.intValue()));
						mstatus.setRcnumber(memberDiaryCenterService
								.getMemberDiaryInListNumber(mdiary.getId()
										.intValue()));
						mstatus.setCommentnumber(commentInfoFrontService
								.getCommentNumber(mdiary.getId().intValue(), 2));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 2, mdiary.getId()
										.intValue()));
						mstatus.setId(mdiary.getId());
						mstatus.setTypename("习惯养成");
					}
				}
				newlist.add(mstatus);
			}
		}
		return newlist;
	}

	/**
	 * 重新封装list,得到更多字符串
	 * 
	 * @return
	 */
	public String getmorelist(List pagelist) {
		StringBuffer sbu = new StringBuffer();
		if (pagelist != null && pagelist.size() > 0) {
			for (int i = 0; i < pagelist.size(); i++) {
				sbu.append("<div class=\"person-sorts mar-t20\">");
				MemberStatus mstatus = (MemberStatus) pagelist.get(i);
				mstatus.setMemberInfo(memberInfoCenterService
						.getMemberInfo(mstatus.getMid()));
				sbu.append("<div class=\"sorts-img fl\">");
				sbu.append("<img src=\""
						+ mstatus.getMemberInfo().getAvatar_small()
						+ "\" height=\"50\" width=\"50\" border=\"0\" />");
				sbu.append("</div>");

				sbu.append("<div class=\"sort-content fl\">");
				sbu.append("<div class=\"user-publish\">");
				int type = mstatus.getType() == null ? 0 : mstatus.getType();
				if (type == 1) {
					MemberMood mmood = memberMoodCenterService
							.getMemberMood(mstatus.getCid().intValue());
					mstatus.setMemberMood(mmood);
					if (mmood != null) {
						mstatus.setTitle(mmood.getContent());
						mstatus.setContent(mmood
								.getContent()
								.replaceAll(
										"<img ",
										"<img class=\"zoombig\" onclick=\"zoom_image(this)\" border=\"0\" style=\"max-width:100px;\" onload=\"fixImage(this,400)\" "));
						mstatus.setMemberInfo(memberInfoCenterService
								.getMemberInfo(mmood.getMid()));
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(1, mmood.getId()
										.intValue()));
						mstatus.setRcnumber(memberMoodCenterService
								.getMemberMoodInListNumber(mmood.getId()
										.intValue()));
						mstatus.setCommentnumber(commentInfoFrontService
								.getCommentNumber(mmood.getId().intValue(), 1));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 1, mmood.getId()
										.intValue()));
						mstatus.setId(mmood.getId());
					}
				} else if (type == 2) {// 疏导
				MemberDiary mdiary = memberDiaryCenterService.getMemberDiary(mstatus.getCid().intValue());
				mstatus.setMemberDiary(mdiary);
					if (mdiary != null) {
						String typeName = "疏导";
						String url = mstatus.getUrl();
						if(mdiary.getType() == 2){
							typeName = "吾日省身";
							url ="showwrssinfo?id=" + mstatus.getCid();
						}
						mstatus.setTitle(DateUtil.getFormatDate(
								mdiary.getPtime(), "yyyy-MM-dd"));
						mstatus.setContent("<a target=\"_blank\" href=\""
								+ url
								+ "\">"
								+ DateUtil.getFormatDate(mdiary.getPtime(),
								"yyyy-MM-dd") + "</a>"
								+ "<div class=\"small-pic mar-t10\">"+typeName+"</div>");
						if (mdiary.getRcid() != null && mdiary.getRcid() > 0) {
							mstatus.setContent("<a target=\"_blank\" href=\""
									+ url
									+ "\">【转载】"
									+ DateUtil.getFormatDate(mdiary.getPtime(),
									"yyyy-MM-dd")
									+ "</a>"
									+ "<div class=\"small-pic mar-t10\">"+typeName+"</div>");
						}
						mstatus.setMemberInfo(memberInfoCenterService
								.getMemberInfo(mdiary.getMid()));
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(2, mdiary.getId()
										.intValue()));
						mstatus.setRcnumber(memberDiaryCenterService
								.getMemberDiaryInListNumber(mdiary.getId()
										.intValue()));
						mstatus.setCommentnumber(commentInfoFrontService
								.getCommentNumber(mdiary.getId().intValue(), 2));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 2, mdiary.getId()
										.intValue()));
						mstatus.setId(mdiary.getId());
						mstatus.setTypename(typeName);
					}
				} else if (type == 3) {
					MemberBlog mblog = memberBlogCenterService
							.getMemberBlog(mstatus.getCid().intValue());
					mstatus.setMemberBlog(mblog);
					if (mblog != null) {
						if (mblog.getCid() != null
								&& memberBlogClassCenterService
										.getMemberBlogClass(mblog.getCid()) != null) {
							mstatus.setMemberBlogClass(memberBlogClassCenterService
									.getMemberBlogClass(mblog.getCid()));
						}
						mstatus.setTitle(mblog.getTitle());
						mstatus.setContent("<a target=\"_blank\" href=\""
								+ mstatus.getUrl() + "\">" + mblog.getTitle()
								+ "</a>" + "<div class=\"small-pic mar-t10\">"
								+ mblog.getDescription() + "</div>");
						if (mblog.getRcid() != null && mblog.getRcid() > 0) {
							mstatus.setContent("<a target=\"_blank\" href=\""
									+ mstatus.getUrl() + "\">【转载】"
									+ mblog.getTitle() + "</a>"
									+ "<div class=\"small-pic mar-t10\">"
									+ mblog.getDescription() + "</div>");
						}
							mstatus.setMemberInfo(memberInfoCenterService
									.getMemberInfo(mblog.getMid()));
							mstatus.setPraisenumber(memberPraiseCenterService
									.getMemberPraiseInListNumber(3, mblog
											.getId().intValue()));
							mstatus.setRcnumber(memberBlogCenterService
									.getMemberBlogInListNumber(mblog.getId()
											.intValue()));
							mstatus.setCommentnumber(commentInfoFrontService
									.getCommentNumber(mblog.getId().intValue(),
											3));
							mstatus.setMemberPraise(memberPraiseCenterService
									.findMemberPraiseAll(memberInfo.getId()
											.intValue(), 3, mblog.getId()
											.intValue()));
						mstatus.setId(mblog.getId());
					}
				} else if (type == 4) {
					MemberPhoto mphoto = memberPhotoCenterService
							.getMemberPhoto(mstatus.getCid().intValue());
					mstatus.setMemberPhoto(mphoto);
					if (mphoto != null) {
						mstatus.setMemberPhotoGroup(memberPhotoGroupCenterService
								.getMemberPhotoGroup(mphoto.getCid()));
						mstatus.setTitle(mphoto.getTitle());
						mstatus.setContent("<a target=\"_blank\" href=\""
								+ mstatus.getUrl()
								+ "\">"
								+ mphoto.getTitle()
								+ "</a>"
								+ "<div class=\"small-pic mar-t10\"><img class=\"zoombig\" onclick=\"zoom_image(this)\" border=\"0\" style=\"max-width:100px;\" onload=\"fixImage(this,400)\" src='"
								+ mphoto.getPicpath() + "' /></div>");
						mstatus.setMemberInfo(memberInfoCenterService
								.getMemberInfo(mphoto.getMid()));
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(4, mphoto.getId()
										.intValue()));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 4, mphoto.getId()
										.intValue()));
						mstatus.setId(mphoto.getId());
					}
				} else if (type == 5) {
					VedioInfo vinfo = vedioInfoFrontService
							.getVedioInfo(mstatus.getCid().intValue());
					mstatus.setVedioInfo(vinfo);
					if (vinfo != null) {
						mstatus.setVedioClass(vedioClassFrontService
								.getVedioClass(vinfo.getCid()));
						mstatus.setTitle(vinfo.getTitle());
						mstatus.setContent("<a target=\"_blank\" href=\""
								+ mstatus.getUrl()
								+ "\">"
								+ vinfo.getTitle()
								+ "</a>"
								+ "<div class=\"small-pic mar-t10\"><a target=\"_blank\" href=\""
								+ mstatus.getUrl()
								+ "\"><img title='点击看视频' alt='点击看视频' border=\"0\" style=\"max-width:200px;\" onload=\"fixImage(this,200)\" src='"
								+ vinfo.getPicpath() + "' /></a></div>");
						mstatus.setMemberInfo(memberInfoCenterService
								.getMemberInfo(vinfo.getMid()));
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(5, vinfo.getId()
										.intValue()));
						mstatus.setRcnumber(vedioInfoFrontService
								.getMemberVedioInListNumber(vinfo.getId()
										.intValue()));
						mstatus.setCommentnumber(commentInfoFrontService
								.getCommentNumber(vinfo.getId().intValue(), 5));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 5, vinfo.getId()
										.intValue()));
						if (vinfo.getRcid() != null && vinfo.getRcid() > 0) {
							mstatus.setContent("<a target=\"_blank\" href=\""
									+ mstatus.getUrl()
									+ "\">【转载】"
									+ vinfo.getTitle()
									+ "</a>"
									+ "<div class=\"small-pic mar-t10\"><a target=\"_blank\" href=\""
									+ mstatus.getUrl()
									+ "\"><img title='点击看视频' alt='点击看视频' border=\"0\" style=\"max-width:400px;\" onload=\"fixImage(this,400)\" src='"
									+ vinfo.getPicpath() + "' /></a></div>");
						}
						mstatus.setId(vinfo.getId());
					}
				} else if (type == 7) { // 52周
					MemberDiary52 mdiary = memberDiary52CenterService
							.getMemberDiary52(mstatus.getCid().intValue());
					mstatus.setMemberDiary52(mdiary);
					if (mdiary != null) {
						mstatus.setTitle(DateUtil.getFormatDate(
								mdiary.getPtime(), "yyyy-MM-dd"));
						String typename = "与家庭有关";
						if (mdiary.getType() == 1) {
							typename = "与自己有关";
						}
						mstatus.setContent("<a target=\"_blank\" href=\""
								+ mstatus.getUrl()
								+ "\">"
								+ DateUtil.getFormatDate(mdiary.getPtime(),
										"yyyy-MM-dd") + "</a>"
								+ "<div class=\"small-pic mar-t10\">52周（"
								+ typename + "）</div>");
						if (mdiary.getRcid() != null && mdiary.getRcid() > 0) {
							mstatus.setContent("<a target=\"_blank\" href=\""
									+ mstatus.getUrl()
									+ "\">【转载】"
									+ DateUtil.getFormatDate(mdiary.getPtime(),
											"yyyy-MM-dd")
									+ "</a>"
									+ "<div class=\"small-pic mar-t10\">52周</div>");
						}
						mstatus.setMemberInfo(memberInfoCenterService
								.getMemberInfo(mdiary.getMid()));
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(2, mdiary.getId()
										.intValue()));
						mstatus.setRcnumber(memberDiaryCenterService
								.getMemberDiaryInListNumber(mdiary.getId()
										.intValue()));
						mstatus.setCommentnumber(commentInfoFrontService
								.getCommentNumber(mdiary.getId().intValue(), 2));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 2, mdiary.getId()
										.intValue()));
						mstatus.setId(mdiary.getId());
						mstatus.setTypename("52周");
					}
				} else if (type == 10) { // 习惯养成
					MemberDiaryXGYC mdiary = memberDiaryXGYCCenterService
							.getMemberDiaryXGYC(mstatus.getCid().intValue());
					mstatus.setMemberDiaryXGYC(mdiary);
					if (mdiary != null) {
						mstatus.setTitle(mdiary.getPtime());
						mstatus.setContent("<a target=\"_blank\" href=\""
								+ mstatus.getUrl()
								+ "\">"
								+ mdiary.getPtime() + "</a>"
								+ "<div class=\"small-pic mar-t10\">习惯养成</div>");
						if (mdiary.getRcid() != null && mdiary.getRcid() > 0) {
							mstatus.setContent("<a target=\"_blank\" href=\""
									+ mstatus.getUrl()
									+ "\">【转载】"
									+ mdiary.getPtime()
									+ "</a>"
									+ "<div class=\"small-pic mar-t10\">习惯养成</div>");
						}
						mstatus.setMemberInfo(memberInfoCenterService
								.getMemberInfo(mdiary.getMid()));
						mstatus.setPraisenumber(memberPraiseCenterService
								.getMemberPraiseInListNumber(2, mdiary.getId()
										.intValue()));
						mstatus.setRcnumber(memberDiaryCenterService
								.getMemberDiaryInListNumber(mdiary.getId()
										.intValue()));
						mstatus.setCommentnumber(commentInfoFrontService
								.getCommentNumber(mdiary.getId().intValue(), 2));
						mstatus.setMemberPraise(memberPraiseCenterService
								.findMemberPraiseAll(memberInfo.getId()
										.intValue(), 2, mdiary.getId()
										.intValue()));
						mstatus.setId(mdiary.getId());
						mstatus.setTypename("习惯养成");
					}
				}
				sbu.append("<a class=\"blue\">"
						+ mstatus.getMemberInfo().getAccount() + " :</a>");
				sbu.append(" " + mstatus.getContent());
				sbu.append("</div>");
				sbu.append("<div class=\"user-talk\">");
				sbu.append("<div class=\"fl gray\">"
						+ DateUtil.getFormatDate(mstatus.getCtime(),
								"yyyy-MM-dd HH:mm") + "</div>");
				sbu.append("<div class=\"fr\">");
				if (type == 1) {
					sbu.append("<a id=\"praisenumber"
							+ mstatus.getId().intValue()
							+ "\" class=\"blue\" href=\"javascript:updateMemberPraiseAJAX(2,"
							+ mstatus.getId().intValue()
							+ ",'praisenumber"
							+ mstatus.getId().intValue()
							+ "')\">赞("
							+ mstatus.getPraisenumber()
							+ ")</a>┊<a class=\"blue\" href=\"javascript:fowardBack('"
							+ mstatus.getUrl()
							+ "&backUrl=','')\">评论("
							+ mstatus.getCommentnumber()
							+ ")</a>┊<a class=\"blue\" href=\"javascript:zz(2,'"
							+ mstatus.getId().intValue()
							+ "','"
							+ mstatus.getMemberInfo().getId()
							+ "','"
							+ memberInfo.getId()
							+ "')\">转载("
							+ mstatus.getRcnumber()
							+ ")</a>┊<a id=\"praisenumber"
							+ mstatus.getId().intValue()
							+ "\" class=\"blue\" href=\"javascript:addMemberCollectionAJAX(2,"
							+ mstatus.getId().intValue()
							+ ",'"
							+ mstatus.getMemberInfo().getId()
							+ "','"
							+ memberInfo.getId()
							+ "')\">收藏</a>┊<a class=\"blue\" href=\"javascript:jb(2,'"
							+ mstatus.getId().intValue() + "','"
							+ memberInfo.getId() + "','"
							+ mstatus.getMemberInfo().getId() + "')\">举报</a>");
				} else if (type == 2) {
					sbu.append("<a id=\"praisenumber"
							+ mstatus.getId().intValue()
							+ "\" class=\"blue\" href=\"javascript:updateMemberPraiseAJAX(2,"
							+ mstatus.getId().intValue()
							+ ",'praisenumber"
							+ mstatus.getId().intValue()
							+ "')\">赞("
							+ mstatus.getPraisenumber()
							+ ")</a>┊<a class=\"blue\" href=\"javascript:fowardBack('"
							+ mstatus.getUrl()
							+ "&backUrl=','')\">评论("
							+ mstatus.getCommentnumber()
							+ ")</a>┊<a class=\"blue\" href=\"javascript:zz(2,'"
							+ mstatus.getId().intValue()
							+ "','"
							+ mstatus.getMemberInfo().getId()
							+ "','"
							+ memberInfo.getId()
							+ "')\">转载("
							+ mstatus.getRcnumber()
							+ ")</a>┊<a id=\"praisenumber"
							+ mstatus.getId().intValue()
							+ "\" class=\"blue\" href=\"javascript:addMemberCollectionAJAX(2,"
							+ mstatus.getId().intValue()
							+ ",'"
							+ mstatus.getMemberInfo().getId()
							+ "','"
							+ memberInfo.getId()
							+ "')\">收藏</a>┊<a class=\"blue\" href=\"javascript:jb(2,'"
							+ mstatus.getId().intValue() + "','"
							+ memberInfo.getId() + "','"
							+ mstatus.getMemberInfo().getId() + "')\">举报</a>");
				} else if (type == 3) {
					sbu.append("<a id=\"praisenumber"
							+ mstatus.getId().intValue()
							+ "\" class=\"blue\" href=\"javascript:updateMemberPraiseAJAX(3,"
							+ mstatus.getId().intValue()
							+ ",'praisenumber"
							+ mstatus.getId().intValue()
							+ "')\">赞("
							+ mstatus.getPraisenumber()
							+ ")</a>┊<a class=\"blue\" href=\"javascript:fowardBack('"
							+ mstatus.getUrl()
							+ "&backUrl=','')\">评论("
							+ mstatus.getCommentnumber()
							+ ")</a>┊<a class=\"blue\" href=\"javascript:zz(3,'"
							+ mstatus.getId().intValue()
							+ "','"
							+ mstatus.getMemberInfo().getId()
							+ "','"
							+ memberInfo.getId()
							+ "')\">转载("
							+ mstatus.getRcnumber()
							+ ")</a>┊<a id=\"praisenumber"
							+ mstatus.getId().intValue()
							+ "\" class=\"blue\" href=\"javascript:addMemberCollectionAJAX(3,"
							+ mstatus.getId().intValue()
							+ ",'"
							+ mstatus.getMemberInfo().getId()
							+ "','"
							+ memberInfo.getId()
							+ "')\">收藏</a>┊<a class=\"blue\" href=\"javascript:jb(3,'"
							+ mstatus.getId().intValue() + "','"
							+ memberInfo.getId() + "','"
							+ mstatus.getMemberInfo().getId() + "')\">举报</a>");
				} else if (type == 4) {
					sbu.append("<a id=\"praisenumber"
							+ mstatus.getId().intValue()
							+ "\" class=\"blue\" href=\"javascript:updateMemberPraiseAJAX(4,"
							+ mstatus.getId().intValue()
							+ ",'praisenumber"
							+ mstatus.getId().intValue()
							+ "')\">赞("
							+ mstatus.getPraisenumber()
							+ ")</a>┊<a class=\"blue\" href=\"javascript:fowardBack('"
							+ mstatus.getUrl()
							+ "&backUrl=','')\">评论("
							+ mstatus.getCommentnumber()
							+ ")</a>┊<a id=\"praisenumber"
							+ mstatus.getId().intValue()
							+ "\" class=\"blue\" href=\"javascript:addMemberCollectionAJAX(4,"
							+ mstatus.getId().intValue()
							+ ",'"
							+ mstatus.getMemberInfo().getId()
							+ "','"
							+ memberInfo.getId()
							+ "')\">收藏</a>┊<a class=\"blue\" href=\"javascript:jb(4,'"
							+ mstatus.getId().intValue() + "','"
							+ memberInfo.getId() + "','"
							+ mstatus.getMemberInfo().getId() + "')\">举报</a>");
				} else if (type == 5) {
					sbu.append("<a id=\"praisenumber"
							+ mstatus.getId().intValue()
							+ "\" class=\"blue\" href=\"javascript:updateMemberPraiseAJAX(5,"
							+ mstatus.getId().intValue()
							+ ",'praisenumber"
							+ mstatus.getId().intValue()
							+ "')\">赞("
							+ mstatus.getPraisenumber()
							+ ")</a>┊<a class=\"blue\" href=\"javascript:fowardBack('"
							+ mstatus.getUrl()
							+ "&backUrl=','')\">评论("
							+ mstatus.getCommentnumber()
							+ ")</a>┊<a class=\"blue\" href=\"javascript:zz(5,'"
							+ mstatus.getId().intValue()
							+ "','"
							+ mstatus.getMemberInfo().getId()
							+ "','"
							+ memberInfo.getId()
							+ "')\">转载("
							+ mstatus.getRcnumber()
							+ ")</a>┊<a id=\"praisenumber"
							+ mstatus.getId().intValue()
							+ "\" class=\"blue\" href=\"javascript:addMemberCollectionAJAX(5,"
							+ mstatus.getId().intValue()
							+ ",'"
							+ mstatus.getMemberInfo().getId()
							+ "','"
							+ memberInfo.getId()
							+ "')\">收藏</a>┊<a class=\"blue\" href=\"javascript:jb(5,'"
							+ mstatus.getId().intValue() + "','"
							+ memberInfo.getId() + "','"
							+ mstatus.getMemberInfo().getId() + "')\">举报</a>");
				} else if (type == 10) {
					sbu.append("<a id=\"praisenumber"
							+ mstatus.getId().intValue()
							+ "\" class=\"blue\" href=\"javascript:updateMemberPraiseAJAX(10,"
							+ mstatus.getId().intValue()
							+ ",'praisenumber"
							+ mstatus.getId().intValue()
							+ "')\">赞("
							+ mstatus.getPraisenumber()
							+ ")</a>┊<a class=\"blue\" href=\"javascript:fowardBack('"
							+ mstatus.getUrl()
							+ "&backUrl=','')\">评论("
							+ mstatus.getCommentnumber()
							+ ")</a>┊<a class=\"blue\" href=\"javascript:zz(10,'"
							+ mstatus.getId().intValue()
							+ "','"
							+ mstatus.getMemberInfo().getId()
							+ "','"
							+ memberInfo.getId()
							+ "')\">转载("
							+ mstatus.getRcnumber()
							+ ")</a>┊<a id=\"praisenumber"
							+ mstatus.getId().intValue()
							+ "\" class=\"blue\" href=\"javascript:addMemberCollectionAJAX(10,"
							+ mstatus.getId().intValue()
							+ ",'"
							+ mstatus.getMemberInfo().getId()
							+ "','"
							+ memberInfo.getId()
							+ "')\">收藏</a>┊<a class=\"blue\" href=\"javascript:jb(10,'"
							+ mstatus.getId().intValue() + "','"
							+ memberInfo.getId() + "','"
							+ mstatus.getMemberInfo().getId() + "')\">举报</a>");
				}
				sbu.append("</div>");
				sbu.append("</div>");
				sbu.append("</div>");
				sbu.append("<div class=\"clear\"></div>");
				sbu.append("</div>");
			}
		}
		return sbu.toString();
	}

	/**
	 * 我的发布
	 * 
	 * @return
	 */
	public String mydtlist() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		return "success";
	}

	/**
	 * 我的疏导列表页
	 * 
	 * @return
	 */
	public String mysdlist() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		String hql = " from MemberDiary where isdel=0 and type<2 and mid="
				+ memberInfo.getId();
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = " ctime desc ";

		// 判断排序条件
		pageBean = memberDiaryCenterService.findMemberDiaryHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/mysdlist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlistsd(pageBean.getPageList()));
		return "success";
	}

	/**
	 * 重新封装list
	 * 
	 * @return
	 */
	public List getnewlistsd(List pagelist) {
		List newlist = new ArrayList();
		if (pagelist != null && pagelist.size() > 0) {
			for (int i = 0; i < pagelist.size(); i++) {
				MemberDiary mdiary = (MemberDiary) pagelist.get(i);
				mdiary.setMemberInfo(memberInfoCenterService
						.getMemberInfo(mdiary.getMid()));
				mdiary.setPraisenumber(memberPraiseCenterService
						.getMemberPraiseInListNumber(2, mdiary.getId()
								.intValue()));
				mdiary.setRcnumber(memberDiaryCenterService
						.getMemberDiaryInListNumber(mdiary.getId().intValue()));
				mdiary.setCommentnumber(commentInfoFrontService
						.getCommentNumber(mdiary.getId().intValue(), 2));
				mdiary.setMemberPraise(memberPraiseCenterService
						.findMemberPraiseAll(memberInfo.getId().intValue(), 2,
								mdiary.getId().intValue()));
				newlist.add(mdiary);
			}
		}
		return newlist;
	}

	/**
	 * 重新封装list
	 *
	 * @return
	 */
	public List getnewlistXGYC(List pagelist) {
		List newlist = new ArrayList();
		if (pagelist != null && pagelist.size() > 0) {
			for (int i = 0; i < pagelist.size(); i++) {
				MemberDiaryXGYC mdiaryXGYC = (MemberDiaryXGYC) pagelist.get(i);
				mdiaryXGYC.setMemberInfo(memberInfoCenterService
						.getMemberInfo(mdiaryXGYC.getMid()));
				mdiaryXGYC.setPraisenumber(memberPraiseCenterService
						.getMemberPraiseInListNumber(10, mdiaryXGYC.getId()
								.intValue()));
				mdiaryXGYC.setRcnumber(memberDiaryCenterService
						.getMemberDiaryInListNumber(mdiaryXGYC.getId().intValue()));
				mdiaryXGYC.setCommentnumber(commentInfoFrontService
						.getCommentNumber(mdiaryXGYC.getId().intValue(), 10));
				mdiaryXGYC.setMemberPraise(memberPraiseCenterService
						.findMemberPraiseAll(memberInfo.getId().intValue(), 10,
								mdiaryXGYC.getId().intValue()));
				newlist.add(mdiaryXGYC);
			}
		}
		return newlist;
	}

	/**
	 * 我的疏导进入页
	 * 
	 * @return
	 */
	public String mysdinfo() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		if (id > 0) {
			memberDiary = memberDiaryCenterService.getMemberDiary(id);
		} else if (dpage != 0) {
			memberDiary = new MemberDiary();
			memberDiary.setMid(memberInfo.getId());
			memberDiary.setIsdel(new Integer(0));
			memberDiary.setCtime(new Date());
			memberDiary.setPtime(new Date());
			memberDiary.setViewnumber(new Integer(0));
			memberDiary.setRcnumber(new Integer(0));
			memberDiary.setQx(new Integer(0));
			memberDiary.setType(type);
			memberDiaryCenterService.saveMemberDiary(memberDiary);
		}
		if (dpage == 1) {
			memberDiary.setFssq(request.getParameter("fssq"));
			memberDiaryCenterService.updateMemberDiary(memberDiary);
		} else if (dpage == 2) {
			memberDiary.setHzbx(request.getParameter("hzbx"));
			memberDiaryCenterService.updateMemberDiary(memberDiary);
		} else if (dpage == 3 && type == 1) {
			memberDiary.setHzbx(request.getParameter("hzbx"));
			memberDiaryCenterService.updateMemberDiary(memberDiary);
		} else if (dpage == 3 && type == 0) {
			memberDiary.setBxjj(request.getParameter("bxjj"));
			memberDiary.setFzkj(request.getParameter("fzkj"));
			memberDiary.setHzxy(request.getParameter("hzxy"));
			memberDiary.setXymz(request.getParameter("xymz"));
			memberDiaryCenterService.updateMemberDiary(memberDiary);
		} else if (dpage == 4) {
			memberDiary.setWdgs(request.getParameter("wdgs"));
			memberDiaryCenterService.updateMemberDiary(memberDiary);
		} else if (dpage == 5) {
			memberDiary.setNxxq(request.getParameter("nxxq"));
			memberDiaryCenterService.updateMemberDiary(memberDiary);
			// 添加会员动态信息 2015-04-14
			memberStatusCenterService
					.saveMemberStatus(
							memberInfo.getId(),
							2,
							memberDiary.getId(),
							DateUtil.getFormatDate(memberDiary.getPtime(),
									"yyyy-MM-dd") + "疏导", 0);
			// 添加获得积分
			IntegralConfig ic = integralConfigFrontService
					.findIntegralConfigInList(3);
			if (ic != null) {
				IntegralInfo integralInfo = new IntegralInfo(
						memberInfo.getId(), memberInfo.getAccount(), 3,
						memberDiary.getId(), ic.getScore());
				integralInfoFrontService.saveIntegralInfo(integralInfo);
				session.put(ConstantUtils.MEMBERINFO, memberInfoCenterService
						.getMemberInfo(memberInfo.getId()));
			}
			if (type == 1) { // 总结页面是不一样的
				return "my";
			}
		}
		return "success";
	}

	/**
	 * 我的疏导删除功能
	 * 
	 * @return
	 */
	public String mysddelete() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberDiary = memberDiaryCenterService
						.getMemberDiary(new Integer(idarrs[i].trim()));
				memberDiary.setIsdel(new Integer(1));
				memberDiaryCenterService.updateMemberDiary(memberDiary);
				// 删除动态信息
				memberStatusCenterService.deleteMemberStatusByCid(2,
						memberDiary.getId(), memberInfo.getId());
				// 删除获得的积分
				IntegralInfo integralInfo = integralInfoFrontService
						.deleteIntegralInfoByCid(3, memberDiary.getId(),
								memberInfo.getId());
				if (integralInfo != null) {
					session.put(ConstantUtils.MEMBERINFO, memberInfoCenterService
							.getMemberInfo(memberInfo.getId()));
				}
			}
			String msg = "删除成功";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 我的疏导详情页
	 * 
	 * @return
	 */
	public String showmysdinfo() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		if (id > 0) {
			memberDiary = memberDiaryCenterService.getMemberDiary(id);
		}
		int qx = memberDiary.getQx() == null ? 0 : memberDiary.getQx();
		if (qx == 1) { // 判断是不是好友
			if (memberInfo.getId().intValue() != memberDiary.getMid()
					.intValue()
					&& !memberFriendCenterService.findMemberFriends(
							memberInfo.getId(), memberDiary.getMid())) {
				return "qx1";
			}
		} else if (qx == 2) { // 判断是不是自己
			if (memberInfo.getId().intValue() != memberDiary.getMid()
					.intValue()) {
				return "qx2";
			}
		}
		memberDiary.setMemberInfo(memberInfoCenterService
				.getMemberInfo(memberDiary.getMid()));
		memberDiary
				.setPraisenumber(memberPraiseCenterService
						.getMemberPraiseInListNumber(2, memberDiary.getId()
								.intValue()));
		memberDiary.setRcnumber(memberDiaryCenterService
				.getMemberDiaryInListNumber(memberDiary.getId().intValue()));
		memberDiary.setCommentnumber(commentInfoFrontService.getCommentNumber(
				memberDiary.getId().intValue(), 2));
		memberDiary.setMemberPraise(memberPraiseCenterService
				.findMemberPraiseAll(memberInfo.getId().intValue(), 2,
						memberDiary.getId().intValue()));
		// 更新访问量
		if (memberDiary.getMid().intValue() != memberInfo.getId().intValue()) {
			memberDiary.setViewnumber(memberDiary.getViewnumber() == null ? 0
					: memberDiary.getViewnumber() + 1);
		}
		memberDiaryCenterService.updateMemberDiary(memberDiary);
		// 添加浏览记录
		memberLLJLCenterService.saveMemberLLJL(memberInfo.getId(), "[疏导]"
				+ memberDiary.getPtime(), "/membercenter/showmysdinfo?id="
				+ memberDiary.getId());
		return "success";
	}

	/**
	 * 我的浏览记录
	 */
	public String lljllist() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));

		String hql = " from MemberLLJL where isdel=0 and mid="
				+ memberInfo.getId();
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = " ctime desc ";

		// 判断排序条件
		pageBean = memberLLJLCenterService.findMemberLLJLHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/membercenter/lljllist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));

		return "success";
	}

	/**
	 * 浏览记录删除功能
	 * 
	 * @return
	 */
	public String lljldelete() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			MemberLLJL memberLLJL = null;
			for (int i = 0; i < idarrs.length; i++) {
				memberLLJL = memberLLJLCenterService.getMemberLLJL(new Integer(
						idarrs[i].trim()));
				memberLLJL.setIsdel(new Integer(1));
				memberLLJLCenterService.updateMemberLLJL(memberLLJL);
			}
			String msg = "删除成功";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 我的订单
	 * 
	 * @return
	 */
	public String orderlist() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		return "success";
	}

	/**
	 * 我的52周 列表页
	 * 
	 * @return
	 */
	public String zhoulist() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		String hql = " from MemberDiary52 where isdel=0 and mid="
				+ memberInfo.getId();
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = " ctime desc ";

		// 判断排序条件
		pageBean = memberDiary52CenterService.findMemberDiary52HQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/zhoulist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlistzhou(pageBean.getPageList()));
		return "success";
	}

	/**
	 * 我的52周 列表页
	 * 
	 * @return
	 */
	public String zhoulist1() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		String hql = " from MemberDiary52 where isdel=0 and mid="
				+ memberInfo.getId();
		hql += " and type="+type;
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = " ctime desc ";

		// 判断排序条件
		pageBean = memberDiary52CenterService.findMemberDiary52HQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/zhoulist1");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlistzhou(pageBean.getPageList()));
		return "success";
	}
	
	/**
	 * 重新封装list
	 * 
	 * @return
	 */
	public List getnewlistzhou(List pagelist) {
		List newlist = new ArrayList();
		if (pagelist != null && pagelist.size() > 0) {
			for (int i = 0; i < pagelist.size(); i++) {
				MemberDiary52 mdiary = (MemberDiary52) pagelist.get(i);
				mdiary.setMemberInfo(memberInfoCenterService
						.getMemberInfo(mdiary.getMid()));
				mdiary.setPraisenumber(memberPraiseCenterService
						.getMemberPraiseInListNumber(2, mdiary.getId()
								.intValue()));
				mdiary.setRcnumber(memberDiaryCenterService
						.getMemberDiaryInListNumber(mdiary.getId().intValue()));
				mdiary.setCommentnumber(commentInfoFrontService
						.getCommentNumber(mdiary.getId().intValue(), 2));
				mdiary.setMemberPraise(memberPraiseCenterService
						.findMemberPraiseAll(memberInfo.getId().intValue(), 2,
								mdiary.getId().intValue()));
				newlist.add(mdiary);
			}
		}
		return newlist;
	}

	/**
	 * 我的52周 内容页
	 * 
	 * @return
	 */
	public String zhouinfo() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		return "success";
	}

	/**
	 * 我的52周 内容页
	 * 
	 * @return
	 */
	public String zhouinfo1() {
		contentList = new ArrayList();
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		// 查询当前是52周的多少天。当第一次填写的时间超过365天，认为这是第一天。
		MemberDiary52 md = memberDiary52CenterService.getFirstMemberDiary52(
				memberInfo.getId().intValue(), type);
		if (md != null) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			day = DateUtil.countDays(df.format(md.getPtime()),
					df.format(new Date())) + 1;
		}
		// 当是第一次进入的时候，才需要验证余额是否大于当前消费金额
		if (day == 1) {
			StoreConfig storeConfig = storeConfigCenterService
					.findStoreConfigInList(type + 1);
			if (type == 1) { // 为自己
				storeConfig = storeConfigCenterService.findStoreConfigInList(2);
			}
			if (storeConfig != null) {
				int money = storeConfig.getMoney();
				if (money > 0) {
					// 判断余额是否大于当前消费的金额，
					Double sumprice = memberStoreCenterService
							.getMemberStoreSumPrice(memberInfo.getId()
									.intValue());
					if (sumprice < money) { // 余额 小于 后台设置的 金额 ，就让进入充值界面
//						return "store";
					}
				}
			}
		}
		List<MemberDiary52Content> list = memberDiary52ContentCenterService
				.findMemberDiary52ContentInList(day, 0);
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				MemberDiary52Content memberDiary52Content = list.get(i);
				String tcontent = memberDiary52Content.getContent();
				String[] tcontentAll = tcontent.split("<hr />");
				for (int j = 0; j < tcontentAll.length; j++) {
//					 System.out.println("tcontentAll[j]==========" + tcontentAll[j]);
//					 去掉所有html元素,
					String str = tcontentAll[j].replaceAll("</p>", "</p>||")
							.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "")
							.replaceAll("</[a-zA-Z]+[1-9]?>", "")
							.replaceAll("\\s*", "");
//					 System.out.println("str==========" + str);
					contentList.add(str);
				}

			}

		}

		return "success";
	}

	/**
	 * 我的52周 内容页
	 * 
	 * @return
	 */
	public String zhouinfo2() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		// 查询当前是52周的多少天。当第一次填写的时间超过365天，认为这是第一天。
		MemberDiary52 md = memberDiary52CenterService.getFirstMemberDiary52(
				memberInfo.getId().intValue(), type);
		if (md != null) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			day = DateUtil.countDays(df.format(md.getPtime()),
					df.format(new Date())) + 1;
			// 判断今天是否写入过日记
			MemberDiary52 newmd = memberDiary52CenterService
					.getNewMemberDiary52(memberInfo.getId().intValue(), type);
			int newday = DateUtil.countDays(df.format(newmd.getPtime()),
					df.format(new Date()));
			if (newday == 0 && id <= 0) {
				day = 0;
				String zhou52 = " 为家庭 ";
				if (type == 1) {
					zhou52 = " 为自己 ";
				}
				session.put("saveinfomsg", "今天已经写过" + zhou52 + "日志了！");
			}
		}
		if (id > 0) {
			memberDiary52 = memberDiary52CenterService.getMemberDiary52(id);
			if (day > 0 && day % 7 == 0) {
				session.put("saveinfomsg", "日记录保存成功，开始写周记录了");
			}
		} else {
			memberDiary52 = new MemberDiary52();
			memberDiary52.setMid(memberInfo.getId());
			memberDiary52.setIsdel(new Integer(0));
			memberDiary52.setCtime(new Date());
			memberDiary52.setPtime(new Date());
			memberDiary52.setViewnumber(new Integer(0));
			memberDiary52.setRcnumber(new Integer(0));
			memberDiary52.setQx(new Integer(0));
			memberDiary52.setType(type);
			memberDiary52.setDay(day);
		}
		return "success";
	}

	/**
	 * 我的52周 内容页 周记录
	 * 
	 * @return
	 */
	public String zhouinfo3() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		// 查询当前是52周的多少天。当第一次填写的时间超过365天，认为这是第一天。
		MemberDiary52 md = memberDiary52CenterService.getFirstMemberDiary52(
				memberInfo.getId().intValue(), type);
		if (md != null) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			day = DateUtil.countDays(df.format(md.getPtime()),
					df.format(new Date()));
		}
		if (id > 0) {
			memberDiary52 = memberDiary52CenterService.getMemberDiary52(id);
		} else {
			memberDiary52 = new MemberDiary52();
			memberDiary52.setMid(memberInfo.getId());
			memberDiary52.setIsdel(new Integer(0));
			memberDiary52.setCtime(new Date());
			memberDiary52.setPtime(new Date());
			memberDiary52.setViewnumber(new Integer(0));
			memberDiary52.setRcnumber(new Integer(0));
			memberDiary52.setQx(new Integer(0));
			memberDiary52.setType(type);
			memberDiary52.setDay(day);
		}
		return "success";
	}

	/**
	 * 我的52周 内容页 周记录之后
	 * 
	 * @return
	 */
	public String zhouinfo4() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		// 查询当前是52周的多少天。当第一次填写的时间超过365天，认为这是第一天。
		MemberDiary52 md = memberDiary52CenterService.getFirstMemberDiary52(
				memberInfo.getId().intValue(), type);
		if (md != null) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			day = DateUtil.countDays(df.format(md.getPtime()),
					df.format(new Date())) + 1;
		}
		//
		List<MemberDiary52Content> list = memberDiary52ContentCenterService
				.findMemberDiary52ContentInList(day, 1);
		if (list != null && !list.isEmpty()) {
			contentList = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				MemberDiary52Content memberDiary52Content = list.get(i);
				String tcontent = memberDiary52Content.getContent();
				String[] tcontentAll = tcontent.split("<hr />");
				for (int j = 0; j < tcontentAll.length; j++) {
					// System.out
					// .println("tcontentAll[j]==========" + tcontentAll[j]);
					// 去掉所有html元素,
					String str = tcontentAll[j].replaceAll("</p>", "</p>||")
							.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "")
							.replaceAll("</[a-zA-Z]+[1-9]?>", "")
							.replaceAll("\\s*", "");
					// System.out
					// .println("str==========" + str);
					contentList.add(str);
				}

			}

		}

		return "success";
	}

	/**
	 * 会员52周添加功能
	 * 
	 * @return
	 */
	public String savezhouinfo() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String msg = "修改成功！";
		if (memberReportCenterService.getIsMemberReport(memberInfo.getId()
				.intValue())) {
			msg = "操作失败，您的账号，正在拘留当中！";
		} else {
			if (memberDiary52.getId() == null) {
				day = memberDiary52.getDay();
				type = memberDiary52.getType();
				// 当是第一次进入的时候，才需要验证余额是否大于当前消费金额
				if (day == 1) {
					StoreConfig storeConfig = storeConfigCenterService
							.findStoreConfigInList(type + 1);
					if (storeConfig != null) {
						int money = storeConfig.getMoney();
						if (money > 0) {
							// 判断余额是否大于当前消费的金额，
							Double sumprice = memberStoreCenterService
									.getMemberStoreSumPrice(memberInfo.getId()
											.intValue());
							if (sumprice < money) { // 余额 小于 后台设置的 金额 ，就让进入充值界面
								return "store";
							}
						}
						// 添加扣除金额信息
						MemberStore memberStore = new MemberStore();
						memberStore.setMid(memberInfo.getId());
						memberStore.setAccount(memberInfo.getAccount());
						memberStore.setEmail(memberInfo.getEmail());
						memberStore.setIsdel(new Integer(0));
						memberStore.setCtime(new Date());
						memberStore.setState(new Integer(1));
						memberStore.setType(new Integer(type + 3)); // 1:52周 家庭
																	// 2:52周 自己
						memberStore.setTid(0);
						memberStore.setPrice(new Double(-money));
						String c_date = DateUtil.getFormatDate(new Date(),
								"yyyyMMdd");
						long code_10 = System.currentTimeMillis(); // 10为一个随机码
						memberStore.setCode(c_date + "-" + code_10);
						memberStoreCenterService.saveMemberStore(memberStore);
						// 添加完成
					}
				}
				msg = "添加成功！";
				memberDiary52.setMid(memberInfo.getId());
				memberDiary52.setIsdel(new Integer(0));
				memberDiary52.setCtime(new Date());
				memberDiary52.setViewnumber(new Integer(0));
				memberDiary52CenterService.saveMemberDiary52(memberDiary52);
				// 添加会员动态信息 2013-08-13
				memberStatusCenterService.saveMemberStatus(
						memberInfo.getId(),
						7,
						memberDiary52.getId(),
						DateUtil.getFormatDate(memberDiary52.getPtime(),
								"yyyy-MM-dd") + "52周", qx);
				// 添加获得积分
				IntegralConfig ic = integralConfigFrontService
						.findIntegralConfigInList(7);
				if (ic != null) {
					IntegralInfo integralInfo = new IntegralInfo(
							memberInfo.getId(), memberInfo.getAccount(), 7,
							memberDiary52.getId(), ic.getScore());
					integralInfoFrontService.saveIntegralInfo(integralInfo);
					session.put(ConstantUtils.MEMBERINFO, memberInfoCenterService
							.getMemberInfo(memberInfo.getId()));
				}
				// 清空提醒短信
				memberMessageCenterService.updateMemberMessageZhou(24,
						memberInfo.getId(), type);
			} else {
				// 同步更新会员动态信息 2013-10-09
				memberStatusCenterService.updateMemberStatus(
						memberInfo.getId(), 7, memberDiary52.getId());
			}
			memberDiary52CenterService.updateMemberDiary52(memberDiary52);
		}
		session.put("saveinfomsg", msg);
		return SUCCESS;
	}

	/**
	 * 我的52周删除功能
	 * 
	 * @return
	 */
	public String zhoudelete() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberDiary52 = memberDiary52CenterService
						.getMemberDiary52(new Integer(idarrs[i].trim()));
				memberDiary52.setIsdel(new Integer(1));
				memberDiary52CenterService.updateMemberDiary52(memberDiary52);
				// 删除动态信息
				memberStatusCenterService.deleteMemberStatusByCid(6,
						memberDiary52.getId(), memberInfo.getId());
				// 删除获得的积分
				IntegralInfo integralInfo = integralInfoFrontService
						.deleteIntegralInfoByCid(7, memberDiary52.getId(),
								memberInfo.getId());
				if (integralInfo != null) {
					session.put(ConstantUtils.MEMBERINFO, memberInfoCenterService
							.getMemberInfo(memberInfo.getId()));
				}
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 我的52周详情页
	 * 
	 * @return
	 */
	public String showzhouinfo() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		if (id > 0) {
			memberDiary52 = memberDiary52CenterService.getMemberDiary52(id);
		}
		int qx = memberDiary52.getQx() == null ? 0 : memberDiary52.getQx();
//		if (qx == 1) { // 判断是不是好友
//			if (memberInfo.getId().intValue() != memberDiary52.getMid()
//					.intValue()
//					&& !memberFriendCenterService.findMemberFriends(
//							memberInfo.getId(), memberDiary52.getMid())) {
//				return "qx1";
//			}
//		} else if (qx == 2) { // 判断是不是自己
//			if (memberInfo.getId().intValue() != memberDiary52.getMid()
//					.intValue()) {
//				return "qx2";
//			}
//		}
		memberDiary52.setMemberInfo(memberInfoCenterService
				.getMemberInfo(memberDiary52.getMid()));
		memberDiary52.setPraisenumber(memberPraiseCenterService
				.getMemberPraiseInListNumber(2, memberDiary52.getId()
						.intValue()));
		memberDiary52.setRcnumber(memberDiaryCenterService
				.getMemberDiaryInListNumber(memberDiary52.getId().intValue()));
		memberDiary52.setCommentnumber(commentInfoFrontService
				.getCommentNumber(memberDiary52.getId().intValue(), 2));
		memberDiary52.setMemberPraise(memberPraiseCenterService
				.findMemberPraiseAll(memberInfo.getId().intValue(), 2,
						memberDiary52.getId().intValue()));
		// 更新访问量
		if (memberDiary52.getMid().intValue() != memberInfo.getId().intValue()) {
			memberDiary52
					.setViewnumber(memberDiary52.getViewnumber() == null ? 0
							: memberDiary52.getViewnumber() + 1);
		}
		memberDiary52CenterService.updateMemberDiary52(memberDiary52);
		// 添加浏览记录
		memberLLJLCenterService.saveMemberLLJL(memberInfo.getId(), "[52周]"
				+ memberDiary52.getPtime(), "/membercenter/showzhouinfo?id="
				+ memberDiary52.getId());
		return "success";
	}

	/**
	 * 我的家庭
	 * 
	 * @return
	 */
	public String familylist() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String hql = " from MemberFriend where isdel=0 and type=0 and isagree=1 and (mid="
				+ memberInfo.getId() + " or fid=" + memberInfo.getId() + " )";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = "";
		sortstr = " id desc ";

		pageSize = 12;
		// 判断排序条件
		pageBean = memberFriendCenterService.findMemberFriendHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/familylist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewflist(pageBean.getPageList()));
		return "success";
	}

	/**
	 * 重新封装list
	 * 
	 * @return
	 */
	public List getnewflist(List pagelist) {
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
	 * 我的 众校之星
	 * 
	 * @return
	 */
	public String zxzxlist() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
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
		sortstr = " lastlogintime desc, id desc ";

		pageSize = 12;
		// 判断排序条件
		pageBean = memberInfoCenterService.findMemberInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/membercenter/zxzxlist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewmlist(pageBean.getPageList()));
		return "success";
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

	/**
	 * 所有分享
	 * 
	 * @return
	 */
	public String allfxlist() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		// 动态的信息
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		String hql = " from MemberStatus where isdel=0 and qx=0";
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = " ptime desc ";

		// 判断排序条件
		pageBean = memberStatusCenterService.findMemberStatusHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/allfxlist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
		return "success";
	}

	/**
	 * 我的分享
	 * 
	 * @return
	 */
	public String myfxlist() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		// 动态的信息
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		String hql = " from MemberStatus where isdel=0 and mid="
				+ memberInfo.getId();
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = " ptime desc ";

		// 判断排序条件
		pageBean = memberStatusCenterService.findMemberStatusHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/myfxlist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlist(pageBean.getPageList()));
		return "success";
	}

	/**
	 * 我要分享
	 * 
	 * @return
	 */
	public String myyfxlist() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		return "success";
	}

	/**
	 * 发布的活动 11
	 * 
	 * @return
	 */
	public String myfblaborlist() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String hql = " from LaborInfo where isdel=0 and mid ="
				+ memberInfo.getId() + "";// 企业会员，我的活动，就是企业发布的活动信息。
		// if (memberInfo.getType() == 0) { // 普通会员，我的活动，就是参加了某个活动，然后显示的活动。
		// String lids = laborReplyInfoFrontService
		// .findLaborReplyInfos(memberInfo.getId());
		// if (lids.length() > 0) {
		// hql = " from LaborInfo where isdel=0 and id in (" + lids + ")";
		// }
		// }
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
		pageBean.setAction("/membercenter/myfblaborlist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlistLabor(pageBean.getPageList()));
		return SUCCESS;
	}

	/**
	 * 重新封装list
	 * 
	 * @return
	 */
	public List getnewlistLabor(List pagelist) {
		List newlist = new ArrayList();
		if (pagelist != null && pagelist.size() > 0) {
			for (int i = 0; i < pagelist.size(); i++) {
				LaborInfo cinfo = (LaborInfo) pagelist.get(i);
				cinfo.setReplycount(laborReplyInfoFrontService
						.findLaborReplyInfoCount(cinfo.getId()));
				cinfo.setMemberInfo(memberInfoCenterService.getMemberInfo(cinfo
						.getMid()));
				cinfo.setRids(laborReplyInfoFrontService
						.findLaborReplyInfoIds(cinfo.getId()));
				newlist.add(cinfo);
			}
		}
		return newlist;
	}

	/**
	 * 编辑活动
	 * 
	 * @return
	 */
	public String myfblaborinfo() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (id > 0) {
			laborInfo = laborInfoFrontService.getLaborInfo(id);
		}
		// 初始化信息
		if (laborInfo == null) {
			laborInfo = new LaborInfo();
			laborInfo.setIsdel(new Integer(0));
			laborInfo.setCid(new Integer(1));
			laborInfo.setScore(new Integer(0));
			laborInfo.setState(new Integer(0));
		}
		return SUCCESS;
	}

	/**
	 * 添加活动
	 * 
	 * @return
	 */
	public String savemyfblaborinfo() {
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
		laborInfo.setState(new Integer(0)); // 修改后，状态都置为 待审核状态
		laborInfoFrontService.updateLaborInfo(laborInfo);
		session.put("saveinfomsg", msg);
		return SUCCESS;
	}

	/**
	 * 修改活动
	 * 
	 * @return
	 */
	public String updatemyfblaborinfo() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				laborInfo = laborInfoFrontService.getLaborInfo(new Integer(
						idarrs[i].trim()));
				laborInfo.setIsdel(new Integer(1));
				laborInfoFrontService.updateLaborInfo(laborInfo);
			}
			String msg = "关闭成功！";
			session.put("msg", msg);
		}
		return "success";
	}

	/**
	 * 查看活动
	 * 
	 * @return
	 */
	public String showmyfblaborinfo() {
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
		}
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
	 * 发布的互动 12
	 * 
	 * @return
	 */
	public String myfbconsultlist() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		String hql = " from ConsultInfo where isdel=0 and mid="
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

		// 判断排序条件
		pageBean = consultInfoFrontService.findConsultInfoHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/membercenter/myfbconsultlist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlistconsult(pageBean.getPageList()));
		return "success";
	}

	/**
	 * 重新封装list
	 * 
	 * @return
	 */
	public List getnewlistconsult(List pagelist) {
		List newlist = new ArrayList();
		if (pagelist != null && pagelist.size() > 0) {
			for (int i = 0; i < pagelist.size(); i++) {
				ConsultInfo cinfo = (ConsultInfo) pagelist.get(i);
				cinfo.setReplycount(consultReplyInfoFrontService
						.findConsultReplyInfoCount(cinfo.getId()));
				cinfo.setReplyisgreecount(consultReplyInfoFrontService
						.findConsultReplyInfoIsgreeCount(cinfo.getId()));
				newlist.add(cinfo);
			}
		}
		return newlist;
	}

	/**
	 * 编辑互动
	 * 
	 * @return
	 */
	public String myfbconsultinfo() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
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
			consultInfo.setState(new Integer(0));
			consultInfo.setSort(new Integer(0));
			consultInfo.setMid(memberInfo.getId());
			consultInfo.setType(new Integer(0));
			consultInfo.setScore(new Integer(5));
			consultInfo.setViewnumber(new Integer(0));
		}
		return "success";
	}

	/**
	 * 添加互动
	 * 
	 * @return
	 */
	public String savemyfbconsultinfo() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		String msg = "修改成功！";
		if (consultInfo.getId() == null) {
			msg = "添加成功！";
			consultInfo.setMid(memberInfo.getId());
			consultInfo.setIsdel(new Integer(0));
			consultInfo.setCtime(new Date());
			consultInfoFrontService.saveConsultInfo(consultInfo);
			consultInfo.setSort(consultInfo.getId());
		}
		consultInfoFrontService.updateConsultInfo(consultInfo);
		session.put("saveinfomsg", msg);
		return "success";
	}

	/**
	 * 修改互动
	 * 
	 * @return
	 */
	public String updatemyfbconsultinfo() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				consultInfo = consultInfoFrontService
						.getConsultInfo(new Integer(idarrs[i].trim()));
				consultInfo.setIsdel(new Integer(1));
				consultInfoFrontService.updateConsultInfo(consultInfo);
			}
			String msg = "关闭成功";
			session.put("msg", msg);
		}
		return "success";
	}

	/**
	 * 查看互动
	 * 
	 * @return
	 */
	public String showmyfbconsultinfo() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		if (id > 0) {
			consultInfo = consultInfoFrontService.getConsultInfo(id);
			consultreplylist = consultReplyInfoFrontService
					.findConsultReplyInfoInList(id);
			consultInfo.setReplyisgreecount(consultReplyInfoFrontService
					.findConsultReplyInfoIsgreeCount(consultInfo.getId()));
			consultInfo.setMemberInfo(memberInfoCenterService
					.getMemberInfo(consultInfo.getMid()));
		}
		return "success";
	}

	/**
	 * 我的作业
	 * 
	 * @return
	 */
	public String myzylist() {
		// 动态的信息
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		String hql = " from MemberMood where isdel=0 and type=1 and mid="
				+ memberInfo.getId();
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = " ctime desc ";

		// 判断排序条件
		pageBean = memberMoodCenterService.findMemberMoodHQLList(hql, sortstr,
				map, currentPage, pageSize);
		pageBean.setAction("/membercenter/myzylist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlistMood(pageBean.getPageList()));
		return "success";
	}

	/**
	 * 好友作业
	 * 
	 * @return
	 */
	public String myzyflist() {
		// 动态的信息
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		String fids = memberFriendCenterService.findMemberFriends(memberInfo
				.getId().intValue());
		if (StringUtils.isNotBlank(fids)) {
			String hql = " from MemberMood where isdel=0 and type=1 and mid in ("
					+ fids + ") ";
			Map map = new HashMap();
			String parameter = "";
			// 默认排序 最新时间
			String sortstr = " ctime desc ";

			// 判断排序条件
			pageBean = memberMoodCenterService.findMemberMoodHQLList(hql,
					sortstr, map, currentPage, pageSize);
			pageBean.setAction("/membercenter/myzyflist");
			backUrl = pageBean.getAction() + "?pageSize="
					+ pageBean.getPageSize() + "&currentPage="
					+ pageBean.getCurrentPage() + parameter;
			pageBean.setGopageTemplate(request
					.getRealPath("/member/center/gopage/gopage.html"));
			pageBean.setPageList(getnewlistMood(pageBean.getPageList()));
		}
		return "success";
	}

	/**
	 * 重新封装list
	 * 
	 * @return
	 */
	public List getnewlistMood(List pagelist) {
		List newlist = new ArrayList();
		if (pagelist != null && pagelist.size() > 0) {
			for (int i = 0; i < pagelist.size(); i++) {
				MemberMood cinfo = (MemberMood) pagelist.get(i);
				cinfo.setMemberInfo(memberInfoCenterService.getMemberInfo(cinfo
						.getMid()));
				newlist.add(cinfo);
			}
		}
		return newlist;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MemberLogCenterService getMemberLogCenterService() {
		return memberLogCenterService;
	}

	public void setMemberLogCenterService(
			MemberLogCenterService memberLogCenterService) {
		this.memberLogCenterService = memberLogCenterService;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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

	public MemberMoodCenterService getMemberMoodCenterService() {
		return memberMoodCenterService;
	}

	public void setMemberMoodCenterService(
			MemberMoodCenterService memberMoodCenterService) {
		this.memberMoodCenterService = memberMoodCenterService;
	}

	public MemberBlogCenterService getMemberBlogCenterService() {
		return memberBlogCenterService;
	}

	public void setMemberBlogCenterService(
			MemberBlogCenterService memberBlogCenterService) {
		this.memberBlogCenterService = memberBlogCenterService;
	}

	public MemberBlogClassCenterService getMemberBlogClassCenterService() {
		return memberBlogClassCenterService;
	}

	public void setMemberBlogClassCenterService(
			MemberBlogClassCenterService memberBlogClassCenterService) {
		this.memberBlogClassCenterService = memberBlogClassCenterService;
	}

	public MemberPhotoCenterService getMemberPhotoCenterService() {
		return memberPhotoCenterService;
	}

	public void setMemberPhotoCenterService(
			MemberPhotoCenterService memberPhotoCenterService) {
		this.memberPhotoCenterService = memberPhotoCenterService;
	}

	public MemberPhotoGroupCenterService getMemberPhotoGroupCenterService() {
		return memberPhotoGroupCenterService;
	}

	public void setMemberPhotoGroupCenterService(
			MemberPhotoGroupCenterService memberPhotoGroupCenterService) {
		this.memberPhotoGroupCenterService = memberPhotoGroupCenterService;
	}

	public VedioInfoFrontService getVedioInfoFrontService() {
		return vedioInfoFrontService;
	}

	public void setVedioInfoFrontService(
			VedioInfoFrontService vedioInfoFrontService) {
		this.vedioInfoFrontService = vedioInfoFrontService;
	}

	public VedioClassFrontService getVedioClassFrontService() {
		return vedioClassFrontService;
	}

	public void setVedioClassFrontService(
			VedioClassFrontService vedioClassFrontService) {
		this.vedioClassFrontService = vedioClassFrontService;
	}

	public MemberStatusCenterService getMemberStatusCenterService() {
		return memberStatusCenterService;
	}

	public void setMemberStatusCenterService(
			MemberStatusCenterService memberStatusCenterService) {
		this.memberStatusCenterService = memberStatusCenterService;
	}

	public MemberFriendCenterService getMemberFriendCenterService() {
		return memberFriendCenterService;
	}

	public void setMemberFriendCenterService(
			MemberFriendCenterService memberFriendCenterService) {
		this.memberFriendCenterService = memberFriendCenterService;
	}

	public List getFstatuslist() {
		return fstatuslist;
	}

	public void setFstatuslist(List fstatuslist) {
		this.fstatuslist = fstatuslist;
	}

	public List getFcstatuslist() {
		return fcstatuslist;
	}

	public void setFcstatuslist(List fcstatuslist) {
		this.fcstatuslist = fcstatuslist;
	}

	public MemberMood getMemberMood() {
		return memberMood;
	}

	public void setMemberMood(MemberMood memberMood) {
		this.memberMood = memberMood;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getClassid() {
		return classid;
	}

	public void setClassid(int classid) {
		this.classid = classid;
	}

	public List getClasslist() {
		return classlist;
	}

	public void setClasslist(List classlist) {
		this.classlist = classlist;
	}

	public MemberBlog getMemberBlog() {
		return memberBlog;
	}

	public void setMemberBlog(MemberBlog memberBlog) {
		this.memberBlog = memberBlog;
	}

	public VedioInfo getVedioInfo() {
		return vedioInfo;
	}

	public void setVedioInfo(VedioInfo vedioInfo) {
		this.vedioInfo = vedioInfo;
	}

	public List getAllstatuslist() {
		return allstatuslist;
	}

	public void setAllstatuslist(List allstatuslist) {
		this.allstatuslist = allstatuslist;
	}

	public List getMayfriendlist() {
		return mayfriendlist;
	}

	public void setMayfriendlist(List mayfriendlist) {
		this.mayfriendlist = mayfriendlist;
	}

	public List getZxfriendlist() {
		return zxfriendlist;
	}

	public void setZxfriendlist(List zxfriendlist) {
		this.zxfriendlist = zxfriendlist;
	}

	public List getZxcfriendlist() {
		return zxcfriendlist;
	}

	public void setZxcfriendlist(List zxcfriendlist) {
		this.zxcfriendlist = zxcfriendlist;
	}

	public List getHotcontentlist() {
		return hotcontentlist;
	}

	public void setHotcontentlist(List hotcontentlist) {
		this.hotcontentlist = hotcontentlist;
	}

	public List getHotccontentlist() {
		return hotccontentlist;
	}

	public void setHotccontentlist(List hotccontentlist) {
		this.hotccontentlist = hotccontentlist;
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

	public MemberInfo getCmemberInfo() {
		return cmemberInfo;
	}

	public void setCmemberInfo(MemberInfo cmemberInfo) {
		this.cmemberInfo = cmemberInfo;
	}

	public int getIsindex() {
		return isindex;
	}

	public void setIsindex(int isindex) {
		this.isindex = isindex;
	}

	public MemberMessageCenterService getMemberMessageCenterService() {
		return memberMessageCenterService;
	}

	public void setMemberMessageCenterService(
			MemberMessageCenterService memberMessageCenterService) {
		this.memberMessageCenterService = memberMessageCenterService;
	}

	public MemberReportCenterService getMemberReportCenterService() {
		return memberReportCenterService;
	}

	public void setMemberReportCenterService(
			MemberReportCenterService memberReportCenterService) {
		this.memberReportCenterService = memberReportCenterService;
	}

	public int getIsmessagenum() {
		return ismessagenum;
	}

	public void setIsmessagenum(int ismessagenum) {
		this.ismessagenum = ismessagenum;
	}

	public int getShfriendnum() {
		return shfriendnum;
	}

	public void setShfriendnum(int shfriendnum) {
		this.shfriendnum = shfriendnum;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public LaborInfoFrontService getLaborInfoFrontService() {
		return laborInfoFrontService;
	}

	public void setLaborInfoFrontService(
			LaborInfoFrontService laborInfoFrontService) {
		this.laborInfoFrontService = laborInfoFrontService;
	}

	public LaborReplyInfoFrontService getLaborReplyInfoFrontService() {
		return laborReplyInfoFrontService;
	}

	public void setLaborReplyInfoFrontService(
			LaborReplyInfoFrontService laborReplyInfoFrontService) {
		this.laborReplyInfoFrontService = laborReplyInfoFrontService;
	}

	public int getActivitynum() {
		return activitynum;
	}

	public void setActivitynum(int activitynum) {
		this.activitynum = activitynum;
	}

	public int getCfriendnum() {
		return cfriendnum;
	}

	public void setCfriendnum(int cfriendnum) {
		this.cfriendnum = cfriendnum;
	}

	public int getDiarycommentnum() {
		return diarycommentnum;
	}

	public void setDiarycommentnum(int diarycommentnum) {
		this.diarycommentnum = diarycommentnum;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public MemberStoreCenterService getMemberStoreCenterService() {
		return memberStoreCenterService;
	}

	public void setMemberStoreCenterService(
			MemberStoreCenterService memberStoreCenterService) {
		this.memberStoreCenterService = memberStoreCenterService;
	}

	public MemberDiaryCenterService getMemberDiaryCenterService() {
		return memberDiaryCenterService;
	}

	public void setMemberDiaryCenterService(
			MemberDiaryCenterService memberDiaryCenterService) {
		this.memberDiaryCenterService = memberDiaryCenterService;
	}

	public MemberDiaryXGYCCenterService getMemberDiaryXGYCCenterService() {
		return memberDiaryXGYCCenterService;
	}

	public void setMemberDiaryXGYCCenterService(
			MemberDiaryXGYCCenterService memberDiaryXGYCCenterService) {
		this.memberDiaryXGYCCenterService = memberDiaryXGYCCenterService;
	}

	public MemberDiary getMemberDiary() {
		return memberDiary;
	}

	public void setMemberDiary(MemberDiary memberDiary) {
		this.memberDiary = memberDiary;
	}

	public int getQx() {
		return qx;
	}

	public void setQx(int qx) {
		this.qx = qx;
	}

	public List getLljllist() {
		return lljllist;
	}

	public void setLljllist(List lljllist) {
		this.lljllist = lljllist;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public MemberLLJLCenterService getMemberLLJLCenterService() {
		return memberLLJLCenterService;
	}

	public void setMemberLLJLCenterService(
			MemberLLJLCenterService memberLLJLCenterService) {
		this.memberLLJLCenterService = memberLLJLCenterService;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public IntegralInfoFrontService getIntegralInfoFrontService() {
		return integralInfoFrontService;
	}

	public void setIntegralInfoFrontService(
			IntegralInfoFrontService integralInfoFrontService) {
		this.integralInfoFrontService = integralInfoFrontService;
	}

	public IntegralConfigFrontService getIntegralConfigFrontService() {
		return integralConfigFrontService;
	}

	public void setIntegralConfigFrontService(
			IntegralConfigFrontService integralConfigFrontService) {
		this.integralConfigFrontService = integralConfigFrontService;
	}

	public int getDpage() {
		return dpage;
	}

	public void setDpage(int dpage) {
		this.dpage = dpage;
	}

	public LaborInfo getLaborInfo() {
		return laborInfo;
	}

	public void setLaborInfo(LaborInfo laborInfo) {
		this.laborInfo = laborInfo;
	}

	public String getSearchtitle() {
		return searchtitle;
	}

	public void setSearchtitle(String searchtitle) {
		this.searchtitle = searchtitle;
	}

	public List getLaborreplylist() {
		return laborreplylist;
	}

	public void setLaborreplylist(List laborreplylist) {
		this.laborreplylist = laborreplylist;
	}

	public String getRids() {
		return rids;
	}

	public void setRids(String rids) {
		this.rids = rids;
	}

	public ConsultInfoFrontService getConsultInfoFrontService() {
		return consultInfoFrontService;
	}

	public void setConsultInfoFrontService(
			ConsultInfoFrontService consultInfoFrontService) {
		this.consultInfoFrontService = consultInfoFrontService;
	}

	public ConsultReplyInfoFrontService getConsultReplyInfoFrontService() {
		return consultReplyInfoFrontService;
	}

	public void setConsultReplyInfoFrontService(
			ConsultReplyInfoFrontService consultReplyInfoFrontService) {
		this.consultReplyInfoFrontService = consultReplyInfoFrontService;
	}

	public ConsultInfo getConsultInfo() {
		return consultInfo;
	}

	public void setConsultInfo(ConsultInfo consultInfo) {
		this.consultInfo = consultInfo;
	}

	public List getConsultreplylist() {
		return consultreplylist;
	}

	public void setConsultreplylist(List consultreplylist) {
		this.consultreplylist = consultreplylist;
	}

	public MemberDiary52CenterService getMemberDiary52CenterService() {
		return memberDiary52CenterService;
	}

	public void setMemberDiary52CenterService(
			MemberDiary52CenterService memberDiary52CenterService) {
		this.memberDiary52CenterService = memberDiary52CenterService;
	}

	public MemberDiary52 getMemberDiary52() {
		return memberDiary52;
	}

	public void setMemberDiary52(MemberDiary52 memberDiary52) {
		this.memberDiary52 = memberDiary52;
	}

	public MemberDiaryXGYC getMemberDiaryXGYC() {
		return memberDiaryXGYC;
	}

	public void setMemberDiaryXGYC(MemberDiaryXGYC memberDiaryXGYC) {
		this.memberDiaryXGYC = memberDiaryXGYC;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public MemberDiary52ContentCenterService getMemberDiary52ContentCenterService() {
		return memberDiary52ContentCenterService;
	}

	public void setMemberDiary52ContentCenterService(
			MemberDiary52ContentCenterService memberDiary52ContentCenterService) {
		this.memberDiary52ContentCenterService = memberDiary52ContentCenterService;
	}

	public List getContentList() {
		return contentList;
	}

	public void setContentList(List contentList) {
		this.contentList = contentList;
	}

	public StoreConfigCenterService getStoreConfigCenterService() {
		return storeConfigCenterService;
	}

	public void setStoreConfigCenterService(
			StoreConfigCenterService storeConfigCenterService) {
		this.storeConfigCenterService = storeConfigCenterService;
	}

	/**
	 * 2017 吾日省身列表页
	 *
	 * @return
	 */
	public String wrsslist() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		String hql = " from MemberDiary where isdel=0 and type=2 and mid="
				+ memberInfo.getId();
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = " ctime desc ";

		// 判断排序条件
		pageBean = memberDiaryCenterService.findMemberDiaryHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/wrsslist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlistsd(pageBean.getPageList()));
		return "success";
	}

	/**
	 * 2017 吾日省身内页
	 *
	 * @return
	 */
	public String wrssinfo() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		if (id > 0) {
			memberDiary = memberDiaryCenterService.getMemberDiary(id);
		} else if (dpage != 0) {
			memberDiary = new MemberDiary();
			memberDiary.setMid(memberInfo.getId());
			memberDiary.setIsdel(new Integer(0));
			memberDiary.setCtime(new Date());
			memberDiary.setPtime(new Date());
			memberDiary.setViewnumber(new Integer(0));
			memberDiary.setRcnumber(new Integer(0));
			memberDiary.setQx(new Integer(0));
			memberDiary.setType(2);
			memberDiaryCenterService.saveMemberDiary(memberDiary);
		}
		if (dpage == 1) {
			memberDiary.setFssq(request.getParameter("fssq"));
			memberDiaryCenterService.updateMemberDiary(memberDiary);
		} else if (dpage == 2) {
			memberDiary.setXymz(request.getParameter("xymz"));
			memberDiaryCenterService.updateMemberDiary(memberDiary);
		} else if (dpage == 3) {
			memberDiary.setWdgs(request.getParameter("wdgs"));
			memberDiaryCenterService.updateMemberDiary(memberDiary);
		} else if (dpage == 4) {
			memberDiary.setNxxq(request.getParameter("nxxq"));
			memberDiaryCenterService.updateMemberDiary(memberDiary);
//			// 添加会员动态信息 2015-04-14
			memberStatusCenterService
					.saveMemberStatus(
							memberInfo.getId(),
							2,
							memberDiary.getId(),
							DateUtil.getFormatDate(memberDiary.getPtime(),
									"yyyy-MM-dd") + "吾日省身", 0);
			// 添加获得积分
			IntegralConfig ic = integralConfigFrontService
					.findIntegralConfigInList(3);
			if (ic != null) {
				IntegralInfo integralInfo = new IntegralInfo(
						memberInfo.getId(), memberInfo.getAccount(), 3,
						memberDiary.getId(), ic.getScore());
				integralInfoFrontService.saveIntegralInfo(integralInfo);
			}
		}
		return "success";
	}

	/**
	 * 2017 吾日省身详情页
	 *
	 * @return
	 */
	public String showwrssinfo() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		if (id > 0) {
			memberDiary = memberDiaryCenterService.getMemberDiary(id);
		}
		int qx = memberDiary.getQx() == null ? 0 : memberDiary.getQx();
		if (qx == 1) { // 判断是不是好友
			if (memberInfo.getId().intValue() != memberDiary.getMid()
					.intValue()
					&& !memberFriendCenterService.findMemberFriends(
					memberInfo.getId(), memberDiary.getMid())) {
				return "qx1";
			}
		} else if (qx == 2) { // 判断是不是自己
			if (memberInfo.getId().intValue() != memberDiary.getMid()
					.intValue()) {
				return "qx2";
			}
		}
		memberDiary.setMemberInfo(memberInfoCenterService
				.getMemberInfo(memberDiary.getMid()));
		memberDiary
				.setPraisenumber(memberPraiseCenterService
						.getMemberPraiseInListNumber(2, memberDiary.getId()
								.intValue()));
		memberDiary.setRcnumber(memberDiaryCenterService
				.getMemberDiaryInListNumber(memberDiary.getId().intValue()));
		memberDiary.setCommentnumber(commentInfoFrontService.getCommentNumber(
				memberDiary.getId().intValue(), 2));
		memberDiary.setMemberPraise(memberPraiseCenterService
				.findMemberPraiseAll(memberInfo.getId().intValue(), 2,
						memberDiary.getId().intValue()));
		// 更新访问量
		if (memberDiary.getMid().intValue() != memberInfo.getId().intValue()) {
			memberDiary.setViewnumber(memberDiary.getViewnumber() == null ? 0
					: memberDiary.getViewnumber() + 1);
		}
		memberDiaryCenterService.updateMemberDiary(memberDiary);
		// 添加浏览记录
		memberLLJLCenterService.saveMemberLLJL(memberInfo.getId(), "[吾日省身]"
				+ memberDiary.getPtime(), "/membercenter/showwrssinfo?id="
				+ memberDiary.getId());
		return "success";
	}

	/**
	 * 2017 习惯养成列表页
	 *
	 * @return
	 */
	public String xgyclist() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		String hql = " from MemberDiaryXGYC where isdel=0 and type=0 and mid="
				+ memberInfo.getId();
		Map map = new HashMap();
		String parameter = "";
		// 默认排序 最新时间
		String sortstr = " ptime asc ";

		// 判断排序条件
		pageBean = memberDiaryXGYCCenterService.findMemberDiaryXGYCHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/membercenter/xgyclist");
		backUrl = pageBean.getAction() + "?pageSize=" + pageBean.getPageSize()
				+ "&currentPage=" + pageBean.getCurrentPage() + parameter;
		pageBean.setGopageTemplate(request
				.getRealPath("/member/center/gopage/gopage.html"));
		pageBean.setPageList(getnewlistXGYC(pageBean.getPageList()));
		return "success";
	}

	/**
	 * 2017 习惯养成内页
	 *
	 * @return
	 */
	public String xgycinfo() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		if (id > 0) {
			memberDiaryXGYC = memberDiaryXGYCCenterService.getMemberDiaryXGYC(id);
		} else{
			MemberDiaryXGYC MemberDiaryXGYC2 = memberDiaryXGYCCenterService.getNewMemberDiaryXGYCByPtime(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
			if(MemberDiaryXGYC2 != null){
				memberDiaryXGYC = MemberDiaryXGYC2;
				day = memberDiaryXGYC.getDay();
			}else{
				MemberDiaryXGYC MemberDiaryXGYC1 = memberDiaryXGYCCenterService.getNewMemberDiaryXGYC(memberInfo.getId());
				if(MemberDiaryXGYC1 != null){
					day = MemberDiaryXGYC1.getDay()+1;
				}
				memberDiaryXGYC = new MemberDiaryXGYC();
				memberDiaryXGYC.setMid(memberInfo.getId());
				memberDiaryXGYC.setDay(day);
				memberDiaryXGYC.setIsdel(new Integer(0));
				memberDiaryXGYC.setCtime(new Date());
				memberDiaryXGYC.setPtime(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
				memberDiaryXGYC.setViewnumber(new Integer(0));
				memberDiaryXGYC.setRcnumber(new Integer(0));
				memberDiaryXGYC.setQx(new Integer(0));
				memberDiaryXGYC.setType(0);
				memberDiaryXGYCCenterService.saveMemberDiaryXGYC(memberDiaryXGYC);
			}
		}
		if (dpage == 1) {
			memberDiaryXGYC.setPdxg1(Integer.parseInt(request.getParameter("pdxg1")));
			memberDiaryXGYC.setPdxg2(Integer.parseInt(request.getParameter("pdxg2")));
			memberDiaryXGYC.setPdxg3(Integer.parseInt(request.getParameter("pdxg3")));
			memberDiaryXGYC.setPdxg4(Integer.parseInt(request.getParameter("pdxg4")));
			memberDiaryXGYC.setPdxg5(Integer.parseInt(request.getParameter("pdxg5")));
			memberDiaryXGYC.setPdxg6(Integer.parseInt(request.getParameter("pdxg6")));
			memberDiaryXGYC.setPdxg7(Integer.parseInt(request.getParameter("pdxg7")));
			memberDiaryXGYCCenterService.updateMemberDiaryXGYC(memberDiaryXGYC);
		} else if (dpage == 2) {
			memberDiaryXGYC.setShxg1(Integer.parseInt(request.getParameter("shxg1")));
			memberDiaryXGYC.setShxg2(Integer.parseInt(request.getParameter("shxg2")));
			memberDiaryXGYC.setShxg3(Integer.parseInt(request.getParameter("shxg3")));
			memberDiaryXGYC.setShxg4(Integer.parseInt(request.getParameter("shxg4")));
			memberDiaryXGYC.setShxg5(Integer.parseInt(request.getParameter("shxg5")));
			memberDiaryXGYCCenterService.updateMemberDiaryXGYC(memberDiaryXGYC);
		} else if (dpage == 3) {
			memberDiaryXGYC.setXxxg1(Integer.parseInt(request.getParameter("xxxg1")));
			memberDiaryXGYC.setXxxg2(Integer.parseInt(request.getParameter("xxxg2")));
			memberDiaryXGYC.setXxxg3(Integer.parseInt(request.getParameter("xxxg3")));
			memberDiaryXGYC.setXxxg4(Integer.parseInt(request.getParameter("xxxg4")));
			memberDiaryXGYCCenterService.updateMemberDiaryXGYC(memberDiaryXGYC);
		} else if (dpage == 4) {
			memberDiaryXGYC.setDsydxg1(Integer.parseInt(request.getParameter("dsydxg1")));
			memberDiaryXGYC.setDsydxg2(Integer.parseInt(request.getParameter("dsydxg2")));
			memberDiaryXGYC.setDsydxg3(Integer.parseInt(request.getParameter("dsydxg3")));
			memberDiaryXGYC.setDsydxg4(Integer.parseInt(request.getParameter("dsydxg4")));
			memberDiaryXGYC.setDsydxg5(Integer.parseInt(request.getParameter("dsydxg5")));
			memberDiaryXGYC.setDsydxg6(Integer.parseInt(request.getParameter("dsydxg6")));
			memberDiaryXGYCCenterService.updateMemberDiaryXGYC(memberDiaryXGYC);
		} else if (dpage == 5) {
			memberDiaryXGYC.setHzbx(request.getParameter("hzbx"));
			memberDiaryXGYC.setFqbx(request.getParameter("fqbx"));
			memberDiaryXGYC.setMqbx(request.getParameter("mqbx"));
			memberDiaryXGYC.setWdgs(request.getParameter("wdgs"));
			memberDiaryXGYCCenterService.updateMemberDiaryXGYC(memberDiaryXGYC);
			// 添加会员动态信息 2015-04-14
			memberStatusCenterService
					.saveMemberStatus(
							memberInfo.getId(),
							10,
							memberDiaryXGYC.getId(),memberDiaryXGYC.getPtime() + "习惯养成", 0);
			// 添加获得积分
			IntegralConfig ic = integralConfigFrontService
					.findIntegralConfigInList(10);
			if (ic != null) {
				IntegralInfo integralInfo = new IntegralInfo(
						memberInfo.getId(), memberInfo.getAccount(), 10,
						memberDiaryXGYC.getId(), ic.getScore());
				integralInfoFrontService.saveIntegralInfo(integralInfo);
			}
		}
		return "success";
	}

	/**
	 * 2017 习惯养成详情页
	 *
	 * @return
	 */
	public String showxgycinfo() {
		this.memberInfo = ((MemberInfo) this.session.get(ConstantUtils.MEMBERINFO));
		if (id > 0) {
			memberDiaryXGYC = memberDiaryXGYCCenterService.getMemberDiaryXGYC(id);
		}
		int qx = memberDiaryXGYC.getQx() == null ? 0 : memberDiaryXGYC.getQx();
		if (qx == 1) { // 判断是不是好友
			if (memberInfo.getId().intValue() != memberDiaryXGYC.getMid()
					.intValue()
					&& !memberFriendCenterService.findMemberFriends(
					memberInfo.getId(), memberDiaryXGYC.getMid())) {
				return "qx1";
			}
		} else if (qx == 2) { // 判断是不是自己
			if (memberInfo.getId().intValue() != memberDiaryXGYC.getMid()
					.intValue()) {
				return "qx2";
			}
		}
		memberDiaryXGYC.setMemberInfo(memberInfoCenterService
				.getMemberInfo(memberDiaryXGYC.getMid()));
		memberDiaryXGYC
				.setPraisenumber(memberPraiseCenterService
						.getMemberPraiseInListNumber(10, memberDiaryXGYC.getId()
								.intValue()));
		memberDiaryXGYC.setRcnumber(memberDiaryCenterService
				.getMemberDiaryInListNumber(memberDiaryXGYC.getId().intValue()));
		memberDiaryXGYC.setCommentnumber(commentInfoFrontService.getCommentNumber(
				memberDiaryXGYC.getId().intValue(), 10));
		memberDiaryXGYC.setMemberPraise(memberPraiseCenterService
				.findMemberPraiseAll(memberInfo.getId().intValue(), 10,
						memberDiaryXGYC.getId().intValue()));
		// 更新访问量
		if (memberDiaryXGYC.getMid().intValue() != memberInfo.getId().intValue()) {
			memberDiaryXGYC.setViewnumber(memberDiaryXGYC.getViewnumber() == null ? 0
					: memberDiaryXGYC.getViewnumber() + 1);
		}
		memberDiaryXGYCCenterService.updateMemberDiaryXGYC(memberDiaryXGYC);
		// 添加浏览记录
		memberLLJLCenterService.saveMemberLLJL(memberInfo.getId(), "[习惯养成]"
				+ memberDiaryXGYC.getPtime(), "/membercenter/showxgycinfo?id="
				+ memberDiaryXGYC.getId());
		return "success";
	}

}

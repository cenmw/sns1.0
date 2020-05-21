package com.cenmw.member.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.learn.manager.service.LearnInfoManagerService;
import com.cenmw.learn.po.LearnInfo;
import com.cenmw.member.manager.service.MemberBlogManagerService;
import com.cenmw.member.manager.service.MemberDiaryManagerService;
import com.cenmw.member.manager.service.MemberInfoManagerService;
import com.cenmw.member.manager.service.MemberMoodManagerService;
import com.cenmw.member.manager.service.MemberPhotoManagerService;
import com.cenmw.member.manager.service.MemberPraiseManagerService;
import com.cenmw.member.po.MemberBlog;
import com.cenmw.member.po.MemberDiary;
import com.cenmw.member.po.MemberMood;
import com.cenmw.member.po.MemberPhoto;
import com.cenmw.member.po.MemberPraise;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.DateUtil;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;
import com.cenmw.vedio.manager.service.VedioInfoManagerService;
import com.cenmw.vedio.po.VedioInfo;

public class MemberPraiseManagerAction extends BaseAction {
	/**
	 * 会员赞 模块
	 */
	private MemberPraiseManagerService memberPraiseManagerService;
	private MemberInfoManagerService memberInfoManagerService;
	private MemberMoodManagerService memberMoodManagerService;
	private MemberBlogManagerService memberBlogManagerService;
	private MemberPhotoManagerService memberPhotoManagerService;
	private VedioInfoManagerService vedioInfoManagerService;
	private LearnInfoManagerService learnInfoManagerService;
	private MemberDiaryManagerService memberDiaryManagerService;
	private MemberPraise memberPraise;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员赞删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteMEMBERPRAISE })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberPraise = memberPraiseManagerService
						.getMemberPraise(new Integer(idarrs[i].trim()));
				memberPraise.setIsdel(new Integer(1));
				memberPraiseManagerService.updateMemberPraise(memberPraise);
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
	@EmployeePermission(perm = { EmployeePermissionType.ADDMEMBERPRAISE })
	public String save() {
		String msg = "修改成功！";
		if (memberPraise.getId() == null) {
			msg = "添加成功！";
			memberPraise.setIsdel(new Integer(0));
			memberPraise.setCtime(new Date());
			memberPraiseManagerService.saveMemberPraise(memberPraise);
		}
		memberPraiseManagerService.updateMemberPraise(memberPraise);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员赞查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERPRAISE })
	public String info() {
		if (id > 0) {
			memberPraise = memberPraiseManagerService.getMemberPraise(id);
			memberPraise.setMemberInfo(memberInfoManagerService
					.getMemberInfo(memberPraise.getMid()));
			if (memberPraise.getType() == 1) {
				MemberMood mb = memberMoodManagerService
						.getMemberMood(memberPraise.getCid());
				memberPraise.setClassname(mb == null ? "" : mb.getContent());
			} else if (memberPraise.getType() == 2) {
				MemberDiary mb = memberDiaryManagerService
						.getMemberDiary(memberPraise.getCid());
				memberPraise.setClassname(mb == null ? "" : DateUtil.getFormatDate(mb
						.getPtime(), "yyyy-MM-dd")+"日记");
			} else if (memberPraise.getType() == 3) {
				MemberBlog mb = memberBlogManagerService
						.getMemberBlog(memberPraise.getCid());
				memberPraise.setClassname(mb == null ? "" : mb.getTitle());
			}  else if (memberPraise.getType() == 4) {
				MemberPhoto memberPhoto = memberPhotoManagerService
						.getMemberPhoto(memberPraise.getCid());
				memberPraise.setClassname(memberPhoto == null ? "" : memberPhoto.getTitle());
			} else if (memberPraise.getType() == 5) {
				VedioInfo vedioInfo = vedioInfoManagerService
						.getVedioInfo(memberPraise.getCid());
				memberPraise.setClassname(vedioInfo == null ? "" : vedioInfo.getTitle());
			} else if (memberPraise.getType() == 6) {
				LearnInfo learnInfo = learnInfoManagerService
						.getLearnInfo(memberPraise.getCid());
				memberPraise.setClassname(learnInfo == null ? "" : learnInfo.getTitle());
			}
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
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERPRAISE })
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
		pageBean = memberPraiseManagerService.findMemberPraiseHQLList(hql,
				sortstr, map, currentPage, pageSize);
		pageBean.setAction("/manager/memberpraise/list");
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
				MemberPraise mpraise = (MemberPraise) pagelist.get(i);
				mpraise.setMemberInfo(memberInfoManagerService
						.getMemberInfo(mpraise.getMid()));
				if (mpraise.getType() == 1) {
					MemberMood mb = memberMoodManagerService
							.getMemberMood(mpraise.getCid());
					mpraise.setClassname(mb == null ? "" : mb.getContent()); 
				} else if (mpraise.getType() == 2) {
					MemberDiary mb = memberDiaryManagerService.getMemberDiary(mpraise.getCid());
					mpraise.setClassname(mb == null ? "" : DateUtil.getFormatDate(mb.getPtime(), "yyyy-MM-dd")+"日记");
				} else if (mpraise.getType() == 3) {
					MemberBlog mb = memberBlogManagerService
					.getMemberBlog(mpraise.getCid());
					mpraise.setClassname(mb == null ? "" : mb.getTitle());
				}  else if (mpraise.getType() == 4) {
					MemberPhoto memberPhoto = memberPhotoManagerService
							.getMemberPhoto(mpraise.getCid());
					mpraise.setClassname(memberPhoto == null ? "" : memberPhoto.getTitle());
				} else if (mpraise.getType() == 5) {
					VedioInfo vedioInfo = vedioInfoManagerService
							.getVedioInfo(mpraise.getCid());
					mpraise.setClassname(vedioInfo == null ? "" : vedioInfo.getTitle());
				} else if (mpraise.getType() == 6) {
					LearnInfo learnInfo = learnInfoManagerService
							.getLearnInfo(mpraise.getCid());
					mpraise.setClassname(learnInfo == null ? "" : learnInfo.getTitle());
				}
				newlist.add(mpraise);
			}
		}
		return newlist;
	}

	public MemberPraiseManagerService getMemberPraiseManagerService() {
		return memberPraiseManagerService;
	}

	public void setMemberPraiseManagerService(
			MemberPraiseManagerService memberPraiseManagerService) {
		this.memberPraiseManagerService = memberPraiseManagerService;
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

	public MemberInfoManagerService getMemberInfoManagerService() {
		return memberInfoManagerService;
	}

	public void setMemberInfoManagerService(
			MemberInfoManagerService memberInfoManagerService) {
		this.memberInfoManagerService = memberInfoManagerService;
	}

	public String getSearchtitle() {
		return searchtitle;
	}

	public void setSearchtitle(String searchtitle) {
		this.searchtitle = searchtitle;
	}

	public MemberMoodManagerService getMemberMoodManagerService() {
		return memberMoodManagerService;
	}

	public void setMemberMoodManagerService(
			MemberMoodManagerService memberMoodManagerService) {
		this.memberMoodManagerService = memberMoodManagerService;
	}

	public MemberBlogManagerService getMemberBlogManagerService() {
		return memberBlogManagerService;
	}

	public void setMemberBlogManagerService(
			MemberBlogManagerService memberBlogManagerService) {
		this.memberBlogManagerService = memberBlogManagerService;
	}

	public MemberPhotoManagerService getMemberPhotoManagerService() {
		return memberPhotoManagerService;
	}

	public void setMemberPhotoManagerService(
			MemberPhotoManagerService memberPhotoManagerService) {
		this.memberPhotoManagerService = memberPhotoManagerService;
	}

	public VedioInfoManagerService getVedioInfoManagerService() {
		return vedioInfoManagerService;
	}

	public void setVedioInfoManagerService(
			VedioInfoManagerService vedioInfoManagerService) {
		this.vedioInfoManagerService = vedioInfoManagerService;
	}

	public LearnInfoManagerService getLearnInfoManagerService() {
		return learnInfoManagerService;
	}

	public void setLearnInfoManagerService(
			LearnInfoManagerService learnInfoManagerService) {
		this.learnInfoManagerService = learnInfoManagerService;
	}

	public MemberDiaryManagerService getMemberDiaryManagerService() {
		return memberDiaryManagerService;
	}

	public void setMemberDiaryManagerService(
			MemberDiaryManagerService memberDiaryManagerService) {
		this.memberDiaryManagerService = memberDiaryManagerService;
	}


}

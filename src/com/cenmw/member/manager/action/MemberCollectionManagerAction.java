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
import com.cenmw.member.manager.service.MemberInfoManagerService;
import com.cenmw.member.manager.service.MemberCollectionManagerService;
import com.cenmw.member.po.MemberBlog;
import com.cenmw.member.po.MemberCollection;
import com.cenmw.security.EmployeePermission;
import com.cenmw.security.EmployeePermissionType;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;
import com.cenmw.vedio.manager.service.VedioInfoManagerService;
import com.cenmw.vedio.po.VedioInfo;

public class MemberCollectionManagerAction extends BaseAction {
	/**
	 * 会员收藏 模块
	 */
	private MemberCollectionManagerService memberCollectionManagerService;
	private MemberInfoManagerService memberInfoManagerService;
	private MemberBlogManagerService memberBlogManagerService;
	private VedioInfoManagerService vedioInfoManagerService;
	private LearnInfoManagerService learnInfoManagerService;
	private MemberCollection memberCollection;
	private int id;
	private String ids;
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员收藏删除功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.DeleteMEMBERCOLLECTION })
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberCollection = memberCollectionManagerService
						.getMemberCollection(new Integer(idarrs[i].trim()));
				memberCollection.setIsdel(new Integer(1));
				memberCollectionManagerService
						.updateMemberCollection(memberCollection);
			}
			String msg = "删除成功！";
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员收藏添加功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.ADDMEMBERCOLLECTION })
	public String save() {
		String msg = "修改成功！";
		if (memberCollection.getId() == null) {
			msg = "添加成功！";
			memberCollection.setIsdel(new Integer(0));
			memberCollection.setCtime(new Date());
			memberCollectionManagerService
					.saveMemberCollection(memberCollection);
		}
		memberCollectionManagerService.updateMemberCollection(memberCollection);
		session.put("msg", msg);
		return SUCCESS;
	}

	/**
	 * 会员收藏查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERCOLLECTION })
	public String info() {
		if (id > 0) {
			memberCollection = memberCollectionManagerService
					.getMemberCollection(id);
			memberCollection.setMemberInfo(memberInfoManagerService
					.getMemberInfo(memberCollection.getMid()));
			if (memberCollection.getType() == 3) {
				MemberBlog mb = memberBlogManagerService
						.getMemberBlog(memberCollection.getCid());
				if (mb != null) {
					memberCollection.setTitle(mb.getTitle());
					memberCollection.setDescription(mb.getDescription());
					memberCollection.setOctime(mb.getCtime());
					memberCollection.setOclassname(mb.getClassname());
				}
			} else if (memberCollection.getType() == 5) {
				VedioInfo vedioInfo = vedioInfoManagerService
						.getVedioInfo(memberCollection.getCid());
				if (vedioInfo != null) {
					memberCollection.setTitle(vedioInfo.getTitle());
					memberCollection.setOclassname(vedioInfo.getClassname());
					memberCollection.setOctime(vedioInfo.getCtime());
					memberCollection.setOpicpath(vedioInfo.getPicpath());
					memberCollection.setViewnumber(vedioInfo.getViewnumber());
				}
			} else if (memberCollection.getType() == 6) {
				LearnInfo learnInfo = learnInfoManagerService
						.getLearnInfo(memberCollection.getCid());
				if (learnInfo != null) {
					memberCollection.setTitle(learnInfo.getTitle());
					memberCollection.setOclassname(learnInfo.getClassname());
					memberCollection.setOctime(learnInfo.getCtime());
					memberCollection.setOpicpath(learnInfo.getPicpath());
				}
			}
		}
		// 初始化信息
		if (memberCollection == null) {
			memberCollection = new MemberCollection();
			memberCollection.setIsdel(new Integer(0));
		}
		return SUCCESS;
	}

	/**
	 * 会员列表查看功能
	 * 
	 * @return
	 */
	@EmployeePermission(perm = { EmployeePermissionType.VIEWMEMBERCOLLECTION })
	public String list() {
		String hql = " from MemberCollection where isdel=0";
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
		pageBean = memberCollectionManagerService.findMemberCollectionHQLList(
				hql, sortstr, map, currentPage, pageSize);
		pageBean.setAction("/manager/membercollection/list");
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
				MemberCollection mcollection = (MemberCollection) pagelist
						.get(i);
				mcollection.setMemberInfo(memberInfoManagerService
						.getMemberInfo(mcollection.getMid()));
				if (mcollection.getType() == 3) {
					MemberBlog mb = memberBlogManagerService
							.getMemberBlog(mcollection.getCid());
					if (mb != null) {
						mcollection.setTitle(mb.getTitle());
						mcollection.setDescription(mb.getDescription());
						mcollection.setOctime(mb.getCtime());
						mcollection.setOclassname(mb.getClassname());
					}
				} else if (mcollection.getType() == 5) {
					VedioInfo vedioInfo = vedioInfoManagerService
							.getVedioInfo(mcollection.getCid());
					if (vedioInfo != null) {
						mcollection.setTitle(vedioInfo.getTitle());
						mcollection.setOclassname(vedioInfo.getClassname());
						mcollection.setOctime(vedioInfo.getCtime());
						mcollection.setOpicpath(vedioInfo.getPicpath());
						mcollection.setViewnumber(vedioInfo.getViewnumber());
					}
				} else if (mcollection.getType() == 6) {
					LearnInfo learnInfo = learnInfoManagerService
							.getLearnInfo(mcollection.getCid());
					if (learnInfo != null) {
						mcollection.setTitle(learnInfo.getTitle());
						mcollection.setOclassname(learnInfo.getClassname());
						mcollection.setOctime(learnInfo.getCtime());
						mcollection.setOpicpath(learnInfo.getPicpath());
					}
				}
				newlist.add(mcollection);
			}
		}
		return newlist;
	}

	public MemberCollectionManagerService getMemberCollectionManagerService() {
		return memberCollectionManagerService;
	}

	public void setMemberCollectionManagerService(
			MemberCollectionManagerService memberCollectionManagerService) {
		this.memberCollectionManagerService = memberCollectionManagerService;
	}

	public MemberCollection getMemberCollection() {
		return memberCollection;
	}

	public void setMemberCollection(MemberCollection memberCollection) {
		this.memberCollection = memberCollection;
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

	public MemberBlogManagerService getMemberBlogManagerService() {
		return memberBlogManagerService;
	}

	public void setMemberBlogManagerService(
			MemberBlogManagerService memberBlogManagerService) {
		this.memberBlogManagerService = memberBlogManagerService;
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

}

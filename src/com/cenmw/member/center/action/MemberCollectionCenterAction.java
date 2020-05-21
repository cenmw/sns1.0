package com.cenmw.member.center.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseAction;
import com.cenmw.learn.front.service.LearnInfoFrontService;
import com.cenmw.learn.po.LearnInfo;
import com.cenmw.member.center.service.MemberBlogCenterService;
import com.cenmw.member.center.service.MemberInfoCenterService;
import com.cenmw.member.center.service.MemberCollectionCenterService;
import com.cenmw.member.po.MemberBlog;
import com.cenmw.member.po.MemberCollection;
import com.cenmw.member.po.MemberInfo;
import com.cenmw.util.ConstantUtils;
import com.cenmw.util.HqlBean;
import com.cenmw.util.StringUtil;
import com.cenmw.vedio.front.service.VedioInfoFrontService;
import com.cenmw.vedio.po.VedioInfo;

public class MemberCollectionCenterAction extends BaseAction {
	/**
	 * 会员收藏 模块
	 */
	private MemberCollectionCenterService memberCollectionCenterService;
	private MemberInfoCenterService memberInfoCenterService;
	private MemberBlogCenterService memberBlogCenterService;
	private VedioInfoFrontService vedioInfoFrontService;
	private LearnInfoFrontService learnInfoFrontService;
	private MemberCollection memberCollection;
	private MemberInfo memberInfo;
	private int id;
	private String ids;
	private int type = 1;
	private int cid;// 收藏类型的id
	private String typename = "collectionlist";
	private String backUrl;
	// 搜索条件
	private String searchtitle = "";

	/**
	 * 会员收藏删除功能
	 * 
	 * @return
	 */
	public String delete() {
		if (ids != null && ids.length() > 0) {
			String[] idarrs = ids.split(",");
			for (int i = 0; i < idarrs.length; i++) {
				memberCollection = memberCollectionCenterService
						.getMemberCollection(new Integer(idarrs[i].trim()));
				memberCollection.setIsdel(new Integer(1));
				memberCollectionCenterService
						.updateMemberCollection(memberCollection);
			}
			String msg = "删除成功！";
			if (type == 5) {
				typename = "vediocollectionlist";
			} else if (type == 6) {
				typename = "learncollectionlist";
			}
			session.put("msg", msg);
		}
		return SUCCESS;
	}

	/**
	 * 会员收藏添加功能
	 * 
	 * @return
	 */
	public String addMemberCollectionAJAX() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		if (type > 0 && cid > 0) {
			memberCollection = memberCollectionCenterService
					.getMemberCollection(memberInfo.getId().intValue(), cid,
							type);
			if (memberCollection == null) {
				memberCollection = new MemberCollection();
				memberCollection.setMid(memberInfo.getId());
				memberCollection.setType(type);
				memberCollection.setCid(cid);
				memberCollection.setIsdel(new Integer(0));
				memberCollection.setCtime(new Date());
				memberCollectionCenterService
						.saveMemberCollection(memberCollection);
				responseHTMLAjax("1");
			} else {
				responseHTMLAjax("0");
			}
		}
		return null;
	}

	/**
	 * 会员收藏查看功能
	 * 
	 * @return
	 */
	public String info() {
		if (id > 0) {
			memberCollection = memberCollectionCenterService
					.getMemberCollection(id);
			memberCollection.setMemberInfo(memberInfoCenterService
					.getMemberInfo(memberCollection.getMid()));
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
	public String list() {
		memberInfo = (MemberInfo) session.get(ConstantUtils.MEMBERINFO);
		String hql = " from MemberCollection where isdel=0 and type=" + type
				+ " and mid=" + memberInfo.getId();
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
		pageSize = 12;
		// 判断排序条件
		pageBean = memberCollectionCenterService.findMemberCollectionHQLList(
				hql, sortstr, map, currentPage, pageSize);
		String action = "/membercenter/collectionlist";
		if (type == 5) {
			action = "/membercenter/vediocollectionlist";
		} else if (type == 6) {
			action = "/membercenter/learncollectionlist";
		}
		pageBean.setAction(action);
		backUrl = pageBean.getAction() + "?type=" + type + "&pageSize="
				+ pageBean.getPageSize() + "&currentPage="
				+ pageBean.getCurrentPage() + parameter;
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
				MemberCollection mcollection = (MemberCollection) pagelist
						.get(i);
				mcollection.setMemberInfo(memberInfoCenterService
						.getMemberInfo(mcollection.getMid()));
				if (mcollection.getType() == 3) {
					MemberBlog mb = memberBlogCenterService
							.getMemberBlog(mcollection.getCid());
					if (mb != null) {
						mcollection.setTitle(mb.getTitle());
						mcollection.setDescription(mb.getDescription());
						mcollection.setOctime(mb.getCtime());
						mcollection.setOclassname(mb.getClassname());
					}
				} else if (mcollection.getType() == 5) {
					VedioInfo vedioInfo = vedioInfoFrontService
							.getVedioInfo(mcollection.getCid());
					if (vedioInfo != null) {
						mcollection.setTitle(vedioInfo.getTitle());
						mcollection.setOclassname(vedioInfo.getClassname());
						mcollection.setOctime(vedioInfo.getCtime());
						mcollection.setOpicpath(vedioInfo.getPicpath());
						mcollection.setViewnumber(vedioInfo.getViewnumber());
					}
				} else if (mcollection.getType() == 6) {
					LearnInfo learnInfo = learnInfoFrontService
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

	public MemberCollectionCenterService getMemberCollectionCenterService() {
		return memberCollectionCenterService;
	}

	public void setMemberCollectionCenterService(
			MemberCollectionCenterService memberCollectionCenterService) {
		this.memberCollectionCenterService = memberCollectionCenterService;
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

	public MemberBlogCenterService getMemberBlogCenterService() {
		return memberBlogCenterService;
	}

	public void setMemberBlogCenterService(
			MemberBlogCenterService memberBlogCenterService) {
		this.memberBlogCenterService = memberBlogCenterService;
	}

	public VedioInfoFrontService getVedioInfoFrontService() {
		return vedioInfoFrontService;
	}

	public void setVedioInfoFrontService(
			VedioInfoFrontService vedioInfoFrontService) {
		this.vedioInfoFrontService = vedioInfoFrontService;
	}

	public LearnInfoFrontService getLearnInfoFrontService() {
		return learnInfoFrontService;
	}

	public void setLearnInfoFrontService(
			LearnInfoFrontService learnInfoFrontService) {
		this.learnInfoFrontService = learnInfoFrontService;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

}

package com.cenmw.topic.po;

import java.util.Date;

import com.cenmw.util.StringUtil;

/**
 * 测试中心题目
 */
public class TopicInfo {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer cid; // 分类id
	private String classname; // 分类名称
	private Integer type;  //题目类型，1：填空题  2：竖式算法题
	private String picpath;  //题目对应的图片
	private String title; // 题目名称
	private String keyword; // 关键词
	private String description; // 描述
	private String author; // 作者
	private String source; // 来源
	private Date ptime; // 发布时间
	private Integer contenttype; // 0:内容 1：链接
	private String content; // 内容
	private Integer score; //  得分数
	private Integer isdel; // 是否删除
	private Integer sort; // 排序
	private Integer state; // 是否发布 1:发布 0：草稿
	private Date ctime; // 创建时间
	private int commentnumber; // 评论次数
	
	private String result; //答题结果
	private Integer code; //编号
	private Integer cost; //费用
	
	private Integer knumber;  //空个数
	private String  whyids;    //原因id串
	private String  lcids;     //课程推荐分类id串
	private String  ttaids;    //生活建议id串
	private Integer viewnumber; // 查看次数
	
	private String tcontent; // 根据图片空替换真正的填空
	private int page; // 分页数
	private Integer countsum;  //总学习次数
	private Integer maxcorrect;  //最高学习记录
	private Integer m_countsum;  //当前会员的总学习次数
	private Integer m_maxcorrect;  //当前会员的最高学习记录
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

	public int getCommentnumber() {
		return commentnumber;
	}

	public void setCommentnumber(int commentnumber) {
		this.commentnumber = commentnumber;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getKnumber() {
		return knumber;
	}

	public void setKnumber(Integer knumber) {
		this.knumber = knumber;
	}

	public String getWhyids() {
		return whyids;
	}

	public void setWhyids(String whyids) {
		this.whyids = whyids;
	}

	public String getTtaids() {
		return ttaids;
	}

	public void setTtaids(String ttaids) {
		this.ttaids = ttaids;
	}

	public String getTcontent() {
		String input1 = "<input type=\"text\" class=\"inpjs\" style=\"width:26px;\" name=\"topictext\" onclick=\"clearTitle(this)\">";
		String input2 = "<input type=\"text\" class=\"inpjs\" style=\"width:36px;\" name=\"topictext\" onclick=\"clearTitle(this)\">";
		String input3 = "<input type=\"text\" class=\"inpxhx\" style=\"width:54px;\" name=\"learntext\" onclick=\"clearTitle(this)\">";
		String input4 = "<input type=\"text\" class=\"inpxhx\" style=\"width:72px;\" name=\"learntext\" onclick=\"clearTitle(this)\">";
		String input5 = "<input type=\"text\" class=\"inpxhx\" style=\"width:90px;\" name=\"learntext\" onclick=\"clearTitle(this)\">";
		String input6 = "<input type=\"text\" class=\"inpxhx\" style=\"width:108px;\" name=\"learntext\" onclick=\"clearTitle(this)\">";
		String input7 = "<input type=\"text\" class=\"inpxhx\" style=\"width:126px;\" name=\"learntext\" onclick=\"clearTitle(this)\">";
		String input8 = "<input type=\"text\" class=\"inpxhx\" style=\"width:144px;\" name=\"learntext\" onclick=\"clearTitle(this)\">";
		String input9 = "<input type=\"text\" class=\"inpxhx\" style=\"width:162px;\" name=\"learntext\" onclick=\"clearTitle(this)\">";
		String input10 = "<input type=\"text\" class=\"inpxhx\" style=\"width:180px;\" name=\"learntext\" onclick=\"clearTitle(this)\">";
		String input15 = "<input type=\"text\" class=\"inpxhx\" style=\"width:360px;\" name=\"learntext\" onclick=\"clearTitle(this)\">";
		String input16 = "<input type=\"text\" class=\"inpxhx\" style=\"width:540px;\" name=\"learntext\" onclick=\"clearTitle(this)\">";
		tcontent = content;
		if (StringUtil.notNullStr(tcontent)) {
			// input1
			if (tcontent
					.indexOf("/common/newkindeditor/plugins/fillblank/images/t1.gif") > 0) {
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://192.168.1.195/common/newkindeditor/plugins/fillblank/images/t1.gif\" border=\"0\" />",
								input1);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://www.longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t1.gif\" border=\"0\" />",
								input1);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t1.gif\" border=\"0\" />",
								input1);
			}
			// input2
			if (tcontent
					.indexOf("/common/newkindeditor/plugins/fillblank/images/t2.gif") > 0) {
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://192.168.1.195/common/newkindeditor/plugins/fillblank/images/t2.gif\" border=\"0\" />",
								input2);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://www.longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t2.gif\" border=\"0\" />",
								input2);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t2.gif\" border=\"0\" />",
								input2);
			}
			// input3
			if (tcontent
					.indexOf("/common/newkindeditor/plugins/fillblank/images/t3.gif") > 0) {
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://192.168.1.195/common/newkindeditor/plugins/fillblank/images/t3.gif\" border=\"0\" />",
								input3);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://www.longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t3.gif\" border=\"0\" />",
								input3);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t3.gif\" border=\"0\" />",
								input3);
			}
			// input4
			if (tcontent
					.indexOf("/common/newkindeditor/plugins/fillblank/images/t4.gif") > 0) {
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://192.168.1.195/common/newkindeditor/plugins/fillblank/images/t4.gif\" border=\"0\" />",
								input4);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://www.longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t4.gif\" border=\"0\" />",
								input4);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t4.gif\" border=\"0\" />",
								input4);
			}
			// input5
			if (tcontent
					.indexOf("/common/newkindeditor/plugins/fillblank/images/t5.gif") > 0) {
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://192.168.1.195/common/newkindeditor/plugins/fillblank/images/t5.gif\" border=\"0\" />",
								input5);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://www.longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t5.gif\" border=\"0\" />",
								input5);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t5.gif\" border=\"0\" />",
								input5);
			}
			// input6
			if (tcontent
					.indexOf("/common/newkindeditor/plugins/fillblank/images/t6.gif") > 0) {
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://192.168.1.195/common/newkindeditor/plugins/fillblank/images/t6.gif\" border=\"0\" />",
								input6);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://www.longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t6.gif\" border=\"0\" />",
								input6);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t6.gif\" border=\"0\" />",
								input6);
			}
			// input7
			if (tcontent
					.indexOf("/common/newkindeditor/plugins/fillblank/images/t7.gif") > 0) {
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://192.168.1.195/common/newkindeditor/plugins/fillblank/images/t7.gif\" border=\"0\" />",
								input7);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://www.longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t7.gif\" border=\"0\" />",
								input7);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t7.gif\" border=\"0\" />",
								input7);
			}
			// input8
			if (tcontent
					.indexOf("/common/newkindeditor/plugins/fillblank/images/t8.gif") > 0) {
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://192.168.1.195/common/newkindeditor/plugins/fillblank/images/t8.gif\" border=\"0\" />",
								input8);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://www.longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t8.gif\" border=\"0\" />",
								input8);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t8.gif\" border=\"0\" />",
								input8);
			}
			// input9
			if (tcontent
					.indexOf("/common/newkindeditor/plugins/fillblank/images/t9.gif") > 0) {
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://192.168.1.195/common/newkindeditor/plugins/fillblank/images/t9.gif\" border=\"0\" />",
								input9);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://www.longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t9.gif\" border=\"0\" />",
								input9);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t9.gif\" border=\"0\" />",
								input9);
			}
			// input10
			if (tcontent
					.indexOf("/common/newkindeditor/plugins/fillblank/images/t10.gif") > 0) {
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://192.168.1.195/common/newkindeditor/plugins/fillblank/images/t10.gif\" border=\"0\" />",
								input10);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://www.longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t10.gif\" border=\"0\" />",
								input10);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t10.gif\" border=\"0\" />",
								input10);
			}
			// input15
			if (tcontent
					.indexOf("/common/newkindeditor/plugins/fillblank/images/t15.gif") > 0) {
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://192.168.1.195/common/newkindeditor/plugins/fillblank/images/t15.gif\" border=\"0\" />",
								input15);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://www.longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t15.gif\" border=\"0\" />",
								input15);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t15.gif\" border=\"0\" />",
								input15);
			}
			// input16
			if (tcontent
					.indexOf("/common/newkindeditor/plugins/fillblank/images/t16.gif") > 0) {
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://192.168.1.195/common/newkindeditor/plugins/fillblank/images/t16.gif\" border=\"0\" />",
								input16);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://www.longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t16.gif\" border=\"0\" />",
								input16);
				tcontent = tcontent
						.replaceAll(
								"<img src=\"http://longbaba.com.cn/common/newkindeditor/plugins/fillblank/images/t16.gif\" border=\"0\" />",
								input16);
			}

		}
		page = getPage();
		if (page > 1) {
			String[] tcontentAll = tcontent
					.split("<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />");
			if (tcontentAll[0].length() > 0) {
				for (int i = 1; i <= page; i++) {
					if (i == 1) {
						tcontent = "<div id=\"topicpage" + i + "\">"
								+ tcontentAll[0];
					}else {
						tcontent += "</div><div id=\"topicpage" + i + "\">"
								+ tcontentAll[i - 1];
					}
				}
				tcontent += "</div>";
			}
		}
		return tcontent;
	}

	public void setTcontent(String tcontent) {
		this.tcontent = tcontent;
	}

	public int getPage() {
		page = 1;
		if (StringUtil.notNullStr(content)) {
			if (content
					.indexOf("<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />") > 0) {
				page = content
						.split("<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />").length;
			}
		}

		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Integer getCountsum() {
		return countsum;
	}

	public void setCountsum(Integer countsum) {
		this.countsum = countsum;
	}

	public Integer getMaxcorrect() {
		return maxcorrect;
	}

	public void setMaxcorrect(Integer maxcorrect) {
		this.maxcorrect = maxcorrect;
	}

	public Integer getM_countsum() {
		return m_countsum;
	}

	public void setM_countsum(Integer m_countsum) {
		this.m_countsum = m_countsum;
	}

	public Integer getM_maxcorrect() {
		return m_maxcorrect;
	}

	public void setM_maxcorrect(Integer m_maxcorrect) {
		this.m_maxcorrect = m_maxcorrect;
	}

	public String getLcids() {
		return lcids;
	}

	public void setLcids(String lcids) {
		this.lcids = lcids;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getPtime() {
		return ptime;
	}

	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}

	public Integer getContenttype() {
		return contenttype;
	}

	public void setContenttype(Integer contenttype) {
		this.contenttype = contenttype;
	}

	public Integer getViewnumber() {
		return viewnumber;
	}

	public void setViewnumber(Integer viewnumber) {
		this.viewnumber = viewnumber;
	}


}

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<a id="praisenumber<s:property value="memberBlog.id" escape="false" />"
	class="blue"
	href="javascript:updateMemberPraiseAJAX(3,<s:property value="memberBlog.id" escape="false" />,'praisenumber<s:property value="memberBlog.id" escape="false" />')"><s:if
		test="memberBlog.memberPraise != null && memberBlog.memberPraise.isdel==0">取消赞</s:if>
	<s:else>赞</s:else>(<s:property value="memberBlog.praisenumber"
		escape="false" />)</a>
┊
<a class="blue" href="javascript:conpl()">评论(<s:property
		value="memberBlog.commentnumber" escape="false" />)</a>
┊
<a class="blue"
	href="javascript:zz(3,'<s:property value="memberBlog.id" escape="false" />','<s:property value="memberBlog.memberInfo.id" escape="false" />','<s:property value="memberInfo.id" escape="false" />')">转载(<s:property
		value="memberBlog.rcnumber" escape="false" />)</a>
┊
<a id="praisenumber<s:property value="memberBlog.id" escape="false" />"
	class="blue"
	href="javascript:addMemberCollectionAJAX(3,<s:property value="memberBlog.id" escape="false" />,'<s:property value="memberBlog.memberInfo.id" escape="false" />','<s:property value="memberInfo.id" escape="false" />')">收藏</a>
┊
<a class="blue"
	href="javascript:jb(3,'<s:property value="memberBlog.id" escape="false" />','<s:property value="memberInfo.id" escape="false" />','<s:property value="memberBlog.memberInfo.id" escape="false" />')">举报</a>

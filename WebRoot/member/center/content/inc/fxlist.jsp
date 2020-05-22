<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<a id="praisenumber<s:property value="#beanlist.id" escape="false" />"
	class="blue"
	href="javascript:updateMemberPraiseAJAX(3,<s:property value="#beanlist.id" escape="false" />,'praisenumber<s:property value="#beanlist.id" escape="false" />')"><s:if
		test="#beanlist.memberPraise != null && #beanlist.memberPraise.isdel==0">取消赞</s:if>
	<s:else>赞</s:else>(<s:property value="#beanlist.praisenumber"
		escape="false" />)</a>
┊
<a class="blue"
	href="javascript:fowardBack('/membercenter/showcontentinfo?id=<s:property value="#beanlist.id" escape="false" />&backUrl=','<s:property value="backUrl"/>')">评论(<s:property
		value="#beanlist.commentnumber" escape="false" />)</a>
┊
<a class="blue"
	href="javascript:zz(3,'<s:property value="#beanlist.id" escape="false" />','<s:property value="#beanlist.memberInfo.id" escape="false" />','<s:property value="cmemberInfo.id" escape="false" />')">转载(<s:property
		value="#beanlist.rcnumber" escape="false" />)</a>
┊
<a id="praisenumber<s:property value="#beanlist.id" escape="false" />"
	class="blue"
	href="javascript:addMemberCollectionAJAX(3,<s:property value="#beanlist.id" escape="false" />,'<s:property value="#beanlist.memberInfo.id" escape="false" />','<s:property value="cmemberInfo.id" escape="false" />')">收藏</a>
┊
<a class="blue"
	href="javascript:jb(3,'<s:property value="#beanlist.id" escape="false" />','<s:property value="cmemberInfo.id" escape="false" />','<s:property value="#beanlist.memberInfo.id" escape="false" />')">举报</a>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<a id="praisenumber<s:property value="memberPhoto.id" escape="false" />" class="blue" href="javascript:updateMemberPraiseAJAX(4,<s:property value="memberPhoto.id" escape="false" />,'praisenumber<s:property value="memberPhoto.id" escape="false" />')"><s:if test="memberPhoto.memberPraise != null && memberPhoto.memberPraise.isdel==0">取消赞</s:if><s:else>赞</s:else>(<s:property value="memberPhoto.praisenumber" escape="false" />)</a>┊<a class="blue" href="javascript:conpl()">评论(<s:property value="memberPhoto.commentnumber" escape="false" />)</a>┊<a class="blue" href="javascript:jb(4,'<s:property value="memberPhoto.id" escape="false" />','<s:property value="memberInfo.id" escape="false" />','<s:property value="memberPhoto.memberInfo.id" escape="false" />')">举报</a>

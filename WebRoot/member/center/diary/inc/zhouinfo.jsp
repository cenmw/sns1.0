<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<a id="praisenumber<s:property value="memberDiary52.id" escape="false" />" class="blue" href="javascript:updateMemberPraiseAJAX(2,<s:property value="memberDiary52.id" escape="false" />,'praisenumber<s:property value="memberDiary52.id" escape="false" />')"><s:if test="memberDiary52.memberPraise != null && memberDiary52.memberPraise.isdel==0">取消赞</s:if><s:else>赞</s:else>(<s:property value="memberDiary52.praisenumber" escape="false" />)</a>┊<a class="blue" href="javascript:conpl()">评论(<s:property value="memberDiary52.commentnumber" escape="false" />)</a>┊<a class="blue" href="javascript:zz(2,'<s:property value="memberDiary52.id" escape="false" />','<s:property value="memberDiary52.memberInfo.id" escape="false" />','<s:property value="memberInfo.id" escape="false" />')">转载(<s:property value="memberDiary52.rcnumber" escape="false" />)</a>┊<a class="blue" href="javascript:jb(2,'<s:property value="memberDiary52.id" escape="false" />','<s:property value="memberInfo.id" escape="false" />','<s:property value="memberDiary52.memberInfo.id" escape="false" />')">举报</a>
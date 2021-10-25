<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/header.jsp"%>
<c:if test="${row==1}">
	<script>
		alert("수정 하였습니다.");
		location.href="/notice/notice_view.do?page=${page}&idx=${idx}";
	</script>
</c:if>
<c:if test="${row==0}">
	<script>
		alert("수정이 실패하였습니다.");
		location.href="/notice/notice_modify.do?page=${page}&idx=${idx}";
	</script>
</c:if>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-view-title">${nvo.subject}</h2>
		<p class="sub-view-wd">${nvo.name} | ${nvo.regdate.substring(0,10)} | <c:if test="${nvo.gubun==0}">
					갤러리게시판
			</c:if>
			<c:if test="${nvo.gubun==1}">
					회원전용게시판
			</c:if>	
			<c:if test="${nvo.gubun==2}">
					질문답변게시판
			</c:if>
			<c:if test="${nvo.gubun==3}">
					자유게시판
			</c:if> | <c:if test="${nvo.juyodo==0}">		
					<span  class="black">평범</span>
			</c:if>
			<c:if test="${nvo.juyodo==1}">		
					<span class="gray">중요</span>
			</c:if>	
			<c:if test="${nvo.juyodo==2}">		
					<span  class="red">매우중요</span>
			</c:if></p>
	</div>
	<div class="sub-view-contnet">
		<p>${nvo.contents}</p>
	<c:if test="${empty nvo.filename}">	    
	</c:if>	
	<c:if test="${!empty nvo.filename}">
		<c:if test="${!empty mvo}">	    
		<a href="/notice/notice_download.do?filename=${nvo.filename}">
		<div class="image">
		<img src="/images/disk.gif" style="alt-left; width:35px; height:24px;"></a>${nvo.filename}
		</div>
		</c:if>
		<c:if test="${empty mvo}">	    
		<div class="image">
		<img src="/images/disk.gif" style="alt-left; width:35px; height:24px;">${nvo.filename}
		</div>
		</c:if>
	</c:if>	
	</div>			
	<div class="sub-view-bottom">
		<c:if test="${!empty mvo}">
			<c:if test="${mvo.userid=='sub3815'}">
		<a href="/notice/notice_modify.do?page=${page}&idx=${idx}" class="btn-modify">수정</a>&nbsp;&nbsp;
		<a href="javascript:void(0)" class="btn-delete" onclick="del();">삭제</a>&nbsp;&nbsp;
			</c:if>
		</c:if>
		<c:if test="${empty mvo}">
		</c:if>
		<a href="/notice/notice_list.do?page=${page}" class="btn-list">목록</a>&nbsp;&nbsp;
	</div>
</div>

<script>
	function del(){
		var url = "/notice/notice_delete.do?idx=${idx}&page=${page}";
		window.open(url,"","width=300 height=200");
	}	
</script>

<%@ include file="/include/footer.jsp"%>
















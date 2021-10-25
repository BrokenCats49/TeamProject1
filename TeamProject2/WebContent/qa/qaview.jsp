<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/header.jsp"%>
<c:if test="${row==0}">
	<script>
		alert("죄송합니다. \n등록에 실패하였습니다.");
		history.back();
	</script>
</c:if>
<c:if test="${row==1}">
	<script>
		alert("글이 등록처리 되었습니다.");
		location.href="/qa/qa_list.do";
	</script>
</c:if>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-view-title">${qvo.subject}</h2>
		<p class="sub-view-wd">${qvo.userid} | ${qvo.regdate.substring(0,10)} | ${qvo.readcnt}</p>
	</div>
	<div class="sub-view-contnet">
		<p>${qvo.contents}</p>
<c:if test="${qvo.qaparent==0}">		
	<c:if test="${!empty qvo.filename}">	
	첨부파일 : 	<a href="qa_download.do?filename=${qvo.filename}">${qvo.filename}</a>
	</c:if>
	<c:if test="${empty qvo.filename}">
		첨부파일이 없습니다.
	</c:if>
</c:if>	
	</div>
	<div class="sub-view-bottom">
	<c:if test="${!empty mvo}">
			<a href="javascript:changeView(1)" class="btn-modify">답글</a>&nbsp;&nbsp;
		<c:if test="${mvo.userid == qvo.userid}">	
			<a href="javascript:doAction(0)" class="btn-modify">수정</a>&nbsp;&nbsp;
			<a href="javascript:doAction(1)" class="btn-delete">삭제</a>&nbsp;&nbsp;
		</c:if>	
	</c:if>	
		<a href="/qa/qa_list.do" class="btn-list">목록</a>&nbsp;&nbsp;
	</div>
		
</div>
<script type="text/javascript">
		function changeView(value)
		{
			if(value == 0)	
				location.href="/qa/qa_list.do?page=${page}";
			else if(value == 1)
				location.href="/qa/qa_reply.do?idx=${idx}&page=${page}";
		}
		
		function doAction(value)
		{
			if(value == 0){
				alert("수정버튼 클릭"); // 수정
				location.href="/qa/qa_modify.do?page=${page}&idx=${idx}";
			}
			else if(value == 1){
				var url = "/qa/qa_delete.do?idx=${idx}&page=${page}";
				window.open(url,"","width=300 height=200");
			} // 삭제
		}
	</script>


<%@ include file="/include/footer.jsp"%>
















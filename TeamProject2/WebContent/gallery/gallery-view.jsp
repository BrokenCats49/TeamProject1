<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/header.jsp"%>
<c:if test="${row==1}">
	<script>
		alert("수정 하였습니다.");
		location.href="/gallery/gallery_view.do?page=${page}&idx=${idx}";
	</script>
</c:if>
<c:if test="${row==0}">
	<script>
		alert("수정이 실패하였습니다.");
		location.href="/gallery/gallery_modify.do?page=${page}&idx=${idx}";
	</script>
</c:if>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-view-title">${gvo.subject}</h2>
		<p class="sub-view-wd">${gvo.name} | ${gvo.regdate.substring(0,10)}</p>
	</div>
	<div class="sub-view-contnet">
		<p>${gvo.contents}</p><br>
		<p><img src="/gallery/upload/${gvo.filename}" width="150px;" height="500px;"></p>
	</div>
	
	<div class="sub-view-bottom">
<c:if test="${!empty mvo}">
		<a href="/gallery/gallery_modify.do?page=${page}&idx=${idx}" class="btn-modify">수정</a>&nbsp;&nbsp;
		<a href="javascript:void(0)" class="btn-delete" onclick="avent();">삭제</a>&nbsp;&nbsp;
</c:if>		
		<a href="/gallery/gallery_list.do?page=${page}" class="btn-list">목록</a>&nbsp;&nbsp;
	</div>
		
</div>
<script>
function avent(){
	if(confirm("후회안해? 삭제?") == true) {
		var url = "/gallery/gallery_delete.do?idx=${idx}&page=${page}";
		window.open(url,"","width=300 height=200");
	}else {
		return;
	}
}
</script>


<%@ include file="/include/footer.jsp"%>
















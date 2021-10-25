<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/header.jsp"%>
<c:if test="${row==1}">
	<script>
		alert("수정 하였습니다.");
		location.href="/portfolio/pds_view.do?page=${page}&idx=${idx}";
	</script>
</c:if>
<c:if test="${row==0}">
	<script>
		alert("수정이 실패하였습니다.");
		location.href="/portfolio/pds_modify.do?page=${page}&idx=${idx}";
	</script>
</c:if>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-view-title">${pvo.subject}</h2>
		<p class="sub-view-wd">${pvo.name} | ${pvo.regdate.substring(0,10)}</p>
	</div>
	<div class="sub-view-contnet">
		<p>${pvo.contents}</p>
		<p></p>
	<c:if test="${empty pvo.filename}">	    
	</c:if>	
	<c:if test="${!empty pvo.filename}">
		<c:if test="${!empty mvo}">	    
		<a href="/portfolio/pds_download.do?filename=${pvo.filename}">
		<div class="image">
		<img src="/images/disk.gif" style="alt-left; width:35px; height:24px;"></a>${pvo.filename}
		</div>
		</c:if>
		<c:if test="${empty mvo}">	    
		<div class="image">
		<img src="/images/disk.gif" style="alt-left; width:35px; height:24px;">${pvo.filename}
		</div>
		</c:if>
	</c:if>	
	</div>

	<div class="sub-view-bottom">
		<c:if test="${!empty mvo}">
		<a href="/portfolio/pds_modify.do?page=${page}&idx=${idx}" class="btn-modify">수정</a>&nbsp;&nbsp;
		<a href="javascript:void(0)" class="btn-delete" onclick="del();">삭제</a>&nbsp;&nbsp;
		</c:if>
		<c:if test="${empty mvo}">
		</c:if>
		<a href="/portfolio/pds_list.do?page=${page}" class="btn-list">목록</a>&nbsp;&nbsp;
	</div>
</div>
<script>
function avent(){
	if(confirm("후회안해? 삭제?") == true) {
		location.href="/portfolio/pds_delete.do";
	}else {
		return;
	}	
}
function del(){
	var url = "/portfolio/pds_delete.do?idx=${idx}&page=${page}";
	window.open(url,"","width=300 height=200");
}
</script>


<%@ include file="/include/footer.jsp"%>
















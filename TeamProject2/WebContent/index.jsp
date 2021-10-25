<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ include file="/include/header.jsp" %>
	
	<div class="slider">
	    <div><img src="/images/1.jpg" alt="" title="신개념 오락실"></div>
	    <div><img src="/images/2.jpg" alt="" title="깔끔한 오락실"></div>
	    <div><img src="/images/3.jpg" alt="" title="누구나 다함께"></div>
	    <div><img src="/images/4.jpg" alt="" title="플레이 해보세요"></div>
  	</div>

	
	<div class="indexgallery">
			<div class="indextitle">
				<h2>유저 자랑게시판<br>갤러리</h2>
				<span><a href="/gallery/gallery_list.do">MORE</a></span>
			</div>
<c:if test="${!empty list}">
	<c:forEach var="i" items="${list}" begin="0" end="2" step="1">
			<ul>
				<li><a href="/gallery/gallery_list.do"><img src="gallery/upload/${i.filename}"></a></li>
			</ul>			
	</c:forEach>
</c:if>	
<c:if test="${empty list}">
			<ul>
				<li><h3>사진이 없네요.</h3></li>
			</ul>			
</c:if>			
	</div>
	<div class="bbs_wrap">
		<div class="bbs_left">
			<h2 class="title">공지시항</h2>
<c:if test="${!empty list3}">
		<c:forEach var="n" items="${list3}" begin="0" end="4" step="1">
			<ul>
				<li><a href="/notice/notice_view.do?idx=${n.idx}&page=${page}">${n.subject}</a></li>
			</ul>	
			<a href="/notice/notice_list.do"><span class="fa fa-plus plus"></span></a>		
	</c:forEach>
</c:if>
<c:if test="${empty list3}">
			<ul>
				<li><h3>글이 없네요.</h3></li>
			</ul>			
</c:if>	
		</div>
		<div class="bbs_right">
			<h2 class="title">기기대여신청</h2>
<c:if test="${!empty list2}">
		<c:forEach var="q" items="${list2}" begin="0" end="4" step="1">
			<ul>
				<li><a href="/qa/qa_view.do?idx=${q.idx}&page=${page}">${q.subject}</a></li>
			</ul>	
			<a href="/qa/qa_list.do"><span class="fa fa-plus plus"></span></a>		
	</c:forEach>
</c:if>

<c:if test="${empty list2}">
			<ul>
				<li><h3>글이 없네요.</h3></li>
			</ul>			
</c:if>					
			
		</div>
	</div>
	
<%@ include file="/include/footer.jsp" %>














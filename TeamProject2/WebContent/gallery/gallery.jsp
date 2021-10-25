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
		location.href="/gallery/gallery_list.do";
	</script>
</c:if>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">자랑 갤러리</h2>
		<div class="sub-search">
			<form name="my" method="post">
				<select name="search" class="sel">
					<option value="subject"<c:if test="${search=='subject'}"> selected </c:if>>제목</option>
					<option value="contents"<c:if test="${search=='contents'}"> selected </c:if>>내용</option>
				</select>
				<input type="text" name="key" class="text" value="${key}">
				<a href="javascript:check()"><input type="button" value="검색" class="btn"></a>
			</form>
			<div style="text-align:right;"><font size="4" face="맑은고딕">전체 :${totpage}건<br>${page}/${totpage}건 </font></div>	
		</div>
	</div>
	<ul class="sub-content">
<c:if test="${!empty list}">
	<c:forEach var="gallery" items="${list}">	
		
			<li>
				<img src="/gallery/upload/${gallery.filename}" width="250px;" height="218px;">
				<p class="p26">
					<a href="/gallery/gallery_view.do?idx=${gallery.idx}&page=${page}">
						<span class="gallery-title">${gallery.subject}</span>
					</a>
						<span class="hit">조회수 : ${gallery.readcnt}</span>
				</p>
			</li>
		
 	</c:forEach>
</c:if>
</ul>
<c:if test="${empty list}">
		<div style="text-align:center;">
		<ul class="content">
			<li>
				<h1>업로드한게 없어서 아무것도 없습니다.</h1>
			</li>
		</ul>
		</div>
</c:if>			
		<div class="paging">
			<ul>
				<li>${pageskip}</li>
			</ul>
		</div>
<c:if test="${empty mvo}">			
			
</c:if>
<c:if test="${!empty mvo}">
			<a href="/gallery/gallery_write.do?page=${page}" class="btn-write">글쓰기</a>
</c:if>				

</div>

<script>
	function check() {
		if(my.key.value=="") {
			alert("검색단어입력하세요");
			my.key.focus;
			return;
		}else{
			my.action="/gallery/gallery_list.do"
			my.submit();
		}
	}
</script>

<%@ include file="/include/footer.jsp"%>
















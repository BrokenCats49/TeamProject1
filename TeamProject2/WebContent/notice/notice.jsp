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
		location.href="/notice/notice_list.do";
	</script>
</c:if>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">BROKEN cat 공지사항</h2>
		<div class="sub-search">
			<form name="my" method="post" action="qasearch.do" onsubmit="return check()">
				<select name="sel" class="sel">
					<option value="subject"<c:if test="${search=='subject'}"> selected </c:if>>제목</option>
					<option value="contents"<c:if test="${search=='contents'}"> selected </c:if>>내용</option>
				</select>
				<input type="text" name="cont" class="text" value="${key}">
				<input type="submit" value="검색" class="btn">
			</form>
		</div>
	</div>
	
	<div class="content-body">
		<table class="qatable">
			<caption class="readonly">공지사항 게시판</caption>
			<colgroup>
				<col width="8%">
				<col width="6%">
				<col width="40%">
				<col width="10%">
				<col width="15%">
				<col width="11%">
				<col width="10%">
			</colgroup>
			<tbody>
				<tr>
					<th>구분</th>
					<th>번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>필독중요도</th>
					<th>날자</th>
					<th>조회수</th>
				</tr>
	<c:if test="${!empty list}">
		<c:forEach var="notice" items="${list}">			
				<tr>
			<c:if test="${notice.gubun==0}">
					<td>유저자랑</td>
			</c:if>
			<c:if test="${notice.gubun==1}">
					<td>공략공유</td>
			</c:if>	
			<c:if test="${notice.gubun==2}">
					<td>대여신청</td>
			</c:if>
			<c:if test="${notice.gubun==3}">
					<td>자유게시판</td>
			</c:if>				
					<td>${notice.idx}</td>
					<td class="tleft">
					<a href="/notice/notice_view.do?idx=${notice.idx}&page=${page}">${notice.subject}</a></td>
					<td>${notice.name}</td>
			<c:if test="${notice.juyodo==0}">		
					<td><span  class="black">평범</span></td>
			</c:if>
			<c:if test="${notice.juyodo==1}">		
					<td><span class="gray">중요</span></td>
			</c:if>	
			<c:if test="${notice.juyodo==2}">		
					<td><span  class="red">매우중요</span></td>
			</c:if>			
					<td>${notice.regdate.substring(0,10)}</td>
					<td>${notice.readcnt}</td>
				</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty list}">
			<tr >
				<td colspan="6"><h1>업로드한게 없어서 아무것도 없습니다.</h1></td>
			</tr>
	</c:if>				
			</tbody>
		</table>
	</div>
		<div class="paging">
			<ul>
				<li>${pageSkip}</li>
			</ul>
		<c:if test="${mvo.userid=='sub3815'}">	
			<a href="/notice/notice_write.do?idx=${idx}&page=${page}" class="btn-write">글쓰기</a>
		</c:if>	
		</div>
</div>

<script>
	function check() {
		if(my.cont.value=="") {
			alert("검색단어입력하세요");
			my.cont.focus;
			return false;
		}
		return true;
	}
</script>

<%@ include file="/include/footer.jsp"%>
















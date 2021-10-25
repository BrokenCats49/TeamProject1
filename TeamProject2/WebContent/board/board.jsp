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
		location.href="/board/board_list.do";
	</script>
</c:if>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">자유게시판</h2>
		<div class="sub-search">
			<form name="my" method="post" action="/board/board.do" onsubmit="return check()">
				<select name="sel" class="sel">
					<option value="subject"<c:if test="${search=='subject'}"> selected </c:if>>제목</option>
					<option value="contents"<c:if test="${search=='contents'}"> selected </c:if>>내용</option>
					<option value="name"<c:if test="${search=='name'}"> selected </c:if>>이름</option>
				</select>
				<input type="text" name="key" class="text" value="${key}">
				<input type="submit" value="검색" class="btn">
			</form>
		</div>
	</div>
	
	<div class="content-body">
		<table class="qatable">
			<caption class="readonly">자유 게시판 표</caption>
			<colgroup>
				<col width="9%">
				<col width="7%">
				<col width="48%">
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
					<th>날자</th>
					<th>조회수</th>
				</tr>
	<c:if test="${!empty list}">
		<c:forEach var="board" items="${list}">			
				<tr>
					<c:if test="${!empty board.userid}"><td>회원</td></c:if>
					<c:if test="${empty board.userid}"><td>비회원</td></c:if>
					<td>${board.idx}</td>
					<td class="tleft"><a href="/board/board_view.do?idx=${board.idx}&page=${page}">${board.subject}</a></td>
					<td>${board.name}</td>
					<td>${board.regdate.substring(0,10)}</td>
					<td>${board.readcnt}</td>
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
			<a href="/board/board_write.do?page=${page}" class="btn-write">글쓰기</a>
		</div>

</div>

<script>
	function check() {
		if(my.key.value=="") {
			alert("검색단어입력하세요");
			my.key.focus;
			return false;
		}
		return true;
	}
</script>

<%@ include file="/include/footer.jsp"%>
















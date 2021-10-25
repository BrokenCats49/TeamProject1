<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
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
		<h2 class="sub-title">대여신청게시판</h2>
		<div class="sub-search">
			<form name="my" method="post" action="/qa/qa_list.do" onsubmit="return check()">
				<select name="opt" class="sel">
					<option value="0">제목</option>
					<option value="1">내용</option>
					<option value="2">제목+내용</option>
					<option value="3">글쓴이</option>
				</select>
				<input type="text" name="condition" class="text">
				<input type="submit" value="검색" class="btn">
			</form>
		</div>
	</div>
	
	<div class="content-body">
		<table class="qatable">
			<caption class="readonly">질문답변 표</caption>
			<colgroup>
				<col width="7%">
				<col width="9%">
				<col width="38%">
				<col width="15%">
				<col width="11%">
				<col width="20%">
			</colgroup>
			<tbody>
				<tr>
					<th>구분</th>
					<th>번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
		<c:if test="${!empty list}">		
			<c:forEach var="board" items="${requestScope.list}">	
				<tr>
				<c:if test="${board.kinds==0}">
					<td>부분대여</td>
				</c:if>
				<c:if test="${board.kinds==1}">
					<td>하루대여</td>
				</c:if>		
					<td>${board.idx}</td>
					<td class="left">
					<c:if test="${board.relev > 1}">
						<c:forEach begin="1" end="${board.relev}">
						&nbsp;&nbsp;
						</c:forEach>
						RE:
					</c:if>
					<a href="/qa/qa_view.do?idx=${board.idx}&page=${spage}">${board.subject}</a>
					</td>
					<td>${board.userid}</td>
					<td>${board.regdate.substring(2,16)}</td>
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
			<c:if test="${startPage != 1}">
				<li><a href="/qa/qa_list.do?page=${startPage-1}">이전</a></li>
			</c:if>
			<c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
				<c:if test="${pageNum == spage}">
					${pageNum}&nbsp;
				</c:if>
				<c:if test="${pageNum != spage}">
					<li><a href="/qa/qa_list.do?page=${pageNum}">${pageNum}</a></li>
				</c:if>
			</c:forEach>	
			<c:if test="${endPage != maxPage }">	
				<li><a href="/qa/qa_list.do?page=${endPage+1}">다음</a></li>
			</c:if>	
			</ul>
			
		</div>
		<c:if test="${empty mvo}">
		
		</c:if>
		<c:if test="${!empty mvo}">
			<a href="/qa/qa_write.do?page=${spage}" class="btn-write">글쓰기</a>	
		</c:if>	
</div>

<script>
	function check() {
		if(my.condition.value=="") {
			alert("검색단어입력하세요");
			my.condition.focus;
			return false;
		}
		return true;
	}
</script>

<%@ include file="/include/footer.jsp"%>
















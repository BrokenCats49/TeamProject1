<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ include file="/include/header.jsp"%>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">Q&A질문답변</h2>
	</div>
	<div class="write-form">
	<form method="post" action="/qa/qa_reply_pro.do?page=${page}?userid=${mvo.userid}" name="qaboard" onsubmit="return formcheck();">
		<input type="hidden" name="userid" value="${mvo.userid}">
		<input type="hidden" name="idx" value="${board.idx}"/>
		<input type="hidden" name="reseq" value="${board.reseq}"/>
	<table summery="답변 글쓰기 테이블 입니다">
			<caption class="readonly">게시글 답변 입력폼</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
					<tr>
						<th>질문종류</th>
						<td>
							<input type="radio"  name="kinds" value="0" <c:if test="${board.kinds.contains('0')}"> checked </c:if>> 부분대여
							<input type="radio"  name="kinds" value="1" <c:if test="${board.kinds.contains('1')}"> checked </c:if>> 하루대여
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="subject" placeholder="제목입력하세요"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="contents"></textarea></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input type="text" name="userid" value="${mvo.userid}"></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="pass"></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="등록" class="btn-write">&nbsp;&nbsp;
							<input type="reset" value="작성취소" class="btn-reset">&nbsp;&nbsp;
							<a href="/qa/qa_view.do?idx=${idx}&page=${page}"><input type="button" value="목록" class="btn-reset"></a>&nbsp;&nbsp;
						</td>
					</tr>
				
					</tbody>
				</table>
			</form>	
		</div>
	</div>
<script>
	function formcheck() {
		if(qaboard.subject.value=="") {
			alert("제목입력하세요");
			qaboard.subject.focus();
			return false;
		}
		if(qaboard.contents.value=="") {
			alert("내용입력하세요");
			qaboard.contnets.focus();
			return false;
		}
		return true;
	}
</script>
<%@ include file="/include/footer.jsp"%>

















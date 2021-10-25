<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/header.jsp"%>
<c:if test="${row==0}">
	<script>
		alert("죄송합니다. \n수정에 실패하였습니다.");
		history.back();
	</script>
</c:if>
<c:if test="${row==1}">
	<script>
		alert("글이 수정처리 되었습니다.");
		location.href="/qa/qa_view.do?idx=${idx}&page=${page}";
	</script>
</c:if>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">Q&A질문답변수정</h2>
	</div>
	
	<div class="write-form">
	<form name="qaboard" method="post" action="/qa/qa_modify_pro.do" enctype="multipart/form-data" onsubmit="return formcheck();">
		<input type="hidden" name="idx" value="${idx}"/>
		<input type="hidden" name="oldfilename" value="${list.filename}"/>
		<input type="hidden" name="page" value="${page}"/>
		<table summery="질문답변 글쓰기 테이블 입니다">
			<caption class="readonly">Q&A질문 수정입력폼</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
					<tr>
						<th>질문종류</th>
						<td>
							<input type="radio"  name="kinds" value="0" <c:if test="${list.kinds.contains('0')}"> checked </c:if>> 부분대여
							<input type="radio"  name="kinds" value="1" <c:if test="${list.kinds.contains('1')}"> checked </c:if>> 하루대여
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="subject" placeholder="제목입력하세요" value="${list.subject}"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="contents">${list.contents}</textarea></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input type="text" name="userid" value="${list.userid}"></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="pass"></td>
					</tr>
				<c:if test="${list.qaparent==0}">
					<tr>
						<th><label for="title">기존파일</label></th>
						<c:if test="${!empty list.filename}"><td>${list.filename}</td></c:if>
						<c:if test="${empty list.filename}"><td>아무것도 첨부하지 않았습니다.</td></c:if>
					</tr>
					<tr>
						<th><label for="filename">첨부</label></th>
						<td><input type="file" name="filename" id="filename"></td>
					</tr>
				</c:if>	
					<tr>
						<td colspan="2">
							<input type="submit" value="등록" class="btn-write">
							<input type="reset" value="작성취소" class="btn-reset" >
							<a href="/qa/qa_list.do?page=${page}&idx=${idx}"><input type="button" value="목록" class="btn-reset" ></a>
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
		if(qaboard.pass.value=="") {
			alert("비밀번호를입력하세요");
			qaboard.pass.focus();
			return false;
		}
		return true;
	}
</script>

<%@ include file="/include/footer.jsp"%>
















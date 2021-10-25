<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/include/header.jsp"%>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">공지사항게시글 수정</h2>
	</div>
	
	<div class="write-form">
	<form name="notice" method="post" action="/notice/notice_modify_pro.do" enctype="multipart/form-data" onsubmit="return formcheck();">
		<input type="hidden" name="page" value="${page}">
		<input type="hidden" name="idx" value="${idx}">
		<input type="hidden" name="userid" value="${mvo.userid}">
		<input type="hidden" name="oldfilename" value="${vo.filename}">
		<table summery="질문답변 글쓰기 테이블 입니다">
			<caption class="readonly">공지사항 수정폼</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
					<tr>
						<th>게시판 종류</th>
						<td>
							<input type="radio" name="gubun" value="0" <c:if test="${vo.gubun.contains('0')}"> checked </c:if>> 갤러리 게시판
							<input type="radio" name="gubun" value="1" <c:if test="${vo.gubun.contains('1')}"> checked </c:if>> 회원전용 게시판
							<input type="radio" name="gubun" value="2" <c:if test="${vo.gubun.contains('2')}"> checked </c:if>> Q&A 게시판
							<input type="radio" name="gubun" value="3" <c:if test="${vo.gubun.contains('3')}"> checked </c:if>> 자유 게시판
						</td>
					</tr>
					<tr>
						<th>중요도 종류</th>
						<td>
							<input type="radio" name="juyodo" value="0" <c:if test="${vo.juyodo.contains('0')}"> checked </c:if>> 평범
							<input type="radio" name="juyodo" value="1" <c:if test="${vo.juyodo.contains('1')}"> checked </c:if>> 중요
							<input type="radio" name="juyodo" value="2" <c:if test="${vo.juyodo.contains('2')}"> checked </c:if>> 매우 중요
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="subject" placeholder="제목입력하세요" value="${vo.subject}"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="contents">${vo.contents}</textarea></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input type="text" name="name" value="${vo.name}"></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="pass" value="${mvo.passwd}"></td>
					</tr>
					<tr>
						<th><label for="filename">첨부</label></th>
						<td><input type="file" name="filename" id="filename" value="${vo.filename}"></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="등록" class="btn-write">
							<input type="reset" value="작성취소" class="btn-reset" >
							<a href="/notice/notice_list.do?page=${page}"><input type="button" value="목록" class="btn-reset" ></a>
						</td>
					</tr>
				
			</tbody>
		</table>
		</form>
	</div>
		
</div>

<script>
	function formcheck() {
		if(notice.subject.value=="") {
			alert("제목입력하세요");
			notice.subject.focus();
			return false;
		}
		if(notice.contents.value=="") {
			alert("내용입력하세요");
			notice.contnets.focus();
			return false;
		}
		if(notice.pass.value=="") {
			alert("비밀번호를입력하세요");
			notice.pass.focus();
			return false;
		}
		return true;
	}
</script>

<%@ include file="/include/footer.jsp"%>
















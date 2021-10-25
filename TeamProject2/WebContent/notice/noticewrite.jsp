<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/include/header.jsp"%>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">공지사항게시판</h2>
	</div>
	
	<div class="write-form">
	<form name="gongji" method="post" action="/notice/notice_write_pro.do" enctype="multipart/form-data" onsubmit="return formcheck();">
		<input type="hidden" name="page" value="${page}">
		<input type="hidden" name="userid" value="${mvo.userid}">
		<table summery="질문답변 글쓰기 테이블 입니다">
			<caption class="readonly">공지사항 입력폼</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
					<tr>
						<th>게시판 종류</th>
						<td>
							<input type="radio" name="gubun" value="0" checked> 유저 자랑
							<input type="radio" name="gubun" value="1"> 공략 공유
							<input type="radio" name="gubun" value="2"> 기기 대여
							<input type="radio" name="gubun" value="3"> 자유 게시판
						</td>
					</tr>
					<tr>
						<th>중요도 종류</th>
						<td>
							<input type="radio" name="juyodo" value="0" checked> 평범
							<input type="radio" name="juyodo" value="1"> 중요
							<input type="radio" name="juyodo" value="2"> 매우 중요
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
						<td><input type="text" name="name" value="${mvo.name}"></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="pass" value="${mvo.passwd}"></td>
					</tr>
					<tr>
						<th><label for="filename">첨부</label></th>
						<td><input type="file" name="filename" id="filename"></td>
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
		if(gongji.subject.value=="") {
			alert("제목입력하세요");
			gongji.subject.focus();
			return false;
		}
		if(gongji.contents.value=="") {
			alert("내용입력하세요");
			gongji.contnets.focus();
			return false;
		}
		if(gongji.pass.value=="") {
			alert("비밀번호를입력하세요");
			gongji.pass.focus();
			return false;
		}
		return true;
	}
</script>

<%@ include file="/include/footer.jsp"%>
















<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/include/header.jsp"%>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">기기대여</h2>
	</div>
	
	<div class="write-form">
	<form name="qaboard" method="post" action="/qa/qa_write_pro.do" enctype="multipart/form-data" onsubmit="return formcheck();">
		<table summery="대여관련 글쓰기 테이블 입니다">
			<caption class="readonly">대여관련 입력폼</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
					<tr>
						<th>질문종류</th>
						<td>
							<input type="radio" name="kinds" value="0" checked>부분대여
							<input type="radio" name="kinds" value="1">하루대여
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
						<th><label for="filename">첨부</label></th>
						<td><input type="file" name="filename" id="filename"></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="등록" class="btn-write">
							<input type="reset" value="작성취소" class="btn-reset" >
							<a href="/qa/qa_list.do"><input type="button" value="목록" class="btn-reset" ></a>
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
















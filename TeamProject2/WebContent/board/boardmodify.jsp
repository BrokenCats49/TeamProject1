<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${row==1}">
	<script>
		alert("수정 하였습니다.");
		location.href="/board/board_view.do?page=${page}&idx=${idx}";
	</script>
</c:if>
<c:if test="${row==0}">
	<script>
		alert("수정이 실패하였습니다.");
		location.href="/board/board_modify.do?page=${page}&idx=${idx}";
	</script>
</c:if>
<%@ include file="/include/header.jsp"%>
<!-- include libraries(jQuery, bootstrap) --> 
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet"> 
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<!-- include summernote css/js--> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet"> 
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script> 

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">자유게시판 글 수정</h2>
	</div>
	
	<div class="write-form">
		<table summery="글쓰기 테이블 입니다">
			<caption class="readonly">게시판 수정폼</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
			<form name="board" method="post" action="/board/board_modify_pro.do" onsubmit="return formcheck();">
				<input type="hidden" name="page" value="${page}">
				<input type="hidden" name="idx" value="${board.idx}">
					<tr>
						<th>제목</th>
						<td><input type="text" name="subject" placeholder="제목입력하세요" value="${board.subject}"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea id="summernote" name="contents" cols ="40%">${board.contents}</textarea></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input type="text" name="name" size="20" value="${board.name}" readonly></td>
					</tr>
					<tr>
						<th>패스워드</th>
						<td><input type="text" name="pass" size="20" ></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="확인" class="btn-write">
							<a href="javascript:history.back()"><input type="button" value="취소"  class="btn-reset"></a>
						</td>
					</tr>
				</form>
			</tbody>
		</table>
	</div>
		
</div>
<%@ include file="/include/footer.jsp"%>

 <script>
	function formcheck(){
		if(board.subject.value==""){
			alert("제목을 입력하세요");
			return false;
		}
		if(board.contents.value==""){
			alert("내용을 입력하세요.");
			return false;
		}
		if(board.name.value==""){
			alert("이름을 입력하세요.");
			return false;
		}
		if(board.pass.value==""){
			alert("비번을 입력하세요.");
			return false;
		}
		return true;
		alert("등록");
		board.submit();
	}
	
	$(document).ready(function() {
        $('#summernote').summernote(); 
   });

</script>














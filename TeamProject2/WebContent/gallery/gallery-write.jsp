<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/header.jsp"%>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">유저자랑</h2>
	</div>
	
	<div class="write-form">
		<table summery="유저자랑 글쓰기 테이블 입니다">
			<caption class="readonly">갤러리 입력폼</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
			<form name="my" method="post" action="/gallery/gallery_write_pro.do" enctype="multipart/form-data" onsubmit="return formcheck();">
			<input type="hidden" name="page" value="${page}">		
			<input type="hidden" name="passwd" value="${uvo.passwd}">		
					<tr>
						<th>제목</th>
						<td><input type="text" name="subject"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="contents"></textarea></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input type="text" name="name" value="${uvo.name}"></td>
					</tr>
					<tr>
						<th>첨부</th>
						<td><input type="file" name="filename"></td>
					</tr>
					
					<tr>
						<td colspan="2">
							<input type="submit" value="전송" class="btn-write">
							<input type="button" value="목록"  class="btn-reset" onclick="javascript:location.href='/gallery/gallery_list.do?page=${page}'">
						</td>
					</tr>
				</form>
			</tbody>
		</table>
	</div>
		
</div>

<script>
	function formcheck() {
		if(my.subject.value=="") {
			alert("제목입력하세요");
			my.title.focus();
			return false;
		}
		if(my.content.value=="") {
			alert("내용입력하세요");
			my.contnet.focus();
			return false;
		}
		return true;
	}
</script>

<%@ include file="/include/footer.jsp"%>
















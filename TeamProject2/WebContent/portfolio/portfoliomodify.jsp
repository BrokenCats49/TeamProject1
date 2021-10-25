<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/header.jsp"%>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">회원정보공유게시판</h2>
	</div>
	
	<div class="write-form">
	<form name="pds" method="post" action="/portfolio/pds_modify_pro.do" enctype="multipart/form-data" onsubmit="return formcheck();">
		<input type="hidden" name="page" value="${page}">
		<input type="hidden" name="idx" value="${idx}">
		<input type="hidden" name="userid" value="${mvo.userid}">
		<input type="hidden" name="oldfilename" value="${vo.filename}">
		<table>
			<caption class="readonly">게시판 입력폼</caption>			
			<colgroup>
				<col class="w20">
				<col class="w80">
			</colgroup>
			<tbody>
			
					<tr>
						<th><label for="subject">제목</label></th>
						<td><input type="text" name="subject" id="subject" value="${vo.subject}"></td>
					</tr>
					<tr>
						<th><label for="contents">내용</th>
						<td><textarea name="contents" id="contents" title="내용을 입력하세요">${vo.contents}</textarea></td>
					</tr>
					<tr>
						<th><label for="name">작성자</label></th>
						<td><input type="text" name="name" id="name" value="${vo.name}"></td>
					</tr>
					<tr>
						<th><label for="filename">첨부</label></th>
						<td><input type="file" name="filename" id="filename" value="${vo.filename}"></td>
					</tr>
					<tr>
						<th><label for="pass">비밀번호</label></th>
						<td><input type="password" name="pass" id="pass" value="${mvo.passwd}"></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="수정" class="btn-write">
							<a href="/portfolio/pds_list.do?page${page}"><input type="button" value="목록"  class="btn-reset"></a>
						</td>
					</tr>
				
			</tbody>
		</table>
		</form>
	</div>
		
</div>

<script>
	function formcheck() {
		
		var re = /^[a-zA-Z0-9]{4,12}$/ 
			// 아이디와 패스워드가 적합한지 검사할 정규식
	    var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	        //이메일이 적합한지 검사할 정규식
	        
	    var pw=document.getElementById("pass");
	        
	    var num = pw.search(/[0-9]/g);
	    var eng = pw.search(/[a-z]/ig);
	    var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
	    
        
		if(pds.subject.value=="") {
			alert("제목입력하세요");
			pds.subject.focus();
			return false;
		}
		if(pds.contents.value=="") {
			alert("내용입력하세요");
			pds.contents.focus();
			return false;
		}
		
		

	    if(my.pw.length < 8){
	    	  alert("8자리 ~ 20자리 이내로 입력해주세요.");
	    	  my.pass.focus();
	    	  return false;
    	 }

	    if(pw.search(/₩s/) != -1){
	    	  alert("비밀번호는 공백업이 입력해주세요.");
	    	  my.pass.focus();
	    	  return false;
		 } if(num < 0 || eng < 0 || spe < 0 ){
	    	  alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
	    	  my.pass.focus();
	    	  return false;
		 }
		return true;
	}
	
	
</script>

<%@ include file="/include/footer.jsp"%>
















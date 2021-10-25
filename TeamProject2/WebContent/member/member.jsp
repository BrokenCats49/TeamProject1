<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/header.jsp"%>
<c:if test="${row==0}">
	<script>
		alert("회원가입에 실패하였습니다. \n다시시도해주세요");
		location.href="/member/member_insert.do";
	</script>
</c:if>
<c:if test="${row==1}">
	<script>
		alert("환영합니다..");
		location.href="/index.do";
	</script>
</c:if>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">BROKEN CAT회원가입</h2>
	</div>
	
	<div class="write-form">
		<table summery="회원가입 글쓰기 테이블 입니다">
			<caption class="readonly">회원가입 입력폼</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
			<form name="my" method="post" action="/member/member_insert_pro.do" onsubmit="return formcheck();">
				<fieldset>
					<legend class="readonly">입력폼</legend>
					<tr>
						<th scope="row">
							<label for="id">아이디</label>
						</th>
						<td>
							<input type="text" name="id" id="id" class="minput" readonly> 
							<a href="javascript:id_check()" class="btn-write">중복확인</a>
						</td>
					</tr>
					<tr>
						<th scope="row"><lavel for="pass1">패스워드</lavel></th>
						<td><input type="password" name="pass1" id="pass1"  class="minput"></td>
					</tr>
					<tr>
						<th scope="row">패스워드 확인</th>
						<td><input type="password" name="pass2"  class="minput"></td>
					</tr>
					<tr>
						<th scope="row">이름</th>
						<td><input type="text" name="name"  class="minput"></td>
					</tr>
					<tr>
						<th scope="row">충전카드번호</th>
						<td><input type="text" name="cardnum"  class="minput"></td>
					</tr>
					<tr>
						<th scope="row">이메일</th>
						<td>
							<input type="text" name="email1" class="email"> @
							<input type="text" name="email2"  class="email"> 
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="전송" class="btn-write">
							<a href="/index.do"><input type="button" value="취소"  class="btn-reset"></a>
						</td>
					</tr>
					</fieldset>
				</form>
			</tbody>
		</table>
	</div>
		
</div>

<script>
	function formcheck() {
		if(my.id.value=="") {
			alert("아이디입력하세요");
			my.id.focus();
			return false;
		}
		if(my.pass1.value=="") {
			alert("패스워드입력하세요");
			my.pass1.focus();
			return false;
		}
		if(my.pass1.value != my.pass2.value) {
			alert("패스워드를 확인하세요");
			my.pass1.focus();
			return false;
		}
		if(my.email1.value==""){
			alert("이메일을 입력하세요");
			my.email1.focus();
			return false;
		}
		if(my.email2.value==""){
			alert("이메일을 입력하세요");
			my.email2.focus();
			return false;
		}
		if(my.name.value==""){
			alert("이름을 입력하세요.");
			my.name.focus();
			return false;
		}
		return true;
	}
	function id_check(){
		var url="/member/member_id_check.do";
		window.open(url, "id 중복검사" , "width=350 height=250")
	}	
</script>

<%@ include file="/include/footer.jsp"%>
















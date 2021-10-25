<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/header.jsp"%>
<c:if test="${row==0}">
	<script>
		alert("비밀번호가 틀렸습니다.. \n다시시도해주세요");
		location.href="/member/member_login.do";
	</script>
</c:if>
<c:if test="${row==1}">
	<script>
		alert("환영합니다.");
		location.href="/index.do";
	</script>
</c:if>
<c:if test="${row==-1}">
	<script>
		alert("아이디가 존재하지 않습니다.");
		location.href="/member/member_login.do";
	</script>
</c:if>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">BROKEN CAT LOG IN</h2>
	</div>
	
	<div class="write-form" style="width:50%; margin:0 auto; border:1px solid #ccc; padding:20px;">
		<form name="my" method="post" action="/member/member_login_pro.do" onSubmit="return formcheck()">
			<fieldset>
				<legend class="readonly">로그인그룹</legend>
				<div style="width:70%; float:left;">
					<label for="id" class="readonly">아이디</label>
					<input type="text" name="id" id="id" placeholder="아이디" onKeydown="autoSubmit">
					<label for="pw" class="readonly">패스워드</label>
					<input type="password" name="pw" id="pw" placeholder="패스워드" onKeydown="autoSubmit">
				</div>
				<div style="width:28%; float:right;padding:15px;
				height:132px; margin-top:3px;">
					<input type="submit" value="로그인" alt="로그인" 
					style="background: #FF4000; color:#fff;
					width:100%; height:100%; border:0 none; cursor:pointer;">
				</div>
				<p style="clear:both;padding-top:20px; text-align:center;"><a href="/member/member_id_search.do">아이디찾기</a> | <a href="/member/member_pass_search.do">패스워드찾기</a> | <a href="/member/member_insert.do">회원가입</a></p>
			</fieldset>
		</form>	
	</div>
		
</div>

<script>
	function formcheck() {
		if(my.id.value=="") {
			alert("아이디를입력하세요");
			my.id.focus();
			return false;
		}
		if(my.pw.value=="") {
			alert("암호를입력하세요");
			my.pw.focus();
			return false;
		}
		return true;
	}
	function autoSubmit(){
		if(event.keyCode==13)
			formcheck();
	}
</script>

<%@ include file="/include/footer.jsp"%>
















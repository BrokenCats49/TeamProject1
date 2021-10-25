<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/header.jsp"%>
<c:if test="${row==0}">
	<script>
		alert("아이디/메일이 틀렸습니다...\n다시시도해주세요");
		location.href="/member/member_pass_search.do";
	</script>
</c:if>
<c:if test="${rows==1}">
	<script>
		alert("임시비밀번호를 메일로 보냈습니다..");
		location.href="/member/member_login.do";
	</script>
</c:if>
<c:if test="${rows==0}">
	<script>
		alert("다시 시도해 주세요..");
		location.href="/member/member_pass_search.do";
	</script>
</c:if>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">비밀번호찾기</h2>
	</div>
	
	<div class="write-form" style="width:50%; margin:0 auto; border:1px solid #ccc; padding:20px;">
		<form name="se" method="post" onSubmit="return false">
			<fieldset>
				<legend class="readonly">비밀번호찾기</legend>
				<div style="width:70%; float:left;">
					<label >아이디</label>
					<input type="text" name="userid"  onKeydown="autoSubmit">
					<label >이메일</label>
					<input type="text" name="email" onKeydown="autoSubmit">
				</div>
				<div style="width:28%; float:right;padding:15px;
				height:132px; margin-top:3px;">
					<a href="javascript:secheck()"><input type="button" value="찾기" 
					style="background: #FF4000; color:#fff;
					width:100%; height:100%; border:0 none; cursor:pointer;"></a>
				</div>
				<p style="clear:both;padding-top:20px; text-align:center;"><a href="/member/member_id_search.do">아이디찾기</a> | 패스워드찾기 | <a href="/member/member_insert.do">회원가입</a></p>
			</fieldset>
		</form>	
	</div>
		
</div>

<script>
	function secheck() {
		if(se.userid.value=="") {
			alert("아이디를입력하세요");
			se.userid.focus();
			return;
		}
		if(se.email.value=="") {
			alert("메일를입력하세요");
			se.email.focus();
			return;
		}
		se.action="/member/member_pass_search_pro.do"
		se.submit();
		return true;
	}
	function autoSubmit(){
		if(event.keyCode==13)
			secheck();
	}
</script>

<%@ include file="/include/footer.jsp"%>
















<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${row==1}">
	<script>
		alert("탈퇴하였습니다..");
		window.opener.location.replace("/index.do");
		self.close();
	</script>
</c:if>
<c:if test="${row==0}">
	<script>
		alert("삭제실패하였습니다.");
		history.back();
	</script>
</c:if>
<html>
<head><title>탈퇴확인</title>
<link rel="stylesheet" type="text/css" href="/stylesheet.css">
<script>
	function send(){
		if(member.pass.value==""){
			alert("비밀번호를 입력하세요.");
			member.pass.focus();
			return;
		}
		member.action="/member/member_delete_pro.do";
		member.submit();
	}
</script>
</head>
<body>
<form name="member" method="post">
<input type="hidden" name="userid" value="${userid}">
  <table border="0" cellpadding="0" cellspacing="0" width="300" align="center">
  <tr>
    <td height="50">
    <b><font size="3" color="red">잠깐 !!</font></b></td></tr>
  <tr>
    <td valign="middle" height="30">
    <font size="2" face="돋움">탈퇴는 본인만 할수있습니다.<br>
    비밀번호를 입력해 주세요...</font></td></tr>
  <tr>
    <td valign="middle" height="40">
    <font size="2" face="돋움">비밀번호 <input type="password" name="pass" size="8"></font>
    <input type="button" value="확인" onClick="send()">
    <input type="button" value="닫기" onClick="self.close()"> </td></tr>
  </table>
  </form>
</body>
</html>

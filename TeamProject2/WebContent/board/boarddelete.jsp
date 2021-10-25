<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${row==1}">
	<script>
		alert("삭제되었습니다..");
		window.opener.location.replace("/board/board_list.do?page=${page}");
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
<head><title>자료 삭제</title>
<link rel="stylesheet" type="text/css" href="/stylesheet.css">
 <script>
	function del(){
		if(board.pass.value==""){
			alert("비밀번호를 입력하세요.");
			board.pass.focus();
			return;
		}
		
		board.action="/board/board_delete_pro.do";
		board.submit();
	}
</script>
</head>
<body>
<form method="post" name="board">
	<input type="hidden" name="idx" value="${idx}">
	<input type="hidden" name="page" value="${page}">
  <table border="0" cellpadding="0" cellspacing="0" width="300" align="center">
  <tr>
    <td height="50">
    <b><font size="3" color="red">잠깐 !!</font></b></td></tr>
  <tr>
    <td valign="middle" height="30">
    <font size="2" face="돋움">게시물은 작성하신 분만 삭제할 수 있습니다.<br>
    글의 비밀번호를 입력해 주세요...</font></td></tr>
  <tr>
    <td valign="middle" height="40">
    <font size="2" face="돋움">비밀번호 <input type="password" name="pass" size="8"></font>
    <a href="javascript:del()"><input type="button" value="삭제"></a>
    <a href="javascript:self.close()"><input type="button" value="닫기"></a> </td></tr>
  </table>
  </form>
</body>
</html>

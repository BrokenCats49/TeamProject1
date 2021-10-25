<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/include/header.jsp"%>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-view-title">${board.subject}</h2>
		<p class="sub-view-wd">${board.name} | ${board.regdate.substring(0,10)} | ${board.readcnt}번읽음</p>
	</div>
	<div class="sub-view-contnet">
		<p>${board.contents}</p>
	</div>
	
	<div class="sub-view-bottom">
		<a href="javascript:void(0)" class="btn-delete" onclick="board_del();">삭제</a>&nbsp;&nbsp;
		<a href="/board/board_modify.do?idx=${board.idx}&page=${page}" class="btn-list">수정</a>&nbsp;&nbsp;
		<a href="/board/board_list.do?page=${page}" class="btn-list">목록</a>&nbsp;&nbsp;
	</div>
		
</div>
<script>
function board_del(){
	url="/board/board_delete.do?page=${page}&idx=${board.idx}";
	window.open(url, "boarddelete","width=300, height=200");		
}
</script>


<%@ include file="/include/footer.jsp"%>
















<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/header.jsp"%>
<c:if test="${row==0}">
	<script>
		alert("충전에 실패하였습니다. \n다시시도해주세요");
		location.href="/member/member_cash.do";
	</script>
</c:if>
<c:if test="${row==1}">
	<script>
		alert("충전완료하였습니다.");
		location.href="/index.do";
	</script>
</c:if>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">카드 충전</h2>
	</div>
	
	<div class="write-form">
		<table summery="충전 테이블 입니다">
			<caption class="readonly">카드충전</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
			<form name="my" method="post">
			<input type="hidden" name="cardnum" value="${mvo.cardnum}">
				<fieldset>
					<legend class="readonly">입력폼</legend>
					<tr>
						<th scope="row">이름</th>
						<td>${mvo.name}</td>
					</tr>
					<tr>
						<th scope="row">충전카드번호</th>
						<td>${mvo.cardnum}</td>
					</tr>
					<tr>
						<th scope="row">잔액</th>
						<td>${cvo.cash}</td>
					</tr>
					<tr>
						<th scope="row">마지막충전날짜</th>
						<td>${cvo.chargeday}</td>
					</tr>
					<tr>
						<th scope="row">누적금액</th>
						<td>${cvo.charge}</td>
					</tr>
					<tr>
						<th scope="row">충전 금액</th>
						<td>
							<select name="charge">
								<option value="">금액을 정해주세요</option>
								<option value="1000">1000원</option>
								<option value="5000">5000원</option>
								<option value="10000">10000원</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="충전"  class="btn-reset" onclick="send()">
						</td>
					</tr>
					</fieldset>
				</form>
			</tbody>
		</table>
	</div>
		
</div>

<script>
	function send(){
		alert("충전하였습니다.");
		my.action="/member/member_cash_pro.do";
		my.submit();
	}
</script>

<%@ include file="/include/footer.jsp"%>
















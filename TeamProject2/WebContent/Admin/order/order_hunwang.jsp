<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/header1.jsp"%>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">발주현황</h2>
		<div class="sub-search">
			<form name="my" method="post" action="/Admin/order/order_list.do" onsubmit="return check()">
				<select name="search" class="sel">
					<option value="company"<c:if test="${search=='company'}"> selected </c:if>>회사명</option>
					<option value="t_name"<c:if test="${search=='t_name'}"> selected </c:if>>제품명</option>
					<option value="kinds"<c:if test="${search=='kinds'}"> selected </c:if>>종류</option>
				</select>
				<input type="text" name="key" class="text" value="${key}">
				<input type="submit" value="검색" class="btn">
			</form>
		</div>
	</div>
	<div class="content-body">
		<table class="qatable">
			<caption class="readonly">발주</caption>
			<colgroup>
				<col width="10%">
				<col width="9%">
				<col width="10%">
				<col width="10%">
				<col width="12%">
				<col width="10%">
				<col width="10%">
				<col width="5%">
				<col width="10%">
			</colgroup>
			<tbody>
				<tr>
					<th>발주일자</th>
					<th>종류</th>
					<th>회사명</th>
					<th>제품명</th>
					<th>모델명</th>
					<th>가격</th>
					<th>옵션</th>
					<th>색상</th>
					<th>수량</th>
				</tr>
<c:if test="${!empty list}">
	<c:forEach var="order" items="${list}">					
				<tr>
					<td>${order.order_code}</td>
					<td>${order.kinds}</td>
					<td>${order.company_name}</td>
					<td>${order.stock}</td>
					<td>${order.model_name}</td>
					<td>${order.unit_cost}</td>
				<c:if test="${order.o_option!='X'}">	
					<td>${order.o_option}</td>
				</c:if>		
				<c:if test="${order.o_option=='X'}">	
					<td>X</td>
				</c:if>	
				<c:if test="${order.color!='X'}">	
					<td>${order.color}</td>
				</c:if>
				<c:if test="${order.color=='X'}">	
					<td>X</td>
				</c:if>	
				<td>${order.amount}</td>
				</tr>
		</c:forEach>
	</c:if>		
					
			</tbody>
		</table>
	</div>
		<div class="paging">
			<ul>
				${pageSkip}
			</ul>
		</div>

</div>

<script>
	function check() {
		if(my.key.value=="") {
			alert("검색단어입력하세요");
			my.key.focus;
			return false;
		}
		return true;
	}

</script>

<%@ include file="/include/footer.jsp"%>
















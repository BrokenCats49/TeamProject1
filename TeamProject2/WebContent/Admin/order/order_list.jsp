<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/header1.jsp"%>
<c:if test="${row==0}">
	<script>
		alert("죄송합니다. \n발주에 실패하였습니다.");
		history.back();
	</script>
</c:if>
<c:if test="${row==1}">
	<script>
		alert("발주처리 되었습니다.");
		location.href="/Admin/order/order_list.do";
	</script>
</c:if>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">발주등록</h2>
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
				<col width="5%">
				<col width="9%">
				<col width="10%">
				<col width="20%">
				<col width="12%">
				<col width="10%">
				<col width="5%">
				<col width="5%">
				<col width="10%">
			</colgroup>
			<tbody>
				<tr>
					<th>발주</th>
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
				<form name="all" method="post" action="order_list_pro.do">
				<input type="hidden" name="page" value="${page}">
				<input type="hidden" name="kinds" value="${order.kinds}">
				<input type="hidden" name="company" value="${order.company}">
				<input type="hidden" name="stock" value="${order.t_name}">
				<input type="hidden" name="model" value="${order.t_model}">
				<input type="hidden" name="price" value="${order.price}">
				<c:if test="${order.t_option=='X'}">	
					<input type="hidden" name="option" value="${order.t_option}">
				</c:if>
				<c:if test="${order.color=='X'}">	
					<input type="hidden" name="color" value="${order.color}">
				</c:if>	
				<tr>
					
					<td><input type="submit" name="bt" value="전송" ></td>
					<td>${order.kinds}</td>
					<td>${order.company}</td>
					<td>${order.t_name}</td>
					<td>${order.t_model}</td>
					<td>${order.price}</td>
				<c:if test="${order.t_option=='O'}">	
					<td><select name="option">
					<option value="">선택하세요</option>
					<option value="스프링">스프링</option>
					<option value="스프링2">강스프링</option>
					<option value="스프링3">약스프링</option>
					</select></td>
				</c:if>		
				<c:if test="${order.t_option=='X'}">	
					<td>X</td>
				</c:if>	
				<c:if test="${order.color=='O'}">	
					<td><select name="color">
					<option value="">선택하세요</option>
					<option value="red">빨강</option>
					<option value="blue">파랑</option>
					<option value="yellow">노랑</option>
					</select></td>
				</c:if>
				<c:if test="${order.color=='X'}">	
					<td>X</td>
				</c:if>	
				<td>
					<input type=text name=amount value=0>
					<input type=button value="증가" onClick="javascript:this.form.amount.value++;">
					<input type=button value="감소" onClick="javascript:this.form.amount.value--;">
				</td>
				
				</tr>
				</form>
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
















<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/include/header.jsp"%>
<%@ page import ="java.text.*,Model.Admin.Stock.*" %>
<%
	String order_code = request.getParameter("order_code");
	StockDAO dao = StockDAO.getInstance();
	StockVO vo = dao.stockView(order_code);
	String price ="";
%>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-view-title">재고 상태 수정</h2>
		<p class="sub-view-wd">제품명 : ${vo.stock} | 입고일 : ${vo.latest_day.substring(0,10)} | 수량 : ${vo.stock_amount} </p>
	</div>
	<div class="sub-view-contnet">
		<form name="modify" method="post" action="/Admin/stock/stock_modify_pro.do">
		<input type="hidden" name="order_code" value="${vo.order_code}">
		<input type="hidden" name="page" value="${page}">
		<table>
		<tr>
		<th>
		회사명 
		<td>
		 <input type="text" readonly value="${vo.company_name}">
		 </td>
		<th>
		</tr>
		<tr>
		<th>제품명</th>
		<td><input type="text" value= "${vo.stock}" readonly>
		</td>
		</tr>
		<tr>
		<th>모델명 </th>
		<td>
		<input type="text" value="${vo.model_name}" readonly>
		</td>
		</tr>
		<tr>
		<th>상세 사항(부속품,옵션등)</th>
		<td><input type="text" value="옵션 : ${vo.option_status} , 색상	: ${vo.parts_status}" readonly>
		</td>
		</tr>
		<tr>
		<th>가격</th>
	<%if(vo.getModel_price()!=0){
			DecimalFormat df = new DecimalFormat("￦,##,###");
			price = df.format(vo.getModel_price());
		%>
		 <td><input type="text" value="<%=price%>"></td>
		</tr>
		<%} %>
		<tr>
		<th>수량</th>
		<td>
			<input type=text name=amount value="${vo.stock_amount}">
			<input type=button value="감소" onClick="javascript:this.form.amount.value--;">
		</td>
		<tr>
		<th>재고 처리 이유 </th>
		<td><textarea name="resive_state"></textarea></td>
		</tr>
		</table>
		</form>
	</div>
	
	<div class="sub-view-bottom" align="center">
		<a href="javascript:send();"><input type="button" value="수정등록" class="btn-list"></a>&nbsp;&nbsp;
		<a href="javascript:avent();"></a><input type="button" value="수정취소" onclick="avent()" class="btn-list">&nbsp;&nbsp;
		<a href="/Admin/stock/Stock_list.do?page=${page}"><input type="button" value="재고 목록"></a>&nbsp;&nbsp;
	</div>
		
</div>
<script>
function avent(){
	alert("수정을 취소합니다.");
	modify.reset();
	modify.amount.focus();
}
function send(){
	alert("수정등록합니다.");
	modify.submit();
}
</script>


<%@ include file="/include/footer.jsp"%>
















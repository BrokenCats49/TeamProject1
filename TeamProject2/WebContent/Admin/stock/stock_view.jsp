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
		<h2 class="sub-view-title">제품 상세 출력</h2>
		<p class="sub-view-wd">제품명 : ${vo.stock} | 입고일 : ${vo.latest_day.substring(0,10)} | 수량 : ${vo.stock_amount} </p>
	</div>
	<div class="sub-view-contnet">
		
		회사명 : ${vo.company_name}<br>
		제품명 : ${vo.stock}<br>
		모델명 : ${vo.model_name}</p>

		<p>상세 사항(부속품,옵션등)  옵션 : ${vo.option_status} , 색상	: ${vo.parts_status}	
		</p>
		<%if(vo.getModel_price()!=0){
			DecimalFormat df = new DecimalFormat("￦,##,###");
			price = df.format(vo.getModel_price());
		%>
		가격 : <%=price%>
		<%} %>
		<br>
		<c:if test="${!empty vo.resive_state}">
		 특이 사항 : ${vo.resive_state}
		</c:if>
		<c:if test="${empty vo.resive_state}">
		특이 사항 : 없음
		</c:if>
	</div>
	
	<div class="sub-view-bottom">
		<a href="/Admin/stock/stock_modify.do?page=${page}&order_code=${vo.order_code}" class="btn-modify">수정</a>&nbsp;&nbsp;
		<a href="javascript:void(0)" class="btn-delete" onclick="avent();">삭제</a>&nbsp;&nbsp;
		<a href="/Admin/stock/Stock_list.do?page=${page}" class="btn-list">목록</a>&nbsp;&nbsp;
	</div>
		
</div>
<script>
function avent(){
	if(confirm("삭제하시겠습니까?") == true) {
		location.href="/Admin/stock/stock_delete.do?page=${page}&order_code=${vo.order_code}";
	}else {
		alert("삭제취소합니다.");
		location.href="/Admin/stock/stock_modify.do?page=${page}&order_code=${vo.order_code}";
		return;
	}
}
</script>


<%@ include file="/include/footer.jsp"%>
















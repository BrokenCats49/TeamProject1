<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ page import="java.util.*, Model.Admin.Acount.*" %>
    <%@ page import="java.text.SimpleDateFormat" %>
<%
	AccountDAO dao = AccountDAO.getInstance();
AccountVO vo1 = new AccountVO();

vo1 = dao.totalsearch();




	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일");
	
%>

<%@ include file="/include/header.jsp"%>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" /> 
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> 
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">일일마감 등록</h2>
	</div>
	
	<div class="write-form">
		<form name="my" method="post" action="qainsert.do" enctype="multipart/form-data" onsubmit="return formcheck();">
		<table summery="일일마감 테이블 입니다">
			<caption class="readonly">일일마감 입력폼</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
			
					<tr>
						<th>작성날짜</th>
						<td><%=sf.format(nowTime) %></td>
					</tr>
					<tr>
						<th>마감등록작성자</th>
						<td><input type="text" name="member" value=""></td>
					</tr>
					<tr>
						<th>시작금액</th>
						<td></td>
					</tr>
					<tr>
						<th>발주금액</th>
						<td><input type="text" name="order_fee" id="order_fee" onkeyup='tot()' oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" value = "<%=vo1.getOrder_fee() %>"></td>
					</tr>
					<tr>
						<th>수리비용</th>
						<td><input type="text" name="repair_fee" id="repair_fee" onkeyup='tot()' oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" value = <%=vo1.getRepair_fee() %>></td>
					</tr>
					<tr>
						<th>튜닝비용</th>
						<td><input type="text" name="tuning_fee" id="tuning_fee" value = <%=vo1.getTuning_fee() %> oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" onkeyup='tot()'></td>
					</tr>
					<tr>
						<th>관리비용</th>
						<td>
							<input type="text" name="maintain_fee" id="maintain_fee" value = <%=vo1.getMaintain_fee() %> oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" onkeyup='tot()' value="">
						</td>
					</tr>
					<tr>
						<th>현금매출</th>
						<td>
							<input type="text" name="cash_sales" id="cash_sales" value = <%=vo1.getCash_sales() %> onkeyup='tot()' oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
						</td>
					</tr>
					<tr>
						<th>카드매출</th>
						<td>
							<input type="text" name="card_sales" id="card_sales" onkeyup='tot()' value = <%=vo1.getCash_sales() %>  oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
						</td>
					</tr>
					
					<tr>
						<th>총매출</th>
						<td>
							<input type="text" name='account_total' id = 'account_total' readonly>
						</td>
					</tr>
					
					
					<tr>
						<td colspan="2">
							<input type="submit" value="등록" onClick = "send()">
							<input type="button" value="취소" onClick = "history.back()">
						</td>
					</tr>
			
			</tbody>
		</table>
			</form>
	</div>
		
</div>

<script>

function tot()
{
 if(document.getElementById("order_fee").value && document.getElementById("repair_fee").value && document.getElementById("tuning_fee").value && document.getElementById("maintain_fee").value && document.getElementById("cash_sales").value && document.getElementById("card_sales").value){
  document.getElementById('account_total').value =-(parseInt(document.getElementById('order_fee').value)) - (parseInt(document.getElementById('repair_fee').value)) - (parseInt(document.getElementById('tuning_fee').value)) - (parseInt(document.getElementById('maintain_fee').value)) + (parseInt(document.getElementById('cash_sales').value)) + (parseInt(document.getElementById('card_sales').value));
 }
}
	function send() {
		alert("등록중...");
		startcoin.action = "/Admin/acount/day_acount_total_pro.do";
		startcoin.submit();
	}
$(function() { $( "#Date" ).datepicker({ }); });	
</script>

<%@ include file="/include/footer.jsp"%>
















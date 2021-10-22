<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
           <%@ page import="java.util.*, Model.Admin.Acount.*" %>
           
           <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.text.SimpleDateFormat" %>
<%
AccountDAO dao = AccountDAO.getInstance();
List<AccountVO> ls = new ArrayList<AccountVO>();
ls = dao.DayList();
%>

<%@ include file="/include/header.jsp"%>
 <script src="/chart/Chart.min.js"></script>
 <script src="/chart/utils.js"></script>
  <style>
    canvas {
        -moz-user-select: none;
        -webkit-user-select: none;
        -ms-user-select: none;
    }
    </style>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">일일 매출 현황</h2>
		<div class="sub-search">
		</div>
		<form name = "sel" method = "post" action="day_graph.do">
		<select name = "date">
		<%
		for(int x=0;x<ls.size();x++){
		%>
		<option value = "<%=ls.get(x).getAccount_day() %>"><%=ls.get(x).getAccount_day() %></option>
		<%
		}
		%>
		</select>
		
		<input type = "button" value = "검색하기" onClick = "send()">
		</form>
	</div>
	
	<div id="container" style="width: 700px;height:480px;">
        <canvas id="canvas" ></canvas>
    </div>  
	<script>
		function send(){
			sel.submit();
		}
	
	
        var color = Chart.helpers.color;
        var ChartData = {            
            labels: ['현금', '카드', '수리' , '튜닝', '발주', '관리' ], // 챠트의 항목명 설정
            datasets: [{
                label: '매출(흑자)',  // 데이터셑의 이름
                pointRadius: 15, // 꼭지점의 원크기
                pointHoverRadius: 30, // 꼭지점에 마우스 오버시 원크기                                   
                backgroundColor: color(window.chartColors.red).alpha(0.5).rgbString(), // 챠트의 백그라운드 색상
                borderColor: window.chartColors.red, // 챠트의 테두리 색상
                borderWidth: 1, // 챠트의 테두리 굵기
                lineTension:0, // 챠트의 유연성( 클수록 곡선에 가깝게 표시됨)
                fill:false,  // 선챠트의 경우 하단 부분에 색상을 채울지 여부                  
                data: [${vo.cash_sales},${vo.card_sales},0,0,0,0]  // 해당 데이터셋의 데이터 리스트
            }, {
                label: '대여및발주(적자)', 
                pointRadius: 5,
                pointHoverRadius: 10,                                    
                backgroundColor: color(window.chartColors.blue).alpha(0.5).rgbString(), 
                borderColor: window.chartColors.green, 
                borderWidth: 1,
                lineTension:0, 
                fill:false,                   
                data: [0,0,${vo.repair_fee},${vo.tuning_fee},${vo.order_fee},${vo.maintain_fee}] // 해당 데이터셋의 데이터 리스트
            }]
 
        };
        window.onload = function() {
            var ctx = document.getElementById('canvas').getContext('2d');
            window.myHorizontalBar = new Chart(ctx, {
                // type 을 변경하면 선차트, 가로막대차트, 세로막대차트 등을 선택할 수 있습니다 
                // ex) horizontalBar, line, bar, pie, bubble
                type: 'bar', 
                data: ChartData,
                options: {
                    responsive: true,                    
                    maintainAspectRatio: false    ,
                    title: {
                        display: true,
                        text: '일일 매출 현황'
                    }
                }
            });
        };
    </script>
    <tbody>
    <tr style="right">
    	<th><h2>총 흑자 : + ${vo.cash_sales+vo.card_sales} 원</h2></th>
    	<th><h2>총 적자 :  - ${vo.repair_fee + vo.tuning_fee + vo.order_fee + vo.maintain_fee}원</h2></th>
    	<th><h2>총 합 : ${vo.cash_sales+vo.card_sales - (vo.repair_fee + vo.tuning_fee + vo.order_fee + vo.maintain_fee)}원</h2></th>
    </tr>
    </tbody>
</div>
<%@ include file="/include/footer.jsp"%>
















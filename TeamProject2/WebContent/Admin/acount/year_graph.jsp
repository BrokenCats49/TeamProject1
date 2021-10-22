<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
               <%@ page import="java.util.*, Model.Admin.Acount.*" %>
           
           <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.text.SimpleDateFormat" %>
<%
AccountDAO dao = AccountDAO.getInstance();
List<AccountVO> ls = new ArrayList<AccountVO>();
String month = request.getParameter("month");
List<AccountVO> list = new ArrayList<AccountVO>();
list = dao.totalmsearch(month);
ls = dao.YearList();
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
		<h2 class="sub-title">년별 매출 현황</h2>
		<div class="sub-search">
		</div>
	</div>
	
		<form name = "sel" method = "post" action="year_graph.do">
		<select name = "year">
		<%
		for(int x=0;x<ls.size();x++){
		%>
		<option value = "<%=ls.get(x).getAccount_day()%>"><%=ls.get(x).getAccount_day() %></option>
		<%
		}
		%>
		</select>
		
		<input type = "button" value = "검색하기" onClick = "send()">
		</form>
	
	<div id="container" style="width: 800px;height:480px;">
        <canvas id="canvas" ></canvas>
    </div>  
	<script>
	
	function send(){
		sel.submit();
	}
        var color = Chart.helpers.color;
        var ChartData = {            
            labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], // 챠트의 항목명 설정
            datasets: [{
                label: '총매출(단위는 만단위)',  // 데이터셑의 이름
                pointRadius: 15, // 꼭지점의 원크기
                pointHoverRadius: 30, // 꼭지점에 마우스 오버시 원크기                                   
                backgroundColor: color(window.chartColors.red).alpha(0.5).rgbString(), // 챠트의 백그라운드 색상
                borderColor: window.chartColors.red, // 챠트의 테두리 색상
                borderWidth: 1, // 챠트의 테두리 굵기
                lineTension:0, // 챠트의 유연성( 클수록 곡선에 가깝게 표시됨)
                fill:false,  // 선챠트의 경우 하단 부분에 색상을 채울지 여부                  
                data: [${list[0].y_plus}, ${list[1].y_plus}, ${list[2].y_plus}, ${list[3].y_plus}, ${list[4].y_plus}, ${list[5].y_plus}, ${list[6].y_plus}, ${list[7].y_plus}, ${list[8].y_plus}, ${list[9].y_plus}, ${list[10].y_plus}, ${list[11].y_plus}]  // 해당 데이터셋의 데이터 리스트
            }]
 
        };
 
        window.onload = function() {
            var ctx = document.getElementById('canvas').getContext('2d');
            window.myHorizontalBar = new Chart(ctx, {
                // type 을 변경하면 선차트, 가로막대차트, 세로막대차트 등을 선택할 수 있습니다 
                // ex) horizontalBar, line, bar, pie, bubble
                type: 'pie', 
                data: ChartData,
                options: {
                    responsive: true,                    
                    maintainAspectRatio: false    ,
                    title: {
                        display: true,
                        text: 'Year 매출 현황(만 단위)'
                    }
                }
            });
 
        };
    </script>

</div>
<%@ include file="/include/footer.jsp"%>
















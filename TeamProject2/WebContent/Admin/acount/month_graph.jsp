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
ls = dao.MonthList();
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
		<h2 class="sub-title">월별 매출 현황</h2>
		<div class="sub-search">
		</div>
	</div>
	<form name = "sel" method = "post" action="month_graph.do">
		<select name = "month">
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
            labels: ['1일', '2일', '3일', '4일', '5일', '6일', '7일', '8일', '9일', '10일','11일', '12일', '13일', '14일', '15일', '16일', '17일', '18일', '19일', '20일','21일', '22일', '23일', '24일', '25일', '26일', '27일', '28일', '29일', '30일','31일'], // 챠트의 항목명 설정
            datasets: [{
                label: '매출(흑자)',  // 데이터셑의 이름
                pointRadius: 6, // 꼭지점의 원크기
                pointHoverRadius: 30, // 꼭지점에 마우스 오버시 원크기                                   
                backgroundColor: color(window.chartColors.red).alpha(0.5).rgbString(), // 챠트의 백그라운드 색상
                borderColor: window.chartColors.red, // 챠트의 테두리 색상
                borderWidth: 1, // 챠트의 테두리 굵기
                lineTension:0, // 챠트의 유연성( 클수록 곡선에 가깝게 표시됨)
                fill:false,  // 선챠트의 경우 하단 부분에 색상을 채울지 여부                  
                data: [${list[0].m_plus}, ${list[1].m_plus},${list[2].m_plus}, ${list[3].m_plus},${list[4].m_plus}, ${list[5].m_plus},${list[6].m_plus}, ${list[7].m_plus},${list[8].m_plus}, ${list[9].m_plus},${list[10].m_plus}, ${list[11].m_plus},${list[12].m_plus}, ${list[13].m_plus},${list[14].m_plus}, ${list[15].m_plus},${list[16].m_plus}, ${list[17].m_plus},${list[18].m_plus}, ${list[19].m_plus},${list[20].m_plus}, ${list[21].m_plus},${list[22].m_plus}, ${list[23].m_plus},${list[24].m_plus}, ${list[25].m_plus},${list[26].m_plus}, ${list[27].m_plus},${list[28].m_plus}, ${list[29].m_plus},${list[30].m_plus}]  // 해당 데이터셋의 데이터 리스트
            }, {
                label: '대여및발주(적자)', 
                pointRadius: 5,
                pointHoverRadius: 10,                                    
                backgroundColor: color(window.chartColors.blue).alpha(0.5).rgbString(), 
                borderColor: window.chartColors.green, 
                borderWidth: 1,
                lineTension:0, 
                fill:false,                 
                data: [${list[0].m_minus}, ${list[1].m_minus},${list[2].m_minus}, ${list[3].m_minus},${list[4].m_minus}, ${list[5].m_minus},${list[6].m_minus}, ${list[7].m_minus},${list[8].m_minus}, ${list[9].m_minus},${list[10].m_minus}, ${list[11].m_minus},${list[12].m_minus}, ${list[13].m_minus},${list[14].m_minus}, ${list[15].m_minus},${list[16].m_minus}, ${list[17].m_minus},${list[18].m_minus}, ${list[19].m_minus},${list[20].m_minus}, ${list[21].m_minus},${list[22].m_minus}, ${list[23].m_minus},${list[24].m_minus}, ${list[25].m_minus},${list[26].m_minus}, ${list[27].m_minus},${list[28].m_minus}, ${list[29].m_minus},${list[30].m_minus}] // 해당 데이터셋의 데이터 리스트
            }]
 
        };
 
        window.onload = function() {
            var ctx = document.getElementById('canvas').getContext('2d');
            window.myHorizontalBar = new Chart(ctx, {
                // type 을 변경하면 선차트, 가로막대차트, 세로막대차트 등을 선택할 수 있습니다 
                // ex) horizontalBar, line, bar, pie, bubble
                type: 'line', 
                data: ChartData,
                options: {
                    responsive: true,                    
                    maintainAspectRatio: false    ,
                    title: {
                        display: true,
                        text: '월 매출 현황'
                    }
                }
            });
 
        };
    </script>

</div>
<%@ include file="/include/footer.jsp"%>
















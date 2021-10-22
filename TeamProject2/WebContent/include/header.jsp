<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자 페이지</title>
	<link href="/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
	<link href="/css/common.css" rel="stylesheet">
	<link href="/css/mystyle.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

  <script>
    $(function(){
    	
      $('.slider').bxSlider({
    	  mode: 'fade',
    	  captions: true
      });
      
      $(".sitemap").click(function() {
			$(".sitewrap").slideDown();
		})
	  $("#close").click(function() {
			$(".sitewrap").slideUp();
		})
		
	  $(".nav > nav > .navi > li").hover(function() {
		  $(this).children(".navi2").stop().slideDown();
	  }, function() {
		  $(this).children(".navi2").stop().slideUp();
	  });
		
    });
  </script>

</head>
<body>
	<div class="header">
		<header>
			<div class="topnav">
				<ul>
					<li><a href="/member/login.jsp">로그인</a></li>
					<li><a href="/member/member.jsp">회원가입</a></li>
					<li><a href="javascript:void(0)" class="sitemap">사이트맵</a></li>
				</ul>
			</div>
			<div class="navigation">
				<h1 class="logo"><a href="/AdminLogin/index.jsp">Broken Cat</a></h1>
				<div class="nav">
					<nav>
						<ul class="navi">
							<li><a href="#">게시판 관리</a>
								<ul class="navi2">
									<li><a href="#">자유게시판 관리</a></li>
									<li><a href="#">공지게시판 관리</a></li>
									<li><a href="#">갤러리게시판 관리</a></li>
									<li><a href="#">회원게시판 관리</a></li>
									<li><a href="#">공략게시판 관리</a></li>
								</ul>
							</li>
							<li><a href="#">관리</a>
								<ul class="navi2">
									<li><a href="#">기기 관리</a></li>
									<li><a href="#">게임 관리</a></li>
									<li><a href="#">카드수량관리</a></li>
									<li><a href="#">관리</a></li>
								</ul>
							</li>
							<li><a href="/Admin/order/order_list.jsp">발주현황</a>
								<ul class="navi2">
									<li><a href="/Admin/order/order_lever.do">발주등록-레버</a></li>
									<li><a href="/Admin/order/order_button_ac.do">발주등록-아케이드버튼</a></li>
									<li><a href="/Admin/order/order_button_rg.do">발주등록-리듬게임버튼</a></li>
									<li><a href="/Admin/order/order_parts.do">발주등록-부품</a></li>
									
								</ul>
							</li>
							<li><a href="/Admin/stock/stock_list.jsp">재고관리</a>
								<ul class="navi2">
									<li><a href="/Admin/recive/recive_write.jsp">입고등록</a></li>
									<li><a href="/Admin/recive/recive_list.jsp">입고현황</a></li>
									<li><a href="/Admin/stock/stock_list.jsp">재고관리</a></li>
								</ul>
							</li>
							<li><a href="/Admin/acount/day_graph.jsp">일일정산</a>
								<ul class="navi2">
									<li><a href="/Admin/acount/day_acount_ready.do">준비금등록</a></li>
									<li><a href="/Admin/acount/day_acount_game.do">게임매출등록</a></li>
									<li><a href="/Admin/acount/day_acount_shop.do">가게매출등록</a></li>
									<li><a href="/Admin/acount/day_acount_repair.do">수리비용등록</a></li>
									<li><a href="/Admin/acount/day_acount_tuning.do">튜닝비용등록</a></li>
									<li><a href="/Admin/acount/day_acount_total.do">일일정산</a></li>
									<li><a href="/Admin/acount/day_graph.do">일 통계</a></li>
									<li><a href="/Admin/acount/month_graph.jsp">월 통계</a></li>
									<li><a href="/Admin/acount/year_graph.jsp">연 통계</a></li>
								</ul>
							</li>
							<li><a href="/Admin/admin_list.jsp">회원관리</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</header>
	</div>
	
	<div class="line"></div>
	
	<div class="sitewrap">
		<span class="fa fa-close" id="close" style="cursor:pointer"></span>
		<div class="inner">
			<span class="map"><a href="#">게시판 관리</a></span>
			<span class="map"><a href="/Admin/stock/stock_list.jsp">재고 관리</a></span>
			<span class="map"><a href="/Admin/order/order.jsp">발주 관리</a></span>
			<span class="map"><a href="/Admin/recive/recive_list.jsp">입고 관리</a></span>
			<span class="map"><a href="/Admin/acount/day_graph.jsp">매출 시스템</a></span>
			<span class="map"><a href="/Admin/admin_list.jsp">회원 관리</a></span>
		</div>
	</div>
	
	
	
	
	
	
	
	
	
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>BROKEN CAT</title>
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
			<c:if test="${empty uvo}">	
					<li><a href="/member/member_login.do">로그인</a></li>
					<li><a href="/member/member_insert.do">회원가입</a></li>
					
			</c:if>
			<c:if test="${!empty uvo}">
					<li><h3>${uvo.userid}/${uvo.name}</h3></li>
					<li><a href="/member/member_logout.do">로그아웃</a></li>
					<li><a href="/member/member_modify.do">회원수정</a></li>
					<li><a href="/member/member_cash.do">카드충전</a></li>
			</c:if>		
				</ul>
			</div>
			<div class="navigation">
				<h1 class="logo"><a href="/index.do">BROKEN CAT</a></h1>
				<div class="nav">
					<nav>
						<ul class="navi">
							<li><a href="/about/about.jsp">역사</a>
						
							</li>
							<li><a href="/gallery/gallery_list.do">유저자랑</a>

							</li>
							<li><a href="/portfolio/pds_list.do">공략공유</a>
							</li>
							<li><a href="/qa/qa_list.do">대여신청</a>
							</li>
							<li><a href="/notice/notice_list.do">공지사항</a></li>
							<li><a href="/board/board_list.do">자유게시판</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</header>
	</div>
	
	<div class="line"></div>
	
	<div class="sitewrap" >
		<span class="fa fa-close" id="close" ></span>
		<div class="inner" >
			<span class="map"><a href="about/about.jsp" style=color:#ffffff>#</a></span>
			<span class="map" ><a href="/gallery/gallery_list.do" style=color:#ffffff>#</a></span>
			<span class="map" ><a href="/portfolio/pds_list.do" style=color:#ffffff>#</a></span>
			<span class="map"><a href="/qa/qa_list.do" style=color:#ffffff>#</a></span>
			<span class="map"><a href="/notice/notice_list.do" style=color:#ffffff>#</a></span>
			<span class="map"><a href="/board/board_list.do" style=color:#ffffff>#</a></span>
		</div>
	</div>
	
	
	
	
	
	
	
	
	
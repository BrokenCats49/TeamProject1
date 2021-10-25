<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/include/header.jsp"%>
<c:if test="${row==0}">
	<script>
		alert("죄송합니다. \n등록에 실패하였습니다.");
		history.back();
	</script>
</c:if>
<c:if test="${row==1}">
	<script>
		alert("글이 등록처리 되었습니다.");
		location.href="/portfolio/pds_list.do";
	</script>
</c:if>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">공략공유 게시판</h2>
		<div class="sub-search">
			<form name="my" method="post" action="/portfolio/pds_list.do" onsubmit="return check()">
				<select name="search" class="sel">
					<option value="subject" <c:if test="${search=='subject'}"> selected </c:if>>제목</option>
					<option value="name" <c:if test="${search=='name'}"> selected </c:if>>작성자</option>
					<option value="contents" <c:if test="${search=='contents'}"> selected </c:if>>내용</option>
				</select>
				<input type="text" name="key" value="${key}" class="text">
				<input type="submit" value="검색" class="btn">
			</form>
		</div>
	</div>
	
		<ul class="sub-port-content">
<c:if test="${!empty list2}">
		<c:forEach var="port" items="${list2}">			
			<li>
				<span class="date"><em>${port.regdate.substring(5,7)}</em>${port.regdate.substring(8,10)}</span>
				<div class="text-wrap">
					<div class="img-wrap">
						<img src="/images/easy.jpg" alt="등록자료">
					</div>
					<div class="text-body">
						<span>${port.idx}</span> | <span class=""><i class="fa fa-eye"></i> ${port.readcnt}</span>
						<a href="/portfolio/pds_view.do?page=${page}&idx=${port.idx}">
							<p class="title">${port.subject}</p>
							<p class="text-cont">${port.contents}</p>
						</a>
					</div>
				</div>
			</li>
	</c:forEach>
</c:if>
<c:if test="${empty list2}">		
			<li>
				<span class="date"><em>00</em>0000-00</span>
				<div class="text-wrap">
					<div class="img-wrap">
						<img src="/images/noting.jpg" alt="자료가 없다">
					</div>
					<div class="text-body">
						<span>No.00</span> | <span class=""><i class="fa fa-eye"></i>00</span>
						<a href="portfolioview.jsp">
							<p class="title">아무것도</p>
							<p class="text-cont">아무것도 없다. 
							뭐라도 올려봐라<br>
							엉?<br></p>
						</a>
					</div>
				</div>
			</li>
</c:if>			
		</ul>
		
		<div class="paging">
			<ul>
				<li>${pageskip}</li>
			</ul>
		</div>
<c:if test="${!empty mvo}">	
	<a href="/portfolio/pds_write.do?page=${page}" class="btn-write">글쓰기</a>
</c:if>
<c:if test="${empty mvo}">

</c:if>

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
















<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>OJH Mall</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<link href="css/justified-nav.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/theme.css" rel="stylesheet">

<!-- 검색 js -->
<script src="js/ojhfunction.js"></script>
</head>

<body>

	<div class="container">
		<div class="masthead">
			<a href="http://localhost:8080/ojhmall"><img src="image/11st.png"></a>

			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${empty sessionScope.userLogInInfo.userName}">
						<li><a href="user/LogInForm.do">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="logOut.do">${sessionScope.userLogInInfo.userName}님</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty sessionScope.userLogInInfo.userName}">
						<li><a href="user/SiginUpForm.do">회원가입</a></li>
						<li><a href="cart/cartView.do">장바구니</a></li>
						<li><a href="#">마이페이지</a></li>
					</c:when>

					<c:when test="${sessionScope.userLogInInfo.userType == ADMIN}">
						<li><a href="user/AdminInfoForm.do">회원정보</a></li>
						<li><a href="cart/cartView.do">장바구니</a></li>
						<li><a href="mypage/mypageForAdmin.do">마이페이지</a></li>
					</c:when>
					<c:when test="${sessionScope.userLogInInfo.userTypeNum == 1}">
						<li><a href="user/CustomerInfoForm.do">회원정보</a></li>
						<li><a href="cart/cartView.do">장바구니</a></li>
						<li><a href="mypage/mypageForCustomer.do">마이페이지</a></li>
					</c:when>
					<c:when test="${sessionScope.userLogInInfo.userTypeNum == 2}">
						<li><a href="user/SellerInfoForm.do">회원정보</a></li>
						<li><a href="cart/cartView.do">장바구니</a></li>
						<li><a href="mypage/mypageForSeller.do">마이페이지</a></li>
					</c:when>

				</c:choose>
			</ul>
		</div>
		<div class="masthead">

			<nav class="navbar navbar-default">
				<ul class="nav nav-justified">
					<c:choose>
						<c:when test="${fn:length(upperCategoryList) > 0 }">
							<c:forEach items="${upperCategoryList}" var="row">
								<li class="dropdown-toggle"><a href="#"
									class="dropdown-toggle" data-toggle="dropdown" role="button"
									aria-expanded="false"> ${row.ctgrName } <span class="caret"></span>
								</a>
									<ul class="dropdown-menu" role="menu">
										<c:forEach items="${lowerCategoryList}" var="col">
											<c:if
												test="${col.ctgrNumber > row.ctgrNumber && col.ctgrNumber < (row.ctgrNumber + 100)}">
												<li><a
													href="category/ctgrView.do?ctgrVal=${col.ctgrNumber }">${col.ctgrName }</a></li>
											</c:if>
										</c:forEach>

									</ul></li>
							</c:forEach>
						</c:when>
					</c:choose>
				</ul>
			</nav>
		</div>
		<!-- 상품 검색 -->
		<div class="container">
			<form class="navbar-form navbar-default" id="form_search"
				role="search" action="product/prdSearch.do" method="POST">
				<div class="form-group">
					<input type="text" class="form-control" id="prdName" name="prdName"
						placeholder="검색어를 입력하세요" value="${prdName}" />
				</div>
				<button type="submit" class="btn btn-default" onclick="search();">검색</button>
			</form>
		</div>
		<!-- 상품 진열 관심지수순 -->
		<div class="container">
			<h1><span class="label label-danger">베스트 상품</span></h1>
			<c:choose>
				<c:when test="${fn:length(prdList) > 0 }">
					<c:forEach items="${prdList}" var="row">
						<div class="col-lg-4">
							<a href=product/prdView.do?prdNum=${row.prdNumber}> <img
								src="${row.image}" width="240" height="240">
								<h4>${row.prdName}</h4> 
								<font color="orange" size="4">${row.price}원</font> 
							</a> 관심지수 : ${row.hitCount}
						</div>
					</c:forEach>
				</c:when>
			</c:choose>
		</div>

		<!-- Site footer -->
		<footer class="footer">
			<p>&copy; Company 2014</p>
		</footer>
	</div>
	<!-- /container -->


	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>

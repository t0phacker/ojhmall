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
<link rel="icon" href="../../favicon.ico">

<title>Product</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<link href="../css/justified-nav.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="../css/theme.css" rel="stylesheet">
<!-- 검색 js -->
<script src="../js/ojhfunction.js"></script>
</head>

<body>

	<div class="container">
		<div class="masthead">
			<a href="#"><img src="../image/11st.png"></a>

			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${empty sessionScope.userLogInInfo.userName}">
						<li><a href="../user/LogInForm.do">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="../logOut.do">${sessionScope.userLogInInfo.userName}님</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty sessionScope.userLogInInfo.userName}">
						<li><a href="../user/SiginUpForm.do">회원가입</a></li>
					</c:when>

					<c:when test="${sessionScope.userLogInInfo.userType == 0}">
						<li><a href="../user/AdminInfoForm.do">회원정보</a></li>
					</c:when>
					<c:when test="${sessionScope.userLogInInfo.userType == 1}">
						<li><a href="../user/CustomerInfoForm.do">회원정보</a></li>
					</c:when>
					<c:when test="${sessionScope.userLogInInfo.userType == 2}">
						<li><a href="../user/SellerInfoForm.do">회원정보</a></li>
					</c:when>

				</c:choose>
				<li><a href="../cart/cartView.do?userNumber">장바구니</a></li>
				<li><a href="#">마이페이지</a></li>
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
													href="../category/ctgrView.do?ctgrVal=${col.ctgrNumber }">${col.ctgrName }</a></li>
											</c:if>
										</c:forEach>

									</ul></li>
								</li>
							</c:forEach>
						</c:when>
					</c:choose>
				</ul>
			</nav>
		</div>
		<!-- 상품 검색 -->
		<div class="container">
			<form class="navbar-form navbar-left" id="form_search" role="search"
				action="prdSearch.do" method="POST">
				<div class="form-group">
					<input type="text" class="form-control" id="prdName" name="prdName" placeholder="Search"
						value="${prdName}" />
				</div>
				<button type="submit" class="btn btn-default" onclick="search();">Submit</button>
			</form>
		</div>
		<!-- Jumbotron -->
		<div class="row">
		<h2>"${prd.prdName}" 검색결과</h2>
			<c:choose>
				<c:when test="${fn:length(prdSchList) > 0 }">
					<c:forEach items="${prdSchList}" var="row">
						<div class="col-lg-4">
							<a href=../product/prdView.do?prdNum=${row.prdNumber}>
								<li><img src="${row.image}" width="240" height="240"></li>
							</a>
						</div>
						<div class="col-lg-7">
							<a href=../product/prdView.do?prdNum=${row.prdNumber}>
								<li><h4>${row.prdName}</h4></li>
								<li>${row.price}원</li>
								<li>${row.text}</li>
								<li>관심지수 : ${row.hitCount}</li>
							</a>
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
	<script src="../js/bootstrap.min.js"></script>
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

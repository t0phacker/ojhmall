<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<%
	
%>
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

<script languege="javascript">
	var prdAmount = 1;
	var totalPrice = 1;
	// 상품 수량 증가
	function plusPrdAmount(id) {
		prdAmount = document.getElementById(id).value;
		prdAmount++;
		document.getElementById(id).value = prdAmount;
	}
	// 상품 수량 감소
	function minusPrdAmount(id) {
		prdAmount = document.getElementById(id).value;
		if (prdAmount > 1) {
			prdAmount--;
			document.getElementById(id).value = prdAmount;
		}
		else {
			alert("최소수량은 1 이상입니다.");
			prdAmount = 1;
		}

	}
	// 상품 가격 계산
	function calPrdPrice(id) {
		var prdPrice = document.getElementById(id).value;
		totalPrice = prdPrice * prdAmount;
	}
	// 상품 가격 출력
	function printPrice(id) {
		document.getElementById(id).value = totalPrice;
	}
	// 상품 수량을 form에 전송
	function sendAmount(id) {
		document.getElementById(id).value = prdAmount;
	}
	// 상품 가격을 form에 전송
	function sendTotalPrice(id) {
		document.getElementById(id).value = totalPrice;
	}
	// 장바구니 추가시 확인 메세지
	function alertCartMsg() {
		alert(prdAmount + "개의 상품이 장바구니에 담겼습니다");
	}
</script>
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
				<li><a
					href="../cart/cartView.do">장바구니</a></li>
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
					<input type="text" class="form-control" id="prdName" name="prdName"
						placeholder="Search" value="${prdName}" />
				</div>
				<button type="submit" class="btn btn-default" onclick="search();">Submit</button>
			</form>
		</div>
		<!-- Jumbotron -->
		<div class="row">
			<div class="col-lg-4">
				<li><img src="${prdInfo.image}" width="240" height="240"></li>

			</div>
			<div class="col-lg-7">
				<li><h3>${prdInfo.prdName}</h3></li>
				<li>가격 : ${prdInfo.price}원</li>
				<form name="amountForm" method="post">
					<input type="hidden" id="prdPrice" name="prdPrice" value="${prdInfo.price}">
					<input type="button" class="btn btn-default" id="minusPrdBtn"
						name="minusPrdBtn" role="button" value="-"
						onclick="minusPrdAmount('amount'); calPrdPrice('prdPrice'); printPrice('totalPrdPrice');sendAmount('prdAmount');sendTotalPrice('cartPrice');"> <input type="text"
						id="amount" name="amount" value="1"> <input type="button"
						class="btn btn-default" id="plusPrdBtn" name="plusPrdBtn"
						role="button" value="+" onclick="plusPrdAmount('amount'); calPrdPrice('prdPrice');printPrice('totalPrdPrice');sendAmount('prdAmount');sendTotalPrice('cartPrice');">
					<br>총 가격 <input type="text" id="totalPrdPrice" name="totalPrdPrice" value="${prdInfo.price}" style="font-size=4; color=#dc143c" disabled>
				</form>
				<!-- 상품번호 장바구니로 전송 -->
				<a class="btn btn-lg btn-warning"
					href="../cart/addPrdToCart.do?prdNum=${prdNumber}" role="button">구매하기</a>
					<form id="singleCart" name="singleCart" method="post" action="../cart/addPrdToCart.do">
						<input type="hidden" id="prdNumber" name="prdNumber" value="${prdInfo.prdNumber}">
						<input type="hidden" id="prdAmount" name="prdAmount" value="1">
						<input type="hidden" id="cartPrice" name="cartPrice" value="${prdInfo.price}">
						<input type="submit" class="btn btn-lg btn-info" role="button" value="장바구니" onclick="alertCartMsg();">
					</form>
				<!-- <a class="btn btn-lg btn-info" href="#" role="button">장바구니</a> -->
			</div>

		</div>
		<div class="jumbotron">
			<h4 style="float: left">${prdInfo.text}</h4>
			<p class="lead">${prdInfo.hitCount}명의 고객님이 이 상품을 확인하였습니다.</p>
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

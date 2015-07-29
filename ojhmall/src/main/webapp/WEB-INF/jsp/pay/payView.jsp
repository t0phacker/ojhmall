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

<title>Pay</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<link href="../css/justified-nav.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="../css/theme.css" rel="stylesheet">
<!-- 검색 js -->
<script src="../js/ojhfunction.js"></script>

<script type="text/javascript">
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
		} else {
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

	function checkHome(selected) {

		var registered = document.getElementById("registeredList"); // 기존 배송지
		var newHome = document.getElementById("newHomeList"); // 새로 입력

		if (selected == 0) {
			registered.style.display = "block";
			newHome.style.display = "none";
			document.getElementById("registered").check = true;
			document.getElementById("newHome").check = false;
		} else {
			registered.style.display = "none";
			newHome.style.display = "block";
			document.getElementById("registered").check = false;
			document.getElementById("newHome").check = true;
		}
	}
</script>
</head>

<body>

	<div class="container">
		<div class="masthead">
			<a href="http://localhost:8080/ojhmall"><img src="../image/11st.png"></a>

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
						<li><a href="../cart/cartView.do">장바구니</a></li>
						<li><a href="../mypage/mypageForAdmin.do">마이페이지</a></li>
					</c:when>

					<c:when test="${sessionScope.userLogInInfo.userType == 0}">
						<li><a href="../user/AdminInfoForm.do">회원정보</a></li>
						<li><a href="../cart/cartView.do">장바구니</a></li>
						<li><a href="../mypage/mypageForAdmin.do">마이페이지</a></li>
					</c:when>
					<c:when test="${sessionScope.userLogInInfo.userType == 1}">
						<li><a href="../user/CustomerInfoForm.do">회원정보</a></li>
						<li><a href="../cart/cartView.do">장바구니</a></li>
						<li><a href="../mypage/mypageForCustomer.do">마이페이지</a></li>
					</c:when>
					<c:when test="${sessionScope.userLogInInfo.userType == 2}">
						<li><a href="../user/SellerInfoForm.do">회원정보</a></li>
						<li><a href="../cart/cartView.do">장바구니</a></li>
						<li><a href="../mypage/mypageForSeller.do">마이페이지</a></li>
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
													href="../category/ctgrView.do?ctgrVal=${col.ctgrNumber }">${col.ctgrName }</a></li>
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
			<form class="navbar-form navbar-left" id="form_search" role="search"
				action="../product/prdSearch.do" method="POST">
				<div class="form-group">
					<input type="text" class="form-control" id="prdName" name="prdName"
						placeholder="검색어를 입력하세요" value="${prdName}" />
				</div>
				<button type="submit" class="btn btn-default" onclick="search();">검색</button>
			</form>
		</div>
		<!-- Jumbotron -->
		<div class="container">
			<h2>"은행에 입금해 주시면 배송이 시작됩니다."</h2>
			<h4>입금완료 후에 상품 배송이 시작됩니다.</h4>
			<div class="col-md-12">
				<h4>입금은행 정보</h4>
				<table class="table table-striped" id="accountTable">
					<tbody>
						<tr>
							<td>입금정보</td>
							<td>${adminInfo.accNum} / 예금주명은 ${adminInfo.userName}으로 확인됩니다.</td>
						</tr>
						<tr>
							<td>입금할 금액</td>
							<td>${totalPrice}원</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- 주문 상품 정보 -->
			<div class="col-md-12">
				<h4>주문하신 상품정보</h4>
				<form name="orderForm">
					<table class="table table-striped" id="cartTable">
						<thead>
							<tr>
								<th>상품정보</th>
								<th>수량</th>
								<th>상품금액</th>
								<th>배송비</th>
								<th>판매자</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${fn:length(orderList) > 0 }">
									<c:forEach items="${orderList}" var="row" varStatus="status">
										<tr>
											<td width=50% height="120"><img style="float: left"
												src="${row.image }" width="120" height="120">
												<h5>${row.prdName}</h5></td>
											<td>${row.count}개<br>
											</td>
											<td>${row.price}원</td>
											<c:if test="${row.deliveryFee == 0}">
												<td>무료</td>
											</c:if>
											<c:if test="${row.deliveryFee != 0}">
												<td>${row.deliveryFee}원</td>
											</c:if>
											<td>${row.id}</td>
										</tr>
									</c:forEach>
								</c:when>
							</c:choose>
						</tbody>
					</table>
				</form>
			</div>
			<!-- 총 주문 금액 -->
			<div class="col-md-12">
				<form>
					<h4>결제내역</h4>
					<table class="table table-bordered" id="priceTable">
						<tbody>
							<tr>
								<td>결제금액</td>
								<td>${totalPrice}원</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<!-- 구매자 정보 -->
			<div class="col-md-12">
				<form>
					<h4>배송지 정보</h4>
					<table class="table table-bordered" id="deliveryTable">
						<tbody id="registeredList" style="display: block">
							<tr>
								<td>받는사람</td>
								<td>${userInfo.userName } <br> (${userInfo.zipCode })&nbsp
									${userInfo.address } <br> ${userInfo.phNumber }
								</td>
						</tbody>
					</table>
				</form>
			</div>
		</div>
		<div style="text-align:center">
		<a href="http://localhost:8080/ojhmall" type="submit" class="btn btn-lg btn-danger">주문완료확인</a>
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

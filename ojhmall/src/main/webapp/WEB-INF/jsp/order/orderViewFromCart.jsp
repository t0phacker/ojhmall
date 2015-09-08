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

<title>Order Bill</title>

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
		<!-- 로고 & 회원메뉴 -->
		<jsp:include page="/WEB-INF/jsp/layout/userBar.jsp" />
		<!-- 카테고리 바 -->
		<jsp:include page="/WEB-INF/jsp/layout/ctgrBar.jsp" />
		<!-- 상품 검색 -->
		<jsp:include page="/WEB-INF/jsp/layout/srchBar.jsp" />
		<!-- Jumbotron -->
		<div class="container">
			<h2>주문/결제</h2>
			<!-- 주문 상품 정보 -->
			<div class="col-md-12">
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
					<h4>가격 정보</h4>
					<table class="table table-bordered" id="priceTable">
						<thead>
							<tr>
								<th>주문금액</th>
								<th>배송금액</th>
								<th>총금액</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${totalPrice}원</td>
								<td>${totalDelivery}원</td>
								<td>${totalPrice}원</td>
							</tr>

						</tbody>
					</table>
				</form>
			</div>
			<!-- 구매자 정보 -->
			<div class="col-md-12">
				<form>
					<h4>배송지 정보 입력</h4>
					<table class="table table-bordered" id="deliveryTable">
						<thead>
							<tr>
								<th width="200px">배송지 선택</th>
								<th><input type="radio" id="registered" name="homeCheck"
									onclick="checkHome(0);" checked> 기존 배송지 <input
									type="radio" id="newHome" name="homeCheck"
									onclick="checkHome(1);"> 새로 입력</th>
							</tr>
						</thead>
						<tbody id="registeredList" style="display: block">
							<tr>
								<td>받으시는 분</td>
								<td>${userInfo.userName }</td>
							</tr>
							<tr>
								<td>우편번호</td>
								<td>${userInfo.zipCode }</td>
							</tr>
							<tr>
								<td>주소</td>
								<td>${userInfo.address }</td>
							</tr>
							<tr>
								<td>휴대전화</td>
								<td>${userInfo.phNumber }</td>
							</tr>
							<tr>
								<td>배송시 요구사항</td>
								<td><input type="text" id="requestFromUser"
									name="requestFromUser"></td>
							</tr>
						</tbody>
						<tbody id="newHomeList" style="display: none">
							<tr>
								<td>받으시는 분</td>
								<td><input type="text" id="newGetter" name="newGetter"></td>
							</tr>
							<tr>
								<td>우편번호</td>
								<td><input type="text" id="newZipCode" name="newZipCode"></td>
							</tr>
							<tr>
								<td>주소</td>
								<td><input type="text" id="newAddress" name="newAddress"></td>
							</tr>
							<tr>
								<td>휴대전화</td>
								<td><input type="text" id="newPhNumber" name="newPhNumber"></td>
							</tr>
							<tr>
								<td>배송시 요구사항</td>
								<td><input type="text" id="requestFromUser"
									name="requestFromUser"></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<!-- 결제 정보 입력 -->
			<div class="col-md-12">
				<form action="../pay/payView.do" method="post">
				<h4>결제 정보 입력</h4>
				<table class="table table-bordered" id="payTable">
					<thead>
						<tr>
							<th>결제 수단</th>
							<th>최종 결제금액</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="radio" id="bankAccount" name="bankAccount"
								checked>무통장 입금</td>
							
							<td>${totalPrice}원<br> <input type="text"
								id="orderNumber" name="orderNumber" value="${orderNumber }">
								<input type="text"
								id="cartNumber" name="cartNumber" value="${cartNumbers }">
								<input type="submit" class="btn btn-danger" value="결제하기">
								<a class="btn btn-default" href="http://localhost:8080/ojhmall" role="button">취소하기</a>
							</td>
							
						</tr>
					</tbody>
				</table>
				</form>
			</div>
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

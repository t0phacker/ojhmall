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

<title>Change Cart Information</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript">
function plusPrdAmount(id) {
	prdAmount = parseInt(document.getElementById(id).value);
	if (parseInt(prdAmount) < parseInt(prdStock)) {
		prdAmount++;
		document.getElementById(id).value = prdAmount;
	}
	else {
		alert("재고수량이 " + prdStock + "개 입니다. 더 이상 선택하실 수 없습니다.");
		prdAmount = 1;
		document.getElementById(id).value = prdAmount;
		
	}
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
</script>

</head>

<body>

	<div class="container">
		<div class="col-md-12">
			<form name="cartForm">
				<table class="table table-striped" id="cartTable">
					<thead>
						<tr>
							<th>상품정보</th>
							<th>상품금액</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width=75% height="120"><img style="float: left"
								src="${selectedCart.image }" width="120" height="120">
								<h5>${selectedCart.prdName}</h5></td>
							<td>${selectedCart.price}원</td>
						</tr>
						<tr>
							<td>수량</td>
							<td>총합계금액</td>
						</tr>
						<tr>
							<td><input type="hidden" id="prdPrice" name="prdPrice"
								value="${selectedCart.price}"> <input type="button"
								class="btn btn-xs btn-default" id="minusPrdBtn"
								name="minusPrdBtn" role="button" value="-"
								onclick="minusPrdAmount('amount'); calPrdPrice('prdPrice'); printPrice('cartTable');sendInfo();">
								<input type="text" id="amount" name="amount"
								value="${selectedCart.prdAmount}"
								style="width: 60px; height: 30px;"> <input type="button"
								class="btn btn-xs btn-default" id="plusPrdBtn" name="plusPrdBtn"
								role="button" value="+"
								onclick="plusPrdAmount('amount'); calPrdPrice('prdPrice');printPrice('cartTable');sendInfo();">
								
							</td>
							<td>${selectedCart.cartPrice}원</td>
						</tr>
					</tbody>
				</table>
			</form>
			<input type="hidden" id="stock" value="${selectedCart.stock }">
			<form method="GET" action="updateCart.do">
				<div style="text-align: center">
					<input type="hidden" id="prdAmount" name="prdAmount"
						value="${selectedCart.prdAmount}"> <input type="hidden"
						id="cartNumber" name="cartNumber"
						value="${selectedCart.cartNumber}"> <input type="hidden"
						id="cartPrice" name="cartPrice" value="${selectedCart.cartPrice}">
					<input type="submit" class="btn btn-danger" value="변경하기"
						onclick="sendForm()"> <a class="btn btn-default"
						onclick="window.close()">닫기</a>
				</div>
			</form>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../../../js/bootstrap.min.js"></script>

	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
	
	<script type="text/javascript">
	var totalPrice = 1;
	var prdAmount = 1;
	var prdStock = parseInt(document.getElementById('stock').value);

	// 상품 수량 증가
	
	// 상품 가격 계산
	function calPrdPrice(id) {
		var prdPrice = document.getElementById(id).value;
		totalPrice = prdPrice * prdAmount;
	}
	// 상품 가격 출력
	function printPrice(id) {
		var cartTable = document.getElementById("cartTable");
		cartTable.rows[3].cells[1].innerHTML = totalPrice + "원";
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
	// 상품 수량, 총주문금액 폼에 전송
	function sendInfo() {
		var testAmount = document.getElementById("amount").value;
		var prdPrice = document.getElementById("prdPrice").value;
		document.getElementById("prdAmount").value = testAmount;
		document.getElementById("cartPrice").value = prdPrice * testAmount;
	}
	function sendForm() {
		if (window.opener && !window.opener.closed) {
			window.opener.location = "cartView.do"
			alert("수량을 변경하였습니다.");
			window.close();
		} else {
			alert("기존 페이지가 닫혀있습니다.");
		}
	}
</script>
</body>
</html>

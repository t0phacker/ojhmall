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

<title>Product</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<link href="../css/justified-nav.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="../css/theme.css" rel="stylesheet">
<!-- 검색 js -->
<script src="../js/ojhfunction.js"></script>

<script type="text/javascript">
	
	var totalPrice = 1;
	var prdAmount = 1;
	window.onload = function() {
		if (document.getElementById('stock').value == 0) {
			document.getElementById('soldOut').style.display = "block";
			document.getElementById('orderDirect').disabled = true;
			document.getElementById('toCart').disabled = true;
		}
	}
	// 상품 수량 증가
	function plusPrdAmount(id) {
		prdAmount = parseInt(document.getElementById(id).value);
		var prdStock = parseInt(document.getElementById('stock').value);
		if (prdAmount >= prdStock) {
			alert("재고수량이 " + prdStock + "개 입니다. 더 이상 선택하실 수 없습니다.");
			prdAmount = 1;
			document.getElementById(id).value = prdAmount;
		}
		else {
			prdAmount++;
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
	// 상품 가격 계산
	function calPrdPrice(id) {
		var prdPrice = document.getElementById(id).value;
		totalPrice = prdPrice * prdAmount;
	}
	// 상품 가격 출력
	function printPrice(id) {
		document.getElementById(id).value = totalPrice;
		document.getElementById("price").value = totalPrice;
	}
	// 상품 수량을 form에 전송
	function sendAmount(id) {
		document.getElementById(id).value = prdAmount;
		document.getElementById("count").value = prdAmount;
	}
	// 상품 가격을 form에 전송
	function sendTotalPrice(id) {
		document.getElementById(id).value = totalPrice;
	}
	
	function alertCartMsg() {
		alert(prdAmount + "개의 상품이 장바구니에 담겼습니다");
	}
	// 장바구니 추가시 확인 메세지
	function checkStock() {
		
		//var prdAmount = document.getElementById('amount').value;
		if (prdAmout > prdStock) {
			alert("죄송합니다. 재고량보다 많습니다.(재고 : " + prdAmoount + ")");
			location.reload(true);
		}
		else {
			alert(prdAmount + "개의 상품이 장바구니에 담겼습니다");
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
		<div class="row">
			<div class="col-lg-4">
				<img src="${prdInfo.image}" width="240" height="240">
			</div>
			<div class="col-lg-7">
				<h3>${prdInfo.prdName}</h3>
				가격 : ${prdInfo.price}원
				<form name="amountForm" method="post">
					<input type="hidden" id="prdPrice" name="prdPrice"
						value="${prdInfo.price}"> <input type="button"
						class="btn btn-default" id="minusPrdBtn" name="minusPrdBtn"
						role="button" value="-"
						onclick="minusPrdAmount('amount'); calPrdPrice('prdPrice'); printPrice('totalPrdPrice');sendAmount('prdAmount');sendTotalPrice('cartPrice');">
					<input type="text" id="amount" name="amount" value="1"> <input
						type="button" class="btn btn-default" id="plusPrdBtn"
						name="plusPrdBtn" role="button" value="+"
						onclick="plusPrdAmount('amount'); calPrdPrice('prdPrice');printPrice('totalPrdPrice');sendAmount('prdAmount');sendTotalPrice('cartPrice');">
					<br>총 가격 <input type="text" id="totalPrdPrice"
						name="totalPrdPrice" value="${prdInfo.price}" style="" disabled>
				</form>
				<!-- 상품에서 직접 주문 -->
				<form action="../order/orderByPrd.do" method="post">
					<input type="hidden" id="prdNumber" name="prdNumber"
						value="${prdInfo.prdNumber}"> <input type="hidden"
						id="count" name="count" value="1"> <input type="hidden"
						id="price" name="price" value="${prdInfo.price}"> <input
						type="submit" id="orderDirect" class="btn btn-lg btn-warning" role="button"
						value="구매하기" onclick="checkStock();">
				</form>
				<!-- 상품번호 장바구니로 전송 -->
				<form id="singleCart" name="singleCart" method="post"
					action="../cart/addPrdToCart.do">
					<input type="hidden" id="prdNumber" name="prdNumber"
						value="${prdInfo.prdNumber}"> <input type="hidden"
						id="prdAmount" name="prdAmount" value="1"> <input
						type="hidden" id="cartPrice" name="cartPrice"
						value="${prdInfo.price}"> <input type="submit" id="toCart"
						class="btn btn-lg btn-info" role="button" value="장바구니"
						onclick="alertCartMsg();">
				</form>
				<h3 id="soldOut" style="display: none">품절된 상품입니다.</h3>
				<input type="hidden" id="stock" value="${prdInfo.stock}">
				<!-- <a class="btn btn-lg btn-info" href="#" role="button">장바구니</a> -->
			</div>

		</div>
		<div class="jumbotron">
			<h4 style="float: left">${prdInfo.text}</h4>
			<p class="lead">${prdInfo.hitCount}명의고객님이 이상품을 확인하였습니다.</p>
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

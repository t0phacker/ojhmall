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

<script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	var cartPrice = 0;
	var checkCount = 0;
	var rowNumber = 0;
	
	// 화면 로딩 후 체크박스 전체 체크
	window.onload = function() {
		var cartTable = document.getElementById("cartTable");
		rowNumber = cartTable.rows.length;
		checkCount = rowNumber-1;
		document.getElementById("checkAll").checked = true;
		for (var n = 1; n <= rowNumber-1; n++) {
			var fname = 'c' + n; 
			document.getElementById(fname).checked = true;
			cartPrice += parseInt(document.getElementById(fname).value);
		}
	}
	// 상품 전체 선택
	function check(count) { 
		
		for (var n = 1; n <= count; n++) {
			var fname = 'c' + n; 

			if (document.cartForm.checkAll.checked) {
				if (document.getElementById(fname).checked == false) {
					cartPrice += parseInt(document.getElementById(fname).value);
				}
				document.getElementById(fname).checked = true;
			} else {
				document.getElementById(fname).checked = false;
				cartPrice = 0;
			}
		}
		if (document.cartForm.checkAll.checked) {
			checkCount = rowNumber - 1;
		}
		else {
			checkCount = 0;
		}
	}
	// 상품 개별 선택 후 주문 금액 증가 및 감소
	function sumPrice(id) {
		if (document.getElementById(id).checked == true) {
			cartPrice += parseInt(document.getElementById(id).value);
			++checkCount;
		}
		else {
			cartPrice -= parseInt(document.getElementById(id).value);
			--checkCount;
		}
		if (checkCount == 0) {
			document.cartForm.checkAll.checked = false;
		}
	}
	// 선택 상품 개수 확인
	function countCheck(id) {
		var selectedTable = document.getElementById("selectedTable");
		if (document.getElementById(id).checked == true) {
			selectedTable.rows[0].cells[1].innerHTML = "선택상품(" + checkCount + "개)";
		}
		else {
			selectedTable.rows[0].cells[1].innerHTML = "선택상품(" + checkCount + "개)";
		}
	}
	// 총 주문 금액
	function totalPrice(count) {
		var priceTable = document.getElementById("priceTable");
		var deliveryFee = 0;
		
		for (var g = 1; g <= count; g++) {
			if (document.getElementById('c'+g).checked == true) {
				var tempSeller = document.getElementById('seller'+g).value;
				deliveryFee += parseInt(document.getElementById('delivery'+g).value);
				for (var h = 1; h <= count; h++) {
					if (tempSeller == document.getElementById('seller'+h).value
							&& document.getElementById('c'+h).checked == true) {
						deliveryFee += 0;
					}
					else if (document.getElementById('c'+h).checked == true){
						deliveryFee += parseInt(document.getElementById('delivery'+h).value);
					}
				}
				break;
			}
		}
		priceTable.rows[1].cells[0].innerHTML = cartPrice + "원";
		priceTable.rows[1].cells[1].innerHTML = deliveryFee + "원";
		priceTable.rows[1].cells[2].innerHTML = cartPrice + deliveryFee + "원";
		document.getElementById('deliveryTotal').value = deliveryFee;
	}
	// 장바구니 변경 창 출력
	function changeCartWithPopUp(id) {
		var cartId = document.getElementById(id).id;
		var popUpAction = "/ojhmall/cart/changeCartView.do?cartNumber="+cartId;
		var popUpOption = "width=500, height=600";
		window.open(popUpAction,"",popUpOption);
	}
	// 체크박스 체크하여 주문정보 전달
	function selectedCheck(count) {
		var totalPrdNumber = "init";
		var totalPrdAmount = "";
		var totalCartPrice = "";
		var totalCartNumber = "";
		for (var t = 1; t <= count; t++) {
			var tOrder = 'c' + t;
			var getPNumber = 'prdN' + t;
			var getPAmount = 'prdA' + t;
			var getCartP = 'cartP' + t;
			var getCNumber = 'cartN' + t;
			
			if (document.getElementById(tOrder).checked == true) {
				if (totalPrdNumber == "init") {
					totalPrdNumber = "";
				}
				else {
					totalPrdNumber += "@";
					totalPrdAmount += "@";
					totalCartPrice += "@";
					totalCartNumber += "@";
				}
				totalPrdNumber += document.getElementById(getPNumber).value;
				totalPrdAmount += document.getElementById(getPAmount).value;
				totalCartPrice += document.getElementById(getCartP).value;
				totalCartNumber += document.getElementById(getCNumber).value;
			}
			document.getElementById("prdNumberTotal").value = totalPrdNumber;
			document.getElementById("prdAmountTotal").value = totalPrdAmount;
			document.getElementById("cartPriceTotal").value = totalCartPrice;
			document.getElementById("cartNumberTotal").value = totalCartNumber;
		}
	}
	// 배송비 체크
	function calDelivery(count) {
		var tempPrice = 0;
		var cartCount = 0;
		var cartTable = document.getElementById("cartTable");
		for (var c = 1; c <= count; c++) {
			tempPrice = 0;
			var cOrder = 'c' + c;
			var sellerNumber = 'seller' + c;
			var cPrice = 'cartP' + c;
			cartCount++;
			if (document.getElementById(cOrder).checked == true) {
				
				var tempUserNumber = document.getElementById(sellerNumber).value;
				tempPrice += parseInt(document.getElementById(cPrice).value);
				
				for (var d = 1; d <= count; d++) {
					var dOrder = 'c' + d;
					var sellerTemp = 'seller' + d;
					if (document.getElementById(dOrder).checked == true
							&& tempUserNumber == document.getElementById(sellerTemp).value
							&& cartCount != d) {
						tempPrice += parseInt(document.getElementById('cartP'+d).value);
					}
				}
				if (tempPrice > 30000) {
					for (var e = 1; e <= count; e++) {
						if (document.getElementById('seller'+e).value == tempUserNumber) {
							document.getElementById('delivery'+e).value = 0;
							cartTable.rows[e].cells[4].innerHTML = "무료";
						}
					}
				}
				else {
					for (var f = 1; f <= count; f++) {
						if (document.getElementById('seller'+f).value == tempUserNumber) {
							document.getElementById('delivery'+f).value = 3000;
							cartTable.rows[f].cells[4].innerHTML = "3000원";
						}
					}
				}
			}
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
		<!-- 장바구니 테이블 -->
		<div class="col-md-12">
			<form name="cartForm">
				<table class="table table-striped" id="cartTable">
					<thead>
						<tr>
							<th><label for="checkAll"><input type="checkbox"
									name="checkAll" id="checkAll"
									onclick="check(${fn:length(cartList)});totalPrice(${fn:length(cartList)});countCheck(this.id);"
									title="전체상품선택"></label></th>
							<th>상품정보</th>
							<th>수량</th>
							<th>상품금액</th>
							<th>배송비</th>
							<th>판매자</th>
							<th>주문</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${fn:length(cartList) > 0 }">
								<c:forEach items="${cartList}" var="row" varStatus="status">
									<tr id="cartTR">
										<td><input type="checkbox" name="c${status.count}"
											value="${row.cartPrice}" id="c${status.count}"
											onclick="sumPrice(this.id);calDelivery(${fn:length(cartList)});totalPrice(${fn:length(cartList)});countCheck(this.id);"></td>
										<td width=40% height="120"><img style="float: left"
											src="${row.image }" width="120" height="120">
											<h5>${row.prdName}</h5></td>
										<td>${row.prdAmount}개<br> <a class="btn btn-default"
											id="${row.cartNumber}" target="Window" role="button"
											onclick="changeCartWithPopUp(this.id)">변경</a> <input
											type="hidden" id="prdA${status.count}"
											value="${row.prdAmount}"> <input type="hidden"
											id="prdN${status.count}" value="${row.prdNumber}"> <input
											type="hidden" id="cartN${status.count}"
											value="${row.cartNumber}">
										</td>
										<td>${row.cartPrice}원<input type="hidden"
											id="cartP${status.count}" value="${row.cartPrice}">
										</td>
										<c:if test="${row.deliveryFee == 0}">
											<td>무료</td>
										</c:if>
										<c:if test="${row.deliveryFee != 0}">
											<td>${row.deliveryFee}원</td>
										</c:if>
										<td>${row.id}<input type="hidden"
											id="delivery${status.count }" value="${row.deliveryFee }">
											<input type="hidden" id="seller${status.count}"
											value="${row.user_userNumber}">
										</td>
										<td><a class="btn btn-danger"
											href="../order/orderOneFromCart.do?cartNumber=${row.cartNumber}">주문하기</a>
											<br> <input type="hidden" id="cartNumber"
											name="cartNumber" value="${row.cartNumber}"> <a
											class="btn btn-default"
											href="deleteCart.do?cartNumber=${row.cartNumber}"
											role="button">삭제하기</a></td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</tbody>
				</table>
			</form>
		</div>

		<div class="col-md-3">
			<table class="table" id="selectedTable">
				<thead>
					<tr>
						<td><input type="checkbox" name="selectedCheckbox" value=""
							id="selectedCheckbox" onclick=""></td>
						<td>선택상품(${fn:length(cartList)}개)</td>
						<td><button type="button" class="btn btn-default">삭제하기</button></td>
					</tr>
				</thead>
			</table>
		</div>
		<!-- 가격 정보 -->
		<div class="col-md-12">
			<form>
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
							<td>${totalCartPrice}원</td>
							<td>${totalDeliveryFee}원</td>
							<td>${totalCartPrice}원</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<div class="col-md-12" style="text-align: right">
			<form id="selectedOrder" action="../order/orderFromCartSelected.do"
				method="post">
				<input type="hidden" id="prdNumberTotal" name="prdNumberTotal"
					value=""> <input type="hidden" id="prdAmountTotal"
					name="prdAmountTotal" value=""> <input type="hidden"
					id="cartPriceTotal" name="cartPriceTotal" value=""> <input
					type="hidden" id="cartNumberTotal" name="cartNumberTotal" value="">
				<input type="hidden" id="deliveryTotal" name="deliveryTotal"
					value="${totalDeliveryFee}">
				<button type="submit" class="btn btn-danger"
					onclick="selectedCheck(${fn:length(cartList)})">선택상품주문</button>

			</form>
			<form id="totalOrder" action="../order/orderFromCartTotal.do"
				method="post">
				<input type="hidden" id="deliveryTotal" name="deliveryTotal"
					value="${totalDeliveryFee}"> <input type="submit"
					class="btn btn-success" value="전체상품주문" role="button">
			</form>
		</div>
	</div>
	<!-- Site footer -->
	<footer class="footer">
		<p>&copy; Company 2014</p>
	</footer>
	<!-- /container -->

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

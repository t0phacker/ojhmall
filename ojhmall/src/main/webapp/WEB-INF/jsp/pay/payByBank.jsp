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

</head>

<body>

	<div class="container">
		<h2>"11번가 간편 무통장 입금 시스템"</h2>
		<div class="col-md-12" style="text-align: center">
			<table class="table table-striped" id="adminTable">
				<thead>
					<tr>
						<th>계좌번호</th>
						<th>보내실 금액</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${adminInfo.accNum }(예금주 :${adminInfo.userName })</td>
						<td>${orderInfo.totalPrice + orderInfo.totalDelivery }원</td>
					</tr>
				</tbody>
			</table>
			<form name="payForm" action="../mypage/completePay.do">
				<table class="table table-striped" id="cartTable">
					<tbody>
						<tr>
							<td>계좌번호 입력 :</td>
							<td><input type="text" id="accNumInput" name="accNum" value=""></td>
						</tr>
						<tr>
							<td>금액 입력 :</td>
							<td><input type="text" id="moneyInput" name="money" value=""></td>
						</tr>
					</tbody>
				</table>
				<input type="text" id="userNumber" name="userNumber" value="${userInfo.userNumber} ">
				<input type="text" id="orderNumber" name="orderNumber" value="${orderInfo.orderNumber} ">
				<input type="submit" class="btn btn-danger" value="입금하기"
					onclick="sendForm();"> <a class="btn btn-default"
					onclick="window.close()">닫기</a>
			</form>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

	<script type="text/javascript">
		
		// 상품 수량, 총주문금액 폼에 전송
		function sendInfo() {
			var testAmount = document.getElementById("accNumInput").value;
			var prdPrice = document.getElementById("moneyInput").value;
			document.getElementById("prdAmount").value = testAmount;
			document.getElementById("cartPrice").value = prdPrice * testAmount;
		}
		function sendForm() {
			if (window.opener && !window.opener.closed) {
				window.opener.location = "http://localhost:8080/ojhmall"
				alert("이용해주셔서 감사합니다. 입금이 확인되는데로 상품을 출고해드리겠습니다.");
				window.close();
			} else {
				alert("기존 페이지가 닫혀있습니다.");
			}
		}
	</script>
</body>
</html>

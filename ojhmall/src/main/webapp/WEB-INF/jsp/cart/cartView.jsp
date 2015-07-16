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
	function selectAllorNot(id) {
		
		var selectedTable = document.getElementById("selectedTable");
		if (document.cartForm.checkAll.checked) {
			alert("selectAllorNot" + selectedTable.rows[0].cells[1].value);
			countCheck = rowNumber-1;
			selectedTable.rows[0].cells[1].innerHTML = "선택상품(" + checkCount + "개)";
		}
		else {
			countCheck = 0;
			selectedTable.rows[0].cells[1].innerHTML = "선택상품(" + checkCount + "개)";
		}
	}
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
	function countCheck(id) {
		var selectedTable = document.getElementById("selectedTable");
		if (document.getElementById(id).checked == true) {
			selectedTable.rows[0].cells[1].innerHTML = "선택상품(" + checkCount + "개)";
		}
		else {
			selectedTable.rows[0].cells[1].innerHTML = "선택상품(" + checkCount + "개)";
		}
	}
	function totalPrice() {
		var priceTable = document.getElementById("priceTable");
		priceTable.rows[1].cells[0].innerHTML = cartPrice + "원";
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
					href="cartView.do?userNumber=${sessionScope.userLogInInfo.userNumber}">장바구니</a></li>
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
						placeholder="Search" value="${prdName}" />
				</div>
				<button type="submit" class="btn btn-default" onclick="search();">Submit</button>
			</form>
		</div>
		<!-- 장바구니 테이블 -->
		<div class="col-md-12">
			<form name="cartForm">
				<table class="table table-striped" id="cartTable">
					<thead>
						<tr>
							<th><label for="checkAll"><input type="checkbox"
									name="checkAll" id="checkAll"
									onclick="check(${fn:length(cartList)});totalPrice();countCheck(this.id);"
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
									<tr>
										<td><input type="checkbox" name="c${status.count}"
											value="${row.cartPrice}" id="c${status.count}"
											onclick="sumPrice(this.id);totalPrice();countCheck(this.id);"></td>
										<td width=40% height="120"><img style="float: left"
											src="${row.image }" width="120" height="120">
											<h5>${row.prdName}</h5></td>
										<td>${row.prdAmount}개</td>
										<td>${row.cartPrice}원</td>
										<c:if test="${row.deliveryFee == 0}">
											<td>무료</td>
										</c:if>
										<c:if test="${row.deliveryFee != 0}">
											<td>${row.deliveryFee}</td>
										</c:if>

										<td>${row.id}</td>
										<td>
											<form>
												<button type="button" class="btn btn-danger"
													onclick="testFunc();">주문하기</button>
											</form>
											<form>
												<button type="button" class="btn btn-default">삭제하기</button>
											</form>
										</td>
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

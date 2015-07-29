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

<title>My Page For Customer</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<link href="../css/justified-nav.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="../css/theme.css" rel="stylesheet">
<!-- 검색 js -->
<script src="../js/ojhfunction.js"></script>

<script type="text/javascript">
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

	// 셀 합치기
	function init() {
		cellMergeChk(document.all.orderTable, 1, 0);
	}
	function cellMergeChk(tableObj, rowIndex, cellIndex) {
		var rowsCn = tableObj.rows.length;

		if (rowsCn - 1 > rowIndex)
			cellMergeProcess(tableObj, rowIndex, cellIndex);
	}

	function cellMergeProcess(tableObj, rowIndex, cellIndex) {
		var rowsCn = tableObj.rows.length;
		var compareCellsLen = tableObj.rows[rowIndex].cells.length;

		var compareObj = tableObj.rows[rowIndex].cells[cellIndex];
		var compareValue = compareObj.innerHTML;
		var cn = 1;
		var delCells = new Array();
		var arrCellIndex = new Array();
		for (i = rowIndex + 1; i < rowsCn; i++) {
			var cellsLen = tableObj.rows[i].cells.length;
			var bufCellIndex = cellIndex

			if (compareCellsLen != cellsLen) {
				bufCellIndex = bufCellIndex - (compareCellsLen - cellsLen);
			}
			cellObj = tableObj.rows[i].cells[bufCellIndex];

			if (compareValue == cellObj.innerHTML) {
				delCells[cn - 1] = tableObj.rows[i];
				arrCellIndex[cn - 1] = bufCellIndex;
				cn++;
			} else {

				compareObj.rowSpan = cn;

				for (j = 0; j < delCells.length; j++) {
					delCells[j].deleteCell(arrCellIndex[j]);
				}

				compareObj = cellObj;
				compareValue = cellObj.innerHTML;
				cn = 1;
				delCells = new Array();
				arrCellIndex = new Array();
			}
		}

		compareObj.rowSpan = cn;

		for (j = 0; j < delCells.length; j++) {
			delCells[j].deleteCell(arrCellIndex[j]);
		}
	}
	// 결제 창 출력
	function openPay(id) {
		var orderId = document.getElementById(id).id;
		var popUpAction = "/ojhmall/pay/payOrder.do?orderNumber=" + orderId;
		var popUpOption = "width=500, height=600";
		window.open(popUpAction, "11번가 은행 결제시스템", popUpOption);
	}
</script>
</head>

<body onload='init()'>

	<div class="container">
		<div class="masthead">
			<a href="http://localhost:8080/ojhmall"><img
				src="../image/11st.png"></a>

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
						<li><a href="mypageForCustomer.do">마이페이지</a></li>
					</c:when>

					<c:when test="${sessionScope.userLogInInfo.userType == 0}">
						<li><a href="../user/AdminInfoForm.do">회원정보</a></li>
						<li><a href="../cart/cartView.do">장바구니</a></li>
						<li><a href="mypageForAdmin.do">마이페이지</a></li>
					</c:when>
					<c:when test="${sessionScope.userLogInInfo.userType == 1}">
						<li><a href="../user/CustomerInfoForm.do">회원정보</a></li>
						<li><a href="../cart/cartView.do">장바구니</a></li>
						<li><a href="mypageForCustomer.do">마이페이지</a></li>
					</c:when>
					<c:when test="${sessionScope.userLogInInfo.userType == 2}">
						<li><a href="../user/SellerInfoForm.do">회원정보</a></li>
						<li><a href="../cart/cartView.do">장바구니</a></li>
						<li><a href="mypageForSeller.do">마이페이지</a></li>
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
		<!-- 주문 정보 확인 -->
		<div class="container">
			<ul class="nav navbar-nav navbar-right">
				<li><h3>
						<a href="mypageForAdmin.do"> <span class="label label-danger"
							style="margin-right: 10px;">입금 확인/송금</span>
						</a>
					</h3></li>
				<li><h3>
						<a href="mypageAdminCheckPrd.do"> <span
							class="label label-primary" style="margin-right: 30px;">상품
								관리</span>
						</a>
					</h3></li>
			</ul>
			<h2>관리자 상품 관리 페이지</h2>
			<div class="col-md-12">
				<table class="table table-striped" id="payTable">
					<thead>
						<tr>
							<th>상품 번호</th>
							<th>판매자</th>
							<th>상품 정보</th>
							<th>등록일</th>
							<th>가격</th>
							<th>재고</th>
							<th>상태</th>
							<th>승인</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${fn:length(prdList) > 0 }">
								<c:forEach items="${prdList }" var="row" varStatus="status">
									<tr>
										<td>${row.prdNumber }<br></td>
										<td>${row.id }</td>
										<td width=450><img style="float: left"
											src="${row.image }" width="100" height="100"> 이름:
											${row.prdName} <br> 설명: ${row.text }</td>
										<td>${row.newDate }</td>
										<td>${row.price }</td>
										<td>${row.stock}</td>
										<td><c:if test="${row.prdStatus == 1 }">
												<h4>
													<span class="label label-info">등록대기</span>
												</h4>
											</c:if> <c:if test="${row.prdStatus == 2 }">
												<h3>
													<span class="label label-info">전시</span>
												</h3>
											</c:if></td>
										<td><form action="changePrdStatus.do" method="POST">
												<input type="hidden" name="prdNumber"
													value="${row.prdNumber }"> <input type="submit"
													class="btn btn-danger" value="승인" role="button">
											</form></td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>

					</tbody>
				</table>
			</div>

			<!-- Site footer -->
			<footer class="footer">
				<p>&copy; Company 2014</p>
			</footer>
		</div>
	</div>
	<!-- /container -->
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

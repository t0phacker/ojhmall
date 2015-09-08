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
		<!-- 로고 & 회원메뉴 -->
		<jsp:include page="/WEB-INF/jsp/layout/userBar.jsp" />
		<!-- 카테고리 바 -->
		<jsp:include page="/WEB-INF/jsp/layout/ctgrBar.jsp" />
		<!-- 상품 검색 -->
		<jsp:include page="/WEB-INF/jsp/layout/srchBar.jsp" />
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
			<h2>주문정보 확인</h2>
			<div class="col-md-12">
				<h4>최근 주문 정보</h4>
				<table class="table table-striped" id="orderTable">
					<thead>
						<tr>
							<th>주문일자</th>
							<th>주문 상품 정보</th>
							<th>상품금액(수량)</th>
							<th>배송비(판매자)</th>
							<th>주문상태</th>
							<th>확인/취소/리뷰</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${fn:length(orderList) > 0 }">
								<c:forEach items="${orderList }" var="row" varStatus="status">
									<tr>
										<td width=15%>${row.orderNewDate }<br> <c:if
												test="${row.orderStatus < 3 }">
												<a class="btn btn-danger" id="${row.orderNumber}"
													target="Window" onclick="openPay(this.id);"
													style="display: block">결제하기</a>
											</c:if> ${row.orderNumber}
										</td>
										<td width=35% height="120"><img style="float: left"
											src="${row.image }" width="120" height="120">
											<h5>${row.prdName}</h5></td>
										<td>${row.price }원</td>
										<c:if test="${row.deliveryFee == 0}">
											<td>무료 <br> ${row.id }
											</td>
										</c:if>
										<c:if test="${row.deliveryFee != 0}">
											<td>${row.deliveryFee}원<br> ${row.id }
											</td>
										</c:if>
										<td><c:if test="${row.orderStatus == 2 }">
												<h4>
													<span class="label label-info">결제확인 중</span>
												</h4>
											</c:if> <c:if test="${row.orderStatus == 3 }">
												<h4>
													<span class="label label-info">결제완료</span>
												</h4>
											</c:if> <c:if test="${row.orderStatus == 4 }">
												<h4>
													<span class="label label-info">배송중</span>
												</h4>
											</c:if> <c:if test="${row.orderStatus == 5 }">
												<h3>
													<span class="label label-info">배송완료</span>
												</h3>
											</c:if></td>
										<td><form action="../order/deleteOrder.do" method="POST">
												<input type="hidden" name="orderNumber"
													value="${row.orderNumber}"> <input type="hidden"
													name="prdNumber" value="${row.prdNumber}">
												<c:if test="${row.orderStatus < 3 }">
													<input type="submit" class="btn btn-default" value="주문취소"
														role="button">
												</c:if>
											</form> <c:if test="${row.orderStatus == 4 }">
												<a type="button" class="btn btn-default"
													href="checkDelivery.do?orderNumber=${row.orderNumber}"
													role="button">배송확인</a>
											</c:if></td>
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
